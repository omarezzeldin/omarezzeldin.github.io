<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-1-row-filter">
    <t:dataTable id="dataT_data" forceId="true" var="list"
                 value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                 rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                 forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                 rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});" renderedIfEmpty="false"
                 binding="#{pageBeanName.myDataTable}" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                 footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" width="100%" align="center"
                 dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                 sortAscending="#{pageBeanName.ascending}">
        <t:column id="radio_column" styleClass="standardTable_Header3" width="5%">
            <f:facet name="header"/>
            <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                              onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                             reRender="divEditLookup,OperationBar"/>
            </t:selectOneRadio>
        </t:column>
        <t:column id="ministriesDTO-code_column" width="10%">
            <f:facet name="header">
                <t:commandLink id="sort_ministriesDTO-code" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.code}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministriesDTO-code') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministriesDTO-code') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_code" value="#{list.ministriesDTO.code.key}"/>
        </t:column>
        <t:column id="ministriesDTO-name_column" width="25%">
            <f:facet name="header">
                <t:commandLink id="sort_ministriesDTO-name" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.minisName}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministriesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministriesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_name" value="#{list.ministriesDTO.name}"/>
        </t:column>
        <t:column id="bgtTypeName_column" sortable="false" width="15%">
            <f:facet name="header">
                <t:commandLink id="sort_bgtTypeName" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.budget}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bgtTypeName') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bgtTypeName') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_bgtTypeName" value="#{list.bgtTypeName}"/>
        </t:column>
        <t:column id="fromDate_column" sortable="false" width="15%">
            <f:facet name="header">
                <t:commandLink id="sort_fromDate" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.fromDate}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_fromDate" value="#{list.fromDate}">
                <f:converter converterId="TimeStampConverter"/>
            </h:outputText>
        </t:column>
        <t:column id="untilDate_column" sortable="false" width="15%">
            <f:facet name="header">
                <t:commandLink id="sort_untilDate" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.untilDate}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_untilDate" value="#{list.untilDate}">
                <f:converter converterId="TimeStampConverter"/>
            </h:outputText>
        </t:column>
        <t:column id="status_column" width="10%">
            <f:facet name="header">
              <t:commandLink id="status" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.active_status}"  actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <t:graphicImage value="/app/media/images/DataGrid_Icon_green_flag.gif" 
                            rendered="#{list.status == 1}" border="0"/>
            <t:graphicImage value="/app/media/images/DataGrid_Icon_red_flag.gif"
                                rendered="#{list.status == 0 }" border="0"/>
                            
                
        </t:column>
    </t:dataTable>
    <h:panelGrid columns="1" rendered="#{pageBeanName.listSize == 0 }" styleClass="msg">
        <h:outputText value="#{globalResources.global_noTableResults}"/>
    </h:panelGrid>
</t:panelGroup>
<f:verbatim>
    <script type="text/javascript">
      setFocusAtMyFirstElement();

      function setFocusAtMyFirstElement() {
          if (document.getElementById('addButton') != null) {
              document.getElementById('addButton').focus();
          }
      }
    </script>
</f:verbatim>