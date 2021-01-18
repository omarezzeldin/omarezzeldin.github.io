<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<loadbundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<loadbundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divEditLookup">
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%" cellpadding="0" cellspacing="0">
            <h:outputText value="#{globalResources.name}"/>
            <t:inputText disabled="true" forceId="true" id="CodeAdd" value="#{pageBeanName.selectedPageDTO.name}"
                         styleClass="textboxlarge"/>
            <h:outputText value="#{resourcesBundle.the_status}"/>
            <t:selectBooleanCheckbox forceId="true" id="viewStatusId" value="#{pageBeanName.viewStatus}"/>
        </h:panelGrid>
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" styleClass="cssButtonSmall"
                             onblur="document.getElementById('CancelButtonEdit').focus();" id="SaveButtonEdit"
                             value="#{globalResources.ok}" action="#{pageBeanName.editHireStage}"/>
            <t:commandButton forceId="true" styleClass="cssButtonSmall" id="CancelButtonEdit"
                             value="#{globalResources.back}"
                             onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,null,null); return false;"/>
        </t:panelGrid>
</t:panelGroup>