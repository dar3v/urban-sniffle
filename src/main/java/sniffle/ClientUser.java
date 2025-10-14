package sniffle;

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

    public void setPort(int port) {
        // TODO some sort of exception handling perhaps...
        this.port = port;
    }

    @Override
    public void login() {
        // TODO code here
    }

    public void connectToServer() {
        // TODO code here
    }
}
