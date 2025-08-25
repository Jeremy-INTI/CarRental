/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.dao;

import carrental.model.CarRental;
import carrental.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Janice
 */
public class CarRentalDAO {
     // --- Get all rentals ---
    public List<CarRental> getAllRentals() {
        List<CarRental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM CarRentals";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                rentals.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentals;
    }

    // --- Get rentals by customer ID ---
    public List<CarRental> getRentalsByCustomer(int customerId) {
        List<CarRental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM CarRentals WHERE CustomerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                rentals.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentals;
    }

    // --- Add a new rental ---
    public boolean addRental(CarRental rental) {
        String sql = "INSERT INTO CarRentals (CarID, CustomerID, StartDate, EndDate, RentDay, TotalCost) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, rental.getCarID());
            pstmt.setInt(2, rental.getCustomerID());
            pstmt.setDate(3, new java.sql.Date(rental.getStartDate().getTime()));
            if (rental.getEndDate() != null)
                pstmt.setDate(4, new java.sql.Date(rental.getEndDate().getTime()));
            else
                pstmt.setNull(4, Types.DATE);
            if (rental.getRentDay() != null)
                pstmt.setInt(5, rental.getRentDay());
            else
                pstmt.setNull(5, Types.INTEGER);
            pstmt.setDouble(6, rental.getTotalCost());

            int affected = pstmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // --- Delete a rental by ID ---
    public boolean deleteRental(int rentalId) {
        String sql = "DELETE FROM CarRentals WHERE RentalID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, rentalId);
            int affected = pstmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // --- Helper: map ResultSet to CarRental ---
    private CarRental mapRow(ResultSet rs) throws SQLException {
        CarRental rental = new CarRental();
        rental.setRentalID(rs.getInt("RentalID"));
        rental.setCarID(rs.getInt("CarID"));
        rental.setCustomerID(rs.getInt("CustomerID"));
        rental.setStartDate(rs.getDate("StartDate"));
        rental.setEndDate(rs.getDate("EndDate"));
        int rentDay = rs.getInt("RentDay");
        rental.setRentDay(rs.wasNull() ? null : rentDay);
        rental.setTotalCost(rs.getDouble("TotalCost"));
        return rental;
    }
}
