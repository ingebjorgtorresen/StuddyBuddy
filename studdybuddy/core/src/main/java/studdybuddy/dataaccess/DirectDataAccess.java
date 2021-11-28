package studdybuddy.dataaccess;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.json.StuddyBuddiesPersistence;

/**
 * Class that works locally with data.
 */
public class DirectDataAccess implements DataAccess {

  private StuddyBuddiesPersistence buddiesPersistence;
  private String filename = "src/main/resources/studdybuddy/ui/directBuddies.json";
  private StuddyBuddies buddies;

  /**
   * Constructor that sets persistence, filename and buddies.
   */
  public DirectDataAccess() {
    buddiesPersistence = new StuddyBuddiesPersistence();
    buddiesPersistence.setSaveFilePath(filename);
    buddies = getStuddyBuddies();
  }

  /**
   * Method that gets StuddyBuddy by name.
   *
   * @param name of StuddyBuddy to get
   */
  @Override
  public StuddyBuddy getStuddyBuddyByName(String name) {
    StuddyBuddy buddy = getStuddyBuddies().getStuddyBuddy(name);
    if (buddy == null) {
      throw new IllegalArgumentException("The user does not exist.");
    }
    return buddy;
  }

  /**
   * Method that gets StuddyBuddy by password.
   *
   * @param name of StuddyBuddy to get password
   * @return password
   */
  @Override
  public String getStuddyBuddyPasswordByName(String name) {
    return getStuddyBuddyByName(name).getPassword();
  }

  /**
   * Method for saving StuddyBuddy to file.
   *
   * @param buddy to save
   */
  @Override
  public void putStuddyBuddy(StuddyBuddy buddy) {
    buddies.addStuddyBuddy(buddy);
    try (Writer writer = new FileWriter(filename, StandardCharsets.UTF_8)) {
      buddiesPersistence.writeStuddyBuddies(buddies, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method for updating a StuddyBuddy.
   *
   * @param buddy to update
   */
  @Override
  public void postStuddyBuddy(StuddyBuddy buddy) {
    buddies.removeStuddyBuddy(buddies.getStuddyBuddy(buddy.getName()));
    buddies.addStuddyBuddy(buddy);
    try (Writer writer = new FileWriter(filename, StandardCharsets.UTF_8)) {
      buddiesPersistence.writeStuddyBuddies(buddies, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Method for setting persistence, which also sets filepath.
   *
   * @param persistence to set
   */
  public void setPersistence(StuddyBuddiesPersistence persistence) {
    this.buddiesPersistence = persistence;
    persistence.setSaveFilePath("directBuddies.json");
  }

  /**
   * Method that uses persistence to save the StuddyBuddies objects.
   */
  public void autoSaveStuddyBuddies(StuddyBuddies buddies) {
    try {
      buddiesPersistence.saveStuddyBuddies(buddies);
    } catch (Exception e) {
      System.err.println("Could not save StuddyBuddies: " + e.getMessage());
    }
  }

  /**
   * Method for getting StuddyBuddies from file.
   *
   * @return StuddyBuddies from file
   */
  @Override
  public StuddyBuddies getStuddyBuddies() {
    try (Reader reader = new FileReader(filename, StandardCharsets.UTF_8)) {
      return buddiesPersistence.readStuddyBuddies(reader);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
