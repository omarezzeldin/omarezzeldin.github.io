<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{correctHireProceduresBean.frm}">
        <f:loadBundle basename="com.beshara.csc.hr.adc.presentation.resources.adc_ar" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{correctHireProceduresBean}">
            <tiles:insert flush="false" definition="correctHireProcedures.page">
                <t:saveState value="#{pageBeanName.civilExist}"/>
                <t:saveState value="#{pageBeanName.validCivilId}"/>
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
                <t:saveState value="#{pageBeanName.procedureType}"/>
                <t:saveState value="#{pageBeanName.candidateCode}"/>
                <t:saveState value="#{pageBeanName.reasonsDataList}"/>
                <t:saveState value="#{pageBeanName.civilId}"/>
                <t:saveState value="#{pageBeanName.empInMin}"/>
                <t:saveState value="#{pageBeanName.ministryFileNo}"/>
                <t:saveState value="#{pageBeanName.hireDate}"/>
                <t:saveState value="#{pageBeanName.civilName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.noCandidateFound}"/>
                <t:saveState value="#{pageBeanName.hireProceduresList}"/>
                <t:saveState value="#{pageBeanName.selectedProcedure}"/>
                <t:saveState value="#{pageBeanName.convertDate}"/>
                <t:saveState value="#{pageBeanName.resultDate}"/>
                <t:saveState value="#{pageBeanName.showReasonPanel}"/>
                <t:saveState value="#{pageBeanName.selectedReasonData}"/>
                <t:saveState value="#{pageBeanName.procedureResults}"/>
                <t:saveState value="#{pageBeanName.selectedProcedureResult}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.maintainMode}"/>
                <t:saveState value="#{pageBeanName.selectedResult}"/>
                <t:saveState value="#{pageBeanName.selectedAddReasonData}"/>
                 <t:saveState value="#{pageBeanName.postponeDate}"/>
               
            </tiles:insert>
        </t:aliasBean>
    </h:form>
    <t:panelGroup forceId="true" id="scriptpanel">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
</f:view>
