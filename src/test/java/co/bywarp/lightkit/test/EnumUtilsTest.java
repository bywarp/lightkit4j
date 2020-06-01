package co.bywarp.lightkit.test;

import co.bywarp.lightkit.util.EnumUtils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class EnumUtilsTest {

    private enum TestEnum {
        A, B, C
    }

    @Test
    public void match() {
        String shouldPass = "A";
        TestEnum enumValue = EnumUtils.match(TestEnum.class,
                value -> value.name().equalsIgnoreCase(shouldPass));

        assertNotNull(enumValue, "Enum value `" + shouldPass + "` should never be null.");
    }

    @Test
    public void matchByName() {
        String shouldPass = "A";
        TestEnum enumValue = EnumUtils.matchByName(TestEnum.class, shouldPass);

        assertNotNull(enumValue, "Enum value `" + shouldPass + "` should never be null.");
    }

    @Test
    public void matchByOrdinal() {
        int shouldPass = 0;
        TestEnum enumValue = EnumUtils.matchByOrdinal(TestEnum.class, shouldPass);

        assertNotNull(enumValue, "Enum value `" + shouldPass + "` should never be null.");
        assertSame(enumValue, TestEnum.A, "Enum value with ordinal `" + shouldPass + "` should always evaluate to TestEnum.A");
    }

}