package studdybuddy.ui;

import java.io.IOException;

import studdybuddy.core.*;
import studdybuddy.dataaccess.*;
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

    private DataAccess dataAccess;
    private StuddyBuddy studdyBuddy;

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

    public boolean checkIfUserExist() {
        try {
            dataAccess.getStuddyBuddyByName(getInputName());
        } catch( RuntimeException e) {
            return false;
        }
        return true;
    }

    public boolean checkPasswordsMacthes() {
        if(getInputPassword().equals(dataAccess.getStuddyBuddyPasswordByName(getInputName()))) {
                return true;
        }
        return false;
    }

    public void setStuddyBuddyFromServer() {
        studdyBuddy = dataAccess.getStuddyBuddyByName(getInputName());
    }

    public StuddyBuddy getStuddyBuddy() {
        return studdyBuddy;
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
            //StuddyBuddyForumController forumController = loader.getController();

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
                //forumController.setStuddyBuddyFromLogin(studdyBuddy);

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

            registerViewController = new RegisterStuddyBuddyController();
            registerViewController.setDataAccess(dataAccess);

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