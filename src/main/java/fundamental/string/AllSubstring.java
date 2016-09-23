package fundamental.string;

import fundamental.CanRun;

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
    }

    /**
     * Generate a tree from a given string.
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

        System.out.println("Output:\n" + String.join("\n", output));
    }

    /**
     * Toggle lower/upper case for all characters, one character each time.
     *
     * Given: a string "Test"
     *
     * Then: return list
     *
     * [Test, TEst, TESt, TEST]
     * [TeSt, TeST]
     * [TesT]
     *
     * [test, tEst, tESt, tEST]
     * [teSt, teST]
     * [tesT]
     *
     * Total:
     */
    private static void removeOneSubsets2() {

    }
}
