<%-- 
    Document   : HivStatusUpdate
    Created on : Dec 20, 2019, 10:43:01 PM
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
<title>HIV Status update</title>
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
-->
</style>
<!--<link href="sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />-->
<link type="text/css" href="css/ui-darkness/jquery-ui-1.8.custom.css" rel="Stylesheet" />
<link href="sdmenu/sdmenu.css" rel="stylesheet" type="text/css" /> 
<link href="css/general/stylefile.css" rel="stylesheet" type="text/css"/>
<link type="text/css" href="css/ui-darkness/jquery-ui-1.8.custom.css" rel="Stylesheet" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script> 
<script type="text/javascript" src="js/odm.js"></script>

<script language="javascript">
        $(function() {
        $("#dateOfNewStatus").datepicker();
        $("#dateEnrolledOnTreatment").datepicker();
    });
function stateChanged()
{
	if (xmlhttp.readyState==4)
	{
            var val=xmlhttp.responseText;
            document.getElementById("hhId").innerHTML=val
            document.getElementById("hhUniqueId").value=val   
	}
        else
        {
            //alert("error "+xmlhttp.responseText)
        }
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
function activateEnrolledOnTreatment(value)
{
    if(value == "1") 
    {
        document.getElementById("enrolledOnTreatment").disabled = false;
    }
    else 
    {
       document.getElementById("enrolledOnTreatment").value=2
       document.getElementById("enrolledOnTreatment").disabled = true;
       document.getElementById("facilityId").value="select"
       document.getElementById("facilityId").disabled = true;
    }
}
function activateReferralList(value) 
{
    if(value == "1") 
    {
        document.getElementById("facilityId").disabled = false;
    }
    else 
    {
        document.getElementById("facilityId").value="select"
        document.getElementById("facilityId").disabled = true;
    }
}
function submitForm(requiredAction,formId)
{
       setActionName(requiredAction)
       document.getElementById(formId).submit()
       return true
}

function confirmAction(name)
{
     if(name=="save")
     {
            setActionName(name)
            return true
     }
     if(confirm("Are you sure you want to "+name+" this record?"))
     {
            setActionName(name)
            return true
     }
       return false
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
                        <%--<jsp:include page="../Navigation/EnvironmentSetupLinkPage.jsp"/>--%>
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
      <td width="659" class="paramPage">
        <html:form action="/hivstatusmanager" method="POST" styleId="ahmForm">
                                <html:hidden property="actionName" styleId="actionName" />
                                <html:hidden property="hhName" styleId="hhName" />
                            <center>
                                <table> 
                                    <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                                    <tr><td colspan="4" align="center">HIV status update</td></tr>
                                      <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                                    <tr><td colspan="4" align="center" style="color:red"><html:errors/></td></tr>
                                    
                                    <tr>
                                        <td colspan="4">
                                            
                                            <fieldset><legend>Beneficiary information</legend>
                                        <table>
                                            
                                            <tr>
                                                    <td align="right"> HH Serial number </td>
                                                    <td colspan="3"><html:text property="hhSerialNo" styleId="hhSerialNo" onkeyup="generateUniqueId(1)" onblur="setActionName('beneficiaryList'); forms[0].submit()" style="width:30px;" /> 
                                                        <html:text property="hhUniqueId" styleId="hhUniqueId" readonly="true"/> 
                                                        &nbsp;<input type="button" name="search" value="Search by name" onclick="showSearchDiv()" /> &nbsp;<label id="hhId" style="color:green"><logic:present name="hhName">${hhName}</logic:present> </label> 
                                                    </td>              
                                            </tr><!--ahmList-->
                                          <tr>
                                        <td >Beneficiary list</td>
                                        <td >
                                        <html:select style="min-width:250px; max-width:350px;" property="beneficiaryId" styleId="beneficiaryId" onchange="setActionName('beneficiaryDetails');forms[0].submit()">
                                                <html:option value="select">select...</html:option>
                                                <logic:present name="hsmBeneficiaryList">
                                                    <logic:iterate name="hsmBeneficiaryList" id="beneficiary">
                                                        <html:option value="${beneficiary.beneficiaryId}">${beneficiary.firstName} ${beneficiary.surname} (${beneficiary.beneficiaryTypeObject.name})</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                            </td>
                                            <td align="right"> </td>
                                        <td >
                                            
                                            </td>
                                            
                                     </tr>  
                                     <tr>
                                         
                                       <td align="right">Name </td>
                                        <td colspan="3">
                                            <html:text property="beneficiaryName" styleId="beneficiaryName" style="width:400px;" disabled="true"/>
                                            </td>
                                        
                                     </tr>
                                     <tr>
                                         <td align="right">Sex </td>
                                        <td >
                                            <html:select property="sex" styleId="sex" disabled="true">
                                                <html:option value="select">select...</html:option>
                                                <html:option value="M">Male</html:option>
                                                <html:option value="F">Female</html:option>
                                            </html:select>
                                        </td>
                                        <td align="right">Last HIV status</td>
                                        <td ><%--disabled="${lastHivDisabled}"--%> 
                                            <html:select property="lastHivStatus" styleId="lastHivStatus"  onchange="activateEnrolledOnTreatment(this.value)" disabled="${lastHivDisabled}">
                                                <logic:present name="allHivStatus">
                                                    <logic:iterate name="allHivStatus" id="hivStatus">
                                                        <html:option value="${hivStatus.code}">${hivStatus.name}</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                            </td>
                                     </tr>
                                     
                                     
                                     <tr><td align="right">New HIV status</td>
                                <td>
                                    <html:select property="newHivStatus" styleId="newHivStatus" onchange="activateEnrolledOnTreatment(this.value)" disabled="${newHivDisabled}">
                                    <logic:present name="posNegUnkHivStatus">
                                                    <logic:iterate name="posNegUnkHivStatus" id="hivStatus">
                                                        <html:option value="${hivStatus.code}">${hivStatus.name}</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                    </html:select>
                                </td>
                                <td class="right" align="right">Date of new status </td>
                                <td>
                                  <html:text property="dateOfNewStatus" styleClass="smallfieldcellinput" styleId="dateOfNewStatus" style="width:120px;" readonly="true"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Enrolled on treatment? </td>
                                        <td>
                                            <html:select property="enrolledOnTreatment" styleClass="fieldcellinput" styleId="enrolledOnTreatment" style="width:100px;" onchange="activateReferralList(this.value)" disabled="${enrOnTreatmentDisabled}">
                                              <html:option value="0">select...</html:option>
                                                <html:option value="2">No</html:option>
                                              <html:option value="1">Yes</html:option>
                                            </html:select>
                                        </td>
                            <td class="right" align="right">Date enrolled on treatment </td>
                                <td>
                                  <html:text property="dateEnrolledOnTreatment" styleClass="smallfieldcellinput" styleId="dateEnrolledOnTreatment" style="width:120px;" readonly="true" disabled="${enrOnTreatmentDisabled}"/>
                                </td>
                              
                            </tr>
                            <tr>
                            <td align="right">Facility enrolled</td>
                            <td colspan="3" > 
                                <html:select property="facilityId" styleId="facilityId" styleClass="fieldcellinput"  style="width:450px;" disabled="${enrOnTreatmentDisabled}">
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
                                    <tr><td colspan="4" align="center"><html:submit value="Save" onclick="setActionName('save')" disabled="${serviceSaveDisabled}"/>
                                            <html:submit value="Modify" onclick="return confirmAction('modify')" disabled="${serviceModifyDisabled}"/></td>
                                            </tr>
                                            <tr><td colspan="4" align="center" height="30"> </td>
                                            </tr>
                   <tr>
                                          <td align="center" colspan="4">
                                              <logic:present name="hsmBeneficiaryList">
                                                  <div align="center" style="width:720px; max-height:300px; overflow:scroll">
                                                      <table width="700" border="1" bordercolor="#FFFFFF" style=" background-color: lightgray; border-collapse: collapse" class="regsitertable">
                                                  <tr><td>Unique Id</td><td>Name </td><td>Beneficiary type </td><td>Baseline Hiv status </td><td>Current Hiv status </td><td>Date of last HIV status </td>
                                                      <td>Enrolled on treatment </td><td>Facility enrolled </td><td>Point of update </td><td>Last modified date </td>
                                                      
                                                      
                                                      </tr><%--onclick="return confirmAction('newStatus')"--%> 
                                                  <logic:iterate name="hsmBeneficiaryList" id="beneficiary">
                                                      <tr style="background-color: ${beneficiary.rowColor}"><td>${beneficiary.beneficiaryId} </td><td>${beneficiary.fullName} </td><td>${beneficiary.beneficiaryTypeObject.name}</td>
                                                          <td>${beneficiary.baselineHivStatusObject.name} </td><td>${beneficiary.currentHivStatusObject.name} </td><td>${beneficiary.dateOfCurrentHivStatus} </td><td>${beneficiary.enrolledOnTreatmentObject.code} </td>
                                                          <td>${beneficiary.hivTreatmentFacilityId}</td><td>${beneficiary.pointOfUpdate}</td><td>${beneficiary.lastModifiedDate} </td><%--<td><a href="hivmanager.do?id=${beneficiary.beneficiaryId}_${beneficiary.beneficiaryType.code}&&hh=${beneficiary.hhUniqueId}&&p=ed">edit</a> </td>--%> 
                                                      
                                                      </tr>
                                                  </logic:iterate>

                                              </table>
                                            </div>
                                            </logic:present>
                                          </td>
                                    </tr>
                                     <tr>
                  <td valign="top" colspan="4">
                      

                  </td>
                </tr>
                                            
                                            <tr><td colspan="4" align="center" height="80"> </td>
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
  </body>
</html>


