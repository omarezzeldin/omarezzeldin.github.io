<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<%-- ##################START################ Strat of Control Buttons Section #################SHEKA############### --%>

<t:panelGrid columns="1" border="0" width="100%" columnClasses="center">
    <t:panelGroup>
        <a4j:commandButton id="merIntgActivatedConditionWarnOkBtn" value="#{globalResources.ok}"
                           styleClass="cssButtonSmall" rendered="#{conditionActivationHelperBean.warnMode}"
                           action="#{conditionActivationHelperBean.hideActivatedConditionWarning}"
                           reRender="merIntgNavSection,merIntgActivatioConditions,merIntgInactiveConditionsSection"/>
        <%-- <t:commandButton type="button" forceId="true" id="merIntgActivationConditionSaveBtn"
             value="#{merIntgResources.ok_button_title}" styleClass="cssButtonSmall" disabled="#{empty
             conditionActivationHelperBean.selectedDTOS}" rendered="#{!conditionActivationHelperBean.warnMode}"
             onclick="okBtnFn();"/>--%>
        <%-- <a4j:jsFunction name="okBtnFn" oncomplete="doClick('oncompleteBtn' , '');"
             action="#{conditionActivationHelperBean.executeConditionActivation}"
             reRender="divMsg,oncompleteBtn,error_panel,OperationBar" />--%>
             
        <t:commandButton type="button" forceId="true" id="merIntgActivationConditionSaveBtn"
                         value="#{merIntgResources.ok_button_title}" styleClass="cssButtonSmall"
                         disabled="#{empty conditionActivationHelperBean.selectedDTOS}"
                         rendered="#{!conditionActivationHelperBean.warnMode}" onclick="hidConditionActivationIntgDiv();changeVisibilityDiv(window.blocker,window.autoConnectElementDiv);"/>
        <%--<a4j:jsFunction name="okBtnFn" oncomplete=""
                        reRender="autoConnectElementDiv"/>--%>
                        
        <t:commandButton id="backButtonConditionActivationIntgDiv" forceId="true" type="button"
                         value="#{conditionActivationHelperBean.warnMode ?globalResources['cancel'] : globalResources['back']}"
                         styleClass="cssButtonSmall" onclick="hidConditionActivationIntgDiv();"/>
                         
        <%-- <a4j:jsFunction name="merIntgDivBackJS" action="#{conditionActivationHelperBean.resetData}"
             reRender="merConditionActivationIntgDiv"/>--%>
    </t:panelGroup>
</t:panelGrid>
<t:commandButton id="oncompleteBtn" forceId="true" type="button" onclick="#{conditionActivationHelperBean.onCompleteJs}" style="display:none;"/>
<f:verbatim>
<script type="text/javascript">
  function doMerIntgDivBack() {
      hidTreeDiv('add', window.blocker, window.divTree, 'successMsgTreeCorrect');
      merIntgDivBackJS();
      return false;
  }

  //    settingfoucsOnGrsIntgDiv();
  //
  //    function settingfoucsOnGrsIntgDiv() {
  //        var done = false;
  //        if(document.getElementById('grsIntgSearchCondCode') != null){
  //            done = true;
  //            document.getElementById('grsIntgSearchCondCode').focus();
  //            document.getElementById('grsIntgSearchCondCode').focus();
  //        }
  //        if(!done){
  //            document.getElementById('grsIntgDivBackBtn').focus();
  //            document.getElementById('grsIntgDivBackBtn').focus();
  //        }
  //    }
  //    function settingfoucsAfterBackFromMerIntgDiv() {
  //        if (typeof (setFocusAfterBackFromGrsIntgDiv) != 'undefined') {
  //            setFocusAfterBackFromGrsIntgDiv();
  //        }
  //    }
  //    settingfoucsOnJoinConditionDiv();
  //
  //    function settingfoucsOnJoinConditionDiv() {
  //        try{
  //            document.getElementById('grsIntgSearchCondCode').focus();
  //            document.getElementById('grsIntgSearchCondCode').focus();
  //        } catch(e){}
  //    }
</script>
</f:verbatim>
