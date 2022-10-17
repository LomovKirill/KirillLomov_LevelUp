package ru.levelp.at.homework4;

public class SleepUtils {

    private SleepUtils() {
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
