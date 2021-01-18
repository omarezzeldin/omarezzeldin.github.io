<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.nl.job.presentation.resources.jobs" var="jobResources"/>
    <f:loadBundle basename="com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"
                  var="infIntegrationBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    <h:form id="myForm" binding="#{empExperiencesBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{empMaintainBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{empExperiencesBean}">
                <t:aliasBean alias="#{workDataListBeanName}" value="#{empExperiencesBean.workDataListBean}">
                    <tiles:insert definition="empexperiences.page" flush="false">
                        <t:saveState value="#{pageBeanName.enableEditQulAndExper}"/>
                        <t:saveState value="#{empMaintainBean.enableContractType}"/>
                        <t:saveState value="#{pageBeanName.enableEditExper}"/>
                        <t:saveState value="#{pageBeanName.myTableData}"/>
                        <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                        <t:saveState value="#{pageBeanName.searchMode}"/>
                        <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                        <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
                        <t:saveState value="#{pageBeanName.myDataTable}"/>
                        <t:saveState value="#{pageBeanName.mainDataOnlyFlag}"/>
                        <t:saveState value="#{pageBeanName.pageDTO}"/>
                        <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
                        <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
                        <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
                        <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
                        <t:saveState value="#{pageBeanName.currentStep}"/>
                        <t:saveState value="#{pageBeanName.maintainMode}"/>
                        <t:saveState value="#{pageBeanName.renderSave}"/>
                        <t:saveState value="#{pageBeanName.renderFinish}"/>
                        <t:saveState value="#{pageBeanName.backEditHireType}"/>
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
                        <%-- start qulList--%>
                        <t:saveState value="#{qulListIntegrationBean.kwtCitizenDTO}"/>
                        <t:saveState value="#{qulListIntegrationBean.civilId}"/>
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
                        <t:saveState value="#{empListBean.pageDTO}"/>
                        <t:saveState value="#{empListBean.filteredList}"/>
                        <t:saveState value="#{empListBean.fullColumnName}"/>
                        <t:saveState value="#{empListBean.sortAscending}"/>
                        <t:saveState value="#{empListBean.saveSortingState}"/>
                        <t:saveState value="#{empListBean.sortColumn}"/>
                        <t:saveState value="#{empListBean.selectedHireType}"/>
                        <t:saveState value="#{empListBean.selectedBackHireType}"/>
                        <t:saveState value="#{workDataListBeanName.treeNodeBase}"/>
                        <t:saveState value="#{workDataListBeanName.usingTreePaging}"/>
                        <t:saveState value="#{workDataListBeanName.treeListPagingRequestDTO}"/>
                        <t:saveState value="#{workDataListBeanName.pagedTreeListSize}"/>
                        <t:saveState value="#{workDataListBeanName.selectedNodeTreeLevelId}"/>
                        <t:saveState value="#{workDataListBeanName.htmlTree}"/>
                        <t:saveState value="#{workDataListBeanName.myTableData}"/>
                        <t:saveState value="#{workDataListBeanName.treeNodeBase}"/>
                        <t:saveState value="#{workDataListBeanName.dtoDetails}"/>
                        <t:saveState value="#{workDataListBeanName.selectedNodeTreeLevelId}"/>
                        <t:saveState value="#{workDataListBeanName.htmlTree}"/>
                        <t:saveState value="#{workDataListBeanName.treeNodeBase}"/>
                        <t:saveState value="#{workDataListBeanName.searchResultSize}"/>
                        <t:saveState value="#{workDataListBeanName.entityKey}"/>
                        <t:saveState value="#{workDataListBeanName.treeListSize}"/>
                        <t:saveState value="#{workDataListBeanName.treeList}"/>
                        <t:saveState value="#{workDataListBeanName.panelGroupStyleClass}"/>
                        <t:saveState value="#{workDataListBeanName.civilId}"/>
                        <t:saveState value="#{workDataListBeanName.civilExist}"/>
                        <t:saveState value="#{workDataListBeanName.validCivilId}"/>
                        <t:saveState value="#{workDataListBeanName.navigationCase}"/>
                        <t:saveState value="#{workDataListBeanName.showBtn}"/>
                        <t:saveState value="#{workDataListBeanName.hmObjects}"/>
                        <t:saveState value="#{workDataListBeanName.beanName}"/>
                        <t:saveState value="#{workDataListBeanName.backAction}"/>
                        <t:saveState value="#{workDataListBeanName.enableAdd}"/>
                        <t:saveState value="#{workDataListBeanName.enableBack}"/>
                        <t:saveState value="#{workDataListBeanName.saveInDB}"/>
                        <t:saveState value="#{experiencesCandidate.workDataListBean.kwtWorkDataDTOList}"/>
                        <t:saveState value="#{workDataListBeanName.pageType}"/>
                        <t:saveState value="#{workDataListBeanName.listPageinWizardBar}"/>
                        <t:saveState value="#{workDataListBeanName.addExperIntegrationpage}"/>
                        
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
            </t:aliasBean>
        </t:aliasBean>
    </h:form>
</f:view>
<!--<script type="text/javascript">
  foucsAddbuttonOnList()

  function foucsAddbuttonOnList() {
      if (document.getElementById('addButton') != null) {
          document.getElementById('addButton').focus();
      }
  }
</script>-->
