<%-- 
    Document   : CareplanAchievementChecklist
    Created on : Dec 26, 2019, 9:36:26 PM
    Author     : smomoh
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Care plan achievement checklist</title>
<link href="images/untitled.css" rel="stylesheet" type="text/css" />
<link href="images/sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
        font-size: 12px;
	background-image: url(images/bg.jpg);
	background-repeat: repeat-x;
}
-->
</style>
<script type="text/javascript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
<link href="images/untitled.css" rel="stylesheet" type="text/css" />
<link href="images/sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: underline;
}
a:active {
	text-decoration: none;
}
.whiteLabel
{
    color: white;
    font-size: 14;
}
-->
</style>
<link href="sdmenu/sdmenu.css" rel="stylesheet" type="text/css" /> 
<link href="css/general/stylefile.css" rel="stylesheet" type="text/css"/>
<link type="text/css" href="css/ui-darkness/jquery-ui-1.8.custom.css" rel="Stylesheet" />
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
        
<script type="text/javascript" src="js/odm.js"></script>
<script language="javascript">
var beneficiaryType=0
var defaultDate="01/01/1900"
$(function() {
        $("#dateOfAssessment").datepicker();
    });

function stateChanged()
{
	if (xmlhttp.readyState==4)
	{
            var val=xmlhttp.responseText;
            if(callerId=="search")
            {
                showSearchResult(val)
            }
            else if(callerId=="schoolList")
            {
                loadSchoolList(val)
            }
           else
           {
               //alert(beneficiaryType)
              
                if(beneficiaryType==1)
                {
                    document.getElementById("hhId").innerHTML=val
                    document.getElementById("hhUniqueId").value=val
                }
                
                else if(beneficiaryType==2)
                document.getElementById("ovcId").value=val
               
              <%--else
              {
                document.getElementById("hhUniqueId").value=val
              }--%>
           }
	}
        else
        {
            //alert("error "+xmlhttp.responseText)
        }
}
function showSearchResult(value)
{
    document.getElementById("searchContent").innerHTML=value
    showComponent("pop")
}
function confirmAction(value)
{
    if(confirm("Are you sure you want to "+value+" this record?"))
    {
        setActionName(value)
        return true
    }
    else
    return false
}
function generateUniqueId(val)
{
    level2OuId=document.getElementById("level2OuId").value;
    level3OuId=document.getElementById("level3OuId").value;
    cboUniqueId=document.getElementById("cboId").value;
    var req=""
    if(val==1)
    {
        serialNumber=document.getElementById("hhSerialNo").value;
        req=level2OuId+";"+level3OuId+";"+cboUniqueId+";"+serialNumber
        beneficiaryType=1
    }
    else if(val==2)
    {
        serialNumber=document.getElementById("childSerialNo").value;
        hhUniqueId=document.getElementById("hhUniqueId").value;
        req=hhUniqueId+";"+serialNumber
        beneficiaryType=2
    }
    
    //req=level2OuId+";"+level3OuId+";"+cboUniqueId+";"+serialNumber
    getValuesByAjaxApi('ajaxaction.do',req,'uniqueId')
    return true;
}
function submitForm(requiredAction,formId)
{
       setActionName(requiredAction)
       document.getElementById(formId).submit()
       return true
}
function setActionName(val)
{
    document.getElementById("actionName").value=val
}
</script>
<link href="kidmap-default.css" rel="stylesheet" type="text/css" />
<link href="images/kidmap2.css" rel="stylesheet" type="text/css" />
<link href="images/untitled.css" rel="stylesheet" type="text/css" />
<link href="images/sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />
</head>

<body onload="MM_preloadImages('images/About_down.jpg','images/Admin_down.jpg','images/Rapid_down.jpg','images/care_down.jpg','images/OVC_down.jpg');">

<table width="949" border="0" align="center" cellpadding="0" cellspacing="0" class="boarder">
  <!--DWLayoutTable-->
  <tr>
    <td height="117" colspan="2" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <!--DWLayoutTable-->
      <tr>
        <td width="7" height="2"></td>
          <td width="271"></td>
          <td width="137"></td>
          <td width="95"></td>
          <td width="8"></td>
          <td width="95"></td>
          <td width="8"></td>
          <td width="95"></td>
          <td width="8"></td>
          <td width="95"></td>
          <td width="8"></td>
          <td width="95"></td>
          <td width="23"></td>
        </tr>

<jsp:include page="../includes/Pagetabs.jsp" />

      <tr>
        <td height="30"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>&nbsp;</td>
        <td></td>
        <td></td>
        <td></td>
        </tr>

      <tr>
        <td height="17"></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><jsp:include page="../Navigation/Logout.jsp" /></td>
          <td></td>
        </tr>
      <tr>
        <td height="3" colspan="13" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#038233">
          <!--DWLayoutTable-->
          <tr>
            <td width="945" height="2"></td>
            </tr>
          <!--DWLayoutTable-->
          <tr>
            <td height="1"></td>
              </tr>
        </table></td>
        </tr>

    </table></td>
  </tr>
  <tr>
    <td width="931" height="251" valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <!--DWLayoutTable-->
      <tr>
        
        <td width="231" rowspan="2" valign="top"  bgcolor="#038233">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#038233">
          <!--DWLayoutTable-->
          <tr>
            <td width="231" height="28" valign="top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <!--DWLayoutTable-->
              <tr>
                <td width="231" height="28"><img src="images/dataentry.jpg" width="231" height="28" /></td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td height="85" valign="top">
                <div style="float: left" id="my_menu" class="sdmenu">
                    <div>
                        <div><jsp:include page="../Navigation/DataEntryLinkPage.jsp"/></div>
                        
                    </div>
              </div>

            </td>
          </tr>
          <tr>
            <td height="30" valign="top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <!--DWLayoutTable-->
              <tr>
                <td width="180" height="30"><img src="images/reports.jpg" width="231" height="30" /></td>
                    </tr>
            </table></td>
          </tr>
          <tr>
            <td height="157" valign="top"><div style="float: left" id="my_menu2" class="sdmenu" >
              <div><jsp:include page="../Navigation/ReportLinkPage.jsp"/></div>
            </div></td>
          </tr>
      </table></td>
    <td width="10">&nbsp;</td>
      <td width="659" class="regsitertable">
        <html:form action="/careplanachievement" method="POST" styleId="ahmForm">
                                <html:hidden property="actionName" styleId="actionName" />
                                <html:hidden property="recordId" styleId="recordId" />
                                
                            <center>
                                <table>
                                    <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                                    <tr><td colspan="4" align="center">Care plan achievement checklist </td></tr>
                                      <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                                    <tr><td colspan="4" align="center" style="color:red"><html:errors/></td></tr>
                                    <tr><td style="font-size: 14px; font-weight: bold; color: red" colspan="4"><logic:present name="cpaWithdrawnMessage">${cpaWithdrawnMessage}</logic:present></td></tr>
                                    <tr>
                                        <td colspan="4">
                                            
                                            <fieldset><legend>Household head information</legend>
                                        <table>
                                             
                                            <tr>
                                                    <td align="center" >HH Serial No </td>
                                                    
                                                    <td colspan="3">
                                                        <html:text property="hhSerialNo" styleId="hhSerialNo" onkeyup="return generateUniqueId(1);" onblur="setActionName('householdDetails');forms[0].submit()" style="width:30px;"/> 
                                                        <html:text property="hhUniqueId" styleId="hhUniqueId" /> 
                                                        &nbsp;<input type="button" name="search" value="Search by name" onclick="showSearchDiv()" /> 
                                                        &nbsp;<label id="hhId" style="color:green"><logic:present name="hhName">${hhName}</logic:present> </label> 
                                                    </td>              
                                            </tr><!--ahmList-->
                                            <tr>
                                        <td align="right"> Caregiver first name</td>
                                        <td>
                                            <html:text property="firstName" styleId="firstName" style="width:150px;"/> 
                                            </td>
                                           <td align="right">Last name </td>
                                        <td > 
                                            <html:text property="surname" styleId="surname" style="width:150px;"/>
                                                
                                        </td>
                                        
                                     </tr>
                                             <tr>
                                        <td align="right">Date of enrollment </td>
                                        <td >
                                            <html:text property="dateOfEnrollment" styleId="dateOfEnrollment" onchange="generateUniqueId()" disabled="true"/>
                                            </td>
                                            <td align="right">Sex </td>
                                        <td >
                                            <html:select property="sex" styleId="sex" onchange="generateUniqueId()" disabled="true">
                                                <html:option value="0">select...</html:option>
                                                <html:option value="M">Male</html:option>
                                                <html:option value="F">Female</html:option>
                                            </html:select>
                                            </td>
                                        
                                     </tr>
                                    
                                     
                                     <tr>
                                         
                                        <td align="right">Phone number</td>
                                        <td >
                                            <html:text property="phoneNumber" styleId="phoneNumber" disabled="true"/>
                                            </td>
                                            <td > </td>
                                        <td >
                                            
                                        </td> 
                                     </tr>
                                    
                                    
                                     </table>
                                            </fieldset>
                                        </td>
                                    </tr>
                                     
                                     <tr>
                  <td valign="top" colspan="4"><fieldset>
                        <legend class="fieldset">Assessment checklist </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="762" height="17">
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">

			<tr>
                          <td width="20%">Date of assessment: </td>
                          <td width="80%" colspan="2">
                              <html:text property="dateOfAssessment" styleId="dateOfAssessment" styleClass="smallfieldcellinput" onchange="setActionName('assessmentDetails'); forms[0].submit()" readonly="true"/>&nbsp;(mm/dd/yyyy)
                          </td>
                        </tr> 
                        </table>
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          
                            <tr><td align="center" colspan="4" style="background-color:#B3DBC3">HEALTH</td></tr>
                          <tr>
                              <td class="right" colspan="2">All children, adolescents, and caregivers in the household have known HIV status or a test is not required based on risk assessment</td>
                              <td >
                                  <html:select styleClass="fieldcellinput" property="childrenHivStatusknown" style="width:82px;" styleId="childrenHivStatusknown" >
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option>
                                  </html:select> </td><td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">
                                  All HIV+ children, adolescents and caregiver in the household are linked to and have adhered to treatment for 12 months after initiation of ART? 
                              </td>
                              <td>
                                  <html:select styleClass="fieldcellinput" property="hivPosAdolscentsLinked" style="width:82px;" styleId="hivPosAdolscentsLinked">
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option><html:option value="3">N/A</html:option>
                                      
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">All HIV+ children, adolescents and caregivers in the household have a viral load result documented in the last 12 months</td><td >
                                  <html:select styleClass="fieldcellinput" property="documentedViralLoadResult" style="width:82px;" styleId="documentedViralLoadResult" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option><html:option value="3">N/A</html:option>
                                  </html:select> </td><td></td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">All adolescents 10-17 years of age in the household have key knowledge about preventing HIV infection</td><td >
                                  <html:select styleClass="fieldcellinput" property="hivPreventionKnowledge" style="width:82px;" styleId="hivPreventionKnowledge">
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option><html:option value="3">N/A</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">No children < 5 years in the household are undernourished</td><td >
                                  <html:select styleClass="fieldcellinput" property="childrenNotUndernourished" style="width:82px;" styleId="childrenNotUndernourished" >
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option><html:option value="3">N/A</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr><td align="center" colspan="4" style="background-color:#B3DBC3"> SAFE</td></tr>
                          <tr>
                              <td class="right" colspan="2">All children in the household have birth certificates</td><td >
                                  <html:select styleClass="fieldcellinput" property="allChildrenHaveBirthCert" styleId="allChildrenHaveBirthCert" style="width:82px;" >
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">There is a stable adult in the household who provides consistent care, attention and support to the children and adolescents</td><td >
                                  <html:select styleClass="fieldcellinput" property="stableAdultInHousehold" style="width:82px;" styleId="stableAdultInHousehold" >
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">Children, adolescent or caregivers in the household reported experience(s) of violence in the last Six months</td><td >
                                  <html:select styleClass="fieldcellinput" property="violenceIncidenceReport" styleId="violenceIncidenceReport" style="width:82px;">
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option><html:option value="3">N/A</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          <tr><td align="center" colspan="4" style="background-color:#B3DBC3">SCHOOLED</td></tr>
                          <tr>
                              <td class="right" colspan="2">All children aged 6 years and above are enrolled in school</td><td >
                                  <html:select styleClass="fieldcellinput" property="childrenEnrolledInSchool" style="width:82px;" styleId="childrenEnrolledInSchool" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option><html:option value="3">N/A</html:option>
                                  </html:select> </td><td> </td>
                          </tr>
                          
                          <tr>
                              <td class="right" colspan="2">All children and adolescents enrolled in school have attended regularly and progressed in the last year</td><td >
                                  <html:select styleClass="fieldcellinput" property="regularSchoolAttendance" styleId="regularSchoolAttendance" style="width:82px;">
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option><html:option value="3">N/A</html:option>
                                  </html:select>                                         
                          </tr>
                          <tr><td align="center" colspan="4" style="background-color:#B3DBC3">STABLE</td></tr>
                          <tr>
                              <td class="right" colspan="2">Caregiver is engaged in economic activities that helps meet the critical needs of the children in the household</td><td >
                                  <html:select styleClass="fieldcellinput" property="cgiversEconomicActivity" style="width:82px;" styleId="cgiversEconomicActivity" disabled="${hracfieldset1disabled}">
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option>
                                  </html:select>                                         
                          </tr>
                          
                          <tr>
                              <td class="right" colspan="2">Caregiver can identify an individual or group recognized as providing social and emotional support</td><td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="emotionalSupportTeamIdentification" style="width:82px;" styleId="emotionalSupportTeamIdentification" disabled="${hracchildatriskdisabled}">
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option> 
                                  </html:select>
                                   </td>
                          </tr>
                          
                        
                        </table>
                      </td>
                      </tr>
                  </table>
                  </fieldset>

                  </td>
                </tr>
                <tr>
                  <td colspan="2">Household graduated </td>
                <td colspan="2"> 
                    <html:select property="householdGraduated" styleId="householdGraduated" style="width:200px;" >
                          <%--<html:option value="select">select...</html:option>--%>
                          <html:option value="1">Yes</html:option>
                          <html:option value="2">No</html:option>
                          
                      </html:select>
                </td>

                                
              </tr>
                <tr>
                  <td colspan="2">Completed by </td>
                <td colspan="2"> 
                    <html:select property="volunteerName" styleId="volunteerName" style="width:200px;" >
                          <%--<html:option value="select">select...</html:option>--%>
                          <html:option value="xxxxxxxxxxx">None</html:option>
                          <logic:present name="enumeratorList">
                              <logic:iterate name="enumeratorList" id="er">
                                <html:option value="${er.communityWorkerId}">${er.firstName} ${er.surname}</html:option>
                              </logic:iterate>

                          </logic:present>
                      </html:select>
                </td>

                                
              </tr>
                                       <tr><td colspan="4" align="center"><html:submit value="Save" onclick="setActionName('save')" disabled="${cpaSaveDisabled}"/>
                                            <html:submit value="Update" onclick="return confirmAction('update')" disabled="${cpaModifyDisabled}"/>
                                           <html:submit value="Delete" onclick="return confirmAction('delete')" disabled="${cpaModifyDisabled}"/></td></tr>
                                    
                                    
                                </table>
                            </center>
                            </html:form>
         </td>
      
    <td width="18">&nbsp;</td>
  </tr>

  <tr>
    <td height="25" colspan="2" valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#038233">
      <!--DWLayoutTable-->
      <tr>
        <td width="945" height="25" class="copyright"><jsp:include page="../includes/Version.jsp"/></td>
        </tr>
    </table></td>
  </tr>
</table>
</table>
  </body>
</html>

