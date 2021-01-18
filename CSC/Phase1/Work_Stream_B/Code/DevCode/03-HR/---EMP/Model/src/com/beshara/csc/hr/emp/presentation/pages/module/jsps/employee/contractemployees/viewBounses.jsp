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
            <t:saveState value="#{contractEmployeesBean.pageDTO}" />
            <t:saveState value="#{contractEmployeesBean.workCenterName}" />
            <t:saveState value="#{contractEmployeesBean.cader}" />
            <t:saveState value="#{contractEmployeesBean.group}" />
            <t:saveState value="#{contractEmployeesBean.degree}" />
            <t:saveState value="#{contractEmployeesBean.raise}" />
            <t:saveState value="#{contractEmployeesBean.eqcader}" />
            <t:saveState value="#{contractEmployeesBean.eqgroup}" />
            <t:saveState value="#{contractEmployeesBean.eqdegree}" />
            <t:saveState value="#{contractEmployeesBean.eqraise}" />
            <t:saveState value="#{contractEmployeesBean.errorJobCondition}"/>
            <t:saveState value="#{contractEmployeesBean.deptNotes}" />
            <t:saveState value="#{contractEmployeesBean.selectionNotes}" />
            <t:saveState value="#{contractEmployeesBean.fatwaNotes}"/>
            <t:saveState value="#{contractEmployeesBean.finalJobDTO}"/>
            <t:saveState value="#{contractEmployeesBean.salEqElemGuidDTO}" />
            <t:saveState value="#{contractEmployeesBean.jobKey}" />
            <t:saveState value="#{contractEmployeesBean.jobsDTO}" />
            <t:saveState value="#{contractEmployeesBean.jobFilter}" />
            <t:saveState value="#{contractEmployeesBean.jobMode}"/>
            <t:saveState value="#{contractEmployeesBean.renderLovDiv}" />
            <t:saveState value="#{contractEmployeesBean.renderJobDiv}" />
            <t:saveState value="#{contractEmployeesBean.searchJobMode}" />
            <t:saveState value="#{contractEmployeesBean.backActionMethodName}"/>
            <t:saveState value="#{contractEmployeesBean.bckBtnNavigationCase}" />
            <t:saveState value="#{contractEmployeesBean.jobSearchType}" />
            <t:saveState value="#{contractEmployeesBean.hireStageCode}" />
            <t:saveState value="#{contractEmployeesBean.hireStageName}" />
            <t:saveState value="#{contractEmployeesBean.emp_final_job}" />
            <t:saveState value="#{contractEmployeesBean.suggestedJobValue}" />
            <t:saveState value="#{contractEmployeesBean.saveStateList}"/>
            <t:saveState value="#{contractEmployeesBean.acceptedTotalReward}"/>
            <t:saveState value="#{contractEmployeesBean.bounsesList}"/>
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
            <tiles:insert definition="viewallowanceandBuoncContract.page" flush="false"></tiles:insert>
        </t:aliasBean>
    </h:form>
    <%-- <t:panelGroup forceId="true" id="scriptpanel"> <c2:scriptGenerator form="myForm"/> </t:panelGroup>--%>
</f:view>
