<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{empAddBean.frm}">
    <f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="intgBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global"  var="globalResources"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{empAddBean}">
            <tiles:insert definition="gulfEmps.page" flush="false">
            <tiles:put name="titlepage" type="string" value="${pageBeanName.pageTitle}"/>
            <%--<t:saveState value="#{pageBeanName.disable}"/>--%>
            <t:saveState value="#{pageBeanName.pageDTO}"/>
            <t:saveState value="#{pageBeanName.civilExist}"/>
            <t:saveState value="#{pageBeanName.validCivilId}"/>
            <t:saveState value="#{pageBeanName.empHired}"/>
            <t:saveState value="#{pageBeanName.civilId}"/>
            <t:saveState value="#{pageBeanName.employeesDTO}"/>
            <t:saveState value="#{pageBeanName.enableEmpLovDiv}"/>
            <t:saveState value="#{pageBeanName.cader}"/>
            <t:saveState value="#{pageBeanName.group}"/>
            <t:saveState value="#{pageBeanName.degree}"/>
            <t:saveState value="#{pageBeanName.selExceptionType}"/>
            <t:saveState value="#{pageBeanName.exceptionTypes}"/>
            <t:saveState value="#{pageBeanName.fromDate}"/>     
            <t:saveState value="#{pageBeanName.untilDate}"/>
            <t:saveState value="#{pageBeanName.exceptionRatio}"/>
            <t:saveState value="#{pageBeanName.selExceptionCode}"/>
            <t:saveState value="#{pageBeanName.exceptionReasons}"/>
            <t:saveState value="#{pageBeanName.exceptionNotes}"/>
            <t:saveState value="#{pageBeanName.pageTitle}"/>
            <t:saveState value="#{pageBeanName.kwtMode}"/>
            <t:saveState value="#{pageBeanName.invalidNationality}"/>
            <t:saveState value="#{pageBeanName.natMsg}"/>
            <t:saveState value="#{pageBeanName. empHiredInMin }"/>
            <t:saveState value="#{pageBeanName.hasSalCalWithInsurance}"/>
            
            <t:saveState value="#{pageBeanName.elmGuideTypes}"/>
            <t:saveState value="#{pageBeanName.selElemGuide}"/>
            <t:saveState value="#{pageBeanName.elementGuides}"/>
            
            <t:saveState value="#{insurExcludedEmps.countries}"/>
            <t:saveState value="#{insurExcludedEmps.selCountry}"/>
            <t:saveState value="#{insurExcludedEmps.selectedGrpCountry}"/>
            <t:saveState value="#{insurExcludedEmps.selectedCountryId}"/>
            <t:saveState value="#{insurExcludedEmps.singleSelection}"/>
            <t:saveState value="#{insurExcludedEmps.myTableData}"/>
            <t:saveState value="#{insurExcludedEmps.highlightedDTOS}"/>
            <t:saveState value="#{insurExcludedEmps.searchMode}"/>
            <t:saveState value="#{insurExcludedEmps.selectedDTOS}"/>
            <t:saveState value="#{insurExcludedEmps.fullColumnName}"/>
            <t:saveState value="#{insurExcludedEmps.sortAscending}"/>
            <%-- Start Paging --%>
            <t:saveState value="#{insurExcludedEmps.currentPageIndex}"/>
            <t:saveState value="#{insurExcludedEmps.oldPageIndex}"/>
            <t:saveState value="#{insurExcludedEmps.sorting}"/>
            <t:saveState value="#{insurExcludedEmps.usingPaging}"/>
            
            <t:saveState value="#{insurExcludedEmps.kwtPage}"/>  
            <t:saveState value="#{pageBeanName.pageMode}"/>            

            
            </tiles:insert>
        </t:aliasBean>
    </h:form>
     <script type="text/javascript">

     </script>
     
        <t:panelGroup forceId="true" id="scriptGenerator">
            <c:scriptGenerator form="myForm"/>
        </t:panelGroup>                
</f:view>