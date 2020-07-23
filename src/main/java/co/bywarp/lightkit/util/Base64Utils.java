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

import java.util.Base64;

public class Base64Utils {

    /**
     * Encodes the provided string to a
     * RFC 4648 compatible Base64 encoded
     * string.
     *
     * @param input the string to encode
     * @return the encoded string
     */
    public static String encode(String input) {
        return encode(input.getBytes());
    }

    /**
     * Encodes the provided bytes to a
     * RFC 4648 compatible Base64 encoded
     * string.
     *
     * @param bytes the bytes to encode
     * @return the encoded string
     */
    public static String encode(byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes));
    }

    /**
     * Decodes a RFC 4648 compatible
     * encoded Base64 string.
     *
     * @param input the string to decode
     * @return the decoded string
     */
    public static String decode(String input) {
        return decode(input.getBytes());
    }

    /**
     * Decodes RFC 4648 compatible
     * Base64 encoded bytes.
     *
     * @param bytes the bytes to decode
     * @return the decoded string
     */
    public static String decode(byte[] bytes) {
        return new String(Base64.getDecoder().decode(bytes));
    }

}
