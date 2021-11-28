package studdybuddy.core;

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
   * Method for checking if password is on right format.
   * Right format is 8 characters or more that is
   * either letters or digits. The letters follows the 
   * english alphabet so characters like æ,ø,å will 
   * not be accepted. 
   *
   * @param password password to check
   * @return true if format is correct, else return false
   */
  public static boolean checkPassword(String password) {
    return Pattern.matches("\\w{8,}", password);
  }

  /**
   * Method for checking if the name has the correct format.
   * The name can only consist of letters and space.
   *
   * @param name the name to check
   * @return true if the formate is correct and false if it is incorrect
   */
  public static boolean checkName(String name) {
    if (!checkNotNullorEmpty(name)) {
      return false;
    }
    char[] chars = name.toCharArray();
    for (char c : chars) {
      if (!(Character.isLetter(c) || (c == ' '))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Method for checking if the argument is not null.
   *
   * @param string string to check.
   */

  public static boolean checkNotNullorEmpty(String string) {
    return (string != null && !string.equals(""));
  }

  /**
   * Method for checking that date is not null. 
   *
   * @param date to check
   * @return true if date is not null, else false
   */
  public static boolean checkDatNotNull(LocalDate date) {
    return date != null;
  }

  /**
   * Method that checks if StuddyBuddy exists by name.
   *
   * @param name name of the user
   * @return true if user exists, else null
   */
  public static boolean buddyExists(StuddyBuddies buddies, String name) {
    return buddies.getStuddyBuddy(name) != null;
  }

  /**
   * Method for checking if the input time is on correct format.
   * The time must be on format("HH:mm") and not null.
   *
   * @param time to check
   * @throws IllegalArgumentException if the format is incorrect
   */
  public static boolean checkTimeFormat(String time) {
    if (!checkNotNullorEmpty(time)) {
      return false;
    }
    DateTimeFormatter formatter
        = DateTimeFormatter.ofPattern("HH:mm")
        .withResolverStyle(ResolverStyle.STRICT);
    try {
      LocalTime.parse(time, formatter).toString();
    } catch (DateTimeException e) {
      return false;
    }
    return true;
  }

  /**
   * Method for checking if startTime is before endTime.
   *
   * @param startTime registration start time
   * @param endTime registration end time
   * @return false if end time is before start time and true if it is after
   */
  public static boolean checkStartTimeBeforeEndTime(String startTime, String endTime) {
    if (checkNotNullorEmpty(startTime) && checkNotNullorEmpty(endTime)) {
      LocalTime start = LocalTime.parse(startTime);
      LocalTime end = LocalTime.parse(endTime);
      if ((end.isAfter(start)) && (start != end)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Method for checking if the room is on the correct format
   * The room must consist of letters, numbers, "-" or
   * spaces.
   *
   * @return true if the format is coorect and false if it is incorrect
   */
  public static boolean checkRoom(String room) {
    if (!checkNotNullorEmpty(room)) {
      return false;
    }
    char[] chars = room.toCharArray();
    for (char c : chars) {
      if (!((Character.isLetter(c)) || (Character.isDigit(c)) || (c == '-') || (c == ' '))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Method for checking if the course is on the correct format.
   * The course must consist of letters, numbers, "-" or
   * spaces.
   *
   * @return true if the format is correct and false if it is incorrect.
   */
  public static boolean checkCourse(String course) {
    if (!checkNotNullorEmpty(course)) {
      return false;
    }
    char[] chars = course.toCharArray();
    for (char c : chars) {
      if (!((Character.isLetter(c)) || (Character.isDigit(c)) || (c == '-') || (c == ' '))) {
        return false;
      }
    }
    return true;
  }
}
