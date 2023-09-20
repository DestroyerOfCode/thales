package org.thales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

//@EnableAutoConfiguration
@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "org.thales")
@EnableElasticsearchRepositories
//@ComponentScan("org.thales")
public class Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
    String[] beans = (ctx.getBeanDefinitionNames());
    Arrays.sort(beans);
    for (String bean : beans) {
      System.out.println(bean);
    }
  }
}
