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
                            <t:outputLabel value="#{resourcesBundle.hireDate}" styleClass="lable01"/>
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
        <h:outputText value="#{resourcesBundle.hireDate}" styleClass="lable01"/>
        <t:panelGroup forceId="true" id="hireDatePanlGroup">
            <t:panelGroup forceId="true" id="hireDateCalendar">
                <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" value="#{pageBeanName.pageDTO.hireDate}" id="employees_hireDate1"
                                 size="20" maxlength="200" styleClass="textboxmedium" tabindex="10"
                                 currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 align="#{globalResources.align}" onkeypress="checkaboutHireDateValidation(event);"
                                 popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                                 title="#{globalResources.inputCalendarHelpText}"
                                 onchange="checkaboutHireDateValidationOnBlur();">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
            </t:panelGroup>
             <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <a4j:jsFunction action="#{pageBeanName.checkaboutHireDate}" name="calculateNextDateOfRaise"
                            reRender="renderErrorHireDatePnlGrp,invaldNextYear,bouns_datepnlGp,pnlhiredateren,pnlhiredateren_input"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:dateFormatValidator componentToValidate="employees_hireDate1"
                                    errorMessage="#{resourcesBundle.dateFormat}" highlight="false" display="dynamic"
                                    group="87"/>
            <c2:requiredFieldValidator componentToValidate="employees_hireDate1" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" highlight="false" group="87"/>
            <t:panelGroup forceId="true" id="renderErrorHireDatePnlGrp">
                <t:outputText forceId="true" id="invaldNextYear"
                              value="#{resourcesBundle.dateShouldBetween} #{pageBeanName.previousYear} #{resourcesBundle.until} #{pageBeanName.nextYear}"
                              rendered="#{pageBeanName.invalidNextYear}" styleClass="errMsg"/>
            </t:panelGroup>
        </t:panelGroup>
        <t:panelGroup forceId="true" Id="pnlhiredateren">
            <h:outputText value="#{resourcesBundle.bouns_date}" styleClass="lable01"
                          rendered="#{pageBeanName.kuwaitCitizen}"/>
        </t:panelGroup>
        <t:panelGroup forceId="true" Id="pnlhiredateren_input">
            <t:panelGroup forceId="true" id="bouns_datepnlGp" rendered="#{pageBeanName.kuwaitCitizen}">
                <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" value="#{pageBeanName.pageDTO.dateOfNextRaise}" id="bouns_dateid"
                                 size="20" maxlength="200" styleClass="textboxmedium"
                                 currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 align="#{globalResources.align}" disabled="true"
                                 popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                                 title="#{globalResources.inputCalendarHelpText}">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
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
        <t:outputText value="#{resourcesBundle.spicifeid_wrk_center}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText value="#{pageBeanName.pageDTO.workCentersDTO.name}" styleClass="textboxmedium"
                         disabled="true"/>
        </t:panelGroup>
        <%-- Row--%>
        <t:outputText value="#{resourcesBundle.budget}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.pageDTO.bgtProgramsDTO.name}" styleClass="textboxmedium" disabled="true"/>
        <t:outputText value="#{resourcesBundle.budgetType}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText value="#{pageBeanName.pageDTO.bgtTypesDTO.name}" styleClass="textboxmedium" disabled="true"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.cader}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.salaryElementDTO.salElementGuidesDTO.parentObject.parentObject.parentObject.name}"
                     styleClass="textboxmedium" disabled="true"/>
        <t:outputText value="#{resourcesBundle.group}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.salaryElementDTO.salElementGuidesDTO.parentObject.parentObject.name}"
                     styleClass="textboxmedium" disabled="true"/>
        <%-- Row 2--%>
        <t:outputText value="#{resourcesBundle.degree}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.firstDegreeName}" styleClass="textboxmedium" disabled="true"/>
        <t:outputText value="#{resourcesBundle.raisesCount}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.salaryElementDTO.salElementGuidesDTO.countGuide}" styleClass="textboxmedium"
                     disabled="true"/>
        <t:outputText value="#{resourcesBundle.job_name}" styleClass="lable01"/>
        <t:inputText value="#{pageBeanName.pageDTO.jobsDTO.name}" styleClass="textboxlarge" disabled="true"/>
    </t:panelGrid>
</t:panelGroup>
<t:panelGrid columns="3" border="0" align="center">
    <t:commandButton forceId="true" id="preExValidationId" styleClass="cssButtonSmall"
                     value="#{resourcesBundle.executeBtn}" action="#{pageBeanName.excuteHire}"
                     onclick="return validatemyForm('87');"/>
    <t:commandButton id="BackButtonManyToMany" forceId="true" action="#{pageBeanName.goBack}"
                     styleClass="cssButtonSmall" value="#{globalResources.back}"/>
</t:panelGrid>