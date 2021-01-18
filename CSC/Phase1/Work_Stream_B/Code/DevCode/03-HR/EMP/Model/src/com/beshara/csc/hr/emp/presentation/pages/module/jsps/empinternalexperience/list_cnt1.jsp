<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>



<t:panelGrid id="panelGrid" columns="1" border="0" rowClasses="row01,row02" width="100%" style="overflow: scroll"  cellpadding="0" cellspacing="0" >

<t:panelGrid columns="4" width="100%" forceId="true" id="mainDataEmpPanel" rowClasses="row01,row02" cellpadding="0" cellspacing="0" >

        <h:outputText value="#{resourcesBundle.civil_id}"  styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText onkeyup="disableCharacters(this);" onkeypress="FireButtonClick('filterByCivilButton')" disabled="#{pageBeanName.civilExist}" maxlength="12" 
                forceId="true" id="CivilIdAdd" value="#{pageBeanName.civilId}" styleClass="textbox"  />
            <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>&nbsp;</f:verbatim> 
            <t:panelGroup>
                <t:commandButton  forceId="true" id="filterByCivilButton" type="button" value="#{globalResources.Available}" styleClass="cssButtonSmall"
                    onclick="resetMsgInAdd(); searchAndvalidate();" rendered="#{!pageBeanName.civilExist}"/>
                <a4j:jsFunction name="searchLines" action="#{pageBeanName.filterByCivilId}" reRender="mainDataEmpPanel,scriptGenerator,dataT_data_panel,OperationBar,paging_panel" oncomplete="setFocusFirstElem();"/>
            </t:panelGroup>
            <a4j:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}" oncomplete="setFocusFirstElem();" 
                                rendered="#{pageBeanName.civilExist}"  styleClass="cssButtonSmall"  actionListener="#{pageBeanName.reSetData}"  
                                reRender="mainDataEmpPanel,scriptGenerator,dataT_data_panel,OperationBar,paging_panel"/>
            <f:verbatim>&nbsp;</f:verbatim>
            <a4j:commandButton  type="button"  id="Search"  styleClass="cssButtonSmall" value="#{globalResources.SearchButton}" 
                oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);setFocusOnElement('civil_div_searchText');" onblur="setFocusFirstElem();"
                action="#{pageBeanName.showSearchForEmployeeDiv}" reRender="lovEmp" rendered="#{!pageBeanName.civilExist}"/>
            <f:verbatim></br></f:verbatim>
            
            <c2:requiredFieldValidator  componentToValidate="CivilIdAdd"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="CivilIdAddUniqueId"/>        
            <c2:regularExpressionValidator componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/" errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false" display="dynamic"/>
            
            <t:outputText forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}" rendered="#{!pageBeanName.validCivilId}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="empHired" value="#{resourcesBundle.emp_not_hired}" rendered="#{!pageBeanName.empHired}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="payrollInfoExist" value="#{resourcesBundle.emp_payroll_info_not_exist}" rendered="#{!pageBeanName.payrollInfoExist}" styleClass="errMsg"/>
        </t:panelGroup>
        
        <h:outputText value="#{resourcesBundle.employee_name}"  styleClass="lable01"/>
        <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="EmpName" value="#{pageBeanName.empFullName}"/>
        
</t:panelGrid>
</t:panelGrid>
