<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<h:panelGrid columns="5" id="navigationpanel">
    <t:commandButton tabindex="17"
                     onkeydown="onKeyDownFirstElement(event,'NextButtonManyToMany','BackButtonManyToMany')"
                     value="#{globalResources.PreviousButton}" action="#{pageBeanName.previous}"
                     disabled="#{pageBeanName.previousNavigationCase==null}" styleClass="cssButtonSmall"
                     onclick="#{pageBeanName.currentStepJSValidation}" rendered="#{pageBeanName.showPrevious}"
                     id="PreviousButtonManyToMany" forceId="true"/>
    <t:commandButton tabindex="18" value="#{globalResources.NextButton}" action="#{pageBeanName.next}"
                     disabled="#{pageBeanName.nextNavigationCase==null}" styleClass="cssButtonSmall"
                     onclick="return validatemyForm();" id="NextButtonManyToMany" forceId="true"/>
    
    <t:commandButton tabindex="19"
                     value="#{pageBeanName.nextNavigationCase==null? globalResources.FinishButton : globalResources.SaveButton}"
                     action="#{pageBeanName.doSave}" 
                     styleClass="cssButtonSmall" onclick="return validatemyForm();"
                     id="FinishButtonManyToMany" forceId="true" />
                     
    <t:commandButton tabindex="20" onkeydown="onKeyDownLastElement(event,'FinishButtonManyToMany','')" forceId="true"
                     id="BackButtonManyToMany" value="#{globalResources.back}" action="#{pageBeanName.back}"
                     onblur="if(typeof(setFocusAtMyFirstElement)!='undefined'){setFocusAtMyFirstElement()};"
                     styleClass="cssButtonSmall" onclick="ignoremyFormValidation();" immediate="true"/>
</h:panelGrid>
<t:inputHidden value="#{detailBeanName.selectedAvailableListSize}" forceId="true" id="selectedAvailableListSize"/>
