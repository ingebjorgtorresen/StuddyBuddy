package studdybuddy.ui;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import studdybuddy.core.StuddyBuddy;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
//import studdybuddy.dataaccess.DirectDataAccess;
import studdybuddy.dataaccess.RemoteDataAccess;

public class StuddyBuddiesControllerTest extends ApplicationTest {

  //private DirectDataAccess dataAccess;
  private RemoteDataAccess remoteDataAccess;
  private StuddyBuddiesController controller;
  private StuddyBuddy buddy;
  private WireMockConfiguration configuration;
  private WireMockServer wireMockServer;
  
  @FXML
  private Label username;

  @FXML
  private Label allRegistrationsText;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("StuddyBuddies_test.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @BeforeEach
  public void startWireMockServerandSetUpStuddyBuddies() throws URISyntaxException {
    configuration = WireMockConfiguration.wireMockConfig().port(8080);
    wireMockServer = new WireMockServer(configuration.portNumber());
    wireMockServer.start();
    WireMock.configureFor("localhost", configuration.portNumber());
    remoteDataAccess = new RemoteDataAccess(new URI("http://localhost:" + wireMockServer.port() + "/studdybuddy"));
  }

  public String getStuddyBuddiesDisplayed() {
    stubFor(get(urlEqualTo("/studdybuddy"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{\"studdyBuddies\": [ {\"name\": \"registration\"}, {\"name\": \"registration\"} ]}")
        )
    );
    String studdyBuddiesDisplayed = remoteDataAccess.getStuddyBuddies().toString();
    return studdyBuddiesDisplayed;
  }

//  @BeforeEach
  public void setUpStuddyBuddies() {
    //dataAccess = new DirectDataAccess();
    controller = new StuddyBuddiesController();
    buddy = new StuddyBuddy();
    buddy.setName("User test");
    username = new Label();
    allRegistrationsText = new Label();
  }

  @Test
  public void testController_studdyBuddies() {
    //assertNotNull(this.dataAccess);
    assertNotNull(this.controller);
    assertNotNull(this.buddy);
    assertNotNull(this.username);
  }

  @Test
  public void testAddRegistrationButton() throws InterruptedException {
    Thread.sleep(5000);
      List<Window> beforeClick = Window.getWindows();
      Parent beforeClickRoot = null;
      for(Window window : beforeClick){
        beforeClickRoot = window.getScene().getRoot();
      }
      clickOn("#addRegistrationButton");
      try {
        Thread.sleep(5000);
      } catch (Exception e) {
        fail();
      }
      List<Window> afterClick = Window.getWindows();
      Parent afterClickRoot = null;
      for(Window window : afterClick){
        afterClickRoot = window.getScene().getRoot();
      }
      assertNotEquals(afterClickRoot, beforeClickRoot);
  }

  @Test
  public void testLogOutButton() throws InterruptedException {
    Thread.sleep(5000);
    List<Window> beforeClick = Window.getWindows();
    Parent beforeClickRoot = null;
    for(Window window : beforeClick){
      beforeClickRoot = window.getScene().getRoot();
    }
    clickOn("#logOut");
    try {
      Thread.sleep(5000);
    } catch (Exception e) {
      fail();
    }
    List<Window> afterClick = Window.getWindows();
    Parent afterClickRoot = null;
    for(Window window : afterClick){
      afterClickRoot = window.getScene().getRoot();
    }
    assertNotEquals(afterClickRoot, beforeClickRoot);
  }

  @Test
  public void testDisplay() {
    username.setText(buddy.getName());
    assertEquals("User test", username.getText()); // må trolig bruke server her egt
    String studdyBuddies = remoteDataAccess.getStuddyBuddies().toString();
    allRegistrationsText.setText(studdyBuddies);
    assertEquals(studdyBuddies, allRegistrationsText.getText());
  }

  @AfterEach
  public void stopWireMockServer() {
    wireMockServer.stop();
  }

}