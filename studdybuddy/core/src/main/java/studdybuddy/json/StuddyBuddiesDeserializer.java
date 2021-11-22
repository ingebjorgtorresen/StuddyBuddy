package studdybuddy.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;

/**
 * Class for deserializing StuddyBuddies objects.
 */
public class StuddyBuddiesDeserializer extends JsonDeserializer<StuddyBuddies> {

  private StuddyBuddyDeserializer studdyBuddyeserializer = new StuddyBuddyDeserializer();

  @Override
  public StuddyBuddies deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  /**
   * Method for deserializing StuddyBuddies object.
   *
   * @param node JsonNode to deserialze.
   * @return deserialized StuddyBuddies object.
   * @throws JsonProcessingException if problem with processing JsonNode.
   * @throws IOException if problem with input or output.
   */
  public StuddyBuddies deserialize(JsonNode node) throws JsonProcessingException, IOException {
    if (node instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) node;
      StuddyBuddies buddies = new StuddyBuddies();
      JsonNode buddiesNode = objectNode.get("StuddyBuddies");

      if (buddiesNode instanceof ArrayNode) {
        for (JsonNode buddyNode : ((ArrayNode) buddiesNode)) {
          StuddyBuddy buddy = studdyBuddyeserializer.deserialize(buddyNode);
          if (buddy != null) {
            buddies.addStuddyBuddy(buddy);
          }
        }
      }
      return buddies;
    }
    return null;
  }
}
