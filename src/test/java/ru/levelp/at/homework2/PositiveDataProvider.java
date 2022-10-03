package ru.levelp.at.homework2;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class PositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of("123123", true),
            Arguments.of("223112", false),
            Arguments.of("435435", true),
            Arguments.of("111831", false)
        );
    }
}
