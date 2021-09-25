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
    private Label messageText; // skal være de ulike feilmeldingene som kastes ut, usikker på om dette kommer til å fungere
	
	
	public void initialize() {
		studdyBuddy = new StuddyBuddy();
        createRegistration();
        // TODO: kobling til fillagring
        studdyBuddyFileHandler = new StuddyBuddyFileHandler();

	}

    private void createRegistration(){

        if (this.registration != null) {
            registration.getChildren().clear();
            //Pane pane = new Pane();
            //pane.setStyle("-fx-background-color: white;");
            messageText.setVisible(false);
            messageText.setStyle("-fx-background-color: #ed4d6e"); // Paradise pink-color
        }
    }

    @FXML
    public String getInputName() {
        String nameString = nameField.getText();
        try{
        this.studdyBuddy.setName(nameString);
        }
        catch(IllegalArgumentException e){
        messageText.setText("Name can not include any characthers but letters and ' ', you wrote: " + nameString);
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
        messageText.setText("Starttime must be on format 'HH:mm' "); // usikker på hva slags feilmelding som skal sendes ut her
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
        messageText.setText("EndTime must be on format 'HH:mm' "); // usikker på hva slags feilmelding som skal sendes ut her
        }
        return endTimeSting;
    }

    // must be input in every textfield in order to register
    private boolean hasInput(){
        return (!getInputName().isBlank() | !getInputRoom().isBlank() | !getInputCourse().isBlank() | !getInputStartTime().isBlank() | !getInputEndTime().isBlank());
    }

    private void registerStuddyBuddy(){
        this.studdyBuddy.setName(nameField.getText());
        this.studdyBuddy.setRoom(roomField.getText());
        this.studdyBuddy.setCourse(courseField.getText());
        this.studdyBuddy.setStartTime(startTimeField.getText());
        this.studdyBuddy.setEndTime(endTimeField.getText());
    }

    @FXML
    public void handleRegister() throws FileNotFoundException{ // try, catch
        registerStuddyBuddy();
        if(hasInput()){
            // the info is saved to file
            studdyBuddyFileHandler.saveRegistrationToFile(this.studdyBuddy);
            messageText.setText("Your registration was successfull!");
            messageText.setStyle("-fx-background-color: #7DDF64"); // Light green-color
        }
        messageText.setVisible(true);
    }

}