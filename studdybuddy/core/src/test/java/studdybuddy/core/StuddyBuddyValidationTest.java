package studdybuddy.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StuddyBuddyValidationTest {

    /**
     * Tests the checkPassword method
     * 
     * Right format is 8 or more characters, either letters or digits.
     */
    @Test
    public void testCheckPassword() {
        String validPassword = "validPassword1";
        String tooShortPassword = "short1";
        String passwordMissingNumbers = "passwordMissingNumbers";
        String passwordMissingLetters = "12345678";
        assertTrue(StuddyBuddyValidation.checkPassword(validPassword));
        assertFalse(StuddyBuddyValidation.checkPassword(tooShortPassword));
        assertFalse(StuddyBuddyValidation.checkPassword(passwordMissingNumbers));
        assertFalse(StuddyBuddyValidation.checkPassword(passwordMissingLetters));
    }

    /**
     * Tests that the checkName method
     * 
     * Name can only consist of letters and space
     */
    @Test
    public void testCheckName() {
        String validName = "valid name";
        String nameWithNumber = "name123";
        String nameWithUnderscore = "invalid_name";
        assertTrue(StuddyBuddyValidation.checkName(validName));
        assertFalse(StuddyBuddyValidation.checkName(nameWithNumber));
        assertFalse(StuddyBuddyValidation.checkName(nameWithUnderscore));
    }

    /**
     * Tests the checkTimeFormat method
     * 
     * Test that exceptions are thrown when the format is wrong
     */
    @Test
    public void testcheckTimeFormat() {
        String invalidFormatOne = "12 pm";
        String invalidFormatTwo = "12";
        String invalidFormatThree = "1200";
        String validFormat = "12:00";
        assertThrows(IllegalArgumentException.class, () -> StuddyBuddyValidation.checkTimeFormat(invalidFormatOne));
        assertThrows(IllegalArgumentException.class, () -> StuddyBuddyValidation.checkTimeFormat(invalidFormatTwo));
        assertThrows(IllegalArgumentException.class, () -> StuddyBuddyValidation.checkTimeFormat(invalidFormatThree));
        assertDoesNotThrow(() -> StuddyBuddyValidation.checkTimeFormat(validFormat));
    }

    /**
     * Tests the checkStartTimeBeforeEndTime method
     * 
     * Should return false if end time is before start time and true if it is after
     */
    @Test
    public void testCheckStartTimeBeforeEndTime() {
        String validStartTime = "10:00";
        String invalidStartTime = "14:00";
        String endTime = "12:00";
        assertTrue(StuddyBuddyValidation.checkStartTimeBeforeEndTime(validStartTime, endTime));
        assertFalse(StuddyBuddyValidation.checkStartTimeBeforeEndTime(invalidStartTime, endTime));
    }

    /**
     * Tests the checkRoom method
     * 
     * True if the room must consist of letters, numbers, "-" and/or spaces
     * 
     * False otherwise
     */
    @Test
    public void testCheckRoom() {
        String validInputWithDash = "Room-1";
        String validInputWithSpace = "Room 1";
        String invalidInput = "#";
        assertTrue(StuddyBuddyValidation.checkRoom(validInputWithDash));
        assertTrue(StuddyBuddyValidation.checkRoom(validInputWithSpace));
        assertFalse(StuddyBuddyValidation.checkRoom(invalidInput));
    }

    /**
     * Tests the checkCourse method
     * 
     * True if the course consist of letters, numbers, "-" or spaces
     * 
     * False otherwise
     */
    @Test
    public void checkCourse() {
        String validInput = "Course";
        String validInputWithNumbers = "C1901";
        String validInputDash = "C- and D-course";
        String invalidInput = "#";
        assertTrue(StuddyBuddyValidation.checkCourse(validInput));
        assertTrue(StuddyBuddyValidation.checkCourse(validInputWithNumbers));
        assertTrue(StuddyBuddyValidation.checkCourse(validInputDash));
        assertFalse(StuddyBuddyValidation.checkCourse(invalidInput));
    }
}
