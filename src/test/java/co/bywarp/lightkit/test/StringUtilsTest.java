package co.bywarp.lightkit.test;

import co.bywarp.lightkit.util.StringUtils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTest {

    @Test
    public void collectArgs() {
        ArrayList<String> test = new ArrayList<>(Arrays.asList("whats", "going", "on"));
        String expected = "whats going on";

        assertEquals(expected, StringUtils.collectArguments(test), "Test case should always evaluate to `" + expected + "`");
    }

    @Test
    public void collectArgsWithSeparator() {
        ArrayList<String> test = new ArrayList<>(Arrays.asList("whats", "going", "on"));
        String expected = "whats, going, on";

        assertEquals(expected, StringUtils.collectArguments(test, ", "), "Test case should always evaluate to `" + expected + "`");
    }

    @Test
    public void collectArgsWithStart() {
        ArrayList<String> test = new ArrayList<>(Arrays.asList("ignored", "whats", "going", "on"));
        String expected = "whats going on";

        assertEquals(expected, StringUtils.collectArguments(test, 1), "Test case should always evaluate to `" + expected + "`");
    }

    @Test
    public void collectArgsWithSeparatorAndStart() {
        ArrayList<String> test = new ArrayList<>(Arrays.asList("ignored", "whats", "going", "on"));
        String expected = "whats, going, on";

        assertEquals(expected, StringUtils.collectArguments(test, ", ", 1), "Test case should always evaluate to `" + expected + "`");
    }

    @Test
    public void capitalizeFirst() {
        String test = "tHiS is a tesT";
        String expected = "This Is A Test";

        assertEquals(expected, StringUtils.capitalizeFirst(test), "`" + test + "` should always evaluate to `" + expected + "`");
    }

    @Test
    public void censorIPv4() {
        String test = "127.0.0.1";
        String expected = "***.*.*.*";

        assertEquals(expected, StringUtils.censorIPv4(test), "`" + test + "` should always evaluate to `" + expected + "`");
    }

    @Test
    public void censorIPv4WithReplacer() {
        String test = "127.0.0.1";
        String expected = "---.-.-.-";

        assertEquals(expected, StringUtils.censorIPv4(test, "-"), "`" + test + "` should always evaluate to `" + expected + "`");
    }

}