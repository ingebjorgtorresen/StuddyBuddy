module studdybuddy.restserver {
    requires transitive com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.core;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires org.slf4j;

    requires studdybuddy.core;

    opens studdybuddy.restserver to spring.beans, spring.context, spring.web;
}
