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
import studdybuddy.core.StuddyBuddyValidation;
import studdybuddy.dataaccess.DataAccess;
import studdybuddy.dataaccess.RemoteDataAccess;

/**
 * Controller class for Login to a StuddyBuddy account.
 */
public class LoginStuddyBuddyController {

    private DataAccess dataAccess;
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

    /**
     * Method that sets the password to be the input from passwordField, and can only consist of letters
     * from the english alphabet(so can not use æ,ø,å) and digits
     * 
     * @return the password from input
     */
    @FXML
    public String getInputPassword() {
        String passwordString = passwordField.getText();
        return passwordString;
    }

    /**
     * Method that checks if user exist in server, and return true if it exists, else false
     * 
     * @return true if user exists
     */
    public boolean userExists() {
        StuddyBuddy buddy = dataAccess.getStuddyBuddyByName(getInputName());
        if(StuddyBuddyValidation.checkNotNullorEmpty(buddy.toString())) {
            return true;
        }
        return false;
    }

    /**
     * Method that checks if input password mathces the acutal password of the user by its username
     * 
     * @return true if passwords match, else false
     */
    public boolean passwordIsCorrect() {
        return getInputPassword().equals(dataAccess.getStuddyBuddyPasswordByName(getInputName()));
    }

    /**
     * Opens the studdyBuddies page if the username and password is correct.
     */
    @FXML
    public void handleLogin() {

        if (!userExists()) {
            nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            errorMessage.setText("User does not exist.\nGo back and register.");
        }

        else if (!passwordIsCorrect()) {
            nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
            passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            nameField.setText(getInputName());
            errorMessage.setText("Wrong password.");
        }

        else {
            try {
                URL fxmlFile = getClass().getResource("StuddyBuddies.fxml");;
                FXMLLoader loader = new FXMLLoader(fxmlFile);
                Parent parent = (Parent) loader.load();
                StuddyBuddiesController buddiesController = loader.getController();
                buddiesController.transferData(dataAccess, buddies, dataAccess.getStuddyBuddyByName(getInputName()));
                Stage buddiesStage = new Stage();
                buddiesStage.setTitle("StuddyBuddies");
                buddiesStage.setScene(new Scene(parent));
                buddiesStage.show();
                Stage thisStage = (Stage) nameField.getScene().getWindow();
                thisStage.close();
            } catch (IOException e) {
                errorMessage.setText("Could not open StuddyBuddies page.");
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
