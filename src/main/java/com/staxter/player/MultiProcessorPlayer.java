package com.staxter.player;

import com.staxter.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MultiProcessorPlayer extends UnicastRemoteObject implements Player<Message>, Remote {

    private static final long serialVersionUID = 1L;
    
    protected MultiProcessorPlayer() throws RemoteException {
        super();
    }

    @Override
    public Message send(Player<Message> receiver, Message message) {
        return null;
    }

    @Override
    public Message receive(Player<Message> sender, Message message) {
        return null;
    }
}
