package studdybuddy.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for registrations.
 * A registration has a StuddyBuddy,
 * date, start time, end time, room
 * and course.
 */
public class StuddyBuddyRegistration {

  private StuddyBuddy studdyBuddy;
  private String date;
  private String startTime;
  private String endTime;
  private String room;
  private String course;

  /**
   * Setter for studdyBuddy.
   *
   * @param studdyBuddy the new studdyBuddy object.
   */
  public void setStuddyBuddy(StuddyBuddy studdyBuddy) {
    this.studdyBuddy = studdyBuddy;
  }

  /**
   * Getter for studdyBuddy.
   *
   * @return this studdyBuddy.
   */
  public StuddyBuddy getStuddyBuddy() {
    return studdyBuddy;
  }

  /**
   * Getter for date.
   *
   * @return this date.
   */
  public String getDate() {
    return date;
  }

  /**
   * Getter for username.
   *
   * @return this username.
   */
  public String getUsername() {
    return studdyBuddy.getName();
  }

  /**
   * Setter for date.
   *
   * @param date the new date.
   */
  public void setDate(LocalDate date) {
    StuddyBuddyValidation.checkDateNotNull(date);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
    String formattedString = date.format(formatter);
    this.date = formattedString;
  }

  /**
   * Setter for startTime.
   *
   * @param startTime the new start time.
   * @throws IllegalArgumentException if the format is incorrect.
   */
  public void setStartTime(String startTime) {
    StuddyBuddyValidation.checkNotNull(startTime);
    StuddyBuddyValidation.checkTimeFormat(startTime);

    this.startTime = startTime;

  }

  /**
   * Getter for startTime.
   *
   * @return registration startTime.
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * Setter for endTime.
   *
   * @param endTime the new end time.
   * @throws IllegalArgumentException if the format is incorrect or if startTime is before endTime.
   */
  public void setEndTime(String endTime) {
    StuddyBuddyValidation.checkNotNull(endTime);
    StuddyBuddyValidation.checkTimeFormat(endTime);

    if (StuddyBuddyValidation.checkStartTimeBeforeEndTime(this.startTime, endTime) == true) {
      this.endTime = endTime;
    } else {
      throw new IllegalArgumentException("Endtime can not be before starttime.");
    }
  }

  /**
   * Getter for endTime.
   *
   * @return this registration endTime.
   */
  public String getEndTime() {
    return endTime;
  }

  /**
   * Setter for room.
   *
   * @param room the new room.
   * @throws IllegalArgumentException if the format is incorrect.
   */
  public void setRoom(String room) {
    StuddyBuddyValidation.checkNotNull(room);

    if (StuddyBuddyValidation.checkRoom(room) == false) {
      throw new IllegalArgumentException(
          "Can not use other characters than letters, digits, '-' and ' '. You wrote: " + room);
    }
    this.room = room;
  }

  /**
   * Getter for room.
   *
   * @return this room.
   */
  public String getRoom() {
    return room;
  }

  /**
   * Setter for course.
   *
   * @param course the new course.
   * @throws IllegalArgumentException if the format of the course param is wrong.
   */
  public void setCourse(String course) {
    StuddyBuddyValidation.checkNotNull(course);

    if (StuddyBuddyValidation.checkCourse(course) == false) {
      throw new IllegalArgumentException(
          "Can not use other characters than letters, digits, '-' and ' '. You wrote: " + course);
    }
    this.course = course;
  }

  /**
   * Getter for course.
   *
   * @return this course.
   */
  public String getCourse() {
    return course;
  }

  /**
   * ToString for StuddyBuddyRegistration objects.
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
