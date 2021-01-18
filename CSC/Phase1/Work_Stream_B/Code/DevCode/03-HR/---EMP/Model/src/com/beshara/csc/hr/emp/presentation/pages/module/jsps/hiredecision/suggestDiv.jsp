<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
            <t:outputText id="Suggest_successMsg" value="#{resourcesBundle.hire_process_suceess_go_Suggest}" styleClass="successMsg msg" />
       
    <t:panelGrid forceId="true" id="navBtnsPnlGrd" columns="3" border="0" align="center">
        <t:commandButton forceId="true"
                         styleClass="cssButtonSmall" id="suggest_ok_Button" value="#{resourcesBundle.yes}"
                         action="#{pageBeanName.goExecuteEmployement}"/>
        <t:commandButton forceId="true"
                         styleClass="cssButtonSmall" id="backButtonCustomDiv2" action="#{pageBeanName.backFromDiv}"
                         value="#{resourcesBundle.no}" onclick ="hideLookUpDiv(window.blocker,window.customDiv2);"/>
    </t:panelGrid>
    