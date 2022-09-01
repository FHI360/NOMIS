<%-- 
    Document   : FacilityOvcConsentForm
    Created on : Sep 11, 2021, 7:58:08 PM
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
<title>Facility OVC Program Offer Form</title>
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
        $("#dateEnrolledOnTreatment").datepicker();
    });
$(function() {
        $("#dateCaregiverSigned").datepicker();
    });
$(function() {
        $("#dateFacilityStaffSigned").datepicker();
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
        <html:form action="/facilityconsentform" method="POST" styleId="ahmForm">
                                <html:hidden property="actionName" styleId="actionName" />
                                <html:hidden property="clientUniqueId" styleId="clientUniqueId" />
                                
                            <center>
                                <table>
                                    <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                                    <tr><td colspan="4" align="center" style="color:red"><html:errors/></td></tr>
                                    <tr><td colspan="4" align="center">Consent to Share Contact & Address with OVC Program Partners </td></tr>
                                      <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                                    
                                    <tr><td style="font-size: 14px; font-weight: bold; color: red" colspan="4"><logic:present name="cpaWithdrawnMessage">${cpaWithdrawnMessage}</logic:present></td></tr>
                                    <tr>
                                        <td colspan="4">
                                            
                                            <fieldset><legend>Client information</legend>
                                        <table>
                                             
                                            <tr>
                                                    <td align="center" >ART No. of client</td>
                                                    
                                                    <td>
                                                    <html:text property="artNoOfClient" styleId="artNoOfClient" onblur="setActionName('clientDetails');forms[0].submit()" /> 
                                                    </td>
                                                    <td align="right">ART start date</td><td><html:text property="dateEnrolledOnTreatment" styleId="dateEnrolledOnTreatment" /> </td>              
                                            </tr><!--ahmList-->
                                            
                                            <tr>
                                        <td align="right"> First name</td>
                                        <td>
                                            <html:text property="clientFirstName" styleId="clientFirstName" style="width:150px;"/> 
                                            </td>
                                           <td align="right">Last name </td>
                                        <td > 
                                            <html:text property="clientSurname" styleId="clientSurname" style="width:150px;"/>
                                                
                                        </td>
                                        
                                     </tr>
                                     <tr>
                                        <td align="right">Age</td>
                                        <td>
                                            <html:select property="clientAge" styleId="clientAge">
                                      <html:option value="0">select...</html:option>
                                            <html:option value="1">1</html:option>
                                            <html:option value="2">2</html:option>
                                            <html:option value="3">3</html:option>
                                            <html:option value="4">4</html:option>
                                           <html:option value="5">5</html:option>
                                           <html:option value="6">6</html:option>
                                           <html:option value="7">7</html:option>
                                           <html:option value="8">8</html:option>
                                           <html:option value="9">9</html:option>
                                           <html:option value="10">10</html:option>
                                           <html:option value="11">11</html:option>
                                           <html:option value="12">12</html:option>
                                           <html:option value="13">13</html:option>
                                           <html:option value="14">14</html:option>
                                           <html:option value="15">15</html:option>
                                           <html:option value="16">16</html:option>
                                           <html:option value="17">17</html:option>
                                           
                                  </html:select>
                                  <html:select property="clientAgeUnit" styleId="clientAgeUnit">
                                      <html:option value="0">Age unit</html:option>
                                            <html:option value="1">Month</html:option>
                                            <html:option value="2">Year</html:option>
                                            
                                  </html:select>
                                            </td>
                                           <td align="right">Sex </td>
                                        <td > 
                                            <html:select property="clientSex" styleId="clientSex" >
                                                <html:option value="select">select...</html:option>
                                                <html:option value="M">Male</html:option>
                                                <html:option value="F">Female</html:option>
                                            </html:select>
                                        </td>
                                        
                                     </tr>
                                             <tr>
                                        <td align="right">Name of facility </td>
                                        <td colspan="3" >
                                            <html:select property="hivTreatmentFacilityId" styleId="hivTreatmentFacilityId"  style="width:540px;" disabled="${enrhivDisabled}">
                                              <html:option value="select">select...</html:option>
                                              <logic:present name="ovcfacilityList">
                                                  <logic:iterate name="ovcfacilityList" id="facility">
                                                      <html:option value="${facility.facilityId}">${facility.facilityName}</html:option>
                                                </logic:iterate>
                                              </logic:present> 
                                          </html:select>
                                            </td>  
                                     </tr>
                                    
                                     </table>
                                            </fieldset>
                                        </td>
                                    </tr>
                                     
                                     <tr>
                  <td valign="top" colspan="4"><fieldset>
                        <legend class="fieldset"></legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="762" height="17">
                          
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          
                            <tr><td align="center" colspan="4" style="background-color:#B3DBC3">Brief on OVC /Key messages for introducing the OVC program (the listed points to be discussed with the Head of HH by the facility staff)</td></tr>
                          <tr>
                              <td class="right" colspan="4">The OVC program has helped a lot of children and families living with HIV to improve the quality of Life </td>
                              
                          </tr>
                          <tr>
                              <td class="right" colspan="4">
                                  The OVC program works with children 0-17 years and their caregivers/households
                              </td>
                              
                          </tr>
                          <tr>
                              <td class="right" colspan="4">The OVC supports treatment outcomes (adherence, nutritional support, Psychosocial support)</td>
                          </tr>
                          <tr>
                              <td class="right" colspan="4">The households are supported by trained Community Volunteers who also conduct home visits</td>
                          </tr>
                          <tr>
                              <td class="right" colspan="4">The OVC program is recommended for every household with a HIV positive child accessing treatment in our facility</td>
                          </tr>
                          
                          <tr>
                              <td class="right" colspan="4">Should I share your contact/address with <html:text property="nameOfPersonToShareContactWith" styleId="nameOfPersonToShareContactWith" style="width:150px;"/> for possible enrolment on the OVC program?
                                  <html:select styleClass="fieldcellinput" property="shareContactQuestion" styleId="shareContactQuestion" style="width:82px;" >
                                      <html:option value="0">select...</html:option><html:option value="1">Yes</html:option><html:option value="2">No</html:option>
                                  </html:select> </td>
                          </tr>
                          <tr><td align="center" colspan="4" style="background-color:#B3DBC3">Name of caregiver</td></tr>
                          <tr>
                              <td class="right" colspan="3">First name: <html:text property="caregiverFirstName" styleId="caregiverFirstName" style="width:150px;"/> 
                                  Surname: <html:text property="caregiverSurname" styleId="caregiverSurname" style="width:150px;"/> </td>
                              <td>Date: <html:text property="dateCaregiverSigned" styleId="dateCaregiverSigned" style="width:150px;"/></td>
                          </tr>
                          
                          <tr><td align="center" colspan="4" style="background-color:#B3DBC3">Name of Facility Staff</td></tr>
                          <tr>
                              <td class="right" colspan="3">First name: <html:text property="facilityStaffFirstName" styleId="facilityStaffFirstName" style="width:150px;"/> 
                                  Surname: <html:text property="facilityStaffSurname" styleId="facilityStaffSurname" style="width:150px;"/> </td>
                              <td>Date: <html:text property="dateFacilityStaffSigned" styleId="dateFacilityStaffSigned" style="width:150px;"/></td>
                          </tr>
                          <tr>
                              <td class="right" colspan="4">&nbsp;</td>
                          </tr>
                          <tr>
                              <td class="right" colspan="4">Note: This form is to be completed by Health Facility Staff /Adherence Counsellor/ART Focal Person at the facility referring children who are HIV positive to be enrolled into the OVC program</td>
                          </tr>
                        </table>
                      </td>
                      </tr>
                  </table>
                  </fieldset>

                  </td>
                </tr>
                
                
               <tr><td colspan="4" align="center"><html:submit value="Save" onclick="setActionName('save')" disabled="${fooSaveDisabled}"/>
                    <html:submit value="Update" onclick="return confirmAction('update')" disabled="${fooModifyDisabled}"/>
                   <html:submit value="Delete" onclick="return confirmAction('delete')" disabled="${fooModifyDisabled}"/></td>
               </tr>


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
