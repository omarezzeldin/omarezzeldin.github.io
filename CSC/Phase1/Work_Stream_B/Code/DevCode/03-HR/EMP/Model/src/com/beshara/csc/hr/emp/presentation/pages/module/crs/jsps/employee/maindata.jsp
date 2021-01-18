<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<f:view locale="#{shared_util.locale}">
 
 <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
 <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
        
  <h:form id="myForm"  binding="#{empMainDataBean_crs.frm}">
         
<t:aliasBean alias="#{pageBeanName}"  value="#{empMaintainBean_crs}">
    <t:aliasBean alias="#{detailBeanName}"  value="#{empMainDataBean_crs}">
             
            <tiles:insert definition="empmaindata_crs.page" flush="false" >
                        
         <t:saveState value="#{pageBeanName.mainDataOnlyFlag}" />
         <t:saveState value="#{pageBeanName.savedHireType}" />
         <t:saveState value="#{pageBeanName.savedDocumentList}" />
         <t:saveState value="#{pageBeanName.pageDTO}" id="mainostep"/>
         <t:saveState value="#{pageBeanName.nextNavigationCase}" id="mainnstep"/>
         <t:saveState value="#{pageBeanName.previousNavigationCase}" id="mainpstep"/>
         <t:saveState value="#{pageBeanName.finishNavigationCase}" id="mainfstep"/>
         <t:saveState value="#{pageBeanName.currentNavigationCase}" id="maincstep"/>
         <t:saveState value="#{pageBeanName.currentStep}" id="mainstep"/>
         <t:saveState value="#{pageBeanName.maintainMode}" id="mainmode"/>
         <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
         
         <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
         <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>
         <t:saveState value="#{detailBeanName.masterEntityKey}" id="mainentitykey"/>
         <t:saveState value="#{detailBeanName.titleFlageOfDiv}" id="titleFlageOfDivId"/>
         
          <t:saveState value="#{pageBeanName.candidatePersonsDTO}" />
          <t:saveState value="#{pageBeanName.categoryCode}" />
          <t:saveState value="#{pageBeanName.minisityCode}" />
          
         <%-- added by nora --%>
           <t:saveState value="#{detailBeanName.loginedMinistrycode}" />
          <t:saveState value="#{detailBeanName.civilId}" />
          <t:saveState value="#{pageBeanName.invalidMinFileNo}" />
          <t:saveState value="#{empMainDataBean_crs.invalidCivilID}" />
          <t:saveState value="#{empMainDataBean_crs.invalidCivilWithHireType}" />
          <t:saveState value="#{empMainDataBean_crs.nonDBCivilID}" />
          <t:saveState value="#{empMainDataBean_crs.civilExist}" />
          <t:saveState  value="#{detailBeanName.techicalJobsList}" />
          <t:saveState  value="#{detailBeanName.hireTypesList}" />
          <t:saveState  value="#{detailBeanName.hireStatusList}"/>
          <t:saveState  value="#{detailBeanName.bankList}"/>
          <t:saveState  value="#{detailBeanName.bankBranchesList}"/>
          <t:saveState  value="#{detailBeanName.selectedBankCode}" />
          <t:saveState  value="#{detailBeanName.workMinistriesList}" />
          
          
          
          <t:saveState  value="#{detailBeanName.selectedJobName}" />
          <t:saveState  value="#{detailBeanName.searchjobString}" />
          <t:saveState  value="#{detailBeanName.jobSearchType}" />
          <t:saveState value="#{detailBeanName.searchQuery}" />
          <t:saveState value="#{detailBeanName.workCenterMode}" />
          <t:saveState  value="#{detailBeanName.workMinistriesListSize}" />
          <t:saveState  value="#{detailBeanName.selectedWorkMinistriesName}" />
          <t:saveState  value="#{detailBeanName.searchWorkMinistriesString}" />
          <t:saveState  value="#{detailBeanName.searchJobMode}" />
          <t:saveState  value="#{detailBeanName.searchWorkMinistriesMode}" />
          <t:saveState value="#{detailBeanName.jobCode}"/>
          <t:saveState value="#{detailBeanName.raiseCode}"/>
            <%--  hr-666  --%>
            <t:saveState value="#{detailBeanName.disableQualificationEditting}"/>
            <t:saveState value="#{detailBeanName.educationLevelList}"/>
            
            <t:saveState value="#{detailBeanName.qulCountriesList}"/>
            <t:saveState value="#{detailBeanName.qulCentersList}"/>
            <t:saveState value="#{detailBeanName.qulName}"/>
            <t:saveState value="#{detailBeanName.qulLevelCode}"/>
            <t:saveState value="#{detailBeanName.qulCenterCode}"/>
            <t:saveState value="#{detailBeanName.qulCountryCode}"/>
            <t:saveState value="#{detailBeanName.personQulDTO}" />
            <t:saveState value="#{treeDivBean.myTableData}" />
            <t:saveState value="#{empMainDataBean_crs.jobDescription}"/>
            <t:saveState  value="#{empMaintainBean_crs.empHireTypeNominationAgainFromOtherMinistries}" />
            <t:saveState value="#{detailBeanName.raiseName}" />
            <t:saveState value="#{proceedingCandidateListBean.fromModuleName}" />
            
            <tiles:put name="titlepage" type="string" value="${'add_title_emp'}" /> 
                         
                <tiles:put name="navigation"  value="${pageBeanName.mainDataOnlyFlag == false ? '/module/crs/jsps/employee/navigationadd.jsp' : '/module/crs/jsps/employee/navigation_maindata.jsp'  }" /> 
                 </tiles:insert>
               </t:aliasBean>
                  </t:aliasBean>
                  <t:panelGroup forceId="true" id="validScriptPanel">
          <c:scriptGenerator form="myForm"/>
          </t:panelGroup>
         </h:form>
</f:view>