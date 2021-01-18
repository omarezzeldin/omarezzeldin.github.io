<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divAddLookup" style="width:600px">

    
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
    <htm:br rendered="#{pageBeanName.success || pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg" />
   
    
        <t:panelGrid columns="2" border="0" rowClasses="row01,row02"  width="100%"  cellpadding="3" cellspacing="0" >
          
                <h:outputText value="#{resourcesBundle.external_experience_desc}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputText onfocus="this.select();"  onchange="trimBorders(external_experience_desc);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="external_experience_desc" value="#{pageBeanName.pageDTO.experienceDesc}" styleClass="textboxlarge" onblur="setFocusOnlyOnElement('experiencefromDate');"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                      <htm:br/>
                   <c:requiredFieldValidator componentToValidate="external_experience_desc" active="#{externalExperienceListBean.pageMode==1}" display="dynamic"   errorMessage="#{globalResources.missingField}"   highlight="false"  />
                </t:panelGroup>
                
                <h:outputText value="#{resourcesBundle.external_experience_fromDate}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputCalendar  popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.pageDTO.fromDate}" id="experiencefromDate"
                            size="20" maxlength="10" styleClass="textbox" currentDayCellClass="currentDayCell" 
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
                   <f:converter converterId="TimeStampConverter"/>
                 </t:inputCalendar>
                 <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <htm:br/>
                <c:dateFormatValidator   componentToValidate="experiencefromDate" active="#{externalExperienceListBean.pageMode==1}" errorMessage="#{resourcesBundle.dateFormat}"   highlight="false"  display="dynamic" />
                <c:requiredFieldValidator componentToValidate="experiencefromDate" active="#{externalExperienceListBean.pageMode==1}" display="dynamic"   errorMessage="#{globalResources.missingField}"   highlight="false"  />
                </t:panelGroup>
                
                <h:outputText value="#{resourcesBundle.external_experience_untilDate}" styleClass="lable01"/> 
                <t:panelGroup>
                   <t:inputCalendar  popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.pageDTO.toDate}" id="experienceToDate"
                            size="20" maxlength="10" styleClass="textbox" currentDayCellClass="currentDayCell" 
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
                   <f:converter converterId="TimeStampConverter"/>
                 </t:inputCalendar>
                    <htm:br/>
                  <c:dateFormatValidator   componentToValidate="experienceToDate" active="#{externalExperienceListBean.pageMode==1}" errorMessage="#{resourcesBundle.dateFormat}"   highlight="false"  display="dynamic" />
                  <c:compareDateValidator   componentToValidate="experienceToDate" active="#{externalExperienceListBean.pageMode==1}" componentToCompare="experiencefromDate" operator="afterOrEqual" errorMessage="#{resourcesBundle.external_experience_untilDate_greater_than_or_equal_fromDate}" display="dynamic"/> 
                </t:panelGroup>
                
                
                
        </t:panelGrid>
        
        

        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.save}" onclick="resetMsgs('successAddLockup','myForm:error');return validatemyForm();"/>            
            <h:panelGroup>
                <t:commandButton forceId="true" id="SaveNewButton" type="button" onclick="resetMsgs('successAddLockup','myForm:error');return validateSaveAndNewClientValidator();" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>                
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup" oncomplete="settingFoucsAtDivAdd();"/>
            </h:panelGroup>
            <h:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" onblur="setFocusOnlyOnElement('external_experience_desc');"  onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');settingFoucsAtListPage(); " reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
            </h:panelGroup>
        </t:panelGrid>
        
    <t:saveState value="#{pageBeanName.success}"/>
</t:panelGroup>