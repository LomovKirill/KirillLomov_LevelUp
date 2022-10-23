package ru.levelp.at.homework6.posts;

import com.github.javafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class PostPositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        var faker = new Faker();

        return Stream.of(
            Arguments.of(faker.random().nextInt(1, 1000), faker.book().title(), faker.book().genre()),
            Arguments.of(faker.random().nextInt(1, 1000), faker.book().title(), faker.book().genre()),
            Arguments.of(faker.random().nextInt(1, 1000), faker.book().title(), faker.book().genre()),
            Arguments.of(faker.random().nextInt(1, 1000), faker.book().title(), faker.book().genre())
        );
    }
}
