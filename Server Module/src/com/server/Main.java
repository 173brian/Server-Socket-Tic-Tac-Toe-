package com.server;

public class Main {
    public static void main(String[] args) {
        try
        {
            Server server = new Server(5000); // creates a new server and specifies the location of the server port
        }
        catch (java.io.IOException exception)
        {
            System.out.println("IO exception caught, view debug for more information."); // handles I/O exceptions that creating a server could cause
        }
    }
}