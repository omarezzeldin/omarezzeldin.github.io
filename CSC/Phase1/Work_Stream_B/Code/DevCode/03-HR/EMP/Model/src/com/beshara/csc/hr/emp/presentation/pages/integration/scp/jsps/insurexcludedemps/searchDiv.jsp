<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%> 
<t:panelGrid columns="1" width="100%">
      
    <t:panelGrid id="messagePanel" align="center" columns="1" dir="#{shared_util.pageDirection}">
        <t:outputText forceId="true" id="errorMessage" value=" " styleClass="errMsgNoHeight"/>
    </t:panelGrid>
    <t:selectOneRadio styleClass="divtext" layout="spread" forceId="true" id="radioBtn"
                      value="#{pageBeanName.searchTypeLongVal}" converter="javax.faces.Long">
        <f:selectItem itemLabel="#{intgBundle.CivilID}" itemValue="#{0}"/>
        <f:selectItem itemLabel="#{globalResources.globalName}" itemValue="#{1}"/>
    </t:selectOneRadio>
    <t:panelGrid id="radioPanel" align="center" columns="2" dir="#{shared_util.pageDirection}" styleClass="divtext">
        <t:radio for="radioBtn" index="0"/>
        <t:radio for="radioBtn" index="1"/>
    </t:panelGrid>
    <t:panelGrid id="textPanel" align="center" columns="1" dir="#{shared_util.pageDirection}">
        <t:inputText forceId="true" id="search_first_inputTxt" value="#{pageBeanName.searchQuery}"
                     styleClass="textboxlarge"/>
    </t:panelGrid>
    <t:panelGrid id="buttonsPanel" columns="2" align="center" dir="#{shared_util.pageDirection}">
        <t:commandButton value="#{globalResources.SearchButton}"
                         onclick="return validateSearchDiv('#{globalResources.missingField}','#{intgBundle.errCivilId}','#{globalResources.SearchMsg}');"
                         styleClass="cssButtonSmall" action="#{pageBeanName.search}"/>
        <t:commandButton forceId="true" id="customSearchBackBtn" value="#{globalResources.back}"
                         styleClass="cssButtonSmall"
                         onclick="hideLookUpDiv(window.blocker,window.divSearch,null,null);return false;"/>
    </t:panelGrid>
</t:panelGrid>
<f:verbatim>&nbsp &nbsp &nbsp</f:verbatim>
<t:outputText value="#{globalResources.search_info_message}" styleClass="search_info_message"/>
<script type="text/javascript">
  function validateSearchDiv(codeLengthErrMsg, civilIdErr, nameLengthErrMsg) {
     try{
      document.getElementById('errorMessage').innerHTML = '';

      if (document.getElementsByName('radioBtn')[0].checked) {
           if (checkLength('search_first_inputTxt', '0')) {
              document.getElementById('errorMessage').innerHTML = codeLengthErrMsg;
              return false;
          }
          checkNumber_Gen(document.getElementById('search_first_inputTxt').value, 'errorMessage');
          if (document.getElementById('errorMessage').innerHTML != '') {
              return false;
          }
          if (!checkLength('search_first_inputTxt', '12')) {
              document.getElementById('errorMessage').innerHTML = civilIdErr;
              return false;
          }
      }
      else {
          if (checkLength('search_first_inputTxt', '0') || checkLength('search_first_inputTxt', '1')) {
              document.getElementById('errorMessage').innerHTML = nameLengthErrMsg;
              return false;
          }
      }
      return true;
      }catch(e){
      alert("Error: "+e);
      }
  }
</script>
