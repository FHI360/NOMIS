/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.reports.utils;

/**
 *
 * @author smomoh
 */
public class DataElementManager 
{
    public DataElement getOVCChildren_ServDataElement()
    {
        DataElement dataElement=new DataElement();
        dataElement.setDataElementId("rxgD4kPnUIg");
        dataElement.setDataElementName("OVC_SERV (N, DSD, Age/Sex/ProgramStatus): Beneficiaries Served");
        return dataElement;
    }
    public DataElement getOVCCaregivers_ServDataElement()
    {
        DataElement dataElement=new DataElement();
        dataElement.setDataElementId("qTfvIKslD0b");
        dataElement.setDataElementName("OVC_SERV (N, DSD, Age/Sex/ProgramStatusCaregiver): Beneficiaries Served");
        return dataElement;
    }
    public DataElement getOVC_ServTransferExitDataElement()
    {
        DataElement dataElement=new DataElement();
        dataElement.setDataElementId("hG9RAKu5UBA");
        dataElement.setDataElementName("OVC_SERV (N, DSD, TransferExit) : Beneficiaries Served");
        return dataElement;
    }
    public DataElement getOVC_HIVSTATDataElement()
    {
        DataElement dataElement=new DataElement();
        dataElement.setDataElementId("BoiTgHlqBmn");
        dataElement.setDataElementName("OVC_HIVSTAT (N, DSD, ReportedStatus): OVC Disclosed Known HIV Status");
        return dataElement;
    }
}
