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
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    <f:loadBundle basename="com.beshara.csc.nl.qul.integration.presentation.resources.qulintegration"
                  var="qulintegration"/>
    <h:form id="myForm" binding="#{qulListIntegrationBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{addCandidateMaintainBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{qulListIntegrationBean}">
                <tiles:insert definition="qualificationsCandidate.page" flush="false">
                    <tiles:put name="titlepage" type="string" value="qualification_Candidate"/>
                    <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.currentStep}"/>
                    <t:saveState value="#{pageBeanName.maintainMode}"/>
                    <t:saveState value="#{pageBeanName.renderSave}"/>
                    <t:saveState value="#{pageBeanName.renderFinish}"/>
                    <t:saveState value="#{pageBeanName.hireTypesList}"/>
                    <t:saveState value="#{pageBeanName.pageDTO}"/>
                    <t:saveState value="#{pageBeanName.showPrevious}"/>
                    <t:saveState value="#{pageBeanName.backHireType}"/>
                    <t:saveState value="#{pageBeanName.hireTypesList}"/>
                    <t:saveState value="#{detailBeanName.saveButtonOverride}"/>
                    <t:saveState value="#{detailBeanName.finishButtonOverride}"/>
                    <%-- start qulList--%>
                    <t:saveState value="#{detailBeanName.showCustomDiv2}"/>
                    <t:saveState value="#{detailBeanName.bundleMsg}"/>
                    <t:saveState value="#{detailBeanName.civilId}"/>
                    <t:saveState value="#{detailBeanName.kwtCitizenDTO}"/>
                    <t:saveState value="#{detailBeanName.customCurentQual}"/>
                    <t:saveState value="#{detailBeanName.citizinFullName}"/>
                    <t:saveState value="#{detailBeanName.personsInformationDTOList}"/>
                    <t:saveState value="#{detailBeanName.selectedDTOS}"/>
                    <t:saveState value="#{detailBeanName.listSize}"/>
                    <t:saveState value="#{detailBeanName.pageType}"/>
                    <t:saveState value="#{detailBeanName.addQualificationIntegrationpage}"/>
                    <t:saveState value="#{detailBeanName.qulListPageinWizardBar}"/>
                    <t:saveState value="#{detailBeanName.saveStateList}"/>
                    <t:saveState value="#{detailBeanName.saveInDB}"/>
                    <t:saveState value="#{detailBeanName.notSavedPersonsInformationDTOList}"/>
                    <t:saveState value="#{detailBeanName.dataTablestyleClass}"/>
                    <t:saveState value="#{detailBeanName.needAddQualification}"/>
                    <%-- start citizenInfo--%>
                    <t:saveState value="#{mainDataCandidateBean.nationality}"/>
                    <t:saveState value="#{mainDataCandidateBean.maritalStatus}"/>
                    <t:saveState value="#{mainDataCandidateBean.genderTypeName}"/>
                    <t:saveState value="#{mainDataCandidateBean.candidateName}"/>
                    <%-- end citizenInfo--%>
                    <t:saveState value="#{mainDataCandidateBean.hireTypeFirstParent}"/>
                    <t:saveState value="#{mainDataCandidateBean.caderList}"/>
                    <t:saveState value="#{mainDataCandidateBean.groupList}"/>
                    <t:saveState value="#{mainDataCandidateBean.degreesList}"/>
                    <t:saveState value="#{mainDataCandidateBean.raisesCount}"/>
                    <t:saveState value="#{mainDataCandidateBean.caderCode}"/>
                    <t:saveState value="#{mainDataCandidateBean.caderName}"/>
                    <t:saveState value="#{mainDataCandidateBean.contractType}"/>
                    <t:saveState value="#{mainDataCandidateBean.jobName}"/>
                    <t:saveState value="#{mainDataCandidateBean.jobCode}"/>
                    <t:saveState value="#{mainDataCandidateBean.jobDTO}"/>
                    <t:saveState value="#{mainDataCandidateBean.workCenterKey}"/>
                    <t:saveState value="#{mainDataCandidateBean.wrkCenterName}"/>
                    <t:saveState value="#{mainDataCandidateBean.workCentersDTO}"/>
                    <t:saveState value="#{mainDataCandidateBean.bgtTypeKey}"/>
                    <t:saveState value="#{mainDataCandidateBean.selectedHireType}"/>
                    <t:saveState value="#{mainDataCandidateBean.fileNo}"/>
                    <t:saveState value="#{mainDataCandidateBean.hireDate}"/>
                    <t:saveState value="#{mainDataCandidateBean.dateOfNextRaise}"/>
                    <t:saveState value="#{mainDataCandidateBean.civilId}"/>
                    <t:saveState value="#{mainDataCandidateBean.empCandidate}"/>
                    <t:saveState value="#{mainDataCandidateBean.validCivilId}"/>
                    <t:saveState value="#{mainDataCandidateBean.civilExist}"/>
                    <t:saveState value="#{mainDataCandidateBean.disabledContractType}"/>
                    <t:saveState value="#{mainDataCandidateBean.empHired}"/>
                    <t:saveState value="#{mainDataCandidateBean.bgtProgName}"/>
                    <t:saveState value="#{mainDataCandidateBean.workCenterName}"/>
                    <t:saveState value="#{mainDataCandidateBean.kwtCitizensResidentsDTO}"/>
                    <t:saveState value="#{mainDataCandidateBean.kuwaitCitizen}"/>
                    <t:saveState value="#{mainDataCandidateBean.groupCode}"/>
                    <t:saveState value="#{mainDataCandidateBean.degreeCode}"/>
                    <t:saveState value="#{mainDataCandidateBean.raiseCode}"/>
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
                    <t:saveState value="#{proceduresCandidateBean.selectedCurrentDetails}"/>
                    <t:saveState value="#{proceduresCandidateBean.selectedAvailableDetails}"/>
                    <t:saveState value="#{proceduresCandidateBean.currentDisplayDetails}"/>
                    <t:saveState value="#{proceduresCandidateBean.proceduresResultList}"/>
                      <t:saveState value="#{proceduresCandidateBean.empProceduresDTO}"/>
                   <t:saveState value="#{proceduresCandidateBean.hasCriminalCase}"/>
                   <t:saveState value="#{proceduresCandidateBean.errorCode}"/>
                   <t:saveState value="#{proceduresCandidateBean.criminalResult}"/>
                   <t:saveState value="#{proceduresCandidateBean.showBlock}"/>
                    <t:saveState value="#{empListBean.pageDTO}"/>
                    <t:saveState value="#{empListBean.filteredList}"/>
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
        <t:panelGroup forceId="true" id="scriptGeneratorID">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
    <script type="text/javascript">
      foucsAddbuttonOnList()
      adjustDataTable('dataT_data_panel', '312');

      function foucsAddbuttonOnList() {
          if (document.getElementById('addButton') != null) {
              document.getElementById('addButton').focus();
          }
      }
    </script>
</f:view>