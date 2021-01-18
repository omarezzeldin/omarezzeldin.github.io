<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:div id="lookupEditDiv" forceId="true" style="visibility:visible;">
<t:messages showDetail="true" />
<t:panelGroup forceId="true" id="divEditLookup">

<t:panelGroup styleClass="divMainContent1WithOutWizardBar">
<t:panelGrid  rowClasses="row02,row01" cellpadding="3" columnClasses="colu1,colu2" cellspacing="0" width="100%" forceId="true" id="employeeEnteredDataPanel" columns="4">
   <t:panelGroup colspan="4" style="background-color:#ffffff;">
    <f:verbatim>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr>    
            <td width="9"><img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
            <td class="group_title"></f:verbatim>&nbsp; <t:outputLabel value="#{resourcesBundle.maindata}" styleClass="lable01"/> <f:verbatim></td>
        </tr>
        <tr>
            <td colspan="2" height="1"><img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
        </tr>
     </table>
    </f:verbatim>
    </t:panelGroup>
    
 <%-- row1---%>
   <t:outputText  value="#{resourcesBundle.civilid}"/>
   <t:inputText value="#{pageBeanName.selectedPageDTO.employeesDTO.code.key}" styleClass="textboxmedium" disabled="true"/>
   
   <t:outputText  value="#{resourcesBundle.employeeName}"/>
   <t:inputText value="#{pageBeanName.selectedPageDTO.employeesDTO.citizensResidentsDTO.fullName}" styleClass="textboxlarge" disabled="true"/>
   
    
   <t:panelGroup colspan="4" style="background-color:#ffffff;">
        <f:verbatim>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" >
            <tr>    
                <td width="9"><img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
                <td class="group_title"></f:verbatim>&nbsp; <t:outputLabel value="#{resourcesBundle.current_employee_data}" styleClass="lable01"/> <f:verbatim></td>
            </tr>
            <tr>
                <td colspan="2" height="1"><img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
            </tr>
         </table>
        </f:verbatim>
    </t:panelGroup>
    
    <t:outputText  value="#{resourcesBundle.spicifeid_wrk_center}" />
    <t:inputText value="#{pageBeanName.selectedPageDTO.employeesDTO.workCenterDTO.name}" styleClass="textboxlarge" disabled="true"/>
    
    <t:outputText  value="#{resourcesBundle.job_name}" />
    <t:inputText value="#{pageBeanName.selectedPageDTO.employeesDTO.jobDTO.name}" styleClass="textboxlarge" disabled="true" rendered="#{pageBeanName.selectedPageDTO.employeesDTO.techJobsDTO.name == null}"/>
    <t:inputText value="#{pageBeanName.selectedPageDTO.employeesDTO.techJobsDTO.name}" styleClass="textboxlarge" disabled="true" rendered="#{pageBeanName.selectedPageDTO.employeesDTO.techJobsDTO.name != null}"/>
  
   <t:outputText  value="#{resourcesBundle.cader}" />
   <t:inputText disabled="true" value="#{pageBeanName.caderName}"   tabindex="11"  maxlength="12" forceId="true"  id="candidate_cader" styleClass="textboxmedium"   />
   <%-- t:inputText value="#{pageBeanName.selectedPageDTO.employeesDTO.salEmpSalaryElementsDTOList[0].salElementGuidesDTO.parentObject.parentObject.parentObject.name}" styleClass="textboxmedium" disabled="true"/--%>
   
   <t:outputText  value="#{resourcesBundle.group}" />
   <t:inputText disabled="true"  value="#{pageBeanName.groupName}" tabindex="12"  maxlength="12" forceId="true"  id="candidate_jobs_group" styleClass="textboxmedium"   />
   <%-- t:inputText value="#{pageBeanName.selectedPageDTO.employeesDTO.salEmpSalaryElementsDTOList[0].salElementGuidesDTO.parentObject.parentObject.name}" styleClass="textboxmedium" disabled="true"/--%>
   
   <%-- t:outputText  value="#{resourcesBundle.degree}" /--%>
   <%-- t:inputText value="#{pageBeanName.selectedPageDTO.employeesDTO.salEmpSalaryElementsDTOList[0].salElementGuidesDTO.parentObject.name}" styleClass="textboxmedium" disabled="true"/--%>
   
   
    <t:outputText  value="#{resourcesBundle.candidate_sal_degree_label}" />
    <t:inputText disabled="true" value="#{pageBeanName.raiseName}" tabindex="13"  maxlength="12" forceId="true"  id="candidate_sal_degree" styleClass="textboxmedium"   />
    <%-- t:inputText value="#{pageBeanName.selectedPageDTO.employeesDTO.salEmpSalaryElementsDTOList[0].salElementGuidesDTO.name}" styleClass="textboxmedium" disabled="true"/--%> 
    
    <t:outputText  value=""/>
    <t:outputText  value=""/>
    
    <t:outputText  value="#{resourcesBundle.ministry_fileNo}"/>
    <t:inputText value="#{pageBeanName.selectedPageDTO.employeesDTO.ministryFileNo}" styleClass="textbox" disabled="true"/>
    
    <t:outputText  value="#{resourcesBundle.hireDate}"/>
    <t:inputText value="#{pageBeanName.selectedPageDTO.employeesDTO.hireDate}" styleClass="textbox" disabled="true" forceId="true" id="hireDateTxt"> 
         <f:converter converterId="SqlDateConverter"/>
    </t:inputText>
    <t:panelGroup colspan="4" style="background-color:#ffffff;">
        <f:verbatim>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" >
            <tr>
                <td width="9"><img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/></td>
                <td class="group_title"></f:verbatim>&nbsp; 
                    <t:outputLabel value="#{resourcesBundle.assign_job_needed_data}" styleClass="lable01"/><f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                     <c2:compareValidator componentToValidate="assign_Types" componentToCompare="assignTypeCodeAdminAssimentType" operator="not" errorMessage="#{resourcesBundle.must_change_selected_data}" highlight="false" display="dynamic" active="#{pageBeanName.adminAssigmentTypeMustChangeData}" />
                    <f:verbatim></td>
            </tr>
            <tr>
                <td colspan="2" height="1"><img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></td>
            </tr>
         </table>
        </f:verbatim>
    </t:panelGroup>

        <t:outputLabel value="#{resourcesBundle.assign_type}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu id="assign_Types" onblur="document.getElementById('work_Ministries').focus();" value="#{pageBeanName.assignTypesCode}" styleClass="textboxmedium" forceId="true">              
              <t:selectItems value="#{pageBeanName.assignTypesDTOList}" var="assignTypes" itemValue="#{assignTypes.code.keys[0]}" itemLabel="#{assignTypes.name}"/>
              <a4j:support actionListener="#{pageBeanName.changeData}" event="onchange" reRender="employeeEnteredDataPanel,scriptPnl" />
              <f:converter converterId="javax.faces.Long"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"  />
        <f:verbatim><br/></f:verbatim>
       <c2:requiredFieldValidator componentToValidate="assign_Types" errorMessage="#{globalResources.missingField}"   highlight="false"  display="dynamic" />
   </t:panelGroup>

    <t:outputText  value="#{resourcesBundle.wrkCenter}"/>    
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="work_Ministries" value="#{pageBeanName.workMinistrieKey}"  styleClass="textboxmedium">
               <f:selectItem itemLabel="#{resourcesBundle.selectElement}" itemValue="" />
               <t:selectItems value="#{pageBeanName.workMinistriesList}" itemLabel="#{workCentersDTO.name}" itemValue="#{workCentersDTO.code.key}" var="workCentersDTO" />                              
               <a4j:support actionListener="#{pageBeanName.changeWorkCenterData}" event="onchange" reRender="employeeEnteredDataPanel,scriptPnl,jobPanelId" />
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"  />
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton value="#{globalResources.Available}" styleClass="cssButtonSmaller" reRender="lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn" action="#{pageBeanName.showWorkMinistriesListOfValuesDiv}" oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();" />           
        <f:verbatim><br/></f:verbatim>
        <c2:compareValidator   componentToValidate="work_Ministries" componentToCompare="employee_work_ministry_code" operator="not" errorMessage="#{resourcesBundle.must_change_wok_ceneter}"   highlight="false"  display="dynamic"
            active="#{pageBeanName.assignTypesCode != null && pageBeanName.assignTypesCode == pageBeanName.assignTypeCodeWithPrivilege}" />
       <c2:requiredFieldValidator componentToValidate="work_Ministries" errorMessage="#{globalResources.missingField}"   highlight="false"  display="dynamic" />
   </t:panelGroup>

    <t:outputLabel value="#{resourcesBundle.cader}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu id="new_cader" value="#{pageBeanName.caderCode}" styleClass="textboxmedium" forceId="true">
              <f:selectItem itemLabel="#{resourcesBundle.selectElement}" itemValue=""/>
              <t:selectItems value="#{pageBeanName.cadersList}" var="cader" itemValue="#{cader.code.keys[0]}" itemLabel="#{cader.name}"/>          
              <a4j:support actionListener="#{pageBeanName.fillGroups}" event="onchange" reRender="employeeEnteredDataPanel,scriptPnl" />
              <f:converter converterId="javax.faces.Long"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"  />
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton value="#{globalResources.Available}" styleClass="cssButtonSmaller" reRender="lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn" action="#{pageBeanName.showCadersListOfValuesDiv}" oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();" />
        <f:verbatim><br/></f:verbatim>
       <c2:requiredFieldValidator componentToValidate="new_cader" errorMessage="#{globalResources.missingField}"   highlight="false"  display="dynamic"/>
   </t:panelGroup>
    
    <t:outputLabel value="#{resourcesBundle.group}" styleClass="lable01"/>
    <t:panelGroup id="grp_panelGrp" forceId="true">
        <t:selectOneMenu id="new_group" value="#{pageBeanName.groupCode}" styleClass="textboxmedium" forceId="true" disabled="#{pageBeanName.caderCode==null}">
              <f:selectItem itemLabel="#{resourcesBundle.selectElement}" itemValue=""/>
              <t:selectItems value="#{pageBeanName.groupsList}" var="group" itemValue="#{group.code.keys[0]}" itemLabel="#{group.name}"/>          
              <f:converter converterId="javax.faces.Long"/>
              <a4j:support actionListener="#{pageBeanName.changeData}" event="onchange" reRender="employeeEnteredDataPanel,scriptPnl" />
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"  />
        <f:verbatim><br/></f:verbatim>
       <c2:requiredFieldValidator componentToValidate="new_group" errorMessage="#{globalResources.missingField}"   highlight="false"  display="dynamic" />
   </t:panelGroup>
    
    <t:outputLabel value="#{resourcesBundle.job_name}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="jobPanelId">
          <t:inputText disabled="true" tabindex="999999999" styleClass="textboxlarge" forceId="true" id="jobs_name" value="#{pageBeanName.jobName}" />
          <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        <a4j:commandButton value="#{globalResources.Available}" styleClass="cssButtonSmaller" disabled="#{pageBeanName.workMinistrieKey == '' || pageBeanName.workMinistrieKey == null}"  reRender="lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn" action="#{pageBeanName.showJobsListOfValuesDiv}" oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();" />
        <h:outputText value="*" styleClass="mandatoryAsterisk"  />
       <c2:requiredFieldValidator componentToValidate="jobs_name" errorMessage="#{globalResources.missingField}"   highlight="false"  display="dynamic" />
    </t:panelGroup>
    
    <t:panelGroup colspan="2" rendered="#{ (pageBeanName.jobDescription == null || pageBeanName.jobDescription =='') }"/>
    <t:outputText value="#{resourcesBundle.Job_Work_Center_Job_Description}"  rendered="#{ !(pageBeanName.jobDescription == null || pageBeanName.jobDescription =='') }"/>
    <t:inputText disabled="true" tabindex="4444444444444" styleClass="textboxlarge" forceId="true" id="jobDescriptionName" value="#{pageBeanName.jobDescription}" rendered="#{ !(pageBeanName.jobDescription == null || pageBeanName.jobDescription =='') }"/>
    
    <t:outputLabel value="#{resourcesBundle.assignment_reason}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu id="assign_Reasons" value="#{pageBeanName.assignReasonCode}" styleClass="textboxmedium" forceId="true">
              <f:selectItem itemLabel="#{resourcesBundle.selectElement}" itemValue=""/>
              <t:selectItems value="#{pageBeanName.assignReasonsDTOList}" var="assignReason" itemValue="#{assignReason.code.keys[0]}" itemLabel="#{assignReason.name}"/>          
              <f:converter converterId="javax.faces.Long"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"  />
        <f:verbatim><br/></f:verbatim>
       <c2:requiredFieldValidator componentToValidate="assign_Reasons" errorMessage="#{globalResources.missingField}"   highlight="false"  display="dynamic" />
   </t:panelGroup>
   
   <t:panelGroup colspan="2"/>
   
   <t:outputLabel value="#{resourcesBundle.assignment_from_date}" styleClass="lable01"/>
   <t:panelGroup>
       <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="assignment_from_date" value="#{pageBeanName.selectedPageDTO.fromDate}" size="20"                  
                  maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                  popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
            <f:converter converterId="SqlDateConverter"/> 
       </t:inputCalendar> 
        <h:outputText value="*" styleClass="mandatoryAsterisk"  />
        <f:verbatim><br/></f:verbatim>
        <c2:compareDateValidator componentToValidate="hireDateTxt" componentToCompare="assignment_from_date" operator="before" display="dynamic" errorMessage="#{resourcesBundle.startAssignDateAfterEmpHireDate}" highlight="false"/>
       <c2:requiredFieldValidator componentToValidate="assignment_from_date" errorMessage="#{globalResources.missingField}"   highlight="false"  display="dynamic" />
       <c2:dateFormatValidator   componentToValidate="assignment_from_date"  errorMessage="#{resourcesBundle.dateFormat}"   highlight="false"  display="dynamic" />
   </t:panelGroup>
   
   <t:outputLabel value="#{resourcesBundle.assignment_to_date}" styleClass="lable01"/>
   <t:panelGroup>
       <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="assignment_to_date" value="#{pageBeanName.selectedPageDTO.untilDate}" size="20"                  
                  maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                  popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
            <f:converter converterId="SqlDateConverter"/> 
      </t:inputCalendar>
        <f:verbatim><br/></f:verbatim>       
       <c2:compareDateValidator componentToValidate="assignment_from_date" componentToCompare="assignment_to_date" operator="before" display="dynamic" errorMessage="#{resourcesBundle.startAssignDatebeforeEndAssignDateErrMsg}" highlight="false" />
       <c2:dateFormatValidator   componentToValidate="assignment_to_date"  errorMessage="#{resourcesBundle.dateFormat}"   highlight="false"  display="dynamic" /> 
   </t:panelGroup> 
</t:panelGrid>
</t:panelGroup>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="assignment_from_date,50,150:assignment_to_date,50,200" />
<t:panelGrid columns="4" border="0" align="center">
        <t:commandButton forceId="true" styleClass="cssButtonSmall" id="SaveButtonEdit" onclick="return validatemyForm();" value="#{globalResources.SaveButton}" action="#{pageBeanName.editAdminAssign}"/>        
        <t:commandButton forceId="true" styleClass="cssButtonSmall" id="CancelButtonEdit" value="#{globalResources.back}" action="#{pageBeanName.goToListPage}" onblur="foucsFirstTextOnEditPage();" immediate="true"/>
        <t:inputText  id="employee_work_ministry_code" value="#{pageBeanName.selectedPageDTO.employeesDTO.workCenterDTO.code.key}" forceId="true"  style="display:none;font-size:0px;height:0px;" disabled="true"/>
        <t:inputText id="assignTypeCodeAdminAssimentType" value="#{pageBeanName.assignTypeCodeAdminAssimentType}" forceId="true"  style="display:none;font-size:0px;height:0px;" disabled="true"/>
</t:panelGrid>
</t:panelGroup>
</t:div>
<f:verbatim>
    <script type="text/javascript">    
        foucsFirstTextOnEditPage();
        
        function foucsFirstTextOnEditPage(){
            if(document.getElementById('assign_Types') != null){            
                document.getElementById('assign_Types').focus();
                document.getElementById('assign_Types').focus();
            }
        }
    </script>
</f:verbatim>    