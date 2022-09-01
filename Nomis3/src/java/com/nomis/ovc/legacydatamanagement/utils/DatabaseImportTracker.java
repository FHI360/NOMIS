/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.ovc.legacydatamanagement.utils;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class DatabaseImportTracker implements Serializable
{
    private int recordId;
    private int snumber;
    private String dateOfImport;
    private String userName;
    private String summary;
    private String fileName;
    private String responseSent;
    private String dateAndTime;

    public String getDateOfImport() {
        return dateOfImport;
    }

    public void setDateOfImport(String dateOfImport) {
        this.dateOfImport = dateOfImport;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getSnumber() {
        return snumber;
    }

    public void setSnumber(int snumber) {
        this.snumber = snumber;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResponseSent() {
        return responseSent;
    }

    public void setResponseSent(String responseSent) {
        this.responseSent = responseSent;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
    
}
