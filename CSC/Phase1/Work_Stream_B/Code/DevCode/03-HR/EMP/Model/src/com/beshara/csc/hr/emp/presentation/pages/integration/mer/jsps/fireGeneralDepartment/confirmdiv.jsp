<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGroup forceId="true" id="confirm_panel"  colspan="7">

<t:panelGrid columns="3"  width="100%">
    <h:outputText value="#{integrationBundle2.confirmoverwritefile}" styleClass="msg warning"/>
</t:panelGrid>

    <t:panelGrid columns="3" align="center">
        <t:commandButton forceId="true" 
        id="saveAll" styleClass="cssButtonSmall" onclick="block();"
        value="#{globalResources.ok}" action="#{pageBeanName.createFireGeneralDepartmentFile}"/>
        
        <t:commandButton forceId="true" 
        id="backButtonCustomDiv1" styleClass="cssButtonSmall" action="#{pageBeanName.back}"
        value="#{globalResources.back}" onclick="hideCustomDiv();"/>
    </t:panelGrid>
    
</t:panelGroup>
