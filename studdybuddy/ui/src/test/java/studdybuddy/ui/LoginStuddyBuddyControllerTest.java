package studdybuddy.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.io.IOException;
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
import studdybuddy.dataaccess.DataAccess;
import studdybuddy.dataaccess.DirectDataAccess;

/**
 * Test class for LoginStuddyBuddyController.
 */
public class LoginStuddyBuddyControllerTest extends ApplicationTest {

  private LoginStuddyBuddyController controller = new LoginStuddyBuddyController();
  private DataAccess dataAccess = new DirectDataAccess();
  private StuddyBuddies buddies;

  @BeforeEach
  public void setUpController() {
    buddies = dataAccess.getStuddyBuddies();
    controller.transferData(dataAccess, buddies);
  }

  /**
   * Method that loads the and sets the scene.
   * @throws IOException if scene cannot load.
   */
  @Override
  public void start(final Stage stage) throws IOException {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginStuddyBuddy_test.fxml"));
    final Parent root = loader.load();
    controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show(); 
  }

  /**
  * Method for checking that the login button works when login is valid.
  * 
  * @throws InterruptedException if the thread is interrupted.
  */
  @Test
  public void testSuccessfullLogin() throws InterruptedException {
    String buddyName = "Selma";
    Thread.sleep(1000);
    clickOn("#nameField").write(buddyName);
    assertEquals(buddyName, controller.getInputName());

    String buddyPassword = "Passord1";
    Thread.sleep(1000);
    clickOn("#passwordField").write(buddyPassword);
    assertEquals(buddyPassword, controller.getInputPassword());

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
  * Method for checking that the login button works when username is invalid.
  * 
  * @throws InterruptedException if the thread is interrupted.
  */
  @Test
  public void testUnsuccessfullLogin() throws InterruptedException {
    String buddyName = "Frank";
    Thread.sleep(1000);
    clickOn("#nameField").write(buddyName);
    assertEquals(buddyName, controller.getInputName());

    String buddyPassword = "Passord1";
    Thread.sleep(1000);
    clickOn("#passwordField").write(buddyPassword);
    assertEquals(buddyPassword, controller.getInputPassword());

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
    assertEquals(afterClickRoot, beforeClickRoot);
  }

  /**
  * Method for checking that the login button works when username is invalid.
  * 
  * @throws InterruptedException if the thread is interrupted.
  */
  @Test
  public void testBackButton() throws InterruptedException {
    List<Window> beforeClick = Window.getWindows();
    Parent beforeClickRoot = null;
    for (Window window : beforeClick) {
    beforeClickRoot = window.getScene().getRoot();
    }

    Thread.sleep(1000);
    clickOn("#backButton");
    Thread.sleep(1000);
    List<Window> afterClick = Window.getWindows();
    Parent afterClickRoot = null;
    for(Window window : afterClick){
      afterClickRoot = window.getScene().getRoot();
    }
    assertEquals(afterClickRoot, beforeClickRoot);
  }
  
}
