<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divEditLookup" style="width:600px">
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error" rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>  
    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg" />
   
    
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">
            <h:outputText value="#{globalResources.Code}" styleClass="lable01"/>                     
            <h:inputText value="#{pageBeanName.selectedPageDTO.code.key}" styleClass="textboxmedium" disabled="true"/>                     
    
             <h:outputText value="#{resourcesBundle.external_experience_desc}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputText onchange="trimBorders(external_experience_desc_edit);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="external_experience_desc_edit" value="#{pageBeanName.selectedPageDTO.experienceDesc}" styleClass="textboxlarge" onblur="setFocusOnlyOnElement('clinces_english_name_edit');"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                     <htm:br/>
                     <c:requiredFieldValidator componentToValidate="external_experience_desc_edit" active="#{externalExperienceListBean.pageMode==2}" display="dynamic"   errorMessage="#{globalResources.missingField}"   highlight="false"  />
                </t:panelGroup>
                
               <h:outputText value="#{resourcesBundle.external_experience_fromDate}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputCalendar  popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.selectedPageDTO.fromDate}" id="experiencefromDate_edit"
                            size="20" maxlength="10" styleClass="textbox" currentDayCellClass="currentDayCell" 
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
                   <f:converter converterId="TimeStampConverter"/>
                 </t:inputCalendar>
                 <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <htm:br/>
                  <c:dateFormatValidator   componentToValidate="experiencefromDate_edit" active="#{externalExperienceListBean.pageMode==2}" errorMessage="#{resourcesBundle.dateFormat}"   highlight="false"  display="dynamic" />
                <c:requiredFieldValidator componentToValidate="experiencefromDate_edit" active="#{externalExperienceListBean.pageMode==2}" display="dynamic"   errorMessage="#{globalResources.missingField}"   highlight="false"  />
                </t:panelGroup>
                
                <h:outputText value="#{resourcesBundle.external_experience_untilDate}" styleClass="lable01"/> 
                <t:panelGroup>
                   <t:inputCalendar  popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.selectedPageDTO.toDate}" id="experienceToDate_edit"
                            size="20" maxlength="10" styleClass="textbox" currentDayCellClass="currentDayCell" 
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
                   <f:converter converterId="TimeStampConverter"/>
                 </t:inputCalendar>
                    <htm:br/>
                 <c:dateFormatValidator   componentToValidate="experienceToDate_edit" active="#{externalExperienceListBean.pageMode==2}" errorMessage="#{resourcesBundle.dateFormat}"   highlight="false"  display="dynamic" />
                  <c:compareDateValidator   componentToValidate="experienceToDate_edit" active="#{externalExperienceListBean.pageMode==2}" componentToCompare="experiencefromDate_edit" operator="afterOrEqual" errorMessage="#{resourcesBundle.external_experience_untilDate_greater_than_or_equal_fromDate}" display="dynamic"/> 
                </t:panelGroup>
                
                
        </h:panelGrid>
        
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}" onclick="return validatemyForm();" />
            <t:commandButton forceId="true" id="CancelButtonEdit" onblur="if(isVisibleComponent('lookupEditDiv')) setFocusOnlyOnElement('external_experience_desc_edit');" styleClass="cssButtonSmall" value="#{globalResources.back}"  onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,'edit_first_inputTxt','myForm:errorEdit');settingFoucsAtListPage();return false;" />
        </t:panelGrid>

</t:panelGroup>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
