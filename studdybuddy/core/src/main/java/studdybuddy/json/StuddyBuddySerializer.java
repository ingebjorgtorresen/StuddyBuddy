package studdybuddy.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.core.StuddyBuddyRegistration;

/**
 * Class for serializing StuddyBuddy objects.
 */
public class StuddyBuddySerializer extends JsonSerializer<StuddyBuddy> {

  /*
   * Formatet vi ønsker at StuddyBuddy-objektene skal se ut:
   * { "Name": "...",
   * "Registrations" : [...] }
   */
  @Override
  public void serialize(StuddyBuddy studdyBuddy,
      JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("Name", studdyBuddy.getName());
    jsonGenerator.writeArrayFieldStart("Registrations");
    for (StuddyBuddyRegistration registration : studdyBuddy.getRegistrations()) {
      jsonGenerator.writeObject(registration);
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.writeEndObject();
  }
}