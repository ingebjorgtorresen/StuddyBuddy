package json;

import core.StuddyBuddy;
import json.StuddyBuddiesPersistence;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.Reader;
import java.io.Writer;
import java.io.InputStreamReader;
import java.io.FileWriter;

//Denne skal bruke StuddyBuddies objekt og ikkje bare StuddyBuddy

    /**
     * Class that works with data locally. (Using predefined StuddyBuddy object or read from file.)
     */

public class LocalDataAccess {

    private StuddyBuddy studdyBuddy; // denne er den som skal endres
    private StuddyBuddiesPersistence studdyBuddiesPersistence= new StuddyBuddiesPersistence();
    private Boolean persistance = true;
    private String jsonFilename = "testStuddyBuddy.json";

    public LocalDataAccess() {
         this.studdyBuddy = readStuddyBuddy();
    }
    
    /**
     * Method for reading from .json file-locally. 
     * 
     * @return  StuddyBuddy object
     */
    public StuddyBuddy readStuddyBuddy() {
        if(this.jsonFilename != null) {
            try {
                URL url = getClass().getResource(this.jsonFilename);
                Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
                StuddyBuddy studdyBuddy = studdyBuddiesPersistence.readStuddyBuddy(reader);
                this.studdyBuddy = studdyBuddy;
                return studdyBuddy;

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
     * @param studdyBuddy   StuddyBuddy object we want to save
     */
    public void writeStuddyBuddy(StuddyBuddy studdyBuddy) {
        if(this.jsonFilename != null) {
            try {
                URL url = getClass().getResource(this.jsonFilename);
                Writer writer = new FileWriter(url.getFile(), StandardCharsets.UTF_8);
                studdyBuddiesPersistence.writeStuddyBuddy(studdyBuddy, writer);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("Unable to write from " + this.jsonFilename + ".");
            }
        }
    } 
}
