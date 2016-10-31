package com.wenyu.brain.dp;

import com.wenyu.brain.CanRun;

/**
 * The dungeon consists of M x N rooms laid out in a 2D grid.
 *
 * Knight start at top-left corner with an initial HP > 0 and can only
 * move RIGHT or DOWN to reach the bottom-right room.
 *
 * Each room may increase or decrease the HP, once the HP <= 0, the knight
 * dies immediately.
 *
 * Determine the minimum initial HP so that the knight can reach the
 * bottom-right room.
 *
 * @author Wenyu
 * @since 10/26/16
 */
public class Dungeon implements CanRun {

    @Override
    public void run() {
        /*
         *   1, -10, -5,   3
         *   1, -20,  5,   1
         * -40,  50,  6, -10
         *   5,   6, -9,   9
         *
         *  Minimum HP = 11
         */
        int[][] dungeon = new int[][] { {1, -10, -5, 3}, {1, -20, 5, 1}, {-40, 50, 6, -10}, {5, 6, -9, 9} };

        printDungeon(dungeon);

        int minHP = minHP(dungeon);

        printDungeon(dungeon);

        System.out.println("minHP = " + minHP);
    }

    private void printDungeon(int[][] dungeon) {
        StringBuilder result = new StringBuilder();

        for (int r = 0; r < dungeon.length; r++) {

            for (int c = 0; c < dungeon[0].length; c++) {
                result.append(dungeon[r][c]);

                if (c != dungeon[0].length - 1) {
                    result.append(",");
                }
            }

            result.append("\n");
        }

        System.out.println(result.toString());
    }

    /**
     * min(X, Y) -- min HP at (X, Y)
     * HP(X, Y) -- HP change at (X, Y)
     *
     * if min { min(X - 1, Y), min(X, Y + 1) } + HP(X, Y) >= 0
     * then min(X, Y) = min { min(X - 1, Y), min(X, Y + 1) }
     *
     * else
     * then min(X, Y) = - HP(X, Y) + 1
     *
     * @param dungeon
     */
    private int minHP(int[][] dungeon) {
        int lastRow = dungeon.length - 1;
        int lastCol = dungeon[0].length - 1;

        // initialize the HP
        if (dungeon[0][0] > 0) {
            dungeon[0][0] = 1;
        } else {
            dungeon[0][0] = - dungeon[0][0] + 1;
        }

        // initialize the first row, because only if the knight has moved RIGHT, he can stay on this row
        for (int c = 1; c <= lastCol; c++) {
            // IMPORTANT: previous minimum HP -- dungeon[0][c - 1] should be greater than 0
            if (dungeon[0][c - 1] + dungeon[0][c] > 0) {
                dungeon[0][c] = dungeon[0][c - 1];
            } else { // else, current room definitely reduce the HP
                dungeon[0][c] = - dungeon[0][c] + 1;
            }
        }

        // initialize the first column
        for (int r = 1; r <= lastRow; r++) {
            if (dungeon[r - 1][0] + dungeon[r][0] > 0) {
                dungeon[r][0] = dungeon[r - 1][0];
            } else {
                dungeon[r][0] = - dungeon[r][0] + 1;
            }
        }

        for (int r = 1; r < dungeon.length; r++) {
            for (int c = 1; c < dungeon[0].length; c++) {
                if (dungeon[r][c] + dungeon[r - 1][c] <= 0) {
                    // Move down will cause the HP <= 0
                    // so we need set the min HP to be abs(current HP change) + 1
                    // and we don't care the left room (Move right)
                    // 1. if the left room requires a higher HP, then that path won't be chosen
                    // 2. if the left room requires a lower HP than the upper room,
                    // we still need to set the min HP to be abs(current HP change) + 1
                    dungeon[r][c] = - dungeon[r][c] + 1;
                } else if (dungeon[r][c] + dungeon[r][c - 1] <= 0) {
                    dungeon[r][c] = - dungeon[r][c] + 1;
                } else {
                    dungeon[r][c] = Math.min(dungeon[r - 1][c], dungeon[r][c - 1]);
                }
            }
        }

        return dungeon[lastRow][lastCol];
    }
}
