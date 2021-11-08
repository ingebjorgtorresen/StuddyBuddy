package ui;

/*
import org.junit.jupiter.api.BeforeEach;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import core.StuddyBuddy;
import core.StuddyBuddyRegistration;
*/

public class StuddyBuddyRegistrationControllerTest {
    /*
    private StuddyBuddyRegistrationController controller;

    private StuddyBuddy studdyBuddy;

    private StuddyBuddyRegistration registration;
        
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
     *
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
        reg1.setStuddyBuddy(studdyBuddy);
        this.registration = reg1;
        return this.studdyBuddy = studdyBuddy;
    }

    /**
     * Set up for the tests. 
     * Clicking on the textFields and filling them with information.
     * 
     * @throws InterruptedException if Thread.sleep() fails
     *
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
     *
    @Test
    public void checkSuccessfullRegistration(){
        checkRegister();
        assertEquals(successfullRegistration, controller.getMessageText());
    }

    /**
     * Checks that the registration is not successfull when the input
     * in roomField is invalid.
     *
    @Test
    public void testRoom(){
      clickOn("#roomField").write("$chool");
      assertNotEquals(successfullRegistration, controller.getMessageText());
    }

     /**
     * Checks that the registration is not successfull when the input
     * in courseField is invalid.
     *
    @Test
    public void testCourse(){
      clickOn("#courseField").write("3");
      assertNotEquals(successfullRegistration, controller.getMessageText());
    }

     /**
     * Checks that the registration is not successfull when the input
     * in startTimeField is invalid.
     *
    @Test
    public void testStartTime(){
      clickOn("#startTimeField").write("123:00");
      assertNotEquals(successfullRegistration, controller.getMessageText());
    }

     /**
     * Checks that the registration is not successfull when the input
     * in endTimeField is invalid.
     *
    @Test
    public void testEndTime(){
      clickOn("#endTimeField").write("24:000");
      assertNotEquals(successfullRegistration, controller.getMessageText());
    }

    /**
     * Ckeck that the label feedbackText is not visable
     * when the registration is unsuccessfull.
     *
    @Test
    public void checkUnsuccessfullRegistration(){
      if(successfullRegistration.equals(controller.getMessageText())){
        assertFalse(controller.getFeedbackLabel().isVisible());
      }
    }

    /**
     * Click register-button
     *
    @Test
    public void checkRegister(){
        clickOn("#register");
    }

    /**
     * Click that the registration is displayed correctly
     *
    @Test
    public void testDisplayRegistration(){
      
      if (this.studdyBuddy == null){
        assertEquals("Couldn't register. Try again.", controller.getMessageText());
      }

      else{
        checkSuccessfullRegistration();
        assertEquals(Color.web("#7DDF64"), controller.getMessageTextLabel().getTextFill());
        //assertEquals("Name: Test" + "\n" + "Room: A3-138" + "\n" + "Course: ITP" + "\n" + "Start time: 11:00" + "\n" + "End time: 12:00", controller.getFeedbackLabel().getText());
        assertEquals("-fx-background-color: #C0DF85", controller.getFeedbackLabel().getStyle());
        assertTrue(controller.getFeedbackLabel().isVisible());
      }

      assertTrue(controller.getMessageTextLabel().isVisible());
      assertTrue(controller.getRoom().getText().equals(""));
      assertTrue(controller.getCourse().getText().equals(""));
      assertTrue(controller.getStartTime().getText().equals(""));
      assertTrue(controller.getEndTime().getText().equals(""));
    }

    @Test
    public void testHandleRegistration(){
      if (controller.getFeedbackLabel().isVisible()){
        assertEquals(Color.web("#ED4D6E"), controller.getMessageTextLabel().getTextFill());
      }

      //Check that the registration is correct
      assertEquals("A3-138", controller.getRoom().getText());
      assertEquals("ITP", controller.getCourse().getText());
      assertEquals("11:00", controller.getStartTime().getText());
      assertEquals("12:00", controller.getEndTime().getText());

      assertEquals(studdyBuddy, registration.getStuddyBuddy());
      assertTrue(studdyBuddy.getRegistrations().contains(registration));

    }
    */

}
