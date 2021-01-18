<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel">
        <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}"
                        status="notdefined" reRender="divDeleteAlert,OperationBar,divEditLookup"
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
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                     sortAscending="#{pageBeanName.ascending}">
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{!pageBeanName.singleSelection}">
                <f:facet name="header">
                    <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                        <f:attribute name="checkAll" value="true"/>
                        <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                        <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"
                                     oncomplete="setAll('checkAll', 'chk');"
                                     reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                    </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                                 oncomplete="checkCheckAll('chk');"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectBooleanCheckbox>
            </t:column>
            <t:column id="radio_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{pageBeanName.singleSelection}">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="code_column" sortable="false" width="8%">
                <f:facet name="header">
                    <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="27%">
                <f:facet name="header">
                    <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:inputText id="content_name" value="#{list.name}" readonly="true" styleClass="inputTextForDataTable"/>
            </t:column>
            <t:column id="ministry_column" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_ministriesDTO-name" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.ministry}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministriesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministriesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:inputText id="ministry_name" value="#{list.ministriesDTO.name}" readonly="true"
                             styleClass="inputTextForDataTable"/>
            </t:column>
            <t:column id="status_column" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_status" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.the_status}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:graphicImage value="/app/media/images/green-circle.gif" rendered="#{list.status==1}"
                                onmouseover="doTooltip(event,'#{resourcesBundle.active_status}')" onmouseout="hideTip()"
                                border="0"/>
                <t:graphicImage value="/app/media/images/red-circle.gif" rendered="#{list.status==0}"
                                onmouseover="doTooltip(event,'#{resourcesBundle.inactiveStatus}')"
                                onmouseout="hideTip()" border="0"/>
                <h:inputHidden id="content_status" value="#{list.status}"/>
            </t:column>
            <t:column id="fromDate_column" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_fromDate" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.fromDate}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_from_date" value="#{list.fromDate}">
                    <f:converter converterId="SqlDateConverter"/>
                </h:outputText>
            </t:column>
            <t:column id="untilDate_column" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_untilDate" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.untilDate}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_until_date" value="#{list.untilDate}">
                    <f:converter converterId="SqlDateConverter"/>
                </h:outputText>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}"
                       binding="#{pageBeanName.reloadField}"/>
    </t:panelGroup>
</t:panelGrid>
<!-- added by nora to enable single selection -->
<t:saveState value="#{pageBeanName.singleSelection}"/>
<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.highlightedDTOS}"/>
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.sortAscending}"/>
<!-- Start Paging -->
<t:saveState value="#{pageBeanName.currentPageIndex}"/>
<t:saveState value="#{pageBeanName.oldPageIndex}"/>
<t:saveState value="#{pageBeanName.sorting}"/>
<t:saveState value="#{pageBeanName.usingPaging}"/>
<t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
<t:saveState value="#{pageBeanName.resettedPageIndex}"/>
<t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
<t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
<!-- End Paging -->
<script type="text/javascript">
  foucsAddbuttonOnList();

  function foucsAddbuttonOnList() {
      if (document.getElementById('addButton') != null) {
          document.getElementById('addButton').focus();
      }
  }

  function FireButton(buttonId) {
      var button = document.getElementById(buttonId);
      button.click();

  }
</script>
