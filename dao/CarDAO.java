/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.dao;

import carrental.model.*;
import carrental.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Janice
 */
public class CarDAO {

    // --- Get all cars ---
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM Cars";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                cars.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    // --- Get available cars ---
    public List<Car> getAvailableCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM Cars WHERE RentalStatus = 'Available'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                cars.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    // --- Get car by ID ---
    public Car getCarById(int carID) {
        String sql = "SELECT * FROM Cars WHERE CarID = ?";
        Car car = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, carID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                car = mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }

    // --- Add new car ---
    public boolean addCar(Car car) {
        String sql = "INSERT INTO Cars (Make, Model, Year, LicensePlate, Color, Rate, RentalStatus) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setInt(3, car.getYear());
            pstmt.setString(4, car.getLicensePlate());
            pstmt.setString(5, car.getColor());
            pstmt.setDouble(6, car.getRate());
            pstmt.setString(7, car.getRentalStatus());

            int affected = pstmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // --- Update existing car ---
    public boolean updateCar(Car car) {
        String sql = "UPDATE Cars SET Make = ?, Model = ?, Year = ?, LicensePlate = ?, Color = ?, Rate = ?, RentalStatus = ? " +
                     "WHERE CarID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setInt(3, car.getYear());
            pstmt.setString(4, car.getLicensePlate());
            pstmt.setString(5, car.getColor());
            pstmt.setDouble(6, car.getRate());
            pstmt.setString(7, car.getRentalStatus());
            pstmt.setInt(8, car.getCarID());

            int affected = pstmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // --- Delete car ---
    public boolean deleteCar(int carID) {
        String sql = "DELETE FROM Cars WHERE CarID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, carID);
            int affected = pstmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // --- Helper: map ResultSet to Car ---
    private Car mapRow(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setCarID(rs.getInt("CarID"));
        car.setMake(rs.getString("Make"));
        car.setModel(rs.getString("Model"));
        car.setYear(rs.getInt("Year"));
        car.setLicensePlate(rs.getString("LicensePlate"));
        car.setColor(rs.getString("Color"));
        car.setRate(rs.getDouble("Rate"));
        car.setRentalStatus(rs.getString("RentalStatus"));
        return car;
    }
}
