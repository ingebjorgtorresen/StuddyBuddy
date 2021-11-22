package json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.StuddyBuddies;
import core.StuddyBuddy;
import java.io.IOException;

/**
 * Serializer for StuddyBudies object.
 */
public class StuddyBuddiesSerializer extends JsonSerializer<StuddyBuddies> {

    /**
     * format: { "Studdybuddies" : [ StuddyBuddy... ] }
     */

    /**
     * Method for writing an instance of StuddyBuddies as a json string to a json
     * generator.
     * 
     * @param buddies            StuddyBuddies object to be serialized
     * @param JGen               generator to use
     * @param serializerProvider serializerProvider to use
     */
    @Override
    public void serialize(StuddyBuddies buddies, JsonGenerator JGen, SerializerProvider serializerProvider)
            throws IOException {
        JGen.writeStartObject();
        JGen.writeArrayFieldStart("StuddyBuddies");
        for (StuddyBuddy buddy : buddies) {
            JGen.writeObject(buddy);
        }
        JGen.writeEndArray();
        JGen.writeEndObject();
    }

}