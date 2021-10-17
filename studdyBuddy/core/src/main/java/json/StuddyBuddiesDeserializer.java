package json;

import java.io.IOException;

import core.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import core.StuddyBuddies;

public class StuddyBuddiesDeserializer extends JsonDeserializer<StuddyBuddies> {

    StuddyBuddyDeserializer buddyDeserializer = new StuddyBuddyDeserializer();

    @Override
    public StuddyBuddies deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        return deserialize((JsonNode) treeNode);
    }

        // help method for deserializing that takes in a Json Node parameter
    public StuddyBuddies deserialize(JsonNode jsonNode) throws JsonProcessingException, IOException {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            StuddyBuddies studdyBuddies = new StuddyBuddies();
            JsonNode buddiesNode = objectNode.get("StuddyBuddies");

            if (buddiesNode instanceof ArrayNode){
                for (JsonNode buddyNode : (ArrayNode) buddiesNode) {
                    StuddyBuddy buddy = buddyDeserializer.deserialize(buddyNode);
                    if (buddy != null) {
                        studdyBuddies.addStuddyBuddy(buddy);
                    }
                }
            }
            return studdyBuddies;
        }
        return null;
    }

}
