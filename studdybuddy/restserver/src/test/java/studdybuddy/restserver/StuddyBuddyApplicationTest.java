package studdybuddy.restserver;

import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StuddyBuddyApplication.class)
@AutoConfigureMockMvc
public class StuddyBuddyApplicationTest {

  @Autowired
	private MockMvc mockMvc;
  
  @Test
  public void testWelcomeText() throws Exception {
    mockMvc.perform(get("/")).andExpect(status().isOk())
    .andExpect(content().string(containsString("Welcome to rest API for StuddyBuddy.")));
  }
}