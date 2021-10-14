package core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

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
	
	@Test
	public void testTime() {
		studdyBuddy.setStartTime("14:00");
		Assertions.assertEquals("14:00", studdyBuddy.getStartTime());
		studdyBuddy.setEndTime("16:00");
		Assertions.assertEquals("16:00", studdyBuddy.getEndTime());

	}
	
	@Test
	public void testRoom() {
		studdyBuddy.setRoom("304");
		Assertions.assertEquals("304", studdyBuddy.getRoom() );
		studdyBuddy.setRoom("A-414");
		Assertions.assertEquals("A-414", studdyBuddy.getRoom());
	}
	
	@Test
	public void setCourse() {
		studdyBuddy.setCourse("Statistikk");
		Assertions.assertEquals("Statistikk", studdyBuddy.getCourse());
	}
	
	//test the format of name
	@Test
	public void testNameFormat() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setName("@nette");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setName(null);
		});
	}
	
	//test the format of time
	@Test
	public void testTimeFormat() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setEndTime("8:00");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setEndTime("25:00");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setEndTime("08.00");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setEndTime("0800");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setEndTime("halv 8");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setEndTime(null);
		});	
	}
	
	//test the format of room
	@Test
	public void testRoomFormat() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setRoom("4,5");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setRoom("#303");
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setRoom(null);
		});
		
	}
	
	//test the format of course
	@Test
	public void testCourseFormat() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			studdyBuddy.setCourse("$tatistikk");
		});
		{
	}
}
}