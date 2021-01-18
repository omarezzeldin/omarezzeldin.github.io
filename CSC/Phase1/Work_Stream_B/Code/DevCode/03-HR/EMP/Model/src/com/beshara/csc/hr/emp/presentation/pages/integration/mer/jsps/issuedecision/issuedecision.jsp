<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="merIntgResources"/>
    <h:form id="myForm" binding="#{issueDecisionBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{issueDecisionBean}">
            <t:aliasBean alias="#{retroactiveSettDetailsHelperBean}" value="#{issueDecisionBean.helperBean}">
                <tiles:insert definition="issueDecision.page" flush="false">
                    <tiles:put name="titlepage" value="${pageBeanName.pageTitleKey}"/>
                    <t:saveState value="#{pageBeanName.pageDTO}"/>
                    <t:saveState value="#{pageBeanName.salEmpRelDTO}"/>
                    <t:saveState value="#{issueDecisionBean.helperBean}"/>
                    <t:saveState value="#{pageBeanName.savedDataObjects}"/>
                    <t:saveState value="#{pageBeanName.returnMethodName}"/>
                    <t:saveState value="#{pageBeanName.successMethodName}"/>
                    <t:saveState value="#{pageBeanName.backNavCase}"/>
                    <t:saveState value="#{pageBeanName.pageTitleKey}"/>
                    <t:saveState value="#{pageBeanName.allowanceDate}"/>
                    <t:saveState value="#{pageBeanName.addRaise}"/>
                    <t:saveState value="#{pageBeanName.stopReason}"/>
                    <t:saveState value="#{pageBeanName.suspendReasons}"/>
                    <t:saveState value="#{pageBeanName.renderStopReasonGrd}"/>
                    <t:saveState value="#{pageBeanName.motherGrantChildrenRaise}"/>
                    <t:saveState value="#{pageBeanName.lastStopDate}"/>
                    <t:saveState value="#{pageBeanName.useSettelment}"/>
                    <t:saveState value="#{pageBeanName.settelmentExist}"/>
                    <t:saveState value="#{pageBeanName.settelmentPageMode}"/>
                    <t:saveState value="#{pageBeanName.elmGuideCode}"/>
                    <t:saveState value="#{pageBeanName.empSalElmSerial}"/>
                    <t:saveState value="#{pageBeanName.civilId}"/> 
                    <t:saveState value="#{pageBeanName.decisionParams}"/>
                    <t:saveState value="#{pageBeanName.isPredefinedDate}"/>
                    <t:saveState value="#{pageBeanName.settMonth}"/>
                    <t:saveState value="#{pageBeanName.settYear}"/>
                    <t:saveState value="#{pageBeanName.settTotalValue}"/>
                    <t:saveState value="#{pageBeanName.settSuccessMethodName}"/>
                    <t:saveState value="#{pageBeanName.empSuggSettMethodName}"/>
                    <t:saveState value="#{pageBeanName.defaultEmpSuggSettMethod}"/>
                    <t:saveState value="#{pageBeanName.showElmValueCol}"/>
                    <t:saveState value="#{pageBeanName.disableCal}"/>
                    <t:saveState value="#{pageBeanName.disableAllowanceCal}"/>
                    <t:saveState value="#{pageBeanName.oldStopDate}"/>
                    <t:saveState value="#{pageBeanName.oldAllowanceDate}"/>
                    <t:saveState value="#{pageBeanName.enableGroup}"/>
                    <t:saveState value="#{pageBeanName.salEmpRelDTOs}"/>
                    <t:saveState value="#{pageBeanName.stopDateID2Label}"/>
                    <t:saveState value="#{pageBeanName.types}"/>
                    
                    <t:saveState value="#{pageBeanName.divorcedDate}"/>
                    <t:saveState value="#{pageBeanName.marriageDate}"/>
                    <t:saveState value="#{pageBeanName.divorcedDateValidationShow}"/>
                    <t:saveState value="#{pageBeanName.divorcedDateShow}"/>
                    <t:saveState value="#{pageBeanName.disableDivorcedDate}"/>
                    
                    <t:saveState value="#{pageBeanName.allowanceReasons}"/>
                    
                </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
         <t:panelGroup forceId="true" id="scriptPanelGroup">
        <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
</f:view>
