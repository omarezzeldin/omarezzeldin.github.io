<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:panelGrid rowClasses="row02,row01" cellpadding="1" cellspacing="0" width="100%" align="center" columns="4">
    <h:outputText value="#{resourcesBundle.hiretype}" styleClass="lable01"/>
    <t:panelGroup colspan="3">
        <t:selectOneMenu forceId="true" styleClass="textboxmedium" id="employees_hiretype"
                         value="#{pageBeanName.empHireTypesValue}"
                         disabled="#{pageBeanName.empEmployeesSearchDTO.hireDateFrom!=null}">
            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.all}"/>
            <t:selectItems value="#{pageBeanName.firstLevelHireTypesList}" itemLabel="#{hireTypesList.name}"
                           itemValue="#{hireTypesList.code.key}" var="hireTypesList"/>
        </t:selectOneMenu>
    </t:panelGroup>
    <t:outputLabel value="#{resourcesBundle.hireDateFrom}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         forceId="true" id="hireDateFrom_Date" tabindex="1"
                         onkeydown="onKeyDownFirstElement(event,'hireDateTo_Date','btn_search')"
                         value="#{pageBeanName.empEmployeesSearchDTO.hireDateFrom}" size="20" maxlength="200"
                         styleClass="textboxmedium" currentDayCellClass="currentDayCell" renderAsPopup="true"
                         align="#{globalResources.align}" onchange="FireButtonClick('btn_search');"
                         onkeypress="FireButtonClick('btn_search');"
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                         title="#{resourcesBundle.inputCalendarHelpText}"
                         disabled="#{pageBeanName.empEmployeesSearchDTO.hireDateFrom!=null}">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator componentToValidate="hireDateFrom_Date"
                                   errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic"/>
        <c2:dateFormatValidator highlight="false" componentToValidate="hireDateFrom_Date" display="dynamic"
                                errorMessage="#{globalResources.InvalidDataEntryException}"/>
    </t:panelGroup>
    <t:outputLabel value="#{resourcesBundle.dec_date_to}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         forceId="true" id="hireDateTo_Date" tabindex="2"
                         value="#{pageBeanName.empEmployeesSearchDTO.hireDateTO}" size="20" maxlength="200"
                         styleClass="textboxmedium" currentDayCellClass="currentDayCell" renderAsPopup="true"
                         align="#{globalResources.align}" onchange="FireButtonClick('btn_search');"
                          onkeypress="FireButtonClick('btn_search');"
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                         title="#{resourcesBundle.inputCalendarHelpText}"
                         disabled="#{pageBeanName.empEmployeesSearchDTO.hireDateFrom!=null}">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:compareDateValidator active="true" componentToValidate="hireDateFrom_Date"
                                 componentToCompare="hireDateTo_Date" operator="before"
                                 errorMessage="#{resourcesBundle.hireDateToShouldbegreaterThanFrom}" display="dynamic"/>
        <c2:dateFormatValidator highlight="false" componentToValidate="hireDateTo_Date" display="dynamic"
                                errorMessage="#{globalResources.InvalidDataEntryException}"/>
    </t:panelGroup>
</t:panelGrid>
<t:panelGrid align="center" cellpadding="1" cellspacing="0" columns="2">
    <t:commandButton forceId="true" id="btn_search" tabindex="3"
                     onkeydown="onKeyDownLastElement(event,'hireDateTo_Date','hireDateFrom_Date')"
                     value="#{resourcesBundle.srchBudgetBtn}" action="#{pageBeanName.filterEmpByHireDate}"
                     styleClass="cssButtonSmall" onclick="return validatemyForm();"
                     disabled="#{pageBeanName.empEmployeesSearchDTO.hireDateFrom!=null}"></t:commandButton>
    <t:commandButton forceId="true" id="btn_reset" tabindex="4" action="#{pageBeanName.reset}"
                     value="#{resourcesBundle.reset}" styleClass="cssButtonSmall"
                     disabled="#{pageBeanName.empEmployeesSearchDTO.hireDateFrom==null}"></t:commandButton>
</t:panelGrid>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID"
               value="hireDateFrom_Date,0,0:hireDateTo_Date,0,0"/>
<script type="text/javascript">
  setFocusAtMyFirstElement();

  function setFocusAtMyFirstElement() {
      document.getElementById('hireDateFrom_Date').focus();
  }

  function resetForm() {
      document.getElementById('hireDateFrom_Date').value = "";
      document.getElementById('hireDateTo_Date').value = "";
      document.getElementById('dataT_data_panel').innerHTML = "";
  }
</script>