<%-- 
    Document   : DatimReport
    Created on : Nov 21, 2018, 10:16:09 PM
    Author     : smomoh
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>DATIM report</title>
    </head>
    <body>
        <table align="center" style="border-collapse: collapse;width: 800px;">
            <tr><td colspan="4" align="center">DATIM report form </td></tr>
                        <tr><td align="left" border="0" style=" border: none;">
                            <jsp:include page="/includes/ReportHeading.jsp" />
                        </td></tr> <%--${datimResultForm.startMth} ${datimResultForm.startYr} to ${datimResultForm.endMth} ${datimResultForm.endYr} --%>
            <tr><td colspan="3">Reporting period: ${datimReportPeriod} </td><td> </td></tr>
            
        </table>
            
        <table border="4" style="border-collapse: collapse; width: 800px;" align="center">
            <tr >
                <td class="tdLine" style="font-size:16px; background-color: #F39814" colspan="2"><div >OVC_HIVSTAT</div></td>
                
             </tr>
             <tr >
                <td class="tdLine" style="font-size:16px; background-color: aquamarine;" colspan="2"><div >Number of OVC with HIV status reported to implementing partner (including status not reported)</div></td>
             </tr>
             <tr >
                <td class="tdLine" style="font-size:16px;"><div >Numerator</div></td>
                <td class="tdLine" style="font-size:16px;" ><div >${datimResultForm.hiv_statNumerator} </div></td>
             </tr>
            <%--<tr >
                <td class="tdLine" style="font-size:16px; background-color: lightsteelblue" colspan="2" ><div >Disaggregated by status type</div></td>
             </tr>
            <tr >
                <td class="tdLine" style="font-size:16px; "><div >Reported HIV positive to IP</div></td>
                <td class="tdLine" style="font-size:16px;"><div > ${datimResultForm.totalPositive}</div></td>
            </tr> 
            
             <tr >
                <td class="tdLine" style="font-size:16px; "><div >Of those positive: Currently receiving ART </div></td>
                <td class="tdLine" style="font-size:16px;"><div > ${datimResultForm.enrolledOnART}</div></td>
            </tr>
            <tr >
                <td class="tdLine" style="font-size:16px; "><div >Of those positive: Not currently receiving ART </div></td>
                <td class="tdLine" style="font-size:16px;"><div > ${datimResultForm.notEnrolledOnART}</div></td>
            </tr>
            <tr>
                <td class="tdLine" style="font-size:16px; "><div >Reported HIV negative to IP </div></td>
                <td class="tdLine" style="font-size:16px;"><div > ${datimResultForm.totalNegative}</div></td>
            </tr>
            <tr >
                <td class="tdLine" style="font-size:16px; "><div >Test not required based on risk assessment</div></td>
                <td class="tdLine" style="font-size:16px;"><div >${datimResultForm.testNotIndicated}</div></td>
            </tr>
            <tr >
                <td class="tdLine" style="font-size:16px; "><div >No HIV status reported to IP (HIV status unknown)</div></td>
                <td class="tdLine" style="font-size:16px;"><div > ${datimResultForm.totalUnknown}</div></td>
            </tr>
                       
             <tr >
                <td class="tdLine" style="font-size:16px; height: 20px;" colspan="5"> </td>
             </tr>--%>
                            
        </table>
             
             
        <table border="4" style="border-collapse: collapse; width: 800px;" align="center">
             
            <tr >
                <td class="tdLine" style="font-size:16px; background-color: lightsteelblue" colspan="8"><div >Reported HIV positive to IP</div></td>
                <td class="tdLine" style="font-size:16px;"><div > </div></td>
            </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;" rowspan="2"><div >Female </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:14px;" colspan="2"><div > </div></td>
                
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveFemaleLessThan1+datimResultForm.graduatedPositiveFemaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveFemale1To4+datimResultForm.graduatedPositiveFemale1To4}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveFemale5To9+datimResultForm.graduatedPositiveFemale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveFemale10To14+datimResultForm.graduatedPositiveFemale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveFemale15To17+datimResultForm.graduatedPositiveFemale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
                
             </tr>
            <%-- <tr >
                <td class="tdLine" style="font-size:16px; height: 20px;" colspan="9"> </td>
             </tr>--%>
            <tr >
                <td class="tdLine" style="font-size:16px; " rowspan="2"><div >Male </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveMaleLessThan1+datimResultForm.graduatedPositiveMaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveMale1To4+datimResultForm.graduatedPositiveMale1To4}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveMale5To9+datimResultForm.graduatedPositiveMale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveMale10To14+datimResultForm.graduatedPositiveMale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveMale15To17+datimResultForm.graduatedPositiveMale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;"><div >Subtotal: </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="8"><div >${datimResultForm.totalPositive} </div></td>
                
             </tr>
                 
             
                <tr >
                <td class="tdLine" style="font-size:16px; background-color: lightsteelblue" colspan="8"><div >Of those positive: Currently receiving ART </div></td>
                <td class="tdLine" style="font-size:16px;"><div > </div></td>
            </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;" rowspan="2"><div >Female </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:14px;" colspan="2"><div > </div></td>
                
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveEnrolledOnARTFemaleLessThan1+datimResultForm.graduatedPositiveEnrolledOnARTFemaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveEnrolledOnARTFemale1To4+datimResultForm.graduatedPositiveEnrolledOnARTFemale1To4} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveEnrolledOnARTFemale5To9+datimResultForm.graduatedPositiveEnrolledOnARTFemale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveEnrolledOnARTFemale10To14+datimResultForm.graduatedPositiveEnrolledOnARTFemale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveEnrolledOnARTFemale15To17+datimResultForm.graduatedPositiveEnrolledOnARTFemale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
                
             </tr>
             <%--<tr >
                <td class="tdLine" style="font-size:16px; height: 20px;" colspan="9"> </td>
             </tr>--%>
            <tr >
                <td class="tdLine" style="font-size:16px; " rowspan="2"><div >Male </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveEnrolledOnARTMaleLessThan1+datimResultForm.graduatedPositiveEnrolledOnARTMaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveEnrolledOnARTMale1To4+datimResultForm.graduatedPositiveEnrolledOnARTMale1To4} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveEnrolledOnARTMale5To9+datimResultForm.graduatedPositiveEnrolledOnARTMale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveEnrolledOnARTMale10To14+datimResultForm.graduatedPositiveEnrolledOnARTMale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveEnrolledOnARTMale15To17+datimResultForm.graduatedPositiveEnrolledOnARTMale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;"><div >Subtotal: </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="8"><div >${datimResultForm.enrolledOnART}</div></td>
                
             </tr>
             
             <tr >
                <td class="tdLine" style="font-size:16px; background-color: lightsteelblue" colspan="8"><div >Of those positive: Not currently receiving ART</div></td>
                <td class="tdLine" style="font-size:16px;"><div > </div></td>
            </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;" rowspan="2"><div >Female </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:14px;" colspan="2"><div > </div></td>
                
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveNotEnrolledOnARTFemaleLessThan1+datimResultForm.graduatedPositiveNotEnrolledOnARTFemaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveNotEnrolledOnARTFemale1To4+datimResultForm.graduatedPositiveNotEnrolledOnARTFemale1To4} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveNotEnrolledOnARTFemale5To9+datimResultForm.graduatedPositiveNotEnrolledOnARTFemale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveNotEnrolledOnARTFemale10To14+datimResultForm.graduatedPositiveNotEnrolledOnARTFemale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveNotEnrolledOnARTFemale15To17+datimResultForm.graduatedPositiveNotEnrolledOnARTFemale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
                
             </tr>
             <%--<tr >
                <td class="tdLine" style="font-size:16px; height: 20px;" colspan="9"> </td>
             </tr>--%>
            <tr >
                <td class="tdLine" style="font-size:16px; " rowspan="2"><div >Male </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveNotEnrolledOnARTMaleLessThan1+datimResultForm.graduatedPositiveNotEnrolledOnARTMaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveNotEnrolledOnARTMale1To4+datimResultForm.graduatedPositiveNotEnrolledOnARTMale1To4} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveNotEnrolledOnARTMale5To9+datimResultForm.graduatedPositiveNotEnrolledOnARTMale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveNotEnrolledOnARTMale10To14+datimResultForm.graduatedPositiveNotEnrolledOnARTMale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activePositiveNotEnrolledOnARTMale15To17+datimResultForm.graduatedPositiveNotEnrolledOnARTMale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;"><div >Subtotal: </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="8"><div>${datimResultForm.notEnrolledOnART} </div></td>
                
             </tr>
             
             
             
             
             
             
             <tr >
                <td class="tdLine" style="font-size:16px; background-color: lightsteelblue" colspan="8"><div >Reported HIV negative to IP</div></td>
                <td class="tdLine" style="font-size:16px;"><div > </div></td>
            </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;" rowspan="2"><div >Female </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:14px;" colspan="2"><div > </div></td>
                
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeNegativeFemaleLessThan1+datimResultForm.graduatedNegativeFemaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeNegativeFemale1To4+datimResultForm.graduatedNegativeFemale1To4} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeNegativeFemale5To9+datimResultForm.graduatedNegativeFemale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeNegativeFemale10To14+datimResultForm.graduatedNegativeFemale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeNegativeFemale15To17+datimResultForm.graduatedNegativeFemale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
                
             </tr>
             <%--<tr >
                <td class="tdLine" style="font-size:16px; height: 20px;" colspan="9"> </td>
             </tr>--%>
            <tr >
                <td class="tdLine" style="font-size:16px; " rowspan="2"><div >Male </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeNegativeMaleLessThan1+datimResultForm.graduatedNegativeMaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeNegativeMale1To4+datimResultForm.graduatedNegativeMale1To4} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeNegativeMale5To9+datimResultForm.graduatedNegativeMale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeNegativeMale10To14+datimResultForm.graduatedNegativeMale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeNegativeMale15To17+datimResultForm.graduatedNegativeMale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;"><div >Subtotal: </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="8"><div >${datimResultForm.totalNegative}</div></td>
                
             </tr>
                 
             
                <tr >
                <td class="tdLine" style="font-size:16px; background-color: lightsteelblue" colspan="8"><div >Test not required based on risk assessment</div></td>
                <td class="tdLine" style="font-size:16px;"><div > </div></td>
            </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;" rowspan="2"><div >Female </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:14px;" colspan="2"><div > </div></td>
                
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeTestNotIndicatedFemaleLessThan1+datimResultForm.graduatedTestNotIndicatedFemaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeTestNotIndicatedFemale1To4+datimResultForm.graduatedTestNotIndicatedFemale1To4} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeTestNotIndicatedFemale5To9+datimResultForm.graduatedTestNotIndicatedFemale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeTestNotIndicatedFemale10To14+datimResultForm.graduatedTestNotIndicatedFemale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeTestNotIndicatedFemale15To17+datimResultForm.graduatedTestNotIndicatedFemale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
                
             </tr>
             <%--<tr >
                <td class="tdLine" style="font-size:16px; height: 20px;" colspan="9"> </td>
             </tr>--%>
            <tr >
                <td class="tdLine" style="font-size:16px; " rowspan="2"><div >Male </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeTestNotIndicatedMaleLessThan1+datimResultForm.graduatedTestNotIndicatedMaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeTestNotIndicatedMale1To4+datimResultForm.graduatedTestNotIndicatedMale1To4} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeTestNotIndicatedMale5To9+datimResultForm.graduatedTestNotIndicatedMale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeTestNotIndicatedMale10To14+datimResultForm.graduatedTestNotIndicatedMale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeTestNotIndicatedMale15To17+datimResultForm.graduatedTestNotIndicatedMale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;"><div >Subtotal: </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="8"><div >${datimResultForm.testNotIndicated} </div></td>
                
             </tr>
             
             <tr >
                <td class="tdLine" style="font-size:16px; background-color: lightsteelblue" colspan="8"><div >No HIV status reported to IP (HIV status unknown)</div></td>
                <td class="tdLine" style="font-size:16px;"><div > </div></td>
            </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;" rowspan="2"><div >Female </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:14px;" colspan="2"><div > </div></td>
                
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeUnknownFemaleLessThan1+datimResultForm.graduatedUnknownFemaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeUnknownFemale1To4+datimResultForm.graduatedUnknownFemale1To4} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeUnknownFemale5To9+datimResultForm.graduatedUnknownFemale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeUnknownFemale10To14+datimResultForm.graduatedUnknownFemale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeUnknownFemale15To17+datimResultForm.graduatedUnknownFemale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
                
             </tr>
             <%--<tr >
                <td class="tdLine" style="font-size:16px; height: 20px;" colspan="9"> </td>
             </tr>--%>
            <tr >
                <td class="tdLine" style="font-size:16px; " rowspan="2"><div >Male </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeUnknownMaleLessThan1+datimResultForm.graduatedUnknownMaleLessThan1} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeUnknownMale1To4+datimResultForm.graduatedUnknownMale1To4} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeUnknownMale5To9+datimResultForm.graduatedUnknownMale5To9}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeUnknownMale10To14+datimResultForm.graduatedUnknownMale10To14}</div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.activeUnknownMale15To17+datimResultForm.graduatedUnknownMale15To17}</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"> </td>
             </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;"><div >Subtotal: </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="8"><div >${datimResultForm.totalUnknown}</div></td>
                
             </tr>
             
             </table>
             
             <table border="4" style="border-collapse: collapse; width: 800px;" align="center">
            <tr>
                <td class="tdLine" style="font-size:16px; background-color: #F39814" colspan="9"><div >OVC_SERV</div></td>
                
             </tr>
             <tr >
                <td class="tdLine" style="font-size:16px; background-color: aquamarine;" colspan="9"><div >Number of beneficiaries served by PEPFAR OVC programs for children and families affected by HIV/AIDS</div></td>
                
             </tr>
             <tr >
                <td class="tdLine" style="font-size:16px;" colspan="2"><div >Numerator</div></td>
                <td class="tdLine" style="font-size:16px;" colspan="7" ><div >${datimResultForm.ovc_servNumerator} </div></td>
             </tr>
                
        
            
            <tr style="background-color: lightsteelblue;">
                <td class="tdLine" style="font-size:16px; font-weight: bolder "><div >Required</div></td>
                <td class="tdLine" style="font-size:16px; font-weight: bold;" colspan="8"><div >Disaggregated by program status (active and graduated) by age/sex (Fine disaggregation)</div></td>
             </tr>
             <tr >
                    <td  colspan="9" style="height: 20px; background-color: aquamarine; font-weight: bold">Active (Received at least one service in each of the preceding two quarters) </td>
                </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;" rowspan="2"><div >Female </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:14px;"><div >18+ (OVC) </div></td>
                <td class="tdLine" style="font-size:16px;"><div >18+ (Caregivers)</div></td>
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActiveLessThan1Female} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActive1to4Female} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActive5to9Female} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActive10to14Female} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActive15to17Female} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servFemale18To24} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActive18AndAboveFemale} </div></td>
             </tr>
             <tr >
                <td class="tdLine" style="font-size:16px; height: 20px;" colspan="9"> </td>
             </tr>
            <tr >
                <td class="tdLine" style="font-size:16px; " rowspan="2"><div >Male </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:14px;"><div >18+ (OVC) </div></td>
                <td class="tdLine" style="font-size:16px;"><div >18+ (Caregivers)</div></td>
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActiveLessThan1Male} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActive1to4Male} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActive5to9Male} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActive10to14Male} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActive15to17Male} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servMale18To24} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servActive18AndAboveMale} </div></td>
             </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;"><div >Subtotal: </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="8"><div > </div></td>
                
             </tr>
                          
                <tr >
                    <td  colspan="9" style="height: 20px; background-color:lightsteelblue; font-weight: bold">Graduated (Q2: Graduated in the past 6 months. At Q4: Report the number of children and parents/caregivers that exited in the past four quarters and did not return to active status) </td>
                </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;" rowspan="2"><div >Female </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >18+ (OVC) </div></td>
                <td class="tdLine" style="font-size:14px;"><div >18+ (Caregivers)</div></td>
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduatedLessThan1Female} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated1to4Female} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated5to9Female} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated10to14Female} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated15to17Female} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated18to24Female} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated18AndAboveFemale} </div></td>
             </tr>
             <tr >
                <td class="tdLine" style="font-size:16px; height: 20px;" colspan="9"> </td>
             </tr>
            <tr >
                <td class="tdLine" style="font-size:16px; " rowspan="2"><div >Male </div></td>
                <td class="tdLine" style="font-size:16px;"><div > Unknown age</div></td>
                <td class="tdLine" style="font-size:16px;"><div > &lt;1</div></td>
                <td class="tdLine" style="font-size:16px;"><div >1-4</div></td>
                <td class="tdLine" style="font-size:16px;"><div >5-9</div></td>
                <td class="tdLine" style="font-size:16px;"><div >10-14 </div></td>
                <td class="tdLine" style="font-size:16px;"><div >15-17 </div></td>
                <td class="tdLine" style="font-size:14px;"><div >18+ (OVC) </div></td>
                <td class="tdLine" style="font-size:16px;"><div >18+ (Caregivers)</div></td>
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;"><div > </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduatedLessThan1Male} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated1to4Male} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated5to9Male} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated10to14Male} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated15to17Male} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated18to24Male} </div></td>
                <td class="tdLine" style="font-size:16px;"><div>${datimResultForm.ovc_servGraduated18AndAboveMale} </div></td>
             </tr>
            <tr >
                <td class="tdLine" style="font-size:16px;"><div >Subtotal: </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="8"><div > </div></td>
                
             </tr>
             <tr style="background-color: lightsteelblue">
                <td class="tdLine" style="font-size:16px; font-weight: bolder;"><div >Required</div></td>
                <td class="tdLine" style="font-size:16px; font-weight: bold;" colspan="8"><div >At Q2: Report exits or transfers within the past 6 months. At Q4: Report exits or transfers within the past 12 months</div></td>
             </tr>
             <tr >
                <td class="tdLine" style="font-size:16px;" ><div> </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="2"><div >Transfer to PEPFAR supported program </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="3"><div>Transfer to non-PEPFAR supported programs </div></td>
                <td class="tdLine" style="font-size:16px;" colspan="3"><div>Exited without graduation </div></td> 
             </tr>
             <tr >
                 <td class="tdLine" style="font-size:16px;" ><div> </div></td>
                 <td class="tdLine" style="font-size:16px;" colspan="2"><div>${datimResultForm.transferedToPEPFAR} </div></td>
                 <td class="tdLine" style="font-size:16px;" colspan="3"><div>${datimResultForm.transferedToNonPEPFAR} </div></td>
                 <td class="tdLine" style="font-size:16px;" colspan="3"><div>${datimResultForm.ovc_servExitedWithoutGraduation}</div></td> 
             </tr>
                
        </table>
        
    </body>
</html>
