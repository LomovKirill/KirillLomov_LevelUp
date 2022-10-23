package ru.levelp.at.homework6.users;

import com.github.javafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class DeletePositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        var faker = new Faker();

        return Stream.of(
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "male", "active"),
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "male", "inactive" ),
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "female", "active"),
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "female", "inactive")
        );
    }
}
