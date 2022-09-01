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
public class CategoryOptionCombo implements Serializable
{
    private String categoryOptionComboId;
    private String categoryOptionComboName;

    public String getCategoryOptionComboId() {
        return categoryOptionComboId;
    }

    public void setCategoryOptionComboId(String categoryOptionComboId) {
        this.categoryOptionComboId = categoryOptionComboId;
    }

    public String getCategoryOptionComboName() {
        return categoryOptionComboName;
    }

    public void setCategoryOptionComboName(String categoryOptionComboName) {
        this.categoryOptionComboName = categoryOptionComboName;
    }
    
}
