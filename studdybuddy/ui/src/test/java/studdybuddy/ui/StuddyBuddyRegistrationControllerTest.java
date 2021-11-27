package studdybuddy.ui;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.core.StuddyBuddyRegistration;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.dataaccess.DataAccess;
import studdybuddy.dataaccess.DirectDataAccess;

public class StuddyBuddyRegistrationControllerTest extends ApplicationTest {

    private StuddyBuddyRegistrationController controller = new StuddyBuddyRegistrationController();
    private DataAccess dataAccess = new DirectDataAccess();
    private StuddyBuddies buddies;
    private StuddyBuddy buddy;
    private StuddyBuddyRegistration registration;

    /**
     * Set up for the tests. 
     * Clicking on the textFields and filling them with information.
     * 
     * @throws InterruptedException if Thread.sleep() fails
     */
    @BeforeEach
    public void setUpRegistrations() throws InterruptedException{
      buddies = dataAccess.getStuddyBuddies();
      controller.transferData(dataAccess, buddies, buddy);
      controller.setStuddyBuddyFromLogin(generateTestStuddyBuddy());
    }
  

    @Override
    public void start(final Stage stage) throws Exception {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("StuddyBuddyRegistration_test.fxml"));
      final Parent root = loader.load();
      //this.controller.setStuddyBuddyFromLogin(generateTestStuddyBuddy());
      controller = loader.getController();
      stage.setScene(new Scene(root));
      stage.show();
    }

    private void enableDatePicker() {
      controller.getDatePicker().setDisable(false);
    }

    private void setDate(LocalDate date) {
      enableDatePicker();
      ((DatePicker) lookup("#datepicker").query()).setValue(date);
    }

    private void setCourse(String course) {
      registration.setCourse(course);
    }

    /** 
     * Set up for testing StuddyBuddyRegistrationController.java
     * 
     * @return StuddyBuddy-instance for use in testing
     */
    private StuddyBuddy generateTestStuddyBuddy(){
        StuddyBuddy buddy = new StuddyBuddy();
        buddy.setName("Test");
        StuddyBuddyRegistration reg1 = new StuddyBuddyRegistration();
        reg1 = new StuddyBuddyRegistration();
        LocalDate date1;
        date1 = LocalDate.of(2022,10,10);
        setDate(date1);
        reg1.setDate(date1);
        reg1.setRoom("A3-138");
        //reg1.setCourse("ITP");
        reg1.setStartTime("11:00");
        reg1.setEndTime("12:00");
        buddy.addRegistration(reg1);
        reg1.setStuddyBuddy(buddy);
        this.registration = reg1;
        return this.buddy = buddy;
    }


    @Test
    public void testSuccessfullRegistration() throws InterruptedException {
      LocalDate date;
      date = LocalDate.of(2022, 12, 12);
      registration.setDate(date);
      setCourse("ITP");
      String room = "A3-138";
      String course = "ITP";
      String startTime = "11:00";
      String endTime = "12:00";

      Thread.sleep(3000);
      clickOn("#roomField").write(room);
      assertEquals(room, registration.getRoom());
      
      Thread.sleep(3000);
      clickOn("#courseField").write(course);
      assertEquals(course, registration.getCourse());

      Thread.sleep(3000);
      clickOn("#startTimeField").write(startTime);
      assertEquals(startTime, registration.getStartTime());

      Thread.sleep(3000);
      clickOn("#endTimeField").write(endTime);
      assertEquals(endTime, registration.getEndTime());

      List<Window> beforeClick = Window.getWindows();
      Parent beforeClickRoot = null;
      for (Window window : beforeClick) {
      beforeClickRoot = window.getScene().getRoot();
      }

      Thread.sleep(3000);
      clickOn("#register");
      Thread.sleep(3000);
      List<Window> afterClick = Window.getWindows();
      Parent afterClickRoot = null;
      for(Window window : afterClick){
        afterClickRoot = window.getScene().getRoot();
      }
      assertNotEquals(afterClickRoot, beforeClickRoot);
    }

    @Test
    public void testUnsuccessfullRegistration() throws InterruptedException {
      LocalDate date;
      date = LocalDate.of(2022, 12, 12);
      registration.setDate(date);
      setCourse("#ITP");
      String room = "A3-138";
      String course = "#ITP";
      String startTime = "11:00";
      String endTime = "12:00";
      
      Thread.sleep(3000);
      clickOn("#roomField").write(room);
      assertEquals(room, registration.getRoom());
      
      Thread.sleep(3000);
      clickOn("#courseField").write(course);
      assertEquals(course, registration.getCourse());

      Thread.sleep(3000);
      clickOn("#startTimeField").write(startTime);
      assertEquals(startTime, registration.getStartTime());

      Thread.sleep(3000);
      clickOn("#endTimeField").write(endTime);
      assertEquals(endTime, registration.getEndTime());

      List<Window> beforeClick = Window.getWindows();
      Parent beforeClickRoot = null;
      for (Window window : beforeClick) {
      beforeClickRoot = window.getScene().getRoot();
      }

      Thread.sleep(3000);
      clickOn("#register");
      try {
        Thread.sleep(3000);
      } catch (Exception e) {
        fail();
      }
      Thread.sleep(3000);
      
      List<Window> afterClick = Window.getWindows();
      Parent afterClickRoot = null;
      for(Window window : afterClick){
        afterClickRoot = window.getScene().getRoot();
      }
      assertEquals(afterClickRoot, beforeClickRoot);
    }

    @Test
    public void testBackButton() throws InterruptedException {
      List<Window> beforeClick = Window.getWindows();
      Parent beforeClickRoot = null;
      for (Window window : beforeClick) {
      beforeClickRoot = window.getScene().getRoot();
      }

      Thread.sleep(5000);
      clickOn("#backButton");
      Thread.sleep(5000);
      List<Window> afterClick = Window.getWindows();
      Parent afterClickRoot = null;
      for(Window window : afterClick){
        afterClickRoot = window.getScene().getRoot();
      }
      assertNotEquals(afterClickRoot, beforeClickRoot);
    }

    /**private void setTimes() throws InterruptedException{
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
      assertNotNull(this.registration);
    }

    /**
     * Ckeck that the label messageText prints the correct message
     * when the registration is successfull.
     */
    /**@Test
    public void checkSuccessfullRegistration() throws InterruptedException{
        checkRegister();
        //assertEquals(successfullRegistration, controller.getMessageText());
    }

    /**
     * Checks that the registration is not successfull when the input
     * in roomField is invalid.
     * @throws InterruptedException
     */
    /**@Test
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
     */
    /**@Test
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
     */
    /**@Test
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
     */
    /**@Test
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
     */
    /**@Test
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
     */
    /**@Test
    public void checkUnsuccessfullRegistration() throws InterruptedException {
      checkRegister();
      assertNotEquals(successfullRegistration, controller.getMessageText());
      //assertFalse(controller.getFeedbackLabel().isVisible());
    }

    /**
     * Click register-button
     */
    /**private void checkRegister(){
        clickOn("#register");
    }

    /**
     * Click that the registration is displayed correctly
     */
    /**@Test
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
        //assertEquals("-fx-background-color: #C0DF85", controller.getFeedbackLabel().getStyle());
        //assertTrue(controller.getFeedbackLabel().isVisible());
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
      //if (controller.getFeedbackLabel().isVisible()){
        //assertEquals(Color.web("#ED4D6E"), controller.getMessageTextLabel().getTextFill());
      //}

      //Check that the registration is correct
      assertEquals("A3-138", controller.getRoom().getText());
      assertEquals("ITP", controller.getCourse().getText());
      assertEquals("11:00", controller.getStartTime().getText());
      assertEquals("12:00", controller.getEndTime().getText());
      assertEquals("10/10/2022", controller.getInputDateString());

      assertEquals(studdyBuddy, registration.getStuddyBuddy());
      assertTrue(studdyBuddy.getRegistrations().contains(registration));

    }*/
}