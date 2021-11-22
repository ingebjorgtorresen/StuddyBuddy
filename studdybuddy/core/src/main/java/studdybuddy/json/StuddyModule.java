package studdybuddy.json;

<<<<<<< HEAD:studdyBuddy/core/src/main/java/json/StuddyModule.java
import core.StuddyBuddy;
import core.StuddyBuddyRegistration;
import core.StuddyBuddies;
=======
import studdybuddy.core.*;
>>>>>>> c90ec2bc42f117c191c6201f95bb5f09a8e1ca68:studdybuddy/core/src/main/java/studdybuddy/json/StuddyModule.java
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

@SuppressWarnings("serial")
public class StuddyModule extends SimpleModule {
  private static final String NAME = "StuddyModule";

  public StuddyModule() {
    super(NAME, Version.unknownVersion());
    addSerializer(StuddyBuddy.class, new StuddyBuddySerializer());
    addDeserializer(StuddyBuddy.class, new StuddyBuddyDeserializer());

    addSerializer(StuddyBuddyRegistration.class, new StuddyBuddyRegistrationSerializer());
    addDeserializer(StuddyBuddyRegistration.class, new StuddyBuddyRegistrationDeserializer());
    
    addSerializer(StuddyBuddies.class, new StuddyBuddiesSerializer());
    addDeserializer(StuddyBuddies.class, new StuddyBuddiesDeserializer());

  }

}