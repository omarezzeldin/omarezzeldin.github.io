<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
    <t:aliasBean alias="#{pageBeanName}" value="#{ilLegalCitizensListBean}">
        <h:form id="myForm" binding="#{pageBeanName.frm}">
             
            
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
            <t:saveState value="#{pageBeanName.pageDTO}"/>
            <t:saveState value="#{pageBeanName.listFilterDTO}"/>
            <t:saveState value="#{pageBeanName.filterDTO}"/>
            <t:saveState value="#{pageBeanName.searchDTO}"/>
            <t:saveState value="#{pageBeanName.requestStatust}"/>
            <t:saveState value="#{pageBeanName.sortingColumnList}"/>
            <t:saveState value="#{pageBeanName.searchName}"/>
            <t:saveState value="#{pageBeanName.selectedCategory}"/>
            <%-- new added--%>
            <%-- start paging--%>
                <t:saveState value="#{pageBeanName.bsnSortingColumnName}"/>
                <t:saveState value="#{pageBeanName.currentPageIndex}"/>
                <t:saveState value="#{pageBeanName.oldPageIndex}"/>
                <t:saveState value="#{pageBeanName.sorting}"/>
                <t:saveState value="#{pageBeanName.usingPaging}"/>
                <t:saveState value="#{pageBeanName.usingBsnPaging}"/>
                <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
                <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
                <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
                <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
                <t:saveState value="#{pageBeanName.singleSelection}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                    <t:saveState value="#{pageBeanName.catList}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{ilLegalCitizensListBean.saveSortingState}"/>
                 <t:saveState value="#{ilLegalCitizensListBean.sortColumn}"/>
                <%-- end paging--%>
            <%-- new added--%>
            
            <t:saveState value="#{pageBeanName.myDataTable}"/>
            <t:inputHidden value="#{pageBeanName.selectedListSize}"/>
            <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
            <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
            <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
            <tiles:insert definition="ilLegalCitizens.page" flush="false"></tiles:insert>
            <c2:scriptGenerator form="myForm"/>
        </h:form>
    </t:aliasBean>
</f:view>