package org.thales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan("org.thales")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
