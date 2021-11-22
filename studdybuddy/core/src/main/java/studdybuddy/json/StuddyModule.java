package studdybuddy.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import studdybuddy.core.StuddyBuddies;
import studdybuddy.core.StuddyBuddy;
import studdybuddy.core.StuddyBuddyRegistration;

/**
 * Module with serializers and deserializers 
 * for all studdybuddy classes.
 */
@SuppressWarnings("serial")
public class StuddyModule extends SimpleModule {

  /**
   * Constructor for StuddyModule.
   */
  public StuddyModule() {
    super("StuddyModule", Version.unknownVersion());
    addSerializer(StuddyBuddy.class, new StuddyBuddySerializer());
    addDeserializer(StuddyBuddy.class, new StuddyBuddyDeserializer());

    addSerializer(StuddyBuddyRegistration.class, new StuddyBuddyRegistrationSerializer());
    addDeserializer(StuddyBuddyRegistration.class, new StuddyBuddyRegistrationDeserializer());
    
    addSerializer(StuddyBuddies.class, new StuddyBuddiesSerializer());
    addDeserializer(StuddyBuddies.class, new StuddyBuddiesDeserializer());
  }
}
