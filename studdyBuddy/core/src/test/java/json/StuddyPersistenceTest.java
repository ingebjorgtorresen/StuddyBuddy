package json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core.*;

public class StuddyPersistenceTest {
    
    private static StuddyBuddyPersistence persistence;
    private static String testFileName = "testPersistence.json";
    private static StuddyBuddies buddies;
    private static StuddyBuddy buddy;
    private static StuddyBuddyRegistration registration;
    private static Writer writer;
    private static Reader reader;
    
    @BeforeEach
    public void setup() {
        buddies = new StuddyBuddies();
        persistence = new StuddyBuddyPersistence();
        buddy = new StuddyBuddy();
        buddy.setName("Test");
        registration = new StuddyBuddyRegistration();
        registration.setRoom("A3");
        registration.setCourse("itp");
        registration.setStartTime("08:00");
        registration.setEndTime("12:00");
        buddy.addRegistration(registration);
        buddies.addStuddyBuddy(buddy);
        buddy.setStuddyBuddies(buddies);
        try {
            writer = new FileWriter(testFileName, StandardCharsets.UTF_8);
            reader = new FileReader(testFileName, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Writes an object to a test file, then checks that the object read from the file is equal to the one that was written
    @Test
    public void testPersistence() {
        StuddyBuddies testBuddy = null;
        try {
            persistence.writeStuddyBuddies(buddies, writer);
            testBuddy = persistence.readStuddyBuddies(reader);
            Assertions.assertEquals(buddy, testBuddy.getStuddyBuddy(buddy.getName()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Couldn't write/read to/from test file.");
        }

        StuddyBuddyRegistration testRegistration = testBuddy.getStuddyBuddy(buddy.getName()).getRegistrations().get(0);
        Assertions.assertEquals(registration.getCourse(), testRegistration.getCourse());
        Assertions.assertEquals(registration.getRoom(), testRegistration.getRoom());
        Assertions.assertEquals(registration.getStartTime(), testRegistration.getStartTime());
        Assertions.assertEquals(registration.getEndTime(), testRegistration.getEndTime()); 
    }
}
