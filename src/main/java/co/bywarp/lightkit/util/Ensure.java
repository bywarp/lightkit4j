package co.bywarp.lightkit.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.UUID;
import java.util.function.Function;
import java.util.regex.Pattern;

import lombok.NonNull;

public class Ensure {

    /**
     * Ensures the the provided object is not null.
     *
     * @param object the object
     * @param <T> the object type
     * @throws IllegalArgumentException if the object is null
     */
    public static <T> void nonNull(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
    }

    /**
     * Ensures the the provided object is not null.
     *
     * @param object the object
     * @param message the exception message
     * @param <T> the object type
     *
     * @throws IllegalArgumentException if the object is null
     */
    public static <T> void nonNull(T object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Ensures the the provided object is not null.
     *
     * @param object the object
     * @param exception the exception to throw
     * @param <T> the object type
     * @param <K> the exception type
     *
     * @throws K if the object is null
     */
    public static <T, K extends Exception> void nonNull(T object, K exception) throws K {
        if (object == null) {
            throw exception;
        }
    }

    /**
     * Ensures the the provided object is not null.
     *
     * @param object the object
     * @param exception the exception to throw
     * @param message the exception message
     * @param <T> the object type
     * @param <K> the exception type
     *
     * @throws K if the object is null
     */
    public static <T, K extends Exception> void nonNull(T object, K exception, String message) throws K {
        if (object != null) {
            return;
        }

        try {
            Field exceptionMessage = exception.getClass().getDeclaredField("detailMessage");
            exceptionMessage.setAccessible(true);
            exceptionMessage.set(exception, message);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}

        throw exception;
    }

    /**
     * Whether or not the provided string
     * can be safely converted to an Integer
     * data type.
     *
     * @param input the string
     * @return if the string can be expressed as an integer
     */
    public static boolean isNumeric(@NonNull String input) {
        return safely(input, Integer::parseInt) != null;
    }

    /**
     * Whether or not the provided string
     * can be safely converted to an Double
     * data type.
     *
     * @param input the string
     * @return if the string can be expressed as a double
     */
    public static boolean isDouble(@NonNull String input) {
        return safely(input, Double::parseDouble) != null;
    }

    /**
     * Whether or not the provided string
     * can be safely converted to a JSON
     * object type.
     *
     * @param input the string
     * @return if the string is json
     */
    public static boolean isJson(@NonNull String input) {
        return safely(input, JSONObject::new) != null;
    }

    /**
     * Whether or not the provided string
     * can be safely converted to a JSON
     * array object type.
     *
     * @param input the string
     * @return if the string is a json array
     */
    public static boolean isJsonArray(@NonNull String input) {
        return safely(input, JSONArray::new) != null;
    }

    /**
     * Whether or not the provided string
     * can be safely converted to a UUID
     * (version 4) object type.
     *
     * @param input the string
     * @return if the string is a uuid (v4)
     */
    public static boolean isUuid(@NonNull String input) {
        return safely(input, UUID::fromString) != null;
    }

    /**
     * Whether or not the entire provided
     * string consists of only alphabetic
     * characters.
     *
     * @param input the string
     * @return if the string is alphabetical
     */
    public static boolean isAlphabetical(@NonNull String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isAlphabetic(c)) {
                return false;
            }
        }

        return false;
    }

    /**
     * Whether or not the input's length,
     * when converted to a string, is equal
     * to the expected length.
     *
     * @param input the input
     * @param expected the expected output
     * @param <T> the input type
     *
     * @return if the expected equals the actual
     */
    public static <T> boolean length(@NonNull T input, @NonNull int expected) {
        String string = String.valueOf(input);
        return string.length() == expected;
    }

    /**
     * Whether or not the provided string
     * conforms to the specified regex pattern.
     *
     * @param input the input
     * @param regex the regex pattern
     * @return if it conforms
     */
    public static boolean conforms(@NonNull String input, @NonNull String regex) {
        return input.matches(regex);
    }

    /**
     * Whether or not the provided string
     * conforms to the specified regex pattern.
     *
     * @param input the input
     * @param regex the regex pattern
     * @return if it conforms
     */
    public static boolean conforms(@NonNull String input, @NonNull Pattern regex) {
        return input.matches(regex.pattern());
    }

    /**
     * Whether or not the provided string
     * contains the specified regex pattern.
     *
     * @param input the input
     * @param regex the regex pattern
     * @return if it contains it
     */
    public static boolean contains(@NonNull String input, @NonNull String regex) {
        return Pattern
                .compile(regex)
                .matcher(input)
                .find();
    }

    private static <T, R> R safely(T value, Function<T, R> function) {
        try {
            return function.apply(value);
        } catch (Exception e) {
            return null;
        }
    }

}
