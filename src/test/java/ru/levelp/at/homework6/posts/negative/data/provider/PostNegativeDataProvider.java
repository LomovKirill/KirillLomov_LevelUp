package ru.levelp.at.homework6.posts.negative.data.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class PostNegativeDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of(0, "title", "body"),
            Arguments.of(1, "", "body"),
            Arguments.of(1, "title", "")
        );
    }
}
