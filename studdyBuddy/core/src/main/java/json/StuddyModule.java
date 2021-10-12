package json;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import studdyBuddy.core.StuddyBuddy;
import studdyBuddy.core.StuddyBuddyRegistration;

public class StuddyModule extends SimpleModule {
  private static final String NAME = "StuddyModule";
  private static final VersionUtil VERSION_UTIL = new VersionUtil() {
  };

  public StuddyModule() {
    super(NAME, VERSION_UTIL.version());
    addSerializer(StuddyBuddy.class, new StuddyBuddySerializer());
    addSerializer(StuddyBuddyRegistration.class, new StuddyBuddyRegistrationSerializer());
  }

  public static void main(String[] args){
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new StuddyModule());
  }
}