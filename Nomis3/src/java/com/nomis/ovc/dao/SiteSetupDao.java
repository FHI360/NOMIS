/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.SiteSetup;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface SiteSetupDao 
{
    public void saveSiteSetup(SiteSetup setup) throws Exception;
    public void updateSiteSetup(SiteSetup setup) throws Exception;
    public void deleteSiteSetup(SiteSetup setup) throws Exception;
    public SiteSetup getSiteSetup(String userName) throws Exception;
    public List getAllSiteSetups() throws Exception;
}
