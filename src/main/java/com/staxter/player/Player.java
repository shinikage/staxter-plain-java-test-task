package com.staxter.player;

import java.util.Queue;

/**
 * A PLayer interface.
 * Specifies a Player entity, which is able to send and receive messages.
 *
 * @param <TMessage> is a type of message object, which will be transmitted.
 */
public interface Player<TMessage> {

    /**
     * Sends exactly one message to writeQueue.
     *
     * @param writeQueue A queue where the message should be sent
     * @param message A message
     */
    void send(Queue<TMessage> writeQueue, TMessage message);

    /**
     * Reads exactly one message from the queue and returns it.
     *
     * @param readQueue A queue to read from
     * @return message A received message
     */
    TMessage receive(Queue<TMessage> readQueue);

    class PlayerException extends RuntimeException {
        public PlayerException(String message) {
            super(message);
        }
    }
}
