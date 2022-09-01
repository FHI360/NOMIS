/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.exportimport;

import com.nomis.ovc.util.AppConstant;
import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class DataExportImportSummary implements Serializable
{
   private String tagName;
   private String tagValue;
   int serialNo=0;
   String rowColor=AppConstant.FIRSTREPORTROWCOLOUR;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public String getRowColor() {
        return rowColor;
    }

    public void setRowColor(String rowColor) {
        this.rowColor = rowColor;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }
   
}
