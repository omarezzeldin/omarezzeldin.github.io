<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style> #customDiv1.divSearch { left: 180px; width: 536px !important; }</htm:style>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
<t:panelGroup forceId="true" id="divEditLookup2">
    <t:outputText value="#{resourcesBundle.EmpIndebtMsg}" id="EmpIndebtId" styleClass="msg warning"/>
    <htm:br/>
    <htm:br/>
</t:panelGroup>
<t:panelGrid columns="3" border="0" align="center" forceId="true" id="savePnl">
    <t:commandButton forceId="true" action="#{pageBeanName.doFinishSteps}" id="backButtonCustomDiv2"
                     styleClass="cssButtonSmall" value="#{globalResources.ok}"/>
</t:panelGrid>