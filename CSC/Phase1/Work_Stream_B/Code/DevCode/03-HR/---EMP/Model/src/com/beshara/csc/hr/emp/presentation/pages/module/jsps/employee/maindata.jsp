<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <h:form id="myForm" binding="#{empMainDataBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{empMaintainBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{empMainDataBean}">
                <t:aliasBean alias="#{jSearchBeanName}" value="#{empMainDataBean.jobFilter}">
                    <t:aliasBean alias="#{wcIntegrationBeanName}" value="#{empMainDataBean.wcIntegrationBean}">
                        <tiles:insert definition="empmaindata.page" flush="false">
                            <t:saveState value="#{pageBeanName.enableEditQulAndExper}"/>
                            <t:saveState value="#{empMaintainBean.enableContractType}"/>
                            <t:saveState value="#{pageBeanName.enableEditExper}"/>
                            <t:saveState value="#{pageBeanName.mainDataOnlyFlag}"/>
                            <t:saveState value="#{pageBeanName.savedHireType}"/>
                            <t:saveState value="#{pageBeanName.savedDocumentList}"/>
                            <t:saveState value="#{pageBeanName.pageDTO}"/>
                            <t:saveState value="#{pageBeanName.pageDTO.hireTypesDTO}"/>
                            <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
                            <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
                            <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
                            <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
                            <t:saveState value="#{pageBeanName.currentStep}"/>
                            <t:saveState value="#{pageBeanName.maintainMode}"/>
                            <t:saveState value="#{pageBeanName.renderSave}"/>
                            <t:saveState value="#{pageBeanName.selectedHireTypeKey}"/>
                            <t:saveState value="#{pageBeanName.invalidMinFileNo}"/>
                            <t:saveState value="#{pageBeanName.backEditHireType}"/>
                            <t:saveState value="#{detailBeanName.saveButtonOverride}"/>
                            <t:saveState value="#{detailBeanName.finishButtonOverride}"/>
                            <t:saveState value="#{detailBeanName.masterEntityKey}"/>
                            <t:saveState value="#{detailBeanName.titleFlageOfDiv}"/>
                            <t:saveState value="#{detailBeanName.wcIntegrationBean}"/>
                            <t:saveState value="#{detailBeanName.workCenterName}"/>
                            <t:saveState value="#{detailBeanName.workCentersDTO}"/>
                            <t:saveState value="#{detailBeanName.workCenterKey}"/>
                            <t:saveState value="#{detailBeanName.civilId}"/>
                            <t:saveState value="#{detailBeanName.jobFilter}"/>
                            <t:saveState value="#{detailBeanName.divMode}"/>
                            <t:saveState value="#{detailBeanName.nonDBCivilID}"/>
                            <t:saveState value="#{detailBeanName.civilExist}"/>
                            <t:saveState value="#{detailBeanName.hireTypesList}"/>
                            <t:saveState value="#{detailBeanName.hireStatusList}"/>
                            <t:saveState value="#{detailBeanName.selectedJobName}"/>
                            <t:saveState value="#{detailBeanName.searchQuery}"/>
                            <t:saveState value="#{detailBeanName.workCenterMode}"/>
                            <t:saveState value="#{detailBeanName.raiseCode}"/>
                            <t:saveState value="#{detailBeanName.degreeCode}"/>
                            <t:saveState value="#{detailBeanName.caderCode}"/>
                            <t:saveState value="#{detailBeanName.caderName}"/>
                            <t:saveState value="#{detailBeanName.groupCode}"/>
                            <t:saveState value="#{detailBeanName.caderList}"/>
                            <t:saveState value="#{detailBeanName.groupList}"/>
                            <t:saveState value="#{detailBeanName.degreesList}"/>
                            <t:saveState value="#{detailBeanName.raisesCount}"/>
                            <t:saveState value="#{detailBeanName.contractType}"/>
                            <t:saveState value="#{detailBeanName.oldContractType}"/>
                            <t:saveState value="#{detailBeanName.renderQulType}"/>
                            <t:saveState value="#{detailBeanName.selectedHireTypeCode}"/>
                            <t:saveState value="#{detailBeanName.dataDisabledIfEmpFromCRS}"/>
                            <t:saveState value="#{detailBeanName.pageMode}"/>
                            <t:saveState value="#{detailBeanName.renderErrorHireDate}"/>
                            <t:saveState value="#{detailBeanName.kuwaitCitizen}"/>
                            <t:saveState value="#{detailBeanName.invalidNextYear}"/>
                            <t:saveState value="#{detailBeanName.loginedMinistrycode}"/>
                            <t:saveState value="#{detailBeanName.raiseName}"/>
                            <t:saveState value="#{detailBeanName.jobDescription}"/>
                            <t:saveState value="#{detailBeanName.bgtProgramName}"/>
                            <t:saveState value="#{detailBeanName.errorJobCondition}"/>
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
                            <%-- end paging--%>
                            <t:saveState value="#{empListBean.saveSortingState}"/>
                            <t:saveState value="#{empListBean.sortColumn}"/>
                            <%-- start qulList--%>
                            <t:saveState value="#{qulListIntegrationBean.kwtCitizenDTO}"/>
                            <t:saveState value="#{qulListIntegrationBean.civilId}"/>
                            <t:saveState value="#{qulListIntegrationBean.citizinFullName}"/>
                            <t:saveState value="#{qulListIntegrationBean.personsInformationDTOList}"/>
                            <t:saveState value="#{qulListIntegrationBean.selectedDTOS}"/>
                            <t:saveState value="#{qulListIntegrationBean.listSize}"/>
                            <t:saveState value="#{qulListIntegrationBean.pageType}"/>
                            <t:saveState value="#{qulListIntegrationBean.customCurentQual}"/>
                            <t:saveState value="#{qulListIntegrationBean.addQualificationIntegrationpage}"/>
                            <t:saveState value="#{qulListIntegrationBean.qulListPageinWizardBar}"/>
                             <t:saveState value="#{qulListIntegrationBean.saveStateList}"/>
                            <t:saveState value="#{qulListIntegrationBean.saveInDB}"/>
                            <t:saveState value="#{qulListIntegrationBean.notSavedPersonsInformationDTOList}"/>
                            <t:saveState value="#{qulListIntegrationBean.dataTablestyleClass}"/>
                            <t:saveState value="#{qulListIntegrationBean.needAddQualification}"/>
                            <t:saveState value="#{qulListIntegrationBean.bundleMsg}"/>
                            <%-- End qulList--%>
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
                            <t:saveState value="#{detailBeanName.errorCanDegree}"/>
                            
                            
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
        </t:aliasBean>
        <t:panelGroup forceId="true" id="validScriptPanel">
            <c:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
</f:view>
<script type="text/javascript">
  function showWorkCenterIntegrationDiv() {
      changeVisibilityDiv(window.blocker, window.integrationDiv1);

  }

  function goChangeFilNumber(e) {
      if (e.keyCode == 13) {
          checkAboutFilNum();
          e.preventDefault();
      }
  }

  function changeFilNumberOnBlur() {
      checkAboutFilNum();
  }

  function checkaboutHireDate(e) {
      if (e.keyCode == 13) {
          if (validatemyForm('87')) {
              calculateNextDateOfRaise();
          }
          e.preventDefault();
      }
  }

  function checkaboutHireDateOnBlur() {
      if (validatemyForm('87')) {
          calculateNextDateOfRaise();
      }
  }

  function stepValidation() {
   result = validatemyForm('hireTypeGroup');
      if (!result) {
          return false;
      }
      var returnFromCmpareDates = validateTwoDatesInDataTable();
      if (returnFromCmpareDates) {
          return validatemyForm();
      }
      return false;
  }
</script>