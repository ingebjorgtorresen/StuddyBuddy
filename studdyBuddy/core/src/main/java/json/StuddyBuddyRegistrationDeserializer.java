package json;

import core.StuddyBuddyRegistration;

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