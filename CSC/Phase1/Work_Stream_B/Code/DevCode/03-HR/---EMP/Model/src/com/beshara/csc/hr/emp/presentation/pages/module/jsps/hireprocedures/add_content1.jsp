<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<t:div id="lookupAddDiv" forceId="true" style="visibility:visible;">
    <t:panelGroup forceId="true" id="divAddLookup">
        <t:panelGrid align="center">
            <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}"
                          styleClass="sucessMsg" rendered="#{pageBeanName.success}"/>
            <t:outputText forceId="true" id="error" value="#{pageBeanName.errorMsg}"
                          styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
        </t:panelGrid>
        <t:panelGrid columns="2" width="100%" forceId="true" id="savePanel" rowClasses="row01,row02" cellpadding="3"
                     cellspacing="0">
            <h:outputText value="#{resourcesBundle.proceduresDesc}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText maxlength="#{pageBeanName.nameMaxLength}" forceId="true" id="add_first_inputTxt" tabindex="1" onkeydown="onKeyDownFirstElement(event,'minCodeId','backButtonAddDiv')"
                             value="#{pageBeanName.pageDTO.name}" styleClass="textboxlarge2" 
                             onchange="trimBorders(add_first_inputTxt);"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>&nbsp;</f:verbatim>
                <c2:requiredFieldValidator componentToValidate="add_first_inputTxt"
                                           errorMessage="#{globalResources.missingField}" display="dynamic"/>
            </t:panelGroup>
            <%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
            <h:outputText value="#{resourcesBundle.resbonsible_ministry}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText id="minCodeId" tabindex="2" value="#{pageBeanName.ministryCode}" forceId="true" 
                    onkeypress="enabelIntegerOnlyWithZero(this);filterById(event);"           
                    styleClass="textbox" style="margin-left: 7px; width:45px;"/>
                <a4j:jsFunction name="getMinByCode" oncomplete="showvalidate_msg();" action="#{hireProceduresAddBean.getMinById}" id="functionGetMin" reRender="ministryAdd" />
                <t:inputText forceId="true" id="ministryAdd" value="#{pageBeanName.pageDTO.ministriesDTO.name}"                
                             disabled="true" styleClass="textboxlarge" style="width:400px;"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>                                                                
                <a4j:commandButton value="..." styleClass="cssButtonSmall" type="button" tabindex="3" action="#{minHelperBeanName.openMinistriesDiv}"
                                   reRender="ministryDiv ,minCodeId,lov_dataT_data_panel,lovLinkStatusPanel,javaScriptCallerOutputText"
                                   oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divSearch);settingFoucsAtDivSearch();"
                                   id="searchMinistryBtn" />
                                   
                <t:outputText value="#{resourcesBundle.inavlid_code}" id="testMsg" forceId="true" style="display:none;"  styleClass="mandatoryAsterisk"/>
                <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                <c2:requiredFieldValidator componentToValidate="ministryAdd"
                                           errorMessage="#{globalResources.missingField}" display="dynamic"/>
            </t:panelGroup>
            
        </t:panelGrid>
        
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" styleClass="cssButtonSmall" id="SaveButton" tabindex="4"
                             onclick="clearMsgField('successAddLockup');clearMsgField('error');return validatemyForm();"
                             value="#{globalResources.SaveButton}" action="#{pageBeanName.saveHireProcedure}" />
            <h:panelGroup>
                <t:commandButton forceId="true" type="button" styleClass="cssButtonSmall" id="SaveNewButton" tabindex="5"
                                 onclick="clearMsgField('error');clearMsgField('successAddLockup');return validatemyForm() && saveAndNew();"
                                 value="#{globalResources.AddNewButton}" />
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveNewHireProcedure}" 
                                oncomplete="setFocusAtMyFirstElement();" reRender="divAddLookup,searchArea"/>
            </h:panelGroup>
            <t:commandButton action="#{pageBeanName.goBack}" tabindex="6" styleClass="cssButtonSmall" forceId="true" id="backButtonAddDiv" 
                             onkeydown="onKeyDownLastElement(event,'SaveNewButton','add_first_inputTxt')"
                             value="#{globalResources.back}"/>
        </t:panelGrid>
    </t:panelGroup>
</t:div>

    <script type="text/javascript">

  setTimeout("setFocusAtMyFirstElement()", 700);

      function setFocusAtMyFirstElement() {
          if ((isVisibleComponent('divSearch') == false) && (document.getElementById('add_first_inputTxt') != null)) {
              document.getElementById('add_first_inputTxt').focus();
         //     document.getElementById('add_first_inputTxt').focus();
              
          }
      }
      
      function clearMsgField(componentId) {

          var component = document.getElementById(componentId);
          if (component != null) {
              component.innerHTML = '';
          }
      }
      

    </script>

