package com.server;

import java.io.*;
import java.net.*;

class Server {

    public Server(int port) throws IOException {
        ServerSocket ss = new ServerSocket(5000); // The socket server that is used for the program
        Socket s = ss.accept(); //The socket mode is set to "accept" new connection requests
        System.out.println("Connected successfully!"); // After this line is reached a connection has been made
        PrintStream printToClient = new PrintStream(s.getOutputStream()); // The stream that the server uses to send data to the client
        BufferedReader readFromClient = new BufferedReader(new InputStreamReader(s.getInputStream())); // The reader that the server uses to read from the client
        BufferedReader readFromKeyboard = new BufferedReader(new InputStreamReader(System.in)); // The reader that the server uses to send data to the client from the keyboard

        // server goes on until program is closed
        while (true) {
            String str, str1;
            printToClient.println("To start a Tic-Tac-Toe game use the command 'STARTGAME'"); // outputs the tic-tac-toe start command to the client
            // read from client until a string containing "exit" is sent
            while ((str = readFromClient.readLine()) != "exit") {
                if (readFromClient.readLine().contains("STARTGAME"))
                {
                    // a Tic tac toe game is created
                    TicTacToe game = new TicTacToe();
                    for (int i = 0; i < 9; i++){
                        if (game.getTurn() == "X")
                        {
                            // prints to the server side the game status
                            System.out.println("Playing Tic-Tac-Toe! Waiting for X to choose a spot.");
                            // The out-string that is created to try to combat the multiple in a row print problem created from this program's setup
                            String outString = game.sendBoard(game, printToClient);
                            // Sends a message to the client using the --board_info-- "header"
                            printToClient.println(outString + "--board_info--" + "Enter X's spot [1-9]: ");
                            int spot = 0;
                            // basic tic-tac-toe code to check and see if a spot is available
                            while ((spot < 1) || (spot > 9))
                            {
                                try
                                {
                                    spot = Integer.parseInt(readFromClient.readLine());
                                    if ((spot < 1) || (spot > 9))
                                    {
                                        throw new NumberFormatException();
                                    }
                                    game.addMove(spot);
                                }
                                catch (NumberFormatException exception)
                                {
                                    // Sends a message to the client using the --board_info-- "header"
                                    printToClient.println("Must enter a number 1-9!" + "--board_info--" + "Enter X's spot [1-9]: ");
                                }
                                catch (SameSpotException exception)
                                {
                                    spot = 0;
                                    // Sends a message to the client using the --board_info-- "header"
                                    printToClient.println("That spot is taken!" + "--board_info--" + "Enter X's spot [1-9]: ");
                                }
                            }
                            game.sendBoard(game, printToClient);
                        }
                        else
                        {
                            // Server side of the Tic-Tac-Toe game
                            // Sends a message to the client to keep them in the loop about the game
                            printToClient.println("Playing Tic-Tac-Toe! Waiting for O to choose a spot.");
                            game.printBoardLocal(game);
                            System.out.println("Enter O's spot [1-9]: ");
                            int spot = 0;
                            while ((spot < 1) || (spot > 9))
                            {
                                try
                                {
                                    spot = Integer.parseInt(readFromKeyboard.readLine());
                                    if ((spot < 1) || (spot > 9))
                                    {
                                        throw new NumberFormatException();
                                    }
                                    game.addMove(spot);
                                }
                                catch (NumberFormatException exception)
                                {
                                    System.out.println("Must enter a number 1-9!");
                                    System.out.println("Enter O's spot [1-9]: ");
                                }
                                catch (SameSpotException exception)
                                {
                                    printToClient.println(exception);
                                    spot = 0;
                                    printToClient.println("Enter X's spot [1-9]: ");
                                }
                            }
                            game.printBoardLocal(game);
                        }
                        // Tic-Tac-Toe winner check
                        String winner = game.checkWinner();
                        if (winner != null)
                        {
                            if (winner != "draw")
                            {
                                System.out.println("The winner is " + winner + "!");
                                printToClient.println("The winner is " + winner + "!");
                                break;
                            }
                            else
                            {
                                System.out.println("The game was a draw!");
                                printToClient.println("The game was a draw!");
                            }
                        }
                    }
                }
                // if not in a game enables communication between server and client to answer questions about the game:
                System.out.println(str); // Prints client string to server
                str1 = readFromKeyboard.readLine(); // Reads server string from keyboard
                printToClient.println(str1); // sends server string to client
            }

            // closes the connection
            printToClient.close();
            readFromClient.close();
            readFromKeyboard.close();
            ss.close();
            s.close();

            // Ends the application
            System.exit(0);

        } // end of while
    }
}