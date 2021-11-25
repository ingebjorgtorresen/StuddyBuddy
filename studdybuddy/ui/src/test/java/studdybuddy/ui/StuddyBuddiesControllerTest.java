package studdybuddy.ui;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import studdybuddy.core.StuddyBuddy;

public class StuddyBuddiesControllerTest extends ApplicationTest {

  private StuddyBuddiesController controller;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("StuddyBuddies_test.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @BeforeEach
  public void setUpStuddyBuddies() {
    controller = new StuddyBuddiesController();
  }

  @Test
  public void testController_studdyBuddies() {
    assertNotNull(this.controller);
  }

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
}