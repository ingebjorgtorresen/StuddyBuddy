package studdybuddy.dataaccess;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpResponse;
import studdybuddy.core.*;
import studdybuddy.json.StuddyBuddiesPersistence;

/**
 * Class for communcating with and accessing server. Has methods for GET, PUT,
 * POST that uses HttpRequest.
 */
public class RemoteDataAccess implements DataAccess {

    private StuddyBuddies buddies;
    private final URI baseURI;
    private ObjectMapper mapper;

    /**
     * Constructor that sets base URI for gitpod and mapper.
     */
    public RemoteDataAccess(URI baseURI) {
        this.baseURI = baseURI;
        mapper = StuddyBuddiesPersistence.createObjectMapper();
    }
     
    public StuddyBuddies getStuddyBuddies() {
        if (buddies == null) {
             HttpRequest request = HttpRequest.newBuilder(baseURI)
                .header("Accept", "application/json").GET().build();
            try {
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
                this.buddies = mapper.readValue(response.body(), StuddyBuddies.class);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return buddies;
    }
    
    private String URIParam(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    private URI studdybuddyURI(String name) {
        return baseURI.resolve("user").resolve(URIParam(name));
    }

    @Override
    public StuddyBuddy getStuddyBuddyByName(String name) {
        StuddyBuddy buddy;
        try {
            HttpRequest request = HttpRequest.newBuilder(studdybuddyURI(name))
            .header("Accept", "application/json").GET().build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            System.out.println("getStuddyBuddy("+ name + ") response: " + responseString);
            buddy = mapper.readValue(responseString, StuddyBuddy.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return buddy;
    }

    /**
     * Method for sending a StudyBuddy object to server.
     */
    @Override
    public void putStuddyBuddy(StuddyBuddy buddy) {
        try {
            String jsonString = mapper.writeValueAsString(buddy);
            HttpRequest request = HttpRequest.newBuilder(studdybuddyURI(buddy.getName()))
                .header("Accept", "application/json").header("Content-Type", "application/json")
                .PUT(BodyPublishers.ofString(jsonString)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            Boolean added = mapper.readValue(responseString, Boolean.class);
            if(added != null) {
                buddies.putStuddyBuddy(buddy.getName());
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
            String jsonString = mapper.writeValueAsString(buddy);
            HttpRequest request = HttpRequest.newBuilder(studdybuddyURI(buddy.getName()))
                .header("Accept", "application/json").header("Content-Type", "application/json")
                // only thing different frokm putStuddyBuddy is that this uses POST method
                .POST(BodyPublishers.ofString(jsonString)).build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            Boolean added = mapper.readValue(responseString, Boolean.class);
            if(added != null) {
                buddies.putStuddyBuddy(buddy.getName());
            }
        } catch (InterruptedException | IOException e) {
            throw new IllegalArgumentException("Could not save user-object to server.");
        }
    }
        
    private URI passwordURI(String name) {
        return baseURI.resolve("user").resolve(URIParam(name)).resolve("pw");
    }

    @Override
    public String getStuddyBuddyPasswordByName(String name) {
        try {
            HttpRequest request = HttpRequest.newBuilder(passwordURI(name))
            .header("Accept", "application/json").GET().build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            System.out.println("getStuddyBuddyPasswordByName("+ name + ") response: " + responseString);
            return responseString;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
