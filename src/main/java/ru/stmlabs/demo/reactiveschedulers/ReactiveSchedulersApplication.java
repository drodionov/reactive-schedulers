package ru.stmlabs.demo.reactiveschedulers;

import static ru.stmlabs.demo.reactiveschedulers.utils.ThreadUtils.logCurrentThread;
import static ru.stmlabs.demo.reactiveschedulers.utils.ThreadUtils.sleepCurrentThreadForOneSecond;

import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class ReactiveSchedulersApplication implements CommandLineRunner {

  public final ApplicationContext context;

  public static void main(String[] args) {
    SpringApplication.run(ReactiveSchedulersApplication.class, args);
  }

  @Override
  public void run(String... args) {
    logCurrentThread("Before the stream");
    Mono.just(42)
        .delayElement(Duration.ofMillis(100))
        .doOnNext(data -> {
          log.info(data.toString());
          logCurrentThread("In Mono");
        })
        .subscribe();
    logCurrentThread("After the stream");

    sleepCurrentThreadForOneSecond();
  }
}
