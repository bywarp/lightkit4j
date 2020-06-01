package co.bywarp.lightkit.util;

import java.util.function.Predicate;

public class EnumUtils {

    /**
     * Attempts to find an enum constant
     * from the specified enum using a
     * predicate.
     *
     * @param target the target enum
     * @param predicate the predicate to search with
     * @param <T> the enum type
     *
     * @return the enum constant
     */
    public static <T extends Enum<T>> T match(Class<T> target, Predicate<T> predicate) {
        for (T value : target.getEnumConstants()) {
            if (predicate.test(value)) {
                return value;
            }
        }

        return null;
    }

    /**
     * Attempts to find an enum constant
     * from the specified enum by it's
     * {@link Enum#name()}.
     *
     * @param target the target enum
     * @param name the constant's native name
     * @param <T> the enum type
     *
     * @return the enum constant
     */
    public static <T extends Enum<T>> T matchByName(Class<T> target, String name) {
        return match(target, value -> value.name().equalsIgnoreCase(name));
    }

    /**
     * Attempts to find an enum constant
     * from the specified enum by it's
     * {@link Enum#ordinal()}.
     *
     * @param target the target enum
     * @param ordinal the constant ordinal
     * @param <T> the enum type
     *
     * @return the enum constant
     */
    public static <T extends Enum<T>> T matchByOrdinal(Class<T> target, int ordinal) {
        return match(target, value -> value.ordinal() == ordinal);
    }

}
