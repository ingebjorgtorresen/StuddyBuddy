package json;

import core.*;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class StuddyBuddyRegistrationDeserializer extends JsonDeserializer<StuddyBuddyRegistration> {

    /*
        formatet vi ønsker at StuddyBuddy-objektene skal se ut:
        {   
            "Registrations": [ ... ]
        }
    */

    public StuddyBuddyRegistration deserialize(JsonNode node) throws IOException, JsonProcessingException {
    if (node instanceof ObjectNode){
        ObjectNode objectNode = (ObjectNode) node;
        StuddyBuddyRegistration studdyBuddyRegistration = new StuddyBuddyRegistration(); 
        
        JsonNode courseNode = objectNode.get("Course");
        if (courseNode instanceof TextNode){
            studdyBuddyRegistration.setCourse(courseNode.asText());
        }
        
        JsonNode roomNode = objectNode.get("Room");
        if (courseNode instanceof TextNode){
            studdyBuddyRegistration.setRoom(roomNode.asText());
        }    

        JsonNode startNode = objectNode.get("Start time");
        if (startNode instanceof TextNode){
            studdyBuddyRegistration.setStartTime(startNode.asText());
        } 

        JsonNode endNode = objectNode.get("End time");
        if (endNode instanceof TextNode){
            studdyBuddyRegistration.setEndTime(endNode.asText());
        }

        return studdyBuddyRegistration;
    }
    return null;
  }

    @Override
    public StuddyBuddyRegistration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        return deserialize((JsonNode) treeNode);
    }

}
