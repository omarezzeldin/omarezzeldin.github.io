<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.prm.presentation.resources.prm" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
    <h:form id="myForm" binding="#{queryAboutEmployee.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{queryAboutEmployee}">
            <tiles:insert definition="queryAboutEmployeeList.page" flush="false">
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.employeesDTO}"/>
                <%--<t:saveState value="#{pageBeanName.employeeSearchBean.advanceEmployeesAddBean.employeeSearchDTO}"/>--%>
                <%--<t:saveState value="#{pageBeanName.employeeSearchBean.advanceEmployeesAddBean.backMethod}"/>--%>
                <%--<t:saveState value="#{pageBeanName.employeeSearchBean.advanceEmployeesAddBean.singleSelection}"/>--%>
                <%--<t:saveState value="#{pageBeanName.employeeSearchBean.advanceEmployeesAddBean.returnMethod}"/>--%>
                <t:saveState value="#{pageBeanName.civilExist}"/>
                <t:saveState value="#{pageBeanName.civilId}"/>
            </tiles:insert>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptpanel">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
</f:view>