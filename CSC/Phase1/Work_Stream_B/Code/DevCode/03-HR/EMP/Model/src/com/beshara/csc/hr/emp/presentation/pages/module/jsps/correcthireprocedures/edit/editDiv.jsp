<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>#lookupEditDiv.lookupEditDivClass { width: 650px !important; right: 18% !important; } }</htm:style>
<t:panelGroup id="divEditLookup">
    <t:panelGrid id="parentPanel" forceId="true" columns="4" border="0" rowClasses="row01,row02" width="100%"
                 cellpadding="3" cellspacing="0">
        <h:outputText value="#{resourcesBundle.civil_id}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText maxlength="12" disabled="true" forceId="true" id="civID" styleClass="textboxmedium"
                         tabindex="1" value="#{pageBeanName.civilId}"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.person_name}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText disabled="true" forceId="true" id="empName" styleClass="textboxmedium"
                         value="#{pageBeanName.civilName}" style="width: 230px;"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.procudre_type}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:inputText disabled="true" forceId="true" id="preceduretypeID" styleClass="textboxmedium"
                         value="#{pageBeanName.selectedPageDTO.hireProceduresDTO.name}"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.transfer_date}"/>
        <t:panelGroup>
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" forceId="true" id="sendDateId"
                             size="20" value="#{pageBeanName.selectedPageDTO.sendDate}" maxlength="200"
                             popupDateFormat="dd/MM/yyyy" styleClass="textbox" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}" converter="SqlDateConverter"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"></t:inputCalendar>
            <h:outputText value="  *  " styleClass="errMsg"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:requiredFieldValidator group="sendDateG" componentToValidate="sendDateId" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" highlight="false"/>
            <c2:dateFormatValidator componentToValidate="sendDateId" display="dynamic"
                                    errorMessage="#{resourcesBundle.DateWrongFormat}" highlight="false"
                                    group="sendDateG"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.result_date}"/>
        <t:panelGroup>
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="resultId" value="#{pageBeanName.selectedPageDTO.resultDate}" size="20"
                             maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                             converter="SqlDateConverter"></t:inputCalendar>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:dateFormatValidator componentToValidate="resultId" display="dynamic"
                                    errorMessage="#{resourcesBundle.DateWrongFormat}" highlight="false"
                                    group="sendDateG"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.procedure_result}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:selectOneMenu converter="javax.faces.Long" forceId="true" id="resultListID"
                             styleClass="DropdownboxMedium2" value="#{pageBeanName.selectedProcedureResult}">
                <t:selectItems value="#{pageBeanName.procedureResults}" itemLabel="#{results.name}"
                               itemValue="#{results.code.proresultCode}" var="results"/>
                <a4j:support event="onchange" action="#{pageBeanName.onChangeResult}" reRender="sharedPG"/>
            </t:selectOneMenu>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid id="sharedPG" forceId="true" columns="4" border="0" rowClasses="row01,row02" width="100%"
                 cellpadding="3" cellspacing="0"
                 style="#{!correctHireProceduresBean.showReasonPanel ? 'display : none;' : 'display: inline-table;'}">
        <h:outputText value="#{resourcesBundle.postpone_reason}" styleClass="divtext"
                      rendered="#{correctHireProceduresBean.showReasonPanel}" style="display: block; width: 41px;"/>
        <t:panelGroup rendered="#{correctHireProceduresBean.showReasonPanel}" style="display: block; width: 153px;">
            <t:selectOneMenu converter="javax.faces.Long" forceId="true" id="reasonDataID"
                             styleClass="DropdownboxMedium2" value="#{pageBeanName.selectedReasonData}">
                <t:selectItems value="#{pageBeanName.reasonsDataList}" itemLabel="#{results.name}"
                               itemValue="#{results.code.resdatSerial}" var="results"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.postpone_date}" rendered="#{correctHireProceduresBean.showReasonPanel}"
        style="width: 60px; display: block; margin-right: -54px;"/>
        <t:panelGroup rendered="#{correctHireProceduresBean.showReasonPanel}">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="postponedId" value="#{pageBeanName.selectedPageDTO.postponeDate}"
                             size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                             converter="SqlDateConverter"></t:inputCalendar>
            <h:outputText value="  *  " styleClass="errMsg"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:requiredFieldValidator group="postponedDateG" componentToValidate="postponedId" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" highlight="false"/>
            <c2:dateFormatValidator componentToValidate="postponedId" display="dynamic"
                                    errorMessage="#{resourcesBundle.DateWrongFormat}" highlight="false"
                                    group="postponedDateG"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<t:panelGrid columns="3" border="0" align="center">
    <%-- Start By css used to spreate button--%>
    <t:commandButton forceId="true" styleClass="cssButtonSmall" id="EditButton" value="#{globalResources.SaveButton}"
                     onclick="return validateComponentByGroup();" action="#{pageBeanName.edit}"/>
    <h:panelGroup>
        <t:commandButton forceId="true" id="backButtonEditDiv" onclick="backJsFunction(); return false;"
                         styleClass="cssButtonSmall" value="#{globalResources.back}"/>
        <a4j:jsFunction name="backJsFunction"
                        oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv);settingFoucsAtListPage(); "
                        reRender="divEditLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
    </h:panelGroup>
    <%-- End By css used to spreate button--%>
</t:panelGrid>
<script type="text/javascript">
  function validateComponentByGroup() {
      if (validatemyForm('sendDateG')) {
          if (document.getElementById('postponedId') != null) {
              return validatemyForm('postponedDateG')
          }
          return true;
      }
      else {
          return false
      };
  }
</script>