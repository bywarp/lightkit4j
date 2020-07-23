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

package co.bywarp.lightkit.util.logger;

import co.bywarp.lightkit.util.AnsiColors;
import co.bywarp.lightkit.util.StringUtils;
import co.bywarp.lightkit.util.TimeUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Logger {

    @Getter
    @AllArgsConstructor
    public enum Color {

        RESET(AnsiColors.RESET),
        RED(AnsiColors.RED),
        YELLOW(AnsiColors.YELLOW),
        GREEN(AnsiColors.GREEN),
        CYAN(AnsiColors.CYAN),
        BLUE(AnsiColors.BLUE),
        PURPLE(AnsiColors.PURPLE),
        BLACK(AnsiColors.BLACK),
        WHITE(AnsiColors.WHITE);

        private String color;

    }

    private String name;
    private Color color;

    public Logger(String name) {
        this.name = name;
        this.color = Color.GREEN;
    }

    public Logger(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public static Logger asSingleton(String name) {
        return new Logger(name);
    }

    public void raw(String msg) {
        System.out.println(msg);
    }

    public void log(String body) {
        raw(AnsiColors.WHITE + TimeUtils.format(System.currentTimeMillis()) + color.getColor() + " | " + AnsiColors.RESET + body);
    }

    public void log(LoggingLevel level, String body) {
        raw(AnsiColors.WHITE + TimeUtils.format(System.currentTimeMillis()) + " " + level.getColor() + level.getName() + AnsiColors.YELLOW + StringUtils.repeat(" ", level.getSpaces()) + "| " + AnsiColors.RESET + body);
    }

    public void log(String color, String head, String body) {
        raw(AnsiColors.WHITE + TimeUtils.format(System.currentTimeMillis()) + this.color.getColor() + " | [" + color + head + "] " + AnsiColors.RESET + body);
    }

    public void log(boolean condition, String color, String head, String body) {
        if (condition)
            raw(AnsiColors.WHITE + TimeUtils.format(System.currentTimeMillis()) + this.color.getColor() + " | [" + color + head + "] " + AnsiColors.RESET + body);
    }

    public void unlisted(String body) {
        log(LoggingLevel.INFO, AnsiColors.RESET + body);
    }

    public void unlisted(String body, boolean condition) {
        if (condition)
            this.unlisted(body);
    }

    public void info(String body) {
        log(LoggingLevel.INFO, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + body);
    }

    public void info(String body, boolean condition) {
        if (condition)
            this.info(body);
    }

    public void debug(String body) {
        log(LoggingLevel.DEBUG, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + body);
    }

    public void debug(String body, boolean condition) {
        if (condition)
            this.debug(body);
    }

    public void warning(String body) {
        log(LoggingLevel.WARNING, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + body);
    }

    public void warning(String body, boolean condition) {
        if (condition)
            this.warning(body);
    }

    public void severe(String body) {
        log(LoggingLevel.SEVERE, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + body);
    }

    public void severe(String body, boolean condition) {
        if (condition)
            this.severe(body);
    }

    public void except(Exception e) {
        log(LoggingLevel.SEVERE, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + e.getMessage() + " (" + e.getClass().getName() + ")");
    }

    public void except(Exception e, String base) {
        log(LoggingLevel.SEVERE, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + base + ": " + e.getMessage() + " (" + e.getClass().getName() + ")");
    }

    public void except(Exception e, String base, boolean condition) {
        if (condition)
            this.except(e, base);
    }

}