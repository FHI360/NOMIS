/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ovc.databasemanagement;

import java.io.Serializable;

/**
 *
 * @author Siaka
 */
public class DatabaseTable implements Serializable
{
    private String code;
    private String name;
    private int numberOfFields;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfFields() {
        return numberOfFields;
    }

    public void setNumberOfFields(int numberOfFields) {
        this.numberOfFields = numberOfFields;
    }   
}
