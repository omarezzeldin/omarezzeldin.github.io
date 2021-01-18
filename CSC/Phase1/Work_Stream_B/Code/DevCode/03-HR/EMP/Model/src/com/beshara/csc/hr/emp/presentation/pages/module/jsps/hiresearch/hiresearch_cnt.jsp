<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>



<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-3-row-filter">
        <t:dataTable id="dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.civilId}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);" >
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" 
                                    reRender="divDeleteAlert, OperationBar"/>
                </t:selectOneRadio>
            </t:column>
            <%-- ********* End Change Request HR-668 By Ashraf Gaber 5-jan-09 *********--%>
            <t:column id="citizensResidentsDTO-code_column"  width="15%" >
                <f:facet name="header">
                    <t:commandLink id="sort_citizensResidentsDTO-code" forceId="true" styleClass="headerLink"
                                value="#{resourcesBundle.civilid}" actionListener="#{pageBeanName.sortDataTable}">
                                
                        <f:param name="bsnSortingColumnName" value="#{pageBeanName.columnNameHR_EMP_EMPLOYEES_CIVIL_ID}"/>
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_code" value="#{list.citizensResidentsDTO.code.key}"/>
            </t:column>
            <t:column id="citizensResidentsDTO-fullName"  width="30%">
                <f:facet name="header">
                    <t:commandLink id="sort_citizensResidentsDTO-fullName" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.candidate_name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_name" value="#{list.citizensResidentsDTO.fullName}"/>
            </t:column>
            <t:column id="ministryFileNo_column" width="15%">
                <f:facet name="header">
               
                    <t:commandLink id="sort_ministryFileNo" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.ministryFileNo}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                                   <f:param name="bsnSortingColumnName" value="#{pageBeanName.columnNameHR_EMP_EMPLOYEES_MINISTRY_FILE_NO}"/>
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministryFileNo') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministryFileNo') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_ministryFileNo" value="#{list.ministryFileNo}"/>
            </t:column>
            <t:column id="hireDate_column"  width="15%">
                <f:facet name="header">
                    <%-- t:commandSortHeader id="hireDateColumn" columnName="hireDate" arrow="false"
                         styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending"> <t:graphicImage
                         id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                         </f:facet> <f:facet name="descending"> <t:graphicImage id="descendingArrow"
                         value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                         <h:outputText id="header_hireDate" value="#{resourcesBundle.hireDate}"/> </t:commandSortHeader--%>
                    <t:commandLink id="sort_hireDate" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.hireDate}" actionListener="#{pageBeanName.sortDataTable}">
                                   <f:param name="bsnSortingColumnName" value="#{pageBeanName.columnNameHR_EMP_EMPLOYEES_HIRE_DATE}"/>
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hireDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_hireDate" value="#{list.hireDate}">
                    <f:converter converterId="SqlDateConverter"/>
                </h:outputText>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1"  align="center" rendered="#{ pageBeanName.showEnterSearch}" >
            <h:outputText value="#{resourcesBundle.start_msg}" styleClass="msg"/>
        </t:panelGrid>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 &&pageBeanName.showEnterSearch==false}" align="center">
            <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}"
                       binding="#{pageBeanName.reloadField}"/>
    </t:panelGroup>
</t:panelGrid>


