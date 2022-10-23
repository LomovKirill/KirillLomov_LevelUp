package ru.levelp.at.homework6.users;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class GetPositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of("April Leannon", "donnie.walter@gmail.com", "male", "active", 4226),
            Arguments.of("Grant Kunze", "krysten.oconner@yahoo.com", "male", "inactive", 4227),
            Arguments.of("Ms. Lien Hickle", "haywood.price@gmail.com", "female", "inactive", 4214),
            Arguments.of("Dr. Wallace Ondricka", "kanesha.tromp@yahoo.com", "female", "active", 4213)
        );
    }
}
