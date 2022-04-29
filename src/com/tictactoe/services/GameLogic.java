package com.tictactoe.services;

public class GameLogic {

    private final Ai ai;
    public static int moves;
    private final Board board = new Board();

    public GameLogic(Ai ai) {
        this.ai = ai;
        this.ai.setBoard(board);
        newGame();
    }

    /**
     * This method forward to the board real method to record the move
     * @param index integer representing the spot
     * @param symbol char representing 'X' or 'O'
     */
    public void recordMove(int index, char symbol) {
        board.recordMove(index, symbol);
    }

    /**
     * Check if a spot is free
     * @param spot a String representing the chosen spot
     * @return True if the spot is free, False otherwise
     */
    public boolean isAvailable(String spot) {
        return Board.getValidPlaces().contains(spot.charAt(0));
    }

    /**
     * Remove a value from the list of available spot
     * @param spot represent the spot where player or Ai wants to place their symbol
     */
    public void removeSpot(String spot) {
        board.removeSpot(spot);
    }

    /**
     * Set default values for state of the game variables
     */
    public void newGame() {
        moves = 0;
        board.reset();
    }

    /**
     * Increase number of moves occurred, when it hit values of 9 and there are no winner
     * the game will be a Tie
     */
    public void addMove() {
        moves++;
    }

    /**
     * Invoke Ai method to compute next move
     * @return a String representing the spot the Ai choose
     */
    public String playAiTurn() {
        return ai.move();
    }

    /**
     * Getter for grid
     * @return Character array representing grid
     */
    public Character[] getGrid() {
        return Board.getGrid();
    }

    /**
     * This method check if one of the player have won the game
     * @return the correct representation of the winner or no winner
     */
    public GameOverStates isGameOver() {

        for (Triples k : Board.getValues().keySet()) {
            if (Board.getValues().get(k) == 3) {
                return GameOverStates.X_WIN;
            }
            if (Board.getValues().get(k) == -3) {
                return GameOverStates.O_WIN;
            }
        }
        if (moves == 9) {
            return GameOverStates.TIE;
        }

        return GameOverStates.NO_WIN;

    }



}
