package com.wenyu.dp;

import com.wenyu.CanRun;

/**
 * Given a grid, find out the number of path from the left upper corner to
 * the right lower corner.
 *
 * @author Wenyu
 * @since 11/18/16
 */
public class Dungeon2 implements CanRun {

    @Override
    public void run() {
        int[][] twoXtwo = new int[2][2];
        int[][] threeXthree = new int[3][3];
        int[][] fourXfour = new int[4][4];
        int[][] eightXeight = new int[8][8];

        System.out.println("left, down, 2X2, # of path = " + numberOfPath(twoXtwo));
        System.out.println("left, down, 3X3, # of path = " + numberOfPath(threeXthree));
        System.out.println("left, down, 4X4, # of path = " + numberOfPath(fourXfour));
        System.out.println("left, down, 8X8, # of path = " + numberOfPath(eightXeight));

        System.out.println("all, 2X2, # of path = " + numberOfPath2(twoXtwo));
        System.out.println("all, 3X3, # of path = " + numberOfPath2(threeXthree));
        System.out.println("all, 4X4, # of path = " + numberOfPath2(fourXfour));
        System.out.println("all, 8X8, # of path = " + numberOfPath2(eightXeight));
    }

    /**
     * Number of path if the user can only move right and down.
     *
     * NP(X, Y) -- Number of path that can reach point (X, Y)
     *
     * if X == 0 && Y == 0
     * then NP(X, Y) = 1
     *
     * else if X == 0 && Y > 0
     * then NP(X, Y) = NP(X, Y - 1)
     *
     * else if Y == 0 && X > 0
     * then NP(X, Y) = NP(X - 1, Y)
     *
     * else
     * NP(X, Y) = NP(X - 1, Y) + NP(X, Y - 1)
     *
     * @return NP(W, L)
     */
    private int numberOfPath(int[][] dungeon) {
        int w = dungeon[0].length - 1;
        int l = dungeon.length - 1;

        // initialize the start point
        dungeon[0][0] = 1;

        // initialize the first row, the user can only stay on this row
        // if the user keep moving right
        for (int c = 1; c <= w; c++) {
            dungeon[0][c] = dungeon[0][c - 1];
        }
        // initialize the first column, the user can only stay on this column
        // if the user keep moving down
        for (int r = 1; r <= l; r++) {
            dungeon[r][0] = dungeon[r - 1][0];
        }

        for (int r = 1; r < dungeon.length; r++) {
            for (int c = 1; c < dungeon[0].length; c++) {
                dungeon[r][c] = dungeon[r - 1][c] + dungeon[r][c - 1];
            }
        }

        return dungeon[w][l];
    }

    /**
     * Number of path if the user can move right, down, up and left.
     *
     * NP(X, Y) -- Number of path that can reach point (X, Y)
     *
     * --------------------------------------------------------------------------
     * The approach below is INCORRECT because of creating loop!
     *
     * if X == 0 && Y == 0
     * then NP(X, Y) = 1
     *
     * else if X == 0 && Y > 0
     * then NP(X, Y) = NP(X, Y - 1) + NP(X, Y + 1) + NP(X + 1, Y)
     *
     * else if Y == 0 && X > 0
     * then NP(X, Y) = NP(X - 1, Y) + NP(X + 1, Y) + NP(X, Y + 1)
     *
     * else if X == W && Y < L
     * then NP(X, Y) = NP(X, Y - 1) + NP(X, Y + 1) + NP(X - 1, Y)
     *
     * else if Y == L && X < W
     * then NP(X, Y) = NP(X - 1, Y) + NP(X + 1, Y) + NP(X, Y - 1)
     *
     * else
     * NP(X, Y) = NP(X - 1, Y) + NP(X + 1, Y) + NP(X, Y - 1) + NP(X, Y + 1)
     * --------------------------------------------------------------------------
     *
     * The correct method is using BFS.
     *
     * @return NP(W, L)
     */
    private int numberOfPath2(int[][] dungeon) {
        return -1;
    }
}
