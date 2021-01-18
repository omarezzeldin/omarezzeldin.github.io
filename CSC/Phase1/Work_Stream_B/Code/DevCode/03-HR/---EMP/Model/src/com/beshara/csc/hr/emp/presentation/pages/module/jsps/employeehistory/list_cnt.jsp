<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" style="height:240px">
        
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.serial}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="center"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
             
             <%--t:column id="check_column" styleClass="standardTable_Header3" width="5%" rendered="#{!pageBeanName.singleSelection}">
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
              </t:column--%>
              
               
                
            <t:column id="serial_column" sortable="false"  rendered="#{pageBeanName.indexArray[0]}" width="5%" >
                <f:facet name="header">
                     
                    <t:commandLink  id="sort_serial" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='serial') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='serial') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>

                </f:facet>
                <h:outputText id="content_code" value="#{list.serial}"/>
            </t:column>
            
            <t:column id="workCentersDTO-ministriesDTO-name_column" rendered="#{pageBeanName.indexArray[1]}"  sortable="false" width="15%">
                <f:facet name="header">

                    <t:commandLink id="sort_workCentersDTO-ministriesDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.ministry}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCentersDTO-ministriesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCentersDTO-ministriesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="content_name"   value="#{list.workCentersDTO.ministriesDTO.name}"/>
            </t:column>
            <t:column id="workCentersDTO-name_column" rendered="#{pageBeanName.indexArray[2]}" sortable="false" width="20%">
                <f:facet name="header">

                    <t:commandLink id="sort_workCentersDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.wrkCenter}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCentersDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCentersDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="workCentersDTO_name" value="#{list.workCentersDTO.name}"/>
            </t:column>
            <t:column id="jobsDTO-name_column" rendered="#{pageBeanName.indexArray[3]}"  sortable="false" width="15%">
                <f:facet name="header">

                    <t:commandLink id="sort_jobsDTO-name" rendered="#{pageBeanName.indexArray[0]}"  forceId="true" styleClass="headerLink" value="#{resourcesBundle.job_name}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobsDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="jobsDTO_name" value="#{list.jobsDTO.name}"/>
            </t:column>
            <t:column id="fromDate_column" rendered="#{pageBeanName.indexArray[4]}"  sortable="false" width="12%">
                <f:facet name="header">

                    <t:commandLink id="sort_fromDate"  forceId="true" styleClass="headerLink" value="#{resourcesBundle.fromDate}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="fromDate" value="#{list.fromDate}">
                    <f:converter converterId="TimeStampConverter"/>
                </h:outputText>
            </t:column>
            <t:column id="untilDate_column" rendered="#{pageBeanName.indexArray[5]}"  sortable="false" width="12%">
                <f:facet name="header">

                    <t:commandLink id="sort_untilDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.untilDate}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="untilDate" value="#{list.untilDate}">
                    <f:converter converterId="TimeStampConverter"/>
                </h:outputText>
            </t:column>
            <t:column id="techJobsDTO-name_column" rendered="#{pageBeanName.indexArray[6]}"  sortable="false" width="15%">
                <f:facet name="header">

                    <t:commandLink id="sort_techJobsDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.employees_special_job}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='techJobsDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='techJobsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="techJobsDTO_name" value="#{list.techJobsDTO.name}"/>
            </t:column>
            
            
            
            
            <t:column id="perFlag_column" rendered="#{pageBeanName.indexArray[7]}"  sortable="false" width="5%">
                <f:facet name="header">
                    <t:commandLink id="sort_perFlag" forceId="true" styleClass="headerLink" value="#{resourcesBundle.PER_Flag}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='perFlag') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='perFlag') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <%--h:outputText id="perFlag" value="#{list.perFlag}"/--%>
                <t:graphicImage id="perFlag" value="#{(list.perFlag == 0) ? '/app/media/images/DataGrid_Icon_not.gif' :'/app/media/images/DataGrid_Icon_ok.gif'}"/>
            </t:column>
            
            <t:column id="pisFlag_column" rendered="#{pageBeanName.indexArray[8]}"  sortable="false" width="5%">
                <f:facet name="header">
                    <t:commandLink id="sort_pisFlag" forceId="true" styleClass="headerLink" value="#{resourcesBundle.PIS_Flag}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='pisFlag') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='pisFlag') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <%--h:outputText id="pisFlag" value="#{list.pisFlag}"/--%>
                <t:graphicImage id="pisFlag" value="#{(list.pisFlag == 0) ? '/app/media/images/DataGrid_Icon_not.gif' :'/app/media/images/DataGrid_Icon_ok.gif'}"/>
            </t:column>
            
            
            
            
            
            
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}" binding="#{pageBeanName.reloadField}"/>
    
    </t:panelGroup>
    
</t:panelGrid>



<script type="text/javascript"> 
    foucsAddbuttonOnList();
    function foucsAddbuttonOnList(){        
        if(document.getElementById('addButton') != null){            
            document.getElementById('addButton').focus();
        }
    }
</script>
