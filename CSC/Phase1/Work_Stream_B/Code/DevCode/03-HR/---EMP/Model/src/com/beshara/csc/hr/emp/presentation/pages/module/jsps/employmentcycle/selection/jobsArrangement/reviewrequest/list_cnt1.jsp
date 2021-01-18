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
                             style="width:235px;"/>
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
            ///////////////////////////////////////
            <%-- الدرجة المالية--%>
            <%-- begin first component الدرجة المالية--%>
            <t:panelGroup id="panel_grp1" forceId="true" colspan="4"
                          rendered="#{!pageBeanName.configBean.hideFinancialGroup}">
                <t:panelGrid columns="4" width="100%" border="0" rowClasses="row01,row02" cellpadding="3"
                             cellspacing="0">
                    <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}"
                                  style="display: block; width: 141px;"/>
                    <t:panelGroup rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}">
                        <t:inputText disabled="true" value="#{pageBeanName.suggestedCaderName}" forceId="true"
                                     id="candidate_caderId" styleClass="textboxmedium" style="margin-left: 54px;"/>
                    </t:panelGroup>
                    <%-- end first component--%>
                    <%-- begin second component--%>
                    <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}"
                                  style="display: block; width: 100px;"/>
                    <t:panelGroup rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}">
                        <t:inputText disabled="true" value="#{pageBeanName.suggestedGroupName}" forceId="true"
                                     id="candidate_jobs_groupId" styleClass="textboxmedium" style="width:235px;"/>
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
                                 style="width:235px;" forceId="true" id="candidate_sal_raisId"
                                 rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}"/>
                    <%-- end third component--%>
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
                            <a4j:support reRender="raisesCount_Id" event="onchange"
                                         action="#{pageBeanName.resetRaiseList}"/>
                        </t:selectOneMenu>
                        <t:panelGroup rendered="#{!pageBeanName.configBean.disableSalDegree}">
                            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                            <f:verbatim>
                                <br/>
                            </f:verbatim>
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
                            <t:selectItems value="#{pageBeanName.raiseList}" itemLabel="#{raise.name}"
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
                </t:panelGrid>
            </t:panelGroup>
            <%-- نهاية الدرجة الماليه--%>
            /////////////////////////////////////////
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
                <t:inputTextarea  readonly="#{pageBeanName.configBean.disableMinistryNotes}"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.ministryNotes}" tabindex="6"
                                 cols="35" rows="4" forceId="true" style="width:650px; height: 50px;"
                                 id="ministry_notes"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.jobs_opinion_notes_label}" styleClass="divtext"
                          rendered="#{!pageBeanName.configBean.disableOpenionDept}"/>
            <t:panelGroup colspan="3" rendered="#{!pageBeanName.configBean.disableOpenionDept}">
                <t:inputTextarea  readonly="true"    tabindex="14" cols="35" rows="4" forceId="true"
                                 style="width:650px; height: 50px;" id="jobs_opinion_notes"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.fatwaDeptNotes}"/>
            </t:panelGroup>
            ///////////////////////////// /////////////////////////////
            
               <t:panelGroup colspan="4" style="background-color:#ffffff;" rendered="#{pageBeanName.jobKeyPrevious !=null }">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td style="background: none repeat scroll 0% 0% white;">
                            <t:outputLabel value="#{resourcesBundle.job_data_from_job_arrangement_previous}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
              <t:panelGroup id="previous_panel_grp" forceId="true" colspan="4" rendered="#{pageBeanName.jobKeyPrevious !=null }">
            <t:panelGrid columns="4" width="100%" border="0" rowClasses="row01,row02" cellpadding="3"
                             cellspacing="0">
                <h:outputText value="#{resourcesBundle.selected_job_label}" styleClass="divtext"/>
                <t:panelGroup colspan="3">
                    <t:inputText value="#{pageBeanName.jobKeyPrevious}" styleClass="textbox" disabled="true"
                                 style="margin-left: 7px; width:163px;"/>
                    <t:inputText disabled="true" styleClass="textboxlarge" value="#{pageBeanName.jobNamePrevious}"
                                 style="width:425px"/>
                </t:panelGroup>
                       <h:outputText value="#{resourcesBundle.jobs_arrangement_notes_label}" styleClass="divtext"/>
                        <t:panelGroup colspan="3">
                            <t:inputTextarea  readonly="true"   
                                             value="#{pageBeanName.pageDTO.previousEmpExtraDataValueDTO.arrangmentDeptNotes}"
                                             tabindex="10" cols="35" rows="4" style="width:650px; height: 50px;"
                                             />
                        </t:panelGroup>              
                        </t:panelGrid>
                </t:panelGroup>
            
            <t:panelGroup id="panel_grp" forceId="true" colspan="4"
                          rendered="#{!pageBeanName.configBean.renderbtnGrp1}">
                <t:panelGroup colspan="4" style="background-color:#ffffff;">
                    <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                        <htm:tr>
                            <htm:td width="9">
                                <htm:img src="../../../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                            </htm:td>
                            <htm:td style="background: none repeat scroll 0% 0% white;">
                                <t:outputLabel value="#{resourcesBundle.job_data_from_job_arrangement}" styleClass="lable01"/>
                            </htm:td>
                        </htm:tr>
                         
                        <htm:tr>
                            <htm:td colspan="2" height="1">
                                <htm:img src="../../../../../app/media/images/seprator_group.jpg" width="100%"
                                         height="1"/>
                            </htm:td>
                        </htm:tr>
                    </htm:table>
                </t:panelGroup>
                <t:panelGrid columns="4" width="100%" border="0" rowClasses="row01,row02" cellpadding="3" 
                             cellspacing="0">
                  <h:outputText value="#{resourcesBundle.job_be_from_min}" styleClass="divtext"/>
                    <t:panelGroup  colspan="3">
                    <t:selectBooleanCheckbox value="#{pageBeanName.jobFromMin}">
                   <a4j:support event="onclick" action="#{pageBeanName.jobFromMinActionEvent}" reRender="selected_job_panel_grp,jobkey,jobBTn" />
                   </t:selectBooleanCheckbox>
                    </t:panelGroup>
                    <h:outputText value="#{resourcesBundle.selected_job_label}" styleClass="divtext"
                                  style="display: block; width: 155px;"/>
                    <t:panelGroup id="selected_job_panel_grp" forceId="true" colspan="3">
                        <t:inputText disabled="#{pageBeanName.configBean.disableCandidateJob}"
                                     value="#{pageBeanName.pageDTO.jobsDTO.name}"
                                     rendered="#{pageBeanName.configBean.disableCandidateJob}" tabindex="8"
                                     maxlength="12" forceId="true" id="selected_job" styleClass="textboxlarge"/>
                        <t:panelGroup rendered="#{!pageBeanName.configBean.disableCandidateJob}">
                            <t:inputText id="jobkey" value="#{pageBeanName.jobKey}" forceId="true"
                                         onkeypress=" filterByjobkey(event,'jobkey','jobName');"
                                         onchange="filterByjobkey(event);" styleClass="textbox" 
                                         style="margin-left: 7px; width:159px;"/>
                            <a4j:jsFunction name="getJobByKey" oncomplete="showvalidate_msg_jobkey('jobName');"
                                            action="#{pageBeanName.findJobName}" id="functionGetJob"
                                            reRender="jobName"/>
                            <t:inputText disabled="true" tabindex="999999999" styleClass="textboxlarge" forceId="true"
                                         id="jobName" value="#{pageBeanName.approvedJobName}" style="width:425px"/>
                            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                            <a4j:commandButton styleClass="cssButtonSmaller" value="#{globalResources.Available}"
                                               action="#{pageBeanName.showJobDivCertified}" 
                                               reRender="customDiv1,suggestedJobPanelGrp,candidate_suggestionPanel,jobDivPanelGroup,jobkey"
                                               oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);settingFoucsAtLovDiv();"/>
                            <h:outputText value="*" styleClass="mandatoryAsterisk" />
                            <f:verbatim>
                                <br/>
                            </f:verbatim>
                            <c2:requiredFieldValidator componentToValidate="jobName"
                                                       errorMessage="#{globalResources.missingField}" active="#{!jobsReviewListBean.configBean.disableCandidateJob}"
                                                       highlight="false" display="dynamic"/>
                            <t:outputText value="#{resourcesBundle.inavlid_code}" id="testMsg" forceId="true"
                                          style="display:none;" styleClass="mandatoryAsterisk"/>
                        </t:panelGroup>
                        <f:verbatim>
                            <br/>
                        </f:verbatim>
                        <t:panelGroup id="error_con_job">
                            <t:outputText value="#{resourcesBundle.error_job_condition}" styleClass="errMsg"
                                          id="outputJobConditionErrMsg" forceId="true"
                                          rendered="#{pageBeanName.errorJobCondition}"/>
                        </t:panelGroup>
                    </t:panelGroup>
                    <h:outputText value="#{resourcesBundle.jobs_arrangement_notes_label}" styleClass="divtext"/>
                    <t:panelGroup colspan="3">
                        <t:outputText forceId="true" id="jobs_opinion_notes_err" value="#{globalResources.missingField}"
                                      styleClass="error" style="display:none"/>
                        <t:inputTextarea    disabled="#{pageBeanName.configBean.disableArrangementDept}"
                                         value="#{pageBeanName.pageDTO.empExtraDataValueDTO.arrangmentDeptNotes}"
                                         tabindex="10" cols="35" rows="4" style="width:650px; height: 50px;"
                                         forceId="true" id="jobs_arrangement_notes"/>
                        <f:verbatim>
                            <br/>
                        </f:verbatim>
                    </t:panelGroup>
                    <%-- begin cader group--%>
                    <%-- <t:panelGroup colspan="4" style="background-color:#ffffff;"
                         rendered="#{pageBeanName.configBean.renderSuggestSalariesSection}"> <htm:table width="100%"
                         border="0" cellspacing="0" cellpadding="2"> <htm:tr> <htm:td width="9"> <htm:img
                         src="../../../../app/media/images/op_arrow.jpg" width="9" height="9"/> </htm:td> <htm:td
                         style="background: none repeat scroll 0% 0% white;"> <t:outputLabel
                         value="#{resourcesBundle.degree_data_arrow_suggest}" styleClass="lable01"/> </htm:td> </htm:tr>
                         <htm:tr> <htm:td colspan="2" height="1"> <htm:img
                         src="../../../../app/media/images/seprator_group.jpg" width="100%" height="1"/> </htm:td>
                         </htm:tr> </htm:table> </t:panelGroup>--%>
                </t:panelGrid>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
     
    <t:panelGroup style="width:99%">
        <t:panelGrid border="0" columns="2" align="center">
            <%-- Detect JobName_Inprogress (11)--%>
            <t:panelGrid columns="2" border="0" align="center"
                         rendered="#{pageBeanName.configBean.renderArrangementDeptBtnGrp}">
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="11"
                                 action="#{pageBeanName.biznasActions.saveJobNameAction}"
                                 value="#{globalResources.SaveButton}"
                                 onclick="clearValidationMSG();return validatemyForm();block();"/>
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="12" id="rejectBtn"
                                 action="#{pageBeanName.biznasActions.rejectJobNameAction}"
                                 value="#{resourcesBundle.transefer_request_to_fatwa}"
                                 onclick="return validateFirstCase();"/>
            </t:panelGrid>
            <t:commandButton styleClass="cssButtonSmall" id="backButtonAddDiv" forceId="true" tabindex="13"
                             action="#{pageBeanName.backAction}" value="#{globalResources.back}"/>
        </t:panelGrid>
    </t:panelGroup>
     
    <t:inputHidden value="#{pageBeanName.virtualLongValue}" id="virtualvalueId2" forceId="true"
                   converter="javax.faces.Long"/>
</t:div>
<script type="text/javascript">
  function validateFirstCase() {
      //var result = validatemyForm();
      var openionnotes = document.getElementById("jobs_arrangement_notes");
      var openionnotes2 = document.getElementById("jobkey");
      /*alert(openionnotes);
      alert(openionnotes2.value);
      alert(openionnotes.value);*/
      var openionnoteserr = document.getElementById("jobs_opinion_notes_err");
      if (openionnotes == null || openionnotes == 'null') {
          openionnoteserr.style.display = "block";
          return false;
      }
      var openionnotesvalue = openionnotes.value;
      //alert('value = ' + openionnotesvalue);
      // return;
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
      if (output_validation != null && output_validation.value != '') {
          output_validation.style.display = "none";

      }
  }
</script>
