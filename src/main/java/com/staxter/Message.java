package com.staxter;

public class Message {

    private int counter;
    private String message;

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
