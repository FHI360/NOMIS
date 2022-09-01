<%-- 
    Document   : CaregiverAccessToEmergencyFund
    Created on : Jan 19, 2020, 10:33:48 PM
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
<title>Caregiver access to emergency fund</title>
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
                document.getElementById("beneficiaryId").value=val
               
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
function changeHowMoneyIsRaised(value)
{
   if(value==1)
   {
        document.getElementById("loan").disabled=false
       document.getElementById("personalSavings").disabled=false
       document.getElementById("incomeFromTrade").disabled=false
       document.getElementById("salary").disabled=false
       document.getElementById("soldItems").disabled=false
       document.getElementById("borrowed").disabled=false
       document.getElementById("lackofmotivation").disabled=false
   }
   else
   {
       document.getElementById("loan").disabled=true
       document.getElementById("personalSavings").disabled=true
       document.getElementById("incomeFromTrade").disabled=true
       document.getElementById("salary").disabled=true
       document.getElementById("soldItems").disabled=true
       document.getElementById("borrowed").disabled=true
       document.getElementById("lackofmotivation").disabled=true
       unselectChkBoxes("sourceOfMoney")
   }
}
function disableEnableUrgentHhNeedsControls(value)
{
   if(value=="Yes")
   {
       document.getElementById("investment").disabled=false
       document.getElementById("transport").disabled=false
       document.getElementById("gift").disabled=false
       document.getElementById("water").disabled=false
       document.getElementById("firewood").disabled=false
       document.getElementById("livestock").disabled=false
       document.getElementById("medical").disabled=false
       document.getElementById("agricInputs").disabled=false
       document.getElementById("clothsandshoes").disabled=false
       document.getElementById("schoolFees").disabled=false
       document.getElementById("savingsorsilc").disabled=false
       
       document.getElementById("clothsandshoes").disabled=false
       document.getElementById("debtRepayment").disabled=false
       document.getElementById("householdItems").disabled=false
       document.getElementById("food").disabled=false
       document.getElementById("rentOrShelterMaintenance").disabled=false
       
       
   }
   else
   {
       document.getElementById("investment").disabled=true
       document.getElementById("transport").disabled=true
       document.getElementById("gift").disabled=true
       document.getElementById("water").disabled=true
       document.getElementById("firewood").disabled=true
       document.getElementById("livestock").disabled=true
       document.getElementById("medical").disabled=true
       document.getElementById("agricInputs").disabled=true
       document.getElementById("clothsandshoes").disabled=true
       document.getElementById("schoolFees").disabled=true
       document.getElementById("savingsorsilc").disabled=true
       
       document.getElementById("clothsandshoes").disabled=true
       document.getElementById("debtRepayment").disabled=true
       document.getElementById("householdItems").disabled=true
       document.getElementById("food").disabled=true
       document.getElementById("rentOrShelterMaintenance").disabled=true
       unselectChkBoxes("urgentHhNeeds")
   }
}
function disableEnableOtherControls(value)
{
    changeHowMoneyIsRaised(value)
    disableEnableUrgentHhNeedsControls(value)
    if(value=="Yes")
    {
        document.getElementById("accessMoneyToPay").disabled=false
    }
    else
    {
        document.getElementById("accessMoneyToPay").disabled=true
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
        <html:form action="/caregiveraccesstoemergencyfund" method="POST" styleId="ahmForm">
                                <html:hidden property="actionName" styleId="actionName" />
                                <html:hidden property="recordId" styleId="recordId" />
                                
                            <center>
                                <table>
                                    <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                                    <tr><td colspan="4" align="center">Caregiver access to emergency fund</td></tr>
                                      <jsp:include page="../includes/OrganizationUnitHeader.jsp" />                                  
                                    <tr><td colspan="4" align="center" style="color:red"><html:errors/></td></tr>
                                    <tr><td style="font-size: 14px; font-weight: bold; color: red" colspan="4"><logic:present name="caefWithdrawnMessage">${caefWithdrawnMessage}</logic:present></td></tr>
                                    <tr>
                                        <td colspan="4">
                                            
                                            <fieldset><legend>Caregiver information</legend>
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
                                        <td >Caregiver name </td>
                                        <td >
                                            <html:select style="width:150px;" property="beneficiaryId" styleId="beneficiaryId" onchange="setActionName('ahmDetails');forms[0].submit()" >
                                                <html:option value="0">select...</html:option>
                                                <logic:present name="caefahmList">
                                                    <logic:iterate name="caefahmList" id="ahm">
                                                        <html:option value="${ahm.beneficiaryId}">${ahm.firstName} ${ahm.surname} (${ahm.beneficiaryTypeObject.name}) </html:option>
                                                    </logic:iterate>
                                                </logic:present>
                                            </html:select>
                                            </td>
                                            <td align="right">Date of enrollment </td>
                                        <td >
                                            <html:text property="dateOfEnrollment" styleId="dateOfEnrollment" disabled="true"/>
                                            </td>
                                     </tr>
                                    
                                     
                                     <tr>
                                         <td align="right">Sex </td>
                                        <td >
                                            <html:select property="sex" styleId="sex" disabled="true">
                                                <html:option value="0">select...</html:option>
                                                <html:option value="M">Male</html:option>
                                                <html:option value="F">Female</html:option>
                                            </html:select>
                                            </td>
                                        <td align="right">age</td>
                                        <td >
                                            <html:text property="age" styleId="age" disabled="true"/>
                                            </td>
                                     </tr>
                                                                          
                                     </table>
                                            </fieldset>
                                        </td>
                                    </tr>
                                     
                                     <tr>
                  <td valign="top" colspan="4">
                      <fieldset>
                        <legend class="fieldset">Assessment </legend>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <!--DWLayoutTable-->
                    <tr>
                      <td width="762" height="17">
                          
                          <table width="686" border="1" bordercolor="#D7E5F2" class="regsitertable">
                         <tr>
                          <td width="20%">Date of assessment: </td>
                          <td width="80%" colspan="3">
                              <html:text property="dateOfAssessment" styleId="dateOfAssessment" styleClass="smallfieldcellinput" onchange="setActionName('assessmentDetails'); forms[0].submit()" readonly="true"/>&nbsp;(mm/dd/yyyy)
                          </td>

                        </tr>
                           <tr>
                              <td class="right" colspan="2">Did you make any unexpected expenditure in the past six (6) months?</td>
                              <td >
                                  <html:select styleClass="fieldcellinput" property="unexpectedExpenditure" styleId="unexpectedExpenditure" style="width:82px;" onchange="disableEnableOtherControls(this.value)">
                                      <html:option value="0">select...</html:option>
                                      <html:option value="1">Yes</html:option>
                                      <html:option value="2">No</html:option>
                                      
                                  </html:select> </td><td>
                          </tr>
                          <tr>
                              <td class="right" colspan="2">If Yes, were you able to access money to pay for this unexpected expenditure?
                                </td>
                              <td colspan="2">
                                  <html:select styleClass="fieldcellinput" property="accessMoneyToPay" style="width:82px;" styleId="accessMoneyToPay" onchange="changeHowMoneyIsRaised(this.value)">
                                      <html:option value="0">select...</html:option><html:option value="2">No</html:option><html:option value="1">Yes</html:option>
                                      
                                  </html:select> </td>
                          </tr>
                          <tr>
                              <td class="right" colspan="4">If No go to (iii), if Yes to the question above, how were you able to raise the money? (tick as appropriate)</td>
                          </tr>
                          <tr>
                      <td width="752" height="102" colspan="4">
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              
                              <tr>
                                  <td>Income from trade </td> <td><html:multibox property='sourceOfMoney' styleId="incomeFromTrade" styleClass='smallfieldcellselect' value="Income from trade" disabled="true"/> </td>
                                  <td>Through my salary </td> <td><html:multibox property='sourceOfMoney' styleId="salary" styleClass='smallfieldcellselect' value="salary" disabled="true"/> </td>    
                                  </tr>
                                  <tr>
                                      <td>I sold some items in the house </td> <td><html:multibox property='sourceOfMoney' styleId="soldItems" styleClass='smallfieldcellselect' value="Sold items in the house" disabled="true"/> </td>
                                      <td>Borrowed from a friend </td> <td><html:multibox property='sourceOfMoney' styleId="borrowed" styleClass='smallfieldcellselect' value="Borrowed from a friend" disabled="true"/> </td>
                                  </tr>
                                  <tr>
                                      <td>Took a loan, received amount saved or social fund from a SILC group </td> <td><html:multibox property='sourceOfMoney' styleId="loan" styleClass='smallfieldcellselect' value="Took a loan received amount saved or social fund from a SILC group" disabled="true"/> </td>
                                      <td>From my personal savings </td> <td><html:multibox property='sourceOfMoney' styleId="personalSavings" styleClass='smallfieldcellselect' value="Personal savings" disabled="true"/> </td>
                                  </tr>
                                  
                        </table>

                      </td>
                      </tr>
                      
                      <tr>
                      <td width="752" height="102" colspan="4">
                          <table width="670" border="1" bordercolor="#D7E5F2" class="regsitertable">
                              <tr><td colspan="4">What are the household (HH) needs requiring routine and/or emergency cash to address? (tick as appropriate) </td> 
                                  </tr>
                                  
                              <tr><td>Food</td> <td><html:multibox property='urgentHhNeeds' styleId="food" styleClass='smallfieldcellselect' value="Food" /> </td>
                                      <td>Business investment</td> <td><html:multibox property='urgentHhNeeds' styleId="investment" styleClass='smallfieldcellselect' value="Business investment" /> </td>
                                  </tr>
                                  <tr>
                                      <td>Transport </td> <td><html:multibox property='urgentHhNeeds' styleId="transport" styleClass='smallfieldcellselect' value="Transport" /> </td>
                                      <td>Household items </td> <td><html:multibox property='urgentHhNeeds' styleId="householdItems" styleClass='smallfieldcellselect' value="Household items" /> </td>
                                  </tr>
                                  <tr>
                                      <td>Gift </td> <td><html:multibox property='urgentHhNeeds' styleId="gift" styleClass='smallfieldcellselect' value="gift" /> </td>
                                      <td>Water </td> 
                                      <td><html:multibox property='urgentHhNeeds' styleId="water" styleClass='smallfieldcellselect' value="water" /> </td>
                                  </tr>
                                  <tr>
                                      <td>House rent or shelter materials </td> <td><html:multibox property='urgentHhNeeds' styleId="rentOrShelterMaintenance" styleClass='smallfieldcellselect' value="House rent or shelter materials" /> </td>
                                      <td>Fire wood </td> <td><html:multibox property='urgentHhNeeds' styleId="firewood" styleClass='smallfieldcellselect' value="Fire wood" /> </td>
                                  </tr>
                                  
                                  <tr>
                                      <td>Livestock </td> <td><html:multibox property='urgentHhNeeds' styleId="livestock" styleClass='smallfieldcellselect' value="livestock" /> </td>
                                      <td>Medical </td> <td><html:multibox property='urgentHhNeeds' styleId="medical" styleClass='smallfieldcellselect' value="Medical" /> </td>
                                  </tr>
                                  <tr>
                                      <td>Agricultural inputs </td> <td><html:multibox property='urgentHhNeeds' styleId="agricInputs" styleClass='smallfieldcellselect' value="Agricultural inputs" /> </td>
                                      <td>Cloths/shoes </td> <td><html:multibox property='urgentHhNeeds' styleId="clothsandshoes" styleClass='smallfieldcellselect' value="Cloths and shoes" /> </td>
                                  </tr>
                                  <tr>
                                      <td>School fees </td> <td><html:multibox property='urgentHhNeeds' styleId="schoolFees" styleClass='smallfieldcellselect' value="School fees" /> </td>
                                      <td>Debt repayment </td> <td><html:multibox property='urgentHhNeeds' styleId="debtRepayment" styleClass='smallfieldcellselect' value="Debt repayment" /> </td>
                                  </tr>
                                  <tr>
                                      <td>Savings/in hand/SILC </td> <td><html:multibox property='urgentHhNeeds' styleId="savingsorsilc" styleClass='smallfieldcellselect' value="Savings/in hand/SILC" /> </td>
                                      <td> </td> <td> </td>
                                  </tr>
                        </table>

                      </td>
                      </tr>
                          </table>
                      </td>
                    </tr>
                        </table>
                      </fieldset> 
                <tr>
                  
                  <td>Completed by </td>
                <td colspan="3"> 
                    <html:select property="volunteerName" styleId="volunteerName" style="width:200px;" >
                          <html:option value="xxxxxxxxxxx">None</html:option>
                          <logic:present name="enumeratorList">
                              <logic:iterate name="enumeratorList" id="er">
                                <html:option value="${er.communityWorkerId}">${er.firstName} ${er.surname}</html:option>
                              </logic:iterate>

                          </logic:present>
                      </html:select>
                </td>
             </tr>
                                       <tr><td colspan="4" align="center"><html:submit value="Save" onclick="setActionName('save')" disabled="${caefSaveDisabled}"/>
                                            <html:submit value="Modify" onclick="return confirmAction('modify')" disabled="${caefModifyDisabled}"/>
                                           <html:submit value="Delete" onclick="return confirmAction('delete')" disabled="${caefModifyDisabled}"/></td>
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
