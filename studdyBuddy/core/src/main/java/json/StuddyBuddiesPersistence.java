package json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.Writer;
import java.io.IOException;
import java.io.Reader;

import core.StuddyBuddies;

public class StuddyBuddiesPersistence {
  
    private ObjectMapper mapper;

    public StuddyBuddiesPersistence() {
        mapper = new ObjectMapper();
        mapper.registerModule(new StuddyModule());
    }

    public void writeStuddyBuddies(StuddyBuddies studdyBuddies, Writer writer) throws JsonGenerationException, JsonMappingException, IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, studdyBuddies);
    }

    public StuddyBuddies readStuddyBuddies(File file) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(file, StuddyBuddies.class);
    }

    public StuddyBuddies readStuddyBuddies(Reader reader) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(reader, StuddyBuddies.class);
    }    

}
