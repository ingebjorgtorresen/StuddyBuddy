module studdyBuddy.ui {
    requires studdyBuddy.core;
    requires javafx.controls;
    requires javafx.fxml;

    opens ui to javafx.graphics, javafx.fxml;
}
