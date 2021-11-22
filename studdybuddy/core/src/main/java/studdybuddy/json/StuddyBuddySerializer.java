package studdybuddy.json;

<<<<<<< HEAD:studdyBuddy/core/src/main/java/json/StuddyBuddySerializer.java
import core.StuddyBuddy;
import core.StuddyBuddyRegistration;
=======
import studdybuddy.core.*;
>>>>>>> c90ec2bc42f117c191c6201f95bb5f09a8e1ca68:studdybuddy/core/src/main/java/studdybuddy/json/StuddyBuddySerializer.java
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