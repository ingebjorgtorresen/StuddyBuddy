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
import javafx.scene.Node;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;


/**
 * Controller class for registering new StuddyBuddy/user. 
 */

public class RegisterStuddyBuddyController {

    private StuddyBuddy buddy;
    private StuddyBuddyPersistence persistence = new StuddyBuddyPersistence();
    private String registrationsFileName = "/userRegistration.json";

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
        try {
            this.buddy.setName(nameString);
        } catch (IllegalArgumentException e) {
            nameField.setPromptText("Invalid name.");
            nameField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
        }
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
        if(passwordString.equals(getInputPasswordCheck())) {
            try {
                this.buddy.setPassword(passwordString);
            } catch (IllegalArgumentException e) {
                passwordField.setPromptText("Invalid password.");
                passwordField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
            }
        } else {
            passwordCheckField.setPromptText("Password does not match.");
            passwordCheckField.setStyle("-fx-prompt-text-fill: red; -fx-border-color: red;");
        }
        return passwordString;
    }

    private StuddyBuddy createNewStuddyBuddy() {
        buddy = new StuddyBuddy();
        buddy.setName(getInputName());
        buddy.setPassword(getInputPassword());
        return buddy;
    }

    private void changeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StuddyBuddy.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private void saveStuddyBuddyToFile() throws IOException {
        try (Writer writer = new FileWriter(System.getProperty("user.home") + registrationsFileName, StandardCharsets.UTF_8)) {
            persistence.writeStuddyBuddy(buddy, writer);
            writer.flush();
        } catch (IOException e) {
            throw new IOException("Could not save registration.");
        }
    }

    @FXML 
    public void handleRegisterUser(ActionEvent event) throws IOException {
        try {
            createNewStuddyBuddy();
            saveStuddyBuddyToFile();
            messageBox.setText("Registering new user was sucessfull.");
            changeScene(event);
        } catch (IOException e) {
            messageBox.setText("Could not save registration.");
        }

    }
    
}
