<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form enctype="multipart/form-data" id="myForm" binding="#{documentAttachmentsIntegrationBean.frm}">
        <f:loadBundle basename="com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"
                      var="infintegrationBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{documentAttachmentsIntegrationBean}">
            <tiles:insert flush="false" definition="documentAttachmentsIntegration.page">
                <t:saveState value="#{pageBeanName.forShowAttachmentOnly}"/>
                <t:saveState value="#{pageBeanName.civilId}"/>
                <t:saveState value="#{pageBeanName.empName}"/>
                <t:saveState value="#{pageBeanName.selectedDocType}"/>
                <t:saveState value="#{pageBeanName.docTypeList}"/>
                <t:saveState value="#{pageBeanName.docName}"/>
                <t:saveState value="#{pageBeanName.docPath}"/>
                <t:saveState value="#{pageBeanName.fromDate}"/>
                <t:saveState value="#{pageBeanName.untilDate}"/>
                <t:saveState value="#{pageBeanName.attchmentPath}"/>
                <t:saveState value="#{pageBeanName.attchmentName}"/>
                <t:saveState value="#{pageBeanName.doctypeCode}"/>
                <t:saveState value="#{pageBeanName.fakeImageString}"/>
                <t:saveState value="#{pageBeanName.relativeFileName}"/>
                <t:saveState value="#{pageBeanName.uploadedFile}"/>
                <t:saveState value="#{pageBeanName.uploadingError}"/>
                <t:saveState value="#{pageBeanName.invalidImageType}"/>
                <t:saveState value="#{pageBeanName.detailsSavedStates}"/>
                <t:saveState value="#{pageBeanName.clonedPageDTO}"/>
                <t:saveState value="#{pageBeanName.selectedImagePath}"/>
                <t:saveState value="#{pageBeanName.personDocumntsDTO}"/>
                <t:saveState value="#{pageBeanName.personDocAttachemntsDTO}"/>
                <t:saveState value="#{pageBeanName.attachmentList}"/>
                <t:saveState value="#{pageBeanName.moduleName}"/>
                <t:saveState value="#{pageBeanName.disableSelectedDocType}"/>
                <t:saveState value="#{pageBeanName.filesTransaction}"/>
                <t:saveState value="#{pageBeanName.file}"/>
                <t:saveState value="#{pageBeanName.doctypeName}"/>
                <t:saveState value="#{pageBeanName.currentDisplayList}"/>
                <%-- <t:saveState value="#{pageBeanName.selectedimgHidden}"/>--%>
                
                <t:saveState value="#{empMaintainBean.pageDTO}"/>
                <t:saveState value="#{empMaintainBean.nextNavigationCase}"/>
                <t:saveState value="#{empMaintainBean.previousNavigationCase}"/>
                <t:saveState value="#{empMaintainBean.finishNavigationCase}"/>
                <t:saveState value="#{empMaintainBean.currentNavigationCase}"/>
                <t:saveState value="#{empMaintainBean.currentStep}"/>
                <t:saveState value="#{empMaintainBean.maintainMode}"/>
                <t:saveState value="#{empMaintainBean.renderSave}"/>
                <t:saveState value="#{empMaintainBean.enableContractType}"/>
                <%-- start qulList--%>
                <t:saveState value="#{qulListIntegrationBean.civilId}"/>
                <t:saveState value="#{qulListIntegrationBean.kwtCitizenDTO}"/>
                <t:saveState value="#{qulListIntegrationBean.citizinFullName}"/>
                <t:saveState value="#{qulListIntegrationBean.personsInformationDTOList}"/>
                <t:saveState value="#{qulListIntegrationBean.selectedDTOS}"/>
                <t:saveState value="#{qulListIntegrationBean.listSize}"/>
                <t:saveState value="#{qulListIntegrationBean.pageType}"/>
                <t:saveState value="#{qulListIntegrationBean.addQualificationIntegrationpage}"/>
                <t:saveState value="#{qulListIntegrationBean.qulListPageinWizardBar}"/>
                 <t:saveState value="#{qulListIntegrationBean.saveStateList}"/> 
                 <t:saveState value="#{qulListIntegrationBean.customCurentQual}"/>
                <t:saveState value="#{qulListIntegrationBean.saveInDB}"/>
                <t:saveState value="#{qulListIntegrationBean.notSavedPersonsInformationDTOList}"/>
                <t:saveState value="#{qulListIntegrationBean.dataTablestyleClass}"/>
                <t:saveState value="#{qulListIntegrationBean.needAddQualification}"/>
                <t:saveState value="#{qulListIntegrationBean.bundleMsg}"/>
                <%-- End qulList--%>
                <!-- MainData Save State-->
                <t:saveState value="#{empMainDataBean.saveButtonOverride}"/>
                <t:saveState value="#{empMainDataBean.finishButtonOverride}"/>
                <t:saveState value="#{empMainDataBean.masterEntityKey}"/>
                <t:saveState value="#{empMainDataBean.titleFlageOfDiv}"/>
                <t:saveState value="#{empMainDataBean.wcIntegrationBean}"/>
                <t:saveState value="#{empMainDataBean.workCenterName}"/>
                <t:saveState value="#{empMainDataBean.workCentersDTO}"/>
                <t:saveState value="#{empMainDataBean.workCenterKey}"/>
                <t:saveState value="#{empMainDataBean.civilId}"/>
                <t:saveState value="#{empMainDataBean.jobFilter}"/>
                <t:saveState value="#{empMainDataBean.divMode}"/>
                <t:saveState value="#{empMainDataBean.nonDBCivilID}"/>
                <t:saveState value="#{empMainDataBean.civilExist}"/>
                <t:saveState value="#{empMainDataBean.hireTypesList}"/>
                <t:saveState value="#{empMainDataBean.hireStatusList}"/>
                <t:saveState value="#{empMainDataBean.selectedJobName}"/>
                <t:saveState value="#{empMainDataBean.searchQuery}"/>
                <t:saveState value="#{empMainDataBean.workCenterMode}"/>
                <t:saveState value="#{empMainDataBean.raiseCode}"/>
                <t:saveState value="#{empMainDataBean.degreeCode}"/>
                <t:saveState value="#{empMainDataBean.caderCode}"/>
                <t:saveState value="#{empMainDataBean.caderName}"/>
                <t:saveState value="#{empMainDataBean.groupCode}"/>
                <t:saveState value="#{empMainDataBean.caderList}"/>
                <t:saveState value="#{empMainDataBean.groupList}"/>
                <t:saveState value="#{empMainDataBean.degreesList}"/>
                <t:saveState value="#{empMainDataBean.raisesCount}"/>
                <t:saveState value="#{empMainDataBean.contractType}"/>
                <t:saveState value="#{empMainDataBean.oldContractType}"/>
                <t:saveState value="#{empMainDataBean.renderQulType}"/>
                <t:saveState value="#{empMainDataBean.selectedHireTypeCode}"/>
                <t:saveState value="#{empMainDataBean.dataDisabledIfEmpFromCRS}"/>
                <t:saveState value="#{empMainDataBean.pageMode}"/>
                <t:saveState value="#{empMainDataBean.renderErrorHireDate}"/>
                <t:saveState value="#{empMainDataBean.invalidNextYear}"/>
                <t:saveState value="#{empMainDataBean.loginedMinistrycode}"/>
                <t:saveState value="#{empMainDataBean.raiseName}"/>
                <t:saveState value="#{empMainDataBean.jobDescription}"/>
                <t:saveState value="#{empMainDataBean.bgtProgramName}"/>
                <t:saveState value="#{empMainDataBean.kuwaitCitizen}"/>
                <%-- Start ProceduresBean--%>
                <t:saveState value="#{empProceduresBean.currentDetails}"/>
                <t:saveState value="#{empProceduresBean.saveButtonOverride}"/>
                <t:saveState value="#{empProceduresBean.finishButtonOverride}"/>
                <t:saveState value="#{empProceduresBean.masterEntityKey}"/>
                <t:saveState value="#{empProceduresBean.searchMode}"/>
                <t:saveState value="#{empProceduresBean.searchString}"/>
                <t:saveState value="#{empProceduresBean.availableDetails}"/>
                <t:saveState value="#{empProceduresBean.selectedCurrentDetails}"/>
                <t:saveState value="#{empProceduresBean.selectedAvailableDetails}"/>
                <t:saveState value="#{empProceduresBean.suspensionReasonsList}"/>
                <t:saveState value="#{empProceduresBean.selectedPageDTO}"/>
                <t:saveState value="#{empProceduresBean.proceduresResultList}"/>
             <t:saveState value="#{empProceduresBean.empProceduresDTO}"/>
                            <t:saveState value="#{empProceduresBean.errorCode}"/>
                            <t:saveState value="#{empProceduresBean.criminalResult}"/>
                            <t:saveState value="#{empProceduresBean.hasCriminalCase}"/>
                             <t:saveState value="#{empProceduresBean.showBlock}"/>
                <%-- Start DocumentsBean--%>
                <t:saveState value="#{empDocumentsBean.currentDetails}"/>
                <t:saveState value="#{empDocumentsBean.saveButtonOverride}"/>
                <t:saveState value="#{empDocumentsBean.finishButtonOverride}"/>
                <t:saveState value="#{empDocumentsBean.masterEntityKey}"/>
                <t:saveState value="#{empDocumentsBean.searchMode}"/>
                <t:saveState value="#{empDocumentsBean.searchString}"/>
                <t:saveState value="#{empDocumentsBean.availableDetails}"/>
                <t:saveState value="#{empDocumentsBean.selectedCurrentDetails}"/>
                <t:saveState value="#{empDocumentsBean.selectedAvailableDetails}"/>
                <t:saveState value="#{empDocumentsBean.sortAscending}"/>
                <t:saveState value="#{empDocumentsBean.attachementStatus}"/>
                <t:saveState value="#{empDocumentsBean.docStatus}"/>
                <t:saveState value="#{empListBean.myTableData}"/>
                <t:saveState value="#{empListBean.pageDTO}"/>
                <t:saveState value="#{empListBean.filteredList}"/>
                <t:saveState value="#{empListBean.fullColumnName}"/>
                <t:saveState value="#{empListBean.sortAscending}"/>
                <t:saveState value="#{empListBean.saveSortingState}"/>
                <t:saveState value="#{empListBean.sortColumn}"/>
                <t:saveState value="#{empListBean.selectedHireType}"/>
                <t:saveState value="#{empListBean.selectedBackHireType}"/>
                 <t:saveState value="#{empListBean.myTableData}"/>
                            <t:saveState value="#{empListBean.highlightedDTOS}"/>
                            <t:saveState value="#{empListBean.searchMode}"/>
                            <t:saveState value="#{empListBean.selectedPageDTO}"/>
                            <t:saveState value="#{empListBean.myDataTable}"/>
                            <t:inputHidden value="#{empListBean.selectedListSize}"/>
                            <t:saveState value="#{empListBean.currentPageIndex}"/>
                            <t:saveState value="#{empListBean.oldPageIndex}"/>
                            <t:saveState value="#{empListBean.sorting}"/>
                            <t:saveState value="#{empListBean.usingPaging}"/>
                            <t:saveState value="#{empListBean.updateMyPagedDataModel}"/>
                            <t:saveState value="#{empListBean.resettedPageIndex}"/>
                            <t:saveState value="#{empListBean.pagingRequestDTO}"/>
                            <t:saveState value="#{empListBean.pagingBean.totalListSize}"/>
                            <t:saveState value="#{empListBean.pagingBean.myPagedDataModel}"/>
                            <t:saveState value="#{empListBean.pagingBean.preUpdatedDataModel}"/>
                            
                            <t:saveState value="#{empMainDataBean.minExcepted}"/>
                            <t:saveState value="#{empMainDataBean.caderListEq}"/>
                            <t:saveState value="#{empMainDataBean.groupListEq}"/>
                            <t:saveState value="#{empMainDataBean.raiseList}"/>
                            <t:saveState value="#{empMainDataBean.degreeList}"/>
                            <t:saveState value="#{empMainDataBean.caderCodeEq}"/>
                            <t:saveState value="#{empMainDataBean.validCond}"/>
                            <t:saveState value="#{empMainDataBean.degreeCodeEq}"/>
                            <t:saveState value="#{empMainDataBean.raiseCodeEq}"/>
                            <t:saveState value="#{empMainDataBean.groupCodeEq}"/>
                            <t:saveState value="#{empMainDataBean.raiseDTO}"/>
                            <t:saveState value="#{empMainDataBean.totalRewardAccepted}"/>
            </tiles:insert>
        </t:aliasBean>
        <c:scriptGenerator form="myForm"/>
    </h:form>
</f:view>
