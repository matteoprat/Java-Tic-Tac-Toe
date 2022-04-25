package com.tictactoe.gui;

import com.tictactoe.services.Ai;
import com.tictactoe.services.GameLogic;
import com.tictactoe.services.GameOverStates;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiGame {

    private final Ai ai = new Ai();
    private final GameLogic gameLogic = new GameLogic(ai);
    private final ArrayList<JButton> buttons = new ArrayList<>();
    private static final Font FONT = new Font("Arial", Font.PLAIN, 70);
    private static final Dimension BUTTON_SIZE = new Dimension(30,30);
    private int player;
    private boolean played;

    /**
     * A GUI made to play Tic Tac Toe using GameLogic shared by the System Console Version.
     */
    public GuiGame() {

        buildButtons();
        JFrame myWindow = new JFrame();
        myWindow.setSize(500, 500);
        myWindow.setLayout(new GridLayout(3,3, 2, 2));
        myWindow.setLocationRelativeTo(null);

        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setTitle("Tic Tac Toe with Java!");
        for (JButton cb : buttons) {
            myWindow.add(cb);
        }

        myWindow.setVisible(true);
    }

    /**
     * Method to initialize a new game.
     */
    public void startGame() {

        // set all game state variables to default values

        for (JButton b: buttons) {
            b.setEnabled(true);
            b.setText("");
            b.setFont(FONT);
        }

        gameLogic.newGame();

        player = 0;
        played = false;

        // start game loop

        while (gameLogic.isGameOver() == GameOverStates.NO_WIN) {
            if (player == 1) {
                int aiMove = Integer.parseInt(gameLogic.playAiTurn());
                execMove(aiMove);
            }
        }

        // kindly inform player of result and ask if he wants to play again
        playAgainButton();

    }

    /**
     * This will create 9 buttons and add them to the buttons ArrayList.
     */
    private void buildButtons() {
        for (int i = 1; i <= 9; i++) {

            // Creating the button
            JButton newButton = new JButton();

            // Assign a name, a size and font size
            newButton.setName(Integer.toString(i));
            newButton.setPreferredSize(BUTTON_SIZE);
            newButton.setFont(FONT);

            /* adding an action listener, on click will call execMove method.
             The method is invoked with the name of the button parsed into an integer.
             */
            newButton.addActionListener(e -> {
                if (player == 0) {
                    execMove(Integer.parseInt(newButton.getName()));
                }
            });

            // adding the button to the list
            buttons.add(newButton);
        }
    }

    /**
     * Method that will perform the operations to update properly game state
     * @param n and int that represent the spot we want to put our symbol
     */
    public void execMove (int n) {
        // increase number of moves played
        gameLogic.addMove();

        char symbol = 'O';

        /* The player need to update the Set, so he has to call the isAvailable method that will control the spot.
        After the control it removes from the Set (see more documentation on Board class). Also change the symbol from O to X.
         */

        if (player == 0) {
            gameLogic.isAvailable(Integer.toString(n));
            symbol = 'X';
        }

        // Change appearance of related button and disable it from further clicks.
        buttons.get(n-1).setEnabled(false);
        buttons.get(n-1).setText(Character.toString(symbol));

        // The move is recorded in the game state
        gameLogic.recordMove(n, symbol);

        // Switch both player and played status
        player = (player == 0) ? 1 : 0;
        played = !played;
    }

    /**
     * Show a dialog box with the title containing the game result.
     * Ok will start a new game. Cancel will end the program.
     */
    private void playAgainButton() {
        String dialogTitle;
        switch(gameLogic.isGameOver()) {
            case O_WIN:
                dialogTitle = "You lose the game.";
                break;
            case X_WIN:
                dialogTitle = "You won the game!";
                break;
            case TIE:
                dialogTitle = "The game has ended in a tie!";
                break;
            default:
                dialogTitle = "Strange, I should have a title...";
        }
        int input = JOptionPane.showConfirmDialog(null,
                "Another game?", dialogTitle,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (input == 0) {
            startGame();
        } else {
            System.out.println("Exit by player choice.");
            System.exit(0);
        }
    }

}
