package com.example.carrentalsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.time.LocalDate; // Needed for dates
import java.time.temporal.ChronoUnit; // Required for calculating days

public class BookingController {

    // --- Left Side (Car Info) ---
    @FXML
    private Label carModelLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private ImageView selectedCarImage;

    // --- Right Side (User Input) ---
    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField cnicField;

    @FXML
    private DatePicker datePicker;       // Pick-up Date

    @FXML
    private DatePicker returnDatePicker; // Return Date

    @FXML
    private Button confirmButton;

    private Car selectedCar; // To hold the car data passed from the Main Page

    // --- Method to Receive Data from Page 1 ---
    public void setCarData(Car car) {
        this.selectedCar = car;

        // Update the labels on the screen
        carModelLabel.setText(car.getBrand() + " " + car.getModel());
        totalPriceLabel.setText("$" + car.getPricePerDay() + " / day");

        // Try to load the image safely
        try {
            // Note: This assumes you have the images in the resources folder!
            selectedCarImage.setImage(new Image(getClass().getResourceAsStream(car.getImagePath())));
        } catch (Exception e) {
            System.out.println("Could not load image: " + car.getImagePath());
        }
    }

    // --- Initialize Button Logic ---
    @FXML
    public void initialize() {
        confirmButton.setOnAction(event -> handleBooking());
    }

    private void handleBooking() {
        // 1. Validation: Check if fields are empty
        if (nameField.getText().isEmpty() || cnicField.getText().isEmpty() ||
                datePicker.getValue() == null || returnDatePicker.getValue() == null) {
            showAlert("Error", "Please fill in all fields and select both dates!");
            return;
        }

        LocalDate start = datePicker.getValue();
        LocalDate end = returnDatePicker.getValue();
        String carName = selectedCar.getModel();

        // 2. RELOAD DATABASE (Ensure we have the latest list from the file)
        BookingSystem.loadBookings();

        // 3. CHECK AVAILABILITY (The Algorithm)
        if (!BookingSystem.isCarAvailable(carName, start, end)) {
            showAlert("Unavailable", "Sorry! The " + carName + " is already booked for these dates.");
            return; // STOP HERE. Do not proceed to payment.
        }

        // 4. Calculate Number of Days
        long days = ChronoUnit.DAYS.between(start, end);

        // Check if return date is invalid (before pickup date)
        if (days < 1) {
            showAlert("Error", "Return date must be after the Pick-up date!");
            return;
        }

        // 5. Calculate Final Price
        double finalPrice = days * selectedCar.getPricePerDay();

        // 6. SAVE TO DATABASE (Excel CSV)
        // 6. SAVE TO DATABASE (Updated to pass all data)
        Booking newBooking = new Booking(
                nameField.getText(),
                cnicField.getText(),
                phoneField.getText(),
                carName,
                start,
                end,
                finalPrice
        );

        BookingSystem.saveBooking(newBooking);
        // 7. Create the Receipt Message
        String billDetails = "--- OFFICIAL RECEIPT ---\n" +
                "Customer: " + nameField.getText() + "\n" +
                "CNIC: " + cnicField.getText() + "\n" +
                "Phone: " + phoneField.getText() + "\n" +
                "--------------------------\n" +
                "Car: " + selectedCar.getBrand() + " " + selectedCar.getModel() + "\n" +
                "Pick-up Date: " + start + "\n" +
                "Return Date: " + end + "\n" +
                "Total Days: " + days + "\n" +
                "--------------------------\n" +
                "TOTAL AMOUNT: $" + finalPrice + "\n\n" +
                "(Data saved to bookings.csv)";

        // 8. Show Success Popup
        showAlert("Booking Confirmed", billDetails);

        // 9. Close the Window
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    // Helper method to show alerts easily
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}