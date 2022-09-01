/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {
                $("#dateEnrollment").datepicker();
            });

 var req;
 var which;
 var callerId=""
 var resetId=0
 var searchIndex=0
 var eligibility=" "
 var daysOfBirthId=""
function getValuesByAjaxApi(url,str,id)
{
    callerId=id;
    xmlhttp=GetXmlHttpObject();
    if (xmlhttp==null)
    {
        alert ("Browser does not support HTTP Request");
        return;
    }
    url=url+"?qparam="+str;
    url=url+"&id="+id;
    url=url+"&sid="+Math.random();
    //alert(url+"---"+str)
    xmlhttp.onreadystatechange=stateChanged;
    xmlhttp.open("GET",url,true);
    xmlhttp.send(null);
}
function stateChangedForDates()
{
	if (xmlhttp.readyState==4)
	{
		var value=xmlhttp.responseText;
                if(callerId=="daysPerMth")
                {
                    loadDaysOfBirth(value)
                }
                else
                {
                    
                }
	}
        else
        {
            //alert("error "+xmlhttp.responseText)
        }
}
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
    //alert(url+"---"+str)
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
function getDaysPerMonth(mth,daysPerMth)
{
    daysOfBirthId=daysPerMth
    var req=mth
    getValuesByAjaxApi('ajaxaction.do',req,'daysPerMth')
}
function loadDaysOfBirth(days)
{
    //dayOfBirth
    var daysSelect=document.getElementById(daysOfBirthId);
    var selectedDay=document.getElementById(daysOfBirthId).value;
    daysArr=days.split(";")
    daysSelect.options.length=0;
    daysSelect.options[0]=new Option("Day",0)
    var count=0;
    for(var i=0; i<daysArr.length; i++)
    {
        daysSelect.options[i+1]=new Option(daysArr[i],daysArr[i])
        count++
    }
    if(selectedDay <= count)
    daysSelect.value=selectedDay
}
function searchBeneficiary(name)
{
    var level3Id=document.getElementById("level3OuId").value
    var level4Id=document.getElementById("organizationUnitId").value
    var beneficiaryType=document.getElementById("beneficiaryType").value
     req=beneficiaryType+"="+name+":"+level3Id+":"+level4Id
     getValuesFromDb('ajaxaction.do',req,'search')
}
function searchHousehold(name)
{
    var level3Id=document.getElementById("level3OuId").value
    var level4Id=document.getElementById("organizationUnitId").value
     req=name+":"+level3Id+":"+level4Id
     getValuesFromDb('ajaxaction.do',req,'search')
}
function cleanUpDatabase(functionName,processId)
{
    //alert(functionName)
     req=functionName;
     getValuesFromDb('databaseCleanupAction.do',req,processId)
}
    function retrieveURL(url)
    {
      if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
      try {
        //req.open("GET", url, true);
        req.open("POST", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChange;
        //req.open("GET", url, true);
        req.open("POST", url, true);
        req.send();
      }
    }
  }

  function processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200)
      { // OK response

       if(req.responseText != " ")
       {
        var resArray = req.responseText.split('-');
        
        if(resArray[0] == "error")
        {
            //resetFieldValues()
            setFieldValues(resArray)
        }
        else{
            var resArray = req.responseText.split('-');

            if(resArray[0] == "success")
            {
                var eligibility=resArray[2]
                if(eligibility !="")
                 {
                     var tokens = tokenizeStr(eligibility);

                     for(i=0; i<tokens.length; i++)
                     {
                        var str=concatStr(tokens[i])
                        document.getElementById(str).checked = false;
                     }
                 }
                if(resetId==1)
                //resetFieldValues()

            document.getElementById("characters").value = resArray[1].trim();
            document.getElementById("characters2").innerHTML = " ";
             var resArray2 = resArray[1].split("/");
            }
        }

        }

      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }
function populateCaregiverInfo(caregiverInfo)
{
    document.getElementById("caregiverName1").value=caregiverInfo[0]
    document.getElementById("caregiverName2").value=caregiverInfo[1]
    document.getElementById("caregiverAddress").value=caregiverInfo[2]
    document.getElementById("caregiverGender").value=caregiverInfo[3]
    document.getElementById("caregiverOccupation").value=caregiverInfo[4]
    document.getElementById("caregiverAge").value=caregiverInfo[5]
    document.getElementById("caregiverPhone").value=caregiverInfo[6]
    document.getElementById("cgiverHivStatus").value=caregiverInfo[7].trim()
    hideComponent("caregiverPop")
}
function showCaregiverId(cgiverId)
{
    document.getElementById("cgiverIdSpace").innerHTML="Id -->"+cgiverId
}
function setCaregiverDetails(cgiverId)
{
   if(cgiverId=="" || cgiverId==" " || cgiverId==null)
    return
    req="searchCaregiverById;"+cgiverId
    getValuesFromDb('addcbo.do',req,'searchCaregiverById')
}
function searchCaregiverInHousehold(namepart)
{
    //alert(namepart)
     var hhId=document.getElementById("hhUniqueId").value
     if(namepart=="" || namepart==" " || namepart==null)
     return
     req="searchCaregiverInHousehold;"+hhId+"-"+namepart
     getValuesFromDb('addcbo.do',req,'searchCaregiverInHousehold')
}
function getConfirmation()
{
    if(confirm("Ovc with duplicate record exist!\nClick OK to view or CANCEL to reset form"))
     {
            var newWindow = window.open("matchpage.jsp", "match",
"width=740,height=585,left=230,top=70,resizable=no,scrollbars=no,menubar=no,location=no,status=no");
            return true
     }
     //resetAllFields();
     return false
}
function getAgeUnit()
{
    var dropdownIndex = document.getElementById("ageUnit").selectedIndex;
    var dropDownValue=document.getElementById("ageUnit").options[dropdownIndex].value
    retrieveURL2('getCharacters.do?ageUnit=' + dropDownValue);
}
function getAge()
{
        var dropDownValue=document.getElementById("age").value
        retrieveURL2('getCharacters.do?age=' + dropDownValue);
}
function capitalize(id, value)
{
    document.getElementById(id).value = value.substring(0,1).toUpperCase() + value.substring(1);
}
function capitalizeAll(id, value)
{
    document.getElementById(id).value = value.toUpperCase();
}
function populateSchoolDropdownBox(value, id)
{
    document.getElementById(id).options.length=0;
    document.getElementById(id).options[0]=new Option(" ")
    for(var i=0; i<value.length; i++)
    {
        document.getElementById(id).options[i+1]=new Option(value[i],value[i])
    }
    cleanUp(id)
}
function setWardSelectBoxValues(value, id)
{
    document.getElementById(id).options.length=0;
    document.getElementById(id).options[0]=new Option(" ","")
    var j=1
    for(var i=0; i<value.length; i+=2)
    {
        document.getElementById(id).options[j]=new Option(trim(value[i]),trim(value[i+1]))
        j++
        //document.getElementById(id).options[i+1]=new Option(trim(value[i]),trim(value[i+1]))
    }
    cleanUp(id)
}
function setSelectBoxValues(value, id)
{
    document.getElementById(id).options.length=0;
    document.getElementById(id).options[0]=new Option(" ","")
    for(var i=0; i<value.length; i+=2)
    {
        document.getElementById(id).options[i+1]=new Option(value[i],value[i+1])
    }
    cleanUp(id)
}
function setSelectBoxValues2(value, id)
{
    document.getElementById(id).options.length=0;
    var j=0;
    for(var i=0; i<value.length; i+=2)
    {
        document.getElementById(id).options[j]=new Option(value[i],value[i+1])
        j++
    }
}
function setSelectBoxValues3(value, id)
{
    document.getElementById(id).options.length=0;
    document.getElementById(id).options[0]=new Option("")
    var j=0;
    for(var i=0; i<value.length; i+=2)
    {
        var optionVal=value[i]+";"+value[i+1]
        document.getElementById(id).options[i+1]=new Option(value[i],optionVal)
    }
    cleanUp(id)
}
function cleanUp(id)
{
    for(i=0; i<document.getElementById(id).options.length; i++ )
    {
        if(document.getElementById(id).options[i].text=="")
        {
            document.getElementById(id).options[i]=null;
        }
    }
}
function tokenizeStr(str){

    var tokenArray = str.split(", ");
    return tokenArray;
}
function concatStr(str){

    var tokenArray = str.split(" ");
    var concstr=" "
    for(var i=0; i<tokenArray.length; i++)
    {
       concstr+=tokenArray[i]
    }
    return trim(concstr);
}

function changeDateFormat(dateStr){

    var dateArray = dateStr.split("-");
    var newDateStr = dateArray[1] + "/" + dateArray[2] + "/" + dateArray[0];
    return newDateStr;
}
function setBtnName(name)
{
     if(name=="save")
     {
            document.getElementById("buttonId").value = name
            return true
     }
     if(confirm("Are you sure you want to "+name+" this record?"))
     {
            document.getElementById("buttonId").value = name
            return true
     }
       return false
}
function getDropDownValue(id)
{
      var dropDownValue=document.getElementById(id).value
      return dropDownValue;
}
function resetId()
    {
        document.getElementById("ovcSno").value=" ";
        document.getElementById("characters").value=" "
        disableControl("ovcSno")
        disableControl("saveBtn")
        disableControl("modifyBtn")
        disableControl("deleteBtn")
        disableControl("newBtn")
    }
    function enableControl(id)
    {
        document.getElementById(id).disabled=false
    }
    function disableControl(id)
    {
        document.getElementById(id).disabled="disabled"
    }
    function checkControl(id)
    {
        document.getElementById(id).checked="checked"
    }
    function uncheckControl(id)
    {
        document.getElementById(id).checked=false
    }
    function clearField(id)
    {
        document.getElementById(id).value=" "
    }
    function childInSchool(boolValue)
    {
        if (boolValue=="Yes")
        {
            enableControl("schoolName")
            enableControl("currentClass")
            populateSchools(boolValue)
        }
        else
        {
            document.getElementById("schoolName").value=" "
            disableControl("schoolName")
            disableControl("currentClass")
            clearField("currentClass")
        }
    }
    function trim(stringToTrim)
    {
           return stringToTrim.replace(/^\s+|\s+$/g,"");
    }
     function populateSchools(inSchool)
     {
         schtype="Public"
         if(inSchool=="Yes")
         {
            var stateId=document.getElementById("stateId").value;
            var req=stateId+";"+"schoolList;"+schtype
            getValuesFromDb('addcbo.do',req,'schoolList')
         }
         
     }
     function setSchoolName(value)
     {
         document.getElementById("schoolName").value=value
     }
     function populateAge(value)
     {
         if(value=="Month")
         i=11;
         else if(value=="Year")
         i=17;

         document.getElementById("age").options.length=0
         for(var j=1; j<=i; j++)
         {
             document.getElementById("age").options[j-1]=new Option(j,j)
         }
     }
     function checkDuplicateByName()
    {
        var sname=document.getElementById("surname").value
        var fname=document.getElementById("otherNames1").value
        var mname=document.getElementById("otherNames2").value
        var age=document.getElementById("age").value
        var ageunit=document.getElementById("ageUnit").value
        if(sname=="" || sname==" " || fname=="" || fname==" " || age=="" || age==" ")
        return
        var otherNames=fname
        if(mname !="" && mname !=" ")
        otherNames+=", "+mname
        var req="duplicateCheck"+";"+sname+":"+otherNames+":"+age+":"+ageunit+";"+"nameAndAge"
        
        getValuesFromDb('addcbo.do',req,'nameAndAge')
    }
     function checkDupId()
     {
         var ovcUniqueId=document.getElementById("ovcId").value;
         getOvcDetails(ovcUniqueId)
     }
    function filterNames(namepart)
    {
        if(namepart=="" || namepart==" " || namepart==null)
        return
        req="partofnamestring;"+namepart
        getValuesFromDb('addcbo.do',req,'partofnamestring')
    }
    function showSearchDiv()
    {
       showComponent("pop")
    }
    function showComponent(id)
    {
        document.getElementById(id).style.visibility="visible"
    }
    function hideComponent(id)
    {
        document.getElementById(id).style.visibility="hidden"
    }
    function showSelected(ovcId)
    {
        document.getElementById("title").innerHTML="Patient Id -->"+ovcId
    }
    function setPatDetails(ovcId)
    {
        searchIndex=1
        hideComponent("pop")
        if(getOvcDetails(ovcId))
        return true
        else
        return false
    }
    function setToolTipText(txt,tp,lft)
    {
        showComponent("tooltip")
        document.getElementById("tooltip").style.top=tp
        document.getElementById("tooltip").style.left=lft
        document.getElementById("tooltipspan").innerHTML=txt
    }
