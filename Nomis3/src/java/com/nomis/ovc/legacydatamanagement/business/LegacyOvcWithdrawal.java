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
public class LegacyOvcWithdrawal implements Serializable {

    public LegacyOvcWithdrawal() 
    {

    }


    private String ovcId;
    private String dateOfWithdrawal;
    private String reasonWithdrawal;
    private String type;
    private int surveyNumber;
    private int markedForDelete;




public String getOvcId() {
return ovcId;
}
public void setOvcId(String ovcId) {
this.ovcId = ovcId;
}

public String getDateOfWithdrawal() {
return dateOfWithdrawal;
}
public void setDateOfWithdrawal(String dateOfWithdrawal) {
this.dateOfWithdrawal = dateOfWithdrawal;
}

public String getReasonWithdrawal() {
return reasonWithdrawal;
}
public void setReasonWithdrawal(String reasonWithdrawal) {
this.reasonWithdrawal = reasonWithdrawal;
}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

public int getSurveyNumber() {
        return surveyNumber;
    }

    public void setSurveyNumber(int surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }



}
