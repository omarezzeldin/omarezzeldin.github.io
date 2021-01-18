<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGrid columns="1" width="100%">
<t:panelGrid columns="3"  width="100%">
    <h:outputText value="#{resourcesBundle.delete_div_warning_msg}" styleClass="msg warning"/>
</t:panelGrid>
<t:panelGrid columns="2" align="center">
        <t:commandButton forceId="true" id="OkButtonDelAlertDiv" styleClass="cssButtonSmall"
                         value="#{globalResources.ok}" action="#{detailBeanName.deleteQualificationFromSession}"/>
        <t:commandButton forceId="true" id="CancelButtonDelAlertDiv" styleClass="cssButtonSmall"
                         value="#{globalResources.cancel}"
                         onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null); return false;"/>

   </t:panelGrid>
</t:panelGrid>