<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:view locale="#{shared_util.locale}">
    <htm:link rel="SHORTCUT ICON" href="#{shared_util.contextPath}/app/media/images/favicon.ico" type="image/x-icon"/>
    <htm:link rel="stylesheet" href="../../../../integration/shared/media/css/ar/csc_report_ar.css" type="text/css"/>
    <f:loadBundle basename="com.beshara.csc.hr.emp.presentation.resources.emp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <h:form id="myForm">
        <t:aliasBean alias="#{pageBeanName}" value="#{printListBean}">
            <t:saveState value="#{printListBean.pageDTO}"/>
            <t:saveState value="#{printListBean.workCenterName}"/>
            <t:saveState value="#{printListBean.suggestedCaderName}"/>
            <t:saveState value="#{printListBean.suggestedJobValue}"/>
            <t:saveState value="#{printListBean.suggestedGroupName}"/>
            <t:saveState value="#{printListBean.suggestedDegreeName}"/>
            <t:saveState value="#{printListBean.suggestedRaiseName}"/>
            <t:saveState value="#{printListBean.acceptedJobValue}"/>
            <t:saveState value="#{printListBean.bgtTypesDTO}"/>
            <t:saveState value="#{printListBean.acceptedCaderName}"/>
            <t:saveState value="#{printListBean.acceptedGroupName}"/>
            <t:saveState value="#{printListBean. acceptedDegreeName}"/>
            <t:saveState value="#{printListBean.acceptedRaiseName}"/>
            <t:saveState value="#{printListBean.civilServiceNotes}"/>
            <t:saveState value="#{empListBean.pageDTO}"/>
            <t:saveState value="#{empListBean.filteredList}"/>
            <t:saveState value="#{empListBean.myTableData}"/>
            <t:saveState value="#{empListBean.myTableData}"/>
            <t:saveState value="#{empListBean.highlightedDTOS}"/>
            <t:saveState value="#{empListBean.searchMode}"/>
            <t:saveState value="#{empListBean.selectedPageDTO}"/>
            <t:saveState value="#{empListBean.myDataTable}"/>
            <t:inputHidden value="#{empListBean.selectedListSize}"/>
            <t:saveState value="#{empListBean.currentPageIndex}"/>
            <t:saveState value="#{empListBean.oldPageIndex}"/>
            <t:saveState value="#{empListBean.sorting}"/>
            <t:saveState value="#{empListBean.usingPaging}"/>
            <t:saveState value="#{empListBean.updateMyPagedDataModel}"/>
            <t:saveState value="#{empListBean.resettedPageIndex}"/>
            <t:saveState value="#{empListBean.pagingRequestDTO}"/>
            <t:saveState value="#{empListBean.pagingBean.totalListSize}"/>
            <t:saveState value="#{empListBean.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{empListBean.pagingBean.preUpdatedDataModel}"/>
            <htm:style>.row01, .row02 { color: #000000; font-family: Tahoma; font-size: 8pt; height: 40px !important;
                       border-left-width: 1px; border-right-width: 1px; border-top-width: 1px; border-bottom: 1px solid
                       #FFFFFF; padding-right:0px; } }</htm:style>
            <t:panelGroup styleClass="report_contents" id="movRequestReport">
                <t:panelGrid styleClass="report_header">
                    <t:panelGroup styleClass="header_data"/>
                </t:panelGrid>
                <t:commandButton value="#{resourcesBundle.print_btn2}" onclick="window.print();" styleClass="print_btn"
                                 title="#{resourcesBundle.print_btn}"/>
                <t:outputText value="#{resourcesBundle.print_btn}" styleClass="report_title"/>
                <t:panelGroup forceId="true" id="requestdetaildiv">
                    <t:panelGrid columns="4" cellspacing="0" cellpadding="5" border="0" rowClasses="row01,row02"
                                 width="100%" columnClasses="td_key,,td_key,">
                        <t:panelGroup colspan="4">
                            <htm:table width="100%" border="0" cellspacing="0" cellpadding="3">
                                <htm:tr>
                                    <htm:td width="9">
                                        <htm:img src="${shared_util.contextPath}/app/media/images/op_arrow.jpg"
                                                 width="9" height="9"/>
                                    </htm:td>
                                    <htm:td styleClass="group_title">
                                        <t:outputLabel value="#{resourcesBundle.contract_emp_main_data_label}"
                                                       styleClass="lable02"/>
                                    </htm:td>
                                </htm:tr>
                                 
                                <htm:tr>
                                    <htm:td colspan="2" height="1">
                                        <htm:img src="${shared_util.contextPath}/app/media/images/seprator_group.jpg"
                                                 width="100%" height="1"/>
                                    </htm:td>
                                </htm:tr>
                            </htm:table>
                        </t:panelGroup>
                        <h:outputLabel value="#{resourcesBundle.candidate_civilId_label}"/>
                        <t:inputText disabled="true" tabindex="1"
                                     value="#{pageBeanName.pageDTO.citizensResidentsDTO.code.civilId}" forceId="true"
                                     id="CivilIdAdd" styleClass="td_value"/>
                        <h:outputLabel value="#{resourcesBundle.candidate_name_label}"/>
                        <t:inputText disabled="true" tabindex="2"
                                     value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}" forceId="true"
                                     id="candidate_name" styleClass="td_value"/>
                        <h:outputLabel value="#{resourcesBundle.candidate_qulification_label}"/>
                        <t:panelGroup colspan="3">
                            <t:inputTextarea disabled="true" tabindex="3" cols="35" rows="1"
                                             value="#{pageBeanName.pageDTO.citizensResidentsDTO.personQualificationsDTOList[0].qualificationsDTO.name}"
                                             forceId="true" id="candidate_qulification" styleClass="td_value"/>
                        </t:panelGroup>
                        <h:outputText value="#{resourcesBundle.transfer_to_dewan_Date}"/>
                        <t:panelGroup colspan="3">
                            <t:inputText disabled="true" value="#{pageBeanName.pageDTO.extraDataValue}"
                                         styleClass="td_value"/>
                        </t:panelGroup>
                        <h:outputText value="#{resourcesBundle.candidate_wrk_center_label}" styleClass="divtext"/>
                        <t:panelGroup colspan="3">
                            <t:inputTextarea disabled="true" tabindex="3" cols="35" rows="1"
                                             value="#{printListBean.workCenterName}" forceId="true"
                                             id="candidate_wrk_center" styleClass="td_value"/>
                        </t:panelGroup>
                        <h:outputText value="#{resourcesBundle.candidate_cader_label}"/>
                        <t:inputText disabled="true" value="#{printListBean.suggestedCaderName}" styleClass="td_value"/>
                        <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}"/>
                        <t:inputText disabled="true" value="#{printListBean.suggestedGroupName}" styleClass="td_value"/>
                        <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}"/>
                        <t:inputText disabled="true" value="#{printListBean.suggestedDegreeName}"
                                     styleClass="td_value"/>
                        <t:outputLabel value="#{resourcesBundle.raisesCount}"/>
                        <t:inputText disabled="true" value="#{printListBean.suggestedRaiseName}" styleClass="td_value"/>
                        <h:outputText value="#{resourcesBundle.total_reward_suggested}"
                                      styleClass="divtext nowrap_txt"/>
                        <t:panelGroup colspan="3">
                            <t:inputText styleClass="td_value"
                                         value="#{pageBeanName.pageDTO.empExtraDataValueDTO.suggestedTotalReward}"
                                         disabled="true"/>
                        </t:panelGroup>
                        <%--<h:outputText value="#{resourcesBundle.orderType}"/>--%>
                        <%--<t:panelGroup colspan="3">
                            <t:inputTextarea disabled="true" tabindex="2"
                                             value="#{pageBeanName.pageDTO.hireStagesDTO.name}" cols="35" rows="1"
                                             forceId="true" id="order_name" styleClass="td_value"/>
                        </t:panelGroup>--%>
                        <h:outputText value="#{resourcesBundle.candidate_suggestion_job_label}"
                                      styleClass="divtext nowrap_txt"/>
                        <t:panelGroup id="suggestedJobPanelGrp" forceId="true" colspan="3">
                            <t:inputTextarea tabindex="3" cols="35" rows="1" value="#{printListBean.suggestedJobValue}"
                                             styleClass="td_value" disabled="true"/>
                        </t:panelGroup>
                        <h:outputText value="#{resourcesBundle.ministry_notes_label}"/>
                        <t:panelGroup colspan="3">
                            <t:inputTextarea disabled="true"
                                             value="#{pageBeanName.pageDTO.empExtraDataValueDTO.ministryNotes}"
                                             tabindex="6" cols="35" rows="4" forceId="true" styleClass="td_value"
                                             id="ministry_notes"/>
                        </t:panelGroup>
                        <t:panelGroup colspan="4" style="background-color:#ffffff;" >
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="${shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
                </htm:td>
                <htm:td styleClass="group_title">
                    <t:outputLabel value=" #{resourcesBundle.Data_of_approved_Job}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
             
            <htm:tr>
                <htm:td colspan="2" height="1">
                    <htm:img src="${shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/>
                </htm:td>
            </htm:tr>
        </htm:table>
    </t:panelGroup>
                        <h:outputText value="#{resourcesBundle.selected_job_label}"/>
                        <t:panelGroup colspan="3">
                            <t:inputTextarea cols="35" rows="1" value="#{printListBean.acceptedJobValue}"
                                             styleClass="td_value" disabled="true"/>
                        </t:panelGroup>
                           <t:panelGroup colspan="4" style="background-color:#ffffff;" >
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="${shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
                </htm:td>
                <htm:td styleClass="group_title">
                    <t:outputLabel value=" #{resourcesBundle.degreeEquivalentData}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
             
            <htm:tr>
                <htm:td colspan="2" height="1">
                    <htm:img src="${shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/>
                </htm:td>
            </htm:tr>
        </htm:table>
    </t:panelGroup>
                      
                        <h:outputText value="#{resourcesBundle.candidate_cader_label}"/>
                        <t:inputText disabled="true" value="#{printListBean.acceptedCaderName}" tabindex="11"
                                     styleClass="td_value"/>
                        <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}"/>
                        <t:inputText disabled="true" value="#{printListBean.acceptedGroupName}" tabindex="11"
                                     styleClass="td_value"/>
                        <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}"/>
                        <t:inputText disabled="true" value="#{printListBean. acceptedDegreeName}" tabindex="11"
                                     styleClass="td_value"/>
                        <h:outputText value="#{resourcesBundle.raisesCount}"/>
                        <t:inputText disabled="true" value="#{printListBean.acceptedRaiseName}" tabindex="11"
                                     styleClass="td_value"/>
                        <h:outputText value="#{resourcesBundle.total_reward_accepted}" styleClass="divtext"/>
                        <t:panelGroup colspan="3">
                            <t:inputText styleClass="td_value" disabled="true"
                                         value="#{pageBeanName.pageDTO.empExtraDataValueDTO.acceptedTotalReward}"/>
                        </t:panelGroup>
                        <h:outputText value="#{resourcesBundle.civil_Service_Notes}"/>
                        <t:panelGroup colspan="3">
                            <t:inputTextarea disabled="true" tabindex="14" cols="35" rows="4" forceId="true"
                                             styleClass="td_value" id="jobs_opinion_notes"
                                             value="#{pageBeanName.civilServiceNotes}"/>
                        </t:panelGroup>
                    </t:panelGrid>
                    <t:panelGroup styleClass="footer" style="display:block;">
                        <t:outputText styleClass="copyrights" value="#{resourcesBundle.copyrights}"/>
                    </t:panelGroup>
                </t:panelGroup>
            </t:panelGroup>
        </t:aliasBean>
        <script type="text/javascript">
          resize();

          function resize() {
              var text = document.getElementById('choice_dept_notes');
              text.style.height = 'auto';
              text.style.height = text.scrollHeight + 'px';
          }
        </script>
    </h:form>
</f:view>