<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel">
        <%-- rowOnDblClick="rowOnDblClickJs('chk',#{index});" by Ashraf Gaber
            <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}" reRender="divDeleteAlert,OperationBar,divEditLookup" oncomplete="changeVisibilityDiv(window.blocker,window.lookupEditDiv);">
                <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
                <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
                <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
                <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
                <a4j:actionparam name="action" value="pageBeanName.showEditDiv"/>
            </a4j:jsFunction>
            --%>
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
             
           
              <t:column id="radio_column" styleClass="standardTable_Header3" width="5%" rendered="#{pageBeanName.singleSelection}">  
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                   <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
                   <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" 
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
              </t:column>
              
               
                
            <t:column id="name_column" sortable="false"  width="30%" >
                <f:facet name="header">
                     
                    <t:commandLink  id="sort_ministriesDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.work_min}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministriesDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministriesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>

                </f:facet>
                <h:outputText id="content_code" value="#{list.ministriesDTO.name}"/>
            </t:column>
            
            
                    
            <t:column id="fromDate_column" sortable="false"  width="30%" >
                <f:facet name="header">
                     
                    <t:commandLink  id="sort_code-fromDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.from_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-fromDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-fromDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>

                </f:facet>
                <h:outputText id="fromDate" value="#{list.fromDate}">
                  <f:converter converterId="SqlDateConverter"/>
                  </h:outputText>
            </t:column>
            
            
            <t:column id="untilDate_column" sortable="false" width="30%">
                <f:facet name="header">

                    <t:commandLink id="sort_untilDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.to_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="content_name" value="#{list.untilDate}">
                <f:converter converterId="SqlDateConverter"/>
                </h:outputText>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}" binding="#{pageBeanName.reloadField}"/>
    
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
    function foucsAddbuttonOnList(){        
        if(document.getElementById('addButton') != null){            
            document.getElementById('addButton').focus();
        }
    }
</script>
