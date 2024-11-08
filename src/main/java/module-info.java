module org.example.week3fuelconsumption {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires dotenv.java;

    opens org.example.week3fuelconsumption to javafx.fxml;
    exports org.example.week3fuelconsumption;
}