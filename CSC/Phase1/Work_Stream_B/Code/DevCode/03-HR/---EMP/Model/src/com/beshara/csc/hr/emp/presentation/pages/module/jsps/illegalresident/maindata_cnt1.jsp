<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<!--start row1-->
<t:panelGroup colspan="4" style="background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td width="9">
                <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
                <t:outputLabel value=" #{resourcesBundle.maindata}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
<t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="col1_WC" cellspacing="0" width="100%"
             forceId="true" id="employeeMainDataPanel" columns="4" align="cenetr" border="0">
    <h:outputText value="#{resourcesBundle.civil_id}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputText onkeypress="enabelIntegerOnly(this);goFilterByCivilButton(event);" onchange="return searchAndvalidateEmp('requiredFields');"
                     disabled="#{detailBeanName.civilExist}" maxlength="12" forceId="true" tabindex="1" id="CivilIdAdd"
                     value="#{detailBeanName.civilId}" styleClass="textboxmedium" onkeydown="onKeyDownFirstElement(event,'filterByCivilButton','BackButtonManyToMany')"/>
        <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:panelGroup forceId="true" id="btnPnlGrp">
            <t:commandButton forceId="true" id="filterByCivilButton" type="button" value="#{globalResources.Available}"
                             styleClass="cssButtonSmall" onclick="searchLines();"
                             tabindex="2"
                             rendered="#{!detailBeanName.civilExist}"/>
            <a4j:jsFunction name="searchLines" action="#{pageBeanName.filterByCivilId}"
                            reRender="employees_hiretypes,emp_minFileNo,hireDateCalendar,workCenterbtn,employees_budgetType,employees_contractType,cader_dropdown,employees_group,employees_degree,raisesCountId,showSupToJob_btn,navigationpanel,scriptGenerator,msgPnlGrp,btnPnlGrp,CivilIdAdd, employee_name_panel, employee_gender_type, employee_maritalStatus, employee_nationality, employee_nationality,employees_hiretypes_PG,QulDataTable,dataT_data_panel,contractTypePanel"
                            oncomplete="setFocusFirstElem();"/>
            <a4j:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}"

                               oncomplete="setFocusFirstElem();" rendered="#{detailBeanName.civilExist}"
                               styleClass="cssButtonSmall" actionListener="#{detailBeanName.reSetData}"
                               reRender="bouns_datepnlGp,employees_hiretypes,emp_minFileNo,hireDateCalendar,workCenterbtn,employees_budgetType,employees_contractType,cader_dropdown,employees_group,employees_degree,raisesCountId,showSupToJob_btn,navigationpanel,scriptGenerator, btnPnlGrp, CivilIdAdd, employee_name_panel, employee_gender_type, employee_nationality, employee_maritalStatus, employee_nationality,QulDataTable,dataT_data_panel,employees_hiretypes_PG"/>
        </t:panelGroup>
       
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.candidate_name}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="employee_name_panel">
        <t:inputText forceId="true" id="employees_nam1" styleClass="textboxmedium"
                     value="#{detailBeanName.candidateName}" tabindex="4" disabled="true"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.gender}" styleClass="lable01"/>
     <t:selectOneMenu 
                         forceId="true" id="employee_gender_type" tabindex="8" styleClass="DropdownboxMedium2"
                        >
            <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                          itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{pageBeanName.genderTypeList}" itemLabel="#{genderTypeList.name}"
                           itemValue="#{genderTypeList.code.key}" var="genderTypeList"/>
           
        </t:selectOneMenu>
    
    <h:outputText value="#{resourcesBundle.status}" styleClass="lable01"/>
    <t:selectOneMenu 
                         forceId="true" id="employee_maritalStatus" tabindex="8" styleClass="DropdownboxMedium2"
                        >
            <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                          itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{pageBeanName.maritalStatusList}" itemLabel="#{maritalStatusList.name}"
                           itemValue="#{maritalStatusList.code.key}" var="maritalStatusList"/>
           
        </t:selectOneMenu>
   
    <h:outputText value="#{resourcesBundle.emp_birth_date}" styleClass="lable01"/>
    
  <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         forceId="true" value="#{detailBeanName.hireDate}" id="employee_birth_date" size="20"
                         maxlength="200" styleClass="textboxmedium" tabindex="10" currentDayCellClass="currentDayCell"
                         renderAsPopup="true" align="#{globalResources.align}" onkeypress="checkaboutHireDate(event);"
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                         title="#{globalResources.inputCalendarHelpText}" onchange="checkaboutHireDateOnBlur();" >
        </t:inputCalendar>
    
    
    
    <h:outputText value="#{resourcesBundle.emp_internal_exp_catName}" styleClass="lable01"/>
      <t:selectOneMenu id="categoryList" tabindex="3" forceId="true" styleClass="DropdownboxMedium2"
                             value="#{pageBeanName.selectedCategory}"
                            >
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.categoryList}" var="categoryList"
                               itemValue="#{categoryList.code.key}" itemLabel="#{categoryList.name}"/>
            <%--<a4j:jsFunction name="changeCategoryValD" actionListener="#{pageBeanName.filterByCategory}" oncomplete="resetMinistry();" reRender="OperationBar,ministryListID,ministryId,listSize,dataT_data_panel,paging_panel_group,errorCodePanelId,errorCodePanelId2"/>--%>
            </t:selectOneMenu>                      
    
                 
</t:panelGrid>
<t:panelGroup colspan="4" style="background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td width="9">
                <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
                <t:outputLabel value=" #{resourcesBundle.phonesInformation}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
<t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="col1_WC" cellspacing="0" width="100%"
             forceId="true" id="phonesPanel" columns="4" align="cenetr" border="0">
    <h:outputText value="#{resourcesBundle.mobile}" styleClass="lable01"/>
    <t:inputText forceId="true" maxlength="20" id="mobile" tabindex="9" styleClass="textboxmedium"
                     value="#{detailBeanName.fileNo}"
                   onkeyup="enabelFloatOnly(this);"></t:inputText>
    <h:outputText value="#{resourcesBundle.phone}" styleClass="lable01"/>
   
        <t:inputText forceId="true" maxlength="20" id="phone" tabindex="9" styleClass="textboxmedium"
                     value="#{detailBeanName.fileNo}"
                   onkeyup="enabelFloatOnly(this);"></t:inputText>

    
    
</t:panelGrid>

<t:panelGroup colspan="4" style="background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td width="9">
                <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
                <t:outputLabel value=" #{resourcesBundle.addressInformation}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
<t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="col1_WC" cellspacing="0" width="100%"
             forceId="true" id="addressPanel" columns="4" align="cenetr" border="0">
    <h:outputText value="#{resourcesBundle.governorate}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputText disabled="true" forceId="true" tabindex="1" id="country"
                      styleClass="textboxmedium" />
        
        <f:verbatim>&nbsp;</f:verbatim>
        
            <t:commandButton forceId="true" id="countryBtn" type="button" value="#{globalResources.Available}"
                             styleClass="cssButtonSmall" 
                             tabindex="2"
                            />
            
        
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.street}" styleClass="lable01"/>
   <t:selectOneMenu 
                         forceId="true" id="street" tabindex="8" styleClass="DropdownboxMedium2"
                        >
            <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                          itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="##{detailBeanName.genderTypeName}" itemLabel="#{hireTypesList.concatenatedName}"
                           itemValue="#{hireTypesList.code.key}" var="hireTypesList"/>
           
        </t:selectOneMenu>
        
    <h:outputText value="#{resourcesBundle.buildingNo}" styleClass="lable01"/>
     <t:inputText forceId="true" id="buildingNo" styleClass="textboxmedium"
                     value="#{detailBeanName.candidateName}" tabindex="4" />
   
    
    <h:outputText value="#{resourcesBundle.floor}" styleClass="lable01"/>
    <t:inputText forceId="true" id="floor" styleClass="textboxmedium"
                     value="#{detailBeanName.candidateName}" tabindex="4" />
   
    <h:outputText value="#{resourcesBundle.unitNo}" styleClass="lable01"/>
    
  <t:inputText forceId="true" id="unitNo" styleClass="textboxmedium"
                     value="#{detailBeanName.candidateName}" tabindex="4" />
    
    
                 
</t:panelGrid>

<t:panelGroup colspan="4" style="background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td width="9">
                <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
                <t:outputLabel value=" #{resourcesBundle.orderType}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
<t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="col1_WC" cellspacing="0" width="100%"
             forceId="true" id="orderTypePnl" columns="4" align="cenetr" border="0">
    <h:outputText value="#{resourcesBundle.orderType}" styleClass="lable01"/>
    
        <t:inputText disabled="true" forceId="true" tabindex="1" id="orderType"
                      styleClass="textboxmedium"/>
                      
</t:panelGrid>
