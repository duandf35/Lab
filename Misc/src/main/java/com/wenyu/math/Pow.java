package com.wenyu.math;

import com.wenyu.CanRun;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyu
 * @since 10/11/16
 */
public class Pow implements CanRun {

    @Override
    public void run() {
        Integer from = 0;
        Integer to = 100;
        List<Integer> wholeSquares = wholeSquaresInRange(from, to);
        printResult(from, to, wholeSquares);

        from = 7;
        to = 9;
        wholeSquares = wholeSquaresInRange(from, to);
        printResult(from, to, wholeSquares);

        from = 0;
        to = 0;
        wholeSquares = wholeSquaresInRange(from, to);
        printResult(from, to, wholeSquares);

        to = new Double(Math.sqrt(Integer.MAX_VALUE)).intValue();
        from = to - 500;
        wholeSquares = wholeSquaresInRange(from, to);
        printResult(from, to, wholeSquares);

        to = Integer.MAX_VALUE;
        from = to - 500;
        try {
            wholeSquaresInRange(from, to);
        } catch (final IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printResult(Integer from, Integer to, List<Integer> wholeSquares) {
        System.out.println("From " + from + " to " + to + " the whole square numbers are: " + wholeSquares.toString() + ", total: " + wholeSquares.size());
    }

    /**
     * Find all whole square in a certain range.
     *
     * Assume from and to are non-negative value and from <= to.
     *
     * @param from the lower boundary
     * @param to the upper boundary
     * @return the list of whole square number
     */
    private List<Integer> wholeSquaresInRange(final Integer from, final Integer to) {
        List<Integer> allWholeSquares = new ArrayList<>();

        Integer firstWholeSquare = from;
        while (!isWholeSquare(firstWholeSquare)) {

            // 1. Prevent overflow when to = Integer.MAX_VALUE
            // 2. Make sure firstWholeSquare can equal to the upper bound
            if (to - 1 < firstWholeSquare) {
                break;
            }

            firstWholeSquare++;
        }

        Integer firstWholeSquareRoot = new Double(Math.sqrt(firstWholeSquare)).intValue();
        for (int nextWholeSquareRoot = firstWholeSquareRoot;; nextWholeSquareRoot++) {

            // Prevent overflow
            if (nextWholeSquareRoot > 0 && Integer.MAX_VALUE / nextWholeSquareRoot < nextWholeSquareRoot) {
                throw new IllegalStateException("Reach maximum integer limit, from " + from + " to " + to + ", whole square numbers: " + allWholeSquares.toString());
            }

            Integer nextWholeSquare = nextWholeSquareRoot * nextWholeSquareRoot;

            if (nextWholeSquare > to) {
                break;
            } else {
                allWholeSquares.add(nextWholeSquare);
            }
        }

        return allWholeSquares;
    }

    /**
     * Check if one number is a whole square.
     *
     * @param value the number
     * @return true if it is whole square
     */
    private boolean isWholeSquare(final Integer value) {
        Double sqrt = Math.sqrt(value);

        return Double.isFinite(sqrt) && Double.compare(sqrt, Math.floor(sqrt)) == 0;
    }
}
