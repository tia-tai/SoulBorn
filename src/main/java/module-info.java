module com.example.soulborn {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;
    requires javafx.swing;

    opens com.example.soulborn to javafx.fxml;
    exports com.example.soulborn;
}