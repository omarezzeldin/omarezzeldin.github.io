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
            <t:aliasBean alias="#{searchMinistryHelper}" value="#{empAddBean.searchMinistryHelper}">
                <tiles:insert definition="kwtEmps.page" flush="false">
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
                
                <t:saveState value="#{pageBeanName.categories}"/>
                <t:saveState value="#{pageBeanName.ministries}"/>
                <t:saveState value="#{pageBeanName.selectedCatId}"/>
                <t:saveState value="#{pageBeanName.selectedMiniId}"/>
                <t:saveState value="#{pageBeanName.selectedCatName}"/>
                <t:saveState value="#{pageBeanName.selectedMiniName}"/>
                <t:saveState value="#{pageBeanName.wrongMinCode}"/>
                <%--<t:saveState value="#{pageBeanName.loggedInMinCode}"/>--%>
                <t:saveState value="#{pageBeanName.selElemGuide}"/>
                <t:saveState value="#{pageBeanName.elementGuides}"/>
                <t:saveState value="#{pageBeanName.elmGuideTypes}"/>
                
                
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


                <t:saveState value="#{insurExcludedEmps.kwtPage}"/>
                <t:saveState value="#{insurExcludedEmps.initiated}"/>
                <t:saveState value="#{insurExcludedEmps.categories}"/>
                <t:saveState value="#{insurExcludedEmps.ministries}"/>
                <t:saveState value="#{insurExcludedEmps.selectedCatId}"/>
                <t:saveState value="#{insurExcludedEmps.selectedMiniId}"/>
                <t:saveState value="#{insurExcludedEmps.selectedCatName}"/>
                <t:saveState value="#{insurExcludedEmps.selectedMiniName}"/>
                <t:saveState value="#{insurExcludedEmps.wrongMinCode}"/>
                <t:saveState value="#{insurExcludedEmps.realCivilId}"/>
                <t:saveState value="#{insurExcludedEmps.empName}"/>
                <%--<t:saveState value="#{insurExcludedEmps.loggedInMinCode}"/>--%>
                <t:saveState value="#{insurExcludedEmps.selElemGuide}"/>
                <t:saveState value="#{insurExcludedEmps.elementGuides}"/>
                <t:saveState value="#{insurExcludedEmps.elmGuideTypes}"/>
                
                <%-- Start BSN Paging --%>
                <t:saveState value="#{insurExcludedEmps.filterDTO}"/>
                <t:saveState value="#{insurExcludedEmps.searchDTO}"/>
                <t:saveState value="#{insurExcludedEmps.resettedPageIndex}"/>
                <t:saveState value="#{insurExcludedEmps.pagingRequestDTO}"/>
                <t:saveState value="#{insurExcludedEmps.pagingBean.totalListSize}"/>
                <t:saveState value="#{insurExcludedEmps.pagingBean.myPagedDataModel}"/>
                
                <t:saveState value="#{insurExcludedEmps.ascending}"/>
                <%--<t:saveState value="#{insurExcludedEmps.sortcolumnName}"/>--%>
                <t:saveState value="#{insurExcludedEmps.bsnSortingColumnName}"/>
                <t:saveState value="#{insurExcludedEmps.sortedTable}"/>
                <t:saveState value="#{insurExcludedEmps.usingBsnPaging}"/>
                <t:saveState value="#{insurExcludedEmps.updateMyPagedDataModel}"/>
                <t:saveState value="#{insurExcludedEmps.indexArray}"/>
                <%-- End BSN Paging --%>


                <%-- Start Paging --%>
                <t:saveState value="#{insurExcludedEmps.currentPageIndex}"/>
                <t:saveState value="#{insurExcludedEmps.oldPageIndex}"/>
                <t:saveState value="#{insurExcludedEmps.sorting}"/>
                <t:saveState value="#{insurExcludedEmps.usingPaging}"/>
                
                <t:saveState value="#{pageBeanName.pageMode}"/>            
    
                
                </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
    </h:form>
    
    <t:panelGroup forceId="true" id="scriptGenerator">
        <c:scriptGenerator form="myForm"/>
    </t:panelGroup>  
    
     <script type="text/javascript">
        function filterDivInputOnKeyPress(e, inputtext, ajaxFunction, nextFocusId) {
          var event = e || window.event;
          if (event.keyCode == 13) {
              trimBorders(inputtext.value);
              if (inputtext.value == '') {
                  if (nextFocusId != null) {
                      setFocusOnElement(nextFocusId);
                  }
                  event.stopPropagation();
                  event.preventDefault();
                  return false;
              }

              if (ajaxFunction != null) {
                  ajaxFunction();
              }
              else {
                  event.stopPropagation();
                  event.preventDefault();
              }
              if (nextFocusId != null) {
                  setFocusOnElement(nextFocusId);
              }

          }
      }

      function filterDivInput(inputtextName, ajaxFunction, nextFocusId) {
          inputtext = document.getElementById(inputtextName);
          trimBorders(inputtext.value);
          if (inputtext.value == '') {
              if (nextFocusId != null) {
                  setFocusOnElement(nextFocusId);
              }
              //              event.stopPropagation();
              //              event.preventDefault();
              return false;
          }

          if (ajaxFunction != null) {
              ajaxFunction();
          }
          else {
              //              event.stopPropagation();
              //              event.preventDefault();
          }
          if (nextFocusId != null) {
              setFocusOnElement(nextFocusId);
          }
      }

      function clearMsgs() {
          if (document.getElementById('errorCodeId2') != null) {
              document.getElementById('errorCodeId2').innerHTML = "";
          }
      }
      
//      document.onclick = function (evt) {
//       try{ 
//           evt = evt || window.event;
//            var selTarget = evt.target;
//            var onClickValue = selTarget.getAttribute('onclick');
//            if (selTarget.type === "submit" && onClickValue.indexOf("AJAX.Submit") ===  - 1) {
//                var selForm = selTarget.form;
//                selForm.onsubmit = function () {
//                    //block();
//                }
//            }
//        }
//        catch (ex) {
//        }
//    };
     </script>
     
</f:view>