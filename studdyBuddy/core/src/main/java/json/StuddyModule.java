package json;

import core.StuddyBuddy;
import core.StuddyBuddyRegistration;

public class StuddyModule extends SimpleModule {
  private static final String NAME = "StuddyModule";
  private static final VersionUtil VERSION_UTIL = new VersionUtil() {
  };

  public StuddyModule() {
    super(NAME, VERSION_UTIL.version());
    addSerializer(StuddyBuddy.class, new StuddyBuddySerializer());
    addDeserializer(StuddyBuddy.class, new StuddyBuddyDeserializer());

    addSerializer(StuddyBuddyRegistration.class, new StuddyBuddyRegistrationSerializer());
    addDeserializer(StuddyBuddyRegistration.class, new StuddyBuddyRegistrationDeserializer());
  }

  public static void main(String[] args){
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new StuddyModule());
    StuddyBuddyRegistration registration = new StuddyBuddyRegistration();
    try {
      String json = mapper.writeValueAsString(registration);
      StuddyBuddyRegistration newRegistration = mapper.readValue(json, StuddyBuddyRegistration.class);
      for (StuddyBuddyRegistration reg : newRegistration){
        System.out.println(reg);
      }
    } catch (JsonProcessingException e){
      System.err.println("Didn´t work");
      e.printStackTrace();
    }
  }
}