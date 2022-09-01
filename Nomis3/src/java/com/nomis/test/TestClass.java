/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.test;

import com.nomis.reports.utils.DatimReportGenerator;
import com.nomis.reports.utils.DatimReportTemplate;
import com.nomis.reports.utils.ReportParameterTemplate;

/**
 *
 * @author smomoh
 */
public class TestClass 
{
    public static void testDatimReports()
    {
        ReportParameterTemplate rpt=new ReportParameterTemplate();
        rpt.setLevel2OuId("Qmh0K5s78FI");
        rpt.setLevel3OuId("xYplBZ0oxiJ");
        DatimReportGenerator drg=new DatimReportGenerator();
        DatimReportTemplate drt=drg.getNoOfActiveHivUnknownOvcServed(rpt,0,17,"2021-04-01","2021-09-30","M");
        System.err.println("drt.getHiv_statNumerator() in TestClass is "+drt.getHiv_statNumerator());
    }
}
