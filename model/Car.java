/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.model;

/**
 *
 * @author Janice
 */
public class Car {
    private int carID;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private String color;
    private double rate;
    private String rentalStatus; // "Available", "Rented", "Maintenance"

    // --- Constructor with all fields ---
    public Car(int carID, String make, String model, int year, String licensePlate,
               String color, double rate, String rentalStatus) {
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.color = color;
        this.rate = rate;
        this.rentalStatus = rentalStatus;
    }

    // --- Default constructor ---
    public Car() {
    }

    // --- Getters and Setters ---
    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}
