package studdybuddy.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.dataaccess.DataAccess;
import studdybuddy.dataaccess.DirectDataAccess;
import studdybuddy.dataaccess.RemoteDataAccess;
import studdybuddy.json.StuddyBuddiesPersistence;

/**
 * AppController class.
 */
public class AppController {

  @FXML
  private String userStuddyBuddyPath;

  @FXML
  private String endpointUri;

  @FXML
  private Button loginButton;

  @FXML
  private Button registerButton;

  private StuddyBuddiesPersistence persistence;

  private DataAccess dataAccess;

  private StuddyBuddies buddies;

  /**
   * Method for initializing.
   */
  @FXML
  public void initialize() {
    buddies = new StuddyBuddies();
    try{
      dataAccess = new RemoteDataAccess(new URI(endpointUri));
      dataAccess.getStuddyBuddies();
      System.out.println("Using remote saving with endpointURI: " + endpointUri);
    } catch (RuntimeException | URISyntaxException  e) {
      System.out.println("Could not connect to server. Using local saving.");
      this.persistence = new StuddyBuddiesPersistence();
      persistence.setSaveFilePath(userStuddyBuddyPath);
      DirectDataAccess directAccess = new DirectDataAccess();
      directAccess.setPersistence(persistence);
      dataAccess = directAccess;
    }
  }

  /**
   * Method for click on register button.
   * Redirects to register page.
   */
  @FXML
  public void handleRegister() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterStuddyBuddy.fxml"));
      Parent parent = (Parent) loader.load();
      RegisterStuddyBuddyController registerController = loader.getController();
      registerController.transferData(dataAccess, buddies);
      Stage registrationStage = new Stage();
      registrationStage.setTitle("Register");
      registrationStage.setScene(new Scene(parent));
      registrationStage.show();
      Stage thisStage = (Stage) registerButton.getScene().getWindow();
      thisStage.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method for click on login button.
   * Redirects to login page.
   */
  @FXML
  public void handleLogin() {
    try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginStuddyBuddy.fxml"));
    Parent parent = (Parent) loader.load();
    LoginStuddyBuddyController loginController = loader.getController();
    loginController.transferData(dataAccess, buddies);
    Stage loginStage = new Stage();
    loginStage.setTitle("Login");
    loginStage.setScene(new Scene(parent));
    loginStage.show();
    Stage thisStage = (Stage) loginButton.getScene().getWindow();
    thisStage.close(); 
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}
