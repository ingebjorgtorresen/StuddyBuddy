package ui;

import org.junit.jupiter.api.BeforeEach;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import core.StuddyBuddy;
import core.StuddyBuddyRegistration;

public class StuddyBuddyControllerTest extends ApplicationTest {

    private StuddyBuddyController controller;

    //private StuddyPersistence studdyPersistence = new studdyPersistence();
    private StuddyBuddy studdyBuddy;
    private StuddyBuddyRegistration reg1;
    private StuddyBuddyRegistration reg2;
  
    @Override
    public void start(final Stage stage) throws Exception {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("/StuddyBuddy_test.fxml"));
      final Parent root = loader.load();
      this.controller = loader.getController();
      stage.setScene(new Scene(root));
      stage.show();
    }
  
    @BeforeEach
    public void setUpRegistrations(){
      studdyBuddy = new StuddyBuddy();
      reg1 = new StuddyBuddyRegistration();
      reg2 = new StuddyBuddyRegistration();
    }
    
    @Test
    public void testController_studdyBuddy() {
      assertNotNull(this.controller);
      assertNotNull(this.studdyBuddy);
    }

    /**
     * Ckeck that the UI changes window and scene when clicking on the
     * login button in UI
     */
    @Test
    public void testLoginButton(){
      List<Window> beforeClick = Window.getWindows();
      Parent beforeClickRoot = null;
      for(Window window : beforeClick){
        beforeClickRoot = window.getScene().getRoot();
      }
      clickOn("#login");
      try {
        Thread.sleep(5000);
      } catch (Exception e) {
        fail();
      }
      List<Window> afterClick = Window.getWindows();
      Parent afterClickRoot = null;
      for(Window window : afterClick){
        afterClickRoot = window.getScene().getRoot();
      }
      assertNotEquals(afterClickRoot, beforeClickRoot);
    }
}
  