package json; 

import com.fasterxml.jackson.databind.ObjectMapper;

import studdyBuddy.core.StuddyBuddy;
import studdyBuddy.core.StuddyBuddyRegistration;

public StuddyModuleTest {

    //TODO

    private static ObjectMapper mapper;

    @BeforeAll
    public void setUp(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new StuddyModule());
    }

    @Test
    public void testSerializers(){
        // hente koden fra main-metoden i StuddyModule 
    }
}