package com.tictactoe;

import com.tictactoe.console.ConsoleGame;
import com.tictactoe.gui.GuiGame;

/**
 * <p>A Tic Tac Toe game.</p>
 * <p>Author: Matteo Pratellesi<p>
 * <p>Date: 23/04/2022 - 25/04/2022.</p>
 * I made this game for learning purpose and the code may seem too long for a simple game.
 * I wanted to try several Java elements, like ENUM, HashMap, Sets and ArrayList, Switch.
 * Also, I wanted to try to build a simple GUI along with the console version (That's why it took me a couple of days).
 * I've placed comments everywhere, so it is really easy to follow the logic behind the game, and it may be useful for other people to see how I made it.
 */

public class Main {

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("-console")) {
            ConsoleGame game = new ConsoleGame();
            game.startGame();
        } else {
            GuiGame game = new GuiGame();
            game.startGame();
        }

    }
}


