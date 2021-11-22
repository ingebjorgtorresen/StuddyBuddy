package studdybuddy.ui;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import studdybuddy.json.*;
import studdybuddy.core.*;
import studdybuddy.dataaccess.*;

public class AppController {

  private static final String studdyBuddiesWithTwoStuddyBuddy = """
      {
          "StuddyBuddies" : [ {
            "Name" : "FirstBuddy",
            "Registrations" : [ {
              "Date" : "10/10/2022",
              "Room" : "TestRoom1",
              "Course" : "TestCourse1",
              "Start time" : "08:00",
              "End time" : "12:00"
            } ]
          }, {
            "Name" : "SecondBuddy",
            "Registrations" : [ {
              "Date" : "12/12/2022",
              "Room" : "TestRoom2",
              "Course" : "TestCourse2",
              "Start time" : "10:00",
              "End time" : "14:00"
            } ]
          } ]
      } """;

  @FXML
  String userStuddyBuddyPath;

  @FXML
  String endpointUri;

  @FXML
  String sampleStuddyBuddyResource;

  @FXML
  private Button getStartedButton;

  private StuddyBuddiesPersistence persistence;

  private DataAccess dataAccess;

  private StuddyBuddies buddies;

  /**
   * Method that first tries to read file from home folder. If StuddyBuddies
   * object is null then tries to sample studdybuddies from resource instead.
   * 
   * @return
   */
  private StuddyBuddies getInitialStuddyBuddies() {
    StuddyBuddies buddies = null;

    if (persistence != null) {
      try {
        buddies = persistence.loadStuddyBuddies();
      } catch (Exception e) {
        System.err.println("Could not load saved StuddyBuddies");
      }
    }

    if (buddies == null) {
      Reader reader = null;
      if (sampleStuddyBuddyResource != null) {
        URL url = getClass().getResource(sampleStuddyBuddyResource);
        if (url != null) {
          try {
            reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
          } catch (IOException e) {
            System.err.println("Could not read from source " + sampleStuddyBuddyResource);
          }
        } else {
          System.err.println("Could not find source: " + sampleStuddyBuddyResource);
        }
      }
      if (reader == null) {
        reader = new StringReader(studdyBuddiesWithTwoStuddyBuddy);
      }
      try {
        buddies = persistence.readStuddyBuddies(reader);
      } catch (IOException e) {
        // this is ingored
      } finally {
        try {
          if (reader != null) {
            reader.close();
          }
        } catch (IOException e) {
          // this is ingored
        }
      }
    }
    if(buddies == null) {
      buddies = new StuddyBuddies();
      StuddyBuddy buddy = new StuddyBuddy();
      buddy.setName("Selma");
      buddy.setPassword("password1");
      StuddyBuddyRegistration registration = new StuddyBuddyRegistration();
      LocalDate date1 = LocalDate.of(2021, 11, 11);
      registration.setDate(date1);
      registration.setCourse("ITP");
      registration.setRoom("A3");
      registration.setStartTime("12:00");
      registration.setEndTime("14:00");
      buddy.addRegistration(registration);
      buddies.addStuddyBuddy(buddy);
    }
    return buddies;
  }

  @FXML
  public void initialize() {
    buddies = new StuddyBuddies();
    if(endpointUri != null) {
      try {
        System.out.println("Using remote endpoint @ " + endpointUri);
        dataAccess = new RemoteDataAccess(new URI(endpointUri));
      } catch(URISyntaxException e) {
        System.err.println(e);
      }
    }
    if(dataAccess == null) {
      this.persistence = new StuddyBuddiesPersistence();
      persistence.setSaveFilePath(userStuddyBuddyPath);
      DirectDataAccess directAccess = new DirectDataAccess(getInitialStuddyBuddies());
      directAccess.setPersistence(persistence);
      dataAccess = directAccess;
    }
  }

  @FXML
  public void handleGetStarted() {
    try {
      // Bytter til registreringssiden, hvertfall foreløpig
      FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterStuddyBuddy.fxml"));
      Parent parent = (Parent) loader.load();
      RegisterStuddyBuddyController registerController = loader.getController();
      registerController.transferData(dataAccess, buddies);
      Stage registrationStage = new Stage();
      registrationStage.setTitle("Register buddy");
      registrationStage.setScene(new Scene(parent));
      registrationStage.show();
      Stage thisStage = (Stage) getStartedButton.getScene().getWindow();
			thisStage.close();

  } catch (IOException e) {
      e.printStackTrace();
  }
  }

}
