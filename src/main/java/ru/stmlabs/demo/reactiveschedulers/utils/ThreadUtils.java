package ru.stmlabs.demo.reactiveschedulers.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ThreadUtils {

  public static void logCurrentThread() {
    logCurrentThread("");
  }

  public static void logCurrentThread(String message) {
    log.info("Current thread name {}: {}", message, Thread.currentThread().getName());
  }

  @SneakyThrows
  public static void sleepCurrentThreadForOneSecond() {
    Thread.sleep(1000);
  }
}
