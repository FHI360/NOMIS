/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.ovc.business.DatasetSetting;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface DatasetSettingDao 
{
    public void saveDatasetSetting(DatasetSetting dts) throws Exception;
    public void updateDatasetSetting(DatasetSetting dts) throws Exception;
    public void deleteDatasetSetting(DatasetSetting dts) throws Exception;
    public DatasetSetting getDatasetSetting(String datasetId) throws Exception;
    public DatasetSetting getDatasetSettingByModuleId(String moduleId) throws Exception;
    public List getDatasetSettings() throws Exception;
}
