<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<htm:style>#integrationDiv2{ top: -8px !important; width: 75% !important; }</htm:style>

<t:panelGroup forceId="true" id="divAddLookup">
    <t:saveState value="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"
                  rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg"
                  rendered="#{pageBeanName.showErrorMsg}"/>
    <htm:br rendered="#{pageBeanName.success || pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg"/>
    <%-- show parameters --%>
    <t:panelGrid columns="4" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01">
        <h:outputText value="#{resourcesBundle.civil_id}" styleClass="divtext"/>
        <t:panelGroup>
            <t:inputText maxlength="12" forceId="true" id="civilIdForShow" value="#{pageBeanName.civilIdForShow}"
                         disabled="true" styleClass="textbox"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.employee_name}" styleClass="divtext"/>
        <t:panelGroup>
            <t:inputText styleClass="textboxlarge" forceId="true" id="nameForShow" disabled="true"
                         value="#{pageBeanName.nameForShow}"/>
        </t:panelGroup>
        <t:panelGroup>
            <h:outputText value="#{resourcesBundle.months_for_dma2em}" styleClass="divtext"/>
            <t:selectOneMenu forceId="true" id="monthID" styleClass="textboxsmall" style="width:87px;"
                             value="#{pageBeanName.month}" disabled="true">
                <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}"
                               var="months"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <t:panelGroup>
            <h:outputText value="#{resourcesBundle.years_for_dma2em}" styleClass="divtext"/>
            <t:selectOneMenu forceId="true" id="yearID" styleClass="textboxsmall" style="width:105px;"
                             value="#{pageBeanName.year}" disabled="true" onblur="setFousAtNextAfterComboItem();">
                <t:selectItems value="#{pageBeanName.years}" itemLabel="#{years.code.key}" itemValue="#{years.code.key}"
                               var="years"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.workCenter}" styleClass="divtext"/>
        <t:panelGroup>
            <t:inputText styleClass="textboxlarge" forceId="true" id="wrkCenterName" disabled="true"
                         value="#{pageBeanName.wrkCenterName}"/>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGroup id="panalScrolId"  forceId="true" style="float: left;overflow-y: auto;height: 304px;width: 645px;">
    <t:panelGrid columns="2" border="0" width="100%" cellpadding="1" cellspacing="0" rowClasses="row02,row01">
        <%-- <t:outputText value="#{resourcesBundle.emp_absence1}" styleClass="lable01"/>--%>
        <t:panelGroup colspan="2" forceId="true" id="headerEquationForms" >
            <t:panelGroup colspan="2" style="background-color:#FFFFFF">
                <htm:table width="100%">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9"
                                     height="9"/>
                        </htm:td>
                        <htm:td styleClass="group_title">
                            &nbsp; 
                            <t:outputLabel value="#{resourcesBundle.absence}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%"
                                     height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
        </t:panelGroup>
        <%-- absence--%>
        <t:panelGroup>
            <t:inputText forceId="true" id="absence1Id" value="#{pageBeanName.commentsDto.absence1}"
                         styleClass="textboxlarge" style="width: 250px;" disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <%-- <t:outputText value="#{resourcesBundle.emp_absence2}" styleClass="lable01"/>--%>
        <t:panelGroup>
            <t:inputText forceId="true" id="absence2Id" value="#{pageBeanName.commentsDto.absence2}"
                         styleClass="textboxlarge" style="width: 250px;" disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <%-- <t:outputText value="#{resourcesBundle.emp_absence3}" styleClass="lable01"/>--%>
        <t:panelGroup>
            <t:inputText forceId="true" id="absence3Id" value="#{pageBeanName.commentsDto.absence3}"
                         styleClass="textboxlarge" style="width: 250px;" disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <%-- <t:outputText value="#{resourcesBundle.emp_absence4}" styleClass="lable01"/>--%>
        <t:panelGroup>
            <t:inputText forceId="true" id="absence4Id" value="#{pageBeanName.commentsDto.absence4}"
                         styleClass="textboxlarge" style="width: 250px;" disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <%-- <t:outputText value="#{resourcesBundle.emp_absence4}" styleClass="lable01"/>--%>
        <t:panelGroup colspan="2">
            <t:inputText forceId="true" id="absence5Id" value="#{pageBeanName.commentsDto.absence4}"
                         styleClass="textboxlarge" style="width: 250px;" disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <%-- sick--%>
        <t:panelGroup forceId="true" id="headerEquationForms1" colspan="2">
            <t:panelGroup style="background-color:#FFFFFF">
                <htm:table width="100%">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9"
                                     height="9"/>
                        </htm:td>
                        <htm:td styleClass="group_title">
                            &nbsp; 
                            <t:outputLabel value="#{resourcesBundle.sick}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td height="1">
                            <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%"
                                     height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
        </t:panelGroup>
        <%-- <t:outputText value="#{resourcesBundle.emp_sick1}" styleClass="lable01"/>--%>
        <t:panelGroup>
            <t:inputText forceId="true" id="sick1Id" value="#{pageBeanName.commentsDto.sick1}" styleClass="textboxlarge" style="width: 250px;"
                         disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <%-- <t:outputText value="#{resourcesBundle.emp_sick2}" styleClass="lable01"/>--%>
        <t:panelGroup>
            <t:inputText forceId="true" id="sick2Id" value="#{pageBeanName.commentsDto.sick2}" styleClass="textboxlarge" style="width: 250px;"
                         disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <%-- <t:outputText value="#{resourcesBundle.emp_sick3}" styleClass="lable01"/>--%>
        <t:panelGroup>
            <t:inputText forceId="true" id="sick3Id" value="#{pageBeanName.commentsDto.sick3}" styleClass="textboxlarge" style="width: 250px;"
                         disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <%-- <t:outputText value="#{resourcesBundle.emp_sick4}" styleClass="lable01"/>--%>
        <t:panelGroup>
            <t:inputText forceId="true" id="sick4Id" value="#{pageBeanName.commentsDto.sick4}" styleClass="textboxlarge" style="width: 250px;"
                         disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <%-- <t:outputText value="#{resourcesBundle.emp_sick4}" styleClass="lable01"/>--%>
        <t:panelGroup colspan="2">
            <t:inputText forceId="true" id="sick5Id" value="#{pageBeanName.commentsDto.sick4}" styleClass="textboxlarge" style="width: 250px;"
                         disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <t:panelGroup forceId="true" id="headerEquationForms2" colspan="2">
            <t:panelGroup style="background-color:#FFFFFF">
                <htm:table width="100%">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9"
                                     height="9"/>
                        </htm:td>
                        <htm:td styleClass="group_title">
                            &nbsp; 
                            <t:outputLabel value="#{resourcesBundle.Notes}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td height="1">
                            <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%"
                                     height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
        </t:panelGroup>
        <t:panelGroup colspan="2">
            <t:outputText value="#{resourcesBundle.note1}" styleClass="lable01"/>
            <f:verbatim>&nbsp; &nbsp; &nbsp;</f:verbatim>
            <t:inputTextarea forceId="true" id="note1Id" style=" width: 463px; height: 30px;"
                             value="#{pageBeanName.commentsDto.note1}" disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <t:panelGroup colspan="2">
            <t:outputText value="#{resourcesBundle.note2}" styleClass="lable01"/>
            <f:verbatim>&nbsp; &nbsp; &nbsp;</f:verbatim>
            <t:inputTextarea forceId="true" id="note2Id" style=" width: 463px; height: 30px;"
                             value="#{pageBeanName.commentsDto.note2}" disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
        <t:panelGroup colspan="2">
            <t:outputText value="#{resourcesBundle.note3}" styleClass="lable01"/>
            <f:verbatim>&nbsp; &nbsp; &nbsp;</f:verbatim>
            <t:inputTextarea forceId="true" id="note3Id" style=" width: 463px; height: 30px;"
                             value="#{pageBeanName.commentsDto.note3}" disabled="#{pageBeanName.disabledMode}"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
</t:panelGroup>
<t:panelGroup>

</t:panelGroup>
                     
 <t:panelGrid styleClass="addDiv_btnsPnlGrd2" columns="4" border="0" align="center" style="margin-bottom:-8px;">

    <%-- <h:panelGroup> <t:commandButton forceId="true" id="SaveNewButton" type="button"
         onclick="clearMsgs();settingFoucsAtDivAdd();validSaveAndNew();" styleClass="cssButtonSmall"
         value="#{globalResources.AddNewButton}"/> <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}"
         reRender="divAddLookup,OperationBar" oncomplete="settingFoucsAtDivAdd();"/> </h:panelGroup>--%>
   
   <t:commandLink  style="display: block; height: 17px; margin-bottom: 1px; text-align: center; text-decoration: none;"
                           target="_blank" id="bgtPrintBtn" value="#{resourcesBundle.detailedsalsheetrep}" styleClass="cssButtonSmall"
                            action="#{pageBeanName.printRDFReport}"/>
                            
         <t:commandButton value="#{resourcesBundle.FINANCIAL_Notes_REPORT_ver1}" styleClass="cssButtonSmall" type="button" >
            <a4j:support event="onclick" action="#{pageBeanName.printFinincialReport}"
                         reRender="reportUrlId"
                         oncomplete="openReportWindow('reportUrlId');"/>
        </t:commandButton>  
        <t:commandButton value="#{resourcesBundle.FINANCIAL_Notes_REPORT_ver2}" styleClass="cssButtonSmall" type="button" >
            <a4j:support event="onclick" action="#{pageBeanName.printFinincialEducationReport}"
                         reRender="reportUrlId"
                         oncomplete="openReportWindow('reportUrlId');"/>
        </t:commandButton>  
         <t:commandButton value="#{resourcesBundle.SALARY_COMMENT_SALARY_CARD_REPORT}" styleClass="cssButtonSmall" type="button" >
            <a4j:support event="onclick" action="#{pageBeanName.printSalaryCardReport}"
                         reRender="reportUrlId"
                         oncomplete="openReportWindow('reportUrlId');"/>
        </t:commandButton>  
    <h:panelGroup>
               <%-- <t:commandButton forceId="true" id="backButtonCustomDiv3" onblur="settingFoucsAtDivAdd();"
             onclick="backJsFunction();" styleClass="cssButtonSmall" value="#{globalResources.back}"/>--%>
        <%-- <a4j:jsFunction name="backJsFunction"
             oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv);settingFoucsAtListPage(); "
             reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>--%>
    </h:panelGroup>
</t:panelGrid>

<t:panelGrid styleClass="addDiv_btnsPnlGrd" columns="2" border="0" align="center" style="margin-bottom:-8px;">
    <t:commandButton forceId="true" id="SaveButton" rendered="#{!pageBeanName.disabledMode}" styleClass="cssButtonSmall"
                     value="#{globalResources.SaveButton}" action="#{pageBeanName.addSalMonComments}"
                     onclick="settingFoucsAtIntegrationDiv2();return validatemyForm('addDivVG');"/>
      <t:commandButton id="backButtonIntegrationDiv2" forceId="true" type="button" styleClass="cssButtonSmall"
         value="#{globalResources.back}"
         onclick="hideLookUpDiv(window.blocker,window.integrationDiv2);"></t:commandButton>

</t:panelGrid>
<t:inputHidden id="reportUrlId" forceId="true" value="#{pageBeanName.reportUrlLink}"/>
<t:inputHidden id="reportWindowId" forceId="true" value="#{pageBeanName.reportWindowName}"/>
<t:saveState value="#{pageBeanName.reportUrlLink}"/>
<t:saveState value="#{pageBeanName.reportWindowName}"/>
<script language="javascript" type="text/javascript">
  function clearMsgs() {
      if (document.getElementById('myForm:error') != null) {
          document.getElementById('myForm:error').innerHTML = "";
      }

      if (document.getElementById('myForm:clientErrorMessage') != null) {
          document.getElementById('myForm:clientErrorMessage').innerHTML = "";
      }

      if (document.getElementById('successAddLockup') != null) {
          document.getElementById('successAddLockup').innerHTML = "";
      }
  }

  function validSaveAndNew() {
      var valid = validatemyForm('addDivVG');
      if (valid == true) {
          saveAndNew();
          return true;
      }
      else {
          return false;
      }
  }
</script>