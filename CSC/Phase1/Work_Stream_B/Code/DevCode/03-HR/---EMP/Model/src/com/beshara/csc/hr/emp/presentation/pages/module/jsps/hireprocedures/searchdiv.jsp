<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>


<t:panelGroup id="searchArea" forceId="true">
<%--  <t:panelGroup rendered="#{pageBeanName.simpleSearchFlag}">
    <t:panelGrid id="radioPanel" align="center"
                 dir="#{shared_util.pageDirection}">
      <t:selectOneRadio styleClass="divtext" forceId="true" id="radioBtn"
                        value="#{pageBeanName.stringSearchType}">
        <f:selectItem itemLabel="#{globalResources.Code}" itemValue="false"/>
        <f:selectItem itemLabel="#{globalResources.name}" itemValue="true"/>
      </t:selectOneRadio>
    </t:panelGrid>
    <t:panelGrid id="textPanel" align="center"
                 dir="#{shared_util.pageDirection}">
      <t:panelGroup>
        <t:inputText maxlength="250" forceId="true" styleClass="textboxlarge"
                     id="searchQuery" value="#{pageBeanName.searchQuery}"/>
        <c2:lengthValidator min="1" componentToValidate="searchQuery"
                            errorMessage="#{globalResources.missingField}"
                            display="dynamic" highlight="false" style="errMsg"
                            active="#{pageBeanName.simpleSearchFlag}"
                            uniqueId="lengthValidatorSearchID"/>
      </t:panelGroup>
    </t:panelGrid>
  </t:panelGroup>--%>
  <t:panelGrid id="messagePanel" columns="1" align="center" cellpadding="0" cellspacing="0" dir="#{shared_util.pageDirection}">
        <t:outputText forceId="true" id="errorMessage" value=" " styleClass="errMsg2" style="font-size:0px"/>
    </t:panelGrid>
    
  <t:panelGroup id="simplesearchArea"   rendered="#{pageBeanName.simpleSearchFlag}" >
    <t:panelGrid columns="1" align="center">
        <t:selectOneRadio id="radioBtn" styleClass="divtext" forceId="true" onclick="toggleRadio(this);" 
                value="#{pageBeanName.stringSearchType}" onblur="document.getElementById('searchString').focus();">
                   <f:selectItem itemLabel="#{globalResources.Code}"  itemValue="false" />                   
                   <f:selectItem itemLabel="#{globalResources.SearchName}" itemValue="true"/>
      
    </t:selectOneRadio>   
    </t:panelGrid>
         <t:panelGroup>      
         <t:panelGrid align="center">
         <t:inputText  id="searchString"  forceId="true" value="#{pageBeanName.searchQuery}" styleClass="textboxlarge"/> 
             </t:panelGrid>
         </t:panelGroup>
     
   </t:panelGroup> 
  
  <html:br/>
  
  <t:panelGrid width="100%" rendered="#{!pageBeanName.simpleSearchFlag}">
    <t:panelGroup rendered="#{!pageBeanName.simpleSearchFlag}">
      <t:panelGrid id="govFlagRadioPanel" align="center"
                   dir="#{shared_util.pageDirection}">
        <t:selectOneRadio styleClass="divtext" forceId="true" onblur="document.getElementById('genderTypeList').focus();"
                          id="govFlagradioBtn" value="#{pageBeanName.stringGovFlag}" >
          <f:selectItem itemLabel="#{resourcesBundle.Gov_Flag}"
                        itemValue="#{pageBeanName.govFlag}"/>
          <f:selectItem itemLabel="#{resourcesBundle.NonGov_Flag}"
                        itemValue="#{pageBeanName.nonGovFlag}"/>
          <a4j:support event="onclick"
                       actionListener="#{pageBeanName.updateCategoryList}" reRender="categoryList,ministryList,nationalityTypeListGrid,genderTypeListGrid"/>
        </t:selectOneRadio>
      </t:panelGrid>
      
      <t:panelGrid cellpadding="3px" cellspacing="0px" width="100%"
                   rowClasses="row01,row02" columns="4" align="center">        
        <h:outputText id="categoryLabelSearch"
                      value="#{resourcesBundle.categoryType}" styleClass="divtext"/>
        <t:panelGrid columns="1" align="center">
          <t:selectOneMenu forceId="true" id="categoryList"
                           readonly="#{pageBeanName.stringGovFlag == null || pageBeanName.stringGovFlag ==''}"
                           value="#{pageBeanName.selectedCategory}" styleClass="DropdownboxMedium2" onkeypress="FireButtonClick('myForm:searchBtn');">
            <f:selectItem itemLabel="#{resourcesBundle.select}"
                          itemValue="#{pageBeanName.emptyItemSelection}"/>
            <t:selectItems var="catList" itemLabel="#{catList.name}"
                           itemValue="#{catList.code.key}"
                           value="#{pageBeanName.categoryList}"/>
            <a4j:support event="onchange"
                         actionListener="#{pageBeanName.updateMinistryList}" reRender="ministryList"/>
          </t:selectOneMenu>
        </t:panelGrid>
        <h:outputText id="ministriesLabelSearch"
                      value="#{resourcesBundle.ministry}" styleClass="divtext"/>
        <t:panelGrid columns="1" align="center">
          <t:selectOneMenu forceId="true" id="ministryList"
                           readonly="#{pageBeanName.selectedCategory == null || pageBeanName.selectedCategory ==''}"
                           value="#{pageBeanName.selectedMinistry}" styleClass="DropdownboxMedium2" onkeypress="FireButtonClick('myForm:searchBtn');">
            <f:selectItem itemLabel="#{resourcesBundle.select}"
                          itemValue="#{pageBeanName.emptyItemSelection}"/>
            <t:selectItems var="minList" itemLabel="#{minList.name}"
                           itemValue="#{minList.code.key}" value="#{pageBeanName.ministryList}"/>
          </t:selectOneMenu>
        </t:panelGrid>
        <h:outputText id="genderLabelSearch"
                      value="#{resourcesBundle.gender}" styleClass="divtext"/>
        <t:panelGrid columns="1" align="center" id="genderTypeListGrid" forceId="true">
          <t:selectOneMenu forceId="true" id="genderTypeList"
                            readonly="#{pageBeanName.stringGovFlag == null || pageBeanName.stringGovFlag ==''}"
                           value="#{pageBeanName.selectedGenderType}" styleClass="DropdownboxMedium2" onkeypress="FireButtonClick('myForm:searchBtn');">
            <f:selectItem itemLabel="#{resourcesBundle.select}"
                          itemValue="#{pageBeanName.emptyItemSelection}"/>
            <t:selectItems var="genList" itemLabel="#{genList.name}"
                           itemValue="#{genList.code.key}" value="#{pageBeanName.genderTypeList}"/>
          </t:selectOneMenu>
        </t:panelGrid>
       <%-- <h:outputText id="nationalityLabelSearch"
                      value="#{resourcesBundle.nationalityType}" styleClass="divtext"/>
        <t:panelGrid columns="1" align="center" id="nationalityTypeListGrid" forceId="true">
          <t:selectOneMenu forceId="true" id="nationalityTypeList"
                        readonly="#{pageBeanName.stringGovFlag == null || pageBeanName.stringGovFlag ==''}"
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
        </t:panelGrid>
        --%>
      </t:panelGrid>
    </t:panelGroup>
  </t:panelGrid>

  <t:panelGrid id="buttonsPanel" columns="3" align="center"
               dir="# {shared_util.pageDirection}">
               
    <h:commandButton value="#{globalResources.SearchButton}"
                     action="#{pageBeanName.searchHireProcedures}"
                     onclick="return validatAdvancedSearchCriteria('govFlagradioBtn', 'nationalityTypeList', 'genderTypeList', 'ministryList', 'categoryList', 'errorMessage', '#{pageBeanName.emptyItemSelection}', '#{globalResources.missingField}');"
                     styleClass="cssButtonSmall"
                      rendered="#{!pageBeanName.simpleSearchFlag}"
                      id="searchBtn"/>
      <h:commandButton   rendered="#{pageBeanName.simpleSearchFlag}"                                                
                     value="#{globalResources.SearchButton}"
                     action="#{pageBeanName.searchHireProcedures}"
                     onclick="return checkInput();"
                     styleClass="cssButtonSmall"/>
    <%--<t:commandButton value="#{globalResources.advancedsearch}"
                           styleClass="cssButtonSmall" 
                       action="#{pageBeanName.searchProcedure}"/>--%>
    <a4j:commandButton value="#{globalResources.simplesearch}"
                       rendered="#{!pageBeanName.simpleSearchFlag}"
                       styleClass="cssButtonSmall" oncomplete="foucsSearchFirstTextOnSearchDiv();"
                       action="#{pageBeanName.changeSearchStatus}"
                       reRender="searchArea,scriptGeneratorPanelID"/>
    <t:commandButton value="#{globalResources.back}" forceId="true" id="customSearchBackBtn"
                     styleClass="cssButtonSmall" onblur="foucsSearchFirstTextOnSearchDiv();"
                    action="#{pageBeanName.back}"/>
  </t:panelGrid>
  
  <t:panelGrid columns="1" rendered="#{pageBeanName.simpleSearchFlag}" align="center">
   <t:outputText value="#{globalResources.search_info_message}"
                 styleClass="search_info_message"/>
  </t:panelGrid>
</t:panelGroup>

<f:verbatim>
    <script type="text/javascript">
        function foucsSearchFirstTextOnSearchDiv(){
            if(document.getElementById('searchString') != null){            
                document.getElementById('searchString').focus();
                document.getElementById('searchString').focus();
            }else if(document.getElementById('genderTypeList') != null){            
                document.getElementById('genderTypeList').focus();
            }
        }
    </script>
</f:verbatim>    

<t:saveState value="#{pageBeanName.selectedGenderType}"/>
<t:saveState value="#{pageBeanName.genderTypeList}"/>
<t:saveState value="#{pageBeanName.selectedMinistry}"/>
<t:saveState value="#{pageBeanName.ministryList}"/>
<t:saveState value="#{pageBeanName.selectedCategory}"/>
<t:saveState value="#{pageBeanName.categoryList}"/>
<t:saveState value="#{pageBeanName.simpleSearchFlag}"/>
<t:saveState value="#{pageBeanName.stringSearchType}"/>
<t:saveState value="#{pageBeanName.stringGovFlag}"/>

<script type="text/javascript">
function validatAdvancedSearchCriteria(radioComponentId, dropMenu1ComponentId, dropMenu2ComponentId, dropMenu3ComponentId, dropMenu4ComponentId, errMsgComponentId, virualValue, requiredFieldMsg){
    var radio;
    var dropMenu1Value;
    var dropMenu2Value;
    var dropMenu3Value;
    var dropMenu4Value;
    
    if(document.forms['myForm'] != null){
        radio =document['forms']['myForm'][radioComponentId];
    }else if(document.forms['treeform'] != null){
        radio = document['forms']['treeform'][radioComponentId] ;
    }
        
    if(document.getElementById(errMsgComponentId)!=null)
        document.getElementById(errMsgComponentId).innerHTML = '';
        document.getElementById(errMsgComponentId).style.fontSize='0px';
           
    if(!radio[0].checked && !radio[1].checked) {
        document.getElementById(errMsgComponentId).innerHTML = requiredFieldMsg;
        document.getElementById(errMsgComponentId).style.fontSize='8pt';
        return false;
    }
    
    dropMenu1Value = document.getElementById(dropMenu1ComponentId).value;
    dropMenu2Value = document.getElementById(dropMenu2ComponentId).value;
    dropMenu3Value = document.getElementById(dropMenu3ComponentId).value;
    dropMenu4Value = document.getElementById(dropMenu4ComponentId).value;
    
    if( dropMenu1Value==virualValue && dropMenu2Value==virualValue && dropMenu3Value==virualValue && dropMenu4Value==virualValue ) {
        document.getElementById(errMsgComponentId).innerHTML = requiredFieldMsg;
        document.getElementById(errMsgComponentId).style.fontSize='8pt';
        return false;
    }
    
    return true;
}
</script>
