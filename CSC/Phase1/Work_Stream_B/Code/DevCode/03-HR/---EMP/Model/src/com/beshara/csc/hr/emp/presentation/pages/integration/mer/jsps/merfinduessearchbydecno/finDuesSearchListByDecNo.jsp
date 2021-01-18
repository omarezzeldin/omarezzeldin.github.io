<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="intgerationBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
    
    <h:form id="myForm" binding="#{financialDuesSearchByDecNoListBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{financialDuesSearchByDecNoListBean}">
            <tiles:insert flush="false" definition="MerFinDuesSearchByDecNo.page"/>    
            
            <t:saveState value="#{pageBeanName.singleSelection}"/>
            <t:saveState value="#{pageBeanName.myTableData}"/>
            <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.fullColumnName}"/>
            <t:saveState value="#{pageBeanName.sortAscending}"/>

            <t:saveState value="#{pageBeanName.currentPageIndex}"/>
            <t:saveState value="#{pageBeanName.oldPageIndex}"/>
            <t:saveState value="#{pageBeanName.sorting}"/>
            <t:saveState value="#{pageBeanName.usingPaging}"/>
            <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
            <t:saveState value="#{pageBeanName.resettedPageIndex}"/>

            <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>

            <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
            <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>

            <t:saveState value="#{pageBeanName.decNumber}" id="decNumber"/>
            <t:saveState value="#{pageBeanName.decNumber}" id="decAutoNumber"/>
            <t:saveState value="#{pageBeanName.decTypeCode}" id="decTypeCode"/>
            <t:saveState value="#{pageBeanName.decYear}" id="decYear"/>
            <t:saveState value="#{pageBeanName.decDTO}" id="decDTO"/>
            <t:saveState value="#{pageBeanName.decisionFound}" id="decisionFound"/>
            <t:saveState value="#{pageBeanName.paymentMethods}" id="paymentMethods"/>
            <t:saveState value="#{pageBeanName.yearsList}" id="yearsList"/>
            <t:saveState value="#{pageBeanName.monthesList}" id="monthesList"/>
            <t:saveState value="#{pageBeanName.paymentMethodCode}" id="paymentMethodCode"/>
            <t:saveState value="#{pageBeanName.yearCode}" id="yearCode"/>
            <t:saveState value="#{pageBeanName.yearName}" id="yearName"/>
            <t:saveState value="#{pageBeanName.monthCode}" id="monthCode"/>
            <t:saveState value="#{pageBeanName.monthName}" id="monthName"/>
            <%--<t:saveState value="#{pageBeanName.decisionTypesList}" id="decisionTypesList"/>--%>
            <t:saveState value="#{pageBeanName.decYearsList}" id="decYearsList"/>
            <t:saveState value="#{pageBeanName.decEmpList}" id="decEmpList"/>
            <t:saveState value="#{pageBeanName.empPayslips}" id="empPayslips"/>
            <t:saveState value="#{pageBeanName.enableMenu}" id="enableMenu"/>
            <t:saveState value="#{pageBeanName.decisionText}" id="decisionText"/>
            <t:saveState value="#{pageBeanName.salFinDuesDetails}" id="salFinDuesDetails"/>
            <t:saveState value="#{pageBeanName.showBar}" id="showBar"/>
            <t:saveState value="#{pageBeanName.userMessage}" id="userMessage"/>
            <t:saveState value="#{pageBeanName.showMessage}" id="showMessage"/>
            <t:saveState value="#{pageBeanName.empErrors}" id="empErrors"/>
            <t:saveState value="#{pageBeanName.empSheetDetails}" id="empSheetDetails"/>
            <t:saveState value="#{pageBeanName.salMonTrxList}" id="salMonTrxList"/>
            <t:saveState value="#{pageBeanName.filterPnlCollapsed}"/>
            
            
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptGenerator">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>        
    </h:form>
</f:view>
