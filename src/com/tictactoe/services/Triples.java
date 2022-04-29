package com.tictactoe.services;

/**
 * This Enum contains the short tag for Horizontal (H from 1 to 3), Vertical (V from 1 to 3) and Diagonal (H from 1 to 2)
 * Each tag contains the indexes that form the row / column / diagonal.
 */
public enum Triples {
    H1(new int[]{1, 2, 3}),
    H2(new int[]{4, 5, 6}),
    H3(new int[]{7, 8, 9}),
    V1(new int[]{1, 4, 7}),
    V2(new int[]{2, 5, 8}),
    V3(new int[]{3, 6, 9}),
    D1(new int[]{1, 5, 9}),
    D2(new int[]{3, 5, 7});

    public final int[] indexes;

    Triples(int[] indexes) {
        this.indexes = indexes;
    }

}
