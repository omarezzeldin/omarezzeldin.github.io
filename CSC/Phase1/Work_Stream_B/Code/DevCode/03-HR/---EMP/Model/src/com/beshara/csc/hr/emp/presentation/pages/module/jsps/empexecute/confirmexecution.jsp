<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>.warning{ height:auto !important; display:table !important; padding-left: 45px !important; padding-top: 55px
           !important; margin-top: -20px !important; } #customDiv1.divSearch { left: 180px; width: 536px !important; }</htm:style>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
<t:panelGroup forceId="true" id="divEditLookup2">
    <t:outputText value="#{resourcesBundle.haveFatherRaise}" id="haveFatherRaise"
                  rendered="#{pageBeanName.raiseIndicator == 1}" styleClass="msg warning"/>
    <t:outputText value="#{resourcesBundle.backToInsurance}" id="backToInsurance"
                  rendered="#{pageBeanName.kwuaity == 1}" styleClass="msg warning"/>
    <htm:br/>
    <htm:br/>
</t:panelGroup>
<t:panelGrid columns="3" border="0" align="center" forceId="true" id="aaaaaa">
    <t:commandButton forceId="true" action="#{pageBeanName.executeEmployment}" id="SaveButtonConfirmEdit"
                     styleClass="cssButtonSmall" value="#{globalResources.ok}"/>
    <t:commandButton forceId="true" id="Cancel" styleClass="cssButtonSmall" value="#{globalResources.CancelButton}">
        <a4j:support disableDefault="true" event="onclick" action="#{pageBeanName.unlock}"
                     oncomplete="hideLookUpDiv(window.blocker,window.customDiv1,null,null);settingFoucsAtListPage();return false;"
                     reRender="msgShow,dataT_data_panel"/>
    </t:commandButton>
</t:panelGrid>