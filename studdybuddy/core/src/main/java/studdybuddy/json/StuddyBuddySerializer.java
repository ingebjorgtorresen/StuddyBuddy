package studdybuddy.json;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.core.StuddyBuddyRegistration;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class StuddyBuddySerializer extends JsonSerializer<StuddyBuddy> {

  /*
   * formatet vi ønsker at StuddyBuddy-objektene skal se ut: { "Name": "...",
   * "Registrations" : [...] }
   */

  @Override
  public void serialize(StuddyBuddy studdyBuddy, JsonGenerator jGen, SerializerProvider serializerProvider)
      throws IOException {
    jGen.writeStartObject();
    jGen.writeStringField("Name", studdyBuddy.getName());
    jGen.writeArrayFieldStart("Registrations");
    for (StuddyBuddyRegistration registration : studdyBuddy.getRegistrations()) {
      jGen.writeObject(registration);
    }
    jGen.writeEndArray();
    jGen.writeEndObject();
  }
}