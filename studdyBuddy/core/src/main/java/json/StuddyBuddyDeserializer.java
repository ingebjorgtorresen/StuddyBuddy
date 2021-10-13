package json;

import core.StuddyBuddy;

public class StuddyBuddyDeserializer extends JsonSerializer<StuddyBuddy> {

        /*
            formatet vi ønsker at StuddyBuddy-objektene skal se ut:
            {   
                "Name": "..."
            }
        */

//public class StuddyBuddyDeserializer<JsonParser> extends JsonDeserializer<TodoItem> {

    @Override
    public StuddyBuddy deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

// deserialize help-method (useful in StuddyBuddyRegistrationDeserializer)
public StuddyBuddy deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode) {
        ObjectNode objectNode = (ObjectNode) jsonNode;
        StuddyBuddy studdyBuddy = new StuddyBuddy(); 
        JsonNode nameNode = objectNode.get("Name");
        if ( nameNode instanceof TextNode){
            studdyBuddy.setName(((TextNode) nameNode).asText()); // usikker på om det er .setName() vi skal bruke
        }
        return studdyBuddy;
    }
    return null;
  }

}