package json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;



class TodoItemDeserializer extends JsonDeserializer<TodoItem> {

    @Override
    public TodoItem deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    if (treeNode instanceOf ObjectNode){ // if treeNode is '{'
        ObjectNode objectNode = (ObjectNode) treeNode;
        StuddyBuddy studdyBuddy = new StuddyBuddy(); 
        JsonNode nameNode = objectNode.get("Name");
        if ( nameNode instanceOf TextNode){
            studdyBuddy.setName(((TextNode) nameNode).asText()); // usikker på om det er .setName() vi skal bruke
        }
        return studdyBuddy;
    }
        return deserialize((JsonNode) treeNode);
  }

}