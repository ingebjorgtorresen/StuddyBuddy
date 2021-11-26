package studdybuddy.restserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

  private String selmaBuddyString = "{\"Name\":"
      +"\"Selma\",\"Password\":\"Passord1\""
      +",\"Registrations\":[{\"Date\":\"12/12/2022\""
      +",\"Room\":\"S7\",\"Course\":\"Statistikk\""
      +",\"Start time\":\"08:15\",\"End time\":\"10:00\"}"
      +",{\"Date\":\"12/12/2022\",\"Room\":\"A3-138\""
      +",\"Course\":\"Informatikk prosjektarbeid\""
      +",\"Start time\":\"10:15\",\"End time\":\"12:00\"}]}";

  private String selmaPasswordString = "Passord1";

  /**
   * Tests getStuddyBuddies
   * @throws Exception if mockMvc fails
   */
  @Test
  public void testGetBuddies() throws Exception {
    mockMvc.perform( MockMvcRequestBuilders
      .get("/studdybuddy")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
  }

  /**
   * Tests getStuddyBuddy
   * @throws Exception if mockMvc fails
   */
  @Test
  public void testGetBuddy() throws Exception {
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders
      .get("/studdybuddy/Selma")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andReturn();

    String resultBuddyString = result.getResponse().getContentAsString();
    assertEquals(selmaBuddyString, resultBuddyString);
  }

  /**
   * Tests getStuddyBuddy
   * @throws Exception if mockMvc fails
   */
  @Test
  public void testGetPassword() throws Exception {
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders
      .get("/studdybuddy/Selma/pw")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andReturn();

    String resultPasswordString = result.getResponse().getContentAsString();
    assertEquals(selmaPasswordString, resultPasswordString);
  }

}
