package core;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class StuddyBuddy {
    
    private String name;
	private String startTime;
	private String endTime;
	private String room;
	private String course;
	
	public void setName(String name) {
		checkNotNull(name);
		
		if(!checkName(name)) {
			throw new IllegalArgumentException("Name can not include any characthers but letters and ' ', you wrote: " + name);
		}
		this.name = name;
	}
	
	private boolean checkName(String name) {
		char[] chars = name.toCharArray();
		
		for(char c: chars) {
			if(!(Character.isLetter(c) || (c == ' '))) {
				return false;
			}
		}
		
		return true;
	}
	
	private void checkNotNull(String string) {
		if(string == null) {
			throw new IllegalArgumentException("Input can not be null/nothing.");
		}
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setStartTime(String startTime) {
		checkNotNull(startTime);
		checkTimeFormat(startTime);
		
		this.startTime = startTime;
		
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setEndTime(String endTime) {
		checkNotNull(endTime);
		checkTimeFormat(endTime);
		
		if(checkStartTimeBeforeEndTime(this.startTime, endTime) == true) {
			this.endTime = endTime;
		}
		
		else {
			throw new IllegalArgumentException("Endtime can not be before starttime.");
		}
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	private void checkTimeFormat(String time) {	
		checkNotNull(time);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);
		try {
			LocalTime.parse(time, formatter).toString();
		} catch (DateTimeException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Feil format, eller kunne ikke parse.");
		}
		
	}
	
	private boolean checkStartTimeBeforeEndTime(String startTime, String endTime) {
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
	
	public void setRoom(String room) {
		checkNotNull(room);
		
		if(checkRoom(room) == false) {
			throw new IllegalArgumentException("Can not use other characters than letters, digits, '-' and ' '. You wrote: " + room);
		}
		this.room = room;
	}
	
	public String getRoom() {
		return room;
	}
	
	private boolean checkRoom(String room) {
	char[] chars = room.toCharArray();
		
		for(char c: chars) {
			if(!((Character.isLetter(c)) || (Character.isDigit(c)) || (c == '-') || (c == ' '))) {
				return false;
			}
		}
		
		return true;
	}
	
	public void setCourse(String course) {
		checkNotNull(course);
		
		if(checkCourse(course) == false) {
			throw new IllegalArgumentException("Can not use other characters than letters, digits, '-' and ' '. You wrote: " + course);
		}
		this.course = course;
	}
	
	public String getCourse() {
		return course;
	}
	
	private boolean checkCourse(String course) {
		char[] chars = course.toCharArray();
		
		for(char c: chars) {
			if(!((Character.isLetter(c)) || (Character.isDigit(c)) || (c == '-') || (c == ' '))) {
				return false;
			}
		}
		
		return true;
	}
}