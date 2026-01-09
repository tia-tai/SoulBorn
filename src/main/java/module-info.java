module com.example.soulborn {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;


    opens com.example.soulborn to javafx.fxml;
    exports com.example.soulborn;
}