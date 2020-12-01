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
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SteppingIterator<T> {

    private List<T> iterating;
    private BiPredicate<Integer, T> step;

    public SteppingIterator(BiPredicate<Integer, T> step) {
        this.iterating = new ArrayList<>();
        this.step = step;
    }

    @SafeVarargs
    public static <T> SteppingIterator<T> with(BiPredicate<Integer, T> step, T... items) {
        return new SteppingIterator<>(step)
                .iterate(items);
    }

    public static <T> SteppingIterator<T> with(BiPredicate<Integer, T> step, List<T> items) {
        return new SteppingIterator<>(step)
                .iterate(items);
    }

    @SafeVarargs
    public final SteppingIterator<T> iterate(T... items) {
        this.iterating = Arrays.asList(items);
        return this;
    }

    public final SteppingIterator<T> iterate(List<T> items) {
        this.iterating = items;
        return this;
    }

    public SteppingIterator<T> map(Function<T, T> converter) {
        this.iterating = iterating
                .stream()
                .map(converter)
                .collect(Collectors.toList());
        return this;
    }

    public SteppingIterator<T> filter(Predicate<T> filter) {
        this.iterating = iterating
                .stream()
                .filter(filter)
                .collect(Collectors.toList());
        return this;
    }

    public void forEach(BiConsumer<Integer, T> and) {
        for (int i = 0; i < iterating.size(); i++) {
            T item = iterating.get(i);
            if (step.test(i, item)) {
                and.accept(i, item);
            }
        }
    }

}
