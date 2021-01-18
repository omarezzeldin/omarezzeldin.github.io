<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">

 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regResources"/>

 
 <h:form id="myForm" binding="#{delegationDecisionMainDataMaintainBean.frm}">
    <t:aliasBean alias="#{pageBeanName}" value="#{delegationDecisionMaintainBean}">
    <t:aliasBean alias="#{detailBeanName}" value="#{delegationDecisionMainDataMaintainBean}">
    
    <tiles:insert definition="delegationdecisionmaindatamaintain.page" flush="false">
        
       <tiles:put name="titlepage" type="string" value="${(pageBeanName.decType == 2) ? ( (pageBeanName.maintainMode == 0) ? 'addDelegationDecisions' : ( (pageBeanName.maintainMode == 1) ? 'editDelegationDecisions' : 'delegationDecision' ) ) : ( (pageBeanName.maintainMode==0) ? 'addRegVariedDecisions' : ( (pageBeanName.maintainMode == 1) ? 'editRegVariedDecisions' : 'regVariedDecision' ) ) }" />      
       
        <t:saveState value="#{pageBeanName.pageDTO}" id="mainostep"/>
        <t:saveState value="#{pageBeanName.multiOrSingleEmps}"/>
        <t:saveState value="#{pageBeanName.decType}"/>
        <t:saveState value="#{pageBeanName.nextNavigationCase}" id="mainnstep"/>
        <t:saveState value="#{pageBeanName.previousNavigationCase}"  id="mainpstep"/>
        <t:saveState value="#{pageBeanName.finishNavigationCase}" id="mainfstep"/>
        <t:saveState value="#{pageBeanName.currentNavigationCase}" id="maincstep"/>
        <t:saveState value="#{pageBeanName.currentStep}" id="mainstep"/>
        <t:saveState value="#{pageBeanName.maintainMode}" id="mainmode"/>
        <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
        <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>
        <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
        <%--<t:saveState value="#{pageBeanName.cancelDecisionMode}"/>--%>
        <%--<t:saveState value="#{decisionCycleMaintainBean.showOnly}"/>--%>
          <t:saveState value="#{pageBeanName.showOnly}"/>
        <t:saveState value="#{detailBeanName.typesDTOKey}"/>
        <t:saveState value="#{detailBeanName.yearsDTOKey}"/>
        <t:saveState value="#{detailBeanName.currentDetails}"/>
        <t:saveState value="#{detailBeanName.availableDetails}"/>
        <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
        <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
        <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>    
        <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
        <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>    
       <t:saveState value="#{detailBeanName.decMkrDisabled}" id="DecMkrDisabled"/>    
        <t:saveState value="#{detailBeanName.types}" id="types"/>
        <t:saveState value="#{detailBeanName.issuanceYears}" id="issuanceYears"/>
        <t:saveState value="#{detailBeanName.subjects}" id="subjects"/>
        <t:saveState value="#{detailBeanName.statusDTOKey}"/>
        <t:saveState value="#{detailBeanName.firstSubject}"/>
        <t:saveState value="#{detailBeanName.decisionMakers}" id="decisionMakers"/>
        <%--<t:saveState value="#{detailBeanName.templates}" id="templates"/>--%>
        <t:saveState value="#{detailBeanName.itemSelectionRequiredValue}" id="itemSelectionRequiredValue"/>
        <t:saveState value="#{detailBeanName.itemSelectionRequiredValueString}" id="itemSelectionRequiredValueString"/>
        <t:saveState value="#{detailBeanName.searchMode}" id="searchMode"/>
        <t:saveState value="#{detailBeanName.typesDTOKey}"/>
            <t:saveState value="#{detailBeanName.yearsDTOKey}"/>
        
        <%--<t:saveState value="#{pageBeanName.copyDecisionWithEmployeesMode}"/>--%>
        
        <t:saveState  value="#{delegateDecisionsListBean.fullColumnName}" />
        <t:saveState  value="#{delegateDecisionsListBean.sortAscending}" />
        <t:saveState  value="#{delegateDecisionsListBean.saveSortingState}" />
        <t:saveState  value="#{delegateDecisionsListBean.sortColumn}" />
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.currentListSize}"/>
            <%--  // Start Apply Business_Pagging ..--%>
            <t:saveState value="#{delegateDecisionsListBean.searchDTO}"/>
            <t:saveState value="#{delegateDecisionsListBean.bsnSortingColumnName}"/>
            <t:saveState value="#{delegateDecisionsListBean.filterDTO}"/>
            <t:saveState value="#{delegateDecisionsListBean.oldPageIndex}"/>
            <t:saveState value="#{delegateDecisionsListBean.currentPageIndex}"/>
            <t:saveState value="#{delegateDecisionsListBean.resettedPageIndex}"/>
            <t:saveState value="#{delegateDecisionsListBean.pagingBean.preUpdatedDataModel}"/>
            <t:saveState value="#{delegateDecisionsListBean.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{delegateDecisionsListBean.pagingBean.totalListSize}"/>
            <t:saveState value="#{delegateDecisionsListBean.usingPaging}"/>
            <t:saveState value="#{delegateDecisionsListBean.usingBsnPaging}"/>
            <t:saveState value="#{delegateDecisionsListBean.pagingRequestDTO}"/>
            <t:saveState value="#{delegateDecisionsListBean.sorting}"/>
            <t:saveState value="#{delegateDecisionsListBean.sortedTable}"/>
            <t:saveState value="#{delegateDecisionsListBean.indexArray}"/>
            <t:saveState value="#{delegateDecisionsListBean.ascending}"/>
            <t:saveState value="#{delegateDecisionsListBean.updateMyPagedDataModel}"/>
            <t:saveState value="#{delegateDecisionsListBean.selectedTypeKey}"/>
            <t:saveState value="#{delegateDecisionsListBean.searchMode}"/>
            <t:saveState value="#{delegationDecisionEmployeesMaintainBean.civilID}"/>
            <t:saveState value="#{delegationDecisionEmployeesMaintainBean.empFullName}"/>
            <t:saveState value="#{delegationDecisionEmployeesMaintainBean.civilExist}"/>
            <t:saveState value="#{delegationDecisionEmployeesMaintainBean.selectedCurrentDetails}"/>
            <t:saveState value="#{delegationDecisionEmployeesMaintainBean.employeesDTO}"/>
            <t:saveState value="#{delegationDecisionEmployeesMaintainBean.currentCivilStatus}"/>
        <%-- // End Apply Business_Pagging ..--%>
        
        <t:saveState  value="#{delegationDecisionEmployeesMaintainBean.elementGuideCode}" />
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.currentDisplayedSalVariedElmGuideDTOList}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.currentSalVariedElmGuideDTOList}"/>
        <t:saveState  value="#{delegationDecisionEmployeesMaintainBean.salVariedElmGuidesDataTable}"/>
        <%--<t:saveState value="#{delegateDecisionsListBean.cancelDescisionFlag}"/>--%>
        <%--<t:saveState value="#{decisionCycleMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>--%>
        <%--<t:saveState value="#{detailBeanName.showMatrialMsg}"/>--%>
        <t:saveState value="#{detailBeanName.returnFromEditor}"/>
        
        <%--t:saveState value="#{detailBeanName.secWorkCenterUsersSearchDTO}"/>
      
        <t:saveState value="#{detailBeanName.workCentersDTOList}"/--%> 
        <%--<t:saveState value="#{detailBeanName.pageDtoBuffer}"/>--%> 
        <t:saveState value="#{detailBeanName.divflag}"/> 
        <%--<t:saveState value="#{decisionCycleMainDataMaintainBean.editorReturn}" />--%> 
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.currentDetails}"/> 
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.enableElementGuideCombo}"/>
        
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.usingPaging}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.resettedPageIndex}"/>
        
        
      
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.pagingRequestDTO}"/>

        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.pagingBean.totalListSize}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.pagingBean.myPagedDataModel}"/>
        
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.currentDisplayDetails}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.countOfAllEmpDecision}"/>
  
        
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.civil_exist}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.selectedElementGuideCode}"/>
        <%--<t:saveState value="#{delegateDecisionsListBean.selectedRegTypeKey}"/>--%>
        <%--<t:saveState value="#{delegateDecisionsListBean.decisionSearchDTO}"/>--%>
            
            

        <%--t:saveState value="#{detailBeanName.formsList}"/>
        <t:saveState value="#{detailBeanName.selectedForm}"/>
        <t:saveState value="#{detailBeanName.selectedFormRadio}" />
        <t:saveState value="#{detailBeanName.formsSearchMode}" />
        <t:saveState value="#{detailBeanName.ascendingForm}" />
        <t:saveState value="#{detailBeanName.sortFormColumn}" /--%>
        
        
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.totalValue}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.selectedElementGuideName}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.validElmguide}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.showErrorMsg1}"/> 
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.selectedElementGuideCode}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.month}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.year}"/>
        
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.hasMovingRequest}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.showResetEmpDataAlertDiv}"/>
        <t:saveState value="#{delegationDecisionEmployeesMaintainBean.decType}"/>
        
        
        <%--  // Start Apply Business_Pagging ..--%>
            <t:saveState value="#{delegateDecisionsListBean.searchDTO}"/>
            <t:saveState value="#{delegateDecisionsListBean.bsnSortingColumnName}"/>
            <t:saveState value="#{delegateDecisionsListBean.filterDTO}"/>
            <t:saveState value="#{delegateDecisionsListBean.oldPageIndex}"/>
            <t:saveState value="#{delegateDecisionsListBean.currentPageIndex}"/>
            <t:saveState value="#{delegateDecisionsListBean.resettedPageIndex}"/>
            <t:saveState value="#{delegateDecisionsListBean.pagingBean.preUpdatedDataModel}"/>
            <t:saveState value="#{delegateDecisionsListBean.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{delegateDecisionsListBean.pagingBean.totalListSize}"/>
            <t:saveState value="#{delegateDecisionsListBean.usingPaging}"/>
            <t:saveState value="#{delegateDecisionsListBean.usingBsnPaging}"/>
            <t:saveState value="#{delegateDecisionsListBean.pagingRequestDTO}"/>
            <t:saveState value="#{delegateDecisionsListBean.sorting}"/>
            <t:saveState value="#{delegateDecisionsListBean.sortedTable}"/>
            <t:saveState value="#{delegateDecisionsListBean.indexArray}"/>
            <t:saveState value="#{delegateDecisionsListBean.ascending}"/>
            <t:saveState value="#{delegateDecisionsListBean.updateMyPagedDataModel}"/>
        <%-- // End Apply Business_Pagging ..--%>
    </tiles:insert>
    </t:aliasBean>
    </t:aliasBean>
 <t:panelGroup id="scriptGeneratorId" forceId="true">    
    <c2:scriptGenerator form="myForm"/>
</t:panelGroup>
<script type="text/javascript">   
    
     
 </script>
 
 </h:form>
</f:view>

