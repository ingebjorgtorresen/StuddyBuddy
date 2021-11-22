package studdybuddy.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.util.List;
import studdybuddy.core.StuddyBuddy;

public class RegisterStuddyBuddyControllerTest extends ApplicationTest{

    private RegisterStuddyBuddyController controller = new RegisterStuddyBuddyController();
    private StuddyBuddy buddy;

    @Override
    public void start(final Stage stage) throws Exception {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterStuddyBuddy_test.fxml"));
      final Parent root = loader.load();
      this.controller = loader.getController();
      stage.setScene(new Scene(root));
      stage.show();
        
    }

    @BeforeEach
    public void setUpRegistration() {
      buddy = new StuddyBuddy();
    }

    @Test
    public void testController() {
        assertNotNull(this.controller);
        assertNotNull(this.buddy);
    }

    @Test
    public void testNameTextfield() throws InterruptedException {
      Thread.sleep(1000);
      String newNameText = "New name";
      clickOn("#nameField").write(newNameText);
      assertEquals(newNameText, this.controller.getInputName());
    }

    @Test
    public void testPasswordTextField() throws InterruptedException {
      Thread.sleep(1000);
      String newPasswordText = "12345678";
      clickOn("#passwordField").write(newPasswordText);
      assertEquals(newPasswordText, this.controller.getInputPassword());
    }

    @Test
    public void testCheckPasswordTextfield() throws InterruptedException {
      Thread.sleep(1000);
      String newCheckPasswordString = "12345678";
      clickOn("#passwordCheckField").write(newCheckPasswordString);
      assertEquals(newCheckPasswordString, this.controller.getInputPasswordCheck());
    }

    @Test
    public void testRegisterButtonNotSwitchesWindom() throws InterruptedException {
      Thread.sleep(5000);
      clickOn("#nameField").write("Tuva0");
      clickOn("#passwordField").write("12345678");
      clickOn("#passwordCheckField").write("12345");
      
      if((this.controller.checkName() == false) || (this.controller.checkPassword() == false) 
      || (this.controller.checkPasswordsMatch() == false)) {
        List<Window> beforeClick = Window.getWindows();
        Parent beforeClickRoot = null;
        for(Window window : beforeClick){
          beforeClickRoot = window.getScene().getRoot();
        }
        clickOn("#register");
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
        assertEquals(afterClickRoot, beforeClickRoot);
      }

    }

    @Test
    public void testRegisterButtonSwitchesWindow() throws InterruptedException {
      Thread.sleep(5000);
      clickOn("#nameField").write("Tuva");
      clickOn("#passwordField").write("12345678");
      clickOn("#passwordCheckField").write("12345678");
      
      if((this.controller.checkName() == true) && (this.controller.checkPassword() == true) 
      && (this.controller.checkPasswordsMatch() == true)) {
        List<Window> beforeClick = Window.getWindows();
        Parent beforeClickRoot = null;
        for(Window window : beforeClick){
          beforeClickRoot = window.getScene().getRoot();
        }
        clickOn("#register");
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

    
}
