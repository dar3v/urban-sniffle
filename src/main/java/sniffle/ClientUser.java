package sniffle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientUser extends User {
    private String serverIP;
    private int port;

    public ClientUser(String username, String serverIP, int port) {
        super(username);
        setServerIP(serverIP);
        setPort(port);
    }

    private void setServerIP(String serverIP) {
        // TODO some sort of exception handling perhaps...
        this.serverIP = serverIP;
    }

    private void setPort(int port) {
        if (port <= 1024 || port > 65535) {
            throw new IllegalArgumentException("Invalid port");
        }
        this.port = port;
    }

    @Override
    public void login() {
        System.out.println("Client " + username + " is ready to connect");
    }

    public void connectToServer() {
        try {
            socket = new Socket(serverIP, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to server.");

            writer.println(username);
            System.out.println("You can now chat:");
            System.out.print("Me: "); // initial prompt

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = reader.readLine()) != null) {
                        System.out.print("\r" + " ".repeat(100) + "\r"); // clear current line
                        System.out.println(msg);
                        System.out.print("Me: ");
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected.");
                }
            }).start();

            // Read console & send
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String line;

            while ((line = console.readLine()) != null) {
                if (!line.trim().isEmpty()) {

                    writer.println(line);
                }
                System.out.print("Me: ");
            }

        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
