package studdybuddy.ui;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.core.StuddyBuddyRegistration;
import studdybuddy.dataaccess.DataAccess;

/**
 * Controller class for a registration.
 */
public class StuddyBuddyRegistrationController {
  
  private StuddyBuddy buddy;
  private StuddyBuddies buddies;
  private DataAccess dataAccess;

  @FXML
  private DatePicker datepicker;

  @FXML
  private TextField roomField;

  @FXML
  private TextField courseField;

  @FXML
  private TextField startTimeField;

  @FXML
  private TextField endTimeField;

  public void initialize() {
    datepicker.getEditor().setDisable(true);
  }

  public void setStuddyBuddyFromLogin(StuddyBuddy studdyBuddy) {
    this.buddy = studdyBuddy;
  }

  /**
   * sets the date to a registration.
   */
  public void setDateFromInput(StuddyBuddyRegistration registration) {
    try {
      registration.setDate(datepicker.getValue());
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      throw new IllegalStateException("Invalid date.");
    }
  }

  public DatePicker getDatePicker() {
    return datepicker;
  }

  /**
   * Getter for InputDateString.
   *
   * @return the input date as String
   */
  public String getInputDateString(StuddyBuddyRegistration registration) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    String formattedString = datepicker.getValue().format(formatter);
    return formattedString;
  }

  /**
   * sets the room to be the input in roomField.
   */
  public void setRoomFromInput(StuddyBuddyRegistration registration) {
    try {
      registration.setRoom(roomField.getText());
      roomField.setStyle("-fx-border-color: grey;");
    } catch (IllegalArgumentException e) {
      roomField.setStyle("-fx-border-color: red;");
      roomField.clear();
      throw new IllegalStateException("Invalid room.");
    }
  }

  /**
   * sets the course to be the input in courseField.
   */
  public void setCourseFromInput(StuddyBuddyRegistration registration) {
    try {
      registration.setCourse(courseField.getText());
      courseField.setStyle("-fx-border-color: grey;");
    } catch (IllegalArgumentException e) {
      courseField.setStyle("-fx-border-color: red;");
      courseField.clear();
      throw new IllegalStateException("Invalid course.");
    }
  }

  /**
   * sets the start time to be the input in startTimeField.
   */
  @FXML
  public void setStartTimeFromInput(StuddyBuddyRegistration registration) {
    try {
      registration.setStartTime(startTimeField.getText());
      startTimeField.setStyle("-fx-border-color: grey;");
    } catch (IllegalArgumentException e) {
      startTimeField.setStyle("-fx-border-color: red;");
      startTimeField.clear();
      throw new IllegalStateException("Invalid start time.");
    }
  }

  /**
   * sets the end time to be the input in endTimeField.
   */
  @FXML
  public void setEndTimeFromInput(StuddyBuddyRegistration registration) {
    try {
      registration.setEndTime(endTimeField.getText());
      endTimeField.setStyle("-fx-border-color: grey;");
    } catch (IllegalArgumentException e) {
      endTimeField.setStyle("-fx-border-color: red;");
      endTimeField.clear();;
      throw new IllegalStateException("Invalid end time.");
    }
  }

  /**
   * register a new StuddyBuddy sets the room, course, start time, end time and date.
   */
  private boolean registerStuddyBuddy() {
    StuddyBuddyRegistration registration = new StuddyBuddyRegistration();
    try {
      setRoomFromInput(registration);
      setCourseFromInput(registration);
      setStartTimeFromInput(registration);
      setEndTimeFromInput(registration);
      setDateFromInput(registration);
      buddy.addRegistration(registration);
      return true;
    } catch (IllegalStateException e) {
      return false;
    }

  }
  
  /**
  * Method for transering dataAccess between classes.
  * Is used in the class that opens an FXML that uses this controller.
  */
  public void transferData(DataAccess dataAccess, StuddyBuddies buddies, StuddyBuddy buddy) {
    this.dataAccess = dataAccess;
    this.buddies = buddies;
    this.buddy = buddy;
  }

  /**
   * Sets the feedback text to not be visable and to have Paradise Pink color saves this .
   * registration to file registration was successful sets message to be visable 
   * sets feedback to be visable if registration was successful sets message
   * text to be Amazon color if registration was successful sets feedback
   * text to have Yellow Green Crayola color if registration was successful clears the
   * texfields if registration was successful
   */
  @FXML
  public void handleRegister() {
    if (registerStuddyBuddy()) {
      dataAccess.postStuddyBuddy(buddy);
      try {
        URL fxmlFile = getClass().getResource("StuddyBuddies.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlFile);
        Parent parent = (Parent) loader.load();
        StuddyBuddiesController buddiesController = loader.getController();
        buddiesController.transferData(dataAccess, buddies, buddy);
        Stage registrationStage = new Stage();
        registrationStage.setTitle("Forum");
        registrationStage.setScene(new Scene(parent));
        registrationStage.show();
        Stage thisStage = (Stage) datepicker.getScene().getWindow();
        thisStage.close(); 
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Method for redirecting back to the welcome page.
   */
  @FXML
  public void handleBack() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("StuddyBuddies.fxml"));
      Parent parent = (Parent) loader.load();
      StuddyBuddiesController buddiesController = loader.getController();
      buddiesController.transferData(dataAccess, buddies, buddy);
      Stage buddiesStage = new Stage();
      buddiesStage.setScene(new Scene(parent));
      buddiesStage.show();
      Stage thisStage = (Stage) datepicker.getScene().getWindow();
      thisStage.close(); 
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}