/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.dao;

/**
 *
 * @author Janice
 */
import carrental.model.*;
import carrental.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for RolePermission
 * Handles CRUD operations
 */
public class RolePermissionDAO {
    
    // CREATE
    public void addRolePermission(RolePermission rp) {
        String sql = "INSERT INTO RolePermissions (RoleName, ModuleName, CanCreate, CanRead, CanUpdate, CanDelete) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, rp.getRoleName());
            stmt.setString(2, rp.getModuleName());
            stmt.setBoolean(3, rp.isCanCreate());
            stmt.setBoolean(4, rp.isCanRead());
            stmt.setBoolean(5, rp.isCanUpdate());
            stmt.setBoolean(6, rp.isCanDelete());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (by RoleName)
    public List<RolePermission> getPermissionsByRole(String roleName) {
        List<RolePermission> permissions = new ArrayList<>();
        String sql = "SELECT * FROM RolePermissions WHERE RoleName = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, roleName);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                RolePermission rp = new RolePermission(
                        rs.getInt("RolePermissionID"),
                        rs.getString("RoleName"),
                        rs.getString("ModuleName"),
                        rs.getBoolean("CanCreate"),
                        rs.getBoolean("CanRead"),
                        rs.getBoolean("CanUpdate"),
                        rs.getBoolean("CanDelete")
                );
                permissions.add(rp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permissions;
    }

    // UPDATE
    public void updateRolePermission(RolePermission rp) {
        String sql = "UPDATE RolePermissions SET CanCreate=?, CanRead=?, CanUpdate=?, CanDelete=? " +
                     "WHERE RolePermissionID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setBoolean(1, rp.isCanCreate());
            stmt.setBoolean(2, rp.isCanRead());
            stmt.setBoolean(3, rp.isCanUpdate());
            stmt.setBoolean(4, rp.isCanDelete());
            stmt.setInt(5, rp.getRolePermissionID());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteRolePermission(int rolePermissionID) {
        String sql = "DELETE FROM RolePermissions WHERE RolePermissionID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, rolePermissionID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

