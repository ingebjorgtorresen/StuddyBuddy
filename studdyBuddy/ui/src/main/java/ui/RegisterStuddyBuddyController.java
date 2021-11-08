package ui;

import core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json.StuddyBuddyPersistence;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;


/**
 * Controller class for registering new StuddyBuddy/user. 
 */

public class RegisterStuddyBuddyController {

    private StuddyBuddy buddy;
    private StuddyBuddyPersistence persistence = new StuddyBuddyPersistence();
    private String userRegistrationFileName = "/userRegistration.json";

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField passwordCheckField;

    @FXML 
    private Label messageBox;

    public void initialize() {
        buddy = new StuddyBuddy();
    }

    @FXML
    public String getInputName() {
        String nameString = nameField.getText();
        return nameString;
    }

    public boolean checkInputName() {
        try {
            this.buddy.setName(getInputName());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
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

    public boolean checkInputPassword() {
        try {
             this.buddy.setPassword(getInputPassword());
        } catch (IllegalArgumentException e) {
                return false;
        }
        return true;
    }

    public boolean checkPasswordsMatch() {
        if(!(getInputPassword().equals(getInputPasswordCheck()))) {
            return false;
        }
        return true;
    }

    private StuddyBuddy createNewStuddyBuddy() {
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

    private void saveStuddyBuddyToFile() throws IOException {
        try (Writer writer = new FileWriter(System.getProperty("user.home") + userRegistrationFileName, StandardCharsets.UTF_8)) {
            persistence.writeStuddyBuddy(buddy, writer);
            writer.flush();
        } catch (IOException e) {
            throw new IOException("Could not save registration.");
        }
    }

    @FXML 
    public void handleRegisterUser(ActionEvent event) throws IOException {
        try {

            checkInputName();
            checkInputPassword();
            checkPasswordsMatch();

            if(((!checkInputName()) && (!checkInputPassword()) && (!checkPasswordsMatch()))) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            }

            else if((!checkInputName()) && (!checkInputPassword())) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            }

            else if ((!checkInputName()) && (!checkPasswordsMatch())) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            }

            else if(!(checkInputName())) {
                nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                passwordField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                passwordField.setText(getInputPassword());
                passwordCheckField.setText(getInputPasswordCheck());
            }

            else if(!(checkInputPassword())) {
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                nameField.setText(getInputName());
            }

            else if(!(checkPasswordsMatch())) {
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
                nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                nameField.setText(getInputName());
            }

            else {

                passwordField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                passwordCheckField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");
                nameField.setStyle("-fx-prompt-text-fill: gray; -fx-border-color: gray;");

                //denne brukeren bør så legges til i listen over brukere når det er implementert riktig
                createNewStuddyBuddy();
                //saveStuddyBuddyToFile();
                messageBox.setText("Registering new user was sucessfull.");
                changeScene(event);
            }
        } catch (IOException e) {
            messageBox.setText("Could not save registration.");
            e.printStackTrace();
        }

    }
    
}
