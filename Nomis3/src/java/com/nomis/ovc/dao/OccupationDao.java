/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.Occupation;

/**
 *
 * @author smomoh
 */
public interface OccupationDao 
{
    public void saveOccupation(Occupation occupation) throws Exception;
    public void updateOccupation(Occupation occupation) throws Exception;
    public void deleteOccupation(Occupation occupation) throws Exception;
    public Occupation getOccupation(String occupationName) throws Exception;
    public Occupation getOccupation(int occupationValue) throws Exception;
    public Occupation getOccupationByRecordId(int recordId) throws Exception;
    public Occupation getOccupationWithHighestValue() throws Exception;
}
