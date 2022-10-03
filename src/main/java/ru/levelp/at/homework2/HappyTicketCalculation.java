package ru.levelp.at.homework2;

import java.util.Collections;

public class HappyTicketCalculation {

    public static Object getHappyTicket(String numberTicket) {
        String isEmpty = "";
        if (numberTicket == null) {
            return isEmpty;
        }

        char[] arrayTest = numberTicket.toCharArray();
        boolean isLucky;

        isLucky = (arrayTest[0] + arrayTest[1] + arrayTest[2]) == (arrayTest[3] + arrayTest[4] + arrayTest[5]);

        return isLucky;
    }
}
