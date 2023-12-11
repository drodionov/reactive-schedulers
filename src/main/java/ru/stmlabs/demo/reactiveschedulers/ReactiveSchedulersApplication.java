package ru.stmlabs.demo.reactiveschedulers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import ru.stmlabs.demo.reactiveschedulers.service.DataBaseService;

import java.time.Duration;

import static ru.stmlabs.demo.reactiveschedulers.utils.ThreadUtils.logCurrentThread;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class ReactiveSchedulersApplication implements CommandLineRunner {

    private final ApplicationContext context;

    private static final Scheduler FIRST_MAIN_SCHED = Schedulers.newSingle("FIRST_MAIN_SCHED");
    private static final Scheduler SECOND_MAIN_SCHED = Schedulers.newSingle("SECOND_MAIN_SCHED");
    private static final Scheduler THIRD_MAIN_SCHED = Schedulers.newSingle("THIRD_MAIN_SCHED");

    public static void main(String[] args) {
        SpringApplication.run(ReactiveSchedulersApplication.class, args);
    }

    @Override
    public void run(String... args) {
        DataBaseService service = context.getBean(DataBaseService.class);

        service.fetchDataAsync(FIRST_MAIN_SCHED)
//                .publishOn(FIRST_MAIN_SCHED)
                .delayElement(Duration.ofMillis(100))
                .doOnNext(data -> logCurrentThread("Got data!"))
                .subscribe();

//        Mono.just(1)
//                .doOnNext(data -> logCurrentThread("doOnNext " + data))
//                .publishOn(FIRST_MAIN_SCHED)
//                .doOnNext(data->logCurrentThread("doOnNext2 " + data))
//                .publishOn(THIRD_MAIN_SCHED)
//                .doOnNext(data->logCurrentThread("doOnNext3"))
//                .subscribeOn(SECOND_MAIN_SCHED)
//                .subscribe();
    }
}
