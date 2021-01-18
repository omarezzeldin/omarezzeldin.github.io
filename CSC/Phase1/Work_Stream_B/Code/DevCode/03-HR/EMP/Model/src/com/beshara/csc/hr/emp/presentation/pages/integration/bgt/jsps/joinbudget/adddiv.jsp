<!-- start CSC-23239 -->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divAddLookup">

    <t:saveState value="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
    <htm:br rendered="#{pageBeanName.success || pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg" />
   
    
        <t:panelGrid columns="4" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01">
          
                <%--<h:outputText value="#{globalResources.SearchName}" styleClass="lable01"/>--%> 
                <%--<t:panelGroup>
                    <t:inputText onkeypress="FireButtonClick('SaveButton')" onfocus="this.select();" onblur="document.getElementById('add_second_inputTxt').focus();" onchange="trimBorders(add_first_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="add_first_inputTxt" value="#{pageBeanName.pageDTO.name}" styleClass="textboxlarge"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                </t:panelGroup>--%>  

        <h:outputText value="#{intgBundle.element}" styleClass="divtext"/>
        <t:panelGroup colspan="3" >
            <t:inputText forceId="true" id="itemCode"  value="#{pageBeanName.selectedElementCode}" styleClass="textboxsmall" disabled="true" style="width:80px"/>
            <t:inputText forceId="true" id="itemName"  value="#{pageBeanName.selectedElementName}" styleClass="textboxmedium"  disabled="true" style="width:307px"/>
            <%--style="width:518px;"--%>
        </t:panelGroup>
        <h:outputText value="#{intgBundle.cader}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:selectOneMenu  forceId="true" id="addCaderMenu" styleClass="textboxlarge" value="#{pageBeanName.addSelectedCader}">
                    <f:selectItem itemLabel="#{intgBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
                    <t:selectItems value="#{pageBeanName.caders}" itemLabel="#{caders.name}" itemValue="#{caders.code.elmguideCode}" var="caders"/>
                    <a4j:support actionListener="#{pageBeanName.getAddGroupsByCader}" event="onchange" reRender="addGroupMenu , addDegreeMenu"/>
                </t:selectOneMenu>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim><BR/></f:verbatim>
                <c:compareValidator componentToValidate="addCaderMenu" componentToCompare="virtualLongValue" operator="not" display="dynamic"
                                    errorMessage="#{globalResources.missingField}" highlight="false" />
            </t:panelGroup>
            <h:outputText value="#{intgBundle.group}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:selectOneMenu  forceId="true" id="addGroupMenu" styleClass="textboxlarge" value="#{pageBeanName.addSelectedGroup}">
                    <f:selectItem itemLabel="#{intgBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
                    <t:selectItems value="#{pageBeanName.addGroups}" itemLabel="#{groups.name}" itemValue="#{groups.code.elmguideCode}" var="groups"/>
                    <a4j:support actionListener="#{pageBeanName.getAddDegreesByGroup}" event="onchange" reRender="addDegreeMenu"/>
                </t:selectOneMenu>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim><BR/></f:verbatim>
                <c:compareValidator componentToValidate="addGroupMenu" componentToCompare="virtualLongValue" operator="not" display="dynamic"
                                    errorMessage="#{globalResources.missingField}" highlight="false" />
            </t:panelGroup>
            <h:outputText value="#{intgBundle.Degree}" styleClass="divtext"/>
            <t:panelGroup colspan="3" >
                <t:selectOneMenu  forceId="true" id="addDegreeMenu" styleClass="textboxlarge"
                                 value="#{pageBeanName.addSelectedDegree}">
                    <f:selectItem itemLabel="#{intgBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
                    <t:selectItems value="#{pageBeanName.addDegrees}" itemLabel="#{degrees.name}"
                                   itemValue="#{degrees.code.elmguideCode}" var="degrees"/>
                </t:selectOneMenu>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim><BR/></f:verbatim>
                <c:compareValidator componentToValidate="addDegreeMenu" componentToCompare="virtualLongValue" operator="not" display="dynamic"
                                    errorMessage="#{globalResources.missingField}" highlight="false" />
            </t:panelGroup>
            
            <h:outputText value="#{intgBundle.budgetItem}" styleClass="divtext"/>
            <t:panelGroup colspan="3" id="bgtpnl" forceId="true">
            <%--<t:inputText forceId="true" id="addBudget_code"  value="" styleClass="textboxsmall" 
            onkeyup="enabelIntegerOnly(this);" onblur="getByBgtCode();" 
            style="width:80px"/>--%>
          
           <t:inputText forceId="true" id="filterBgtCodeID" styleClass="filteration_input_text"
                     value="#{pageBeanName.filterBgtCode}" onkeyup="enabelIntegerOnly(this);"
                     style="margin-left:5px;" 
                     onkeypress="filterDivInputOnKeyPress(event,this,filterBgtCode,null);"
                     onblur="filterDivInput('filterBgtCodeID',filterBgtCode,null);">
          
            <a4j:jsFunction name="filterBgtCode" actionListener="#{pageBeanName.filterByBgtCode}"
                            reRender="bgtpnl,scriptGenerator"/>
        </t:inputText>
        
            <%--<a4j:jsFunction name="getByBgtCode" oncomplete="setFocusOnlyOnElement('problem_importanceID');"
                            action="#{pageBeanName.getByBgtCode}"
                            reRender="radio_type_group,systempOrmachine_pnl,systempOrmachine_pnld,scriptGenerator,coord_pnl"/>--%>
                            
                <t:inputText forceId="true" id="addBudget"  value="#{pageBeanName.addSelectedBudgetDTO.name}" styleClass="textboxmedium" disabled="true"/>
                <a4j:commandButton value="..."  styleClass="cssButtonSmall" 
                    action="#{pageBeanName.openTreeDiv}"  
                    oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');changeVisibilityDiv(window.blocker,window.divTree,'backButtonTreeDiv');focusOnSearchText();" 
                    reRender="divTree" id="selectBudgetBtn" />
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim><BR/></f:verbatim>
        
         <t:outputLabel id="filterErrId" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                       styleClass="error" rendered="#{pageBeanName.wrongfilterBgtCode}"/>
    
       <c:requiredFieldValidator componentToValidate="addBudget" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false" />
            </t:panelGroup>
            
            <h:outputText value="#{intgBundle.defaultValue}" styleClass="divtext"/>
            <t:selectBooleanCheckbox id="addDefault_value" value="#{pageBeanName.addDefaultFlag}" />
                
        </t:panelGrid>
        
       <t:outputLabel  style="font-size: 4pt;height:3px"/>  
    <t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.addBgtAcctElmguides}" 
                onclick="settingFoucsAtDivAdd();return validatemyForm();"  rendered="#{pageBeanName.pageMode == 2}"/>  
                <%-- onclick="settingFoucsAtDivAdd();return validateInputTextAndBlock();" --%>
            <%--<h:panelGroup>
                <t:commandButton forceId="true" id="SaveNewButton" type="button" onclick="clearMsgs();settingFoucsAtDivAdd();validateSaveAndNew('add_first_inputTxt','myForm:clientErrorMessage',null,null,'successAddLockup');" 
                    rendered="#{pageBeanName.pageMode != 1}" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>                
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup,OperationBar" oncomplete="settingFoucsAtDivAdd();"/>
            </h:panelGroup>--%>
            <t:commandButton value="#{intgBundle.edit}" styleClass="cssButtonSmall" action="#{pageBeanName.edit}" 
                onclick="return validatemyForm();" rendered="#{pageBeanName.pageMode == 1}"/>
            <h:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}" 
                    oncomplete="hidTreeDiv(null,window.blocker,window.lookupAddDiv,null);" 
                    reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
                    <%--"hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','myForm:error','add');settingFoucsAtListPage(); " --%>
            </h:panelGroup>
        </t:panelGrid>

</t:panelGroup>

<script language="javascript" type="text/javascript">
    function clearMsgs() {
        if(document.getElementById('myForm:error') != null) {
            document.getElementById('myForm:error').innerHTML = "";
        }
        
        if(document.getElementById('myForm:clientErrorMessage') != null) {
            document.getElementById('myForm:clientErrorMessage').innerHTML = "";
        }
        
        if(document.getElementById('successAddLockup') != null) {
            document.getElementById('successAddLockup').innerHTML = "";
        }
    }
    
      function filterDivInput(inputtextName, ajaxFunction, nextFocusId) {
          inputtext = document.getElementById(inputtextName);
          trimBorders(inputtext.value);
          if (inputtext.value == '') {
              if (nextFocusId != null) {
                  setFocusOnElement(nextFocusId);
              }
              //              event.stopPropagation();
              //              event.preventDefault();
              return false;
          }

          if (ajaxFunction != null) {
              ajaxFunction();
          }
          else {
              //              event.stopPropagation();
              //              event.preventDefault();
          }
          if (nextFocusId != null) {
              setFocusOnElement(nextFocusId);
          }
      }
    
    function filterDivInputOnKeyPress(e, inputtext, ajaxFunction, nextFocusId) {
          var event = e || window.event;
          if (event.keyCode == 13) {
              
              event.stopPropagation();
             event.preventDefault();
              trimBorders(inputtext.value);
              if (inputtext.value == '') {
                  if (nextFocusId != null) {
                      setFocusOnElement(nextFocusId);
                  }
                 event.stopPropagation();
                  event.preventDefault();
                  return false;
              }

              if (ajaxFunction != null) {
                  ajaxFunction();
              }
              else {
//                  event.stopPropagation();
//                  event.preventDefault();
              }
              if (nextFocusId != null) {
                  setFocusOnElement(nextFocusId);
              }

          }
      }
      
    function validateInputTextAndBlock()
    {
        if(validateInputText('add_first_inputTxt','myForm:clientErrorMessage',null,null,'successAddLockup')== true)
        {
        block();
        return true;
        }
        else
        {
        return false;
        }
    }
    
</script>
<!-- end CSC-23239 -->