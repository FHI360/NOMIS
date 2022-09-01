<%-- 
    Document   : ListOfIndicatorsParamPage
    Created on : Oct 29, 2018, 9:22:29 PM
    Author     : smomoh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of indicators</title>
        <link href="css/sdmenu/sdmenu.css" rel="stylesheet" type="text/css" />
        <link href="css/general/stylefile.css" rel="stylesheet" type="text/css" />
        <link type="text/css" href="css/ui-darkness/jquery-ui-1.8.custom.css" rel="Stylesheet" />
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
        <script type="text/javascript" src="js/Enrollmentjsfile.js"></script>
        <link href="images/kidmap2.css" rel="stylesheet" type="text/css" />
        <style type="text/css">

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
.selectBoxtWidth
{
    width: 160px;
}


</style>
        <script language="javascript">
$(function() {
    $("#startDate").datepicker();
     $("#endDate").datepicker();
        //$("#dateOfBirth").datepicker();
    });
            function setActionName(val)
            {
                document.getElementById("actionName").value=val
            }
function enableDateFields()
{
    if(document.getElementById("chkReportType").checked==true)
    {
        enableComponents("sumstat_month")
        enableComponents("sumstat_year")
        document.getElementById("reportType").value="on"
    }
    else
    {
        disableComponents("sumstat_month")
        disableComponents("sumstat_year")
        document.getElementById("reportType").value="off"
    }
}
function enableComponents(id)
{
    document.getElementById(id).disabled=false
}
function disableComponents(id)
{
    document.getElementById(id).disabled=true
}
        </script>
    </head>
    <body style="background-color: #f1f1f1">
        <br/><br/>
        <center>
            <html:form action="/listofindicators" method="post">
                <html:hidden property="actionName" styleId="actionName"/>
                                <!--<div styleId="sumstatDivPerMth" class="paramPage" style="height: 240px; width: 450px; margin-top: 100px;">-->
        <span>
        <center>
            <table>
                <tr><td align="center"><span style="margin-left:95px;"><jsp:include page="/includes/ReportOrganizationUnitParameter.jsp" /></span> </td></tr>
                <tr><td> 
            <html:errors/>
            <table align="center">
                <tr><td colspan="4" align="center"><logic:present name="accessErrorMsg">${accessErrorMsg}</logic:present></td></tr>
                <tr><td colspan="4" align="center">List of indicators</td></tr>
                <logic:present name="sumstatmsg">
                <tr><td colspan="4" class="title" style="color:red; font-size: 16px;" align="center">${sumstatmsg}</td></tr>
            </logic:present>
            </table>
                
        
        <table class="paramPage" style="margin-left:95px;">
            
                <tr>
            <td class="orglabel"> </td><td class="orglabel" > </td>
                <td align="right">Age</td>
                <td align="left">
                    <html:select property="startAge" styleId="startAge" style="width: 124px;" onchange="setActionName('endAge'); forms[0].submit()">
                        <logic:present name="ageList">
                            <html:option value="&lt1"> &lt; 1 year</html:option>
                            <logic:iterate id="age" name="ageList">
                                <html:option value="${age}">${age}</html:option>
                            </logic:iterate>
                    </logic:present>
                    </html:select>  <label class="orglabel" style=" margin-left: 6px; margin-right: 6px"> to</label>
                    <html:select property="endAge" styleId="endAge" style="width: 124px;" >
                        <logic:present name="endAgeList">
                            <logic:iterate id="age" name="endAgeList">
                                <html:option value="${age}">${age}</html:option>
                            </logic:iterate>
                        </logic:present>
                    </html:select></td>
            </tr>
            
            <tr><td>&nbsp;</td>
                <td valign="top" colspan="3" align="center">
                      <fieldset style="width:705px;">
                        <legend class="fieldset">Indicators </legend>
                        <div style="width:700px; min-height:200px; max-height:450px; overflow:scroll; border:1px silver solid; text-align:left; background-color:#FFFFFF;">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="regsitertable">
                    <tr>
                      <td>
                          <logic:present name="sunstatIndictors">
                          <fieldset>
                            <table width="720" border="1" bordercolor="#D7E5F2" class="regsitertable">
                                <tr><td colspan="2" align="center" style="background-color: #FFEBCD;">OVC indicators</td></tr>
                                
                              <logic:iterate collection="${sunstatIndictors[0]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate>
                              <tr><td colspan="2" align="center" style="background-color:#FFEBCD;">School enrollment indicators</td></tr>
                              <logic:iterate collection="${sunstatIndictors[1]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate>
                                  
                              <tr><td colspan="2" align="center" style="background-color:#FFEBCD;">Enrollment stream indicators</td></tr>
                              <logic:iterate collection="${sunstatIndictors[2]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate>
                                  
                              <tr><td colspan="2" align="center" style="background-color:#FFEBCD;">Birth registration indicators</td></tr>
                              <logic:iterate collection="${sunstatIndictors[3]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate>
                              
                             <tr><td colspan="2" align="center" style="background-color:#FFEBCD;" >OVC HIV indicators</td></tr>
                              <logic:iterate collection="${sunstatIndictors[4]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate>
                             <%--     
                              <tr><td colspan="2" align="center" style="background-color:#FFEBCD;" >Case plan indicators</td></tr>
                              <logic:iterate collection="${sunstatIndictors[5]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate>--%>
                                                               
                             <tr><td colspan="2" align="center" style="background-color: #FFEBCD;">OVC Service indicators</td></tr>
                              <logic:iterate collection="${sunstatIndictors[11]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate>
                                  
                              
                                  <%--<tr><td colspan="2" align="center" style="background-color:#FFEBCD;">Referral indicators</td></tr>
                              <logic:iterate collection="${sunstatIndictors[13]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate>--%>
                               
                                  <tr><td colspan="2" align="center" style="background-color: #FFEBCD;">Caregiver indicators</td></tr>
                              <logic:iterate collection="${sunstatIndictors[16]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate>
                              
                                 <tr><td colspan="2" align="center" style="background-color: #FFEBCD;">Caregiver service indicators</td></tr>
                              <logic:iterate collection="${sunstatIndictors[18]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate> 
                               <tr><td colspan="2" align="center" style="background-color:#FFEBCD;">Household indicators</td></tr>
                              <logic:iterate collection="${sunstatIndictors[19]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate>
                              <tr><td colspan="2" align="center" style="background-color:#FFEBCD;">Special indicators</td></tr>
                              <logic:iterate collection="${sunstatIndictors[21]}" id="indicator">
                                  <tr><td><html:multibox property='indicatorIndexes' styleId="${indicator.indicatorId}" value="${indicator.indicatorId}" styleClass='smallfieldcellselect'/> </td><td>${indicator.indicatorName} </td> </tr>
                              </logic:iterate>
                            </table>
                          </fieldset>
                         </logic:present>
                          
                      </td>
                      </tr>
                   
                  </table>
                </div>
                  </fieldset></td>
            </tr>
            <tr><td>&nbsp;</td>
                <td colspan="3" align="center">
                    <html:submit value="Submit" onclick="setActionName('summstatreport')" style="width: 70px;" disabled="${licSaveDisabled}" />
                </td>
            </tr>
        </table>
                </td></tr></table> 
        </center>
        </span>
   <!-- </div>--> 

            </html:form>
        </center>
        
    </body>
</html>