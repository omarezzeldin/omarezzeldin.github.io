<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:saveState value="#{pageBeanName.viewInNewWindow}"/>
<h:panelGrid columns="5" id="navigationpanel">
    <t:commandButton value="#{globalResources.PreviousButton}" action="#{pageBeanName.previous}"
                     disabled="#{pageBeanName.previousNavigationCase==null}" styleClass="cssButtonSmall"
                     onclick="#{pageBeanName.currentStepJSValidation}" rendered="#{pageBeanName.showPrevious}"
                     id="PreviousButtonManyToMany" forceId="true"/>
    <t:commandButton value="#{globalResources.NextButton}" rendered="#{pageBeanName.nextNavigationCase!=null}"
                     action="#{pageBeanName.next}" styleClass="cssButtonSmall"
                     onclick="#{pageBeanName.currentStepJSValidation}" id="NextButtonManyToMany" forceId="true"/>
    <t:commandButton value="#{pageBeanName.nextNavigationCase==null? globalResources.FinishButton : globalResources.SaveButton}"
                     action="#{pageBeanName.finish}" disabled="#{!pageBeanName.renderFinish}"
                     styleClass="cssButtonSmall" onclick="#{pageBeanName.currentStepJSValidation}"
                     id="FinishButtonManyToMany" forceId="true" rendered="#{pageBeanName.maintainMode!=2}"/>
    <%-- <h:commandButton value="#{globalResources.SaveButton}" action="#{pageBeanName.saveObject}"
         disabled="#{!pageBeanName.renderSave}" styleClass="cssButtonSmall" onclick="return stepValidation();" />--%>
    <%-- modified by m.elsaied --- NL-485 Job Change Request <h:commandButton value="#{globalResources.CancelButton}"
         action="#{pageBeanName.back}" styleClass="cssButtonSmall" onclick="ignoremyFormValidation();" immediate="true"
         />--%>
    <t:commandButton rendered="#{!pageBeanName.viewInNewWindow}" forceId="true" id="BackButtonManyToMany"
                     value="#{globalResources.back}" action="#{pageBeanName.back}"
                     onblur="if(typeof(setFocusAtMyFirstElement)!='undefined'){setFocusAtMyFirstElement()};"
                     styleClass="cssButtonSmall" onclick="ignoremyFormValidation();" immediate="true"/>
</h:panelGrid>
<t:inputHidden value="#{detailBeanName.selectedAvailableListSize}" forceId="true" id="selectedAvailableListSize"/>