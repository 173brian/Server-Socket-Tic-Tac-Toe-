package com.client;

import java.io.*;
import java.net.*;

class Client {

    public Client(String address, int port) throws IOException {
        Socket socket = new Socket(address, port); // Creates a socket object with the address and port info specified in object declaration
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream()); // The stream that is sent to the server
        BufferedReader serverInputReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // The stream that reads from the server at the socket connection
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in)); // Captures information from the keyboard to send to the server
        String str = "Connected.";

        // repeat as long as exit isn't typed on the client side
        while (!(str.equals("exit")))
        {
            // send to the server
            str = keyboardReader.readLine();
            outputStream.writeBytes(str + "\n");
            // receive from the server
            String[] str1 = serverInputReader.readLine().split("--board_info--");
            for(String line : str1)
            {
                if (line != "")
                {
                    System.out.println(line);
                }
            }
        }
        serverInputReader.close();
        outputStream.close();
        keyboardReader.close();
        socket.close();
    }
}