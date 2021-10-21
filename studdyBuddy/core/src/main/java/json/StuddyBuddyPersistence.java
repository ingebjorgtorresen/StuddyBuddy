package json;

import core.*;
import java.io.IOException;
import java.io.Writer;
import java.io.Reader;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StuddyBuddyPersistence {

    private ObjectMapper mapper = new ObjectMapper();

    public StuddyBuddyPersistence() {
        mapper.registerModule(new StuddyModule());
    }

    public void writeStuddyBuddy(StuddyBuddy buddy, Writer writer) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, buddy);
    }

    public StuddyBuddy readStuddyBuddy(Reader reader) throws IOException {
        return mapper.readValue(reader, StuddyBuddy.class);
    }

}
