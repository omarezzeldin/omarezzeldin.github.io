<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<!--
/*
 * Licensed to the Beshara Group (BG)
 * authore: Ahmed Abd El-Fatah
 */
//-->
<f:view locale="#{shared_util.locale}">
    <!-- Load the resource bundle of the page -->
    <h:form id="treeform" binding="#{hireTypesTreeListBean.frm}" style="margin-bottom: 0">
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
        <f:loadBundle basename="com.beshara.csc.nl.job.presentation.resources.jobs" var="jobResources"/>
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
        <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="empResources"/>
        <f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration"
                      var="grsIntgResources"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{hireTypesTreeListBean}">
            <t:aliasBean alias="#{jcHelperBeanName}" value="#{hireTypesTreeListBean.jcHelper}">
            <tiles:insert flush="false" definition="hireTypesTreeList.page">
              <t:saveState value="#{pageBeanName.enabled}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.conditionsDTO}"/>
                <t:saveState value="#{hireTypesMainData.jcHelper}"/>
                <t:saveState value="#{hireTypesMainData.myTableData}"/>
                <t:saveState value="#{pageBeanName.jcHelper}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.enabledNotLeaf}"/>
                <t:saveState value="#{pageBeanName.dtoDetails}"/>
                <t:saveState value="#{pageBeanName.dto}"/>
                <%-- <t:saveState value="#{pageBeanName.jcHelper}"/>--%>
                <%-- <t:saveState value="#{pageBeanName.selectedPageDTOLeaf}"/>--%>
                <t:saveState value="#{pageBeanName.booleanLeafFlag}"/>
                <t:saveState value="#{pageBeanName.treeNodeBase}"/>
            </tiles:insert>
        </t:aliasBean>
</t:aliasBean>
    <c:scriptGenerator form="treeform" />

    </h:form>
</f:view>




