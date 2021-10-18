package ui;

import org.junit.jupiter.api.BeforeEach;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import core.StuddyBuddy;
import core.StuddyBuddyRegistration;
import org.junit.jupiter.api.Assertions;

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
    public void setUpRegistrations() {
      studdyBuddy = new StuddyBuddy();
      reg1 = new StuddyBuddyRegistration();
      reg2 = new StuddyBuddyRegistration();
    }
    
    @Test
    public void testController_studdyBuddy() {
      assertNotNull(this.controller);
      assertNotNull(this.studdyBuddy);
    }

    @Test
    public void testTextfield() {
      String newNameText = "New name";
      clickOn("#nameField").write(newNameText);
      //clickOn("#logInButton");
      //StuddyBuddy newName = new StuddyBuddy().name(newNameText);
      Assertions.assertEquals(newNameText, this.controller.getInputName());
    }
}
  