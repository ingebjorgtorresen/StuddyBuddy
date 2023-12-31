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
 * Class for testing StuddyBuddiesPersistence. 
 * This tests all serializers, deserializers and StuddyModule indirectly,
 * because StuddyBuddiesPersistence uses all those classes.
 */
public class StuddyBuddiesPersistenceTest {

  private static StuddyBuddiesPersistence persistence;
  private static String testFileName = "src/test/resources/studdybuddy/core/testPersistence.json";
  private static StuddyBuddies buddies;
  private static StuddyBuddy buddy1;
  private static StuddyBuddy buddy2;
  private static StuddyBuddyRegistration registration1;
  private static StuddyBuddyRegistration registration2;
  private static Writer writer;
  private static Reader reader;

  /**
   * Set up for each test
   */
  @BeforeEach
  public void setup() {
    buddies = new StuddyBuddies();
    persistence = new StuddyBuddiesPersistence();

    buddy1 = new StuddyBuddy();
    buddy1.setName("FirstBuddy");
    buddy1.setPassword("eple1234");
    registration1 = new StuddyBuddyRegistration();
    LocalDate date1;
    date1 = LocalDate.of(2022, 10, 10);
    registration1.setDate(date1);
    registration1.setRoom("TestRoom1");
    registration1.setCourse("TestCourse1");
    registration1.setStartTime("08:00");
    registration1.setEndTime("12:00");

    buddy2 = new StuddyBuddy();
    buddy2.setName("SecondBuddy");
    buddy2.setPassword("12345678");
    registration2 = new StuddyBuddyRegistration();
    LocalDate date2;
    date2 = LocalDate.of(2022, 12, 12);
    registration2.setDate(date2);
    registration2.setRoom("TestRoom2");
    registration2.setCourse("TestCourse2");
    registration2.setStartTime("10:00");
    registration2.setEndTime("14:00");

    buddy1.addRegistration(registration1);
    buddy2.addRegistration(registration2);
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
   * Method that writes a StuddyBuddies object to a test file, 
   * then tests if the object read from the file is equal to
   * the one that was written.
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
    StuddyBuddyRegistration testRegistration = testBuddies.getStuddyBuddy(buddy2.getName()).getRegistrations().get(0);
    Assertions.assertEquals(registration2.getDate(), testRegistration.getDate());
    Assertions.assertEquals(registration2.getCourse(), testRegistration.getCourse());
    Assertions.assertEquals(registration2.getRoom(), testRegistration.getRoom());
    Assertions.assertEquals(registration2.getStartTime(), testRegistration.getStartTime());
    Assertions.assertEquals(registration2.getEndTime(), testRegistration.getEndTime());
  }
}
