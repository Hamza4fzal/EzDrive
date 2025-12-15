package com.example.carrentalsystem;

public class Car {
    // 1. DATA (Attributes)
    private String carId;
    private String brand;
    private String model;
    private double pricePerDay;
    private String imagePath;
    private boolean isAvailable;

    // 2. CONSTRUCTOR (The critical part!)
    // This allows you to say: new Car("1", "Toyota", "Corolla", 50.0, "img.png")
    public Car(String carId, String brand, String model, double pricePerDay, String imagePath) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.imagePath = imagePath;
        this.isAvailable = true; // Default to available
    }

    // 3. GETTERS (So the UI can read the data)
    public String getCarId() { return carId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getPricePerDay() { return pricePerDay; }
    public String getImagePath() { return imagePath; }
}