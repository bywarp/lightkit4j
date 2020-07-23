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

public class RandomUtils {

    public static int random(final int min, final int max) {
        final Random ran = new Random();
        return ran.ints(min, max).findFirst().getAsInt();
    }

    public static boolean random() {
        return new Random().nextBoolean();
    }

    public static int random(final double min, final double max) {
        return random((int) min, (int) max);
    }

    public static <T> T random(List<T> list) {
        return list.get(random(0, list.size()));
    }

    public static <K, V> V random(Map<K, V> map) {
        return random(new ArrayList<>(map.entrySet())).getValue();
    }

}
