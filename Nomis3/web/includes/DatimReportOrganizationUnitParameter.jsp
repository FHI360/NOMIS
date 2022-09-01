<%-- 
    Document   : DatimReportOrganizationUnitParameter
    Created on : Sep 6, 2020, 5:06:40 PM
    Author     : smomoh
--%>

<%-- 
    Document   : ReportOrganizationUnitParameter
    Created on : Oct 8, 2018, 6:24:04 AM
    Author     : smomoh
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<table >
<tr><td colspan="4" align="center"> </td></tr>

<tr>
<td align="right">${ouhLevel2Name.name}</td>
<td colspan="3">
    <html:select property="level2OuId" styleId="level2OuId" style="width:570px;" onchange="setActionName('cboList');forms[0].submit()" >
        <html:option value="select">select....</html:option>
        <logic:present name="allLevel2OuList">
            <logic:iterate name="allLevel2OuList" id="ou">
                <html:option value="${ou.uid}">${ou.name}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>

</tr>
<tr>
<td align="right"><jsp:include page="LocalOrganizationName.jsp" /> </td>
<td colspan="3">
    <html:select property="cboId" styleId="cboId" style="width:570px;" onchange="setActionName('level3OuList');forms[0].submit()" >
        <html:option value="select">select....</html:option>
        <logic:present name="cboListForReport">
            <logic:iterate name="cboListForReport" id="cbo">
                <html:option value="${cbo.uniqueId}">${cbo.cboName}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>

</tr>
<tr>
<td align="right"><logic:present name="ouhLevel3Name">${ouhLevel3Name.name}</logic:present> </td>
<td >
<html:select property="level3OuId" styleId="level3OuId" onchange="setActionName('level4OuList');forms[0].submit()" >
        <html:option value="select">select....</html:option>
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
        <html:option value="select">select....</html:option>
        <logic:present name="level4OuListForReport">
            <logic:iterate name="level4OuListForReport" id="ou">
                <html:option value="${ou.uid}">${ou.name}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>
</tr>
<tr>
  <td width="5%"><span style="color:#333">Period</span></td>
  <td width="30%" colspan="4">
        <%--<html:option value="2020-04-01:2020-09-30">APR 2020</html:option>--%> 
      <html:select property="reportPeriod" styleId="reportPeriod" style="width:350px;">
          <logic:present name="dateParameterListForDatim">
              <logic:iterate name="dateParameterListForDatim" id="dparam">
          <html:option value="${dparam.value}">${dparam.displayName}</html:option>

              </logic:iterate>
          </logic:present>

      </html:select>
  </td>
</tr>
</table>

