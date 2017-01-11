package com.wenyu.brain.string;

import com.wenyu.brain.CanRun;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyu
 * @since 11/20/16
 */
public class StringPerm implements CanRun {

    @Override
    public void run() {
        allPermutation();
        isPerm();
    }

    /**
     * All permutations.
     *
     *                      ABB(1) <-- swapping start index
     *       ABB(2)         BAB(2)        BBA(X) <-- will be generated in the next recursion!
     *   ABB(3) ABB(X)   BAB(3) BBA(3)
     */
    private static void allPermutation() {
        String string = "ABB";

        List<String> result = new ArrayList<>();

        allPermutation(new StringBuilder(string), 0, result);

        System.out.println("Permutation of String '" + string + "' is: " + result.toString() + "\n total: " + result.size() + "\n");
    }

    private static void allPermutation(StringBuilder string, int index, List<String> result) {
        if (index >= string.length()) {
            result.add(string.toString());
            return;
        }

        // 1. each recursion reflect all swapping choices of each permutation (NOT represent each level!!)
        // 2. each level, if X has swapped with Y at index I, X won't swapped with Y at any index after I
        byte[] toSwapChar = new byte[256];

        for (int i = index; i < string.length(); i++) {
            if (toSwapChar[string.charAt(i)] != 1) {

                swap(string, index, i); // for example, ABB -> BAB (1, 2 switch)
                allPermutation(new StringBuilder(string), index + 1, result);
                swap(string, index, i); // need swap back for ABB -> BBA (1, 3 switch)

                toSwapChar[string.charAt(i)] = 1;
            }
        }
    }

    private static void swap(StringBuilder string, int from, int to) {
        if (from != to) {
            Character c = string.charAt(from);
            string.setCharAt(from, string.charAt(to));
            string.setCharAt(to, c);
        }
    }

    /**
     * Judge if one string is the permutation of another one.
     */
    private void isPerm() {
        String orig = "pass";
        String test1 = "pas";  // false
        String test2 = "spas"; // true
        String test3 = "pass"; // false
        String test4 = "sas";  // false

        String[] inputs = new String[] { test1, test2, test3, test4 };

        for (String input : inputs) {
            System.out.println("String = " + input + " is a permutation? " + isPerm(orig, input) + " of original string = " + orig);
        }
    }

    private static boolean isPerm(String orig, String test) {
        if (test == null || test.length() != orig.length() || test.equals(orig)) {
            return false;
        }

        int[] charCount = new int[256];

        for (int i = 0; i < test.length(); i++) {
            charCount[test.charAt(i)]++;
            charCount[orig.charAt(i)]--;
        }

        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] != 0) {
                return false;
            }
        }

        return true;
    }
}
