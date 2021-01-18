<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<t:panelGroup id="searchArea" forceId="true">
<t:panelGrid columns="2" cellpadding="0" border="0" align="center"  cellspacing="0" width="100%">
    

<t:panelGrid columns="2" rowClasses="row02,row01" cellpadding="3" border="0" align="center" columnClasses="colu1,colu2" cellspacing="0" width="100%">

 <!-- Row1-->
 <t:outputLabel value="#{resourcesBundle.civilid}" styleClass="lable01"/>
 <t:inputText id="search_first_inputTxt" forceId="true" onblur="document.getElementById('emp_Name').focus();" value="#{pageBeanName.searchDTO.civilId}" styleClass="textboxmedium" onkeyup="disableCharacters(this);" maxlength="12" converter="javax.faces.Long"/>
 <!-- Row2-->
 <t:outputLabel value="#{resourcesBundle.employeeName}" styleClass="lable01"/>
 <t:inputText id="emp_Name" forceId="true" value="#{pageBeanName.searchDTO.name}"  styleClass="textboxmedium"/>
 <!-- Row3-->
 <t:outputLabel value="#{resourcesBundle.request_no}" styleClass="lable01"/>
 <t:inputText id="request_no" forceId="true" value="#{pageBeanName.searchDTO.admassignmentSerial}" styleClass="textboxmedium"/>
 <!-- Row4-->
 <t:outputLabel value="#{resourcesBundle.assignment_from_date}" styleClass="lable01"/>
 <t:panelGroup>
     <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="assignment_from_date" value="#{pageBeanName.searchDTO.fromDate}" size="20"
                      maxlength="200" styleClass="textboxmedium" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                      popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
     <f:converter converterId="SqlDateConverter"/> 
     </t:inputCalendar> 
     <f:verbatim> &nbsp; &nbsp; </f:verbatim>
     <c2:dateFormatValidator highlight="false" componentToValidate="assignment_from_date" display="dynamic" errorMessage="#{resourcesBundle.dateFormat}"/>
 </t:panelGroup>
 
 <!-- Row5 -->
 <t:outputLabel value="#{resourcesBundle.assignment_to_date}" styleClass="lable01"/>
 <t:panelGroup>
     <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="assignment_to_date" value="#{pageBeanName.searchDTO.untilDate}" size="20"
                      maxlength="200" styleClass="textboxmedium" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                      popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
     <f:converter converterId="SqlDateConverter"/> 
     </t:inputCalendar>
     <f:verbatim> &nbsp; &nbsp; </f:verbatim>
     <c2:dateFormatValidator highlight="false" componentToValidate="assignment_to_date" display="dynamic" errorMessage="#{resourcesBundle.dateFormat}"/>
     <c2:compareDateValidator componentToValidate="assignment_from_date" componentToCompare="assignment_to_date" operator="before" display="dynamic" errorMessage="#{resourcesBundle.startAssignDatebeforeEndAssignDateErrMsg}" highlight="false"/>
 </t:panelGroup>

 <!-- Row6 -->
 <t:outputLabel value="#{resourcesBundle.assignment_reason}" styleClass="lable01"/>
 <t:selectOneMenu id="assignment_reason" value="#{pageBeanName.searchDTO.assreasonCode}" styleClass="textboxmedium" forceId="true">
  <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue=""/>
  <t:selectItems value="#{pageBeanName.assignReasonsDTOList}" var="assReason" itemValue="#{assReason.code.keys[0]}" itemLabel="#{assReason.name}"/>
  <f:converter converterId="javax.faces.Long"/>
 </t:selectOneMenu>
</t:panelGrid>
<t:panelGroup style="width:5px"/>
</t:panelGrid>
</t:panelGroup>

<f:verbatim>
 <br/>
</f:verbatim>
<f:verbatim> &nbsp; &nbsp; &nbsp; </f:verbatim>
<t:outputText value="#{globalResources.search_info_message}" styleClass="search_info_message" />   
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="assignment_from_date,300,190:assignment_to_date,300,190" />
<t:panelGrid columns="2" align="center" >
 <t:commandButton value="#{globalResources.SearchButton}" action="#{pageBeanName.search}" styleClass="cssButtonSmall" onclick="settingFoucsAtDivSearch();return validatemyForm();"/>
 <t:commandButton forceId="true" id="customSearchBackBtn" onblur="settingFoucsAtDivSearch();" value="#{globalResources.back}" type="button" onclick="hideLookUpDiv(window.blocker,window.divSearch,null,null);settingFoucsAtListPage();" styleClass="cssButtonSmall"/>

</t:panelGrid>
