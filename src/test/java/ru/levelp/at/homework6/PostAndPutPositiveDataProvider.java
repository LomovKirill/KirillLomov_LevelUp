package ru.levelp.at.homework6;

import com.github.javafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class PostAndPutPositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        var faker = new Faker();

        return Stream.of(
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "male", "active", 3744),
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "male", "inactive" , 3742),
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "female", "active", 3741),
            Arguments.of(faker.name().fullName(), faker.internet().emailAddress(), "female", "inactive", 3739)
        );
    }
}
