package json;

import core.*;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class StuddyBuddyRegistrationDeserializer extends JsonDeserializer<StuddyBuddyRegistration> {

    private StuddyBuddyDeserializer suddyBuddyDeserializer = new StuddyBuddyDeserializer();

    /*
        formatet vi ønsker at StuddyBuddy-objektene skal se ut:
        {   
            "Registrations": [ ... ]
        }
    */

    @Override
    public StuddyBuddyRegistration deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    if (treeNode instanceof ObjectNode){ // if treeNode is '{'
        ObjectNode objectNode = (ObjectNode) treeNode;
        StuddyBuddyRegistration studdyBuddyRegistration = new StuddyBuddyRegistration(); 
        JsonNode registrationsNode = objectNode.get("Registrations");
        if (registrationsNode instanceof ArrayNode){
            for (JsonNode regNode : ((ArrayNode) registrationsNode)) {
                StuddyBuddyRegistration registration = suddyBuddyDeserializer.deserialize(regNode);
                if(regNode != null){
                    studdyBuddyRegistration.addRegistration(regNode);
                }
            }
        }        
        return studdyBuddyRegistration;
    }
    return null;
  }

}
