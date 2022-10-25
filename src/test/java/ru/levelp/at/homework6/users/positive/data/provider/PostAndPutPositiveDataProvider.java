package ru.levelp.at.homework6.users.positive.data.provider;

import com.github.javafaker.Faker;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class PostAndPutPositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        var faker = new Faker();

        return Stream.of(
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "male", "active"),
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "male", "inactive"),
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "female", "active"),
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "female", "inactive")
        );
    }
}
