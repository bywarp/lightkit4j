package co.bywarp.lightkit.util;

import java.util.Arrays;
import java.util.List;

public class Joiner {

    public static String join(String separator, String... strings) {
        return join(separator, Arrays.asList(strings));
    }

    public static String join(String separator, List<String> strings) {
        if (strings.size() <= 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder(strings.get(0));
        for (int i = 1; i<strings.size(); i++) {
            String next = strings.get(i);
            builder.append(separator + " " + next);
        }

        return builder.toString().trim();
    }

}
