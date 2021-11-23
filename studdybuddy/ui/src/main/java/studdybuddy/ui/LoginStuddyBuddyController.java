package studdybuddy.ui;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.dataaccess.DataAccess;

/**
 * Controller class for Login to a StuddyBuddy account.
 */
public class LoginStuddyBuddyController {

    private DataAccess dataAccess;
    private StuddyBuddy buddy;
    private StuddyBuddies buddies;

    @FXML
    private TextField nameField;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField passwordField;

    @FXML
    private RegisterStuddyBuddyController registerViewController;

    public void setDataAccess(DataAccess access) {
        this.dataAccess = access;
    }

    /**
     * Sets the name to be the input in nameField the name can only consist of letters and space.
     *
     * @return the name from input
     */
    @FXML
    public String getInputName() {
        String nameString = nameField.getText();
        return nameString;
    }

    /**
     * Method for transering dataAccess and studdyBuddies between classes.
     * Is used in the class that opens an FXML that uses this controller.
     *
     * @param dataAccess dataAccess for the run of the application
     * @param buddies studdyBuddies for the run of the application
     */
    public void transferData(DataAccess dataAccess, StuddyBuddies buddies) {
        this.dataAccess = dataAccess;
        this.buddies = buddies;
    }

    @FXML
    public String getInputPassword() {
        String passwordString = passwordField.getText();
        return passwordString;
    }

    public boolean checkIfUserExist() {
        try {
            dataAccess.getStuddyBuddyByName(getInputName(), buddies);
        } catch( RuntimeException e) {
            return false;
        }
        return true;
    }

    public boolean checkPasswordsMacthes() {
        return getInputPassword().equals(dataAccess.getStuddyBuddyPasswordByName(getInputName(), buddies));
    }

    public void setStuddyBuddyFromServer() {
        buddy = dataAccess.getStuddyBuddyByName(getInputName(), buddies);
    }

    public StuddyBuddy getStuddyBuddy() {
        return buddy;
    }

    /**
     * Sends the username and password to the next controller and loads a new window
     */
    @FXML
    public void handleLogin() {

        try {

            URL fxmlFile = getClass().getResource("StuddyBuddyForum.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlFile);
            Parent parent = (Parent) loader.load();

            checkIfUserExist();
            checkPasswordsMacthes();

            if (!checkIfUserExist()) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                errorMessage.setText("User does not exist.");

            }

            else if (!checkPasswordsMacthes()) {
                nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                nameField.setText(getInputName());
            }

            else {
                setStuddyBuddyFromServer();
                nameField.clear();
                passwordField.clear();

                Stage buddiesStage = new Stage();
                buddiesStage.setTitle("StuddyBuddies");
                buddiesStage.setScene(new Scene(parent));
                buddiesStage.show();
                Stage thisStage = (Stage) nameField.getScene().getWindow();
                thisStage.close();
            }

        } catch (IOException e) {
            errorMessage.setText("Could not Log in. Try again.");
            e.printStackTrace();
        }
    }

  /**
   * Method for redirecting back to the welcome page.
   */
  @FXML
  public void handleBack() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RemoteApp.fxml"));
        Parent parent = (Parent) loader.load();
        Stage welcomeStage = new Stage();
        welcomeStage.setScene(new Scene(parent));
        welcomeStage.setTitle("StuddyBuddy");
        welcomeStage.show();
        Stage thisStage = (Stage) nameField.getScene().getWindow();
        thisStage.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
