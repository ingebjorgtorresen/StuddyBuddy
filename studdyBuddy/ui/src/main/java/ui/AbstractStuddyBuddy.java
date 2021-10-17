package ui;

import java.util.Collections;
import java.util.Iterator;

import core.StuddyBuddyRegistration;

public class AbstractStuddyBuddy implements Iterable<StuddyBuddyRegistration> {

    private String name;

    public AbstractStuddyBuddy(String name){
        setName(name);
    }

    @Override
    public String toString() {
        return String.format("[AbstractStuddyBuddy name=%s]", getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StuddyBuddyRegistration createStuddyBuddyRegistration() {
        throw new UnsupportedOperationException("An abstract StuddyBuddy cannot create StuddybuddyRegistration");
      }
    
    /**
    * Adds the provided StuddyBuddyRegistrations to this StuddyBuddy.
    * If a StuddyBuddyRegistration is not an instance of StuddyBuddy-item,
    * its contents is copied in to a new StuddyBuddy-item and that is added instead.
    *
    * @param registrations the StuddyBuddyRegistrations to add
    */
    public void addStuddyBuddyRegistrations(StuddyBuddyRegistration... registrations) {
        throw new UnsupportedOperationException("An abstract StuddyBuddy cannot add StuddybuddyRegistration");
    }

    
    /**
    * Adds the provided StuddyBuddyRegistration to this StuddyBuddy.
    * If the StuddyBuddyRegistration is not an instance of StuddyBuddy-item,
    * its contents is copied in to a new StuddyBuddy-item and that is added instead.
    *
    * @param registration the StuddyBuddyRegistration to add
    */
    public void addStuddyBuddyRegistration(StuddyBuddyRegistration registration){
        addStuddyBuddyRegistrations(registration);
    }

    public void removeStuddyBuddyRegistration(StuddyBuddyRegistration registration) {
        throw new UnsupportedOperationException("An abstract StuddyBuddy cannot remove StuddybuddyRegistration");
    }

    @Override
    public Iterator<StuddyBuddyRegistration> iterator() {
        return Collections.emptyIterator();
    }    

}
