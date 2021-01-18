<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<t:panelGroup id="searchPanel" forceId="true">
<t:outputText styleClass="errMsg" forceId="true" id="outPutMsg"/>
<t:panelGrid columns="4" rowClasses="row02,row01" cellpadding="3" columnClasses="colu1,colu2" cellspacing="0" width="100%">
 <!-- Row1-->
 <t:outputLabel value="#{resourcesBundle.civilid}" styleClass="lable01"/>
 <t:inputText id="search_first_inputTxt" forceId="true" tabindex="1"  onkeydown="onKeyDownFirstElement(event,'emp_Name','customSearchBackBtn')" value="#{pageBeanName.empEmployeesSearchDTO.civilId}" styleClass="textbox" onkeyup="disableCharacters(this);" maxlength="12" converter="javax.faces.Long"/>
 
 <t:outputLabel value="#{resourcesBundle.candidate_name}" styleClass="lable01"/>
 <t:inputText id="emp_Name" tabindex="2" forceId="true" value="#{pageBeanName.searchEmpName}"  styleClass="textbox"/>
 
 
 
 <!-- Row2-->
 
 <t:outputLabel value="#{resourcesBundle.hireDateFrom}" styleClass="lable01"/>
  <t:panelGroup>
 <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="hireDateFrom_Date" tabindex="3" value="#{pageBeanName.empEmployeesSearchDTO.hireDateFrom}" size="20"
                  maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                  popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
 <f:converter converterId="SqlDateConverter"/> 
 </t:inputCalendar>
 <f:verbatim><br/></f:verbatim>
 <c2:dateFormatValidator highlight="false" componentToValidate="hireDateFrom_Date" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
</t:panelGroup>
  <t:outputLabel value="#{resourcesBundle.dec_date_to}" styleClass="lable01"/>
 <t:panelGroup>
 <t:inputCalendar  popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="hireDateTo_Date" tabindex="4" value="#{pageBeanName.empEmployeesSearchDTO.hireDateTO}" size="20"
                  maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                  popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
 <f:converter converterId="SqlDateConverter"/> 
 </t:inputCalendar>
 <f:verbatim><br/></f:verbatim>
 <c2:compareDateValidator   componentToValidate="hireDateFrom_Date" componentToCompare="hireDateTo_Date" operator="before" errorMessage="#{resourcesBundle.hireDateToShouldbegreaterThanFrom}" display="dynamic"/> 
 <c2:dateFormatValidator highlight="false" componentToValidate="hireDateTo_Date" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
</t:panelGroup>
 
</t:panelGrid>

<t:panelGrid columns="2" align="center" >
 <%--<t:commandButtonvalue="#{globalResources.SearchButton}" action="#{pageBeanName.simpleSearch}" styleClass="cssButtonSmall" onclick="x= validateSearchData('search_first_inputTxt,emp_Name,job_Name,hiretype,work_Center,startWork_Date','outPutMsg','#{globalResources.enter_at_least_one_field}'); y = validatemyForm(); return (x&&y);"/>--%>
 
 <t:commandButton forceId="true" id="customSearchBtn" value="#{globalResources.SearchButton}" action="#{pageBeanName.simpleSearch}" tabindex="5" styleClass="cssButtonSmall"  onclick="x= validateSearchData('search_first_inputTxt,emp_Name,hireDateFrom_Date,hireDateTo_Date','outPutMsg','#{globalResources.enter_at_least_one_field}'); y = validatemyForm(); return (x&&y);"/> 
 <t:commandButton forceId="true" id="customSearchBackBtn"  tabindex="6"  onkeydown="onKeyDownLastElement(event,'customSearchBtn','search_first_inputTxt')" value="#{globalResources.back}" type="button" onclick="hideLookUpDiv(window.blocker,window.divSearch,null,null);setFocusAtMyFirstElement();" styleClass="cssButtonSmall"/> 
</t:panelGrid>
</t:panelGroup>
<t:panelGrid align="center">
<t:outputText value="#{globalResources.search_info_message}" styleClass="search_info_message" />  
</t:panelGrid>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="startWork_Date,0,150" />
