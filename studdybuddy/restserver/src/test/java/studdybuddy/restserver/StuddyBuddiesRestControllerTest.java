package studdybuddy.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for StuddyBuddiesRestController.
 */
@SpringBootTest(classes = {StuddyBuddiesRestController.class, StuddyBuddiesService.class, StuddyBuddyApplication.class})
@AutoConfigureMockMvc
public class StuddyBuddiesRestControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private StuddyBuddiesRestController restController;
  
}
