<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
    
    <f:loadBundle basename="com.beshara.csc.hr.crs.presentation.resources.crs" var="resourcesBundle"/>
    
    <h:form id="myForm" binding="#{proceedingCandidateListBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{proceedingCandidateListBean}" id="alias2">
            
            <tiles:insert definition="needsdetails.page" flush="false">
            </tiles:insert>
            
            <t:saveState value="#{proceedingCandidateListBean.saveSortingState}"/>
            <t:saveState value="#{proceedingCandidateListBean.fullColumnName}"/>
            <t:saveState value="#{proceedingCandidateListBean.sortAscending}"/>
            <t:saveState value="#{proceedingCandidateListBean.sortColumn}"/>
            
            <t:saveState value="#{proceedingCandidateListBean.miniCode}" id="saveStateID13"/>
            <t:saveState value="#{proceedingCandidateListBean.catCode}" id="saveStateID14"/>
            <t:saveState value="#{proceedingCandidateListBean.ministryList}" id="saveStateID123"/>
            <t:saveState value="#{proceedingCandidateListBean.categoryList}" id="saveStateID124"/>
            
            <t:saveState value="#{proceedingCandidateListBean.fromModuleName}"/>
            <t:saveState value="#{proceedingCandidateListBean.selectedCandType}"/>
            
            <%-- start paging --%>
            <t:saveState value="#{proceedingCandidateListBean.currentPageIndex}"/>
            <t:saveState value="#{proceedingCandidateListBean.oldPageIndex}"/>
            <t:saveState value="#{proceedingCandidateListBean.sorting}"/>
            <t:saveState value="#{proceedingCandidateListBean.usingPaging}"/>
            <t:saveState value="#{proceedingCandidateListBean.usingBsnPaging}"/>
            <t:saveState value="#{proceedingCandidateListBean.updateMyPagedDataModel}"/>
            <t:saveState value="#{proceedingCandidateListBean.resettedPageIndex}"/>
            <t:saveState value="#{proceedingCandidateListBean.pagingRequestDTO}"/>
            <t:saveState value="#{proceedingCandidateListBean.pagingBean.totalListSize}"/>
            <t:saveState value="#{proceedingCandidateListBean.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{proceedingCandidateListBean.pagingBean.preUpdatedDataModel}"/>
            <%-- end paging --%>
            
        </t:aliasBean>
    </h:form>
</f:view>