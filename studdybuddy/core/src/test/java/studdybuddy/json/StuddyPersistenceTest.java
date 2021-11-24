package studdybuddy.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.core.StuddyBuddyRegistration;

/**
 * Test for StuddyPersistence.
 * Indirectly tests all classes in studdybuddy/json
 * because they are all used in StuddyPersistence.
 */
public class StuddyPersistenceTest {

  private StuddyBuddiesPersistence persistence;
  private String testFileName = "/workspace/gr2144/studdybuddy/core/src/test/resources/studdybuddy/json/testPersistence.json";
  private StuddyBuddies buddies;
  private StuddyBuddy buddy1;
  private StuddyBuddy buddy2;
  private StuddyBuddyRegistration registration1;
  private StuddyBuddyRegistration registration2;
  private StuddyBuddyRegistration registration3;
  private Writer writer;
  private Reader reader;

  /**
   * Sets up two StuddyBuddy objects with registrations, 
   * a reader and a writer for testing.
   */
  @BeforeEach
  public void setup() {
    buddies = new StuddyBuddies();
    persistence = new StuddyBuddiesPersistence();

    buddy1 = new StuddyBuddy();
    buddy1.setName("Frank");
    buddy1.setPassword("eple1234");
    registration1 = new StuddyBuddyRegistration();
    LocalDate date1;
    date1 = LocalDate.of(2022, 10, 10);
    registration1.setDate(date1);
    registration1.setRoom("A3-138");
    registration1.setCourse("ITGK");
    registration1.setStartTime("08:00");
    registration1.setEndTime("12:00");

    buddy2 = new StuddyBuddy();
    buddy2.setName("Sofie");
    buddy2.setPassword("123456789");
    registration2 = new StuddyBuddyRegistration();
    LocalDate date2;
    date2 = LocalDate.of(2022, 12, 12);
    registration2.setDate(date2);
    registration2.setRoom("S7");
    registration2.setCourse("Statistikk");
    registration2.setStartTime("10:15");
    registration2.setEndTime("12:00");
    registration3 = new StuddyBuddyRegistration();
    LocalDate date3;
    date3 = LocalDate.of(2022, 06, 03);
    registration3.setDate(date3);
    registration3.setRoom("F1");
    registration3.setCourse("Datamaskiner og digitalteknikk");
    registration3.setStartTime("14:15");
    registration3.setEndTime("16:00");

    buddy1.addRegistration(registration1);
    buddy2.addRegistration(registration2);
    buddy2.addRegistration(registration3);
    buddies.addStuddyBuddy(buddy1);
    buddies.addStuddyBuddy(buddy2);

    try {
      writer = new FileWriter(testFileName, StandardCharsets.UTF_8);
      reader = new FileReader(testFileName, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes the StuddyBuddy objects to a test file,
   * and checks that the reader returns objects that
   * are equal to the ones that were written.
   */
  @Test
  public void testPersistence() {
    StuddyBuddies testBuddies = null;
    try {
      persistence.writeStuddyBuddies(buddies, writer);
      testBuddies = persistence.readStuddyBuddies(reader);
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Couldn't write/read to/from test file.");
    }

    Assertions.assertEquals(buddy1.getName(), testBuddies.getStuddyBuddy(buddy1.getName()).getName());
    Assertions.assertEquals(buddy1.getPassword(), testBuddies.getStuddyBuddy(buddy1.getName()).getPassword());
    
    Assertions.assertEquals(buddy2.getName(), testBuddies.getStuddyBuddy(buddy2.getName()).getName());
    Assertions.assertEquals(buddy2.getPassword(), testBuddies.getStuddyBuddy(buddy2.getName()).getPassword());
    
    StuddyBuddyRegistration testRegistration1 = testBuddies.getStuddyBuddy(buddy2.getName()).getRegistrations().get(0);
    Assertions.assertEquals(registration2.getDate(), testRegistration1.getDate());
    Assertions.assertEquals(registration2.getCourse(), testRegistration1.getCourse());
    Assertions.assertEquals(registration2.getRoom(), testRegistration1.getRoom());
    Assertions.assertEquals(registration2.getStartTime(), testRegistration1.getStartTime());
    Assertions.assertEquals(registration2.getEndTime(), testRegistration1.getEndTime());
    
    StuddyBuddyRegistration testRegistration2 = testBuddies.getStuddyBuddy(buddy2.getName()).getRegistrations().get(1);
    Assertions.assertEquals(registration3.getDate(), testRegistration2.getDate());
    Assertions.assertEquals(registration3.getCourse(), testRegistration2.getCourse());
    Assertions.assertEquals(registration3.getRoom(), testRegistration2.getRoom());
    Assertions.assertEquals(registration3.getStartTime(), testRegistration2.getStartTime());
    Assertions.assertEquals(registration3.getEndTime(), testRegistration2.getEndTime());
  }
}
