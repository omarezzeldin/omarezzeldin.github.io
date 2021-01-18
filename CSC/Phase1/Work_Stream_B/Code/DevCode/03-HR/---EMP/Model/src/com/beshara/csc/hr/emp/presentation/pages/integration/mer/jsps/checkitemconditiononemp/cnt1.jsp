<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<htm:style>
div#tipDiv
{
    width: auto;
}

div#tipDiv .green
{
 border:1px solid green;
    width:auto;
    height:auto;
    background:white;
    border-radius:6px;
}
div#tipDiv .red
{
 border:1px solid red;
    width:auto;
    height:auto;
    background:white;
    border-radius:6px;
}
 .valid_style {
    color:Green;
    text-align: left;
    font-size: 11px;
}
 .not_valid_style {
    color:red;
    font-size: 11px;
    text-align: left;
    
}
.apply{
 color:green !important;
}
.textarea_GRS {
    max-height: 200px;
    min-height: 22px;
    overflow: auto;
}

</htm:style>

<t:panelGrid forceId="true" id="cnt1Panel" columns="4" width="100%" rowClasses="row01,row02" cellspacing="0"
             cellpadding="3">
    <%--<jsp:include page="/integration/emp/jsps/empData/empDataCivilName.jsp"/>--%>
    <h:outputText value="#{resourcesBundle.CivilID}" styleClass="divtext"/>
   
    <t:panelGroup  id="emp_panel" forceId="true">
        <t:inputText rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.civilExist :!pageBeanName.employeeHelper.civilExist}" tabindex="1"   onkeyup="disableCharacters(this)" 
              maxlength="12" forceId="true"  onkeypress="FireButtonClickOldStyle(event,'civil_exist_btn');"
              id="CivilIdAdd" styleClass="textbox"  value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.realCivilId : pageBeanName.employeeHelper.realCivilId}" 
              
              /><%--keyPressNumberOrEnter(false);--%>
         <t:inputText disabled="true"  styleClass="textbox"  
         value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.realCivilId : pageBeanName.employeeHelper.realCivilId}" 
         rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.civilExist : pageBeanName.employeeHelper.civilExist}"
         style="#{  (appMainLayout.manyToMany)? ( (detailBeanName.employeeHelper.showPayrollInfo) ? 'margin-right: -16px' : '' ) : (pageBeanName.employeeHelper.showPayrollInfo ) ? 'margin-right: -16px' : '' }"/>
        
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <t:commandButton rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.civilExist : !pageBeanName.employeeHelper.civilExist}" id="civil_exist_btn" forceId="true" onclick="return validatemyForm('empData');" tabindex="2" 
            value="#{globalResources.Available}"  styleClass="cssButtonSmall"  action="#{appMainLayout.manyToMany ? detailBeanName.filterByCivilId : pageBeanName.filterByCivilId}"/>

        <t:commandButton rendered="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.civilExist : pageBeanName.employeeHelper.civilExist}" 
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
        <c2:requiredFieldValidator  componentToValidate="CivilIdAdd" group="empData" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{!checkBenRewardOnEmp.employeeHelper.civilExist}"/><%--active="#{!stopSalaryListBean.civilExist}"--%>
        <c2:regularExpressionValidator componentToValidate="CivilIdAdd" group="empData" pattern="/^[0-9]{12}$/"    errorMessage="#{globalResources.civil_no_not_less_than_12}"   highlight="false"  display="dynamic" active="#{!checkBenRewardOnEmp.employeeHelper.civilExist}"/><%--active="#{!stopSalaryListBean.civilExist}"--%>

        <t:panelGroup id="emp_panel_errs" forceId="true">
        <t:outputText  forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}" rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.validCivilId : !pageBeanName.employeeHelper.validCivilId}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="empHired"   value="#{resourcesBundle.emp_not_hired}"  rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.empHired : !pageBeanName.employeeHelper.empHired}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="payrollInfoExist"   value="#{resourcesBundle.emp_payroll_info_not_exist}"  rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.payrollInfoExist : !pageBeanName.employeeHelper.payrollInfoExist}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHiredInMin" value="#{resourcesBundle.emp_not_hired_in_min}"
                          rendered="#{appMainLayout.manyToMany ? !detailBeanName.employeeHelper.empHiredInMin : !pageBeanName.employeeHelper.empHiredInMin}" styleClass="errMsg"/>
        </t:panelGroup>
        <%--<t:outputText forceId="true" id="invalNat" value="#{pageBeanName.natMsg}"
                          rendered="#{(appMainLayout.manyToMany ? detailBeanName.employeeHelper.checkNat : pageBeanName.employeeHelper.checkNat ) && pageBeanName.invalidNationality}" styleClass="errMsg"/>--%>
    </t:panelGroup> 

                          
    <h:outputText value="#{resourcesBundle.EmpName}" styleClass="divtext"/>                         
            
    <t:inputText styleClass="textboxmedium2" style="margin-right: 3px;" disabled="true" forceId="true" id="EmpName" value="#{appMainLayout.manyToMany ? detailBeanName.employeeHelper.employeesDTO.citizensResidentsDTO.fullName : pageBeanName.employeeHelper.employeesDTO.citizensResidentsDTO.fullName}"/> 


    <t:outputText value="#{resourcesBundle[pageBeanName.itemLabelKey]}" styleClass="divtext"/>
    <t:panelGroup >
        <t:inputText id="iem_code" forceId="true" value="#{pageBeanName.itemCode}" styleClass="filteration_input_text" onkeypress="filterDivInputOnKeyPress(event,this,changeItem,'display_btn_id');" onkeyup="enabelIntegerOnly(this);" >
            <a4j:jsFunction name="changeItem" actionListener="#{pageBeanName.changeItem}" reRender="item_name,linesDetails,errorCodePanelId2,myForm:searchItemsBtn"/>
        </t:inputText>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:inputText id="item_name" forceId="true" value="#{pageBeanName.itemDetails.name}" styleClass="textboxmedium"  disabled="true" />
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <a4j:commandButton id="searchItemsBtn" value="..." action="#{pageBeanName.openSearchItemsDiv}" styleClass="cssButtonSmall" reRender="customDiv1" oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"/>
        
        
         <f:verbatim><br/></f:verbatim>
        <t:panelGroup  forceId = "true" id="errorCodePanelId2">
            <t:outputText id="errorCodeId2" value="#{integrationBundle.MessageForWrongCode}" forceId = "true" styleClass="validation_error" style="color: #FF0000;" rendered="#{pageBeanName.wrongCode}"/>
            <c2:requiredFieldValidator componentToValidate="item_name" errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic"/>
        </t:panelGroup>
    </t:panelGroup>
    
    <t:panelGroup colspan="2" rendered="#{pageBeanName.fromMer}"></t:panelGroup>
     <h:outputText value="#{integrationBundle.Allaowence_Date_Label}" styleClass="label01" rendered="#{!pageBeanName.fromMer}"/>
    <t:panelGroup rendered="#{!pageBeanName.fromMer}" >
        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                         popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         forceId="true" id="fromDate" size="20" maxlength="#{pageBeanName.calendarTextLength}"
                         styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true"
                         align="#{globalResources.align}" converter="SqlDateConverter"
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                         value="#{pageBeanName.fromDate}"></t:inputCalendar>
        <t:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.pageMode != 3 && !pageBeanName.fromMer}"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator active="#{!checkBenRewardOnEmp.fromMer}" componentToValidate="fromDate" highlight="false" display="dynamic"
                                   errorMessage="#{globalResources.missingField}"/>
        <c2:dateFormatValidator active="#{!checkBenRewardOnEmp.fromMer}" componentToValidate="fromDate"
                display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" />
    </t:panelGroup>
    
    <t:panelGroup forceId="true" id="displayBtnPanel" colspan="4" style="display: block;text-align: center;">
        <t:commandButton type="button" id="display_btn_id" value="#{integrationBundle.show_details}" disabled="#{!pageBeanName.employeeHelper.civilExist}"
                           styleClass="cssButtonSmall" onclick="if(validatemyForm()){viewCondLines();}" />
        <a4j:jsFunction name="viewCondLines"  action="#{pageBeanName.loadData}" oncomplete="javascript:changeVisibilityMsg();" reRender="linesDetails,divMsg" />
    </t:panelGroup>
</t:panelGrid>    
    
<t:panelGrid forceId="true" id="linesDetails" columns="4" width="100%" rowClasses="row02,row01" cellspacing="0"
             cellpadding="3">    
    
     <h:outputText id="conditionIDForEmp" value="#{integrationBundle.condition}" styleClass="lable01 nowrap_txt"/>                         
     <t:panelGroup colspan="3">
         <t:inputTextarea disabled="true" forceId="true" id="conditionNamId" 
                             value="#{pageBeanName.conditionName}" styleClass="#{(pageBeanName.conditionStatus == true)? 'textboxlarge apply':'textboxlarge dont_apply'}" style=" width: 822px;" />
     </t:panelGroup>
     
     <t:selectBooleanCheckbox id="ExceptionFromChkId" value="#{pageBeanName.exceptionFromCondition}"  disabled="true" />
     <t:panelGroup colspan="3">
          <t:outputText value="#{integrationBundle.exceptionFrom}" styleClass="lable01"/>
     </t:panelGroup>
    
     <h:outputText id="linesListId" value="#{integrationBundle.lines}" styleClass="lable01 nowrap_txt"/>
   
     
     
     <t:panelGroup styleClass="textarea_GRS" colspan="3">
     <t:dataList forceId="true" id="linesList"
               var="list"
               value="#{pageBeanName.linesDTOList}">
         <t:outputText value="#{(list.rightArcs !=null)? list.rightArcs:''}" styleClass="conditions" />      
         <t:outputText value="#{list.linesDTO.name}" onmouseout="hideTip();" onmouseover="doTooltipWithDetails(event,getToolTipHTML('#{(list.linesDTO.actualLineValue != null)? list.linesDTO.actualLineValue : integrationBundle.noActualValue}','#{(list.lineStatus == 1)? integrationBundle.valid : integrationBundle.unvalid}','#{(list.exceptionStatus == 1) ? integrationBundle.valid : integrationBundle.unvalid}','#{(list.exceptionDTO != null && list.exceptionStatus != null) ? true : false}','#{(list.lineStatus ==1)?'green':'red'}'));" styleClass="#{(list.lineStatus ==1)?'apply':'dont_apply'}" />
         <t:outputText value="#{(list.leftArcs !=null)? list.leftArcs:''}" styleClass="conditions" />
         <f:verbatim>
           <br/>
         </f:verbatim>
         <t:outputText value="#{(list.joinSign !=null )? list.joinSign:''}" styleClass="conditions" />
         <f:verbatim>
           <br/>
         </f:verbatim>
      </t:dataList>
     </t:panelGroup>

</t:panelGrid>

<script type="text/javascript"> 
    function getToolTipHTML(param1,param2,param4,valid ,style){
         var str = ' <div class=\"'+ style +'\">';
            if(style == 'green'){
               str +='<table class=\"valid_style\"><tr><td>حالة الموظف:</td><td nowrap=\"nowrap\">'+param1+'</td></tr>';
               str +='<tr><td>الحالة:</td><td>'+param2+'</td></tr>';
            }else{
               str +='<table class=\"not_valid_style\"><tr><td>حالة الموظف:</td><td nowrap=\"nowrap\">'+param1+'</td></tr>';
               str +='<tr><td>الحالة:</td><td>'+param2+'</td></tr>';
            }
             if(valid == 'true'){
                 //str +='<tr><td>نوع الاستثناء:</td><td>'+param3+'</td></tr>';
                 str +='<tr><td>مستثنى من السطور:</td>';
                 str +='<td>'+param4+'</td></tr>';
             }
       return str;
       
     }
     
   </script>
