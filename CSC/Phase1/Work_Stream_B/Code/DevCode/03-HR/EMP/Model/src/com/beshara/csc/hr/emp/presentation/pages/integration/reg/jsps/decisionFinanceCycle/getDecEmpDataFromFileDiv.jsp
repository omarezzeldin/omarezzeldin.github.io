<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<style>
        .fileUploadFakeInputText {
            background: none repeat scroll 0 0 #ffffff;
            border: 0 none;
            border-radius: 5px;
            box-shadow: 0 0 2px #000000;
            color: #1d7cbf;
            font-family: Tahoma;
            font-size: 12px;
            height: 23px;
            left: 0;
            padding-left: 5px;
            padding-right: 5px;
            position: absolute;
            right: 3px;
            top: 1px;
            width: 277px;
            z-index: 3;
        }

        .fileUploadFakeButton {
            right: 285px;
        }

        .fileUploadComponent {
            height: 25px;
            margin-right: 0;
            opacity: 0;
            padding-right: 0;
            width: 360px;
            z-index: 2;
        }
    </style>
    
<t:panelGroup forceId="true" id="divAddLookup">
<%--  <t:outputText forceId="true" id="success"  value="#{globalResources.SuccessAdds}" styleClass="sucessMsg" rendered="#{pageBeanName.success}"/>
  <t:outputText forceId="true" id="error" value="#{globalexceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
  <htm:br rendered="#{pageBeanName.success || pageBeanName.errorMsg}"/> --%>
  <h:outputText id="clientErrorMessage" styleClass="errMsg"/>
  
  <h:panelGrid cellpadding="0" cellspacing="0" columns="5" width="100%">
    <t:outputText value="#{regResources.fileUpload}" styleClass="lable01"/>
    <t:panelGroup id="attachmentPanel" forceId="true" colspan="4" style="display:block;position: relative;min-height: 26px;">
    
        <div style="position: relative;"></div>
        <t:inputFileUpload id="myInputFileUpload" styleClass="fileUploadComponent"
                           onchange="document.getElementById('myInputText').value=this.value;" storage="file"
                           accept="txt/*" style="z-index: 12;" value="#{detailBeanName.uploadedFile}"/>
        <t:inputText id="myInputText" onkeydown="disableEditing(event)" value="#{detailBeanName.fileName}"
                     forceId="true" styleClass="fileUploadFakeInputText" onmouseout="hideTip()" readonly="true"
                     onmouseover="doTooltip(event,'(txt)')" style="right: 10!important; left: auto!important"/><%--value="#{pageBeanName.fakeImageString}"--%>
        <h:commandButton styleClass="fileUploadFakeButton" value="#{regResources.browse}"
                         onclick="return false;" style="left: auto!important; right: 285px!important;"/>
        <t:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>            <br/>        </f:verbatim>
        <t:outputText id="uploading_error" value="#{regResources.uploading_error}" styleClass="errMsg" rendered="#{detailBeanName.uploadingError}"/>
        <c2:customValidator componentToValidate="myInputText" errorMessage="#{globalResources.missingField}" group="uploadedFileGroup"
                           function="validateAttachment" display="dynamic"/>
        <%-- <c:requiredFieldValidator componentToValidate="myInputText" display="dynamic"
             errorMessage="#{globalResources.missingField}" highlight="false"/>--%>
        <%-- ///////////// scanne Startr--%>
        <%--<t:panelGroup style="display:block;position: absolute;left: 290px;top: -3px;">
            <f:verbatim>
                <applet code="com.beshara.infrastructure.scanner.Scanner" archive="${pageBeanName.archivePath}" height="30" width="90" align="top">
                    This browser does not support Applets.
                  
                    <param name="separate_jvm" value="true"
                           style="background: linear-gradient(#72C3E9, #208CBE, #2CA2D8); border-color: #1F87CF !important;"/>
                --%><%--<jsp:params>
                   <jsp:param name="param" value="${testManagedBean.param}"/>
                   <jsp:param name="java_codebase" value="${pageBeanName.archivePath}"/>
             </jsp:params>--%><%--
                </applet>
            </f:verbatim>
            <t:inputHidden id="appletHiddenField" forceId="true" value="#{pageBeanName.myAppletHiddenImg}"/>
            <t:inputHidden id="nodeSelectedForApplet" forceId="true" value="#{pageBeanName.nodeSelectedForApplet}"/>
            <t:inputHidden id="showErrorForNodeSelection" forceId="true"
                           value="#{pageBeanName.showErrorForNodeSelection}"/>
            <t:commandButton style="display:none;" id="appletCButton" forceId="true"
                             action="#{pageBeanName.appletSavingImgs}"/>
        </t:panelGroup>--%>
    </t:panelGroup>

        <t:outputLabel  style="font-size: 4pt;height:3px"/>  

  </h:panelGrid>
  
        <t:panelGrid columns="2" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{detailBeanName.getDecEmpDataFromFile}" 
            onclick="clearMsgs(); return validatemyForm('uploadedFileGroup');"/>
            <t:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();" onclick="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','errorTxt','add');settingFoucsAtListPage();return false;" 
                styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                <%--<a4j:jsFunction name="backJsFunction" action="#{detailBeanName.hideAddDiv}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_first_inputTxt','errorTxt','add');settingFoucsAtListPage(); " reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>--%>
            </t:panelGroup>
        </t:panelGrid>
  
</t:panelGroup>
<script type="text/javascript">
  function clearMsgs() {
      if (document.getElementById('myForm:clientErrorMessage') != null) {
          document.getElementById('myForm:clientErrorMessage').innerHTML = "";
      }

      if (document.getElementById('myForm:uploading_error') != null) {
          document.getElementById('myForm:uploading_error').innerHTML = "";
      }
  }

  function validateAttachment() {
      //alert('validateAttachment');
      var fileUploaderValue = document.getElementById('myInputText').value;
      //var scannerValue = document.getElementById('appletHiddenField').value;&& (scannerValue == null || scannerValue == "")
       
      if ((fileUploaderValue == null || fileUploaderValue == "") ) {
          return false;
      }
      return true;
  }
   
</script>