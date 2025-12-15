package com.example.carrentalsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox; // Import VBox since your root is a VBox
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private TilePane carContainer; // Must match fx:id in MainView.fxml

    private List<Car> carList; // Our "Database" of cars

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // This method runs AUTOMATICALLY when the app starts.

        // 1. Initialize our list
        carList = new ArrayList<>();

        // 2. Add some dummy data (We can comment this out later or move it)
        carList.add(new Car("1", "Toyota", "Corolla", 6000, "corolla.jpg"));
        carList.add(new Car("2", "BMW", "X5", 20000, "bmw.jpg"));
        carList.add(new Car("3", "Kia", "Sportage", 7500, "sportage.jpg"));
        carList.add(new Car("4", "Suzuki", "Alto", 4000, "alto.jpg"));
        carList.add(new Car("5", "Honda", "City", 6500, "city.jpg"));
        carList.add(new Car("6", "Hyundai", "Tucson", 7500, "Tucson.jpg"));
        carList.add(new Car("7", "MG", "HS", 6500, "HS.jpg"));
        carList.add(new Car("8", "Changan", "Alsvin", 6500, "Alsvin.jpg"));
        carList.add(new Car("9", "Suzuki", "Swift", 5500, "Swift.jpg"));
        carList.add(new Car("10", "Kia", "Picanto", 5500, "Picanto.jpg"));
        carList.add(new Car("11", "Haval", "H6", 10000, "H6.jpg"));
        carList.add(new Car("12", "Toyota", "Hilux", 15000, "Hilux.jpg"));
        carList.add(new Car("13", "Suzuki", "Wagon R", 4000, "Wagon R.jpg"));
        carList.add(new Car("14", "Toyota", "Yaris", 7500, "Yaris.jpg"));
        carList.add(new Car("15", "Toyota", "Vitz", 6500, "Vitz.jpg"));
        carList.add(new Car("16", "Honda", "Brv", 7500, "Brv.jpg"));
        carList.add(new Car("17", "Suzuki", "bolan", 3500, "bolan.jpg"));
        carList.add(new Car("18", "Toyota", "Hiace", 8000, "Hiace.jpg"));

        // 3. Display the cars on the screen
        try {
            for (Car car : carList) {
                // Load the "Car Card" design we made earlier
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CarCard.fxml"));

                // Create the actual node (the VBox) from the design
                VBox cardBox = fxmlLoader.load();

                // Get the controller for this SPECIFIC card so we can set its data
                CarCardController cardController = fxmlLoader.getController();
                cardController.setData(car);

                // Add the card to the dashboard grid
                carContainer.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}