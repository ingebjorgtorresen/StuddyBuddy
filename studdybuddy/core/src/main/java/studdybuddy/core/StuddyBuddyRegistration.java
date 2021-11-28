package studdybuddy.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for registrations. A registration has
 * date, start time, end time, room and course.
 */
public class StuddyBuddyRegistration {

  private String date;
  private String startTime;
  private String endTime;
  private String room;
  private String course;

  /**
   * Method for getting date.
   *
   * @return this date
   */
  public String getDate() {
    return date;
  }

  /**
   * Method for setting date.
   *
   * @param date the new date
   */
  public void setDate(LocalDate date) {
    if (StuddyBuddyValidation.checkDatNotNull(date)) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
      String formattedString = date.format(formatter);
      this.date = formattedString;
    } else {
      throw new IllegalArgumentException("Ivalid date.");
    }
  }

  /**
   * Method for setting startTime.
   *
   * @param startTime the new start time.
   * @throws IllegalArgumentException if the format is incorrect.
   */
  public void setStartTime(String startTime) {
    if (StuddyBuddyValidation.checkNotNullorEmpty(startTime) 
        && StuddyBuddyValidation.checkTimeFormat(startTime)) {
      this.startTime = startTime;
    } else {
      throw new IllegalArgumentException("Invalid start time.");
    }
  }

  /**
   * Mehtod for setting startTime.
   *
   * @return registration startTime.
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * Method for setting endTime.
   *
   * @param endTime the new end time.
   * @throws IllegalArgumentException if the format is incorrect or if startTime is before endTime.
   */
  public void setEndTime(String endTime) {
    if (StuddyBuddyValidation.checkStartTimeBeforeEndTime(this.startTime, endTime) 
        && StuddyBuddyValidation.checkNotNullorEmpty(endTime) 
        && StuddyBuddyValidation.checkTimeFormat(endTime)) {
      this.endTime = endTime;
    } else {
      throw new IllegalArgumentException("Invalid end time.");
    }
  }

  /**
   * Method for getting endTime.
   *
   * @return this registration endTime.
   */
  public String getEndTime() {
    return endTime;
  }

  /**
   * Method for setting room.
   *
   * @param room the new room.
   * @throws IllegalArgumentException if the format is incorrect.
   */
  public void setRoom(String room) {
    if (StuddyBuddyValidation.checkRoom(room) && StuddyBuddyValidation.checkNotNullorEmpty(room)) {
      this.room = room;
    } else {
      throw new IllegalArgumentException(
          "Can not use other characters than letters, digits, '-' and ' '. You wrote: " + room);
    }
  }

  /**
   * Method for setting room.
   *
   * @return this room.
   */
  public String getRoom() {
    return room;
  }

  /**
   * Method for setting course.
   *
   * @param course the new course.
   * @throws IllegalArgumentException if the format of the course param is wrong.
   */
  public void setCourse(String course) {
    if (StuddyBuddyValidation.checkCourse(course) 
        && (StuddyBuddyValidation.checkNotNullorEmpty(course))) {
      this.course = course;
    } else {
      throw new IllegalArgumentException(
        "Can not use other characters than letters, digits, '-' and ' '. You wrote: " + course);
    }
  }

  /**
   * Method for getting course.
   *
   * @return this course.
   */
  public String getCourse() {
    return course;
  }

  /**
   * Method for making toString for StuddyBuddyRegistration objects.
   *
   * @return Registration object as String.
   */
  @Override
  public String toString() {
    return "Date: " + getDate()
        + "\n" + "Room: " + getRoom()
        + "\n" + "Course: " + getCourse()
        + "\n" + "Start time: " + getStartTime()
        + "\n" + "End time: " + getEndTime() + "\n ";
  }
}
