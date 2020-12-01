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

import co.bywarp.lightkit.util.SteppingIterator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SteppingIteratorTest {

    private final List<String> ITERABLES = Arrays.asList(
            "a", "b", "c", "d", "e", "f", "g"
    );

    @Test
    public void testIterate() {
        SteppingIterator.with((i, val) -> i % 2 == 0, ITERABLES)
                .filter(val -> !val.equalsIgnoreCase("c"))
                .map(String::toUpperCase)
                .forEach((i, val) -> System.out.println(val));
    }

}