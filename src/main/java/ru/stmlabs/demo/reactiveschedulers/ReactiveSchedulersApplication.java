package ru.stmlabs.demo.reactiveschedulers;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@AllArgsConstructor
@SpringBootApplication
public class ReactiveSchedulersApplication implements CommandLineRunner {

  public final ApplicationContext context;

  public static void main(String[] args) {
    SpringApplication.run(ReactiveSchedulersApplication.class, args);
  }

  @Override
  public void run(String... args) {

  }
}
