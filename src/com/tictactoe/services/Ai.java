package com.tictactoe.services;

import java.util.Random;

public class Ai {

    private static final Random rand = new Random();
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * The AI will try to find if it can win. To win it should have -3 on a row / column
     * or diagonal. If it cannot win this turn, the AI will check if the other player can win.
     * The player win if it gets a value of 3 on a row / column or diagonal.
     * If the player cannot win next turn, the AI will play on a random position.
     * @return a String representing the spot where it will place its symbol.
     */
    public String move() {

        // try to win if possible
        String ans = tryToWin();
        if (!ans.equals("N")) {
            return ans;
        }

        // try to stop if possible
        ans = tryToStop();
        if (!ans.equals("N")) {
            return ans;
        }

        return tryRandom();

    }

    /**
     * The AI will check in the board representation if in some row / column / diagonal the result is -2.
     * The AI will win with a value of -3, so it means that there is a spot to win! It then checks the
     * spots of the target row / column / diagonal have that valid spot free.
     * @return a String representing the spot where the AI want to place its symbol or "N" if it cannot win.
     */
    private String tryToWin() {
        for (Triples key: Board.getValues().keySet()) {
            if (Board.getValues().get(key) == -2) {
                int ans = checkIndex(key.indexes);
                /* this check is necessary because -2 could be in two or three places like row / column / diagonal.
                   We have to determine which one is the right one with free spot.
                   -1 is what the AI will get if there is no spot in such row / column / diagonal. */
                if (ans != -1) {
                    return Integer.toString(ans);
                }
            }
        }
        return "N";
    }

    /**
     * The AI will check in the board representation if in some row / column / diagonal the result is 2.
     * The AI will lose with a value of 3, so it means that it should block that option! It then checks the
     * spots of the target row / column / diagonal have that valid spot free.
     * @return a String representing the spot where the AI want to place its symbol or "N" if it doesn't have to block.
     */
    private String tryToStop() {
        for (Triples key: Board.getValues().keySet()) {
            if (Board.getValues().get(key) == 2) {
                int ans = checkIndex(key.indexes);
                /* this check is necessary because 2 could be in two or three places like row / column / diagonal.
                   We have to determine which one is the right one with free spot.
                   -1 is what the AI will get if there is no spot in such row / column / diagonal. */
                if (ans != -1) {
                    return Integer.toString(ans);
                }
            }
        }
        return "N";

    }

    /**
     * The AI knows that the player will not win next turn, so it checks for an empty spot
     * @return a String representing the spot where AI want to place its symbol.
     */
    private String tryRandom() {
        int ans = rand.nextInt(8)+1;
        while (!board.isAvailable(Integer.toString(ans))) {
            ans = rand.nextInt(8)+1;
        }
        return Integer.toString(ans);
    }

    /**
     * This method control if at a certain index the place is free
     * @param indexes is an int array containing indexes to check
     * @return -1 if there are no available places or the index of the available
     */
    private int checkIndex(int[] indexes) {
        for (int idx:indexes) {
            if (board.isAvailable(Integer.toString(idx))){
                return idx;
            }
        }
        return -1;
    }

}
