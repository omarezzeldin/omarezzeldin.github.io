<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>


<h:panelGrid columns="5" id="navigationpanel">
    <t:commandButton id="PreviousButtonManyToMany" forceId="true" tabindex="29" value="#{globalResources.PreviousButton}"
                     action="#{pageBeanName.previous}" disabled="#{pageBeanName.previousNavigationCase==null}"
                     styleClass="cssButtonSmall" onclick="#{pageBeanName.currentStepJSValidation}"
                     rendered="#{pageBeanName.showPrevious}"/>
    <t:commandButton id="NextButtonManyToMany" forceId="true" tabindex="30" value="#{globalResources.NextButton}"
                     action="#{pageBeanName.next}" disabled="#{pageBeanName.nextNavigationCase==null}"
                     styleClass="cssButtonSmall" onclick="#{pageBeanName.currentStepJSValidation}"/>
    <t:commandButton id="FinishButtonManyToMany" forceId="true" tabindex="31" value="#{globalResources.SaveButton}"
                     action="#{pageBeanName.finish}" styleClass="cssButtonSmall" onclick="#{pageBeanName.currentStepJSValidation}"/>
    <t:commandButton forceId="true" tabindex="32" id="BackButtonManyToMany"  value="#{globalResources.back}"
                     action="#{pageBeanName.back}"
                    onkeydown="onKeyDownLastElement(event,'','')"
                     styleClass="cssButtonSmall" onclick="ignoremyFormValidation();" immediate="true"/>
</h:panelGrid>
