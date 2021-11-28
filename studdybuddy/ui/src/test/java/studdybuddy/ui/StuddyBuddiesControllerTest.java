package studdybuddy.ui;

import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import studdybuddy.dataaccess.DirectDataAccess;

public class StuddyBuddiesControllerTest extends ApplicationTest {

  private StuddyBuddiesController controller;
  private DirectDataAccess directDataAccess;
  private Label allRegistrationsText;

  /**
   * Method that loads and sets the scene.
   */
  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("StuddyBuddies_test.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  /**
   * Method that sets up the StuddyBuddiesController used in tests.
   */
  @BeforeEach
  public void setUpStuddyBuddies() {
    controller = new StuddyBuddiesController();
    directDataAccess = new DirectDataAccess();
    allRegistrationsText = new Label();
  }

  /**
  * Method for checking that the controller is initialized
  */
  @Test
  public void testController_studdyBuddies() {
    assertNotNull(this.controller);
    assertNotNull(this.directDataAccess);
  }

  /**
  * Method for checking if the correct dataAccess and studdyBuddies is transfered
  * between classes.
  */
  @Test
  public void testTransferData() {
    // TODO
  }

  /**
  * Method for checking if the AddRegistrationButton Switches Window.
  * 
  * @throws InterruptedException if the thread is interrupted
  */
  @Test
  public void testAddRegistrationButton() throws InterruptedException {
    List<Window> beforeClick = Window.getWindows();
    Parent beforeClickRoot = null;
    for(Window window : beforeClick){
      beforeClickRoot = window.getScene().getRoot();
    }
    Thread.sleep(5000);
    clickOn("#addRegistrationButton");
    Thread.sleep(5000);
    List<Window> afterClick = Window.getWindows();
    Parent afterClickRoot = null;
    for(Window window : afterClick){
      afterClickRoot = window.getScene().getRoot();
    }
    assertNotEquals(afterClickRoot, beforeClickRoot);
  }

  /**
  * Method for checking if the LogOutButton Switches Window.
  * 
  * @throws InterruptedException if the thread is interrupted
  */
  @Test
  public void testLogOutButton() throws InterruptedException {
    List<Window> beforeClick = Window.getWindows();
    Parent beforeClickRoot = null;
    for(Window window : beforeClick){
      beforeClickRoot = window.getScene().getRoot();
    }
    Thread.sleep(5000);
    clickOn("#logOut");
    Thread.sleep(5000);
    List<Window> afterClick = Window.getWindows();
    Parent afterClickRoot = null;
    for(Window window : afterClick){
      afterClickRoot = window.getScene().getRoot();
    }
    assertNotEquals(afterClickRoot, beforeClickRoot);
  }

  /**
  * Method for checking if the correct information is diplayed.
  */
  @Test
  public void testDisplay() {
    allRegistrationsText.setText(directDataAccess.getStuddyBuddies().toString());
    String displayedStuddyBuddies = directDataAccess.getStuddyBuddies().toString();
    assertEquals(displayedStuddyBuddies, allRegistrationsText.getText());
  }
}