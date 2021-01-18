<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGroup id="dataT_data_panel" forceId="true" styleClass="dataT-With-1-row-filter">
     <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}" status="notdefined"
                    reRender="divDeleteAlert,OperationBar,divEditLookup"
                    oncomplete="javascript:FireButton('editButton');">
        <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
        <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
        <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
        <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
    </a4j:jsFunction>
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
        <t:column id="check_column" styleClass="standardTable_Header3" width="3%">
            <f:facet  name="header"/>
            
            <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                              onmousedown="toggleRadio(this);">
                <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                             reRender="divDeleteAlert,OperationBar"/>
            </t:selectOneRadio>
        </t:column>
        <t:column id="code_column" width="10%">
            <f:facet name="header">
                <t:commandLink id="sort_code-key" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.civilid}" actionListener="#{pageBeanName.sortDataTable}">
                <f:param name="bsnSortingColumnName" value="#{pageBeanName.sortByCivil}"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-key') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_code" value="#{list.code.key}"/>
        </t:column>
        <t:column id="name_column" width="30%">
            <f:facet name="header">
                <t:commandLink id="sort_fullName" forceId="true" styleClass="headerLink"
                                value="#{resourcesBundle.candidate_name}" actionListener="#{pageBeanName.sortDataTable}">
                    <f:param name="bsnSortingColumnName" value="#{pageBeanName.sortByFullName}"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fullName') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_name" value="#{list.fullName}"/>
        </t:column>
        <t:column id="lastPersonQualification_column" width="42%">
            <f:facet name="header">
            
                <t:outputText value="#{resourcesBundle.qualificationName}" id="lpqc"/>
            </f:facet>
            <h:outputText id="content_lastPersonQualification"
                          value="#{pageBeanName.lastPersonQualification.qualificationsDTO.name}"/>
        </t:column>
        
        
        
        
        <t:column id="specialCaseTypesName_column" sortable="false" width="20%">
            <f:facet name="header">
             
             <t:commandLink id="sort_specialCaseTypesDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.emp_internal_exp_catName}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="#{pageBeanName.sortBySpecName}"/>
                    <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='specialCaseTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                    <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='specialCaseTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
             </t:commandLink>

                
            </f:facet>
            <h:outputText id="spec_name" value="#{list.specialCaseTypesDTO.name}"/>
        </t:column>
         <%--<t:column id="specialcaseType" width="42%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.emp_internal_exp_catName}" id="lpqc"/>
            </f:facet>
            <h:outputText id="spec"
                          value="#{list.specialCaseTypesDTO.name}"/>
        </t:column>--%>
        
        
         <t:column id="specification" width="42%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.orderType}" id="lpqc"/>
            </f:facet>
            <h:outputText id="type1"   rendered="#{list.nonStatus==1}" converter="javax.faces.Long"
                          value="#{resourcesBundle.under_studding}"/>
                          
                           <h:outputText id="type2"  rendered="#{list.nonStatus==2}" converter="javax.faces.Long"
                        value="#{resourcesBundle.approve}"/>
                           <h:outputText id="type3" rendered="#{list.nonStatus==3}" converter="javax.faces.Long"
                          value="#{resourcesBundle.refuse}"/>
        </t:column>
        
       
       
       
    </t:dataTable>
    <h:panelGrid columns="1" rendered="#{pageBeanName.listSize == 0 }">
        <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </h:panelGrid>
</t:panelGroup>