/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.model;

import java.util.Date;
/**
 *
 * @author Janice
 */
public class CarRental {
    private int rentalID;
    private int carID;
    private int customerID;
    private Date startDate;
    private Date endDate;
    private Integer rentDay;  // Nullable field
    private double totalCost;

    // --- Constructor with all fields ---
    public CarRental(int rentalID, int carID, int customerID, Date startDate, Date endDate, Integer rentDay, double totalCost) {
        this.rentalID = rentalID;
        this.carID = carID;
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentDay = rentDay;
        this.totalCost = totalCost;
    }

    // --- Default constructor ---
    public CarRental() {
    }

    // --- Getters and Setters ---
    public int getRentalID() {
        return rentalID;
    }

    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getRentDay() {
        return rentDay;
    }

    public void setRentDay(Integer rentDay) {
        this.rentDay = rentDay;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
