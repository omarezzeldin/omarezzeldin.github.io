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
<t:div id="lookupAddDiv" forceId="true" style="visibility:visible;">
    <t:panelGroup styleClass="review_divMainContent1" style="visibility:visible; display: block;">
        <t:messages showDetail="true"/>
        <t:panelGroup colspan="4" style="background-color:#ffffff;">
            <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                <htm:tr>
                    <htm:td width="9">
                        <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
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
            <t:inputText disabled="true" tabindex="1" onkeydown="onKeyDownFirstElement(event,'candidate_name','backButtonAddDiv')"  value="#{pageBeanName.pageDTO.citizensResidentsDTO.code.civilId}"
                         maxlength="12" forceId="true" id="CivilIdAdd" styleClass="textboxmedium"/>
            <h:outputText value="#{resourcesBundle.candidate_name_label}" styleClass="divtext"/>
            <t:inputText disabled="true" tabindex="2" value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}"
                         maxlength="12" forceId="true" id="candidate_name" styleClass="textboxmedium"
                         style="width:235"/>
            <h:outputText value="#{resourcesBundle.candidate_qulification_label}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:inputText disabled="true" tabindex="3"
                             value="#{pageBeanName.pageDTO.citizensResidentsDTO.personQualificationsDTOList[0].qualificationsDTO.name}"
                             maxlength="12" forceId="true" id="candidate_qulification" styleClass="textboxlarge3"
                             style="width: 658px;"/>
            </t:panelGroup>
            
              <h:outputText value="#{resourcesBundle.transfer_to_dewan_Date}" styleClass="lable01"/>
         <t:panelGroup colspan="3">
        <t:inputText disabled="true" value="#{pageBeanName.pageDTO.extraDataValue}"  styleClass="textbox"/>
        </t:panelGroup>
        
            <h:outputText value="#{resourcesBundle.candidate_wrk_center_label}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:inputText disabled="#{pageBeanName.configBean.disableWorkCenter}"
                             rendered="#{pageBeanName.configBean.disableWorkCenter}" tabindex="4"
                             value="#{pageBeanName.workCenterName}" maxlength="12" forceId="true"
                             id="candidate_wrk_center" styleClass="textboxlarge3" style="width: 658px;"/>
                <t:panelGroup forceId="true" id="WrkCenterPanel"
                              rendered="#{!pageBeanName.configBean.disableWorkCenter}">
                    <t:panelGroup forceId="true" id="WorkCenter_Panel"
                                  rendered="#{!pageBeanName.configBean.disableWorkCenter}">
                        <t:inputText disabled="true" readonly="true" tabindex="888888888" styleClass="textboxlarge"
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
                        <%-- c2:requiredFieldValidator active="#{!reviewMinListBean.configBean.disableSuggestedJob}"
                             componentToValidate="workCenterCodeText" errorMessage="#{globalResources.missingField}"
                             highlight="false" display="dynamic" /--%>
                    </t:panelGroup>
                </t:panelGroup>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.candidate_suggestion_job_label}" styleClass="divtext nowrap_txt"/>
            <t:panelGroup id="suggestedJobPanelGrp" forceId="true" colspan="3">
                <t:inputText id="jobkey" value="#{pageBeanName.jobKey}" forceId="true"
                             onkeypress=" filterByjobkey(event,'jobkey','jobNameForMin');"
                             onchange="filterByjobkey(event,jobkey,jobNameForMin);" styleClass="textbox"
                             style="margin-left: 7px; width:160px;"
                             disabled="#{pageBeanName.configBean.disableSuggestedJob}"/>
                <a4j:jsFunction name="getJobByKey" oncomplete="showvalidate_msg_jobkey('jobNameForMin');"
                                action="#{pageBeanName.findJobName}" id="functionGetJob" reRender="errorJobCondPanel,jobNameForMin"/>
                <t:inputText disabled="#{pageBeanName.configBean.disableSuggestedJob}"
                             rendered="#{pageBeanName.configBean.disableSuggestedJob}"
                             value="#{pageBeanName.suggestedJobValue}" tabindex="5" maxlength="12" forceId="true"
                             id="candidate_suggestion_job" styleClass="textboxMedium" style="width:437px;"/>
                <%-- <t:panelGroup forceId="true" id="candidate_suggestionPanel"
                     rendered="#{!pageBeanName.configBean.disableSuggestedJob}"> <t:inputText disabled="true"
                     tabindex="999999999" styleClass="textboxMedium" style="width:425px;" forceId="true"
                     value="#{pageBeanName.suggestedJobValue}" id="jobNameForMin" />
                     <f:verbatim>&nbsp;&nbsp;</f:verbatim> <a4j:commandButton styleClass="cssButtonSmaller"
                     disabled="#{empty pageBeanName.workCenterName}" value="#{globalResources.Available}"
                     action="#{pageBeanName.showListOfValuesDivForJobCrs}"
                     reRender="lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,suggestedJobPanelGrp,lov_searchRadioBtn,candidate_suggestionPanel"
                     oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();" />
                     <h:outputText value="*" styleClass="mandatoryAsterisk" /> <f:verbatim><br/></f:verbatim>
                     <c2:requiredFieldValidator componentToValidate="jobNameForMin"
                     errorMessage="#{globalResources.missingField}"
                     active="#{!reviewMinListBean.configBean.disableSuggestedJob}" highlight="false" display="dynamic"
                     /> <t:outputText value="#{resourcesBundle.inavlid_code}" id="testMsg" forceId="true"
                     style="display:none;" styleClass="mandatoryAsterisk"/> </t:panelGroup>--%>
                <t:panelGroup forceId="true" id="candidate_suggestionPanel"
                              rendered="#{!pageBeanName.configBean.disableSuggestedJob}">
                    <t:inputText disabled="true" tabindex="999999999" styleClass="textboxMedium" style="width:425px;"
                                 forceId="true" value="#{pageBeanName.suggestedJobValue}" id="jobNameForMin"/>
                    <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                    <a4j:commandButton styleClass="cssButtonSmaller" value="#{globalResources.Available}"
                                       action="#{pageBeanName.showJobDiv}"
                                       reRender="customDiv1,errorJobCondPanel,lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,suggestedJobPanelGrp,lov_searchRadioBtn,candidate_suggestionPanel"
                                       oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);settingFoucsAtLovDiv();"/>
                    <%-- new commented by M.abdelsabour<h:outputText value="*" styleClass="mandatoryAsterisk" />--%>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    
                    
 <t:panelGroup id="errorJobCondPanel">
            <t:outputText value="#{resourcesBundle.inavlid_code}" id="testMsg" forceId="true" style="display:none;"
                      styleClass="mandatoryAsterisk"/>
          <t:outputText value="#{resourcesBundle.error_job_condition}" styleClass="errMsg" id="outputJobConditionErrMsg"
                          forceId="true" rendered="#{pageBeanName.errorJobCondition}"/>
         
         </t:panelGroup>

                    <%-- new commented by M.abdelsabour<c2:requiredFieldValidator componentToValidate="jobNameForMin"
                         errorMessage="#{globalResources.missingField}"
                         active="#{!reviewMinListBean1.configBean.disableSuggestedJob}" highlight="false"
                         display="dynamic" />--%>
                    <%-- new commented by M.abdelsabour <t:outputText value="#{resourcesBundle.inavlid_code}"
                         id="testMsg" forceId="true" style="display:none;" styleClass="mandatoryAsterisk"/>--%>
                </t:panelGroup>
            </t:panelGroup>
            <t:panelGroup rendered="#{pageBeanName.configBean.disableOrderStatus}" colspan="4">
                <h:outputText value="#{resourcesBundle.orderType}" styleClass="divtext"/>
                <t:inputText disabled="true" tabindex="2" value="#{pageBeanName.pageDTO.hireStagesDTO.name}"
                             maxlength="12" forceId="true" id="order_name" styleClass="textboxmedium"
                             style="margin-right: 110px; width:242px;"/>
            </t:panelGroup>
            <%-- this panelgroup to adjust design--%>
            <t:panelGroup colspan="4" rendered="#{!pageBeanName.configBean.disableOrderStatus}"/>
            <%-- begin cader group--%>
            <t:panelGroup colspan="4">
                <t:panelGrid columns="4" width="100%" border="0" columnClasses="colm1,colm2,colm3,colm4"
                             rowClasses="row01,row02" cellpadding="3" cellspacing="0" forceId="true"
                             id="suggestionJobgroupId" rendered="#{pageBeanName.configBean.renderCadergroupId}">
                    <t:panelGroup colspan="4" style="background-color:#ffffff;">
                        <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                            <htm:tr>
                                <htm:td width="9">
                                    <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                                </htm:td>
                                <htm:td style="background: none repeat scroll 0% 0% white;">
                                    <t:outputLabel value="#{resourcesBundle.degree_data_arrow_suggest}"
                                                   styleClass="lable01"/>
                                </htm:td>
                            </htm:tr>
                             
                            <htm:tr>
                                <htm:td colspan="2" height="1">
                                    <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%"
                                             height="1"/>
                                </htm:td>
                            </htm:tr>
                        </htm:table>
                    </t:panelGroup>
                    <%-- begin first component--%>
                    <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderFirstSalariesSection}"/>
                    <t:panelGroup rendered="#{pageBeanName.configBean.renderFirstSalariesSection}">
                        <t:inputText disabled="#{pageBeanName.configBean.disableFirstCadre}"
                                     value="#{pageBeanName.suggestedCaderName}"
                                     rendered="#{pageBeanName.configBean.disableFirstCadre}" tabindex="11"
                                     maxlength="12" forceId="true" id="candidate_caderId" styleClass="textboxmedium"/>
                        <t:panelGroup id="employeeCaderPanelId" forceId="true"
                                      rendered="#{!pageBeanName.configBean.disableFirstCadre}">
                            <t:inputText disabled="true" tabindex="9999999999999" styleClass="textboxmedium"
                                         forceId="true" id="caderTextId" value="#{pageBeanName.suggestedCaderName}"/>
                            <f:verbatim>&nbsp;</f:verbatim>
                            <a4j:commandButton value="#{globalResources.Available}" styleClass="cssButtonSmaller"
                                               reRender="suggestedDegreePanel,raisesCount_Id,jobs_groupId,lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn,employeeCaderPanelId"
                                               action="#{reviewMinListBean1.showCaderListOfValuesDiv}"
                                               oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
                            <%-- commented by m.abdelsabour<h:outputText value="*" styleClass="mandatoryAsterisk"/>--%>
                            <f:verbatim>
                                <br/>
                            </f:verbatim>
                            <%-- commented by m.abdelsabour <c2:requiredFieldValidator componentToValidate="caderTextId"
                                 active="#{!reviewMinListBean1.configBean.disableFirstSalDegree}"
                                 errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic" />--%>
                        </t:panelGroup>
                    </t:panelGroup>
                    <%-- end first component--%>
                    <%-- begin second component--%>
                    <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderFirstSalariesSection}"/>
                    <t:panelGroup rendered="#{pageBeanName.configBean.renderFirstSalariesSection}">
                        <t:panelGroup id="jobs_groupId" forceId="true">
                            <t:inputText disabled="#{pageBeanName.configBean.disableFirstJobGroup}"
                                         rendered="#{pageBeanName.configBean.disableFirstJobGroup}"
                                         value="#{pageBeanName.suggestedGroupName}" tabindex="12" maxlength="12"
                                         forceId="true" id="candidate_jobs_groupId" styleClass="textboxmedium"
                                         style="width: 222px;"/>
                            <t:selectOneMenu value="#{pageBeanName.suggestedGroupCode}"
                                             disabled="#{empty pageBeanName.suggestedCaderName}"
                                             converter="javax.faces.Long" id="JobGrpMenuId" forceId="true"
                                             styleClass="DropdownboxMedium2"
                                             rendered="#{!pageBeanName.configBean.disableFirstJobGroup}">
                                <f:selectItem itemValue="#{pageBeanName.virtualLongValue}"
                                              itemLabel="#{resourcesBundle.select}"/>
                                <t:selectItems value="#{pageBeanName.groupList}" itemLabel="#{group.name}"
                                               itemValue="#{group.code.elmguideCode}" var="group"/>
                                <a4j:support reRender="degreePanelId,suggestedDegreePanel,jobs_groupId,raisesCount_Id"
                                             action="#{pageBeanName.resetDegreeAndRaiseList}" event="onchange"/>
                            </t:selectOneMenu>
                            <t:panelGroup>
                                <%-- commented by m.abdelsabour<h:outputText value="*" styleClass="mandatoryAsterisk"
                                     rendered="#{!pageBeanName.configBean.disableFirstJobGroup}" />--%>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                                <%-- commented by m.abdelsabour<c2:compareValidator componentToValidate="JobGrpMenuId"
                                     active="#{!reviewMinListBean1.configBean.disableFirstSalDegree}"
                                     componentToCompare="virtualvalueId2" operator="not"
                                     errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic"
                                     />--%>
                            </t:panelGroup>
                        </t:panelGroup>
                    </t:panelGroup>
                    <%-- end second component--%>
                    <%-- begin third component--%>
                    <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext"
                                  rendered="#{pageBeanName.configBean.renderFirstSalariesSection}"/>
                    <%-- <t:panelGroup colspan="3" rendered="#{pageBeanName.configBean.renderFirstSalariesSection}">
                         <t:inputText disabled="#{pageBeanName.configBean.disableFirstSalDegree}"
                         rendered="#{pageBeanName.configBean.disableFirstSalDegree}"
                         value="#{pageBeanName.suggestedRaiseName}" tabindex="13" maxlength="12" forceId="true"
                         id="candidate_sal_degreeId" styleClass="textboxmedium" /> <t:panelGroup forceId="true"
                         id="degreePanelId" rendered="#{!pageBeanName.configBean.disableFirstSalDegree}"> <t:inputText
                         forceId="true" id="employees_degreeId" styleClass="textboxmedium"
                         value="#{pageBeanName.suggestedRaiseName}" disabled="true"/> <f:verbatim>&nbsp;</f:verbatim>
                         <a4j:commandButton value="..." disabled="#{empty pageBeanName.suggestedGroupCode ||
                         pageBeanName.suggestedGroupCode == virtualLongValue}" styleClass="cssButtonSmaller"
                         reRender="searchText,cancelsearchpanel,noResult,treeDivPanel,radioTreeDivPanel11,okPanel,degreePanelId"
                         action="#{pageBeanName.OpenTreeDiv}"
                         oncomplete="changeVisibilityDiv(window.blocker,window.divTree);settingFoucsAtTreeDiv();return
                         false;" /> <t:panelGroup > <h:outputText value="*" styleClass="mandatoryAsterisk" />
                         <f:verbatim><br/></f:verbatim> <c2:requiredFieldValidator
                         componentToValidate="employees_degreeId"
                         active="#{!reviewMinListBean.configBean.disableFirstSalDegree}"
                         errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic" />--%>
                    <%-- a4j:support action="#{detailBeanName.resetRaiseList}" event="onchange" /--%>
                    <%-- </t:panelGroup> </t:panelGroup> </t:panelGroup>--%>
                    <t:panelGroup id="suggestedDegreePanel" forceId="true"
                                  rendered="#{pageBeanName.configBean.renderFirstSalariesSection}">
                        <t:inputText disabled="#{pageBeanName.configBean.disableFirstSalDegree}"
                                     rendered="#{pageBeanName.configBean.disableFirstSalDegree}"
                                     value="#{pageBeanName.suggestedDegreeName}" tabindex="13" maxlength="12"
                                     forceId="true" id="candidate_sal_degreeId" styleClass="textboxmedium"/>
                        <t:selectOneMenu forceId="true" id="suggestedemployees_degree" styleClass="DropdownboxMedium2"
                                         disabled="#{empty pageBeanName.suggestedGroupCode || pageBeanName.suggestedGroupCode == virtualLongValue}"
                                         value="#{pageBeanName.suggestedDegreeCode}" converter="javax.faces.Long"
                                         rendered="#{!pageBeanName.configBean.disableFirstSalDegree}">
                            <f:selectItem itemValue="#{pageBeanName.virtualLongValue}"
                                          itemLabel="#{resourcesBundle.select}"/>
                            <t:selectItems value="#{pageBeanName.degreeList}" var="degree" itemLabel="#{degree.name}"
                                           itemValue="#{degree.code.elmguideCode}"/>
                            <a4j:support reRender="suggestedDegreePanel, raisesCount_Id" event="onchange" action="#{pageBeanName.resetRaiseList}"/>
                        </t:selectOneMenu>
                        <t:panelGroup>
                            <%-- commented by m.abdelsabour<h:outputText value="*" styleClass="mandatoryAsterisk"
                                 rendered="#{!pageBeanName.configBean.disableFirstSalDegree}" />--%>
                            <f:verbatim>
                                <br/>
                            </f:verbatim>
                            <h:outputText id="grsCondMsgId" value="#{resourcesBundle.error_degreeForEmp}"
                                      styleClass="errMsg" rendered="#{!pageBeanName.validCond}"/>
                            <%-- commented by m.abdelsabour<c2:compareValidator
                                 componentToValidate="suggestedemployees_degree"
                                 active="#{!reviewMinListBean1.configBean.disableFirstSalDegree}"
                                 componentToCompare="virtualvalueId2" operator="not"
                                 errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic" />--%>
                        </t:panelGroup>
                    </t:panelGroup>
                    <t:outputLabel value="#{resourcesBundle.raisesCount}" styleClass="lable01"
                                   rendered="#{pageBeanName.configBean.renderFirstSalariesSection}"/>
                    <t:panelGroup colspan="3" id="raisesCount_Id" forceId="true"
                                  rendered="#{pageBeanName.configBean.renderFirstSalariesSection}">
                        <t:inputText disabled="#{pageBeanName.configBean.disableFirstSalDegree}"
                                     rendered="#{pageBeanName.configBean.disableFirstSalDegree}"
                                     value="#{pageBeanName.suggestedRaiseName}" tabindex="13" maxlength="12"
                                     forceId="true" id="candidate_sal_degree2" styleClass="textboxmedium"
                                     style="width:222px;"/>
                        <t:selectOneMenu forceId="true" id="raisesCountId"
                                         disabled="#{empty pageBeanName.suggestedDegreeCode || pageBeanName.suggestedDegreeCode == virtualLongValue}"
                                         styleClass="DropdownboxMedium2" value="#{pageBeanName.suggestedRaiseCode}"
                                         rendered="#{!pageBeanName.configBean.disableFirstSalDegree}">
                            <f:selectItem itemValue="#{pageBeanName.virtualConstValue}"
                                          itemLabel="#{resourcesBundle.select}"/>
                            <t:selectItems value="#{pageBeanName.raiseList}" itemLabel="#{raise.countGuide}"
                                           itemValue="#{raise.code.key}" var="raise"/>
                        </t:selectOneMenu>
                        <t:panelGroup>
                            <%-- commented by m.abdelsabour<h:outputText value="*" styleClass="mandatoryAsterisk"
                                 rendered="#{!pageBeanName.configBean.disableFirstSalDegree}"/>--%>
                            <f:verbatim>
                                <br/>
                            </f:verbatim>
                            <%-- commented by m.abdelsabour<c2:compareValidator componentToValidate="raisesCountId"
                                 active="#{!reviewMinListBean1.configBean.disableFirstSalDegree}"
                                 componentToCompare="virtualvalueId2" operator="not"
                                 errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic" />--%>
                        </t:panelGroup>
                    </t:panelGroup>
                    <%-- end third component--%>
                </t:panelGrid>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.ministry_notes_label}" styleClass="divtext"/>
            <t:panelGroup colspan="3">
                <t:inputTextarea disabled="#{pageBeanName.configBean.disableMinistryNotes}"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.ministryNotes}" tabindex="6"
                                 cols="35" rows="4" forceId="true" style="width:650px; height: 50px;"
                                 id="ministry_notes"/>
            </t:panelGroup>
            <%-- end page of stageCode equal 8--%>
            <t:panelGroup colspan="4" id="selectedDeptNotesID" forceId="true"
                          rendered="#{pageBeanName.configBean.disableSelectionDeptNotes}">
                <t:panelGrid columns="4" width="100%" border="0" columnClasses="colm1 nowrap_txt">
                    <t:panelGroup colspan="1">
                        <t:outputText value="#{resourcesBundle.choice_dept_notes_label}" styleClass="divtext"/>
                    </t:panelGroup>
                    <t:panelGroup colspan="3">
                        <t:inputTextarea tabindex="7" disabled="#{pageBeanName.configBean.disableSelectionDeptNotes}"
                                         value="#{pageBeanName.pageDTO.empExtraDataValueDTO.selectionDeptNotes}"
                                         cols="35" rows="4" style="width:650px; height: 50px; margin-right: -5px;"
                                         forceId="true" id="choice_dept_notes"/>
                    </t:panelGroup>
                </t:panelGrid>
            </t:panelGroup>
                      <h:outputText value="#{resourcesBundle.job_be_from_min}" styleClass="divtext"/>
                    <t:panelGroup  colspan="3">
                    <t:selectBooleanCheckbox value="#{pageBeanName.jobFromMin}" disabled="true" >
                   </t:selectBooleanCheckbox>
                    </t:panelGroup>
                    
                <t:panelGroup id="selected_job_panel_grp" forceId="true" colspan="4" rendered="#{!pageBeanName.jobFromMin}" >
                <h:outputText value="#{resourcesBundle.selected_job_label}" styleClass="divtext"/>
                    <t:inputText disabled="#{pageBeanName.configBean.disableCandidateJob}" 
                                 value="#{pageBeanName.acceptedJobValue}"
                                 rendered="#{pageBeanName.configBean.disableCandidateJob}" tabindex="8" maxlength="12"
                                 forceId="true" id="selected_job" styleClass="textboxmedium"
                                 style="margin-right: 80px;"/>
                    <t:panelGroup rendered="#{!pageBeanName.configBean.disableCandidateJob}">
                        <t:inputText disabled="true" tabindex="999999999" styleClass="textboxlarge" forceId="true" 
                                     id="jobName" value="#{pageBeanName.acceptedJobValue}"/>
                        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                        <a4j:commandButton styleClass="cssButtonSmaller" value="#{globalResources.Available}"
                                           action="#{pageBeanName.showListOfValuesDivForJob}"
                                           reRender="lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,suggestedJobPanelGrp,lov_searchRadioBtn,candidate_suggestionPanel,jobDivPanelGroup"
                                           oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
                        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                        <%-- c2:requiredFieldValidator componentToValidate="jobName"
                             errorMessage="#{globalResources.missingField}"
                             active="#{!reviewMinListBean.configBean.disableCandidateJob}" highlight="false"
                             display="dynamic" /--%>
                    </t:panelGroup>
                    
                 </t:panelGroup>
                 
                
                    
                <t:panelGroup id="selected_job_panel_grp12" forceId="true" colspan="4" rendered="#{pageBeanName.jobFromMin}" >
                <h:outputText value="#{resourcesBundle.job_from_job_arrangment}" styleClass="divtext"/>
                    <t:inputText disabled="#{pageBeanName.configBean.disableCandidateJob}" 
                                 value="#{pageBeanName.acceptedJobValue}"
                                 rendered="#{pageBeanName.configBean.disableCandidateJob}" tabindex="8" maxlength="12"
                                 forceId="true" id="selected_job12" styleClass="textboxmedium"
                                 style="margin-right: 80px;"/>
                  
                    
                </t:panelGroup>
                  <t:panelGroup id="selected_job_panel_grp1" forceId="true" colspan="4" rendered="#{pageBeanName.jobFromMin}">
                  <h:outputText value="#{resourcesBundle.selected_job_label}" styleClass="divtext"/>
                     <f:verbatim>&nbsp;&nbsp;</f:verbatim>
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
                            
            <t:panelGroup colspan="4" forceId="true" id="budgetTypeContainer"
                          rendered="#{pageBeanName.configBean.disableBudgetContainer}">
                <h:outputText value="#{resourcesBundle.bget_type_label}" styleClass="divtext"/>
                <t:panelGroup colspan="3">
                    <t:inputText disabled="#{pageBeanName.configBean.disableBudgetType}"
                                 rendered="#{pageBeanName.configBean.disableBudgetType}" tabindex="9"
                                 value="#{pageBeanName.bgtTypesDTO.name}" maxlength="12" forceId="true" id="bget_type"
                                 styleClass="textboxlarge3" style="width: 650px; margin-right: 105px;"/>
                    <t:panelGroup forceId="true" id="budgetTypePanel"
                                  rendered="#{!pageBeanName.configBean.disableBudgetType}">
                        <t:selectOneMenu forceId="true" id="employees_budgetType"
                                         styleClass="DropdownboxLargeForSharing"
                                         value="#{pageBeanName.pageDTO.bgtTypeKey}">
                            <t:selectItems value="#{pageBeanName.budgetTypeList}" itemLabel="#{budgetType.name}"
                                           itemValue="#{budgetType.code.key}" var="budgetType"/>
                        </t:selectOneMenu>
                        <t:panelGroup></t:panelGroup>
                    </t:panelGroup>
                </t:panelGroup>
            </t:panelGroup>
            <t:panelGroup colspan="4" rendered="#{pageBeanName.configBean.disableArrangementDept}">
                <t:panelGrid columns="4" width="100%" border="0" columnClasses="colm1 nowrap_txt">
                    <h:outputText value="#{resourcesBundle.jobs_arrangement_notes_label}" styleClass="divtext"/>
                    <t:panelGroup colspan="3">
                        <t:inputTextarea disabled="#{pageBeanName.configBean.disableArrangementDept}"
                                         value="#{pageBeanName.pageDTO.empExtraDataValueDTO.arrangmentDeptNotes}"
                                         tabindex="10" cols="35" rows="4"
                                         style="width:650px; height: 50px; margin-right: -5px;" forceId="true"
                                         id="jobs_arrangement_notes"/>
                    </t:panelGroup>
                </t:panelGrid>
            </t:panelGroup>
            <t:panelGroup colspan="4" style="background-color:#ffffff;"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td style="background: none repeat scroll 0% 0% white;">
                            <t:outputLabel value="#{resourcesBundle.degree_data_arrow}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
            <%-- <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext"
                 rendered="#{pageBeanName.configBean.renderSalariesSection}"/> <t:panelGroup
                 rendered="#{pageBeanName.configBean.renderSalariesSection}"> <t:inputText
                 disabled="#{pageBeanName.configBean.disableCadre}" value="#{pageBeanName.acceptedCaderName}"
                 rendered="#{pageBeanName.configBean.disableCadre}" tabindex="11" maxlength="12" forceId="true"
                 id="candidate_cader" styleClass="textboxmedium" /> <t:panelGroup id="employeeCaderPanel" forceId="true"
                 rendered="#{!pageBeanName.configBean.disableCadre}"> <t:inputText disabled="true"
                 tabindex="9999999999999" styleClass="textboxmedium" forceId="true" id="caderText"
                 value="#{pageBeanName.acceptedCaderName}"/> <f:verbatim>&nbsp;</f:verbatim> <a4j:commandButton
                 value="#{globalResources.Available}" styleClass="cssButtonSmaller"
                 reRender="jobs_group,lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn,employeeCaderPanel"
                 action="#{reviewMinListBean.showCaderListOfValuesDiv}"
                 oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();" /> <h:outputText
                 value="*" styleClass="mandatoryAsterisk"/> <f:verbatim><br/></f:verbatim> <c2:requiredFieldValidator
                 componentToValidate="caderText" active="#{!reviewMinListBean.configBean.disableSalDegree}"
                 errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic" /> </t:panelGroup>
                 </t:panelGroup> <%-- <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}"
                 styleClass="divtext" rendered="#{pageBeanName.configBean.renderSalariesSection}"/> <t:panelGroup
                 rendered="#{pageBeanName.configBean.renderSalariesSection}"> <t:panelGroup id="jobs_group"
                 forceId="true" > <t:inputText disabled="#{pageBeanName.configBean.disableJobGroup}"
                 rendered="#{pageBeanName.configBean.disableJobGroup}" value="#{pageBeanName.acceptedGroupName}"
                 tabindex="12" maxlength="12" forceId="true" id="candidate_jobs_group" styleClass="textboxmedium" />
                 <t:selectOneMenu value="#{pageBeanName.groupCode}" disabled="#{empty pageBeanName.acceptedCaderName}"
                 converter="javax.faces.Long" id="JobGrpMenu" forceId="true" styleClass="DropdownboxMedium2"
                 rendered="#{!pageBeanName.configBean.disableJobGroup}" > <f:selectItem
                 itemValue="#{pageBeanName.virtualLongValue}" itemLabel="#{resourcesBundle.select}"/> <t:selectItems
                 value="#{pageBeanName.groupList}" itemLabel="#{group.name}" itemValue="#{group.code.elmguideCode}"
                 var="group"/> <a4j:support reRender="degreePanel,jobs_group"
                 action="#{pageBeanName.resetDegreeAndRaiseList}" event="onchange" /> </t:selectOneMenu> <t:panelGroup>
                 <h:outputText value="*" styleClass="mandatoryAsterisk"
                 rendered="#{!pageBeanName.configBean.disableJobGroup}" /> <f:verbatim><br/></f:verbatim>
                 <%--<c2:compareValidator componentToValidate="JobGrpMenu"
                 active="#{!reviewMinListBean.configBean.disableSalDegree}" componentToCompare="virtualvalueId2"
                 operator="not" errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic" />
                 </t:panelGroup> </t:panelGroup> </t:panelGroup> <h:outputText
                 value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext"
                 rendered="#{pageBeanName.configBean.renderSalariesSection}"/> <%--<t:panelGroup colspan="3"
                 rendered="#{pageBeanName.configBean.renderSalariesSection}"> <t:inputText
                 disabled="#{pageBeanName.configBean.disableSalDegree}"
                 rendered="#{pageBeanName.configBean.disableSalDegree}" value="#{pageBeanName.acceptedRaiseName}"
                 tabindex="13" maxlength="12" forceId="true" id="candidate_sal_degree" styleClass="textboxmedium" />
                 <t:panelGroup forceId="true" id="degreePanel" rendered="#{!pageBeanName.configBean.disableSalDegree}">
                 <t:inputText forceId="true" id="employees_degree" styleClass="textboxmedium"
                 value="#{pageBeanName.acceptedRaiseName}" disabled="true"/> <f:verbatim>&nbsp;</f:verbatim>
                 <a4j:commandButton value="..." disabled="#{empty pageBeanName.acceptedGroupCode ||
                 pageBeanName.acceptedGroupCode == virtualLongValue}" styleClass="cssButtonSmaller"
                 action="#{pageBeanName.OpenTreeDiv}"
                 oncomplete="changeVisibilityDiv(window.blocker,window.divTree);settingFoucsAtTreeDiv();return false;"
                 reRender="searchText,cancelsearchpanel,noResult,treeDivPanel,radioTreeDivPanel11,okPanel" />
                 <t:panelGroup> <h:outputText value="*" styleClass="mandatoryAsterisk" /> <f:verbatim><br/></f:verbatim>--%>
            <%-- <c2:requiredFieldValidator componentToValidate="employees_degree"
                 active="#{!reviewMinListBean.configBean.disableSalDegree}"
                 errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic" />--%>
            <%-- --%>
            <%-- a4j:support action="#{detailBeanName.resetRaiseList}" event="onchange" /--%>
            <%-- </t:panelGroup> </t:panelGroup> </t:panelGroup>--%>
            <%-- begin first component--%>
            <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
            <t:inputText disabled="true" value="#{pageBeanName.acceptedCaderName}"
                         rendered="#{pageBeanName.configBean.renderSalariesSection}" tabindex="11" maxlength="12"
                         forceId="true" id="candidate_cader" styleClass="textboxmedium"/>
            <%-- end first component--%>
            <%-- begin second component--%>
            <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
            <t:inputText disabled="true" rendered="#{pageBeanName.configBean.renderSalariesSection}"
                         value="#{pageBeanName.acceptedGroupName}" tabindex="12" maxlength="12" forceId="true"
                         id="candidate_jobs_group" styleClass="textboxmedium" style="width:235px;"/>
            <%-- end second component--%>
            <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
            <t:inputText forceId="true" id="employees_degree" styleClass="textboxmedium"
                         value="#{pageBeanName.acceptedDegreeName}"
                         rendered="#{pageBeanName.configBean.renderSalariesSection}" disabled="true"/>
            <%-- begin third component--%>
            <h:outputText value="#{resourcesBundle.raisesCount}" styleClass="divtext"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
            <t:inputText disabled="true" value="#{pageBeanName.acceptedRaiseName}" styleClass="textboxmedium"
                         forceId="true" id="candidate_sal_raisId"
                         rendered="#{pageBeanName.configBean.renderSalariesSection}" style="width:235px;"/>
            <%-- end third component--%>
            <h:outputText value="#{resourcesBundle.jobs_opinion_notes_label}" styleClass="divtext"
                          rendered="#{pageBeanName.configBean.renderSalariesSection}"/>
            <t:panelGroup colspan="3" rendered="#{pageBeanName.configBean.renderSalariesSection}">
                <t:inputTextarea disabled="#{pageBeanName.configBean.disableOpenionDept}" tabindex="14" cols="35"
                                 rows="4" forceId="true" style="width:650px; height: 50px;" id="jobs_opinion_notes"
                                 value="#{pageBeanName.pageDTO.empExtraDataValueDTO.fatwaDeptNotes}"/>
            </t:panelGroup>
            <%-- panelGroup--%>
        </t:panelGrid>
    </t:panelGroup>
     
    <t:panelGroup style="width:99%">
        <t:panelGrid border="0" columns="2" align="center">
            <%-- view 1--%>
            <t:panelGrid columns="2" border="0" align="center" rendered="#{pageBeanName.configBean.renderbtnGrp2}">
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="15"
                                 action="#{pageBeanName.biznasActions.approveRequestAction}"
                                 onclick="return validatemyForm();block();"
                                 rendered="#{( !pageBeanName.pageDTO.hasExperience &&  pageBeanName.stageId == pageBeanName.managedConstantsBean.HIRE_STAGE_JOB_NAME_ACCEPTRD)|| pageBeanName.stageId == pageBeanName.managedConstantsBean.HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED}"
                                 value="#{resourcesBundle.approve_request_btn}"/>
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="16"
                                 action="#{pageBeanName.biznasActions.transeferForFatwaDeptAction2}"
                                 rendered="#{pageBeanName.pageDTO.hasExperience && pageBeanName.stageId != pageBeanName.managedConstantsBean.HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED}"
                                 value="#{resourcesBundle.transefer_request_opinion_btn}" onclick="block();"/>
                <%-- t:commandButton styleClass="cssButtonMedium"
                     action="#{pageBeanName.biznasActions.replyRequestForUpdateDataAction}"
                     rendered="#{pageBeanName.pageDTO.hasExperience && pageBeanName.stageId !=
                     pageBeanName.managedConstantsBean.HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED}"
                     value="#{resourcesBundle.transefer_request_ministry_btn}" /--%>
            </t:panelGrid>
            <%-- view 2--%>
            <%-- <t:panelGrid columns="3" border="0" align="center" rendered="#{pageBeanName.configBean.renderbtnGrp1}">
                 <t:commandButton styleClass="cssButtonMedium cssButtonLarge"
                 action="#{pageBeanName.biznasActions.transferForJobArrangmentAction}"
                 value="#{resourcesBundle.transefer_request_arrangment_btn}" rendered="#{pageBeanName.stageId ==
                 pageBeanName.managedConstantsBean.HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT || pageBeanName.stageId ==
                 pageBeanName.managedConstantsBean.HIRE_STAGE_COMPLETING_JOB_NAME}" onclick="block();" />
                 <t:commandButton styleClass="cssButtonMedium cssButtonLarge"
                 action="#{pageBeanName.biznasActions.transeferForFatwaDeptAction2}" rendered="#{(pageBeanName.stageId
                 == pageBeanName.managedConstantsBean.HIRE_STAGE_JOB_NAME_ACCEPTRD &&
                 pageBeanName.pageDTO.hasExperience) || pageBeanName.stageId ==
                 pageBeanName.managedConstantsBean.HIRE_STAGE_REJECTED_BY_FATWA}"
                 value="#{resourcesBundle.transefer_request_opinion_btn}" onclick="block();" /> <t:commandButton
                 styleClass="cssButtonMedium cssButtonLarge"
                 action="#{pageBeanName.biznasActions.replyRequestForUpdateDataAction}"
                 value="#{resourcesBundle.transefer_request_ministry_btn}" /> </t:panelGrid>--%>
            <%-- ص�?حة إدارة ترتيب الوظائ�?--%>
            <t:panelGrid columns="2" border="0" align="center"
                         rendered="#{pageBeanName.configBean.renderArrangementDeptBtnGrp}">
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="17"
                                 action="#{pageBeanName.biznasActions.approveJobNameAction}"
                                 value="#{resourcesBundle.request_approve_job_name_btn}" onclick="block();"/>
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="18"
                                 action="#{pageBeanName.biznasActions.rejectJobNameAction}"
                                 value="#{resourcesBundle.request_not_compelet_job_arraangment_btn}"
                                 onclick="block();"/>
            </t:panelGrid>
            <%-- بيانات المرشح - إدارة ال�?توي--%>
            <%-- <t:panelGrid columns="2" border="0" align="center"
                 rendered="#{pageBeanName.configBean.renderOpenionDeptBtnGrp}"> <t:commandButton
                 styleClass="cssButtonMedium cssButtonLarge"
                 action="#{pageBeanName.biznasActions.approveSalaryDegreeAction}" onclick="return validatemyForm();"
                 value="#{resourcesBundle.approve_sal_degree_btn}" /> <t:commandButton styleClass="cssButtonMedium
                 cssButtonLarge" action="#{pageBeanName.biznasActions.replyRequestForUpdateDataForFatwaDeptAction}"
                 value="#{resourcesBundle.request_not_complete_btn}" onclick="block();" /> </t:panelGrid>--%>
            <%-- جهة عمل مرشخ--%>
            <%-- <t:panelGrid columns="2" border="0" align="center"
                 rendered="#{pageBeanName.configBean.renderMinistryBtnGrp}"> <t:commandButton
                 styleClass="cssButtonMedium cssButtonLarge" rendered="#{pageBeanName.stageId !=
                 pageBeanName.managedConstantsBean.HIRE_STAGE_REJECTED_BY_DEWAN}"
                 action="#{pageBeanName.biznasActions.moveRequestToDiwan}" onclick="return validatemyForm();block();"
                 value="#{resourcesBundle.forward_request_to_diwan_btn}" /> <t:commandButton styleClass="cssButtonMedium
                 cssButtonLarge" rendered="#{pageBeanName.stageId ==
                 pageBeanName.managedConstantsBean.HIRE_STAGE_REJECTED_BY_DEWAN}"
                 action="#{pageBeanName.biznasActions.hireStageCompletingJobNameAction}" onclick="return
                 validatemyForm();block();" value="#{resourcesBundle.forward_request_to_diwan_btn}" /> </t:panelGrid>--%>
            <%-- �?ى حالة تم اعتماد الوظي�?ة والطلب--%>
            <t:panelGrid columns="3" border="0" align="center"
                         rendered="#{pageBeanName.configBean.renderApprovebtnGrp}">
                         
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="18"
                                 action="#{pageBeanName.biznasActions.hireStageCompletingAddNewJobNameActionDiv}"
                                 onclick="return validatemyForm();block();"
                                 value="#{resourcesBundle.suggestion_transefer_request_choice_btn22}"/>
                              
                 <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="19"
                                 action="#{pageBeanName.biznasActions.approveOrderAction}" onclick="block();"
                                 value="#{resourcesBundle.approve_request_btn}"/>
                 <t:commandButton styleClass="cssButtonSmall" tabindex="20"
                                 action="#{pageBeanName.biznasActions.printAction}" onclick="block();"
                                 value="#{resourcesBundle.print_btn2}"/>
            </t:panelGrid>
            <%-- �?ى حالة جارى اقتراح وظي�?ه ودرجه--%>
            <t:panelGrid columns="1" border="0" align="center" rendered="#{pageBeanName.configBean.renderbtnGrp1}">
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="21"
                                 action="#{pageBeanName.biznasActions.hireStageCompletingJobNameAction}"
                                 value="#{resourcesBundle.transefer_request_choiceOrg}"
                                 onclick="return validatemyForm();block();"/>
            </t:panelGrid>
            <%-- و�?ى حالة تم رد الطلب لجهة العمل--%>
            <t:panelGrid columns="1" border="0" align="center" rendered="#{pageBeanName.configBean.renderbtnGrp3}">
                <t:commandButton styleClass="cssButtonMedium cssButtonLarge" tabindex="22"
                                 action="#{pageBeanName.biznasActions.hireStageCompletingAddNewJobNameAction}"
                                 value="#{resourcesBundle.transefer_request_choiceOrg}"
                                 onclick="return validatemyForm();block();"/>
            </t:panelGrid>
            <t:commandButton styleClass="cssButtonSmall" id="backButtonAddDiv" forceId="true" tabindex="23"
                             action="#{pageBeanName.backAction}" value="#{globalResources.back}"/>
        </t:panelGrid>
    </t:panelGroup>
     
    <t:inputHidden value="#{pageBeanName.virtualLongValue}" id="virtualvalueId2" forceId="true"
                   converter="javax.faces.Long"/>
                   
                   <t:panelGroup forceId="true" id="movReuestValues">
        <t:inputHidden forceId="true" id="empRequestFullURLId" value="#{printListBean.fullURL}"/>
        <t:outputText forceId="true" id="empRequestWindowTitleId" value="#{resourcesBundle.print_btn}" style="display:none;"/>
    </t:panelGroup>
</t:div>


<script type="text/javascript">
  function clearValidationMSG() {
      var output_validation = document.getElementById("outputJobConditionErrMsg");
      if (output_validation != null && output_validation.value != '') {
          output_validation.style.display = "none";

      }
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