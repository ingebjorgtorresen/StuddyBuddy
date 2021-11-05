package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.*;

public class RegisterStuddyBuddyTest extends Application{

    private RegisterStuddyBuddyController controller = new RegisterStuddyBuddyController();
    private StuddyBuddy buddy;

    @Override
    public void start(final Stage stage) throws Exception {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterStuddyBuddy_test.fxml"));
      final Parent root = loader.load();
      this.controller = loader.getController();
      stage.setScene(new Scene(root));
      stage.show();
        
    }

    @BeforeEach
    public void setUpRegistration() {
      buddy = new StuddyBuddy();
    }

    @Test
    public void testController() {
        assertNotNull(this.controller);
        assertNotNull(this.buddy);
    }

    
}
