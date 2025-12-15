package com.example.carrentalsystem;

import java.time.LocalDate;

public class Booking {
    private String customerName;
    private String cnic;         // NEW
    private String phone;        // NEW
    private String carModel;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private double totalPrice;   // NEW

    // Updated Constructor with ALL fields
    public Booking(String customerName, String cnic, String phone, String carModel, LocalDate pickupDate, LocalDate returnDate, double totalPrice) {
        this.customerName = customerName;
        this.cnic = cnic;
        this.phone = phone;
        this.carModel = carModel;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.totalPrice = totalPrice;
    }

    public String getCarModel() { return carModel; }
    public LocalDate getPickupDate() { return pickupDate; }
    public LocalDate getReturnDate() { return returnDate; }

    // Updated CSV format to include everything
    @Override
    public String toString() {
        return customerName + "," + cnic + "," + phone + "," + carModel + "," + pickupDate + "," + returnDate + "," + totalPrice;
    }
}