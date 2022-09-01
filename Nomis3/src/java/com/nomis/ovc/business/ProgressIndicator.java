/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.business;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class ProgressIndicator implements Serializable
{
    private String taskName;
    private int totalSize;
    private int currentRecordSize;
    private int newRecordsSize;
    private int updatedRecordsSize;
    private String progressStatus;
    private String statusMessage;
    private int processStartFlag;
    private int processEndFlag;

    public int getCurrentRecordSize() {
        return currentRecordSize=getNewRecordsSize()+getUpdatedRecordsSize();
    }
        
    public int getNewRecordsSize() {
        return newRecordsSize;
    }

    public void setNewRecordsSize(int newRecordsSize) {
        this.newRecordsSize = newRecordsSize;
    }

   public int getProcessEndFlag() {
        return processEndFlag;
    }

    public void setProcessEndFlag(int processEndFlag) {
        this.processEndFlag = processEndFlag;
    }

    public int getProcessStartFlag() {
        return processStartFlag;
    }

    public void setProcessStartFlag(int processStartFlag) {
        this.processStartFlag = processStartFlag;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getUpdatedRecordsSize() {
        return updatedRecordsSize;
    }

    public void setUpdatedRecordsSize(int updatedRecordsSize) {
        this.updatedRecordsSize = updatedRecordsSize;
    }
   
}
