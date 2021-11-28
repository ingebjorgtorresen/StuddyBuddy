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

  /* Method for writing an instance of StuddyBuddyRegistration
   * as an json string to a json generator.
   * Format: { "Room" : "...", "Course" : "...", "Start time" : "...", "End time" : "..." }
   * 
   * @param registration StuddyBuddyRegistration object to be serialized
   * @param jsonGenerator generator to use
   * @param serializerProvider serializerProvider to use
   * @throws IOException if problem with input or output
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