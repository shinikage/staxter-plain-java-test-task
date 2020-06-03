package com.staxter.player;

import com.staxter.Message;

import java.util.Queue;
import java.util.function.Function;

/**
 * Implements plain not thread-safe one-process player
 */
public class PlainPlayer implements Player<Message> {

    private final String name;
    private final Function<Message, Message> messageBuilder;
    private int sentMessages;
    private int receivedMessages;

    public PlainPlayer(String playerName,
                       Function<Message, Message> messageBuilder) {
        this.name = playerName;

        if (messageBuilder == null) {
            throw new PlayerException("Cannot create player because message builder should not be null!");
        }
        this.messageBuilder = messageBuilder;
    }

    /**
     * Constructs a new message on the basis of message object, adds new message to the queue
     * and increments sent messages counter.
     *
     * @param writeQueue A queue where the message should be sent
     * @param message A message
     */
    @Override
    public void send(Queue<Message> writeQueue, Message message) {
        if (writeQueue == null) {
            throw new PlayerException("Cannot send a message if the queue is null!");
        }

        if (message == null) {
            throw new PlayerException("Cannot send a message if the message is null!");
        }

        Message newMessage = this.messageBuilder.apply(message);

        System.out.println("Player " + name + " sent message " + newMessage.toString());
        writeQueue.add(newMessage);
        this.sentMessages++;
    }

    /**
     * Reads a message from queue and returns the received message.
     * Increments the counter of received messages.
     *
     * @param readQueue A queue to read from
     * @return message obtained from the queue
     */
    @Override
    public Message receive(Queue<Message> readQueue) {
        if (readQueue == null) {
            throw new PlayerException("Cannot receive a message if the queue is null!");
        }
        Message message = readQueue.poll();
        if (message == null) {
            throw new PlayerException("Cannot receive a message! Queue is empty");
        }

        this.receivedMessages++;
        System.out.println("Player " + name + " received message " + message.toString());
        return message;
    }

    public int getSentMessages() {
        return sentMessages;
    }

    public int getReceivedMessages() {
        return receivedMessages;
    }
}
