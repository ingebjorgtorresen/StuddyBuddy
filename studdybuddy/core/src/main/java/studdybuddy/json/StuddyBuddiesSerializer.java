package studdybuddy.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;

/**
 * Class for serializing StuddyBuddies objects.
 */
public class StuddyBuddiesSerializer extends JsonSerializer<StuddyBuddies> {

  /**
   * Method for writing an instance of StuddyBuddies as a json string to a json generator.
   * Format: { "Studdybuddies" : [ StuddyBuddy... ] }
   *
   * @param buddies StuddyBuddies object to be serialized
   * @param jsonGenerator generator to use
   * @param serializerProvider serializerProvider to use
   * @throws IOException if problem with input or output
   */
  @Override
  public void serialize(StuddyBuddies buddies, 
      JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeArrayFieldStart("StuddyBuddies");
    for (StuddyBuddy buddy : buddies) {
      jsonGenerator.writeObject(buddy);
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.writeEndObject();
  }

}
