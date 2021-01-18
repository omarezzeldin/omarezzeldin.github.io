<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
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
          document.getElementById('civilID1').focus();
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

  function validateAddComponentByGroup() {
      if (validatemyForm('sendDateAddG')) {
          if (document.getElementById('addPostponedId') != null) {
              return validatemyForm('addPostponedDateG')
          }
          return true;
      }
      else {
          return false
      };
  }
</script>
<t:panelGroup id="divAddLookup">
    <t:panelGrid id="addParentPanel" columns="4" border="0" rowClasses="row01,row02" width="100%" cellpadding="3"
                 cellspacing="0">
        <h:outputText value="#{resourcesBundle.civil_id}" styleClass="lable01"/>
        <t:panelGroup forceId="true" id="empCivilIdPnlgrp1">
            <t:inputText readonly="#{pageBeanName.validCivilId}" maxlength="12" disabled="#{pageBeanName.validCivilId}"
                         forceId="true" id="civilID1" styleClass="textboxmedium" tabindex="1"
                         value="#{pageBeanName.civilId}"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.person_name}" styleClass="lable01"/>
        <t:inputText disabled="true" forceId="true" id="empNameIntxt1" styleClass="textboxmedium" readonly="true"
                     value="#{pageBeanName.civilName}" style="width: 230px;"/>
        <h:outputText value="#{resourcesBundle.procudre_type}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:selectOneMenu converter="javax.faces.Long" disabled="#{!pageBeanName.validCivilId}" forceId="true"
                             id="proceduresList" styleClass="DropdownboxMedium2"
                             value="#{pageBeanName.selectedProcedure}">
                <t:selectItems value="#{pageBeanName.hireProceduresList}" itemLabel="#{procedures.name}"
                               itemValue="#{procedures.code.hirprocedureCode}" var="procedures"/>
                <%-- <a4j:support actionListener="#{pageBeanName.getGroupsByCader}" event="onchange"
                     reRender="groupMenu, degreeMenu"/>--%>
            </t:selectOneMenu>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.transfer_date}"/>
        <t:panelGroup>
            <t:panelGroup colspan="3">
                <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" id="ssconvertDateId" value="#{pageBeanName.convertDate}" size="20"
                                 maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                                 renderAsPopup="true" align="#{globalResources.align}"
                                 popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <h:outputText value="  *  " styleClass="errMsg"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:requiredFieldValidator group="sendDateAddG" componentToValidate="ssconvertDateId" display="dynamic"
                                           errorMessage="#{globalResources.missingField}" highlight="false"/>
                <c2:dateFormatValidator componentToValidate="ssconvertDateId" display="dynamic"
                                        errorMessage="#{resourcesBundle.DateWrongFormat}" highlight="false"
                                        group="sendDateAddG"/>
            </t:panelGroup>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.result_date}"/>
        <t:panelGroup>
            <t:panelGroup>
                <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" id="resultDateId" value="#{pageBeanName.resultDate}" size="20"
                                 maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                                 renderAsPopup="true" align="#{globalResources.align}"
                                 popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:dateFormatValidator componentToValidate="resultDateId" display="dynamic"
                                        errorMessage="#{resourcesBundle.DateWrongFormat}" highlight="false"
                                        group="sendDateAddG"/>
            </t:panelGroup>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.procedure_result}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:selectOneMenu converter="javax.faces.Long" forceId="true" id="addResultListID"
                             styleClass="DropdownboxMedium2" value="#{pageBeanName.selectedResult}">
                <t:selectItems value="#{pageBeanName.procedureResults}" itemLabel="#{results.name}"
                               itemValue="#{results.code.proresultCode}" var="results"/>
                <a4j:support event="onchange" action="#{pageBeanName.onChangeAddResult}" reRender="reasonPG"/>
            </t:selectOneMenu>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid id="reasonPG" columns="4" border="0" rowClasses="row01,row02" width="100%" cellpadding="3"
                 cellspacing="0" 
                 style="#{!correctHireProceduresBean.showReasonPanel ? 'display : none;' : 'display: inline-table;'}">
        <h:outputText value="#{resourcesBundle.postpone_reason}" styleClass="divtext"
                      rendered="#{correctHireProceduresBean.showReasonPanel}" style="display: block; width: 41px;"/>
        <t:panelGroup rendered="#{correctHireProceduresBean.showReasonPanel}" style="display: block; width: 153px;">
            <t:selectOneMenu converter="javax.faces.Long" forceId="true" id="addReasonDataID" 
                             styleClass="DropdownboxMedium2" value="#{pageBeanName.selectedAddReasonData}">
                <%-- <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>--%>
                <t:selectItems value="#{pageBeanName.reasonsDataList}" itemLabel="#{Reasons.name}"
                               itemValue="#{Reasons.code.resdatSerial}" var="Reasons"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.postpone_date}" rendered="#{correctHireProceduresBean.showReasonPanel}"
        style="width: 60px; display: block; margin-right: -54px;"/>
        <t:panelGroup rendered="#{correctHireProceduresBean.showReasonPanel}">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="addPostponedId" value="#{pageBeanName.postponeDate}" size="20"
                             maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                             converter="SqlDateConverter"></t:inputCalendar>
            <h:outputText value="  *  " styleClass="errMsg"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:requiredFieldValidator group="addPostponedDateG" componentToValidate="addPostponedId" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" highlight="false"/>
            <c2:dateFormatValidator componentToValidate="addPostponedId" display="dynamic"
                                    errorMessage="#{resourcesBundle.DateWrongFormat}" highlight="false"
                                    group="addPostponedDateG"/>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid columns="3" border="0" align="center">
        <%-- Start By css used to spreate button--%>
        <t:commandButton forceId="true" styleClass="cssButtonSmall" id="SaveButton"
                         value="#{globalResources.SaveButton}" action="#{pageBeanName.performAdd}"
                         onclick="return validateAddComponentByGroup();"/>
        <h:panelGroup>
            <t:commandButton forceId="true" id="backButtonAddDiv" onclick="backJsFunction(); return false;"
                             styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.back}"
                            oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'','myForm:error','add');settingFoucsAtListPage(); "
                            reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
        </h:panelGroup>
        <%-- End By css used to spreate button--%>
    </t:panelGrid>
</t:panelGroup>