package studdybuddy.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StuddyBuddyApp extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("StuddyBuddy.fxml"));
    Parent parent = fxmlLoader.load();
    stage.setScene(new Scene(parent));
    stage.setTitle("StuddyBuddy");
    stage.show();
  }

  public static void main(String[] args) {
    launch(StuddyBuddyApp.class);
  }
}
