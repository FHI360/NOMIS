<%-- 
    Document   : MetadataExport
    Created on : Mar 19, 2020, 7:20:31 AM
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
<title>Meta data export</title>
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
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>

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
                        <div><jsp:include page="../Navigation/ExportImportLink.jsp"/></div>
                        
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
          
      </table></td>
    <td width="10">&nbsp;</td>
      <td width="659" class="paramPage">
            <html:form action="/metadataexport">
                <label style="color:red; font-weight: bold; font-size: 16px; margin-left: 50px;"><html:errors/>
                <logic:present name="metadataExportMsg"><label style="color:white; font-size: 14px; font-weight: bold;">${metadataExportMsg}</label></logic:present></label>
    <html:hidden property="actionName" styleId="actionName" />
    <table  style=" font-size: 14px;">
        <%--<tr><td> <label style=" color: white;">Organization unit</label></td><td colspan="3"><html:checkbox property="organizationUnit" styleId="organizationUnit" value="1" /></td></tr>--%> 
        <tr>
                  <td height="123" valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset" style=" color: white;">Organizations units </legend>
                        <div style="width:680px; height:100px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="675" height="102">
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <logic:present name="organizationListForExport">
                                  <logic:iterate name="organizationListForExport" id="ou">
                                      <tr><td><html:multibox property='organizationUnits' styleId="${ou.uid}" value="${ou.uid}" styleClass='smallfieldcellselect'/> </td><td>${ou.name} </td> 
                                          
                                      </tr>
                                  </logic:iterate>
                              </logic:present>
                              
                        </table>

                      </td>
                      </tr>
                      
                  </table>
                </div>
                  </fieldset></td>
                </tr>
                <tr><td colspan="4">
            <input type="button" value="Select all " onclick="selectChkBoxes('organizationUnits')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('organizationUnits')" />
                    </td></tr>  
        <tr>
                  <td valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset" style=" color: white;">Community Based Organizations </legend>
                        <div style="width:680px; max-height:100px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="675">
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <logic:present name="CBOListForExport">
                                  <logic:iterate name="CBOListForExport" id="cbo">
                                      <tr><td><html:multibox property='cbos' styleId="${cbo.uniqueId}" value="${cbo.uniqueId}" styleClass='smallfieldcellselect'/> </td><td>${cbo.cboName} </td> 
                                          
                                      </tr>
                                  </logic:iterate>
                              </logic:present>
                              
                        </table>

                      </td>
                      </tr>
                  </table>
                </div>
                  </fieldset></td>
                </tr>
                <tr><td colspan="4">
            <input type="button" value="Select all " onclick="selectChkBoxes('cbos')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('cbos')" />
                    </td></tr>
               <tr>
                  <td valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset" style=" color: white;">Schools </legend>
                        <div style="width:680px; height:100px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="675" height="102">
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <logic:present name="schoolListForExport">
                                  <logic:iterate name="schoolListForExport" id="school">
                                      <tr><td><html:multibox property='schools' styleId="${school.id}" value="${school.id}" styleClass='smallfieldcellselect'/> </td><td>${school.schoolName}</td> 
                                          
                                      </tr>
                                  </logic:iterate>
                              </logic:present>
                              
                        </table>

                      </td>
                      </tr>
                  </table>
                </div>
                  </fieldset></td>
                </tr> 
                <tr><td colspan="4">
            <input type="button" value="Select all " onclick="selectChkBoxes('schools')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('schools')" />
                    </td></tr>
                <tr>
                  <td valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset" style=" color: white;">Schools grades </legend>
                        <div style="width:680px; height:100px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="675" height="102">
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <logic:present name="schoolGradeListForExport">
                                  <logic:iterate name="schoolGradeListForExport" id="grade">
                                      <tr><td><html:multibox property='grades' styleId="${grade.id}" value="${grade.id}" styleClass='smallfieldcellselect'/> </td><td>${grade.gradeName}</td> 
                                          
                                      </tr>
                                  </logic:iterate>
                              </logic:present>
                              
                        </table>

                      </td>
                      </tr>
                  </table>
                </div>
                  </fieldset></td>
                </tr> 
                <tr><td colspan="4">
            <input type="button" value="Select all " onclick="selectChkBoxes('grades')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('grades')" />
                    </td></tr>
                <tr>
                  <td valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset" style=" color: white;">Referral facilities </legend>
                        <div style="width:680px; max-height:100px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="675" style="max-height:100px;">
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <logic:present name="referralFacilityListForExport">
                                  <logic:iterate name="referralFacilityListForExport" id="facility">
                                      <tr><td><html:multibox property='referralFacilities' styleId="${facility.facilityId}" value="${facility.facilityId}" styleClass='smallfieldcellselect'/> </td><td>${facility.facilityName}</td> 
                                          
                                      </tr>
                                  </logic:iterate>
                              </logic:present>
                              
                        </table>

                      </td>
                      </tr>
                  </table>
                </div>
                  </fieldset></td>
                </tr>
                <tr><td colspan="4">
            <input type="button" value="Select all " onclick="selectChkBoxes('referralFacilities')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('referralFacilities')" />
                    </td></tr>
                <tr>
                  <td valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset" style=" color: white;">Vulnerability status </legend>
                        <div style="width:680px; max-height:100px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="675" >
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <logic:present name="vulnerabilityStatusListForExport">
                                  <logic:iterate name="vulnerabilityStatusListForExport" id="vs">
                                      <tr><td><html:multibox property='vulnerabilityStatus' styleId="${vs.vulnerabilityStatusId}" value="${vs.vulnerabilityStatusId}" styleClass='smallfieldcellselect'/> </td><td>${vs.vulnerabilityStatusName}</td> 
                                          
                                      </tr>
                                  </logic:iterate>
                              </logic:present>
                              
                        </table>

                      </td>
                      </tr>
                  </table>
                </div>
                  </fieldset></td>
                </tr>
                <tr><td colspan="4">
            <input type="button" value="Select all " onclick="selectChkBoxes('vulnerabilityStatus')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('vulnerabilityStatus')" />
                    </td></tr>
                <tr>
                  <td valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset" style=" color: white;">Implementing Partners</legend>
                        <div style="width:680px; max-height:100px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="675">
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <logic:present name="implementingPartnerListForExport">
                                  <logic:iterate name="implementingPartnerListForExport" id="partner">
                                      <tr><td><html:multibox property='implementingPartners' styleId="${partner.partnerCode}" value="${partner.partnerCode}" styleClass='smallfieldcellselect'/> </td><td>${partner.partnerName}</td> 
                                          
                                      </tr>
                                  </logic:iterate>
                              </logic:present>
                              
                        </table>

                      </td>
                      </tr>
                  </table>
                </div>
                  </fieldset></td>
                </tr>
                <tr><td colspan="4">
            <input type="button" value="Select all " onclick="selectChkBoxes('implementingPartners')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('implementingPartners')" />
                    </td></tr>
                <tr>
                  <td valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset" style=" color: white;">Beneficiary services </legend>
                        <div style="width:680px; max-height: 100px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="675">
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <logic:present name="beneficiaryServiceListForExport">
                                  <logic:iterate name="beneficiaryServiceListForExport" id="service">
                                      <tr><td><html:multibox property='beneficiaryServices' styleId="${service.serviceId}" value="${service.serviceId}" styleClass='smallfieldcellselect'/> </td><td>${service.serviceName}</td> 
                                          
                                      </tr>
                                  </logic:iterate>
                              </logic:present>
                              
                        </table>

                      </td>
                      </tr>
                  </table>
                </div>
                  </fieldset></td>
                </tr>
                <tr><td colspan="4">
            <input type="button" value="Select all " onclick="selectChkBoxes('beneficiaryServices')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('beneficiaryServices')" />
                    </td></tr>
                <tr>
                  <td valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset" style=" color: white;">Community workers </legend>
                        <div style="width:680px; height:100px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="675" height="102">
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <logic:present name="communityWorkersListForExport">
                                  <logic:iterate name="communityWorkersListForExport" id="cw">
                                      <tr><td><html:multibox property='communityWorkers' styleId="${cw.communityWorkerId}" value="${cw.communityWorkerId}" styleClass='smallfieldcellselect'/> </td><td>${cw.firstName} ${cw.surname}</td> 
                                          
                                      </tr>
                                  </logic:iterate>
                              </logic:present>
                              
                        </table>

                      </td>
                      </tr>
                  </table>
                </div>
                  </fieldset></td>
                </tr>
               <tr><td colspan="4">
            <input type="button" value="Select all " onclick="selectChkBoxes('communityWorkers')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('communityWorkers')" />
                    </td></tr> 
        <tr> <td colspan="3" align="center">
    <html:submit style="width:75px; height: 30px;" styleId="export" value="Export" onclick="return confirmAction('export'); forms[0].submit()" disabled="${metaDataExportButtonDisabled}"/>
    

</td></tr>
    </table>
       </html:form>   
 </td>
   
      
      </tr>
    
</table>


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

