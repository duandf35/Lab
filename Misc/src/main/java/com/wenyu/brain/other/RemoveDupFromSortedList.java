package com.wenyu.brain.other;

import com.wenyu.brain.CanRun;

import java.util.Arrays;

/**
 * Given 1->1->2 return 1->2
 * Given 1->1->2->3->3 return 1->2->3
 * Given 1->2->2->2->2 return 1->2
 * Given 1 return 1
 * Given 1->2 return 1->2
 * Given [] return []
 *
 * @author Wenyu
 * @since 1/4/17
 */
public class RemoveDupFromSortedList implements CanRun {

    @Override
    public void run() {
        int[] case1 = new int[] {};
        int[] case2 = new int[] { 1 };
        int[] case3 = new int[] { 1, 1 };
        int[] case4 = new int[] { 1, 1, 1 };
        int[] case5 = new int[] { 1, 1, 2, 2 };
        int[] case6 = new int[] { 1, 2, 2, 2, 3 };
        int[] case7 = new int[] { 1, 2, 2, 3, 3, 4 };

        int[][] testCases = new int[][] { case1, case2, case3, case4, case5, case6, case7 };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Case " + i + " original array = " + Arrays.toString(testCases[i]));

            int[] dupRemoved = removeDuplicate(testCases[i]);
            System.out.println("Case " + i + " end index = " + dupRemoved[testCases[i].length] + ", duplication removed array = " + Arrays.toString(dupRemoved));

            System.out.println();
        }
    }

    //
    // Two pointer, p and n
    //
    // once p == n
    // stop p and keep moving n
    // util p != n or n == null
    // then pointer p to n
    //
    // 1. Normal case
    //
    // p    n
    // 1 -> 2 -> 2 -> 2 -> 3
    //
    //      p    n
    // 1 -> 2 -> 2 -> 2 -> 3
    //
    //      p              n
    // 1 -> 2 -> 2 -> 2 -> 3
    //
    //           p              n
    // 1 -> 2 -> 3 -> 2 -> 3
    //
    // 2. Edge case
    //
    // p    n
    // 1 -> 1
    //
    // p         n
    // 1 -> 1
    //
    // p         n
    // 1
    //
    private int[] removeDuplicate(int[] orig) {
        // the last index is for saving the end index of the new array
        int[] ans = Arrays.copyOf(orig, orig.length + 1);

        int p = 0, n = 1;

        // stop util n reach the end of the array
        // or the array is empty
        // or the array only has one element
        while (p < orig.length && n < orig.length) {

            // stop p and keep moving n
            while (n < orig.length && ans[p] == ans[n]) {
                n++;
            }

            // ans[p] != ans[n]
            if (n < orig.length) {
                p++;
                ans[p] = ans[n];
                n++;
            }
            // n == ans.length
            // do nothing, p should be the end index of the new array
        }

        ans[orig.length] = p;

        return ans;
    }
}
