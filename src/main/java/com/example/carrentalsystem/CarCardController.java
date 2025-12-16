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

        // 1. Set Text Data
        modelLabel.setText(car.getBrand() + " " + car.getModel());
        priceLabel.setText("PKR " + car.getPricePerDay());

        // 2. Load the Image
        try {
            if (car.getImagePath() != null) {
                Image image = new Image(getClass().getResourceAsStream(car.getImagePath()));
                carImage.setImage(image);
            }
        } catch (Exception e) {
            System.out.println("Image not found for " + car.getModel());
        }

        // 3. FIX: Style the VBox (Card Look)
        // This adds a white background, rounded corners, and a soft shadow
        VboxCard.setStyle("-fx-background-color: white;" +
                "-fx-background-radius: 15;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");
    }

    // --- Handle the "Book Now" Button Click ---
    @FXML
    private void onBookNowClick() {
        try {
            // Load the Booking View
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingView.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the car data
            BookingController bookingController = loader.getController();
            bookingController.setCarData(this.car);

            // Show the new window
            Stage stage = new Stage();
            stage.setTitle("Booking - " + car.getBrand() + " " + car.getModel());
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading BookingView.fxml. Check your file name!");
        }
    }
}