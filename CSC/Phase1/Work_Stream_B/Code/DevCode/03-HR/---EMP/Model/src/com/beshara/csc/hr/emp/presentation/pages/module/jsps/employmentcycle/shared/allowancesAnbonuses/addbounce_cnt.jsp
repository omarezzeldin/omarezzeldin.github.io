<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>.dataT-With-4-row-filter{height: 162px !important;}</htm:style>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="bounse_dataT_data_panel" styleClass="dataT-With-4-row-filter">
        <t:dataTable id="bounse_dataT_data" var="list" rows="#{shared_util.noOfTableRows}" value="#{pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" renderedIfEmpty="false"
                     binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:bounse_dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <%-- <t:column id="code_column" width="5%" rendered="#{pageBeanName.indexArray[0]}" > <f:facet
                 name="header"> <t:commandLink id="sort_code-elmguideCode" forceId="true" styleClass="headerLink"
                 value="#{globalResources.Code}" actionListener="#{pageBeanName.sortDataTable}"> <t:graphicImage
                 value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-elmguideCode') ?
                 '/app/media/images/ascending-arrow.gif' :''}" border="0"/> <t:graphicImage
                 value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-elmguideCode') ?
                 '/app/media/images/descending-arrow.gif' :''}" border="0"/> </t:commandLink> </f:facet> <h:outputText
                 id="content_code" value="#{list.code.elmguideCode}"/> </t:column>--%>
            <t:column id="BenefitRewardPayType_column" rendered="#{pageBeanName.indexArray[1]}" sortable="false"
                      width="55%">
                <f:facet name="header">
                    <t:commandLink id="sort_salElementGuidesDTO-fullName" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.BenefitRewardPayType}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-fullName') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salElementGuidesDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="BenefitRewardPayType_name" value="#{list.salElementGuidesDTO.fullName}"/>
            </t:column>
            <t:column id="elementValue_column" rendered="#{pageBeanName.indexArray[2]}" width="7%">
                <f:facet name="header">
                    <t:commandLink id="sort_elementValue" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.Value}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='elementValue') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='elementValue') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_merEmpBen_elementValue" value="#{list.salElementGuidesDTO.value}"></t:outputText>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" align="center" rendered="#{ pageBeanName.listSize == 0 }">
            <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg msg_no_icon"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}"
                       binding="#{pageBeanName.reloadField}"/>
    </t:panelGroup>
</t:panelGrid>
<%--<t:panelGrid columns="1" border="0" align="center" forceId="true" id="backBtns">
    <t:commandButton forceId="true" id="backButtonAddDiv" value="#{globalResources.back}"
                     action="#{pageBeanName.backAction}" styleClass="cssButtonSmall"/>
</t:panelGrid>--%>
<t:saveState value="#{pageBeanName.myTableData}"/>
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
