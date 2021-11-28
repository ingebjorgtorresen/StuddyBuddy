package studdybuddy.dataaccess;

import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;

/**
 * Interface for GET, PUT and POST methods used in DataAccess classes.
 */

public interface DataAccess {

  /**
   * Method for getting a StuddyBuddy object by its name.
   *
   * @param name name of the studdybuddy
   * @return a StuddyBuddy object
   */
  public StuddyBuddy getStuddyBuddyByName(String name);

  /**
   * Method for getting the password connected to a SatuddyBuddy by name.
   *
   * @param name the name of the studdybuddy
   * @return password connected to the studdybuddy as string
   */
  public String getStuddyBuddyPasswordByName(String name);

  /**
   * Method for adding a StuddyBuddy object to DataAccess.
   */
  public void putStuddyBuddy(StuddyBuddy buddy);

  /**
   * Method for updating by adding a StuddyBuddy object when changed.
   *
   * @param buddy the StuddyBuddy to save
   */
  public void postStuddyBuddy(StuddyBuddy buddy);

  /**
   * Method for getting for StuddyBuddies objects.
   */
  public StuddyBuddies getStuddyBuddies();
}
