package com.staxter.system;

/**
 * Represents an interface for system of players,
 * which send messages to each other.
 *
 */
public interface PlayerSystem {

    /**
     * A method to initiate messaging process between players.
     * The players are initialized and then the messaging is started.
     */
    void startMessaging();

    class PlayerSystemCreationException extends RuntimeException {
        public PlayerSystemCreationException(String message) {
            super(message);
        }
    }
}
