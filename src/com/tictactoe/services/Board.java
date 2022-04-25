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
    private static final Map<String, Integer> values = new HashMap<>();
    private static final Map<Integer, List<String>> indexes = new HashMap<>();
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
        indexes.put(1, new ArrayList<>(Arrays.asList("H1","V1","D1")));
        indexes.put(2, new ArrayList<>(Arrays.asList("H1","V2")));
        indexes.put(3, new ArrayList<>(Arrays.asList("H1","V3","D2")));
        indexes.put(4, new ArrayList<>(Arrays.asList("H2","V1")));
        indexes.put(5, new ArrayList<>(Arrays.asList("H2","V2","D1","D2")));
        indexes.put(6, new ArrayList<>(Arrays.asList("H2","V3")));
        indexes.put(7, new ArrayList<>(Arrays.asList("H3","V1","D2")));
        indexes.put(8, new ArrayList<>(Arrays.asList("H3","V2")));
        indexes.put(9, new ArrayList<>(Arrays.asList("H3","V3","D1")));
    }

    private void fillValues() {
        values.clear(); // empty current Map, then fill with default values
        values.put("H1",0);
        values.put("H2",0);
        values.put("H3",0);
        values.put("V1",0);
        values.put("V2",0);
        values.put("V3",0);
        values.put("D1",0);
        values.put("D2",0);
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
        List<String> check = indexes.get(index);
        // Cycling the list and changing values
        for (String idx: check) {
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
     * Getter for values Map.
     * @return HashMap with values.
     */
    public static Map<String, Integer> getValues() {
        return values;
    }

    /**
     * Getter for grid representation
     * @return a Character Array filled with values
     */
    public static Character[] getGrid() {
        return grid;
    }
}
