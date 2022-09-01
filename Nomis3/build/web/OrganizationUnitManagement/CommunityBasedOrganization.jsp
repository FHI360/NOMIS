<%-- 
    Document   : CBO
    Created on : Dec 1, 2019, 9:55:16 AM
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
<title>Organization unit hierarchy setup</title>
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
function confirmMerge(val)
{
    if(confirm("Are you sure you want merge the selected records?"))
    {
        if(confirm("The selected records will be merged. Click Ok to continue"))
        {
          setActionName(val)
        }
        else
        return false;
    }
    else
    return false;
}
function clearAndDisableDeleteControl(id)
{
    //alert(id)
    var deleteId=id+"_de"
    if(document.getElementById(id).checked==true)
    {
        document.getElementById(deleteId).checked=false
        enableAllCheckBoxes()
        disableControl(deleteId)
    }
    else
    {
        enableControl(deleteId)
    }

}
function enableAllCheckBoxes()
{
    var elements=document.getElementsByName("selectedCbosToDelete")
    for(var i=0; i<elements.length; i++)
    {
        elements[i].disabled=false
    }
}
function disableControl(id)
{
    document.getElementById(id).disabled=true;
}
function enableControl(id)
{
    document.getElementById(id).disabled=false;
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
        <td> </td>
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
                        <div><jsp:include page="../Navigation/AdministrationLink.jsp"/></div>
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
          
      </table></td>
    <td width="10">&nbsp;</td>
      <td width="659" class="paramPage">
        <html:form action="/cbo" method="POST">
                                <html:hidden property="actionName" styleId="actionName" />
                                <html:hidden property="hiddenUniqueId" styleId="hiddenUniqueId" />
                            <center>
                                <table> <%--<jsp:include page="../includes/LocalOrganizationName.jsp" />--%>
                                    <tr><td colspan="4" align="center">Community Based Organization setup</td></tr>
                                    <tr><td colspan="4" align="center"> </td></tr>
                                    <logic:present name="siteSetupOu">
                                        <tr><td colspan="4" align="center"><label style="color: blue">${level2Ouh.name}: ${siteSetupOu.name} </label></td></tr>
                                    </logic:present>
                                    <tr><td colspan="4" align="center"><html:errors/></td></tr>
                                    <tr>
                                        <td> </td>
                                        <td colspan="3">
                                            <label class="labels">Organization name </label>    
                                            </td>
                                     </tr>
                                    <tr>
                                        <td> </td>
                                        <td colspan="3">
                                            <html:text property="cboName" styleId="cboName" style="width: 335px" />
                                            &nbsp; CBO Code &nbsp;<html:text property="cboCode" styleId="cboCode" style="width: 50px" />
                                            Id <html:text property="uniqueId" styleId="uniqueId" style="width: 100px" />
                                            </td>
                                    </tr>
                                    
                                    <tr>
                                        <td> </td>
                                        <td colspan="3">
                                            <label class="labels">Address </label>    
                                            </td>
                                     </tr>
                                    <tr>
                                        <td> </td>
                                        <td colspan="3">
                                            <html:textarea property="address" styleId="address" style="width: 560px" /> 
                                                
                                            </td> 
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <table>
                                            <tr>
                                                <td> </td>
                                                <td><label class="labels">Contact person name </label></td>
                                                <td>Phone </td>
                                                <td>Email </td>                                               
                                            </tr>
                                            <tr>
                                                <td> </td>
                                            
                                                <td>
                                                    <html:text property="contactPersonName" styleId="contactPersonName" style="width: 250px"/> </td>
                                                <td><html:text property="contactPersonPhone" styleId="contactPersonPhone"/> </td>
                                                <td><html:text property="contactPersonEmail" styleId="contactPersonEmail"/></td>
                                        
                                            </tr>
                                            <tr>
                                                <td> </td>
                                                <td><label class="labels">Latitude </label></td>
                                                <td>Longitude </td>
                                                <td>Data exchange Id </td>                                               
                                            </tr>
                                            <tr>
                                                <td> </td>
                                                <td><html:text property="latitude" styleId="latitude" style="width: 250px"/> </td>
                                                <td><html:text property="longitude" styleId="longitude"/> </td>
                                                <td><html:text property="dataExchangeId" styleId="dataExchangeId" /> </td>
                                            </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    
                                    
                                    
                                    <tr><td colspan="4"><label><logic:present name="siteSetupOuh"> ${siteSetupOuh.name} </logic:present> </label> </td></tr>
                                    <tr>
                                        <td colspan="4">
                                            <div style="width:570px; height: 100px; border: 1px black solid;background-color: #D7E5F2; overflow: scroll;">
                                            <table>
                                          <logic:present name="level3OuList">
                                            <logic:iterate name="level3OuList" id="ou">
                                                <tr>
                                                    <td><html:multibox property="level3OuId" styleId="level3OuId" value="${ou.uid}"/>${ou.name} </td>
                                            </tr>
                                            </logic:iterate>
                                        </logic:present>
                                        </table>
                                            </div>
                                            </td>
                                    </tr>
                                    <tr><td colspan="4"><label>Services </label> </td></tr>
                                    <tr>
                                        <td colspan="4">
                                            <div style="width:570px; height: 100px; border: 1px black solid;background-color: #D7E5F2; overflow: scroll;">
                                            <table>
                                        <logic:present name="serviceDomainList">
                                            <logic:iterate name="serviceDomainList" id="sd">
                                                <tr>
                                                    <td><html:multibox property="services" value="${sd.serviceDomainId}"/>${sd.serviceDomainName}</td>
                                            </tr>
                                        </logic:iterate>
                                        </logic:present>
                                        </table>
                                            </div>
                                            </td>
                                    </tr>
                                    <tr bgcolor="#F0F0F0"><td> </td><td>
            <input type="button" value="Select all " onclick="selectChkBoxes('services')" />
                    <input type="button" value="Unselect all " onclick="unselectChkBoxes('services')" />
                    </td></tr> <%----%>
                                    <tr><td colspan="4" align="center"><html:submit value="Save" onclick="setActionName('save')" disabled="${cboSaveDisabled}"/>
                                            <html:submit value="Modify" onclick="return confirmAction('modify')" disabled="${cboModifyDisabled}"/></td></tr>
                                    
                                    <tr>
                                          <td align="center" colspan="4">
                                              <logic:present name="cboList">
                                                  <div align="center" style="width:600px; max-height:150px; overflow:scroll">
                                                      <table width="580" border="1" bordercolor="#FFFFFF" style=" background-color: lightgray; border-collapse: collapse" class="regsitertable">
                                                  <tr><td>CBO name </td>
                                                       <td>Date created </td><td>Last modified date </td>
                                                       <td>edit </td>
                                                       <td>Keep</td>
                                                      <td>Delete</td>
                                                      </tr>
                                                  <logic:iterate name="cboList" id="cbo">
                                                      <tr><td>${cbo.cboName}</td>
                                                         <td>${cbo.dateCreated} </td><td>${cbo.lastModifiedDate} </td><td>
                                                             <a href="cbo.do?id=${cbo.uniqueId}&&p=ed">edit</a> </td>
                                                      <td width="4%"><html:radio property="cboToKeep" value="${cbo.uniqueId}" styleId="${cbo.uniqueId}" onchange="clearAndDisableDeleteControl('${cbo.uniqueId}')"/></td>
                                                      <td width="4%"><html:multibox property="selectedCbosToDelete" value="${cbo.uniqueId}" styleId="${cbo.uniqueId}_de"/></td>
                                                      </tr>
                                                  </logic:iterate>

                                              </table>
                                            </div>
                                            </logic:present>
                                          </td>
                                    </tr>
                                    <tr><td colspan="4" align="center"><html:submit value="Merge records" onclick="return confirmMerge('mergeCBORecords')" style="width: 150px; margin-left: 50px;" disabled="${cbomergeBtnDisabled}"/></td></tr>
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
