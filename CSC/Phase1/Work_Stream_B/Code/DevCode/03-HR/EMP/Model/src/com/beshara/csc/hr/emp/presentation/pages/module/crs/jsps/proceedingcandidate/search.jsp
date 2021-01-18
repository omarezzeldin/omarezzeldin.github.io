<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<t:panelGroup id="searchPanel" forceId="true">
<t:outputLabel forceId="true"  id="dateMessage"/>
<t:outputText styleClass="errMsg" forceId="true" id="outPutMsg"/>
<t:panelGrid columns="2" rowClasses="row02,row01" cellpadding="0" columnClasses="colu1,colu2" cellspacing="0" width="100%" onkeypress="FireButtonClick('customSearchOkBtn');">
 <!-- Row1-->
 <t:panelGroup>
 <t:outputLabel value="#{resourcesBundle.canditatesDateFrom}" styleClass="lable01"/>
 <%--h:outputText value="*" styleClass="mandatoryAsterisk"/--%> 
 </t:panelGroup>
 <t:panelGroup>
 <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" value="#{pageBeanName.candidatePersonsSearchDTO.candidateFrom}" popupDateFormat="dd/MM/yyyy" forceId="true" id="canditatesDateFrom"
                  onchange="markDirty();" size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                  popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                  onblur="setFocusOnlyOnElement('canditatesDateTo');">
  <f:converter converterId="SqlDateConverter"/>
 </t:inputCalendar>
 <f:verbatim> <br/> </f:verbatim>
 <%--c2:requiredFieldValidator componentToValidate="canditatesDateFrom" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 3}"/--%>
 <c2:dateFormatValidator highlight="false" componentToValidate="canditatesDateFrom" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
 </t:panelGroup>
 <!-- Row2-->
 <t:panelGroup>
 <t:outputLabel value="#{resourcesBundle.canditatesDateTo}" styleClass="lable01"/>
 <%--h:outputText value="*" styleClass="mandatoryAsterisk"/--%>
 </t:panelGroup>
 <t:panelGroup>
 <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" value="#{pageBeanName.candidatePersonsSearchDTO.candidateTo}" popupDateFormat="dd/MM/yyyy" forceId="true" id="canditatesDateTo"
                  onchange="markDirty();" size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                  popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
  <f:converter converterId="SqlDateConverter"/>
 </t:inputCalendar>
 <f:verbatim> <br/> </f:verbatim>
<%--c2:requiredFieldValidator componentToValidate="canditatesDateTo" errorMessage="#{globalResources.missingField}" display="dynamic" active="#{proceedingCandidateListBean.divIndicator == 3}"/--%>
<c2:compareDateValidator componentToValidate="canditatesDateFrom" componentToCompare="canditatesDateTo" operator="before" display="dynamic" errorMessage="#{resourcesBundle.startgreaterThanEnd}"/>
 <c2:dateFormatValidator highlight="false" componentToValidate="canditatesDateTo" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
 </t:panelGroup>
 <!-- Row3-->
 <t:outputLabel value="#{resourcesBundle.civilIdCandidate}" styleClass="lable01"/>
 <t:panelGroup>
 <t:inputText onkeyup="disableCharacters(this);" value="#{pageBeanName.candidatePersonsSearchDTO.civilId}" maxlength="12" styleClass="textbox" forceId="true" id="civilIdCandidate"/>
 <f:verbatim> <br/> </f:verbatim>
 <c2:regularExpressionValidator componentToValidate="civilIdCandidate" pattern="/^[0-9]{12}$/"  display="dynamic" errorMessage="#{resourcesBundle.civil_no_not_less_than_12}" active="#{proceedingCandidateListBean.divIndicator == 3}"/>
 </t:panelGroup>
</t:panelGrid>
<t:panelGrid columns="3" align="center" width="30%">
 <t:commandButton forceId="true" id="customSearchOkBtn" value="#{globalResources.SearchButton}" action="#{pageBeanName.search}" onclick="x= validateSearchData('canditatesDateFrom,canditatesDateTo,civilIdCandidate','outPutMsg','#{globalResources.enter_at_least_one_field}'); y = validatemyForm(); return (x&&y);" styleClass="cssButtonSmall"/>
 <t:commandButton value="#{resourcesBundle.advancedSearch}" action="#{pageBeanName.goAdvancedSearch}" styleClass="cssButtonSmall"/>
 <t:commandButton value="#{globalResources.back}" type="button" id="customSearchBackBtn" forceId="true" 
                    onclick="hideLookUpDiv(window.blocker,window.divSearch,null,null);" styleClass="cssButtonSmall" onblur="setFocusOnlyOnElement('canditatesDateFrom');" />
</t:panelGrid>
</t:panelGroup>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="canditatesDateFrom,250,80:canditatesDateTo,250,70" />
