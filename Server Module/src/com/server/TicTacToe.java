package com.server;
import java.io.PrintStream;
import java.util.*;

public class TicTacToe {

    private String[] board;
    public String[] getBoard() { return board; }
    private String turn;

    public String getTurn() { return turn; }

    // CheckWinner method will
    // decide the combination
    // of three box given below.
    public String checkWinner()
    {
        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
            //For X winner
            if (line.equals("XXX")) {
                return "X";
            }

            // For O winner
            else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(board).contains(
                    String.valueOf(a + 1))) {
                break;
            }
            else if (a == 8) {
                return "draw";
            }
        }
        return null;
    }

    // To print out the board.
    /* |---|---|---|
       | 1 | 2 | 3 |
       |-----------|
       | 4 | 5 | 6 |
       |-----------|
       | 7 | 8 | 9 |
       |---|---|---|*/

    private String[] printBoard()
    {
        String[] s = new String[7];
        s[0] = ("|---|---|---|");
        s[1] = ("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        s[2] = ("|-----------|");
        s[3] = ("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        s[4] = ("|-----------|");
        s[5] = ("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        s[6] = ("|---|---|---|");
        return s;
    }

    public TicTacToe()
    {
        Scanner in = new Scanner(System.in);
        board = new String[9];
        turn = "X";
        String winner = null;
        for (int i = 1; i < 10; i++)
        {
            board[i-1] = ("" + i);
        }
    }

    public void addMove(int spot) throws SameSpotException {
        try
        {
            Integer.parseInt(board[spot - 1]);
        }
        catch (NumberFormatException exception)
        {
            throw new SameSpotException("This spot has already been taken!");
        }
        board[spot - 1] = turn;
        if(turn == "X")
        {
            turn = "O";
        }
        else
        {
            turn = "X";
        }
    }

    public String sendBoard(TicTacToe game, PrintStream ps)
    {
        String singleLineBoard = null;
        for (String line : game.printBoard())
        {
            singleLineBoard += "--board_info--" + line;
        }
        String outString = ("\033[H\033[2J" + singleLineBoard);
        return  outString;
    }

    public void printBoardLocal(TicTacToe game)
    {
        System.out.println("\033[H\033[2J");
        for (String line : game.printBoard())
        {
            System.out.println(line);
        }
        System.out.println();
    }
}