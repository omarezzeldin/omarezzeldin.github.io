<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{insurExcludedEmps.frm}">
        <f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="intgBundle"/>
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{insurExcludedEmps}">
            <tiles:insert definition="insurexcludedgulfempslist.page" flush="false"></tiles:insert>
            <t:saveState value="#{pageBeanName.countries}"/>
            <t:saveState value="#{pageBeanName.selCountry}"/>
            <t:saveState value="#{pageBeanName.selectedGrpCountry}"/>
            <t:saveState value="#{pageBeanName.selectedCountryId}"/>
            <t:saveState value="#{pageBeanName.singleSelection}"/>
            <t:saveState value="#{pageBeanName.myTableData}"/>
            <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.fullColumnName}"/>
            <t:saveState value="#{pageBeanName.sortAscending}"/>
            <%-- Start Paging --%>
            <t:saveState value="#{pageBeanName.currentPageIndex}"/>
            <t:saveState value="#{pageBeanName.oldPageIndex}"/>
            <t:saveState value="#{pageBeanName.sorting}"/>
            <t:saveState value="#{pageBeanName.usingPaging}"/>
            
            <t:saveState value="#{insurExcludedEmps.kwtPage}"/>
            <t:saveState value="#{insurExcludedEmps.initiated}"/>
             <%-- Start BSN Paging --%>
            <t:saveState value="#{pageBeanName.filterDTO}"/>
            <t:saveState value="#{pageBeanName.searchDTO}"/>
            <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
            <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
            <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
            <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
            
            <t:saveState value="#{pageBeanName.ascending}"/>
            <%--<t:saveState value="#{pageBeanName.sortcolumnName}"/>--%>
            <t:saveState value="#{pageBeanName.bsnSortingColumnName}"/>
            <t:saveState value="#{pageBeanName.sortedTable}"/>
            <t:saveState value="#{pageBeanName.usingBsnPaging}"/>
            <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
            <t:saveState value="#{pageBeanName.indexArray}"/>
            <%-- End BSN Paging --%>
            <t:saveState value="#{pageBeanName.pageMode}"/>
                <t:saveState value="#{pageBeanName.stopDate}"/>
                <t:saveState value="#{pageBeanName.fromDate}"/>
        </t:aliasBean>
    </h:form>
     <t:panelGroup forceId="true" id="scriptGeneratorPanel">
        <c:scriptGenerator form="myForm"/>
    </t:panelGroup>
</f:view>