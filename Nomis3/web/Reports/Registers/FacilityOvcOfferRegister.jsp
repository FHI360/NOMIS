<%-- 
    Document   : FacilityOvcOfferRegister
    Created on : Sep 13, 2021, 10:42:45 PM
    Author     : smomoh
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facility OVC Offer register</title>
    </head>
    <body><br/><br/>
        <jsp:include page="/includes/ReportHeading.jsp" />
        <%--<label><a href="exceldownload.do?rqparam=childRegister">Download in excel</a> </label>--%>
        <br/>
        <table border="1" cellspacing="0" cellpadding="0" style="border:1px black solid;border-collapse: collapse; margin-bottom:40px">
            <logic:present name="facilityOvcOfferListForRegister">
                <tr align="left" style=" background-color: #D7E5F2">
                                <th >S/No </th>
                                <th >${level2Ouh.name} </th>
                                <th >${level3Ouh.name}</th>
                                <th >${level4Ouh.name} </th> 
                                <th >CBO </th>
                                <th >Partner</th>
                                <th>Client Unique Id</th>
                                <th width="100">Client first name </th>
                                <th width="100">Client surname </th>
                                <th >Client age</th>
                                <th >Sex(M/F)</th>
                                <th>Treatment ID</th>
                                <th >Date enrolled on treatment?</th>
                                <th >Facility enrolled</th>
                                <th >Name Of Person to Share Contact with</th>
                                <th >Share contact Question</th>
                                <th >Caregiver first name</th>
                                <th >Caregiver surname</th>
                                <th >Date Caregiver signed</th>
                                <th >Facility Staff first name </th>
                                <th >Facility staff surname </th>
                                <th >Date facility staff signed</th>
                                <th >last modified date</th>
                                <th >Recorded by</th>
                             </tr>
                               <logic:iterate name="facilityOvcOfferListForRegister" id="foo">
                                   <tr style="background-color: ${foo.rowColor}">
                                       <td>${foo.serialNo}</td>
                                       <td>${foo.level2Ou.name}</td>
                                       <td>${foo.level3Ou.name}</td>
                                       <td>${foo.level4Ou.name}</td>
                                       <td>${foo.cbo.cboName}</td>
                                       <td>${foo.partnerName}</td>
                                       <td>${foo.clientUniqueId}</td>
                                       <td>${foo.clientFirstName}</td>
                                       <td>${foo.clientSurname}</td>
                                       <td>${foo.age} ${foo.ageUnitName}</td>
                                       <td>${foo.sex}</td>
                                       <td>${foo.treatmentId}</td>
                                       <td>${foo.dateEnrolledOnTreatment}</td>
                                       <td>${foo.referralFacility.facilityName}</td>
                                       <td>${foo.nameOfPersonToShareContactWith}</td>
                                       <td>${foo.shareContactAnswer}</td>
                                       <td>${foo.caregiverFirstName}</td>
                                       <td>${foo.caregiverSurname}</td>
                                       <td>${foo.dateCaregiverSigned}</td>
                                       
                                       <td>${foo.facilityStaffFirstName}</td>
                                       <td>${foo.facilityStaffSurname}</td>
                                       <td>${foo.dateFacilityStaffSigned}</td>
                                       <td>${foo.lastModifiedDate}</td>
                                       <td>${foo.userName}</td>
                                       
                                   </tr>
                               </logic:iterate>
            </logic:present>
        </table>
    </body>
</html>
