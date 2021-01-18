<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="budgetTypeStyle" style="width:100%;height: 150px;" >    
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{pageBeanName.listSize}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
             <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header">
                  <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                    <f:attribute name="checkAll" value="true"/>
                    <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"
                                 oncomplete="setAll('checkAll', 'chk');"
                                 reRender="selectedListX,executeButton"/>
                  </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                  <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                               oncomplete="checkCheckAll('chk');"
                               reRender="selectedListX,executeButton"/>
                </t:selectBooleanCheckbox>
              </t:column>
            <t:column id="code_column" width="20%">
                <f:facet name="header">
                    <%--t:commandSortHeader id="codeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                        </f:facet>
                        <h:outputText id="header_code" value="#{globalResources.Code}"/>
                    </t:commandSortHeader--%>
                    <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            <t:column id="name_column" width="55%">
                <f:facet name="header">
                    <%--t:commandSortHeader id="nameColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                        </f:facet>
                        <h:outputText id="header_name" value="#{globalResources.name}"/>
                    </t:commandSortHeader--%>
                    <t:commandLink id="sort_citizensResidentsDTO-fullName" forceId="true" styleClass="headerLink" value="#{globalResources.name}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                
                <%--h:outputText id="content_name" value="#{list.citizensResidentsDTO.firstName} #{list.citizensResidentsDTO.secondName} #{list.citizensResidentsDTO.thirdName} #{list.citizensResidentsDTO.lastName}"/--%>
                <h:outputText id="content_name" value="#{list.citizensResidentsDTO.fullName}"/>
                
            </t:column>
            
            
            <t:column id="startWorkingDate_column" width="20%">
                <f:facet name="header">
                    <%--t:commandSortHeader id="nameColumnHeader" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                        </f:facet>
                        <h:outputText id="startWorkingDateTitle" value="#{resourcesBundle.hireDate}"/>
                    </t:commandSortHeader--%>
                    <t:commandLink id="sort_startWorkingDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.hireDate}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='startWorkingDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='startWorkingDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                      <h:outputText id="content_startWorkingDate" value="#{list.startWorkingDate}">
                         <f:converter converterId="SqlDateConverter"/>
                      </h:outputText>
            </t:column>
            
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
    
</t:panelGrid>

<t:panelGrid id="executeButton" forceId="true" columns="1">
    <f:verbatim></f:verbatim>
    <t:commandButton  action="#{pageBeanName.executeAction}" onblur="document.getElementById('toBudgetType').focus();"  styleClass="cssButtonSmall" value="#{resourcesBundle.executeBtn}" disabled="#{empty budgetTypeList.selectedDTOS}" onclick="return validatemyForm();"/>
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

