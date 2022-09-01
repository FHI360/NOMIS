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
public class DataElementCategoryOptionCombo implements Serializable
{
    private CategoryOptionCombo categoryOptionCombo;
    private DataElement dataElement;

    public CategoryOptionCombo getCategoryOptionCombo() {
        return categoryOptionCombo;
    }

    public void setCategoryOptionCombo(CategoryOptionCombo categoryOptionCombo) {
        this.categoryOptionCombo = categoryOptionCombo;
    }

    public DataElement getDataElement() {
        return dataElement;
    }

    public void setDataElement(DataElement dataElement) {
        this.dataElement = dataElement;
    }
    
}
