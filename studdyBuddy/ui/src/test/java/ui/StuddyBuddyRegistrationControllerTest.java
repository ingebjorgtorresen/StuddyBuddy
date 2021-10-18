package ui;

import org.junit.jupiter.api.BeforeEach;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import core.StuddyBuddy;
import core.StuddyBuddyRegistration;


public class StuddyBuddyRegistrationControllerTest extends ApplicationTest {
    
    private StuddyBuddyRegistrationController controller;

    private StuddyBuddy studdyBuddy;
    
    private StuddyBuddyRegistration reg1;
    
    private String successfullRegistration = "Registration was successfull!";

    @Override
    public void start(final Stage stage) throws Exception {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("/StuddyBuddyRegistration_test.fxml"));
      final Parent root = loader.load();
      this.controller = loader.getController();
      this.controller.setStuddyBuddyFromLogin(generateTestStuddyBuddy());
      stage.setScene(new Scene(root));
      stage.show();
    }

    /**
     * Set up for testing StuddyBuddyRegistrationController.java
     * 
     * @return StuddyBuddy-instance for use in testing
     */
    private StuddyBuddy generateTestStuddyBuddy(){
        StuddyBuddy studdyBuddy = new StuddyBuddy();
        studdyBuddy.setName("Test");
        StuddyBuddyRegistration reg1 = new StuddyBuddyRegistration();
        reg1 = new StuddyBuddyRegistration();
        reg1.setRoom("A3-138");
        reg1.setCourse("ITP");
        reg1.setStartTime("11:00");
        reg1.setEndTime("12:00");
        studdyBuddy.addRegistration(reg1);
        return studdyBuddy;
    }

    /**
     * Set up for the tests. 
     * Clicking on the textFields and filling them with information.
     * 
     * @throws InterruptedException if Thread.sleep() fails
     */
    @BeforeEach
    public void setUpRegistrations() throws InterruptedException{
      Thread.sleep(1500);
      clickOn("#roomField").write("A3-138");
      clickOn("#courseField").write("ITP");
      clickOn("#startTimeField").write("11:00");
      clickOn("#endTimeField").write("12:00");
      Thread.sleep(500);
    }

    @Test
    public void testController_studdyBuddy() {
      assertNotNull(this.controller);
      assertNotNull(this.studdyBuddy);
    }

    /**
     * Ckeck that the label messageText prints the correct message
     * when the registration is successfull.
     */
    @Test
    public void checkSuccessfullRegistration(){
        checkRegister();
        assertEquals(successfullRegistration, controller.getMessageText());
    }

    /**
     * Ckeck that the label messageText don´t prints the successfull-message
     * when the registration is unsuccessfull.
     */
    @Test
    public void checkUnsuccessfullRegistration(){
        clickOn("#roomField").write("$chool");
        checkRegister();
        assertNotEquals(controller.getMessageText(), successfullRegistration);
    }

    /**
     * Click register-button
     */
    @Test
    public void checkRegister(){
        clickOn("#register");
    }

    // TODO: testing av lagringen? (skal kanskje ikke gjøre i denne test-klassen)

}
