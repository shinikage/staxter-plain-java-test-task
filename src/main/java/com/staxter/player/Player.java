package com.staxter.player;

public interface Player<TMessage> {

    TMessage send(Player<TMessage> receiver, TMessage message);
    TMessage receive(Player<TMessage> sender, TMessage message);

    class PlayerException extends RuntimeException {
        public PlayerException(String message) {
            super(message);
        }
    }
}
