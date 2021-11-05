package ui;

import core.*;
import json.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class StuddyBuddyRegistrationController {
    
    private StuddyBuddyRegistration registration;
    private StuddyBuddy buddy;
    private StuddyBuddiesPersistence persistence = new StuddyBuddiesPersistence();
    private String registrationsFileName = "/registrations.json";

	@FXML 
	private TextField roomField;
	
	@FXML 
	private TextField courseField;
	
	@FXML 
	private TextField startTimeField;
	
	@FXML 
	private TextField endTimeField;

    @FXML
    private Label messageText;
	
    @FXML 
    private Label feedbackText;

	public void initialize() {
		registration = new StuddyBuddyRegistration();
        createRegistration();
	}

    /**
	 * creates a new registration
	 * if the registration is not null, the pane is cleared
     * meassage text is set to not be visable and in Paradise Pink color
	 */
    private void createRegistration(){
        messageText.setVisible(false);
        messageText.setTextFill(Color.web("#ED4D6E"));
    }

    public void setStuddyBuddyFromLogin(StuddyBuddy studdyBuddy) {
        registration.setStuddyBuddy(studdyBuddy);
    }

    /**
	 * sets the room to be the input in roomField
	 * the room can only consist of letters, "-" and space.
	 * @return the room from input
	 */
    @FXML
    public String getInputRoom() {
        String roomString = roomField.getText();
        try{
            registration.setRoom(roomString);
        }
        catch (IllegalArgumentException e){
            messageText.setText("Can not use other characters \nthan letters, digits, '-' and \n' '. You wrote: " + roomString);
            messageText.setVisible(true);
        }
        return roomString;
    }

    /**
	 * sets the course to be the input in courseField
	 * the room can only consist of letters, "-" and space.
	 * @return the course from input
	 */
    @FXML
    public String getInputCourse() {
        String courseString = courseField.getText();
        try{
            registration.setCourse(courseString);
        }
        catch(IllegalArgumentException e){
            messageText.setText("Can not use other characters \nthan letters, digits, '-' and \n' '. You wrote: " + courseString);
            messageText.setVisible(true);
        }
        return courseString;
    }

    /**
	 * sets the start time to be the input in startTimeField
	 * the start time must be on format 'HH:mm'
	 * @return the start time from input
	 */
    @FXML
    public String getInputStartTime() {
        String startTimeString = startTimeField.getText();
        try{
            registration.setStartTime(startTimeString);
        }
        catch(IllegalArgumentException e){
            messageText.setText("Starttime must be on format \n'HH:mm' ");
            messageText.setVisible(true);
        }
        return startTimeString;
    }

    /**
	 * sets the end time to be the input in endTimeField
	 * the end time must be on format 'HH:mm' and after StartTime
	 * @return the end time from input
	 */
    @FXML
    public String getInputEndTime() {
        String endTimeSting = endTimeField.getText();
        try{
            registration.setEndTime(endTimeSting);
        }
        catch(IllegalArgumentException e){
            messageText.setText("EndTime must be on format \n'HH:mm' and after StartTime");
            messageText.setVisible(true);
        }
        return endTimeSting;
    }

    /**
	 * @return roomField
	 */
    public TextField getRoom(){
        return roomField;
    }

    /**
	 * @return courseField
	 */
    public TextField getCourse(){
        return courseField;
    }

    /**
	 * @return startTimeField
	 */
    public TextField getStartTime(){
        return startTimeField;
    }

    /**
	 * @return endTimeField
	 */
    public TextField getEndTime(){
        return endTimeField;
    }


    /**
	 * Get the text printed from the label messageText
	 * 
	 * @return messageText from label
	 */
	public String getMessageText(){
		return messageText.getText();
	}

    /**
	 * Get the label messageText
	 * 
	 * @return the label messageText 
	 */
	public Label getMessageTextLabel(){
		return messageText;
	}

    /**
	 * Get the label feedbackText
	 * 
	 * @return the label feedbackText 
	 */
	public Label getFeedbackLabel(){
		return feedbackText;
	}

    /**
	 * register a new StuddyBuddy
	 * sets the name, room, course, start time and end time
	 */
    private void registerStuddyBuddy(){
        registration.setRoom(getInputRoom());
        registration.setCourse(getInputCourse());
        registration.setStartTime(getInputStartTime());
        registration.setEndTime(getInputEndTime());
    }

    private void saveStuddyBuddyToFile() {
        try (Writer writer = new FileWriter(System.getProperty("user.home") + registrationsFileName, StandardCharsets.UTF_8)) {
            persistence.writeStuddyBuddies(buddy.getStuddyBuddies(), writer);
            writer.flush();
        } catch (IOException e) {
            messageText.setText("Couldn't save your registration.");
            messageText.setVisible(true);
        }
    }

    public StuddyBuddy getRedigsteredStuddyBuddy() {
        StuddyBuddy registeredBuddy = null;
        try (Reader reader = new FileReader(System.getProperty("user.home") + registrationsFileName, StandardCharsets.UTF_8)) {
            registeredBuddy = persistence.readStuddyBuddies(reader).getStuddyBuddy(buddy.getName());
        } catch (IOException e) {
            System.err.println("Couldn't read from file.");
            e.printStackTrace();
        } 
        return registeredBuddy;
    }

    public void displayRegistration() {
        StuddyBuddy registeredBuddy = getRedigsteredStuddyBuddy();
        if (registeredBuddy == null) {
            messageText.setText("Couldn't register. Try again.");
        } else {
            messageText.setText("Registration was successfull!");
            messageText.setTextFill(Color.web("#7DDF64"));
            feedbackText.setText(buddy.getRegistrations().get(0).toString());
            feedbackText.setStyle("-fx-background-color: #C0DF85");
            feedbackText.setVisible(true);
        }

        messageText.setVisible(true);
        roomField.clear();
        courseField.clear();
        startTimeField.clear();
        endTimeField.clear();
    }

    /**
	 * sets the feedback text to not be visable and to have Paradise Pink color
	 * saves this registration to file registration was successful
     * sets message to be visable
     * sets feedback to be visable if registration was successful
     * sets message text to be Amazon color if registration was successful
     * sets feedback text to have Yellow Green Crayola color if registration was successful
	 * clears the texfields if registration was successful
	 */
    @FXML
    public void handleRegister() throws FileNotFoundException{ // try, catch

        if (feedbackText.isVisible()) {
            feedbackText.setVisible(false);
            messageText.setTextFill(Color.web("#ED4D6E"));
        }

        registerStuddyBuddy();
        buddy = registration.getStuddyBuddy();
        buddy.addRegistration(registration);
        saveStuddyBuddyToFile();
        displayRegistration();
    }

}