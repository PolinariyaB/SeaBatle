package org.example;
import java.io.*;
import java.net.*;

public class Ship {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7099);
            System.out.println("Server started. Waiting for players...");

            Socket player1Socket = serverSocket.accept();
            System.out.println("Player 1 connected.");

            Socket player2Socket = serverSocket.accept();
            System.out.println("Player 2 connected.");

            BufferedReader player1In = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            PrintWriter player1Out = new PrintWriter(player1Socket.getOutputStream(), true);

            BufferedReader player2In = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
            PrintWriter player2Out = new PrintWriter(player2Socket.getOutputStream(), true);

            player1Out.println("You are player 1. Please enter your choice (rock, paper, scissors):");
            player2Out.println("You are player 2. Please enter your choice (rock, paper, scissors):");

            String player1Choice = player1In.readLine().toLowerCase();
            String player2Choice = player2In.readLine().toLowerCase();

            String result = determineWinner(player1Choice, player2Choice);
            player1Out.println(result);
            player2Out.println(result);

            player1Socket.close();
            player2Socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String determineWinner(String choice1, String choice2) {
        if (choice1.equals(choice2)) {
            return "It's a tie!";
        } else if ((choice1.equals("rock") && choice2.equals("scissors")) ||
                (choice1.equals("scissors") && choice2.equals("paper")) ||
                (choice1.equals("paper") && choice2.equals("rock"))) {
            return "Player 1 wins!";
        } else {
            return "Player 2 wins!";
        }
    }
}