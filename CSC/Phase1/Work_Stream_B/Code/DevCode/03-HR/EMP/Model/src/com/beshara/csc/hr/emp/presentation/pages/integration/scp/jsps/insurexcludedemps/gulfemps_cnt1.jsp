<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGrid columns="2" width="100%" forceId="true" id="searchPanel" rowClasses="row01,row02" cellspacing="0">
       
     <t:panelGroup>
        <t:panelGrid columns="2" forceId="true" id="searchPanel1" cellspacing="0" cellpadding="0" width="100%" rowClasses="row02,row01">
        <h:outputLabel value="#{intgBundle.countryName}" styleClass="lable01" style="display: block; width: 21px;" />
        <t:panelGrid columns="2">

         <t:inputText id="countryCode" styleClass="textboxsmall" forceId="true" value="#{pageBeanName.selectedCountryId}" 
                      onKeyPress="filterationInputOnKeyPress(event, this, 'countriesList', 'errorCodeId', onchangeCountry, null);"
                      onblur="filterationInputOnBlur(event, this, 'countriesList', 'errorCodeId', onchangeCountry, null);"
                      onkeyup="enabelIntegerOnly(this);"/>
         
         <t:selectOneMenu forceId="true" id="countriesList" value="#{pageBeanName.selCountry}" styleClass="DropdownboxMedium" 
                          onChange="filterationDropboxOnChange(event, this, 'countryCode', 'errorCodeId', onchangeCountry, null);" style="margin-right:5px;" >
            <f:selectItem itemLabel="#{intgBundle.select}" itemValue="#{pageBeanName.virtualConstValue}"/>
            <t:selectItems  value="#{pageBeanName.countries}" itemValue="#{countriesName.countriesDTO.code.key}" var="countriesName" itemLabel="#{countriesName.countriesDTO.name}"/> 
            
            <a4j:jsFunction action="#{pageBeanName.filterTable}" name="onchangeCountry"
                             reRender="dataT_data_panel,countryCode,dataT_data,scriptGenerator,OperationBar,paging_panel" oncomplete ="hideDetailDivAfterfilter();"/>
        </t:selectOneMenu>
        <t:panelGroup colspan="2">
            <t:outputLabel id="errorCodeId"  value="#{intgBundle.MessageForWrongCode}" forceId = "true" styleClass="validation_error" style="display:none;color: #FF0000;" />
        </t:panelGroup>
        </t:panelGrid>
        
        </t:panelGrid>
   </t:panelGroup>
    
</t:panelGrid>