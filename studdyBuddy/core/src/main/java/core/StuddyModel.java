package core;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class StuddyModel implements Iterable<AbstractStuddyBuddy> {

    private Map<String, AbstractStuddyBuddy> studdyBuddies = new LinkedHashMap<>();

    @Override
    public String toString() {
        return String.format("[StuddyModel #studdyBuddies=%s]", studdyBuddies.size());
    }

    /**
    * Checks if this StuddyModel already has a StuddyBuddy with the provided name.
    *
    * @param name the name to check
    * @return true if a StuddyBUddy with the provided name exists, false otherwise
    */
    public boolean hasStuddyBuddy(String name) {
        return studdyBuddies.containsKey(name);
    }

    /**
    * Checks if it is OK to add a StuddyBuddy with the provided name.
    *
    * @param name the new name
    * @return true if the name is value, false otherwise
    */
    public boolean isValidStuddyBuddyName(String name) {
        return name.strip().length() > 0;
    }

    /**
    * Adds the StuddyBuddy to this StuddyModel.
    *
    * @param studdyBuddy the StuddyBuddy
    * @throws IllegalArgumentException if the studdyBuddy's name is invalid
    */
    public void addStuddyBuddy(AbstractStuddyBuddy studdyBuddy) {
        if (! isValidStuddyBuddyName(studdyBuddy.getName())) {
        throw new IllegalArgumentException(studdyBuddy.getName() + " is not a legal name for a new list");
        }
        studdyBuddies.put(studdyBuddy.getName(), studdyBuddy);
    }

    public void removeStuddyBuddy(AbstractStuddyBuddy studdyBuddy){
        studdyBuddies.remove(studdyBuddy.getName());
    }

    @Override
    public Iterator<AbstractStuddyBuddy> iterator() {
        return studdyBuddies.values().iterator();
    }

    /**
    * Gets the StuddyBuddy with the provided name.
    *
    * @param name the name
    * @return the StuddyBuddy with the provided name
    */
    public AbstractStuddyBuddy getStuddyBuddy(String name) {
        return studdyBuddies.get(name);
    }

    /**
    * Replaces an existing StuddyBuddy with the same name, or adds it.
    *
    * @param studdyBuddy the StuddyBuddy
    * @return the replaced StuddyBuddy, or null
    */
    public AbstractStuddyBuddy putStuddyBuddy(AbstractStuddyBuddy studdyBuddy) {
        return studdyBuddies.put(studdyBuddy.getName(), studdyBuddy);
    }

}