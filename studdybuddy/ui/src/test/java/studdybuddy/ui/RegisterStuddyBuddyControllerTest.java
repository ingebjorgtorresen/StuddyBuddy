package studdybuddy.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.dataaccess.DataAccess;
import studdybuddy.dataaccess.DirectDataAccess;
import studdybuddy.json.StuddyBuddiesPersistence;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

public class RegisterStuddyBuddyControllerTest extends ApplicationTest{

    private RegisterStuddyBuddyController controller = new RegisterStuddyBuddyController();
    private StuddyBuddy buddy;
    private DataAccess dataAccess = new DirectDataAccess();
   // private StuddyBuddiesPersistence persistence;
    private StuddyBuddies buddies;

    @BeforeEach
    public void setUpRegistration() {
      buddies = dataAccess.getStuddyBuddies();
      controller.transferData(dataAccess, buddies);
      buddy = new StuddyBuddy();
      buddy.setName("name");
     
     // this.persistence = new StuddyBuddiesPersistence();
      //persistence.setSaveFilePath(userStuddyBuddyPath);
      //directAccess.setPersistence(persistence);
      //dataAccess = directAccess;
    }

    @Override
    public void start(final Stage stage) throws Exception {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterStuddyBuddy_test.fxml"));
      final Parent root = loader.load();
      this.controller = loader.getController();
      stage.setScene(new Scene(root));
      stage.show();
    }

    @Test
    public void testController() {
        assertNotNull(this.controller);
        assertNotNull(this.buddies);
    }

    @Test
    public void testNameTextfield() throws InterruptedException {
      WaitForAsyncUtils.waitForFxEvents();
      String newNameText = "New name";
      clickOn("#nameField");
      write(newNameText);
      assertEquals(newNameText, this.controller.getInputName());
    }

    @Test
    public void testPasswordTextField() throws InterruptedException {
      WaitForAsyncUtils.waitForFxEvents();
      String newPasswordText = "12345678";
      clickOn("#passwordField");
      write(newPasswordText);
      assertEquals(newPasswordText, this.controller.getInputPassword());
    }

    @Test
    public void testCheckPasswordTextfield() throws InterruptedException {
      WaitForAsyncUtils.waitForFxEvents();
      String newCheckPasswordString = "12345678";
      clickOn("#passwordCheckField");
      write(newCheckPasswordString);
      assertEquals(newCheckPasswordString, this.controller.getInputPasswordCheck());
    }

    @Test
    public void testRegisterButtonNotSwitchesWindom() throws InterruptedException {
      WaitForAsyncUtils.waitForFxEvents();
      clickOn("#nameField").write("test2");
      clickOn("#passwordField").write("12345678");
      clickOn("#passwordCheckField").write("12345");
      Thread.sleep(2000);
      List<Window> beforeClick = Window.getWindows();
      Parent beforeClickRoot = null;
      for(Window window : beforeClick){
        beforeClickRoot = window.getScene().getRoot();
      }
      clickOn("#registerButton");
      List<Window> afterClick = Window.getWindows();
      Parent afterClickRoot = null;
      for(Window window : afterClick){
        afterClickRoot = window.getScene().getRoot();
      }
      assertEquals(afterClickRoot, beforeClickRoot);
    }

    @Test
    public void testRegisterButtonSwitchesWindow() throws InterruptedException {
      WaitForAsyncUtils.waitForFxEvents();
      clickOn("#nameField").write("liugøjh");
      clickOn("#passwordField").write("12345678");
      clickOn("#passwordCheckField").write("12345678");
      List<Window> beforeClick = Window.getWindows();
      Parent beforeClickRoot = null;
      for(Window window : beforeClick){
        beforeClickRoot = window.getScene().getRoot();
      }
      clickOn("#registerButton");
      List<Window> afterClick = Window.getWindows();
      Parent afterClickRoot = null;
      for(Window window : afterClick){
        afterClickRoot = window.getScene().getRoot();
      }
      assertNotEquals(afterClickRoot, beforeClickRoot);
    }
}
