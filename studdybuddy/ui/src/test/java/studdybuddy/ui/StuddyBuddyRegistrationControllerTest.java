package studdybuddy.ui;

import org.junit.jupiter.api.BeforeEach;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.core.StuddyBuddyRegistration;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.dataaccess.DataAccess;
import studdybuddy.dataaccess.DirectDataAccess;

public class StuddyBuddyRegistrationControllerTest extends ApplicationTest {

    private StuddyBuddyRegistrationController controller = new StuddyBuddyRegistrationController();
    private DataAccess dataAccess = new DirectDataAccess();
    private StuddyBuddy buddy = new StuddyBuddy();;
    private StuddyBuddies buddies;
    private StuddyBuddyRegistration registration;

    /**
     * Set up for the tests. 
     * Clicking on the textFields and filling them with information.
     * 
     * @throws InterruptedException if Thread.sleep() fails
     */
    @BeforeEach
    public void setUpController() throws InterruptedException {
      buddies = dataAccess.getStuddyBuddies();
      controller.transferData(dataAccess, buddies, buddy);
      buddy.setName("Name");
      buddy.setPassword("Password");
      registration = new StuddyBuddyRegistration();
    }
  

    @Override
    public void start(final Stage stage) throws Exception {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("StuddyBuddyRegistration_test.fxml"));
      final Parent root = loader.load();
      controller = loader.getController();
      stage.setScene(new Scene(root));
      stage.show();
    }


    /**
     * Test that a valid registration is added to registrations.
     * 
     * @throws InterruptedException if the thread is unterrupted.
     */
    @Test
    public void testSuccessfullRegistration() throws InterruptedException {
      controller.getDatePicker().setDisable(false);
      registration.setDate(LocalDate.of(2022, 12, 12));
      ((DatePicker) lookup("#datepicker").query()).setValue(LocalDate.of(2022, 12, 12));
      registration.setRoom("A3-138");
      clickOn("#roomField").write("A3-138");
      Thread.sleep(3000);
      registration.setCourse("ITP");
      clickOn("#courseField").write("ITP");
      Thread.sleep(3000);
      registration.setStartTime("11:00");
      clickOn("#startTimeField").write("11:00");
      Thread.sleep(3000);
      registration.setEndTime("12:00");
      clickOn("#endTimeField").write("12:00");
      Thread.sleep(3000);
      
      clickOn("#register");
      Thread.sleep(3000);

      StuddyBuddyRegistration registeredRegistration = buddy.getRegistrations().get(0);

      assertEquals(registeredRegistration.getRoom(), registration.getRoom());
      assertEquals(registeredRegistration.getCourse(), registration.getCourse());
      assertEquals(registeredRegistration.getStartTime(), registration.getStartTime());
      assertEquals(registeredRegistration.getEndTime(), registration.getEndTime());
    }

    /**
     * Test that an invalid registration is not added to registrations.
     *
     * @throws InterruptedException if the thread is unterrupted.
     */
    @Test
    public void testUnsuccessfullRegistration() throws InterruptedException {
      Thread.sleep(3000);
      clickOn("#roomField").write("Room");
      Thread.sleep(3000);
      clickOn("#courseField").write("Course");
      Thread.sleep(3000);
      clickOn("#startTimeField").write("12:00");
      Thread.sleep(3000);
      clickOn("#endTimeField").write("14:00");
      Thread.sleep(3000);
      clickOn("#register");

      assertThrows(IndexOutOfBoundsException.class, () -> {buddy.getRegistrations().get(0);});
    }

}