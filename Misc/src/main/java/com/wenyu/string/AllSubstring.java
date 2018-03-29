package com.wenyu.string;

import com.wenyu.CanRun;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wenyu
 * @since 9/18/16
 */
public class AllSubstring implements CanRun {

    @Override
    public void run() {
        removeOneSubsets();
        toggleAllChars();
    }

    /**
     * Case 1: Generate a tree from a given string.
     *
     * Given: a string "Test"
     *
     * Then: print out
     *
     * [Test, est, st, t],
     * [est, st, t],
     * [st, t],
     * [t]
     */
    private static void removeOneSubsets() {
        String string = "Test";

        System.out.println("Input string: " + string + "\n");

        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < string.length(); i++) {
            List<String> entry = new ArrayList<>();
            String substring = string.substring(i, string.length());

            for (int j = 0; j < substring.length(); j++) {
                entry.add(substring.substring(j, substring.length()));
            }

            result.add(entry);
        }

        List<String> output = result.stream()
                                    .map(e -> String.join(",", e))
                                    .collect(Collectors.toList());

        System.out.println("Output:\n" + String.join("\n", output) + "\n");
    }

    /**
     * Case 2: Toggle lower/upper case for all characters, one character each time.
     *
     * Toggle -> two child nodes
     *        -> tree
     *        -> layer
     *        -> recursion
     *
     * Given: a string "Test"
     *
     * Then: return list
     *
     *                                    Test
     * 1                  Test                                    test
     * 2          Test               TEst                test               tEst
     * 3    Test      TeSt      TEst     TESt       test      teSt      tEst     tESt
     * 4 Test TesT TeSt TeST TEst TEsT TESt TEST test tesT teSt teST tEst tEsT tESt tEST
     */
    private static void toggleAllChars() {
        String input = "Test";
        List<String> output = new ArrayList<>();

        toggleAllChars(new StringBuilder(input), 0, output);

        System.out.println("Input: " + input + "\nOutput: " + output.toString() + "\n");
    }

    private static void toggleAllChars(StringBuilder string, int index, List<String> result) {
        if (index >= string.length()) {
            result.add(string.toString());
            return;
        }

        StringBuilder next = new StringBuilder(string);

        // not toggle
        toggleAllChars(string, index + 1, result);
        // toggle
        next.setCharAt(index, toggle(next.charAt(index)));
        toggleAllChars(next, index + 1, result);
    }

    private static Character toggle(Character c) {
        if (c <= 'z' && c >= 'a') {
            return Character.toUpperCase(c);
        } else if (c <= 'Z' && c >= 'A'){
            return Character.toLowerCase(c);
        } else {
            return c;
        }
    }
}
