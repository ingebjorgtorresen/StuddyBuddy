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
    
    private static StuddyBuddiesPersistence persistence;
    private static String testFileName = "testPersistence.json";
    private static StuddyBuddies buddies;
    private static StuddyBuddy buddy;
    private static StuddyBuddyRegistration registration;
    private static Writer writer;
    private static Reader reader;
    
    @BeforeEach
    public void setup() {
        buddies = new StuddyBuddies();
        persistence = new StuddyBuddiesPersistence();

        buddy = new StuddyBuddy();
        buddy.setName("Name");

        registration = new StuddyBuddyRegistration();
        registration.setRoom("Room");
        registration.setCourse("Course");
        registration.setStartTime("08:00");
        registration.setEndTime("12:00");

        buddy.addRegistration(registration);
        buddies.addStuddyBuddy(buddy);
        
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
        StuddyBuddies testBuddies = null;
        try {
            persistence.writeStuddyBuddies(buddies, writer);
            testBuddies = persistence.readStuddyBuddies(reader);
            System.out.println(testBuddies);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Couldn't write/read to/from test file.");
        }

        //Assertions.assertEquals(buddy, testBuddies.getStuddyBuddy(buddy.getName()));
        StuddyBuddyRegistration testRegistration = testBuddies.getStuddyBuddy(buddy.getName()).getRegistrations().get(0);
        Assertions.assertEquals(registration.getCourse(), testRegistration.getCourse());
        Assertions.assertEquals(registration.getRoom(), testRegistration.getRoom());
        Assertions.assertEquals(registration.getStartTime(), testRegistration.getStartTime());
        Assertions.assertEquals(registration.getEndTime(), testRegistration.getEndTime());
    }
}
