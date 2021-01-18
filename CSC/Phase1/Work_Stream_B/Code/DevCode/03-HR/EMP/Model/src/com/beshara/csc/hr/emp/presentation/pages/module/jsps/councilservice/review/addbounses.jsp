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
            <t:saveState value="#{pageBeanName.empCandExtraDataList}"/>
            <t:saveState value="#{civilServiceReviewBean.empCandExtraDataList}"/>
            <t:saveState value="#{addBounceBean.myTableData}"/>
            <t:saveState value="#{civilServiceReviewBean.pageDTO}"/>
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
            <t:saveState value="#{civilServiceReviewBean.workCenterHasJobs}"/>
            <t:saveState value="#{civilServiceReviewBean.jobList}"/>
            <t:saveState value="#{civilServiceReviewBean.jobKey}"/>
            <t:saveState value="#{civilServiceReviewBean.budgetTypeList}"/>
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
            <t:saveState value="#{civilServiceReviewBean.raiseDTO}"/>
            <t:saveState value="#{civilServiceReviewBean.ministryNotes}"/>
            <tiles:insert definition="addallowanceandBuoncEstana.page" flush="false"></tiles:insert>
        </t:aliasBean>
    </h:form>
    <t:panelGroup forceId="true" id="scriptpanel">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
</f:view>
