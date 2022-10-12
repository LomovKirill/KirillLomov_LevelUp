package ru.levelp.at.homework2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PositiveTestIT {

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework2.PositiveDataProvider#dataTest")
    public void sendIsLuckyTrue(String num, boolean actual) {
        Object actualOutput = HappyTicketCalculation.getHappyTicket(num);
        Assertions.assertThat(actualOutput).isEqualTo(actual);
    }
}
