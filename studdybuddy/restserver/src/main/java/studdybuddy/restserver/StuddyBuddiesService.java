package studdybuddy.restserver;

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
  private String initialBuddiesFile = "initial_buddies.json";

  public StuddyBuddiesService(StuddyBuddies buddies) {
    this.buddies = buddies;
    persistence.setSaveFilePath("springbootserver-studdyBuddy.json");
  }

  public StuddyBuddiesService() {
    putInitialStuddyBuddiesObject();
    getInitialStuddyBuddiesObject();
  }

  public StuddyBuddies getStuddyBuddies() {
    //TODO: FIKS DENNE METODEN SLIK AT DEN RETURNERER DET INSTANSIERTE BUDDIES OBJEKTET
    return buddies;
  }

  public void setStuddyBuddies(StuddyBuddies buddies) {
    this.buddies = buddies;
  }

  /*private static StuddyBuddies createDefaultStuddyBuddies() {
    StuddyBuddiesPersistence studdyBuddiesPersistence = new StuddyBuddiesPersistence();
    URL url = StuddyBuddiesService.class.getResource("default-studdyBuddies.json");
    if (url != null) {
      try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
        return studdyBuddiesPersistence.readStuddyBuddies(reader);
      } catch (IOException e) {
        System.out.println("Couldn't read default-studdyBuddies.json, so rigging studdyBuddies manually ("
            + e + ")");
      }
    }
    StuddyBuddies studdyBuddies = new StuddyBuddies();
    StuddyBuddy studdyBuddy1 = new StuddyBuddy();
    studdyBuddy1.setName("Anette");
    studdyBuddy1.setPassword("Anette123");
    studdyBuddies.addStuddyBuddy(studdyBuddy1);
    StuddyBuddy studdyBuddy2 = new StuddyBuddy();
    studdyBuddy2.setName("Tuva");
    studdyBuddy2.setPassword("Tuva1234");
    studdyBuddies.addStuddyBuddy(studdyBuddy2);
    return studdyBuddies;
  }*/

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
    buddies = getInitialStuddyBuddiesObject();
    writeBuddies(buddies);
  }

  /**
   * Method for getting a intial StuddyBuddies object from a json-file in the resources folder.
   *
   * @return a StuddyBuddies object
   */
  private StuddyBuddies getInitialStuddyBuddiesObject() {
    if (initialBuddiesFile != null) {
      try {
        URL url = getClass().getResource(initialBuddiesFile);
        System.out.println();
        System.out.println("url: " + url);
        System.out.println();
        Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
        StuddyBuddies buddies = persistence.readStuddyBuddies(reader);
        return buddies;
      } catch (IOException | NullPointerException e) {
        e.printStackTrace();
        throw new IllegalStateException(
          "Unable to read from '" + initialBuddiesFile + "'.");
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
}