<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:saveState value="#{detailBeanName.currentDisplayDetails}"/>
<t:saveState value="#{detailBeanName.fullColumnName}"/>
<t:saveState value="#{detailBeanName.sortAscending}"/>
<t:saveState value="#{detailBeanName.sorting}"/>
<t:panelGroup forceId="true" id="dataT_data_panel" style="height: 318px;">
    <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false" value="#{detailBeanName.currentDisplayDetails}"
                 rowIndexVar="index" binding="#{detailBeanName.currentDataTable}"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                 styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2"
                 width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                 sortColumn="#{detailBeanName.sortColumn}" sortAscending="#{detailBeanName.ascending}">
        <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"
                                 oncomplete="setAll('checkAll', 'chk2');" reRender="divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}">
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}"
                             oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar"/>
            </t:selectBooleanCheckbox>
        </t:column>
        <t:column id="Id" width="10%">
            <f:facet name="header">
                <t:commandLink id="sort_documentTypeDTO-code-doctypeCode" forceId="true" styleClass="headerLink"
                               value="#{globalResources.Code}" actionListener="#{detailBeanName.sortDataTable}">
                    <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='documentTypeDTO-code-doctypeCode') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='documentTypeDTO-code-doctypeCode') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_name" value="#{list.documentTypesDTO.code.doctypeCode}"/>
        </t:column>
        <t:column id="typeTitle" width="20%" sortable="false">
            <f:facet name="header">
                <t:commandLink id="sort_documentTypeDTO-name" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.emp_documents}"
                               actionListener="#{detailBeanName.sortDataTable}">
                    <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='documentTypeDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='documentTypeDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_tt" value="#{list.documentTypesDTO.name}"/>
        </t:column>
        <t:column id="document_status" width="13%" sortable="false">
            <f:facet name="header">
                <h:outputText value="#{resourcesBundle.document_status}" id="document_document_status"/>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="selectBooleanCheckbox1" value="#{list.docStatusFlag}">
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedDocStatus}" reRender="dataT_data_panel,dataT_data"/>
            </t:selectBooleanCheckbox>
        </t:column>
        <t:column id="attachemnet_status" width="13%">
            <f:facet name="header">
                <h:outputText value="#{resourcesBundle.attachemnet_status}" id="document_attachemnet_status"/>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="attachmentStatusFlag" value="#{list.attachmentStatusFlag}" disabled="#{!list.docStatusFlag}">
                <a4j:support event="onclick"  actionListener="#{detailBeanName.checkAboutAttachmentForCivil}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv); javascript:changeVisibilityMsg();" reRender="attachmentStatusFlag,dataT_data_panel,divMsg"/>                
            </t:selectBooleanCheckbox>
        </t:column>
        
        <t:column id="attachemnet" width="13%">
            <f:facet name="header">
                <h:outputText value="#{resourcesBundle.attachements}" id="document_attachements"/>
            </f:facet>
            <t:commandButton id="SaveButton" forceId="true" value="..." styleClass="cssButtonSmall" action="#{detailBeanName.addIntegrationDocument}" disabled="#{!list.docStatusFlag}" />
        </t:column>
    </t:dataTable>
    <t:panelGrid rendered="#{detailBeanName.currentListSize == 0}" styleClass="msg">
        <h:outputText value="#{globalResources.global_noTableResults}"/>
        <h:outputText value="#{globalResources.global_noTableResults_m2m}"/>
    </t:panelGrid>
</t:panelGroup>