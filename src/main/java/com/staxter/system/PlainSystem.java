package com.staxter.system;

import com.staxter.Message;
import com.staxter.player.PlainPlayer;
import com.staxter.player.Player;

import java.util.LinkedList;
import java.util.Optional;

/**
 * A plain single-threaded single-process implementation of the system of players.
 */
public class PlainSystem implements PlayerSystem {

    private final int maxMessagesCount;

    /**
     * Creates a system
     * @param maxMessagesCount a maximum number of messages which messaging initiator can send and receive.
     */

    public PlainSystem(int maxMessagesCount) {
        if (maxMessagesCount <= 0) {
            throw new PlayerSystemCreationException("Cannot create player system because max messages count should be" +
                    " bigger than zero!");
        }
        this.maxMessagesCount = maxMessagesCount;
    }

    /**
     * Creates two players and two queues for messages.
     * First player writes to the first queue and the second player reads messages from the first queue.
     * Second player writes to the second queue and the first player reads messages from the second queue.
     * The process happens until the number of send messages and the number of received messages reach the maximum
     * messages counter, specified in the constructor.
     */
    @Override
    public void startMessaging() {
        PlainPlayer first = new PlainPlayer("first", this::buildMessage);
        PlainPlayer second = new PlainPlayer("second", this::buildMessage);
        Message message = new Message(1, "Hello ");

        //first player sends messages to this queue and the second player reads from it
        LinkedList<Message> firstQueue = new LinkedList<>();
        //second player sends messages to this queue and the first player reads from it
        LinkedList<Message> secondQueue = new LinkedList<>();

        while(first.getSentMessages() != maxMessagesCount && first.getReceivedMessages() != maxMessagesCount){
            first.send(firstQueue, message);
            message = second.receive(firstQueue);
            second.send(secondQueue, message);
            message = first.receive(secondQueue);
        }
    }

    protected Message buildMessage(Message message) {
        return Optional.ofNullable(message)
                .map(msg -> {
                            String messageText = msg.getMessage() == null ? "" : msg.getMessage();
                            return new Message(
                                    msg.getCounter() + 1,
                                    messageText + " " + msg.getCounter()
                            );
                        }
                )
                .orElseThrow(() -> new Player.PlayerException("Cannot build message because message is null!"));
    }
}
