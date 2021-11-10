package ui;

/*
import org.junit.jupiter.api.BeforeEach;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.scene.control.DatePicker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import core.StuddyBuddy;
import core.StuddyBuddyRegistration;

public class StuddyBuddyRegistrationControllerTest extends ApplicationTest {

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
        LocalDate date1;
        date1 = LocalDate.of(2022,10,10);
        reg1.setDate(date1);
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
      Thread.sleep(500);
    }

    private void setTimes() throws InterruptedException{
      Thread.sleep(1500);
      clickOn("#startTimeField").write("11:00");
      clickOn("#endTimeField").write("12:00");
    }

    private void setDate() throws InterruptedException {
      Thread.sleep(1500);
      clickOn(((DatePicker)lookup("#datepicker").query()).getEditor()).write("10/10/2022\n");
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
    public void checkSuccessfullRegistration() throws InterruptedException{
        checkRegister();
        assertEquals(successfullRegistration, controller.getMessageText());
    }

    /**
     * Checks that the registration is not successfull when the input
     * in roomField is invalid.
     * @throws InterruptedException
     *
    @Test
    public void testRoom() throws InterruptedException {
      setTimes();
      setDate();
      clickOn("#roomField").write("$");
      //checkRegister();
      Assertions.assertThrows(IllegalArgumentException.class, () -> {
        checkRegister();
      });
      //assertThrows(successfullRegistrat controller.getMessageText());
    }

     /**
     * Checks that the registration is not successfull when the input
     * in courseField is invalid.
     *
    @Test
    public void testCourse() throws InterruptedException {
      setTimes();
      setDate();
      clickOn("#courseField").write("#");
      checkRegister();
      assertNotEquals(successfullRegistration, controller.getMessageText());
    }

     /**
     * Checks that the registration is not successfull when the input
     * in startTimeField is invalid.
     *
    @Test
    public void testStartTime() throws InterruptedException {
      setTimes();
      setDate();
      clickOn("#startTimeField").write("0");
      checkRegister();
      assertNotEquals(successfullRegistration, controller.getMessageText());
    }

     /**
     * Checks that the registration is not successfull when the input
     * in endTimeField is invalid.
     *
    @Test
    public void testEndTime() throws InterruptedException {
      setTimes();
      setDate();
      clickOn("#endTimeField").write("0");
      checkRegister();
      assertNotEquals(successfullRegistration, controller.getMessageText());
    }

    /**
     * Method for checking that exception gets thrown if start time is after end time.
     * 
     * @throws InterruptedException
     *
    @Test
    public void checkStartTimeBeforeEndTime() throws InterruptedException {
      setDate();
      Thread.sleep(1500);
      clickOn("#startTimeField").write("11:00");
      clickOn("#endTimeField").write("10:00");
      checkRegister();
      assertNotEquals(successfullRegistration, controller.getMessageText());
    }

    /**
     * Ckeck that the label feedbackText is not visable
     * when the registration is unsuccessfull.
     *
    @Test
    public void checkUnsuccessfullRegistration() throws InterruptedException {
      checkRegister();
      assertNotEquals(successfullRegistration, controller.getMessageText());
      assertFalse(controller.getFeedbackLabel().isVisible());
    }

    /**
     * Click register-button
     *
    private void checkRegister(){
        clickOn("#register");
    }

    /**
     * Click that the registration is displayed correctly
     *
    @Test
    public void testDisplayRegistration() throws InterruptedException {
      setTimes();
      setDate();
      checkRegister();
      
      if (this.studdyBuddy == null){
        assertEquals("Couldn't register. Try again.", controller.getMessageText());
      }

      else{
        checkSuccessfullRegistration();
        assertEquals(Color.web("#7DDF64"), controller.getMessageTextLabel().getTextFill());
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
    public void testHandleRegistration() throws InterruptedException{
      setTimes();
      setDate();
      if (controller.getFeedbackLabel().isVisible()){
        assertEquals(Color.web("#ED4D6E"), controller.getMessageTextLabel().getTextFill());
      }

      //Check that the registration is correct
      assertEquals("A3-138", controller.getRoom().getText());
      assertEquals("ITP", controller.getCourse().getText());
      assertEquals("11:00", controller.getStartTime().getText());
      assertEquals("12:00", controller.getEndTime().getText());
      assertEquals("10/10/2022", controller.getInputDateString());

      assertEquals(studdyBuddy, registration.getStuddyBuddy());
      assertTrue(studdyBuddy.getRegistrations().contains(registration));

    }
}
*/
