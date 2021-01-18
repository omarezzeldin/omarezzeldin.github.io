<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<t:panelGroup forceId="true" id="empInquiryCnt1Pnl">
    <t:messages id="msgShowId" forceId="true" showDetail="true"/>
    <t:panelGrid columns="5" rowClasses="row02,row01" width="100%" cellspacing="0" style="table-layout: auto">
        <%-- first row--%>
        <t:outputLabel id="civilID_Label_Add" value="#{resourcesBundle.civil_id}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText onkeypress="FireButtonClickOldStyle(event , 'filterByCivilButton')"
                         readonly="#{pageBeanName.civilExist}" maxlength="12" onkeyup="enabelIntegerOnly(this);"
                         onkeydown="enabelIntegerOnly(this);" forceId="true" id="civilID"
                         value="#{pageBeanName.civilId}" styleClass="textbox"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>&nbsp;</f:verbatim>
            <t:panelGroup>
                <t:commandButton forceId="true" id="filterByCivilButton" type="button"
                                 value="#{globalResources.Available}" styleClass="cssButtonSmall"
                                 onclick="resetMsgInAdd(); searchLines();" rendered="#{!pageBeanName.civilExist}"/>
                <a4j:jsFunction name="searchLines" action="#{pageBeanName.getEmpInfo}"
                                reRender="empInquiryCnt1Pnl,dataT_data_panel,scriptpanel,paging_panel_group,OperationBar,paging_panel,listSize,showDetails_btn_id"/>
            </t:panelGroup>
            <a4j:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}"
                               oncomplete="setFocusOnlyOnElement('civilID');" rendered="#{pageBeanName.civilExist}"
                               styleClass="cssButtonSmall" action="#{pageBeanName.reSetData}"
                               reRender="empInquiryCnt1Pnl,OperationBar,dataT_data_panel,paging_panel,paging_panel_group,listSize,emp_name_pnl,fullEmpName,empInquiryCnt1Pnl"/>
            <a4j:commandButton type="button" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"
                               oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();"
                               action="#{pageBeanName.showSearchForEmployeeDiv}"
                               reRender="resetBtn,lovEmp,mainDataEmpPanel,displayBtnPanel,searchDataPanel,showDetails_btn_id"
                               rendered="#{!pageBeanName.civilExist}" onblur="doOnBlurEmpLovBtn();"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <%-- <t:outputText forceId="true" id="errorId" value="#{resourcesBundle.notValidCivilId}"
                 styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>--%>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.emp_name}" styleClass="lable01"/>
        <t:panelGroup id="emp_name_pnl" forceId="true">
            <t:inputText disabled="true" id="fullEmpName" forceId="true" styleClass="textboxmedium"
                         value="#{pageBeanName.employeesDTO.citizensResidentsDTO.name}"/>
        </t:panelGroup>
        <t:commandButton id="showDetails_btn_id" value="#{resourcesBundle.show_detail}"
                           disabled="#{!pageBeanName.civilExist}" action="#{pageBeanName.viewEmpDetails}"
                           styleClass="cssButtonSmall"
                          />
    </t:panelGrid>
    <t:outputText forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}"
                  rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
    <t:outputText forceId="true" id="empHired" value="#{resourcesBundle.emp_not_hired}"
                  rendered="#{!pageBeanName.empHired}" styleClass="errMsg"/>
    <t:outputText forceId="true" id="empHiredInMin" value="#{resourcesBundle.emp_not_hired_in_min}"
                  rendered="#{!pageBeanName.empHiredInMin}" styleClass="errMsg"/>
    <t:outputText forceId="true" id="payrollInfoExist" value="#{resourcesBundle.emp_payroll_info_not_exist}"
                  rendered="#{!pageBeanName.payrollInfoExist}" styleClass="errMsg"/>
   <t:outputText forceId="true" id="payrollInfoServer" value="#{resourcesBundle.server_error}"
                  rendered="#{!pageBeanName.payrollInfoServer}" styleClass="errMsg"/>
    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
    <c:requiredFieldValidator componentToValidate="civilID" display="dynamic"
                              errorMessage="#{globalResources.missingField}" highlight="false"/>
    <c:regularExpressionValidator componentToValidate="civilID" pattern="/^[0-9]{12}$/"
                                  errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false"
                                  display="dynamic"/>
</t:panelGroup>
<f:verbatim>
    <script type="text/javascript">
      foucsAddFirstTextOnPage();

      function foucsAddFirstTextOnPage() {
          if (document.getElementById('civilID') != null) {
              document.getElementById('civilID').focus();
              document.getElementById('civilID').focus();
          }
      }

      function resetMsgInAdd() {
          if (document.getElementById('invalCivilID') != null) {
              document.getElementById('invalCivilID').innerHTML = '';
          }
          if (document.getElementById('empHired') != null) {
              document.getElementById('empHired').innerHTML = '';
          }
          if (document.getElementById('payrollInfoExist') != null) {
              document.getElementById('payrollInfoExist').innerHTML = '';
          }
             if (document.getElementById('payrollInfoServer') != null) {
              document.getElementById('payrollInfoServer').innerHTML = '';
          }
      }

      function resetName() {

          if (document.getElementById('fullEmpName') != null) {
              document.getElementById('fullEmpName').innerHTML = '';
          }
      }
    </script>
</f:verbatim>
