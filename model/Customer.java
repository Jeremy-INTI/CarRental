/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.model;

/**
 *
 * @author Janice
 */
import java.sql.Timestamp;

public class Customer {
    // === Private Properties ===
    private int customerID;
    private String customerName;
    private String contactNumber;
    private String email;
    private String licenseNumber;
    private Integer createdBy;          // can be null
    private Timestamp createdAt;        // SQL TIMESTAMP
    private Integer lastModifiedBy;     // can be null
    private Timestamp lastModifiedAt;   // SQL TIMESTAMP

    // === Constructors ===
    public Customer() {
        // Empty constructor
    }

    public Customer(int customerID, String customerName, String contactNumber, String email,
                    String licenseNumber, Integer createdBy, Timestamp createdAt,
                    Integer lastModifiedBy, Timestamp lastModifiedAt) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedAt = lastModifiedAt;
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Timestamp getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Timestamp lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
