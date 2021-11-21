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

    private final URI baseURI;
    private ObjectMapper mapper;

    /**
     * Constructor that sets base URI for gitpod and mapper.
     */
    public RemoteDataAccess(URI baseURI) {
        this.baseURI = baseURI;
        mapper = StuddyBuddiesPersistence.createObjectMapper();
    }
     
    public StuddyBuddies getStuddyBuddies(StuddyBuddies buddies) {
        if (buddies == null) {
             HttpRequest request = HttpRequest.newBuilder(baseURI)
                .header("Accept", "application/json").GET().build();
            try {
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
                buddies = mapper.readValue(response.body(), StuddyBuddies.class);
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
    public StuddyBuddy getStuddyBuddyByName(String name, StuddyBuddies buddies) {
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
    public void putStuddyBuddy(StuddyBuddy buddy, StuddyBuddies buddies) {
        System.out.println();
        System.out.println("Denne metoden kjøres");
        System.out.println();
        try {
            String byddyAsString = mapper.writeValueAsString(buddy);
            HttpRequest request = HttpRequest.newBuilder(studdybuddyURI(buddy.getName()))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .PUT(BodyPublishers.ofString(byddyAsString)).build();
            System.out.println("Funker før response");
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                HttpResponse.BodyHandlers.ofString());
            System.out.println("Funker etter response");
            String responseString = response.body();
            System.out.println();
            System.out.println("respnseString");
            System.out.println(responseString);
            System.out.println();
            StuddyBuddy added = mapper.readValue(responseString, StuddyBuddy.class);
            System.out.println();
            System.out.println("added");
            System.out.println(added);
            System.out.println();
            if(added != null) {
                buddies.putStuddyBuddy(buddy.getName());
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Could not save user-object to server.");
        }
    }

    /**
     * Method for updating a StuddyBuddy object on server.
     */
    @Override
    public void postStuddyBuddy(StuddyBuddy buddy, StuddyBuddies buddies) {
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
    public String getStuddyBuddyPasswordByName(String name, StuddyBuddies buddies) {
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
