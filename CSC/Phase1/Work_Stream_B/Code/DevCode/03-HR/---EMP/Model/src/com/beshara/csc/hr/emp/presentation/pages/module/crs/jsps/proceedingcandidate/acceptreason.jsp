<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.csc.hr.crs.presentation.resources.crs" var="resourcesBundle"/>
<t:messages showDetail="true"/>
<t:panelGroup forceId="true" id="divEditLookup">
<t:messages showDetail="true"/>
  <t:panelGrid columns="4" rowClasses="row02,row01" cellpadding="0" columnClasses="colu1,colu2,colu1,colu2" cellspacing="0" width="100%">
    <!-- Row1 -->
    <t:panelGroup>
    <t:outputLabel value="#{resourcesBundle.jobCenter}" styleClass="lable01"/>
    </t:panelGroup>
    <t:panelGroup>
      <t:selectOneMenu id="job_center_list" value="#{pageBeanName.employeeDTO.workCenterKey}" styleClass="DropdownboxMedium2" forceId="true" onblur="setFocusOnlyOnElement('supervision_jobs_list');">
        <f:selectItem itemLabel="#{resourcesBundle.Item_Select}" itemValue=""/>
        <t:selectItems value="#{pageBeanName.work_centers_list}" var="reasonscat" itemValue="#{reasonscat.code.key}" itemLabel="#{reasonscat.name}"/>
      </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
      <f:verbatim>
        <br/>
      </f:verbatim>
      <c2:requiredFieldValidator componentToValidate="job_center_list" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 1}"/>
    </t:panelGroup>
    <t:outputLabel/>
    <t:outputLabel/>
    <!-- Row2 -->
    <t:outputLabel value="#{resourcesBundle.job2}" styleClass="lable01"/>
    <t:selectOneMenu id="supervision_jobs_list" value="#{pageBeanName.employeeDTO.jobKey}" styleClass="DropdownboxMedium2" forceId="true">
      <f:selectItem itemLabel="#{resourcesBundle.Item_Select}" itemValue=""/>
      <t:selectItems value="#{pageBeanName.supervision_jobs_list}" var="supervisionJob" itemValue="#{supervisionJob.code.key}" itemLabel="#{supervisionJob.name}"/>
    </t:selectOneMenu>
    <t:panelGroup>
    <t:outputLabel value="#{resourcesBundle.job3}" styleClass="lable01"/>
    </t:panelGroup>
    <t:panelGroup>
      <t:selectOneMenu id="technical_jobs_list" value="#{pageBeanName.employeeDTO.techJobKey}" styleClass="DropdownboxMedium2" forceId="true">
        <f:selectItem itemLabel="#{resourcesBundle.Item_Select}" itemValue=""/>
        <t:selectItems value="#{pageBeanName.technical_jobs_list}" var="technicaljob" itemValue="#{technicaljob.code.key}" itemLabel="#{technicaljob.name}"/>
      </t:selectOneMenu>
      <h:outputText value="*" styleClass="mandatoryAsterisk"/>
      <f:verbatim>
        <br/>
      </f:verbatim>
      <c2:requiredFieldValidator componentToValidate="technical_jobs_list" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 1}"/>
    </t:panelGroup>
    <!-- Row3 -->
    <t:panelGroup>
    <t:outputLabel value="#{resourcesBundle.acceptance_date}" styleClass="lable01"/>
    </t:panelGroup>
    <t:panelGroup>
      <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" value="#{pageBeanName.employeeDTO.hireDate}" popupDateFormat="dd/MM/yyyy" forceId="true" id="acceptance_date"
                       onchange="markDirty();" size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                       popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
        <f:converter converterId="SqlDateConverter"/>
      </t:inputCalendar>      
      <h:outputText value="*" styleClass="mandatoryAsterisk"/>
      <f:verbatim>
        <br/>
      </f:verbatim>
      <c2:requiredFieldValidator componentToValidate="acceptance_date" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 1}"/>
      <c2:dateFormatValidator componentToValidate="acceptance_date" errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"
                              active="#{proceedingCandidateListBean.divIndicator == 1}"/>
      <c2:compareDateValidator componentToValidate="nominationDateID" componentToCompare="acceptance_date" active="#{proceedingCandidateListBean.divIndicator == 1}" errorMessage="#{resourcesBundle.acceptDateAfterNominationDate}" operator="before" display="dynamic"/>
    </t:panelGroup>
    <t:panelGroup>
    <t:outputLabel value="#{resourcesBundle.work_start_date}" styleClass="lable01"/>
    </t:panelGroup>
    <t:panelGroup>
      <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" value="#{pageBeanName.employeeDTO.startWorkingDate}" popupDateFormat="dd/MM/yyyy" forceId="true" id="work_start_date"
                       onchange="markDirty();" size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                       popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
        <f:converter converterId="SqlDateConverter"/>
      </t:inputCalendar>
      <h:outputText value="*" styleClass="mandatoryAsterisk"/>
      <f:verbatim>
        <br/>
      </f:verbatim>
      <c2:requiredFieldValidator componentToValidate="work_start_date" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 1}"/>
      <c2:dateFormatValidator componentToValidate="work_start_date" errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"
                              active="#{proceedingCandidateListBean.divIndicator == 1}"/>
    <c2:compareDateValidator componentToValidate="work_start_date" componentToCompare="acceptance_date"  display="dynamic" errorMessage="#{resourcesBundle.work_start_date_after_acceptance_date}" operator="after"  highlight="false" uniqueId="compareDatecompareID" />
    </t:panelGroup>
    
    <!-- Row4 -->
    <t:panelGroup>
    <t:outputLabel value="#{resourcesBundle.ministryFileNo}" styleClass="lable01"/>
    </t:panelGroup>
    <t:panelGroup>
     <t:inputText  id="ministryFileNoAccept" value="#{pageBeanName.employeeDTO.ministryFileNo}" styleClass="textbox"  />
     <h:outputText value="*" styleClass="mandatoryAsterisk"/>
     <f:verbatim>
        <br/>
      </f:verbatim>
      <c2:requiredFieldValidator componentToValidate="ministryFileNoAccept" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 1}"/>
     </t:panelGroup>
     <h:outputLabel/>
     <h:outputLabel/>
        
        <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <htm:table width="100%" border="0" cellspacing="0" cellpadding="0" >
            <htm:tr>    
                <htm:td width="9"><htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
                <htm:td styleClass="group_title"> <t:outputLabel value="#{resourcesBundle.degreeJob}" styleClass="lable01"/> </htm:td>
            </htm:tr>
            <htm:tr>
                <htm:td colspan="2" height="3" valign="top"><htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td>
            </htm:tr>
            </htm:table>
        </t:panelGroup>
    
     <!--Row 5-->
       <h:outputText  value="#{resourcesBundle.cader}" />
   <t:panelGroup forceId="true" id="caderPanel">
        <t:selectOneMenu forceId="true"  id="employees_cader" styleClass="DropdownboxMedium2"  value="#{pageBeanName.caderCode}">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.caderList}" itemLabel="#{cader.name}" itemValue="#{cader.code.elmguideCode}" var="cader"/>              
                <a4j:support  reRender="groupPanel,degreePanel,raisePanel" actionListener="#{pageBeanName.resetSelectedJobData}" event="onchange" />
        </t:selectOneMenu> 
   </t:panelGroup>

   
   <h:outputText  value="#{resourcesBundle.group}" />
   <t:panelGroup forceId="true" id="groupPanel">
        <t:selectOneMenu forceId="true"  id="employees_group" styleClass="DropdownboxMedium2"  value="#{pageBeanName.groupCode}">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.groupList}" itemLabel="#{group.name}" itemValue="#{group.code.elmguideCode}" var="group"/>              
                <a4j:support   reRender="degreePanel,raisePanel" actionListener="#{pageBeanName.resetDegreeAndRaiseList}" event="onchange" />
        </t:selectOneMenu> 
   </t:panelGroup>
   
    <h:outputText  value="#{resourcesBundle.degree}" />
   <t:panelGroup forceId="true" id="degreePanel">
        <t:selectOneMenu forceId="true"  id="employees_degree" styleClass="DropdownboxMedium2"  value="#{pageBeanName.degreeCode}">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.degreeList}" itemLabel="#{degree.name}" itemValue="#{degree.code.elmguideCode}" var="degree"/>              
                <a4j:support  reRender="raisePanel"  actionListener="#{pageBeanName.resetRaiseList}" event="onchange" />
        </t:selectOneMenu> 
   </t:panelGroup>
   <t:panelGroup>
        <h:outputText  value="#{resourcesBundle.raise}" />    
    </t:panelGroup>

   <t:panelGroup forceId="true" id="raisePanel">
        <t:selectOneMenu forceId="true"  id="employees_raise" styleClass="DropdownboxMedium2"  value="#{pageBeanName.raiseCode}">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.raiseList}" itemLabel="#{raisem.name}" itemValue="#{raisem.code.elmguideCode}" var="raisem"/>
        </t:selectOneMenu> 
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
             <f:verbatim>
        <br/>
      </f:verbatim>
      <c2:requiredFieldValidator componentToValidate="employees_raise" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 1}"/>
   </t:panelGroup>
     
      <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <htm:table width="100%" border="0" cellspacing="0" cellpadding="0" >
            <htm:tr>    
                <htm:td width="9"><htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
                <htm:td styleClass="group_title"> <t:outputLabel value="#{resourcesBundle.budget}" styleClass="lable01"/> </htm:td>
            </htm:tr>
            <htm:tr>
                <htm:td colspan="2" height="3" valign="top"><htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td>
            </htm:tr>
            </htm:table>
     </t:panelGroup>
     <t:panelGroup>
      <h:outputText  value="#{resourcesBundle.budgetType}" />
     </t:panelGroup>
   <t:panelGroup forceId="true" id="budgetTypePanel">
        <t:selectOneMenu forceId="true"  id="employees_budgetType" styleClass="DropdownboxMedium2" value="#{pageBeanName.bgtTypeKey}" >
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.budgetTypeList}" itemLabel="#{budgetType.name}" itemValue="#{budgetType.code.key}" var="budgetType"/>
        </t:selectOneMenu> 
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
           <f:verbatim>
        <br/>
      </f:verbatim>
      <c2:requiredFieldValidator componentToValidate="employees_budgetType" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 1}"/>
   </t:panelGroup>
    
   <t:panelGroup>
           <h:outputText  value="#{resourcesBundle.budget}" />
   </t:panelGroup>
   <t:panelGroup forceId="true" id="budgetPanel">
        <t:selectOneMenu forceId="true"  id="employees_budget"  styleClass="DropdownboxMedium2" value="#{pageBeanName.bgtPrgmKey}">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.budgetList}" itemLabel="#{budget.name}" itemValue="#{budget.code.key}" var="budget"/>
        </t:selectOneMenu> 
           <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
        <br/>
      </f:verbatim>
      <c2:requiredFieldValidator componentToValidate="employees_budget" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 1}"/>
   </t:panelGroup>
   
     
  </t:panelGrid>
</t:panelGroup>
<f:verbatim>
  <br/>
</f:verbatim>
<t:panelGrid columns="3" border="0" align="center">
  <h:commandButton styleClass="cssButtonSmall" id="SaveButtonEdit" value="#{globalResources.SaveButton}" action="#{pageBeanName.accept}" onclick="return validatemyForm();"/>
  <t:commandButton styleClass="cssButtonSmall" id="CancelButtonEdit" value="#{globalResources.back}" type="button" forceId="true"
                   onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,'myForm:NameEdit','myForm:errorEdit');" onblur="setFocusAtMyFirstElement();" />                   
</t:panelGrid>
<t:inputHidden value="#{pageBeanName.selectedDTOS[0].cndApproveDate}" id="nominationDateID" forceId="true" rendered="#{proceedingCandidateListBean.divIndicator == 1}">
    <f:converter converterId="SqlDateConverter"/>
</t:inputHidden>

 <script type="text/javascript">
       setFocusAtMyFirstElement(); 
       function setFocusAtMyFirstElement(){
               setFocusOnElement('job_center_list');
       }
</script>
