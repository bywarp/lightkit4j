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

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
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
     * Returns whether or not a given list contains any
     * elements that conform to the provided predicate.
     *
     * @param list the list to search
     * @param predicate the provided predicate
     * @return if the list contains an element conforming to the predicate
     */
    public static <T> boolean contains(List<T> list, Predicate<T> predicate) {
        return list
                .stream()
                .anyMatch(predicate);
    }

    /**
     * Returns whether or not a given map contains
     * a key/value pair via a provided predicate.
     *
     * @param map the map to search
     * @param predicate the provided predicate
     *
     * @return if the map contains the key/value pair
     */
    public static <K, V> boolean contains(Map<K, V> map, BiPredicate<K, V> predicate) {
        return map
                .entrySet()
                .stream()
                .anyMatch(ent -> predicate
                        .test(ent.getKey(), ent.getValue()));
    }

    /**
     * Attempts to retrieve a key/value pair from the provided
     * map through the use of a provided predicate.
     *
     * @param map the map to search
     * @param predicate the predicate to search with
     * @return the value, if found
     */
    public static <K, V> Map.Entry<K, V> get(Map<K, V> map, BiPredicate<K, V> predicate) {
        return map
                .entrySet()
                .stream()
                .filter(ent -> predicate
                        .test(ent.getKey(), ent.getValue()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Attempts to retrieve a key/value pair from the provided
     * map through the use of a provided predicate, or otherwise
     * return the provided default key/value pair via a {@link BiPredicate}.
     *
     * @param map the map to search
     * @param predicate the predicate to search with
     * @param defaultValue the default key/value pair
     *
     * @return the value, if found, or the default value
     */
    public static <K, V> Map.Entry<K, V> getOrDefault(Map<K, V> map, BiPredicate<K, V> predicate, BiOptional<K, V> defaultValue) {
        return map
                .entrySet()
                .stream()
                .filter(ent -> predicate
                        .test(ent.getKey(), ent.getValue()))
                .findFirst()
                .orElse(new AbstractMap.SimpleEntry<>(
                        defaultValue.getKey(),
                        defaultValue.getValue()));
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
