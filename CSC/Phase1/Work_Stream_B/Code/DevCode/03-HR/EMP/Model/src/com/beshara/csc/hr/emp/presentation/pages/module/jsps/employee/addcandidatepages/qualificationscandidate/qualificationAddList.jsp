<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <!--f:loadBundle basename="com.beshara.csc.hr.crs.presentation.resources.crs" var="resourcesBundle"/-->
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <h:form id="myForm" binding="#{qualificationAddHelperBean.frm}">
        <t:messages showSummary="true" showDetail="true"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{qualificationAddHelperBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{qualificationAddHelperBean}">
                <t:aliasBean alias="#{qulEduLevelsIntegrationBean}"
                             value="#{qualificationAddHelperBean.qulEduLevelsIntegrationBean}">
                    <t:aliasBean alias="#{qulCentersIntegrationBean}"
                                 value="#{qualificationAddHelperBean.qulCentersIntegrationBean}">
                        <tiles:insert definition="qulIntigration.page" flush="false">
                            <t:saveState value="#{pageBeanName.degreeValuesList}"/>
                            <t:saveState value="#{pageBeanName.shangLangth}"/>
                            <%-- <t:saveState value="#{pageBeanName.qulIntegrationListBean}"/>--%>
                            <t:saveState value="#{pageBeanName.selectedQulDegreeValue}"/>
                            <t:saveState value="#{pageBeanName.qulDegreeType}"/>
                            <t:saveState value="#{pageBeanName.inValidValue}"/>
                            <%-- <t:saveState value="#{pageBeanName.qulificationMinistriesList}"/>--%>
                            <t:saveState value="#{pageBeanName.personQualificationsDTO}"/>
                            <t:saveState value="#{pageBeanName.selectedQulType}"/>
                            <t:saveState value="#{pageBeanName.searchMode}"/>
                            <t:saveState value="#{pageBeanName.qualificationName}"/>
                            <%-- <t:saveState value="#{pageBeanName.qualificationList}"/>--%>
                            <t:saveState value="#{pageBeanName.searchQulifictaionString}"/>
                            <t:saveState value="#{pageBeanName.countriesList}"/>
                            <t:saveState value="#{pageBeanName.centersList}"/>
                            <t:saveState value="#{pageBeanName.countryCode}"/>
                            <t:saveState value="#{pageBeanName.centerCode}"/>
                            <t:saveState value="#{pageBeanName.societyCode}"/>
                            <t:saveState value="#{pageBeanName.kwtCitizensResidentsDTO}"/>
                            <t:saveState value="#{pageBeanName.civilId}"/>
                            <t:saveState value="#{pageBeanName.personsInformationDTO}"/>
                            <t:saveState value="#{pageBeanName.success}"/>
                            <t:saveState value="#{pageBeanName.showErrorMsg}"/>
                            <t:saveState value="#{pageBeanName.qulCenterCode}"/>
                            <t:saveState value="#{pageBeanName.qulCentersList}"/>
                            <t:saveState value="#{pageBeanName.qulCenterTreeDiv}"/>
                            <t:saveState value="#{pageBeanName.qulCountryCode}"/>
                            <t:saveState value="#{pageBeanName.qulCountriesList}"/>
                            <t:saveState value="#{pageBeanName.generalSpecslist}"/>
                            <t:saveState value="#{pageBeanName.majorSpecsList}"/>
                            <t:saveState value="#{pageBeanName.educationKey}"/>
                            <t:saveState value="#{pageBeanName.generalSpecsKey}"/>
                            <t:saveState value="#{pageBeanName.majorSpecsKey}"/>
                            <t:saveState value="#{pageBeanName.pageMode}"/>
                            <t:saveState value="#{pageBeanName.qulAddDivTitleName}"/>
                            <t:saveState value="#{pageBeanName.selectedCenterName}"/>
                            <t:saveState value="#{pageBeanName.enableResetData}"/>
                            <t:saveState value="#{pageBeanName.certificateLevelId}"/>
                            <t:saveState value="#{pageBeanName.selectedCertificateId}"/>
                            <t:saveState value="#{pageBeanName.centerDTO}"/>
                            <t:saveState value="#{pageBeanName.levelDTO}"/>
                            <t:saveState value="#{pageBeanName.certificatesList}"/>
                            <t:saveState value="#{pageBeanName.selectedCertificateId}"/>
                            <t:saveState value="#{pageBeanName.certificateLevel}"/>
                            <t:saveState value="#{pageBeanName.pageDTO}"/>
                            <t:saveState value="#{pageBeanName.prepareMethodName}"/>
                            <t:saveState value="#{pageBeanName.returnMethodName}"/>
                            <t:saveState value="#{pageBeanName.civilId}"/>
                            <t:saveState value="#{pageBeanName.disableQulDateRegistration}"/>
                            <t:saveState value="#{addCandidateMaintainBean.pageDTO}"/>
                            <t:saveState value="#{addCandidateMaintainBean.nextNavigationCase}"/>
                            <t:saveState value="#{addCandidateMaintainBean.previousNavigationCase}"/>
                            <t:saveState value="#{addCandidateMaintainBean.finishNavigationCase}"/>
                            <t:saveState value="#{addCandidateMaintainBean.currentNavigationCase}"/>
                            <t:saveState value="#{addCandidateMaintainBean.currentStep}"/>
                            <t:saveState value="#{addCandidateMaintainBean.maintainMode}"/>
                            <t:saveState value="#{addCandidateMaintainBean.renderSave}"/>
                            <t:saveState value="#{addCandidateMaintainBean.backHireType}"/>
                            <t:saveState value="#{addCandidateMaintainBean.hireTypesList}"/>
                            <t:saveState value="#{documentsCandidateBean.saveButtonOverride}"/>
                            <t:saveState value="#{documentsCandidateBean.finishButtonOverride}"/>
                            <%-- start citizenInfo--%>
                            <t:saveState value="#{mainDataCandidateBean.nationality}"/>
                            <t:saveState value="#{mainDataCandidateBean.maritalStatus}"/>
                            <t:saveState value="#{mainDataCandidateBean.genderTypeName}"/>
                            <t:saveState value="#{mainDataCandidateBean.candidateName}"/>
                            <%-- end citizenInfo--%>
                            <t:saveState value="#{mainDataCandidateBean.hireTypeFirstParent}"/>
                            <t:saveState value="#{mainDataCandidateBean.caderList}"/>
                            <t:saveState value="#{mainDataCandidateBean.groupList}"/>
                            <%-- <t:saveState value="#{mainDataCandidateBean.nextYear}"/>--%>
                            <t:saveState value="#{mainDataCandidateBean.groupCode}"/>
                            <t:saveState value="#{mainDataCandidateBean.degreeCode}"/>
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
                            <t:saveState value="#{mainDataCandidateBean.raiseCode}"/>
                            <t:saveState value="#{mainDataCandidateBean.empCandidate}"/>
                            <t:saveState value="#{mainDataCandidateBean.validCivilId}"/>
                            <t:saveState value="#{mainDataCandidateBean.civilExist}"/>
                            <t:saveState value="#{mainDataCandidateBean.disabledContractType}"/>
                            <t:saveState value="#{mainDataCandidateBean.empHired}"/>
                            <t:saveState value="#{mainDataCandidateBean.bgtProgName}"/>
                            <t:saveState value="#{mainDataCandidateBean.workCenterName}"/>
                            <t:saveState value="#{mainDataCandidateBean.kwtCitizensResidentsDTO}"/>
                            <t:saveState value="#{mainDataCandidateBean.kuwaitCitizen}"/>
                            <t:saveState value="#{empListBean.fullColumnName}"/>
                            <t:saveState value="#{empListBean.sortAscending}"/>
                            <t:saveState value="#{empListBean.saveSortingState}"/>
                            <t:saveState value="#{empListBean.sortColumn}"/>
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
                            <t:saveState value="#{proceduresCandidateBean.renderShowSuspensionReason}"/>
                             <t:saveState value="#{proceduresCandidateBean.empProceduresDTO}"/>
                   <t:saveState value="#{proceduresCandidateBean.hasCriminalCase}"/>
                   <t:saveState value="#{proceduresCandidateBean.errorCode}"/>
                   <t:saveState value="#{proceduresCandidateBean.criminalResult}"/>
                   <t:saveState value="#{proceduresCandidateBean.showBlock}"/>
                            <t:saveState value="#{addDocAttachmentsIntegrationBean.empName}"/>
                            <t:saveState value="#{addDocAttachmentsIntegrationBean.civilId}"/>
                            <t:saveState value="#{addDocAttachmentsIntegrationBean.selectedDocType}"/>
                            <t:saveState value="#{addDocAttachmentsIntegrationBean.disableSelectedDocType}"/>
                            <t:saveState value="#{addDocAttachmentsIntegrationBean.moduleName}"/>
                            <t:saveState value="#{addDocAttachmentsIntegrationBean.doctypeName}"/>
                            <%-- start qulList--%>
                            <t:saveState value="#{qulListIntegrationBean.showCustomDiv2}"/>
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
                            <t:saveState value="#{empListBean.pageDTO}"/>
                            <t:saveState value="#{empListBean.filteredList}"/>
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
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptPanel">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
        <script type="text/javascript">
          function setFocusAfterSelectDegreeType() {
              if (document.getElementById('qulDegreeType') != null && document.getElementById('qulDegreeType').value != null) {
                  if (document.getElementById('qulDegreeType').value == 3 || document.getElementById('qulDegreeType').value == 2) {
                      setFocusOnlyOnElement('qulDegreeValueCombo');
                  }
                  else {
                      setFocusOnlyOnElement('qulDegreeValueID');
                  }
              }
          }

          foucsCountryBoxOnList();

          function foucsCountryBoxOnList() {
              if (document.getElementById('countriesListt') != null) {
                  document.getElementById('countriesListt').focus();
              }
          }

          function reinitializeSaveMsg(componentID) {

              if (document.getElementById(componentID) != null) {
                  document.getElementById(componentID).innerHTML = '';
              }
          }
        </script>
    </h:form>
</f:view>