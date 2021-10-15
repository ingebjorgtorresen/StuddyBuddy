package json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.Writer;
import java.io.IOException;
import java.io.Reader;

import core.StuddyBuddy;

public class StuddyBuddiesPersistence {
    // skal endre denne til å ta inn StuddyBuddies objekt!
    private ObjectMapper mapper;

    public StuddyBuddiesPersistence() {
        mapper = new ObjectMapper();
        mapper.registerModule(new StuddyModule());
    }

    public void writeStuddyBuddy(StuddyBuddy studdyBuddy, Writer writer) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, studdyBuddy);
    }

    public StuddyBuddy readStuddyBuddy(File file) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(file, StuddyBuddy.class);
    }

    public StuddyBuddy readStuddyBuddy(Reader reader) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(reader, StuddyBuddy.class);
    }    

}
