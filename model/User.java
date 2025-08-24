package carrental.model;

import java.time.LocalDateTime;

/**
 *
 * @author Janice
 */
public class User {
    private int UserID;    
    private String Username;
    private String Password;
    private String Role;
    private Boolean IsActive;
    private int CreatedBy;
    private LocalDateTime CreatedAt;
    private int LastModifiedBy;
    private LocalDateTime LastModifiedAt;
    
    public User() {
 
    }

    public User(int userID, String username, String password, String role, Boolean isActive, int createdBy, LocalDateTime createdAt, int lastModifiedBy, LocalDateTime lastModifiedAt) {
        this.setUserID(userID);
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
        this.setIsActive(isActive);
        this.setCreatedBy(createdBy);
        this.setCreatedAt(createdAt);
        this.setLastModifiedBy(lastModifiedBy);
        this.setLastModifiedAt(lastModifiedAt);
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        this.Role = role;
    }

    public Boolean getIsActive() {
        return IsActive;
    }

    public void setIsActive(Boolean isActive) {
        this.IsActive = isActive;
    }

    public int getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(int createdBy) {
        this.CreatedBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.CreatedAt = createdAt;
    }

    public int getLastModifiedBy() {
        return LastModifiedBy;
    }

    public void setLastModifiedBy(int lastModifiedBy) {
        this.LastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedAt() {
        return LastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.LastModifiedAt = lastModifiedAt;
    }
}
