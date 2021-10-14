package json;

import core.*;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


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
                        SerializerProvider serializerProvider) throws IOException {
    jGen.writeStartObject(); 
    jGen.writeStringField("Name", studdyBuddy.getName()); // endre 'start' til å navnet, hentet ut med .getName() (??)
    jGen.writeEndObject(); 
  }
}