<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:view locale="#{shared_util.locale}">

 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regResources"/>
 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="resourcesBundle"/>
 
 <h:form id="myForm" binding="#{delegationDecisionElmsBean.frm}">
  
  <%--<t:aliasBean alias="#{pageBeanName}" value="#{delegationDecisionMaintainBean}">
   <t:aliasBean alias="#{detailBeanName}" value="#{delegationDecisionEmployeesMaintainBean}">--%>
<t:aliasBean alias="#{pageBeanName}" value="#{delegationDecisionElmsBean}">
    <tiles:insert definition="delegationdecisionempElms.page" flush="false">
     <%--<tiles:put name="content1" value="${ pageBeanName.multiOrSingleEmps == 0 ? '/integration/reg/jsps/delegationDecisions/decisionemployeesmaintaincontent_1.jsp' : '/integration/reg/jsps/delegationDecisions/decisionemployeesmaintaincontent1.jsp' }"></tiles:put>--%>
     <%--<tiles:put name="titlepage" type="string" value="${(pageBeanName.decType == 2) ? ( (pageBeanName.maintainMode == 0) ? 'addDelegationDecisions' : ( (pageBeanName.maintainMode == 1) ? 'editDelegationDecisions' : 'delegationDecision' ) ) : ( (pageBeanName.maintainMode==0) ? 'addRegVariedDecisions' : ( (pageBeanName.maintainMode == 1) ? 'editRegVariedDecisions' : 'regVariedDecision' ) ) }" />--%>      
        <t:messages showDetail="true" forceId="true" id="errorsMsgsId"/>
        
        <t:saveState value="#{delegationDecisionMaintainBean.pageDTO}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.nextNavigationCase}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.previousNavigationCase}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.finishNavigationCase}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.currentNavigationCase}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.currentStep}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.maintainMode}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.detailsSavedStates}" id="detailsSavedStates"/>
        <t:saveState value="#{delegationDecisionMaintainBean.renderSave}" id="mainmode2"/>
        <t:saveState value="#{delegationDecisionMaintainBean.renderFinish}" id="mainmode3"/>
        <t:saveState value="#{delegationDecisionMaintainBean.showOnly}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.months}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.years}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.multiOrSingleEmps}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.decType}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.copyDecisionWithEmployeesMode}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.currentStag}"/>
        <t:saveState value="#{delegationDecisionMaintainBean.pageDTO.empDecisionsDTOList}"/>

        <t:saveState value="#{wizardbar.wizardSteps}"/>
        <t:saveState value="#{wizardbar.configurationId}"/>
        <t:saveState value="#{wizardbar.currentStep}"/>   
        
        <t:saveState value="#{pageBeanName.enableElementGuideCombo}"/>
        <%--<t:saveState value="#{pageBeanName.currentDetails}"/>--%>
        <%--<t:saveState value="#{pageBeanName.availableDetails}"/>--%>
        <%--<t:saveState value="#{pageBeanName.selectedCurrentDetails}"/>--%>
        <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
        <t:saveState value="#{pageBeanName.month}"/>
        <t:saveState value="#{pageBeanName.year}"/>
        <%--<t:saveState value="#{pageBeanName.selectedAvailableDetails}"/>--%>
        <%--<t:saveState value="#{pageBeanName.masterEntityKey}" id="mentitykey"/>--%>
        <%--<t:saveState value="#{pageBeanName.saveButtonOverride}" id="mainmode4"/>--%>
        <%--<t:saveState value="#{pageBeanName.finishButtonOverride}" id="mainmode5"/>--%>
        <t:saveState value="#{pageBeanName.isSearchMode}"/>
        <t:saveState value="#{pageBeanName.content1Div}"/> 
        <t:saveState value="#{pageBeanName.tempResponse}"/> 
        <t:saveState value="#{pageBeanName.divMainContentMany}"/>
        <t:saveState value="#{delegationDecisionMainDataMaintainBean.typesDTOKey}"/>
        <t:saveState value="#{delegationDecisionMainDataMaintainBean.yearsDTOKey}"/>
        <t:saveState value="#{delegationDecisionMainDataMaintainBean.decisionMakers}"/>
        <t:saveState value="#{pageBeanName.civilID}"/>
        <t:saveState value="#{pageBeanName.currentDisplayedSalVariedElmGuideDTOList}"/>
        <t:saveState value="#{pageBeanName.currentSalVariedElmGuideDTOList}"/>
        <t:saveState value="#{pageBeanName.salVariedElmGuidesDataTable}"/>
        <t:saveState value="#{pageBeanName.empFullName}"/>
        <t:saveState value="#{pageBeanName.fullColumnName}"/>
        <t:saveState value="#{pageBeanName.sortAscending}"/>
        <t:saveState value="#{pageBeanName.currentPageIndex}"/>
        <t:saveState value="#{pageBeanName.oldPageIndex}"/>
        <t:saveState value="#{pageBeanName.sorting}"/>
        <t:saveState value="#{pageBeanName.usingPaging}"/>
        <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
        <t:saveState value="#{pageBeanName.maintainMode}"/>
        <t:saveState value="#{pageBeanName.selectedDTOS}"/>
        <t:saveState value="#{pageBeanName.civil_exist}"/>
      
        <%--<t:saveState value="#{pageBeanName.currentDisplayDetails}"/>--%>
        <t:saveState value="#{pageBeanName.elementGuideCode}"/>
        <t:saveState value="#{pageBeanName.selectedElementGuideCode}"/>
        <t:saveState value="#{pageBeanName.elementGuideTypeList}"/>
        <t:saveState value="#{pageBeanName.elementGuideList}"/>
        <t:saveState value="#{pageBeanName.selectedElementGuideName}"/>
        <t:saveState value="#{pageBeanName.validElmguide}"/>
        <t:saveState value="#{pageBeanName.showErrorMsg1}"/>
        <t:saveState value="#{pageBeanName.countOfAllEmpDecision}"/>
        <%--<t:saveState value="#{pageBeanName.currentListSize}"/>--%>
        
        
        
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
        
        <t:saveState value="#{pageBeanName.totalValue}"/>
        <t:saveState value="#{pageBeanName.felsValue}"/>
        <t:saveState value="#{pageBeanName.dinarValue}"/>
        <t:saveState value="#{pageBeanName.signal}"/>
        
        <t:saveState value="#{pageBeanName.empDecisionsDTOSlelected}"/>
        <t:saveState value="#{pageBeanName.pageDTO}"/>
        
        <t:saveState value="#{delegationDecisionMainDataMaintainBean.editorReturn}" />
        <t:saveState  value="#{delegateDecisionsListBean.fullColumnName}" />
        <t:saveState  value="#{delegateDecisionsListBean.sortAscending}" />
        <t:saveState  value="#{delegateDecisionsListBean.saveSortingState}" />
        <t:saveState  value="#{delegateDecisionsListBean.sortColumn}" />
        <t:saveState value="#{delegateDecisionsListBean.cancelDescisionFlag}"/>
        <t:saveState value="#{delegateDecisionsListBean.decisionSearchDTO}"/>
        <t:saveState value="#{delegateDecisionsListBean.pagingBean.myPagedDataModel}"/>
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
            
            <t:saveState value="#{delegationDecisionEmployeesMaintainBean.totalValue}"/>
            
            <t:saveState value="#{pageBeanName.civilExist}"/>
            <t:saveState value="#{pageBeanName.elmGuideExist}"/>
            <t:saveState value="#{pageBeanName.employeesDTO}"/>
            <t:saveState value="#{pageBeanName.currentCivilStatus}"/>
            <t:saveState value="#{pageBeanName.hasMovingRequest}"/>
            <t:saveState value="#{pageBeanName.decType}"/>
            <t:saveState value="#{pageBeanName.showResetEmpDataAlertDiv}"/>
            <t:saveState value="#{pageBeanName.dedElmTypesList}"/>
        <%-- // End Apply Business_Pagging ..--%>
        
    </tiles:insert>
   </t:aliasBean>
  <%-- </t:aliasBean>--%>
 <t:panelGroup  id="scriptPanel" forceId="true">
  <c2:scriptGenerator form="myForm"/>
 </t:panelGroup>
  <script type="text/javascript"> 
        setFocusAtMyFirstElement();
      //  checkSaveButtonFirstVisit();
        
        function setFocusAtMyFirstElement(){
            setFocusOnElement('addButton');
        }
        
        function setFocusAtMyAddDiv(){
            setFocusOnlyOnElement('maintain_regType_div'); 
        }
        
        function setFocusAtMyDelDiv(){
            setFocusOnlyOnElement('CancelButtonDelAlertDiv');
        }
        
    function checkSaveButtonFirstVisit()
    {
     if(document.getElementById('SaveButton')!=null)
     {
         if(document.getElementById('add_first_inputTxt').value!='' || document.getElementById('add_second_inputTxt').value!='' || document.getElementById('add_third_inputTxt').value!='')
           {
            document.getElementById('SaveButton').disabled=false;
            document.getElementById('financialEmpValueNumId').style.visibility='visible';
            document.getElementById('financialEmpValueCharId').style.visibility='visible';
            document.getElementById('financialEmpValueDateId').style.visibility='visible';
           }
           else
           {
            document.getElementById('financialEmpValueNumId').style.visibility='hidden';
            document.getElementById('financialEmpValueCharId').style.visibility='hidden';
            document.getElementById('financialEmpValueDateId').style.visibility='hidden';
           }
      }
    }
    
    function checkSaveButtonEditFirstVisit()
    {
     if(document.getElementById('SaveButtonEdit')!=null)
     {
         if((document.getElementById('informEmpTypeListMenu').value!='' || document.getElementById('edit_third_inputTxt').value!='' || document.getElementById('edit_first_inputTxt').value!='') || document.getElementById('maintain_informEmpFlag').checked==true  )
           {
            document.getElementById('SaveButtonEdit').disabled=false;
           }
      }
    }
    
/*moved from  by a.Nour*/
  document.getElementById('myForm:ava').focus();
   function setFocusAtMyFirstElement(){
       document.getElementById("myForm:content1Div").scrollTop = 0;
       if(document.getElementById('civilIdInTxt') !=null)
             setFocusOnlyOnElement('civilIdInTxt');
     }   
//  function validateRequiredElement(listOfControls,message,listOfMessageId){    
//    var tempx = new Array();    
//    var errx = new Array();    
//    flag=true;
//    tempx = listOfControls.split('#');
//    errx = listOfMessageId.split('#');
//    for (i=0;i<errx.length;i++){
//        document.getElementById(errx[i]).innerHTML = '';
//        if(document.getElementById(tempx[i]).value==''){
//            flag=false;
//            document.getElementById(errx[i]).innerHTML = message;
//        }
//    }    
//    return flag;
//}

function disableCharacters(field) {
    //alert("field.value = "+field.value);
    if (!/^\d*$/.test(field.value)) {
        field.value = field.value.replace(/[^\d]/g,"");
    }
}

  function validateRequiredGuideElement(listOfControls,message,listOfMessageId){ 
//    var tempx = new Array();    
//    var errx = new Array();    
    flag=true;
//    tempx = listOfControls;
//    errx = listOfMessageId;
//alert(document.getElementById('empCountId'));

    var empCount = document.getElementById('empCountId').value;
  //  alert(empCount);
    for (i=0;i<empCount;i++){
        document.getElementById(listOfMessageId+"["+i+"]").innerHTML = '';
        var id = listOfControls+"["+i+"]";
        var obj = document.getElementById(id);
        //alert(i);
      //  alert(obj);
      //  alert(obj.value);
        if(typeof (obj) == 'undefined' || obj == null || (obj!= null && obj.value=='') || obj.value==0){
            flag=false;
            document.getElementById(listOfMessageId+"["+i+"]").innerHTML = message;
        }
    }    
    return flag;
}

function stepValidation(){
 var returnFromCmpareDates=validateTwoDatesInDataTable();
 
 if(returnFromCmpareDates)
    {
        //if(typeof doValidatemyForm != 'undefined'){
            if(doValidatemyForm){
             var missingValue =  '${resourcesBundle.missingReqField}'; 
             if(validateRequiredGuideElement('denar_number',missingValue,'errorValueMessage')){
                return validatemyForm();
               }else{
                return false ; 
               }
               
            }
        //}
        return true;   
    }
    
    return false;
 }

</script>
 </h:form>
</f:view>
