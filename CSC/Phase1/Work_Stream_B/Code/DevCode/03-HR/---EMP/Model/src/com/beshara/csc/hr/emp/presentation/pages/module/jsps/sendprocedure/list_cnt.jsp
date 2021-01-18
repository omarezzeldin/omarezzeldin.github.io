<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.highlightedDTOS}"/>
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
<t:saveState value="#{pageBeanName.myDataTable}"/>
<t:inputHidden value="#{pageBeanName.selectedListSize}"/>
<t:messages/>
<t:panelGroup forceId="true" id="dataT_data_panel">
<t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}" forceIdIndexFormula="#{list.code.key}"
             rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
             footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
             width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
  <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
    <f:facet name="header">
      <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
        <f:attribute name="checkAll" value="true"/>
        <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
        <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}" oncomplete="setAll('checkAll', 'chk');" reRender="OperationBar"/>
      </t:selectBooleanCheckbox>
    </f:facet>
    <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
      <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}" oncomplete="checkCheckAll('chk');" reRender="OperationBar"/>
    </t:selectBooleanCheckbox>
  </t:column>
  <t:column id="citizensResidentsDTO-code_column" width="15%">
    <f:facet name="header">
      <%--t:commandSortHeader id="codeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
        <f:facet name="ascending">
          <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <f:facet name="descending">
          <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <h:outputText id="header_code" value="#{resourcesBundle.civilid}"/>
      </t:commandSortHeader--%>
        <t:commandLink id="sort_citizensResidentsDTO-code" forceId="true" styleClass="headerLink" value="#{resourcesBundle.civilid}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <h:outputText id="content_code" value="#{list.citizensResidentsDTO.code.key}"/>
  </t:column>
  <t:column id="citizensResidentsDTO-fullName_column" width="25%">
    <f:facet name="header">
      <%--t:commandSortHeader id="name_sort" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
        <f:facet name="ascending">
          <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <f:facet name="descending">
          <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <h:outputText id="header_name" value="#{resourcesBundle.name}"/>
      </t:commandSortHeader--%>
        <t:commandLink id="sort_citizensResidentsDTO-fullName" forceId="true" styleClass="headerLink" value="#{resourcesBundle.employeeName}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <h:outputText id="content_name" value="#{list.citizensResidentsDTO.fullName}"/>
  </t:column>
  <t:column id="cscFileNo_column" width="10%">
    <f:facet name="header">
      <%--t:commandSortHeader id="cscFileNo_sort" columnName="cscFileNo" arrow="false" styleClass="standardTable_Header2" immediate="true">
        <f:facet name="ascending">
          <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <f:facet name="descending">
          <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <h:outputText id="header_validFrom" value="#{resourcesBundle.cscfilno}"/>
      </t:commandSortHeader--%>
        <t:commandLink id="sort_cscFileNo" forceId="true" styleClass="headerLink" value="#{resourcesBundle.cscfilno}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='cscFileNo') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='cscFileNo') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <h:outputText id="content_validFrom" value="#{list.cscFileNo}"/>
  </t:column>
  <t:column id="ministryFileNo_column" width="10%">
    <f:facet name="header">
      <%--t:commandSortHeader id="ministryFileNo_sort" columnName="ministryFileNo" arrow="false" styleClass="standardTable_Header2" immediate="true">
        <f:facet name="ascending">
          <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <f:facet name="descending">
          <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <h:outputText id="header_validTo" value="#{resourcesBundle.ministryFileNo}"/>
      </t:commandSortHeader--%>
        <t:commandLink id="sort_ministryFileNo" forceId="true" styleClass="headerLink" value="#{resourcesBundle.ministryFileNo}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministryFileNo') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministryFileNo') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <h:outputText id="content_validTo" value="#{list.ministryFileNo}"/>
  </t:column>
  <t:column id="hireDate_column" width="20%">
    <f:facet name="header">
      <%--t:commandSortHeader id="hireDate_sort" columnName="hireDate" arrow="false" styleClass="standardTable_Header2" immediate="true">
        <f:facet name="ascending">
          <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <f:facet name="descending">
          <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <h:outputText id="header_email" value="#{resourcesBundle.hireDate}"/>
      </t:commandSortHeader--%>
        <t:commandLink id="sort_hireDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.hireDate}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <h:outputText id="content_email" value="#{list.hireDate}" converter="SqlDateConverter"/>
  </t:column>
  <t:column id="documentsStatus_column" width="8%">
    <f:facet name="header">
      <%--t:commandSortHeader id="document_type_status_sort" columnName="document_type_status" arrow="false" styleClass="standardTable_Header2" immediate="true">
        <f:facet name="ascending">
          <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <f:facet name="descending">
          <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
      <h:outputText value="#{resourcesBundle.document_type_status}" />
      </t:commandSortHeader--%>
        <t:commandLink id="sort_documentsStatus" forceId="true" styleClass="headerLink" value="#{resourcesBundle.document_type_status}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='documentsStatus') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='documentsStatus') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <t:graphicImage border="0" onmouseover="doTooltip(event,'#{resourcesBundle.completed}') " onmouseout="hideTip()" url="/module/media/images/ico_Green.gif" width="10" height="10" rendered="#{list.documentsStatus == true }"/>
    <t:graphicImage border="0" onmouseover="doTooltip(event,'#{resourcesBundle.not_completed}') " onmouseout="hideTip()" url="/module/media/images/ico_Red.gif" width="10" height="10" rendered="#{list.documentsStatus == false }"/>
    <h:inputHidden value="#{list.documentsStatus}"/>
  </t:column>
  <t:column id="proceduresStatus_column" width="8%">
    <f:facet name="header">
      <%--t:commandSortHeader id="proceduresStatus_sort" columnName="proceduresStatus" arrow="false" styleClass="standardTable_Header2" immediate="true">
        <f:facet name="ascending">
          <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
        <f:facet name="descending">
          <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
        </f:facet>
      <h:outputText value="#{resourcesBundle.result}"/>
      </t:commandSortHeader--%>
        <t:commandLink id="sort_proceduresStatus" forceId="true" styleClass="headerLink" value="#{resourcesBundle.result}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='proceduresStatus') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='proceduresStatus') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
     <t:graphicImage border="0" onmouseover="doTooltip(event,'#{resourcesBundle.finished}') " onmouseout="hideTip()" url="/module/media/images/ico_Green.gif" width="10" height="10" rendered="#{list.proceduresStatus == true }"/>
    <t:graphicImage border="0" onmouseover="doTooltip(event,'#{resourcesBundle.not_finished}') " onmouseout="hideTip()" url="/module/media/images/ico_Red.gif" width="10" height="10" rendered="#{list.proceduresStatus == false }"/>
    
    <h:inputHidden value="#{list.proceduresStatus}"/>
  </t:column>
</t:dataTable>
<h:panelGrid columns="1" rendered="#{pageBeanName.listSize == 0 }">
  <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
</h:panelGrid>
</t:panelGroup>

<f:verbatim>
    <script type="text/javascript"> 
        foucsAddbuttonOnList();
        function foucsAddbuttonOnList(){        
            if(document.getElementById('searchButton') != null){            
                document.getElementById('searchButton').focus();
            }
        }
    </script>
</f:verbatim>
