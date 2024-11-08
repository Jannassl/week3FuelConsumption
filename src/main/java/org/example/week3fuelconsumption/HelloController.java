package org.example.week3fuelconsumption;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    private ResourceBundle bundle;

    @FXML
    public void initialize() {
        loadLanguage("en", "EN");
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
        } catch (NumberFormatException e) {
            invalidInputLabel.setVisible(true);
        }
    }
}