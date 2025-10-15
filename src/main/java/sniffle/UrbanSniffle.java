package sniffle;

import java.io.*;

public class UrbanSniffle {
    public static void main(String[] args) {
        try {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Run as (s)erver or (c)lient? ");
            String choice = console.readLine().trim().toLowerCase();

            if (choice.equals("s")) {
                System.out.print("Enter server name: ");
                String name = console.readLine().trim();
                System.out.print("Enter port number: ");
                int port = Integer.parseInt(console.readLine().trim());

                ServerUser server = new ServerUser(name, port);
                server.login();
                server.startServer();

            } else if (choice.equals("c")) {
                System.out.print("Enter your name: ");
                String name = console.readLine().trim();
                System.out.print("Enter server IP: ");
                String ip = console.readLine().trim();
                System.out.print("Enter port number: ");
                int port = Integer.parseInt(console.readLine().trim());

                ClientUser client = new ClientUser(name, ip, port);
                client.login();
                client.connectToServer();

            } else {
                System.out.println("Invalid choice.");
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
