/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class DateParamenterTemplate implements Serializable
{
    private String displayName;
    private String value;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
