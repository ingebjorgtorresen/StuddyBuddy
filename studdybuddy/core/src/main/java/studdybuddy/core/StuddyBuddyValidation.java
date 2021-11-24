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
   * either letters or digits.
   *
   * @param password password to check
   * @return true if format is correct, else return false
   */
  public static boolean checkPassword(String password) {
    return Pattern.matches("\\w{8,}", password);
  }

  /**
   * checks that the name has the correct format. The name can only consist of letters and space.
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
   * Checks that the argument is not null.
   *
   * @param string string to check.
   */

  public static boolean checkNotNullorEmpty(String string) {
    return (string != null && !string.equals(""));
  }

  public static boolean checkDatNotNull(LocalDate date) {
    return date != null;
  }

  /**
   * Check the format of the time parameter
   * The time must be in format("HH:mm") and not null.
   *
   * @param time to check.
   * @throws IllegalArgumentException if the format is incorrect.
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
   * Checks that startTime is before endTime.
   *
   * @param startTime registration start time.
   * @param endTime registration end time.
   * @return false if end time is before start time and true if it is after.
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
   * Checks if the room is in the correct format
   * the room must consist of letters, numbers, "-" or
   * spaces.
   *
   * @return true if the format is coorect and false if it is incorrect.
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
   * Checks if the course is in the correct format
   * the course must consist of letters, numbers, "-" or
   * spaces.
   *
   * @return true if the format is coorect and false if it is incorrect.
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
