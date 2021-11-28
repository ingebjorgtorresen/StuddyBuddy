package studdybuddy.ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.core.StuddyBuddyValidation;
import studdybuddy.dataaccess.DataAccess;

/**
 * Controller class for registering new StuddyBuddy/user.
 */
public class RegisterStuddyBuddyController {

  @FXML
  private TextField nameField;

  @FXML
  private TextField passwordField;

  @FXML
  private TextField passwordCheckField;

  @FXML
  private Label messageBox;

  private StuddyBuddy buddy;

  private StuddyBuddies buddies;

  private DataAccess dataAccess;

  /**
   * Method for getting name in nameField.
   *
   * @return name
   */
  @FXML
  public String getInputName() {
    String nameString = nameField.getText();
    return nameString;
  }

  /**
   * Method for getting password in passwordCheckField.
   *
   * @return password
   */
  @FXML
  public String getInputPasswordCheck() {
    String passwordString = passwordCheckField.getText();
    return passwordString;
  }

  /**
   * Method for getting password in passwordField.
   *
   * @return password
   */
  @FXML
  public String getInputPassword() {
    String passwordString = passwordField.getText();
    return passwordString;
  }

  /**
   * Method for checking input name not null.
   *
   * @return true if name is not null, else false
   */
  private boolean checkNotNull() {
    try {
      StuddyBuddyValidation.checkNotNullorEmpty(getInputName());
    } catch (IllegalArgumentException e) {
      return false;
    }
    return true;
  }

  /**
   * Method for checking format of input name.
   *
   * @return true if format is correct, else false
   */
  private boolean checkInputName() {
    return StuddyBuddyValidation.checkName(getInputName());
  }

  /**
   * Method for checking format of input password.
   *
   * @return true if format is correct, else false
   */
  private boolean checkInputPassword() {
    return StuddyBuddyValidation.checkPassword(getInputPassword());
  }

  /**
   * Method for checking if buddy exists.
   *
   * @return true if buddy exists, else false
   */
  private boolean checkBuddyExists() {
    return StuddyBuddyValidation.buddyExists(dataAccess.getStuddyBuddies(), getInputName());
  }

  /**
   * Method for checking if the name has the correct format.
   *
   * @return true if it is correct, else false
   * 
   */
  public boolean checkName() {
    if ((checkNotNull() == false) || (checkInputName() == false)) {
      return false;
    }
    return true;
  }

  /**
   * Method for checking if the password has the correct format.
   *
   * @return true if it is correct, else false
   * 
   */
  public boolean checkPassword() {
    if ((checkNotNull() == false) || (checkInputPassword() == false)) {
      return false;
    }
    return true;
  }


  /**
   * Method for checking if the password- and checkpassword-textfields has the same input.
   *
   * @return true if passwords match, else false
   */
  public boolean checkPasswordsMatch() {
    if (!(getInputPassword().equals(getInputPasswordCheck()))) {
      return false;
    }
    return true;
  }

  /**
   * Method for creating new StuddyBuddy.
   *
   * @return studdybuddy
   */
  private StuddyBuddy createNewStuddyBuddy() {
    buddy = new StuddyBuddy();
    buddy.setName(getInputName());
    buddy.setPassword(getInputPassword());
    return buddy;
  }
  
  /**
   * Method for click on register button.
   *
   * @param event the event
   * @throws IOException if it is wrong
   */
  @FXML
  public void handleRegister(ActionEvent event) throws IOException {
    messageBox.setText("");

    if (checkBuddyExists()) {
      nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      passwordField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
      passwordCheckField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
      passwordField.setText(getInputPassword());
      passwordCheckField.setText(getInputPasswordCheck());
      messageBox.setText("User already exists. \nChoose another username.");
    
    } else if (((!checkName()) && (!checkPassword()) && (!checkPasswordsMatch()))) {
      nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");

    } else if ((!checkName()) && (!checkPassword())) {
      nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");

    } else if ((!checkName()) && (!checkPasswordsMatch())) {
      nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");

    } else if (!(checkName())) {
      nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      passwordCheckField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
      passwordField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
      passwordField.setText(getInputPassword());
      passwordCheckField.setText(getInputPasswordCheck());

    } else if (!(checkPassword())) {
      passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
      nameField.setText(getInputName());

    } else if (!(checkPasswordsMatch())) {
      passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
      nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
      nameField.setText(getInputName());

    } else {
      passwordField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
      passwordCheckField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
      nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
      StuddyBuddy buddy = createNewStuddyBuddy();
      buddies.addStuddyBuddy(buddy);
      dataAccess.putStuddyBuddy(buddy);

      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StuddyBuddies.fxml"));
        Parent parent = (Parent) loader.load();
        StuddyBuddiesController buddiesController = loader.getController();
        buddiesController.transferData(dataAccess, buddies, buddy);
        Stage buddiesStage = new Stage();
        buddiesStage.setScene(new Scene(parent));
        buddiesStage.setTitle("StuddyBuddies");
        buddiesStage.show();
        Stage thisStage = (Stage) nameField.getScene().getWindow();
        thisStage.close(); 
      } catch (IOException | NullPointerException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Method for transfering dataAccess and studdyBuddies between classes.
   * Is used in the class that opens an FXML that uses this controller.
   *
   * @param dataAccess dataAccess for the run of the application
   *
   * @param buddies studdyBuddies for the run of the application
   */
  public void transferData(DataAccess dataAccess, StuddyBuddies buddies) {
    this.dataAccess = dataAccess;
    this.buddies = buddies;
  }

  /**
   * Method for redirecting back to the welcome page.
   */
  @FXML
  public void handleBack() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("StuddyBuddy.fxml"));
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
