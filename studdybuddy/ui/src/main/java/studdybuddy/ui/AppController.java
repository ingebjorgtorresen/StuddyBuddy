package studdybuddy.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.dataaccess.DataAccess;
import studdybuddy.dataaccess.DirectDataAccess;
import studdybuddy.dataaccess.RemoteDataAccess;
import studdybuddy.json.StuddyBuddiesPersistence;

/**
 * AppController class.
 */
public class AppController {

  @FXML
  String userStuddyBuddyPath;

  @FXML
  String endpointUri;

  @FXML
  String sampleStuddyBuddyResource;

  @FXML
  private Button getStartedButton;

  private StuddyBuddiesPersistence persistence;

  private DataAccess dataAccess;

  private StuddyBuddies buddies;

  /**
   * Method for initializing.
   */
  @FXML
  public void initialize() {
    buddies = new StuddyBuddies();
    if (endpointUri != null) {
      try {
        System.out.println("Using remote endpoint @ " + endpointUri);
        dataAccess = new RemoteDataAccess(new URI(endpointUri));
      } catch (URISyntaxException e) {
        System.err.println(e);
      }
    }
    if (dataAccess == null) {
      this.persistence = new StuddyBuddiesPersistence();
      persistence.setSaveFilePath(userStuddyBuddyPath);
      DirectDataAccess directAccess = new DirectDataAccess();
      directAccess.setPersistence(persistence);
      dataAccess = directAccess;
    }
  }

  /**
   * method for handling GetStarted button.
   */
  @FXML
  public void handleGetStarted() {
    try {
      // Bytter til registreringssiden, hvertfall foreløpig
      FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterStuddyBuddy.fxml"));
      Parent parent = (Parent) loader.load();
      RegisterStuddyBuddyController registerController = loader.getController();
      registerController.transferData(dataAccess, buddies);
      Stage registrationStage = new Stage();
      registrationStage.setTitle("Register buddy");
      registrationStage.setScene(new Scene(parent));
      registrationStage.show();
      Stage thisStage = (Stage) getStartedButton.getScene().getWindow();
      thisStage.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
