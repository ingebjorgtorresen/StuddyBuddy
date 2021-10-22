package json;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import json.StuddyBuddyPersistence;

public class StuddyModuleTest {
    
    private static ObjectMapper mapper = new ObjectMapper();
    private StuddyBuddyPersistence persistence = new StuddyBuddyPersistence();
    
    @BeforeAll
    public static void setup() {
    }

    
}
