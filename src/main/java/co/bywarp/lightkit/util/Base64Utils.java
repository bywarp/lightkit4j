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

    public static String encode(String s) {
        return encode(s.getBytes());
    }

    public static String encode(byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes));
    }

    public static String decode(String s) {
        return decode(s.getBytes());
    }

    public static String decode(byte[] bytes) {
        return new String(Base64.getDecoder().decode(bytes));
    }

}