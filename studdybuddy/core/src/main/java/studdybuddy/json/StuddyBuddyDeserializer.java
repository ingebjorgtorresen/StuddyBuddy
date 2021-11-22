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
   * Deserialize help-method.
   * (useful in StuddyBuddyRegistrationDeserializer)
   * format: { "Name": "...", Registrations: [...] }
   *
   * @param jsonNode node to deserialze.
   * @return deserialized StuddyBuddy objects.
   * @throws JsonProcessingException if problem with processing JsonNode.
   * @throws IOException if problem with input or output.
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
   * Deserialize method.
   *
   * @return adeserialized StuddyBuddy object.
   */
  @Override
  public StuddyBuddy deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = p.getCodec().readTree(p);
    return deserialize((JsonNode) treeNode);
  }
}
