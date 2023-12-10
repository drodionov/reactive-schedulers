package ru.stmlabs.demo.reactiveschedulers;

import static ru.stmlabs.demo.reactiveschedulers.utils.ThreadUtils.logCurrentThread;
import static ru.stmlabs.demo.reactiveschedulers.utils.ThreadUtils.sleepCurrentThreadForOneSecond;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import ru.stmlabs.demo.reactiveschedulers.service.DataBaseService;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class ReactiveSchedulersApplication implements CommandLineRunner {

  public final ApplicationContext context;

  private static final Scheduler MAIN_SCHEDULER = Schedulers.newSingle("MAIN_THREAD");

  public static void main(String[] args) {
    SpringApplication.run(ReactiveSchedulersApplication.class, args);
  }

  @Override
  public void run(String... args) {
    var dbService = context.getBean(DataBaseService.class);
    logCurrentThread("Before the stream");
    dbService.fetchTheAnswerOfLifeUniverseAndEverything(MAIN_SCHEDULER)
//        .publishOn(MAIN_SCHEDULER)
        .doOnNext(data -> logCurrentThread("Process the answer"))
        .subscribeOn(MAIN_SCHEDULER)
        .subscribe();
    logCurrentThread("After the stream");

    sleepCurrentThreadForOneSecond();
    DataBaseService.DB_SCHEDULER.dispose();
    MAIN_SCHEDULER.dispose();
  }
}
