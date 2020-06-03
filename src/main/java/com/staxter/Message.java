package com.staxter;

/**
 * Represents a message object.
 * Contains plain text string and a counter.
 * Counter and string could be used in any way.
 */
public class Message {

    private final int counter;
    private final String message;

    public Message(int counter, String message) {
        this.counter = counter;
        this.message = message;
    }

    public int getCounter() {
        return this.counter;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
