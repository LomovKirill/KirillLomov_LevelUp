package ru.levelp.at.homework6.posts.positive.data.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class GetPositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of(300),
            Arguments.of(301)
        );
    }
}
