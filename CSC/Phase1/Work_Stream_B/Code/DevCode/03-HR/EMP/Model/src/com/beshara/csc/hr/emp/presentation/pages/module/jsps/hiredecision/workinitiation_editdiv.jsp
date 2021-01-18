<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

    <t:panelGroup forceId="true" id="divEditLookup" >
        <h:panelGrid columns="1" width="100%">        
            <t:panelGrid align="center">
                <h:outputText value="#{resourcesBundle.dec_join_initiation_confirmation_msg}" styleClass="msg" rendered="#{pageBeanName.addNewDecision}"/>
                <h:outputText value="#{resourcesBundle.dec_join_initiation_confirmation_msg}" styleClass="msg" rendered="#{!pageBeanName.addNewDecision}"/>
            </t:panelGrid>            
            <t:panelGrid columns="2" border="0" align="center">
               <%-- <t:commandButton forceId="true" styleClass="cssButtonSmall" onblur="document.getElementById('CancelButtonEdit').focus();" id="SaveButtonEdit" value="#{globalResources.ok}" action="#{pageBeanName.goToWorkInitiation}"/>--%>
                <t:commandButton forceId="true" styleClass="cssButtonSmall" onblur="document.getElementById('SaveButtonEdit').focus();" id="CancelButtonEdit" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,null,null); return false;" />
            </t:panelGrid>
        </h:panelGrid>
    </t:panelGroup>