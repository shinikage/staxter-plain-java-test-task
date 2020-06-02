package com.staxter.player;

import com.staxter.Message;

import java.util.function.Function;

public class PlainPlayer implements Player<Message> {

    private final String name;
    //private final BiFunction<Integer, Integer, Boolean> onStopCondition;
    private final Function<Message, Message> messageBuilder;
    private int sentMessages;
    private int receivedMessages;

    public PlainPlayer(String playerName,
                       //BiFunction<Integer, Integer, Boolean> onStopCondition,
                       Function<Message, Message> messageBuilder) {
        //this.onStopCondition = onStopCondition;
        this.name = playerName;

        if(messageBuilder == null) {
            throw new PlayerException("Cannot create player because message builder should not be null!");
        }
        this.messageBuilder = messageBuilder;
    }

//    @Override
//    public void startMessaging(Player<Message> receiver, Message message) {
//        this.send(receiver, message);
//    }

    @Override
    public Message send(Player<Message> receiver, Message message) {
        if (this == receiver) {
            throw new PlayerException("Player cannot send messages to itself!");
        }

        if (receiver == null) {
            throw new PlayerException("Cannot send a message if the receiver is null!");
        }

        if (message == null) {
            throw new PlayerException("Cannot send null message!");
        }

//        if (onStopCondition != null && onStopCondition.apply(sentMessages, receivedMessages)) {
//            System.out.println("Player " + name + " has stopped when sent " + sentMessages + "th " +
//                    "message");
//        }

        Message newMessage = this.messageBuilder.apply(message);
        this.sentMessages++;
        System.out.println("Player " + name + " sent message " + newMessage.toString());
        return receiver.receive(this, newMessage);
    }

    @Override
    public Message receive(Player<Message> sender, Message message) {
        if (this == sender) {
            throw new PlayerException("Player cannot receive messages from itself!");
        }

        if (sender == null) {
            throw new PlayerException("Sender cannot be null!");
        }

        if (message == null) {
            throw new PlayerException("Cannot receive null message!");
        }

        System.out.println("Player " + name + " received message " + message.toString());
        this.receivedMessages++;

//        if (onStopCondition != null && onStopCondition.apply(sentMessages, receivedMessages)) {
//            System.out.println("Player " + name + " has stopped when received " + receivedMessages + "th " +
//                    "message");
//            return;
//        }
        return message;
    }

    public int getSentMessages() {
        return sentMessages;
    }

    public int getReceivedMessages() {
        return receivedMessages;
    }
}
