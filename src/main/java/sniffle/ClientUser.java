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
        // TODO some excepton handling perhaps..
        if (port <= 1024 || port > 65535) {
            throw new IllegalArgumentException("Invalid port");
        }
        this.port = port;
    }

    @Override
    public void login() {
        // TODO code here
        System.out.println("Client " + username + "is ready to connect");
    }

    public void connectToServer() {
        try {
            socket = new Socket(serverIP, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to server.");

            writer.println(username);


            // Thread to receive plain messages (no more decryption)
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = reader.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected.");
                }
            }).start();


            // Read console & send
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String line;
            System.out.print("Me: ");
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
