<%-- 
    Document   : BulkDeleteOperation
    Created on : Apr 13, 2020, 9:01:48 PM
    Author     : smomoh
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="images/kidmap2.css" rel="stylesheet" type="text/css" />
        <title> Bulk Delete Operation</title>
        <style type="text/css">
<!--
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #333333;
	font-weight: bold;
}
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #000000;
}
a:hover {
	text-decoration: underline;
	color: #0075B6;
}
a:active {
	text-decoration: none;
	color: #000000;
}
.title
{
  color: #FFFFFF;
  font-weight: bold;
}
.orglabel
{
    color: #FFFFFF;
}

-->
</style>
<script language="javascript">
function selectChkBoxes(chkname)
{
   var elements=document.getElementsByName(chkname)
    for(var i=0; i<elements.length; i++)
    {
        elements[i].checked=true
    }
}
function unselectChkBoxes(chkname)
{
   var elements=document.getElementsByName(chkname)
    for(var i=0; i<elements.length; i++)
    {
        elements[i].checked=false
    }
}
function setActionName(val)
{
    document.getElementById("actionName").value=val
}
function disableControl(id)
{
    document.getElementById(id).disabled=true;
}
function confirmAction(val)
{
    if(confirm("This action will mark the selected records for delete. Are you  sure you want to proceed?"))
    {
        setActionName(val)
        return true
    }
    else
    return false
}


</script>
    </head>
    <body>
        <br/><br/>
        <center>
            <html:form action="/bulkdeleteoperation" method="post">
                <html:hidden property="actionName" styleId="actionName"/>

        <span>
        <center>
                    
        <table >
            <tr>
                <td>
                    <table >
            
            <jsp:include page="../includes/OrganizationUnitHeader.jsp"/>
            
            
            
            <tr><td class="orglabel" align="left">Sort by </td><td colspan="3">
                    <html:select property="sortOrder" styleId="sortOrder" style="width: 290px;" >
                      <html:option value="hhUniqueId">Unique Id</html:option>
                      <html:option value="hhFirstname">Surname</html:option>  
                    </html:select> </td>
            </tr> 
            <tr><td class="orglabel" align="left">Beneficiary </td><td colspan="3">
                    <html:select property="beneficiaryType" styleId="beneficiaryType" style="width: 290px;" >
                        <%--<html:option value="ovc">Ovc</html:option>--%>
                        <html:option value="caregiver">Caregiver</html:option>
                        <html:option value="household">Household</html:option>
                     </html:select> </td>
            </tr> 
            <tr><td colspan="3" align="center"><html:submit property="hhmReportBtn" value="Generate list" onclick="setActionName('generateList');" style="width: 150px; margin-left: 50px;" /></td></tr>
                    </table></td> <td colspan="2">&nbsp;</td>
                    <td style="vertical-align:top">
            
            
            </td> 
             
        </table>
                        
                    <table border=0>
                        <tr><td align="center" height="40">&nbsp;</td></tr>
                                
    
    <logic:present name="bdhheList">
    <tr><td style="border: none; text-align: left">
            
                        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid; border-collapse: collapse; margin-bottom:40px">
                            <tr align="left" bgcolor="#D7E5F2"> 
                                <th>SNo</th>
                                <th>Unique id</th>
                                <th >Address</th>
                                <th >Date of assessment (mm/dd/yyyy)</th>
                                <th >Volunteer name</th>
                                <th >Marked for delete </th>
                                <th >View Household members</th>
                                <th >Select records to be marked</th>
                                
                            </tr>
                            <logic:iterate id="hviData" name="bdhheList">

                                <tr align="left" bgcolor="${hviData.rowColor}">
                                    <td width="3%">${hviData.serialNo}</td>
                                    <td width="10%">
                                        ${hviData.hhUniqueId}
                                    </td>
                                    <td width="23%">
                                        ${hviData.address}
                                    </td>
                                                                       
                                    <td width="10%">
                                        ${hviData.dateOfAssessment}
                                    </td>
                                    <td width="4%">${hviData.volunteerName}</td>
                                    <td width="4%">${hviData.markedForDelete}</td>
                                    <td width="10%">
                                        <a href="householdMergeAction.do?id=${hviData.hhUniqueId}" target="_blank"> View members</a>
                                    </td>
                                    <td width="8%"><html:checkbox property="householdsToDelete" styleId="${hviData.hhUniqueId}" value="${hviData.hhUniqueId}"/></td>
                                     
                                </tr>
                            </logic:iterate>
                                
                        </table>
                            </td></tr>
    
                            <tr><td >
                                <input type="button" value="Select all" onclick="selectChkBoxes('householdsToDelete')" />
                                <input type="button" value="Unselect all" onclick="unselectChkBoxes('householdsToDelete')" />
                            </td></tr>
                           </logic:present> 
                    <logic:present name="bdadultList">
                        <tr><td style="border: none; text-align: left">
            
                        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid; border-collapse: collapse; margin-bottom:40px">
                            <tr align="left" bgcolor="#D7E5F2">
                                <th>SNo</th>
                                <th >${level2Ouh.name} </th>
                                <th >${level3Ouh.name}</th>
                                <th >${level4Ouh.name} </th> 
                                <th >CBO </th>
                                <th>Unique id</th>
                                <th>Beneficiary id</th>
                                <th >Full name </th>
                                <th >Address</th>
                                <th >Age at baseline</th>
                                <th >Current age</th>
                                <th >Sex(M/F)</th>
                                <th >Phone</th>
                                
                                
                                <th >No. of children</th>
                                <th >Date of assessment (mm/dd/yyyy)</th>
                                <th >Last modified date</th>
                                <th >Recorded by</th>
                                <th >View children</th>
                                <th >Marked for delete</th>
                                <th >Change </th>
                                
                            </tr>
                            
                            <logic:iterate id="hviData" name="bdadultList">

                                    <tr align="left" bgcolor="${hviData.rowColor}">
                                    <td width="3%">${hviData.serialNo}</td>
                                    <td>${hviData.hhe.level2Ou.name}</td>
                                   <td>${hviData.hhe.level3Ou.name}</td>
                                   <td>${hviData.hhe.level4Ou.name}</td>
                                   <td>${hviData.hhe.cbo.cboName}</td>
                                    <td width="10%">
                                        ${hviData.hhUniqueId}
                                    </td>
                                   <td width="10%">
                                        ${hviData.beneficiaryId}
                                    </td>
                                    <td width="15%">
                                        ${hviData.surname} ${hviData.firstName}
                                    </td>
                                    
                                    <td width="23%">
                                        ${hviData.address}
                                    </td>
                                    
                                    <td width="3%">
                                        ${hviData.ageAtBaseline}
                                    </td>
                                    <td width="3%">
                                        ${hviData.currentAge}
                                    </td>
                                    <td width="3%">
                                        ${hviData.sex}
                                    </td>
                                    <td width="8%">
                                        ${hviData.phoneNumber}
                                    </td>
                                                                        
                                    <td width="3%">
                                        ${hviData.numberOfChildren}
                                    </td>
                                    
                                    <td width="10%">
                                        ${hviData.dateOfEnrollment}
                                    </td>
                                    <td>${hviData.lastModifiedDate}</td>
                                    <td width="4%">${hviData.recordedBy}</td>
                                    
                                    <td width="10%">
                                        <a href="householdMergeAction.do?id=${hviData.beneficiaryId}" target="_blank"> View members</a>
                                    </td>
                                    <td width="3%">
                                        ${hviData.markedForDelete}
                                    </td>
                                    <td width="8%"><html:checkbox property="householdsToDelete" styleId="${hviData.beneficiaryId}" value="${hviData.beneficiaryId}"/></td>
                                     
                                </tr>
                            </logic:iterate>
                                
                        </table>
                            </td></tr>
    
                            <tr><td >
                                <input type="button" value="Select all" onclick="selectChkBoxes('householdsToDelete')" />
                                <input type="button" value="Unselect all" onclick="unselectChkBoxes('householdsToDelete')" />
                            </td></tr>
                           </logic:present>
                            
                            <logic:present name="bdOvcList">
                        <tr><td style="border: none; text-align: left">
            
                        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid; border-collapse: collapse; margin-bottom:40px">
                            <tr align="left" bgcolor="#D7E5F2">
                                <th>SNo</th>
                                <th >${level2Ouh.name} </th>
                                <th >${level3Ouh.name}</th>
                                <th >${level4Ouh.name} </th> 
                                <th >CBO </th>
                                <th>Date of enrollment</th>
                                <th>HH unique id</th>
                                <th>Beneficiary id</th>
                                <th >Full name </th>
                                <th >Address</th>
                                <th >Age at baseline</th>
                                <th >Current age</th>
                                <th >Sex(M/F)</th>
                                <th >Current HIV status</th>
                                <th >Name of caregiver</th>
                                <th >Relationship to child </th>
                                <th >Name of volunteer</th>
                                <th >Date created</th>
                                <th >Recorded by</th>
                                <th >Marked for delete</th>
                                
                            </tr>
                            
                            <logic:iterate id="ovc" name="bdOvcList">

                                    <tr style="background-color: ${ovc.rowColor}">
                                       <td>${ovc.serialNo}</td>
                                       <td>${ovc.hhe.level2Ou.name}</td>
                                       <td>${ovc.hhe.level3Ou.name}</td>
                                       <td>${ovc.hhe.level4Ou.name}</td>
                                       <td>${ovc.hhe.cbo.cboName}</td>
                                       <td>${ovc.dateOfEnrollment}</td>
                                       <td>${ovc.hhUniqueId}</td>
                                       <td>${ovc.ovcId}</td>
                                       <td>${ovc.firstName} ${ovc.surname}</td>
                                       <td>${ovc.hhe.address}</td>
                                       <td>${ovc.ageAtBaseline}</td>
                                       <td>${ovc.currentAge} ${ovc.currentAgeUnitName}</td>
                                       <td>${ovc.sex}</td>
                                       <td>${ovc.currentHivStatusObject.name}</td>
                                       <td>${ovc.primaryCaregiver.fullName}</td>
                                       <td>${ovc.caregiverRelationshipObj.name}</td>
                                       
                                       <td>${ovc.communityWorker.firstName} ${ovc.communityWorker.surname}</td>
                                       <td>${ovc.lastModifiedDate}</td>
                                       <td>${ovc.recordedBy}</td>
                                       <td>${ovc.markedForDelete}</td>
                                   </tr>
                            </logic:iterate>
                                
                        </table>
                            </td></tr>
    
                            <tr><td >
                                <input type="button" value="Select all" onclick="selectChkBoxes('householdsToDelete')" />
                                <input type="button" value="Unselect all" onclick="unselectChkBoxes('householdsToDelete')" />
                            </td></tr>
                           </logic:present>
      
        <tr><td align="center"><html:submit value="Mark selected records for delete" onclick="return confirmAction('delete')" style="width: 180px; margin-left: 50px;" disabled="${hhmBtnDisabled}"/>
            <html:submit value="Generate scripts for marked records" onclick="return confirmAction('BulkDeleteUpload.jsp')" style="width: 230px; margin-left: 50px;" disabled="${hhmBtnDisabled}"/></td></tr>
     </table>                 
        </center>
        </span>
        </html:form>
        </center>
    </body>
</html>
