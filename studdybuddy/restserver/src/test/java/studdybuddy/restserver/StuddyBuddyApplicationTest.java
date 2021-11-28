package studdybuddy.restserver;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for StuddyBuddyApllication.
 */
@SpringBootTest(classes = StuddyBuddyApplication.class)
@AutoConfigureMockMvc
public class StuddyBuddyApplicationTest {

  @Autowired
	private MockMvc mockMvc;
  
  /**
   * Tests that the home method returns the welcome massage.
   * Only tests get methods seeing as they would not work without put and post.
   */
  @Test
  public void testHome() {
    try {
      mockMvc.perform(get("/")).andExpect(status().isOk())
      .andExpect(content().string(containsString("Welcome to rest API for StuddyBuddy.")));
    } catch (Exception e) {
        fail();
    }
  }
}