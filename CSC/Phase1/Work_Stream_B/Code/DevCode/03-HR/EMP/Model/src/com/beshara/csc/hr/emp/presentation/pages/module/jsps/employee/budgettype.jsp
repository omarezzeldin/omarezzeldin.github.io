<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
 <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
 <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
 <h:form id="myForm" binding="#{budgetTypeList.frm}">
  <t:aliasBean alias="#{pageBeanName}" value="#{budgetTypeList}">
  <tiles:insert definition="budgetType.page" flush="false">
  <t:saveState value="#{pageBeanName.bugetProgramList}" />
  <t:saveState value="#{pageBeanName.frmBudgetType}" />
  <t:saveState value="#{pageBeanName.wrkCenterList}" />
  <t:saveState value="#{pageBeanName.searchNotDone}" />
  <t:saveState value="#{pageBeanName.selectedListSize}" />
  <t:saveState value="#{pageBeanName.selectedDTOS}" />
  <t:saveState value="#{pageBeanName.budgetProgramValue}" />
  <t:saveState value="#{pageBeanName.bgtTypesDTOValue}" />
  <t:saveState value="#{pageBeanName.selectedWorkCentersValue}" />
  <t:saveState value="#{pageBeanName.toBudgetTypeValue}" />
  <t:saveState value="#{pageBeanName.wrkCenter" />
  <t:saveState value="#{pageBeanName.myTableData}"/>
  <t:saveState value="#{pageBeanName.searchMode}"/>
  <t:saveState value="#{pageBeanName.srchAgainBtn}"/>
  

  </tiles:insert>
  </t:aliasBean>
  <t:panelGroup id="bottomGroup">
    <c2:scriptGenerator form="myForm" />
    </t:panelGroup>
 </h:form>
</f:view>
