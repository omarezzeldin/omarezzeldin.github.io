<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:saveState value="#{detailBeanName.fullColumnName}"/>
<t:saveState value="#{detailBeanName.sortAscending}"/>
<t:saveState value="#{detailBeanName.sorting}"/>

<t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="col1_WC" cellspacing="0" width="100%"
             forceId="true" id="criminalDataPanel" columns="4" align="cenetr" border="0"
             rendered="#{detailBeanName.hasCriminalCase}">
    <h:outputText value="#{resourcesBundle.criminal_data_auto}"/>
    <t:commandButton value="#{globalResources.Available}" action="#{detailBeanName.saveAndCheckWebService}"
                     styleClass="cssButtonSmall"/>
    <h:outputText value="#{resourcesBundle.ciminal_result}"/>
    <h:inputText disabled="true" styleClass="textboxmedium" value="#{detailBeanName.criminalResult}"/>
    <h:outputText value="#{resourcesBundle.book_date}" rendered="#{detailBeanName.showBlock}"/>
    <t:panelGroup rendered="#{detailBeanName.showBlock}">
        <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         forceId="true" value="#{detailBeanName.empProceduresDTO.crmLetterDate}" id="book_date"
                         size="20" maxlength="200" styleClass="textboxmedium" tabindex="10"
                         currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                         title="#{globalResources.inputCalendarHelpText}">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
         <f:verbatim><br/></f:verbatim>
        <c2:requiredFieldValidator componentToValidate="book_date" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.book_num}" rendered="#{detailBeanName.showBlock}"/>
   <t:panelGroup rendered="#{detailBeanName.showBlock}">
        <h:inputText forceId="true" id="crmLetterNo" styleClass="textboxmedium"
                     value="#{detailBeanName.empProceduresDTO.crmLetterNo}" rendered="#{detailBeanName.showBlock}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
         <f:verbatim><br/></f:verbatim>
        <c2:requiredFieldValidator componentToValidate="crmLetterNo" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.criminal_case_num}" rendered="#{detailBeanName.showBlock}"/>
    <t:panelGroup rendered="#{detailBeanName.showBlock}">
        <h:inputText forceId="true" id="crmSheetNo" styleClass="textboxmedium"
                     value="#{detailBeanName.empProceduresDTO.crmSheetNo}" rendered="#{detailBeanName.showBlock}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
         <f:verbatim><br/></f:verbatim>
        <c2:requiredFieldValidator componentToValidate="crmSheetNo" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"/>
    </t:panelGroup>
    <t:panelGroup>
        <t:selectBooleanCheckbox value="#{detailBeanName.empProceduresDTO.crmChecked}"
                                 rendered="#{detailBeanName.showBlock}"/>
        <h:outputText value="#{resourcesBundle.emp_done}" rendered="#{detailBeanName.showBlock}"/>
    </t:panelGroup>
</t:panelGrid>
<t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-6-row-filter">
    <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false" value="#{detailBeanName.currentDisplayDetails}"
                 rowIndexVar="index" binding="#{detailBeanName.currentDataTable}"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                 styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" width="100%" align="top"
                 dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{detailBeanName.sortColumn}"
                 sortAscending="#{detailBeanName.ascending}">
        <t:column id="check_column" width="35px">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"
                                 oncomplete="setAll('checkAll', 'chk2');" reRender="divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}">
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}"
                             oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar"/>
            </t:selectBooleanCheckbox>
        </t:column>
        <t:column id="code_column" width="35" sortable="false">
            <f:facet name="header">
                <t:commandLink id="sort_hireProceduresDTO-code-hirprocedureCode" forceId="true" styleClass="headerLink"
                               value="#{globalResources.Code}" actionListener="#{detailBeanName.sortDataTable}">
                    <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='hireProceduresDTO-code-hirprocedureCode') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='hireProceduresDTO-code-hirprocedureCode') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_code" value="#{list.hireProceduresDTO.code.hirprocedureCode}"/>
        </t:column>
        <t:column id="name_column" width="175" sortable="false">
            <f:facet name="header">
                <t:commandLink id="sort_hireProceduresDTO-name" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.name_procedure}"
                               actionListener="#{detailBeanName.sortDataTable}">
                    <t:graphicImage value="#{(detailBeanName.sortAscending&&detailBeanName.fullColumnName=='hireProceduresDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!detailBeanName.sortAscending&&detailBeanName.fullColumnName=='hireProceduresDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <%-- <f:facet name="header"> <h:outputText value="#{resourcesBundle.name_procedure}" id="name_column1"/>
                 </f:facet>--%>
            <h:outputText id="content_name" value="#{list.hireProceduresDTO.name}"/>
        </t:column>
        <t:column id="joinData_column" width="120">
            <f:facet name="header">
                <h:outputText value="#{resourcesBundle.sending_date}" id="joinData_column1"/>
            </f:facet>
            <t:panelGroup styleClass="sendingDate_DataTableCalender">
                <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" value="#{list.sendDate}" size="20" maxlength="200"
                                 styleClass="textboxsmall2" currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                 renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}"
                                 id="sendingDate_DataTableCalender">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            </t:panelGroup>
            <c2:requiredFieldValidator componentToValidate="sendingDate_DataTableCalender" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" highlight="false" isArray="true"
                                       arraySize="#{empProceduresBean.currentListSize}" uniqueId="maintain_regTypeID"/>
            <c2:dateFormatValidator componentToValidate="sendingDate_DataTableCalender"
                                    errorMessage="#{resourcesBundle.checkDataEntry}" display="dynamic" isArray="true"
                                    arraySize="#{empProceduresBean.currentListSize}"
                                    uniqueId="sendingDate_DataTableCalenderId"/>
        </t:column>
        <t:column id="resulrData_column" width="110">
            <f:facet name="header">
                <h:outputText value="#{resourcesBundle.result_date}" id="resulrData_column1"/>
            </f:facet>
            <t:panelGroup styleClass="resultDate_DataTableCalender">
                <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" value="#{list.resultDate}" size="20" maxlength="200"
                                 styleClass="textboxsmall2" currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                 renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}"
                                 id="resultDate_DataTableCalender">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <c2:dateFormatValidator componentToValidate="resultDate_DataTableCalender"
                                        errorMessage="#{resourcesBundle.checkDataEntry}" display="dynamic"
                                        isArray="true" arraySize="#{empProceduresBean.currentListSize}"
                                        uniqueId="resultDate_DataTableCalenderId"/>
                <c2:compareDateValidator componentToValidate="resultDate_DataTableCalender"
                                         componentToCompare="sendingDate_DataTableCalender" operator="after"
                                         display="dynamic" errorMessage="#{resourcesBundle.reviseDate}"
                                         highlight="false" isArray="true"
                                         arraySize="#{empProceduresBean.currentListSize}"
                                         uniqueId="resultDate_DataTableCompareCalenderId"/>
            </t:panelGroup>
        </t:column>
        <t:column id="resultCombo_column" width="200">
            <f:facet name="header">
                <h:outputText id="resultCombo_name" value="#{resourcesBundle.result_procedure}"/>
            </f:facet>
            <t:panelGroup style="display: block; text-align: right; width: 200px;">
                <t:selectOneMenu forceId="true" id="proceduresResultList" styleClass="textbox"
                                 value="#{list.procedureResultKey}" onchange="handleReasonBtn(#{index});">
                    <t:selectItems value="#{detailBeanName.proceduresResultList}" itemLabel="#{proceduresResult.name}"
                                   itemValue="#{proceduresResult.code.key}" var="proceduresResult"/>
                    <a4j:support event="onchange" reRender="dataT_data_panel_procedures,dataT_data,openReasonDivPG"/>
                </t:selectOneMenu>
                <f:verbatim>&nbsp;</f:verbatim>
                <t:panelGroup id="openReasonDivPG">
                    <t:commandButton forceId="true" id="openReasonDivBtn" value="#{globalResources.Available}"
                                     rendered="#{list.procedureResultKey=='3'}" styleClass="cssButtonSmaller"
                                     onclick="openReasonDivJs(#{index}); return false;" style="visibility:'hidden'"/>
                </t:panelGroup>
            </t:panelGroup>
        </t:column>
    </t:dataTable>
    <t:panelGrid rendered="#{detailBeanName.currentListSize == 0}" styleClass="msg">
        <h:outputText value="#{globalResources.global_noTableResults}"/>
        <h:outputText value="#{globalResources.global_noTableResults_m2m}"/>
    </t:panelGrid>
</t:panelGroup>


<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="dataTableCalenderPosition,200,5"/>
<t:inputHidden forceId="true" id="empResultSuspended" value="#{detailBeanName.empResultSuspended}"/>
<a4j:jsFunction name="openReasonDivJs" action="#{detailBeanName.showSuspensionReasonsDiv}" reRender="divEditLookup"
                oncomplete="changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('fakeInputForFocus').focus();">
    <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{detailBeanName.procedureRowIndex}"/>
</a4j:jsFunction>
<f:verbatim>
    <script type="text/javascript">
  setFocusAtMyFirstElement();

      function setFocusAtMyFirstElement() {
          if (document.getElementById('addButton') != null) {
              document.getElementById('addButton').focus();
          }
      }

      function handleReasonBtn(index) {
          var listItemId = 'proceduresResultList[' + index + ']';
          var selectedResult = document.getElementById(listItemId).value;
          var suspendedResultCode = document.getElementById('empResultSuspended').value;
          var btnId = 'openReasonDivBtn[' + index + ']';
          var btnComponent = document.getElementById(btnId);
          btnComponent.style.visibility = 'hidden';
          if (selectedResult == suspendedResultCode) {
              btnComponent.style.visibility = 'visible';
          }
      }
      handleAllReasonBtns();

      function handleAllReasonBtns() {
          var doLoop = true;
          var index = 0;
          while (doLoop) {
              var btnId = 'openReasonDivBtn[' + index + ']';
              var btnComponent = document.getElementById(btnId);
              if (btnComponent == null) {
                  doLoop = false;
              }
              else {
                  handleReasonBtn(index);
              }
              index++;
          }
      }
    </script>
</f:verbatim>