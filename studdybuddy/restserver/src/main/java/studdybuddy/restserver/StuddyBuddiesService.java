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
import org.springframework.stereotype.Service;
import studdybuddy.core.*;
import studdybuddy.json.*;


@Service
public class StuddyBuddiesService {
    
  private StuddyBuddies buddies;
  private StuddyBuddiesPersistence persistence = new StuddyBuddiesPersistence();;
  private String buddiesJsonFileName = "buddies.json";
  private String initialBuddiesFileName = "initial_buddies.json";

  public StuddyBuddiesService() {
    putInitialStuddyBuddiesObject();
    getInitialStuddyBuddiesObject();
    persistence.setSaveFilePath(buddiesJsonFileName);
  }

  public StuddyBuddies getStuddyBuddies() {
    return readBuddies();
  }

  public void setStuddyBuddies(StuddyBuddies buddies) {
    this.buddies = buddies;
  }

  public void autoSaveStuddyBuddies() {
    if (persistence != null) {
      try {
        System.out.println();
        System.out.println("Fra service skrives dette studdybuddies objektet");
        System.out.println(buddies);
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
    buddies = getInitialStuddyBuddiesObject();
    writeBuddies(buddies);
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
   * @param users StuddyBuddies object to be saved
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
      if (path != null && path.toFile().exists())
      try (Reader reader = new FileReader(path.toFile(), StandardCharsets.UTF_8)){
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
    return null;
  }

  public void addStuddyBuddyToBuddies(StuddyBuddy buddy) {
    buddies.addStuddyBuddy(buddy);
  }

}