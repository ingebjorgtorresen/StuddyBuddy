package studdybuddy.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import studdybuddy.core.StuddyBuddyRegistration;

/**
 * Class for deserializing StuddyBuddyRegistration objects.
 */
public class StuddyBuddyRegistrationDeserializer extends JsonDeserializer<StuddyBuddyRegistration> {

  /**
   * Help-method for deserializing StuddyBuddyRegistration objects.
   *
   * @param node to deserialize
   * @return deserialized StuddyBuddyRegistration
   * @throws IOException if problem with input or output
   * @throws JsonProcessingException if problem with processing JsonNode
   */
  public StuddyBuddyRegistration deserialize(JsonNode node)
      throws IOException, JsonProcessingException {
    if (node instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) node;
      StuddyBuddyRegistration studdyBuddyRegistration = new StuddyBuddyRegistration();

      JsonNode dateNode = objectNode.get("Date");
      if (dateNode instanceof TextNode) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(dateNode.asText(), formatter);
        studdyBuddyRegistration.setDate(date);
      }

      JsonNode courseNode = objectNode.get("Course");
      if (courseNode instanceof TextNode) {
        studdyBuddyRegistration.setCourse(courseNode.asText());
      }

      JsonNode roomNode = objectNode.get("Room");
      if (courseNode instanceof TextNode) {
        studdyBuddyRegistration.setRoom(roomNode.asText());
      }

      JsonNode startNode = objectNode.get("Start time");
      if (startNode instanceof TextNode) {
        studdyBuddyRegistration.setStartTime(startNode.asText());
      }

      JsonNode endNode = objectNode.get("End time");
      if (endNode instanceof TextNode) {
        studdyBuddyRegistration.setEndTime(endNode.asText());
      }

      return studdyBuddyRegistration;
    }
    return null;
  }

  /**
   * Method for deserializing StuddyBuddyRegistration object.
   *
   * @param p Jasonparser to parse
   * @param ctxt the DeserializationCOntext  
   * @return a deserialized StuddyBuddyRegistration object
   * @throws JsonProcessingException if problem with processing JsonNode
   * @throws IOException if problem with input or output
   */
  @Override
  public StuddyBuddyRegistration deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = p.getCodec().readTree(p);
    return deserialize((JsonNode) treeNode);
  }

}
