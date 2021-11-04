package ui;

import java.io.IOException;

import core.*;
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
        return nameString;
    }

    // Denne metoden må endres etterhvert, fordi den bli overskrevet av nameField om det også er av feil format.
    @FXML
    public String getInputPassword() {
        String passwordString = passwordField.getText();
        return passwordString;
    }

    private boolean checkInputName() {
        try {
            this.studdyBuddy.setName(getInputName());
        } catch(IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private boolean checkInputPassword() {
        try {
            this.studdyBuddy.setPassword(getInputPassword());
        } catch(IllegalArgumentException e) {
            return false;
        }
        return true;
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

            checkInputName();
            checkInputPassword();

            if(((!checkInputPassword()) && (!checkInputName()))) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");

            }
    
            else if(!checkInputName()) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                passwordField.setText(getInputPassword());

            }
    
            else if(!checkInputPassword()) {
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                nameField.setText(getInputName());
            }

            else {
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
            }
        
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
            //errorMessage.setText("Could not load window.");
            errorMessage.setText(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}