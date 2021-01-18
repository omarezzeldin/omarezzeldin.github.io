<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:saveState value="#{detailBeanName.procedureRowIndex}"/>
<t:saveState value="#{detailBeanName.postponeDate}"/>
<t:saveState value="#{detailBeanName.suspensionReasonCode}"/>
<t:panelGroup forceId="true" id="divEditLookup">
    <t:inputText id="fakeInputForFocus" value="sheka" forceId="true"
                 onfocus="if(isVisibleComponent('lookupEditDiv')){document.getElementById('edit_first_inputTxt').focus();}"
                 styleClass="fakeInputForFocus"/>
    <h:panelGrid columns="1" width="100%">
        <t:panelGrid align="center" columns="2" rowClasses="row02,row01" cellpadding="3" columnClasses="colu1,colu2"
                     cellspacing="0" width="100%">
            <t:outputText value="#{resourcesBundle.name_procedure}" styleClass="divtext"/>
            <t:inputText disabled="true" styleClass="textboxmedium"
                         value="#{detailBeanName.selectedPageDTO.hireProceduresDTO.name}"
                         rendered="#{detailBeanName.selectedPageDTO != null && detailBeanName.selectedPageDTO.hireProceduresDTO != null}"/>
            <t:outputText value="#{resourcesBundle.suspension_reason}" styleClass="divtext"/>
            <t:selectOneMenu forceId="true" id="edit_first_inputTxt" tabindex="1"
                             onkeydown="onKeyDownFirstElement(event,'clndr_postponeDate','CancelButtonEdit')"
                             styleClass="textboxmedium" value="#{detailBeanName.suspensionReasonCode}">
                <t:selectItems value="#{detailBeanName.suspensionReasonsList}" itemLabel="#{item.name}"
                               itemValue="#{item.code.key}" var="item"/>
            </t:selectOneMenu>
            <t:outputText value="#{resourcesBundle.postponeDate}" styleClass="divtext"/>
            <t:panelGroup styleClass="clndr_maintain_regDate12">
                <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" value="#{detailBeanName.postponeDate}" id="clndr_postponeDate"
                                 tabindex="2" size="20" maxlength="#{pageBeanName.calendarTextLength}"
                                 styleClass="textboxmedium" currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                 renderPopupButtonAsImage="true">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:requiredFieldValidator componentToValidate="clndr_postponeDate" display="dynamic" group="123"
                                           errorMessage="#{globalResources.missingField}" highlight="false"
                                           />
                <c2:dateFormatValidator highlight="false" componentToValidate="clndr_postponeDate" group="123" display="dynamic"
                                        errorMessage="#{globalResources.InvalidDataEntryException}"
                                         />
            </t:panelGroup>
        </t:panelGrid>
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" styleClass="cssButtonSmall" id="SaveButtonEdit" tabindex="3"
                             value="#{globalResources.ok}"
                              onclick="if(validatemyForm('123')){saveSelectedReasonJs();return false;}else{return false;}"/>
            <a4j:jsFunction name="saveSelectedReasonJs" action="#{detailBeanName.saveSuspensionReasonCode}"
                            oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,null,null);"/>
            <t:commandButton forceId="true" styleClass="cssButtonSmall" id="CancelButtonEdit" tabindex="4"
                             onkeydown="onKeyDownLastElement(event,'SaveButtonEdit','edit_first_inputTxt')"
                             value="#{resourcesBundle.contract_emp_button_Back}"
                             onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,null,null); return false;"/>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>