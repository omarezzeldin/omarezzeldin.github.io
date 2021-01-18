<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
 <f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="merIntgResources"/>
<%-- edit by m.elsaied (fix issue key : NL-279 - 2. Delete 001.JPG)--%>
<t:panelGroup id="merConfirm">
    <t:panelGroup forceId="true" id="merConfirmPnl">
        <t:panelGrid styleClass="msg" align="center">
            <t:outputText style="width:350px;" value="#{merIntgResources.confirm_merit}" styleClass="msg warning"/>
            <f:verbatim><br/></f:verbatim>
        </t:panelGrid>
    </t:panelGroup>
    <%--<h:outputText value="#{merIntgResources.confirm_merit}" styleClass="msg warning"/>--%>

    <t:panelGrid columns="2" align="center">
        <t:commandButton forceId="true" id="OkButtonIntegrationDiv1"   styleClass="cssButtonSmall" value="#{globalResources.ok}" action="#{pageBeanName.saveMerit}"/>
        <t:commandButton forceId="true" id="backButtonIntegrationDiv1" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.integrationDiv1,null,null); return false;"/>
    </t:panelGrid>
</t:panelGroup>







