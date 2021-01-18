<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
  <h:outputText value="#{resourcesBundle.are_you_sure_to_return_record}" styleClass="msg warning"/>
</t:panelGrid>
<t:panelGrid columns="2" align="center">
        <t:commandButton forceId="true" 
                        id="okBtnId" styleClass="cssButtonSmall"
                        value="#{globalResources.ok}"
                        action="#{pageBeanName.biznasActions.hireStageCompletingAddNewJobNameAction}"/>
                        
       <t:commandButton forceId="true" id="backButtonCustomDiv2" styleClass="cssButtonSmall"
                         value="#{globalResources.back}"
                         onclick="hideLookUpDiv(window.blocker,window.customDiv2,null,null); return false;"/>
</t:panelGrid>