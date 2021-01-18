<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
<t:panelGrid columns="3" border="0" align="center">
    <%-- <h:commandButton styleClass="cssButtonSmall" onclick="validatePreExecution();"
         value="#{resourcesBundle.executeBtn}" />--%>
    <t:commandButton type="button" forceId="true" id="preExValidationId" styleClass="cssButtonSmall"
                     value="#{resourcesBundle.executeBtn}"
                     onclick="if(validatemyForm()){validateBeforeExecution();}else{return false;}"/>
    <a4j:jsFunction name="validateBeforeExecution" action="#{pageBeanName.preExecution}"
                    reRender="customDiv1, javaScriptCallerOutputText, divMsg" 
                    oncomplete="javascript:changeVisibilityMsg();" />
    <t:commandButton id="BackButtonManyToMany" forceId="true" action="#{pageBeanName.goBack}"
                     styleClass="cssButtonSmall" value="#{globalResources.back}"
                     onblur="document.getElementById('go_work_date').focus();"/>
</t:panelGrid>