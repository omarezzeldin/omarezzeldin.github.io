<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

<f:view locale="#{shared_util.locale}">
    
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global"  var="globalResources"/>
    
    <h:form id="myForm" binding="#{fatwaSelectionListBean.frm}">
    
               
        <t:aliasBean alias="#{pageBeanName}" value="#{fatwaSelectionListBean}" id="alias1">
            <tiles:insert definition="fatwaSelectionList.page" flush="false">
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState  value="#{empListBean.sortColumn}" />
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                
                <t:saveState value="#{pageBeanName.selectedStageId}"/>
                
                 <t:saveState value="#{pageBeanName.bsnSortingColumnName}"/>
                <t:saveState value="#{pageBeanName.selectedCategory}"/>
                <t:saveState value="#{pageBeanName.categoryList}"/>
                
                <t:saveState value="#{pageBeanName.ministryList}"/>
                <t:saveState value="#{pageBeanName.selectedMinistry}"/>
                
                
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
                
                <%-- start paging --%>
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
                <t:saveState value="#{pageBeanName.calcPageMode}"/>
                <t:saveState value="#{pageBeanName.centeralEmployment}"/>
                <t:saveState value="#{pageBeanName.contracts}"/>
                <t:saveState value="#{pageBeanName.searchDTO1}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                 <t:saveState value="#{fatwaSelectionListBean.approveBtnShow}"/>
                <t:saveState value="#{fatwaSelectionListBean.saveSortingState}"/>
                 <t:saveState value="#{fatwaSelectionListBean.sortColumn}"/>
                <%-- end paging --%>
                
                   <t:saveState value="#{fatwaSelectionListBean.civilId}"/>
                  <t:saveState value="#{fatwaSelectionListBean.civilName}"/>
                  <t:saveState value="#{fatwaSelectionListBean.civilExist}"/>
                   <t:saveState value="#{fatwaSelectionListBean.validCivilId}"/>
                <t:saveState value="#{fatwaSelectionListBean.hideHasExprince}"/>
            </tiles:insert>
        </t:aliasBean>
    </h:form>
    
    <t:panelGroup forceId="true" id="scriptGeneratorGrp">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
    
</f:view>

<script type="text/javascript">
</script>