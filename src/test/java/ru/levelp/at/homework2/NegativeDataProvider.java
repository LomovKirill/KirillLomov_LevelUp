package ru.levelp.at.homework2;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class NegativeDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of(null, false),
            Arguments.of("Test", false),
            Arguments.of("12345", false),
            Arguments.of("1234567", false),
            Arguments.of("", false)
        );
    }
}
