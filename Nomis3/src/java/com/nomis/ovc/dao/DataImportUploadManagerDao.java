/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.dao;

import com.nomis.exportimport.DataImportFileUploadManager;
import java.util.List;

/**
 *
 * @author smomoh
 */
public interface DataImportUploadManagerDao 
{
    public void saveDataImportFileUploadManager(DataImportFileUploadManager difum) throws Exception;
    public void updateDataImportFileUploadManager(DataImportFileUploadManager difum) throws Exception;
    public void deleteDataImportFileUploadManager(DataImportFileUploadManager difum) throws Exception;
    public DataImportFileUploadManager getDataImportFileUploadManager(int recordId) throws Exception;
    public List getAllDataImportFileUploadManager() throws Exception;
    public List getAllDataImportFileUploadManager(int uploadStatus) throws Exception;
}
