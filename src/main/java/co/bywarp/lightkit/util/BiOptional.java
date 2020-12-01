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

import java.util.Optional;

import lombok.Getter;

@Getter
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class BiOptional<K, V> {

    private Optional<K> key;
    private Optional<V> value;

    public BiOptional(K key, V value) {
        this.key = Optional.of(key);
        this.value = Optional.of(value);
    }

    /**
     * Returns the key, or null if it is not present.
     * @return the key, if present
     */
    public K getKey() {
        return getKey(null);
    }

    /**
     * Returns the key, or a specified default value.
     * @param or the default value for the key
     * @return the key, or a default value
     */
    public K getKey(K or) {
        return key.orElse(or);
    }

    /**
     * Returns the value, or null if it is not present.
     * @return the value, if present
     */
    public V getValue() {
        return getValue(null);
    }

    /**
     * Returns the value, or the specified default value.
     * @param or the default value for the value
     * @return the value, or a default value
     */
    public V getValue(V or) {
        return value.orElse(or);
    }

    /**
     * Returns whether both the key, and the
     * value are present for this {@link BiOptional} instance.
     * @return if both the key and value are present
     */
    public boolean bothPresent() {
        return key.isPresent() && value.isPresent();
    }

}
