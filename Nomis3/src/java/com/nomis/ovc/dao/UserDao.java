/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.User;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface UserDao 
{
    public void saveUser(User user) throws Exception;
    public void updateUser(User user) throws Exception;
    public void deleteUser(User user) throws Exception;
    public User getUser(String userName, String password) throws Exception;
    public User getUser(String username) throws Exception;
    public List getAllUsers() throws Exception;
}
