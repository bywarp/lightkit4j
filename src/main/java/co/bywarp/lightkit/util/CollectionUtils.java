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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import lombok.NonNull;

public class CollectionUtils {

    /**
     * Converts a varargs array of items
     * into a List compatible type.
     *
     * @param items the vararg items
     * @param <T> the type of items
     * @return a list compatible representation of the data
     */
    @SafeVarargs
    public static <T> List<T> collect(T... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    /**
     * Removes all items that conform to
     * the supplied {@link BiPredicate} condition
     * in the provided {@link Map} compatible structure.
     *
     * @param map the map
     * @param predicate the predicate condition
     * @param <K> the key type of the map
     * @param <V> the value type of the map
     *
     * @return the resultant map
     */
    public static <K, V> Map<K, V> removeIf(Map<K, V> map, BiPredicate<K, V> predicate) {
        Map<K, V> result = clone(map);
        List<Map.Entry<K, V>> toRemove = map
                .entrySet()
                .stream()
                .filter(ent -> predicate.test(ent.getKey(), ent.getValue()))
                .collect(Collectors.toList());

        toRemove.forEach(ent -> result.remove(ent.getKey(), ent.getValue()));
        toRemove.clear();
        return result;
    }

    /**
     * Clones a {@link List} compatible structure
     * into a new List.
     *
     * @param list the list
     * @param <T> the list item type
     * @return the cloned list
     */
    public static <T> List<T> clone(@NonNull List<T> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(list);
    }

    /**
     * Clones a {@link Map} compatible structure
     * into a new Map.
     *
     * @param map the map
     * @param <K> the map key type
     * @param <V> the map value type
     *
     * @return the cloned map
     */
    public static <K, V> Map<K, V> clone(@NonNull Map<K, V> map) {
        if (map.isEmpty()) {
            return new HashMap<>();
        }

        return new HashMap<>(map);
    }

}
