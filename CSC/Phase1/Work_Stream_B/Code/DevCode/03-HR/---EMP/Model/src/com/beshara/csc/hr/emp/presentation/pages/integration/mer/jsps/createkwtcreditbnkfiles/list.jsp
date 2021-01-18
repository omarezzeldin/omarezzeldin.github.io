<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration"
                  var="integrationBundle2"/>
                  
    <h:form id="myForm" binding="#{createCreditBnkFileBean.frm}">
   <%--<t:messages showDetail="true" id="msgdetId" forceId="true"/>--%>
            <t:aliasBean alias="#{pageBeanName}" value="#{createCreditBnkFileBean}">
            <t:aliasBean alias="#{searchMinistryHelper}" value="#{createCreditBnkFileBean.searchMinistryHelper}">
                <tiles:insert definition="createCreaditBnkFile.page" flush="false"/>
                    <t:saveState value="#{pageBeanName.categories}"/>
                    <t:saveState value="#{pageBeanName.ministries}"/>
                    <t:saveState value="#{pageBeanName.selectedCatId}"/>
                    <t:saveState value="#{pageBeanName.selectedMiniId}"/>
                    <t:saveState value="#{pageBeanName.selectedCatName}"/>
                    <t:saveState value="#{pageBeanName.selectedMiniName}"/>
                    <t:saveState value="#{pageBeanName.banksTypeList}"/>
                    <t:saveState value="#{pageBeanName.selectedTypeId}"/>
                    <t:saveState value="#{pageBeanName.months}"/>
                    <t:saveState value="#{pageBeanName.years}"/>
                    <t:saveState value="#{pageBeanName.month}"/>
                    <t:saveState value="#{pageBeanName.year}"/>
                     <t:saveState value="#{pageBeanName.confirmDivOpened}"/>
                      <t:saveState value="#{pageBeanName.minTypeRadio}"/>
                      <t:saveState value="#{pageBeanName.salarySheets}"/>
                      <t:saveState value="#{pageBeanName.selectedSheetCodeStr}"/>
                     <t:saveState value="#{pageBeanName.selectedMinTypeHidden}"/>
                    <t:saveState value="#{pageBeanName.selectedDeductCode}"/>
                     <t:saveState value="#{pageBeanName.deductionList}"/>
                     <t:saveState value="#{pageBeanName.disabledButtons}"/>
                     
                <%-- <t:saveState value="#{pageBeanName.ministryAccountsList}"/> <t:saveState
                     value="#{pageBeanName.selectedDTOS}"/> <t:saveState value="#{pageBeanName.success}"/> <t:saveState
                     value="#{pageBeanName.enableAddBtn}"/> <t:saveState value="#{pageBeanName.accountPurposesList}"/>
                     <t:saveState value="#{pageBeanName.statusList}"/> <t:saveState
                     value="#{pageBeanName.selectedPageDTO}"/> <t:saveState value="#{pageBeanName.selectedPurposeId}"/>
                     <t:saveState value="#{pageBeanName.bankAccountTypeId}"/> <t:saveState
                     value="#{pageBeanName.bankBranchesList}"/> <t:saveState value="#{pageBeanName.selectedBankId}"/>
                     <t:saveState value="#{pageBeanName.selectedBankBranchId}"/> <t:saveState
                     value="#{pageBeanName.minsAccountNumber}"/>--%>
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
      document.onclick = function (evt) {
       try{ 
           evt = evt || window.event;
            var selTarget = evt.target;
            var onClickValue = selTarget.getAttribute('onclick');
            if (selTarget.type === "submit" && onClickValue.indexOf("AJAX.Submit") ===  - 1) {
                var selForm = selTarget.form;
                selForm.onsubmit = function () {
                    //block();
                }
            }
        }
        catch (ex) {
        }
    };
    </script>
</f:view>
