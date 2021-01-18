<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{insurExcludedEmps.frm}">
        <f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="intgBundle"/>
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{insurExcludedEmps}">
            <t:aliasBean alias="#{searchMinistryHelper}" value="#{insurExcludedEmps.searchMinistryHelper}">
                <tiles:insert definition="insurexcludedempslist.page" flush="false"></tiles:insert>
                <t:saveState value="#{pageBeanName.singleSelection}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <%-- Start Paging --%>
                <t:saveState value="#{pageBeanName.currentPageIndex}"/>
                <t:saveState value="#{pageBeanName.oldPageIndex}"/>
                <t:saveState value="#{pageBeanName.sorting}"/>
                <t:saveState value="#{pageBeanName.usingPaging}"/>
                <t:saveState value="#{pageBeanName.searchTypeLongVal}"/>
                
                <t:saveState value="#{insurExcludedEmps.kwtPage}"/>
                <t:saveState value="#{insurExcludedEmps.initiated}"/>
                <t:saveState value="#{pageBeanName.categories}"/>
                <t:saveState value="#{pageBeanName.ministries}"/>
                <t:saveState value="#{pageBeanName.selectedCatId}"/>
                <t:saveState value="#{pageBeanName.selectedMiniId}"/>
                <t:saveState value="#{pageBeanName.selectedCatName}"/>
                <t:saveState value="#{pageBeanName.selectedMiniName}"/>
                <t:saveState value="#{pageBeanName.wrongMinCode}"/>
                <t:saveState value="#{pageBeanName.realCivilId}"/>
                <t:saveState value="#{pageBeanName.empName}"/>
                <%--<t:saveState value="#{pageBeanName.loggedInMinCode}"/>--%>
                <t:saveState value="#{pageBeanName.selElemGuide}"/>
                <t:saveState value="#{pageBeanName.elementGuides}"/>
                <t:saveState value="#{pageBeanName.elmGuideTypes}"/>
                
                <%-- Start BSN Paging --%>
                <t:saveState value="#{pageBeanName.filterDTO}"/>
                <t:saveState value="#{pageBeanName.searchDTO}"/>
                <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
                <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
                <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
                <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
                
                <t:saveState value="#{pageBeanName.ascending}"/>
                <%--<t:saveState value="#{pageBeanName.sortcolumnName}"/>--%>
                <t:saveState value="#{pageBeanName.bsnSortingColumnName}"/>
                <t:saveState value="#{pageBeanName.sortedTable}"/>
                <t:saveState value="#{pageBeanName.usingBsnPaging}"/>
                <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.indexArray}"/>
                <t:saveState value="#{pageBeanName.stopDate}"/>
                <t:saveState value="#{pageBeanName.fromDate}"/>
                 <t:saveState value="#{pageBeanName.stopConfirmationMsg}"/>

                <%-- End BSN Paging --%>
            </t:aliasBean>
        </t:aliasBean>
    </h:form>
    <t:panelGroup forceId="true" id="scriptGeneratorPanel">
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