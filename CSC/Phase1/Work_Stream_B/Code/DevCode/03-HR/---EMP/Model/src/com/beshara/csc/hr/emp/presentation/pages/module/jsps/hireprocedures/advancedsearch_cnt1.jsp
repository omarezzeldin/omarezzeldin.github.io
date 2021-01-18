<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:verbatim>
    <script type="text/javascript">
        function clearMsgField(componentId){
            
            var component = document.getElementById(componentId);
            if(component != null){
                component.innerText = '';
            }    
        }
    </script>
</f:verbatim>    
<t:div id="lookupAddDiv" forceId="true" style="visibility:visible;">
 <t:panelGrid id="messagePanel" columns="1" align="center" cellpadding="0" cellspacing="0" dir="#{shared_util.pageDirection}">
        <t:outputText forceId="true" id="errorMessage" value=" " styleClass="errMsg2" style="font-size:0px"/>
    </t:panelGrid>
<t:panelGroup forceId="true" id="divAddLookup">
          
            
            <t:panelGrid columns="4"  width="100%" forceId="true" id="savePanel" rowClasses="row01,row02" cellpadding="3" cellspacing="0" >
                
                    
                    
                            <t:outputText value="#{resourcesBundle.resbonsible_ministry}"  />
                            
                       <t:panelGroup  colspan="3">
                   
                        <t:inputText  forceId="true" id="ministryAdd" value="#{pageBeanName.pageDTO.ministriesDTO.name}"  disabled="true" styleClass="textboxlarge"/>   
                        
                        <f:verbatim>&nbsp;</f:verbatim>
                        <%--<a4j:commandButton value="..." actionListener="#{pageBeanName.openMinistryDiv}" styleClass="cssButtonSmall" reRender="searchArea,ministryAdd" oncomplete="javascript:changeVisibilityDiv(window.blocker,window.lookupAddDiv);" />--%>
                         <a4j:commandButton value="..."   styleClass="cssButtonSmall" action="#{pageBeanName.openAddMinistryDiv}" reRender="ministryDiv" oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divSearch);settingFoucsAtDivSearch();" id="searchMinistryBtn"  />
                        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                         <c2:requiredFieldValidator componentToValidate="ministryAdd" errorMessage="#{globalResources.missingField}" display="dynamic"/>
                    </t:panelGroup>
                    <%--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~---%>
                   
                
                    <t:outputText id="genderLabelSearch"
                      value="#{resourcesBundle.gender}" styleClass="divtext"/>
          <t:panelGroup colspan="3">
          <t:selectOneMenu forceId="true" id="genderTypeList"
                           value="#{pageBeanName.selectedGenderType}" styleClass="DropdownboxMedium2" onkeypress="FireButtonClick('myForm:searchBtn');">
            <f:selectItem itemLabel="#{resourcesBundle.select}"
                          itemValue="#{pageBeanName.emptyItemSelection}"/>
            <t:selectItems var="genList" itemLabel="#{genList.name}"
                           itemValue="#{genList.code.key}" value="#{pageBeanName.genderTypeList}"/>
          </t:selectOneMenu>
      
        </t:panelGroup>
        
    <%--    <t:outputText id="nationalityLabelSearch"
                      value="#{resourcesBundle.nationalityType}" styleClass="divtext"/>
           <t:panelGroup colspan="3">
          <t:selectOneMenu forceId="true" id="nationalityTypeList"
                           value="#{pageBeanName.selectedNationalityType}" styleClass="DropdownboxMedium2" onkeypress="FireButtonClick('myForm:searchBtn');">
            <f:selectItem itemLabel="#{resourcesBundle.select}"
                          itemValue="#{pageBeanName.emptyItemSelection}"/>
            <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_KUWAITI}"
                          itemValue="#{pageBeanName.kuwaiti}"/>
            <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_NON_KUWAITI}"
                          itemValue="#{pageBeanName.nonKuwaiti}"/>
            <f:selectItem itemLabel="#{resourcesBundle.NATIONALITY_NON_SPECIFIED}"
                          itemValue="#{pageBeanName.nonSpecified}"/>              
          </t:selectOneMenu>
       
                   </t:panelGroup>   --%>
                   
                   
                     </t:panelGrid>
             
       
             
   
             
                     </t:panelGroup >
                                 
                    
                   
 <t:panelGrid columns="2" border="0" align="center">
       <t:commandButton value="#{globalResources.SearchButton}"
                     action="#{pageBeanName.searchHireProcedures}"  
                     onclick="return validatAdvancedSearchCriteria('govFlagradioBtn', 'nationalityTypeList', 'genderTypeList', 'ministryList', 'categoryList', 'errorMessage', '#{pageBeanName.emptyItemSelection}', '#{globalResources.missingField}');"
                     styleClass="cssButtonSmall" id="searchButn"/>
       
        <t:commandButton action="list" styleClass="cssButtonSmall" forceId="true" id="backButtonAddDiv" value="#{globalResources.back}" onblur="setFocusAtMyFirstElement();"/>
    </t:panelGrid>


</t:div>

<f:verbatim>
    <script type="text/javascript">    
         setTimeout("setFocusAtMyFirstElement()",700);
        
        function setFocusAtMyFirstElement(){
            if((isVisibleComponent('divSearch')==false) && (document.getElementById('add_first_inputTxt') != null)){ 
                document.getElementById('add_first_inputTxt').focus();
                document.getElementById('add_first_inputTxt').focus();
            }
        }
    </script>
   


</f:verbatim>    
