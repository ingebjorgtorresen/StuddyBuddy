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

    private void checkStuddyBuddyNotNull(StuddyBuddy studdyBuddy, String name) {
        if (studdyBuddy == null) {
          throw new IllegalArgumentException("The studdyBuddy user named \"" + name + "\" has not been registered.");
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
    checkStuddyBuddyNotNull(studdyBuddy, name);
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
    checkStuddyBuddyNotNull(studdyBuddy, name);
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
  public StuddyBuddy putStuddyBuddy(@PathVariable("name") String name,
      @RequestBody StuddyBuddy studdyBuddy) {
    buddiesService.autoSaveStuddyBuddies();
    return studdyBuddy;
  }

 @PostMapping(path = "/{name}")
  public boolean postStuddyBuddy(@PathVariable("name") String name,
     @RequestBody StuddyBuddy studdyBuddy) {
    boolean updated = getStuddyBuddies().getStuddyBuddy(name) == null;
    autoSaveStuddyBuddies();
    return updated;
  }

}



