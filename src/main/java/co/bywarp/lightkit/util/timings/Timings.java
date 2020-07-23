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

package co.bywarp.lightkit.util.timings;

import co.bywarp.lightkit.util.AnsiColors;
import co.bywarp.lightkit.util.Callback;
import co.bywarp.lightkit.util.logger.Logger;

public class Timings {

    private long start;
    private String task;
    private Logger logger;

    public Timings(String prefix, String task) {
        this.start = System.currentTimeMillis();
        this.task = task;
        this.logger = new Logger(prefix);
    }

    /**
     * Completes the timings and records
     * the time it took to stdout.
     */
    public void complete() {
        long elapsed = (System.currentTimeMillis() - start);
        logger.info(task + " took " + getColorForElapsedTime(elapsed) + elapsed + "ms" + AnsiColors.RESET + ".");
    }

    /**
     * Completes the timings and records
     * the time it took to stdout with
     * a custom format.
     *
     * @apiNote the following replacers
     * are available in the custom format:
     *  - "%t" will be replaced with the
     *         time it took in millis.
     *
     *  - "%c" will be replaced with the
     *         color associated with the
     *         elapsed time in millis.
     *
     *  - "%r" will be replaced with the
     *         {@link AnsiColors#RESET} color
     *         as this is needed so the console
     *         colors don't get messed up.
     *
     * @param custom the custom format (read apiNote)
     */
    public void complete(String custom) {
        long elapsed = (System.currentTimeMillis() - start);
        logger.info(parse(custom, elapsed));
    }

    /**
     * Completes the timings and records
     * the time it took to stdout with an
     * additional callback to perform once
     * the timings are completed.
     *
     * @param callback the callback
     */
    public void complete(Callback callback) {
        long elapsed = (System.currentTimeMillis() - start);
        logger.info(task + " took " + getColorForElapsedTime(elapsed) + elapsed + "ms" + AnsiColors.RESET + ".");
        callback.complete();
    }

    /**
     * Completes the timings and records
     * the time it took to stdout with a
     * custom format, and an additional
     * callback to perform once the
     * timings are completed.
     *
     * @apiNote the following replacers
     * are available in the custom format:
     *  - "%t" will be replaced with the
     *         time it took in millis.
     *
     *  - "%c" will be replaced with the
     *         color associated with the
     *         elapsed time in millis.
     *
     *  - "%r" will be replaced with the
     *         {@link AnsiColors#RESET} color
     *         as this is needed so the console
     *         colors don't get messed up.
     *
     * @param callback the callback
     * @param custom the custom format (read apiNote)
     */
    public void complete(Callback callback, String custom) {
        long elapsed = (System.currentTimeMillis() - start);
        logger.info(parse(custom, elapsed));
        callback.complete();
    }

    private String parse(String input, long elapsed) {
        return input.replaceAll("%t", Long.toString(elapsed))
                .replaceAll("%c", getColorForElapsedTime(elapsed))
                .replaceAll("%r", AnsiColors.RESET);
    }

    private String getColorForElapsedTime(long ms) {
        if (ms >= 0 && ms <= 200) {
            return AnsiColors.GREEN;
        }
        if (ms >= 201 && ms <= 400) {
            return AnsiColors.YELLOW;
        }
        if (ms >= 401) {
            return AnsiColors.RED;
        }
        return AnsiColors.PURPLE;
    }

}
