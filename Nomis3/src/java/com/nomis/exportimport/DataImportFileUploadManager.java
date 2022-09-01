/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author smomoh
 */
public class DataImportFileUploadManager implements Serializable
{
    private int recordId;
    private String importFileName;
    private String importFilePath;
    private String userName;
    private String partnerCode;
    private int importStatus;
    private int lastProcessedTableIndex;
    private String importOptions;
    private Date dateOfUpload;
    private Date dateImportCompleted;
    private String timeImportCompleted;
    private String selectedTableCodes;
    private int totalNumberOfTablesSelected;
    private int numberOfTablesProcessed;
    
    public Date getDateImportCompleted() {
        return dateImportCompleted;
    }

    public void setDateImportCompleted(Date dateImportCompleted) {
        this.dateImportCompleted = dateImportCompleted;
    }

    public Date getDateOfUpload() {
        return dateOfUpload;
    }

    public void setDateOfUpload(Date dateOfUpload) {
        this.dateOfUpload = dateOfUpload;
    }

    public String getImportFileName() {
        return importFileName;
    }

    public void setImportFileName(String importFileName) {
        this.importFileName = importFileName;
    }

    public int getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(int importStatus) {
        this.importStatus = importStatus;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimeImportCompleted() {
        return timeImportCompleted;
    }

    public void setTimeImportCompleted(String timeImportCompleted) {
        this.timeImportCompleted = timeImportCompleted;
    }

    public String getImportFilePath() {
        return importFilePath;
    }

    public void setImportFilePath(String importFilePath) {
        this.importFilePath = importFilePath;
    }

    public String getSelectedTableCodes() {
        return selectedTableCodes;
    }

    public void setSelectedTableCodes(String selectedTableCodes) {
        this.selectedTableCodes = selectedTableCodes;
    }

    public String getImportOptions() {
        return importOptions;
    }

    public void setImportOptions(String importOptions) {
        this.importOptions = importOptions;
    }

    public int getLastProcessedTableIndex() {
        return lastProcessedTableIndex;
    }

    public void setLastProcessedTableIndex(int lastProcessedTableIndex) {
        this.lastProcessedTableIndex = lastProcessedTableIndex;
    }

    public int getNumberOfTablesProcessed() {
        return numberOfTablesProcessed;
    }

    public void setNumberOfTablesProcessed(int numberOfTablesProcessed) {
        this.numberOfTablesProcessed = numberOfTablesProcessed;
    }

    public int getTotalNumberOfTablesSelected() {
        return totalNumberOfTablesSelected;
    }

    public void setTotalNumberOfTablesSelected(int totalNumberOfTablesSelected) {
        this.totalNumberOfTablesSelected = totalNumberOfTablesSelected;
    }
    
}
