<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" >
    <t:panelGroup forceId="true" id="dataT_data_panel" style="width:100%;height:240px;">
        
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
             
             <t:column id="check_column" styleClass="standardTable_Header3" width="5%" rendered="#{!pageBeanName.singleSelection}">
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
               
              <t:column id="radio_column" styleClass="standardTable_Header3" width="5%" rendered="#{pageBeanName.singleSelection}">  
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                   <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
                   <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" 
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
              </t:column>
              
               
                
            <t:column id="code_column" sortable="false"  width="8%" >
                <f:facet name="header">
                     
                    <t:commandLink  id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>

                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            
            <t:column id="experienceDesc_column" sortable="false" width="25%">
                <f:facet name="header">

                    <t:commandLink id="sort_experienceDesc" forceId="true" styleClass="headerLink" value="#{resourcesBundle.external_experience_desc}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='experienceDesc') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='experienceDesc') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="content_name" value="#{list.experienceDesc}"/>
            </t:column>
            
             <t:column id="external_experience_fromDate_column" sortable="false" width="15%">
                <f:facet name="header">

                    <t:commandLink id="sort_fromDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.external_experience_fromDate}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <t:outputText id="content_fromDate" value="#{list.fromDate}" >
                    <f:converter converterId="TimeStampConverter"/>
                </t:outputText>
            </t:column>
            
             <t:column id="external_experience_untilDate_column" sortable="false" width="15%">
                <f:facet name="header">

                    <t:commandLink id="sort_toDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.external_experience_untilDate}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='toDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='toDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <t:outputText id="content_toDate" value="#{list.toDate}" >
                    <f:converter converterId="TimeStampConverter"/>
                </t:outputText>
            </t:column>
             <t:column id="external_experience_revision_by_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:outputText  value="#{resourcesBundle.external_experience_revision_by}"/>
                    <%--
                    <t:commandLink id="sort_toDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.external_experience_revision_by}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='toDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='toDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   --%>
                </f:facet>
                <t:outputText id="content_toDate" value="---" > </t:outputText>
            </t:column>
            
            
               <t:column id="external_experience_revision_date_column" sortable="false" width="10%">
                <f:facet name="header">
                   <t:outputText  value="#{resourcesBundle.external_experience_revision_date}"/>
                   <%--
                    <t:commandLink id="sort_toDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.external_experience_revision_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='toDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='toDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   --%>
                </f:facet>
                <t:outputText id="content_rrr" value="---" > </t:outputText>
            </t:column>
            
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        
    
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

  <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="experiencefromDate,420,90:experienceToDate,420,100:experiencefromDate_edit,420,90:experienceToDate_edit,420,100:experienceFromDate_search,350,90:ToDate_search,350,90" />

<script type="text/javascript"> 
    foucsAddbuttonOnList();
    function foucsAddbuttonOnList(){        
        if(document.getElementById('addButton') != null){            
            document.getElementById('addButton').focus();
        }
    }
</script>
