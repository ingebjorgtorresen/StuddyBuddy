package studdybuddy.restserver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.json.StuddyBuddiesPersistence;

/**
 * Service class for Spring Boot server.
 */
@Service
public class StuddyBuddiesService {
    
  private StuddyBuddies buddies;
  private StuddyBuddiesPersistence persistence = new StuddyBuddiesPersistence();
  private String buddiesJsonFileName = "buddies.json";
  private String initialBuddiesFileName = "initial_buddies.json";
  private boolean initialBuddiesInBuddies = false;

  /**
   * Constructor that sets the StuddyBuddies.
   */
  public StuddyBuddiesService() {
    putInitialStuddyBuddiesObject();
    getInitialStuddyBuddiesObject();
    persistence.setSaveFilePath(buddiesJsonFileName);
  }

  public StuddyBuddies getStuddyBuddies() {
    return readBuddies();
  }

  /**
   * Method that uses persistence to save studdyBuddies.
   */
  public void autoSaveStuddyBuddies() {
    if (persistence != null) {
      try {
        persistence.saveStuddyBuddies(buddies);
      } catch (IllegalStateException | IOException e) {
        System.err.println("Couldn't auto-save StuddyBuddies: " + e);
      }
    }
  }

  /**
   * Method for putting an initial StuddyBuddies object to the server based on a json-file
   * in the resources folder.
   */
  protected void putInitialStuddyBuddiesObject() {
    if (!initialBuddiesInBuddies) {
      buddies = getInitialStuddyBuddiesObject();
      writeBuddies(buddies);
      initialBuddiesInBuddies = true;
    }

  }

  /**
   * Method for getting a intial StuddyBuddies object from a json-file in the resources folder.
   *
   * @return a StuddyBuddies object
   */
  private StuddyBuddies getInitialStuddyBuddiesObject() {
    if (initialBuddiesFileName != null) {
      try {
        URL url = getClass().getResource(initialBuddiesFileName);
        Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
        StuddyBuddies buddies = persistence.readStuddyBuddies(reader);
        return buddies;
      } catch (IOException | NullPointerException e) {
        e.printStackTrace();
        throw new IllegalStateException(
          "Unable to read from '" + initialBuddiesFileName + "'.");
      }
    }
    return null;
  }

  /**
   * Method for writing a StuddyBuddies object to a json-file.
   *
   * @param buddies StuddyBuddies object to be saved
   */
  public void writeBuddies(StuddyBuddies buddies) {
    if (buddiesJsonFileName != null) {
      Path path = Paths.get(System.getProperty("user.home"), buddiesJsonFileName);
      try (Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8, false)) {
        persistence.writeStuddyBuddies(buddies, writer);
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Couldn't write");
      }
    }
  }

  /**
   * Method for reading a StuddyBuddies object from a json-file. 
   *
   * @return the StuddyBuddies object
   */
  public StuddyBuddies readBuddies() {
    if (buddiesJsonFileName != null) {
      Path path = Paths.get(System.getProperty("user.home"), buddiesJsonFileName);
      if (path != null && path.toFile().exists()) {
        try (Reader reader = new FileReader(path.toFile(), StandardCharsets.UTF_8)) {
          putInitialStuddyBuddiesObject();
          StuddyBuddies buddies = persistence.readStuddyBuddies(reader);
          return buddies;
        } catch (IOException e) {
          e.printStackTrace();
          throw new IllegalStateException(
              "Unable to read from '" + Paths.get(System.getProperty("user.home"), 
              buddiesJsonFileName) + "'.");
        } 
      }
    }
    return null;
  }

  public void addStuddyBuddyToBuddies(StuddyBuddy buddy) {
    buddies.addStuddyBuddy(buddy);
  }

  public void updateStuddyBuddies(StuddyBuddy buddy) {
    buddies.removeStuddyBuddy(buddies.getStuddyBuddy(buddy.getName()));
    buddies.addStuddyBuddy(buddy);;
  }
}