/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.model;

/**
 *
 * @author Janice
 */

public class Customer {
    // === Private Properties ===
    private int customerID;
    private String customerName;
    private String contactNumber;
    private String email;
    private String licenseNumber;

    // === Constructors ===
    public Customer() {
        // Empty constructor
    }

    public Customer(int customerID, String customerName, String contactNumber, String email, String licenseNumber) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.licenseNumber = licenseNumber;
    }

    // === Getters & Setters ===
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
