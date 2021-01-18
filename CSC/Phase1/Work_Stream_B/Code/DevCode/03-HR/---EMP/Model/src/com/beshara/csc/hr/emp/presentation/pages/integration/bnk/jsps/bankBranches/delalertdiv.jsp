<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%><%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<htm:style>
.delDivScroll {
    height: 100px;
    }
</htm:style>
<t:messages showDetail="true" id="msgShowId" forceId="true"/>
<t:panelGrid columns="1" width="100%">
     <t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll" style="display:block;width: 100%;overflow:auto;">
        <t:dataTable id="dataT_Delete" var="list" value="#{pageBeanName.selectedDTOS}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"

                styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"

             
                columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top" rowIndexVar="index" dir="rtl">
            <t:column width="25%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="code" value="#{globalResources.globalCivilId}"/>
                </f:facet>
                <%--<h:outputText id="popup_code" value="#{list.code.key}"/>
                <h:outputText id="lck1" value="#{list.code.keys[1]}" rendered="#{list.code.keys[1]!=null}"/>
                <h:outputText id="lck2" value="#{list.code.key}" rendered="#{list.code.keys[1]==null}"/#{list.civilId}--%>
                <h:outputText id="lck2" value="#{list.civilId}"/>
            </t:column>
            <t:column width="75%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="name" value="#{globalResources.globalName}"/>
                </f:facet>
                <h:outputText id="popup_name" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    
    <t:panelGrid columns="2" align="center">
        <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts --%>
        <t:commandButton forceId="true" id="OkButtonDelAlertDiv"  styleClass="cssButtonSmall" value="#{globalResources.ok}" action="#{pageBeanName.deleteDiv}"/>
        <t:commandButton forceId="true" id="CancelButtonDelAlertDiv"  styleClass="cssButtonSmall" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null); return false;"/>
        <%--h:commandButton id="cancel" type="button" value="#{globalResources.cancel}" onclick="if(isVisibleComponent('delAlert'))hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall"/--%>
    </t:panelGrid>
</t:panelGrid>
