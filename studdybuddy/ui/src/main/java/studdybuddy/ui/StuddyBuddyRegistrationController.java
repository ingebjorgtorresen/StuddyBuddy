package studdybuddy.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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

  @FXML
  private Label messageText;

  @FXML
  private Label feedbackText;

  public void initialize() {
    datepicker.getEditor().setDisable(true);
    createRegistration();
  }

  /**
   * Method that creates a new registration if the registration is not null.
   * The pane is cleared meassage text is set to not be visable and in Paradise Pink color.
   */
  private void createRegistration() {
    messageText.setVisible(false);
    messageText.setTextFill(Color.web("#ED4D6E"));
  }

  public void setStuddyBuddyFromLogin(StuddyBuddy studdyBuddy) {
    this.buddy = studdyBuddy;
  }

  /**
   * Getter for the inputDate.
   *
   * @return the date from input
   */
  @FXML
  public LocalDate getInputDate() {
    return datepicker.getValue();
  }

  /**
   * sets the date to a registration.
   */
  public void setDateFromInput(StuddyBuddyRegistration registration) {
    try {
      registration.setDate(getInputDate());
    } catch (IllegalArgumentException e) {
      messageText.setText("Use the calender to choose date!");
      messageText.setVisible(true);
    }
  }

  /**
   * Getter for InputDateString.
   *
   * @return the input date as String
   */
  public String getInputDateString(StuddyBuddyRegistration registration) {
    // String dateString = getInputDate().toString();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    String formattedString = getInputDate().format(formatter);
    return formattedString;
  }

  /**
   * Getter for the inputRoom.
   * the room can only consist of letters, "-" and space.
   *
   * @return the room from input
   */
  @FXML
  public String getInputRoom() {
    return roomField.getText();
  }

  /**
   * sets the room to be the input in roomField.
   */
  public void setRoomFromInput(StuddyBuddyRegistration registration) {
    try {
      registration.setRoom(getInputRoom());
    } catch (IllegalArgumentException e) {
      messageText
          .setText("Can not use other characters \nthan letters," 
          + "digits, '-' and \n' '. You wrote: " + getInputRoom());
      messageText.setVisible(true);
    }
  }

  /**
   * the room can only consist of letters, "-" and space.
   *
   * @return the course from input
   */
  @FXML
  public String getInputCourse() {
    return courseField.getText();
  }

  /**
   * sets the course to be the input in courseField.
   */
  public void setCourseFromInput(StuddyBuddyRegistration registration) {
    try {
      registration.setCourse(getInputCourse());
    } catch (IllegalArgumentException e) {
      messageText.setText(
          "Can not use other characters \nthan letters, digits, '-' and \n' '. You wrote: " 
          + getInputCourse());
      messageText.setVisible(true);
    }
  }

  /**
   * the start time must be on format 'HH:mm'.
   *
   * @return the start time from input
   */
  @FXML
  public String getInputStartTime() {
    return startTimeField.getText();
  }

  /**
   * sets the start time to be the input in startTimeField.
   */
  @FXML
  public void setStartTimeFromInput(StuddyBuddyRegistration registration) {
    try {
      registration.setStartTime(getInputStartTime());
    } catch (IllegalArgumentException e) {
      messageText.setText("Starttime must be on format \n'HH:mm' ");
      messageText.setVisible(true);
    }
  }

  /**
   * the end time must be on format 'HH:mm' and after StartTime.
   *
   * @return the end time from input
   */
  @FXML
  public String getInputEndTime() {
    return endTimeField.getText();
  }

  /**
   * sets the end time to be the input in endTimeField.
   */
  @FXML
  public void setEndTimeFromInput(StuddyBuddyRegistration registration) {
    try {
      registration.setEndTime(getInputEndTime());
    } catch (IllegalArgumentException e) {
      messageText.setText("EndTime must be on format \n'HH:mm' and after StartTime");
      messageText.setVisible(true);
    }
  }

  /**
   * Getter for the date.
   *
   * @return datepicker
   */
  public DatePicker getDate() {
    return datepicker;
  }

  /**
   * Getter for the room.
   *
   * @return roomField
   */
  public TextField getRoom() {
    return roomField;
  }

  /**
   * Getter for the course.
   *
   * @return courseField
   */
  public TextField getCourse() {
    return courseField;
  }

  /**
   * Getter for the startTime.
   *
   * @return startTimeField
   */
  public TextField getStartTime() {
    return startTimeField;
  }

  /**
   * Getter for the endTime.
   *
   * @return endTimeField
   */
  public TextField getEndTime() {
    return endTimeField;
  }

  /**
   * Get the text printed from the label messageText.
   *
   * @return messageText from label
   */
  public String getMessageText() {
    return messageText.getText();
  }

  /**
   * Get the label messageText.
   *
   * @return the label messageText
   */
  public Label getMessageTextLabel() {
    return messageText;
  }

  /**
   * Get the label feedbackText.
   *
   * @return the label feedbackText
   */
  public Label getFeedbackLabel() {
    return feedbackText;
  }

  /**
   * register a new StuddyBuddy sets the room, course, start time, end time and date.
   */
  private void registerStuddyBuddy() {
    StuddyBuddyRegistration registration = new StuddyBuddyRegistration();
    setRoomFromInput(registration);
    setCourseFromInput(registration);
    setStartTimeFromInput(registration);
    setEndTimeFromInput(registration);
    setDateFromInput(registration);
    buddy.addRegistration(registration);
  }
  
  /**
  * Method for transering dataAccess between classes.
  * Is used in the class that opens an FXML that uses this controller.
  */
    public void transferData(DataAccess dataAccess, StuddyBuddies buddies) {
      this.dataAccess = dataAccess;
      this.buddies = buddies;
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
    registerStuddyBuddy();
    dataAccess.putStuddyBuddy(buddy, buddies);

    try {
      URL fxmlFile = getClass().getResource("StuddyBuddies.fxml");
      FXMLLoader loader = new FXMLLoader(fxmlFile);
      Parent parent = (Parent) loader.load();
      StuddyBuddiesController buddiesController = loader.getController();
      System.out.println("Den kjører handle register i registration controller");
      buddiesController.transferData(dataAccess, buddies);
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
