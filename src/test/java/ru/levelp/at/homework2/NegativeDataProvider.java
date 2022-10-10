package ru.levelp.at.homework2;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

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
