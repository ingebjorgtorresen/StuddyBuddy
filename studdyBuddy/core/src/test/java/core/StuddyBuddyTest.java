package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StuddyBuddyTest {

    private StuddyBuddy studdyBuddy;

    @BeforeEach
    public void setup() {
        studdyBuddy = new StuddyBuddy();
    }

    @Test
    public void testName() {
        studdyBuddy.setName("Turi");
        Assertions.assertEquals("Turi", studdyBuddy.getName());
    }

    // test the format of name
    @Test
    public void testNameFormat() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            studdyBuddy.setName("@nette");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            studdyBuddy.setName(null);
        });
    }

}