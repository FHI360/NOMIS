/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class UserRole implements Serializable
{
    private String roleId;
    private String roleName;
    private String assignedPrivileges;

    public String getAssignedPrivileges() {
        return assignedPrivileges;
    }

    public void setAssignedPrivileges(String assignedPrivileges) {
        this.assignedPrivileges = assignedPrivileges;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}
