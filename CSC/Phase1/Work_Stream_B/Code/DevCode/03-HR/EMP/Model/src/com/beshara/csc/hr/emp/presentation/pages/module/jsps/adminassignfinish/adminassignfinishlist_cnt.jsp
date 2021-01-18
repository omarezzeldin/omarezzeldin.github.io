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

<t:saveState value="#{pageBeanName.sortAscending}"/>
<t:saveState value="#{pageBeanName.fullColumnName}"/>

<t:inputHidden value="#{pageBeanName.selectedListSize}"/>
<t:messages/>
<a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}" reRender="divDeleteAlert,OperationBar,divEditLookup">
    <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
    <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
    <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
    <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
    <a4j:actionparam name="action" value="pageBeanName.viewEmpDetails"/>
</a4j:jsFunction>
<t:panelGroup forceId="true" id="dataT_data_panel">
<t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}" forceIdIndexFormula="#{list.code.key}"
             rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
             footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
             width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}"
             rowOnDblClick="rowOnDblClickJs('chk',#{index});">
  
  <t:column id="check_column" styleClass="standardTable_Header3" width="5%">  
       <f:facet name="header"/>
       <t:selectOneRadio  id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
          <f:selectItem    itemLabel=" " itemValue="#{list.code.key}" />
          <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" 
                        reRender="OperationBar"/>
       </t:selectOneRadio>
     </t:column>
  
  <t:column id="civilid_column" width="15%">
    <f:facet name="header">      
        <t:commandLink id="sort_employeesDTO-code-key" forceId="true" styleClass="headerLink" value="#{resourcesBundle.civilID}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='employeesDTO-code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='employeesDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <h:outputText id="content_civilid" value="#{list.employeesDTO.code.key}"/>
  </t:column>
  
  <t:column id="fullName_column" width="25%">
    <f:facet name="header">
        <t:commandLink id="sort_employeesDTO-citizensResidentsDTO-fullName" forceId="true" styleClass="headerLink" value="#{resourcesBundle.employeeName}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='employeesDTO-citizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='employeesDTO-citizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <h:outputText id="content_fullName" value="#{list.employeesDTO.citizensResidentsDTO.fullName}"/>
  </t:column> 
  
 <t:column id="request_no_column" width="10%">
    <f:facet name="header">
        <t:commandLink id="sort_code-admassignmentSerial" forceId="true" styleClass="headerLink" value="#{resourcesBundle.request_no}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-admassignmentSerial') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-admassignmentSerial') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <h:outputText id="content_request_no" value="#{list.code.admassignmentSerial}">
        <f:converter converterId="javax.faces.Long"/>
    </h:outputText>
  </t:column> 

  <t:column id="assignment_from_date_column" width="15%">
    <f:facet name="header">
        <t:commandLink id="sort_fromDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.assignment_from_date}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <h:outputText id="content_assignment_from_date" value="#{list.fromDate}" >
        <f:converter converterId="SqlDateConverter" />
    </h:outputText>
  </t:column>
  
  <t:column id="assignment_to_date_column" width="15%">
    <f:facet name="header">
        <t:commandLink id="sort_untilDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.assignment_to_date}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <h:outputText id="content_assignment_to_date" value="#{list.untilDate}" >
        <f:converter converterId="SqlDateConverter" />
    </h:outputText>
  </t:column>
  
  <t:column id="assignment_reason_column" width="15%">
    <f:facet name="header">
        <t:commandLink id="sort_assignReasonsDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.assignment_reason}" actionListener="#{pageBeanName.sortDataTable}">                         
            <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='assignReasonsDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
            <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='assignReasonsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
        </t:commandLink>
    </f:facet>
    <h:outputText id="content_assignment_reason" value="#{list.assignReasonsDTO.name}" />        
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
