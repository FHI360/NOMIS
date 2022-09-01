<%-- 
    Document   : OrganizationUnitHeader
    Created on : Sep 24, 2018, 12:23:46 PM
    Author     : smomoh
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<tr><td colspan="4" align="center"> </td></tr>
<logic:present name="siteSetupOu">
<tr><td colspan="4" align="center">
        <label>${level2Ouh.name}: ${siteSetupOu.name} </label>
        <html:hidden property="level2OuId" styleId="level2OuId" value="${siteSetupOu.uid}"/>
    </td></tr>
</logic:present> <%--class="whiteLabel"--%>
<tr>
<td  align="right"><logic:present name="level3Ouh">${level3Ouh.name}</logic:present> </td>
<td >
<html:select property="level3OuId" styleId="level3OuId" onchange="setActionName('level4OuList'); generateUniqueId(); forms[0].submit()" style="width:220px;">
        <%--<html:option value="select">select....</html:option>--%>
        <logic:present name="assignedLevel3OuList">
            <logic:iterate name="assignedLevel3OuList" id="ou">
                <html:option value="${ou.uid}">${ou.name}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>
    <td align="right" ><logic:present name="level4Ouh">${level4Ouh.name}</logic:present> </td>
<td >
<html:select property="organizationUnitId" styleId="organizationUnitId" onchange="generateUniqueId()" style="width:252px;">
        <%--<html:option value="select">select....</html:option>--%>
        <logic:present name="level4OuList">
            <logic:iterate name="level4OuList" id="ou">
                <html:option value="${ou.uid}">${ou.name}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>
</tr>
<tr> <%--class="whiteLabel"--%> 
<td style="text-align: right;"><jsp:include page="LocalOrganizationName.jsp" /> </td>
<td colspan="3">
    <html:select property="cboId" styleId="cboId" style="width:570px;">
        <%--<html:option value="select">select....</html:option>--%>
        <logic:present name="assignedCboList">
            <logic:iterate name="assignedCboList" id="cbo">
                <html:option value="${cbo.uniqueId}">${cbo.cboName}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>
</tr> 

