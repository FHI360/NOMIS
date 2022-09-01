<%-- 
    Document   : OrganizationUnitHeaderWithoutCBO
    Created on : Oct 20, 2018, 8:33:53 AM
    Author     : smomoh
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<tr><td colspan="4" align="center"> </td></tr>
<logic:present name="siteSetupOu">
<tr><td colspan="4" align="center"><label style="color: blue">${level2Ouh.name}: ${siteSetupOu.name} </label></td></tr>
</logic:present>
<tr>
<td>${level2Ouh.name} </td>
<td colspan="3">
    <html:select property="level2OuId" styleId="level2OuId" style="width:570px;" onchange="setActionName('level3OuList');forms[0].submit()" >
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
<td><logic:present name="level3Ouh">${level3Ouh.name}</logic:present> </td>
<td >
<html:select property="level3OuId" styleId="level3OuId" onchange="setActionName('level4OuList');forms[0].submit()" >
        <html:option value="select">select....</html:option>
        <logic:present name="level3OuIdList">
            <logic:iterate name="level3OuIdList" id="ou">
                <html:option value="${ou.uid}">${ou.name}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>
    <td align="right"><logic:present name="level4Ouh">${level4Ouh.name}</logic:present> </td>
<td >
<html:select property="level4OuId" styleId="level4OuId" onchange="generateUniqueId();forms[0].submit()">
        <html:option value="select">select....</html:option>
        <logic:present name="level4OuList">
            <logic:iterate name="level4OuList" id="ou">
                <html:option value="${ou.uid}">${ou.name}</html:option>
            </logic:iterate>
        </logic:present>
    </html:select>
    </td>
</tr>