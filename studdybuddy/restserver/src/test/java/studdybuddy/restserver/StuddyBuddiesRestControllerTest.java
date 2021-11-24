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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for StuddyBuddiesRestController.
 */
@SpringBootTest(classes = StuddyBuddiesRestController.class)
@AutoConfigureMockMvc
public class StuddyBuddiesRestControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private StuddyBuddiesRestController restController;

  @Test
  public void testGetBuddies() {
    try {
      mockMvc.perform(get("")).andExpect(status().isOk())
      .andExpect(jsonPath("$.studdybuddy").isArray());
    } catch (Exception e) {
        fail();
    }
  }
}
