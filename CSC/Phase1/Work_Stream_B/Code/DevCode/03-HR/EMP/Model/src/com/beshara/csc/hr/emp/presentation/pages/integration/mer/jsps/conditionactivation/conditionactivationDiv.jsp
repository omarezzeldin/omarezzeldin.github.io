<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="merIntgResources"/>
<jsp:include page="/integration/mer/jsps/conditionactivation/autoconnectelementconfirmDiv.jsp"/>  
<t:panelGroup forceId="true" id="merConditionActivationIntgDiv">
    <%-- ##################START################# Strat of Join/Cancel Section ##############SHEKA#####################--%>
    <t:panelGroup forceId="true" id="merIntgActivatioConditions">
        <t:panelGroup forceId="true" id="merIntgWarnSection" rendered="#{conditionActivationHelperBean.warnMode}">
            <t:outputText value="#{merIntgResources[conditionActivationHelperBean.existanceOfActivatedCondtion_key]}"
                          styleClass="msg warning"/>
            <jsp:include page="${conditionActivationHelperBean.activatedConditionPath}" flush="true"/>
        </t:panelGroup>
        <t:panelGroup forceId="true" id="merIntgInactiveConditionsSection"
                      rendered="#{!conditionActivationHelperBean.warnMode}">
            <t:outputText value="#{merIntgResources[conditionActivationHelperBean.inativeConditionsTitle_key]}"
                          styleClass="TitlePage"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:panelGroup id="error_panel" forceId="true">
                <t:outputText value="#{merIntgResources['invalidCondition']}"
                              rendered="#{conditionActivationHelperBean.invalidCondition}" styleClass="errMsg"/>
            </t:panelGroup>
            <jsp:include page="${conditionActivationHelperBean.inactiveconditionsPath}" flush="true"/>
        </t:panelGroup>
        <t:panelGroup forceId="true" id="merIntgNavSection">
            <jsp:include page="${conditionActivationHelperBean.navigationPath}" flush="true"/>
        </t:panelGroup>
    </t:panelGroup>
    <%-- ##################START################ Strat of Hidden Values Section #############SHEKA#####################--%>
    <t:panelGroup forceId="true" id="merIntgHiddenValues">
        <t:inputHidden forceId="true" id="merIntgFullURLId" value="#{conditionActivationHelperBean.fullURL}"/>
        <t:outputText forceId="true" id="merIntgWindowTitleId" value="#{merIntgResources.conditionDetails}"
                      style="display:none;"/>
    </t:panelGroup>
    <t:saveState value="#{conditionActivationHelperBean.warnMode}"/>
    <t:saveState value="#{conditionActivationHelperBean.refTabrecSerial}"/>
    <t:saveState value="#{conditionActivationHelperBean.singleSelection}"/>
    <t:saveState value="#{conditionActivationHelperBean.refTabrecSerial}"/>
    <t:saveState value="#{conditionActivationHelperBean.showRelatedModules}"/>
    <t:saveState value="#{conditionActivationHelperBean.onCompleteJs}"/>
    <t:saveState value="#{conditionActivationHelperBean.invalidCondition}"/>
    <t:saveState value="#{conditionActivationHelperBean.containerBeanName}"/>
    <t:saveState value="#{conditionActivationHelperBean.autoConnectElement}"/>
    <t:saveState value="#{conditionActivationHelperBean.elmguideCode}"/>
    <t:saveState value="#{conditionActivationHelperBean.fullURL}"/>
    
</t:panelGroup>
<f:verbatim>
    <script type="text/javascript">
      function openMerIntgConditionDetailsWindow() {
          var moduleName ='${shared_util.contextPath}';
          openGrsIntgNewWindowNew('merIntgFullURLId', moduleName, 'merIntgWindowTitleId');
      }
      
//      function openMerIntgNewWindow(fullURL, winNameInput) {
//          var url = document.getElementById(fullURL).value;
//
//          var windowTitle = document.getElementById(winNameInput).innerHTML;
//          var wOpen;
//          var sOptions;
//          var popupWindowWidth = 632;
//          var popupWindowheight = 540;
//
//          sOptions = 'status=no,menubar=no,location=no,scrollbars=no,toolbar=no,resizable=no,addressbar=no';
//          sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
//          sOptions = sOptions + ',height=' + (popupWindowheight).toString();
//          sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';
//
//          globalHTML = "<html><head><title>" + windowTitle + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe style='overflow:hidden'  width=625 height=500 src=\"" + url + "\"/></body></html>";
//
//          wOpen = window.open("", '', sOptions);
//          wOpen.document.write(globalHTML);
//          wOpen.focus();
//          wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);
//
//          return wOpen;
//      }
    </script>
</f:verbatim>