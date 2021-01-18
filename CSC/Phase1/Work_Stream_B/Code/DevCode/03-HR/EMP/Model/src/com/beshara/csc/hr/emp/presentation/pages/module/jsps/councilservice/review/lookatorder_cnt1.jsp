<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<htm:style>.divContent1Dynamic { max-height: 460px; }</htm:style>
<t:div id="lookupAddDiv" forceId="true" style="visibility:visible;">
    <t:panelGroup styleClass="review_divMainContent1_empCycle" style="visibility:visible; display: block;">
        <t:messages showDetail="true"/>
        <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                <htm:tr>
                    <htm:td width="9">
                        <htm:img src="../../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                    </htm:td>
                    <htm:td>
                        <t:outputLabel value="#{resourcesBundle.candidate_main_data_label}" styleClass="lable01"/>
                    </htm:td>
                </htm:tr>
                 
                <htm:tr>
                    <htm:td colspan="2" height="1">
                        <htm:img src="../../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                    </htm:td>
                </htm:tr>
            </htm:table>
        </t:panelGroup>
        <t:panelGrid columns="4" width="100%" border="0" rowClasses="row02,row01" cellpadding="3" cellspacing="0"
                     forceId="true" id="cnt1Panel">
            <%-- كود المرشح--%>
            <h:outputText value="#{resourcesBundle.candidate_civilId_label}" styleClass="divtext"/>
            <t:panelGroup>
                <t:inputText disabled="true" tabindex="1"
                             onkeydown="onKeyDownFirstElement(event,'candidate_name','backButtonAddDiv')"
                             value="#{pageBeanName.pageDTO.citizensResidentsDTO.code.civilId}" maxlength="12"
                             forceId="true" id="CivilIdAdd" styleClass="textboxmedium"/>
            </t:panelGroup>
            <%-- اسم المرشح--%>
            <h:outputText value="#{resourcesBundle.candidate_name_label}" styleClass="divtext"/>
            <t:panelGroup>
                <t:inputText disabled="true" tabindex="2" value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}"
                             maxlength="12" forceId="true" id="candidate_name" styleClass="textboxmedium"
                             style="width:235px;"/>
            </t:panelGroup>
            <%-- المؤهل--%>
            <h:outputText value="#{resourcesBundle.candidate_qulification_label}" styleClass="divtext"/>
            <t:panelGroup colspan="4">
                <t:inputText disabled="true" tabindex="3"
                             value="#{pageBeanName.pageDTO.citizensResidentsDTO.personQualificationsDTOList[0].qualificationsDTO.name}"
                             maxlength="12" forceId="true" id="candidate_qulification" styleClass="textboxlarge3"
                             style="width: 645px;"/>
            </t:panelGroup>
            <%-- رقم الملف--%>
            <t:outputLabel value="#{resourcesBundle.ministryFileNo}" styleClass="lable01"/>
            <t:panelGroup colspan="3">
                <t:panelGroup>
                    <t:inputText id="ministryFileNoAccept" disabled="true" forceId="true" maxlength="20"
                                 onblur="trimBorders(ministryFileNoAccept);"
                                 value="#{pageBeanName.pageDTO.ministryFileNo}" styleClass="textbox">
                        <a4j:support event="onchange" actionListener="#{pageBeanName.checkValidFilMinNo}"
                                     reRender="validScriptPanel,minFilNomsgPanel"/>
                    </t:inputText>
                </t:panelGroup>
            </t:panelGroup>
            <%-- مركز العمل--%>
            <h:outputText value="#{resourcesBundle.candidate_wrk_center_label}" styleClass="divtext"/>
            <t:panelGroup colspan="4">
                <t:inputText disabled="true" tabindex="4" value="#{pageBeanName.workCenterName}" maxlength="12"
                             forceId="true" id="candidate_wrk_center" styleClass="textboxlarge3" style="width: 645px;"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext"
                          style="display: block; width: 141px;"/>
            <t:panelGroup colspan="1">
                <t:inputText disabled="true" value="#{pageBeanName.suggestedCaderName}" forceId="true"
                             id="candidate_caderId" styleClass="textboxmedium" style="margin-left: 54px;"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"
                          style="display: block; width: 100px;"/>
            <t:panelGroup colspan="1">
                <t:inputText disabled="true" value="#{pageBeanName.suggestedGroupName}" forceId="true"
                             id="candidate_jobs_groupId" styleClass="textboxmedium" style="width:235px;"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext"/>
            <t:panelGroup>
                <t:inputText disabled="true" value="#{pageBeanName.suggestedDegreeName}" styleClass="textboxmedium"
                             forceId="true" id="candidate_sal_degreeId"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.raisesCount}" styleClass="divtext"/>
            <t:panelGroup>
                <t:inputText disabled="true" value="#{pageBeanName.suggestedRaiseName}" styleClass="textboxmedium"
                             style="width:235px;" forceId="true" id="candidate_sal_raisId"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.total_reward_suggested}" styleClass="divtext"/>
            <t:inputText styleClass="textbox" value="#{pageBeanName.pageDTO.empExtraDataValueDTO.suggestedTotalReward}"
                         forceId="true" Id="suggestedTotalReward" disabled="true"/>
            <t:panelGroup colspan="2"/>
            <%-- الوظيفة المقترحة--%>
            <h:outputText value="#{resourcesBundle.candidate_suggestion_job_label}" styleClass="divtext"/>
            <t:panelGroup id="suggestedJobPanelGrp" forceId="true" colspan="4">
                <t:inputText disabled="true" value="#{pageBeanName.jobNameForMin}" tabindex="5" maxlength="12"
                             forceId="true" id="candidate_suggestion_job" styleClass="textboxlarge"/>
            </t:panelGroup>
            <%-- ملاحظات الجهه--%>
            <h:outputText value="#{resourcesBundle.ministry_notes_label}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:inputTextarea  readonly="true"    value="#{pageBeanName.ministryNotes}" tabindex="6" cols="35" rows="4"
                                 forceId="true" style="width:650px; height: 50px;" id="ministry_notes"/>
            </t:panelGroup>
            <t:panelGroup colspan="4" style="background-color:#ffffff;">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td style="background: none repeat scroll 0% 0% white;">
                            <t:outputLabel value="#{resourcesBundle.Data_of_approved_Job}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
            <%-- الوظيفة المعتمده--%>
            <h:outputText value="#{resourcesBundle.selected_job_label}" styleClass="divtext"
                          style="display: block; width: 155px;"/>
            <t:panelGroup id="acceptedJobPanelGrp" forceId="true" colspan="3">
                <t:inputText value="#{pageBeanName.acceptedJobKey}" tabindex="5" forceId="true"
                             onchange="getJobByKey();" id="candidate_accepted_job" styleClass="textbox"/>
                <a4j:jsFunction name="getJobByKey" oncomplete="validateJobkey('acceptjobNameForMin');"
                                action="#{pageBeanName.findJobName}" id="functionGetJob"
                                reRender="testMsg, outputJobConditionErrMsg, acceptedJobPanelGrp, acceptjobNameForMin, candidate_suggestionPanel"/>
                <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                <t:panelGroup forceId="true" id="candidate_suggestionPanel">
                    <t:inputText disabled="true" tabindex="999999999" styleClass="textboxlarge2" forceId="true"
                                 value="#{pageBeanName.acceptedJobName}" id="acceptjobNameForMin"/>
                    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                    <a4j:commandButton styleClass="cssButtonSmaller" value="#{globalResources.Available}"
                                       action="#{pageBeanName.showJobDiv}"
                                       reRender="acceptedJobPanelGrp,candidate_suggestionPanel"
                                       oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator componentToValidate="acceptjobNameForMin"
                                               errorMessage="#{globalResources.missingField}" highlight="false"
                                               display="dynamic"/>
                    <t:outputText value="#{resourcesBundle.error_job_condition}" styleClass="errMsg"
                                  id="outputJobConditionErrMsg" forceId="true"
                                  rendered="#{pageBeanName.errorJobCondition}"/>
                    <t:outputText value="#{resourcesBundle.inavlid_code}" id="testMsg" forceId="true"
                                  style="display:none;" styleClass="mandatoryAsterisk"/>
                </t:panelGroup>
            </t:panelGroup>
            <%-- بيانات ما يوازى الدرجة--%>
            <t:panelGroup colspan="4" style="background-color:#ffffff;display: block;">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td>
                            <t:outputLabel value="#{resourcesBundle.degreeEquivalentData}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
            <!--Cader-->
            <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext"/>
            <t:panelGroup colspan="1" id="employeeCaderPanel" forceId="true">
                <t:inputText disabled="true" tabindex="9999999999999" styleClass="textboxmedium" forceId="true"
                             id="caderText" value="#{pageBeanName.caderName}"/>
                <f:verbatim>&nbsp;</f:verbatim>
                <a4j:commandButton value="#{globalResources.Available}" id="button" styleClass="cssButtonSmaller"
                                   action="#{pageBeanName.showCaderListOfValuesDiv}"
                                   oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();return false;"
                                   reRender="degreePanel,raisesCount_Id,searchText,cancelsearchpanel,noResult,okPanel,jobs_group,lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn,employeeCaderPanel"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:requiredFieldValidator componentToValidate="caderText"
                                           errorMessage="#{globalResources.missingField}" highlight="false"
                                           display="dynamic"/>
            </t:panelGroup>
            <!--jobs_group-->
            <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"/>
            <t:panelGroup id="jobs_group" forceId="true">
                <t:selectOneMenu value="#{pageBeanName.groupCode}" disabled="#{empty pageBeanName.caderName}"
                                 converter="javax.faces.Long" id="JobGrpMenu" forceId="true"
                                 styleClass="DropdownboxMedium2">
                    <f:selectItem itemValue="#{pageBeanName.virtualLongValue}" itemLabel="#{resourcesBundle.select}"/>
                    <t:selectItems value="#{pageBeanName.groupList}" itemLabel="#{group.name}"
                                   itemValue="#{group.code.elmguideCode}" var="group"/>
                    <a4j:support reRender="degreePanel,raisesCount_Id,jobs_group"
                                 action="#{pageBeanName.resetDegreeAndRaiseList}" event="onchange"/>
                </t:selectOneMenu>
                <t:panelGroup>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:compareValidator componentToValidate="JobGrpMenu" componentToCompare="virtualvalueId2"
                                         operator="not" errorMessage="#{globalResources.missingField}" highlight="false"
                                         display="dynamic"/>
                </t:panelGroup>
            </t:panelGroup>
            <!--SalDegree-->
            <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext"/>
            <t:panelGroup id="degreePanel" forceId="true">
                <t:selectOneMenu forceId="true"
                                 disabled="#{empty pageBeanName.groupCode || pageBeanName.groupCode == virtualLongValue}"
                                 id="employees_degree" styleClass="DropdownboxMedium2"
                                 value="#{pageBeanName.degreeCode}" converter="javax.faces.Long">
                    <f:selectItem itemValue="#{pageBeanName.virtualLongValue}" itemLabel="#{resourcesBundle.select}"/>
                    <t:selectItems value="#{pageBeanName.degreeList}" var="degree" itemLabel="#{degree.name}"
                                   itemValue="#{degree.code.elmguideCode}"/>
                    <a4j:support reRender="raisesCount_Id,rewardsPG" event="onchange" action="#{pageBeanName.fillRaiseCount}" />
                </t:selectOneMenu>
                <t:panelGroup>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:compareValidator componentToValidate="employees_degree" componentToCompare="virtualvalueId2"
                                         operator="not" errorMessage="#{globalResources.missingField}" highlight="false"
                                         display="dynamic"/>
                </t:panelGroup>
            </t:panelGroup>
            <!--RaiseCount-->
            <t:outputLabel value="#{resourcesBundle.raisesCount}" styleClass="lable01"/>
            <t:panelGroup colspan="3" id="raisesCount_Id" forceId="true">
                <t:selectOneMenu forceId="true" id="raisesCountId"
                                 disabled="#{empty pageBeanName.degreeCode || pageBeanName.degreeCode == virtualLongValue}"
                                 styleClass="DropdownboxMedium2" value="#{pageBeanName.raiseCode}">
                    <f:selectItem itemValue="#{pageBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.select}"/>
                    <t:selectItems value="#{pageBeanName.raiseList}" itemLabel="#{raise.countGuide}"
                                   itemValue="#{raise.code.key}" var="raise"/>
                    <a4j:support reRender="rewardsPG" event="onchange"
                                 action="#{pageBeanName.fillTotalRewardAccepted}"/>
                </t:selectOneMenu>
                <t:panelGroup>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:compareValidator componentToValidate="raisesCountId" componentToCompare="virtualvalueId2"
                                         operator="not" errorMessage="#{globalResources.missingField}" highlight="false"
                                         display="dynamic"/>
                </t:panelGroup>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.total_reward_accepted}" styleClass="divtext"/>
            <t:panelGroup colspan="3" id="rewardsPG" forceId="true">
                <t:inputText disabled="true" value="#{pageBeanName.totalRewardAccepted}" styleClass="textboxmedium"
                             forceId="true" id="total_reward_acceptedId"/>
                <h:commandButton value="#{resourcesBundle.allowance_bounses}" id="rewardsbtnId"
                                 styleClass="cssButtonSmaller" action="#{pageBeanName.addBounses}"
                                 disabled="#{!pageBeanName.enableAddRewardButton}"/>
            </t:panelGroup>
            <!-- ملاحظات -->
            <h:outputText value="#{resourcesBundle.civilServiceNotes}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:outputText forceId="true" id="jobs_opinion_notes_err" value="#{globalResources.missingField}"
                              styleClass="error" style="display:none"/>
                <t:inputTextarea     tabindex="14" cols="35" rows="4" forceId="true" style="width:650px; height: 50px;"
                                 id="jobs_opinion_notes"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.civilServiceNotes}"/>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
     
    <t:panelGroup style="width:99%">
        <t:panelGrid border="0" columns="3" align="center">
            <%-- Detect JobName_Inprogress (11)--%>
            <t:commandButton styleClass="cssButton" tabindex="11" action="#{pageBeanName.approveJobNameAction}"
                             value="#{resourcesBundle.civilServiceApprove}"
                             onclick="clearValidationMSG();return validatemyForm();block();"/>
            <t:commandButton styleClass="cssButton" tabindex="12" id="rejectBtn"
                             action="#{pageBeanName.rejectJobNameAction}"
                             value="#{resourcesBundle.civilServiceRejection}" onclick="return validateFirstCase();"/>
            <t:commandButton styleClass="cssButton" id="backButtonAddDiv" forceId="true" tabindex="13"
                             action="#{pageBeanName.backAction}" value="#{globalResources.back}"/>
        </t:panelGrid>
    </t:panelGroup>
     
    <t:inputHidden value="#{pageBeanName.virtualLongValue}" id="virtualvalueId2" forceId="true"
                   converter="javax.faces.Long"/>
     <t:inputHidden value="#{pageBeanName.minCode}" id="minCode" forceId="true"
                   converter="javax.faces.Long"/>
</t:div>
<script type="text/javascript">
  function validateFirstCase() {
      var openionnotes = document.getElementById("jobs_opinion_notes");

      var openionnoteserr = document.getElementById("jobs_opinion_notes_err");
      if (openionnotes == null || openionnotes == 'null') {
          openionnoteserr.style.display = "block";
          return false;
      }
      var openionnotesvalue = openionnotes.value;
      if (openionnotesvalue == '' || openionnotesvalue.trim().length == 0) {
          openionnoteserr.style.display = "block";
          return false;
      }
      else {
          openionnoteserr.style.display = "none";
          return true;
      }
  }

  function clearValidationMSG() {
      var output_validation = document.getElementById("outputJobConditionErrMsg");
      var jobKey_output = document.getElementById("testMsg");
      if (output_validation != null && output_validation.value != '') {
          output_validation.style.display = "none";

      }
      if (jobKey_output != null && jobKey_output.value != '') {
          jobKey_output.style.display = "none";

      }
  }

  function validateJobkey(id_input) {
      var condErr = document.getElementById('outputJobConditionErrMsg');
      if (condErr != null && condErr.style.display != 'none') {
          document.getElementById("testMsg").style.display = "none";
      }
      else {
          if (document.getElementById("testMsg") != null) {
              if (document.getElementById(id_input).value == "") {
                  document.getElementById("testMsg").style.display = "block";
              }
              else {
                  document.getElementById("testMsg").style.display = "none";
              }
          }
      }
  }
</script>