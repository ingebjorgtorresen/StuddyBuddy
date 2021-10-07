package ui;

import java.io.FileNotFoundException;

import core.StuddyBuddy;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StuddyBuddyController {
    
    private StuddyBuddy studdyBuddy;

    private StuddyBuddyFileHandler studdyBuddyFileHandler;

    @FXML
    Pane registration;

	@FXML 
	private TextField nameField;
	
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
		studdyBuddy = new StuddyBuddy();
        createRegistration();
        studdyBuddyFileHandler = new StuddyBuddyFileHandler();
	}

    /**
	 * creates a new registration
	 * if the registration is not null, the pane is cleared
     * meassage text is set to not be visable and in Paradise Pink color
	 */
    private void createRegistration(){

        if (this.registration != null) {
            registration.getChildren().clear();
        }

        messageText.setVisible(false);
        messageText.setTextFill(Color.web("#ED4D6E"));
    }

    /**
	 * sets the name to be the input in nameField
	 * the name can only consist of letters and space.
	 * @return the name from input
	 */
    @FXML
    public String getInputName() {
        String nameString = nameField.getText();
        try{
            this.studdyBuddy.setName(nameString);
        }
        catch(IllegalArgumentException e){
            messageText.setText("Name can not include any \ncharacthers but letters and \n' ', you wrote: " + nameString);
            messageText.setVisible(true);
        }
        return nameString;
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
            this.studdyBuddy.setRoom(roomString);
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
            this.studdyBuddy.setCourse(courseString);
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
            this.studdyBuddy.setStartTime(startTimeString);
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
            this.studdyBuddy.setEndTime(endTimeSting);
        }
        catch(IllegalArgumentException e){
            messageText.setText("EndTime must be on format \n'HH:mm' and after StartTime");
            messageText.setVisible(true);
        }
        return endTimeSting;
    }

    /**
	 * register a new StuddyBuddy
	 * sets the name, room, course, start time and end time
	 */
    private void registerStuddyBuddy(){
        this.studdyBuddy.setName(getInputName());
        this.studdyBuddy.setRoom(getInputRoom());
        this.studdyBuddy.setCourse(getInputCourse());
        this.studdyBuddy.setStartTime(getInputStartTime());
        this.studdyBuddy.setEndTime(getInputEndTime());
    }

    /**
	 * sets the feedback text to be Paradise Pink color
	 * saves this registration to file
     * sets message and feedback to be visable 
	 * @return the start time from input
	 */
    @FXML
    public void handleRegister() throws FileNotFoundException{ // try, catch

        if (feedbackText.isVisible()) {
            feedbackText.setVisible(false);
            messageText.setTextFill(Color.web("#ED4D6E"));
        }

        registerStuddyBuddy();
        studdyBuddyFileHandler.saveRegistrationToFile(this.studdyBuddy);
        messageText.setText("Registration was successfull!");
        messageText.setTextFill(Color.web("#7DDF64"));
        messageText.setVisible(true);
        feedbackText.setText(studdyBuddyFileHandler.readRegistrationFromFile());
        feedbackText.setStyle("-fx-background-color: #C0DF85");
        feedbackText.setVisible(true);
        nameField.clear();
        roomField.clear();
        courseField.clear();
        startTimeField.clear();
        endTimeField.clear();
    }

}