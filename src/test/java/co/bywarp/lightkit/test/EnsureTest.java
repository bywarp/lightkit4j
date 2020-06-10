package co.bywarp.lightkit.test;

import co.bywarp.lightkit.util.Ensure;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class EnsureTest {

    @Test
    public void nonNull() {
        String shouldPass = "hello there";

        Ensure.nonNull(shouldPass);
        Ensure.nonNull(shouldPass, "This error should never be called");
        Ensure.nonNull(shouldPass, new IllegalArgumentException("This error should never be called"));
        Ensure.nonNull(shouldPass, new IllegalArgumentException(), "This error should never be called");
    }

    @Test
    public void isNumeric() {
        String shouldPass = "1";
        assertTrue(Ensure.isNumeric(shouldPass), "\"" + shouldPass + "\" should always be numeric.");
    }

    @Test
    public void isDouble() {
        String shouldPass = "1.0";
        assertTrue(Ensure.isDouble(shouldPass), "\"" + shouldPass + "\" should always be a double.");
    }

    @Test
    public void isAlphabetical() {
        String shouldPass = "abcdef";
        assertTrue(Ensure.isAlphabetical(shouldPass), "\"" + shouldPass + "\" should always be alphabetical.");
    }

    @Test
    public void isJson() {
        String shouldPass = "{\"hello\":\"there\"}";
        assertTrue(Ensure.isJson(shouldPass), "\"" + shouldPass + "\" should always be a JSON object expression.");
    }

    @Test
    public void isJsonArray() {
        String shouldPass = "[\"hello\", \"there\"]";
        assertTrue(Ensure.isJsonArray(shouldPass), "\"" + shouldPass + "\" should always be a JSON array expression.");
    }

    @Test
    public void isUuid() {
        String shouldPass = "ede56e5b-9251-4636-bf5d-303d6d292c34";
        assertTrue(Ensure.isUuid(shouldPass), "\"" + shouldPass + "\" should always be a valid UUID.");
    }

    @Test
    public void length() {
        String test = "abc";
        int expected = 3;

        assertTrue(Ensure.length(test, expected), "Length of `test` is always 3.");
    }

    @Test
    public void conforms() {
        String test = "abc";
        String regex = "^(abc)$";

        assertTrue(Ensure.conforms(test, regex), "`" + test + "` should always conform to pattern `" + regex + "`");
        assertTrue(Ensure.conforms(test, Pattern.compile(regex)), "`" + test + "` should always conform to pattern `" + regex + "`");
    }

    @Test
    public void contains() {
        String test = "hello there this is a test guys";
        String regex = "(test|this)";

        assertTrue(Ensure.contains(test, regex), "`" + test + "` should always contain pattern `" + regex + "`");
    }

}