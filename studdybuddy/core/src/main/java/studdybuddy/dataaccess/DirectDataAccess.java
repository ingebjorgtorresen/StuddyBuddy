package studdybuddy.dataaccess;

import studdybuddy.core.*;
import studdybuddy.json.StuddyBuddiesPersistence;

/**
 * Class that works locally with data. Can use pre-defined StuddyBuddy object,
 * or read from file.
 */

public class DirectDataAccess implements DataAccess {

    private final StuddyBuddies buddies;
    private StuddyBuddiesPersistence buddiesPersistence = null;

    /**
     * Constructor that creates an instance of StuddyBuddies
     */
    public DirectDataAccess(StuddyBuddies buddies) {
        this.buddies = buddies;
    }

    @Override
    public StuddyBuddy getStuddyBuddyByName(String name) {
        StuddyBuddy buddy = buddies.getStuddyBuddy(name);
        if (buddy == null) {
            throw new IllegalArgumentException("The user does not exist.");
        }
        return buddy;
    }

    @Override
    public String getStuddyBuddyPasswordByName(String name) {
        return getStuddyBuddyByName(name).getPassword();
    }  

    @Override
    public void putStuddyBuddy(StuddyBuddy buddy) {
        buddies.addStuddyBuddy(buddy);
    }

    @Override
    public void postStuddyBuddy(StuddyBuddy buddy) {
        buddies.addStuddyBuddy(buddy);
    }

    public void setPersistence(StuddyBuddiesPersistence persistence) {
      this.buddiesPersistence = persistence;
    }
  
    public void autoSaveStuddyBuddies() {
        try {
            buddiesPersistence.saveStuddyBuddies(buddies);
        } catch (Exception e) {
            System.err.println("Could not save StuddyBuddies: " + e.getMessage());
        }
    }

}
