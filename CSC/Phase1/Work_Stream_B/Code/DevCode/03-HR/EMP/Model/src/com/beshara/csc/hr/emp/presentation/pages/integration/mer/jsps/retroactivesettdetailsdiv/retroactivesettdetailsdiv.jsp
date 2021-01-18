<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<style>
.textboxsmall2 {
    width: 65px;
}
.info-icon-btn {
    padding-right: 13px;
}
</style>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="merIntgResources"/>
<t:panelGroup forceId="true" id="errPanel">
    <t:outputText id="nameError" forceId="true" styleClass="errMsg" value="#{retroactiveSettDetailsHelperBean.errMsg}"
                  rendered="#{retroactiveSettDetailsHelperBean.showErrMsg}"/>
    <htm:br rendered="#{retroactiveSettDetailsHelperBean.showErrMsg}"/>
</t:panelGroup>
<t:panelGroup forceId="true" id="merRetroactiveSettDetailsIntgDiv">
    <%-- ##################START################# Strat of Join/Cancel Section ##############SHEKA#####################--%>
    <t:panelGroup forceId="true" id="merIntgRetroactiveSettDetails">
        <t:panelGroup forceId="true" id="merIntgInsertableSection">
            <t:outputText style="font-weight: bold; display: block; "
                          value="#{merIntgResources['auto_settLement_msg']}"/>
            <t:panelGrid columns="4" border="0" width="100%" columnClasses="center" cellpadding="3" cellspacing="0"
                         rowClasses="row01,row02">
                <t:outputText value="#{merIntgResources.month}" styleClass="lable01"/>
                <t:panelGroup>
                    <t:selectOneMenu forceId="true" id="monthMenu" styleClass="DropdownboxMedium2"
                                     value="#{retroactiveSettDetailsHelperBean.month}">
                        <t:selectItems value="#{retroactiveSettDetailsHelperBean.months}" itemLabel="#{months.name}"
                                       itemValue="#{months.code.key}" var="months"/>
                    </t:selectOneMenu>
                    <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c:requiredFieldValidator group="ValidationGroup" componentToValidate="monthMenu" display="dynamic"
                                              errorMessage="#{globalResources.missingField}"></c:requiredFieldValidator>
                </t:panelGroup>
                <t:outputText value="#{merIntgResources.year}" styleClass="lable01"/>
                <t:panelGroup>
                    <t:selectOneMenu forceId="true" id="yearMenu" styleClass="DropdownboxMedium2"
                                     value="#{retroactiveSettDetailsHelperBean.year}"
                                     onblur="setFousAtNextAfterComboItem();">
                        <t:selectItems value="#{retroactiveSettDetailsHelperBean.years}" itemLabel="#{years.code.key}"
                                       itemValue="#{years.code.key}" var="years"/>
                    </t:selectOneMenu>
                    <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim> 
                        <br/>
                    </f:verbatim>
                    <c:requiredFieldValidator group="ValidationGroup" componentToValidate="yearMenu" display="dynamic"
                                              errorMessage="#{globalResources.missingField}"></c:requiredFieldValidator>
                </t:panelGroup>
                <%-- <t:panelGroup colspan="3"> <h:outputText value="#{merIntgResources.total_suggested_sett_value}"
                     styleClass="divtext" style="font-weight: bold; display: block; text-align: right;"/> </t:panelGroup>--%>
                <%-- <t:panelGroup> <t:inputText forceId="true" id="totalSuggestedSettValue" disabled="true"
                     styleClass="textbox" value="#{retroactiveSettDetailsHelperBean.totalSuggSettValue}"/> <f:verbatim>
                     <br/> </f:verbatim> <t:outputText id="totalSuggValueErr" forceId="true"
                     value="#{merIntgResources.valueEqualZeroErr}" styleClass="errMsg" style="display:none;"/>
                     </t:panelGroup>--%>
                <!-- Start CSC-12179 -->
                <h:outputText value="#{merIntgResources.type}"/>
                <t:panelGroup>
                    <t:selectOneMenu forceId="true" id="typeListId" styleClass="DropdownboxMedium2"
                                     value="#{retroactiveSettDetailsHelperBean.pageDTO.typesDTOKey}">
                        <f:selectItem itemValue="" itemLabel="#{merIntgResources.select}"/>
                        <t:selectItems value="#{retroactiveSettDetailsHelperBean.types}" itemLabel="#{type.name}"
                                       itemValue="#{type.code.key}" var="type"/>
                        <a4j:support actionListener="#{retroactiveSettDetailsHelperBean.filterDecMkrBytype}"
                                     event="onchange" reRender="decisionMakerListId"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c:requiredFieldValidator group="ValidationGroup" componentToValidate="typeListId" display="dynamic"
                                              errormessage="#{globalResources.missingField}" highlight="false"
                                              uniqueid="maintain_regTypeID"/>
                    <%-- active="#{pageBeanName.addDecisionMode || pageBeanName.cancelDecisionMode}"--%>
                </t:panelGroup>
                <h:outputText value="#{merIntgResources.decisions_decision_maker}"/>
                <t:panelGroup colspan="2">
                    <t:selectOneMenu forceId="true" id="decisionMakerListId" styleClass="DropdownboxMedium2"
                                     value="#{retroactiveSettDetailsHelperBean.pageDTO.decisionMakerDTOKey}">
                        <f:selectItem itemValue="" itemLabel="#{merIntgResources.select}"/>
                        <t:selectItems value="#{retroactiveSettDetailsHelperBean.decisionMakers}"
                                       itemLabel="#{dMaker.name}" itemValue="#{dMaker.code.key}" var="dMaker"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c:requiredFieldValidator group="ValidationGroup" componentToValidate="decisionMakerListId"
                                              display="dynamic" errormessage="#{globalResources.missingField}"
                                              highlight="false" uniqueid="maintain_decisionDecisionMakerID"/>
                    <%-- active="#{!pageBeanName.cancelDecisionMode}"--%>
                </t:panelGroup>
               
               <h:outputText value="#{merIntgResources.dec_mer_int_notes}"  />
               <t:panelGroup colspan="4"  >
                <t:inputText  styleClass="textboxLarge"  forceId="true" id="mer_int_decisionNotes" 
                                  style="width:495px;"
                                 value="#{retroactiveSettDetailsHelperBean.pageDTO.decisionTitle}"/>
                                 
                                 
               </t:panelGroup>                  
               <!--start of  CSC-17389 -->
                <%--h:outputText value="#{merIntgResources.decision_number}"/>
                <t:panelGroup>
                    <t:inputText forceId="true" id="decisionNumberId" maxlength="10" onkeyup="disableCharacters(this)"
                                 styleClass="textboxmedium"
                                 value="#{retroactiveSettDetailsHelperBean.pageDTO.decisionNumber}"/>
                    <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c:requiredFieldValidator group="ValidationGroup" componentToValidate="decisionNumberId"
                                              display="dynamic" errormessage="#{globalResources.missingField}"
                                              highlight="false" uniqueid="decisionNumberIdID"/>
                </t:panelGroup--%>
                   <!--end of  CSC-17389 -->
                <!-- End CSC-12179 -->
            </t:panelGrid>
        </t:panelGroup>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <t:panelGroup forceId="true" id="merIntgRetroactiveSettDetailsSection">
            <%-- <t:outputText style="font-weight: bold; display: block; "
                 value="#{merIntgResources['settlementsDetail_title']}"/>--%>
            <%-- <jsp:include page="${retroactiveSettDetailsHelperBean.retroactiveSettDetailsPath}" flush="true"/>--%>
            <t:panelGroup forceId="true" id="merIntgRetroactiveSettDetailsTblPnl" styleClass="fullWidthScroll160">
                <t:dataTable forceId="true" id="merIntgRetroactiveSettDetailsTbl" var="list"
                             value="#{retroactiveSettDetailsHelperBean.myTableData}" binding="#{retroactiveSettDetailsHelperBean.myDataTable}"
                             forceIdIndexFormula="#{list.code.key}" footerClass="grid-footer"
                             headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered" width="100%" align="top"
                             dir="#{shared_util.pageDirection}" styleClass="grid-footer" renderedIfEmpty="false">
                    <t:column id="month_column" sortable="false" width="8%">
                        <f:facet name="header">
                            <h:outputText id="header_month" value="#{merIntgResources.month_header}"/>
                        </f:facet>
                        <h:outputText id="content_month" value="#{list.month}" converter="MonthYearConverter"/>
                    </t:column>
                    <t:column id="elmValue_column" sortable="false" width="12%"
                              rendered="#{retroactiveSettDetailsHelperBean.showElmValueCol}">
                        <f:facet name="header">
                            <h:outputText id="header_elmValue" value="#{merIntgResources.elm_value_header}"/>
                        </f:facet>
                        <t:outputText id="content_elmValue" forceId="true" value="#{list.elmGuideValue}" converter="DoubleThreeDigitConverter" />
                    </t:column>
                    <t:column id="fromDate_column" sortable="false" width="12%">
                        <f:facet name="header">
                            <h:outputText id="header_fromDate" value="#{merIntgResources.from_period_header}"/>
                        </f:facet>
                        <h:outputText id="content_fromDate" value="#{list.fromDate}" converter="SqlDateConverter"/>
                    </t:column>
                    <t:column id="toDate_column" sortable="false" width="8%">
                        <f:facet name="header">
                            <h:outputText id="header_toDate" value="#{merIntgResources.to_period_header}"/>
                        </f:facet>
                        <h:outputText id="content_toDate" value="#{list.toDate}" converter="SqlDateConverter"/>
                    </t:column>
                    <t:column id="neededValue_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="header_neededValue" value="#{merIntgResources.needed_value_header}"/>
                        </f:facet>
                        <h:outputText id="content_neededValue" value="#{list.dueValue}" converter="DoubleThreeDigitConverter"/>
                    </t:column>
                    <t:column id="paidValue_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="header_paidValue" value="#{merIntgResources.paid_value_header}"/>
                        </f:facet>
                        <h:outputText id="content_paidValue" value="#{list.paidValue}" converter="DoubleThreeDigitConverter"/>
                    </t:column>
                    <t:column id="calcStatus_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="header_calcStatus" value="#{merIntgResources.calc_status_header}"/>
                        </f:facet>
                        <h:outputText id="content_calcStatus" value="#{list.statusName}"/>
                    </t:column>

                    <t:column id="oldSett_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="header_oldSett" value="#{merIntgResources.old_sett}"/>
                        </f:facet>
                        <h:outputText id="content_oldSett" value="#{list.oldSettValue}" converter="DoubleThreeDigitConverter"/>
                    </t:column>
                     <t:column id="tottalSettlements_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="header_tottalSettlements" value="#{merIntgResources.tottalSettlements_label}"/>
                        </f:facet>
                        <h:outputText id="content_tottalSettlements" value="#{list.tottalSettlements}" converter="DoubleThreeDigitConverter"/>
                    </t:column>
                    <t:column id="suggestedSett_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="header_suggestedSett" value="#{merIntgResources.suggested_sett_header}"/>
                        </f:facet>
                        <t:inputText forceId="true" id="content_suggestedSett" value="#{list.suggValue}"
                                     onchange="calcTotalSuggSettValue();" styleClass="textboxsmall2"/>
                        <a4j:jsFunction name="calcTotalSuggSettValue"
                                        action="#{retroactiveSettDetailsHelperBean.calcTotalSuggSettValue}"
                                        reRender="totalSuggestedSettValue"/>
                        <f:verbatim>
                            <br/>
                        </f:verbatim>
                        <%-- <t:outputText id="valueErr1" forceId="true" value="#{merIntgResources.invalid_Sugg_Value}"
                             styleClass="errMsg" style="display:none;"/>--%>
                        <%-- <t:outputText id="valueErr2" forceId="true" value="#{globalResources.missingField}"
                             styleClass="errMsg" style="display:none;"/>--%>
                        <t:outputText id="valueErr3" forceId="true" value="#{merIntgResources.err_invalid_number}"
                                      styleClass="errMsg" style="display:none;"/>
                        <%-- <t:outputText id="valueErr4" forceId="true" value="#{merIntgResources.valueEqualZeroErr}"
                             styleClass="errMsg" style="display:none;"/>--%>
                    </t:column>
                     <t:column id="notes_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="header_notes" value=""/>
                        </f:facet>
                         <t:commandButton id="displayIntervals" forceId="true" 
                                          onmouseout="hideTip()" value="" 
                                          styleClass="#{!list.hasIntervals? 'info-icon-btn-18 imgGray' : 'info-icon-btn-18'}"
                                          action="#{retroactiveSettDetailsHelperBean.showEmpIntervals}" 
                                          onmouseover="doTooltip(event,'#{merIntgResources.displayIntervals}')"
                                          disabled="#{!list.hasIntervals}" />
                         <t:commandButton id="displaySettlement" forceId="true" 
                                          onmouseout="hideTip()" value="" 
                                          styleClass="#{!list.hasRetroactiveSettlements ? 'info-icon-btn2-18 imgGray' : 'info-icon-btn2-18'}"
                                          action="#{retroactiveSettDetailsHelperBean.showEmpRetroactiveSett}"
                                          onmouseover="doTooltip(event,'#{merIntgResources.displaySettlement}')"
                                          disabled="#{!list.hasRetroactiveSettlements}" />               
                </t:column>
                </t:dataTable>
            </t:panelGroup>
            <t:panelGrid columns="2">
                <t:panelGroup>
                    <h:outputText value="#{merIntgResources.total_suggested_sett_value}" styleClass="divtext"
                                  style="font-weight: bold; display: block; text-align: right;"/>
                </t:panelGroup>
                <t:panelGroup>
                    <t:inputText forceId="true" id="totalSuggestedSettValue" disabled="true" styleClass="textbox"
                                 value="#{retroactiveSettDetailsHelperBean.totalSuggSettValue}">
                        <f:convertNumber maxFractionDigits="3" />
                        
                    </t:inputText>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <t:outputText id="totalSuggValueErr" forceId="true" value="#{merIntgResources.valueEqualZeroErr}"
                                  styleClass="errMsg" style="display:none;"/>
                </t:panelGroup>
            </t:panelGrid>
        </t:panelGroup>
        <t:panelGroup forceId="true" id="merIntgNavSection">
            <t:panelGrid columns="1" border="0" width="100%" columnClasses="center">
                <t:panelGroup>
                 <a4j:commandButton id="printBt" styleClass="cssButtonSmall" value="#{resourcesBundle.print}" 
                  actionListener="#{retroactiveSettDetailsHelperBean.opensettReport}" reRender="sett_reportUrlId" 
                  oncomplete="openReportWindow('sett_reportUrlId');"
                  />
                     <%--<a4j:support event="openReportWindow('sett_reportUrlId');"   actionListener="#{retroactiveSettDetailsHelperBean.opensettReport}" reRender="sett_reportUrlId"
                         oncomplete=""  />--%>  
                  
                    <a4j:commandButton id="merIntgRetroactiveSettDetailsSave1"
                                       reRender="#{retroactiveSettDetailsHelperBean.rerenderComponentsIds}"
                                       onclick=" if(!validateDiv()){return false;}"
                                       value="#{merIntgResources.save_with_Sett_label}" styleClass="cssButtonSmall"
                                       action="#{retroactiveSettDetailsHelperBean.accept}"
                                       oncomplete="doClick('onCompleteAcceptJSBtn', null);"/>
                    <t:commandButton id="merIntgRetroactiveSettDetailsSave2"
                                     action="#{retroactiveSettDetailsHelperBean.acceptWithoutSettlement}"
                                     value="#{merIntgResources.saveDecsWithNoSett_title}" styleClass="cssButtonSmall"/>
                    <t:commandButton id="backButtonRetroactiveSettDetailsIntgDiv" forceId="true" 
                                     value="#{globalResources['CancelButton']}" 
                                     styleClass="cssButtonSmall" action="#{retroactiveSettDetailsHelperBean.goBack}"/>
                                     <%--type="button" onclick="doIntgDivBack();"--%>
                </t:panelGroup>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGroup>
    <%-- <t:inputHidden value="#{retroactiveSettDetailsHelperBean.containerDivId}" id="divName" forceId="true"/>--%>
    <t:inputHidden id="sett_reportUrlId" forceId="true" value="#{retroactiveSettDetailsHelperBean.reportUrlLink}"/>
    <t:inputHidden value="#{retroactiveSettDetailsHelperBean.listSize}" id="listSizeInputHidden" forceId="true"/>
    <t:commandButton type="button" style="display:none;" forceId="true" id="onCompleteAcceptJSBtn"
                     onclick="#{retroactiveSettDetailsHelperBean.onCompleteJSBtn}"/>
    <%-- ##################START################ Strat of Hidden Values Section #############SHEKA#####################--%>
</t:panelGroup>
<f:verbatim>
    <script type="text/javascript">
              
      function doIntgDivBack() {
          hideLookUpDiv(window.blocker, window.retroactiveSettDetailsDiv, null, null, null);
          return false;
      }

      function validateDiv() {

          var listSize = document.getElementById('listSizeInputHidden').value;
          var validTable = true;
          var totalSuggValue = document.getElementById('totalSuggestedSettValue').value;
          document.getElementById('totalSuggValueErr').style = "display: none;";

          for (var i = 0;i < listSize;i++) {

              var suggValue = document.getElementById('content_suggestedSett' + '[' + i + ']').value;
              //        var comparedValue = document.getElementById('content_elmValue' + '[' + i + ']').innerHTML;
              //document.getElementById('valueErr2' + '[' + i + ']').style = "display: none;";
              document.getElementById('valueErr3' + '[' + i + ']').style = "display: none;";
              //document.getElementById('valueErr1' + '[' + i + ']').style = "display: none;";
              /*if (suggValue == null || suggValue == "") {
                  document.getElementById('valueErr2' + '[' + i + ']').style = "display: inline;";
                  validTable = false;
              }
              else {*/

              if (suggValue != null && suggValue != "" && !isFloatAcceptNegative(suggValue)) {
                  /*if (parseInt(suggValue) > parseInt(comparedValue)) {
                          document.getElementById('valueErr1' + '[' + i + ']').style = "display: inline;";
                          validTable = false;
                      }
                      if (suggValue == "0" || suggValue == "0.0") {
                          document.getElementById('valueErr4' + '[' + i + ']').style = "display: inline;";
                          validTable = false;
                      }
                  }
                  else {*/
                  document.getElementById('valueErr3' + '[' + i + ']').style = "display: inline;";
                  validTable = false;
                  // }
              }

          }
          if (totalSuggValue == "0" || totalSuggValue == "0.0") {
              document.getElementById('totalSuggValueErr').style = "display: inline;";
              validTable = false;
          }
          var clientValidator = validatemyForm('ValidationGroup');

          return validTable && clientValidator;
      }

      function isFloatAcceptNegative(s) {
          var reFloat = /[-+]?(\d*[.])?\d+/
          if ((s == null) || (s.length == 0))
              if (isFloat.arguments.length == 1)
                  return false;
              else 
                  return (isFloat.arguments[1] == true);
          return reFloat.test(s)
      }
    </script>
</f:verbatim>

<t:saveState value="#{retroactiveSettDetailsHelperBean.showDecisionText}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.myTableData}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.years}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.months}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.totalSuggSettValue}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.year}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.month}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.saveDecisionMethodName}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.containerBeanName}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.containerDivId}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.successAddSettDecMethodName}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.civilId}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.pageDTO}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.pageDTO.decisionMakerDTOKey}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.empSalElmSerial}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.employeesDTO}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.elmGuideCode}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.previousDecisionDTO}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.onCompleteJSBtn}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.realCivilId}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.empSuggSettMethodName}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.defaultEmpSuggSettMethod}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.checkDecision}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.rerenderComponentsIds}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.showElmValueCol}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.resultList}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.lastCalcSalaryDate}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.decisionType}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.amount}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.fromDate}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.toDate}"/>
<t:saveState value="#{retroactiveSettDetailsHelperBean.reportUrlLink}"/>
