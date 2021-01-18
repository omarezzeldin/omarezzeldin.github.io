<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{workDataListBean.frm}">
        <f:loadBundle basename="com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"
                      var="infIntegrationBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{workDataListBean}">
            <t:aliasBean alias="#{workDataListBeanName}" value="#{workDataListBean}">
                <tiles:insert flush="false" definition="workDataList.page">
                    <tiles:put name="titlepage" type="string" value="${'experiance'}"></tiles:put>
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
                    <t:saveState value="#{workDataListBeanName.kwtWorkDataDTOList}"/>
                    <t:saveState value="#{workDataListBeanName.pageType}"/>
                    <t:saveState value="#{workDataListBeanName.listPageinWizardBar}"/>
                    <t:saveState value="#{workDataListBeanName.addExperIntegrationpage}"/>
                    <t:saveState value="#{civilServiceReviewBean.pageDTO}"/>
                    <t:saveState value="#{civilServiceReviewBean.jobFilter}"/>
                    <t:saveState value="#{civilServiceReviewBean.bgtTypesDTO}"/>
                    <t:saveState value="#{civilServiceReviewBean.bckBtnNavigationCase}"/>
                    <t:saveState value="#{civilServiceReviewBean.saveStateList}"/>
                    <t:saveState value="#{civilServiceReviewBean.configBean}"/>
                    <t:saveState value="#{civilServiceReviewBean.caderName}"/>
                    <t:saveState value="#{civilServiceReviewBean.titleFlageOfDiv}"/>
                    <t:saveState value="#{civilServiceReviewBean.caderList}"/>
                    <t:saveState value="#{civilServiceReviewBean.workCenterMode}"/>
                    <t:saveState value="#{civilServiceReviewBean.jobMode}"/>
                    <t:saveState value="#{civilServiceReviewBean.caderCode}"/>
                    <t:saveState value="#{civilServiceReviewBean.suggestedCaderCode}"/>
                    <t:saveState value="#{civilServiceReviewBean.suggestedCaderName}"/>
                    <t:saveState value="#{civilServiceReviewBean.renderLovDiv}"/>
                    <t:saveState value="#{civilServiceReviewBean.groupList}"/>
                    <t:saveState value="#{civilServiceReviewBean.groupCode}"/>
                    <t:saveState value="#{civilServiceReviewBean.raiseList}"/>
                    <t:saveState value="#{civilServiceReviewBean.raiseCode}"/>
                    <t:saveState value="#{civilServiceReviewBean.raiseName}"/>
                    <t:saveState value="#{civilServiceReviewBean.degreeCode}"/>
                    <t:saveState value="#{civilServiceReviewBean.degreeList}"/>
                    <t:saveState value="#{civilServiceReviewBean.eqTypeTemp}"/>
                    <t:saveState value="#{civilServiceReviewBean.suggestedRaiseCode}"/>
                    <t:saveState value="#{civilServiceReviewBean.suggestedRaiseName}"/>
                    <t:saveState value="#{civilServiceReviewBean.errorJobCondition}"/>
                    <t:saveState value="#{treeDivBean.myTableData}"/>
                    <t:saveState value="#{civilServiceReviewBean.workCenterHasJobs}"/>
                    <t:saveState value="#{civilServiceReviewBean.jobList}"/>
                    <t:saveState value="#{civilServiceReviewBean.jobKey}"/>
                    <t:saveState value="#{civilServiceReviewBean.budgetTypeList}"/>
                    <t:saveState value="#{civilServiceReviewBean.ministryNotes}"/>
                    <t:saveState value="#{civilServiceReviewBean.minWorkCenter}"/>
                    <t:saveState value="#{civilServiceReviewBean.workCenterName}"/>
                    <t:saveState value="#{civilServiceReviewBean.jobNameForMin}"/>
                    <t:saveState value="#{civilServiceReviewBean.backActionMethodName}"/>
                    <t:saveState value="#{civilServiceReviewBean.pageBeanName}"/>
                    <t:saveState value="#{civilServiceReviewBean.stageId}"/>
                    <t:saveState value="#{civilServiceReviewBean.hireStageConst}"/>
                    <t:saveState value="#{civilServiceReviewBean.selectedMinistery}"/>
                    <t:saveState value="#{civilServiceReviewBean.invalidMinFileNo}"/>
                    <t:saveState value="#{civilServiceReviewBean.configBean.disableCadre}"/>
                    <t:saveState value="#{civilServiceReviewBean.groupName}"/>
                    <t:saveState value="#{civilServiceReviewBean.suggestedGroupName}"/>
                    <t:saveState value="#{civilServiceReviewBean.suggestedGroupCode}"/>
                    <t:saveState value="#{civilServiceReviewBean.suggestedDegreeCode}"/>
                    <t:saveState value="#{civilServiceReviewBean.suggestedDegreeName}"/>
                    <t:saveState value="#{civilServiceReviewBean.approvedBgtTypeCode}"/>
                    <t:saveState value="#{civilServiceReviewBean.approvedBgtTypeName}"/>
                    <t:saveState value="#{civilServiceReviewBean.acceptedJobKey}"/>
                    <t:saveState value="#{civilServiceReviewBean.acceptedJobName}"/>
                    <t:saveState value="#{civilServiceReviewBean.acceptedJobDTO}"/>
                    <t:saveState value="#{civilServiceReviewBean.totalRewardAccepted}"/>
                    <t:saveState value="#{civilServiceReviewBean.enableAddRewardButton}"/>
                    <t:saveState value="#{civilServiceReviewBean.empCandExtraDataList}"/>
                    <t:saveState value="#{civilServiceReviewBean.raiseDTO}"/>
                    <t:saveState value="#{addBounceBean.myTableData}"/>
                    <t:saveState value="#{addBounceBean.totalBounces}"/>
                    <t:saveState value="#{addBounceBean.totalBouncseNotBasicSal}"/>
                    <t:saveState value="#{civilServiceReviewBean.minCode}"/>
                    <t:saveState value="#{jobsReviewListBean.pageDTO}"/>
                    <t:saveState value="#{jobsReviewListBean.bgtTypesDTO}"/>
                    <t:saveState value="#{jobsReviewListBean.jobFilter}"/>
                    <t:saveState value="#{jobsReviewListBean.pageDTO.empExtraDataValueDTO}"/>
                    <t:saveState value="#{jobsReviewListBean.bckBtnNavigationCase}"/>
                    <t:saveState value="#{jobsReviewListBean.saveStateList}"/>
                    <t:saveState value="#{jobsReviewListBean.configBean}"/>
                    <t:saveState value="#{jobsReviewListBean.caderName}"/>
                    <t:saveState value="#{jobsReviewListBean.titleFlageOfDiv}"/>
                    <t:saveState value="#{jobsReviewListBean.caderList}"/>
                    <t:saveState value="#{jobsReviewListBean.workCenterMode}"/>
                    <t:saveState value="#{jobsReviewListBean.jobMode}"/>
                    <t:saveState value="#{jobsReviewListBean.caderCode}"/>
                    <t:saveState value="#{jobsReviewListBean.jobKeyPrevious}"/>
                    <t:saveState value="#{jobsReviewListBean.jobNamePrevious}"/>
                    <t:saveState value="#{jobsReviewListBean.showFatwaNotes}"/>
                    <t:saveState value="#{jobsReviewListBean.fatwaNotes}"/>
                    <t:saveState value="#{jobsReviewListBean.suggestedCaderCode}"/>
                    <t:saveState value="#{jobsReviewListBean.suggestedCaderName}"/>
                    <t:saveState value="#{jobsReviewListBean.renderLovDiv}"/>
                    <t:saveState value="#{jobsReviewListBean.groupList}"/>
                    <t:saveState value="#{jobsReviewListBean.groupCode}"/>
                    <t:saveState value="#{jobsReviewListBean.raiseList}"/>
                    <t:saveState value="#{jobsReviewListBean.raiseCode}"/>
                    <t:saveState value="#{jobsReviewListBean.raiseName}"/>
                    <t:saveState value="#{jobsReviewListBean.degreeCode}"/>
                    <t:saveState value="#{jobsReviewListBean.degreeList}"/>
                    <t:saveState value="#{jobsReviewListBean.eqTypeTemp}"/>
                    <t:saveState value="#{jobsReviewListBean.suggestedRaiseCode}"/>
                    <t:saveState value="#{jobsReviewListBean.suggestedRaiseName}"/>
                    <t:saveState value="#{treeDivBean.myTableData}"/>
                    <t:saveState value="#{jobsReviewListBean.workCenterHasJobs}"/>
                    <t:saveState value="#{jobsReviewListBean.jobList}"/>
                    <t:saveState value="#{jobsReviewListBean.jobKey}"/>
                    <t:saveState value="#{jobsReviewListBean.budgetTypeList}"/>
                    <t:saveState value="#{jobsReviewListBean.workMinistriesList}"/>
                    <t:saveState value="#{jobsReviewListBean.workMinistriesList}"/>
                    <t:saveState value="#{jobsReviewListBean.minWorkCenter}"/>
                    <t:saveState value="#{jobsReviewListBean.workCenterName}"/>
                    <t:saveState value="#{jobsReviewListBean.jobNameForMin}"/>
                    <t:saveState value="#{jobsReviewListBean.backActionMethodName}"/>
                    <t:saveState value="#{jobsReviewListBean.pageBeanName}"/>
                    <t:saveState value="#{jobsReviewListBean.stageId}"/>
                    <t:saveState value="#{jobsReviewListBean.jobsDTO}"/>
                    <t:saveState value="#{jobsReviewListBean.hireStageConst}"/>
                    <t:saveState value="#{jobsReviewListBean.selectedMinistery}"/>
                    <t:saveState value="#{jobsReviewListBean.invalidMinFileNo}"/>
                    <t:saveState value="#{jobsReviewListBean.configBean.disableCadre}"/>
                    <t:saveState value="#{jobsReviewListBean.hireSystemMode}"/>
                    <t:saveState value="#{jobsReviewListBean.hasExperience}"/>
                    <t:saveState value="#{jobsReviewListBean.groupName}"/>
                    <t:saveState value="#{jobsReviewListBean.suggestedGroupName}"/>
                    <t:saveState value="#{jobsReviewListBean.suggestedGroupCode}"/>
                    <t:saveState value="#{jobsReviewListBean.suggestedDegreeCode}"/>
                    <t:saveState value="#{jobsReviewListBean.suggestedDegreeName}"/>
                    <t:saveState value="#{jobsReviewListBean.approvedBgtTypeCode}"/>
                    <t:saveState value="#{jobsReviewListBean.approvedBgtTypeName}"/>
                    <t:saveState value="#{jobsReviewListBean.approvedJobName}"/>
                    <t:saveState value="#{jobsReviewListBean.errorJobCondition}"/>
                    <t:saveState value="#{jobsReviewListBean.jobFromMin}"/>
                    <t:saveState value="#{jobsSelectionListBean.categoryList}"/>
                    <t:saveState value="#{jobsSelectionListBean.ministryList}"/>
                    <t:saveState value="#{jobsSelectionListBean.selectedMinistry}"/>
                    <t:saveState value="#{jobsSelectionListBean.selectedDTOS}"/>
                    <t:saveState value="#{jobsSelectionListBean.selectedPageDTO}"/>
                    <t:saveState value="#{jobsSelectionListBean.selectedCategory}"/>
                    <%-- start paging--%>
                    <t:saveState value="#{jobsSelectionListBean.currentPageIndex}"/>
                    <t:saveState value="#{jobsSelectionListBean.oldPageIndex}"/>
                    <t:saveState value="#{jobsSelectionListBean.civilId}"/>
                    <t:saveState value="#{jobsSelectionListBean.civilName}"/>
                    <t:saveState value="#{jobsSelectionListBean.civilExist}"/>
                    <t:saveState value="#{jobsSelectionListBean.validCivilId}"/>
                    <t:saveState value="#{jobsSelectionListBean.searchDTO1}"/>
                    <t:saveState value="#{jobsSelectionListBean.searchMode}"/>
                    <t:saveState value="#{jobsSelectionListBean.hideHasExprince}"/>
                    <t:saveState value="#{ceneteralEmpArrang.pageDTO}"/>
                    <t:saveState value="#{ceneteralEmpArrang.bgtTypesDTO}"/>
                    <t:saveState value="#{ceneteralEmpArrang.pageDTO.empExtraDataValueDTO}"/>
                    <t:saveState value="#{ceneteralEmpArrang.bckBtnNavigationCase}"/>
                    <t:saveState value="#{ceneteralEmpArrang.saveStateList}"/>
                    <t:saveState value="#{ceneteralEmpArrang.jobFilter}"/>
                    <t:saveState value="#{ceneteralEmpArrang.configBean}"/>
                    <t:saveState value="#{ceneteralEmpArrang.caderName}"/>
                    <t:saveState value="#{ceneteralEmpArrang.titleFlageOfDiv}"/>
                    <t:saveState value="#{ceneteralEmpArrang.caderList}"/>
                    <t:saveState value="#{ceneteralEmpArrang.workCenterMode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.jobMode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.caderCode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.approvedBgtTypeNamePrevious}"/>
                    <t:saveState value="#{ceneteralEmpArrang.jobKeyPrevious}"/>
                    <t:saveState value="#{ceneteralEmpArrang.jobNamePrevious}"/>
                    <t:saveState value="#{ceneteralEmpArrang.showFatwaNotes}"/>
                    <t:saveState value="#{ceneteralEmpArrang.fatwaNotes}"/>
                    <t:saveState value="#{ceneteralEmpArrang.jobFromMin}"/>
                    <t:saveState value="#{ceneteralEmpArrang.suggestedCaderCode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.suggestedCaderName}"/>
                    <t:saveState value="#{ceneteralEmpArrang.renderLovDiv}"/>
                    <t:saveState value="#{ceneteralEmpArrang.groupList}"/>
                    <t:saveState value="#{ceneteralEmpArrang.groupCode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.raiseList}"/>
                    <t:saveState value="#{ceneteralEmpArrang.raiseCode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.raiseName}"/>
                    <t:saveState value="#{ceneteralEmpArrang.degreeCode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.degreeList}"/>
                    <t:saveState value="#{ceneteralEmpArrang.eqTypeTemp}"/>
                    <t:saveState value="#{ceneteralEmpArrang.suggestedRaiseCode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.suggestedRaiseName}"/>
                    <t:saveState value="#{treeDivBean.myTableData}"/>
                    <t:saveState value="#{ceneteralEmpArrang.workCenterHasJobs}"/>
                    <t:saveState value="#{ceneteralEmpArrang.jobList}"/>
                    <t:saveState value="#{ceneteralEmpArrang.jobKey}"/>
                    <t:saveState value="#{ceneteralEmpArrang.budgetTypeList}"/>
                    <t:saveState value="#{ceneteralEmpArrang.workMinistriesList}"/>
                    <t:saveState value="#{ceneteralEmpArrang.workMinistriesList}"/>
                    <t:saveState value="#{ceneteralEmpArrang.minWorkCenter}"/>
                    <t:saveState value="#{ceneteralEmpArrang.workCenterName}"/>
                    <t:saveState value="#{ceneteralEmpArrang.jobNameForMin}"/>
                    <t:saveState value="#{ceneteralEmpArrang.backActionMethodName}"/>
                    <t:saveState value="#{ceneteralEmpArrang.pageBeanName}"/>
                    <t:saveState value="#{ceneteralEmpArrang.stageId}"/>
                    <t:saveState value="#{ceneteralEmpArrang.hireStageConst}"/>
                    <t:saveState value="#{ceneteralEmpArrang.selectedMinistery}"/>
                    <t:saveState value="#{ceneteralEmpArrang.invalidMinFileNo}"/>
                    <t:saveState value="#{ceneteralEmpArrang.configBean.disableCadre}"/>
                    <t:saveState value="#{ceneteralEmpArrang.hireSystemMode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.hasExperience}"/>
                    <t:saveState value="#{ceneteralEmpArrang.errorJobCondition}"/>
                    <t:saveState value="#{ceneteralEmpArrang.groupName}"/>
                    <t:saveState value="#{ceneteralEmpArrang.suggestedGroupName}"/>
                    <t:saveState value="#{ceneteralEmpArrang.suggestedGroupCode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.suggestedDegreeCode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.suggestedDegreeName}"/>
                    <t:saveState value="#{ceneteralEmpArrang.approvedBgtTypeCode}"/>
                    <t:saveState value="#{ceneteralEmpArrang.approvedBgtTypeName}"/>
                    <t:saveState value="#{reviewListBean.pageDTO}"/>
                    <t:saveState value="#{reviewListBean.bgtTypesDTO}"/>
                    <t:saveState value="#{reviewListBean.pageDTO}"/>
                    <t:saveState value="#{reviewListBean.bckBtnNavigationCase}"/>
                    <t:saveState value="#{reviewListBean.saveStateList}"/>
                    <t:saveState value="#{reviewListBean.configBean}"/>
                    <t:saveState value="#{reviewListBean.caderName}"/>
                    <t:saveState value="#{reviewListBean.titleFlageOfDiv}"/>
                    <t:saveState value="#{reviewListBean.caderList}"/>
                    <t:saveState value="#{reviewListBean.workCenterMode}"/>
                    <t:saveState value="#{reviewListBean.jobMode}"/>
                    <t:saveState value="#{reviewListBean.caderCode}"/>
                    <t:saveState value="#{reviewListBean.suggestedCaderCode}"/>
                    <t:saveState value="#{reviewListBean.suggestedCaderName}"/>
                    <t:saveState value="#{reviewListBean.renderLovDiv}"/>
                    <t:saveState value="#{reviewListBean.groupList}"/>
                    <t:saveState value="#{reviewListBean.groupCode}"/>
                    <t:saveState value="#{reviewListBean.raiseList}"/>
                    <t:saveState value="#{reviewListBean.raiseCode}"/>
                    <t:saveState value="#{reviewListBean.raiseName}"/>
                    <t:saveState value="#{reviewListBean.degreeCode}"/>
                    <t:saveState value="#{reviewListBean.degreeList}"/>
                    <t:saveState value="#{reviewListBean.eqTypeTemp}"/>
                    <t:saveState value="#{reviewListBean.suggestedRaiseCode}"/>
                    <t:saveState value="#{reviewListBean.suggestedRaiseName}"/>
                    <t:saveState value="#{treeDivBean.myTableData}"/>
                    <t:saveState value="#{reviewListBean.workCenterHasJobs}"/>
                    <t:saveState value="#{reviewListBean.jobList}"/>
                    <t:saveState value="#{reviewListBean.jobKey}"/>
                    <t:saveState value="#{reviewListBean.budgetTypeList}"/>
                    <t:saveState value="#{reviewListBean.workMinistriesList}"/>
                    <t:saveState value="#{reviewListBean.workMinistriesList}"/>
                    <t:saveState value="#{reviewListBean.minWorkCenter}"/>
                    <t:saveState value="#{reviewListBean.workCenterName}"/>
                    <t:saveState value="#{reviewListBean.jobNameForMin}"/>
                    <t:saveState value="#{reviewListBean.backActionMethodName}"/>
                    <t:saveState value="#{reviewListBean.pageBeanName}"/>
                    <t:saveState value="#{reviewListBean.stageId}"/>
                    <t:saveState value="#{reviewListBean.hireStageConst}"/>
                    <t:saveState value="#{reviewListBean.selectedMinistery}"/>
                    <t:saveState value="#{reviewListBean.invalidMinFileNo}"/>
                    <t:saveState value="#{reviewListBean.configBean.disableCadre}"/>
                    <t:saveState value="#{reviewListBean.hireSystemMode}"/>
                    <t:saveState value="#{reviewListBean.hasExperience}"/>
                    <t:saveState value="#{reviewListBean.groupName}"/>
                    <t:saveState value="#{reviewListBean.suggestedGroupName}"/>
                    <t:saveState value="#{reviewListBean.suggestedGroupCode}"/>
                    <t:saveState value="#{reviewListBean.suggestedDegreeCode}"/>
                    <t:saveState value="#{reviewListBean.suggestedDegreeName}"/>
                    <t:saveState value="#{reviewListBean.approvedBgtTypeCode}"/>
                    <t:saveState value="#{reviewListBean.approvedBgtTypeName}"/>
                    <t:saveState value="#{reviewListBean.validCond}"/>
                    <t:saveState value="#{reviewListBean.concederedExper}"/>
                    <t:saveState value="#{reviewListBean.previousNotes}"/>
                    <t:saveState value="#{selectionListBean.approveBtnShow}"/>
                    <t:saveState value="#{reviewListBean.degreeNamePrevious}"/>
                    <t:saveState value="#{reviewListBean.raiseNamePrevious}"/>
                    <t:saveState value="#{reviewListBean.caderNamePrevious}"/>
                    <t:saveState value="#{reviewListBean.groupNamePrevious}"/>
                    <t:saveState value="#{selectionListBean.civilId}"/>
                    <t:saveState value="#{selectionListBean.civilName}"/>
                    <t:saveState value="#{selectionListBean.civilExist}"/>
                    <t:saveState value="#{selectionListBean.validCivilId}"/>
                    <t:saveState value="#{selectionListBean.selectedCategory}"/>
                    <t:saveState value="#{selectionListBean.categoryList}"/>
                    <t:saveState value="#{selectionListBean.ministryList}"/>
                    <t:saveState value="#{selectionListBean.selectedMinistry}"/>
                    <t:saveState value="#{selectionListBean.searchMode}"/>
                    <t:saveState value="#{selectionListBean.selectedDTOS}"/>
                    <t:saveState value="#{selectionListBean.selectedPageDTO}"/>
                    <t:saveState value="#{selectionListBean.searchDTO}"/>
                    <t:saveState value="#{selectionListBean.currentPageIndex}"/>
                    <t:saveState value="#{selectionListBean.oldPageIndex}"/>
                    <t:saveState value="#{selectionListBean.hideHasExprince}"/>
                    <t:saveState value="#{fatwaReviewListBean.pageDTO}"/>
                    <t:saveState value="#{fatwaReviewListBean.bgtTypesDTO}"/>
                    <t:saveState value="#{fatwaReviewListBean.pageDTO.empExtraDataValueDTO}"/>
                    <t:saveState value="#{fatwaReviewListBean.bckBtnNavigationCase}"/>
                    <t:saveState value="#{fatwaReviewListBean.saveStateList}"/>
                    <t:saveState value="#{fatwaReviewListBean.configBean}"/>
                    <t:saveState value="#{fatwaReviewListBean.caderName}"/>
                    <t:saveState value="#{fatwaReviewListBean.titleFlageOfDiv}"/>
                    <t:saveState value="#{fatwaReviewListBean.degreeNamePrevious}"/>
                    <t:saveState value="#{fatwaReviewListBean.raiseNamePrevious}"/>
                    <t:saveState value="#{fatwaReviewListBean.caderNamePrevious}"/>
                    <t:saveState value="#{fatwaReviewListBean.groupNamePrevious}"/>
                    <t:saveState value="#{fatwaReviewListBean.workCenterMode}"/>
                    <t:saveState value="#{fatwaReviewListBean.jobMode}"/>
                    <t:saveState value="#{fatwaReviewListBean.caderCode}"/>
                    <t:saveState value="#{fatwaReviewListBean.suggestedCaderCode}"/>
                    <t:saveState value="#{fatwaReviewListBean.suggestedCaderName}"/>
                    <t:saveState value="#{fatwaReviewListBean.renderLovDiv}"/>
                    <t:saveState value="#{fatwaReviewListBean.groupCode}"/>
                    <t:saveState value="#{fatwaReviewListBean.raiseCode}"/>
                    <t:saveState value="#{fatwaReviewListBean.raiseName}"/>
                    <t:saveState value="#{fatwaReviewListBean.degreeCode}"/>
                    <t:saveState value="#{fatwaReviewListBean.eqTypeTemp}"/>
                    <t:saveState value="#{fatwaReviewListBean.suggestedRaiseCode}"/>
                    <t:saveState value="#{fatwaReviewListBean.suggestedRaiseName}"/>
                    <t:saveState value="#{treeDivBean.myTableData}"/>
                    <t:saveState value="#{fatwaReviewListBean.workCenterHasJobs}"/>
                    <t:saveState value="#{fatwaReviewListBean.jobList}"/>
                    <t:saveState value="#{fatwaReviewListBean.jobKey}"/>
                    <t:saveState value="#{fatwaReviewListBean.budgetTypeList}"/>
                    <t:saveState value="#{fatwaReviewListBean.workMinistriesList}"/>
                    <t:saveState value="#{fatwaReviewListBean.workMinistriesList}"/>
                    <t:saveState value="#{fatwaReviewListBean.minWorkCenter}"/>
                    <t:saveState value="#{fatwaReviewListBean.workCenterName}"/>
                    <t:saveState value="#{fatwaReviewListBean.jobNameForMin}"/>
                    <t:saveState value="#{fatwaReviewListBean.backActionMethodName}"/>
                    <t:saveState value="#{fatwaReviewListBean.pageBeanName}"/>
                    <t:saveState value="#{fatwaReviewListBean.stageId}"/>
                    <t:saveState value="#{fatwaReviewListBean.hireStageConst}"/>
                    <t:saveState value="#{fatwaReviewListBean.selectedMinistery}"/>
                    <t:saveState value="#{fatwaReviewListBean.invalidMinFileNo}"/>
                    <t:saveState value="#{fatwaReviewListBean.configBean.disableCadre}"/>
                    <t:saveState value="#{fatwaReviewListBean.groupName}"/>
                    <t:saveState value="#{fatwaReviewListBean.suggestedGroupName}"/>
                    <t:saveState value="#{fatwaReviewListBean.suggestedGroupCode}"/>
                    <t:saveState value="#{fatwaReviewListBean.suggestedDegreeCode}"/>
                    <t:saveState value="#{fatwaReviewListBean.suggestedDegreeName}"/>
                    <t:saveState value="#{fatwaReviewListBean.approvedBgtTypeCode}"/>
                    <t:saveState value="#{fatwaReviewListBean.approvedBgtTypeName}"/>
                    <t:saveState value="#{fatwaReviewListBean.raiseCRSList}"/>
                    <t:saveState value="#{fatwaReviewListBean.degreeCRSList}"/>
                    <t:saveState value="#{fatwaReviewListBean.groupCRSList}"/>
                    <t:saveState value="#{fatwaReviewListBean.caderCRSList}"/>
                    <t:saveState value="#{fatwaReviewListBean.hireSystemMode}"/>
                    <t:saveState value="#{fatwaReviewListBean.hasExperience}"/>
                    <t:saveState value="#{fatwaReviewListBean.validCond}"/>
                    <t:saveState value="#{fatwaSelectionListBean.approveBtnShow}"/>
                    <t:saveState value="#{fatwaSelectionListBean.civilId}"/>
                    <t:saveState value="#{fatwaSelectionListBean.civilName}"/>
                    <t:saveState value="#{fatwaSelectionListBean.civilExist}"/>
                    <t:saveState value="#{fatwaSelectionListBean.validCivilId}"/>
                    <t:saveState value="#{fatwaSelectionListBean.selectedCategory}"/>
                    <t:saveState value="#{fatwaSelectionListBean.categoryList}"/>
                    <t:saveState value="#{fatwaSelectionListBean.ministryList}"/>
                    <t:saveState value="#{fatwaSelectionListBean.selectedMinistry}"/>
                    <t:saveState value="#{fatwaSelectionListBean.searchMode}"/>
                    <t:saveState value="#{fatwaSelectionListBean.selectedDTOS}"/>
                    <t:saveState value="#{fatwaSelectionListBean.selectedPageDTO}"/>
                    <t:saveState value="#{fatwaSelectionListBean.searchDTO1}"/>
                    <t:saveState value="#{fatwaSelectionListBean.currentPageIndex}"/>
                    <t:saveState value="#{fatwaSelectionListBean.oldPageIndex}"/>
                    <t:saveState value="#{fatwaSelectionListBean.hideHasExprince}"/>
                    <t:saveState value="#{addBounceBean.myTableData}"/>
                    <t:saveState value="#{addBounceBean.totalBounces}"/>
                    <t:saveState value="#{addBounceBean.totalBouncseNotBasicSal}"/>
                    <t:saveState value="#{fatwaReviewListBean.caderList}"/>
                    <t:saveState value="#{fatwaReviewListBean.groupList}"/>
                    <t:saveState value="#{fatwaReviewListBean.raiseList}"/>
                    <t:saveState value="#{fatwaReviewListBean.degreeList}"/>
                    <t:saveState value="#{fatwaReviewListBean.totalRewardAccepted}"/>
                    <t:saveState value="#{fatwaReviewListBean.totalRewardSuggested}"/>
                    <t:saveState value="#{fatwaReviewListBean.acceptedJobName}"/>
                    <t:saveState value="#{fatwaReviewListBean.enableAddRewardButton}"/>
                    <t:saveState value="#{fatwaReviewListBean.raiseDTO}"/>
                    <t:saveState value="#{fatwaReviewListBean.empCandExtraDataList}"/>
                </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptPanelGroup">
            <c:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
</f:view>