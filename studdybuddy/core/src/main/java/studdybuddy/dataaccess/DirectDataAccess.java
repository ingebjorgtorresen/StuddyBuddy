package studdybuddy.dataaccess;

import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.json.StuddyBuddiesPersistence;

/**
 * Class that works locally with data. 
 * Can use pre-defined StuddyBuddy object, or read from file.
 */
public class DirectDataAccess implements DataAccess {

  private StuddyBuddiesPersistence buddiesPersistence = null;

  @Override
  public StuddyBuddy getStuddyBuddyByName(String name, StuddyBuddies buddies) {
    StuddyBuddy buddy = buddies.getStuddyBuddy(name);
    if (buddy == null) {
      throw new IllegalArgumentException("The user does not exist.");
    }
    return buddy;
  }

  @Override
  public String getStuddyBuddyPasswordByName(String name, StuddyBuddies buddies) {
    return getStuddyBuddyByName(name, buddies).getPassword();
  }

  @Override
  public void putStuddyBuddy(StuddyBuddy buddy, StuddyBuddies buddies) {
    buddies.addStuddyBuddy(buddy);
  }

  @Override
  public void postStuddyBuddy(StuddyBuddy buddy, StuddyBuddies buddies) {
    buddies.addStuddyBuddy(buddy);
  }

  public void setPersistence(StuddyBuddiesPersistence persistence) {
    this.buddiesPersistence = persistence;
  }

  /**
   * Uses persistence to save the StuddyBuddies objects.
   */
  public void autoSaveStuddyBuddies(StuddyBuddies buddies) {
    try {
      buddiesPersistence.saveStuddyBuddies(buddies);
    } catch (Exception e) {
      System.err.println("Could not save StuddyBuddies: " + e.getMessage());
    }
  }
}