/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Janice
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/CarRentalSystem";
    private static final String USER = "root";
    private static final String PASSWORD = "sa123";

    public static Connection getConnection() {
        Connection con = null;
        try {
            // Load MySQL driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
