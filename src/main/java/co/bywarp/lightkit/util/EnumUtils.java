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

import java.util.function.Predicate;

public class EnumUtils {

    /**
     * Attempts to find an enum constant
     * from the specified enum using a
     * predicate.
     *
     * @param target the target enum
     * @param predicate the predicate to search with
     * @param <T> the enum type
     *
     * @return the enum constant
     */
    public static <T extends Enum<T>> T match(Class<T> target, Predicate<T> predicate) {
        for (T value : target.getEnumConstants()) {
            if (predicate.test(value)) {
                return value;
            }
        }

        return null;
    }

    /**
     * Attempts to find an enum constant
     * from the specified enum by it's
     * {@link Enum#name()}.
     *
     * @param target the target enum
     * @param name the constant's native name
     * @param <T> the enum type
     *
     * @return the enum constant
     */
    public static <T extends Enum<T>> T matchByName(Class<T> target, String name) {
        return match(target, value -> value.name().equalsIgnoreCase(name));
    }

    /**
     * Attempts to find an enum constant
     * from the specified enum by it's
     * {@link Enum#ordinal()}.
     *
     * @param target the target enum
     * @param ordinal the constant ordinal
     * @param <T> the enum type
     *
     * @return the enum constant
     */
    public static <T extends Enum<T>> T matchByOrdinal(Class<T> target, int ordinal) {
        return match(target, value -> value.ordinal() == ordinal);
    }

}
