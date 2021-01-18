<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:saveState value="#{pageBeanName.selected_procedure_name}"/>
<t:saveState value="#{pageBeanName.selectedEmployees}"/>
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>

<t:div id="lookupEditDiv" forceId="true" style="visibility:visible;">
<t:panelGroup forceId="true" id="divEditLookup">

<t:panelGrid columns="2" rowClasses="row02,row01" cellpadding="0" columnClasses="colu1,colu2" cellspacing="0" width="100%">
 <!-- Row1-->
 <t:panelGroup>
  <t:outputLabel value="#{resourcesBundle.procedure_name}" styleClass="lable01"/>
  <h:outputText value="*" styleClass="mandatoryAsterisk"/>
 </t:panelGroup>
 <t:panelGroup>
  <t:inputText value="#{pageBeanName.selected_procedure_name}" readonly="true" styleClass="textboxlarge" forceId="true" id="procedure_name"/>
  <t:commandButton onblur="setFocusAtDivOrPage();" id="add_procedure_btn" forceId="true" styleClass="cssButtonSmall" value="..." type="button" onclick="changeVisibilityDiv(window.blocker,window.lookupAddDiv);settingFoucsAtDivAdd();"/>
  <f:verbatim>
   <br/>
  </f:verbatim>
  <c2:requiredFieldValidator componentToValidate="procedure_name" errorMessage="#{globalResources.missingField}" display="dynamic"/>
 </t:panelGroup>
 <!-- Row2-->
 <t:panelGroup>
  <t:outputLabel value="#{resourcesBundle.sending_date}" styleClass="lable01"/>
  <h:outputText value="*" styleClass="mandatoryAsterisk"/>
 </t:panelGroup>
 <t:panelGroup>
  <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" value="#{pageBeanName.employeeProceduresDTO.sendDate}" popupDateFormat="dd/MM/yyyy" forceId="true" id="sending_date"
                   onchange="markDirty();" size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}"
                   popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
   <f:converter converterId="SqlDateConverter"/>
  </t:inputCalendar>
  <f:verbatim>
   <br/>
  </f:verbatim>
  <c2:requiredFieldValidator componentToValidate="sending_date" errorMessage="#{globalResources.missingField}" display="dynamic"/>
  <c2:dateFormatValidator componentToValidate="sending_date" errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"/>
 </t:panelGroup>
 <!-- Row3-->
 <t:panelGroup>
  <t:outputLabel value="#{resourcesBundle.procedure_des}" styleClass="lable01"/>
 </t:panelGroup>
 <t:panelGroup>
  <t:inputTextarea value="#{pageBeanName.employeeProceduresDTO.procDesc}" styleClass="textarealarge" forceId="true" id="procedure_des"/>
 </t:panelGroup>
</t:panelGrid>
<t:panelGrid columns="2" align="center" width="20%">
 <t:commandButton forceId="true" id="SaveButtonEdit" value="#{resourcesBundle.sending_button}" action="#{pageBeanName.sendingprocedure}" onclick="return validatemyForm();" styleClass="cssButtonSmall"/>
 <t:commandButton forceId="true" id="CancelButtonEdit" onblur="setFocusAtMyFirstElement();" value="#{resourcesBundle.exit_button}" action="list_send_procedure" styleClass="cssButtonSmall"/>
</t:panelGrid>
</t:panelGroup>
</t:div>
<f:verbatim>
    <script type="text/javascript">
        setFocusAtMyFirstElement();
        function setFocusAtMyFirstElement(){
            if(document.getElementById('add_procedure_btn') != null){
                document.getElementById('add_procedure_btn').focus();
            }
        }
        
        function setFocusAtDivOrPage(){
            if(document.getElementById('add_first_inputTxt') != null){
                settingFoucsAtDivAdd();
            }else if(document.getElementById('sending_date') != null){
                document.getElementById('sending_date').focus();
            }
        }
    </script>
</f:verbatim>