package com.client;

public class Main {
    public static void main(String[] args) {
        try
        {
            Client client = new Client("127.0.0.1", 5000); // Connects to a server at the given address, currently uses hardcoded localhost address
        }
        catch (java.io.IOException exception)
        {
            System.out.println("IO exception caught, view debug for more information."); // catches the I/O errors that connecting to a server could cause.
        }
    }
}