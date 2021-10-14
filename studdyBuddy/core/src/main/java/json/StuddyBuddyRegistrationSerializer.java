package json;

import core.*;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class StuddyBuddyRegistrationSerializer extends JsonSerializer<StuddyBuddyRegistration> {

        /*
            formatet vi ønsker at StuddyBuddy-objektene skal se ut:
            {   
                "Registrations": [ ... ]
            }
        */

  @Override
  public void serialize(StuddyBuddyRegistration registration,
                        JsonGenerator jGen,
                        SerializerProvider serializerProvider) throws IOException {
    jGen.writeStartObject(); 
    jGen.writeArrayFieldStart("Registrations"); // ikke garantert at dette fungerer, i todo-list brukes .writeArrayFieldStart("... ")
    // skrive en metode for å hente ut de ulike registreringene til den aktuelle personen
    // Todo-list har brukt iterator til å gå gjennom, vi kan kanskje også gjøre det
    // kan være en for-løkke som går gjennom registreringene til personen (hvis vi lagrer det i en array og bruker iterator)
      // jGen.writeObject('regsitreringen'); 
    jGen.writeEndArray(); // ikke garantert at dette fungerer, i todo-list brukes .writeEndArray()
    jGen.writeEndObject();
  }
}