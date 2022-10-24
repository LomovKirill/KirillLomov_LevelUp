package ru.levelp.at.homework6.comments;

import com.github.javafaker.Faker;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class PositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        var faker = new Faker();

        return Stream.of(
            Arguments.of(227, faker.name().fullName(), faker.internet().emailAddress(), faker.book().publisher(), 100),
            Arguments.of(352, faker.name().fullName(), faker.internet().emailAddress(), faker.book().publisher(), 101)
        );
    }
}
