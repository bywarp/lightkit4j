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

@Getter
@AllArgsConstructor
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

    /**
     * Creates a singleton instance of a Logger.
     * @param name the name of the logger
     * @return the logger
     */
    public static Logger asSingleton(String name) {
        return new Logger(name);
    }

    /**
     * Creates a single instance of a Logger.
     *
     * @param name the name of the logger
     * @param color the color of the logger
     * @return the logger
     */
    public static Logger asSingleton(String name, Color color) {
        return new Logger(name, color);
    }

    /**
     * Logs a message to stdout.
     * @param msg the message
     */
    public void raw(String msg) {
        System.out.println(msg);
    }

    /**
     * Logs a formatted message to stdout.
     * @param body the message
     */
    public void log(String body) {
        raw(AnsiColors.WHITE + TimeUtils.format(System.currentTimeMillis()) + color.getColor() + " | " + AnsiColors.RESET + body);
    }

    /**
     * Logs a formatted message to stdout.
     *
     * @param level the logging level
     * @param body the message
     */
    public void log(LoggingLevel level, String body) {
        raw(AnsiColors.WHITE + TimeUtils.format(System.currentTimeMillis()) + " " + level.getColor() + level.getName() + AnsiColors.YELLOW + StringUtils.repeat(" ", level.getSpaces()) + "| " + AnsiColors.RESET + body);
    }

    /**
     * Logs a formatted message to stdout.
     *
     * @param color the color of the head
     * @param head the header of the log
     * @param body the message
     */
    public void log(String color, String head, String body) {
        raw(AnsiColors.WHITE + TimeUtils.format(System.currentTimeMillis()) + this.color.getColor() + " | [" + color + head + AnsiColors.RESET + "] " + body);
    }

    /**
     * Logs a formatted message to stdout.
     *
     * @param condition the condition to log this message
     * @param color the color of the head
     * @param head the header of the log
     * @param body the message
     */
    public void log(boolean condition, String color, String head, String body) {
        if (condition)
            raw(AnsiColors.WHITE + TimeUtils.format(System.currentTimeMillis()) + this.color.getColor() + " | [" + color + head + "] " + AnsiColors.RESET + body);
    }

    /**
     * Logs a formatted message with no header.
     * @param body the message
     */
    public void unlisted(String body) {
        log(LoggingLevel.INFO, AnsiColors.RESET + body);
    }

    /**
     * Logs a formatted message with no header.
     * @param condition the condition to log this message
     * @param body the message
     */
    public void unlisted(String body, boolean condition) {
        if (condition)
            this.unlisted(body);
    }

    /**
     * Logs an informative message.
     * @param body the message
     */
    public void info(String body) {
        log(LoggingLevel.INFO, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + body);
    }

    /**
     * Logs an informative message.
     * @param condition the condition to log this message
     * @param body the message
     */
    public void info(String body, boolean condition) {
        if (condition)
            this.info(body);
    }

    /**
     * Logs a debug message.
     * @param body the message
     */
    public void debug(String body) {
        log(LoggingLevel.DEBUG, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + body);
    }

    /**
     * Logs a debug message.
     * @param condition the condition to log this message
     * @param body the message
     */
    public void debug(String body, boolean condition) {
        if (condition)
            this.debug(body);
    }

    /**
     * Logs a warning message.
     * @param body the message
     */
    public void warning(String body) {
        log(LoggingLevel.WARNING, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + body);
    }

    /**
     * Logs a warning message.
     * @param condition the condition to log this message
     * @param body the message
     */
    public void warning(String body, boolean condition) {
        if (condition)
            this.warning(body);
    }

    /**
     * Logs an error message.
     * @param body the message
     */
    public void severe(String body) {
        log(LoggingLevel.SEVERE, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + body);
    }

    /**
     * Logs an error message.
     * @param condition the condition to log this message
     * @param body the message
     */
    public void severe(String body, boolean condition) {
        if (condition)
            this.severe(body);
    }

    /**
     * Logs an exception.
     * @param exception the exception
     */
    public void except(Exception exception) {
        log(LoggingLevel.SEVERE, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + exception.getMessage() + " (" + exception.getClass().getName() + ")");
    }

    /**
     * Logs an exception.
     *
     * @apiNote the "base" param lets
     * you specify what comes before the
     * exception information in the following
     * format: (do not include a colon, it will
     * do this for you!)
     *
     * <base>: <exception message> (<exception class>)
     *
     * @param exception the exception
     * @param base the base of the message
     */
    public void except(Exception exception, String base) {
        log(LoggingLevel.SEVERE, AnsiColors.WHITE + "[" + this.name + "] " + AnsiColors.RESET + base + ": " + exception.getMessage() + " (" + exception.getClass().getName() + ")");
    }

    /**
     * Logs an exception.
     *
     * @apiNote the "base" param lets
     * you specify what comes before the
     * exception information in the following
     * format: (do not include a colon, it will
     * do this for you!)
     *
     * <base>: <exception message> (<exception class>)
     *
     * @param exception the exception
     * @param base the base of the message
     * @param condition the condition to log this message
     */
    public void except(Exception exception, String base, boolean condition) {
        if (condition)
            this.except(exception, base);
    }

}