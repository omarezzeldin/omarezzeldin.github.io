<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>



<%--t:panelGroup  forceId="true" style="width:99%" id="emp_query_panel_id">


<t:panelGroup rendered="#{pageBeanName.outerModule}" style="width:99%" >
<t:panelGrid  border="0" align="center">
 <t:outputText  forceId="true" id="invalCivilID2" value="#{globalResources.civiliderror}" rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
 <t:outputText  forceId="true" id="empHired2"   value="#{resourcesBundle.emp_not_hired}"  rendered="#{!pageBeanName.empHired}" styleClass="errMsg"/>
<t:outputText  forceId="true" id="payrollInfoExist2"   value="#{resourcesBundle.emp_payroll_info_not_exist}"  rendered="#{!pageBeanName.payrollInfoExist}" styleClass="errMsg"/>
</t:panelGrid>
</t:panelGroup>

<t:panelGrid columnClasses="colu1,colu2" columns="4" width="100%"  rowClasses="row01,row02" cellpadding="3" cellspacing="0" forceId="true" id="cnt1Panel" rendered="#{!pageBeanName.outerModule}" >
    <h:outputText value="#{resourcesBundle.civilid}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText rendered="#{!pageBeanName.civilExist}" tabindex="1" onblur="nextMoveByTab();" maxlength="12" forceId="true"  id="CivilIdAdd" styleClass="textbox"  value="#{pageBeanName.civilId}" onkeypress="FireButtonClick('civil_exist_btn')"/>
        <t:outputText   styleClass="textbox"  value="#{pageBeanName.civilId}" rendered="#{pageBeanName.civilExist}" />
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <t:commandButton rendered="#{! pageBeanName.civilExist}" id="civil_exist_btn" forceId="true" onclick="return validatemyForm();" tabindex="2" 
            value="#{globalResources.Available}"  styleClass="cssButtonSmall"  action="#{pageBeanName.filterByCivilId}"/>
        <a4j:commandButton value="#{globalResources.reSetButton}" rendered="#{pageBeanName.civilExist}"  styleClass="cssButtonSmall"  action="#{pageBeanName.reSetData}"  
            reRender="emp_query_panel_id,scriptPanelID, OperationBar,dataT_data_panel"/>
        <f:verbatim> &nbsp; </f:verbatim>
           
        <f:verbatim ><br/></f:verbatim>
        <c2:requiredFieldValidator     active="#{!employeeHistory.civilExist}"    componentToValidate="CivilIdAdd"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
        <c2:regularExpressionValidator active="#{!employeeHistory.civilExist}"   componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/"    errorMessage="#{globalResources.civil_no_not_less_than_12}"   highlight="false"  display="dynamic"/>
        
        <t:outputText  forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}" rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="empHired"   value="#{resourcesBundle.employee_not_hired_query}"  rendered="#{!pageBeanName.empHired}" styleClass="errMsg"/>
        <t:outputText  forceId="true" id="payrollInfoExist"   value="#{resourcesBundle.emp_payroll_info_not_exist}"  rendered="#{!pageBeanName.payrollInfoExist}" styleClass="errMsg"/>
    </t:panelGroup> 
</t:panelGrid>
</t:panelGroup--%>
<%-- ****************************************************************************************************************************************************** --%>
<%-- ****************************************************************************************************************************************************** --%>
<%-- ****************************************************************************************************************************************************** --%>


<t:panelGrid id="panelGrid" columns="1" border="0" rowClasses="row01,row02" width="100%" style="overflow: scroll"  >

<t:panelGrid columns="4" width="100%" forceId="true" id="mainDataEmpPanel" rowClasses="row01,row02" cellpadding="0" cellspacing="0" >

                    <h:outputText value="#{resourcesBundle.civil_id}" styleClass="lable01"/>
                    <t:panelGroup>
                        <t:inputText onkeypress="FireButtonClick('filterByCivilButton')" disabled="#{pageBeanName.civilExist}" maxlength="12" 
                            forceId="true" id="CivilIdAdd" value="#{pageBeanName.civilId}" styleClass="textbox"  />
                        <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
                        <f:verbatim>&nbsp;</f:verbatim> 
                        <t:panelGroup>
                            <t:commandButton  forceId="true" id="filterByCivilButton" type="button" value="#{globalResources.Available}" styleClass="cssButtonSmall"
                                onclick="resetMsgInAdd(); searchAndvalidate();" rendered="#{!pageBeanName.civilExist}"/>
                            <a4j:jsFunction name="searchLines" action="#{pageBeanName.filterByCivilId}" reRender="mainDataEmpPanel,scriptGenerator,displayBtnPanel, dataT_data_panel, OperationBar ,paging_panel" oncomplete="setFocusFirstElem();"/>
                        </t:panelGroup>
                        <a4j:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}" oncomplete="setFocusFirstElem();" 
                                            rendered="#{pageBeanName.civilExist}"  styleClass="cssButtonSmall"  actionListener="#{pageBeanName.reSetData}"  
                                            reRender="mainDataEmpPanel,scriptGenerator,bankingDataPanel, dataT_data_panel, OperationBar ,paging_panel,scriptGenGroup,scriptPanelID"/>
                        <f:verbatim>&nbsp;</f:verbatim>
                        <a4j:commandButton  type="button"  id="Search"  styleClass="cssButtonSmall" value="#{globalResources.SearchButton}" 
                            oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);setFocusOnElement('civil_div_searchText');" onblur="setFocusFirstElem();"
                            action="#{pageBeanName.showSearchForEmployeeDiv}" reRender="lovEmp" disabled="#{pageBeanName.civilExist}"/>
                        <f:verbatim></br></f:verbatim>
                        
                        <c2:requiredFieldValidator  componentToValidate="CivilIdAdd"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="CivilIdAddUniqueId"/>        
                        <c2:regularExpressionValidator componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/" errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false" display="dynamic"/>
                        
                        <t:outputLabel value="#{resourcesBundle.notDefinedGenderTypeFlag}" rendered="#{pageBeanName.notDefinedGenderTypeFlag}" styleClass="errMsg"/>
                        <t:outputText forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}" rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
                        <t:outputText forceId="true" id="empHired"   value="#{resourcesBundle.emp_not_hired}"  rendered="#{!pageBeanName.empHired}" styleClass="errMsg"/>
                        <t:outputText forceId="true" id="payrollInfoExist"   value="#{resourcesBundle.emp_payroll_info_not_exist}"  rendered="#{!pageBeanName.payrollInfoExist}" styleClass="errMsg"/>
                    </t:panelGroup>
                    
                    <h:outputText value="#{resourcesBundle.employee_name}" styleClass="lable01"/>
                    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="EmpName" value="#{pageBeanName.employeesDTO.citizensResidentsDTO.fullName}"/>
                    
                    
                    <h:outputText value="#{resourcesBundle.finance_degree}" styleClass="lable01"/>
                    <t:panelGroup colspan="3">
                        <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="degree" value="#{pageBeanName.degree}" />
                    </t:panelGroup>
                    
                    <h:outputText value="#{resourcesBundle.work_center}" styleClass="lable01"/>
                    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="WorkCenter" value="#{pageBeanName.employeesDTO.workCenterDTO.name}" />
                    
                    
                    <h:outputText value="#{resourcesBundle.jobName}" styleClass="lable01"/>
                    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="JobName"  value="#{pageBeanName.employeesDTO.jobDTO.name}" /> 
                    
                    <h:outputText value="#{resourcesBundle.gender_type}" styleClass="lable01"/>
                    <t:inputText disabled="true" styleClass="textbox"  forceId="true" id="Type"  value="#{pageBeanName.employeesDTO.citizensResidentsDTO.genderTypesDTO.name}" />
                    
                    
                    <h:outputText value="#{resourcesBundle.marital_status}" styleClass="lable01"/>
                        <t:panelGroup> 
                         <t:inputText disabled="true" styleClass="textbox" forceId="true" id="SocialStatus"  value="#{pageBeanName.employeesDTO.citizensResidentsDTO.maritalStatusDTO.name}" />
                        <t:inputHidden forceId="true" id="civilExistHidden" value="#{pageBeanName.civilExist}" />
                    </t:panelGroup>
</t:panelGrid>
</t:panelGrid>
