<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-3-row-filter">
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
                     width="100%" align="top" 
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                     sortAscending="#{pageBeanName.ascending}"> <%--rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});"--%>           
             <t:column id="radio_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar,fromDateId"/>
                </t:selectOneRadio>
            </t:column>                                    
            <t:column id="civilId_column" sortable="false" width="12%">
                <f:facet name="header">
                    <t:commandLink id="sort_civilId" forceId="true" styleClass="headerLink" value="#{intgBundle.citizenNo}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <f:param name="bsnSortingColumnName" value="civilId"/>
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='civilId') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='civilId') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_civilId" value="#{list.civilId}"  />
            </t:column>
            
            <t:column id="empname_column" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_kwtCitizensResidentsDTO-fullName" forceId="true" styleClass="headerLink" value="#{intgBundle.employee_name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <f:param name="bsnSortingColumnName" value="salkwtCitizensResidentsEntity.name"/>
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_kwtCitizensResidentsDTO-fullName" value="#{list.kwtCitizensResidentsDTO.fullName}"  />
            </t:column>
            <t:column id="salElementGuidesDTO_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_salElementGuidesDTO-name" forceId="true" styleClass="headerLink" value="#{intgBundle.sal_elm_guide}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <f:param name="bsnSortingColumnName" value="salElementGuidesEntity.emlguideName"/>
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_name" value="#{list.salElementGuidesDTO.name}"  />
            </t:column>
            <t:column id="fromDate_column" sortable="false" width="9%">
                <f:facet name="header">
                    <t:commandLink id="sort_fromDate" forceId="true" styleClass="headerLink" value="#{intgBundle.dedDateFrom}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <f:param name="bsnSortingColumnName" value="fromDate"/>
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="fromDate_name" value="#{list.fromDate}"   converter="SqlDateConverter"/>
            </t:column>
            
            <t:column id="untilDate_column" sortable="false" width="9%">
                <f:facet name="header">
                    <t:commandLink id="sort_untilDate" forceId="true" styleClass="headerLink" value="#{intgBundle.dedDateTo}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <f:param name="bsnSortingColumnName" value="untilDate"/>
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="untilDate_name" value="#{list.untilDate}"   converter="SqlDateConverter"/>
            </t:column>
             <t:column id="code_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_salExceptionType" forceId="true" styleClass="headerLink" value="#{intgBundle.EXCEPTION_TYPE}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <f:param name="bsnSortingColumnName" value="salExceptionType"/>
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salExceptionType') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salExceptionType') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_code" value="#{list.salExceptionType == 1 ? intgBundle.salInsRatiosMaxValue : list.salExceptionType == 2 ? intgBundle.sal_base_actual : ''}"/>
            </t:column>
            <t:column id="exceptionRatio_column" sortable="false" width="8%">
                <f:facet name="header">
                    <t:commandLink id="sort_exceptionRatio" forceId="true" styleClass="headerLink" value="#{intgBundle.emp_vac_percentage}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <f:param name="bsnSortingColumnName" value="exceptionRatio"/>
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='exceptionRatio') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='exceptionRatio') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="exceptionRatio" value="#{list.exceptionRatio}"  />
            </t:column>
            
            <t:column id="salInsExcptReasonsDTO_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_salInsExcptReasonsDTO-name" forceId="true" styleClass="headerLink" value="#{intgBundle.INSEXCREASON_CODE}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <f:param name="bsnSortingColumnName" value="salInsExcptReasonsEntity.insexcreasonName"/>
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salInsExcptReasonsDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salInsExcptReasonsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="salInsExcptReasonsDTO-name" value="#{list.salInsExcptReasonsDTO.name}"  />
            </t:column>
            
            <%--<t:column id="status_column" sortable="false" width="5%">
                <f:facet name="header">
                    <t:commandLink id="sort_status" forceId="true" styleClass="headerLink" value="#{intgBundle.status}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                
                <t:graphicImage id="graph1"  value="/app/media/images/green-circle.gif"   onmouseout="hideTip()" onmouseover="doTooltip(event,' #{intgBundle.valid}')" rendered="#{list.status == 1}"/>
                <t:graphicImage id="graph2"  value="/app/media/images/red-circle.gif"   onmouseout="hideTip()" onmouseover="doTooltip(event,' #{intgBundle.invalid}')" rendered="#{list.status == 0}"/>                    
            </t:column>--%>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
