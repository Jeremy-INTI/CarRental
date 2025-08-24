/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental.model;

/**
 *
 * @author Janice
 */
public class RolePermission {
    private int rolePermissionID;
    private String roleName;     // 'Admin' or 'Staff'
    private String moduleName;   // e.g., 'Users', 'Cars', etc.
    private boolean canCreate;
    private boolean canRead;
    private boolean canUpdate;
    private boolean canDelete;

    // Constructor
    public RolePermission(int rolePermissionID, String roleName, String moduleName,
                          boolean canCreate, boolean canRead, boolean canUpdate, boolean canDelete) {
        this.rolePermissionID = rolePermissionID;
        this.roleName = roleName;
        this.moduleName = moduleName;
        this.canCreate = canCreate;
        this.canRead = canRead;
        this.canUpdate = canUpdate;
        this.canDelete = canDelete;
    }

    // Getters
    public int getRolePermissionID() {
        return rolePermissionID;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public boolean isCanCreate() {
        return canCreate;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public boolean isCanUpdate() {
        return canUpdate;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    // Setters
    public void setRolePermissionID(int rolePermissionID) {
        this.rolePermissionID = rolePermissionID;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setCanCreate(boolean canCreate) {
        this.canCreate = canCreate;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
}
