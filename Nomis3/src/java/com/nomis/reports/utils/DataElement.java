/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class DataElement implements Serializable
{
    private String dataElementId;
    private String dataElementName;

    public String getDataElementId() {
        return dataElementId;
    }

    public void setDataElementId(String dataElementId) {
        this.dataElementId = dataElementId;
    }

    public String getDataElementName() {
        return dataElementName;
    }

    public void setDataElementName(String dataElementName) {
        this.dataElementName = dataElementName;
    }
    
}
