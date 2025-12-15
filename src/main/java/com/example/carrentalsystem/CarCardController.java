package com.example.carrentalsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CarCardController {

    @FXML
    private ImageView carImage;

    @FXML
    private VBox VboxCard;

    @FXML
    private Label modelLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Button bookButton;

    private Car car; // Holds the data for this specific car

    // This method is called by MainController to set the data
    public void setData(Car car) {
        this.car = car;
        modelLabel.setText(car.getBrand() + " " + car.getModel());

        // Load the real image
        try {
            // This looks for the file name (e.g., "alto.png") in your resources folder
            Image image = new Image(getClass().getResourceAsStream(car.getImagePath()));
            carImage.setImage(image);
        } catch (Exception e) {
            System.out.println("Image not found for " + car.getModel());
        }
    }

    // --- NEW: Handle the "Book Now" Button Click ---
    @FXML
    private void onBookNowClick() {
        try {
            // 1. Load the Booking Page layout
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingView.fxml"));
            Parent root = loader.load();

            // 2. Get the controller for the Booking Page
            BookingController bookingController = loader.getController();

            // 3. Pass the car data (Model, Price, Image) to the Booking Page
            bookingController.setCarData(this.car);

            // 4. Show the new window
            Stage stage = new Stage();
            stage.setTitle("Booking - " + car.getBrand() + " " + car.getModel());
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading BookingView.fxml. Check if the file name is correct.");
        }
    }
}