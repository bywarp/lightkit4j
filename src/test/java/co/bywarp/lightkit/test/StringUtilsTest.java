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

    @Test
    public void repeatText() {
        String arg = " ";
        String expected = "   ";

        assertEquals(expected, StringUtils.repeat(arg, 3));
    }

}