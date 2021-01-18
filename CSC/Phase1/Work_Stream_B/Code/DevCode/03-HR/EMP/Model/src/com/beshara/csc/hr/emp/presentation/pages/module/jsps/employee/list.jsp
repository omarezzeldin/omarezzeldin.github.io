<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
<script type="text/javascript"> 
        var ctxPath = '<%=request.getContextPath()%>';
</script>

    <h:form id="myForm" binding="#{empListBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{empListBean}">
            <%--<t:saveState value="#{empMaintainBean.savedHireType}"/>--%>
            <%--<t:saveState value="#{empMainDataBean.bgtProgramName}"/>--%>
            <%--<t:saveState value="#{empMaintainBean.savedDocumentList}"/>--%>
            <%--<t:saveState value="#{empMaintainBean.mainDataOnlyFlag}"/>--%>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.filteredList}"/>
            <%--<t:saveState value="#{empMaintainBean.selectedHireTypeKey}" id="selectedHireTypeKey2"/>--%>
            <%--<t:saveState value="#{empMainDataBean.selectedHireTypeCode}" id="selectedHireTypeCode22"/>--%>
            <%--<t:saveState value="#{empMaintainBean.mainDataOnlyFlag}"/>--%>
            <t:saveState value="#{pageBeanName.fullColumnName}"/>
            <t:saveState value="#{pageBeanName.sortAscending}"/>
            <%--<t:saveState value="#{empMainDataBean.dataDisabledIfEmpFromCRS}"/>--%>
            <%--<t:saveState value="#{empMainDataBean.fromEmpList}"/>--%>
            <%--<t:saveState value="#{qulListIntegrationBean.needAddQualification}"/>--%>
            <%-- start paging--%>
            <t:saveState value="#{pageBeanName.myTableData}"/>
            <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
            <t:saveState value="#{pageBeanName.myDataTable}"/>
            <t:inputHidden value="#{pageBeanName.selectedListSize}"/>
            <t:saveState value="#{pageBeanName.currentPageIndex}"/>
            <t:saveState value="#{pageBeanName.oldPageIndex}"/>
            <t:saveState value="#{pageBeanName.sorting}"/>
            <t:saveState value="#{pageBeanName.usingPaging}"/>
            <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
            <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
            <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
            <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
            <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
            <%-- end paging--%>
            <t:saveState value="#{empListBean.saveSortingState}"/>
            <t:saveState value="#{empListBean.sortColumn}"/>
            <%-- Start Kamal--%>
            <t:saveState value="#{empListBean.civilId}"/>
            <t:saveState value="#{empListBean.pageDTO}"/>
            <t:saveState value="#{empListBean.filteredList}"/>
            <%--<t:saveState value="#{empListBean.selectedHireType}"/>--%>
            <t:saveState value="#{empListBean.bsnSortingColumnName}"/>
            <t:saveState value="#{empListBean.selectedBackHireType}"/>
            <%-- <t:saveState value="#{addCandidateMaintainBean.backEmpDTO}"/>--%>
            <%-- <t:saveState value="#{qualificationAddHelperBean.prepareMethodName}"/>--%>
            <%-- <t:saveState value="#{qualificationAddHelperBean.returnMethodName}"/>--%>
            <%-- End Kamal--%>
            <t:saveState value="#{empListBean.reportUrl}"/>
            <tiles:insert definition="empList.page" flush="false">
            <t:inputHidden id="reportUrl" forceId="true" value="#{empListBean.reportUrl}"/>
            </tiles:insert>
        </t:aliasBean>
    </h:form>
</f:view>