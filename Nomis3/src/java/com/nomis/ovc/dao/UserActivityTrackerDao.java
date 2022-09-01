/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.UserActivityTracker;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface UserActivityTrackerDao 
{
    public void saveUserActivityTracker(UserActivityTracker uat) throws Exception;
    public void updateUserActivityTracker(UserActivityTracker uat) throws Exception;
    public void markForDelete(UserActivityTracker uat) throws Exception;
    public void deleteUserActivityTracker(UserActivityTracker uat) throws Exception;
    public UserActivityTracker getUserActivityTracker(int recordId) throws Exception;
    public UserActivityTracker getUserActivityTracker(String userName, String dateAndTime) throws Exception;
    public List getUserActivityTrackerByUserName(String userName) throws Exception;
    public List getUserActivityTrackerByDate(Date dateCreated) throws Exception;
    public List getAllUserActivityTrackers() throws Exception;
    public List getUserActivityTrackerByUserAction(String userAction) throws Exception;
    public List getUserActivityTrackerByUserNameAndAction(String userName,String userAction) throws Exception;
    public List getDistinctUserNames() throws Exception;
    public List getDistinctUserActions() throws Exception;
}
