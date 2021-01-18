<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>

    <t:panelGrid forceId="true" id="MsgSearchId">
    <t:outputText styleClass="errMsg" id="errMsg_output" forceId="true" />
    </t:panelGrid>
   
   <t:panelGrid columns="2" border="0" rowClasses="row01,row02"  width="100%"  cellpadding="3" cellspacing="0" forceId="true" id="searchPanelId" onkeyup="FireButtonClick('searchButtonx');">  
       
         <%--<h:outputText value="#{globalResources.Code}" styleClass="lable01"/> 
        <t:panelGroup>
            <t:inputText converter="javax.faces.Long" value="#{pageBeanName.searchDTO.serial}"  styleClass="textboxmedium" onkeyup="disableCharacters(this)" onkeypress="keyPressNumber(false,event);" maxlength="38" forceId="true" id="serialCode" onblur="setFocusOnlyOnElement('experienceDescSearch');"/>
       
        </t:panelGroup>
        --%>
       
        <h:outputText value="#{resourcesBundle.external_experience_desc}" styleClass="lable01"/> 
        <t:panelGroup>
            <t:inputText value="#{pageBeanName.searchDTO.experienceDesc}" styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" forceId="true" id="experienceDescSearch"/>
        </t:panelGroup>
        
        <h:outputText value="#{resourcesBundle.external_experience_fromDate}" styleClass="lable01"/> 
        <t:panelGroup>
               <t:inputCalendar  popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"   forceId="true" value="#{pageBeanName.searchDTO.fromDate}" id="experienceFromDate_search"
                        size="20" maxlength="10" styleClass="textbox" currentDayCellClass="currentDayCell" 
                        renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
               <f:converter converterId="SqlDateConverter"/>
             </t:inputCalendar>
                <htm:br/>
             <c:dateFormatValidator   componentToValidate="experienceFromDate_search" active="#{externalExperienceListBean.pageMode==3}" errorMessage="#{resourcesBundle.dateFormat}"   highlight="false"  display="dynamic" />
        </t:panelGroup>
        
        <h:outputText value="#{resourcesBundle.external_experience_untilDate}" styleClass="lable01"/> 
        <t:panelGroup>
                 <t:inputCalendar  popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.searchDTO.untilDate}" id="ToDate_search"
                        size="20" maxlength="10" styleClass="textbox" currentDayCellClass="currentDayCell" 
                        renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
               <f:converter converterId="SqlDateConverter"/>
             </t:inputCalendar>
                <htm:br/>
              <c:dateFormatValidator   componentToValidate="ToDate_search" active="#{externalExperienceListBean.pageMode==3}" errorMessage="#{resourcesBundle.dateFormat}"   highlight="false"  display="dynamic" />
              <c:compareDateValidator   componentToValidate="ToDate_search" active="#{externalExperienceListBean.pageMode==3}" componentToCompare="experienceFromDate_search" operator="afterOrEqual" errorMessage="#{resourcesBundle.external_experience_untilDate_greater_than_or_equal_fromDate}" display="dynamic"/> 
        </t:panelGroup>
        
 </t:panelGrid>

        
  <t:panelGrid columns="2" border="0" align="center">      
    <t:commandButton styleClass="cssButtonSmall" forceId="true" id="searchButtonx" value="#{globalResources.SearchButton}" action="#{pageBeanName.search}" onclick=" if( validateSearchData('experienceDescSearch,experienceFromDate_search,ToDate_search','errMsg_output','#{globalResources.enter_at_least_one_field}')) {return validatemyForm()} else {return false;};"/>
  <t:commandButton forceId="true" id="customSearchBackBtn" type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.divSearch,null,null,null);"  styleClass="cssButtonSmall" onblur="setFocusOnlyOnElement('serialCode');"/>
  </t:panelGrid>
  
