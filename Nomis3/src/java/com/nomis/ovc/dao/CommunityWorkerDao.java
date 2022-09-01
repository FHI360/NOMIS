/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.CommunityWorker;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface CommunityWorkerDao 
{
    public void saveCommunityWorker(CommunityWorker cw) throws Exception;
    public void updateCommunityWorker(CommunityWorker cw) throws Exception;
    public void deleteCommunityWorker(CommunityWorker cw) throws Exception;
    public CommunityWorker getCommunityWorker(String communityWorkerId) throws Exception;
    public List getAllCommunityWorkers() throws Exception;
    public CommunityWorker getCommunityWorkerByName(String firstName,String surname) throws Exception;
    public List getActiveCommunityWorkers() throws Exception;
}
