<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<script type="text/javascript">
  function clearMsgField(componentId) {

      var component = document.getElementById(componentId);
      if (component != null) {
          component.innerHTML = '';

      }
  }

  function clearValue(componentId) {

      var component = document.getElementById(componentId);
      if (component != null) {
          component.value = '';
      }
  }

  function doOnBlur() {

      if (isVisibleComponent("lovEmp")) {
          settingFoucsAtEmpLovDiv();
      }
      else {
          document.getElementById('civilID').focus();
      }
  }

  function clickButtonOnEnter(event, buttonID) {
      if (event.keyCode == 13) {
          document.getElementById(buttonID).click();
      }
      else {
          return;
      }
  }
</script>
<t:panelGroup id="searchDiv" forceId="true">
    <t:panelGrid columns="4" width="100%" forceId="true" id="mainDataEmpPanel" rowClasses="row01,row02" cellpadding="3"
                 cellspacing="0">
        <h:outputText value="#{resourcesBundle.civil_id}" styleClass="lable01"/>
        <t:panelGroup forceId="true" id="empCivilIdPnlgrp">
            <t:inputText readonly="#{pageBeanName.validCivilId}" maxlength="12" disabled="#{pageBeanName.validCivilId}"
                         forceId="true" id="civilID" styleClass="textboxmedium" tabindex="1"
                         onkeydown="onKeyDownFirstElement(event,'collectInfoBtn','listResult')"
                         onkeypress="clickButtonOnEnter(event,'collectInfoBtn');" value="#{pageBeanName.civilId}"
                         onkeyup="disableCharacters(this);"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:panelGroup rendered="#{! pageBeanName.validCivilId}">
                <t:commandButton id="collectInfoBtn" forceId="true" tabindex="2"
                                 onclick="clearMsgField('errInvalidCivilID');clearMsgField('errCandidateCode');x= validatemyForm(); if (x){block();}; return x;"
                                 value="#{globalResources.Available}" styleClass="cssButtonSmallest"
                                 action="#{pageBeanName.checkAvailable}"/>
                <f:verbatim>&nbsp;</f:verbatim>
                <a4j:commandButton type="button" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"
                                   tabindex="3"
                                   oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();"
                                   action="#{pageBeanName.showSearchForEmployeeDiv}" reRender="lovEmp"/>
            </t:panelGroup>
            <t:commandButton value="#{globalResources.reSetButton}" rendered="#{pageBeanName.validCivilId }"
                             tabindex="4" styleClass="cssButtonSmall" actionListener="#{pageBeanName.reSetData}"
                             onclick="clearValue('vacTypeCode');"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:outputText forceId="true" id="errInvalidCivilID" value="#{globalResources.civiliderror}"
                          rendered="#{!pageBeanName.civilExist && pageBeanName.civilId != null}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="errCandidateCode" value="#{resourcesBundle.no_data_found_for_emp}"
                          rendered="#{pageBeanName.noCandidateFound}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="errInvalidInMIn" value="#{resourcesBundle.vac_civilNotvalid}"
                          rendered="#{pageBeanName.empInMin}" styleClass="errMsg"/>
            <%-- <c2:requiredFieldValidator componentToValidate="civilID" display="dynamic"
                 errorMessage="#{globalResources.missingField}" highlight="false"/>--%>
            <%-- <c2:regularExpressionValidator componentToValidate="civilID" pattern="/^[0-9]{12}$/"
                 errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false" display="dynamic"/>--%>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.person_name}" styleClass="lable01"/>
        <t:inputText disabled="true" forceId="true" id="empNameIntxt" styleClass="textboxmedium" readonly="true"
                     value="#{pageBeanName.civilName}" style="width: 255px;"/>
        <h:outputText value="#{resourcesBundle.MinistryFileNo}"/>
        <t:inputText disabled="true" forceId="true" id="employees_geha_fileNo" styleClass="textboxmedium"
                     value="#{pageBeanName.ministryFileNo}"/>
        <h:outputText value="#{resourcesBundle.Hire_date}"/>
        <t:inputText disabled="true" forceId="true" id="employees_hireDate" styleClass="textboxmedium"
                     value="#{pageBeanName.hireDate}">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputText>
    </t:panelGrid>
    <t:panelGrid align="center" forceId="true" id="searchBtnPnlgrd">
        <t:commandButton id="listResult" onclick="x= validatemyForm(); if (x){block();} return x;" tabindex="10"
                         disabled="#{! pageBeanName.validCivilId}" value="#{resourcesBundle.Hire_procedures_view}"
                         styleClass="cssButtonSmall" action="#{pageBeanName.showDataTable}"/>
    </t:panelGrid>
</t:panelGroup>
