<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:saveState value="#{pageBeanName.searchHireTypeDTO}"/>
<t:panelGroup id="searchHireTypepanelGroup" forceId="true">
<t:panelGrid columns="1" width="100%" align="center">
    <t:panelGrid columns="4" align="center" width="100%" styleClass="divtext" cellpadding="3" cellspacing="0" rowClasses="row01,row02">
        <!--######### Row 1 #########-->
        <t:outputText value="#{resourcesBundle.typeCode}" styleClass="divtext"/>
        <t:panelGroup>
        <t:inputText id="search_first_inputTxt" forceId="true" value="#{pageBeanName.searchHireTypeDTO.hireTypeCode}" styleClass="textboxsmall" onblur="document.getElementById('hire_type_name_search').focus();" onkeyup="disableCharacters(this)" maxlength="3"/>
            <f:verbatim><br/></f:verbatim>
            <c2:regularExpressionValidator componentToValidate="search_first_inputTxt" pattern="/^[0-9]*$/" display="dynamic" errorMessage="#{globalResources.ValidateNumbers}"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.typeName}" styleClass="divtext"/>
        <t:inputText value="#{pageBeanName.searchHireTypeDTO.hireTypeName}" styleClass="textboxmedium"  id="hire_type_name_search" forceId="true" maxlength="180"/>
        <!--######### Row 2 #########-->
        <t:outputText value="#{resourcesBundle.documentCode}" styleClass="divtext"/>
        <t:panelGroup>
            <t:inputText id="docTypeCode" forceId="true" value="#{pageBeanName.searchHireTypeDTO.docTypeCode}" styleClass="textboxsmall" onkeyup="disableCharacters(this)" maxlength="3"/>
            <f:verbatim><br/></f:verbatim>
            <c2:regularExpressionValidator componentToValidate="docTypeCode" pattern="/^[0-9]*$/" display="dynamic" errorMessage="#{globalResources.ValidateNumbers}"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.documentName}" styleClass="divtext"/>
        <t:inputText value="#{pageBeanName.searchHireTypeDTO.docTypeName}" styleClass="textboxmedium" maxlength="180"/>
    </t:panelGrid>
    <t:panelGrid columns="2" align="center">
        <t:commandButton id="searchhiretype" value="#{globalResources.SearchButton}" action="#{pageBeanName.search}" onclick="return validatemyForm();" styleClass="cssButtonSmall"/>
        
        <t:commandButton forceId="true" id="customSearchBackBtn" onblur="settingFoucsAtDivSearch();" onclick="backSearchJsFunction();  return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
        <a4j:jsFunction name="backSearchJsFunction" action="#{pageBeanName.backAction}" oncomplete="hideLookUpDiv(window.blocker,window.divSearch,'null','null');settingFoucsAtListPage(); " reRender="searchBenDiv,searchHireTypepanelGroup,scriptpanel"/>
    </t:panelGrid>
    <t:panelGrid columns="2" align="center">
        <t:panelGroup style="text-align:center">
            <t:outputText value="#{globalResources.search_info_message}" styleClass="search_info_message"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGrid>
</t:panelGroup>
