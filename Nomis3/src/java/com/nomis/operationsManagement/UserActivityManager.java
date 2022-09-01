/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;

import com.nomis.ovc.business.UserActivityTracker;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.util.DateManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public class UserActivityManager 
{
    public void saveUserActivity(String userName,String userAction,String description)
    {
        try
        {
            UserActivityTracker uat=new UserActivityTracker();
            Date dateCreated=DateManager.getCurrentDateInstance();
            String dateAndTime=DateManager.getDefaultCurrentDateAndTime();
            uat.setDateAndTime(dateAndTime);
            uat.setDateCreated(dateCreated);
            uat.setDescription(description);
            uat.setUserAction(userAction);
            uat.setUserName(userName);
            DaoUtility util=new DaoUtility();
            util.getUserActivityTrackerDaoInstance().saveUserActivityTracker(uat);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public List getUserActivity(String userName,String userAction)
    {
        List mainList=new ArrayList();
        try
        {
            DaoUtility util=new DaoUtility();
            List list=util.getUserActivityTrackerDaoInstance().getUserActivityTrackerByUserNameAndAction(userName, userAction);
            if(list !=null)
            {
                for(int i=0; i<list.size(); i++)
                {
                    UserActivityTracker uat=(UserActivityTracker)list.get(i);
                    uat.setSerialNo(i+1);
                    if(i%2>0)
                    uat.setRowColor("#D7E5F2");
                    mainList.add(uat);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return mainList;
    }
}
