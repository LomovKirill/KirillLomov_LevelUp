package ru.levelp.at.homework2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class NegativeTestIT {

    @ParameterizedTest
    @MethodSource("ru.levelp.at.homework2.NegativeDataProvider#dataTest")
    public void sendEmptyTicket (String num, String actual) {
        Object actualOutput = HappyTicketCalculation.getHappyTicket(num);
        Assertions.assertThat(actualOutput).isEqualTo(actual);
        System.out.println("123");
    }
}
