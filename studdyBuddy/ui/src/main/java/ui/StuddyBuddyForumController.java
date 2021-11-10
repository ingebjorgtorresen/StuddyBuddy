package ui;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import core.StuddyBuddies;
import core.StuddyBuddy;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import json.StuddyBuddiesPersistence;

public class StuddyBuddyForumController {

    private StuddyBuddy studdyBuddy;
    private StuddyBuddiesPersistence persistence = new StuddyBuddiesPersistence();
    private String registrationsFileName = "/registrations.json";

    @FXML
    private ImageView studdyBuddyLogo;

    @FXML
    private ImageView userIcon;

    @FXML
    private Label username;

    @FXML
    private Button logOut;

    @FXML
    private Button addRegistrationButton;
    
    @FXML
    private Label studdyBuddyUser;

    @FXML
    private Label registrationsTitle;

    @FXML
    private Label allRegistrationsText;


    public void initialize() {
        //createForum();
    }

    public void handleAddRegistration() {
        System.out.println("Skal ta brukeren til registreringssiden");
    }

    /*private void createForum() {
        username.setText(studdyBuddy.getName());
    }

    public void setStuddyBuddyFromLogin(StuddyBuddy studdyBuddy) {
        this.studdyBuddy = studdyBuddy;
    }

    @FXML
    public void handleAddRegistration() {
        
        try {

            URL fxmlFile = getClass().getResource("StuddyBuddyRegistration.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlFile);
            Parent parent = (Parent) loader.load();
            Stage registrationStage = new Stage();
            registrationStage.setTitle("Registration");
            registrationStage.setScene(new Scene(parent));
            registrationStage.show();
            Stage thisStage = (Stage) username.getScene().getWindow();
            thisStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLogOut(){
        // TODO
    }

    public StuddyBuddies getRegistratedStuddyBuddies(){
        StuddyBuddies registeredBuddies = null;
        try (Reader reader = new FileReader(System.getProperty("user.home") + registrationsFileName, StandardCharsets.UTF_8)) {
                registeredBuddies = persistence.readStuddyBuddies(reader);
        } catch (Exception e) {
            System.err.println("Couldn´t read from file");
            e.printStackTrace();
        }
        return registeredBuddies;
    } */
/**
    public void displayRegistrations() {
        StuddyBuddies registeredBuddies = getRegistratedStuddyBuddies();
        if (registeredBuddies != null){
            allRegistrationsText.setText(studdyBuddies.getStuddyBuddies().toString());
        }
    }*/


}