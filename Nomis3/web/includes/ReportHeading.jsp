<%-- 
    Document   : ReportHeading
    Created on : Oct 10, 2018, 6:53:24 AM
    Author     : smomoh
--%>

<table align="center" border="0" width="90%">
    <tr><th colspan="4" align="center">
        <logic:present name="reportType">
            <label style="font-weight: bolder">${reportType}</label>
        </logic:present></th>
    </tr>
    <tr><th colspan="4" align="center"> &nbsp;</th></tr>
    <tr>
        <th><logic:present name="ouhLevel2Name"><label style="margin-right: 30px;">${ouhLevel2Name.name}: ${level2OuForReport.name}</label></logic:present></th>
    <th><logic:present name="ouhLevel3Name"><label style="margin-right: 30px;">${ouhLevel3Name.name}: ${level3OuForReport.name}</label></logic:present></th>
    <th><logic:present name="ouhLevel4Name"><label style="margin-right: 30px;">${ouhLevel4Name.name}: ${level4OuForReport.name}</label></logic:present></th>
    <th><logic:present name="cboForReport"><label style="margin-right: 30px;"><jsp:include page="LocalOrganizationName.jsp" />: ${cboForReport.cboName}</label></logic:present></th>
</tr>

<tr><th colspan="4"><logic:present name="indicatorName"> Indicator: ${indicatorName}</logic:present></th></tr>

    <tr><th colspan="4" align="center"> <logic:present name="reportPeriod">Report period: ${reportPeriod}</logic:present></th></tr>
</table>
