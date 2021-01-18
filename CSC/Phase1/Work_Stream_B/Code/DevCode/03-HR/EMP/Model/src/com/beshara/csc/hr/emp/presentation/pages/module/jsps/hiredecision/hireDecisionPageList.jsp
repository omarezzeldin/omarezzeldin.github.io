<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    <t:aliasBean alias="#{pageBeanName}" value="#{hireDecisionAddBean}">
        <h:form id="myForm" binding="#{pageBeanName.frm}">
            <t:saveState value="#{hireDecisionListBean.myTableData}"/>
            <t:saveState value="#{hireDecisionListBean.highlightedDTOS}"/>
            <t:saveState value="#{hireDecisionListBean.searchMode}"/>
            <t:saveState value="#{hireDecisionListBean.selectedDTOS}"/>
            <t:saveState value="#{hireDecisionListBean.decisionBasicDTO}"/>
            <t:saveState value="#{hireDecisionListBean.fullColumnName}"/>
            <t:saveState value="#{hireDecisionListBean.sortAscending}"/>
            <t:saveState value="#{hireDecisionListBean.validToLink}"/>
            <t:saveState value="#{hireDecisionListBean.resList}"/>
            <t:saveState value="#{hireDecisionListBean.empExecuteListBean}"/>
            <t:saveState value="#{hireDecisionListBean.jdHelper}"/>
            <%-- start paging--%>
            <t:saveState value="#{hireDecisionListBean.currentPageIndex}"/>
            <t:saveState value="#{hireDecisionListBean.oldPageIndex}"/>
            <t:saveState value="#{hireDecisionListBean.sorting}"/>
            <t:saveState value="#{hireDecisionListBean.usingPaging}"/>
            <t:saveState value="#{hireDecisionListBean.updateMyPagedDataModel}"/>
            <t:saveState value="#{hireDecisionListBean.resettedPageIndex}"/>
            <t:saveState value="#{hireDecisionListBean.pagingRequestDTO}"/>
            <t:saveState value="#{hireDecisionListBean.pagingBean.totalListSize}"/>
            <t:saveState value="#{hireDecisionListBean.allList}"/>
            <t:saveState value="#{hireDecisionListBean.firstLevelHireTypesList}"/>
            <t:saveState value="#{hireDecisionListBean.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{hireDecisionListBean.pagingBean.preUpdatedDataModel}"/>
            <t:saveState value="#{hireDecisionListBean.loginedMinistrycode}"/>
            <t:saveState value="#{hireDecisionListBean.hireType}"/>
            <t:saveState value="#{hireDecisionListBean.hireTypeName}"/>
            <t:saveState value="#{hireDecisionListBean.enableViewCondDtls}"/>
            <t:saveState value="#{hireDecisionListBean.filteredList}"/>
            <t:saveState value="#{hireDecisionListBean.pageDTO}"/>
            <t:saveState value="#{hireDecisionListBean.saveSortingState}"/>
            <t:saveState value="#{hireDecisionListBean.sortColumn}"/>
            <%-- end paging--%>
            <t:saveState value="#{pageBeanName.pageDTO}"/>
            <t:saveState value="#{pageBeanName.salaryElementDTO}"/>
            <t:saveState value="#{pageBeanName.kuwaitCitizen}"/>
            <t:saveState value="#{pageBeanName.invalidNextYear}"/>
            <t:saveState value="#{pageBeanName.dateOfNextRaise}"/>
            <t:saveState value="#{pageBeanName.renderErrorHireDate}"/>
            <t:saveState value="#{pageBeanName.nextYear}"/>
            <t:saveState value="#{pageBeanName.previousYear}"/>
            <tiles:insert definition="hiredecisionAdd.page" flush="false"></tiles:insert>
            <t:panelGroup forceId="true" id="scriptPanel">
                <c2:scriptGenerator form="myForm"/>
            </t:panelGroup>
        </h:form>
    </t:aliasBean>
</f:view>