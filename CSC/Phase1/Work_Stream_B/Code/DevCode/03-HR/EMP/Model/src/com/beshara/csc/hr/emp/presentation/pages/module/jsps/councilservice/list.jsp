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
    <t:aliasBean alias="#{pageBeanName}" value="#{councilServiceListBean}">
        <h:form id="myForm" binding="#{pageBeanName.frm}">
            <tiles:insert definition="councilServiceList.page" flush="false">
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.saveSortingState}"/>
                <t:saveState value="#{pageBeanName.sortColumn}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.selectedListSize}"/>
                <%-- <tiles:put name="titleDelDiv" type="string" value="${'empty_label'}"/>--%>
                <t:saveState value="#{pageBeanName.selectedMinistry}"/>
                <t:saveState value="#{pageBeanName.selectedCategory}"/>
                <t:saveState value="#{pageBeanName.ministryList}"/>
                <t:saveState value="#{pageBeanName.categoryList}"/>
                <t:saveState value="#{pageBeanName.noKwtCitOnQual}"/>
                <t:saveState value="#{pageBeanName.disabled}"/>
                <t:saveState value="#{pageBeanName.validCivilId}"/>
      <t:saveState value="#{pageBeanName.civilExist}"/>
      <t:saveState value="#{pageBeanName.civilName}"/>
       <t:saveState value="#{pageBeanName.civilId}"/>
   <t:saveState value="#{pageBeanName.searchDTO}"/>
            </tiles:insert>
            <t:panelGroup forceId="true" id="scriptPanelID">
                <c2:scriptGenerator form="myForm"/>
            </t:panelGroup>
        </h:form>
    </t:aliasBean>
</f:view>