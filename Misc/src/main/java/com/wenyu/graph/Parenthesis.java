package com.wenyu.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * @author Wenyu
 * Date 3/14/18
 */
public class Parenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        gen(n, 0, "(", "", results);
        return results;
    }

    private void gen(int n, int oc, String next, String result, List<String> results) {
        if (next.equals(")")) {
            if (oc > 0) {
                oc--;
                result += ")";
            } else {
                // end invalid path
                return;
            }
        }

        if (next.equals("(")) {
            if (n > 0) {
                n--;
                oc++;
                result += "(";
            } else {
                // end invalid path
                return;
            }
        }

        if (n > 0 || oc > 0) {
            gen(n, oc, "(", result, results);
            gen(n, oc, ")", result, results);
        } else {
            results.add(result);
        }

        return;
    }
}
