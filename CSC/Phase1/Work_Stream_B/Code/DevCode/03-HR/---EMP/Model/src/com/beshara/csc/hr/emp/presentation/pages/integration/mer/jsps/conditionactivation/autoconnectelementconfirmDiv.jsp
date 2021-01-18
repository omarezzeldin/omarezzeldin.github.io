<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="merIntgResources"/>
<t:div forceId="true" styleClass="divSearch" id="autoConnectElementDiv" style="margin-right: -130px;">
    <t:div styleClass="popup_header">
        <t:commandButton id="autoConnectElementDivClose" forceId="true" type="button"
                         onclick="hideLookUpDiv(window.blocker,window.autoConnectElementDiv,null,null);" styleClass="close"/>
         
         <t:outputText styleClass="popup_title" value="#{resourcesBundle[title]}"
                      rendered="#{conditionActivationHelperBean.divTitleKey==null}"/>
         
         <t:outputText styleClass="popup_title" value="#{resourcesBundle[conditionActivationHelperBean.divTitleKey]}"
                      rendered="#{conditionActivationHelperBean.divTitleKey!=null}"/>
         
       
    </t:div>
    <t:div styleClass="popup_body">
        <t:div styleClass="popup_body_contents">
        
         <t:outputText styleClass="msg warning" value="#{merIntgResources.AutoConnectElementWithAllMinistriesMsgTitle}"
                      />
                      
      
            <t:panelGrid columns="2" align="center" >
                <t:commandButton forceId="true"  id="autoConnectElementBtn_okay" styleClass="cssButtonSmall"
                                 value="#{globalResources.ok}"  action="#{conditionActivationHelperBean.autoConnectElementWithAllMinistries}" />
                <t:panelGroup>
                    <t:commandButton forceId="true" id="autoConnectElementBtn_cancel" styleClass="cssButtonSmall"
                                     value="#{globalResources.cancel}" type="button"/>
                    <a4j:support action="#{conditionActivationHelperBean.executeConditionActivation}" 
                                 event="onclick" reRender="divMsg,oncompleteBtn,error_panel,OperationBar"
                                 oncomplete="hideLookUpDiv(window.blocker,window.autoConnectElementDiv,null,null);doClick('oncompleteBtn' , '');changeVisibilityMsg();"/>
                </t:panelGroup>
            </t:panelGrid>
            <t:saveState value="#{conditionActivationHelperBean.autoConnectElement}"/>
        </t:div>
    </t:div>
</t:div>