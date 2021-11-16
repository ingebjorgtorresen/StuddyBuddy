module studdybuddy.ui {
    requires com.fasterxml.jackson.databind;
    requires studdybuddy.core;
    requires javafx.controls;
    requires javafx.fxml;

    opens studdybuddy.ui to javafx.graphics, javafx.fxml;
}
