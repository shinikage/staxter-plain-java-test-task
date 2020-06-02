package com.staxter.system;

public abstract class PlayerSystem<TMessage> {

    public abstract void startMessaging();

    public static class PlayerSystemCreationException extends RuntimeException {
        public PlayerSystemCreationException(String message) {
            super(message);
        }
    }
}
