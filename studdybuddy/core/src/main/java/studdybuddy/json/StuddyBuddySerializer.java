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

  /* Method for writing an instance of StuddyBuddy as a json string to an json generator. 
   * Format: { "Name": "...","Registrations" : [...] }
   * 
   * @param studdyBuddy to write
   * @param jsonGenerator generator to use
   * @param serializerProvider SerializerProvider to use
   * @throws IOException if problem with input or output
   */
  @Override
  public void serialize(StuddyBuddy studdyBuddy,
      JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("Name", studdyBuddy.getName());
    jsonGenerator.writeStringField("Password", studdyBuddy.getPassword());
    jsonGenerator.writeArrayFieldStart("Registrations");
    for (StuddyBuddyRegistration registration : studdyBuddy.getRegistrations()) {
      jsonGenerator.writeObject(registration);
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.writeEndObject();
  }
}