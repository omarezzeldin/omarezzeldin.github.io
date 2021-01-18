<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<script type="text/javascript">

  
   function openEmpRequestDetailsWindow() {
   var moduleName ='${shared_util.contextPath}';
          //openGrsIntgNewWindowNew('movRequestFullURLId' ,moduleName, 'movRequestWindowTitleId');
      var url = document.getElementById('empRequestFullURLId').value;
      var newUrl = window.location.protocol + "//" + window.location.host;
         url = newUrl+moduleName+url;
        var windowTitle = document.getElementById('empRequestWindowTitleId').innerHTML;
    var wOpen;
    var sOptions;
    var popupWindowWidth = 632;
    var popupWindowheight = 540;

    sOptions = 'status=no,menubar=no,location=no,scrollbars=no,toolbar=no,resizable=no,addressbar=no,dialog=yes,minimizable=yes,maximizable=no';
    sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
    sOptions = sOptions + ',height=' + (popupWindowheight).toString();
    sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

    globalHTML = "<html><head><title>" + windowTitle + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe style='overflow:hidden'  width=625 height=500 src=\"" + url + "\"/></body></html>";

    wOpen = window.open("", '', sOptions);
    wOpen.document.write(globalHTML);
    wOpen.focus();
    wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);

    return wOpen;
      }
</script>
<t:messages showDetail="true"/>
<t:panelGrid columns="4" width="100%" border="0" rowClasses="row02,row01" cellpadding="3" cellspacing="0" forceId="true"
             id="cntPanel">
    <t:panelGroup colspan="4" style="background-color:#ffffff;">
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                </htm:td>
                <htm:td styleClass="group_title">
                    <t:outputLabel value=" #{resourcesBundle.contract_emp_main_data_label}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
             
            <htm:tr>
                <htm:td colspan="2" height="1">
                    <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                </htm:td>
            </htm:tr>
        </htm:table>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.contract_emp_civilId_label}" styleClass="divtext"/>
    <t:inputText disabled="true" tabindex="1" value="#{pageBeanName.pageDTO.citizensResidentsDTO.code.civilId}"
                 maxlength="12" forceId="true" id="CivilIdAdd" styleClass="textboxmedium"/>
    <h:outputText value="#{resourcesBundle.contract_emp_name_label}" styleClass="divtext"/>
    <t:inputText disabled="true" tabindex="2" value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}"
                 maxlength="12" forceId="true" id="candidate_name" styleClass="textboxmedium"/>
    <h:outputText value="#{resourcesBundle.contract_emp_qual_label}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
        <t:inputText disabled="true" tabindex="3"
                     value="#{pageBeanName.pageDTO.citizensResidentsDTO.personQualificationsDTOList[0].qualificationsDTO.name}"
                     maxlength="12" forceId="true" id="candidate_qulification" styleClass="textboxlarge3"
                     style="width: 604px;"/>
    </t:panelGroup>
    <t:outputLabel value="#{resourcesBundle.contract_emp_ministry_file_no}" styleClass="lable01"/>
    <t:inputText id="ministryFileNoAccept" disabled="true" forceId="true" maxlength="20"
                 value="#{pageBeanName.pageDTO.ministryFileNo}" styleClass="textboxmedium"/>
    <h:outputText value="#{resourcesBundle.contract_emp_ministry_hirde_date}" styleClass="lable01"/>
    <t:inputText disabled="true" value="#{pageBeanName.pageDTO.hireDate}" id="employees_hireDate1" maxlength="10"
                 styleClass="textboxmedium">
        <f:converter converterId="SqlDateConverter"/>
    </t:inputText>
      <h:outputText value="#{resourcesBundle.transfer_to_dewan_Date}" styleClass="lable01"/>
         <t:panelGroup colspan="3">
        <t:inputText disabled="true" value="#{pageBeanName.pageDTO.extraDataValue}"  styleClass="textboxmedium"/>
        </t:panelGroup>
    
    <t:panelGroup colspan="4">
        <h:outputText value="#{resourcesBundle.contract_emp_ministry_work_center}" styleClass="divtext"/>
        <t:inputText disabled="true" style="margin-right: 69px; width: 604px;" tabindex="4"
                     value="#{pageBeanName.workCenterName}" maxlength="12" forceId="true" id="candidate_wrk_center"
                     styleClass="textboxLarge3"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.contract_emp_cader}" styleClass="divtext"/>
    <t:inputText disabled="true" tabindex="4" value="#{pageBeanName.cader}" maxlength="12" forceId="true"
                 id="candidate_cader" styleClass="textboxmedium"/>
    <h:outputText value="#{resourcesBundle.contract_emp_group}" styleClass="divtext"/>
    <t:inputText disabled="true" tabindex="4" value="#{pageBeanName.group}" maxlength="12" forceId="true"
                 id="candidate_group" styleClass="textboxmedium"/>
    <h:outputText value="#{resourcesBundle.contract_emp_degree}" styleClass="divtext"/>
    <t:inputText disabled="true" tabindex="4" value="#{pageBeanName.degree}" maxlength="12" forceId="true"
                 id="candidate_degree" styleClass="textboxmedium"/>
    <h:outputText value="#{resourcesBundle.contract_emp_raise}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
        <t:inputText disabled="true" tabindex="4" value="#{pageBeanName.raise}" maxlength="12" forceId="true"
                     id="candidate_raise" styleClass="textboxmedium"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.total_reward_suggested}" styleClass="divtext nowrap_txt"/>
    <t:inputText styleClass="textbox" value="#{pageBeanName.pageDTO.empExtraDataValueDTO.suggestedTotalReward}"
                 forceId="true" Id="suggestedTotalReward" disabled="#{!pageBeanName.enableSuggestJob}"
                 onkeypress="enabelIntegerOnly(this);" onkeyup="enabelIntegerOnly(this)"/>
    <t:panelGroup colspan="2"/>
    <%-- <t:panelGroup colspan="4" id="hireStage" forceId="true" rendered="#{pageBeanName.hireStageCode!='1' &&
         pageBeanName.hireStageCode!='2'}"> <h:outputText value="#{resourcesBundle.contract_emp_hireStageName}"
         styleClass="divtext" /> <t:inputText disabled="true" style="margin-right: 128px; width: 607px;"
         rendered="#{pageBeanName.hireStageCode!='1' && pageBeanName.hireStageCode!='2'}" tabindex="4"
         value="#{pageBeanName.hireStageName}" maxlength="12" forceId="true" id="candidate_hire_stage"
         styleClass="textboxLarge3" /> </t:panelGroup>--%>
    <h:outputText value="#{resourcesBundle.contract_emp_min_job}" styleClass="divtext nowrap_txt"/>
    <t:panelGroup id="suggestedJobPanelGrp" forceId="true" colspan="3">
        <t:inputText id="jobkey" value="#{pageBeanName.jobKey}" forceId="true"
                     onkeypress=" filterByjobkey(event,'jobkey','candidate_suggestion_job');"
                     onchange="filterByjobkey(event);" styleClass="textbox" style="margin-left: 7px; width:160px;"
                     disabled="#{!pageBeanName.enableSuggestJob}"/>
        <a4j:jsFunction name="getJobByKey" oncomplete="showvalidate_msg_jobkey('candidate_suggestion_job');"
                        action="#{pageBeanName.findJobName}" id="functionGetJob" reRender="error_condition_code,candidate_suggestion_job,suggestedJobPanelGrp"/>
        <t:inputText disabled="true" value="#{pageBeanName.suggestedJobValue}" tabindex="5" maxlength="12"
                     forceId="true" id="candidate_suggestion_job" styleClass="textboxMedium" style="width:388px;"/>
        <a4j:commandButton styleClass="cssButtonSmaller" value="#{globalResources.Available}"
                           action="#{pageBeanName.showJobDiv}"
                           reRender="error_condition_code,lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,suggestedJobPanelGrp,lov_searchRadioBtn"
                           oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);settingFoucsAtLovDiv();"
                           disabled="#{!pageBeanName.enableSuggestJob}"/>
        <t:outputText value="#{resourcesBundle.inavlid_code}" id="testMsg" forceId="true" style="display:none;"
                      styleClass="mandatoryAsterisk"/>
                       <f:verbatim>
                      <br/>
                      </f:verbatim>
            <t:panelGroup  id="error_condition_code"  forceId="true">
          <t:outputText value="#{resourcesBundle.error_job_condition}" styleClass="errMsg" id="outputJobConditionErrMsg"
                          forceId="true" rendered="#{pageBeanName.errorJobCondition}"/>
         
         </t:panelGroup>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.contract_emp_min_notes}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
        <t:inputTextarea value="#{pageBeanName.pageDTO.empExtraDataValueDTO.ministryNotes}" tabindex="6" cols="35"
                         rows="4" forceId="true" disabled="#{!pageBeanName.enableMinistryNotes}"
                         style="width:602px; height: 50px;" id="ministry_notes"/>
    </t:panelGroup>
    <t:panelGroup colspan="4" style="background-color:#ffffff;" rendered="#{pageBeanName.enableAcceptedJob}">
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                </htm:td>
                <htm:td styleClass="group_title">
                    <t:outputLabel value=" #{resourcesBundle.Data_of_approved_Job}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
             
            <htm:tr>
                <htm:td colspan="2" height="1">
                    <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                </htm:td>
            </htm:tr>
        </htm:table>
    </t:panelGroup>
    <t:panelGroup colspan="4" id="candidate_status_panel" forceId="true" rendered="#{pageBeanName.enableAcceptedJob}">
        <h:outputText value="#{resourcesBundle.contract_emp_final_job}" styleClass="divtext"/>
        <t:inputText style="margin-right: 97px;" disabled="true" tabindex="4" value="#{pageBeanName.emp_final_job}"
                     maxlength="12" forceId="true" id="candidate_final_job" styleClass="textboxmedium"/>
    </t:panelGroup>
    <t:panelGroup colspan="4" style="background-color:#ffffff;" rendered="#{pageBeanName.enableAcceptedJob}">
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                </htm:td>
                <htm:td styleClass="group_title">
                    <t:outputLabel value=" #{resourcesBundle.degreeEquivalentData}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
             
            <htm:tr>
                <htm:td colspan="2" height="1">
                    <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                </htm:td>
            </htm:tr>
        </htm:table>
    </t:panelGroup>
    <t:panelGroup colspan="4" id="equv_data" forceId="true" rendered="#{pageBeanName.enableEquvSalData}">
        <t:panelGrid columns="4" width="100%" border="0" rowClasses="row02,row01" cellpadding="3" cellspacing="0">
            <h:outputText value="#{resourcesBundle.contract_emp_cader}" styleClass="divtext"
                          style="display:block; width:150px;"/>
            <t:inputText disabled="true" tabindex="4" value="#{pageBeanName.eqcader}" maxlength="12" forceId="true"
                         id="candidate_equv_cader" styleClass="textboxmedium"/>
            <h:outputText value="#{resourcesBundle.contract_emp_group}" styleClass="divtext"/>
            <t:inputText disabled="true" tabindex="4" value="#{pageBeanName.eqgroup}" maxlength="12" forceId="true"
                         id="candidate_equv_group" styleClass="textboxmedium"/>
            <h:outputText value="#{resourcesBundle.contract_emp_degree}" styleClass="divtext"/>
            <t:inputText disabled="true" tabindex="4" value="#{pageBeanName.eqdegree}" maxlength="12" forceId="true"
                         id="candidate_equv_degree" styleClass="textboxmedium"/>
            <h:outputText value="#{resourcesBundle.contract_emp_raise}" styleClass="divtext"/>
            <t:inputText disabled="true" tabindex="4" value="#{pageBeanName.eqraise}" maxlength="12" forceId="true"
                         id="candidate_equv_raise" styleClass="textboxmedium"/>
            <h:outputText value="#{resourcesBundle.total_reward_accepted}" styleClass="divtext"/>
            <t:panelGroup>
            <t:inputText styleClass="textbox" disabled="true" Id="acceptedTotalReward"
                         value="#{pageBeanName.pageDTO.empExtraDataValueDTO.acceptedTotalReward}" forceId="true"/>
            <h:commandButton value="#{resourcesBundle.allowance_bounses}" id="estanaviewBounsesbtnId" 
                                    disabled="#{!pageBeanName.enableSuggestJob}"
                                     styleClass="cssButtonSmaller" action="#{pageBeanName.viewBounses}"/>
             </t:panelGroup>                        
            <t:panelGroup colspan="2"/>
            <h:outputText value="#{resourcesBundle.civil_Service_Notes}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:inputTextarea value="#{pageBeanName.civilServiceNotes}" tabindex="6" cols="35" rows="4"
                                 forceId="true" disabled="true" style="width:602px; height: 50px;"
                                 id="civil_Service_Notes"/>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<t:panelGrid align="center">
    <t:panelGroup id="grpButtons" forceId="true" colspan="3">
        <t:commandButton id="btnToDewan" styleClass="cssButtonSmaller"
                         value="#{resourcesBundle.contract_emp_button_ToDewan}" action="#{pageBeanName.goExecute}"
                         rendered="#{pageBeanName.enableConvertToDewanBtn}"/>
        <t:commandButton id="btnSuggToDewan" styleClass="cssButtonSmaller"
                         rendered="#{pageBeanName.enableSuggestAndConvertToDewanBtn}"
                         value="#{resourcesBundle.contract_emp_button_SuggToDewan}" action="#{pageBeanName.goExecute}"/>
        <a4j:commandButton id="btnSignRequest" styleClass="cssButtonSmaller"
                           rendered="#{pageBeanName.hireStageCode=='15'}"
                           value="#{resourcesBundle.contract_emp_button_SignReq}"
                           action="#{pageBeanName.goSignExecute}"/>
        <t:commandButton styleClass="cssButtonSmall" rendered="#{pageBeanName.hireStageCode=='15'}"
                             action="#{pageBeanName.printAction}" value="#{resourcesBundle.print_btn2}"/>
        <t:commandButton id="btnBack" styleClass="cssButtonSmaller" value="#{resourcesBundle.contract_emp_button_Back}"
                         action="#{pageBeanName.Back}"/>
    </t:panelGroup>
</t:panelGrid>
   <t:panelGroup forceId="true" id="movReuestValues">
        <t:inputHidden forceId="true" id="empRequestFullURLId" value="#{printListBean.fullURL}"/>
        <t:outputText forceId="true" id="empRequestWindowTitleId" value="#{resourcesBundle.print_btn}" style="display:none;"/>
    </t:panelGroup>
