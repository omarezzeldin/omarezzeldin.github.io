<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:panelGrid id="mainEmpsAddPnl" forceId="true" width="100%">
    <t:panelGrid columns="8" width="100%" forceId="true" id="mainDataPanel" rowClasses="row01,row02" cellpadding="3"
                 cellspacing="0">
        <h:outputText value="#{intgBundle.civil_id}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:inputText onkeypress="FireButtonClickOldStyle(event,'filterByCivilButton')" readonly="#{pageBeanName.civilExist}"
                         maxlength="12" forceId="true" id="CivilIdAdd" value="#{pageBeanName.civilId}"
                         styleClass="textbox"/>
            <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>&nbsp;</f:verbatim>
            <t:panelGroup>
                <t:commandButton forceId="true" id="filterByCivilButton" type="button"
                                 value="#{globalResources.Available}" styleClass="cssButtonSmall"
                                 onclick="clearMsgs();if(validatemyForm()){searchLines();}" rendered="#{!pageBeanName.civilExist}"/>
                <a4j:jsFunction name="searchLines" action="#{pageBeanName.filterByCivilId}"
                                reRender="mainEmpsAddPnl,mainDataPanel,scriptGenerator,displayBtnPanel,buttonGrid"/>
            </t:panelGroup>
            <a4j:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}"
                               oncomplete="setFocusOnlyOnElement('CivilIdAdd');" rendered="#{pageBeanName.civilExist}"
                               styleClass="cssButtonSmall" actionListener="#{pageBeanName.reSetData}"
                               reRender="mainDataPanel,scriptGenerator,displayBtnPanel,buttonGrid"/>
            <f:verbatim>&nbsp;</f:verbatim>
            <a4j:commandButton type="button" id="Search" styleClass="cssButtonSmall"
                               value="#{globalResources.SearchButton}"
                               oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();"
                               action="#{pageBeanName.showSearchForEmployeeDiv}"
                               reRender="lovEmp,mainDataEmpPanel,displayBtnPanel"
                               disabled="#{pageBeanName.civilExist}"/>
            <f:verbatim>
            <br/>
            </f:verbatim>
            <c2:requiredFieldValidator componentToValidate="CivilIdAdd" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" highlight="false"
                                       uniqueId="CivilIdAddUniqueId" />
            <c2:regularExpressionValidator componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/"
                                           errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false"
                                           display="dynamic" />
            <t:outputText forceId="true" id="invalNat" value="#{pageBeanName.natMsg}"
                          rendered="#{pageBeanName.invalidNationality}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="payrollInfoExist" value="#{intgBundle.emp_payroll_info_not_exist}"
                                  rendered="#{!pageBeanName.payrollInfoExist}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}"
                          rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="empHired" value="#{intgBundle.emp_not_hired}"
                          rendered="#{!pageBeanName.empHired}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="empHiredInMin" value="#{intgBundle.emp_not_hired_in_min}"
                          rendered="#{!pageBeanName.empHiredInMin}" styleClass="errMsg"/>

        </t:panelGroup>
       
        <h:outputText  value="#{globalResources.globalName}" styleClass="divtext"/>
         <t:panelGroup colspan="3">
        <t:inputText disabled="true" styleClass="textboxmedium" forceId="true" id="emp_name"
                     value="#{pageBeanName.employeesDTO.citizensResidentsDTO.fullName}"/>
        </t:panelGroup>  
        
        <h:outputText value="#{intgBundle.cader}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
        <t:inputText disabled="true" styleClass="textboxmedium" forceId="true" id="empCader"
                     value="#{pageBeanName.cader}"/>
        </t:panelGroup>
        
        <h:outputText value="#{intgBundle.group}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
        <t:inputText disabled="true" styleClass="textboxmedium" forceId="true" id="empGroup"
                     value="#{pageBeanName.group}"/>
        </t:panelGroup>
        
        <h:outputText value="#{intgBundle.finance_degree}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
        <t:inputText disabled="true" styleClass="textboxmedium" forceId="true" id="empDegree"
                     value="#{pageBeanName.degree}"/>
         </t:panelGroup>
        
        <h:outputText value="#{intgBundle.job_name}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
        <t:inputText disabled="true" styleClass="textboxmedium" forceId="true" id="empJob"
                     value="#{pageBeanName.employeesDTO.jobDTO.name}"/>
         </t:panelGroup>
        
        <h:outputText value="#{intgBundle.work_center}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
        <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="empWorkCenter"
                     value="#{pageBeanName.employeesDTO.workCenterDTO.name}"/>
         </t:panelGroup>
        
        <h:outputText value="#{intgBundle.budgetProgram}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
        <t:inputText disabled="true" styleClass="textboxmedium" forceId="true" id="bgtProgram"
                     value="#{pageBeanName.employeesDTO.bgtProgramsDTO.name}"/>
        </t:panelGroup>
        
        <h:outputText value="#{intgBundle.EXCEPTION_TYPE}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:selectOneMenu forceId="true" id="exceptionType" styleClass="Dropdownbox"
                             value="#{pageBeanName.selExceptionType}" onblur="setFousAtNextAfterComboItem();">
                <f:selectItem itemLabel="#{intgBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.exceptionTypes}" itemLabel="#{exceptionTypes.name}"
                               itemValue="#{exceptionTypes.code.key}" var="exceptionTypes"/>
            </t:selectOneMenu>
            <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:compareValidator componentToValidate="exceptionType" active="#{empAddBean.civilExist}"
                                 componentToCompare="virtualvalueId" display="dynamic" operator="not"
                                 errorMessage="#{intgBundle.selectItem}" highlight="false" />
        </t:panelGroup>
        <h:outputText value="#{intgBundle.sal_elm_guide}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:selectOneMenu forceId="true" id="salElemGuide" styleClass="Dropdownbox"
                             value="#{pageBeanName.selElemGuide}" onblur="setFousAtNextAfterComboItem();">
                <f:selectItem itemLabel="#{intgBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.elementGuides}" itemLabel="#{elementGuides.name}"
                               itemValue="#{elementGuides.code.key}" var="elementGuides"/>
            </t:selectOneMenu>
            <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:compareValidator componentToValidate="salElemGuide" active="#{empAddBean.civilExist}"
                                 componentToCompare="virtualvalueId" display="dynamic" operator="not"
                                 errorMessage="#{intgBundle.selectItem}" highlight="false" />
        </t:panelGroup>
        <%-- -------------------------------------------------------------------------------%>
        <h:outputText id="date_from_month" value="#{intgBundle.date_from_month}" styleClass="lable01"/>
        <t:panelGroup>
            <t:selectOneMenu forceId="true" id="date_from_monthMenu" styleClass="textboxsmall2"
                             value="#{pageBeanName.fromMonth}">
                <f:selectItem itemLabel="#{intgBundle.select}" itemValue=""/>
                <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}"
                               var="months"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:requiredFieldValidator componentToValidate="date_from_monthMenu" 
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      display="dynamic" active="#{empAddBean.civilExist}" />
            <%--<c2:customValidator componentToValidate="date_from_monthMenu" display="dynamic"
                               errorMessage="#{intgBundle.lastCalcDate_Validation_Mess}" highlight="false"
                               function="validateDates"  active="#{empAddBean.civilExist}"/>--%>
       </t:panelGroup>
       
        <h:outputText id="date_from_year" value="#{intgBundle.date_from_year}" styleClass="lable01" style="display: inline; margin-right: 7px;"/>
        <t:panelGroup>
            <t:selectOneMenu forceId="true" id="date_from_yearMenu" styleClass="textboxsmall2"
                             value="#{pageBeanName.fromYear}">
                <f:selectItem itemLabel="#{intgBundle.select}" itemValue=""/>
                <t:selectItems value="#{pageBeanName.years}" itemLabel="#{years.code.key}" itemValue="#{years.code.key}"
                               var="years"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:requiredFieldValidator componentToValidate="date_from_yearMenu" 
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      display="dynamic" active="#{empAddBean.civilExist}"/>
        </t:panelGroup>
        <h:outputText id="date_to_month" value="#{intgBundle.date_to_month}" styleClass="lable01"/>
        
        <t:panelGroup>
            <t:selectOneMenu forceId="true" id="date_to_monthMenu" styleClass="textboxsmall2"
                             value="#{pageBeanName.toMonth}">
                <f:selectItem itemLabel="#{intgBundle.select}" itemValue=""/>
                <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}"
                               var="months"/>
            </t:selectOneMenu>
            <%-- <h:outputText value="*" styleClass="mandatoryAsterisk"/>--%> <%-- <c:requiredFieldValidator componentToValidate="date_to_monthMenu" 
                 errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic"/>--%>
            <c2:customValidator componentToValidate="date_to_monthMenu" display="dynamic"
                               errorMessage="#{intgBundle.endDate_Validation_Mess}" highlight="false"
                               function="validateEquevilance"  active="#{empAddBean.civilExist}"/>
        </t:panelGroup>
        
        <h:outputText id="date_to_year" value="#{intgBundle.date_to_year}" styleClass="lable01" style="display: inline; margin-right: 7px;"/>
        <t:panelGroup>
            <t:selectOneMenu forceId="true" id="date_to_yearMenu" styleClass="textboxsmall2"
                             value="#{pageBeanName.toYear}" style="margin-right: 20px;">
                <f:selectItem itemLabel="#{intgBundle.select}" itemValue=""/>
                <t:selectItems value="#{pageBeanName.years}" itemLabel="#{years.code.key}" itemValue="#{years.code.key}"
                               var="years"/>
            </t:selectOneMenu>
            <%-- <h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
            <f:verbatim>
                <br/>
            </f:verbatim>
             <c2:customValidator componentToValidate="date_to_monthMenu" display="dynamic"
                               errorMessage="#{intgBundle.checkNotEmptyEnddate_Mess}" highlight="false"
                               function="checkNotEmpty"  active="#{empAddBean.civilExist}"/>
           
            <%--<c:customValidator componentToValidate="date_to_yearMenu" display="dynamic"
                               errorMessage="#{intgBundle.missingField}" highlight="false"
                               function="checkNotEmpty" />--%>
            <%-- <c:requiredFieldValidator componentToValidate="date_to_yearMenu" 
                 errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic"/>--%>
        </t:panelGroup>
        <%--<t:outputLabel styleClass="lable01" id="lblFromDate" value="#{intgBundle.from_date}"/>--%>
        <%--<t:panelGroup>
            <t:inputText id="clndr_From_Date" styleClass="textboxsmall2" style="width: 87px;" forceId="true"
                         value="#{pageBeanName.fromDate}" maxlength="7" onkeypress="validateDateOnEvent(this);"
                         onkeyup="validateDateOnEvent(this);" converter="MonthYearConverter"/>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:outputText value="(mm/yyyy)" styleClass="gray_hint"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:outputText id="fromDateErr" forceId="true" value="#{globalResources.messageErrorForAdding}"
                          styleClass="errMsg" style="display:none;"/>            
            <c2:requiredFieldValidator componentToValidate="clndr_From_Date" display="dynamic"
                                       active="#{empAddBean.civilExist}" errorMessage="#{globalResources.missingField}"
                                       highlight="false"/>
        </t:panelGroup>--%>
        <%-- <t:panelGroup> <t:inputCalendar forceId="true" id="clndr_From_Date"
             title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
             popupDateFormat="dd/MM/yyyy" value="#{pageBeanName.fromDate}" size="20" maxlength="200"
             styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true"
             renderPopupButtonAsImage="true" align="#{globalResources.align}"
             popupLeft="#{shared_util.calendarpopupLeft}"> <f:converter converterId="SqlDateConverter"/>
             </t:inputCalendar> <t:outputLabel value="*" styleClass="mandatoryAsterisk"/> <f:verbatim><br/></f:verbatim>
             <c2:requiredFieldValidator componentToValidate="clndr_From_Date" display="dynamic"
             active="#{empAddBean.civilExist}" errorMessage="#{globalResources.missingField}" highlight="false" />
             <c2:dateFormatValidator componentToValidate="clndr_From_Date" display="dynamic"
             active="#{empAddBean.civilExist}" errorMessage="#{globalResources.messageErrorForAdding}"
             highlight="false"/> </t:panelGroup>--%>
        <%--<t:outputLabel styleClass="lable01" id="lblDateTo" value="#{intgBundle.until_date}"
                       style="margin-left:10px;"/>--%>
        <%--<t:panelGroup>
            <t:inputText id="clndr_Date_To" styleClass="textboxsmall2" style="width: 87px;" forceId="true"
                         value="#{pageBeanName.untilDate}" maxlength="7" onkeypress="validateDateOnEvent(this);"
                         onkeyup="validateDateOnEvent(this);" converter="MonthYearConverter"/>
            <f:verbatim>&nbsp;</f:verbatim>
            <t:outputText value="(mm/yyyy)" styleClass="gray_hint"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:outputText id="untilDateErr" forceId="true" value="#{globalResources.messageErrorForAdding}"
                          styleClass="errMsg" style="display:none;"/>
            <t:outputText id="untilDateErr1" forceId="true" value="#{intgBundle.startDategreaterEndDate}"
                          styleClass="errMsg" style="display:none;"/>            
        </t:panelGroup>--%>
        <%-- <t:panelGroup> <t:inputCalendar forceId="true" id="clndr_Date_To"
             title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
             popupDateFormat="dd/MM/yyyy" value="#{pageBeanName.untilDate}" size="20"
             maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell"
             renderAsPopup="true" renderPopupButtonAsImage="true" align="#{globalResources.align}"
             popupLeft="#{shared_util.calendarpopupLeft}" > <f:converter converterId="SqlDateConverter"/>
             </t:inputCalendar> <f:verbatim><br/></f:verbatim> <c2:compareDateValidator
             componentToValidate="clndr_From_Date" componentToCompare="clndr_Date_To" active="#{empAddBean.civilExist}"
             operator="before" display="dynamic" errorMessage="#{intgBundle.startDategreaterThanEndDate}" />
             <c2:dateFormatValidator componentToValidate="clndr_Date_To" display="dynamic"
             active="#{empAddBean.civilExist}" errorMessage="#{globalResources.messageErrorForAdding}"
             highlight="false"/> </t:panelGroup>--%>
        <h:outputText value="#{intgBundle.exception_ratio}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:inputText styleClass="textbox" forceId="true" onkeyup="enabelFloatOnly(this);" id="excepRatio"
                         value="#{pageBeanName.exceptionRatio}"/>
            <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
            <c2:requiredFieldValidator componentToValidate="excepRatio" display="dynamic"
                                       active="#{empAddBean.civilExist}" errorMessage="#{globalResources.missingField}"
                                       highlight="false" />
        </t:panelGroup>
        <h:outputText value="#{intgBundle.INSEXCREASON_CODE}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:selectOneMenu forceId="true" id="excReason" styleClass="Dropdownbox"
                             value="#{pageBeanName.selExceptionCode}" onblur="setFousAtNextAfterComboItem();">
                <f:selectItem itemLabel="#{intgBundle.all}" itemValue="#{pageBeanName.virtualConstValue}"/>
                <t:selectItems value="#{pageBeanName.exceptionReasons}" itemLabel="#{exceptionReasons.name}"
                               itemValue="#{exceptionReasons.code.key}" var="exceptionReasons"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <h:outputText value="#{intgBundle.exception_notes}" styleClass="divtext"/>
        <t:panelGroup colspan="7" id="ssds" forceId="true">
            <t:inputTextarea style=" width: 730px;" styleClass="masterinput" forceId="true" id="notes"
                             value="#{pageBeanName.exceptionNotes}"/>
            <t:inputHidden value="#{pageBeanName.hasSalCalWithInsurance}" id="salcalchidden" forceId="true" />
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid align="center" columns="3" forceId="true" id="buttonGrid">
        <t:panelGroup rendered="#{pageBeanName.kwtMode}">
            <t:commandButton forceId="true" type="button" value="#{intgBundle.exception_save}"
                             disabled="#{!pageBeanName.civilExist}" styleClass="cssButtonSmall" id="btnSave"
                             onclick="if(validatemyForm()){saveException();} else {return false;}"/>
            <a4j:jsFunction name="saveException" action="#{pageBeanName.saveEmployeeException}"
                            rerender="mainEmpsAddPnl,mainDataPanel,buttonGrid,scriptGenerator,OperationBar,divMsg"
                            oncomplete="changeVisibilityMsg();"/>
        </t:panelGroup>
        
         
        
        <t:panelGroup rendered="#{pageBeanName.kwtMode}">
            <t:commandButton forceId="true" type="button" value="#{intgBundle.exception_save_new}"
                             disabled="#{!pageBeanName.civilExist}" styleClass="cssButtonSmall" id="btnSaveAdd"
                             onclick="if(validatemyForm()){saveAndNew();} else {return false;}"/>
            <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveEmployeeExceptionAndNew}"
                            rerender="mainEmpsAddPnl,mainDataPanel,buttonGrid,scriptGenerator,OperationBar,divMsg"
                            oncomplete="changeVisibilityMsg();"/>
        </t:panelGroup>
        
        <t:panelGroup rendered="#{!pageBeanName.kwtMode}">
            <t:commandButton forceId="true" type="button" value="#{intgBundle.exception_save}"
                             disabled="#{!pageBeanName.civilExist}" styleClass="cssButtonSmall" id="btnSave2"
                             onclick="if(validatemyForm()){saveException();} else {return false;}"/>
            <a4j:jsFunction name="saveException" action="#{pageBeanName.saveException}"
                            rerender="ssds,salcalchidden,buttonGrid,customDiv1,mainEmpsAddPnl,mainDataPanel,buttonGrid,scriptGenerator,OperationBar,divMsg"
                            oncomplete="if(checkSalCalc()){ changeVisibilityDiv(window.blocker, window.customDiv1); changeVisibilityMsg(); }else{ changeVisibilityMsg(); }  "/>
        </t:panelGroup>
        
         <t:panelGroup rendered="#{!pageBeanName.kwtMode}">
            <t:commandButton forceId="true" type="button" value="#{intgBundle.exception_save_new}"
                             disabled="#{!pageBeanName.civilExist}" styleClass="cssButtonSmall" id="btnSaveAdd2"
                             onclick="if(validatemyForm()){saveAndNew();} else {return false;}"/>
            <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveEmployeeExceptionAndNewGulf}"
                            rerender="ssds,salcalchidden,buttonGrid,customDiv1,mainEmpsAddPnl,mainDataPanel,buttonGrid,scriptGenerator,OperationBar,divMsg"
                            oncomplete="if(checkSalCalc()){ changeVisibilityDiv(window.blocker, window.customDiv1); changeVisibilityMsg(); }else{ changeVisibilityMsg(); }"/>
        </t:panelGroup>
        
        <t:panelGroup>
            <t:commandButton forceId="true" id="backToMain" type="button" onclick="backFunc();"
                             value="#{intgBundle.exception_back}" styleClass="cssButtonSmall"/>
            <a4j:jsFunction name="backFunc" action="#{pageBeanName.backToView}"
                            rerender="mainEmpsAddPnl,mainDataPanel,buttonGrid,scriptGenerator,OperationBar"
                            oncomplete="changeVisibilityMsg();"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGrid>
<script language="javascript" type="text/javascript">
  function checkSalCalc() {
      var isCalced = document.getElementById('salcalchidden').value;
      if (isCalced=='1') {
          return true;
      }else{
          return false;
      }
  }

  function validateDates() {
      var lastCalcMonth = document.getElementById('lastCalcMonth').value;
      var lastCalcYear = document.getElementById('lastCalcYear').value;
      var date_from_monthMenu = document.getElementById('date_from_monthMenu').value;
      var date_from_yearMenu = document.getElementById('date_from_yearMenu').value;

      // var date_to_monthMenu = document.getElementById('date_to_monthMenu').value;
      // var date_to_yearMenu = document.getElementById('date_to_yearMenu').value;
      if (lastCalcMonth != null && lastCalcMonth != "" && lastCalcYear != null && lastCalcYear != "") {
          if (parseInt(date_from_yearMenu) < parseInt(lastCalcYear)) {

              return false;
          }
          else if (parseInt(date_from_yearMenu) == parseInt(lastCalcYear)) {
              if (parseInt(date_from_monthMenu) <= parseInt(lastCalcMonth)) {
                  return false;
              }
              else {
                  return true;
              }
          }
          else {
              return true;
          }

      }
  }

  function validateEquevilance() {
      var date_from_monthMenu = document.getElementById('date_from_monthMenu').value;
      var date_from_yearMenu = document.getElementById('date_from_yearMenu').value;

      var date_to_monthMenu = document.getElementById('date_to_monthMenu').value;
      var date_to_yearMenu = document.getElementById('date_to_yearMenu').value;

      if (parseInt(date_to_yearMenu) < parseInt(date_from_yearMenu)) {
          return false;
      }
      else if (parseInt(date_to_yearMenu) == parseInt(date_from_yearMenu)) {
          if (parseInt(date_to_monthMenu) < parseInt(date_from_monthMenu)) {
              return false;
          }
          else {
              return true;
          }
      }
      else {
          return true;
      }
  }

  function checkNotEmpty() {

      var date_to_monthMenu = document.getElementById('date_to_monthMenu').value;
      var date_to_yearMenu = document.getElementById('date_to_yearMenu').value;

      if ((date_to_yearMenu != "" && date_to_monthMenu != "") || (date_to_yearMenu == "" && date_to_monthMenu == "")) {
          return true;
      }
      else {
          return false;
      }
  }
  // by saad 
  function testFn() {
      if (document.getElementById('date_from_yearMenu').value == '')
          return true;
      else 
          return false;
  }

  function validateAll() {
      document.getElementById('fromDateErr').style.display = "none";
      document.getElementById('untilDateErr').style.display = "none";
      document.getElementById('untilDateErr1').style.display = "none";
      var result = 0;
      if (!validatemyForm()) {
          result = result + 1;
      }
      var fromDate = document.getElementById('clndr_From_Date');
      var untilDate = document.getElementById('clndr_Date_To');
      var fromDateValue = fromDate.value;
      var fromDateLength = fromDateValue.length;
      var untilDateValue = untilDate.value;
      var untilDateLength = untilDateValue.length;

      if (fromDateLength > 0 && fromDateLength < 7) {
          document.getElementById('fromDateErr').style.display = "inline";
          result = result + 1;
      }
      else {
          document.getElementById('fromDateErr').style.display = "none";
      }

      if (untilDateLength > 0 && untilDateLength < 7) {
          document.getElementById('untilDateErr').style.display = "inline";
          result = result + 1;
      }
      else {
          document.getElementById('untilDateErr').style.display = "none";
      }

      //////////////////
      var firstValue = fromDateValue.split('/');
      var secondValue = untilDateValue.split('/');
      if (fromDateLength == 7 && (firstValue[0] < 1 || firstValue[0] > 12)) {
          document.getElementById('fromDateErr').style.display = "inline";
          result = result + 1;
      }

      if (untilDateLength == 7 && (secondValue[0] < 1 || secondValue[0] > 12)) {
          document.getElementById('untilDateErr').style.display = "inline";
          result = result + 1;
      }

      if (fromDateLength == 7 && untilDateLength == 7) {
          if (firstValue[0] >= 1 && firstValue[0] <= 12 && secondValue[0] >= 1 && secondValue[0] <= 12) {

              var firstDate = new Date();
              firstDate.setFullYear(firstValue[1], (firstValue[0] - 1), 01);

              var secondDate = new Date();
              secondDate.setFullYear(secondValue[1], (secondValue[0] - 1), 01);
              if (firstDate > secondDate) {
                  document.getElementById('untilDateErr1').style.display = "inline";
                  result = result + 1;
              }
              else {
                  document.getElementById('untilDateErr1').style.display = "none";
              }
          }
      }

      if (result != 0)
          return false;
      else 
          return true;
  }

  function clearMsgs() {
      if (document.getElementById('empHired') != null) {
          document.getElementById('empHired').innerHTML = "";
      }

      if (document.getElementById('invalCivilID') != null) {
          document.getElementById('invalCivilID').innerHTML = "";
      }

      if (document.getElementById('payrollInfoExist') != null) {
          document.getElementById('payrollInfoExist').innerHTML = "";
      }
      if (document.getElementById('invalNat') != null) {
          document.getElementById('invalNat').innerHTML = "";
      }
  }
</script>