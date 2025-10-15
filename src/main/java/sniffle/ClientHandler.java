package sniffle;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private ServerUser server;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String clientName;
    
    public ClientHandler(ServerUser server, Socket socket) {
        setServer(server);
        setSocket(socket);
    }

    private void setServer(ServerUser server) {
        // TODO exception handling
        this.server = server;
    }

    private void setSocket(Socket socket) {
        // TODO exception handling
        this.socket = socket;
    }

    public String getClientName() {
        return clientName;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Enter your name:");
            clientName = reader.readLine();
            System.out.println(" Connected: " + clientName);

            server.broadcast(clientName + " joined the chat!", this);

            String incoming;
            while ((incoming = reader.readLine()) != null) {
                try {
                    System.out.println("[" + clientName + "]: " + incoming);
                    server.broadcast(incoming, this);
                } catch (Exception e) {
                    System.err.println("Decrypt fail from " + clientName + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("ClientHandler error: " + e.getMessage());
        } finally {
            server.removeClient(this);
            server.broadcast("📢 " + clientName + " left.", this);
            try { socket.close(); } catch (IOException ignored) {}
        }
    }
}
