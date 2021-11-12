package restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import todolist.core.*;

@RestController
@RequestMapping(StuddyBuddiesController.STUDDY_BUDDIES_SERVICE_PATH)
public class StuddyBuddiesController {

    public static final String STUDDY_BUDDIES_SERVICE_PATH = "restserver/studdyBuddy";

    @Autowired
    private StuddyBuddiesService studdyBuddiesService;
  
    @GetMapping
    public StuddyBuddies getStuddyBuddies() {
      return studdyBuddiesService.getStuddyBuddies();
    }
  
    private void autoSaveStuddyBuddies() {
        studdyBuddiesService.autoSaveStuddyBuddies();
    }

    private void checkStuddyBuddy(StuddyBuddy studdyBuddy, String name) {
        if (todoList == null) {
          throw new IllegalArgumentException("There is no studdyBuddy user named \"" + name + "\"");
        }
    
     /**
   * Gets the corresponding registration.
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
   * Replaces or adds a StuddyBuddy.
   *
   * @param name the name of the StuddyBuddy
   * @param studdyBuddy the studdyBuddy to add
   * @return true if it was added, false if it was replaced
   */
  @PutMapping(path = "/{name}")
  public boolean putStuddyBuddy(@PathVariable("name") String name,
      @RequestBody StuddyBuddy studdyBuddy) {
    boolean added = getStuddyBuddies().putStuddyBuddy(studdyBuddy) == null;
    autoSaveStuddyBuddies();
    return added;
  }

   /**
   * Renames the StuddyBuddy.
   *
   * @param name the name of the StuddyBuddy
   * @param newName the new name
   */
  @PostMapping(path = "/{name}/rename")
  public boolean renameStuddyBuddy(@PathVariable("name") String name,
      @RequestParam("newName") String newName) {
    StuddyBuddy studdyBuddy = getStuddyBuddies().getStuddyBuddy(name);
    checkStuddyBuddy(studdyBuddy, name);
    if (getStuddyBuddies().getStuddyBuddy(newName) != null) {
      throw new IllegalArgumentException("A StuddyBuddy named \"" + newName + "\" already exists");
    }
    studdyBuddy.setName(newName);
    autoSaveStuddyBuddies();
    return true;
  }

  /**
   * Removes the StuddyBuddy.
   *
   * @param name the name of the studdyBuddy
   */
  @DeleteMapping(path = "/{name}")
  public boolean removeStuddyBuddy(@PathVariable("name") String name) {
    StuddyBuddy studdyBuddy = getStuddyBuddies().getStuddyBuddy(name);
    checkStuddyBuddy(studdyBuddy, name);
    getStuddyBuddies().removeStuddyBuddy(studdyBuddy);
    autoSaveTodoModel();
    return true;
  }

  
}



