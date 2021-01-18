<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGroup id="dataT_data_panel" forceId="true" styleClass="delDivScroll4" style="width:100%">

    <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
        forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
        rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
        rowClasses="standardTable_Row1,standardTable_Row2" 
        columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%"
        align="top" dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
        
        <t:column id="check_column" styleClass="standardTable_Header3" width="5px">
            <f:facet name="header"/>        
            <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);">
                <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" reRender="divDeleteAlert,OperationBar"/>
            </t:selectOneRadio>
        </t:column>
        
        <%-- specify JobName From Dewan --%>
        <t:column id="code_column" width="10%"  rendered="#{proceedingCandidateListBean.fromModuleName=='crs' || (proceedingCandidateListBean.fromModuleName=='emp' && proceedingCandidateListBean.selectedCandType==proceedingCandidateListBean.specifyJobNameFromDewan)}">
            <f:facet name="header">
                <t:commandLink id="sort_jobSeekersDTO-kwtCitizensResidentsDTO-code-key" forceId="true" styleClass="headerLink" value="#{resourcesBundle.civilIdCandidate}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="#{pageBeanName.columnNameHR_EMP_EMPLOYEES_CIVIL_ID}"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobSeekersDTO-kwtCitizensResidentsDTO-code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobSeekersDTO-kwtCitizensResidentsDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_code" value="#{list.jobSeekersDTO.kwtCitizensResidentsDTO.code.key}"/>
        </t:column>
        
        <t:column id="name_column" width="40%"  rendered="#{proceedingCandidateListBean.fromModuleName=='crs' || (proceedingCandidateListBean.fromModuleName=='emp' && proceedingCandidateListBean.selectedCandType==proceedingCandidateListBean.specifyJobNameFromDewan)}">
            <f:facet name="header">
                <t:commandLink id="sort_jobSeekersDTO-kwtCitizensResidentsDTO-fullName" forceId="true" styleClass="headerLink" value="#{resourcesBundle.name_candidate}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="#{pageBeanName.columnNameHR_EMP_EMPLOYEES_FULL_NAME}"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobSeekersDTO-kwtCitizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobSeekersDTO-kwtCitizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_name" value="#{list.jobSeekersDTO.kwtCitizensResidentsDTO.fullName}"/>
        </t:column>
        
        <t:column id="regDate_column" width="10%"  rendered="#{proceedingCandidateListBean.fromModuleName=='crs' || (proceedingCandidateListBean.fromModuleName=='emp' && proceedingCandidateListBean.selectedCandType==proceedingCandidateListBean.specifyJobNameFromDewan)}">
            <f:facet name="header">
                <t:commandLink id="sort_cndApproveDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.canditatesDate}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="#{pageBeanName.columnNameHR_CRS_CANDIDATE_PERSONS_CNDAPPROVE_DATE}"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='cndApproveDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='cndApproveDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_validFrom" value="#{list.cndApproveDate}" converter="SqlDateConverter"/>
        </t:column>
        
        <t:column id="bookNo_column" width="20%"  rendered="#{proceedingCandidateListBean.fromModuleName=='crs' || (proceedingCandidateListBean.fromModuleName=='emp' && proceedingCandidateListBean.selectedCandType==proceedingCandidateListBean.specifyJobNameFromDewan)}">
            <f:facet name="header">
                <t:commandLink id="sort_jobNeedsDTO-needBooksDTO-code-bookNo" forceId="true" styleClass="headerLink" value="#{resourcesBundle.bookNumber}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="#{pageBeanName.columnNameCOLUMN_NAME_HR_CRS_NEED_BOOKS_BOOK_NO}"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobNeedsDTO-needBooksDTO-code-bookNo') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobNeedsDTO-needBooksDTO-code-bookNo') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_bookNo" value="#{list.jobNeedsDTO.needBooksDTO.code.bookNo}"/>
        </t:column>
        
        <t:column id="exp_column" width="20%"  rendered="#{proceedingCandidateListBean.fromModuleName=='crs' || (proceedingCandidateListBean.fromModuleName=='emp' && proceedingCandidateListBean.selectedCandType==proceedingCandidateListBean.specifyJobNameFromDewan)}">
            <f:facet name="header">
                <t:outputText id="sort_exp" forceId="true" styleClass="headerLink" value="#{empBundle.candHasPrevExperiences}"/>
            </f:facet>
            <%--   <t:graphicImage border="0" url="/module/media/images/ico_Green.gif" width="10" height="10" rendered="true"/>
            <t:graphicImage border="0" url="/module/media/images/ico_Green.gif" width="10" height="10" rendered="false"/>--%>
             <t:graphicImage border="0" url="/app/media/images/DataGrid_Icon_ok.gif" width="16" height="16" rendered="#{list.hasExperience}"/>
            <t:graphicImage border="0" url="/app/media/images/DataGrid_Icon_not.gif" width="16" height="16" rendered="#{!list.hasExperience}"/>
        </t:column>
        
        <%-- rejected by dewan --%>
        <t:column id="code_column2" width="10%"  rendered="#{proceedingCandidateListBean.fromModuleName=='emp' && proceedingCandidateListBean.selectedCandType==proceedingCandidateListBean.rejectedByDewan}">
            <f:facet name="header">
                <t:commandLink id="sort_citizensResidentsDTO-code-key" forceId="true" styleClass="headerLink" value="#{resourcesBundle.civilid}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="#{pageBeanName.columnNameHR_EMP_EMPLOYEES_CIVIL_ID}"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code-key') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_code2" value="#{list.citizensResidentsDTO.code.key}"/>
        </t:column>
        
        <t:column id="name_column2" width="34%"  rendered="#{proceedingCandidateListBean.fromModuleName=='emp' && proceedingCandidateListBean.selectedCandType==proceedingCandidateListBean.rejectedByDewan}">
            <f:facet name="header">
                <t:commandLink id="sort_citizensResidentsDTO-fullName" forceId="true" styleClass="headerLink" value="#{empBundle.candidate_name}" actionListener="#{pageBeanName.sortDataTable}">                         
                    <f:param name="bsnSortingColumnName" value="#{pageBeanName.columnNameHR_EMP_EMPLOYEES_FULL_NAME}"/>
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-fullName') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_name2" value="#{list.citizensResidentsDTO.fullName}"/>
        </t:column>
        
        <t:column id="lastPersonQualification_column2" width="40%"  rendered="#{proceedingCandidateListBean.fromModuleName=='emp' && proceedingCandidateListBean.selectedCandType==proceedingCandidateListBean.rejectedByDewan}">
            <f:facet name="header">
                <t:outputText value="#{empBundle.qualificationName}"/>
            </f:facet>
            <h:outputText id="content_lastPersonQualification" value="#{pageBeanName.lastPersonQualification.qualificationsDTO.name}"/>
        </t:column>
        
        <t:column id="exp_column2" width="15%"  rendered="#{proceedingCandidateListBean.fromModuleName=='emp' && proceedingCandidateListBean.selectedCandType==proceedingCandidateListBean.rejectedByDewan}">
            <f:facet name="header">
                <t:outputText value="#{empBundle.candHasPrevExperiences}"/>
            </f:facet>
            <t:graphicImage border="0" url="/app/media/images/DataGrid_Icon_ok.gif" width="16" height="16" rendered="#{list.hasExperience}"/>
            <t:graphicImage border="0" url="/app/media/images/DataGrid_Icon_not.gif" width="16" height="16" rendered="#{!list.hasExperience}"/>
        </t:column>
        
    </t:dataTable>
    
    <h:panelGrid columns="1" rendered="#{pageBeanName.listSize == 0 }">
        <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </h:panelGrid>
    
    <t:saveState value="#{pageBeanName.myTableData}"/>
    <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
    <t:saveState value="#{pageBeanName.searchMode}"/>
    <t:saveState value="#{pageBeanName.selectedDTOS}"/>
    <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
    <t:saveState value="#{pageBeanName.divIndicator}"/>
    
</t:panelGroup>
