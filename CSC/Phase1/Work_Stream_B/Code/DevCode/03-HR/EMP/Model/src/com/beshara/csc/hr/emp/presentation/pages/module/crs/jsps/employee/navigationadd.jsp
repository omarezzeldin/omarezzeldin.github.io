<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGrid columns="3" id="navigationpanel" align="center" width="15%">
   <t:commandButton value="#{globalResources.FinishButton}" action="#{pageBeanName.finish}"  styleClass="cssButtonSmall"    onclick="return stepValidation();"/>
   <t:commandButton  forceId="true" id="BackButtonManyToMany" value="#{globalResources.back}" action="#{pageBeanName.back}" onblur="if(typeof(setFocusAtMyFirstElement)!='undefined'){setFocusAtMyFirstElement()};" styleClass="cssButtonSmall" onclick="ignoremyFormValidation();"  />
</t:panelGrid>
