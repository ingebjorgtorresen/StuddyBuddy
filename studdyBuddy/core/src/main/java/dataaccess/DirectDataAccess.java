package dataaccess;

import core.StuddyBuddies;
import core.StuddyBuddy;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import json.StuddyBuddiesPersistence;

/**
 * Class that works locally with data. Can use pre-defined StuddyBuddy object, or read from file.
 */

public class DirectDataAccess implements DataAccess {

    private StuddyBuddies buddies;
    private String studdybuddiesFilename = "testStuddyBuddies.json";
    private Boolean persistence = true;
    private StuddyBuddiesPersistence buddiesPersistence = new StuddyBuddiesPersistence();

    /**
     * Constructor that creates an instance that reads from a json-file.
     */
    public DirectDataAccess() {
        buddies = readStuddyBuddies();
    }

    /**
     * Constructor that creates an instance that reads from the json.file specified as a parameter.
     * 
     * @param file the filename of the json file to write to
     */
    public DirectDataAccess(String file) {
        studdybuddiesFilename = file;
        buddies = readStuddyBuddies();
    }

    /**
     * Method for reading from json-file with reasource from field: studdybuddiesFilename
     * 
     * @return a StuddyBuddies object
     */
    public StuddyBuddies readStuddyBuddies() {
        if (studdybuddiesFilename != null) {
            try {
                URL url = getClass().getResource(studdybuddiesFilename);
                Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
                StuddyBuddies buddies = buddiesPersistence.readStuddyBuddies(reader);
                this.buddies = buddies;
                return buddies;
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalStateException("Unable to read from file: " + studdybuddiesFilename);
            }
        }
        return null;
    }

    /**
     * Method for writing a StuddyBuddy object serialized as a json-string to file in the resource file.
     * 
     * @param buddies StuddyBuddies object to save
     */
    public void writeStuddyBuddies(StuddyBuddies buddies) {
        if (studdybuddiesFilename != null) {
            try {
                URL url = getClass().getResource(studdybuddiesFilename);
                Writer writer = new FileWriter(url.getFile(), StandardCharsets.UTF_8);
                buddiesPersistence.writeStuddyBuddies(buddies, writer);
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException | RuntimeException e) {
                throw new IllegalStateException("Could not write to file: " + studdybuddiesFilename);
            }
        }
    }

    /**
     * Method that writes buddies field to the file.
     */
    public void writeStuddyBuddies() {
        writeStuddyBuddies(this.buddies);
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
        this.readStuddyBuddies();
        try {
            this.buddies.addStuddyBuddy(buddy);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "User already exist in the server, and the user can therefore not be created");
        }
        // This is for testing
        if (persistence) {
            writeStuddyBuddies();
        }
    }

    @Override
    public void postStuddyBuddy(StuddyBuddy buddy) {
        this.readStuddyBuddies();
        try {
            this.buddies.getStuddyBuddy(buddy.getName()).updateStuddyBuddyObject(buddy);;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(
                    "User does not exist in the server, and the user can therefore not be updated.");
        }
        // This is for testing
        if (persistence) {
            writeStuddyBuddies();
        }

    }

    /**
     * Method for setting StuddyBuddies objects.
     * 
     * @param buddies StuddyBuddies object to set
     */
    public void setStuddyBuddies(StuddyBuddies buddies) {
        this.buddies = buddies;
    }

    /**
     * Method for setting studdybuddiesFilename
     * 
     * @param filename the filename to be set
     */
    public void setFilename(String filename) {
        this.studdybuddiesFilename = filename;
    }

    /**
     * Method for getting the filename
     * 
     * @return the filename
     */
    public String getFilename() {
        return studdybuddiesFilename;
    }

    /**
     * Method for writing a empty StuddyBuddy object to the json-file. This is used for testing when one
     * wants to clear the document before testing.
     */
    public void writeEmptyStuddyBuddyToDocument() {
        writeStuddyBuddies(new StuddyBuddies());
    }
}
