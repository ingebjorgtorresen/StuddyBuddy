package ui;

import java.io.IOException;

import core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

/**
 * Controller class for Log in to a StuddyBuddy account.
 */

public class StuddyBuddyController {

    private StuddyBuddy studdyBuddy;

    @FXML
    private TextField nameField;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField passwordField;

    public void initialize() {
        studdyBuddy = new StuddyBuddy();
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
        try {
            this.studdyBuddy.setName(nameString);
        } catch (IllegalArgumentException e) {
            nameField.setText("Invalid name.");
            nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            //errorMessage.setText("Name can not include any \ncharacthers but letters and \n' ', you wrote: " + nameString);
            //errorMessage.setTextFill(Color.web("#ED4D6E"));
            //errorMessage.setVisible(true);
        }
        return nameString;
    }

    // Denne metoden må endres etterhvert, fordi den bli overskrevet av nameField om det også er av feil format.
    @FXML
    public String getInputPassword() {
        String passwordString = passwordField.getText();
        try {
            this.studdyBuddy.setPassword(passwordString);
        } catch (IllegalArgumentException e) {
            passwordField.setText("Invalid name.");
            passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            //errorMessage.setText("Please set a valid password with at least 8 characters consisting of digits and letters.");
            //errorMessage.setTextFill(Color.web("#ED4D6E"));
            //errorMessage.setVisible(true);
        }
        return passwordString;
    }

    /**
     * IKKE FERDIG Sends the username to the next controller and loads a new window
     */
    @FXML
    public void handleLogin() {

        try {

            URL fxmlFile = getClass().getResource("StuddyBuddyRegistration.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlFile);
            Parent parent = (Parent) loader.load();
            StuddyBuddyRegistrationController registrationController = loader.getController();
            studdyBuddy.setName(getInputName());
            studdyBuddy.setPassword(getInputPassword());
            nameField.clear();
            passwordField.clear();
            registrationController.setStuddyBuddyFromLogin(studdyBuddy);
            Stage registrationStage = new Stage();
            registrationStage.setTitle("Registration");
            registrationStage.setScene(new Scene(parent));
            registrationStage.show();
            Stage thisStage = (Stage) nameField.getScene().getWindow();
            thisStage.close();

        } catch (IOException e) {
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