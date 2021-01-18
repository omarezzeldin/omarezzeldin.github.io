<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="relatedListDiv" 
                  style="display:block;width: 100%;overflow:auto;">
        <center>
            <h:outputText value="#{resourcesBundle.related_hir_type_error_msg}" styleClass="msg warning"/>
        </center>
        <t:dataTable id="dataT_relatedList" var="list" value="#{pageBeanName.relatedList}" preserveDataModel="false"
                     renderedIfEmpty="false" footerClass="grid-footer" sortable="false" styleClass="grid-footer"
                     headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="top" rowIndexVar="index" dir="rtl">
            <t:column width="25%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="header_code" value="#{globalResources.Code}"/>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            <t:column width="75%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="header_name" value="#{globalResources.name}"/>
                </f:facet>
                <h:outputText id="popup_name" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <t:panelGrid columns="1" align="center">
        <t:commandButton forceId="true" id="backButtonCustomDiv1" value="#{globalResources.back}"
                         styleClass="cssButtonSmall" onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null);"/>
    </t:panelGrid>
</t:panelGrid>