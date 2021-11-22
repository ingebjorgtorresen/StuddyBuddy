package core;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

/**
 * Validation class for all classes in package core.
 */
public abstract class StuddyBuddyValidation {

	/**
	 * Method for checking if password is on right format. Right format is 8 characters or more that is
	 * either letters or digits.
	 * 
	 * @param password password to check
	 * @return true if format is correct, else return false
	 */
	public static boolean checkPassword(String password) {
		if (Pattern.matches("\\w{8,}", password)) {
			return true;
		}
		return false;
	}

	/**
	 * checks that the name has the correct format. The name can only consist of letters and space.
	 * 
	 * @param name the name to check
	 * @return true if the formate is correct and false if it is incorrect
	 */
	public static boolean checkName(String name) {
		char[] chars = name.toCharArray();

		for (char c : chars) {
			if (!(Character.isLetter(c) || (c == ' '))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * checks that the argument is not null
	 * 
	 * @param string string to check
	 */

	public static void checkNotNull(String string) {
		if (string == null) {
			throw new IllegalArgumentException("Input can not be null/nothing.");
		}
	}

	/**
	 * checks that the LocalDate-argument is not null
	 * 
	 * @param date to check
	 */
	public static void checkDateNotNull(LocalDate date) {
		if (date == null) {
			throw new IllegalArgumentException("Input can not be null/nothing.");
		}
	}

	/**
	 * check the format of the time parameter the time must be in format("HH:mm") and not null
	 * 
	 * @param time
	 * @throws IllegalArgumentException if the format is incorrect
	 */
	public static void checkTimeFormat(String time) {
		checkNotNull(time);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);
		try {
			LocalTime.parse(time, formatter).toString();
		} catch (DateTimeException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Wrong format, or could not parse.");
		}
	}

	/**
	 * @param startTime
	 * @param endTime
	 * @return false if end time is before start time and true if it is after
	 */
	public static boolean checkStartTimeBeforeEndTime(String startTime, String endTime) {
		if ((startTime == null) || (endTime == null)) {
			throw new IllegalArgumentException(
					"Time can not be null/nothing. Starttime was " + startTime + " endtime was: " + endTime);
		}

		LocalTime start = LocalTime.parse(startTime);
		LocalTime end = LocalTime.parse(endTime);

		if ((start.isAfter(end)) || (start == end)) {
			return false;
		}

		return true;
	}

	/**
	 * checks if the room is in the correct format the room must consist of letters, numbers, "-" or
	 * spaces
	 * 
	 * @return true if the format is coorect and false if it is incorrect
	 */
	public static boolean checkRoom(String room) {
		char[] chars = room.toCharArray();

		for (char c : chars) {
			if (!((Character.isLetter(c)) || (Character.isDigit(c)) || (c == '-') || (c == ' '))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * checks if the course is in the correct format the course must consist of letters, numbers, "-" or
	 * spaces
	 * 
	 * @return true if the format is coorect and false if it is incorrect
	 */
	public static boolean checkCourse(String course) {
		char[] chars = course.toCharArray();

		for (char c : chars) {
			if (!((Character.isLetter(c)) || (Character.isDigit(c)) || (c == '-') || (c == ' '))) {
				return false;
			}
		}

		return true;
	}

}
