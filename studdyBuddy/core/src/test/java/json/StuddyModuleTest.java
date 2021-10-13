package json;

import org.junit.jupiter.api.Test;

public StuddyModuleTest {

    //TODO

    private static ObjectMapper mapper;

    @BeforeAll
    public void setUp(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new StuddyModule());
    }

    private final static String correctTextWhenTesting; // hente String/kode fra main-metode i StuddyModule

    @Test
    public void testSerializers(){
        // hente koden fra main-metoden i StuddyModule 
    }

    private void checkStuddyBuddyRegistration(StuddyBuddyRegistration registration, String text) {
        AssertEquals(text, registration.getText());
    }

    private void checkStuddyBuddyRegistration(StuddyBuddyRegistration registration1, StuddyBuddyRegistration registration2) {
        checkStuddyBuddyRegistration(registration1, registration2.getText());

    }

    @Test
    public void testDeserializers(){
        try {
            StuddyBuddyRegistration registration = mapper.readValue(correctTextWhenTesting, StuddyBuddyRegistration.class ); 
            // her sjekkers det div ved listen i todo-list, usiker på hvordan vi skal løse dette eller om vi trenger det
            checkStuddyBuddyRegistration(registration, ""); // "" må erstattes med det som er riktig
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testSerializersDeserializers() {
        StuddyBuddyRegistration registration = new StuddyBuddyRegistration();
        //oppretter en liste med registreringen, bruk kode fra main metoden i StuddyModule -> listen skal skrives til fil
        try {
            //SERIALIZER: skal ikke ikke ha assertEquals; skal opprette en string vha mapper
            String json = mapper.writeValueAsString(); // usikker på hva vi egt skal opprette her, forvirrende å "oversette" til vår app
            //DESERIALIZER: verdien som opprettes i serializer-delen av denne testen skal puttes inn i deserializer-delen av testen
            // oppretter en ny liste, som leses fra fil (mapper.readValue('verdien fra serializer', StuddyBuddyRegistration.class))
            StuddyBuddyRegistration registration2 = mapper.readFromFile(json, StuddyBuddyRegistration.class);
            checkStuddyBuddyRegistration(); // skal teste om det vi skriver til fil og det vi leser fra fil blir det samme
        } catch (JsonProcessingException e) {
            //TODO: handle exception
        }        

    }
}