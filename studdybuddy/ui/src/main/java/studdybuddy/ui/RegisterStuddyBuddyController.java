package studdybuddy.ui;

import studdybuddy.core.*;
import studdybuddy.dataaccess.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Controller class for registering new StuddyBuddy/user.
 */

public class RegisterStuddyBuddyController {

    private StuddyBuddy buddy;
    private DataAccess dataAccess;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField passwordCheckField;

    @FXML
    private Label messageBox;

    @FXML
    public String getInputName() {
        String nameString = nameField.getText();
        return nameString;
    }

    @FXML
    public String getInputPasswordCheck() {
        String passwordString = passwordCheckField.getText();
        return passwordString;
    }

    @FXML
    public String getInputPassword() {
        String passwordString = passwordField.getText();
        return passwordString;
    }

    public void setDataAccess(DataAccess access) {
        this.dataAccess = access;
    }

    private boolean checkNotNull() {
        try {
            StuddyBuddyValidation.checkNotNull(getInputName());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private boolean checkInputName() {
        return StuddyBuddyValidation.checkName(getInputName());
    }

    private boolean checkInputPassword() {
        return StuddyBuddyValidation.checkPassword(getInputPassword());
    }

    public boolean checkName() {
        if((checkNotNull() == false) || (checkInputName() == false)) {
            return false;
        }
        return true;
    }

    public boolean checkPassword() {
        if((checkNotNull() == false) || (checkInputPassword() == false)) {
            return false;
        }
        return true;
    }
    

    public boolean checkPasswordsMatch() {
        if (!(getInputPassword().equals(getInputPasswordCheck()))) {
            return false;
        }
        return true;
    }

    private StuddyBuddy createNewStuddyBuddy() {
        buddy = new StuddyBuddy();
        buddy.setName(getInputName());
        buddy.setPassword(getInputPassword());
        return buddy;
    }

    private void changeScene(ActionEvent event) throws IOException {
        try {
            URL file = getClass().getResource("StuddyBuddy.fxml");
            FXMLLoader loader = new FXMLLoader(file);
            Parent parent = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Log in");
            stage.setScene(new Scene(parent));
            stage.show();
            Stage thisStage = (Stage) nameField.getScene().getWindow();
            thisStage.close();

        } catch (IOException e) {
            messageBox.setText("Could not load window.");
        }
    }

    @FXML
    public void handleRegisterUser(ActionEvent event) throws IOException {
        try {

            checkName();
            checkPassword();
            checkPasswordsMatch();

            if (((!checkName()) && (!checkPassword()) && (!checkPasswordsMatch()))) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            }

            else if ((!checkName()) && (!checkPassword())) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            }

            else if ((!checkName()) && (!checkPasswordsMatch())) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            }

            else if (!(checkName())) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                passwordField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                passwordField.setText(getInputPassword());
                passwordCheckField.setText(getInputPasswordCheck());
            }

            else if (!(checkPassword())) {
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                nameField.setText(getInputName());
            }

            else if (!(checkPasswordsMatch())) {
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                nameField.setText(getInputName());
            }

            else {

                passwordField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");

                createNewStuddyBuddy();
                dataAccess.putStuddyBuddy(buddy);
                messageBox.setText("Registering new user was sucessfull.");
                changeScene(event);
            }
        } catch (IOException e) {
            messageBox.setText("Could not register user. Try again.");
            e.printStackTrace();
        }

    }

}
