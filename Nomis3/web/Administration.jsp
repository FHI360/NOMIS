<%-- 
    Document   : Administration
    Created on : Mar 14, 2011, 12:12:28 PM
    Author     : smomoh
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>NOMIS - National OVC Management Information System </title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
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
<link href="images/kidmap2.css" rel="stylesheet" type="text/css" />
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
<script language="javascript">
var param=""
var callerId=""
function getValuesFromDb(url,str,id)
{
	callerId=id;
	xmlhttp=GetXmlHttpObject();
    if (xmlhttp==null)
    {
        alert ("Browser does not support HTTP Request");
        return;
    }
    url=url+"?qparam="+str;

    url=url+"&sid="+Math.random();
    xmlhttp.onreadystatechange=stateChanged;
    xmlhttp.open("GET",url,true);
    xmlhttp.send(null);
}
function GetXmlHttpObject()
{
    if (window.XMLHttpRequest)
    {
       return new XMLHttpRequest();
    }
    if (window.ActiveXObject)
    {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
    return null;
}
function stateChanged()
{
    	if (xmlhttp.readyState==4)
	{
            var result=xmlhttp.responseText;
            if(result==" " || result=="" || result==";")
            return false;
            var values=result.split(";")
            if(callerId=="cboForReports")
            {
                populateSelectBox(values, "cbo")
            }
            else if(callerId=="csi_cbo")
            {
                populateSelectBox(values, "csi_cbo")
            }
            else if(callerId=="lgaForReports")
            {
                setSelectBoxValues(values, "lga")
            }
            else if(callerId=="csi_lga")
            {
                setSelectBoxValues(values, "csi_lga")
            }
            else if(callerId=="sumstat_lga")
            {
                setSelectBoxValues(values, "sumstat_lga")
            }
            else if(callerId=="sumstat_cbo")
            {
                setSelectBoxValues(values, "sumstat_cbo")
            }
            else if(callerId=="wardForReports")
            {
                setSelectBoxValues(values, "ward")
            }
            else if(callerId=="schools")
            {
                setSelectBoxValues(values, "schools")
            }
            else if(callerId=="sizeOfData")
            {
              dataSize=values[0]
              csiDataSize=values[1]
            }
	}
}
function showComponent(id)
{
    document.getElementById("wizard").innerHTML = document.getElementById(id).innerHTML;
}


function hideComponent()
{
    document.getElementById("wizard").innerHTML = "";
}
</script>

</head>

<body onload="MM_preloadImages('images/About_down.jpg','images/Admin_down.jpg','images/Rapid_down.jpg','images/care_down.jpg','images/OVC_down.jpg')">
    <br/><br/>
    <table border="0" align="center" cellpadding="0" cellspacing="0" class="boarder">
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
<jsp:include page="includes/Pagetabs.jsp" />

      
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
          <td> <jsp:include page="Navigation/Logout.jsp" /></td>
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
    <td width="931" height="761" valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <!--DWLayoutTable-->
      <tr>
        <td width="18" height="30">&nbsp;</td>
        <td width="231" rowspan="2" valign="top">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#038233">
          <!--DWLayoutTable-->
          <tr>
            <td width="231" height="28" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
              <!--DWLayoutTable-->
              <tr>
                <td width="231" height="28"><img src="images/dataentry.jpg" width="231" height="28" /></td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td height="85" valign="top">
                <div style="float: left" id="my_menu" class="sdmenu">

                    <div><jsp:include page="Navigation/AdministrationLink.jsp"/></div>

               <%-- <div>
                <a href="addPartners.do">Environment setup</a>
                    <a href="userSetup.do">User account management</a>
                    <%--<a href="#" onclick="javascript:showComponent('lookupDiv');">Lookup table </a><a href="#" onclick="showComponent('schoolSetupDiv')">School setup</a>--%>
                    <%--<a href="#" onclick="showComponent('userRoleDiv')">User role setup</a><a href="#" onclick="showComponent('userSetupDiv')">User setup </a>--%>
                    <%--<a href="#" onclick="javascript:showComponent('statesDiv');">State setup </a><a href="#" onclick="javascript:showComponent('lgaDiv');">LGA setup </a>
                    <a href="indicators.do" target="_blank">Indicator setup</a>--%>
                    <%--<a href="databasemgt.do" target="_blank">Database Management</a>
                <a href="databaseupdate.do" target="_blank">Database update</a><a href="DataElementsAndIndicators.jsp">Data elements and Indicators</a>
                <a href="partnerUpdateAction.do" target="_blank">Partner assignment</a><a href="exporttodhis.do" target="_blank">Export to DHIS </a>
                <a href="importExportAction.do">Import/Export</a>
                <a href="organizationUnitGroupAction.do">Organization unit management</a>
                
                </div>--%>
            </div></td>
          </tr>
          <tr>
            <td height="30" valign="top">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
              
              <tr>
                <td width="231" height="30"><img src="images/reports.jpg" width="231" height="30" /></td>
                    </tr>
            </table></td>
          </tr>
          <tr>
            <td height="157" valign="top">
                <div style="float: left" id="my_menu2" class="sdmenu">
                    <div><a href="orgrecordsreport.do" target="_blank" >List of Organizations </a><a href="sitesetupreport.do" target="_blank" >Site setup list </a><a href="#"> </a><a href="#"> </a><a href="#"> </a><a href="#"> </a><a href="#"> </a></div>
            </div></td>
          </tr>



      </table></td>
    <td width="13">&nbsp;</td>
      <td width="739"> </td>
      </tr>



      <tr>
        <td height="301">&nbsp;</td>
        <td>&nbsp;</td>
        <td rowspan="2" valign="top">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <!--DWLayoutTable-->
          <tr>
              <td width="760" >

                 <div id="wizard" align="center" style=" width: 750px; "><img src="images/logo_big.jpg" width="607" height="204" /></div>
              </td>


        </tr>
            </table></td>
    </tr>
      <tr>
        <td height="25">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="26">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>    </td>
    <td width="18">&nbsp;</td>
  </tr>
  <tr>
    <td height="25" colspan="2" valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#038233">
      <!--DWLayoutTable-->
      <tr>
          <td width="949" height="25" class="copyright"><jsp:include page="includes/Version.jsp"/></td>
        </tr>
    </table></td>
  </tr>
</table>
    <%--<div id="userRoleDiv"  style="height: 700px; width: 700px; position: absolute; left: 430px; top: 200px; visibility: hidden; border: 1px blue solid;">
        <center>
        <table >
               <tr><td style=" width: 680px; height:685px;"><iframe name="userRole" width="100%" height="100%" src="userRoleAction.do" frameborder="0" scrolling="no" ></iframe> </td><td> </td></tr>
        </table>

        </center>
    </div>
    <div id="lookupDiv"  style="height: 700px; width: 700px; position: absolute; left: 430px; top: 200px; visibility: hidden; border: 1px blue solid;">
        <center>
        <table >
               <tr><td style=" width: 680px; height:685px;"><iframe name="lookupTable" width="100%" height="100%" src="LookupTable.jsp" frameborder="0" scrolling="no" ></iframe> </td><td> </td></tr>
        </table>

        </center>
    </div>

    <div id="schoolSetupDiv" style="height: 450px; width: 660px; position: absolute; left: 430px; top: 200px; visibility: hidden; border: 1px blue solid;">
        <center>
            <table class="paramPage" style=" border: 1px black solid;"> 
               <tr><td style=" width: 650px; height:450px;"><iframe name="schoolsetup" width="95%" height="95%" src="AddOvcSchool.do" frameborder="0" scrolling="no" ></iframe> </td><td> </td></tr>
        </table>

        </center>
    </div>
    <div id="userSetupDiv" style="height: 450px; width: 660px; position: absolute; left: 430px; top: 200px; visibility: hidden; border: 1px blue solid;">
        <center>
            <table style=" border: 1px black solid;" class="paramPage">
               <tr><td style=" width: 650px; height:450px;"><iframe name="userSetup" width="95%" height="95%" src="userSetup.do" frameborder="0" scrolling="no" ></iframe> </td><td> </td></tr>
        </table>

        </center>
    </div>
    <div id="statesDiv" style="height: 450px; width: 660px; position: absolute; left: 430px; top: 200px; visibility: hidden; border: 1px blue solid;">
        <center>
            <table style=" border: 1px black solid;" class="paramPage">
               <tr><td style=" width: 650px; height:450px;"><iframe name="stateLookup" width="100%" height="100%" src="statesAction.do" frameborder="0" scrolling="no" ></iframe></td><td> </td></tr>
        </table>

        </center>
    </div>
    <div id="lgaDiv" style="height: 450px; width: 660px; position: absolute; left: 430px; top: 200px; visibility: hidden; border: 1px blue solid;">
        <center>
            <table style=" border: 1px black solid;" class="paramPage">
               <tr><td style=" width: 650px; height:450px;"><iframe name="lgaLookup" width="100%" height="100%" src="addLga.do" frameborder="0" scrolling="no" ></iframe></td><td> </td></tr>
        </table>

        </center>
    </div>--%>
</body>
</html>

