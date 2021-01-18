<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>


<htm:style>
    .divContent1Dynamic {
        height: 380px !important;
        margin: 8px 3px !important;
    }
</htm:style>
<t:panelGroup  forceId="true" style="width:99%" id="emp_query_panel_id">


<t:panelGroup rendered="#{pageBeanName.outerModule}" style="width:99%" >
<t:panelGrid  border="0" align="center">
 <t:outputText  forceId="true" id="invalCivilID2" value="#{globalResources.civiliderror}" rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
 <t:outputText  forceId="true" id="empHired2"   value="#{resourcesBundle.emp_not_hired}"  rendered="#{!pageBeanName.empHired}" styleClass="errMsg"/>
<t:outputText  forceId="true" id="payrollInfoExist2"   value="#{resourcesBundle.emp_payroll_info_not_exist}"  rendered="#{!pageBeanName.payrollInfoExist}" styleClass="errMsg"/>
</t:panelGrid>
</t:panelGroup>

<t:panelGrid columnClasses="colu1,colu2" columns="4" width="100%"  rowClasses="row01,row02" cellpadding="3" cellspacing="0" forceId="true" id="cnt1Panel" rendered="#{!pageBeanName.outerModule}" >
    <h:outputText value="#{resourcesBundle.civilid}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputText rendered="#{!pageBeanName.civilExist}" tabindex="1" onblur="nextMoveByTab();" maxlength="12" forceId="true"  id="CivilIdAdd" styleClass="textbox"  value="#{pageBeanName.civilId}" onkeypress="FireButtonClick('civil_exist_btn')"/>
        <t:outputText   styleClass="textbox"  value="#{pageBeanName.civilId}" rendered="#{pageBeanName.civilExist}" />
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <t:commandButton rendered="#{! pageBeanName.civilExist}" id="civil_exist_btn" forceId="true" onclick="return validatemyForm();" tabindex="2" 
            value="#{globalResources.Available}"  styleClass="cssButtonSmall"  action="#{pageBeanName.filterByCivilId}"/>
        <a4j:commandButton value="#{globalResources.reSetButton}" rendered="#{pageBeanName.civilExist}"  styleClass="cssButtonSmall"  action="#{pageBeanName.reSetData}"  
            reRender="emp_query_panel_id,scriptPanelID"/>
        <f:verbatim> &nbsp; </f:verbatim>
       <%-- <a4j:commandButton type="button"  value="#{globalResources.SearchButton}" styleClass="cssButtonSmall" oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();" action="#{pageBeanName.showSearchForEmployeeDiv}" onblur="doOnBlur();" tabindex="3"
               reRender="lovEmp,mainDataEmpPanel" rendered="#{!pageBeanName.civilExist}" />   --%>     
        <f:verbatim ><br/></f:verbatim>
        <c2:requiredFieldValidator     active="#{!queryListBean.civilExist}"    componentToValidate="CivilIdAdd"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
        <c2:regularExpressionValidator active="#{!queryListBean.civilExist}"   componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/"    errorMessage="#{globalResources.civil_no_not_less_than_12}"   highlight="false"  display="dynamic"/>
        
        <t:outputText  forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}" rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="empHired"   value="#{resourcesBundle.employee_not_hired_query}"  rendered="#{!pageBeanName.empHired}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="payrollInfoExist"   value="#{resourcesBundle.emp_payroll_info_not_exist}"  rendered="#{!pageBeanName.payrollInfoExist}" styleClass="errMsg"/>
    </t:panelGroup> 
</t:panelGrid>


<t:panelGroup styleClass="divMainContent1WithOutWizardBar"  id="divMainContent1WithOutWizardBar" forceId="true" style="width:100%" >

<t:panelGrid  rowClasses="row02,row01" cellpadding="3" columnClasses="colu1,colu2" cellspacing="0" width="100%" forceId="true" id="employeeMainDataPanel" columns="4">

<t:panelGroup colspan="4" style="background-color:#ffffff;" >
    <f:verbatim>
        <table width="100%" border="0" cellspacing="0" cellpadding="3" height="100%">
        <tr>    
            <td width="9"><img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
            <td class="group_title"></f:verbatim>&nbsp; <t:outputLabel value="#{resourcesBundle.go_work}" styleClass="lable01"/> <f:verbatim></td>
        </tr>
        <tr>
            <td colspan="2" height="1"><img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
        </tr>
     </table>
    </f:verbatim>
    </t:panelGroup>
   
   <t:outputText  value="#{resourcesBundle.hiretype}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.hireTypeDTO.name}" styleClass="textboxmedium" disabled="true"/>
    
   <t:outputText  value="#{resourcesBundle.go_work_data}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.startWorkingDate}" styleClass="textbox" disabled="true"> 
        <f:converter converterId="SqlDateConverter"/>
   </t:inputText>   

   <t:panelGroup colspan="4" style="background-color:#ffffff;">
   <htm:table width="100%">
    <htm:tr>
       <htm:td width="9"><htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
           <htm:td styleClass="group_title">&nbsp;<t:outputLabel value="#{resourcesBundle.maindata}" styleClass="lable01"/> </htm:td>
       </htm:tr>
    <htm:tr><htm:td colspan="2" height="1"><htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td></htm:tr>
    </htm:table>
   </t:panelGroup>
   
   <%--t:outputText  value="#{resourcesBundle.civilid}"/>   
   <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.code.civilId}" styleClass="textbox" disabled="true"/--%>        
   
   <t:outputText  value="#{resourcesBundle.employeeName}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}" styleClass="textboxlarge" disabled="true"/>
   
   <t:outputText  value="#{resourcesBundle.gender}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.genderTypesDTO.name}" styleClass="textbox" disabled="true"/>
   
   <t:outputText  value="#{resourcesBundle.status}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.maritalStatusDTO.name}" styleClass="textbox" disabled="true"/>
   
   <t:outputText  value="#{resourcesBundle.emp_birth_date}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.birthDate}" styleClass="textbox" disabled="true" converter="TimeStampConverter"/>
   
   <t:outputText  value="#{resourcesBundle.nationalityType}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.countriesDTO.name}" styleClass="textbox" disabled="true"/>
   
    <t:outputText  value="#{resourcesBundle.ministry_fileNo}" styleClass="lable01"/>
    <t:inputText value="#{pageBeanName.pageDTO.ministryFileNo}" styleClass="textbox" disabled="true"/>
   
   <t:outputText  value="#{resourcesBundle.job_name}"  styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.jobDTO.name}" styleClass="textboxlarge" disabled="true"/>
   
    <t:outputText  value="#{resourcesBundle.cscfilno}" styleClass="lable01" />
    <t:inputText value="#{pageBeanName.pageDTO.cscFileNo}" styleClass="textbox" disabled="true"/>
    
    <t:outputText  value="#{resourcesBundle.hireDate}" styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.hireDate}" styleClass="textbox" disabled="true" forceId="true" id="hireDateTxt"> 
        <f:converter converterId="SqlDateConverter"/>
   </t:inputText>
    
     <t:outputText  value="#{resourcesBundle.go_work_data}" styleClass="lable01" />
    <t:panelGroup >
        <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.pageDTO.startWorkingDate}" id="go_work_date"
                            onchange="return validateInputCalenderFormate('go_work_date','null','null')" size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" 
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" disabled="true" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
    </t:panelGroup>
   
   <t:outputText  value="#{resourcesBundle.spicifeid_wrk_center}" styleClass="lable01" />
   <t:panelGroup colspan="3">
         <t:inputText value="#{pageBeanName.pageDTO.workCenterDTO.name}" styleClass="textboxlarge" disabled="true"/>
   </t:panelGroup>
   
   <!-- Row 2 -->   
    <t:panelGroup colspan="4" style="background-color:#ffffff;">
   <htm:table width="100%">
    <htm:tr>
       <htm:td width="9"><htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
           <htm:td styleClass="group_title">&nbsp;<t:outputLabel value="#{resourcesBundle.budget}" styleClass="lable01"/> </htm:td>
       </htm:tr>
    <htm:tr><htm:td colspan="2" height="1"><htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td></htm:tr>
    </htm:table>
   </t:panelGroup>
   
    <t:outputText  value="#{resourcesBundle.budgetType}"  styleClass="lable01"/>
    <t:inputText value="#{pageBeanName.pageDTO.bgtTypesDTO.name}" styleClass="textboxmedium" disabled="true"/>
   
   <t:outputText  value="#{resourcesBundle.budget}"  styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.bgtProgramsDTO.name}" styleClass="textboxmedium" disabled="true"/>
   
   <!-- row 3-->
   <t:panelGroup colspan="4" style="background-color:#ffffff;">
   <htm:table width="100%">
    <htm:tr>
       <htm:td width="9"><htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
           <htm:td styleClass="group_title">&nbsp;<t:outputLabel value="#{resourcesBundle.job_main_data}" styleClass="lable01"/> </htm:td>
       </htm:tr>
    <htm:tr><htm:td colspan="2" height="1"><htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td></htm:tr>
    </htm:table>
   </t:panelGroup>
   
    
   <t:outputText  value="#{resourcesBundle.cader}"  styleClass="lable01"/>
   <%--  --%>
   <t:inputText  value="#{pageBeanName.salaryElementDTO.salElementGuidesDTO.parentObject.parentObject.parentObject.name}" styleClass="textboxmedium" disabled="true"/>
   
   <t:outputText  value="#{resourcesBundle.group}" styleClass="lable01" />
   <%--  --%>
   <t:inputText value="#{pageBeanName.salaryElementDTO.salElementGuidesDTO.parentObject.parentObject.name}" styleClass="textboxmedium" disabled="true"/>
   
   
   <t:outputText  value="#{resourcesBundle.degree}"  styleClass="lable01"/>
   <%--  --%>
   <t:inputText value="#{pageBeanName.salaryElementDTO.salElementGuidesDTO.name}" styleClass="textboxmedium" disabled="true"/>
    <t:outputText  value="#{resourcesBundle.bouns_date}"  styleClass="lable01"/>
    <t:inputText value="#{pageBeanName.pageDTO.dateOfNextRaise}" styleClass="textbox" disabled="true">
    <f:converter converterId="SqlDateConverter"  />
    </t:inputText>
    
   <t:panelGroup colspan="4" style="background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="3" >
    <htm:tr>    
        <htm:td width="9"><htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
        <htm:td styleClass="group_title"> <t:outputLabel value=" #{resourcesBundle.qualificationData}" styleClass="lable01"/> </htm:td>
    </htm:tr>
    <htm:tr>
        <htm:td colspan="2" height="1"><htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td>
    </htm:tr>
 </htm:table>
</t:panelGroup>

<h:outputText  value="#{resourcesBundle.educationLevel}" styleClass="lable01"/>
<t:inputText disabled="true"  value="#{pageBeanName.personQulDTO.qualificationsDTO.educationLevelsDTO.name}"  styleClass="textboxmedium"  />

<h:outputText  value="#{resourcesBundle.qualification}" styleClass="lable01"/>
<t:inputText disabled="true"  value="#{pageBeanName.personQulDTO.qualificationsDTO.name}"  styleClass="textboxlarge"  />

<h:outputText  value="#{resourcesBundle.qualificationCountry}" styleClass="lable01"/>
<t:inputText disabled="true"  value="#{pageBeanName.personQulDTO.centersDTO.countries.name}"  styleClass="textboxmedium"  />

<h:outputText  value="#{resourcesBundle.qualificationMinistry}" styleClass="lable01"/>
<t:inputText disabled="true"  value="#{pageBeanName.personQulDTO.centersDTO.name}"  styleClass="textboxlarge"  />

<h:outputText  value="#{resourcesBundle.qualificationDate}" styleClass="lable01"/>
 <t:inputCalendar value="#{pageBeanName.personQulDTO.qualificationDate}"    popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true"  id="qualificationDateTwo"
  styleClass="textbox" currentDayCellClass="currentDayCell"  disabled="true" renderAsPopup="true" 
  align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
 <f:converter converterId="SqlDateConverter"/>
</t:inputCalendar>
<h:outputText  value="#{resourcesBundle.grade}" styleClass="lable01"/>
<t:inputText  value="#{pageBeanName.personQulDTO.qualificationDegree}" disabled="true"  forceId="true"  id="grade" styleClass="textbox" />

      
   <t:panelGroup colspan="4" style="background-color:#ffffff;">
   <htm:table width="100%">
    <htm:tr>
       <htm:td width="9"><htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
           <htm:td styleClass="group_title">&nbsp;<t:outputLabel value="#{resourcesBundle.bank_main_data}" styleClass="lable01"/> </htm:td>
       </htm:tr>
    <htm:tr><htm:td colspan="2" height="1"><htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td></htm:tr>
    </htm:table>
   </t:panelGroup>
      
     <%-- row 6---%>
   <t:outputText  value="#{resourcesBundle.employees_bank}" styleClass="lable01" />
   <t:inputText value="#{pageBeanName.pageDTO.bankBrancheDTO.banksDTO.name}" styleClass="textboxmedium" disabled="true"/>
   
   <t:outputText  value="#{resourcesBundle.employees_bransh}"  styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.bankBrancheDTO.name}" styleClass="textboxmedium" disabled="true"/> 
   
   <%-- row 7--%>
   <t:outputText  value="#{resourcesBundle.employees_bank_account}"  styleClass="lable01"/>
   <t:inputText value="#{pageBeanName.pageDTO.accountNo}" styleClass="textbox" disabled="true"/> 
   <f:verbatim/> 
   <f:verbatim/> 
       

</t:panelGrid>
</t:panelGroup>
<t:panelGroup rendered="#{pageBeanName.outerModule}" style="width:99%" >
<t:panelGrid columns="3" border="0" align="center">
        <t:commandButton styleClass="cssButtonSmall" id="CancelButtonEdit" forceId="true"  value="#{globalResources.back}" type="button" onclick="window.opener='self';window.top.close();"  />   
</t:panelGrid>
</t:panelGroup>
</t:panelGroup>
