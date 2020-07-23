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

package co.bywarp.lightkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import lombok.NonNull;

public class RandomUtils {

    /**
     * Computes a random integer
     * between the two provided
     * bounds.
     *
     * @param min the minimum number
     * @param max the maximum number
     * @return the random integer
     */
    public static int random(final int min, final int max) {
        final Random ran = new Random();
        return ran.ints(min, max).findFirst().getAsInt();
    }

    /**
     * Computes a random integer
     * between the two provided
     * bounds.
     *
     * @param min the minimum number
     * @param max the maximum number
     * @return the random integer
     */
    public static int random(final double min, final double max) {
        return random((int) min, (int) max);
    }

    /**
     * Computes a random boolean
     * @return a random boolean
     */
    public static boolean random() {
        return new Random().nextBoolean();
    }

    /**
     * Retrieves a random element
     * of the provided list.
     *
     * @param list the list
     * @param <T> the type of element
     * @return the randomly selected element
     */
    public static <T> T random(@NonNull List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(random(0, list.size()));
    }

    /**
     * Retrieves a random entry
     * from the provided map.
     *
     * @param map the map
     * @param <K> the key type
     * @param <V> the value type
     *
     * @return the randomly selected entry
     */
    public static <K, V> Map.Entry<K, V> random(Map<K, V> map) {
        if (map.isEmpty()) {
            return null;
        }
        return random(new ArrayList<>(map.entrySet()));
    }

}
