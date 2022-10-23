package ru.levelp.at.homework6.users;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class GetNegativeDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of("April Leannon", "donnie.walter@gmail.com", "male", "qweinactive"),
            Arguments.of("Test", "krysten.oconner@yahoo.com", "", "qweinactive")
        );
    }
}
