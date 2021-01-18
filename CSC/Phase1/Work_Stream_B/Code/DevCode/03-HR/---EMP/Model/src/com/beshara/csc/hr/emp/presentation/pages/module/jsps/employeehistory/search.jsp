<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGroup forceId="true" id="divSearchContainer">
<t:panelGrid columns="4" rowClasses="row02,row01" cellpadding="0" columnClasses="colu1,colu2" cellspacing="0" width="100%">
 
 <!-- Row1-->
 <t:outputText value="#{globalResources.Code}" styleClass="lable01"/>
 <t:inputText value="#{pageBeanName.searchDTO.serial}" styleClass="textboxmedium" forceId="true" id="serialtxt" onblur="setFocusOnlyOnElement('job_title');" maxlength="12"/>
 <t:outputText value="#{resourcesBundle.job_name}" styleClass="lable01"/>
 <%--t:inputText value="#{pageBeanName.searchDTO.jobsDTO.name}"/--%>
 <t:selectOneMenu id="job_title" value="#{pageBeanName.searchDTO.jobCode}" styleClass="textboxmedium" forceId="true"
    onkeypress="FireButtonClick('search_btn');">
      <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="#{pageBeanName.virtual_value}"/>
      <t:selectItems value="#{pageBeanName.jobsSearchDTOList}" var="job" itemValue="#{job.code.keys[0]}" itemLabel="#{job.name}"/>
      
 </t:selectOneMenu>
 <!-- Row2-->
 <t:outputText value="#{resourcesBundle.ministry}" styleClass="lable01"/>
 <%--t:inputText value="#{pageBeanName.searchDTO.workCentersDTO.ministriesDTO.name}"/--%>
  <t:selectOneMenu id="ministry_title" value="#{pageBeanName.searchDTO.minCode}" styleClass="textboxmedium" forceId="true"
    onkeypress="FireButtonClick('search_btn');" onchange="filterWorkCenterByMinistry();">
      <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue=""/>
      <t:selectItems value="#{pageBeanName.ministriesSearchDTOList}" var="ministry" itemValue="#{ministry.code.keys[0]}" itemLabel="#{ministry.name}"/>
      <a4j:jsFunction name="filterWorkCenterByMinistry" action="#{pageBeanName.filterWorkCenterByMinistry}" reRender="workCenter_title" oncomplete="settingFoucsAtDivAdd();"/>
      <f:converter converterId="javax.faces.Long"/>
 </t:selectOneMenu>
 <t:outputText value="#{resourcesBundle.wrkCenter}" styleClass="lable01"/>
 <%--t:inputText value="#{pageBeanName.searchDTO.workCentersDTO.name}"/--%>
   <t:selectOneMenu id="workCenter_title" value="#{pageBeanName.searchDTO.wrkCode}" styleClass="textboxmedium" forceId="true"
    onkeypress="FireButtonClick('search_btn');">
      <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue=""/>
      <t:selectItems value="#{pageBeanName.workCentersSearchDTOList}" var="workCenter" itemValue="#{workCenter.code.keys[1]}" itemLabel="#{workCenter.name}"/>      
 </t:selectOneMenu>
 
 <!-- Row3-->
 <t:outputText styleClass="lable01" value="#{resourcesBundle.fromDate}"/>
<t:panelGroup>
    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.searchDTO.fromDate}" id="clndr_fromDate"
           maxlength="10" styleClass="textbox" currentDayCellClass="currentDayCell"
            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" >
        <f:converter converterId="TimeStampConverter"/>
    </t:inputCalendar>
     <t:outputText forceId="true" id="vaildateIssuanceYearWithDecitionDateId" value="" styleClass="errMsg"/>
    <f:verbatim>
        <br/>
    </f:verbatim>
    <c2:dateFormatValidator componentToValidate="clndr_fromDate"
                    display="dynamic"
                    errorMessage="#{globalResources.messageErrorForAdding}"
                    highlight="false"
                    />
 </t:panelGroup>
 <t:outputText styleClass="lable01" value="#{resourcesBundle.untilDate}"/>
 <t:panelGroup>
    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.searchDTO.untilDate}" id="clndr_untilDate"
            maxlength="10" styleClass="textbox" currentDayCellClass="currentDayCell"
            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" >
        <f:converter converterId="TimeStampConverter"/>
    </t:inputCalendar>
    <f:verbatim>
        <br/>
    </f:verbatim>
    <c2:dateFormatValidator componentToValidate="clndr_untilDate"
                    display="dynamic"
                    errorMessage="#{globalResources.messageErrorForAdding}"
                    highlight="false"
                    />
    <c2:compareDateValidator componentToValidate="clndr_fromDate" componentToCompare="clndr_untilDate" operator="before" display="dynamic" errorMessage="#{regResources.error_message_decision_date_must_be_applied_date}" highlight="false" />
 </t:panelGroup>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="clndr_fromDate,150,100:clndr_untilDate,150,100"/>
</t:panelGrid>
<f:verbatim>
 <br/>
</f:verbatim>
<t:panelGrid columns="2" align="center" width="30%"> 
 <%--t:commandButton id="search_btn" forceId="true" value="#{globalResources.SearchButton}" action="#{pageBeanName.simpleSearchsearch}" styleClass="cssButtonSmall" onclick="settingFoucsAtDivSearch();return validatemyForm();"/--%>
<t:panelGroup>
    <t:commandButton forceId="true" id="search_btn" type="button" onclick="simpleSearchsearch();" styleClass="cssButtonSmall" value="#{globalResources.SearchButton}"/>                
    <a4j:jsFunction name="simpleSearchsearch" action="#{pageBeanName.simpleSearchsearch}" reRender="dataT_data_panel, divSearchContainer, OperationBar" oncomplete="hideLookUpDiv(window.blocker,window.divSearch,'add_first_inputTxt','myForm:error','search');settingFoucsAtDivSearch();return validatemyForm();"/>
</t:panelGroup>
 <t:commandButton forceId="true" id="customSearchBackBtn" onblur="setFocusOnlyOnElement('serialtxt');" value="#{globalResources.back}" type="button" onclick="hideLookUpDiv(window.blocker,window.divSearch,null,null);settingFoucsAtListPage();" styleClass="cssButtonSmall"/>
</t:panelGrid>
</t:panelGroup>