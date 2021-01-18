<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
<t:panelGrid columns="2" id="masterdata" rowClasses="row01,row02" width="100%" cellspacing="0" border="0"
             cellpadding="5"
             columnClasses="dataTableColumn1,dataTableColumn12forJob,dataTableColumn2forJob,dataTableColumn4forJob">
    <h:outputText value="#{resourcesBundle.civilid}:" styleClass="lable01"/>
    <t:inputText forceId="true" id="employees_civilId" value="#{pageBeanName.pageDTO.citizensResidentsDTO.code.key}"
                 styleClass="textboxlarge" disabled="true"/>
    <h:outputText value="#{resourcesBundle.candidate_name}" id="cndnme_id1" styleClass="lable01"/>
    <t:inputText forceId="true" id="employees_name" styleClass="textboxlarge"
                 value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}" disabled="true"/>
</t:panelGrid>