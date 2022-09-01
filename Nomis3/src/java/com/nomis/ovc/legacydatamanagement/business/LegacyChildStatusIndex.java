/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.ovc.legacydatamanagement.business;

import java.io.Serializable;

/**
 *
 * @author HP USER
 */
public class LegacyChildStatusIndex implements Serializable {

    public LegacyChildStatusIndex() {

    }

    private int id;
    private int surveyNumber;
    private int totalCsiScore;
    private String ovcId;
    private String csiDate;
    private String dateOfEntry;
    private int csiFactor1;
    private int csiFactor2;
    private int csiFactor3;
    private int csiFactor4;
    private int csiFactor5;
    private int csiFactor6;
    private int csiFactor7;
    private int csiFactor8;
    private int csiFactor9;
    private int csiFactor10;
    private int csiFactor11;
    private int csiFactor12;
    private int markedForDelete;

    public int getCsiFactor1() {
    return csiFactor1;
    }
    public void setCsiFactor1(int csiFactor1) {
    this.csiFactor1 = csiFactor1;
    }

    public int getCsiFactor2() {
    return csiFactor2;
    }
    public void setCsiFactor2(int csiFactor2) {
    this.csiFactor2 = csiFactor2;
    }

    public int getCsiFactor3() {
    return csiFactor3;
    }
    public void setCsiFactor3(int csiFactor3) {
    this.csiFactor3 = csiFactor3;
    }

    public int getCsiFactor4() {
    return csiFactor4;
    }
    public void setCsiFactor4(int csiFactor4) {
    this.csiFactor4 = csiFactor4;
    }

    public int getCsiFactor5() {
    return csiFactor5;
    }
    public void setCsiFactor5(int csiFactor5) {
    this.csiFactor5 = csiFactor5;
    }

    public int getCsiFactor6() {
    return csiFactor6;
    }
    public void setCsiFactor6(int csiFactor6) {
    this.csiFactor6 = csiFactor6;
    }

    public int getCsiFactor7() {
    return csiFactor7;
    }
    public void setCsiFactor7(int csiFactor7) {
    this.csiFactor7 = csiFactor7;
    }

    public int getCsiFactor8() {
    return csiFactor8;
    }
    public void setCsiFactor8(int csiFactor8) {
    this.csiFactor8 = csiFactor8;
    }

    public int getCsiFactor9() {
    return csiFactor9;
    }
    public void setCsiFactor9(int csiFactor9) {
    this.csiFactor9 = csiFactor9;
    }

    public int getCsiFactor10() {
    return csiFactor10;
    }
    public void setCsiFactor10(int csiFactor10) {
    this.csiFactor10 = csiFactor10;
    }

    public int getCsiFactor11() {
    return csiFactor11;
    }
    public void setCsiFactor11(int csiFactor11) {
    this.csiFactor11 = csiFactor11;
    }

    public int getCsiFactor12() {
    return csiFactor12;
    }
    public void setCsiFactor12(int csiFactor12) {
    this.csiFactor12 = csiFactor12;
    }

    public String getCsiDate() {
    return csiDate;
    }
    public void setCsiDate(String csiDate) {
    this.csiDate = csiDate;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public void setId(int id) {
    this.id = id;
    }

    public int getId() {
    return id;
    }

    public void setOvcId(String ovcId) {
    this.ovcId = ovcId;
    }

    public String getOvcId() {
    return ovcId;
    }

    public int getSurveyNumber() {
        return surveyNumber;
    }

    public void setSurveyNumber(int surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

    public int getTotalCsiScore() {
        return totalCsiScore;
    }

    public void setTotalCsiScore(int totalCsiScore) {
        this.totalCsiScore = totalCsiScore;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }
    
}
