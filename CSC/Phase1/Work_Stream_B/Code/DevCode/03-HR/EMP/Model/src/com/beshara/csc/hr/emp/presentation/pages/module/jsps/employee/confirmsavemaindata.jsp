<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

<t:panelGrid columns="1" width="100%">
    <t:panelGrid align="center" forceId="true" id="saveMainDataAlert" >
        <t:outputText styleClass="msg" value="#{resourcesBundle.employeeSuccesfullyNominated}" />
        <t:outputText styleClass="msg" value="#{resourcesBundle.doYouWantToCompleteData}" />
    </t:panelGrid>
    <t:panelGrid columns="2" align="center">
        <t:commandButton forceId="true" id="yes_complete_data" onblur="document.getElementById('backButtonAddDiv').focus();"  value="#{resourcesBundle.yes}" action="#{detailBeanName.goEdit}" styleClass="cssButtonSmall"/>
        <t:commandButton forceId="true" id="backButtonAddDiv" onblur="document.getElementById('yes_complete_data').focus();"  action="#{detailBeanName.cancelDataComplete}" value="#{resourcesBundle.no}" styleClass="cssButtonSmall"/>
    </t:panelGrid>
</t:panelGrid>
 





