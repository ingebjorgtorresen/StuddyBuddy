package json;

import core.*;

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

public class StuddyBuddyDeserializer extends JsonDeserializer<StuddyBuddy> {

    StuddyBuddyRegistrationDeserializer registrationDeserializer = new StuddyBuddyRegistrationDeserializer();

    /*
     * formatet vi ønsker at StuddyBuddy-objektene skal se ut: { "Name": "...",
     * Registrations: [...] }
     */

    // public class StuddyBuddyDeserializer<JsonParser> extends
    // JsonDeserializer<TodoItem> {

    // deserialize help-method (useful in StuddyBuddyRegistrationDeserializer)
    public StuddyBuddy deserialize(JsonNode jsonNode) throws JsonProcessingException, IOException {
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
                    StuddyBuddyRegistration registration = registrationDeserializer.deserialize(registrationNode);
                    if (registration != null) {
                        studdyBuddy.addRegistration(registration);
                    }
                }
            }
            return studdyBuddy;
        }
        return null;
    }

    @Override
    public StuddyBuddy deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        return deserialize((JsonNode) treeNode);
    }

}