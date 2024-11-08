module org.example.week3fuelconsumption {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.week3fuelconsumption to javafx.fxml;
    exports org.example.week3fuelconsumption;
}