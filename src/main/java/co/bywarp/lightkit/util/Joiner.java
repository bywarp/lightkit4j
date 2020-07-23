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

import java.util.Arrays;
import java.util.List;

public class Joiner {

    /**
     * Joins the specified strings
     * by a delimiting string.
     *
     * @param separator the delimiter
     * @param strings the strings to join
     * @return the joined strings
     */
    public static String join(String separator, String... strings) {
        return join(separator, Arrays.asList(strings));
    }

    /**
     * Joins the specified strings
     * by a delimiting string.
     *
     * @param separator the delimiter
     * @param strings the strings to join
     * @return the joined strings
     */
    public static String join(String separator, List<String> strings) {
        if (strings.size() <= 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder(strings.get(0));
        for (int i = 1; i<strings.size(); i++) {
            String next = strings.get(i);
            builder.append(separator + next);
        }

        return builder.toString().trim();
    }

}
