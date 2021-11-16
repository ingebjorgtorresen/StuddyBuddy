package studdybuddy.ui;

import java.io.IOException;

import studdybuddy.core.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

/**
 * Controller class for Log in to a StuddyBuddy account.
 */

public class StuddyBuddyController {

    private StuddyBuddy studdyBuddy;
    // private StuddyBuddies buddies;

    @FXML
    private TextField nameField;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField passwordField;

    public void initialize() {
        studdyBuddy = new StuddyBuddy();
        // buddies = new StuddyBuddies();
    }

    /**
     * sets the name to be the input in nameField the name can only consist of
     * letters and space.
     * 
     * @return the name from input
     */
    @FXML
    public String getInputName() {
        String nameString = nameField.getText();
        return nameString;
    }

    @FXML
    public String getInputPassword() {
        String passwordString = passwordField.getText();
        return passwordString;
    }

    public boolean checkInputName() {
        try {
            this.studdyBuddy.setName(getInputName());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean checkInputPassword() {
        try {
            this.studdyBuddy.setPassword(getInputPassword());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
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
            // StuddyBuddyForumController forumController = loader.getController();

            checkInputName();
            checkInputPassword();

            if (((!checkInputPassword()) && (!checkInputName()))) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");

            }

            else if (!checkInputName()) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                passwordField.setText(getInputPassword());

            }

            else if (!checkInputPassword()) {
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                nameField.setText(getInputName());
            }

            else {
                studdyBuddy.setName(getInputName());
                studdyBuddy.setPassword(getInputPassword());
                nameField.clear();
                passwordField.clear();
                // forumController.setStuddyBuddyFromLogin(studdyBuddy);

                Stage forumStage = new Stage();
                forumStage.setTitle("Forum");
                forumStage.setScene(new Scene(parent));
                forumStage.show();
                Stage thisStage = (Stage) nameField.getScene().getWindow();
                thisStage.close();
            }

        } catch (IOException e) {
            errorMessage.setText("Could not Log in. Try again.");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRegisterUser() {
        try {
            URL file = getClass().getResource("RegisterStuddyBuddy.fxml");
            FXMLLoader loader = new FXMLLoader(file);
            Parent parent = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Register user");
            stage.setScene(new Scene(parent));
            stage.show();
            Stage thisStage = (Stage) nameField.getScene().getWindow();
            thisStage.close();

        } catch (IOException e) {
            errorMessage.setText("Could not load window.");
        }
    }

}