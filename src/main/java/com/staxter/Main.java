package com.staxter;

import com.staxter.system.PlainSystem;

/**
 * Main class to start the application.
 */
public class Main {

    public static void main(String[] args) {

        PlainSystem s = new PlainSystem(10);
        s.startMessaging();
    }
}
