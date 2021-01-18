<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<t:panelGroup style="width:100%" forceId="true" id="MsgGroupDecsion">
    <t:panelGrid  align="center">
        <t:outputText styleClass="errMsg" forceId="true" id="outPutMsg"/>
    </t:panelGrid>
</t:panelGroup>
<t:panelGrid align="center" columns="1" width="100%">
<t:panelGroup forceId="true" id="hiredecisonSearch" style="width:100%">
    
<t:panelGrid columns="4" rowClasses="row02,row01" cellpadding="0" columnClasses="colu1,colu2" cellspacing="0" width="100%">
 <!-- Row1-->
 <t:outputLabel value="#{resourcesBundle.civilid}" styleClass="lable01"/>
 <t:inputText id="search_first_inputTxt"  tabindex="1"  onkeydown="onKeyDownFirstElement(event,'emp_Name','customSearchBackBtn')" forceId="true" value="#{pageBeanName.empEmployeesSearchDTO.civilId}" styleClass="textbox" onkeyup="disableCharacters(this);" maxlength="12" converter="javax.faces.Long"/>
 
 <t:outputLabel value="#{resourcesBundle.candidate_name}" styleClass="lable01"/>
 <t:inputText id="emp_Name" tabindex="2" forceId="true" value="#{pageBeanName.empEmployeesSearchDTO.empName}"  styleClass="textbox"/>
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
 <!-- Row3-->
  <t:outputLabel value="#{resourcesBundle.candStatus}" styleClass="lable01"/>
 
<t:selectOneMenu id="hirestage"  tabindex="5" value="#{pageBeanName.empEmployeesSearchDTO.empHireStage}" styleClass="textbox" forceId="true">
  <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
    <t:selectItems value="#{pageBeanName.hireStagesList}" itemLabel="#{hireStagesList.name}" itemValue="#{hireStagesList.code.hirstageCode}" var="hireStagesList"/>
  
  <f:converter converterId="javax.faces.Long"/>
 </t:selectOneMenu>

  
<%-- <t:outputLabel value="#{resourcesBundle.hiretype}" styleClass="lable01"/>
 
 
 
 <t:selectOneMenu id="hiretype" value="#{pageBeanName.empEmployeesSearchDTO.empHireTypes}" styleClass="textboxmedium" forceId="true">
  <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue=""/>
    <t:selectItems value="#{pageBeanName.hireTypesList}" itemLabel="#{hireTypesList.name}" itemValue="#{hireTypesList.code.hirtypeCode}" var="hireTypesList"/>
 
  <f:converter converterId="javax.faces.Long"/>
 </t:selectOneMenu>--%>
 
        <%--t:panelGrid columns="2" align="center" >
                <h:outputText id="hiredecisonLabelSearch" value="#{resourcesBundle.hiretype}" styleClass="divtext"/>
                 <t:selectOneMenu forceId="true" id="hireTypesList"  value="#{hireDecisionListBean.hireType}"  styleClass="Dropdownbox">
                       <f:selectItem  itemLabel="" itemValue="#{hireDecisionListBean.itemSelection}"/>
                       <t:selectItems var="hireTypesList" itemLabel="#{hireTypesList.name}" itemValue="#{hireTypesList.code.key}" value="#{hireDecisionListBean.hireTypes}"/>                       
                 </t:selectOneMenu>
               
        </t:panelGrid--%>
        </t:panelGrid>
    </t:panelGroup>
    <t:panelGrid columns="2" align="center">        
        <t:commandButton forceId="true" id="customSearchBtn" value="#{globalResources.SearchButton}" tabindex="6" action="#{pageBeanName.searchHireDecision}" styleClass="cssButtonSmall"  onclick="x= validateSearchData('search_first_inputTxt,emp_Name,hireDateFrom_Date,hireDateTo_Date,hirestage','outPutMsg','#{globalResources.enter_at_least_one_field}'); y = validatemyForm(); return (x&&y);"/>
        <t:commandButton forceId="true" id="customSearchBackBtn"  tabindex="7"  onkeydown="onKeyDownLastElement(event,'customSearchBtn','search_first_inputTxt')"  value="#{globalResources.back}" type="button" onclick="hideLookUpDiv(window.blocker,window.divSearch,null,null);settingFoucsAtListPage();" styleClass="cssButtonSmall"/>
    </t:panelGrid>
</t:panelGrid>
<f:verbatim> &nbsp; &nbsp; &nbsp; </f:verbatim>
<t:outputText value="#{globalResources.search_info_message}" styleClass="search_info_message" />


<t:saveState value="#{hireDecisionListBean.hireType}" />
<t:saveState value="#{hireDecisionListBean.hireTypesList}" />
<t:saveState value="#{hireDecisionListBean.empEmployeesSearchDTO}" />
1
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="hireDateTo_Date,5,45:hireDateFrom_Date,5,45" />
