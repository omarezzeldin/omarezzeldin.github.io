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
    
        <h:form id="myForm" binding="#{contractEmployeesBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{contractEmployeesBean}">
        <t:aliasBean alias="#{jSearchBeanName}" value="#{contractEmployeesBean.jobFilter}">
            <tiles:insert definition="contractEmployees.page" flush="false">
            <t:saveState value="#{contractEmployeesBean.pageDTO}" id="maindto"/>
            <%--<t:saveState value="#{contractEmployeesBean.candidateDTO}" id="candidateDTO"/>--%>
            <t:saveState value="#{pageBeanName.workCenterName}" id="workCenterName"/>
            <t:saveState value="#{pageBeanName.cader}" id="cader"/>
            <t:saveState value="#{pageBeanName.group}" id="group"/>
            <t:saveState value="#{pageBeanName.degree}" id="degree"/>
            <t:saveState value="#{pageBeanName.raise}" id="raise"/>
            <t:saveState value="#{pageBeanName.eqcader}" id="eqcader"/>
            <t:saveState value="#{pageBeanName.eqgroup}" id="eqgroup"/>
            <t:saveState value="#{pageBeanName.eqdegree}" id="eqdegree"/>
            <t:saveState value="#{pageBeanName.eqraise}" id="eqraise"/>
            
             <t:saveState value="#{pageBeanName.errorJobCondition}" />
            <t:saveState value="#{pageBeanName.deptNotes}" id="deptNotes"/>
            <t:saveState value="#{pageBeanName.selectionNotes}" id="selectionNotes"/>
            <t:saveState value="#{pageBeanName.fatwaNotes}" id="fatwaNotes"/>
            <t:saveState value="#{pageBeanName.finalJobDTO}" id="finalJobDTO"/>
            <t:saveState value="#{pageBeanName.salEqElemGuidDTO}" id="salEqElemGuidDTO"/>
             <t:saveState value="#{pageBeanName.errorJobCondition1}"/>
     <t:saveState value="#{pageBeanName.jobFromMin}"/>
     <t:saveState value="#{pageBeanName.approvedJobValue}"/>
     <t:saveState value="#{pageBeanName.jobKey1}"/>
            <t:saveState value="#{pageBeanName.jobKey}" id="jobKey"/>
            <t:saveState value="#{pageBeanName.jobsDTO}" id="jobsDTO"/>
            <t:saveState value="#{pageBeanName.jobFilter}" id="jobFilter"/>
            <t:saveState value="#{pageBeanName.jobMode}" id="jobMode"/>
            <t:saveState value="#{pageBeanName.renderLovDiv}" id="renderLovDiv"/>
            <t:saveState value="#{pageBeanName.renderJobDiv}" id="renderJobDiv"/>
            <t:saveState value="#{pageBeanName.searchJobMode}" id="searchJobMode"/>
            <t:saveState value="#{pageBeanName.backActionMethodName}" id="backActionMethodName"/>
            <t:saveState value="#{pageBeanName.bckBtnNavigationCase}" id="bckBtnNavigationCase"/>
            
            
            <t:saveState value="#{pageBeanName.jobSearchType}" id="jobSearchType"/>
            <t:saveState value="#{pageBeanName.hireStageCode}" id="hireStageCode"/>
            <t:saveState value="#{pageBeanName.hireStageName}" id="hireStageName"/>
            <t:saveState value="#{pageBeanName.emp_final_job}" id="emp_final_job"/>
            <t:saveState value="#{pageBeanName.suggestedJobValue}" id="suggestedJobValue"/>
            <t:saveState value="#{pageBeanName.saveStateList}"/>
            <t:saveState value="#{pageBeanName.acceptedTotalReward}"/>
            <t:saveState value="#{pageBeanName.bounsesList}"/>
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
            </tiles:insert>
            <t:panelGroup forceId="true" id="scriptPanelID">
                <c2:scriptGenerator form="myForm"/>
            </t:panelGroup>
            </t:aliasBean>
            </t:aliasBean>
        </h:form>
    <f:verbatim>
        <script type="text/javascript">

        </script>
    </f:verbatim>
</f:view>
