package json;

import core.StuddyBuddy;

public class StuddyBuddySerializer extends JsonSerializer<StuddyBuddy> {

        /*
            formatet vi ønsker at StuddyBuddy-objektene skal se ut:
            {   
                "Name": "..."
            }
        */

  @Override
  public void serialize(StuddyBuddy studdyBuddy,
                        JsonGenerator jGen,
                        SerializerProvider serializerProvider) {
    jGen.writeStartObject(); 
    jGen.writeStringField("Name", studdyBuddy.getName()); // endre 'start' til å navnet, hentet ut med .getName() (??)
    jGen.writeEndObject(); 
  }
}