package ui;

import core.StuddyBuddy;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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
            errorMessage.setText("Name can not include any \ncharacthers but letters and \n' ', you wrote: " + nameString);
            errorMessage.setTextFill(Color.web("#ED4D6E"));
            errorMessage.setVisible(true);
        }
        return nameString;
    }

    /**
     * IKKE FERDIG
	 * Sends the username to the next controller
	 */
    @FXML
    public void handleLogin() {
        this.studdyBuddy.setName(getInputName());
        nameField.clear();
    }

}