package ru.stmlabs.demo.reactiveschedulers.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ThreadUtils {

    public static void logCurrentThread(String message) {
        log.info("Current thread name ({}): {}", message, Thread.currentThread().getName());
    }

    public static void sleepCurrentThreadForOneSecond() {
        sleepCurrentThread(1_000);
    }

    @SneakyThrows
    public static void sleepCurrentThread(long millis) {
        Thread.sleep(millis);
    }
}
