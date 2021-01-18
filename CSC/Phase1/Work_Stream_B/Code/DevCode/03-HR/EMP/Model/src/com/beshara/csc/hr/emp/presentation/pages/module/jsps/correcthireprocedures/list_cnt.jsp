<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%-- <htm:style> #dataT_data_panel { display: block !important; height: 280px !important; overflow: auto !important; }
     </htm:style>--%>
<t:panelGroup forceId="true" id="dataT_data_panel" style="height: 270px">
    <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}"
                    reRender="divDeleteAlert,OperationBar,divEditLookup"
                    oncomplete="javascript:FireButton('editButton');">
        <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
        <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
        <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
        <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
    </a4j:jsFunction>
    <t:dataTable id="dataT_data" var="list"
                 value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                 rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                 forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                 renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                 styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2"
                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                 width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true"
                 sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}"
                 rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});">
        <t:column id="radio_column" styleClass="standardTable_Header3" width="3%">
            <f:facet name="header"/>
            <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                              onkeypress="FireButtonClick('myForm:okBtn')" onmousedown="toggleRadio(this);"
                              onkeydown="toggleRadio(this);">
                <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                             reRender="divDeleteAlert,OperationBar"/>
            </t:selectOneRadio>
        </t:column>
        <t:column id="code_column" sortable="false" width="7%">
            <f:facet name="header">
                <t:commandLink id="sort_code-candidateCode" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.code}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-candidateCode') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-candidateCode') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_code" value="#{list.code.candidateCode}"/>
        </t:column>
         <t:column id="procDesc_column" sortable="false" width="7%">
            <f:facet name="header">
                <t:commandLink id="sort_hireProceduresDTO-name" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.procedure_name}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireProceduresDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireProceduresDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="procDesc" value="#{list.hireProceduresDTO.name}"/>
        </t:column>
         <t:column id="sendDate_column" sortable="false" width="7%">
            <f:facet name="header">
                <t:commandLink id="sort_sendDate" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.transfer_date}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='sendDate') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='sendDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="sendDate" value="#{list.sendDate}"/>
        </t:column>
         <t:column id="resultDate_column" sortable="false" width="7%">
            <f:facet name="header">
                <t:commandLink id="sort_resultDate" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.result_date}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='resultDate') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='resultDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="resultDate" value="#{list.resultDate}"/>
        </t:column>
         <t:column id="procedureResult_column" sortable="false" width="7%">
            <f:facet name="header">
                <t:commandLink id="sort_procedureResultsDTO-name" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.procedure_result}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='procedureResultsDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='procedureResultsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="procedureResult" value="#{list.procedureResultsDTO.name}"/>
        </t:column>
         <t:column id="postponeReason_column" sortable="false" width="7%">
            <f:facet name="header">
                <t:commandLink id="sort_empReasonDataDTO-name" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.postpone_reason}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='empReasonDataDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='empReasonDataDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="postponeReason" value="#{list.empReasonDataDTO.name}"/>
        </t:column>
         <t:column id="postponeDate_column" sortable="false" width="7%">
            <f:facet name="header">
                <t:commandLink id="sort_postponeDate" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.postpone_date}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='postponeDate') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='postponeDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="postponeDate" value="#{list.postponeDate}"/>
        </t:column>
    </t:dataTable>
    <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </t:panelGrid>
</t:panelGroup>
<script type="text/javascript">
  foucsDataTable();

  function foucsDataTable() {
      if (document.getElementById('dataT_data') != null) {
          document.getElementById('dataT_data').focus();
      }
  }
</script>
