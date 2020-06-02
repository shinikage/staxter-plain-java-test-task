package com.staxter.system;

import com.staxter.Message;
import com.staxter.player.PlainPlayer;
import com.staxter.player.Player;

import java.util.Optional;

public class PlainSystem extends PlayerSystem<Message> {

    private final int maxMessagesCount;

    public PlainSystem(int maxMessagesCount) {
        if (maxMessagesCount <= 0) {
            throw new PlayerSystemCreationException("Cannot create player system because max messages count should be" +
                    " bigger than zero!");
        }
        this.maxMessagesCount = maxMessagesCount;
    }

    @Override
    public void startMessaging() {
        PlainPlayer first = new PlainPlayer("first", this::buildMessage);
        PlainPlayer second = new PlainPlayer("second", this::buildMessage);
        Message m = new Message(1, "Hello ");
        while(first.getSentMessages() != maxMessagesCount && first.getReceivedMessages() != maxMessagesCount){
            m = first.send(second, m);
            m = second.send(first, m);
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
