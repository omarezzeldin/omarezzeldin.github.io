<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<script type="text/javascript">
  function clearMsgField(componentId) {

      var component = document.getElementById(componentId);
      if (component != null) {
          component.innerHTML = '';

      }
  }

  function clearValue(componentId) {

      var component = document.getElementById(componentId);
      if (component != null) {
          component.value = '';
      }
  }

  function doOnBlur() {

      if (isVisibleComponent("lovEmp")) {
          settingFoucsAtEmpLovDiv();
      }
      else {
          document.getElementById('civilID1').focus();
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

  function validateAddComponentByGroup() {
      if (validatemyForm('sendDateAddG')) {
          if (document.getElementById('addPostponedId') != null) {
              return validatemyForm('addPostponedDateG')
          }
          return true;
      }
      else {
          return false
      };
  }
</script>
<t:panelGroup id="divAddLookup">

<t:outputLabel id="addBeforeId" value="#{resourcesBundle.addBefore}" forceId="true" rendered="#{pageBeanName.addBefore}"
                               styleClass="error"/>
                               
    <t:panelGrid id="addParentPanel" columns="2" border="0" rowClasses="row01,row02" width="100%" cellpadding="3"
                 cellspacing="0">
   
        <h:outputText value="#{resourcesBundle.extraDataTypesName}" styleClass="divtext"/>
        <t:panelGroup >
         <t:inputText disabled="true" styleClass="textboxlarge" tabindex="1" style="width: 337px;"
                         value="#{pageBeanName.extraDataTypesName}"/>
        </t:panelGroup>
        
         <h:outputText id="category_name" value="#{resourcesBundle.emp_internal_exp_catName}" styleClass="lable01"/>
            <t:panelGroup>
            <t:inputText forceId="true" id="categoryId" tabindex="2" styleClass="filteration_input_text"
                         onkeypress="filterationInputOnKeyPress(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                          value="#{pageBeanName.selectedCategory}"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
            <a4j:jsFunction name="changeCategoryVal" actionListener="#{pageBeanName.filterByCategory}" oncomplete="resetMinistry();"
                               reRender="ministryListID,ministryId,errorCodePanelId,errorCodePanelId2,BgtPanel"/>
            </t:inputText>
            
            
            <t:selectOneMenu id="categoryList" tabindex="3" forceId="true" styleClass="DropdownboxLarge"
                             value="#{pageBeanName.selectedCategory}"
                             onchange="filterationDropboxOnChange(event,this,'categoryId','errorCodeId',changeCategoryValD);">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.categoryList}" var="categoryList"
                               itemValue="#{categoryList.code.key}" itemLabel="#{categoryList.name}"/>
            <a4j:jsFunction name="changeCategoryValD" actionListener="#{pageBeanName.filterByCategory}" oncomplete="resetMinistry();" reRender="ministryListID,ministryId,errorCodePanelId,errorCodePanelId2,BgtPanel"/>
            </t:selectOneMenu>                      
            
            <t:panelGroup forceId="true" id="errorCodePanelId" style="display:block;width:250px;">
                <t:outputLabel id="errorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                               styleClass="error" style="display:none"/>
            </t:panelGroup>
            </t:panelGroup>
            
            <%--                      minisry                             --%>
            <h:outputText id="ministry_name" value="#{resourcesBundle.emp_internal_exp_minName}" styleClass="lable01"/>
             <t:panelGroup>
             <t:inputText forceId="true" id="ministryId" tabindex="4" styleClass="filteration_input_text"
                 disabled="#{pageBeanName.selectedCategory == null || pageBeanName.selectedCategory == ''}"
                 onkeypress="filterationInputOnKeyPress(event,this,'ministryListID','errorCodeId2',changeMinistryVal);"
                    value="#{pageBeanName.selectedMinistry}"
                 onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">  
                 <a4j:jsFunction name="changeMinistryVal" action="#{pageBeanName.fillWcBgtList}"  oncomplete="copyDropdownIntoInputtext('ministryId', 'ministryId');" reRender="errorCodePanelId2,BgtPanel"/>

                 </t:inputText>
            
            <t:selectOneMenu id="ministryListID" forceId="true" tabindex="5" styleClass="DropdownboxLarge"
                             value="#{pageBeanName.selectedMinistry}"
                             
                             disabled="#{pageBeanName.selectedCategory == null || pageBeanName.selectedCategory == ''}"
                              onchange="filterationDropboxOnChange(event,this,'ministryId','errorCodeId',changeMinistryValD);">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.ministryList}" var="ministryList"
                               itemValue="#{ministryList.code.key}" itemLabel="#{ministryList.name}"/>
                 <a4j:jsFunction name="changeMinistryValD" action="#{pageBeanName.fillWcBgtList}" oncomplete="copyDropdownIntoInputtext('ministryId', 'ministryId');" reRender="errorCodePanelId2,BgtPanel"/>

            </t:selectOneMenu>
            <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
              <f:verbatim>
                <br/>
            </f:verbatim>
            <t:panelGroup forceId="true" id="errorCodePanelId2" style="display:block;width:250px;">
                <t:outputLabel id="errorCodeId2" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                               styleClass="error" style="display:none"/>
                               
            </t:panelGroup>
              
            
             <c2:requiredFieldValidator  componentToValidate="ministryListID" display="dynamic"
                                           errorMessage="#{globalResources.missingField}" highlight="false"/>
            </t:panelGroup>
            
            <h:outputText value="#{resourcesBundle.budget}" styleClass="divtext"/>
    <t:panelGroup colspan="3" id="BgtPanel" forceId="true">
            <t:selectOneMenu forceId="true" id="bgtProgramId"  style="width: 337px;" value="#{pageBeanName.wcBgtValue}"  styleClass="DropdownboxLarge" disabled="#{pageBeanName.selectedMinistry == null || pageBeanName.selectedMinistry == ''}">
                <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="0"/>
                <t:selectItems value="#{pageBeanName.wcBgtList}" var="bgtProg" itemValue="#{bgtProg.code.prgCode}"
                               itemLabel="#{bgtProg.name}"/>
            </t:selectOneMenu>
        </t:panelGroup>
    </t:panelGrid>
  
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton forceId="true" styleClass="cssButtonSmall" id="SaveButton"
                         value="#{globalResources.SaveButton}" action="#{pageBeanName.performAdd}"
                         onclick="return validatemyForm();"/>
        <h:panelGroup>
            <t:commandButton forceId="true" id="backButtonAddDiv" onclick="backJsFunction(); return false;"        
                             styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunction" 
                            oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'','myForm:error','add');settingFoucsAtListPage(); "
                            reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
        </h:panelGroup>
        <%-- End By css used to spreate button--%>
    </t:panelGrid>
</t:panelGroup>

<script language="javascript" type="text/javascript">
    
    function resetMinistry()
    {
        copyDropdownIntoInputtext('ministryListID', 'ministryId');
        setFocusOnlyOnElement('ministryId');
        document.getElementById('errorCodeId2').style.display = "none";
     
    }
     </script>