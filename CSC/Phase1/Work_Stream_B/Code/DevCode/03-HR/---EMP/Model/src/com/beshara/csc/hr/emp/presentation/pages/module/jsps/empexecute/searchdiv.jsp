<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<t:panelGrid columns="1" width="100%">
    <t:panelGrid id="messagePanel" align="center" columns="1" dir="#{shared_util.pageDirection}">
        <t:outputText forceId="true" id="errorMessage" value=" " styleClass="errMsgNoHeight"/>
    </t:panelGrid>
    
    <t:panelGrid id="radioPanel" align="center" columns="1" dir="#{shared_util.pageDirection}">
        <%--t:selectOneRadio id="searchType_Radio" value="#{pageBeanName.searchType}" styleClass="radio1" converter="javax.faces.Integer">
            <f:selectItem itemLabel="#{resourcesBundle.civilid}" itemValue="0" />
            <f:selectItem itemLabel="#{resourcesBundle.employeeName}" itemValue="1" />
        </t:selectOneRadio--%>
        <t:selectOneRadio styleClass="divtext" onblur="settingFoucsAtDivSearch();" forceId="true" id="_radioBtn" value="#{pageBeanName.searchTypeLongVal}" converter="javax.faces.Long">
           <f:selectItem itemLabel="#{resourcesBundle.civilid}" itemValue="#{0}"/>
           <f:selectItem itemLabel="#{resourcesBundle.the_name}" itemValue="#{1}"/>
       </t:selectOneRadio>
       
    </t:panelGrid>
    
    <t:panelGrid id="textPanel" align="center" columns="1" dir="#{shared_util.pageDirection}">
        <t:inputText forceId="true" id="search_first_inputTxt" value="#{pageBeanName.searchQuery}" styleClass="textboxlarge" onkeypress="FireButtonClick('search_btn');"/>
    </t:panelGrid>
    
    <t:panelGrid id="buttonsPanel" columns="2" align="center" dir="#{shared_util.pageDirection}">
        <t:commandButton id="search_btn" forceId="true" value="#{globalResources.SearchButton}" onclick="return checkRadio_CivilId('_radioBtn','search_first_inputTxt','#{globalResources.civil_no_not_less_than_12}');" styleClass="cssButtonSmall" action="#{pageBeanName.search}"/>
        <t:commandButton  forceId="true" id="customSearchBackBtn" onblur="settingFoucsAtDivSearch();"  value="#{globalResources.back}" styleClass="cssButtonSmall" onclick="hideLookUpDiv(window.blocker,window.divSearch,null,null);document.getElementById('searchButton').focus();return false;"/>        
    </t:panelGrid>    

</t:panelGrid>
<f:verbatim> &nbsp &nbsp &nbsp </f:verbatim>
<t:outputText value="#{globalResources.search_info_message}" styleClass="search_info_message" />

<t:saveState value="#{pageBeanName.searchTypeLongVal}"/>
<t:saveState value="#{pageBeanName.searchQuery}"/>