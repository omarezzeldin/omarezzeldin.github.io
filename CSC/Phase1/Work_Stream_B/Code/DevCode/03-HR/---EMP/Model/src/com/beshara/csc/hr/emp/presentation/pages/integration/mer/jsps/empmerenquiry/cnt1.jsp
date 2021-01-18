<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:messages showDetail="true"/>
<t:panelGrid forceId="true" id="cnt1Panel" columns="4" width="100%" rowClasses="row01,row02" cellspacing="0"
             cellpadding="0">
             <%--
    <h:outputText value="#{integrationBundle.CivilID}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText rendered="#{!pageBeanName.civilExist}" onblur="document.getElementById('civil_exist_btn').focus();"
                     maxlength="12" forceId="true" id="CivilIdAdd" styleClass="textbox" value="#{pageBeanName.civilId}"
                     onkeypress="FireButtonClick('civil_exist_btn')"/>
        <t:inputText styleClass="textbox" value="#{pageBeanName.civilId}" disabled="true"
                     rendered="#{pageBeanName.civilExist}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <t:commandButton rendered="#{!pageBeanName.civilExist}" id="civil_exist_btn" forceId="true"
                         onclick="resetMsgInAdd(); return validatemyForm('civilValidations');" value="#{globalResources.Available}"
                         styleClass="cssButtonSmall" action="#{pageBeanName.filterByCivilId}"/>
        <a4j:commandButton type="submit" value="#{globalResources.reSetButton}" rendered="#{pageBeanName.civilExist}"
                           styleClass="cssButtonSmall" actionListener="#{pageBeanName.reSetData}" id="reSetData"
                           reRender="dedectionsTotalValue,benfitsTotalValue,cnt1Panel,mainDataEmpPanel,scriptpanel,benfitsList_data,benfitsList_panel,deductionsList_panel,deductionsList_data,displayBtnPanel,yearMenu,monthMenu,benfits_paging_panel_group,deductions_paging_panel_group,totalSalaryGroup,paging_panel_grid"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton type="button" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"
                           oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();"
                           action="#{pageBeanName.showSearchForEmployeeDiv}"
                           reRender="lovEmp,mainDataEmpPanel,displayBtnPanel" rendered="#{!pageBeanName.civilExist}"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator active="#{!merEmpBenListBean.civilExist}" componentToValidate="CivilIdAdd"
                                 group="civilValidations"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
        <c2:regularExpressionValidator active="#{!merEmpBenListBean.civilExist}" componentToValidate="CivilIdAdd"
                                       pattern="/^[0-9]{12}$/" group="civilValidations"
                                       errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false"
                                       display="dynamic"/><!--globalResources.civiliderror-->
        <t:outputText forceId="true" id="invalCivilID" value="#{integrationBundle.emp_not_hired_in_min}"
                      rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHired" value="#{integrationBundle.emp_not_hired}"
                      rendered="#{!pageBeanName.empHired}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="payrollInfoExist" value="#{integrationBundle.emp_payroll_info_not_exist}"
                      rendered="#{!pageBeanName.payrollInfoExist}" styleClass="errMsg"/>
    </t:panelGroup>
    <h:outputText value="#{integrationBundle.EmpName}" styleClass="divtext"/>
    <t:inputText styleClass="textboxlarge" forceId="true" disabled="true" id="EmpName"  style="width: 358px;"
                 value="#{pageBeanName.pageDTO.employeesDTO.citizensResidentsDTO.fullName}"/>
    <h:outputText value="#{integrationBundle.Degree}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
        <t:inputText styleClass=" textboxXlarge" forceId="true" id="degree" disabled="true"
                     value="#{pageBeanName.degree}" style="width: 774px !important;"/>
    </t:panelGroup>
    <h:outputText value="#{integrationBundle.WorkCenter}" styleClass="divtext"/>
    <t:inputText styleClass="textboxlarge" disabled="true" forceId="true" id="WorkCenter"
                 value="#{pageBeanName.pageDTO.employeesDTO.workCenterDTO.name}"/>
                 
    <h:outputText value="#{integrationBundle.budgetProgram}" styleClass="divtext"/>
    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="budgetProgram" value="#{pageBeanName.employeesDTO.bgtProgramsDTO.name}"  style="width: 358px;"/>
    
    <h:outputText value="#{integrationBundle.budgetType}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="budgetType" value="#{pageBeanName.employeesDTO.bgtTypesDTO.name}" />
    </t:panelGroup>
    --%>
    
    <%--<jsp:include page="/integration/emp/jsps/empData/empDataCivilName.jsp"/>--%>
    
    <!--================================================================ -->
 <h:outputText value="#{resourcesBundle.CivilID}" styleClass="divtext"/>
   
    <t:panelGroup  id="emp_panel" forceId="true">
        <t:inputText rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.civilExist :!pageBeanName.employeeHelper.civilExist}" tabindex="1"   onkeyup="disableCharacters(this)" 
              maxlength="12" forceId="true"  onkeypress="myFireButtonClick(event, 'civil_exist_btn');"
              id="CivilIdAdd" styleClass="textbox"  value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.realCivilId : pageBeanName.employeeHelper.realCivilId}" 
              style="#{  (appMainLayout.manyToMany)? ( (detailBeanName.employeeHelper.showPayrollInfo) ? '' : ' margin-right: 6px;' ) : (pageBeanName.employeeHelper.showPayrollInfo ) ? '' : ' margin-right: 6px;' }"
              /><%--keyPressNumberOrEnter(false);--%>
         <t:inputText readonly="true" styleClass="textbox"  
         value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.realCivilId : pageBeanName.employeeHelper.realCivilId}" 
         rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.civilExist : pageBeanName.employeeHelper.civilExist}"
         style="#{  (appMainLayout.manyToMany)? ( (detailBeanName.employeeHelper.showPayrollInfo) ? 'margin-right: -16px' : '' ) : (pageBeanName.employeeHelper.showPayrollInfo ) ? 'margin-right: -16px' : '' }"/>
        
        <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{!pageBeanName.fromMerEmpWaitApproval}"/>
        <t:commandButton rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.civilExist : !pageBeanName.employeeHelper.civilExist}" id="civil_exist_btn" forceId="true" onclick="resetMsgInAdd(); return validatemyForm('empData');" tabindex="2" 
            value="#{globalResources.Available}"  styleClass="cssButtonSmall"  action="#{appMainLayout.manyToMany ? detailBeanName.filterByCivilId : pageBeanName.filterByCivilId}"/>

        <t:commandButton rendered="#{(pageBeanName.employeeHelper.civilExist) && (!pageBeanName.fromMerEmpWaitApproval)}" 
            id="reset_civil_btn" forceId="true" onclick="resetMsgInAdd(); return validatemyForm('empData');" tabindex="2" 
            value="#{globalResources.reSetButton}"  styleClass="cssButtonSmall"  action="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.reSetData : pageBeanName.employeeHelper.reSetData}"/>
      
        <%--<a4j:commandButton value="#{globalResources.reSetButton}" 
            rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.civilExist : pageBeanName.employeeHelper.civilExist}"  
            styleClass="cssButtonSmall"  actionListener="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.reSetData : pageBeanName.employeeHelper.reSetData}"  
            reRender="empPayrollInfoGrp,cnt1Panel,scriptGenerator,scriptpanel,dataT_data_panel,OperationBar,paging_panel,emp_panel,EmpName,emp_validation"/>--%><%--oncomplete="foucsAddbuttonOnList();foucsAddbuttonOnList();" --%>
        <f:verbatim> &nbsp; </f:verbatim>
        <a4j:commandButton type="button"  value="#{globalResources.SearchButton}" styleClass="cssButtonSmall" oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();" action="#{appMainLayout.manyToMany ? detailBeanName.showSearchForEmployeeDiv : pageBeanName.showSearchForEmployeeDiv}"  tabindex="3"
               reRender="lovEmp,mainDataEmpPanel" rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.civilExist : !pageBeanName.employeeHelper.civilExist}" />        
        <f:verbatim ><br/></f:verbatim>
        <c2:requiredFieldValidator  group="empData"   componentToValidate="CivilIdAdd"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/><%--active="#{!stopSalaryListBean.civilExist}"--%>
        <c2:regularExpressionValidator group="empData"  componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/"    errorMessage="#{globalResources.civil_no_not_less_than_12}"   highlight="false"  display="dynamic"/><%--active="#{!stopSalaryListBean.civilExist}"--%>

        <t:outputText  forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}" rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.validCivilId : !pageBeanName.employeeHelper.validCivilId}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="empHired"   value="#{resourcesBundle.emp_not_hired}"  rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.empHired : !pageBeanName.employeeHelper.empHired}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="payrollInfoExist"   value="#{resourcesBundle.emp_payroll_info_not_exist}"  rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.payrollInfoExist : !pageBeanName.employeeHelper.payrollInfoExist}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHiredInMin" value="#{resourcesBundle.emp_not_hired_in_min}"
                          rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.empHiredInMin : !pageBeanName.employeeHelper.empHiredInMin}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="empEndedService"   value="#{resourcesBundle.emp_not_ended_service}"  rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.empEndedService : !pageBeanName.employeeHelper.empEndedService}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="empLastDegNotMokafaaShamla"   value="#{resourcesBundle.empLastDegNotMokafaaShamla}"  rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.empLastDegNotMokafaaShamla : !pageBeanName.employeeHelper.empLastDegNotMokafaaShamla}" styleClass="errMsg"/>
        <%--<t:outputText forceId="true" id="invalNat" value="#{pageBeanName.natMsg}"
                          rendered="#{(appMainLayout.manyToMany ? detailBeanName.employeeHelper.checkNat : pageBeanName.employeeHelper.checkNat ) && pageBeanName.invalidNationality}" styleClass="errMsg"/>--%>
    </t:panelGroup> 

                          
    <h:outputText value="#{resourcesBundle.EmpName}" styleClass="divtext"/>                         
        
    <t:panelGroup>
        
        <t:inputText styleClass="textboxmedium2" style="margin-right: 3px;" disabled="true" forceId="true" id="EmpName" value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO.citizensResidentsDTO.fullName : pageBeanName.employeeHelper.employeesDTO.citizensResidentsDTO.fullName}"/> 

<%--<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.civilId : pageBeanName.employeeHelper.civilId}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.realCivilId : pageBeanName.employeeHelper.realCivilId}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.validCivilId : pageBeanName.employeeHelper.validCivilId}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.civilExist : pageBeanName.employeeHelper.civilExist}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.empHired : pageBeanName.employeeHelper.empHired}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.empHiredInMin : pageBeanName.employeeHelper.empHiredInMin}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollInfoExist : pageBeanName.employeeHelper.payrollInfoExist}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO : pageBeanName.employeeHelper.employeesDTO}"/>
<t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.showPayrollInfo : pageBeanName.employeeHelper.showPayrollInfo}"/>--%>
<f:verbatim>
<script type="text/javascript"> 
    
    function resetMsgInAdd()
    {
        if(document.getElementById('invalCivilID') != null)
        {
            document.getElementById('invalCivilID').innerHTML='';
        } 
        if(document.getElementById('empHired')!=null)
        {
            document.getElementById('empHired').innerHTML='';
        }
        if(document.getElementById('payrollInfoExist')!=null)
        {
            document.getElementById('payrollInfoExist').innerHTML='';
        }
        if(document.getElementById('empHiredInMin')!=null)
        {
            document.getElementById('empHiredInMin').innerHTML='';
        }
    }
    
    function doOnBlur() {
        
        if(isVisibleComponent("lovEmp")){
          
            settingFoucsAtEmpLovDiv();
            settingFoucsAtEmpLovDiv();
        } else {
       //    alert('now in list part');
           document.getElementById('CivilIdAdd').focus();
           document.getElementById('CivilIdAdd').focus();
        //   foucsAddbuttonOnList();
        }
    }
    
    function myFireButtonClick(e, buttonId) {
        if (e.keyCode == 13) {
            e.cancelBubble = true;
            e.returnValue = false;
            e.preventDefault();
            var button = document.getElementById(buttonId);
            button.click();
        }
    }
    
 function nextMoveByTab()
    { 
      document.getElementById('civil_exist_btn').focus();
      document.getElementById('civil_exist_btn').focus(); 
    }

</script>
</f:verbatim>
                       
</t:panelGroup>                          

    <t:panelGroup colspan="4"  id="empPayrollInfoGrp" forceId="true" rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.showPayrollInfo : pageBeanName.employeeHelper.showPayrollInfo}" >
    <t:panelGrid columns="4" rowClasses="row02,row01" width="100%" cellpadding="0" cellspacing="0" id="empPayrollInfoGrid" forceId="true" >
       
         
        <h:outputText value="#{integrationBundle.Degree}" styleClass="divtext"/>
        <t:panelGroup >
            <t:inputText styleClass=" textboxXlarge" forceId="true" id="degree" disabled="true"
                         value="#{appMainLayout.manyToMany ? detailBeanName.degree : pageBeanName.degree}" style="width: 315px !important;#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollRMargin : pageBeanName.employeeHelper.payrollRMargin}"/>
        </t:panelGroup>
            <!--Dev-213 -->
        <h:outputText value="#{pageBeanName.empStatusForSalDTO == null  ? integrationBundle.EMP_Start_work_date_STR :pageBeanName.empStatusForSalDTO.empStatus}" styleClass="divtext"/>
        <t:inputText styleClass="textboxlarge" disabled="true" forceId="true" 
                     value="#{pageBeanName.empStatusForSalDTO.empqurDate}" converter="BesharaDateConverter" style="width: 280px; #{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollRMargin : pageBeanName.employeeHelper.payrollRMargin}"/>
        <!-- End Dev-213 -->        
        <h:outputText value="#{integrationBundle.WorkCenter}" styleClass="divtext"/>
        <t:inputText styleClass="textboxlarge" disabled="true" forceId="true" id="WorkCenter"
                     value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO.workCenterDTO.name : pageBeanName.employeeHelper.employeesDTO.workCenterDTO.name}" style="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollRMargin : pageBeanName.employeeHelper.payrollRMargin}"/>
                     

        <h:outputText value="#{integrationBundle.job_name}" styleClass="divtext"/>
        <t:panelGroup >
        <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="empJob"
                     value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO.jobDTO.name : pageBeanName.employeeHelper.employeesDTO.jobDTO.name}"
                     style="width: 280px; #{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollRMargin : pageBeanName.employeeHelper.payrollRMargin}"/>
         </t:panelGroup>
         
         <h:outputText value="#{integrationBundle.budgetProgram}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="budgetProgram" 
            value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO.workCenterDTO.name : pageBeanName.employeeHelper.employeesDTO.bgtProgramsDTO.name}"  
            style="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollRMargin : pageBeanName.employeeHelper.payrollRMargin}"/>
        
        <h:outputText value="#{integrationBundle.budgetType}" styleClass="divtext"/>
        <%--<t:panelGroup colspan="#{(appMainLayout.manyToMany)? ( (detailBeanName.employeeHelper.showBankAccData) ? 1 : 3 ) : (pageBeanName.employeeHelper.showBankAccData ) ? 1 : 3 }">--%>
        <t:panelGroup >
        <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="budgetType" 
            value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO.workCenterDTO.name : pageBeanName.employeeHelper.employeesDTO.bgtTypesDTO.name}"  
            style="width: 280px; #{appMainLayout.manyToMany ? detailBeanName.employeeHelper.payrollRMargin : pageBeanName.employeeHelper.payrollRMargin}"/>
        </t:panelGroup>
        

        
    <t:panelGroup colspan="4" rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.showBankAccData : pageBeanName.employeeHelper.showBankAccData}">
        <h:outputText value="#{resourcesBundle.sal_calc_date}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textbox" forceId="true" id="salCalcDate" value="#{pageBeanName.monSalDTO.calcDate}" >
        <f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+2" />
        </t:inputText>
        <h:outputText value="#{resourcesBundle.sal_status}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textbox" style="margin: 0px 22px 0px 10px;" forceId="true" id="salStatus" value="#{pageBeanName.monSalDTO.paymentStatusDTO.name}" />
    <%--</t:panelGroup>
    
    <t:panelGroup colspan="2" rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.showBankAccData : pageBeanName.employeeHelper.showBankAccData}">--%>
          <h:outputText value="#{resourcesBundle.sheet_no}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textbox" style="margin: 0px 25px 0px 10px;" forceId="true" id="sheet_no" value="#{paySlipMaintainQueryBean.sheetCode}" />
        
        <h:outputText value="#{resourcesBundle.bank_name}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textbox" style="margin: 0px 22px 0px 0px;" forceId="true" id="bankName" value="#{pageBeanName.monSalDTO.bankBranchesDTO.banksDTO.name}" />
    </t:panelGroup>
    
    <t:panelGroup colspan="4" rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.showBankAccData : pageBeanName.employeeHelper.showBankAccData}">
        <h:outputText value="#{resourcesBundle.branch_name}" styleClass="divtext"/>
        <t:inputText disabled="true" styleClass="textbox" style="margin: 0px 70px 0px 0px;" forceId="true" id="branchName" value="#{pageBeanName.monSalDTO.bankBranchesDTO.name}" />
        
        <h:outputText value="#{resourcesBundle.account_no}" styleClass="divtext"/>
        <t:panelGroup>
            <t:inputText disabled="true" styleClass="textboxlarge" style="margin: 0 13px 0 0; width: 290px;" forceId="true" id="accountNo" value="#{pageBeanName.monSalDTO.accountChekNo}" />
            <t:inputHidden forceId="true" id="civilExistHidden" value="#{pageBeanName.civilExist}" />
        </t:panelGroup>
    </t:panelGroup>

        
        </t:panelGrid>
    </t:panelGroup>
    <!--================================================================ -->
    
    <t:outputText value="#{integrationBundle.search_date_type}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
    <t:panelGroup id="radioPanel"  dir="#{shared_util.pageDirection}" style="float: right; margin-left: 50px; display: block;">
        <t:selectOneRadio onclick="changDateView();"  forceId="true" id="radioBtn" value="#{pageBeanName.dayRadio}">
            <f:selectItem   itemLabel="#{integrationBundle.search_day_title}" itemValue="0"/>
            <f:selectItem itemLabel="#{integrationBundle.search_monthYear_title}" itemValue="1"/>
        </t:selectOneRadio>
        <a4j:jsFunction name="changDateView" actionListener="#{pageBeanName.resetDate}" reRender="displayBtnPanel,calDate_monYearPnl,calDatePanel" />
    </t:panelGroup>
    
    
    <t:panelGroup id="calDate_monYearPnl" forceId="true" >
    <t:panelGroup id="monYearPnl" forceId="true" rendered="#{pageBeanName.dayRadio == '1'}">
     <t:outputText value="#{integrationBundle.MONTH}" styleClass="divtext" style="margin-left: 15px; display: inline;"/>
       <t:selectOneMenu forceId="true" id="monthMenu" styleClass="textboxsmall2" value="#{pageBeanName.month}">
            <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}"
                           var="months"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
         <h:outputText value="#{integrationBundle.YEAR}" styleClass="divtext"/>
         <t:selectOneMenu forceId="true" id="yearMenu" styleClass="textboxsmall2" value="#{pageBeanName.year}"
                         style="margin-right: 20px;">
            <t:selectItems value="#{pageBeanName.years}" itemLabel="#{years.code.key}" itemValue="#{years.code.key}"
                           var="years"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
    </t:panelGroup>


     <t:panelGroup id="calDatePanel" forceId="true" style="#{pageBeanName.dayRadio=='0' ? '' : 'display:none;'}">
     <t:panelGroup id="calDatePnl" forceId="true">
        <t:outputText value="#{integrationBundle.search_date_title}" style="margin-left: 15px; display: inline;"/>
       <t:inputCalendar  title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true"  id="calDateClientId" 
        value="#{pageBeanName.calDate}"
                size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" 
                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <%--<f:verbatim><br/></f:verbatim>--%>
        <c2:dateFormatValidator group="dateValidation" active="#{pageBeanName.dayRadio == '0'}" componentToValidate="calDateClientId" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" />
        <c2:requiredFieldValidator group="dateValidation"  componentToValidate="calDateClientId"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
     </t:panelGroup>
     </t:panelGroup>
    </t:panelGroup>
        
        
     <t:panelGroup >  
   
    <t:panelGroup forceId="true" id="displayBtnPanel">
                <%--<t:commandButton type="button" id="display_btn_id" value="#{integrationBundle.show_details}"
                                 style="margin-right: 20px;" disabled="#{!pageBeanName.civilExist}"
                                 styleClass="cssButtonSmall"
                                 onclick="if( myValidateDate(#{pageBeanName.dayRadio})){viewJSFn();}"/>--%>
                <%--<a4j:jsFunction name="viewJSFn" action="#{pageBeanName.viewTable}"
                                reRender="benfitsList_data,benfitsList_panel,deductionsList_panel,deductionsList_data,benfits_paging_panel,deductions_paging_panel,yearMenu,monthMenu,display_btn_id,totalSalary,dedectionsTotalValue,benfitsTotalValue,totalSalary,totalSalaryGroup,paging_panel_grid"/>--%>
                <t:commandButton id="display1_btn_id" value="#{integrationBundle.show_details}"
                                 styleClass="cssButtonSmall" style="margin-right: 20px;"
                                 disabled="#{!pageBeanName.civilExist}" action="#{pageBeanName.viewTable}"
                                 onclick="return myValidateDate(#{pageBeanName.dayRadio});"/>
    </t:panelGroup>
    </t:panelGroup>
    </t:panelGroup>
</t:panelGrid>
<script type="text/javascript">
    function myValidateDate(dayRadio) {
        //alert("dayRadio = "+dayRadio);
        if(dayRadio == '0'){
            //alert("0");
            return validatemyForm('dateValidation');
        }
        else {
            //alert("1");
         return true;
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
  }

  function doOnBlur() {

      if (isVisibleComponent("divLov")) {
          settingFoucsAtLovDiv();
      }
      else {
          document.getElementById('CivilIdAdd').focus();
      }
  }

  foucsAddbuttonOnList();

  function foucsAddbuttonOnList() {
      if (document.getElementById('CivilIdAdd') != null) {
          document.getElementById('CivilIdAdd').focus();
      }
  }
</script>
