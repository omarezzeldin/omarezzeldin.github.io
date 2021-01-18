<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:div id="lookupEditDiv" forceId="true" style="visibility:visible;">
    <t:panelGroup forceId="true" id="divEditLookup">
        <t:panelGrid align="center">
            <t:outputText forceId="true" id="successEditLockup" value="#{globalResources.SuccessAdds}"
                          styleClass="sucessMsg" rendered="#{pageBeanName.success}"/>
            <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg"
                          rendered="#{pageBeanName.showErrorMsg}"/>
        </t:panelGrid>
        <t:panelGrid cellpadding="3" cellspacing="0" columns="2" width="100%" forceId="true" id="savePanel"
                     rowClasses="row01,row02">
            <h:outputText value="#{globalResources.Code}"/>
            <t:inputText disabled="true" forceId="true" id="CodeAdd" value="#{pageBeanName.selectedPageDTO.code.key}"
                         styleClass="textboxsmall"/>
            <h:outputText value="#{resourcesBundle.proceduresDesc}"/>
            <t:panelGroup>
                <t:inputText maxlength="#{pageBeanName.nameMaxLength}" forceId="true" id="proceduresDescAdd"
                             tabindex="1" onkeydown="onKeyDownFirstElement(event,'minCodeId','CancelButtonEdit')"
                             value="#{pageBeanName.selectedPageDTO.name}" styleClass="textboxlarge2"
                             onchange="trimBorders(proceduresDescAdd);"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>&nbsp;</f:verbatim>
                <c2:requiredFieldValidator componentToValidate="proceduresDescAdd"
                                           errorMessage="#{globalResources.missingField}" display="dynamic"/>
            </t:panelGroup>
            <%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~--%>
            <h:outputText value="#{resourcesBundle.resbonsible_ministry}"/>
            <t:panelGroup>
                <t:inputText id="minCodeId" tabindex="2" value="#{pageBeanName.ministryCode}" forceId="true"
                             onkeypress="enabelIntegerOnlyWithZero(this);filterById(event);" styleClass="textbox"
                             style="margin-left: 7px;"/>
                <a4j:jsFunction name="getMinByCode" action="#{hireProceduresEditBean.getMinById}"
                                oncomplete="showvalidate_msg();" id="functionGetMin"
                                reRender="ministryAdd,minCodeErrMsg"/>
                <t:inputText forceId="true" id="ministryAdd" value="#{pageBeanName.selectedPageDTO.ministriesDTO.name}"
                             disabled="true" styleClass="textboxlarge"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>&nbsp;</f:verbatim>
                <a4j:commandButton value="..." styleClass="cssButtonSmall" type="button"
                                   action="#{minHelperBeanName.openMinistriesDiv}"
                                   reRender="ministryDiv ,minCodeId,lov_dataT_data_panel,lovLinkStatusPanel,javaScriptCallerOutputText"
                                   oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divSearch);settingFoucsAtDivSearch();"
                                   id="searchMinistryBtn" tabindex="3"/>
                <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                <c2:requiredFieldValidator componentToValidate="ministryAdd"
                                           errorMessage="#{globalResources.missingField}" display="dynamic"/>
            
            <t:outputText value="#{resourcesBundle.inavlid_code}" id="testMsg" forceId="true" style="display:none;"
                          styleClass="mandatoryAsterisk"/>
                          </t:panelGroup>
            <%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~---%>
            <%-- <h:outputText value="#{resourcesBundle.gender}" /> <t:panelGroup> <t:selectOneMenu id="genderListAdd"
                 value="#{pageBeanName.selectedGender}" styleClass="DropdownboxMedium2" > <f:selectItem
                 itemLabel="#{resourcesBundle.select}" itemValue="" /> <t:selectItems var="genderList"
                 itemLabel="#{genderList.name}" itemValue="#{genderList.code.key}" value="#{pageBeanName.genderList}" />
                 </t:selectOneMenu> <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                 <f:verbatim>&nbsp;</f:verbatim> <c2:requiredFieldValidator componentToValidate="genderListAdd"
                 errorMessage="#{globalResources.missingField}" display="dynamic"/> </t:panelGroup> <h:outputText
                 value="#{resourcesBundle.nationalityType}" /> <t:panelGroup> <t:selectOneMenu forceId="true"
                 id="nationalityTypeAdd" value="#{pageBeanName.selectedNationalityType}" styleClass="DropdownboxMedium2"
                 > <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue="" /> <f:selectItem
                 itemLabel="#{resourcesBundle.NATIONALITY_KUWAITI}" itemValue="#{pageBeanName.nationality_Kuwaiti}" />
                 <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_NON_KUWAITI}"
                 itemValue="#{pageBeanName.nationality_NonKuwaiti}" /> <f:selectItem
                 itemLabel="#{resourcesBundle.NATIONALITY_NON_SPECIFIED}"
                 itemValue="#{pageBeanName.nationality_NonSpecified}"/> </t:selectOneMenu> <h:outputText value="*"
                 styleClass="mandatoryAsterisk"/> <f:verbatim>&nbsp;</f:verbatim> <c2:requiredFieldValidator
                 componentToValidate="nationalityTypeAdd" errorMessage="#{globalResources.missingField}"
                 display="dynamic"/> </t:panelGroup> %> <%--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~---%>
            <h:outputText value="#{resourcesBundle.active_status}"/>
            <t:selectBooleanCheckbox forceId="true" id="viewStatusId" value="#{pageBeanName.viewStatus}"/>
        </t:panelGrid>
        <t:panelGrid columns="2" border="0" align="center">
            <t:commandButton forceId="true" styleClass="cssButtonSmall" id="SaveButtonEdit" tabindex="4"
                             onclick="return validatemyForm();" value="#{globalResources.SaveButton}"
                             action="#{pageBeanName.saveHireProcedure}"/>
            <t:commandButton forceId="true" action="list" styleClass="cssButtonSmall" id="CancelButtonEdit" tabindex="5"
                             onkeydown="onKeyDownLastElement(event,'SaveButtonEdit','proceduresDescAdd')"
                             value="#{globalResources.back}"/>
        </t:panelGrid>
    </t:panelGroup>
</t:div>
<f:verbatim>
    <script type="text/javascript">
      setTimeout("setFocusAtMyFirstElement()", 700);

      function setFocusAtMyFirstElement() {
          if ((isVisibleComponent('divSearch') == false) && document.getElementById('proceduresDescAdd') != null) {
              document.getElementById('proceduresDescAdd').focus();
              document.getElementById('proceduresDescAdd').focus();
          }
      }
    </script>
</f:verbatim>
