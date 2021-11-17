package studdybuddy.dataaccess;

import studdybuddy.core.*;

/**
 * Interface for methods GET, PUT and POST methods used in DataAccess classes.
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
     * Method for getting a the password connected to a SatuddyBuddy by name
     * 
     * @param name the name of the studdybuddy
     * @return password connected to the studdybuddy as string
     */
    public String getStuddyBuddyPasswordByName(String name);

    /**
     * Method for adding a StuddyBuddy object to server.
     */
    public void putStuddyBuddy(StuddyBuddy buddy);

    /**
     * Method for updating by adding a StuddyBuddyc object when changed.
     * 
     * @param buddy the StuddyBuddy to save
     */
    public void postStuddyBuddy(StuddyBuddy buddy);

}