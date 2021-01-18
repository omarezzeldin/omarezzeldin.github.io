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
            <t:saveState value="#{estanaListBean.pageDTO}"/>
            <t:saveState value="#{estanaListBean.errorJobCondition}"/>
            <t:saveState value="#{estanaListBean.workCenterName}"/>
            <t:saveState value="#{estanaListBean.cader}"/>
            <t:saveState value="#{estanaListBean.group}"/>
            <t:saveState value="#{estanaListBean.degree}"/>
            <t:saveState value="#{estanaListBean.raise}"/>
            <t:saveState value="#{estanaListBean.eqcader}"/>
            <t:saveState value="#{estanaListBean.eqgroup}"/>
            <t:saveState value="#{estanaListBean.eqdegree}"/>
            <t:saveState value="#{estanaListBean.eqraise}"/>
            <t:saveState value="#{estanaListBean.deptNotes}"/>
            <t:saveState value="#{estanaListBean.selectionNotes}"/>
            <t:saveState value="#{estanaListBean.fatwaNotes}"/>
            <t:saveState value="#{estanaListBean.civilServiceNotes}"/>
            <t:saveState value="#{estanaListBean.finalJobDTO}"/>
            <t:saveState value="#{estanaListBean.salEqElemGuidDTO}"/>
            <t:saveState value="#{estanaListBean.jobKey}"/>
            <t:saveState value="#{estanaListBean.jobsDTO}"/>
            <t:saveState value="#{estanaListBean.jobFilter}"/>
            <t:saveState value="#{estanaListBean.jobMode}" id="jobMode"/>
            <t:saveState value="#{estanaListBean.renderLovDiv}"/>
            <t:saveState value="#{estanaListBean.renderJobDiv}"/>
            <t:saveState value="#{estanaListBean.searchJobMode}"/>
            <t:saveState value="#{estanaListBean.backActionMethodName}"/>
            <t:saveState value="#{estanaListBean.bckBtnNavigationCase}"/>
            <t:saveState value="#{estanaListBean.jobSearchType}"/>
            <t:saveState value="#{estanaListBean.hireStageCode}"/>
            <t:saveState value="#{estanaListBean.hireStageName}"/>
            <t:saveState value="#{estanaListBean.emp_final_job}"/>
            <t:saveState value="#{estanaListBean.suggestedJobValue}"/>
            <t:saveState value="#{estanaListBean.saveStateList}"/>
            <t:saveState value="#{empListBean.pageDTO}"/>
            <t:saveState value="#{empListBean.filteredList}"/>
            <t:saveState value="#{empListBean.myTableData}"/>
            <t:saveState value="#{estanaListBean.enableSuggestJob}"/>
            <t:saveState value="#{estanaListBean.enableMinistryNotes}"/>
            <t:saveState value="#{estanaListBean.enableConvertToDewanBtn}"/>
            <t:saveState value="#{estanaListBean.enableAcceptedJob}"/>
            <t:saveState value="#{estanaListBean.enableSuggestAndConvertToDewanBtn}"/>
            <t:saveState value="#{estanaListBean.enableEquvSalData}"/>
            <t:saveState value="#{estanaListBean.acceptedTotalReward}"/>
            <t:saveState value="#{estanaListBean.bounsesList}"/>
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
            <tiles:insert definition="viewallowanceandBuoncEstana.page" flush="false"></tiles:insert>
        </t:aliasBean>
    </h:form>
    <%-- <t:panelGroup forceId="true" id="scriptpanel"> <c2:scriptGenerator form="myForm"/> </t:panelGroup>--%>
</f:view>
