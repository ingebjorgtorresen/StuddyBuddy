package studdybuddy.json;

import studdybuddy.core.*;
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