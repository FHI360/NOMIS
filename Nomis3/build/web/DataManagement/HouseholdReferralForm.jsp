<%-- 
    Document   : HouseholdReferralForm
    Created on : Dec 28, 2019, 9:46:44 PM
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
<title>Vulnerable Household Referral form</title>
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
$(function() {
        $("#dateOfReferral").datepicker();
    });
var beneficiaryType=0
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

function confirmAction(name)
{
     if(name=="save" || name=="generateForms")
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
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
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
    <td width="10" >&nbsp;</td>
      <td width="659" class="regsitertable">
        <html:form action="/householdreferral" method="POST" styleId="serviceForm">
                                <html:hidden property="actionName" styleId="actionName" />
                                <%--<html:hidden property="beneficiaryId" styleId="beneficiaryId" />--%>
                                <html:hidden property="hhName" styleId="hhName" />
                            <center>
                                <table>
                                    <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                                    <tr><td colspan="4" align="center">Vulnerable Household Referral form </td></tr>
                                      <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                                    <tr><td colspan="4" align="center" style="color:red"><html:errors/></td></tr>
                                    <tr><td style="font-size: 14px; font-weight: bold; color: red" colspan="4"><logic:present name="hhrWithdrawnMessage">${hhrWithdrawnMessage}</logic:present></td></tr>
                                    <tr>
                                        <td colspan="4">
                                            
                                            <fieldset><legend>Beneficiary information</legend>
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
                                        <td >Beneficiary list</td>
                                        <td >
                                        <html:select style="min-width:250px; max-width:350px;" property="beneficiaryId" styleId="beneficiaryId" onchange="setActionName('beneficiaryDetails');forms[0].submit()">
                                                <html:option value="select">select...</html:option>
                                                <logic:present name="refBeneficiaryList">
                                                    <logic:iterate name="refBeneficiaryList" id="beneficiary">
                                                        <html:option value="${beneficiary.beneficiaryId}">${beneficiary.firstName} ${beneficiary.surname} (${beneficiary.beneficiaryTypeObject.name})</html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                            </td>
                                            <td align="right">Date of enrollment </td>
                                        <td >
                                            <html:text property="dateOfEnrollment" styleId="dateOfEnrollment" onchange="generateUniqueId()" disabled="true"/>
                                            </td>
                                            
                                     </tr>
                                    
                                    <tr>
                                         <td align="right">Sex </td>
                                        <td >
                                            <html:select property="sex" styleId="sex" onchange="generateUniqueId()" disabled="true">
                                                <html:option value="select">select...</html:option>
                                                <html:option value="M">Male</html:option>
                                                <html:option value="F">Female</html:option>
                                            </html:select>
                                            </td>
                                        <td align="right">Phone number</td>
                                        <td >
                                            <html:text property="phoneNumber" styleId="phoneNumber" disabled="true"/>
                                            </td>
                                     </tr>
                                    
                                    <tr>
                                        
                                        <td align="right">HIV status</td>
                                        <td colspan="3">
                                            <html:select property="hivStatus" styleId="hivStatus" onchange="setEnrolledOnTreatmentStatus(this.value)" disabled="true">
                                                <logic:present name="allHivStatus">
                                                    <logic:iterate name="allHivStatus" id="hivStatus">
                                                        <html:option value="${hivStatus.code}">${hivStatus.name}</html:option>
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
                        <legend class="fieldset">Services </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="762" height="17">
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                        <tr>
                                                <td>Referring organization</td>
                                                <td colspan="2">
                                                    <html:select property="referringOrganization" styleId="referringOrganization" style="min-width:300px;" >
                                                        <html:option value="select">select...</html:option>
                                                        <logic:present name="referringOrganizationList">
                                                            <logic:iterate name="referringOrganizationList" id="cbo">
                                                              <html:option value="${cbo.uniqueId}">${cbo.cboName}</html:option>
                                                            </logic:iterate>
                                                        </logic:present>
                                                    </html:select>
                                                        
                                                </td>   
                                            </tr>
                                            <tr>
                                                <td>Receiving organization</td>
                                                <td colspan="2">
                                                    <html:select property="receivingOrganization" styleId="receivingOrganization" style="min-width:300px;">
                                                        <html:option value="select">select...</html:option>
                                                        <logic:present name="refFacilityList">
                                                            <logic:iterate name="refFacilityList" id="facility">
                                                              <html:option value="${facility.facilityId}">${facility.facilityName}</html:option>
                                                            </logic:iterate>
                                                        </logic:present> 
                                                    </html:select>

                                                </td>   
                                            </tr>
			<tr>
                          <td width="20%">Date of Referral </td>
                          <td width="80%" colspan="2">
                              <html:text property="dateOfReferral" styleId="dateOfReferral" styleClass="smallfieldcellinput" onchange="setActionName('serviceDetails'); forms[0].submit()" readonly="true"/>&nbsp;(mm/dd/yyyy)
                          </td>

                        </tr>
                        </table>
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr style="border: #528B8B 4px solid">
                              <td width="21%" bgcolor="#CCCCCC" class="right" >HEALTHY</td>
                              <td colspan="2" bgcolor="#CCCCCC">&nbsp;</td>
                              
                          </tr>
                          
                          <tr>
                              <td rowspan="19">&nbsp;</td>
                              <td width="4%"><html:multibox property="healthServices" value="1r" styleClass="smallfieldcellselect" styleId="1r" disabled="${hivPosRelatedServices}"/></td>
                            <td>HIV-related testing (HTS, PMTCT) </td>
                          </tr>
                          <tr>
                            <td width="4%"><html:multibox property="healthServices" value="2r" styleClass="smallfieldcellselect" styleId="2r" disabled="${hivPosRelatedServices}"/></td>
                            <td>Early Infant Diagnosis (EID) </td>
                          </tr>
                          <tr>
                            <td width="4%"><html:multibox property="healthServices" value="3r" styleClass="smallfieldcellselect" styleId="3r" disabled="${hivPosRelatedServices}"/></td>
                            <td>ART</td>
                          </tr>
			   
                          <tr>

                              <td width="4%"><html:multibox property="healthServices" value="4r" styleClass="smallfieldcellselect" styleId="4r" disabled="${hivPosRelatedServices}"/></td>
                            <td>CD4 VL</td>
                          </tr>
                         
                          <tr>
                              
                              <td width="4%"><html:multibox property="healthServices" value="5r" styleClass="smallfieldcellselect" styleId="5r" disabled="${nonPosRelatedServices}"/></td>
                            <td>TB diagnosis</td>
                          </tr>                         
                          <tr>

                              <td width="4%"><html:multibox property="healthServices" value="6r" styleClass="smallfieldcellselect" styleId="6r" disabled="${hivPosRelatedServices}"/></td>
                            <td>STI treatment</td>
                          </tr>
                          <tr>

                              <td width="4%"><html:multibox property="healthServices" value="7r" styleClass="smallfieldcellselect" styleId="7r"/></td>
                            <td>Prevention support (PrEP/condoms and/or VMMC) </td>
                          </tr>
                          <tr>
                              
                              <td><html:multibox property="healthServices" value="8r" styleClass="smallfieldcellselect" styleId="8r"/></td>
                            <td>Sexual/Reproductive health</td>
                          </tr>
                          <tr >
                              <td><html:multibox property="healthServices" value="9r" styleClass="smallfieldcellselect" styleId="9r" disabled="{eidRelatedServices}"/></td>
                            <td>Routine healthcare (e.g. Immunization etc.)</td>
                          </tr>
                          
                          <tr>
                              
                              <td><html:multibox property="healthServices" value="10r" styleClass="smallfieldcellselect" styleId="10r"/></td>
                            <td>Emergency health care</td>
                          </tr>
                          <tr>
                              
                              <td><html:multibox property="healthServices" value="11r" styleClass="smallfieldcellselect" styleId="11r"/></td>
                            <td>Vitamin A, Zinc & Iron supplementation</td>
                          </tr>
                          <tr>
                              
                              <td><html:multibox property="healthServices" value="12r" styleClass="smallfieldcellselect" styleId="12r"/></td>
                            <td>Insecticide treated Nets</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="13r" styleClass="smallfieldcellselect" styleId="13r"/></td>
                            <td>Water treatment</td>
                          </tr>
                          
                          <tr>
                            <td><html:multibox property="healthServices" value="14r" styleClass="smallfieldcellselect" styleId="14r"/></td>
                            <td>Food and nutrition supplement</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="15r" styleClass="smallfieldcellselect" styleId="15r"/></td>
                            <td>Severe Acute Malnutrition (SAM)</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="healthServices" value="16r" styleClass="smallfieldcellselect" styleId="16r"/></td>
                            <td>Wasting/edema</td>
                          </tr>
                                                  
                        </table>

                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr style="border: #528B8B 4px solid">
                            <td width="21%" bgcolor="#CCCCCC" class="right">SAFE </td>
                            <td width="4%" colspan="2" bgcolor="#CCCCCC">&nbsp;</td>
                            
                          </tr>
                          <tr>
                              <td rowspan="14">&nbsp;</td>
                            <td><html:multibox property="safetyServices" value="17r" styleClass="smallfieldcellselect" styleId="17r"/></td>
                            <td>Post-violence trauma-informed counseling</td>
                          </tr>
                          <tr>
                          <tr>
                            
                            <td width="4%"><html:multibox property="safetyServices" value="18r" styleClass="smallfieldcellselect" styleId="18r"/></td>
                            <td width="75%">Spiritual support</td>
                          </tr> 
                          <tr>
                            <td><html:multibox property="safetyServices" value="19r" styleClass="smallfieldcellselect" styleId="19r"/></td>
                            <td>Community support group (positive living group, caregiver support group, etc.)</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="safetyServices" value="20r" styleClass="smallfieldcellselect" styleId="20r"/></td>
                            <td>Life building skills</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="safetyServices" value="21r" styleClass="smallfieldcellselect" styleId="21r"/></td>
                            <td>Legal services</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="safetyServices" value="22r" styleClass="smallfieldcellselect" styleId="22r"/></td>
                            <td>Birth registration</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="safetyServices" value="23r" styleClass="smallfieldcellselect" styleId="23r"/></td>
                            <td>Post-violence medical services</td>
                          </tr>
                          <tr>
                            <td><html:multibox property="safetyServices" value="24r" styleClass="smallfieldcellselect" styleId="24r"/></td>
                            <td>Emergency shelter</td>
                          </tr>
                                                                             
                          </table>
                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr style="border: #528B8B 4px solid">
                            <td width="20%" bgcolor="#CCCCCC" class="right">SCHOOLED </td>
                            <td width="75%" colspan="2" bgcolor="#CCCCCC"> </td>
                          </tr>
                          <tr>
                              <!---->
                            <td rowspan="7"> </td>
                            <td width="4%"><html:multibox property="schoolServices" value="5er" styleClass="smallfieldcellselect" styleId="5er"/></td>
                            <td>School enrollment/re-enrollment</td>
                          </tr>
                          <tr>
                           
                            <td width="4%"><html:multibox property="schoolServices" value="6er" styleClass="smallfieldcellselect" styleId="6er"/></td>
                            <td>Waiver of school fees</td>
                          </tr>
                          <tr>
                              <td width="4%"><html:multibox property="schoolServices" value="7er" styleClass="smallfieldcellselect" styleId="7er"/></td>
                            <td>Provision of school materials/uniform </td>
                          </tr>
                          <tr>
                            <td width="4%"><html:multibox property="schoolServices" value="1er" styleClass="smallfieldcellselect" styleId="1er"/></td>
                            <td>Assistance/support with homework </td>
                          </tr>
                          <tr>
                            <td width="4%"><html:multibox property="schoolServices" value="8er" styleClass="smallfieldcellselect" styleId="8er"/></td>
                            <td> School performance assessment </td>
                          </tr>
                          
                        </table>
                         
                        <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                          <tr style="border: #528B8B 4px solid">
                            <td width="20%" bgcolor="#CCCCCC" class="right">STABLE </td>
                            <td width="75%" colspan="2" bgcolor="#CCCCCC"> </td>
                          </tr>
                          <tr>
                            <td rowspan="10"></td>
                            <td width="4%"><html:multibox property="stableServices" value="3esr" styleClass="smallfieldcellselect" styleId="3esr"/></td>
                            <td>Vocational training </td>
                          </tr>
                          <tr>
                            
                            <td width="4%"><html:multibox property="stableServices" value="2esr" styleClass="smallfieldcellselect" styleId="2esr"/></td>
                            <td>Micro-finance</td>
                          </tr>
                          <tr>
                            
                            <td width="4%"><html:multibox property="stableServices" value="25r" styleClass="smallfieldcellselect" styleId="25r"/></td>
                            <td>Private and public sector skill acquisition schemes</td>
                          </tr>
                          
                          <tr>
                            <td width="4%"><html:multibox property="stableServices" value="26r" styleClass="smallfieldcellselect" styleId="26r"/></td>
                            <td>Income Generating Activities</td>
                          </tr>
                          
                        </table>  
                       
                      </td>
                      </tr>
                  </table>
                  </fieldset>

                  </td>
                </tr>
                <tr>
                  <td>Referral completed</td>
                <td > 
                    <html:select property="referralCompleted" styleId="referralCompleted" style="width:200px;" >
                          <html:option value="0">select...</html:option>
                          <html:option value="1">Yes</html:option>
                          <html:option value="2">No</html:option>
                          
                      </html:select>
                </td>

                <td  align="right"> </td>
                <td > 

                </td>
              </tr>
              <tr>
                  <td>Name of referring person</td>
                <td > 
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

                <td  align="right"> </td>
                <td > 

                </td>
              </tr>
                       <tr><td colspan="4" align="center"><html:submit value="Save" onclick="setActionName('save')" disabled="${referralSaveDisabled}"/>
                                <html:submit value="Update" onclick="return confirmAction('update')" disabled="${referralModifyDisabled}"/>
                            <html:submit value="Delete" onclick="return confirmAction('delete')" disabled="${referralModifyDisabled}"/></td></tr>
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
