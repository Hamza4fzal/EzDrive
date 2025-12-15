package com.example.carrentalsystem;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
    private static final String FILE_NAME = "bookings.csv";
    private static List<Booking> allBookings = new ArrayList<>();

    public static void loadBookings() {
        allBookings.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; } // Skip header

                String[] parts = line.split(",");
                // We now check for 7 parts because we added more data
                if (parts.length >= 6) {
                    // Note: We only strictly need Name, Car, and Dates for the availability logic
                    // So we parse those carefully.
                    // (For simple reading, we are just storing them in the list)
                    Booking b = new Booking(
                            parts[0], // Name
                            parts[1], // CNIC
                            parts[2], // Phone
                            parts[3], // Car
                            LocalDate.parse(parts[4]), // Pickup
                            LocalDate.parse(parts[5]), // Return
                            Double.parseDouble(parts[6]) // Price
                    );
                    allBookings.add(b);
                }
            }
        } catch (Exception e) {
            System.out.println("No previous database found or file format changed.");
        }
    }

    public static void saveBooking(Booking newBooking) {
        allBookings.add(newBooking);

        File file = new File(FILE_NAME);
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Write the Correct Headers
            if (!fileExists) {
                writer.write("Customer Name,CNIC,Phone,Car Model,Pick-up Date,Return Date,Total Price");
                writer.newLine();
            }

            writer.write(newBooking.toString());
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isCarAvailable(String carModel, LocalDate start, LocalDate end) {
        for (Booking b : allBookings) {
            if (b.getCarModel().equals(carModel)) {
                if (!start.isAfter(b.getReturnDate()) && !end.isBefore(b.getPickupDate())) {
                    return false;
                }
            }
        }
        return true;
    }
}