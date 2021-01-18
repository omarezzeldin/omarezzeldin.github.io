<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <t:aliasBean alias="#{pageBeanName}" value="#{hireSearchListBean}">
        <h:form id="myForm" binding="#{pageBeanName.frm}">
            <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
            <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
            <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
            <t:saveState value="#{pageBeanName.myTableData}"/>
            <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.sortAscending}"/>
            <t:saveState value="#{pageBeanName.fullColumnName}"/>
            <t:saveState value="#{pageBeanName.allList}"/>
            <t:saveState value="#{pageBeanName.empEmployeesSearchDTO}"/>
            <t:saveState value="#{pageBeanName.sortingColumnList}"/>
            <%-- start paging--%>
            <t:saveState value="#{pageBeanName.currentPageIndex}"/>
            <t:saveState value="#{pageBeanName.oldPageIndex}"/>
            <t:saveState value="#{pageBeanName.sorting}"/>
            <t:saveState value="#{pageBeanName.usingPaging}"/>
            <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
            <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
            <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
            <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
            <t:saveState value="#{pageBeanName.showEnterSearch}"/>
            <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
            <%-- end paging--%>
            <t:saveState value="#{pageBeanName.empHireTypesValue}"/>
            <t:saveState value="#{pageBeanName.firstLevelHireTypesList}"/>
            <tiles:insert definition="hiresearch.page" flush="false"></tiles:insert>
            <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID"
                           value="hireDateFrom_Date,200,150:hireDateTo_Date,200,150"/>
            <t:panelGroup forceId="true" id="scriptPanel">
                <c2:scriptGenerator form="myForm"/>
            </t:panelGroup>
        </h:form>
    </t:aliasBean>
</f:view>
