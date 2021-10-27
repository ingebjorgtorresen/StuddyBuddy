package core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StuddyBuddiesTest {

    private StuddyBuddies buddies;

    @BeforeEach
    public void setup() {
        buddies = new StuddyBuddies();
    }

    @Test
    public void testGetStuddyBuddy() {
        assertNull(buddies.getStuddyBuddy("Tuva"));
        StuddyBuddy buddy = new StuddyBuddy();
        buddy.setName("Petra");
        buddies.addStuddyBuddy(buddy);
        assertEquals(buddy, buddies.getStuddyBuddy("Petra"));
    }

    /**
     * This method tests checkIfStuddyBuddyExists indirectly because it is used in addStuddyBuddy.
     */
    @Test
    public void testAddStuddyBuddy() {
        StuddyBuddy buddy = new StuddyBuddy();
        buddy.setName("Tuva");
        buddies.addStuddyBuddy(buddy);
        assertTrue(buddies.getStuddyBuddies().contains(buddy));
        assertThrows(IllegalArgumentException.class, () -> buddies.addStuddyBuddy(buddy));
    }

    @Test
    public void testAddStuddyBuddies() {
        
    }
}