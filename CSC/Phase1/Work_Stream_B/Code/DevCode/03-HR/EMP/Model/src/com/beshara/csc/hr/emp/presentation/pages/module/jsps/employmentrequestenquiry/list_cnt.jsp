<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
 <htm:style> .dataT-With-3-row-filter {
	width: 890px !important;
}
     </htm:style>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" id="cnPGId">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-3-row-filter">
       
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
         rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
         rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
         rowClasses="standardTable_Row1,standardTable_Row2" style="width: 1040 !important;"
         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" 
         align="top" dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
         
   
        <t:column id="code_column" width="50">
            <f:facet name="header">
                <t:commandLink id="sort_realCivilId" forceId="true" styleClass="headerLink" value="#{resourcesBundle.civilid}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="CIVIL_ID"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='realCivilId') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='realCivilId') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_code" value="#{list.realCivilId}"/>
        </t:column>
        
        <t:column id="name_column" width="170">
            <f:facet name="header">
                <t:commandLink id="sort_fullName" forceId="true" styleClass="headerLink" value="#{resourcesBundle.candidate_name}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="GET_NAME(CIVIL_ID)"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fullName') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_name" value="#{list.fullName}"/>
        </t:column>
        
        <t:column id="hireTypeName_column" width="170">
            <f:facet name="header">
                <t:commandLink id="sort_hireTypeName" forceId="true" styleClass="headerLink" value="#{resourcesBundle.hiretype}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="HT.HIRTYPE_NAME"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireTypeName') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireTypeName') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_hireTypeName" value="#{list.hireTypeName}"/>
        </t:column>
        <t:column id="candMinName_column" width="170">
            <f:facet name="header">
                <t:commandLink id="sort_candMinName" forceId="true" styleClass="headerLink" value="#{resourcesBundle.candidate_min}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="M.MIN_NAME"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='candMinName') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='candMinName') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_candMinName" value="#{list.candMinName}"/>
        </t:column>
        <t:column id="concernedMinName_column" width="100">
            <f:facet name="header">
                <t:commandLink id="sort_concernedMinCode" forceId="true" styleClass="headerLink" value="#{resourcesBundle.concerned_ministry}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="REQ_DEPT"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='concernedMinCode') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='concernedMinCode') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_concernedMinName" value="#{list.concernedMinCode=='0'?resourcesBundle.candidate_min:list.concernedMinCode=='1'?resourcesBundle.selection_min:list.concernedMinCode=='2'?resourcesBundle.order_min:list.concernedMinCode=='3'?resourcesBundle.fatwa_min:list.concernedMinCode=='4'?resourcesBundle.service_min:''}"/>
        </t:column>
        <t:column id="requestArrivalDate_column" width="80">
            <f:facet name="header">
                <t:commandLink id="sort_requestArrivalDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.req_arrival_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="C.TRANS_REQ_DATE"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='requestArrivalDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='requestArrivalDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_requestArrivalDate" value="#{list.requestArrivalDate}"/>
        </t:column>
        <t:column id="hireStageName_column" width="300">
            <f:facet name="header">
                <t:commandLink id="sort_hireStageName" forceId="true" styleClass="headerLink" value="#{resourcesBundle.orderType}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="G.HIRSTAGE_NAME"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireStageName') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireStageName') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_hireStageName" value="#{list.hireStageName}"/>
        </t:column>
    </t:dataTable>
    
    <h:panelGrid columns="1" rendered="#{pageBeanName.listSize == 0 }" align="center">
        <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </h:panelGrid>
    </t:panelGroup>
</t:panelGrid>