package sniffle;

import java.net.*;
import java.security.*;
import java.util.*;

public class ServerUser extends User {
    private int port;
    private ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();

    public ServerUser(String username, int port) {
        super(username);
        this.port = port;
    }

    private void setPort(int port) {
        // TODO some excepton handling perhaps..
        this.port = port;
    }

    @Override
    public void login() {
        // TODO code here
    }

    public void startServer() {
        // TODO code here
    }

    public synchronized void broadcast(String plain, ClientHandler sender) {
        // TODO code here
    }

    public synchronized void removeClient(ClientHandler c) { clients.remove(c); }
    public PublicKey getPublicKey() { return publicKey; }
    public PrivateKey getPrivateKey() { return privateKey; }
}
