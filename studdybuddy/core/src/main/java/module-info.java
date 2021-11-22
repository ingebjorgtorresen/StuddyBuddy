module studdybuddy.core {
    requires transitive com.fasterxml.jackson.databind;
    requires java.net.http;
    exports studdybuddy.core;
    exports studdybuddy.json;
    exports studdybuddy.dataaccess;
    opens studdybuddy.core to org.junit.jupiter.api;
}
