<%-- 
    Document   : ReportOrganizationUnitParameter
    Created on : Oct 8, 2018, 6:24:04 AM
    Author     : smomoh
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<table >
<tr><td colspan="4" align="center"> 
        <label style="color: red; font-weight: bold; font-size: 14px;">${reportGenerationDisabled}</label>
    </td></tr>
<%--<logic:present name="siteSetupOu">
<tr><td colspan="4" align="center"><label style="color: blue">${level2Ouh.name}: ${siteSetupOu.name} </label></td></tr>
</logic:present>--%>
<tr>
<td align="right">Implementing partner</td>
<td colspan="3">&nbsp; <%--allLevel2OuList level3OuListForReports--%>
    <html:select property="partnerCode" styleId="partnerCode" style="width:570px;" onchange="setActionName('cboList');forms[0].submit()" >
        <html:option value="All">All</html:option>
        <logic:present name="partnerListForReport"> <%--userAssignedPartners partnerListForReport--%>
            <logic:iterate name="partnerListForReport" id="partner">
                <html:option value="${partner.partnerCode}">${partner.partnerName}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>

</tr>

<tr>
<td align="right">${ouhLevel2Name.name}</td>
<td colspan="3">&nbsp; <%--allLevel2OuList level3OuListForReports--%>
    <html:select property="level2OuId" styleId="level2OuId" style="width:570px;" onchange="setActionName('cboList');forms[0].submit()" >
        <html:option value="select">All</html:option>
        <logic:present name="level2OuListForReport">
            <logic:iterate name="level2OuListForReport" id="ou">
                <html:option value="${ou.uid}">${ou.name}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>

</tr>
<tr>
<td align="right"><logic:present name="ouhLevel3Name">${ouhLevel3Name.name}</logic:present> </td>
<td > &nbsp;
<html:select property="level3OuId" styleId="level3OuId" onchange="setActionName('level4OuList');forms[0].submit()" >
        <html:option value="select">All</html:option>
        <logic:present name="level3OuListForReport">
            <logic:iterate name="level3OuListForReport" id="ou">
                <html:option value="${ou.uid}">${ou.name}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>
    <td align="right"><label style="margin-left: 20px;"><logic:present name="ouhLevel4Name">${ouhLevel4Name.name}</logic:present> </label></td>
<td>
<html:select property="organizationUnitId" styleId="organizationUnitId" onchange="generateUniqueId()">
        <html:option value="select">All</html:option>
        <logic:present name="level4OuListForReport">
            <logic:iterate name="level4OuListForReport" id="ou">
                <html:option value="${ou.uid}">${ou.name}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>
</tr>
<tr>
<td align="right"><jsp:include page="LocalOrganizationName.jsp" /> </td>
<td colspan="3">&nbsp;
    <html:select property="cboId" styleId="cboId" style="width:570px;" onchange="setActionName('level3OuList');forms[0].submit()" >
        <html:option value="select">All</html:option>
        <logic:present name="cboListForReport">
            <logic:iterate name="cboListForReport" id="cbo">
                <html:option value="${cbo.uniqueId}">${cbo.cboName}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>

</tr>
<tr>
<td align="right">Period </td>
<td colspan="3">&nbsp; <%--value="${reportStartDate}" value="${reportEndDate}" --%>
<html:text property="startDate" styleId="startDate" readonly="true"/>
    
    <label style="margin-left: 20px; margin-right: 20px;">To </label>
    <html:text property="endDate" styleId="endDate" readonly="true"/>
    </td>
</tr>
</table>
