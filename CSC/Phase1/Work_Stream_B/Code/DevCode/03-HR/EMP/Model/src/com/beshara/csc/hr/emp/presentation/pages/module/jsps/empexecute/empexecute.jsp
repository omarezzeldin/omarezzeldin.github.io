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
    <t:aliasBean alias="#{pageBeanName}" value="#{empExecuteBean}">
        <h:form id="myForm" binding="#{pageBeanName.frm}">
            <t:saveState value="#{empExecuteListBean.fullColumnName}"/>
            <t:saveState value="#{empExecuteListBean.sortAscending}"/>
            <t:saveState value="#{empExecuteListBean.saveSortingState}"/>
            <t:saveState value="#{empExecuteListBean.sortColumn}"/>
            <t:saveState value="#{empExecuteListBean.selectedDTOS}"/>
            <t:saveState value="#{empExecuteListBean.lastLockingAction}"/>
            <t:saveState value="#{pageBeanName.kwuaity}"/>
            <t:saveState value="#{pageBeanName.raiseIndicator}"/>
            <t:saveState value="#{pageBeanName.firstDegreeName}"/>
            <tiles:insert definition="empexecute.page" flush="false">
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.selectedAllHireTypes}"/>
                <t:saveState value="#{pageBeanName.contractType}"/>
                <t:saveState value="#{pageBeanName.salaryElementDTO}"/>
                <t:saveState value="#{pageBeanName.centralEmphireType}"/>
                <t:saveState value="#{pageBeanName.enableDateOfNextRaise}"/>
                <t:saveState value="#{pageBeanName.renderEmpFilNumRedundant}"/>
                <t:saveState value="#{pageBeanName.renderCandFilNumRedundant}"/>
                
            </tiles:insert>
            <c2:scriptGenerator form="myForm"/>
        </h:form>
    </t:aliasBean>
    <script type="text/javascript">
     history.pushState({ page: 1 }, "title 1", "#anyText");
        window.onhashchange = function (event) {
        window.location.hash = "anyText";
        
        };
    
    </script>
</f:view>