/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.ChildStatusIndex;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface ChildStatusDao 
{
    public void saveChildStatusIndex(ChildStatusIndex csi) throws Exception;
    public void updateChildStatusIndex(ChildStatusIndex csi) throws Exception;
    public void deleteChildStatusIndex(ChildStatusIndex csi) throws Exception;
    public void markForDelete(ChildStatusIndex csi) throws Exception;
    public ChildStatusIndex getChildStatusIndex(String ovcId, Date csiDate) throws Exception;
    public List getCsiAsList(String ovcId, Date csiDate) throws Exception;
    public void saveOrUpdateChildStatusIndex(ChildStatusIndex csi) throws Exception;
    /*public void deleteChildStatusIndexWithoutReordering(ChildStatusIndex childStatusIndex) throws Exception;
    public void deleteAllCsiRecordsPerOvc(String ovcId) throws Exception;
    
    public void saveChildStatusIndexAndReorderAssessmentNumber(ChildStatusIndex childStatusIndex) throws Exception;
    public void updateChildStatusIndexAndReorderAssessmentNumber(ChildStatusIndex childStatusIndex) throws Exception;
     */
}
