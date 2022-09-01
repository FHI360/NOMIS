/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.UserPrivilege;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface UserPrivilegesDao {
    public void saveUserPrivileges(UserPrivilege up) throws Exception;
    public void updateUserPrivileges(UserPrivilege up) throws Exception;
    public void deleteUserPrivileges(UserPrivilege up) throws Exception;
    public List getAllUserPrivileges() throws Exception;
}
