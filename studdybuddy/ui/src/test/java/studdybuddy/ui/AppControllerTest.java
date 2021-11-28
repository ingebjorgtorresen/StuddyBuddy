package studdybuddy.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import studdybuddy.core.StuddyBuddies;

public class AppControllerTest extends ApplicationTest{
  
  private AppController controller = new AppController();
  private StuddyBuddies buddies;

  /**
   * Method that loads the and sets the scene.
   */
  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("StuddyBuddy_test.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show(); 
  }
  
  /**
   * Method that does setup before each test.
   */
  @BeforeEach
  public void setUpRegistration() {
    buddies = new StuddyBuddies();
  }

  /**
   * Method for checking that the controller and the studdyBuddies object are initialized
   */
  @Test
  public void testController() {
      assertNotNull(this.controller);
      assertNotNull(this.buddies);
  }

  /**
   * Method for checking if the LoginButton Switches Window.
   * 
   * @throws InterruptedException if the thread is interrupted
   */
  @Test
  public void testLoginButtonSwitchesWindow() throws InterruptedException {
    List<Window> beforeClick = Window.getWindows();
    Parent beforeClickRoot = null;
    for (Window window : beforeClick) {
    beforeClickRoot = window.getScene().getRoot();
    }
    Thread.sleep(1000);
    clickOn("#loginButton");
    Thread.sleep(1000);
    List<Window> afterClick = Window.getWindows();
    Parent afterClickRoot = null;
    for(Window window : afterClick){
      afterClickRoot = window.getScene().getRoot();
    }
    assertNotEquals(afterClickRoot, beforeClickRoot);
  }

  /**
   * Method for checking if the Register Button switches window.
   * 
   * @throws InterruptedException
   */
  @Test
  public void testRegisterButtonSwitchesWindow() throws InterruptedException {
    List<Window> beforeClick = Window.getWindows();
    Parent beforeClickRoot = null;
    for (Window window : beforeClick) {
    beforeClickRoot = window.getScene().getRoot();
    }
    Thread.sleep(1000);
    clickOn("#registerButton");
    Thread.sleep(1000);
    List<Window> afterClick = Window.getWindows();
    Parent afterClickRoot = null;
    for(Window window : afterClick){
      afterClickRoot = window.getScene().getRoot();
    }
    assertNotEquals(afterClickRoot, beforeClickRoot);
  }
}
