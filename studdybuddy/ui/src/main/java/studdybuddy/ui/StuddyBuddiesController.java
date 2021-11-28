package studdybuddy.ui;

import java.io.IOException;
import java.net.URL;
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
import studdybuddy.dataaccess.DataAccess;

/**
 * Controller for studdyBuddies page.
 */
public class StuddyBuddiesController {

  private DataAccess dataAccess;
  private StuddyBuddies buddies;
  private StuddyBuddy buddy;

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
  private Label allRegistrationsText;


  /**
   * Method for transering dataAccess and studdyBuddies between classes. Is used in the class that
   * opens an FXML that uses this controller.
   *
   * @param dataAccess dataAccess for the run of the application
   *
   * @param buddies studdyBuddies for the run of the application
   */
  public void transferData(DataAccess dataAccess, StuddyBuddies buddies, StuddyBuddy buddy) {
    this.dataAccess = dataAccess;
    this.buddies = buddies;
    this.buddy = buddy;
    display();
  }

  /**
   * Method for click on Add registration button.
   * Redirects to register page.
   */
  @FXML
  public void handleAddRegistration() {
    try {
      URL fxmlFile = getClass().getResource("StuddyBuddyRegistration.fxml");
      FXMLLoader loader = new FXMLLoader(fxmlFile);
      Parent parent = (Parent) loader.load();
      StuddyBuddyRegistrationController registrationController = loader.getController();
      registrationController.transferData(dataAccess, buddies, buddy);
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
   * Method for click on logout button.
   * Redirects to register page.
   */
  @FXML
  public void handleLogOut() {
    try {
      URL fxmlFile = getClass().getResource("StuddyBuddy.fxml");;
      FXMLLoader loader = new FXMLLoader(fxmlFile);
      Parent parent = (Parent) loader.load();
      Stage welcomeStage = new Stage();
      welcomeStage.setTitle("StuddyBuddy");
      welcomeStage.setScene(new Scene(parent));
      welcomeStage.show();
      Stage thisStage = (Stage) studdyBuddyLogo.getScene().getWindow();
      thisStage.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method for displaying buddies and registrations.
   */
  @FXML
  public void display() {
    username.setText(buddy.getName());
    allRegistrationsText.setText(dataAccess.getStuddyBuddies().toString());
  }

}
