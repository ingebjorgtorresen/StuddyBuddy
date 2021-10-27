package core;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public abstract class StuddyBuddyValidation {

    /**
	 * checks that the name has the correct format. The name can only consist of
	 * letters and space.
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
	 * @param string string to check
	 */
	
	public static void checkNotNull(String string) {
		if(string == null) {
			throw new IllegalArgumentException("Input can not be null/nothing.");
		}
	}

    /**
	 * check the format of the time parameter
	 * the time must be in format("HH:mm") and not null
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
			throw new IllegalArgumentException("Feil format, eller kunne ikke parse.");
		}	
	}

    /**
	 * @param startTime
	 * @param endTime
	 * @return false if end time is before start time and true if it is after
	 */
	public static boolean checkStartTimeBeforeEndTime(String startTime, String endTime) {
		if((startTime == null) || (endTime== null)) {
			throw new IllegalArgumentException("Time can not be null/nothing. Starttime was " + startTime + " endtime was: " + endTime);
		}
		
		LocalTime start = LocalTime.parse(startTime);
		LocalTime end = LocalTime.parse(endTime);
		
		if((start.isAfter(end)) || (start == end) ) {
			return false;
		}

		return true;
	}

    /**
	 * checks if the room is in the correct format
	 * the room must consist of letters, numbers, "-" or spaces
	 * @return true if the format is coorect and false if it is incorrect
	 */
	public static boolean checkRoom(String room) {
        char[] chars = room.toCharArray();
            
            for(char c: chars) {
                if(!((Character.isLetter(c)) || (Character.isDigit(c)) || (c == '-') || (c == ' '))) {
                    return false;
                }
            }
            
            return true;
    }

    /**
	 * checks if the course is in the correct format
	 * the course must consist of letters, numbers, "-" or spaces
	 * @return true if the format is coorect and false if it is incorrect
	 */
	public static boolean checkCourse(String course) {
		char[] chars = course.toCharArray();
		
		for(char c: chars) {
			if(!((Character.isLetter(c)) || (Character.isDigit(c)) || (c == '-') || (c == ' '))) {
				return false;
			}
		}
		
		return true;
	}



}