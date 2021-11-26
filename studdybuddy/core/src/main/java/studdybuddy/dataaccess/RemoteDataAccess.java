package studdybuddy.dataaccess;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.json.StuddyBuddiesPersistence;

/**
 * Class for communcating with and accessing server. Has methods for GET, PUT, POST that uses
 * HttpRequest.
 */
public class RemoteDataAccess implements DataAccess {

  private final URI baseUri;
  private ObjectMapper mapper;

  /**
   * Constructor that sets base URI for gitpod and mapper.
   */
  public RemoteDataAccess(URI baseUri) {
    this.baseUri = baseUri;
    mapper = StuddyBuddiesPersistence.createObjectMapper();
  }

  /**
   * Getter for StuddyBuddies.
   *
   * @return buddies.
   */
  public StuddyBuddies getStuddyBuddies() {
    StuddyBuddies buddies = null;
    HttpRequest request = 
        HttpRequest.newBuilder(baseUri)
        .header("Accept", "application/json")
        .GET().build();
    try {
      final HttpResponse<String> response =
          HttpClient.newBuilder().build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      buddies = mapper.readValue(response.body(), StuddyBuddies.class);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return buddies;
  }

  private String encodeUriParam(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

  private URI studdybuddyUri(String name) {
    return baseUri.resolve("user").resolve(encodeUriParam(name));
  }

  /**
   * Gets the password URI.
   *
   * @param name of StuddyBuddy.
   * @return the password URI.
   */
  private URI passwordUri(String name) {
    return baseUri.resolve("user").resolve(encodeUriParam(name) + "/pw");
  }

  @Override
  public StuddyBuddy getStuddyBuddyByName(String name) {
    StuddyBuddy buddy;
    try {
      HttpRequest request =
          HttpRequest.newBuilder(studdybuddyUri(name))
          .header("Accept", "application/json")
          .GET().build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      buddy = mapper.readValue(responseString, StuddyBuddy.class);
      return buddy;
    } catch (IOException | InterruptedException e) {
      return null;
    }
  }

  /**
   * Method for sending a StudyBuddy object to server.
   *
   * @param buddy buddy to be saved.
   * @param buddies that buddy will be saved in.
   */
  @Override
  public void putStuddyBuddy(StuddyBuddy buddy) {
    try {
      String buddyAsString = mapper.writeValueAsString(buddy);
      HttpRequest request 
          = HttpRequest.newBuilder(studdybuddyUri(buddy.getName()))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .PUT(BodyPublishers.ofString(buddyAsString)).build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() != 200) {
        throw new IllegalStateException("Request was unsuccessfull");
      }
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Could not save user-object to server.");
    }
  }

  /**
   * Method for updating a StuddyBuddy object on server.
   *
   * @param buddy buddy to be saved.
   * @param buddies that buddy will be saved in.
   */
  @Override
  public void postStuddyBuddy(StuddyBuddy buddy) {
    try {
      String jsonString = mapper.writeValueAsString(buddy);
      HttpRequest request 
          = HttpRequest.newBuilder(studdybuddyUri(buddy.getName()))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(jsonString)).build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() != 200) {
        throw new IllegalStateException("Request was unsuccessfull");
      }
    } catch (InterruptedException | IOException e) {
      throw new IllegalArgumentException("Could not save user-object to server.");
    }
  }

  /**
   * Getter for password.
   *
   * @param name name of StuddyBuddy.
   * @param buddies to get from.
   */
  @Override
  public String getStuddyBuddyPasswordByName(String name) {
    try {
      HttpRequest request =
          HttpRequest.newBuilder(passwordUri(name))
          .header("Accept", "application/json")
          .GET().build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      return responseString;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
