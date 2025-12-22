module com.example.soulborn {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.soulborn to javafx.fxml;
    exports com.example.soulborn;
}