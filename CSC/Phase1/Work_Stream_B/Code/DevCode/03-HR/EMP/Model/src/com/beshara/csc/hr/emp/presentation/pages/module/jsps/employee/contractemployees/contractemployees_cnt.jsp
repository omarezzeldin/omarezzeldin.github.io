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
      
        function filterByjobkey1(event, id_text, id_input) {
    var id_text = id_text;
    var id_input = id_input;
    if (document.getElementById("testMsg") != null) {
        document.getElementById("testMsg").style.display = "none";
    }
    if (event.keyCode == 13 || event.keyCode == 9) {
        event.cancelBubble = true;
        event.returnValue = false;
        event.preventDefault();

        if (document.getElementById(id_text).value == null || document.getElementById(id_text).value == "") {
            document.getElementById(id_input).value = "";
        }
        else {
            getJobByKey1();
        }

    }
    return;
}
      
</script>
<t:div id="lookupAddDiv" forceId="true" style="visibility:visible;">
    <t:messages showDetail="true"/>
     
    <t:panelGrid columns="4" width="100%" border="0" rowClasses="row02,row01" cellpadding="3" cellspacing="0"
                 forceId="true" id="cntPanel" style="overflow: auto; display: block; height: 440px;">
        <t:panelGroup colspan="4" style="display: block; width: 870px;">
            <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                <htm:tr>
                    <htm:td width="9">
                        <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                    </htm:td>
                    <htm:td>
                        <t:outputLabel value="#{resourcesBundle.contract_emp_main_data_label}" styleClass="lable01"/>
                    </htm:td>
                </htm:tr>
                 
                <htm:tr>
                    <htm:td colspan="2" height="1">
                        <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
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
        <%--<t:outputLabel value="#{resourcesBundle.contract_emp_ministry_file_no}" styleClass="lable01"/>--%>
        <%--<t:inputText id="ministryFileNoAccept" disabled="true" forceId="true" maxlength="20"
                     value="#{pageBeanName.pageDTO.ministryFileNo}" styleClass="textboxmedium"/>--%>
        <%--<h:outputText value="#{resourcesBundle.contract_emp_ministry_hirde_date}" styleClass="lable01"/>--%>
        <%--<t:inputText disabled="true" value="#{pageBeanName.pageDTO.hireDate}" id="employees_hireDate1" maxlength="10"
                     styleClass="textboxmedium">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputText>--%>
       
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
                     forceId="true" Id="suggestedTotalReward" onkeypress="enabelIntegerOnly(this);"
                     onkeyup="enabelIntegerOnly(this)"
                     disabled="#{pageBeanName.hireStageCode=='9' || pageBeanName.hireStageCode=='10' || pageBeanName.hireStageCode=='11'}"/>
        <t:panelGroup colspan="2"/>
        <t:panelGroup colspan="4" id="hireStage" forceId="true"
                      rendered="#{pageBeanName.hireStageCode!='1' && pageBeanName.hireStageCode!='2'}">
            <h:outputText value="#{resourcesBundle.contract_emp_hireStageName}" styleClass="divtext"/>
            <t:inputText disabled="true" style="margin-right: 128px; width: 607px;"
                         rendered="#{pageBeanName.hireStageCode!='1' && pageBeanName.hireStageCode!='2'}" tabindex="4"
                         value="#{pageBeanName.hireStageName}" maxlength="12" forceId="true" id="candidate_hire_stage"
                         styleClass="textboxLarge3"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.contract_emp_min_job}" styleClass="divtext nowrap_txt"/>
        <t:panelGroup id="suggestedJobPanelGrp" forceId="true" colspan="3">
            <t:inputText id="jobkey" value="#{pageBeanName.jobKey}" forceId="true"
                         onkeypress=" filterByjobkey(event,'jobkey','candidate_suggestion_job');"
                         onchange="filterByjobkey(event);" styleClass="textbox" style="margin-left: 7px; width:160px;"
                         disabled="#{pageBeanName.hireStageCode=='9' || pageBeanName.hireStageCode=='10' || pageBeanName.hireStageCode=='11'}"/>
            <a4j:jsFunction name="getJobByKey" oncomplete="showvalidate_msg_jobkey('candidate_suggestion_job');"
                            action="#{pageBeanName.findJobName}" id="functionGetJob"
                            reRender="candidate_suggestion_job,error_con_job"/>
            <t:inputText disabled="true" value="#{pageBeanName.suggestedJobValue}" tabindex="5" maxlength="12"
                         forceId="true" id="candidate_suggestion_job" styleClass="textboxMedium" style="width:388px;"/>
            <a4j:commandButton styleClass="cssButtonSmaller" value="#{globalResources.Available}"
                               action="#{pageBeanName.showJobDiv}" reRender="customDiv1,error_con_job"
                               oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);settingFoucsAtLovDiv();"
                               disabled="#{pageBeanName.hireStageCode=='9' || pageBeanName.hireStageCode=='10' || pageBeanName.hireStageCode=='11'}"/>
            <t:outputText value="#{resourcesBundle.inavlid_code}" id="testMsg" forceId="true" style="display:none;"
                          styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:panelGroup id="error_con_job">
                <t:outputText value="#{resourcesBundle.error_job_condition}" styleClass="errMsg"
                              id="outputJobConditionErrMsg" forceId="true"
                              rendered="#{pageBeanName.errorJobCondition}"/>
            </t:panelGroup>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.contract_emp_min_notes}" styleClass="divtext"/>
        <t:panelGroup colspan="3">
            <t:inputTextarea value="#{pageBeanName.pageDTO.empExtraDataValueDTO.ministryNotes}" tabindex="6" cols="35"
                             rows="4" forceId="true"
                             disabled="#{pageBeanName.hireStageCode=='9' || pageBeanName.hireStageCode=='10' || pageBeanName.hireStageCode=='11'}"
                             style="width:602px; height: 50px;" id="ministry_notes"/>
        </t:panelGroup>
          <h:outputText value="#{resourcesBundle.job_be_from_min}" styleClass="divtext"/>
                    <t:panelGroup  colspan="3">
                    <t:selectBooleanCheckbox value="#{pageBeanName.jobFromMin}" disabled="true" >
                   </t:selectBooleanCheckbox>
                    </t:panelGroup>
        <t:panelGroup colspan="4" id="candidate_status_panel" forceId="true"
                      rendered="#{pageBeanName.hireStageCode!='1' && pageBeanName.hireStageCode!='2' && !pageBeanName.jobFromMin}">
           
                <h:outputText value="#{resourcesBundle.contract_emp_final_job}" styleClass="divtext"/>
                 <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                <t:inputText  disabled="true" tabindex="4"
                             value="#{pageBeanName.emp_final_job}" maxlength="12" forceId="true"
                             id="candidate_final_job" styleClass="textboxmedium"/>
                
        </t:panelGroup>
         <t:panelGroup colspan="4" id="candidate_status_panel1" forceId="true"
                      rendered="#{pageBeanName.hireStageCode!='1' && pageBeanName.hireStageCode!='2' && pageBeanName.jobFromMin}">
           
                <h:outputText value="#{resourcesBundle.job_from_job_arrangment}" styleClass="divtext"/>
                 <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                <t:inputText  disabled="true" tabindex="4"
                             value="#{pageBeanName.emp_final_job}" maxlength="12" forceId="true"
                             id="candidate_final_job1" styleClass="textboxmedium"/>
                
        </t:panelGroup>
          <t:panelGroup colspan="4" rendered="#{pageBeanName.hireStageCode!='1' && pageBeanName.hireStageCode!='2' && pageBeanName.jobFromMin}">
           <h:outputText value="#{resourcesBundle.contract_emp_final_job}" styleClass="divtext"/>
                     <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                            <t:inputText id="jobkey1" value="#{pageBeanName.jobKey1}" forceId="true"
                                         onkeypress=" filterByjobkey1(event,'jobkey1','jobName1');" styleClass="textbox"
                                         style="margin-left: 7px; width:163px;"/>
                            <a4j:jsFunction name="getJobByKey1" oncomplete="validateJobkey('jobName1');"
                                            action="#{pageBeanName.findJobName1}" id="functionGetJob1"
                                            reRender="testMsg1, outputJobConditionErrMsg1, jobName1,error_job_Condition_Code1"/>
                            <t:inputText disabled="true" tabindex="999999999" styleClass="textboxlarge" forceId="true"
                                         id="jobName1" value="#{pageBeanName.pageDTO.approvedJobsDTO.name}" style="width:425px"/>
                            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                            <a4j:commandButton styleClass="cssButtonSmaller" value="#{globalResources.Available}"  id="jobBTn"
                                               action="#{pageBeanName.showJobDivCertified}"  
                                               reRender="customDiv1,lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,suggestedJobPanelGrp,lov_searchRadioBtn,candidate_suggestionPanel,jobDivPanelGroup,jobkey,error_job_Condition_Code"
                                               oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);settingFoucsAtLovDiv();"/>
                  
                            <t:outputText value="#{resourcesBundle.inavlid_code}" id="testMsg1" forceId="true"
                                          style="display:none;" styleClass="mandatoryAsterisk"/>
                        <t:panelGroup id="error_job_Condition_Code1" forceId="true">
                            <t:outputText value="#{resourcesBundle.error_job_condition}" styleClass="errMsg"
                                          id="outputJobConditionErrMsg1" forceId="true"
                                          rendered="#{pageBeanName.errorJobCondition1}"/>
                        </t:panelGroup>
                        </t:panelGroup>
                         <t:panelGroup colspan="4"  rendered="#{pageBeanName.hireStageCode!='1' && pageBeanName.hireStageCode!='2'}">
                          <h:outputText value="#{resourcesBundle.contract_trateeb_notes}" styleClass="divtext"/>
                <t:inputTextarea disabled="true"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.arrangmentDeptNotes}" tabindex="6"
                                 cols="35" rows="4" forceId="true"
                                 style="width:610px; height: 50px; margin-right: 47px;" id="tarteeb_dept_notes"/>
                          </t:panelGroup>
        <t:panelGroup colspan="4" id="equv_data" forceId="true"
                      rendered="#{pageBeanName.hireStageCode!='1' && pageBeanName.hireStageCode!='2'}">
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
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.acceptedTotalReward}"
                                 forceId="true"/>
                    <h:commandButton value="#{resourcesBundle.allowance_bounses}" id="viewBounsesbtnId" 
                                    disabled="#{pageBeanName.hireStageCode=='9' || pageBeanName.hireStageCode=='10' || pageBeanName.hireStageCode=='11'}"
                                     styleClass="cssButtonSmaller" action="#{pageBeanName.viewBounses}"/>
                </t:panelGroup>
                <t:panelGroup colspan="2"/>
                <h:outputText value="#{resourcesBundle.contract_fatwa_notes}" styleClass="divtext"/>
                <t:panelGroup colspan="3">
                    <t:inputTextarea disabled="true" value="#{pageBeanName.pageDTO.empExtraDataValueDTO.fatwaDeptNotes}"
                                     tabindex="6" cols="35" rows="4" forceId="true" style="width:612px; height: 50px;"
                                     id="fatwa_dept_notes"/>
                </t:panelGroup>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
     
    <t:panelGrid align="center">
        <t:panelGroup id="grpButtons" forceId="true" colspan="3">
            <a4j:commandButton id="btnToDewan" styleClass="cssButtonSmaller"
                               value="#{resourcesBundle.contract_emp_button_ToDewan}" action="#{pageBeanName.goExecute}"
                               rendered="#{pageBeanName.hireStageCode=='1' || pageBeanName.hireStageCode=='2'}"/>
            <a4j:commandButton id="btnSuggToDewan" styleClass="cssButtonSmaller"
                               rendered="#{pageBeanName.hireStageCode=='16' || pageBeanName.hireStageCode=='15'}"
                               value="#{resourcesBundle.contract_emp_button_SuggToDewan}"
                               action="#{pageBeanName.goExecute}"/>
            <a4j:commandButton id="btnSignRequest" styleClass="cssButtonSmaller" oncomplete="changeVisibilityMsg()"
                               rendered="#{pageBeanName.hireStageCode=='15'}" reRender="divMsg"
                               value="#{resourcesBundle.contract_emp_button_SignReq}"
                               action="#{pageBeanName.goSignExecute}"/>
            <t:commandButton styleClass="cssButtonSmall" rendered="#{pageBeanName.hireStageCode=='15'}"
                             action="#{pageBeanName.printAction}" value="#{resourcesBundle.print_btn2}"/>
            <a4j:commandButton id="btnBack" styleClass="cssButtonSmaller"
                               value="#{resourcesBundle.contract_emp_button_Back}" action="#{pageBeanName.Back}"/>
        </t:panelGroup>
    </t:panelGrid>
     <t:panelGroup forceId="true" id="movReuestValues">
        <t:inputHidden forceId="true" id="empRequestFullURLId" value="#{printListBean.fullURL}"/>
        <t:outputText forceId="true" id="empRequestWindowTitleId" value="#{resourcesBundle.print_btn}" style="display:none;"/>
    </t:panelGroup>
</t:div>
