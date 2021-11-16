package studdybuddy.restserver;

import com.fasterxml.jackson.databind.Module;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import studdyBuddy.core.*;


@SpringBootApplication
public class StuddyBuddiesApplication {

@Bean
  public Module objectMapperModule() {
    return new StuddyBuddiesModule();
  }

  public static void main(String[] args) {
    SpringApplication.run(StuddyBuddiesApplication.class, args);
  }
}