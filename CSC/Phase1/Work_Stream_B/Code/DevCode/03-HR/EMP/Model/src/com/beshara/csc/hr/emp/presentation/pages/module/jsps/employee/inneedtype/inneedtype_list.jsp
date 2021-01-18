<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    <h:form id="myForm" binding="#{estanaListBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{estanaListBean}">
            <t:aliasBean alias="#{jSearchBeanName}" value="#{estanaListBean.jobFilter}">
                <tiles:insert definition="inNeedTypeEmployees.page" flush="false">
                    <t:saveState value="#{estanaListBean.pageDTO}"/>
                     <t:saveState value="#{estanaListBean.errorJobCondition}"/>
                    <t:saveState value="#{pageBeanName.workCenterName}"/>
                    <t:saveState value="#{pageBeanName.cader}"/>
                    <t:saveState value="#{pageBeanName.group}"/>
                    <t:saveState value="#{pageBeanName.degree}"/>
                    <t:saveState value="#{pageBeanName.raise}"/>
                    <t:saveState value="#{pageBeanName.eqcader}"/>
                    <t:saveState value="#{pageBeanName.eqgroup}"/>
                    <t:saveState value="#{pageBeanName.eqdegree}"/>
                    <t:saveState value="#{pageBeanName.eqraise}"/>
                    <t:saveState value="#{pageBeanName.deptNotes}"/>
                    <t:saveState value="#{pageBeanName.selectionNotes}"/>
                    <t:saveState value="#{pageBeanName.fatwaNotes}"/>
                    <t:saveState value="#{pageBeanName.civilServiceNotes}"/>
                    <t:saveState value="#{pageBeanName.finalJobDTO}"/>
                    <t:saveState value="#{pageBeanName.salEqElemGuidDTO}"/>
                    <t:saveState value="#{pageBeanName.jobKey}"/>
                    <t:saveState value="#{pageBeanName.jobsDTO}"/>
                    <t:saveState value="#{pageBeanName.jobFilter}"/>
                    <t:saveState value="#{pageBeanName.jobMode}" id="jobMode"/>
                    <t:saveState value="#{pageBeanName.renderLovDiv}"/>
                    <t:saveState value="#{pageBeanName.renderJobDiv}"/>
                    <t:saveState value="#{pageBeanName.searchJobMode}"/>
                    <t:saveState value="#{pageBeanName.backActionMethodName}"/>
                    <t:saveState value="#{pageBeanName.bckBtnNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.jobSearchType}"/>
                    <t:saveState value="#{pageBeanName.hireStageCode}"/>
                    <t:saveState value="#{pageBeanName.hireStageName}"/>
                    <t:saveState value="#{pageBeanName.emp_final_job}"/>
                    <t:saveState value="#{pageBeanName.suggestedJobValue}"/>
                    <t:saveState value="#{pageBeanName.saveStateList}"/>
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
                    <t:saveState value="#{pageBeanName.enableSuggestJob}"/>
                    <%--<t:saveState value="#{pageBeanName.enableHireStage}"/>--%>
                    <t:saveState value="#{pageBeanName.enableMinistryNotes}"/>
                    <t:saveState value="#{pageBeanName.enableConvertToDewanBtn}"/>
                    <t:saveState value="#{pageBeanName.enableAcceptedJob}"/>
                    <t:saveState value="#{pageBeanName.enableSuggestAndConvertToDewanBtn}"/>
                    <t:saveState value="#{pageBeanName.enableEquvSalData}"/>
                    <t:saveState value="#{pageBeanName.acceptedTotalReward}"/>
            <t:saveState value="#{pageBeanName.bounsesList}"/>
                </tiles:insert>
                <t:panelGroup forceId="true" id="scriptPanelID">
                    <c2:scriptGenerator form="myForm"/>
                </t:panelGroup>
            </t:aliasBean>
        </t:aliasBean>
    </h:form>
</f:view>
