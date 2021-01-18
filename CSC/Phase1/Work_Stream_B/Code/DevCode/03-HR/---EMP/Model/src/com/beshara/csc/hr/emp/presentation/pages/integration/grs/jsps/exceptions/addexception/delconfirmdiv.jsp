<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.popup_inner_title{display:none;}

</htm:style>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>

<%-- edit by m.elsaied (fix issue key : NL-279 - 2. Delete 001.JPG)--%>
<t:panelGrid columns="1" width="100%">
    <t:panelGroup forceId="true" id="divDeleteConfirm" styleClass="delDivScroll">
    
       <f:verbatim>
                    <br/>
                    <center>
                   </f:verbatim>

                     <h:outputText id="divTitle" value="#{resourcesBundle.ExceptionResult}" styleClass="lookupDivTitle"/>  
                 <f:verbatim>
                    </center>
                    <br/>
                   
                   </f:verbatim>
            
            
    
        <t:dataTable id="dataT_DeleteConf" var="list" value="#{pageBeanName.deleteStatusDTOS}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top" rowIndexVar="index" dir="rtl">
                  <t:column sortable="false" width="30%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="civilId" value="#{globalResources.globalCivilId}"/>
                </f:facet>
                <h:outputText id="popup_civilId" value="#{list.currentObject.code.key}"/>
            </t:column>
            <t:column sortable="false" width="30%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="name" value="#{globalResources.TableName}"/>
                </f:facet>
                <h:outputText id="popup_name" value="#{list.currentObject.name}"/>
            </t:column>
            <t:column sortable="false" width="40%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="status" value="#{globalResources.Status}"/>
                </f:facet>
                <%-- TODO locking code: we add check for the business exception --%>
                <h:outputText id="popup_status" value="#{(list.databaseException !=null && list.databaseException.message !=null) ? grsIntgResources[list.databaseException.message]:(list.businessException !=null && list.businessException.message !=null) ? grsIntgResources[list.businessException.message] : globalResources[list.status]}" rendered="#{list.status!='Deleted'}" styleClass="errMsg"/>
                <h:outputText id="popup_status2" value="#{globalResources[list.status]}" rendered="#{list.status=='Deleted'}" styleClass="sucessMsg2"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <t:panelGrid align="center">
        <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts --%>
        <t:commandButton forceId="true" id="CancelButtonDelConfirmDiv" onblur="if(isVisibleComponent('delConfirm'))settingFoucsAtDivDeleteConfirm();" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.delConfirm,null,null); return false;"/>
        <%--h:commandButton id="cancelconfirm" type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.delConfirm,null,null);" styleClass="cssButtonSmall"/--%>
    </t:panelGrid>
</t:panelGrid>
