<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    <h:form id="myForm" binding="#{experienceListBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{experienceListBean}" id="alias1">
            <tiles:insert definition="experienceList.page" flush="false">
                
                <t:saveState value="#{pageBeanName.crsFromMouduleNmae}"/>
                <t:saveState value="#{pageBeanName.employeeName}" />
                <t:saveState value="#{pageBeanName.civileId}" />
                <t:saveState value="#{pageBeanName.saveStateList}" />
                <t:saveState value="#{pageBeanName.backBtnNavigationCase}" />
                <t:saveState value="#{pageBeanName.backActionMethodName}" />
                
                <t:saveState value="#{proceedingCandidateListBean.fromModuleName}" />
                <t:saveState value="#{proceedingCandidateListBean.selectedCandType}" />
                
            </tiles:insert>
             <t:panelGroup id="scriptPnl">
                <c2:scriptGenerator form="myForm"/>
            </t:panelGroup>
        </t:aliasBean>
    </h:form>
</f:view>