<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.csc.hr.crs.presentation.resources.crs" var="resourcesBundle"/>
<t:panelGroup forceId="true" id="divAddLookup">
<t:saveState value="#{pageBeanName.selectedCategory}"/>
 <h:outputText id="clientErrorMessage" styleClass="errMsg"/>
 <h:panelGrid columns="1"  width="100%">
  <h:panelGrid columns="3" border="0" styleClass="scroller"  width="100%">
   <h:panelGrid columns="2" border="0" rowClasses="row02,row01" cellspacing="0" width="100%" cellpadding="0" columnClasses="col1,col2">
    
    <h:outputText value="#{resourcesBundle.cancelNominationBookNo}:" styleClass="lable01"/>
    <h:panelGroup>
        <t:inputText id="RejectBookNoID" forceId="true" value="#{pageBeanName.cndRejLetterNo}" styleClass="textbox" size="50"
                        onblur="setFocusOnlyOnElement('RejectReasonDateID');"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim><br/></f:verbatim>
        <c2:requiredFieldValidator componentToValidate="RejectBookNoID" display="dynamic" errorMessage="#{globalResources.missingField}" active="#{proceedingCandidateListBean.divIndicator == 2}"/>
    </h:panelGroup>
    
    <h:outputText value="#{resourcesBundle.cancelNominationDate}:" styleClass="lable01"/>
  <t:panelGroup>
    <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="RejectReasonDateID"  size="20" maxlength="200" styleClass="textbox"
                                                    title="#{globalResources.inputCalendarHelpText}"
                                                     currentDayCellClass="currentDayCell" value="#{pageBeanName.cndRejDate}" renderAsPopup="true" align="#{globalResources.align}"
                                                     popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                                        <f:converter converterId="SqlDateConverter"/>
                                    </t:inputCalendar>
    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
    <f:verbatim><br/></f:verbatim>
    <c2:dateFormatValidator highlight="false" componentToValidate="RejectReasonDateID" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
    <c2:requiredFieldValidator componentToValidate="RejectReasonDateID" display="dynamic" errorMessage="#{globalResources.missingField}" active="#{proceedingCandidateListBean.divIndicator == 2}"/>
  </t:panelGroup>
  
    <h:outputText value="#{resourcesBundle.reject_reason_categories}  :" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu id="reasons_categories_list" value="#{pageBeanName.selectedCategory}" styleClass="DropdownboxMedium" forceId="true">
         <f:selectItem itemLabel="#{resourcesBundle.Item_Select}" itemValue=""/>
         <t:selectItems value="#{pageBeanName.reasons_categories_list}" var="reasonscat" itemValue="#{reasonscat.code.key}" itemLabel="#{reasonscat.name}"/>
        <a4j:support reRender="reasons_list" event="onchange"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
      <f:verbatim><br/></f:verbatim>
      <c2:requiredFieldValidator componentToValidate="reasons_categories_list" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 2}"/>
    </t:panelGroup>
    
    <h:outputText value="#{resourcesBundle.reject_reason}:" styleClass="lable01"/>
    <t:panelGroup>
    <t:selectOneMenu id="reasons_list" styleClass="DropdownboxMedium" value="#{pageBeanName.selectReason}" forceId="true">
     <f:selectItem itemLabel="#{resourcesBundle.Item_Select}" itemValue=""/>
     <t:selectItems value="#{pageBeanName.reasons_list}" var="reasons" itemValue="#{reasons.code.key}" itemLabel="#{reasons.name}"/>
    </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
    <f:verbatim><br/></f:verbatim>
      <c2:requiredFieldValidator componentToValidate="reasons_list" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 2}"/>
    </t:panelGroup>
   </h:panelGrid>
  </h:panelGrid>    
  
  <t:panelGrid columns="2" border="0" align="center">
   <h:commandButton styleClass="cssButtonSmall" id="SaveButton" value="#{globalResources.ok}" action="#{pageBeanName.reject}"
                    onclick="return validatemyForm();"/> 
                      
    <t:commandButton forceId="true" type="button" id="backButtonAddDiv" value="#{globalResources.back}" styleClass="cssButtonSmall"  onblur="if(isVisibleComponent('lookupAddDiv')) setFocusOnlyOnElement('RejectBookNoID');">
   <a4j:support event="onclick" reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel" action="#{pageBeanName.cancelAdd}"  oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error','add');" />           
    </t:commandButton>  
                          
  </t:panelGrid>
 </h:panelGrid>
</t:panelGroup>
