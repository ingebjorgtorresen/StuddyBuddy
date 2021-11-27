package studdybuddy.dataaccess;

import java.io.File;
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
 * Can use pre-defined StuddyBuddy object, or read from file.
 */
public class DirectDataAccess implements DataAccess {

  private StuddyBuddiesPersistence buddiesPersistence;
  private String filename = "src/main/resources/studdybuddy/ui/directBuddies.json";
  private StuddyBuddies buddies;

  public DirectDataAccess() {
    buddiesPersistence = new StuddyBuddiesPersistence();
    buddiesPersistence.setSaveFilePath(filename);
    buddies = getStuddyBuddies();
  }

  @Override
  public StuddyBuddy getStuddyBuddyByName(String name) {
    StuddyBuddy buddy = getStuddyBuddies().getStuddyBuddy(name);
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
    buddies.addStuddyBuddy(buddy);
    try (Writer writer = new FileWriter(filename, StandardCharsets.UTF_8)) {
      buddiesPersistence.writeStuddyBuddies(buddies, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

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

  public void setPersistence(StuddyBuddiesPersistence persistence) {
    this.buddiesPersistence = persistence;
    persistence.setSaveFilePath("directBuddies.json");
  }

  /**
   * Uses persistence to save the StuddyBuddies objects.
   */
  public void autoSaveStuddyBuddies(StuddyBuddies buddies) {
    try {
      buddiesPersistence.saveStuddyBuddies(buddies);
    } catch (Exception e) {
      System.err.println("Could not save StuddyBuddies: " + e.getMessage());
    }
  }

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
