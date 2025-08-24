/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.dao;

import carrental.model.Customer;
import carrental.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Janice
 */
public class CustomerDAO {
    // === INSERT ===
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO Customers (CustomerName, ContactNumber, Email, LicenseNumber, CreatedBy) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getContactNumber());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getLicenseNumber());
            
            if (customer.getCreatedBy() != null) {
                stmt.setInt(5, customer.getCreatedBy());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === UPDATE ===
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE Customers SET CustomerName=?, ContactNumber=?, Email=?, LicenseNumber=?, LastModifiedBy=? " +
                     "WHERE CustomerID=?";
            try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getContactNumber());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getLicenseNumber());
            
            if (customer.getLastModifiedBy() != null) {
                stmt.setInt(5, customer.getLastModifiedBy());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            
            stmt.setInt(6, customer.getCustomerID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === DELETE ===
    public boolean deleteCustomer(int customerID) {
        String sql = "DELETE FROM Customers WHERE CustomerID=?";
             try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === GET BY ID ===
    public Customer getCustomerById(int customerID) {
        String sql = "SELECT * FROM Customers WHERE CustomerID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCustomer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // === GET ALL ===
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                customers.add(mapResultSetToCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // === Helper Method to Map ResultSet → Customer Object ===
    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        Customer c = new Customer();
        c.setCustomerID(rs.getInt("CustomerID"));
        c.setCustomerName(rs.getString("CustomerName"));
        c.setContactNumber(rs.getString("ContactNumber"));
        c.setEmail(rs.getString("Email"));
        c.setLicenseNumber(rs.getString("LicenseNumber"));
        c.setCreatedBy((Integer) rs.getObject("CreatedBy"));
        c.setCreatedAt(rs.getTimestamp("CreatedAt"));
        c.setLastModifiedBy((Integer) rs.getObject("LastModifiedBy"));
        c.setLastModifiedAt(rs.getTimestamp("LastModifiedAt"));
        return c;
    }
}
