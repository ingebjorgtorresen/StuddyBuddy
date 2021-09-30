package ui;

import java.io.FileNotFoundException;

import core.StuddyBuddy;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
	
	
	public void initialize() {
		studdyBuddy = new StuddyBuddy();
        createRegistration();
        studdyBuddyFileHandler = new StuddyBuddyFileHandler();

	}

    private void createRegistration(){

        if (this.registration != null) {
            registration.getChildren().clear();
        }

        messageText.setVisible(false);
        messageText.setStyle("-fx-background-color: #ED4D6E"); // Paradise pink-color
    }

    @FXML
    public String getInputName() {
        String nameString = nameField.getText();
        try{
            this.studdyBuddy.setName(nameString);
        }
        catch(IllegalArgumentException e){
            messageText.setText("Name can not include any characthers but letters and ' ', you wrote: " + nameString);
            messageText.setVisible(true);
        }
        return nameString;
    }

    @FXML
    public String getInputRoom() {
        String roomString = roomField.getText();
        try{
            this.studdyBuddy.setRoom(roomString);
        }
        catch (IllegalArgumentException e){
            messageText.setText("Can not use other characters than letters, digits, '-' and ' '. You wrote: " + roomString);
            messageText.setVisible(true);
        }
        return roomString;
    }

    @FXML
    public String getInputCourse() {
        String courseString = courseField.getText();
        try{
            this.studdyBuddy.setCourse(courseString);
        }
        catch(IllegalArgumentException e){
            messageText.setText("Can not use other characters than letters, digits, '-' and ' '. You wrote: " + courseString);
            messageText.setVisible(true);
        }
        return courseString;
    }

    @FXML
    public String getInputStartTime() {
        String startTimeString = startTimeField.getText();
        try{
            this.studdyBuddy.setStartTime(startTimeString);
        }
        catch(IllegalArgumentException e){
            messageText.setText("Starttime must be on format 'HH:mm' ");
            messageText.setVisible(true);
        }
        return startTimeString;
    }

    @FXML
    public String getInputEndTime() {
        String endTimeSting = endTimeField.getText();
        try{
            this.studdyBuddy.setEndTime(endTimeSting);
        }
        catch(IllegalArgumentException e){
            messageText.setText("EndTime must be on format 'HH:mm' and after StartTime");
            messageText.setVisible(true);
        }
        return endTimeSting;
    }

    private void registerStuddyBuddy(){
        this.studdyBuddy.setName(getInputName());
        this.studdyBuddy.setRoom(getInputRoom());
        this.studdyBuddy.setCourse(getInputCourse());
        this.studdyBuddy.setStartTime(getInputStartTime());
        this.studdyBuddy.setEndTime(getInputEndTime());
    }

    @FXML
    public void handleRegister() throws FileNotFoundException{ // try, catch
        registerStuddyBuddy();
        studdyBuddyFileHandler.saveRegistrationToFile(this.studdyBuddy);
        messageText.setText("Your registration was successfull!");
        messageText.setStyle("-fx-background-color: #7DDF64"); // Light green-color
        messageText.setVisible(true);
    }

}