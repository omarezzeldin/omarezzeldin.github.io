<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
<%-- edit by m.elsaied (fix issue key : NL-279 - 2. Delete 001.JPG)--%>
<t:outputText value="#{grsResources.cancelValAlertCon}" styleClass="successMsg msg" />
<t:panelGrid columns="1" width="100%">
    <t:panelGroup forceId="true" id="divCancelValidityConfirm" styleClass="delDivScroll">
        <t:dataTable id="dataT_CancelValidityConf" var="list" value="#{pageBeanName.cancelValidityStatusDTOS}" preserveDataModel="false"
                     renderedIfEmpty="false" footerClass="grid-footer" sortable="false" styleClass="grid-footer"
                     headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="top" rowIndexVar="index" dir="rtl">
            <t:column sortable="false" width="60%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="name" value="#{globalResources.TableName}"/>
                </f:facet>
                <h:outputText id="popup_name" value="#{list.currentObject.name}"/>
            </t:column>
            <t:column sortable="false" width="40%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="status" value="#{globalResources.Status}"/>
                </f:facet>
                <%-- TODO locking code: we add check for the business exception--%>
                <h:outputText id="popup_status"
                              value="#{(list.databaseException !=null && list.databaseException.message !=null) ? globalexceptions[list.databaseException.message]:(list.businessException !=null && list.businessException.message !=null) ? globalexceptions[list.businessException.message] : globalResources[list.status]}"
                              rendered="#{list.status!='Updated'}" styleClass="errMsg"/>
                <h:outputText id="popup_status2" value="#{globalResources[list.status]}"
                              rendered="#{list.status=='Updated'}" styleClass="sucessMsg2"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <t:panelGrid align="center">
        <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts--%>
        <t:commandButton forceId="true" id="CancelButtonCancelConfirmDiv"
                         styleClass="cssButtonSmall" value="#{globalResources.back}"
                         onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,null,null); return false;"/>
        <%-- h:commandButton id="cancelconfirm" type="button" value="#{globalResources.back}"
             onclick="hideLookUpDiv(window.blocker,window.delConfirm,null,null);" styleClass="cssButtonSmall"/--%>
    </t:panelGrid>
</t:panelGrid>
