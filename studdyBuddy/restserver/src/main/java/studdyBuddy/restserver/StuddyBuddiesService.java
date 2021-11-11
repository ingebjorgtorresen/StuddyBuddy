package restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import core.*;
import json.*;


@Service
public class StuddyBuddiesService {
    
  private StuddyBuddies studdyBuddies;
  private StuddyBuddiesPersistence studdyBuddiesPersistence;

  public StuddyBuddiesService(StuddyBuddies studdyBuddies) {
    this.studdyBuddies = studdyBuddies;
    this.studdyBuddiesPersistence = new StuddyBuddiesPersistence();
    studdyBuddiesPersistence.setSaveFile("springbootserver-studdyBuddy.json");
  }

  public StuddyBuddiesService() {
    this(createDefaultStuddyBuddies());
  }

  public TodoModel getStuddyBuddies() {
    return studdyBuddies;
  }

  public void setStuddyBuddiesl(StuddyBuddies studdyBuddies) {
    this.studdyBuddies = studdyBuddies;
  }

  private static StuddyBuddies createDefaultStuddyBuddies() {
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
    studdyBuddies.addStuddyBuddy(new StuddyBuddy("studdyBudyy1"));
    studdyBuddies.addStuddyBuddy(new StuddyBuddy("studdyBudyy2"));
    return studdyBuddies;
  }

  public void autoSaveStuddyBuddies() {
    if (studdyBuddiesPersistence != null) {
      try {
        todoPersistence.SaveStuddyBuddies(this.studdyBuddies);
      } catch (IllegalStateException | IOException e) {
        System.err.println("Couldn't auto-save StuddyBuddies: " + e);
      }
    }
  }
}