<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<t:messages/>

<t:saveState value="#{pageBeanName.selectedTypeId}"/>
<t:saveState value="#{pageBeanName.selectedCategoryId}"/>
<t:saveState value="#{pageBeanName.org_categories_list}"/>
<t:saveState value="#{pageBeanName.selectedMinistryryId}"/>
<t:saveState value="#{pageBeanName.org_ministries_list}"/>
<t:saveState value="#{pageBeanName.candidatePersonsSearchDTO}"/>
<t:saveState value="#{pageBeanName.jobName}"/>
<t:saveState value="#{pageBeanName.qualName}"/>

<t:panelGroup id="searchPanel" forceId="true" style="width:100%">
<t:outputLabel forceId="true"  id="dateMessage"/>
<t:panelGrid  align="center">
<t:outputText styleClass="errMsg" forceId="true" id="outPutMsg"/>
</t:panelGrid>
<t:div id="navigationDiv" forceId="true" style="visibility:visible;">
<t:panelGrid columns="2" rowClasses="row02,row01" cellpadding="0" columnClasses="col1,col2" cellspacing="0" width="100%">
     <!-- Row1-->
     <t:panelGroup>
          <t:outputLabel value="#{resourcesBundle.canditatesDateFrom}" styleClass="lable01"/>
          <%--h:outputText value="*" styleClass="mandatoryAsterisk"/--%>
     </t:panelGroup>
     <t:panelGroup>
          <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" value="#{pageBeanName.candidatePersonsSearchDTO.candidateFrom}" popupDateFormat="dd/MM/yyyy" forceId="true"
                           id="canditatesDateFrom" onchange="markDirty();" size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true"
                           align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                           onblur="if( (!isVisibleComponent('lookupAddDiv')) && (!isVisibleComponent('lookupEditDiv'))){setFocusOnElement('canditatesDateTo');}">
               <f:converter converterId="SqlDateConverter"/>
          </t:inputCalendar>
          <f:verbatim>
               <br/>
          </f:verbatim>
          <%--c2:requiredFieldValidator componentToValidate="canditatesDateFrom" errorMessage="#{globalResources.missingField}" display="dynamic"/--%>
          <c2:dateFormatValidator componentToValidate="canditatesDateFrom" errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"/>
     </t:panelGroup>
     <!-- Row2-->
     <t:panelGroup>
          <t:outputLabel value="#{resourcesBundle.canditatesDateTo}" styleClass="lable01"/>
          <%--h:outputText value="*" styleClass="mandatoryAsterisk"/--%>
     </t:panelGroup>
     <t:panelGroup>
          <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" value="#{pageBeanName.candidatePersonsSearchDTO.candidateTo}" popupDateFormat="dd/MM/yyyy" forceId="true"
                           id="canditatesDateTo" onchange="markDirty();" size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true"
                           align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
               <f:converter converterId="SqlDateConverter"/>
          </t:inputCalendar>
          <f:verbatim>
               <br/>
          </f:verbatim>
          <%--c2:requiredFieldValidator componentToValidate="canditatesDateTo" errorMessage="#{globalResources.missingField}" display="dynamic"/--%>
          <c2:dateFormatValidator componentToValidate="canditatesDateTo" errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"/>
          <c2:compareDateValidator componentToValidate="canditatesDateTo" componentToCompare="canditatesDateFrom" display="dynamic"
                                   errorMessage="#{resourcesBundle.canditatesDateTo_after_canditatesDateTo}" operator="after" highlight="false"/>
     </t:panelGroup>
     <!-- Row3-->
     <t:outputLabel value="#{resourcesBundle.civilIdCandidate}" styleClass="lable01"/>
     <t:panelGroup>
          <t:inputText onkeyup="disableCharacters(this);" value="#{pageBeanName.candidatePersonsSearchDTO.civilId}" styleClass="textbox" forceId="true" maxlength="12" id="civilIdCandidate"/>
          <f:verbatim>
               <br/>
          </f:verbatim>
          <c2:regularExpressionValidator componentToValidate="civilIdCandidate" pattern="/^[0-9]{12}$/" display="dynamic" errorMessage="#{resourcesBundle.civil_no_not_less_than_12}"/>
     </t:panelGroup>
     <!-- Row4-->
     <t:outputLabel value="#{resourcesBundle.qualification}" styleClass="lable01"/>
     <t:panelGroup>
          <t:inputText forceId="true" id="qualName" value="#{pageBeanName.qualName}" styleClass="textboxmedium" readonly="true" disabled="true"/>
          <f:verbatim>&nbsp;&nbsp;</f:verbatim>
          <t:commandButton value="..." styleClass="cssbuttonSmall" type="button" onclick="changeVisibilityDiv(window.blocker,window.lookupAddDiv); setFocusOnlyOnElement('backButtonAddDiv');"/>
     </t:panelGroup>
     <!-- Row5-->
     <%--
     <t:outputLabel value="#{resourcesBundle.job}" styleClass="lable01"/>
     <t:panelGroup>
          <t:inputText forceId="true" id="jobName" value="#{pageBeanName.jobName}" styleClass="textboxmedium" readonly="true" disabled="true"/>
          <t:commandButton value="..." styleClass="cssbuttonSmall" type="button" 
                            onclick="changeVisibilityDiv(window.blocker,window.lookupEditDiv); setFocusOnlyOnElement('CancelButtonEdit');"/>
     </t:panelGroup>
     <!-- Row6-->
     <t:outputLabel value="#{resourcesBundle.candidates}" styleClass="lable01"/>
     <t:selectOneMenu id="candidates" styleClass="DropdownboxMedium" value="#{pageBeanName.selectedcandidate}" forceId="true">
          <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue=""/>
          <t:selectItems value="#{pageBeanName.candidates_list}" var="candidate" itemValue="#{candidate.code.key}" itemLabel="#{candidate.name}"/>
     </t:selectOneMenu>
     --%>
     <!-- Row7-->
  
    <%--
     <!-- Row8-->
     <t:outputLabel value="#{resourcesBundle.category_name}" styleClass="lable01"/>
     <t:selectOneMenu id="categoriesList" forceId="true" value="#{pageBeanName.selectedCategoryId}" styleClass="DropdownboxMedium">
          <f:selectItem itemLabel="#{resourcesBundle.Item_Select}" itemValue=""/>
          <t:selectItems value="#{pageBeanName.org_categories_list}" var="categories" itemValue="#{categories.code.key}" itemLabel="#{categories.name}"/>
          <a4j:support actionListener="#{pageBeanName.reflectMinistries}" reRender="MinistriesList" event="onchange"/>
     </t:selectOneMenu>
     <!-- Row9-->
     <t:outputLabel value="#{resourcesBundle.ministry_name}" styleClass="lable01"/>
     <t:selectOneMenu id="MinistriesList" forceId="true" value="#{pageBeanName.selectedMinistryryId}" styleClass="DropdownboxMedium"
                        disabled="#{(pageBeanName.selectedCategoryId == null || pageBeanName.selectedCategoryId == '')}">
          <f:selectItem itemLabel="#{resourcesBundle.Item_Select}" itemValue=""/>
          <t:selectItems value="#{pageBeanName.org_ministries_list}" var="ministries" itemValue="#{ministries.code.key}" itemLabel="#{ministries.name}"/>
     </t:selectOneMenu>
     --%>
     
     <!-- Row10-->
     <t:outputLabel value="#{resourcesBundle.bookNeedNumber}" styleClass="lable01"/>
     <t:panelGroup>
          <t:inputText value="#{pageBeanName.candidatePersonsSearchDTO.bookNo}" styleClass="textbox" forceId="true" id="bookNeedNumID"/>
          <f:verbatim>
               <br/>
          </f:verbatim>          
     </t:panelGroup>
     
</t:panelGrid>
<t:panelGrid columns="2" align="center" width="20%">
     <t:commandButton value="#{globalResources.SearchButton}" action="#{pageBeanName.advancedsearch}" onclick="x= validateSearchData('canditatesDateFrom,canditatesDateTo,civilIdCandidate,qualName,jobName,candidates,categoriesList,MinistriesList,bookNeedNumID','outPutMsg','#{globalResources.enter_at_least_one_field}'); y = validatemyForm(); return (x&&y);" styleClass="cssButtonSmall"/>
     <t:commandButton forceId="true" id="BackButtonManyToMany" value="#{globalResources.back}" action="#{pageBeanName.backAdvancedSearch}" styleClass="cssButtonSmall"
                        onblur="setFocusOnElement('canditatesDateFrom');"/>
</t:panelGrid>

<t:inputText style="width:0px; height:0px;"/>
</t:div>
</t:panelGroup>
<%-- --%>