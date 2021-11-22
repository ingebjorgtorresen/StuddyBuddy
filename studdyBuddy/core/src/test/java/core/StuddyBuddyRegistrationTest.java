package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Class for testing StuddyBuddyRegistration.
 */
public class StuddyBuddyRegistrationTest {

	private StuddyBuddyRegistration registration;

	/**
	 * Setup for each test.
	 */
	@BeforeEach
	public void setup() {
		registration = new StuddyBuddyRegistration();
	}

	/**
	 * Method for testing setter for date.
	 */
	@Test
	public void testDate() {
		LocalDate date;
		date = LocalDate.of(2022, 12, 12);
		registration.setDate(date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedString = date.format(formatter);
		Assertions.assertEquals(formattedString, registration.getDate());
	}

	/**
	 * Method for testing setter for starttime and endtime.
	 */
	@Test
	public void testTime() {
		registration.setStartTime("14:00");
		Assertions.assertEquals("14:00", registration.getStartTime());
		registration.setEndTime("16:00");
		Assertions.assertEquals("16:00", registration.getEndTime());

	}

	/**
	 * Method for testing setter for room.
	 */
	@Test
	public void testRoom() {
		registration.setRoom("304");
		Assertions.assertEquals("304", registration.getRoom());
		registration.setRoom("A-414");
		Assertions.assertEquals("A-414", registration.getRoom());
	}

	/**
	 * Method for testing setter for course.
	 */
	@Test
	public void setCourse() {
		registration.setCourse("Statistikk");
		Assertions.assertEquals("Statistikk", registration.getCourse());
	}

	/**
	 * Method for testing format of time.
	 */
	@Test
	public void testTimeFormat() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setEndTime("8:00");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setEndTime("25:00");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setEndTime("08.00");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setEndTime("0800");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setEndTime("halv 8");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setEndTime(null);
		});
	}

	/**
	 * Method for testing room format.
	 */
	@Test
	public void testRoomFormat() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setRoom("4,5");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setRoom("#303");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setRoom(null);
		});

	}

	/**
	 * Method for testing course format.
	 */
	@Test
	public void testCourseFormat() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setCourse("$tatistikk");
		});
	}

	@Test
	public void testDateFormat() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setDate(null);
		});
	}
}
