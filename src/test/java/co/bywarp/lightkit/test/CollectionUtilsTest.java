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

import co.bywarp.lightkit.util.CollectionUtils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionUtilsTest {

    @Test
    public void collect() {
        ArrayList<String> expected = new ArrayList<String>() {
            {
                add("hello");
                add("there");
            }
        };

        List<String> collected = CollectionUtils.collect("hello", "there");
        assertEquals(expected, collected, "The collected list doesn't equal the expected output.");
    }

    @Test
    public void removeIf() {
        Map<String, Integer> test = new HashMap<String, Integer>() {
            {
                put("hello", 0);
                put("world", 0);
            }
        };

        Map<String, Integer> expected = new HashMap<>();
        Map<String, Integer> result = CollectionUtils.removeIf(test, (k, v) -> v == 0);

        assertEquals(expected, result, "The modified map is not empty.");
    }

    @Test
    public void cloneList() {
        List<String> expected = Collections.singletonList("hello");
        List<String> result = CollectionUtils.clone(expected);

        assertEquals(expected, result, "The cloned list doesn't equal the original list.");
    }

    @Test
    public void cloneMap() {
        Map<String, String> expected = new HashMap<String, String>() {
            {
                put("hello", "there");
            }
        };

        Map result = CollectionUtils.clone(expected);
        assertEquals(expected, result, "The cloned map doesn't equal the original map.");
    }

}