package com.tictactoe.services;

import java.util.Scanner;

public class Human {

    private static final Scanner input = new Scanner(System.in);

    /**
     * Ask the player to input a number
     * @return a String containing the player number
     */
    public String moveConsole() {
        System.out.print("Please enter the number for your next move: ");
        return input.next();
    }

    /**
     * Inform the player that the last input was not available or correct.
     */
    public void printPlacementError() {
        System.out.println("I'm sorry, the place you try to get is not available");
    }

    /**
     * Ask the player if he wants to play again.
     * @return
     */
    public String playAgain() {
        System.out.print("Another game? (Y / N): ");
        return input.next();
    }
}
