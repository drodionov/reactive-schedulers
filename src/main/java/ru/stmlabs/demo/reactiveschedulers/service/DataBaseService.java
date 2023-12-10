package ru.stmlabs.demo.reactiveschedulers.service;

import static ru.stmlabs.demo.reactiveschedulers.utils.ThreadUtils.logCurrentThread;
import static ru.stmlabs.demo.reactiveschedulers.utils.ThreadUtils.sleepCurrentThreadForOneSecond;

import java.time.Duration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Service
public class DataBaseService {

  public static final Scheduler DB_SCHEDULER = Schedulers.newSingle("DB_THREAD");
  public static final int THE_ANSWER_OF_LIFE_UNIVERSE_AND_EVERYTHING = 42;

  public Mono<Integer> fetchTheAnswerOfLifeUniverseAndEverything(Scheduler callbackScheduler) {
    return Mono.just(THE_ANSWER_OF_LIFE_UNIVERSE_AND_EVERYTHING)
        .doOnNext(data -> {
          logCurrentThread("Before publish & Subscribe");
          sleepCurrentThreadForOneSecond();
        })
        .publishOn(callbackScheduler)
//        .doOnNext(data -> logCurrentThread("After publish & Subscribe"))
        .subscribeOn(DB_SCHEDULER)
        ;
  }
}
