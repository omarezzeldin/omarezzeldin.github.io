<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    <h:form id="myForm" binding="#{mainDataCandidateBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{addCandidateMaintainBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{mainDataCandidateBean}">
                <t:aliasBean alias="#{wcIntegrationBeanName}" value="#{mainDataCandidateBean.wcIntegrationBean}">
                    <t:aliasBean alias="#{jSearchBeanName}" value="#{mainDataCandidateBean.jobFilter}">
                        <tiles:insert definition="mainDataCandidate.page" flush="false">
                            <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
                            <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
                            <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
                            <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
                            <t:saveState value="#{pageBeanName.currentStep}"/>
                            <t:saveState value="#{pageBeanName.maintainMode}"/>
                            <t:saveState value="#{pageBeanName.renderSave}"/>
                            <t:saveState value="#{pageBeanName.renderFinish}"/>
                            <t:saveState value="#{pageBeanName.showPrevious}"/>
                            <t:saveState value="#{detailBeanName.saveButtonOverride}"/>
                            <t:saveState value="#{detailBeanName.finishButtonOverride}"/>
                            <t:saveState value="#{pageBeanName.pageDTO}"/>
                            <t:saveState value="#{pageBeanName.backHireType}"/>
                            <%-- start citizenInfo--%>
                            <t:saveState value="#{detailBeanName.wcIntegrationBean}"/>
                            <t:saveState value="#{detailBeanName.nationality}"/>
                            <t:saveState value="#{detailBeanName.maritalStatus}"/>
                            <t:saveState value="#{detailBeanName.genderTypeName}"/>
                            <t:saveState value="#{detailBeanName.candidateName}"/>
                            <t:saveState value="#{detailBeanName.bgtTypeKey}"/>
                            <t:saveState value="#{detailBeanName.groupCode}"/>
                            <t:saveState value="#{detailBeanName.degreeCode}"/>
                            <t:saveState value="#{detailBeanName.raiseCode}"/>
                            <%-- end citizenInfo--%>
                            <%-- start qulList--%>
                            <t:saveState value="#{qulListIntegrationBean.kwtCitizenDTO}"/>
                            <t:saveState value="#{qulListIntegrationBean.civilId}"/>
                            <t:saveState value="#{qulListIntegrationBean.citizinFullName}"/>
                            <t:saveState value="#{qulListIntegrationBean.personsInformationDTOList}"/>
                            <t:saveState value="#{qulListIntegrationBean.selectedDTOS}"/>
                            <t:saveState value="#{qulListIntegrationBean.listSize}"/>
                            <t:saveState value="#{qulListIntegrationBean.pageType}"/>
                            <t:saveState value="#{qulListIntegrationBean.qulListPageinWizardBar}"/>
                             <t:saveState value="#{qulListIntegrationBean.saveStateList}"/>
                             <t:saveState value="#{qulListIntegrationBean.customCurentQual}"/>
                            <t:saveState value="#{qulListIntegrationBean.saveInDB}"/>
                            <t:saveState value="#{qulListIntegrationBean.notSavedPersonsInformationDTOList}"/>
                            <t:saveState value="#{qulListIntegrationBean.dataTablestyleClass}"/>
                            <t:saveState value="#{qulListIntegrationBean.needAddQualification}"/>
                            <t:saveState value="#{qulListIntegrationBean.addQualificationIntegrationpage}"/>
                            <t:saveState value="#{qulListIntegrationBean.bundleMsg}"/>
                            <t:saveState value="#{qulListIntegrationBean.showCustomDiv2}"/>
                            <%-- End qulList--%>
                            <t:saveState value="#{mainDataCandidateBean.hireTypeFirstParent}"/>
                            <t:saveState value="#{detailBeanName.renderErrorHireDate}"/>
                            <t:saveState value="#{detailBeanName.caderList}"/>
                            <t:saveState value="#{detailBeanName.groupList}"/>
                            <t:saveState value="#{detailBeanName.degreesList}"/>
                            <t:saveState value="#{detailBeanName.raisesCount}"/>
                            <t:saveState value="#{detailBeanName.caderCode}"/>
                            <t:saveState value="#{detailBeanName.caderName}"/>
                            <t:saveState value="#{detailBeanName.contractType}"/>
                            <t:saveState value="#{detailBeanName.jobName}"/>
                            <t:saveState value="#{detailBeanName.jobCode}"/>
                            <t:saveState value="#{detailBeanName.jobDTO}"/>
                            <t:saveState value="#{detailBeanName.workCenterKey}"/>
                            <t:saveState value="#{detailBeanName.wrkCenterName}"/>
                            <t:saveState value="#{detailBeanName.workCentersDTO}"/>
                            <t:saveState value="#{detailBeanName.bgtTypeKey}"/>
                            <t:saveState value="#{pageBeanName.hireTypesList}"/>
                            <t:saveState value="#{detailBeanName.selectedHireType}"/>
                            <t:saveState value="#{detailBeanName.fileNo}"/>
                            <t:saveState value="#{detailBeanName.nextYear}"/>
                            <t:saveState value="#{detailBeanName.kuwaitCitizen}"/>
                            <t:saveState value="#{detailBeanName.errorJobCondition}"/>
                            <t:saveState value="#{detailBeanName.previousYear}"/>
                            <t:saveState value="#{detailBeanName.hireDate}"/>
                            <t:saveState value="#{detailBeanName.dateOfNextRaise}"/>
                            <t:saveState value="#{detailBeanName.civilId}"/>
                            <t:saveState value="#{detailBeanName.empCandidate}"/>
                            <t:saveState value="#{detailBeanName.validCivilId}"/>
                            <t:saveState value="#{detailBeanName.civilExist}"/>
                            <t:saveState value="#{detailBeanName.empHired}"/>
                            <t:saveState value="#{detailBeanName.empNationality}"/>
                            <t:saveState value="#{detailBeanName.bgtProgName}"/>
                            <t:saveState value="#{detailBeanName.disabledContractType}"/>
                            <t:saveState value="#{detailBeanName.workCenterName}"/>
                            <t:saveState value="#{detailBeanName.kwtCitizensResidentsDTO}"/>
                            <t:saveState value="#{documentsCandidateBean.saveButtonOverride}"/>
                            <t:saveState value="#{documentsCandidateBean.finishButtonOverride}"/>
                            <t:saveState value="#{documentsCandidateBean.attachementStatus}"/>
                            <t:saveState value="#{documentsCandidateBean.docStatus}"/>
                            <t:saveState value="#{documentsCandidateBean.searchMode}"/>
                            <t:saveState value="#{documentsCandidateBean.searchString}"/>
                            <t:saveState value="#{documentsCandidateBean.currentDetails}"/>
                            <t:saveState value="#{documentsCandidateBean.availableDetails}"/>
                            <t:saveState value="#{documentsCandidateBean.selectedCurrentDetails}"/>
                            <t:saveState value="#{documentsCandidateBean.selectedAvailableDetails}"/>
                            <t:saveState value="#{documentsCandidateBean.currentDisplayDetails}"/>
                            <t:saveState value="#{proceduresCandidateBean.saveButtonOverride}"/>
                            <t:saveState value="#{proceduresCandidateBean.finishButtonOverride}"/>
                            <t:saveState value="#{proceduresCandidateBean.searchMode}"/>
                            <t:saveState value="#{proceduresCandidateBean.searchString}"/>
                            <t:saveState value="#{proceduresCandidateBean.currentDetails}"/>
                            <t:saveState value="#{proceduresCandidateBean.availableDetails}"/>
                            <t:saveState value="#{proceduresCandidateBean.proceduresResultList}"/>
                            <t:saveState value="#{proceduresCandidateBean.selectedCurrentDetails}"/>
                            <t:saveState value="#{proceduresCandidateBean.selectedAvailableDetails}"/>
                            <t:saveState value="#{proceduresCandidateBean.currentDisplayDetails}"/>
                            <t:saveState value="#{proceduresCandidateBean.renderShowSuspensionReason}"/>
                            <t:saveState value="#{proceduresCandidateBean.empProceduresDTO}"/>
                            <t:saveState value="#{proceduresCandidateBean.hasCriminalCase}"/>
                            <t:saveState value="#{proceduresCandidateBean.errorCode}"/>
                            <t:saveState value="#{proceduresCandidateBean.criminalResult}"/>
                            <t:saveState value="#{proceduresCandidateBean.showBlock}"/>
                            <t:saveState value="#{detailBeanName.invalidNextYear}"/>
                            <%-- <t:saveState value="#{empListBean.myTableData}"/>--%>
                            <t:saveState value="#{empListBean.pageDTO}"/>
                            <t:saveState value="#{empListBean.filteredList}"/>
                            <t:saveState value="#{empListBean.myTableData}"/>
                            <t:saveState value="#{detailBeanName.errorCanDegree}"/>
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
                            <t:saveState value="#{mainDataCandidateBean.minExcepted}"/>
                            <t:saveState value="#{mainDataCandidateBean.caderListEq}"/>
                            <t:saveState value="#{mainDataCandidateBean.groupListEq}"/>
                            <t:saveState value="#{mainDataCandidateBean.raiseList}"/>
                            <t:saveState value="#{mainDataCandidateBean.degreeList}"/>
                            <t:saveState value="#{mainDataCandidateBean.caderCodeEq}"/>
                            <t:saveState value="#{mainDataCandidateBean.validCond}"/>
                            <t:saveState value="#{mainDataCandidateBean.degreeCodeEq}"/>
                            <t:saveState value="#{mainDataCandidateBean.raiseCodeEq}"/>
                            <t:saveState value="#{mainDataCandidateBean.groupCodeEq}"/>
                            <t:saveState value="#{mainDataCandidateBean.raiseDTO}"/>
                            <t:saveState value="#{mainDataCandidateBean.totalRewardAccepted}"/>
                        </tiles:insert>
                    </t:aliasBean>
                </t:aliasBean>
            </t:aliasBean>
            <c2:scriptGenerator form="myForm" id="scriptGenerator"/>
        </t:aliasBean>
    </h:form>
</f:view>
<script type="text/javascript">
  setFocusFirstElem();

  function stepValidation() {
      var result = validatemyForm('requiredFields');
      if (!result) {
          return false;
      }
      result = validatemyForm('hireTypeGroup');
      if (!result) {
          return false;
      }
      var returnFromCmpareDates = validateTwoDatesInDataTable();
      if (returnFromCmpareDates) {
          return validateAllForm();
      }
      return false;
  }
</script>
