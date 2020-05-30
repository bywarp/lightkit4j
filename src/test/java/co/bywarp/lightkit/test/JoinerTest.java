package co.bywarp.lightkit.test;

import co.bywarp.lightkit.util.Joiner;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JoinerTest {

    @Test
    public void joinList() {
        List<String> test = Arrays.asList("test", "test2", "test3");
        String joined = Joiner.join( ", ", test);

        assertEquals("test, test2, test3", joined, "The joined string doesn't equal the desired output.");
    }

    @Test
    public void joinArray() {
        String[] test = new String[] {"test", "test2", "test3"};
        String joined = Joiner.join(", ", test);

        assertEquals("test, test2, test3", joined, "The joined string doesn't equal the desired output.");
    }

    @Test
    public void joinVarArgs() {
        String joined = Joiner.join(", ", "test", "test2", "test3");

        assertEquals("test, test2, test3", joined, "The joined string doesn't equal the desired output.");
    }


}
