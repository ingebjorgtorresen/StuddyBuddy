package json;

import core.*;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class StuddyBuddiesSerializer extends JsonSerializer<StuddyBuddies> {

/*
    Thw format that we want to save the information on
    {   
        "StuddyBuddies" : [...]             [...] = List of StuddyBuddy object
    }
*/

    @Override
    public void serialize(StuddyBuddies value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject(); 
        gen.writeArrayFieldStart("StuddyBuddies");
        for(StuddyBuddy studdyBuddy : value.getStuddyBuddies()) {
            gen.writeObject(studdyBuddy);
        }
        gen.writeEndArray();
        gen.writeEndObject(); 
    }
        
}


