package com.wenyu.brain.math;

import com.wenyu.brain.CanRun;

/**
 * @author Wenyu
 * @since 10/22/16
 */
public class Sqrt implements CanRun {

    @Override
    public void run() {
        sqrt();
    }

    /**
     * Implementation of square root.
     */
    private void sqrt() {
        int[] tests = new int[] { 0, 1, 2, 9, 10, 64, 256 };

        for (int i = 0; i < tests.length; i++) {
            System.out.println("The sqrt of " + tests[i] + " is " + sqrt(tests[i]));
        }
    }

    private double sqrt(double d) {
        double f = 0, t = d;
        double p = (t - f) / 2 + f;
        double c = p * p - d;

        // TODO: overflow!!

        // e.g. d = 9
        // 1. f = 0, t = 9, p = (9 - 0) / 2 + 0 = 4.5
        // c > 0
        // 2. f = 0, t = 4.5, p = 2.25
        // c < 0
        // 3. f = 2.25, t = 4.5, p = (4.5 - 2.25) / 2 + 2.25 = 3.375
        // c > 0
        // 4. f = 2.25, t = 3.375, p = ...
        while (c > 0.00001d || c < -0.00001d) {
            if (c > 0) { // p * p > d
                t = p;
            } else { // p * p < d
                f = p;
            }
            p = (t - f) / 2 + f;
            c = p * p - d;
        }

        return p;
    }
}
