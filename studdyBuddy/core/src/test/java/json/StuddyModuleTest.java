package json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import core.*;

public class StuddyModuleTest {
    
    private static StuddyBuddyPersistence persistence;
    private static String testFileName = "testPersistence.json";
    private static StuddyBuddy buddy;
    private static StuddyBuddyRegistration registration;
    private static Writer writer;
    private static Reader reader;
    
    @BeforeAll
    public static void setup() {
        persistence = new StuddyBuddyPersistence();
        buddy = new StuddyBuddy();
        buddy.setName("Test");
        registration = new StuddyBuddyRegistration();
        registration.setRoom("A3");
        registration.setCourse("itp");
        registration.setStartTime("08:00");
        registration.setEndTime("12:00");
        buddy.addRegistration(registration);
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
        StuddyBuddy testBuddy = null;
        try {
            persistence.writeStuddyBuddy(buddy, writer);
            testBuddy = persistence.readStuddyBuddy(reader);
            Assertions.assertEquals(buddy.getName(), testBuddy.getName());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Couldn't write/read to/from test file.");
        }

        StuddyBuddyRegistration testRegistration = testBuddy.getRegistrations().get(0);
        Assertions.assertEquals(registration.getCourse(), testRegistration.getCourse());
        Assertions.assertEquals(registration.getRoom(), testRegistration.getRoom());
        Assertions.assertEquals(registration.getStartTime(), testRegistration.getStartTime());
        Assertions.assertEquals(registration.getEndTime(), testRegistration.getEndTime()); 
    }

    
}
