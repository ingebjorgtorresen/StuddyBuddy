package studdybuddy.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import studdybuddy.core.StuddyBuddyRegistration;

/**
 * Class for serializing StuddyBuddyRegistration objects.
 */
public class StuddyBuddyRegistrationSerializer extends JsonSerializer<StuddyBuddyRegistration> {

  /*
   * format: { "Room" : "...",
   * "Course" : "...", "Start time" : "...", "End time" : "..." }
   */
  @Override
  public void serialize(StuddyBuddyRegistration registration,
      JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("Date", registration.getDate());
    jsonGenerator.writeStringField("Room", registration.getRoom());
    jsonGenerator.writeStringField("Course", registration.getCourse());
    jsonGenerator.writeStringField("Start time", registration.getStartTime());
    jsonGenerator.writeStringField("End time", registration.getEndTime());
    jsonGenerator.writeEndObject();
  }
}