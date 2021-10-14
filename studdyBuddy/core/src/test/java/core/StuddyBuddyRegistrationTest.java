package core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class StuddyBuddyRegistrationTest {

	private StuddyBuddyRegistration registration;

	@BeforeEach
	public void setup() {
		registration = new StuddyBuddyRegistration();
	}

	@Test
	public void testTime() {
		registration.setStartTime("14:00");
		Assertions.assertEquals("14:00", registration.getStartTime());
		registration.setEndTime("16:00");
		Assertions.assertEquals("16:00", registration.getEndTime());

	}

	@Test
	public void testRoom() {
		registration.setRoom("304");
		Assertions.assertEquals("304", registration.getRoom());
		registration.setRoom("A-414");
		Assertions.assertEquals("A-414", registration.getRoom());
	}

	@Test
	public void setCourse() {
		registration.setCourse("Statistikk");
		Assertions.assertEquals("Statistikk", registration.getCourse());
	}

	// test the format of time
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

	// test the format of room
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

	// test the format of course
	@Test
	public void testCourseFormat() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			registration.setCourse("$tatistikk");
		});
	}
}