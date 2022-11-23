package ru.levelp.at.homework6.users.negative.data.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class PostNegativeDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of("April Leannon", "donnie.walter@gmail.com", "male", "activer", "status", "can't be blank"),
            Arguments.of("Test255String", "krysten.oconner@yahoo.com", "notmale", "inactive", "gender",
                "can't be blank, can be male of female"),
            Arguments
                .of("Test255StringTest255StringTest255StringTest255StringTest255String"
                        + "Test255StringTest255StringTest255StringTest255StringTest255String"
                        + "Test255StringTest255StringTest255StringTest255StringTest255String"
                        + "Test255StringTest255StringTest255StringTest255String"
                        + "Te", "krysten.oconner@yahoo.com", "female", "inactive",
                    "name", "is too long (maximum is 200 characters)"),
            Arguments.of("Test", "Test255StringTest255StringTest255StringTest255String"
                    + "Test255StringTest255StringTest255StringTest255StringTest255String"
                    + "Test255StringTest255StringTest255StringTest255StringTest255String"
                    + "Test255StringTest255StringTest255StringTest255String"
                    + "Test255StringTe", "male", "active", "email",
                "is too long (maximum is 200 characters), is invalid")
        );
    }
}
