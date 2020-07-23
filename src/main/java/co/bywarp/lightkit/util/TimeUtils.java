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

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("Duplicates")
public class TimeUtils {

    private static DateFormat DATE_FORMAT = new SimpleDateFormat("M/d/Y HH:mm:ss a");
    public static DateFormat FULL_DATE_FORMAT = new SimpleDateFormat("MMMM dd yyyy, h:mm:ss a");
    public static DateFormat DATE_NO_TIME_FORMAT = new SimpleDateFormat("MMMM dd, yyyy");
    private static DecimalFormat STD_DECIMAL_FORMAT = new DecimalFormat("#.##");

    public static final long DAY = 24 * 60 * 60 * 1000;
    public static final long LONG_NAN = -7777L;

    /**
     * Formats a millis time string
     * using a provided date format.
     *
     * @param format the date format
     * @param input the input millis
     * @return the formatted date string
     */
    public static String format(SimpleDateFormat format, long input) {
        return format.format(input);
    }

    /**
     * Formats a millis time string
     * using the default date format.
     *
     * @param input the input millis
     * @return the formatted date string
     */
    public static String format(long input) {
        return DATE_FORMAT.format(input);
    }

    /**
     * Formats a millis time string
     * using the default date format.
     *
     * @param input the date
     * @return the formatted date string
     */
    public static String format(Date input) {
        return format(input.getTime());
    }

    /**
     * Formats a millis time difference.
     *
     * @param time the time difference
     * @return the duration string
     */
    public static String getTime(long time) {
        int seconds = (int)(time / 1000L) % 60;
        int minutes = (int)(time / 60000L % 60L);
        int hours = (int)(time / 3600000L % 24L);
        int day = (int)(time / 86400000L % 30.4368D);
        int month = (int)(time / 2.6297424E9D % 12.0D);
        int year = (int)(time / 3.15569088E10D);

        String y = year + " year" + numberEnding(year);
        String mo = month  + " month" + numberEnding(month);
        String d = day + " day" + numberEnding(day);
        String h = hours + " hour" + numberEnding(hours);
        String m = minutes + " minute" + numberEnding(minutes);
        String s = seconds + " second" + numberEnding(seconds);

        return y + ", " + mo + ", " + d + ", " + h + ", " + m + ", " + s;
    }

    /**
     * Returns whether an "s" should
     * follow a string given an integer.
     *
     * @param i the integer
     * @return the suffix
     */
    public static String numberEnding(int i) {
        return i == 1 ? "":"s";
    }

    /**
     * Returns whether a millis date time
     * occurred within the last day.
     *
     * @param time the millis time
     * @return if it occurred within the last day
     */
    public static boolean occurredInLastDay(long time) {
        return time > System.currentTimeMillis() - DAY;
    }

    /**
     * Formats a millis duration.
     *
     * @param time the millis duration
     * @return the formatted duration string
     */
    public static String getLatestTimeValue(long time) {
        int second = (int)(time / 1000L) % 60;
        int minutes = (int)(time / 60000L % 60L);
        int hours = (int)(time / 3600000L % 24L);
        int days = (int)(time / 86400000L % 30.4368D);
        int month = (int)(time / 2.6297424E9D % 12.0D);
        int year = (int)(time / 3.15569088E10D);

        String y = year + " year" + numberEnding(year);
        String mo = month + " month" + numberEnding(month);
        String d = days + " day" + numberEnding(days);
        String h = hours + " hour" + numberEnding(hours);
        String m = minutes + " minute" + numberEnding(minutes);
        String s = second + " second" + numberEnding(second);

        StringBuilder sb = new StringBuilder();
        if (year != 0) {
            sb.append(y + ", ");
        }
        if (month != 0) {
            sb.append(mo + ", ");
        }
        if (days != 0) {
            sb.append(d + ", ");
        }
        if (hours != 0) {
            sb.append(h + ", ");
        }
        if (minutes != 0) {
            sb.append(m + ", ");
        }
        sb = new StringBuilder(sb.substring(0, Math.max(0, sb.length() - 2)));
        if ((year != 0 || month != 0 || days != 0 || minutes != 0 || hours != 0) && second != 0) {
            sb.append(", " + s);
        }
        if (year == 0 && month == 0 && days == 0 && minutes == 0 && hours == 0) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * Formats a millis duration
     * with a more shortened output.
     *
     * @param time the millis duration
     * @return the formatted duration string
     */
    public static String getShortenedTimeValue(long time) {
        int second = (int)(time / 1000L) % 60;
        int minutes = (int)(time / 60000L % 60L);
        int hours = (int)(time / 3600000L % 24L);
        int days = (int)(time / 86400000L % 30.4368D);
        int month = (int)(time / 2.6297424E9D % 12.0D);
        int year = (int)(time / 3.15569088E10D);

        String y = year + "y";
        String mo = month + "mo";
        String d = days + "d";
        String h = hours + "h";
        String m = minutes + "m";
        String s = second + "s";

        StringBuilder sb = new StringBuilder();
        if (year != 0) {
            sb.append(y + ", ");
        }
        if (month != 0) {
            sb.append(mo + ", ");
        }
        if (days != 0) {
            sb.append(d + ", ");
        }
        if (hours != 0) {
            sb.append(h + ", ");
        }
        if (minutes != 0) {
            sb.append(m + ", ");
        }
        sb = new StringBuilder(sb.substring(0, Math.max(0, sb.length() - 2)));
        if ((year != 0 || month != 0 || days != 0 || minutes != 0 || hours != 0) && second != 0) {
            sb.append(", " + s);
        }
        if (year == 0 && month == 0 && days == 0 && minutes == 0 && hours == 0) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * Formats a decimal according
     * to the standard decimal format.
     *
     * @param input the inputted decimal
     * @return the formatted decimal
     */
    public static String formatStdDecimal(double input) {
        return STD_DECIMAL_FORMAT.format(input);
    }

    /**
     * Returns whether the inputted month
     * and day numbers are valid in regards
     * to the gregorian calendar standard.
     *
     * @param day the inputted day
     * @param month the inputted month
     * @return if the day/month combination is valid
     */
    public static boolean isDate(int day, int month) {
        if (day < 1 || day > 31) {
            return false;
        }

        // TODO: Add leap year detection
        if (month == 2 && day > 29) {
            return false;
        }

        if (month < 1 || month > 12) {
            return false;
        }

        return true;
    }

    /**
     * Parses a time string into millis.
     *
     * @apiNote the inputted string must
     * consist of numbers and time specifiers.
     * The string cannot consist of spaces, and
     * the following specifiers are valid:
     *  - "y" standing for years
     *  - "M" standing for months
     *  - "d" standing for days
     *  - "h" standing for hours
     *  - "m" standing for minutes
     *  - "s" standing for seconds
     *
     * These can be combined in strings
     * such as "1d50m30s" to yield 1 day,
     * 50 minutes, and 30 seconds.
     *
     * @param input the inputted string
     * @return the resulting time in millis
     */
    public static long parse(String input) {
        if (input == null || input.isEmpty()) {
            return -1L;
        }

        long result = 0L;
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                number.append(c);
                continue;
            }

            String str;
            if (Character.isLetter(c) && !(str = number.toString()).isEmpty()) {
                result += convert(Integer.parseInt(str), c);
                number = new StringBuilder();
            }
        }

        return result;
    }

    private static long convert(int value, char unit) {
        switch (unit) {
            case 'y':
                return value * TimeUnit.DAYS.toMillis(365L);
            case 'M':
                return value * TimeUnit.DAYS.toMillis(30L);
            case 'd':
                return value * TimeUnit.DAYS.toMillis(1L);
            case 'h':
                return value * TimeUnit.HOURS.toMillis(1L);
            case 'm':
                return value * TimeUnit.MINUTES.toMillis(1L);
            case 's':
                return value * TimeUnit.SECONDS.toMillis(1L);
        }
        return -1L;
    }

}