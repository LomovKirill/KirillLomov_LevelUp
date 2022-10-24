package ru.levelp.at.homework6.users.negative.data.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class PutNegativeDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of("April Leannon", "donnie.walter@gmail.com", "male",
                "activer", "status", "can't be blank", 3001),
            Arguments.of("Test255String", "krysten.oconner@yahoo.com", "notmale",
                "inactive", "gender",
                "can't be blank, can be male of female", 3001),
            Arguments
                .of("Test255StringTest255StringTest255StringTest255String"
                        + "Test255StringTest255StringTest255StringTest255String"
                        + "Test255StringTest255StringTest255StringTest255String"
                        + "Test255StringTest255StringTest255StringTest255String"
                        + "Test255StringTest255StringTest255StringTe",
                    "krysten.oconner@yahoo.com", "female", "inactive",
                    "name", "is too long (maximum is 200 characters)", 3001),
            Arguments.of("Test", "Test255StringTest255StringTest255String"
                    + "Test255StringTest255StringTest255StringTest255String"
                    + "Test255StringTest255StringTest255StringTest255String"
                    + "Test255StringTest255StringTest255StringTest255String"
                    + "Test255StringTest255StringTest255StringTest255StringTe",
                "male", "active", "email",
                "is too long (maximum is 200 characters), is invalid", 3001)
        );
    }
}
