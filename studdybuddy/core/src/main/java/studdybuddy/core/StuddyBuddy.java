package studdybuddy.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for StuddyBuddy, which is the user class. 
 * Each user has a name and a password, and a list over all registrations. 
 */

public class StuddyBuddy {

  private StuddyBuddies buddies;
  private String name;
  private String password;

  private List<StuddyBuddyRegistration> registrations = new ArrayList<>();

  /**
   * Method for setting the StuddyBuddies object.
   *
   * @param buddies to set
   */
  public void setStuddyBuddies(StuddyBuddies buddies) {
    this.buddies = buddies;
  }

  /**
   * Method for getting StuddyBuddies from field buddies.
   *
   * @return buddies to get
   */
  public StuddyBuddies getStuddyBuddies() {
    return this.buddies;
  }

  /**
   * Method for setting name of the user if it is a valid name.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    if (StuddyBuddyValidation.checkName(name) && StuddyBuddyValidation.checkNotNullorEmpty(name)) {
      this.name = name;
    } else {
      throw new IllegalArgumentException(
          "Name can not include any characthers but letters and ' ', you wrote: " + name);
    }
  }

  /**
   * Method for getting the name of the user.
   *
   * @return name of the user
   */
  public String getName() {
    return name;
  }

  /**
   * Method for setting password if it is valid. 
   * A valid password has at least 8 characters, and
   * consists of only digits and letters. Also throws exeption if password is invalid.
   *
   * @param password the password the user wishes to set.
   */
  public void setPassword(String password) {
    if (!StuddyBuddyValidation.checkPassword(password) 
        || !StuddyBuddyValidation.checkNotNullorEmpty(password)) {
      throw new IllegalArgumentException("Please write a valid password.");
    }
    this.password = password;
  }

  /**
   * Method for getting the password connected to the user.
   *
   * @return the password of the user
   */
  public String getPassword() {
    return password;
  }

  /**
   * Method for adding a registration to the users registration object.
   *
   * @param registration the registration we wnat to add
   */
  public void addRegistration(StuddyBuddyRegistration registration) {
    registrations.add(0, registration);
  }

  /**
   * Method for getting the list over this users registrations.
   *
   * @return the list over registrations
   */
  public List<StuddyBuddyRegistration> getRegistrations() {
    return new ArrayList<StuddyBuddyRegistration>(registrations);
  }
}