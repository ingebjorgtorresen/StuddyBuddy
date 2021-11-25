package studdybuddy.dataaccess;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;

public class RemoteDataAccessTest {

  private WireMockConfiguration config;
  private WireMockServer wireMockServer;
  private RemoteDataAccess dataAccess;
  private String buddies = "{\"StuddyBuddies\" : [ {\"Name\" : \"FirstBuddy\", \"Password\" : \"eple1234\", \"Registrations\" : [ {" +
      "\"Date\" : \"10/10/2022\",\"Room\" : \"A3-138\",\"Course\" : \"ITGK\",\"Start time\" : \"08:00\",\"End time\" : \"12:00\" } ]}, {" +
      "\"Name\" : \"SecondBuddy\", \"Password\" : \"123456789\", \"Registrations\" : [ {\"Date\" : \"06/03/2022\", \"Room\" : \"F1\", \"Course\" : \"Datamaskiner og digitalteknikk\"," +
      "\"Start time\" : \"14:15\", \"End time\" : \"16:00\"} ]} ]}";
  
  @BeforeEach
  public void startWireMockServerAndSetup() throws URISyntaxException {
    config = WireMockConfiguration.wireMockConfig().port(8080);
    wireMockServer = new WireMockServer(config.portNumber());
    wireMockServer.start();
    WireMock.configureFor("localhost", config.portNumber());
    dataAccess = new RemoteDataAccess(new URI("http://localhost:" + wireMockServer.port() + "/studdybuddy"));
  }

  @Test
  public void testGetStuddyBuddyNames() {
    stubFor(get(urlEqualTo("/studdybuddy"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json").withBody(buddies)
        )
    );
    StuddyBuddies buddies = dataAccess.getStuddyBuddies();
    List<String> names = new ArrayList<>();
    for (StuddyBuddy buddy : buddies) {
      String name = buddy.getName();
      names.add(name);
    }
    assertEquals(2, names.size());
    assertTrue(names.containsAll(List.of("FirstBuddy", "SecondBuddy")));
  }

  @Test
  public void testGetStuddyBuddyPassword() {
    stubFor(get(urlEqualTo("/studdybuddy"))
        .withHeader("Accept", equalTo("application/json"))
        .willReturn(aResponse().withStatus(200)
        .withHeader("Content-Type", "application/json").withBody(buddies)
        )
    );
    StuddyBuddies buddies = dataAccess.getStuddyBuddies();
    List<String> passwords = new ArrayList<>();
    for (StuddyBuddy buddy : buddies) {
      String password = buddy.getPassword();
      passwords.add(password);
    }
    assertEquals(2, passwords.size());
    assertTrue(passwords.containsAll(List.of("eple1234", "123456789")));
  }

  @AfterEach
  public void stopWireMockServer() {
    wireMockServer.stop();
  }
}
