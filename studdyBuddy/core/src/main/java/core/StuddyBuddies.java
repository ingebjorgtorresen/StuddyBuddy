package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is handling the user database. Each user/StuddyBuddy is saved in
 * an ArrayList as StuddyBuddy-objects. It has methods for saving, adding and
 * getting users/studdiebuddies.
 */

public class StuddyBuddies implements Iterable<StuddyBuddy> {

    private List<StuddyBuddy> studdyBuddies = new ArrayList<>();

    /**
     * Empty constructor that initializes the instance. This will load
     * studdiebuddies from file.
     * 
     */
    public StuddyBuddies() {

    }

    /**
     * Method that returns StuddyBuddy object if it exists, else it returns null.
     * 
     * @param name The name of the user
     * @return StuddyBuddy if it exists or else null
     */
    public StuddyBuddy getStuddyBuddy(String name) {
        for (StuddyBuddy studdyBuddy : this.studdyBuddies) {
            if (studdyBuddy.getName().equals(name)) {
                return studdyBuddy;
            }
        }

        return null;
    }

    /**
     * Method that checks if a StuddyBuddy object already exists.
     * 
     * @param name The name of the user
     * @return true/false based on if the user exists or not
     */
    public boolean checkIfStuddyBuddyExists(String name) {
        if (getStuddyBuddy(name) != null) {
            return true;
        }

        return false;
    }

    /**
     * Method that adds new StuddyBuddy if it does not already exist.
     * 
     * @param studdyBuddy StuddyBuddy object we want to add
     */
    public void addStuddyBuddy(StuddyBuddy studdyBuddy) {
        if (checkIfStuddyBuddyExists(studdyBuddy.getName()) == false) {
            StuddyBuddy newStuddyBuddy = (StuddyBuddy) studdyBuddy;
            this.studdyBuddies.add(newStuddyBuddy);
        }

        else {
            throw new IllegalArgumentException("User already exists.");
        }
    }

    /**
     * Method that removes StuddyBuddy object if it exists.
     * 
     * @param studdyBuddy StuddyBuddy object we want to remove
     */
    public void removeStuddyBuddy(StuddyBuddy studdyBuddy) {
        this.studdyBuddies.remove(studdyBuddy);
    }

    /**
     * Method that iterates over StuddyBuddy-objects.
     */
    @Override
    public Iterator<StuddyBuddy> iterator() {
        return studdyBuddies.iterator();
    }

    /**
     * Method to get list over StuddyBuddy-objects.
     * 
     * @return List over StuddyBuddy-objects.
     */
    public List<StuddyBuddy> getStuddyBuddies() {
        return studdyBuddies;
    }

    /**
     * Method for string-represantation of studdybuddies list.
     */
    @Override
    public String toString() {
        return "" + studdyBuddies.stream().map(studdyBuddy -> studdyBuddy.toString()).collect(Collectors.toList());
    }

}
