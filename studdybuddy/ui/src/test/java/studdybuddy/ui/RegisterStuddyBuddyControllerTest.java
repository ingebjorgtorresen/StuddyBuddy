package studdybuddy.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.util.List;
import com.github.javafaker.Faker;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.dataaccess.DataAccess;
import studdybuddy.dataaccess.DirectDataAccess;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

/**
 * Test class for RegisterStuddyBuddyController.
 */
public class RegisterStuddyBuddyControllerTest extends ApplicationTest {

  private RegisterStuddyBuddyController controller = new RegisterStuddyBuddyController();
  private StuddyBuddy buddy;
  private DataAccess dataAccess = new DirectDataAccess();
  private StuddyBuddies buddies;
  Faker faker = new Faker();

  /**
   * Sets StuddyBuddies and DataAccess.
   */
  @BeforeEach
  public void setUpRegistration() throws InterruptedException {
    Thread.sleep(1000);
    buddies = dataAccess.getStuddyBuddies();
    controller.transferData(dataAccess, buddies);
    buddy = new StuddyBuddy();
    buddy.setName("name");
  }

  /**
   * Method that loads the and sets the scene.
   * 
   * @throws IOException if scene cannot load.
   */
  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterStuddyBuddy_test.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  /**
   * Method that checks that the controller and buddies is not null.
   */
  @Test
  public void testController() {
    assertNotNull(this.controller);
    assertNotNull(this.buddies);
  }

  /**
   * Method for checking that the NameTextfield works.
   * 
   * @throws InterruptedException
   */
  @Test
  public void testNameTextfield() throws InterruptedException {
    String newNameText = "New name";
    clickOn("#nameField");
    Thread.sleep(500);
    write(newNameText);
    Thread.sleep(500);
    assertEquals(newNameText, this.controller.getInputName());
  }

  /**
   * Method for checking that the passwordTextfield works.
   * 
   * @throws InterruptedException
   */
  @Test
  public void testPasswordTextField() throws InterruptedException {
    String newPasswordText = "12345678";
    clickOn("#passwordField");
    Thread.sleep(500);
    write(newPasswordText);
    Thread.sleep(500);
    assertEquals(newPasswordText, this.controller.getInputPassword());
  }

  /**
   * Method for checking that the checkpasswordTextfield works.
   * 
   * @throws InterruptedException
   */
  @Test
  public void testCheckPasswordTextfield() throws InterruptedException {
    String newCheckPasswordString = "12345678";
    clickOn("#passwordCheckField");
    Thread.sleep(500);
    write(newCheckPasswordString);
    Thread.sleep(500);
    assertEquals(newCheckPasswordString, this.controller.getInputPasswordCheck());
  }


  /**
   * Method for checking that the Register buttom works correctly for an incorrect registration.
   * 
   * @throws InterruptedException
   */
  @Test
  public void testRegisterButtonNotSwitchesWindom() throws InterruptedException {
    clickOn("#nameField");
    Thread.sleep(500);
    write("test2");
    clickOn("#passwordField").write("12345678");
    Thread.sleep(500);
    clickOn("#passwordCheckField").write("12345");
    Thread.sleep(500);
    List<Window> beforeClick = Window.getWindows();
    Parent beforeClickRoot = null;
    for (Window window : beforeClick) {
      beforeClickRoot = window.getScene().getRoot();
    }
    clickOn("#registerButton");
    List<Window> afterClick = Window.getWindows();
    Parent afterClickRoot = null;
    for (Window window : afterClick) {
      afterClickRoot = window.getScene().getRoot();
    }
    assertEquals(afterClickRoot, beforeClickRoot);
  }

  /**
   * Method for generating a random name for testing. To avoid using the same username if tests are
   * run multiple times.
   *
   * @return a random String name
   */
  private String getRandomName() {
    String firstName = faker.name().firstName();
    return firstName;
  }

  /**
   * Method for checking that the Register buttom works correctly for a correct registration.
   * 
   * @throws InterruptedException
   */
  @Test
  public void testRegisterButtonSwitchesWindow() throws InterruptedException {
    WaitForAsyncUtils.waitForFxEvents();
    clickOn("#nameField");
    Thread.sleep(500);
    String string = getRandomName();
    write(string);
    clickOn("#passwordField");
    write("12345678");
    Thread.sleep(500);
    clickOn("#passwordCheckField");
    Thread.sleep(500);
    write("12345678");
    List<Window> beforeClick = Window.getWindows();
    Parent beforeClickRoot = null;
    for (Window window : beforeClick) {
      beforeClickRoot = window.getScene().getRoot();
    }
    clickOn("#registerButton");
    Thread.sleep(500);
    List<Window> afterClick = Window.getWindows();
    Parent afterClickRoot = null;
    for (Window window : afterClick) {
      afterClickRoot = window.getScene().getRoot();
    }
    assertNotEquals(afterClickRoot, beforeClickRoot);
  }

  /**
   * Method for checking that the back button works.
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
    for (Window window : afterClick) {
      afterClickRoot = window.getScene().getRoot();
    }
    assertNotEquals(afterClickRoot, beforeClickRoot);
  }

}
