module studdyBuddy.ui {
    requires com.fasterxml.jackson.databind;
    requires studdyBuddy.core;
    requires javafx.controls;
    requires javafx.fxml;

    opens ui to javafx.graphics, javafx.fxml;
}
