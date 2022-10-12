package ru.levelp.at.homework2;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class PositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of("123123", true),
            Arguments.of("435435", true),
            Arguments.of("554431", false),
            Arguments.of("891231", false)
        );
    }
}
