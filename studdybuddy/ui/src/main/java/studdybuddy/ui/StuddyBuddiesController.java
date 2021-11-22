package studdybuddy.ui;

/*import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;*/

/**
 * Controller for studdyBuddies objects.
 */
public class StuddyBuddiesController {
    
    // Commented out while working on other classes
    /*private DataAccess dataAccess;
    private StuddyBuddiesPersistence persistence = new StuddyBuddiesPersistence();

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


    /**
    * Sets the DataAccess for this controller,
    * so data can come from different sources.
    *
    * @param dataAccess the new dataAccess
    
    public void setDataAccess(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @FXML
    public void initialize() {
        System.out.println();
        System.out.println("Den kjører initialize i StuddyBuddiesController.java");
        System.out.println();
        display();
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
    public void handleLogOut() {
        try {

            URL fxmlFile = getClass().getResource("StuddyBuddy.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlFile);
            Parent parent = (Parent) loader.load();
            Stage registrationStage = new Stage();
            registrationStage.setTitle("StuddyBuddy");
            registrationStage.setScene(new Scene(parent));
            registrationStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StuddyBuddies getRegistratedStuddyBuddies() {
        // Must be updated with server logic
        /*StuddyBuddies registeredBuddies = null;
        try (Reader reader = new FileReader(System.getProperty("user.home") + registrationsFileName,
                StandardCharsets.UTF_8)) {
            registeredBuddies = persistence.readStuddyBuddies(reader);
        } catch (Exception e) {
            System.err.println("Couldn´t read from file");
            e.printStackTrace();
        }
        return registeredBuddies;
        return null;
    }

    @FXML
    public void display() {
        // setUpBuddies();
        //allRegistrationsText.setText(buddies.toString());
        allRegistrationsText.setText("HEIHEI:))");
    } */

}
