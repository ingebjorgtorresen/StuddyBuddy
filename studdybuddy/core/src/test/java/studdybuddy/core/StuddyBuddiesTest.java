package studdybuddy.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing StuddyBuddies class.
 */
public class StuddyBuddiesTest {

  private StuddyBuddies buddies;

  /**
   * Setup for each test.
   */
  @BeforeEach
  public void setup() {
    buddies = new StuddyBuddies();
  }

  /**
   * Method for testing getStuddyBuddy method.
   */
  @Test
  public void testGetStuddyBuddy() {
    assertNull(buddies.getStuddyBuddy("Tuva"));
    StuddyBuddy buddy = new StuddyBuddy();
    buddy.setName("Petra");
    buddies.addStuddyBuddy(buddy);
    assertEquals(buddy, buddies.getStuddyBuddy("Petra"));
  }

  /**
   * Method for testing addStuddyBuddy method. It indirectly tests if checkIfStuddyBuddyExists works,
   * because it is used in addStuddyBuddy.
   */
  @Test
  public void testAddStuddyBuddy() {
    StuddyBuddy buddy = new StuddyBuddy();
    buddy.setName("Tuva");
    buddies.addStuddyBuddy(buddy);
    assertTrue(buddies.getStuddyBuddies().contains(buddy));
    assertThrows(IllegalArgumentException.class, () -> buddies.addStuddyBuddy(buddy));
  }

  /**
   * Method for testing addStuddyBuddies method.
   */
  @Test
  public void testAddStuddyBuddies() {
    StuddyBuddy buddy = new StuddyBuddy();
    buddy.setName("Tuva");
    buddies.addStuddyBuddy(buddy);
    StuddyBuddy buddy2 = new StuddyBuddy();
    buddy2.setName("Selma");
    buddies.addStuddyBuddies(buddy2);
    assertTrue(buddies.getStuddyBuddies().contains(buddy));
    assertTrue(buddies.getStuddyBuddies().contains(buddy2));
    assertThrows(IllegalArgumentException.class, () -> buddies.addStuddyBuddies(buddy, buddy2));
  }

  /**
   * Method for testing removeStuddyBuddy method.
   */
  @Test
  public void testRemoveStuddyBuddy() {
    StuddyBuddy buddy = new StuddyBuddy();
    buddies.addStuddyBuddy(buddy);
    buddies.removeStuddyBuddy(buddy);
    assertTrue(!buddies.getStuddyBuddies().contains(buddy));
  }
}
