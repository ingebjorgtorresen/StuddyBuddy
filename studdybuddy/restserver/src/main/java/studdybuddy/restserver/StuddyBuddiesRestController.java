package studdybuddy.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;

@RestController
@RequestMapping("/studdybuddy")
public class StuddyBuddiesRestController {

    @Autowired
    private StuddyBuddiesService buddiesService;
  
    @GetMapping("")
    public StuddyBuddies getStuddyBuddies() {
      return buddiesService.getStuddyBuddies();
    }
  
    private void autoSaveStuddyBuddies() {
      buddiesService.autoSaveStuddyBuddies();
    }

    private void checkStuddyBuddy(StuddyBuddy studdyBuddy, String name) {
        if (studdyBuddy == null) {
          throw new IllegalArgumentException("There is no studdyBuddy user named \"" + name + "\"");
        }
    }
    
  /**
   * Gets the corresponding studdybuddy.
   *
   * @param name the name of the StuddyBuddy
   * @return the corresponding StuddyBuddy
   */
  @GetMapping(path = "/{name}")
  public StuddyBuddy getStuddyBuddy(@PathVariable("name") String name) {
    StuddyBuddy studdyBuddy = getStuddyBuddies().getStuddyBuddy(name);
    checkStuddyBuddy(studdyBuddy, name);
    return studdyBuddy;
  }

  /**
   * Gets the corresponding password.
   *
   * @param name the name of the StuddyBuddy
   * @return the corresponding password
   */
  @GetMapping(path = "/{name}/pw")
  public String getPasswordByName(@PathVariable("name") String name) {
    StuddyBuddy studdyBuddy = getStuddyBuddies().getStuddyBuddy(name);
    String password = studdyBuddy.getPassword();
    checkStuddyBuddy(studdyBuddy, name);
    return password;
  }

  /**
   * Adds a StuddyBuddy if it does not already exist.
   *
   * @param name the name of the StuddyBuddy
   * @param studdyBuddy the studdyBuddy to add
   * @return true if it was added, false if it was replaced
   */
  @PutMapping(path = "/{name}")
  public boolean putStuddyBuddy(@PathVariable("name") String name,
      @RequestBody StuddyBuddy studdyBuddy) {
    boolean added = getStuddyBuddies().putStuddyBuddy(name) == null;
    autoSaveStuddyBuddies();
    return added;
  }

 @PostMapping(path = "/{name}")
  public boolean postStuddyBuddy(@PathVariable("name") String name,
     @RequestBody StuddyBuddy studdyBuddy) {
    boolean updated = getStuddyBuddies().getStuddyBuddy(name) == null;
    autoSaveStuddyBuddies();
    return updated;
  }

}



