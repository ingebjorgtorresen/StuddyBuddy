package studdybuddy.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing StuddyBuddy class.
 */
public class StuddyBuddyTest {

  private StuddyBuddy studdyBuddy;

  /**
   * Setup for each test.
   */
  @BeforeEach
  public void setup() {
    studdyBuddy = new StuddyBuddy();
  }

  /**
   * Method for testing name format.
   */
  @Test
  public void testNameFormat() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      studdyBuddy.setName("@nette");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      studdyBuddy.setName(null);
    });
  }

  /**
   * Method for testing password format.
   */
  @Test
  public void testPassswordFormat() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      studdyBuddy.setName("PASS1");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      studdyBuddy.setName(null);
    });
  }

}
