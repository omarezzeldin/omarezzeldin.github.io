<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.crs.presentation.resources.crs" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="empBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global"  var="globalResources"/>
    <h:form id="myForm" binding="#{proceedingCandidateListBean.frm}">
    <t:messages showDetail="true"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{proceedingCandidateListBean}" id="alias1">
            <tiles:insert definition="proceedcandidateslist.page" flush="false">
                <t:saveState value="#{pageBeanName.caderCode}" id="saveStateID1"/>
                <t:saveState value="#{pageBeanName.groupCode}" id="saveStateID2"/>
                <t:saveState value="#{pageBeanName.degreeCode}" id="saveStateID3"/>
                <t:saveState value="#{pageBeanName.raiseCode}" id="saveStateID4"/>
                <t:saveState value="#{pageBeanName.caderList}" id="saveStateID5"/>
                <t:saveState value="#{pageBeanName.groupList}" id="saveStateID6"/>
                <t:saveState value="#{pageBeanName.degreeList}" id="saveStateID7"/>
                <t:saveState value="#{pageBeanName.raiseList}" id="saveStateID8"/>
                <t:saveState value="#{pageBeanName.budgetTypeList}" id="saveStateID9"/>
                <t:saveState value="#{pageBeanName.budgetList}" id="saveStateID10"/>
                <t:saveState value="#{pageBeanName.categoryList}" id="saveStateID11"/>
                <t:saveState value="#{pageBeanName.ministryList}" id="saveStateID12"/>
                <t:saveState value="#{pageBeanName.miniCode}" id="saveStateID13"/>
                <t:saveState value="#{pageBeanName.catCode}" id="saveStateID14"/>
                <t:saveState value="#{pageBeanName.cndRejLetterNo}" id="saveStateID15"/>
                <t:saveState value="#{pageBeanName.cndRejDate}" id="saveStateID16"/>
                <t:saveState value="#{pageBeanName.fromModuleName}" id="fromModuleNameSt"/>
                
                <t:saveState value="#{pageBeanName.selectedCandType}" id="selectedCandTypeSt"/>
                
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState  value="#{empListBean.sortColumn}" />
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.candidates_list}"/>
                <t:saveState value="#{pageBeanName.bgtTypesDTO}" />
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.sortedTable}"/>
                
                <t:saveState value="#{pageBeanName.hideHasExprince}"/>
                
                <%-- start paging --%>
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
                <%-- end paging --%>
                
            </tiles:insert>
        </t:aliasBean>
    </h:form>
    <c2:scriptGenerator form="myForm"/>
</f:view>

<script type="text/javascript"> 
    if( (!isVisibleComponent('divSearch'))){
        setFocusOnElement('categoryList');
    }
</script>