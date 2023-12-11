package ru.stmlabs.demo.reactiveschedulers.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import ru.stmlabs.demo.reactiveschedulers.utils.ThreadUtils;

@Service
public class DataBaseService {

    private static final Scheduler DB_THREAD = Schedulers.newSingle("DB_THREAD");

    public Mono<Integer> fetchDataAsync(Scheduler callbackThread) {
        return Mono.just(42)
                .doOnNext(data -> ThreadUtils.logCurrentThread("Fetch data from DB! " + data))
                .subscribeOn(DB_THREAD)
                .publishOn(callbackThread)
                ;
    }
}
