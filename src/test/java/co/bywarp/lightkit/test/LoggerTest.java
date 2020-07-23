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

package co.bywarp.lightkit.test;

import co.bywarp.lightkit.util.logger.Logger;

import org.junit.jupiter.api.Test;

public class LoggerTest {

    @Test
    public void log() {
        Logger logger = new Logger("Test");
        logger.info("This is an info entry.");
        logger.debug("This is a debug string.");
        logger.warning("This is a warning.");
        logger.severe("This is an error.");
        logger.except(new Exception("Generic Exception"), "Oops, something happened");
    }

}