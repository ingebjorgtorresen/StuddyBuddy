package studdybuddy.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
<<<<<<< HEAD:studdyBuddy/core/src/main/java/json/StuddyBuddiesDeserializer.java
import core.StuddyBuddies;
import core.StuddyBuddy;
import java.io.IOException;
=======

import studdybuddy.core.*;
>>>>>>> c90ec2bc42f117c191c6201f95bb5f09a8e1ca68:studdybuddy/core/src/main/java/studdybuddy/json/StuddyBuddiesDeserializer.java

public class StuddyBuddiesDeserializer extends JsonDeserializer<StuddyBuddies> {

    private StuddyBuddyDeserializer studdyBuddyeserializer = new StuddyBuddyDeserializer();

    @Override
    public StuddyBuddies deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

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
