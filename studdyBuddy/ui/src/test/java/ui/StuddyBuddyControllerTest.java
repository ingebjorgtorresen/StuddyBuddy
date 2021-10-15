package ui;

import org.junit.jupiter.api.BeforeEach;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Iterator;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//import java.util.function.Predicate;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.ListView;
import javafx.stage.Stage;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.testfx.framework.junit5.ApplicationTest;
//import org.testfx.util.WaitForAsyncUtils;
//import StuddyBuddy.core.AbstractTodoList;
//import StuddyBuddy.core.StuddyBuddyRegister;
//import StuddyBuddy.core.StuddyBuddy;
//import StuddyBuddy.core.TodoModel;
//import StuddyBuddy.core.TodoSettings.TodoItemsSortOrder;
//import StuddyBuddy.json.TodoPersistence;

import core.StuddyBuddy;
import core.StuddyBuddyRegistration;

public class StuddyBuddyControllerTest extends ApplicationTest {

    private StuddyBuddyController controller;
  
    @Override
    public void start(final Stage stage) throws Exception {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("StuddyBuddy_test.fxml"));
      final Parent root = loader.load();
      this.controller = loader.getController();
      //controller.setTodoItemsProvider(TodoModel.getSortedTodoItemsProvider(TodoItemsSortOrder.UNCHECKED_CHECKED));
      stage.setScene(new Scene(root));
      stage.show();
    }

    //private TodoPersistence todoPersistence = new TodoPersistence();
    private StuddyBuddy studdyBuddy;
    private StuddyBuddyRegistration reg1;
    private StuddyBuddyRegistration reg2;
    private StuddyBuddyRegistration reg3;
  
    @BeforeEach
    public void setUpRegistrations(){
      Iterator<StuddyBuddyRegistration> registrations = studdyBuddy.iterator();
      //reg1 = new StuddyBuddyRegistration().as(registrations.next());
      //reg2.registerStuddyBuddy();
      //reg3.registerStuddyBuddy();
      studdyBuddy.addRegistration(reg1);
      studdyBuddy.addRegistration(reg2);
      studdyBuddy.addRegistration(reg3);
    }
  

    
}
  