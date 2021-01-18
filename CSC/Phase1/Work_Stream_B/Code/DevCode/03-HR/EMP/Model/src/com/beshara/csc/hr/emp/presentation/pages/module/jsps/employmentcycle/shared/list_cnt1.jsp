<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:div id="lookupAddDiv" forceId="true" style="visibility:visible;">
    <t:panelGroup styleClass="review_divMainContent1" style="visibility:visible; display: block;">
        <t:messages showDetail="true"/>
        <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                <htm:tr>
                    <htm:td width="9">
                        <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                    </htm:td>
                    <htm:td>
                        <t:outputLabel value="#{resourcesBundle.candidate_main_data_label}" styleClass="lable01"/>
                    </htm:td>
                </htm:tr>
                 
                <htm:tr>
                    <htm:td colspan="2" height="1">
                        <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                    </htm:td>
                </htm:tr>
            </htm:table>
        </t:panelGroup>
        <t:panelGrid columns="4" width="100%" border="0" columnClasses="colm1,colm2,colm3,colm4"
                     rowClasses="row02,row01" cellpadding="3" cellspacing="0" forceId="true" id="cnt1Panel">
            <h:outputText value="#{resourcesBundle.candidate_civilId_label}" styleClass="divtext"/>
            <t:panelGroup>
                <t:inputText disabled="true" tabindex="1"
                             onkeydown="onKeyDownFirstElement(event,'candidate_name','backButtonAddDiv')"
                             value="#{pageBeanName.pageDTO.citizensResidentsDTO.code.civilId}" maxlength="12"
                             forceId="true" id="CivilIdAdd" styleClass="textboxmedium"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.candidate_name_label}" styleClass="divtext"/>
            <t:panelGroup>
                <t:inputText disabled="true" tabindex="2" value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}"
                             maxlength="12" forceId="true" id="candidate_name" styleClass="textboxmedium"
                             style="width:220px;"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.candidate_qulification_label}" styleClass="divtext"/>
            <t:panelGroup colspan="4">
                <t:inputText disabled="true" tabindex="3"
                             value="#{pageBeanName.pageDTO.citizensResidentsDTO.personQualificationsDTOList[0].qualificationsDTO.name}"
                             maxlength="12" forceId="true" id="candidate_qulification" styleClass="textboxlarge3"
                             style="width: 645px;"/>
            </t:panelGroup>
            <t:outputLabel value="#{resourcesBundle.ministryFileNo}" styleClass="lable01"/>
            <t:panelGroup>
                <t:panelGroup>
                    <t:inputText id="ministryFileNoAccept" disabled="#{!pageBeanName.configBean.renderMinistryBtnGrp}"
                                 forceId="true" maxlength="20" onblur="trimBorders(ministryFileNoAccept);"
                                 value="#{pageBeanName.pageDTO.ministryFileNo}" styleClass="textbox">
                        <a4j:support event="onchange" actionListener="#{pageBeanName.checkValidFilMinNo}"
                                     reRender="validScriptPanel,minFilNomsgPanel"
                                     rendered="#{pageBeanName.configBean.renderMinistryBtnGrp}"/>
                    </t:inputText>
                    <t:panelGroup forceId="true" id="minFilNomsgPanel">
                        <h:outputText value="*" styleClass="mandatoryAsterisk"
                                      rendered="#{pageBeanName.configBean.renderMinistryBtnGrp}"/>
                        <t:outputText escape="false" value="&lt;br />"/>
                        <t:outputLabel value="#{resourcesBundle.MINISTRY_FILE_NOFound}" forceId="true"
                                       rendered="#{pageBeanName.invalidMinFileNo==true }" id="minFileErr"
                                       styleClass="errMsg"/>
                        <c2:requiredFieldValidator componentToValidate="ministryFileNoAccept"
                                                   errorMessage="#{globalResources.missingField}" display="dynamic"
                                                   active="#{reviewListBean.configBean.renderMinistryBtnGrp}"/>
                    </t:panelGroup>
                </t:panelGroup>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.hireDate}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputCalendar onblur="calcNextRaiseJs();" disabled="#{!pageBeanName.configBean.renderMinistryBtnGrp}"
                                 onchange="calcNextRaiseJs();" popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                                 popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.pageDTO.hireDate}"
                                 id="employees_hireDate1" size="20" maxlength="200" styleClass="textbox"
                                 currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                 renderPopupButtonAsImage="true" title="#{globalResources.inputCalendarHelpText}">
                    <f:converter converterId="SqlDateConverter"/>
                    <%-- a4j:jsFunction name="calcNextRaiseJsFunction"
                         action="#{detailBeanName.calculateNextDateOfRaise}" reRender="nextRaisePnlGrp"/--%>
                    <%-- a4j:support action="#{pageBeanName.calculateNextDateOfRaise}" reRender="nextRaisePnlGrp"
                         event="onchange"/--%>
                </t:inputCalendar>
                <h:outputText value="*" styleClass="mandatoryAsterisk"
                              rendered="#{pageBeanName.configBean.renderMinistryBtnGrp}"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:requiredFieldValidator active="#{reviewListBean.configBean.renderMinistryBtnGrp}"
                                           componentToValidate="employees_hireDate1"
                                           errorMessage="#{globalResources.missingField}" highlight="false"
                                           display="dynamic"/>
                <c2:dateFormatValidator active="#{reviewListBean.configBean.renderMinistryBtnGrp}"
                                        componentToValidate="employees_hireDate1"
                                        errorMessage="#{resourcesBundle.dateFormat}" highlight="false"
                                        display="dynamic"/>
                <%-- c2:compareDateValidator componentToValidate="employees_hireDate1"
                     componentToCompare="employees_start_work_date" operator="before"
                     errorMessage="#{resourcesBundle.hireDatebeforeStartDateErrMsg}" display="dynamic"/--%>
            </t:panelGroup>
            <t:panelGroup rendered="true" colspan="4">
                <h:outputText value="#{resourcesBundle.orderType}" styleClass="divtext"/>
                <t:inputText disabled="true" tabindex="2" value="#{pageBeanName.pageDTO.hireStagesDTO.name}"
                             maxlength="12" forceId="true" id="order_name" styleClass="textboxmedium"
                             style="margin-right: 110px; width:242px;"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.candidate_wrk_center_label}" styleClass="divtext"/>
            <t:panelGroup colspan="4">
                <t:inputText disabled="#{pageBeanName.configBean.disableWorkCenter}"
                             rendered="#{pageBeanName.configBean.disableWorkCenter}" tabindex="4"
                             value="#{pageBeanName.workCenterName}" maxlength="12" forceId="true"
                             id="candidate_wrk_center" styleClass="textboxlarge3" style="width: 645px;"/>
                <t:panelGroup forceId="true" id="WrkCenterPanel"
                              rendered="#{!pageBeanName.configBean.disableWorkCenter}">
                    <t:panelGroup forceId="true" id="WorkCenter_Panel"
                                  rendered="#{!pageBeanName.configBean.disableWorkCenter}">
                        <t:inputText  readonly="true" tabindex="888888888" styleClass="textboxlarge"
                                     forceId="true" id="workCenterCodeText" value="#{pageBeanName.workCenterName}"/>
                        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                        <a4j:commandButton value="#{globalResources.Available}" styleClass="cssButtonSmaller"
                                           reRender="lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,suggestedJobPanelGrp,lov_searchRadioBtn"
                                           action="#{pageBeanName.showListOfValuesDiv}"
                                           oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
                        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                        <f:verbatim>
                            <br/>
                        </f:verbatim>
                        <c2:requiredFieldValidator active="#{!reviewListBean.configBean.disableSuggestedJob}"
                                                   componentToValidate="workCenterCodeText"
                                                   errorMessage="#{globalResources.missingField}" highlight="false"
                                                   display="dynamic"/>
                    </t:panelGroup>
                </t:panelGroup>
            </t:panelGroup>
            <t:panelGroup colspan="4" style="background-color:#ffffff;"
                          rendered="#{pageBeanName.configBean.disableSuggestedJob}">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td style="background: none repeat scroll 0% 0% white;">
                            <t:outputLabel value="#{resourcesBundle.s_arrow}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.candidate_suggestion_job_label}" styleClass="divtext"/>
            <t:panelGroup id="suggestedJobPanelGrp" forceId="true" colspan="4">
                <t:inputText disabled="#{pageBeanName.configBean.disableSuggestedJob}"
                             rendered="#{pageBeanName.configBean.disableSuggestedJob}"
                             value="#{pageBeanName.jobNameForMin}" tabindex="5" maxlength="12" forceId="true"
                             id="candidate_suggestion_job" styleClass="textboxlarge"/>
                <t:panelGroup forceId="true" id="candidate_suggestionPanel"
                              rendered="#{!pageBeanName.configBean.disableSuggestedJob}">
                    <t:inputText disabled="true" tabindex="999999999" styleClass="textboxlarge" forceId="true"
                                 value="#{pageBeanName.jobNameForMin}" id="jobNameForMin"/>
                    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                    <a4j:commandButton styleClass="cssButtonSmaller" disabled="#{empty pageBeanName.workCenterName}"
                                       value="#{globalResources.Available}" action="#{pageBeanName.showJobDiv}"
                                       reRender="lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,suggestedJobPanelGrp,lov_searchRadioBtn,candidate_suggestionPanel"
                                       oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);settingFoucsAtLovDiv();"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator componentToValidate="jobNameForMin"
                                               errorMessage="#{globalResources.missingField}"
                                               active="#{!reviewListBean.configBean.disableSuggestedJob}"
                                               highlight="false" display="dynamic"/>
                </t:panelGroup>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.ministry_notes_label}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:inputTextarea readonly="#{pageBeanName.configBean.disableMinistryNotes}"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.ministryNotes}" tabindex="6"
                                 cols="35" rows="4" forceId="true" style="width:650px; height: 50px;"
                                 id="ministry_notes"/>
            </t:panelGroup>
            <t:panelGroup colspan="4" style="background-color:#ffffff;">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td style="background: none repeat scroll 0% 0% white;">
                            <t:outputLabel value="" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
              <h:outputText value="#{resourcesBundle.previous_choice_dept_notes_label}" styleClass="divtext" rendered="#{(pageBeanName.stageId == '9' || pageBeanName.stageId == '13'  ) && pageBeanName.previousNotes != null}"/>
               <t:panelGroup rendered="#{(pageBeanName.stageId == '9' || pageBeanName.stageId == '13'  )&& pageBeanName.previousNotes != null}" colspan="3">
                    <t:inputTextarea readonly = "true"    tabindex="7"  value="#{pageBeanName.previousNotes}" cols="35"
                                     rows="4" forceId="true" style="width:650px; height: 50px;" id="previousNotes"/>
                </t:panelGroup>
                    <h:outputText value="#{resourcesBundle.previous_choice_dept_notes_label}" styleClass="divtext" rendered="#{pageBeanName.stageId == '10'  && pageBeanName.caderNamePrevious !=null }"/>
            <t:panelGroup colspan="3" rendered="#{pageBeanName.stageId == '10' && pageBeanName.caderNamePrevious !=null }">
                <t:inputTextarea readonly = "true"    tabindex="7"  
                                 value="#{pageBeanName.pageDTO.previousEmpExtraDataValueDTO.selectionDeptNotes}" cols="35"
                                 rows="4" forceId="true" style="width:650px; height: 50px;" id="choice_dept_notes_previous"/>
                                 </t:panelGroup>
            <h:outputText value="#{resourcesBundle.choice_dept_notes_label}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:outputText forceId="true" id="choice_dept_notes_err" value="#{globalResources.missingField}"
                              styleClass="error" style="display:none"/>
                <t:inputTextarea    tabindex="7" disabled="#{pageBeanName.configBean.disableSelectionDeptNotes}"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.selectionDeptNotes}" cols="35"
                                 rows="4" forceId="true" style="width:650px; height: 50px;" id="choice_dept_notes"/>
             
                <t:panelGroup rendered="#{pageBeanName.stageId == '9'}">
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <t:selectBooleanCheckbox value="#{pageBeanName.concederedExper}"></t:selectBooleanCheckbox>
                    <h:outputText value="#{resourcesBundle.emp_concedered_with_exper}" styleClass="divtext"/>
                </t:panelGroup>
            </t:panelGroup>
            /////////////////////////////
            <t:panelGroup colspan="4" style="background-color:#ffffff;"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td style="background: none repeat scroll 0% 0% white;">
                            <t:outputLabel value="#{resourcesBundle.K_arrow}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
            <t:panelGroup id="panel_grp" forceId="true" colspan="4"
                          rendered="#{!pageBeanName.configBean.renderbtnGrp1}">
                <t:panelGrid columns="4" width="100%" border="0" rowClasses="row01,row02" cellpadding="3"
                             cellspacing="0">
                    <h:outputText value="#{resourcesBundle.job_be_from_min}" styleClass="divtext" rendered="#{pageBeanName.configBean.renderJobFromMinCheckBox}"/>
                    <t:panelGroup  colspan="3" rendered="#{pageBeanName.configBean.renderJobFromMinCheckBox}">
                        <t:selectBooleanCheckbox value="#{pageBeanName.jobFromMin}" disabled="true"/>
                    </t:panelGroup>         
                    <h:outputText value="#{resourcesBundle.selected_job_label}" styleClass="divtext"/>
                    <t:panelGroup id="selected_job_panel_grp" forceId="true" colspan="3">
                        <t:inputText disabled="#{pageBeanName.configBean.disableCandidateJob}"
                                     value="#{pageBeanName.pageDTO.jobsDTO.name}"
                                     rendered="#{pageBeanName.configBean.disableCandidateJob}" tabindex="8"
                                     maxlength="12" forceId="true" id="selected_job" styleClass="textboxlarge"/>
                        <t:panelGroup rendered="#{!pageBeanName.configBean.disableCandidateJob}">
                            <t:inputText id="jobkey" value="#{pageBeanName.jobKey}" forceId="true"
                                         onkeypress=" filterByjobkey(event,'jobkey','jobName');"
                                         onchange="filterByjobkey(event);" styleClass="textbox"
                                         style="margin-left: 7px; width:163px;"/>
                            <a4j:jsFunction name="getJobByKey" oncomplete="showvalidate_msg_jobkey('jobName');"
                                            action="#{pageBeanName.findJobName}" id="functionGetJob"
                                            reRender="jobName"/>
                            <t:inputText disabled="true" tabindex="999999999" styleClass="textboxlarge" forceId="true"
                                         id="jobName" value="#{pageBeanName.pageDTO.jobsDTO.name}" style="width:425px"/>
                            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                            <a4j:commandButton styleClass="cssButtonSmaller" value="#{globalResources.Available}"
                                               action="#{pageBeanName.showJobDivCertified}"
                                               reRender="lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,suggestedJobPanelGrp,lov_searchRadioBtn,candidate_suggestionPanel,jobDivPanelGroup,jobkey"
                                               oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);settingFoucsAtLovDiv();"/>
                            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                            <c2:requiredFieldValidator componentToValidate="jobName"
                                                       errorMessage="#{globalResources.missingField}"
                                                       active="#{!reviewListBean.configBean.disableCandidateJob}"
                                                       highlight="false" display="dynamic"/>
                            <t:outputText value="#{resourcesBundle.inavlid_code}" id="testMsg" forceId="true"
                                          style="display:none;" styleClass="mandatoryAsterisk"/>
                        </t:panelGroup>
                    </t:panelGroup>
                    <h:outputText value="#{resourcesBundle.bget_type_label}" styleClass="divtext"/>
                    <t:panelGroup colspan="3">
                        <t:inputText disabled="#{pageBeanName.configBean.disableBudgetType}"
                                     rendered="#{pageBeanName.configBean.disableBudgetType}" tabindex="9"
                                     value="#{pageBeanName.approvedBgtTypeName}" maxlength="12" forceId="true"
                                     id="bget_type" styleClass="textboxlarge"/>
                        <t:panelGroup forceId="true" id="budgetTypePanel"
                                      rendered="#{!pageBeanName.configBean.disableBudgetType}">
                            <t:selectOneMenu forceId="true" id="employees_budgetType" styleClass="textboxlarge"
                                             value="#{pageBeanName.approvedBgtTypeCode}">
                                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                                <t:selectItems value="#{pageBeanName.budgetTypeList}" itemLabel="#{budgetType.name}"
                                               itemValue="#{budgetType.code.key}" var="budgetType"/>
                            </t:selectOneMenu>
                            <h:outputText value="*" styleClass="mandatoryAsterisk"
                                          rendered="#{!pageBeanName.configBean.disableBudgetType}"/>
                            <f:verbatim>
                                <br/>
                            </f:verbatim>
                            <c2:requiredFieldValidator componentToValidate="employees_budgetType"
                                                       errorMessage="#{globalResources.missingField}"
                                                       active="#{!reviewListBean.configBean.disableBudgetType}"
                                                       highlight="false" display="dynamic"/>
                        </t:panelGroup>
                    </t:panelGroup>
                    <h:outputText value="#{resourcesBundle.jobs_arrangement_notes_label}" styleClass="divtext"/>
                    <t:panelGroup colspan="3">
                        <t:inputTextarea readonly="#{pageBeanName.configBean.disableArrangementDept}"
                                         value="#{pageBeanName.pageDTO.empExtraDataValueDTO.arrangmentDeptNotes}"
                                         tabindex="10" cols="35" rows="4" style="width:650px; height: 50px;"
                                         forceId="true" id="jobs_arrangement_notes"/>
                    </t:panelGroup>
                    <t:panelGroup colspan="4" style="background-color:#ffffff;"
                                 rendered="#{pageBeanName.stageId == '10' && pageBeanName.caderNamePrevious !=null }">
                        <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                            <htm:tr>
                                <htm:td width="9">
                                    <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                                </htm:td>
                                <htm:td style="background: none repeat scroll 0% 0% white;">
                                    <t:outputLabel value="#{resourcesBundle.degree_data_previous}" styleClass="lable01"/>
                                </htm:td>
                            </htm:tr>
                             
                            <htm:tr>
                                <htm:td colspan="2" height="1">
                                    <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%"
                                             height="1"/>
                                </htm:td>
                            </htm:tr>
                        </htm:table>
                    </t:panelGroup>
                    <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext" rendered="#{pageBeanName.stageId == '10' && pageBeanName.caderNamePrevious !=null }"/>
                    <t:inputText disabled="true" tabindex="9999999999999" styleClass="textboxmedium"  rendered="#{pageBeanName.stageId == '10' && pageBeanName.caderNamePrevious !=null }"
                                 value="#{pageBeanName.caderNamePrevious}"/>
                    <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"  rendered="#{pageBeanName.stageId == '10' && pageBeanName.groupNamePrevious !=null }"/>
                    <t:inputText disabled="true" value="#{pageBeanName.groupNamePrevious}" styleClass="textboxmedium"  rendered="#{pageBeanName.stageId == '10' && pageBeanName.groupNamePrevious !=null }"
                                 style="width: 225px;"/>
                    <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext"  rendered="#{pageBeanName.stageId == '10' && pageBeanName.degreeNamePrevious !=null }"/>
                    <t:inputText value="#{pageBeanName.degreeNamePrevious}" tabindex="13" maxlength="12" disabled="true"  rendered="#{pageBeanName.stageId == '10' && pageBeanName.degreeNamePrevious !=null }"
                                 styleClass="textboxmedium"/>
                    <t:outputLabel value="#{resourcesBundle.raisesCount}" styleClass="lable01"  rendered="#{pageBeanName.stageId == '10' && pageBeanName.raiseNamePrevious !=null }"/>
                    <t:inputText value="#{pageBeanName.raiseNamePrevious}" tabindex="13" maxlength="12" disabled="true"  rendered="#{pageBeanName.stageId == '10' && pageBeanName.raiseNamePrevious !=null }"
                                 styleClass="textboxmedium" style="width: 225px;"/>
                                 
                          <t:panelGroup colspan="4" style="background-color:#ffffff;"
                                  rendered="#{pageBeanName.configBean.renderSalariesSection}">
                        <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                            <htm:tr>
                                <htm:td width="9">
                                    <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                                </htm:td>
                                <htm:td style="background: none repeat scroll 0% 0% white;">
                                    <t:outputLabel value="#{resourcesBundle.degree_data_arrow}" styleClass="lable01"/>
                                </htm:td>
                            </htm:tr>
                             
                            <htm:tr>
                                <htm:td colspan="2" height="1">
                                    <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%"
                                             height="1"/>
                                </htm:td>
                            </htm:tr>
                        </htm:table>
                    </t:panelGroup>        
                    <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
                    <t:panelGroup rendered="#{pageBeanName.configBean.renderSalariesSection}">
                        <t:inputText disabled="#{pageBeanName.configBean.disableCadre}"
                                     value="#{pageBeanName.caderName}"
                                     rendered="#{pageBeanName.configBean.disableCadre}" tabindex="11" maxlength="12"
                                     forceId="true" id="candidate_cader" styleClass="textboxmedium"/>
                        <t:panelGroup id="employeeCaderPanel" forceId="true"
                                      rendered="#{!pageBeanName.configBean.disableCadre}">
                            <t:inputText disabled="true" tabindex="9999999999999" styleClass="textboxmedium"
                                         forceId="true" id="caderText" value="#{pageBeanName.caderName}"/>
                            <f:verbatim>&nbsp;</f:verbatim>
                            <a4j:commandButton value="#{globalResources.Available}" id="button"
                                               styleClass="cssButtonSmaller"
                                               action="#{reviewListBean.showCaderListOfValuesDiv}"
                                               oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();return false;"
                                               reRender="degreePanel,raisesCount_Id,searchText,cancelsearchpanel,noResult,okPanel,jobs_group,lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn,employeeCaderPanel"/>
                            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                            <f:verbatim>
                                <br/>
                            </f:verbatim>
                            <c2:requiredFieldValidator componentToValidate="caderText"
                                                       active="#{!reviewListBean.configBean.disableSalDegree}"
                                                       errorMessage="#{globalResources.missingField}" highlight="false"
                                                       display="dynamic"/>
                        </t:panelGroup>
                    </t:panelGroup>
                    <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
                    <t:panelGroup rendered="#{pageBeanName.configBean.renderSalariesSection}">
                        <t:panelGroup id="jobs_group" forceId="true">
                            <t:inputText disabled="#{pageBeanName.configBean.disableJobGroup}"
                                         rendered="#{pageBeanName.configBean.disableJobGroup}"
                                         value="#{pageBeanName.groupName}" tabindex="12" maxlength="12" forceId="true"
                                         id="candidate_jobs_group" styleClass="textboxmedium" style="width: 225px;"/>
                            <t:selectOneMenu value="#{pageBeanName.groupCode}"
                                             disabled="#{empty pageBeanName.caderName}" converter="javax.faces.Long"
                                             id="JobGrpMenu" forceId="true" styleClass="DropdownboxMedium2"
                                             rendered="#{!pageBeanName.configBean.disableJobGroup}">
                                <f:selectItem itemValue="#{pageBeanName.virtualLongValue}"
                                              itemLabel="#{resourcesBundle.select}"/>
                                <t:selectItems value="#{pageBeanName.groupList}" itemLabel="#{group.name}"
                                               itemValue="#{group.code.elmguideCode}" var="group"/>
                                <a4j:support reRender="degreePanel,raisesCount_Id,jobs_group"
                                             action="#{pageBeanName.resetDegreeAndRaiseList}" event="onchange"/>
                            </t:selectOneMenu>
                            <t:panelGroup>
                                <h:outputText value="*" styleClass="mandatoryAsterisk"
                                              rendered="#{!pageBeanName.configBean.disableJobGroup}"/>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                                <c2:compareValidator componentToValidate="JobGrpMenu"
                                                     active="#{!reviewListBean.configBean.disableSalDegree}"
                                                     componentToCompare="virtualvalueId2" operator="not"
                                                     errorMessage="#{globalResources.missingField}" highlight="false"
                                                     display="dynamic"/>
                            </t:panelGroup>
                        </t:panelGroup>
                    </t:panelGroup>
                    <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
                    <%-- <t:panelGroup colspan="3" rendered="#{pageBeanName.configBean.renderSalariesSection}">
                         <t:inputText disabled="#{pageBeanName.configBean.disableSalDegree}"
                         rendered="#{pageBeanName.configBean.disableSalDegree}" value="#{pageBeanName.raiseName}"
                         tabindex="13" maxlength="12" forceId="true" id="candidate_sal_degree"
                         styleClass="textboxmedium" /> <t:panelGroup forceId="true" id="degreePanel"
                         rendered="#{!pageBeanName.configBean.disableSalDegree}"> <t:inputText forceId="true"
                         id="employees_degree" styleClass="textboxmedium" value="#{pageBeanName.raiseName}"
                         disabled="true"/> <f:verbatim>&nbsp;</f:verbatim> <a4j:commandButton value="..."
                         disabled="#{empty pageBeanName.groupCode || pageBeanName.groupCode == virtualLongValue}"
                         styleClass="cssButtonSmaller" action="#{pageBeanName.OpenTreeDiv}"
                         oncomplete="changeVisibilityDiv(window.blocker,window.divTree);settingFoucsAtTreeDiv();return
                         false;"
                         reRender="searchText,cancelsearchpanel,noResult,treeDivPanel,radioTreeDivPanel11,okPanel" />
                         <t:panelGroup> <h:outputText value="*" styleClass="mandatoryAsterisk" />
                         <f:verbatim><br/></f:verbatim> <c2:requiredFieldValidator
                         componentToValidate="employees_degree" active="#{!reviewListBean.configBean.disableSalDegree}"
                         errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic" />--%>
                    <%-- a4j:support action="#{detailBeanName.resetRaiseList}" event="onchange" /--%>
                    <%-- </t:panelGroup> </t:panelGroup> </t:panelGroup>--%>
                    <%-- <h:outputText value="#{resourcesBundle.degree}" styleClass="lable01"/>--%>
                    <t:panelGroup id="degreePanel" forceId="true"
                                  rendered="#{pageBeanName.configBean.renderSalariesSection}">
                        <t:inputText disabled="#{pageBeanName.configBean.disableSalDegree}"
                                     rendered="#{pageBeanName.configBean.disableSalDegree}"
                                     value="#{pageBeanName.degreeName}" tabindex="13" maxlength="12" forceId="true"
                                     id="candidate_sal_degree2" styleClass="textboxmedium"/>
                        <t:selectOneMenu forceId="true"
                                         disabled="#{empty pageBeanName.groupCode || pageBeanName.groupCode == virtualLongValue}"
                                         id="employees_degree" styleClass="DropdownboxMedium2"
                                         value="#{pageBeanName.degreeCode}" converter="javax.faces.Long"
                                         rendered="#{!pageBeanName.configBean.disableSalDegree}">
                            <f:selectItem itemValue="#{pageBeanName.virtualLongValue}"
                                          itemLabel="#{resourcesBundle.select}"/>
                            <t:selectItems value="#{pageBeanName.degreeList}" var="degree" itemLabel="#{degree.name}"
                                           itemValue="#{degree.code.elmguideCode}"/>
                            <a4j:support reRender="degreePanel, raisesCount_Id" event="onchange"
                                         action="#{pageBeanName.resetRaiseList}"/>
                        </t:selectOneMenu>
                        <t:panelGroup rendered="#{!pageBeanName.configBean.disableSalDegree}">
                            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                            <f:verbatim>
                                <br/>
                            </f:verbatim>
                            <h:outputText id="grsCondMsgId" value="#{resourcesBundle.error_degreeForEmp}"
                                          styleClass="errMsg" rendered="#{!pageBeanName.validCond}"/>
                            <c2:compareValidator componentToValidate="employees_degree"
                                                 active="#{!reviewListBean.configBean.disableSalDegree}"
                                                 componentToCompare="virtualvalueId2" operator="not"
                                                 errorMessage="#{globalResources.missingField}" highlight="false"
                                                 display="dynamic"/>
                        </t:panelGroup>
                    </t:panelGroup>
                    <t:outputLabel value="#{resourcesBundle.raisesCount}" styleClass="lable01"
                                   rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
                    <t:panelGroup colspan="3" id="raisesCount_Id" forceId="true"
                                  rendered="#{pageBeanName.configBean.renderSalariesSection}">
                        <t:inputText disabled="#{pageBeanName.configBean.disableSalDegree}"
                                     rendered="#{pageBeanName.configBean.disableSalDegree}"
                                     value="#{pageBeanName.raiseName}" tabindex="13" maxlength="12" forceId="true"
                                     id="candidate_sal_degree" styleClass="textboxmedium" style="width: 225px;"/>
                        <t:selectOneMenu forceId="true" id="raisesCountId"
                                         disabled="#{empty pageBeanName.degreeCode || pageBeanName.degreeCode == virtualLongValue}"
                                         styleClass="DropdownboxMedium2" value="#{pageBeanName.raiseCode}"
                                         rendered="#{!pageBeanName.configBean.disableSalDegree}">
                            <f:selectItem itemValue="#{pageBeanName.virtualConstValue}"
                                          itemLabel="#{resourcesBundle.select}"/>
                            <t:selectItems value="#{pageBeanName.raiseList}" itemLabel="#{raise.countGuide}"
                                           itemValue="#{raise.code.key}" var="raise"/>
                        </t:selectOneMenu>
                        <t:panelGroup rendered="#{!pageBeanName.configBean.disableSalDegree}">
                            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                            <f:verbatim>
                                <br/>
                            </f:verbatim>
                            <c2:compareValidator componentToValidate="raisesCountId"
                                                 active="#{!reviewListBean.configBean.disableSalDegree}"
                                                 componentToCompare="virtualvalueId2" operator="not"
                                                 errorMessage="#{globalResources.missingField}" highlight="false"
                                                 display="dynamic"/>
                        </t:panelGroup>
                    </t:panelGroup>
                    <h:outputText value="#{resourcesBundle.jobs_opinion_notes_label}" styleClass="divtext"
                                  rendered="#{!pageBeanName.configBean.disableOpenionDept}"/>
                    <t:panelGroup colspan="3" rendered="#{!pageBeanName.configBean.disableOpenionDept}">
                        <t:inputTextarea readonly = "true"    tabindex="14" cols="35" rows="4" forceId="true"
                                         style="width:650px; height: 50px;" id="jobs_opinion_notesID"
                                         value="#{pageBeanName.pageDTO.empExtraDataValueDTO.fatwaDeptNotes}"/>
                    </t:panelGroup>
                </t:panelGrid>
            </t:panelGroup>
            <%-- begin cader group--%>
            <t:panelGroup colspan="4" style="background-color:#ffffff;"
                          rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td style="background: none repeat scroll 0% 0% white;">
                            <t:outputLabel value="#{resourcesBundle.degree_data_arrow_suggest}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
            <%-- الدرجة المالية--%>
            <%-- begin first component الدرجة المالية--%>
            <t:panelGroup id="panel_grp1" forceId="true" colspan="4"
                          rendered="#{!pageBeanName.configBean.hideFinancialGroup}">
                <t:panelGrid columns="4" width="100%" border="0" rowClasses="row01,row02" cellpadding="3"
                             cellspacing="0">
                    <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}"/>
                    <t:panelGroup rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}">
                        <t:inputText disabled="true" value="#{pageBeanName.suggestedCaderName}" forceId="true"
                                     id="candidate_caderId" styleClass="textboxmedium"/>
                    </t:panelGroup>
                    <%-- end first component--%>
                    <%-- begin second component--%>
                    <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}"/>
                    <t:panelGroup rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}">
                        <t:inputText disabled="true" value="#{pageBeanName.suggestedGroupName}" forceId="true"
                                     id="candidate_jobs_groupId" styleClass="textboxmedium"/>
                    </t:panelGroup>
                    <%-- end second component--%>
                    <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}"/>
                    <t:inputText disabled="true" value="#{pageBeanName.suggestedDegreeName}" styleClass="textboxmedium"
                                 forceId="true" id="candidate_sal_degreeId"
                                 rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}"/>
                    <%-- begin third component--%>
                    <h:outputText value="#{resourcesBundle.raisesCount}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}"/>
                    <t:inputText disabled="true" value="#{pageBeanName.suggestedRaiseName}" styleClass="textboxmedium"
                                 forceId="true" id="candidate_sal_raisId"
                                 rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}"/>
                    <%-- end third component--%>
                    <%-- <h:outputText value="#{resourcesBundle.jobs_opinion_notes_label}" styleClass="divtext"
                         rendered="#{!pageBeanName.configBean.disableOpenionDept}"/>--%>
                    <%-- <t:panelGroup colspan="3" rendered="#{!pageBeanName.configBean.disableOpenionDept}">
                         <t:inputTextarea readonly = "true"    disabled="#{pageBeanName.configBean.disableOpenionDept}" tabindex="14"
                         cols="35" rows="4" forceId="true" style="width:650px; height: 50px;" id="jobs_opinion_notes"
                         value="#{pageBeanName.pageDTO.empExtraDataValueDTO.fatwaDeptNotes}"/> </t:panelGroup>--%>
                </t:panelGrid>
            </t:panelGroup>
            <%-- نهاية الدرجة الماليه--%>
        </t:panelGrid>
    </t:panelGroup>
     
    <t:panelGroup style="width:99%">
        <t:panelGrid border="0" columns="2" align="center">
            <%-- START -------------------------------------إدارة ال�?توى والرأى ----------------------------------%>
            <%-- Detect_Degree&Job with Experiences (10)--%>
            <t:panelGrid columns="2" border="0" align="center" rendered="#{pageBeanName.configBean.renderbtnGrp2}">
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="15"
                                 action="#{pageBeanName.biznasActions.approveRequestAction}"
                                 onclick="return validatemyForm();block();"
                                 rendered="#{( !pageBeanName.pageDTO.hasExperience &&  pageBeanName.stageId == pageBeanName.managedConstantsBean.HIRE_STAGE_JOB_NAME_ACCEPTRD)|| pageBeanName.stageId == pageBeanName.managedConstantsBean.HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED}"
                                 value="#{resourcesBundle.approve_sal_degree_btn}"/>
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="16"
                                 action="#{pageBeanName.biznasActions.replyRequestForUpdateDataAction}"
                                 value="#{resourcesBundle.transefer_request_choice_btn1}"
                                 rendered="#{( !pageBeanName.pageDTO.hasExperience &&  pageBeanName.stageId == pageBeanName.managedConstantsBean.HIRE_STAGE_JOB_NAME_ACCEPTRD)|| pageBeanName.stageId == pageBeanName.managedConstantsBean.HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED}"
                                 onclick="return validateFirstCase();"/>
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="17"
                                 action="#{pageBeanName.biznasActions.transeferForFatwaDeptAction2}"
                                 rendered="#{pageBeanName.pageDTO.hasExperience && pageBeanName.stageId == pageBeanName.managedConstantsBean.HIRE_STAGE_FIN_DEGREE_REQUIRED}"
                                 value="#{resourcesBundle.approve_sal_degree_btn}"
                                 onclick="return validatemyForm();block();"/>
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="18"
                                 action="#{pageBeanName.biznasActions.replyRequestForUpdateDataForFatwaDeptAction}"
                                 rendered="#{pageBeanName.pageDTO.hasExperience && pageBeanName.stageId == pageBeanName.managedConstantsBean.HIRE_STAGE_FIN_DEGREE_REQUIRED}"
                                 value="#{resourcesBundle.transefer_request_choice_btn1}" onclick="block();"/>
            </t:panelGrid>
            <%-- END -------------------------------------إدارة ال�?توى والرأى ----------------------------------%>
            <%-- START ------------------------------------- إدارة الاختيار ----------------------------------%>
            <%-- review_Degree&Job_inprogress(9)--%>
            <t:panelGrid columns="3" border="0" align="center" rendered="#{pageBeanName.configBean.renderbtnGrp1}">
                <t:commandButton styleClass="cssButton" tabindex="19"
                                 action="#{pageBeanName.biznasActions.transferForJobArrangmentAction}"
                                 value="#{resourcesBundle.transefer_request_arrangment_btn}"
                                 rendered="#{pageBeanName.stageId == pageBeanName.managedConstantsBean.HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT || pageBeanName.stageId == pageBeanName.managedConstantsBean.HIRE_STAGE_COMPLETING_JOB_NAME}"
                                 onclick="block();"/>
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="20"
                                 action="#{pageBeanName.biznasActions.replyRequestForUpdateDataAction}"
                                 value="#{resourcesBundle.transefer_request_ministry_btn}"
                                 onclick="return validateFirstCase();"/>
            </t:panelGrid>
            <%-- Detect_Degree&Job with no Experiences (10)--%>
            <t:panelGrid columns="3" border="0" align="center"
                         rendered="#{pageBeanName.configBean.renderOpenionDeptBtnGrp}">
                <t:commandButton id="xyz" styleClass="cssButtonSmall" tabindex="21"
                                 action="#{pageBeanName.biznasActions.saveSalaryDegreeActionWithoutExp}"
                                 onclick="return validatemyForm();block();"
                                 value="#{globalResources.SaveButton}"/>
                                 
             <t:panelGrid columns="1" border="0" align="center" >
                <t:commandButton styleClass="cssButtonMedium" style="width: 145px;"
                                 action="#{pageBeanName.biznasActions.forwardToJobArrangmentAction}"
                                value="#{resourcesBundle.go_to_job_arrange}" onclick="block();"/>
            </t:panelGrid>                
            
                <t:commandButton styleClass="cssButtonMedium" tabindex="22"
                                 action="#{pageBeanName.biznasActions.replyRequestForUpdateDataAction}"
                                 value="#{resourcesBundle.transefer_request_ministry_btn}"
                                 onclick="block();"/>
            </t:panelGrid>
            <%-- Accept_Degree&Job_inprogress(13)--%>
            <t:panelGrid columns="4" border="0" align="center" rendered="#{pageBeanName.configBean.renderbtnGrp3}">
                <t:commandButton styleClass="cssButtonMedium" tabindex="23"
                                 action="#{pageBeanName.biznasActions.approveRequestAction}"
                                 onclick="return validatemyForm();block();"
                                 value="#{resourcesBundle.approve_sal_degree_btnChoice}"/>
              <t:panelGrid columns="1" border="0" align="center" >
                <t:commandButton styleClass="cssButtonMedium" style="width: 145px;"
                                 action="#{pageBeanName.biznasActions.forwardToJobArrangmentAction}"
                                value="#{resourcesBundle.go_to_job_arrange}" onclick="block();"/>
            </t:panelGrid>          
                 <t:panelGrid columns="1" border="0" align="center" >
                <t:commandButton styleClass="cssButtonMedium" style="width: 145px;" rendered="#{pageBeanName.hasExperience}"
                                 action="#{pageBeanName.biznasActions.forwardToFatwaAction}"
                                value="#{resourcesBundle.go_to_Fatwa}" onclick="block();"/>
            </t:panelGrid>          
                <t:commandButton styleClass="cssButtonMedium" tabindex="24"
                                 action="#{pageBeanName.biznasActions.replyRequestForUpdateDataAction}"
                                 value="#{resourcesBundle.transefer_request_ministry_btn}"
                                 onclick="block();"/>
            </t:panelGrid>
            <%-- Request response has been(14 16)--%>
            <t:panelGrid columns="3" border="0" align="center" rendered="#{pageBeanName.configBean.renderbtnGrp4}">
      
                <t:commandButton styleClass="cssButtonMedium" style="width: 145px;"   rendered="#{pageBeanName.pageDTO.hireStagesDTO.code.key == pageBeanName.managedConstantsBean.HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT}"
                                 action="#{pageBeanName.biznasActions.forwardToJobArrangmentAction}"
                                value="#{resourcesBundle.go_to_job_arrange}" onclick="block();"/>
            
                 <t:panelGrid columns="1" border="0" align="center" rendered="#{pageBeanName.pageDTO.hireStagesDTO.code.key == pageBeanName.managedConstantsBean.HIRE_STAGE_REJECTED_BY_FATWA}">
                <t:commandButton styleClass="cssButtonMedium" style="width: 145px;" 
                                 action="#{pageBeanName.biznasActions.forwardToFatwaAction}"
                                value="#{resourcesBundle.go_to_Fatwa}" onclick="block();"/>
            </t:panelGrid>          
                <t:commandButton styleClass="cssButtonMedium" tabindex="25"
                                 action="#{pageBeanName.biznasActions.replyRequestForUpdateDataAction}"
                                 value="#{resourcesBundle.transefer_request_ministry_btn}"
                                 onclick="block();"/>
            </t:panelGrid>
            <%-- END -------------------------------------------- إدارة الاختيار ----------------------------------%>
            <%-- START --------------------------------------------إدارة الترتيب ----------------------------------%>
            <%-- Detect JobName_Inprogress (11)--%>
            <t:panelGrid columns="2" border="0" align="center"
                         rendered="#{pageBeanName.configBean.renderArrangementDeptBtnGrp}">
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="26"
                                 action="#{pageBeanName.biznasActions.approveJobNameAction}"
                                 value="#{resourcesBundle.request_approve_job_name_btn}"
                                 onclick="return validatemyForm();block();"/>
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="27"
                                 action="#{pageBeanName.biznasActions.rejectJobNameAction}"
                                 value="#{resourcesBundle.transefer_request_choice_btn1}" onclick="block();"/>
            </t:panelGrid>
            <%-- END --------------------------------------------إدارة الترتيب ----------------------------------%>
            <%-- جهة عمل مرشخ--%>
            <t:panelGrid columns="2" border="0" align="center"
                         rendered="#{pageBeanName.configBean.renderMinistryBtnGrp}">
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="28"
                                 rendered="#{pageBeanName.stageId != pageBeanName.managedConstantsBean.HIRE_STAGE_REJECTED_BY_DEWAN}"
                                 action="#{pageBeanName.biznasActions.moveRequestToDiwan}"
                                 onclick="return validatemyForm();block();"
                                 value="#{resourcesBundle.forward_request_to_diwan_btn}"/>
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="29"
                                 rendered="#{pageBeanName.stageId == pageBeanName.managedConstantsBean.HIRE_STAGE_REJECTED_BY_DEWAN}"
                                 action="#{pageBeanName.biznasActions.hireStageCompletingJobNameAction}"
                                 onclick="return validatemyForm();block();"
                                 value="#{resourcesBundle.forward_request_to_diwan_btn}"/>
            </t:panelGrid>
            <t:commandButton styleClass="cssButtonSmall" id="backButtonAddDiv" forceId="true" tabindex="30"
                             action="#{pageBeanName.backAction}" value="#{globalResources.back}"/>
        </t:panelGrid>
    </t:panelGroup>
     
    <t:inputHidden value="#{pageBeanName.virtualLongValue}" id="virtualvalueId2" forceId="true"
                   converter="javax.faces.Long"/>
</t:div>
<script type="text/javascript">
  function validateFirstCase() {

      var openionnotes = document.getElementById("choice_dept_notes");
      var openionnoteserr = document.getElementById("choice_dept_notes_err");
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
</script>