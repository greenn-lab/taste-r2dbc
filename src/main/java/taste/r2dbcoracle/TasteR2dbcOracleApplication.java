package taste.r2dbcoracle;

import java.time.Duration;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class TasteR2dbcOracleApplication {

  public static void main(String[] args) {
    SpringApplication.run(TasteR2dbcOracleApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(CustomerRepository repository) {
    return args -> {
      repository.saveAll(
          Arrays.asList(
              new Customer("Jack", "Bauer"),
              new Customer("Chloe", "O'Brian"),
              new Customer("Kim", "Bauer"),
              new Customer("David", "Palmer"),
              new Customer("Michelle", "Dessler")
          )
      ).blockLast(Duration.ofSeconds(10));

      // fetch all
      logger.info("Customers found with findAll()");
      logger.info("------------------------------");

      repository.findAll().doOnNext(customer -> {
        logger.info(customer.toString());
      }).blockLast(Duration.ofSeconds(10));

      logger.info("\n\n");

      // fetch an individual customer by ID
      repository.findById(1L).doOnNext(customer -> {
        logger.info("Customer findById(1L)");
        logger.info("------------------------");
        logger.info(customer.toString());
      }).block(Duration.ofSeconds(10));

      logger.info("\n\n");

      // fetch customers by last name
      logger.info("Customer findByLastName('Bauer')");
      logger.info("--------------------------------");
      repository.findByLastName("Bauer").doOnNext(customer -> {
        logger.info(customer.toString());
      }).blockLast(Duration.ofSeconds(10));

    };
  }
}
