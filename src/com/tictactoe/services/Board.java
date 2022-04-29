package com.tictactoe.services;

import java.util.*;


/**
 * A Class representing the board for Tic Tac Toe with some utilities.
 * <p>The board values are stored in an array of Character called grid.</p>
 * <p>The values for each row / column / diagonal are stored inside an HashMap called values.</p>
 * <p>Each position is the key of a map containing lists of row/column/diagonal that will affect.</p>
 * <p>Finally, validPlaces is a Set with available spot, a spot is removed from set when populated.</p>
 *  */
public class Board {

    private static Character[] grid;
    private static final Map<Triples, Integer> values = new HashMap<>();
    private static final Map<Integer, List<Triples>> indexes = new HashMap<>();
    private static Set<Character> validPlaces;

    public Board() {
        initializeIndexes();
        reset();
    }

    /**
     * Public method to reset board with initial states.
     */
    public void reset() {
        grid = new Character[]{'1','2','3','4','5','6','7','8','9'};
        validPlaces = new HashSet<>(Arrays.asList(grid));
        fillValues();
    }

    private void initializeIndexes() {
        indexes.put(1, new ArrayList<>(Arrays.asList(Triples.H1,Triples.V1,Triples.D1)));
        indexes.put(2, new ArrayList<>(Arrays.asList(Triples.H1,Triples.V2)));
        indexes.put(3, new ArrayList<>(Arrays.asList(Triples.H1,Triples.V3,Triples.D2)));
        indexes.put(4, new ArrayList<>(Arrays.asList(Triples.H2,Triples.V1)));
        indexes.put(5, new ArrayList<>(Arrays.asList(Triples.H2,Triples.V2,Triples.D1,Triples.D2)));
        indexes.put(6, new ArrayList<>(Arrays.asList(Triples.H2,Triples.V3)));
        indexes.put(7, new ArrayList<>(Arrays.asList(Triples.H3,Triples.V1,Triples.D2)));
        indexes.put(8, new ArrayList<>(Arrays.asList(Triples.H3,Triples.V2)));
        indexes.put(9, new ArrayList<>(Arrays.asList(Triples.H3,Triples.V3,Triples.D1)));
    }

    private void fillValues() {
        values.clear(); // empty current Map, then fill with default values
        values.put(Triples.H1,0);
        values.put(Triples.H2,0);
        values.put(Triples.H3,0);
        values.put(Triples.V1,0);
        values.put(Triples.V2,0);
        values.put(Triples.V3,0);
        values.put(Triples.D1,0);
        values.put(Triples.D2,0);
    }

    /**
     * This method record a move changing the symbol on the grid array.
     * It also updates values of the HashMap representing rows / columns / diagonals.
     * @param index represent the index to change
     * @param symbol represent the symbol to insert
     */
    public void recordMove(int index, char symbol) {
        grid[index-1] = symbol;
        int modifier = (symbol == 'X') ? 1 : -1;

        // retrieving the keys to change in the map
        List<Triples> check = indexes.get(index);
        // Cycling the list and changing values
        for (Triples idx: check) {
            values.replace(idx, values.get(idx)+modifier);
        }
    }

    /**
     * Control if a certain place is available
     * @param spot represent the place to check
     * @return True if the place is available / False if not available
     */
    public boolean isAvailable(String spot) {
        if (validPlaces.contains(spot.charAt(0))) {
            // remove the spot from available Set.
            validPlaces.remove(spot.charAt(0));
            return true;
        }
        return false;
    }

    /**
     * Remove a spot
     * @param spot is a String representing the spot we want to remove
     */
    public void removeSpot(String spot) {
        validPlaces.remove(spot.charAt(0));
    }

    /**
     * Getter for values Map.
     * @return HashMap with values.
     */
    public static Map<Triples, Integer> getValues() {
        return values;
    }

    /**
     * Getter for grid representation
     * @return a Character Array filled with values
     */
    public static Character[] getGrid() {
        return grid;
    }

    /**
     * Getter for valid places
     * @return a Set of Characters representing available spots
     */
    public static Set<Character> getValidPlaces() {
        return validPlaces;
    }
}
