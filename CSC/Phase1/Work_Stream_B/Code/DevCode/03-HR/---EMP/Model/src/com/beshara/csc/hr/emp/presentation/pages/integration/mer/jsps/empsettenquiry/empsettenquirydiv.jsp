<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>.dataT-With-7-row-filter{ height: 160px !important;}</htm:style>
<t:messages showDetail="true"/>
<f:loadBundle basename="com.beshara.csc.hr.mer.integration.presentation.resources.integration" var="intgResource"/>
<t:panelGroup style="display:block;text-align:center;">
    <h:outputText value="#{intgResource.Settlement_Div_Title}" styleClass="popup_inner_title"/>
</t:panelGroup>
<t:panelGrid forceId="true" id="intg_cnt1Panel" columns="4" width="100%" rowClasses="row01,row02" cellspacing="0"
             cellpadding="0">
    <h:outputText value="#{intgResource.CivilID}" styleClass="divtext"/>
    <t:inputText forceId="true" id="intg_CivilIdAdd" styleClass="textbox"
                 value="#{empSettEnquiryHelperBeanName.civilId}" disabled="true"/>
    <h:outputText value="#{intgResource.EmpName}" styleClass="divtext"/>
    <t:inputText styleClass="textboxlarge" forceId="true" disabled="true" id="intg_EmpName" style="width: 232px;"
                 value="#{empSettEnquiryHelperBeanName.employeesDTO.name}"/>
    <h:outputText value="#{intgResource.setType}" styleClass="divtext"/>
    <t:panelGroup colspan="2" forceId="true" id="radioPanel">
        <t:selectOneMenu forceId="true" id="settlemenetTypeMenu" styleClass="DropdownboxMedium"
                         value="#{empSettEnquiryHelperBeanName.settlemenetType}">
            <t:selectItems value="#{empSettEnquiryHelperBeanName.settelementTypeList}" itemLabel="#{setType.name}"
                           itemValue="#{setType.code.key}" var="setType"/>
            <a4j:support actionListener="#{empSettEnquiryHelperBeanName.checkSettlementType}" event="onchange"
                         reRender="intg_dataT_data_panel__settDetailingPanal,totalSettId"/>
        </t:selectOneMenu>
    </t:panelGroup>
    <t:panelGroup>
        <t:outputText value="#{intgResource.With_month_salary}" styleClass="divtext"/>
        <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
        <t:inputText forceId="true" id="intg_monthMenu" disabled="true" styleClass="textboxsmall" style="width: 80px;"
                     value="#{empSettEnquiryHelperBeanName.monthName}"/>
        <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
        <h:outputText value="#{intgResource.year}" styleClass="divtext"/>
        <f:verbatim>&nbsp;&nbsp;&nbsp;</f:verbatim>
        <t:inputText forceId="true" id="intg_yearMenu" disabled="true" styleClass="textboxsmall" style="width: 80px;"
                     value="#{empSettEnquiryHelperBeanName.year}"/>
    </t:panelGroup>
</t:panelGrid>
<t:panelGroup forceId="true" id="intg_dataT_data_panel__settDetailingPanal">
    <t:panelGroup>
        <htm:table width="100%">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
                </htm:td>
                <htm:td styleClass="group_title">
                    &nbsp; 
                    <t:outputLabel value="#{intgResource.caption_txt}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
             
            <htm:tr>
                <htm:td colspan="2" height="1">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%"
                             height="1"/>
                </htm:td>
            </htm:tr>
        </htm:table>
    </t:panelGroup>
    <t:dataTable id="intg_dataT_data" var="list" rows="#{shared_util.noOfTableRows}" style="display: block;height: 114px;overflow: auto;"
                 value="#{empSettEnquiryHelperBeanName.settlemenetType == empSettEnquiryHelperBeanName.settWithDecsType ? empSettEnquiryHelperBeanName.myTableData : null}"
                 rowStyleClass="#{ empSettEnquiryHelperBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                 forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" renderedIfEmpty="false"
                 binding="#{empSettEnquiryHelperBeanName.myDataTable}" footerClass="grid-footer"
                 styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2"
                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                 width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true"
                 sortColumn="#{empSettEnquiryHelperBeanName.sortColumn}"
                 sortAscending="#{empSettEnquiryHelperBeanName.ascending}"
                 rowOnMouseOver="javascript:showDescisionTitleToolTip(event , '#{intgResource.dec_mer_int_notes}' , '#{list.empDecDTO.decisionTitle}' );"
                 rowOnMouseOut="hideTip();">
        <t:column id="intg_single_select_column" styleClass="standardTable_Header3"  >
            <%/*rendered="#{empSettEnquiryHelperBeanName.singleSelection}"*/%>
            <f:facet name="header"></f:facet>
            <t:selectOneRadio styleClass="radioButton_DataTable" id="intg_chk"
                              value="#{empSettEnquiryHelperBeanName.selectedRadio}" onmousedown="toggleRadio(this);"
                              onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                <a4j:support event="onclick" actionListener="#{empSettEnquiryHelperBeanName.selectedRadioButton}"
                             reRender="subPanalGroupTorenderAllTable,totalSettId"/>
            </t:selectOneRadio>
        </t:column>
        <%-- decision type--%>
        <t:column id="intg_typename_column" sortable="false" >
            <f:facet name="header">
                <t:outputText id="sort_empDecDTO-typesDTO-name" forceId="true" styleClass="headerLink"
                              value="#{intgResource.dec_type}"/>
            </f:facet>
            <h:outputText id="typename_name" value="#{list.empDecDTO.typesDTO.name}"/>
        </t:column>
        <t:column id="intg_subjectname_column" sortable="false" >
            <f:facet name="header">
                <t:outputText id="sort_empDecDTO-subjectsDTO-name" forceId="true" styleClass="headerLink"
                              value="#{intgResource.dec_subject}"/>
            </f:facet>
            <h:outputText id="subjectname_name" value="#{list.empDecDTO.subjectsDTO.name}"/>
        </t:column>
        <%-- decision number--%>
        <t:column id="intg_decisionNumber_column" sortable="false"  >
            <f:facet name="header">
                <t:outputText id="sort_empDecDTO-code-decisionNumber" forceId="true" styleClass="headerLink"
                              value="#{intgResource.dec_no}"/>
            </f:facet>
            <h:outputText id="dec_number" value="#{list.empDecDTO.regNum}"/>
        </t:column>
        <%-- decision year--%>
        <t:column id="intg_decisionYear_column" sortable="false"  >
            <%/*sortable="false"*/%>
            <f:facet name="header">
                <t:outputText id="sort_empDecDTO-yearsDTO-code-key" forceId="true" styleClass="headerLink"
                              value="#{intgResource.dec_make_year}"/>
            </f:facet>
            <h:outputText id="decisionDate_txt" value="#{list.empDecDTO.yearsDTO.code.key}"/>
        </t:column>
        <t:column id="intg_decisionYear_column2" sortable="false"  >
            <%/*sortable="false"*/%>
            <f:facet name="header">
                <t:outputText id="sort_empDecDTO-yearsDTO-code-key2" forceId="true" styleClass="headerLink"
                            value="#{intgResource.month_of_settl}"/>
            </f:facet>
            <h:outputText id="decisionDate_txt"   converter="MonthYearConverter"     value="#{list.setttrxDateSql}"/>
        </t:column>
        <%-- decision Maker name--%>
        <t:column id="intg_decisionMakername_column" sortable="false"  >
            <f:facet name="header">
                <t:outputText id="sort_empDecDTO-decisionMakerTypesDTO-name" forceId="true" styleClass="headerLink"
                              value="#{intgResource.dec_source}"/>
            </f:facet>
            <h:outputText id="decisionMakername_txt" value="#{list.empDecDTO.decisionMakerTypesDTO.name}"/>
        </t:column>
        <t:column id="intg_val_column" sortable="false"  >
            <f:facet name="header">
                <t:outputText id="sort_settelmentValue" forceId="true" styleClass="headerLink"
                              value="#{intgResource.total_settlement}"/>
            </f:facet>
            <h:outputText id="status_txt" value="#{list.settelmentValue}"/>
        </t:column>
        <t:column id="intg_status_column" sortable="false"  >
            <f:facet name="header">
                <t:outputText id="sort_salSettlmentStatusDTO-name" forceId="true" styleClass="headerLink"
                              value="#{globalResources.status}"/>
            </f:facet>
            <h:outputText id="status_txt" value="#{list.salSettlmentStatusDTO.name}"/>
        </t:column>
    </t:dataTable>
    <t:panelGroup id="subPanalGroupTorenderAllTable">
    <t:panelGroup styleClass="fullWidthScroll190" style="max-height: 90px;"
                  rendered="#{empSettEnquiryHelperBeanName.settlemenetType != empSettEnquiryHelperBeanName.settWithDecsType && empSettEnquiryHelperBeanName.settlemenetType != 8 && empSettEnquiryHelperBeanName.settlemenetType != 9}">
        <t:dataTable id="sett_Header_dataT_data" var="list" value="#{empSettEnquiryHelperBeanName.vacList}"
                     forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" renderedIfEmpty="false"
                     binding="#{empSettEnquiryHelperBeanName.vacTable}" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="false"
                     rowOnMouseOver="javascript:doTooltip(event,'#{intgResource.vac_emp_from_date}:#{list.vacEmployeeVacationsDTO.fromDate},#{intgResource.vac_emp_to_date}:#{list.vacEmployeeVacationsDTO.untilDate}');"
                     rowOnMouseOut="hideTip();">
            <t:column id="radio_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk2"
                                  value="#{empSettEnquiryHelperBeanName.vacSelectedRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{empSettEnquiryHelperBeanName.selectedVacRadioButton}"
                                 reRender="_set_divsettPnl,totalSettId"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="code_column" sortable="false" width="10%">
                <f:facet name="header">
                    <h:outputText id="sort_code-key_sett" value="#{intgResource.sett_code}" styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_sett_content_code_sett" value="#{list.code.key}"/>
            </t:column>
            <t:column id="vac_code_column" sortable="false" width="10%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEmployeeVacationsDTO-code-_sett" value="#{intgResource.vac_code}"
                                  styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_content__sett" value="#{list.vacEmployeeVacationsDTO.vacVacationTypesDTO.code.key}"/>
            </t:column>
            <t:column id="vac_name_column" sortable="false" width="20%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEmployeeVacationsDTO-vacVacationTypesDTO_sett"
                                  value="#{intgResource.vac_type}" styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_content_sett" value="#{list.vacEmployeeVacationsDTO.vacVacationTypesDTO.name}"
                              styleClass="inputTextForDataTable"/>
            </t:column>
            <t:column id="vac_emp_from_date" sortable="false" width="10%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEm_sett" value="#{intgResource.from_date}" styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_fromDate" value="#{list.newFromDate}" converter="TimeStampConverter"/>
            </t:column>
            <t:column id="vac_emp_to_date" sortable="false" width="10%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEmployeeV_sett" value="#{intgResource.until_date}"
                                  styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_untilDat_sette" value="#{list.newUntilDate}" converter="TimeStampConverter"/>
            </t:column>
           <t:column id="vac_emp_to_date2" sortable="false" width="10%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEmployeeV_set2t" value="#{intgResource.num_vac_days}"
                                  styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_untilDat_sette2" value="#{list.numOfDays+1}"  />
            </t:column>
            <t:column id="vacTottalSettelemnt_column" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="sort_vacTottalSettelemnt" value="#{intgResource.total_settlement}"
                                  styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_content__vacTottalSettelemnt" value="#{list.vacTottalSettelemnt}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <t:panelGrid forceId="true" id="Sum_Vac_total_settlement_panal" columns="3" style="center"
     rendered="#{empSettEnquiryHelperBeanName.settlemenetType != empSettEnquiryHelperBeanName.settWithDecsType && empSettEnquiryHelperBeanName.settlemenetType != 8 && empSettEnquiryHelperBeanName.settlemenetType != 9}">
        <t:panelGroup style="margin-right:478px !important;display: block;">
            <h:outputText value="#{intgResource.Sum_Vac_total_settlement}" styleClass="lable01"
                          style="font-weight:bold"/>
            <t:inputText forceId="true" id="Sum_Vac_total_settlement" styleClass="textbox" style="width:140px;"
                         value="#{empSettEnquiryHelperBeanName.sumVacSettlementStr}" disabled="true"/>
        </t:panelGroup>
    </t:panelGrid>
     <t:panelGrid forceId="true" id="Sum_Vac_total_settlement_panal2" columns="3" style="center"
     rendered="#{empSettEnquiryHelperBeanName.settlemenetType == empSettEnquiryHelperBeanName.settWithDecsType }">
        <t:panelGroup style="margin-right: 466px !important;display: block;">
            <h:outputText value="#{intgResource.Sum_Vac_total_settlement}" styleClass="lable01"
                          style="font-weight:bold"/>
            <t:inputText forceId="true" id="Sum_Vac_total_settlement2" styleClass="textbox" style="width:140px;" converter="DoubleThreeDecimalMaskConverter"
                         value="#{empSettEnquiryHelperBeanName.totalSattementDes}" disabled="true"/>
        </t:panelGroup>
    </t:panelGrid>
    <!--=================================LATE=======================================-->
    <t:panelGroup styleClass="fullWidthScroll190" style="max-height: 90px;"
                  rendered="#{empSettEnquiryHelperBeanName.settlemenetType == 8 || empSettEnquiryHelperBeanName.settlemenetType == 9}">
        <t:dataTable id="sett_Header_dataT_dataa" var="list" value="#{empSettEnquiryHelperBeanName.absenceList}"
                     forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" renderedIfEmpty="false"
                     binding="#{empSettEnquiryHelperBeanName.absenceTable}" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="false">
            <t:column id="radio_columna" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk2a"
                                  value="#{empSettEnquiryHelperBeanName.absenceSelectedRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick"
                                 actionListener="#{empSettEnquiryHelperBeanName.selectedAbsenceRadioButton}"
                                 reRender="_set_divsettPnl,totalSettId"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="code_columna" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="sort_code-key_setta" value="#{intgResource.sett_code}" styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_sett_content_code_setta" value="#{list.code.key}"/>
            </t:column>
            <t:column id="vac_code_columna" sortable="false" width="25%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEmployeeVacationsDTO-code-_sett" value="#{intgResource.setType}"
                                  styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_content__setta" value="#{list.salSettlmentTypesDTO.name}"/>
            </t:column>
            <t:column id="vac_emp_from_datesaa" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEm_setta" value="#{intgResource.LATE_DAYS}" styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_fromDateaaa" value="#{list.lateDays}"/>
            </t:column>
            <t:column id="vac_name_columna" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEmployeeVacationsDTO-vacVacationTypesDTO_setta"
                                  value="#{intgResource.LATE_MONTH}" styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_content_setta" value="#{list.lateMonth}" styleClass="inputTextForDataTable"/>
            </t:column>
            <t:column id="vac_emp_from_datea" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEm_setta" value="#{intgResource.LATE_YEAR}" styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="vac_fromDatea" value="#{list.lateYear}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <!--================================= END LATE =======================================-->
    <t:panelGrid columns="1"
                 rendered="#{ (empSettEnquiryHelperBeanName.vacListSize == 0 &&  empty empSettEnquiryHelperBeanName.absenceList &&  empSettEnquiryHelperBeanName.settlemenetType != empSettEnquiryHelperBeanName.settWithDecsType) || (empty empSettEnquiryHelperBeanName.myTableData && empSettEnquiryHelperBeanName.settlemenetType == empSettEnquiryHelperBeanName.settWithDecsType ) }"
                 align="center">
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </t:panelGrid>
    <t:panelGrid id="_set_divsettPnl" forceId="true" border="0" cellpadding="0" cellspacing="0" width="100%">
        <t:panelGroup style="height:100%;background-color:#ffffff;"
                      rendered="#{ !empty empSettEnquiryHelperBeanName.settlementDetailList || !empty empSettEnquiryHelperBeanName.merDedList}">
            <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
                <htm:tr>
                    <htm:td width="9">
                        <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
                         
                        <%-- <h:graphicImage url="/app/media/images/op_arrow.jpg" width="9" height="9"/>--%>
                    </htm:td>
                    <htm:td styleClass="group_title">
                        <t:outputLabel value="#{intgResource.retroactive_vac_cashReplacement_sett_details}"
                                       styleClass="lable01"/>
                    </htm:td>
                </htm:tr>
                 
                <htm:tr>
                    <htm:td colspan="2" height="3" valign="top">
                        <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%"
                                 height="1"/>
                         
                        <%-- <h:graphicImage url="/app/media/images/seprator_group.jpg" width="100%" height="1"/>--%>
                    </htm:td>
                </htm:tr>
            </htm:table>
        </t:panelGroup>
        <t:panelGroup forceId="true" id="sett_detail_qry_panel" styleClass="dataT-With-7-row-filter-and-collapse"
                      style="height: 90px !important;" >
            <t:dataTable id="sett_detail_data" var="list" value="#{empSettEnquiryHelperBeanName.settlementDetailList}"
                         forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" renderedIfEmpty="false"
                         footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2"
                         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                         width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="false">
                <t:column id="_set_divguideCode_column" sortable="false" width="5%">
                     <f:facet name="header">
                        <t:commandLink  forceId="true" styleClass="headerLink"
                                       value="#{intgResource.code}" disabled="true"
                                        >
                            <a4j:support event="onclick" id="sort_guideCode" actionListener="#{empSettEnquiryHelperBeanName.sortDataTableSettlementDetailList}"
                             reRender="subPanalGroupTorenderAllTable"/>
                              <t:graphicImage value="#{(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/ascending-arrow.gif' :''}"
                                            border="0"/>
                            <t:graphicImage value="#{!(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/descending-arrow.gif' :''}"
                                            border="0"/>
                        </t:commandLink> 
                         
                    </f:facet> 
                    <h:outputText id="content_code_set_div" value="#{list.guideCode}"/>
                </t:column>
                <t:column id="_set_divname_column" sortable="false" width="25%">
                    <f:facet name="header">
                    <t:commandLink  forceId="true" styleClass="headerLink"
                                       value="#{intgResource.name}" disabled="true"
                                        >
                            <a4j:support event="onclick" id="sort_guideName" actionListener="#{empSettEnquiryHelperBeanName.sortDataTableSettlementDetailList}"
                             reRender="subPanalGroupTorenderAllTable"/>
                              <t:graphicImage value="#{(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/ascending-arrow.gif' :''}"
                                            border="0"/>
                            <t:graphicImage value="#{!(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/descending-arrow.gif' :''}"
                                            border="0"/>
                        </t:commandLink> 
                       
                    </f:facet>
                    <h:inputText id="content_n_set_divame"
                                 onmouseover="doTooltip(event,' #{list.salEmpSettelmentsDTO.guideName}')"
                                 onmouseout="hideTip()" value="#{list.salEmpSettelmentsDTO.guideName}" readonly="true"
                                 styleClass="inputTextForDataTable"/>
                </t:column>
                <t:column id="settlment_valu_set_dive" sortable="false" width="10%">
                    <f:facet name="header">
                     <t:commandLink  forceId="true" styleClass="headerLink"
                                       value="#{intgResource.settlment_value}" disabled="true"
                                        >
                            <a4j:support event="onclick" id="sort_value" actionListener="#{empSettEnquiryHelperBeanName.sortDataTableSettlementDetailList}"
                             reRender="subPanalGroupTorenderAllTable"/>
                              <t:graphicImage value="#{(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/ascending-arrow.gif' :''}"
                                            border="0"/>
                            <t:graphicImage value="#{!(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/descending-arrow.gif' :''}"
                                            border="0"/>
                        </t:commandLink> 
                         
                    </f:facet>
                    <h:outputText id="total_value_set_div" value="#{list.value}" converter="DoubleThreeDigitConverter"/>
                </t:column>
                <t:column id="_set_div-stlstatusCode_column" sortable="false" width="5%">
                    <f:facet name="header">
                        <h:outputText value="#{intgResource.status}" styleClass="headerLink"/>
                    </f:facet>
                    <t:graphicImage id="graph1__set_div" value="/app/media/images/green-circle.gif"
                                    onmouseout="hideTip()"
                                    onmouseover="doTooltip(event,' #{list.auditStatus !=null ? intgResource.signed :  list.salEmpSettelmentsDTO.salSettlmentStatusDTO.name}')"
                                    rendered="#{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.code.key=='1' || list.auditStatus!=null }"/>
                    <t:graphicImage id="grey_set_div" value="/app/media/images/grey-circle.gif" onmouseout="hideTip()"
                                    rendered="#{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.code.key=='-2'}"
                                    onmouseover="doTooltip(event,' #{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.name}')"/>
                    <t:graphicImage id="yellow_set_div"
                                    rendered="#{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.code.key=='-1'}"
                                    value="/app/media/images/yellow-circle.gif" onmouseout="hideTip()"
                                    onmouseover="doTooltip(event,' #{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.name}')"/>
                    <t:graphicImage id="red_set_div"
                                    rendered="#{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.code.key=='0' && list.auditStatus == null }"
                                    value="/app/media/images/red-circle.gif" onmouseout="hideTip()"
                                    onmouseover="doTooltip(event,' #{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.name}')"/>
                </t:column>
            </t:dataTable>
            <t:dataTable id="settDetailingList_data" var="sett" value="#{empSettEnquiryHelperBeanName.merDedList }"
                         rowIndexVar="index" renderedIfEmpty="false" footerClass="grid-footer" styleClass="grid-footer"
                         headerClass="standardTable_Header" width="100%" align="top" dir="#{shared_util.pageDirection}"
                         preserveSort="true" sortColumn="#{empSettEnquiryHelperBeanName.sortColumn}" 
                         sortAscending="#{empSettEnquiryHelperBeanName.ascending}">
                <t:column id="merDedcode_column" sortable="false" width="10%">
                    <f:facet name="header">
                    <t:commandLink  forceId="true" styleClass="headerLink"
                                       value="#{intgResource.code}" disabled="true"
                                       actionListener="#{empSettEnquiryHelperBeanName.sortDataTable}">
                            <a4j:support event="onclick" id="sort_elmGuideCode" actionListener="#{empSettEnquiryHelperBeanName.sortDataTable2}"
                             reRender="subPanalGroupTorenderAllTable"/>
                              <t:graphicImage value="#{(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/ascending-arrow.gif' :''}"
                                            border="0"/>
                            <t:graphicImage value="#{!(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/descending-arrow.gif' :''}"
                                            border="0"/>
                        </t:commandLink>
                        <%--<t:outputText id="merDedsort_code" forceId="true" styleClass="headerLink"
                                      value=""/>--%>
                    </f:facet>
                    <h:outputText id="merDedcontent_code" value="#{sett.elmGuideCode}"/>
                </t:column>
                <t:column id="merDedname_column" sortable="false" width="30%">
                    <f:facet name="header">
                    <t:commandLink  forceId="true" styleClass="headerLink"
                                       value="#{intgResource.name}" disabled="true"
                                       actionListener="#{empSettEnquiryHelperBeanName.sortDataTable}">
                            <a4j:support event="onclick" id="sort_name" actionListener="#{empSettEnquiryHelperBeanName.sortDataTable2}"
                             reRender="subPanalGroupTorenderAllTable"/>
                               <t:graphicImage value="#{(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/ascending-arrow.gif' :''}"
                                            border="0"/>
                            <t:graphicImage value="#{!(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/descending-arrow.gif' :''}"
                                            border="0"/>
                        </t:commandLink>
                        
                    </f:facet>
                    <h:outputText id="merDedcontent_name" value="#{sett.name}"/>
                </t:column>
                <t:column id="deductionvalue_column" sortable="false" width="10%">
                    <f:facet name="header">
                    <t:commandLink  forceId="true" styleClass="headerLink"
                                       value="#{intgResource.value}" disabled="true"
                                       actionListener="#{empSettEnquiryHelperBeanName.sortDataTable}">
                            <a4j:support event="onclick" id="sort_value" actionListener="#{empSettEnquiryHelperBeanName.sortDataTable2}"
                             reRender="subPanalGroupTorenderAllTable"/>
                               <t:graphicImage value="#{(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/ascending-arrow.gif' :''}"
                                            border="0"/>
                            <t:graphicImage value="#{!(empSettEnquiryHelperBeanName.sortAscending2) ? '/app/media/images/descending-arrow.gif' :''}"
                                            border="0"/>
                        </t:commandLink>
                         
                    </f:facet>
                    <h:outputText id="merDedcontent_value" value="#{sett.value}" converter="DoubleThreeDigitConverter"/>
                </t:column>
            </t:dataTable>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid forceId="true" id="totalSettId" columns="3" style="center">
        <t:panelGroup style="width: 260px !important;display: block;"/>
        <t:commandButton styleClass="cssButtonSmaller" id="backButtonCustomDiv2" type="button" forceId="true"
                         value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.customDiv2);"/>
        <t:panelGroup style="margin-right: 187px !important;display: block;">
            <h:outputText value="#{intgResource.total_settlement}" styleClass="lable01" style="font-weight:bold"
                          rendered="#{ !empty empSettEnquiryHelperBeanName.settlementDetailList || !empty empSettEnquiryHelperBeanName.merDedList}"/>
            <t:inputText forceId="true" id="intg_totalSett" styleClass="textbox" style="width:140px;"
                         value="#{empSettEnquiryHelperBeanName.totalSettlementStr}" disabled="true"
                         rendered="#{ !empty empSettEnquiryHelperBeanName.settlementDetailList || !empty empSettEnquiryHelperBeanName.merDedList}"/>
        </t:panelGroup>
    </t:panelGrid>
    </t:panelGroup>
</t:panelGroup>
<t:saveState value="#{empSettEnquiryHelperBeanName.myTableData}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.civilId}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.employeesDTO}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.month}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.year}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.settlemenetType}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.retroactive_SettType}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.settWithDecsType}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.monthName}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.settOnlyList}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.merDedList}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.vacList}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.vacListSize}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.totalSettlmentValue}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.vacSelectedRadio}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.selSalEmpSettelmentsDTO}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.settelementTypeList}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.settlementDetailList}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.totalSettlement}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.totalSettlementStr}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.absenceSelectedRadio}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.absenceList}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.absenceListSize}"/>
<t:saveState value="#{empSettEnquiryHelperBeanName.totalSattementDes}"/>
 <t:saveState value="#{empSettEnquiryHelperBeanName.sortAscending2}"/>
<script type="text/javascript">
  var ctxPath = '<%=request.getContextPath()%>';

  function showDescisionTitleToolTip(event, title1, txt1) {
      var line1 = title1 + ' : ' + txt1;
      doTooltip(event, line1);
  }
</script>