package ui;

import java.io.FileNotFoundException;

import core.*;

public interface StuddyBuddyFileHandlerInterface {

    void saveRegistrationToFile(StuddyBuddyRegistration registration) throws FileNotFoundException;
    String readRegistrationFromFile() throws FileNotFoundException; 
}
