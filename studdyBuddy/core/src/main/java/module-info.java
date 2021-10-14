module studdyBuddy.core {
    requires transitive com.fasterxml.jackson.databind;
    exports core;
    exports json;
    opens core to org.junit.jupiter.api;
}
