package ru.levelp.at.homework2;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class NegativeDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of(null, "")
        );
    }
}
