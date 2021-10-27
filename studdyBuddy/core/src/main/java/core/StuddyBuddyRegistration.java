package core;

public class StuddyBuddyRegistration {
    
    private StuddyBuddy studdyBuddy;
	private String startTime;
	private String endTime;
	private String room;
	private String course;

	public void setStuddyBuddy(StuddyBuddy studdyBuddy) {
		this.studdyBuddy = studdyBuddy;
	}

	public StuddyBuddy getStuddyBuddy() {
		return studdyBuddy;
	}

	public String getUsername() {
		return studdyBuddy.getName();
	}
	
	/**
	 * sets the start time.
	 * @param startTime
	 * @throws IllegalArgumentException if the format is incorrect
	 */
	public void setStartTime(String startTime) {
		StuddyBuddyValidation.checkNotNull(startTime);
		StuddyBuddyValidation.checkTimeFormat(startTime);
		
		this.startTime = startTime;
		
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	/**
	 * sets the end time.
	 * @param endTime
	 * @throws IllegalArgumentException if the format is incorrect or if startTime is before endTime
	 */
	public void setEndTime(String endTime) {
		StuddyBuddyValidation.checkNotNull(endTime);
		StuddyBuddyValidation.checkTimeFormat(endTime);
		
		if(StuddyBuddyValidation.checkStartTimeBeforeEndTime(this.startTime, endTime) == true) {
			this.endTime = endTime;
		}
		
		else {
			throw new IllegalArgumentException("Endtime can not be before starttime.");
		}
	}
	
	public String getEndTime() {
		return endTime;
	}

	
	/**
	 * @param room
	 * @throws IllegalArgumentException if the format is incorrect.
	 */
	public void setRoom(String room) {
		StuddyBuddyValidation.checkNotNull(room);
		
		if(StuddyBuddyValidation.checkRoom(room) == false) {
			throw new IllegalArgumentException("Can not use other characters than letters, digits, '-' and ' '. You wrote: " + room);
		}
		this.room = room;
	}
	
	public String getRoom() {
		return room;
	}
	
	
	/**
	 * sets course
	 * @param course
	 * @throws IllegalArgumentException if the format of the course param is wrong.
	 */
	public void setCourse(String course) {
		StuddyBuddyValidation.checkNotNull(course);
		
		if(StuddyBuddyValidation.checkCourse(course) == false) {
			throw new IllegalArgumentException("Can not use other characters than letters, digits, '-' and ' '. You wrote: " + course);
		}
		this.course = course;
	}
	
	public String getCourse() {
		return course;
	}
	

	@Override
	public String toString() {
		return "Room: " + getRoom() + "\n" +
				"Course: " + getCourse() + "\n" +
				"Start time: " + getStartTime() + "\n" +
				"End time: " + getEndTime();
	}
}