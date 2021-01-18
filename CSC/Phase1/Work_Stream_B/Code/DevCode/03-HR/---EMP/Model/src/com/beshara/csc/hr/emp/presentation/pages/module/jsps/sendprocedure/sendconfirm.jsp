<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGrid columns="1" width="100%">
 <t:panelGroup forceId="true" id="divDeleteConfirm" styleClass="delDivScroll">
  <t:dataTable id="dataT_DeleteConf" var="list" value="#{pageBeanName.deleteStatusDTOS}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
               styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
               columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center" rowIndexVar="index" dir="rtl">
   <t:column sortable="false" width="40%" defaultSorted="true">
    <f:facet name="header">
     <h:outputText value="#{resourcesBundle.name}"/>
    </f:facet>
    <h:outputText value="#{list.currentObject.employeesDTO.citizensResidentsDTO.fullName}"/>
   </t:column>
   <t:column sortable="false" width="35%" defaultSorted="true">
    <f:facet name="header">
     <h:outputText value="#{resourcesBundle.send_confirm_title}"/>
    </f:facet>
    <h:outputText value="#{globalResources[list.status]}" rendered="#{ list.status!='SENDED'}" styleClass="errMsg"/>
    <h:outputText value="#{globalResources[list.status]}" rendered="#{list.status=='SENDED'}" styleClass="sucessMsg"/>
   </t:column>
  </t:dataTable>
 </t:panelGroup>
 <t:panelGrid align="center">
  <t:commandButton forceId="true" id="CancelButtonDelConfirmDiv" onblur="settingFoucsAtDivDeleteConfirm();" action="list_send_procedure" value="#{globalResources.back}" styleClass="cssButtonSmall"/>
 </t:panelGrid>
</t:panelGrid>
