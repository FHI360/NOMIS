/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.UserRole;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface UserRoleDao 
{
    public void saveUserRole(UserRole ur) throws Exception;
    public void updateUserRole(UserRole ur) throws Exception;
    public void deleteUserRole(UserRole ur) throws Exception;
    public List getAllUserRoles() throws Exception;
    public int getNoOfUserRoles() throws Exception;
    public UserRole getUserRole(String roleId) throws Exception;
    public UserRole getUserRoleByRoleName(String roleName) throws Exception;
}
