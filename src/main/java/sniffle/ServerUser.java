package sniffle;

import java.net.*;
import java.security.*;
import java.util.*;
import java.io.*;

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
        if (port <= 1024 || port > 65535) {
            throw new IllegalArgumentException("Invalid port");
        }
        this.port = port;
    }

    @Override
    public void login() {
        // TODO code here
        System.out.println("Server " + username + " ready on port " + port);

    }

    public void startServer() {
        // TODO code here
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started: Listening on port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(this, clientSocket);
                clients.add(handler);
                new Thread(handler).start();
            }
            } catch (IOException e)
            {
                System.err.println("Server error: " + e.getMessage());
            }
        }

    public synchronized void broadcast(String plain, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage("[" + sender.getClientName() + "]: " + plain);
            }
        }
    }


    public synchronized void removeClient(ClientHandler c) { clients.remove(c); }

    //removed getPublicKey and getPrivateKey
}
