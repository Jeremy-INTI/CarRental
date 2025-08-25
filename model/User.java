package carrental.model;


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
    
    public User() {
 
    }

    public User(int userID, String username, String password, String role, Boolean isActive) {
        this.setUserID(userID);
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
        this.setIsActive(isActive);
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

}
