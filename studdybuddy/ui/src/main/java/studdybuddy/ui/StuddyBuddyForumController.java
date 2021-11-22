package studdybuddy.ui;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.core.StuddyBuddyRegistration;
import studdybuddy.json.StuddyBuddiesPersistence;


/**
 * Controller class for the forum page.
 */
public class StuddyBuddyForumController {

  private StuddyBuddiesPersistence persistence = new StuddyBuddiesPersistence();
  private String registrationsFileName = "/registrations.json";

  @FXML
  private ImageView studdyBuddyLogo;

  @FXML
  private ImageView userIcon;

  @FXML
  private Label username;

  @FXML
  private Button logOut;

  @FXML
  private Button addRegistrationButton;

  @FXML
  private Label studdyBuddyUser;

  @FXML
  private Label registrationsTitle;

  @FXML
  private Label allRegistrationsText;

  public void initialize() {
    display();
  }

  /**
   * Method for handling the add AddRegistration Button.
   */
  @FXML
  public void handleAddRegistration() {
    try {

      URL fxmlFile = getClass().getResource("StuddyBuddyRegistration.fxml");
      FXMLLoader loader = new FXMLLoader(fxmlFile);
      Parent parent = (Parent) loader.load();
      Stage registrationStage = new Stage();
      registrationStage.setTitle("Registration");
      registrationStage.setScene(new Scene(parent));
      registrationStage.show();
      Stage thisStage = (Stage) username.getScene().getWindow();
      thisStage.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method for handling the LogOut button.
   */
  @FXML
  public void handleLogOut() {
    try {

      URL fxmlFile = getClass().getResource("StuddyBuddy.fxml");
      FXMLLoader loader = new FXMLLoader(fxmlFile);
      Parent parent = (Parent) loader.load();
      Stage registrationStage = new Stage();
      registrationStage.setTitle("StuddyBuddy");
      registrationStage.setScene(new Scene(parent));
      registrationStage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * getter for registered studdyBuddies.
   *
   * @return the studdyBuddies that have been registered.
   */
  public StuddyBuddies getRegistratedStuddyBuddies() {
    StuddyBuddies registeredBuddies = null;
    try (Reader reader =
        new FileReader(System.getProperty("user.home") 
        + registrationsFileName, StandardCharsets.UTF_8)) {
      registeredBuddies = persistence.readStuddyBuddies(reader);
    } catch (Exception e) {
      System.err.println("Couldn´t read from file");
      e.printStackTrace();
    }
    return registeredBuddies;
  }

  /**
   * public void displayRegistrations() { StuddyBuddies registeredBuddies =
   * getRegistratedStuddyBuddies(); if (registeredBuddies != null){
   * allRegistrationsText.setText(registeredBuddies.getStuddyBuddies().toString()); } }
   */

  private StuddyBuddies buddies = new StuddyBuddies();

  /**
   * Method to set ut the studdyBuddies that will be displayed on the forum-page.
   */
  private void setUpBuddies() {

    StuddyBuddy buddy1 = new StuddyBuddy();
    StuddyBuddy buddy2 = new StuddyBuddy();
    StuddyBuddy buddy3 = new StuddyBuddy();
    StuddyBuddy buddy4 = new StuddyBuddy();

    StuddyBuddyRegistration reg1 = new StuddyBuddyRegistration();
    StuddyBuddyRegistration reg2 = new StuddyBuddyRegistration();
    StuddyBuddyRegistration reg3 = new StuddyBuddyRegistration();
    StuddyBuddyRegistration reg4 = new StuddyBuddyRegistration();

    LocalDate date1 = LocalDate.of(2021, 11, 11);
    LocalDate date2 = LocalDate.of(2021, 11, 11);
    LocalDate date3 = LocalDate.of(2021, 11, 11);
    LocalDate date4 = LocalDate.of(2021, 11, 11);

    buddy1.setName("Selma");
    buddy1.setPassword("Passord123");
    reg1.setDate(date1);
    reg1.setRoom("A3-138");
    reg1.setCourse("ITP");
    reg1.setStartTime("08:15");
    reg1.setEndTime("16:00");
    buddy1.addRegistration(reg1);

    buddy2.setName("Anette");
    buddy2.setPassword("Passord123");
    reg2.setDate(date2);
    reg2.setRoom("A3-138");
    reg2.setCourse("Statistics");
    reg2.setStartTime("08:15");
    reg2.setEndTime("16:00");
    buddy2.addRegistration(reg2);

    buddy3.setName("Tuva");
    buddy3.setPassword("Passord123");
    reg3.setDate(date3);
    reg3.setRoom("A3-138");
    reg3.setCourse("Statistics");
    reg3.setStartTime("08:15");
    reg3.setEndTime("16:00");
    buddy3.addRegistration(reg3);

    buddy4.setName("Ingebjørg");
    buddy4.setPassword("Passord123");
    reg4.setDate(date4);
    reg4.setRoom("A3-138");
    reg4.setCourse("ITP");
    reg4.setStartTime("08:15");
    reg4.setEndTime("16:00");
    buddy4.addRegistration(reg4);

    buddies.addStuddyBuddies(buddy1, buddy2, buddy3, buddy4);

  }

  @FXML
  public void display() {
    setUpBuddies();
    allRegistrationsText.setText(buddies.toString());
  }
}
