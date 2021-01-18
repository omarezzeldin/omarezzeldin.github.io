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
    <t:aliasBean alias="#{pageBeanName}" value="#{civilServiceReviewBean}">
        <t:aliasBean alias="#{jSearchBeanName}" value="#{civilServiceReviewBean.jobFilter}">
            <h:form id="myForm" binding="#{pageBeanName.frm}">
                <tiles:insert definition="civilServiceDef.page" flush="false">
                    <t:saveState value="#{pageBeanName.pageDTO}"/>
                    <t:saveState value="#{civilServiceReviewBean.jobFilter}"/>
                     <t:saveState value="#{pageBeanName.bgtTypesDTO}"/>
                    <t:saveState value="#{pageBeanName.bckBtnNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.saveStateList}"/>
                    <t:saveState value="#{pageBeanName.configBean}"/>
                    <t:saveState value="#{pageBeanName.caderName}"/>
                    <t:saveState value="#{pageBeanName.titleFlageOfDiv}"/>
                    <t:saveState value="#{pageBeanName.caderList}"/>
                    <t:saveState value="#{pageBeanName.workCenterMode}"/>
                    <t:saveState value="#{pageBeanName.jobMode}"/>
                    <t:saveState value="#{pageBeanName.caderCode}"/>
                    <t:saveState value="#{pageBeanName.suggestedCaderCode}"/>
                    <t:saveState value="#{pageBeanName.suggestedCaderName}"/>
                    <t:saveState value="#{pageBeanName.renderLovDiv}"/>
                    <t:saveState value="#{pageBeanName.groupList}"/>
                    <t:saveState value="#{pageBeanName.groupCode}"/>
                    <t:saveState value="#{pageBeanName.raiseList}"/>
                    <t:saveState value="#{pageBeanName.raiseCode}"/>
                    <t:saveState value="#{pageBeanName.raiseName}"/>
                    <t:saveState value="#{pageBeanName.degreeCode}"/>
                    <t:saveState value="#{pageBeanName.degreeList}"/>
                    <t:saveState value="#{pageBeanName.eqTypeTemp}"/>
                    <t:saveState value="#{pageBeanName.suggestedRaiseCode}"/>
                    <t:saveState value="#{pageBeanName.suggestedRaiseName}"/>
                    <t:saveState value="#{pageBeanName.errorJobCondition}"/>
                    <t:saveState value="#{treeDivBean.myTableData}"/>
                    <t:saveState value="#{pageBeanName.workCenterHasJobs}"/>
                    <t:saveState value="#{pageBeanName.jobList}"/>
                    <t:saveState value="#{pageBeanName.jobKey}"/>
                    <t:saveState value="#{pageBeanName.budgetTypeList}"/>
                    <t:saveState value="#{pageBeanName.ministryNotes}"/>
                    <t:saveState value="#{pageBeanName.minWorkCenter}"/>
                    <t:saveState value="#{pageBeanName.workCenterName}"/>
                    <t:saveState value="#{pageBeanName.jobNameForMin}"/>
                    <t:saveState value="#{pageBeanName.backActionMethodName}"/>
                    <t:saveState value="#{pageBeanName.pageBeanName}"/>
                    <t:saveState value="#{pageBeanName.stageId}"/>
                    <t:saveState value="#{pageBeanName.hireStageConst}"/>
                    <t:saveState value="#{pageBeanName.selectedMinistery}"/>
                    <t:saveState value="#{pageBeanName.invalidMinFileNo}"/>
                    <t:saveState value="#{pageBeanName.configBean.disableCadre}"/>
                    <t:saveState value="#{pageBeanName.groupName}"/>
                    <t:saveState value="#{pageBeanName.suggestedGroupName}"/>
                    <t:saveState value="#{pageBeanName.suggestedGroupCode}"/>
                    <t:saveState value="#{pageBeanName.suggestedDegreeCode}"/>
                    <t:saveState value="#{pageBeanName.suggestedDegreeName}"/>
                    <t:saveState value="#{pageBeanName.approvedBgtTypeCode}"/>
                    <t:saveState value="#{pageBeanName.approvedBgtTypeName}"/>
                    <t:saveState value="#{pageBeanName.acceptedJobKey}"/>
                    <t:saveState value="#{pageBeanName.acceptedJobName}"/>
                    <t:saveState value="#{pageBeanName.acceptedJobDTO}"/>
                    <t:saveState value="#{pageBeanName.totalRewardAccepted}"/>
                    <t:saveState value="#{pageBeanName.enableAddRewardButton}"/>
                    <t:saveState value="#{pageBeanName.empCandExtraDataList}"/>
                    <t:saveState value="#{pageBeanName.raiseDTO}"/>
                    <t:saveState value="#{addBounceBean.myTableData}"/>
                    <t:saveState value="#{addBounceBean.totalBounces}"/>
                    <t:saveState value="#{addBounceBean.totalBouncseNotBasicSal}"/>
                     <t:saveState value="#{pageBeanName.minCode}"/>
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