package ui;

import org.junit.jupiter.api.BeforeEach;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import core.StuddyBuddy;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testRegisterButtonSwitchesWindow() {
      clickOn("#nameField").write("Tuva");
      clickOn("#passwordField").write("12345678");
      clickOn("#passwordCheckField").write("12345678");
      
      if((this.controller.checkInputName() == true) && (this.controller.checkInputPassword() == true) 
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

    @Test
    public void testRegisterButtonNotSwitchesWindom() {
      clickOn("#nameField").write("Tuva0");
      clickOn("#passwordField").write("12345678");
      clickOn("#passwordCheckField").write("12345");
      
      if((this.controller.checkInputName() == false) || (this.controller.checkInputPassword() == false) 
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

    
}
