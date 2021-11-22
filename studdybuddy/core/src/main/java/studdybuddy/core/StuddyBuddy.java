package studdybuddy.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for StuddyBuddy, which is the user class. Each user has a name and a password.
 */

public class StuddyBuddy {

  private StuddyBuddies buddies;
  private String name;
  private String password;

  private List<StuddyBuddyRegistration> registrations = new ArrayList<>();

  public void setStuddyBuddies(StuddyBuddies buddies) {
    this.buddies = buddies;
  }

  public StuddyBuddies getStuddyBuddies() {
    return this.buddies;
  }

  /**
   * Method for setting name of the user if it is a valid name.
   * 
   * @param name the name to set.
   */
  public void setName(String name) {
    StuddyBuddyValidation.checkNotNull(name);
    if (!StuddyBuddyValidation.checkName(name)) {
      throw new IllegalArgumentException(
          "Name can not include any characthers but letters and ' ', you wrote: " + name);
    }
    this.name = name;
  }

  /**
   * Method for getting the name of the user.
   * 
   * @return name of the user.
   */
  public String getName() {
    return name;
  }

  /**
   * Method for setting password if it is valid. A valid password has at least 8 characters, and
   * consists of only digits and letters. Also throws exeption if password is invalid.
   * 
   * @param password the password the user wishes to set.
   */
  public void setPassword(String password) {
    StuddyBuddyValidation.checkNotNull(password);
    if (!StuddyBuddyValidation.checkPassword(password)) {
      throw new IllegalArgumentException("Please write a valid password.");
    }
    this.password = password;
  }

  /**
   * Method for getting the password connected to the user.
   * 
   * @return the password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Method for making an iterator over StuddyBuddyRegistration objects.
   * 
   * @return iterator over StudduBuddyregistration objects.
   */
  public Iterator<StuddyBuddyRegistration> iterator() {
    return registrations.iterator();
  }

  /**
   * Method for adding a registration to the users registration object.
   * 
   * @param registration the registration we wnat to add.
   */
  public void addRegistration(StuddyBuddyRegistration registration) {
    registrations.add(0, registration);
  }

  /**
   * Method for removing an element in the list over registrations if it exists.
   * 
   * @param registration the registration we want to remove.
   */
  public void removeRegistration(StuddyBuddyRegistration registration) {
    registrations.remove(registration);
  }

  /**
   * Method for getting the list over this users registrations.
   * 
   * @return the list over registrations.
   */
  public List<StuddyBuddyRegistration> getRegistrations() {
    return registrations;
  }

  public void updateStuddyBuddyObject(StuddyBuddy buddy) {
    this.name = buddy.name;
    this.password = buddy.password;
    this.registrations = buddy.registrations;

  }

  // Return a studdyBuddy object by its name
  @Override
  public String toString() {
    return getName();
  }
}