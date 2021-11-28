package studdybuddy.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.core.StuddyBuddyRegistration;

/**
 * Class for deserializing StuddyBuddy objects.
 */
public class StuddyBuddyDeserializer extends JsonDeserializer<StuddyBuddy> {

  StuddyBuddyRegistrationDeserializer registrationDeserializer 
      = new StuddyBuddyRegistrationDeserializer();

  /**
   * Help-Method for deserializing StuddyBuddy object.
   * (useful in StuddyBuddyRegistrationDeserializer)
   *
   * @param jsonNode node to deserialze
   * @return deserialized StuddyBuddy objects
   * @throws JsonProcessingException if problem with processing JsonNode
   * @throws IOException if problem with input or output
   */
  public StuddyBuddy deserialize(JsonNode jsonNode) 
      throws JsonProcessingException, IOException {
    if (jsonNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) jsonNode;
      StuddyBuddy studdyBuddy = new StuddyBuddy();
      
      JsonNode nameNode = objectNode.get("Name");
      if (nameNode instanceof TextNode) {
        studdyBuddy.setName((nameNode).asText());
      }

      JsonNode passwordNode = objectNode.get("Password");
      if (passwordNode instanceof TextNode) {
        studdyBuddy.setPassword((passwordNode).asText());
      }

      JsonNode registrationsNode = objectNode.get("Registrations");
      if (registrationsNode instanceof ArrayNode) {
        for (JsonNode registrationNode : (ArrayNode) registrationsNode) {
          StuddyBuddyRegistration registration
              = registrationDeserializer.deserialize(registrationNode);
          if (registration != null) {
            studdyBuddy.addRegistration(registration);
          }
        }
      }
      return studdyBuddy;
    }
    return null;
  }

  /**
   * Method for deserializing StuddyBuddy object.
   *
   * @param p Jasonparser to parse
   * @param ctxt the DeserializationCOntext  
   * @return a deserialized StuddyBuddy object
   * @throws JsonProcessingException if problem with processing JsonNode
   * @throws IOException if problem with input or output
   */
  @Override
  public StuddyBuddy deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = p.getCodec().readTree(p);
    return deserialize((JsonNode) treeNode);
  }
}
