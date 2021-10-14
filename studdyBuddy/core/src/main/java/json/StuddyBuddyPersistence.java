package json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.Writer;
import java.io.IOException;

import core.StuddyBuddy;

public class StuddyBuddyPersistence {
    
    private ObjectMapper mapper;

    public StuddyBuddyPersistence() {
        mapper = new ObjectMapper();
        mapper.registerModule(new StuddyModule());
    }

    public void writeStuddyBuddy(StuddyBuddy studdyBuddy, Writer writer) {
        //TODO
    }

    public StuddyBuddy readStuddyBuddy(File file) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(file, StuddyBuddy.class);
    }

}
