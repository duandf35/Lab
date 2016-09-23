package fundamental;

import fundamental.string.AllSubstring;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Wenyu
 * @since 9/18/16
 */
public class CaseRunner {

    public static final Set<CanRun> Q = new HashSet<>();

    public static void main (String... args) {
        Q.add(new AllSubstring());

        Q.forEach(CanRun::run);
    }
}
