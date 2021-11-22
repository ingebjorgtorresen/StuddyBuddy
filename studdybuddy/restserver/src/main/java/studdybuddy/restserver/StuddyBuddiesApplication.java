package studdybuddy.restserver;

import com.fasterxml.jackson.databind.Module;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import studdybuddy.json.*;

@RestController
@SpringBootApplication
public class StuddyBuddiesApplication {

  private WelcomeService welcomeService = new WelcomeService();
  
  @GetMapping("/")
  public String home() {
    return this.welcomeService.getWelcomeText();
  }

  @Bean
  public Module objectMapperModule() {
    return new StuddyModule();
  }

  public static void main(String[] args) {
    SpringApplication.run(StuddyBuddiesApplication.class, args);
  }
}