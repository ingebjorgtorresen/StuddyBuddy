module studdyBuddy.core {
    requires transitive com.fasterxml.jackson.databind;
    requires java.net.http;
    exports core;
    exports json;
    exports dataaccess;
    opens core to org.junit.jupiter.api;
}
