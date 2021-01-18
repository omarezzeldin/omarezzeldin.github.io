<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<script type="text/javascript">
  function setFocusOnCalendar() {
      var component = document.getElementById('go_work_date');
      if (component != null) {
          component.focus();
      }
  }
  setFocusOnCalendar();
</script>
<t:panelGroup styleClass="divMainContent1WithOutWizardBar">
    <t:panelGrid rowClasses="row02,row01" cellpadding="0" columnClasses="colu1,colu2" cellspacing="0" width="100%"
                 forceId="true" id="employeeMainDataPanel" columns="4">
        
        <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <f:verbatim>
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td styleClass="group_title">
                            <t:outputLabel value="#{resourcesBundle.maindata}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </f:verbatim>
        </t:panelGroup>
        <%-- row1---%>
        <t:outputText value="#{resourcesBundle.civilid}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.code.civilId}" styleClass="textbox"
                     disabled="true"/>
        <t:outputText value="#{resourcesBundle.candidate_name_label}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}" styleClass="textboxmedium"
                     disabled="true"/>
        <%-- Row 2--%>
        <t:outputText value="#{resourcesBundle.gender}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.genderTypesDTO.name}" styleClass="textbox"
                     disabled="true"/>
        <t:outputText value="#{resourcesBundle.status}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.pageDTO.citizensResidentsDTO.maritalStatusDTO.name}" styleClass="textbox"
                     disabled="true"/>
        <%-- Row--%>
        <h:outputText value="#{resourcesBundle.nationalityType}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputText forceId="true" id="countriesDTO_status" styleClass="textbox"
                         value="#{pageBeanName.pageDTO.citizensResidentsDTO.countriesDTO.name}" disabled="true"/>
        </t:panelGroup>
        
        
        <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <f:verbatim>
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td styleClass="group_title">
                            <t:outputLabel value="#{resourcesBundle.go_work}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </f:verbatim>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.go_work_data}" styleClass="lable01"/>
        <t:panelGroup >
            <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" value="#{pageBeanName.pageDTO.startWorkingDate}" id="go_work_date"
                             onchange="return validateInputCalenderFormate('go_work_date','null','null')" size="20"
                             maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                             title="#{globalResources.inputCalendarHelpText}">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c2:requiredFieldValidator componentToValidate="go_work_date" errorMessage="#{globalResources.missingField}"
                                       highlight="false" display="dynamic"/>
            <%-- c2:dateFormatValidator componentToValidate="go_work_date" errorMessage="#{resourcesBundle.dateFormat}"
                 highlight="false" display="dynamic" /--%>
            <c2:compareDateValidator componentToValidate="hireDateTxt" componentToCompare="go_work_date"
                                     operator="before" display="dynamic"
                                     errorMessage="#{resourcesBundle.empExecuteDatebeforeStartDateErrMsg}"
                                     highlight="false"/>
            <c2:compareDateValidator componentToValidate="nextYearDate" componentToCompare="go_work_date"
                                     operator="after" display="dynamic"
                                     errorMessage="#{resourcesBundle.empExecuteDatebeforeNextYearDateErrMsg}"
                                     highlight="false"/>
            <t:inputHidden value="#{pageBeanName.nextYearValue}" forceId="true" id="nextYearDate"/>
        </t:panelGroup>
        
        
        
         <h:outputText value="#{resourcesBundle.ministry_fileNo}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="filnumPnlGp">
        <t:inputText forceId="true" maxlength="20" id="emp_minFileNo" tabindex="9" styleClass="textboxmedium"
                     value="#{pageBeanName.pageDTO.ministryFileNo}" onkeypress="goChangeFilNumber(event);" 
                     onchange="changeFilNumberOnBlur();"></t:inputText>
                      <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                       <c2:requiredFieldValidator componentToValidate="emp_minFileNo" errorMessage="#{globalResources.missingField}" 
                                       highlight="false" display="dynamic"/>   
                  
        <a4j:jsFunction name="checkAboutFilNum" action="#{pageBeanName.checkAboutFilNum}" reRender="fileNomsgPnlGrp,emp_minFileNo"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        
         
        <t:panelGroup forceId="true" id="fileNomsgPnlGrp">
          
          
            <t:outputText forceId="true" id="renderEmpFilNumRedundant" value="#{resourcesBundle.empfilNumRedundantinMin}"
                          rendered="#{pageBeanName.renderEmpFilNumRedundant}" styleClass="errMsg" style="white-space: nowrap;"/>
            <t:outputText forceId="true" id="renderCandFilNumRedundant" value="#{resourcesBundle.candFilNumRedundant}"
                          rendered="#{pageBeanName.renderCandFilNumRedundant}" styleClass="errMsg"/>
        </t:panelGroup>
    </t:panelGroup>
        
        
        
        <!-- البيانات الوظيفيه -->
        <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <f:verbatim>
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td styleClass="group_title">
                            <t:outputLabel value="#{resourcesBundle.job_data}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </f:verbatim>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.hiretype}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.pageDTO.hireTypesDTO.name}" styleClass="textboxmedium" disabled="true"/>
        <%--<t:outputText value="#{resourcesBundle.ministry_fileNo}" styleClass="lable01"/>--%>
        <%--<t:inputText value="#{pageBeanName.pageDTO.ministryFileNo}" styleClass="textboxsmall2" disabled="true"/>--%>
        <%-- Row--%>
        <t:outputText value="#{resourcesBundle.hireDate}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.pageDTO.hireDate}" styleClass="textbox" disabled="true" forceId="true"
                     id="hireDateTxt">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputText>
        <t:outputText value="#{resourcesBundle.bouns_date}" styleClass="lable01" rendered="#{pageBeanName.enableDateOfNextRaise}"/>
        <t:inputText value="#{pageBeanName.pageDTO.dateOfNextRaise}" styleClass="textboxsmall2" disabled="true" rendered="#{pageBeanName.enableDateOfNextRaise}">
            <f:converter converterId="SqlDateConverter" />
        </t:inputText>
        <t:panelGroup colspan="2" rendered="#{!pageBeanName.enableDateOfNextRaise}"/>
        <t:outputText value="#{resourcesBundle.spicifeid_wrk_center}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText value="#{pageBeanName.pageDTO.workCentersDTO.name}" styleClass="textboxlarge" disabled="true"/>
        </t:panelGroup>
        <%-- Row--%>
        <t:outputText value="#{resourcesBundle.budget}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.pageDTO.bgtProgramsDTO.name}" styleClass="textboxmedium" disabled="true"/>
        <t:outputText value="#{resourcesBundle.budgetType}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
        <t:inputText value="#{pageBeanName.pageDTO.bgtTypesDTO.name}" styleClass="textboxlarge" disabled="true"/>
        </t:panelGroup>
        
        
        <h:outputText value="#{resourcesBundle.contract_type}" styleClass="lable01" rendered="#{!pageBeanName.centralEmphireType}"/>
        <t:panelGroup forceId="true" id="contractTypePanel" colspan="4" rendered="#{!pageBeanName.centralEmphireType}">
            <t:selectOneMenu forceId="true" id="employees_contractType" styleClass="textboxmedium" 
                             value="#{pageBeanName.contractType}" disabled="true">
                <f:selectItem itemValue="#{pageBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.select}"/>
                <f:selectItem itemValue="6" itemLabel="#{resourcesBundle.contracts}"/>
                <f:selectItem itemValue="7" itemLabel="#{resourcesBundle.Estana}"/>
                <f:selectItem itemValue="0" itemLabel="#{resourcesBundle.other}"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <%-- Row 1--%>
        <t:outputText value="#{resourcesBundle.cader}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.salaryElementDTO.salElementGuidesDTO.parentObject.parentObject.parentObject.name}"
                     styleClass="textboxmedium" disabled="true"/>
        <t:outputText value="#{resourcesBundle.group}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.salaryElementDTO.salElementGuidesDTO.parentObject.parentObject.name}"
                     styleClass="textboxmedium" disabled="true"/>
        <%-- Row 2--%>
        <t:outputText value="#{resourcesBundle.degree}" styleClass="lable01"/>
            <t:inputText value="#{pageBeanName.firstDegreeName}" styleClass="textboxmedium"
                         disabled="true"/>
        <t:outputText value="#{resourcesBundle.raisesCount}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.salaryElementDTO.salElementGuidesDTO.countGuide}" styleClass="textboxmedium"
                         disabled="true"/>
        <t:outputText value="#{resourcesBundle.job_name}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.pageDTO.jobsDTO.name}" styleClass="textboxlarge" disabled="true"/>
    </t:panelGrid>
</t:panelGroup>
