<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<h:panelGrid columns="5" id="navigationpanel">
    <t:commandButton value="#{globalResources.PreviousButton}" tabindex="25" action="#{pageBeanName.previous}"
                     disabled="#{pageBeanName.previousNavigationCase==null}" onclick="#{pageBeanName.currentStepJSValidation}" styleClass="cssButtonSmall"
                      rendered="#{pageBeanName.showPrevious}"
                     id="PreviousButtonManyToMany" forceId="true"/>
    <t:commandButton value="#{globalResources.NextButton}" action="#{pageBeanName.next}"
                     disabled="#{pageBeanName.nextNavigationCase==null || !mainDataCandidateBean.civilExist}"
                     styleClass="cssButtonSmall" onclick="#{pageBeanName.currentStepJSValidation}" id="NextButtonManyToMany"
                     tabindex="26" forceId="true"/>
    <t:commandButton value="#{globalResources.SaveButton}" action="#{pageBeanName.finish}" tabindex="27"
                     styleClass="cssButtonSmall" disabled="#{!mainDataCandidateBean.civilExist}"
                     onclick="#{pageBeanName.currentStepJSValidation}" id="FinishButtonManyToMany" forceId="true"
                     rendered="#{pageBeanName.maintainMode!=2}"/>
    <t:commandButton forceId="true" id="BackButtonManyToMany" value="#{globalResources.back}"
                     action="#{pageBeanName.goBack}" tabindex="28" styleClass="cssButtonSmall"
                      onkeydown="onKeyDownLastElement(event,'','')"
                     onclick="ignoremyFormValidation();" immediate="true"/>
</h:panelGrid>
<t:inputHidden value="#{detailBeanName.selectedAvailableListSize}" forceId="true" id="selectedAvailableListSize"/>

<script type="text/javascript">
    A4J.AJAX.onError = function(req, status, message){
        return;
    }
    
</script>