package dataaccess;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpResponse;
import core.*;
import json.StuddyModule;

/**
 * Class for communcating with and accessing server.
 * Has methods for GET, PUT, POST that uses HttpRequest.
 */
public class RemoteDataAcess implements DataAccess {

    // This fields are set to public to make it easier to access them in other classes
    public StuddyBuddy buddy;
    public StuddyBuddies buddies;
    public URI baseURI;

    /**
     * Constructor for setting default base URI for gitpod. 
     */
    public RemoteDataAcess() {
        this.baseURI = URI.create("http://localhost:6080/api/");
    }

    /**
     * Constructor for setting default base URI with custom port.
     * @param port
     */
    public RemoteDataAcess(String port) {
        this.baseURI = URI.create("http://localhost:" + port + "/api/");
    }

    /**
     * Method for getting a studdybuddy by its name from server.
     */
    @Override
    public StuddyBuddy getStuddyBuddyByName(String name) {
        buddy = null;
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(baseURI + "user/" + name))
            .header("Accept", "application/json").GET().build();
            final HttpResponse<String> response = HttpClient.newBuilder().build()
            .send(request, HttpResponse.BodyHandlers.ofString());
            final String responseString = response.body();
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new StuddyModule());
            this.buddy = mapper.readValue(responseString, StuddyBuddy.class);
            if(response.statusCode() == 404) { // HTTP 404 is a not found message, that means that the server could not fin what was asked for.
                throw new IllegalArgumentException("The user does not exist.");
            }
            return buddy;
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not get user by name: " + name +
            ". Something went wrong with the server.");
        }
    }

    /**
     * Method for getting a users password by its name on server.
     */
    @Override
    public String getStuddyBuddyPasswordByName(String name) {
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(baseURI + "/user" + name + "/password"))
            .header("Accept", "application/json").header("Content-Type", "application/json").GET().build();
            final HttpResponse<String> response = HttpClient.newBuilder().build()
            .send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200) { // HTTP 200 OK is code that indicates that the request has suceeded.
                throw new IllegalArgumentException("User does not exist.");
            }
            String responseString = response.body();
            return responseString;
        } catch (InterruptedException | IOException e) {
            throw new IllegalArgumentException("Could not load password from server.");
        }
    }

    /**
     * Method for sending a StudyBuddy object to server. 
     */
    @Override
    public void putStuddyBuddy(StuddyBuddy buddy) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new StuddyModule());
            String jsonString = mapper.writeValueAsString(buddy);
            HttpRequest request = HttpRequest.newBuilder(URI.create(baseURI + "/user" + buddy.getName() + "/password"))
            .header("Accept", "application/json").header("Content-Type", "application/json").PUT(BodyPublishers.ofString(jsonString)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build()
            .send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200) {
                throw new IllegalArgumentException("Could not save user-object to server.");
            }
        } catch (InterruptedException | IOException e) {
            throw new IllegalArgumentException("Could not save user-object to server.");
        }
        
    }

    /**
     * Method for updating a StuddyBuddy object on server.
     */
    @Override
    public void postStuddyBuddy(StuddyBuddy buddy) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new StuddyModule());
            String jsonString = mapper.writeValueAsString(buddy);
            HttpRequest request = HttpRequest.newBuilder(URI.create(baseURI + "/user" + buddy.getName() + "/password"))
            .header("Accept", "application/json").header("Content-Type", "application/json").POST(BodyPublishers.ofString(jsonString)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build()
            .send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200) {
                throw new IllegalArgumentException("Could not save user-object to server.");
            }
        } catch (InterruptedException | IOException e) {
            throw new IllegalArgumentException("Could not save user-object to server.");
        }    
    }
}
