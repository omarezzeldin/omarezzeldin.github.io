<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGrid columns="1" width="100%">
    <t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll"
                  style="display:block;width: 100%;overflow:auto;">
        <t:dataTable id="dataT_Delete" var="list" value="#{pageBeanName.selectedDTOS}" preserveDataModel="false"
                     renderedIfEmpty="false" footerClass="grid-footer" sortable="false" styleClass="grid-footer"
                     headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="top" rowIndexVar="index" dir="rtl">
            <t:column width="25%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="code" value="#{intgBundle.citizenNo}"/>
                </f:facet>
                <h:outputText id="lck1" value="#{list.civilId}" />
            </t:column>
            <t:column width="75%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="name" value="#{intgBundle.employee_name}"/>
                </f:facet>
                <h:outputText id="popup_name" value="#{list.kwtCitizensResidentsDTO.fullName}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <t:panelGrid columns="2" align="center">
        <t:commandButton forceId="true" id="OkButtonDelAlertDiv" styleClass="cssButtonSmall" 
                         value="#{globalResources.ok}" action="#{pageBeanName.checkAndDelete}"/>
        <t:commandButton forceId="true" id="CancelButtonDelAlertDiv" styleClass="cssButtonSmall"
                         value="#{globalResources.cancel}"
                         onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null); return false;"/>
    </t:panelGrid>
</t:panelGrid>