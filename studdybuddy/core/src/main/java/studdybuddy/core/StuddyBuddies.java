package studdybuddy.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for all StuddyBuddies.
 * Has a list of all StuddyBuddy objects.
 */

public class StuddyBuddies implements Iterable<StuddyBuddy> {

  private List<StuddyBuddy> studdyBuddies = new ArrayList<>();

  /**
   * Method for searching for StuddyBuddy by name.
   *
   * @param name name of the user we want to get.
   * @return StuddyBuddy if it exists or null.
   */
  public StuddyBuddy getStuddyBuddy(String name) {
    for (StuddyBuddy buddy : this.studdyBuddies) {
      if (buddy.getName().equals(name)) {
        return buddy;
      }
    }
    return null;
  }

  /**
   * Method that checks if StuddyBuddy exitst by name.
   *
   * @param name name of the user
   * @return true if user exists, else null
   */
  private boolean checkIfStuddyBuddyExists(String name) {
    return getStuddyBuddy(name) != null;
  }

  /**
   * Method for adding a new StuddyBuddy to StuddyBuddies.
   *
   * @param buddy StuddyBuddy we want to add.
   */
  public void addStuddyBuddy(StuddyBuddy buddy) {
    if (!checkIfStuddyBuddyExists(buddy.getName())) {
      StuddyBuddy newStuddyBuddy = (StuddyBuddy) buddy;
      this.studdyBuddies.add(newStuddyBuddy);
      buddy.setStuddyBuddies(this);
    } else {
      throw new IllegalArgumentException("Already user existing with name: " + buddy.getName());
    }
  }

  /**
   * Method for adding many StuddyBuddy objects at once.
   *
   * @param buddies StuddyBuddy Objects we want to add.
   */
  public void addStuddyBuddies(StuddyBuddy... buddies) {
    for (StuddyBuddy buddy : buddies) {
      addStuddyBuddy(buddy);
    }
  }

  /**
   * Method that removes StudyBuddy if it exists.
   *
   * @param buddy StuddyBuddy to remove.
   */
  public void removeStuddyBuddy(StuddyBuddy buddy) {
    studdyBuddies.remove(buddy);
  }

  /**
   * Method that return this object as an iterator, and it enables (studdyBuddy : studdyBuddies).
   */
  @Override
  public Iterator<StuddyBuddy> iterator() {
    return this.studdyBuddies.iterator();
  }

  /**
   * Method for getting list over all StuddyBuddy objects.
   *
   * @return the list that contains StuddyBuddy objects.
   */
  public List<StuddyBuddy> getStuddyBuddies() {
    return this.studdyBuddies;
  }

  /**
   * Method for string representation of a list over StuddyBuddy objects.
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("Buddies: ");
    for (StuddyBuddy buddy : studdyBuddies) {
      builder.append(
          "\n" + buddy.getName() 
          + "\n" + buddy.getRegistrations()
          + "\n-----------------------------------------");
    }
    return builder.toString();
  }
}
