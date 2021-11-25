package studdybuddy.dataaccess;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
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
  private String filename = "directBuddies.json";
  private StuddyBuddies buddies;

  public DirectDataAccess() {
    buddiesPersistence = new StuddyBuddiesPersistence();
    buddiesPersistence.setSaveFilePath(filename);
    buddies = getStuddyBuddies();
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
    buddies.addStuddyBuddy(buddy);
  }

  @Override
  public void postStuddyBuddy(StuddyBuddy buddy) {
    buddies.addStuddyBuddy(buddy);
  }

  public void setPersistence(StuddyBuddiesPersistence persistence) {
    this.buddiesPersistence = persistence;
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
    try {
      URL url = getClass().getResource(filename);
      Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
      return buddiesPersistence.readStuddyBuddies(reader);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
