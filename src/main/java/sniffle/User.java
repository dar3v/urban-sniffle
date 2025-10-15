package sniffle;

import java.io.*;
import java.net.*;
import java.security.*;

// Parent user class
public abstract class User {
    protected String username;
    protected Socket socket;
    protected BufferedReader reader;
    protected PrintWriter writer;

  /*  protected PublicKey publicKey;
    protected PrivateKey privateKey;
    protected PublicKey serverPublicKey;*/
    //removed keys for the encryption

    // Constructor
    public User(String username) {
        setUsername(username);
    }

    public abstract void login();

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        // TODO username too long error
        this.username = username;
    }
}
