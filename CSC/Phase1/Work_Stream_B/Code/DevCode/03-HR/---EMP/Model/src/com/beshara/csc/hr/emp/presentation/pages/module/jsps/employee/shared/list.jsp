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
    <t:aliasBean alias="#{pageBeanName}" value="#{reviewMinListBean1}">
    <t:aliasBean alias="#{jSearchBeanName}" value="#{reviewMinListBean1.jobFilter}">
        <h:form id="myForm" binding="#{pageBeanName.frm}">
            <tiles:insert definition="reviewMinList1.page" flush="false">
                <t:saveState value="#{reviewMinListBean1.pageDTO}"/>
                <t:saveState value="#{reviewMinListBean1.bgtTypesDTO}"/>
                <t:saveState value="#{pageBeanName.pageDTO.empExtraDataValueDTO}"/>
                <t:saveState value="#{pageBeanName.bckBtnNavigationCase}"/>
                <t:saveState value="#{pageBeanName.saveStateList}"/>
                <t:saveState value="#{pageBeanName.configBean}"/>
                <t:saveState value="#{pageBeanName.titleFlageOfDiv}"/>
                <t:saveState value="#{pageBeanName.workCenterMode}"/>
                <t:saveState value="#{pageBeanName.jobMode}"/>
                <t:saveState value="#{pageBeanName.renderLovDiv}"/>
                <t:saveState value="#{pageBeanName.caderList}"/>
                <t:saveState value="#{pageBeanName.groupList}"/>
                <t:saveState value="#{pageBeanName.degreeList}"/>
                <t:saveState value="#{pageBeanName.raiseList}"/>
                <t:saveState value="#{pageBeanName.suggestedCaderCode}"/>
                <t:saveState value="#{pageBeanName.suggestedCaderName}"/>
                <t:saveState value="#{pageBeanName.suggestedGroupCode}"/>
                <t:saveState value="#{pageBeanName.suggestedGroupName}"/>
                <t:saveState value="#{pageBeanName.suggestedDegreeCode}"/>
                <t:saveState value="#{pageBeanName.suggestedDegreeName}"/>
                <t:saveState value="#{pageBeanName.suggestedRaiseCode}"/>
                <t:saveState value="#{pageBeanName.suggestedRaiseName}"/>
                <t:saveState value="#{pageBeanName.acceptedCaderCode}"/>
                <t:saveState value="#{pageBeanName.acceptedCaderName}"/>
                <t:saveState value="#{pageBeanName.acceptedGroupCode}"/>
                <t:saveState value="#{pageBeanName.acceptedGroupName}"/>
                <t:saveState value="#{pageBeanName.acceptedDegreeCode}"/>
                <t:saveState value="#{pageBeanName.acceptedDegreeName}"/>
                <t:saveState value="#{pageBeanName.acceptedRaiseCode}"/>
                <t:saveState value="#{pageBeanName.acceptedRaiseName}"/>
                <t:saveState value="#{pageBeanName.jobKey}"/>
                <t:saveState value="#{pageBeanName.eqTypeTemp}"/>
                <t:saveState value="#{treeDivBean.myTableData}"/>
                <t:saveState value="#{pageBeanName.workCenterHasJobs}"/>
                <t:saveState value="#{pageBeanName.jobList}"/>
                <t:saveState value="#{pageBeanName.budgetTypeList}"/>
                <%--<t:saveState value="#{pageBeanName.workMinistriesList}"/>--%>
                <%--<t:saveState value="#{pageBeanName.workMinistriesList}"/>--%>
                <t:saveState value="#{pageBeanName.minWorkCenter}"/>
                <t:saveState value="#{pageBeanName.workCenterName}"/>
                <t:saveState value="#{pageBeanName.jobNameForMin}"/>
                <t:saveState value="#{pageBeanName.suggestedJobValue}"/>
                <t:saveState value="#{pageBeanName.acceptedJobValue}"/>
                <t:saveState value="#{pageBeanName.suggestedJobCode}"/>
                <t:saveState value="#{pageBeanName.acceptedJobCode}"/>
                <t:saveState value="#{pageBeanName.backActionMethodName}"/>
                <t:saveState value="#{pageBeanName.pageBeanName}"/>
                <t:saveState value="#{pageBeanName.stageId}"/>
                <t:saveState value="#{pageBeanName.hireStageConst}"/>
                <t:saveState value="#{pageBeanName.selectedMinistery}"/>
                <t:saveState value="#{pageBeanName.invalidMinFileNo}"/>
                <t:saveState value="#{pageBeanName.errorJobCondition}"/>
                <t:saveState value="#{pageBeanName.validCond}"/>
                <t:saveState value="#{pageBeanName.jobFromMin}"/>
                <t:saveState value="#{empListBean.pageDTO}"/>
                <t:saveState value="#{empListBean.filteredList}"/>
                 <t:saveState value="#{empListBean.myTableData}"/>
                         <t:saveState value="#{pageBeanName.errorJobCondition1}"/>
     <t:saveState value="#{pageBeanName.jobFromMin}"/>
     <t:saveState value="#{pageBeanName.approvedJobValue}"/>
     <t:saveState value="#{pageBeanName.jobKey1}"/>
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
             
                <tiles:put name="titleDelDiv" type="string" value="${'empty_label'}"/>
            </tiles:insert>
            <t:panelGroup forceId="true" id="scriptPanelID">
                <c2:scriptGenerator form="myForm"/>
            </t:panelGroup>
        </h:form>
        </t:aliasBean>
    </t:aliasBean>
    <f:verbatim>
        <script type="text/javascript">
  function calcNextRaiseJs() {
              if (document.getElementById('employees_dateOfNextRaise') == null) {
                  return;// not kwt
              }
              var oldValue = document.getElementById('hireDateHF').value;
              var newValue = document.getElementById('employees_hireDate1').value;
              if (oldValue == newValue) {
                  return;// no updates
              }
              calcNextRaiseJsFunction();
              document.getElementById('hireDateHF').value = newValue;
          }
        </script>
    </f:verbatim>
</f:view>
