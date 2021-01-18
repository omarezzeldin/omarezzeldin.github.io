<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
<t:panelGroup forceId="true" id="cnt1PG">
<t:panelGrid columns="4" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01" style="text-align:right">
        <h:outputText id="category_name" value="#{resourcesBundle.emp_internal_exp_catName}" styleClass="lable01"/>
        <t:panelGroup >
            <t:inputText forceId="true" id="categoryId" tabindex="2" styleClass="filteration_input_text"
                         onkeypress="filterationInputOnKeyPress(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                         onblur="filterationInputOnBlur(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
                <a4j:jsFunction name="changeCategoryVal" actionListener="#{pageBeanName.filterByCategory}"
                                oncomplete="resetMinistry();"
                                reRender="OperationBar,ministryListID,ministryId,listSize,dataT_data_panel,paging_panel_group,errorCodePanelId,errorCodePanelId2,paging_panel"/>
            </t:inputText>
            <t:selectOneMenu id="categoryList" tabindex="3" forceId="true" styleClass="DropdownboxLarge"
                             value="#{pageBeanName.selectedCategory}"
                             onchange="filterationDropboxOnChange(event,this,'categoryId','errorCodeId',changeCategoryValD);">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.categoryList}" var="categoryList"
                               itemValue="#{categoryList.code.key}" itemLabel="#{categoryList.name}"/>
                <a4j:jsFunction name="changeCategoryValD" actionListener="#{pageBeanName.filterByCategory}"
                                oncomplete="resetMinistry();"
                                reRender="OperationBar,ministryListID,ministryId,listSize,dataT_data_panel,paging_panel_group,errorCodePanelId,errorCodePanelId2,paging_panel"/>
            </t:selectOneMenu>
            <%-- <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>--%>
            <t:panelGroup forceId="true" id="errorCodePanelId" style="display:block;width:250px;">
                <t:outputLabel id="errorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                               styleClass="error" style="display:none"/>
            </t:panelGroup>
        </t:panelGroup>
        <%-- minisry--%>
        <h:outputText id="ministry_name" value="#{resourcesBundle.emp_internal_exp_minName}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="ministryId" tabindex="4" styleClass="filteration_input_text"  value="#{pageBeanName.selectedMinistry}"
                         disabled="#{pageBeanName.selectedCategory == null || pageBeanName.selectedCategory == ''}"
                         onkeypress="filterationInputOnKeyPress(event,this,'ministryListID','errorCodeId2',changeMinistryVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
                <a4j:jsFunction name="changeMinistryVal" 
                                oncomplete="copyDropdownIntoInputtext('ministryId', 'ministryId');"
                                reRender="OperationBar,listSize,dataT_data_panel,paging_panel_group,paging_panel,errorCodePanelId2"/>
            </t:inputText>
            <t:selectOneMenu id="ministryListID" tabindex="5" forceId="true" styleClass="DropdownboxLarge"
                             value="#{pageBeanName.selectedMinistry}"
                             disabled="#{pageBeanName.selectedCategory == null || pageBeanName.selectedCategory == ''}"
                             onchange="filterationDropboxOnChange(event,this,'ministryId','errorCodeId',changeMinistryValD);">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.ministryList}" var="ministryList"
                               itemValue="#{ministryList.code.key}" itemLabel="#{ministryList.name}"/>
                <a4j:jsFunction name="changeMinistryValD" 
                                oncomplete="copyDropdownIntoInputtext('ministryId', 'ministryId');"
                                reRender="OperationBar,listSize,dataT_data_panel,paging_panel_group,paging_panel,errorCodePanelId2"/>
            </t:selectOneMenu>
            <%-- <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>--%>
            <t:panelGroup forceId="true" id="errorCodePanelId2" style="display:block;width:250px;">
                <t:outputLabel id="errorCodeId2" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                               styleClass="error" style="display:none"/>
            </t:panelGroup>
        </t:panelGroup>
        
            <h:outputText value="#{resourcesBundle.civil_id}" styleClass="lable01"/>
        <t:panelGroup forceId="true" id="empCivilIdPnlgrp">
            <t:inputText maxlength="12" disabled="#{pageBeanName.validCivilId}"
                         forceId="true" id="civilID" styleClass="textboxmedium" tabindex="1"
                         onkeydown="onKeyDownFirstElement(event,'collectInfoBtn','listResult')"
                         onkeypress="clickButtonOnEnter(event,'collectInfoBtn');"
                         value="#{pageBeanName.civilId}" onkeyup="disableCharacters(this);"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:panelGroup rendered="#{! pageBeanName.validCivilId}">
                <t:commandButton id="collectInfoBtn" forceId="true" tabindex="2"
                                 onclick="clearMsgField('errInvalidCivilID');clearMsgField('errInvalidInDepartment'); x= validatemyForm(); if (x){block();} return x;"
                                 value="#{globalResources.Available}" styleClass="cssButtonSmallest"
                                 action="#{pageBeanName.checkAvailable}" />
                <f:verbatim>&nbsp;</f:verbatim>
                <a4j:commandButton type="button" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"
                                   tabindex="3"
                                   oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);settingFoucsAtEmpLovDiv();"
                                   action="#{pageBeanName.showSearchForEmployeeDiv}" reRender="lovEmp"
                                   />
            </t:panelGroup>
            <t:commandButton value="#{globalResources.reSetButton}" rendered="#{pageBeanName.validCivilId }"
                             tabindex="4" styleClass="cssButtonSmall" actionListener="#{pageBeanName.reSetData}"
                             />
       
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:outputText forceId="true" id="errInvalidCivilID" value="#{globalResources.civiliderror}"
                          rendered="#{!pageBeanName.civilExist  && pageBeanName.civilId != null}"
                          styleClass="errMsg"/>
            <t:outputText forceId="true" id="errInvalidInDepartment" value="#{resourcesBundle.civilNotvalidAtDepartment}"
                          rendered="#{pageBeanName.civilId != null && pageBeanName.civilExist && !pageBeanName.validCivilId}"
                          styleClass="errMsg"/>
            <c2:requiredFieldValidator  componentToValidate="civilID"
                                       display="dynamic" errorMessage="#{globalResources.missingField}"
                                       highlight="false"/>
            <c2:regularExpressionValidator  componentToValidate="civilID"
                                           pattern="/^[0-9]{12}$/"
                                           errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false"
                                           display="dynamic"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.person_name}" styleClass="lable01"/>
        <t:inputText disabled="true" forceId="true" id="empNameIntxt" styleClass="textboxmedium" readonly="true"
                     value="#{pageBeanName.civilName}" style="width: 255px;"/>
        
        
</t:panelGrid>
<t:panelGrid id="buttonsId" forceId="true"  cellpadding="3" align="center" cellspacing="0" columns="2" >

<t:commandButton id="viewButtonId" forceId="true" action="#{pageBeanName.filterDataTable}"    value="#{resourcesBundle.Hire_procedures_view}"   styleClass="cssButtonSmall"/>

<%--<a4j:commandButton id="reEnterValuesButton" action="#{pageBeanName.reEnterValues}" reRender="topPanel,filterationGridId , buttonsId , OperationBar ,dataT_data_panel , paging_panel" value="#{misResources.reset}" disabled="#{pageBeanName.viewBtnActive}"  styleClass="cssButtonSmall" oncomplete="enableMenuItem();"/>--%>

</t:panelGrid>
</t:panelGroup>
<script language="javascript" type="text/javascript">
  function clearMsgField(componentId) {

      var component = document.getElementById(componentId);
      if (component != null) {
          component.innerHTML = '';

      }
  }
  
   function clickButtonOnEnter(event, buttonID) {
      if (event.keyCode == 13) {
          document.getElementById(buttonID).click();
      }
      else {
          return;
      }
   }
  </script>