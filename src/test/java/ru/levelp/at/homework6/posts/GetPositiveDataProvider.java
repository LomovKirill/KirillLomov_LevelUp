package ru.levelp.at.homework6.posts;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class GetPositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of(1790),
            Arguments.of(1783)
        );
    }
}
