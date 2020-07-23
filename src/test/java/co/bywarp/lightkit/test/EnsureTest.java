/*
 * Copyright (c) 2020 Warp Studios
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package co.bywarp.lightkit.test;

import co.bywarp.lightkit.util.Ensure;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class EnsureTest {

    @Test
    public void nonNull() {
        String shouldPass = "hello there";

        Ensure.notNull(shouldPass);
        Ensure.notNull(shouldPass, "This error should never be called");
        Ensure.notNull(shouldPass, new IllegalArgumentException("This error should never be called"));
        Ensure.notNull(shouldPass, new IllegalArgumentException(), "This error should never be called");
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