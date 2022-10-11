package ru.levelp.at.homework2;

public class HappyTicketCalculation {

    private static final String regexp = "^[0-9]{6}";

    public static boolean getHappyTicket(String numberTicket) {
        if (numberTicket == null) {
            return false;
        } else if (!numberTicket.matches(regexp)) {
            return false;
        } else {
            char[] arrayTest = numberTicket.toCharArray();
            return (arrayTest[0] + arrayTest[1] + arrayTest[2]) == (arrayTest[3] + arrayTest[4] + arrayTest[5]);
        }
    }
}
