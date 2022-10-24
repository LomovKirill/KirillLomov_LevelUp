package ru.levelp.at.homework6.users.negative.data.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class GetNegativeDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of("April Leannon", "donnie.walter@gmail.com", "male", "qweinactive"),
            Arguments.of("Test", "krysten.oconner@yahoo.com", "", "qweinactive")
        );
    }
}
