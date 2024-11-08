package org.example.week3fuelconsumption;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Label distanceLabel;
    @FXML
    private Label fuelLabel;
    @FXML
    private Button calculateBtn;
    @FXML
    private Label resultLabel;
    @FXML
    private Label invalidInputLabel;
    @FXML
    private TextField distanceField;
    @FXML
    private TextField fuelField;
    @FXML
    private TableView<Result> resultsTable;
    @FXML
    private TableColumn<Result, Double> distanceColumn;
    @FXML
    private TableColumn<Result, Double> fuelColumn;
    @FXML
    private TableColumn<Result, Double> consumptionColumn;

    private ResourceBundle bundle;
    private ObservableList<Result> results;
    private Dotenv dotenv;

    @FXML
    public void initialize() {
        dotenv = Dotenv.load();
        loadLanguage("en", "EN");
        results = FXCollections.observableArrayList();
        resultsTable.setItems(results);
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
        fuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
        consumptionColumn.setCellValueFactory(new PropertyValueFactory<>("consumption"));
    }

    @FXML
    private void loadEnglish() {
        loadLanguage("en", "EN");
    }

    @FXML
    private void loadFrench() {
        loadLanguage("fr", "FR");
    }

    @FXML
    private void loadJapanese() {
        loadLanguage("ja", "JP");
    }

    @FXML
    private void loadFarsi() {
        loadLanguage("fa", "IR");
    }

    private void loadLanguage(String language, String country) {
        Locale locale = new Locale(language, country);
        bundle = ResourceBundle.getBundle("messages", locale);
        updateLabels();
    }

    private void updateLabels() {
        distanceLabel.setText(bundle.getString("distance.label"));
        fuelLabel.setText(bundle.getString("fuel.label"));
        calculateBtn.setText(bundle.getString("calculate.button"));
        resultLabel.setText(bundle.getString("result.label"));
        invalidInputLabel.setText(bundle.getString("invalid.input"));
    }

    @FXML
    private void calculateFuelConsumption() {
        try {
            double distance = Double.parseDouble(distanceField.getText());
            double fuel = Double.parseDouble(fuelField.getText());
            double consumption = (fuel / distance) * 100;
            resultLabel.setText(String.format(bundle.getString("result.label"), consumption));
            invalidInputLabel.setVisible(false);
            results.add(new Result(distance, fuel, consumption));
        } catch (NumberFormatException e) {
            invalidInputLabel.setVisible(true);
        }
    }

    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            String url = dotenv.get("DB_URL");
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static class Result {
        private final double distance;
        private final double fuel;
        private final double consumption;

        public Result(double distance, double fuel, double consumption) {
            this.distance = distance;
            this.fuel = fuel;
            this.consumption = consumption;
        }

        public double getDistance() {
            return distance;
        }

        public double getFuel() {
            return fuel;
        }

        public double getConsumption() {
            return consumption;
        }
    }
}