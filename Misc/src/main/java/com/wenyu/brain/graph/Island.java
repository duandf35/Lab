package com.wenyu.brain.graph;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * @author Wenyu
 * Date 3/11/18
 */
public class Island {
    public int countIslands(char[][] grid) {
        int count = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    mark(grid, r, c);
                }
            }
        }

        return count;
    }

    private void mark(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0'; // toggle 1 to 0

        mark(grid, r + 1, c);
        mark(grid, r, c + 1);
        mark(grid, r - 1, c);
        mark(grid, r, c - 1);
    }
}
