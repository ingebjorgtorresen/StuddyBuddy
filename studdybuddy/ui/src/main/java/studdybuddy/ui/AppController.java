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
  StuddyBuddiesController studdyBuddiesViewController;

  private StuddyBuddiesPersistence persistence;

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
    DataAccess access = null;
    if(endpointUri != null) {
      RemoteDataAccess remoteAccess;
      try {
        System.out.println("Using remote endpoint @ " + endpointUri);
        remoteAccess = new RemoteDataAccess(new URI(endpointUri));
        access = remoteAccess;
      } catch(URISyntaxException e) {
        System.err.println(e);
      }
    }
    if(access == null) {
      this.persistence = new StuddyBuddiesPersistence();
      persistence.setSaveFilePath(userStuddyBuddyPath);
      DirectDataAccess directAccess = new DirectDataAccess(getInitialStuddyBuddies());
      directAccess.setPersistence(persistence);
      access = directAccess;
    }
    System.out.println();
    System.out.println("KONTROLLEREN ER " + studdyBuddiesViewController);
    System.out.println();
    studdyBuddiesViewController.setDataAccess(access);
  }
}
