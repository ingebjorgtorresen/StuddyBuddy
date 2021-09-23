package ui;

import java.io.FileNotFoundException;

import core.StuddyBuddy;

public interface StuddyBuddyFileHandlerInterface {

    void saveRegistrationToFile(StuddyBuddy studdybuddy) throws FileNotFoundException;
    String readRegistrationFromFile() throws FileNotFoundException; 
}
