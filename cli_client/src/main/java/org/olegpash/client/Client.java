package org.olegpash.client;


public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        ClientWorker clientWorker = new ClientWorker();
        clientWorker.startClientWorker();
    }
}