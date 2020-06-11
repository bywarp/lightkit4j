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

import java.util.Collection;
import java.util.regex.Pattern;

public class StringUtils {

    public static final Pattern IPV4_REGEX = Pattern.compile("(?!(10\\.|172\\.(1[6-9]|2\\d|3[01])\\.|192\\.168\\.).*)" +
            "(?!255\\.255\\.255\\.255)(25[0-5]|2[0-4]\\d|[1]\\d\\d|[1-9]\\d|[1-9])" +
            "(\\.(25[0-5]|2[0-4]\\d|[1]\\d\\d|[1-9]\\d|\\d)){3}");
    public static final Pattern IPV6_REGEX = Pattern.compile("([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}");

    /**
     * Collects arguments and separates
     * them with a whitespace character.
     *
     * @param args the collection of args
     * @return the separated string
     */
    public static String collectArguments(Collection<String> args) {
        return collectArguments(args, " ");
    }

    /**
     * Collects arguments and separates
     * them with the specified character.
     *
     * @param args the collection of args
     * @param separator the separating character
     * @return the separated string
     */
    public static String collectArguments(Collection<String> args, String separator) {
        return String.join(separator, args);
    }

    /**
     * Collects arguments and separates
     * them with a whitespace character,
     * denoted at the beginning index.
     *
     * @param args the collection of args
     * @param start the index at which to start
     * @return the separated string
     */
    public static String collectArguments(Collection<String> args, int start) {
        return collectArguments(args, " ", start);
    }

    /**
     * Collects arguments and separates
     * them with the specified character,
     * denoted at the beginning index.
     *
     * @param args the collection of args
     * @param separator the separating character
     * @param start the index at which to start
     * @return the separated string
     */
    public static String collectArguments(Collection<String> args, String separator, int start) {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        for (String arg : args) {
            if (i < start) {
                i++;
                continue;
            }

            sb.append(arg);

            if (i != args.size() - 1) {
                sb.append(separator);
            }

            i++;
        }

        return sb.toString().trim();
    }

    /**
     * Capitalizes the first letter of each
     * word in the provided string.
     *
     * @param input a string/sentence
     * @return the final string
     */
    public static String capitalizeFirst(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }

        return String.join(" ", words);
    }

    /**
     * Censors an IPv4 Address string
     *
     * @apiNote returns null if the input
     * does not conform to the standard
     * IPv4 regex pattern, as defined above.
     *
     * @param input the ipv4 address
     * @return the censored string
     */
    public static String censorIPv4(String input) {
        return censorIPv4(input, "*");
    }

    /**
     * Censors an IPv4 Address string
     *
     * @apiNote returns null if the input
     * does not conform to the standard
     * IPv4 regex pattern, as defined above.
     *
     * @param input the ipv4 address
     * @param replacement the replacement character
     * @return the censored string
     */
    public static String censorIPv4(String input, String replacement) {
        if (!Ensure.conforms(input, IPV4_REGEX)) {
            return null;
        }

        return input.replaceAll("\\d", replacement);
    }

}
