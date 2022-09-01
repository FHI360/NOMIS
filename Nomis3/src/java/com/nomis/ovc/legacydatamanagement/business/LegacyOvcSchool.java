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
public class LegacyOvcSchool implements Serializable {

    public LegacyOvcSchool() {

    }

    private int id;
    private String code;
    private String name;
    private String type;
    private String address;
    private String lga;
    private String state;
    private int markedForDelete;


    public int getId() {
    return id;
    }
    public void setId(int id) {
    this.id = id;
    }

    public String getName() {
    return name;
    }
    public void setName(String name) {
    this.name = name;
    }

    public String getType() {
    return type;
    }
    public void setType(String type) {
    this.type = type;
    }

    public String getAddress() {
    return address;
    }
    public void setAddress(String address) {
    this.address = address;
    }

    public String getLga() {
    return lga;
    }
    public void setLga(String lga) {
    this.lga = lga;
    }

    public String getState() {
    return state;
    }
    public void setState(String state) {
    this.state = state;
    }

    public int getMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(int markedForDelete) {
        this.markedForDelete = markedForDelete;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
