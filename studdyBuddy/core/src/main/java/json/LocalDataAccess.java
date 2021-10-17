package json;

import core.StuddyBuddies;
import core.StuddyBuddy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.Reader;
import java.io.Writer;
import java.io.InputStreamReader;
import java.io.FileWriter;

    /**
     * Class that works with data locally. (Using predefined StuddyBuddy object or read from file.)
     */

public class LocalDataAccess {

    private StuddyBuddies studdyBuddies; 
    private StuddyBuddiesPersistence studdyBuddiesPersistence= new StuddyBuddiesPersistence();
    private Boolean persistance = true;
    private String jsonFilename = "testStuddyBuddy.json";

    public LocalDataAccess() {
         this.studdyBuddies = readStuddyBuddies();
    }
    
    /**
     * Method for reading from .json file-locally. 
     * 
     * @return  StuddyBuddies object
     */
    public StuddyBuddies readStuddyBuddies() {
        if(this.jsonFilename != null) {
            try {
                URL url = getClass().getResource(this.jsonFilename);
                Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
                StuddyBuddies studdyBuddies = studdyBuddiesPersistence.readStuddyBuddies(reader);
                this.studdyBuddies = studdyBuddies;
                return studdyBuddies;

            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("Could not read from " + this.jsonFilename + ".");
            }
        }
        return null;
    }
    /**
     * Method that writes a serialized StuddyBuddy object as a json-string to the file in resources file
     * 
     * @param studdyBuddies   StuddyBuddies object we want to save
     */
    public void writeStuddyBuddies(StuddyBuddies studdyBuddies) {
        if(this.jsonFilename != null) {
            try {
                URL url = getClass().getResource(this.jsonFilename);
                Writer writer = new FileWriter(url.getFile(), StandardCharsets.UTF_8);
                studdyBuddiesPersistence.writeStuddyBuddies(studdyBuddies, writer);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("Unable to write from " + this.jsonFilename + ".");
            }
        }
    } 

    public void writeStuddyBuddies() {
        writeStuddyBuddies(this.studdyBuddies);
    }

    public StuddyBuddy getStuddyBuddyByName(String name) {
        StuddyBuddy studdyBuddy = studdyBuddies.getStuddyBuddy(name);
        if(studdyBuddy == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        return studdyBuddy;
    }

    public void putStuddyBuddy(StuddyBuddy studdyBuddy) {
        this.readStuddyBuddies();
        try {
            studdyBuddies.addStuddyBuddy(studdyBuddy);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("User already exists, and can't be created.");
        }
        if(persistance) {
            writeStuddyBuddies();
        }
    }
}
