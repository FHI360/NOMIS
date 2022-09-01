/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.ovc.legacydatamanagement.business;

import java.io.Serializable;

/**
 *
 * @author COMPAQ USER
 */
public class LegacyWards implements Serializable
{
    //private String state_code;
    //private String lga_code;
    private String cbo_code;
    private String ward_code;
    private String ward_name;

    public LegacyWards()
    {

    }
    public String getWard_code() {
        return ward_code;
    }

    public void setWard_code(String ward_code) {
        this.ward_code = ward_code;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }
    public String getCbo_code() {
        return cbo_code;
    }

    public void setCbo_code(String cbo_code) {
        this.cbo_code = cbo_code;
    }

    /*public String getLga_code() {
        return lga_code;
    }

    public void setLga_code(String lga_code) {
        this.lga_code = lga_code;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }*/
}
