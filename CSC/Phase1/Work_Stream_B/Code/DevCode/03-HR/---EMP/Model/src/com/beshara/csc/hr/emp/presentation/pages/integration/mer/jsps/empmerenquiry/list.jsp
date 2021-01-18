<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:view locale="#{shared_util.locale}">
    <%--<f:loadBundle basename="com.beshara.csc.hr.mer.presentation.resources.mer" var="resourcesBundle"/>--%>
    <f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="integrationBundle"/>
    <%--<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>--%>
    <%--<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>--%>
    <h:form id="myForm" binding="#{empMerQueryBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{empMerQueryBean}">
            <tiles:insert flush="false" definition="empMerQuery.page"/>
            <t:saveState value="#{pageBeanName.empStopFlag}"/>
            <t:saveState value="#{pageBeanName.myTableData}"/>
            <t:saveState value="#{pageBeanName.dayRadio}"/>            
            <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.pageDTO}"/>
            <t:saveState value="#{pageBeanName.showEdit}"/>
            <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
            <t:saveState value="#{pageBeanName.pageMode}"/>
            <t:saveState value="#{pageBeanName.civilId}"/>
            <t:saveState value="#{pageBeanName.civilIdNotReal}"/>
            <t:saveState value="#{pageBeanName.employeesDTO}"/>
            <t:saveState value="#{pageBeanName.searchDTO}"/>
            <t:saveState value="#{pageBeanName.benfitsList}"/>
            <t:saveState value="#{pageBeanName.deductionsList}"/>
            <t:saveState value="#{pageBeanName.selectedSearchBenType}"/>
            <t:saveState value="#{pageBeanName.civilExist}"/>
            <t:saveState value="#{pageBeanName.benfitsListSize}"/>
            <t:saveState value="#{pageBeanName.deductionsListSize}"/>
            <t:saveState value="#{pageBeanName.basicSalaryCode}"/>
            <t:saveState value="#{pageBeanName.employeeHelper}" id="employeeHelper1"/>
            <t:saveState value="#{pageBeanName.degree}"/>
            
            <t:saveState value="#{pageBeanName.empHireStatus}" id="empHireStatus1"/>
            <t:saveState value="#{pageBeanName.salEmpDTOList}"/>
            <t:saveState value="#{pageBeanName.fromMerEmpWaitApproval}"/>
            <t:saveState value="#{pageBeanName.specialRaiseDetailsList}"/>
            <t:saveState value="#{empMerQueryBean.customDiv1TitleKey}"/>
            <t:saveState value="#{empMerQueryBean.tempBenfitsList}"/>
            <t:saveState value="#{empMerQueryBean.promotionDiffValue}"/>
             <t:saveState value="#{empMerQueryBean.empStatusForSalDTO}"/>
            <t:saveState value="#{empMerQueryBean.salaryDiffValue}"/> 
        </t:aliasBean>
                
        <t:panelGroup forceId="true" id="scriptpanel">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
    
    </h:form>
</f:view>
