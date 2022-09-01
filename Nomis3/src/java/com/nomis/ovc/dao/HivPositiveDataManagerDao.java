/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

/**
 *
 * @author smomoh
 */
public interface HivPositiveDataManagerDao 
{
    public int getNumberOfOvcEligibleForViralLoad() throws Exception;
    public int getNumberOfAdultsEligibleForViralLoad() throws Exception;
}
