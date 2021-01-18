<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<t:panelGrid columns="4" rowClasses="row02,row01" cellpadding="0" columnClasses="colu1,colu2" cellspacing="0" width="100%">
 <!-- Row1-->
 <t:outputLabel value="#{resourcesBundle.hiretype}" styleClass="lable01"/>
 <t:selectOneMenu id="search_first_inputTxt"  onblur="document.getElementById('hire_stage_title').focus();" 
    value="#{pageBeanName.empEmployeesSearchDTO.empHireTypes}" styleClass="textboxmedium" forceId="true"
     onkeypress="FireButtonClick('search_btn');">
  <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="#{pageBeanName.virtual_value}"/>
  <t:selectItems value="#{pageBeanName.hire_type_list}" var="hire_type" itemValue="#{hire_type.code.keys[0]}" itemLabel="#{hire_type.name}"/>
  <f:converter converterId="javax.faces.Long"/>
 </t:selectOneMenu>
 <t:outputLabel value="#{resourcesBundle.hire_stage_title}" styleClass="lable01"/>
 <t:selectOneMenu id="hire_stage_title" value="#{pageBeanName.empEmployeesSearchDTO.empHireStage}" styleClass="textboxmedium" forceId="true"
    onkeypress="FireButtonClick('search_btn');">
  <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="#{pageBeanName.virtual_value}"/>
  <t:selectItems value="#{pageBeanName.hire_stage_list}" var="hire_stage" itemValue="#{hire_stage.code.keys[0]}" itemLabel="#{hire_stage.name}"/>
  <f:converter converterId="javax.faces.Long"/>
 </t:selectOneMenu>
 <!-- Row2-->
 <t:outputLabel value="#{resourcesBundle.nationalityType}" styleClass="lable01"/>
 <t:selectOneMenu forceId="true" id="nationalityTypeAdd" value="#{pageBeanName.empEmployeesSearchDTO.nationalityType}" styleClass="textboxmedium"
    onkeypress="FireButtonClick('search_btn');">
  <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="#{pageBeanName.virtual_value}"/>
  <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_KUWAITI}" itemValue="#{pageBeanName.nationality_kuwait}"/>
  <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_NON_KUWAITI}" itemValue="#{pageBeanName.nationality_non_kuwait}"/>
  <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_NON_SPECIFIED}" itemValue="#{pageBeanName.nationality_non_specified}"/>
 </t:selectOneMenu>
 <t:outputLabel value="#{resourcesBundle.typeGender}" styleClass="lable01"/>
 <t:selectOneMenu id="typeGender" value="#{pageBeanName.selectedGenderType}" styleClass="textboxmedium" forceId="true"
    onkeypress="FireButtonClick('search_btn');">
  <t:selectItems value="#{pageBeanName.gender_type_list}" var="gender_type" itemValue="#{gender_type.code.key}" itemLabel="#{gender_type.name}"/>
 </t:selectOneMenu>
</t:panelGrid>
<f:verbatim>
 <br/>
</f:verbatim>
<t:panelGrid columns="2" align="center" width="30%"> 
 <t:commandButton id="search_btn" forceId="true" value="#{globalResources.SearchButton}" action="#{pageBeanName.simpleSearchsearch}" styleClass="cssButtonSmall" onclick="settingFoucsAtDivSearch();return validatemyForm();"/>
 <t:commandButton forceId="true" id="customSearchBackBtn" onblur="settingFoucsAtDivSearch();" value="#{globalResources.back}" type="button" onclick="hideLookUpDiv(window.blocker,window.divSearch,null,null);settingFoucsAtListPage();" styleClass="cssButtonSmall"/>
</t:panelGrid>
