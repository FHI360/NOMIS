/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.AppSetting;

/**
 *
 * @author smomoh
 */
public interface AppSettingDao 
{
    public void saveAppSetting(AppSetting opm) throws Exception;
    public void updateAppSetting(AppSetting opm) throws Exception;
    public void deleteAppSetting(AppSetting opm) throws Exception;
    public AppSetting getAppSetting(String optionId) throws Exception;
}
