<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <!-- added by M.abdelsabour -->
    <f:loadBundle basename="com.beshara.csc.nl.org.integration.presentation.resources.integration"
                  var="orgIntgResources"/>
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    <t:aliasBean alias="#{pageBeanName}" value="#{hireProceduresEditBean}">
        <!-- added by M.abdelsabour for integration-->
        <t:aliasBean alias="#{minHelperBeanName}" value="#{hireProceduresEditBean.ministryHelper}">
            <h:form id="myForm" binding="#{pageBeanName.frm}">
            <tiles:insert definition="hireproceduresedit.page" flush="false">
                <t:saveState value="#{pageBeanName.selectedGovFlag}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.selectedCategoryType}"/>
                <t:saveState value="#{pageBeanName.categoryList}"/>
                <t:saveState value="#{pageBeanName.selectedRadio}"/>
                <t:saveState value="#{pageBeanName.ministryCode}"/>
                <%-- <t:saveState value="#{pageBeanName.selectedNationalityType}" /> <t:saveState
                     value="#{pageBeanName.selectedGender}" /> <t:saveState value="#{pageBeanName.genderList}" />--%>
                <t:saveState value="#{pageBeanName.success}"/>
                <t:saveState value="#{pageBeanName.ministrySearchStr}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.errorMsgValue}"/>
                <t:saveState value="#{hireProceduresListBean.fullColumnName}"/>
                <t:saveState value="#{hireProceduresListBean.sortAscending}"/>
                <t:saveState value="#{hireProceduresListBean.saveSortingState}"/>
                <t:saveState value="#{hireProceduresListBean.sortColumn}"/>
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <!-- added by m.abdelsabour-->
                <t:saveState value="#{hireProceduresEditBean.ministryHelper}"/>
                <t:saveState value="#{hireProceduresEditBean.minDTO}"/>
                 <t:saveState value="#{hireProceduresEditBean.selectedMinDTO}"/>
                <t:saveState value="#{pageBeanName.viewStatus}"/>
                </tiles:insert>
                <c2:scriptGenerator form="myForm"/>
            </h:form>
        </t:aliasBean>
    </t:aliasBean>
</f:view>
