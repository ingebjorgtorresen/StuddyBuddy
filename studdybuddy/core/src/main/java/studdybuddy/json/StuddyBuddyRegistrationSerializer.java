package studdybuddy.json;

import studdybuddy.core.StuddyBuddyRegistration;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class StuddyBuddyRegistrationSerializer extends JsonSerializer<StuddyBuddyRegistration> {

  /*
   * formatet vi ønsker at StuddyBuddy-objektene skal se ut: { "Room" : "...",
   * "Course" : "...", "Start time" : "...", "End time" : "..." }
   */

  @Override
  public void serialize(StuddyBuddyRegistration registration, JsonGenerator jGen, SerializerProvider serializerProvider)
      throws IOException {
    jGen.writeStartObject();
    jGen.writeStringField("Date", registration.getDate());
    jGen.writeStringField("Room", registration.getRoom());
    jGen.writeStringField("Course", registration.getCourse());
    jGen.writeStringField("Start time", registration.getStartTime());
    jGen.writeStringField("End time", registration.getEndTime());
    jGen.writeEndObject();
  }
}