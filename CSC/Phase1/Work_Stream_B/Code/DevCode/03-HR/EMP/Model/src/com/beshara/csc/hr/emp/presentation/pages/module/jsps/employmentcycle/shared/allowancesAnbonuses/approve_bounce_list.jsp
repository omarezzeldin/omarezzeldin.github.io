<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
    <h:form id="myForm" binding="#{addBounceBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{addBounceBean}">
            <t:saveState value="#{pageBeanName.benefitRewardCodeExists}"/>
            <t:saveState value="#{pageBeanName.selectedBenReward}"/>
            <t:saveState value="#{pageBeanName.success}"/>
            <t:saveState value="#{pageBeanName.civilId}"/>
            <t:saveState value="#{pageBeanName.id}"/>
            <t:saveState value="#{pageBeanName.pageDTO}"/>
            <t:saveState value="#{treeDivBean.myTableData}"/>
            <t:saveState value="#{treeDivBean.pagingRequestDTO}"/>
            <t:saveState value="#{pageBeanName.fullName}"/>
            <t:saveState value="#{pageBeanName.elmGuideMinValue}"/>
            <t:saveState value="#{pageBeanName.elmGuideMaxValue}"/>
            <t:saveState value="#{pageBeanName.totalBounces}"/>
            <t:saveState value="#{pageBeanName.basicSalary}"/>
            <t:saveState value="#{pageBeanName.empCandidatesDTO}"/>
            <t:saveState value="#{pageBeanName.backActionMethodName}"/>
            <t:saveState value="#{pageBeanName.bckBtnNavigationCase}"/>
            <t:saveState value="#{pageBeanName.afterSaveMethodName}"/>
            <t:saveState value="#{pageBeanName.saveStateAddBounceList}"/>
            <t:saveState value="#{pageBeanName.savedList}"/>
            <t:saveState value="#{pageBeanName.totalBouncseNotBasicSal}"/>
            <t:saveState value="#{pageBeanName.viewBounses}"/>
            <t:saveState value="#{pageBeanName.empCandExtraDataList}"/>
            <t:saveState value="#{fatwaReviewListBean.empCandExtraDataList}"/>
            <t:saveState value="#{addBounceBean.myTableData}"/>
            <t:saveState value="#{fatwaReviewListBean.pageDTO.empExtraDataValueDTO}"/>
            <t:saveState value="#{fatwaReviewListBean.bckBtnNavigationCase}"/>
            <t:saveState value="#{fatwaReviewListBean.saveStateList}"/>
            <t:saveState value="#{fatwaReviewListBean.configBean}"/>
            <t:saveState value="#{fatwaReviewListBean.caderName}"/>
            <t:saveState value="#{fatwaReviewListBean.titleFlageOfDiv}"/>
            <t:saveState value="#{fatwaReviewListBean.caderList}"/>
            <t:saveState value="#{fatwaReviewListBean.workCenterMode}"/>
            <t:saveState value="#{fatwaReviewListBean.jobMode}"/>
            <t:saveState value="#{fatwaReviewListBean.caderCode}"/>
            <t:saveState value="#{fatwaReviewListBean.suggestedCaderCode}"/>
            <t:saveState value="#{fatwaReviewListBean.suggestedCaderName}"/>
            <t:saveState value="#{fatwaReviewListBean.renderLovDiv}"/>
            <t:saveState value="#{fatwaReviewListBean.groupList}"/>
            <t:saveState value="#{fatwaReviewListBean.groupCode}"/>
            <t:saveState value="#{fatwaReviewListBean.raiseList}"/>
            <t:saveState value="#{fatwaReviewListBean.raiseCode}"/>
            <t:saveState value="#{fatwaReviewListBean.raiseName}"/>
            <t:saveState value="#{fatwaReviewListBean.degreeCode}"/>
            <t:saveState value="#{fatwaReviewListBean.degreeList}"/>
            <t:saveState value="#{fatwaReviewListBean.eqTypeTemp}"/>
            <t:saveState value="#{fatwaReviewListBean.suggestedRaiseCode}"/>
            <t:saveState value="#{fatwaReviewListBean.suggestedRaiseName}"/>
            <t:saveState value="#{fatwaReviewListBean.raiseDTO}"/>
            <t:saveState value="#{fatwaReviewListBean.raiseDTO}"/>
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
            <t:saveState value="#{fatwaReviewListBean.hireSystemMode}"/>
            <t:saveState value="#{fatwaReviewListBean.hasExperience}"/>
            <t:saveState value="#{fatwaReviewListBean.totalRewardAccepted}"/>
            <t:saveState value="#{fatwaReviewListBean.totalRewardSuggested}"/>
            <t:saveState value="#{fatwaReviewListBean.enableAddRewardButton}"/>
            <t:saveState value="#{fatwaReviewListBean.acceptedJobName}"/>
            
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
                
            <tiles:insert definition="addallowanceandApproveBuonc.page" flush="false"></tiles:insert>
        </t:aliasBean>
    </h:form>
    <t:panelGroup forceId="true" id="scriptpanel">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
</f:view>
