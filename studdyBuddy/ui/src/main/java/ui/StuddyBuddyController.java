package ui;

import java.io.IOException;

import core.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class StuddyBuddyController {

    private StuddyBuddy studdyBuddy;

    @FXML
    private TextField nameField;

    @FXML
    private Label errorMessage;

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
            errorMessage.setText("Name can not include any \ncharacthers but letters and \n' ', you wrote: " + nameString);
            errorMessage.setTextFill(Color.web("#ED4D6E"));
            errorMessage.setVisible(true);
        }
        return nameString;
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
            nameField.clear();
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
}