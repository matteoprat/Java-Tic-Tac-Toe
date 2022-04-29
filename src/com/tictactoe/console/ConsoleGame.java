package com.tictactoe.console;

import com.tictactoe.services.Ai;
import com.tictactoe.services.GameLogic;
import com.tictactoe.services.GameOverStates;
import com.tictactoe.services.Human;

import java.util.Locale;

public class ConsoleGame {

    private final Human human = new Human();
    private final Ai ai = new Ai();
    private final GameLogic gameLogic = new GameLogic(ai);
    private int player = 0;

    /**
     * Method that handle the game
     */
    public void startGame() {
        // reset variables and printing initial board
        gameLogic.newGame();
        player = 0;
        printBoard();

        // main game loop
        while (gameLogic.isGameOver() == GameOverStates.NO_WIN) {
            gameLogic.addMove();
            if (player == 0) {
                // player move block
                while (true) {
                    String spot = human.moveConsole();
                    if (gameLogic.isAvailable(spot)) {
                        gameLogic.removeSpot(spot);
                        gameLogic.recordMove(Integer.parseInt(spot), 'X');
                        break;
                    }
                    human.printPlacementError();
                }
            } else {
                // Ai move block
                String spot = gameLogic.playAiTurn();
                gameLogic.removeSpot(spot);
                gameLogic.recordMove(Integer.parseInt(spot), 'O');
            }
            // switch active player and print board
            player = (player == 0) ? 1 : 0;
            printBoard();
        }

        // display game over message and prompt the user for another game
        printGameOver(gameLogic.isGameOver());
        anotherGame();

    }

    /**
     * Print on console a representation of current board state
     */
    public void printBoard() {
        // System.out.println(values); - it was for debug
        Character[] grid = gameLogic.getGrid();

        System.out.format("%s|%s|%s%n-+-+-%n%s|%s|%s%n-+-+-%n%s|%s|%s%n",
                grid[0], grid[1], grid[2], grid[3], grid[4], grid[5],
                grid[6], grid[7], grid[8]);

        System.out.println("-------------------------------------------");
        if (player == 0) {
            System.out.println("YOUR TURN");
        } else {
            System.out.println("AI TURN");
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * Print to console the Game Over message
     * @param result is a GameOverStates Enum option
     */
    public void printGameOver(GameOverStates result) {
        switch (result) {
            case TIE:
                System.out.println("Game Over - Tie game, no winner this time.");
                break;
            case X_WIN:
                System.out.println("Game Over - YOU WIN THE GAME!!!! Good job!");
                break;
            case O_WIN:
                System.out.println("Game Over - The AI won the game.");
                break;
        }
    }

    /**
     * Prompt the user asking if a new game is required
     */
    public void anotherGame() {
        do {
            char ans = human.playAgain().toUpperCase(Locale.ROOT).charAt(0);
            if (ans == 'N') {
                System.exit(0);
            } else if (ans == 'Y') {
                startGame();
            } else {
                System.out.println("Invalid input.");
            }
        } while (true);
    }

}
