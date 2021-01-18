<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:div id="lookupAddDiv" forceId="true" style="visibility:visible;">
    <t:panelGroup styleClass="review_divMainContent1"
                  style="visibility:visible; display: block; height: 400px !important;">
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
                        <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                    </htm:td>
                </htm:tr>
            </htm:table>
        </t:panelGroup>
        <t:panelGrid columns="4" width="100%" border="0" columnClasses="colm1,colm2,colm3,colm4"
                     rowClasses="row02,row01" cellpadding="3" cellspacing="0" forceId="true" id="cnt1Panel">
            <h:outputText value="#{resourcesBundle.candidate_civilId_label}" styleClass="divtext"/>
            <t:panelGroup>
                <t:inputText disabled="true"  
                             value="#{pageBeanName.pageDTO.citizensResidentsDTO.code.civilId}" maxlength="12"
                             forceId="true" id="CivilIdAdd" styleClass="textboxmedium"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.candidate_name_label}" styleClass="divtext"/>
            <t:panelGroup>
                <t:inputText disabled="true"  value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}"
                             maxlength="12" forceId="true" id="candidate_name" styleClass="textboxmedium"
                             style="width:220px;"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.candidate_qulification_label}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:inputText disabled="true" tabindex="1"
                             value="#{pageBeanName.pageDTO.citizensResidentsDTO.personQualificationsDTOList[0].qualificationsDTO.name}"
                             maxlength="12" forceId="true" id="candidate_qulification" styleClass="textboxlarge3"
                             style="width: 645px;"/>
            </t:panelGroup>
            <t:outputLabel value="#{resourcesBundle.ministryFileNo}" styleClass="lable01"/>
            <t:panelGroup colspan="3">
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
                                                   active="#{fatwaReviewListBean.configBean.renderMinistryBtnGrp}"/>
                    </t:panelGroup>
                </t:panelGroup>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.candidate_wrk_center_label}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:inputText disabled="#{pageBeanName.configBean.disableWorkCenter}"
                             rendered="#{pageBeanName.configBean.disableWorkCenter}" tabindex="2"
                             value="#{pageBeanName.workCenterName}" maxlength="12" forceId="true"
                             id="candidate_wrk_center" styleClass="textboxlarge3" style="width: 645px;"/>
                <t:panelGroup forceId="true" id="WrkCenterPanel"
                              rendered="#{!pageBeanName.configBean.disableWorkCenter}">
                    <t:panelGroup forceId="true" id="WorkCenter_Panel"
                                  rendered="#{!pageBeanName.configBean.disableWorkCenter}">
                        <t:inputText  readonly="true" tabindex="3" styleClass="textboxlarge"
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
                        <c2:requiredFieldValidator active="#{!fatwaReviewListBean.configBean.disableSuggestedJob}"
                                                   componentToValidate="workCenterCodeText"
                                                   errorMessage="#{globalResources.missingField}" highlight="false"
                                                   display="dynamic"/>
                    </t:panelGroup>
                </t:panelGroup>
            </t:panelGroup>
               <%-- begin cader group--%>
            <t:panelGroup colspan="4" style="background-color:#ffffff;"
                          rendered="true">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td style="background: none repeat scroll 0% 0% white;">
                            <t:outputLabel value="#{resourcesBundle.degree_data_arrow_suggest}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
            <%-- begin first component الدرجة المالية--%>
            <t:panelGroup id="panel_grp1" forceId="true" colspan="4"
                          rendered="true">
                <t:panelGrid columns="4" width="100%" border="0" rowClasses="row01,row02" cellpadding="3"
                             cellspacing="0">
                    <t:panelGroup style="display: block; width: 119px;">
                        <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext"
                                      rendered="true"/>
                    </t:panelGroup>
                    <t:panelGroup rendered="true">
                        <t:inputText disabled="true" value="#{pageBeanName.suggestedCaderName}" forceId="true"
                                     id="candidate_caderId" styleClass="textboxmedium"/>
                    </t:panelGroup>
                    <%-- end first component--%>
                    <%-- begin second component--%>
                    <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"
                                  rendered="true"/>
                    <t:panelGroup rendered="true">
                        <t:inputText disabled="true" value="#{pageBeanName.suggestedGroupName}" forceId="true"
                                     id="candidate_jobs_groupId" styleClass="textboxmedium"/>
                    </t:panelGroup>
                    <%-- end second component--%>
                    <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext"
                                  rendered="true"/>
                    <t:inputText disabled="true" value="#{pageBeanName.suggestedDegreeName}" styleClass="textboxmedium"
                                 forceId="true" id="candidate_sal_degreeId"
                                 rendered="true"/>
                    <%-- begin third component--%>
                    <h:outputText value="#{resourcesBundle.raisesCount}" styleClass="divtext"
                                  rendered="true"/>
                    <t:inputText disabled="true" value="#{pageBeanName.suggestedRaiseName}" styleClass="textboxmedium"
                                 forceId="true" id="candidate_sal_raisId"
                                 rendered="true"/>
                    <%-- end third component--%>
                </t:panelGrid>
            </t:panelGroup>
            <%-- نهاية الدرجة الماليه--%>
            <h:outputText value="#{resourcesBundle.ministry_notes_label}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:inputTextarea  readonly="#{pageBeanName.configBean.disableMinistryNotes}"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.ministryNotes}" tabindex="4"
                                 cols="35" rows="4" forceId="true" style="width:650px; height: 50px;"
                                 id="ministry_notes"/>
            </t:panelGroup>
            <%-- start--suggest_Job----%>
            <h:outputText value="#{resourcesBundle.candidate_suggestion_job_label}" styleClass="divtext"/>
            <t:panelGroup id="suggestedJobPanelGrp" forceId="true" colspan="4">
                <t:inputText disabled="#{pageBeanName.configBean.disableSuggestedJob}"
                             rendered="#{pageBeanName.configBean.disableSuggestedJob}"
                             value="#{pageBeanName.jobNameForMin}" tabindex="5" maxlength="12" forceId="true"
                             id="candidate_suggestion_job" styleClass="textboxlarge"/>
                <t:panelGroup forceId="true" id="candidate_suggestionPanel"
                              rendered="#{!pageBeanName.configBean.disableSuggestedJob}">
                    <t:inputText disabled="true" tabindex="6" styleClass="textboxlarge" forceId="true"
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
                                               active="#{!fatwaReviewListBean.configBean.disableSuggestedJob}"
                                               highlight="false" display="dynamic"/>
                </t:panelGroup>
            </t:panelGroup>
            <%-- End--suggest_Job----%>
            <h:outputText value="#{resourcesBundle.job_be_from_min}" styleClass="divtext"/>
            <t:panelGroup  colspan="3">
                <t:selectBooleanCheckbox value="#{pageBeanName.jobFromMin}" disabled="true"/>
            </t:panelGroup>
            <%-- Start--accept_job----%>
            <h:outputText value="#{resourcesBundle.selected_job_label}" styleClass="divtext"
                          rendered="#{pageBeanName.configBean.disableAcceptedJob}"/>
            <t:panelGroup id="acceptJobPanelGrp" forceId="true" colspan="4"
                          rendered="#{pageBeanName.configBean.disableAcceptedJob}">
                <t:inputText disabled="#{pageBeanName.configBean.disableAcceptedJob}"
                             value="#{pageBeanName.pageDTO.jobsDTO.name}" tabindex="7" maxlength="12" forceId="true"
                             id="accepted_Job_Name" styleClass="textboxlarge"/>
            </t:panelGroup>
            <%-- End--accept_job----%>
              <h:outputText value="#{resourcesBundle.choice_dept_notes_label}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:inputTextarea    tabindex="7" readonly="#{pageBeanName.configBean.disableSelectionDeptNotes}"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.selectionDeptNotes}" cols="35"
                                 rows="4" forceId="true" style="width:650px; height: 50px;" id="choice_dept_notes"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.jobs_arrangement_notes_label}"
                          rendered="#{pageBeanName.configBean.disableArrangementDept}" styleClass="divtext"/>
            <t:panelGroup colspan="3" rendered="#{pageBeanName.configBean.disableArrangementDept}">
                <t:inputTextarea  readonly="#{pageBeanName.configBean.disableArrangementDept}"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.arrangmentDeptNotes}" tabindex="8"
                                 cols="35" rows="4" style="width:650px; height: 50px;" forceId="true"
                                 id="jobs_arrangement_notes"/>
            </t:panelGroup>
            <%-- Start--contract_emp_SAL_equv----%>
              <t:panelGroup colspan="4" style="background-color:#ffffff;display: block;" rendered="#{pageBeanName.caderNamePrevious !=null }">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td>
                            <t:outputLabel value="#{resourcesBundle.fatwa_previous_data}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
            <t:panelGroup id="previous_panel_grp" forceId="true" colspan="4" rendered="#{pageBeanName.caderNamePrevious !=null }">
            <t:panelGrid columns="4" width="100%" border="0" rowClasses="row01,row02" cellpadding="3"
                             cellspacing="0">
              <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext" />
                    <t:inputText disabled="true" tabindex="9999999999999" styleClass="textboxmedium" 
                                 value="#{pageBeanName.caderNamePrevious}"/>
                    <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"  />
                    <t:inputText disabled="true" value="#{pageBeanName.groupNamePrevious}" styleClass="textboxmedium"  
                                 style="width: 225px;"/>
                    <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext" />
                    <t:inputText value="#{pageBeanName.degreeNamePrevious}" tabindex="13" maxlength="12" disabled="true"  
                                 styleClass="textboxmedium"/>
                    <t:outputLabel value="#{resourcesBundle.raisesCount}" styleClass="lable01"  />
                    <t:inputText value="#{pageBeanName.raiseNamePrevious}" tabindex="13" maxlength="12" disabled="true" 
                                 styleClass="textboxmedium" style="width: 225px;"/>
         
             <h:outputText value="#{resourcesBundle.jobs_opinion_notes_label}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:inputTextarea  readonly="true"    tabindex="14" cols="35"
                                 rows="4"  style="width:650px; height: 50px;" 
                                 value="#{pageBeanName.pageDTO.previousEmpExtraDataValueDTO.fatwaDeptNotes}"/>
            </t:panelGroup>
               </t:panelGrid>
            </t:panelGroup>
            
            <t:panelGroup colspan="4" style="background-color:#ffffff;display: block;"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td>
                            <t:outputLabel value="#{resourcesBundle.contract_emp_equv_title}" styleClass="lable01"/>
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
            <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
            <t:panelGroup rendered="#{pageBeanName.configBean.renderSalariesSection}">
                <t:inputText disabled="#{pageBeanName.configBean.disableCadre}" value="#{pageBeanName.caderName}"
                             rendered="#{pageBeanName.configBean.disableCadre}" tabindex="9" maxlength="12"
                             forceId="true" id="candidate_cader" styleClass="textboxmedium"/>
                <t:panelGroup id="employeeCaderPanel" forceId="true"
                              rendered="#{!pageBeanName.configBean.disableCadre}">
                    <t:inputText disabled="true"  styleClass="textboxmedium" forceId="true"
                                 id="caderText" value="#{pageBeanName.caderName}"/>
                    <f:verbatim>&nbsp;</f:verbatim>
                    <a4j:commandButton value="#{globalResources.Available}"  tabindex="10"    id="button" styleClass="cssButtonSmaller"
                                       action="#{fatwaReviewListBean.showCRSCaderListOfValuesDiv}"
                                       oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();return false;"
                                       reRender="degreePanel,raisesCount_Id,searchText,cancelsearchpanel,noResult,okPanel,jobs_group,lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn,employeeCaderPanel"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator componentToValidate="caderText"
                                               active="#{!fatwaReviewListBean.configBean.disableSalDegree}"
                                               errorMessage="#{globalResources.missingField}" highlight="false"
                                               display="dynamic"/>
                </t:panelGroup>
            </t:panelGroup>
            <!--jobs_group-->
            <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
            <t:panelGroup rendered="#{pageBeanName.configBean.renderSalariesSection}">
                <t:panelGroup id="jobs_group" forceId="true">
                    <t:inputText disabled="#{pageBeanName.configBean.disableJobGroup}"
                                 rendered="#{pageBeanName.configBean.disableJobGroup}" value="#{pageBeanName.groupName}"
                                 tabindex="11" maxlength="12" forceId="true" id="candidate_jobs_group"
                                 styleClass="textboxmedium" style="width: 225px;"/>
                    <t:selectOneMenu value="#{pageBeanName.groupCode}" disabled="#{empty pageBeanName.caderName}"
                                     converter="javax.faces.Long" id="JobGrpMenu" forceId="true"
                                     styleClass="DropdownboxMedium2"
                                     rendered="#{!pageBeanName.configBean.disableJobGroup}">
                        <f:selectItem itemValue="#{pageBeanName.virtualLongValue}"
                                      itemLabel="#{resourcesBundle.select}"/>
                        <t:selectItems value="#{pageBeanName.groupCRSList}" itemLabel="#{group.name}"
                                       itemValue="#{group.code.elmguideCode}" var="group"/>
                        <a4j:support reRender="degreePanel,raisesCount_Id,jobs_group"
                                     action="#{pageBeanName.resetCRSDegreeAndRaiseList}" event="onchange"/>
                    </t:selectOneMenu>
                    <t:panelGroup>
                        <h:outputText value="*" styleClass="mandatoryAsterisk"
                                      rendered="#{!pageBeanName.configBean.disableJobGroup}"/>
                        <f:verbatim>
                            <br/>
                        </f:verbatim>
                        <c2:compareValidator componentToValidate="JobGrpMenu"
                                             active="#{!fatwaReviewListBean.configBean.disableSalDegree}"
                                             componentToCompare="virtualvalueId2" operator="not"
                                             errorMessage="#{globalResources.missingField}" highlight="false"
                                             display="dynamic"/>
                    </t:panelGroup>
                </t:panelGroup>
            </t:panelGroup>
            <!--SalDegree-->
            <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
            <t:panelGroup id="degreePanel" forceId="true" rendered="#{pageBeanName.configBean.renderSalariesSection}">
                <t:inputText disabled="#{pageBeanName.configBean.disableSalDegree}"
                             rendered="#{pageBeanName.configBean.disableSalDegree}" value="#{pageBeanName.degreeName}"
                             tabindex="12" maxlength="12" forceId="true" id="candidate_sal_degree2"
                             styleClass="textboxmedium"/>
                <t:selectOneMenu forceId="true"
                                 disabled="#{empty pageBeanName.groupCode || pageBeanName.groupCode == virtualLongValue}"
                                 id="employees_degree" styleClass="DropdownboxMedium2"
                                 value="#{pageBeanName.degreeCode}" converter="javax.faces.Long"
                                 rendered="#{!pageBeanName.configBean.disableSalDegree}">
                    <f:selectItem itemValue="#{pageBeanName.virtualLongValue}" itemLabel="#{resourcesBundle.select}"/>
                    <t:selectItems value="#{pageBeanName.degreeCRSList}" var="degree" itemLabel="#{degree.name}"
                                   itemValue="#{degree.code.elmguideCode}"/>
                    <a4j:support reRender="degreePanel, raisesCount_Id" event="onchange" action="#{pageBeanName.onDegreeCRSChange}"/>
                </t:selectOneMenu>
                <t:panelGroup rendered="#{!pageBeanName.configBean.disableSalDegree}">
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <h:outputText id="grsCondMsgId" value="#{resourcesBundle.error_degreeForEmp}"
                                      styleClass="errMsg" rendered="#{!pageBeanName.validCond}"/>
                    <c2:compareValidator componentToValidate="employees_degree"
                                         active="#{!fatwaReviewListBean.configBean.disableSalDegree}"
                                         componentToCompare="virtualvalueId2" operator="not"
                                         errorMessage="#{globalResources.missingField}" highlight="false"
                                         display="dynamic"/>
                </t:panelGroup>
            </t:panelGroup>
            <!--RaiseCount-->
            <t:outputLabel value="#{resourcesBundle.raisesCount}" styleClass="lable01"
                           rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
            <t:panelGroup colspan="3" id="raisesCount_Id" forceId="true"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}">
                <t:inputText disabled="#{pageBeanName.configBean.disableSalDegree}"
                             rendered="#{pageBeanName.configBean.disableSalDegree}" value="#{pageBeanName.raiseName}"
                             tabindex="13" maxlength="12" forceId="true" id="candidate_sal_degree"
                             styleClass="textboxmedium" style="width: 225px;"/>
                <t:selectOneMenu forceId="true" id="raisesCountId"
                                 disabled="#{empty pageBeanName.degreeCode || pageBeanName.degreeCode == virtualLongValue}"
                                 styleClass="DropdownboxMedium2" value="#{pageBeanName.raiseCode}"
                                 rendered="#{!pageBeanName.configBean.disableSalDegree}">
                    <f:selectItem itemValue="#{pageBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.select}"/>
                    <t:selectItems value="#{pageBeanName.raiseCRSList}" itemLabel="#{raise.countGuide}"
                                   itemValue="#{raise.code.key}" var="raise"/>
                </t:selectOneMenu>
                <t:panelGroup rendered="#{!pageBeanName.configBean.disableSalDegree}">
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:compareValidator componentToValidate="raisesCountId"
                                         active="#{!fatwaReviewListBean.configBean.disableSalDegree}"
                                         componentToCompare="virtualvalueId2" operator="not"
                                         errorMessage="#{globalResources.missingField}" highlight="false"
                                         display="dynamic"/>
                </t:panelGroup>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.jobs_opinion_notes_label}" styleClass="divtext"
                          rendered="#{!pageBeanName.configBean.disableOpenionDept}"/>
            <t:panelGroup colspan="3" rendered="#{!pageBeanName.configBean.disableOpenionDept}">
                <t:outputText forceId="true" id="jobs_opinion_notes_err" value="#{globalResources.missingField}"
                              styleClass="error" style="display:none"/>
                <t:inputTextarea    disabled="#!{pageBeanName.configBean.disableOpenionDept}" tabindex="14" cols="35"
                                 rows="4" forceId="true" style="width:650px; height: 50px;" id="jobs_opinion_notes"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.fatwaDeptNotes}"/>
            </t:panelGroup>
        </t:panelGrid> 
        <t:panelGrid border="0" columns="4" align="center">
         
            <t:panelGrid columns="1" border="0" align="center" rendered="#{pageBeanName.configBean.renderbtnGrp2}">
                <t:commandButton styleClass="cssButtonMedium" tabindex="15"
                                 action="#{pageBeanName.biznasActions.approveFinDegreeFatwa}"
                                 value="#{resourcesBundle.approve_sal_degree_btn}"
                                 onclick="return validatemyForm();block();"/>
            </t:panelGrid>
             <t:panelGrid columns="1" border="0" align="center" rendered="#{pageBeanName.configBean.renderbtnGrp2}">
                <t:commandButton styleClass="cssButtonMedium" style="width: 145px;"
                                 action="#{pageBeanName.biznasActions.forwardToJobArrangmentAction}"
                                value="#{resourcesBundle.go_to_job_arrange}" onclick="block();"/>
            </t:panelGrid>
            <t:commandButton styleClass="cssButtonMedium" tabindex="16"
                             action="#{pageBeanName.biznasActions.convertEmpToChoiceDpt}"
                             value="#{resourcesBundle.requestResponseHasBeenToChoiceMang55}"
                             onclick="block();"/>
            <t:commandButton styleClass="cssButtonSmall" id="backButtonAddDiv" forceId="true" tabindex="17"
                             action="#{pageBeanName.backAction}" value="#{globalResources.back}"/>
        </t:panelGrid>
    </t:panelGroup>
     
     
    <t:inputHidden value="#{pageBeanName.virtualLongValue}" id="virtualvalueId2" forceId="true"
                   converter="javax.faces.Long"/>
</t:div>
<script type="text/javascript">
  function validateFirstCase() {

      var openionnotes = document.getElementById("jobs_opinion_notes");
      var openionnoteserr = document.getElementById("jobs_opinion_notes_err");
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
