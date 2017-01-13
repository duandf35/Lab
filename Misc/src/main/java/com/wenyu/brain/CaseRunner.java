package com.wenyu.brain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Wenyu
 * @since 9/18/16
 */
public class CaseRunner {

    private static final Set<CanRun> Q = new HashSet<>();

    private static void runAllCases() {
        Q.add(new WhiteBoard());
//        Q.add(new Pow());
//        Q.add(new AllSubstring());
//        Q.add(new StringPerm());
//        Q.add(new Sqrt());
//        Q.add(new Dungeon());
//        Q.add(new Dungeon2());
//        Q.add(new BinarySearchRelated());
//        Q.add(new LinkedListOpt());
//        Q.add(new RemoveDupFromSortedList());
        Q.forEach(CanRun::run);
    }

    public static void main (String... args) {
        runAllCases();
    }
}
