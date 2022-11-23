package ru.levelp.at.homework6.posts.positive.data.provider;

import com.github.javafaker.Faker;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class PostPositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        var faker = new Faker();

        return Stream.of(
            Arguments.of(227, faker.book().title(), faker.book().genre()),
            Arguments.of(352, faker.book().title(), faker.book().genre())
        );
    }
}
