<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>


<t:panelGroup colspan="4">

<htm:table width="100%">
       <htm:tr>
               <htm:td width="9"><htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
                                 <htm:td styleClass="group_title"> <t:outputLabel value="#{resourcesBundle.arrowTitle}" styleClass="lable01"/> </htm:td>
                                    </htm:tr>
                                 <htm:tr>
                                <htm:td colspan="2" height="1"><htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td>
                                 </htm:tr>
                   </htm:table>
                                             
                                             
                       
</t:panelGroup>

<t:panelGrid border="0"  rowClasses="row02,row01" cellpadding="3" columnClasses="colu1,colu2" cellspacing="0" width="100%" forceId="true" id="employeeMainDataPanel" columns="4">

<h:outputText  value="#{resourcesBundle.wrkCenter}" styleClass="lable01"/>
<t:panelGroup>
    <t:selectOneMenu id="work_center_combo" onblur="document.getElementById('myForm:work_center_btn').focus();" forceId="true" value="#{pageBeanName.selectedWorkCentersValue}" disabled="#{pageBeanName.srchAgainBtn}"  styleClass="textboxmedium">
                <f:selectItem itemLabel="#{resourcesBundle.defaultValue}" itemValue="#{pageBeanName.defaultValue}" />
                <t:selectItems value="#{pageBeanName.wrkCenterList}" itemLabel="#{workCentersDTO.name}" itemValue="#{workCentersDTO.code.key}" var="workCentersDTO" />
                <a4j:support action="#{pageBeanName.wrkCenterValue}" event="onchange" reRender="employeeMainDataPanel, searchButtonGroup" />
    </t:selectOneMenu>
    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
    <a4j:commandButton id="work_center_btn" value="..." styleClass="cssButtonSmall" reRender="lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn" action="#{pageBeanName.showListOfValuesDiv}" 
                     disabled="#{pageBeanName.srchAgainBtn}" oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();" />
    </t:panelGroup>
   
    <h:outputText  value="#{resourcesBundle.bugetProgram}" styleClass="lable01"/>
      <t:selectOneMenu value="#{pageBeanName.budgetProgramValue}" forceId="true" id="budget_program_combo" disabled="#{pageBeanName.srchAgainBtn}"  styleClass="textboxmedium">
                <f:selectItem itemLabel="#{resourcesBundle.defaultValue}" itemValue="#{pageBeanName.defaultValue}" />
                <t:selectItems value="#{pageBeanName.bugetProgramList}" itemLabel="#{BgtProgramsDTO.name}" itemValue="#{BgtProgramsDTO.code.key}" var="BgtProgramsDTO" />
              
    </t:selectOneMenu>
    
    
    
    <t:panelGroup colspan="4">
    <htm:table width="100%"  style="background-color: #FFFFFF;">
       <htm:tr>
               <htm:td width="9"><htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
                                 <htm:td styleClass="group_title" >&nbsp; <t:outputLabel value="#{resourcesBundle.transferArrowTitle}" styleClass="lable01"/> </htm:td>
                                </htm:tr>
                                 <htm:tr>
                                <htm:td colspan="2" height="1"><htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td>
                                 </htm:tr>
                   </htm:table>
    </t:panelGroup>
    
    
    
    <h:outputText  value="#{resourcesBundle.fromCombo}" styleClass="lable01"/>
    <t:panelGroup>
   <t:selectOneMenu forceId="true" id="fromBudgetType" value="#{pageBeanName.bgtTypesDTOValue}" disabled="#{pageBeanName.srchAgainBtn}"  styleClass="textboxmedium">
                <f:selectItem itemLabel="#{resourcesBundle.defaultValue}" itemValue="#{ pageBeanName.virtualConstValue}" />
                <t:selectItems value="#{pageBeanName.frmBudgetType}" itemLabel="#{BgtTypesDTO.name}" itemValue="#{BgtTypesDTO.code.key}" var="BgtTypesDTO" />                
                  <a4j:support event="onchange" action="#{pageBeanName.fromBudgetTypeAction}" reRender="employeeMainDataPanel,searchButtonGroup, searchButtonGroup" />
    </t:selectOneMenu>    
    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{!pageBeanName.srchAgainBtn}" />     
    <c2:compareValidator componentToValidate="fromBudgetType" componentToCompare="virtualvalueId"  operator="not" errorMessage="#{globalResources.missingField}"   highlight="false"  display="dynamic" active="#{budgetTypeList.searchNotDone}"/>
    </t:panelGroup>
   
   

    
    <h:outputText  value="#{resourcesBundle.toCombo}" styleClass="lable01"/>
    <t:panelGroup>
    <t:selectOneMenu id="toBudgetType" forceId="true" value="#{pageBeanName.toBudgetTypeValue}"   styleClass="textboxmedium" >
                <f:selectItem itemLabel="#{resourcesBundle.defaultValue}" itemValue="#{pageBeanName.defaultValue}" />
                <t:selectItems value="#{pageBeanName.toBudgetType}" itemLabel="#{BgtTypesDTO.name}" itemValue="#{BgtTypesDTO.code.key}" var="BgtTypesDTO" />
    </t:selectOneMenu>
    <h:outputText value="*" styleClass="mandatoryAsterisk" />    
    <c2:compareValidator componentToValidate="toBudgetType" componentToCompare="virtualvalueId"  operator="not" errorMessage="#{globalResources.missingField}"   highlight="false"  display="dynamic" active="#{!budgetTypeList.searchNotDone}"/>
    </t:panelGroup>
    
    
    <h:outputText  value="#{resourcesBundle.transeferDate}" styleClass="lable01"/>
    <t:panelGroup colspan="4">
    <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg"  popupDateFormat="dd/MM/yyyy"  onblur="foucsAfterDate();"                                                                                                                         
                    forceId="true" id="frmdate"  size="25" maxlength="250" styleClass="textboxmedium" currentDayCellClass="currentDayCell"                                                                                                                                                                                          
                    value="#{pageBeanName.pageDTO.applyDate}" renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"                                                                                                                                                                                          
                    renderPopupButtonAsImage="true" onchange="return validateInputCalenderFormate('frmdate','null','null')" 
                    title="#{globalResources.inputCalendarHelpText}">    
           <f:converter converterId="SqlDateConverter"/>
    </t:inputCalendar> 
                         
                         <h:outputText value="*" styleClass="mandatoryAsterisk" />
        <c2:requiredFieldValidator componentToValidate="frmdate" errorMessage="#{globalResources.missingField}" display="dynamic" />        
        </t:panelGroup>
        

    

</t:panelGrid>

   <t:panelGroup  id="searchButtonGroup"  forceId="true" colspan="4">
    <t:panelGrid styleClass="buttonMiddle" columns="2"  align="center">
         <t:commandButton forceId="true"  type="button" onblur="foucsFirstElemenyOnList();" disabled="#{pageBeanName.bgtTypesDTOValue== pageBeanName.virtualConstValue}" rendered="#{!pageBeanName.srchAgainBtn}"  onclick="searchLines();" styleClass="cssButtonSmall" id="searchfromCombo" value="#{resourcesBundle.srchBudgetBtn}"/>
         <a4j:jsFunction name="searchLines" action="#{pageBeanName.searchAction}"  reRender="dataT_data_panel,bottomGroup,executeButton,comboGroup,dateGroup,employeeMainDataPanel,paging_panel,searchButtonGroup" />
         <a4j:commandButton action="#{pageBeanName.searchAgain}" rendered="#{pageBeanName.srchAgainBtn}" reRender="employeeMainDataPanel,searchButtonGroup,dataT_data_panel,executeButton" value="#{resourcesBundle.reSrchBudgetBtn}" styleClass="cssButtonSmall" />
         
    </t:panelGrid>
   </t:panelGroup>


<f:verbatim>
    <script type="text/javascript"> 
        foucsFirstElemenyOnList();
        function foucsFirstElemenyOnList(){        
            if(document.getElementById('work_center_combo') != null){
                document.getElementById('work_center_combo').focus();
                document.getElementById('work_center_combo').focus();
            }
        }
        
        function foucsAfterDate(){        
            if(document.getElementById('searchfromCombo') != null){                
                if(document.getElementById('fromBudgetType') != null && document.getElementById('virtualvalueId') != null){                    
                    var virtualValue = document.getElementById('virtualvalueId').value;                    
                    var currentValue = document.getElementById('fromBudgetType').value;
                    if(virtualValue != currentValue){
                        document.getElementById('searchfromCombo').focus();
                    }else {                        
                        foucsFirstElemenyOnList();
                    }
                }
            }else {
                foucsFirstElemenyOnList();
            }
        }
    </script>
</f:verbatim>
