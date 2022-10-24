package ru.levelp.at.homework6.users.positive.data.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class GetPositiveDataProvider {
    public static Stream<Arguments> dataTest() {
        return Stream.of(
            Arguments.of("Shakuntala Marar", "shakuntala_marar@lind.co", "female", "active", 301),
            Arguments.of("Sen. Chanakya Chopra", "sen_chanakya_chopra@medhurst-mclaughlin.net", "male", "inactive", 300)
        );
    }
}
