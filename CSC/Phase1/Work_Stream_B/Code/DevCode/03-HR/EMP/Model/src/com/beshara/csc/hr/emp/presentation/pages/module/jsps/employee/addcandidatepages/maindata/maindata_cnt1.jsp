<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<!--start row1-->
<t:panelGroup colspan="4" style="background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td width="9">
                <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
                <t:outputLabel value=" #{resourcesBundle.maindata}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
<t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="col1_WC" cellspacing="0" width="100%"
             forceId="true" id="employeeMainDataPanel" columns="4" align="cenetr" border="0">
    <h:outputText value="#{resourcesBundle.civil_id}" styleClass="lable01"/>
    <t:panelGroup >
    
      
        <t:inputText onkeypress="enabelIntegerOnly(this);goFilterByCivilButton(event);" 
                     disabled="#{detailBeanName.civilExist}" maxlength="12" forceId="true" tabindex="1" id="CivilIdAdd"
                     value="#{detailBeanName.civilId}" styleClass="textboxmedium" onkeydown="onKeyDownFirstElement(event,'filterByCivilButton','BackButtonManyToMany')"/>
        <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:panelGroup forceId="true" id="btnPnlGrp">
            <t:commandButton forceId="true" id="filterByCivilButton" type="button" value="#{globalResources.Available}"
                             styleClass="cssButtonSmall" onclick="resetMsgInAdd();return searchAndvalidateEmp('requiredFields');"
                             tabindex="2"
                             rendered="#{!detailBeanName.civilExist}"/>
            <a4j:jsFunction name="searchLines" action="#{detailBeanName.filterByCivilId}"
                            reRender="pnlhiredateren,pnlhiredateren_input,budgetTypePanel,bgtNamePanel,bgtInputId,workCenterName,employees_hiretypes,emp_minFileNo,hireDateCalendar,workCenterbtn,employees_budgetType,employees_contractType,cader_dropdown,employees_group,employees_degree,raisesCountId,showSupToJob_btn,navigationpanel,scriptGenerator,msgPnlGrp,btnPnlGrp,CivilIdAdd, employee_name_panel, employee_gender_type, employee_maritalStatus, employee_nationality, employee_nationality,employees_hiretypes_PG,QulDataTable,dataT_data_panel,contractTypePanel,witoutQualFlag"
                            oncomplete="setFocusFirstElem();"/>
            <a4j:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}"
                               tabindex="3"
                               oncomplete="setFocusFirstElem();" rendered="#{detailBeanName.civilExist}"
                               styleClass="cssButtonSmall" actionListener="#{detailBeanName.reSetData}"
                               reRender="renderErrorHireDatePnlGrp,pnlhiredateren,pnlhiredateren_input,jobpnlgroup,budgetTypePanel,bgtNamePanel,bgtInputId,workCenterName,employees_hiretypes,emp_minFileNo,hireDateCalendar,workCenterbtn,employees_budgetType,employees_contractType,cader_dropdown,employees_group,employees_degree,raisesCountId,showSupToJob_btn,navigationpanel,scriptGenerator, btnPnlGrp, CivilIdAdd, employee_name_panel, employee_gender_type, employee_nationality, employee_maritalStatus, employee_nationality,QulDataTable,dataT_data_panel,employees_hiretypes_PG"/>
        </t:panelGroup>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator componentToValidate="CivilIdAdd" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"
                                   uniqueId="CivilIdAddUniqueId" group="requiredFields"/>
        <c2:regularExpressionValidator componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/"
                                       errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false"
                                       display="dynamic" group="requiredFields" />
        <t:panelGroup forceId="true" id="msgPnlGrp">
            <t:outputText forceId="true" id="invalCivilID" value="#{resourcesBundle.civilId_not_found}"
                          rendered="#{!detailBeanName.validCivilId}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="empHired_error" value="#{resourcesBundle.emp_Hired}"
                          rendered="#{detailBeanName.empHired}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="emp_Candidate_error" value="#{resourcesBundle.emp_Candidate}"
                          rendered="#{detailBeanName.empCandidate}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="emp_Nationality_error" value="#{resourcesBundle.emp_Nationality}"
                          rendered="#{detailBeanName.empNationality}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="data_notComplete_error" value="#{detailBeanName.dataNotcompleteErrorMSG}"
                          rendered="#{detailBeanName.dataNotcomplete}" styleClass="errMsg"/>
        </t:panelGroup>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.candidate_name}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="employee_name_panel">
        <t:inputText forceId="true" id="employees_nam1" styleClass="textboxmedium"
                     value="#{detailBeanName.candidateName}" tabindex="4" disabled="true"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.gender}" styleClass="lable01"/>
    <t:inputText forceId="true" id="employee_gender_type" tabindex="5" styleClass="textboxmedium"
                 value="#{detailBeanName.genderTypeName}" disabled="true"/>
    <h:outputText value="#{resourcesBundle.status}" styleClass="lable01"/>
    <t:inputText forceId="true" id="employee_maritalStatus" tabindex="6" styleClass="textboxmedium"
                 value="#{detailBeanName.maritalStatus}" disabled="true"/>
    <h:outputText value="#{resourcesBundle.nationalityType}" styleClass="lable01"/>
    <t:inputText forceId="true" id="employee_nationality" tabindex="7" styleClass="textboxmedium"
                 value="#{detailBeanName.nationality}" disabled="true"/>
</t:panelGrid>
<t:panelGroup colspan="4" style="background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td width="9">
                <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
                <t:outputLabel value=" #{resourcesBundle.job_data}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
<t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="col1_WC" cellspacing="0" width="100%"
             forceId="true" id="jobDataPanel" columns="4" align="cenetr" border="0">
    <h:outputText value="#{resourcesBundle.hiretype}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="employees_hiretypes_PG">
        <t:selectOneMenu onchange="changeHireType();" 
                         forceId="true" id="employees_hiretypes" tabindex="8" styleClass="DropdownboxMedium2"
                         value="#{detailBeanName.selectedHireType}" disabled="#{!detailBeanName.civilExist}">
            <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                          itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{pageBeanName.hireTypesList}" itemLabel="#{hireTypesList.concatenatedName}"
                           itemValue="#{hireTypesList.code.key}" var="hireTypesList"/>
            <a4j:jsFunction action="#{detailBeanName.setHireTypeCodeOnChange}" name="changeHireType"
                            reRender="hireTypePnlGrp"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <t:panelGroup forceId="true" id="hireTypePnlGrp">
            <t:outputText value="#{resourcesBundle.hirtype_notRelatedByCond}" styleClass="errMsg" id="outputErrMsg"
                          forceId="true" rendered="#{detailBeanName.renderedErrorMsgHirType}"/>
        </t:panelGroup>
        <c2:compareValidator componentToValidate="employees_hiretypes" componentToCompare="virtualvalueId"
                             operator="not" errorMessage="#{globalResources.missingField}" highlight="false"
                             display="dynamic" group="hireTypeGroup" />
                             
        <%--<c2:requiredFieldValidator componentToValidate="employees_hiretypes" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"
                                   uniqueId="CivilIdAddUniqueId" group="requiredFields"/>--%>
                                   
    </t:panelGroup>
    <%--<h:outputText value="#{resourcesBundle.ministry_fileNo}" styleClass="lable01"/>--%>
    <%--<t:panelGroup forceId="true" id="filnumPnlGp">
        <t:inputText forceId="true" maxlength="20" id="emp_minFileNo" tabindex="9" styleClass="textboxmedium"
                     value="#{detailBeanName.fileNo}" onkeypress="goChangeFilNumber(event);" disabled="#{!detailBeanName.civilExist}"
                     onchange="changeFilNumberOnBlur();"></t:inputText>
        <a4j:jsFunction name="checkAboutFilNum" action="#{detailBeanName.checkAboutFilNum}" reRender="fileNomsgPnlGrp"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <t:panelGroup forceId="true" id="fileNomsgPnlGrp">
            <t:outputText forceId="true" id="renderEmpFilNumRedundant" value="#{resourcesBundle.empfilNumRedundantinMin}"
                          rendered="#{detailBeanName.renderEmpFilNumRedundant}" styleClass="errMsg" style="white-space: nowrap;"/>
            <t:outputText forceId="true" id="renderCandFilNumRedundant" value="#{resourcesBundle.candFilNumRedundant}"
                          rendered="#{detailBeanName.renderCandFilNumRedundant}" styleClass="errMsg"/>
        </t:panelGroup>
    </t:panelGroup>--%>
    
    <t:panelGroup  colspan="2" />
    
    
    <h:outputText value="#{resourcesBundle.employees_work_center}" styleClass="lable01"/>
    <t:panelGroup colspan="1" id="workCenterPanel" forceId="true">
        <t:inputText disabled="true" styleClass="textboxmedium" forceId="true" id="workCenterName"
                     value="#{detailBeanName.workCenterName}"/>
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        <a4j:commandButton value="#{globalResources.Available}" tabindex="11" action="#{detailBeanName.openWorkCentersIntegDiv}"
                           styleClass="cssButtonSmaller" oncomplete="showWorkCenterIntegrationDiv();" id="workCenterbtn" 
                           reRender="workCenterName,workCenterPnl,workcentersIntgSearchTbl,integrationDiv1" disabled="#{!detailBeanName.civilExist}"/>
    
      <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>
                <br/>
            </f:verbatim>
             <c2:requiredFieldValidator componentToValidate="workCenterName" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false" />
    </t:panelGroup>
    <h:outputText id="bgtoutputId" value="#{resourcesBundle.budget}" styleClass="lable01"/>
    <t:panelGroup id="bgtNamePanel" forceId="true">
        <t:inputText forceId="true" Id="bgtInputId" disabled="true" value="#{detailBeanName.bgtProgName}"
                     styleClass="textboxmedium"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.budgetType}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="budgetTypePanel">
        <t:selectOneMenu forceId="true" id="employees_budgetType" tabindex="12" styleClass="textboxmedium" style="width:200px;"
                         value="#{detailBeanName.bgtTypeKey}" disabled="#{!detailBeanName.civilExist}">
            <f:selectItem itemValue="#{detailBeanName.bgtTypeKey}" itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{detailBeanName.budgetTypeList}" itemLabel="#{budgetType.name}"
                           itemValue="#{budgetType.code.key}" var="budgetType"/>
        </t:selectOneMenu>
    </t:panelGroup>
      <t:panelGroup   colspan="2"  >
       <%--<t:selectBooleanCheckbox value="#{pageBeanName.pageDTO.witoutQualFlag}"
                            forceId="true" id="witoutQualFlag"   disabled="#{!detailBeanName.civilExist}"/>--%>
                                 
        <%--<h:outputText value="#{resourcesBundle.cand_without_qual}" styleClass="lable01" />--%>
        </t:panelGroup>
    <h:outputText value="#{resourcesBundle.contract_type}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="contractTypePanel" colspan="3">
        <t:selectOneMenu forceId="true" id="employees_contractType" tabindex="13" styleClass="textboxmedium" style="width:200px;"
                         value="#{detailBeanName.contractType}" onchange="fillCaderList();"
                         disabled="#{!detailBeanName.civilExist || (detailBeanName.civilExist && detailBeanName.disabledContractType)}">
            <f:selectItem itemValue="#{detailBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.select}"/>
            <f:selectItem itemValue="6" itemLabel="#{resourcesBundle.contracts}"/>
            <f:selectItem itemValue="7" itemLabel="#{resourcesBundle.Estana}"/>
            <f:selectItem itemValue="0" itemLabel="#{resourcesBundle.other}"/>
        </t:selectOneMenu>
        <a4j:jsFunction action="#{detailBeanName.fillCaderList}"
                        reRender="jobDataPanel"
                        name="fillCaderList"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.cader}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="caderPanel">
        <t:selectOneMenu forceId="true" id="cader_dropdown" tabindex="14" styleClass="textboxmedium" style="width:200px;"
                         value="#{detailBeanName.caderCode}" onchange="fillGroupList();" disabled="#{!detailBeanName.civilExist}">
            <f:selectItem itemValue="#{detailBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{detailBeanName.caderList}" itemLabel="#{cader.name}" itemValue="#{cader.code.key}"
                           var="cader"/>
        </t:selectOneMenu>
        <a4j:jsFunction action="#{detailBeanName.fillGroupList}" reRender="jobDataPanel" name="fillGroupList"/>
    </t:panelGroup>
    <%-- <t:inputText styleClass="textboxmedium" forceId="true" id="caderText" value="#{detailBeanName.caderName}"/>--%>
    <%-- <t:panelGroup colspan="2"> <a4j:commandButton value="#{globalResources.Available}"
         styleClass="cssButtonSmaller"
         reRender="lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn" <<<<<<<
         maindata_cnt1.jsp action="#{detailBeanName.showCaderListOfValuesDiv}"
         oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);settingFoucsAtLovDiv();"/> </t:panelGroup>--%>
    <h:outputText value="#{resourcesBundle.group}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="groupPanel">
        <t:selectOneMenu forceId="true" id="employees_group" tabindex="15" styleClass="DropdownboxMedium2"
                         value="#{detailBeanName.groupCode}" onchange="filterDegreesByGroup();" disabled="#{!detailBeanName.civilExist}">
            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{detailBeanName.groupList}" itemLabel="#{group.name}" itemValue="#{group.code.key}"
                           var="group"/>
        </t:selectOneMenu>
        <a4j:jsFunction action="#{detailBeanName.filterDegreesByGroup}" reRender="jobDataPanel"
                        name="filterDegreesByGroup"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.degree}" styleClass="lable01"/>
    <t:panelGroup id="degreePanel" forceId="true">
        <t:selectOneMenu forceId="true" id="employees_degree" tabindex="16" styleClass="DropdownboxMedium2"
                         value="#{detailBeanName.degreeCode}" onchange="filterRaisesByDegree();" disabled="#{!detailBeanName.civilExist}">
            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{detailBeanName.degreesList}" var="degree" itemLabel="#{degree.name}"
                           itemValue="#{degree.code.key}"/>
        </t:selectOneMenu>
   
                         <f:verbatim><br/></f:verbatim>
                <t:panelGroup id="errorCodeForDegree">  
                     <a4j:jsFunction action="#{detailBeanName.filterRaisesByDegree}" reRender="jobDataPanel"
                        name="filterRaisesByDegree"/>
              
        <h:outputText id="error_Can_Degree" value="#{resourcesBundle.error_degreeForEmp}" styleClass="errMsg" rendered="#{detailBeanName.errorCanDegree}"/>                
        
        </t:panelGroup>
    </t:panelGroup>
    <t:outputLabel value="#{resourcesBundle.raisesCount}" styleClass="lable01"/>
    <t:panelGroup id="raisesCountPanel" forceId="true">
        <t:selectOneMenu forceId="true" id="raisesCountId" tabindex="17" styleClass="DropdownboxMedium2" onchange="resetEqData();"
                         value="#{detailBeanName.raiseCode}" disabled="#{!detailBeanName.civilExist}">
            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{detailBeanName.raisesCount}" itemLabel="#{raise.countGuide}"
                           itemValue="#{raise.code.key}" var="raise"/>
        </t:selectOneMenu>
           <a4j:jsFunction action="#{detailBeanName.resetEqData}" reRender="jobDataPanel"
                        name="resetEqData"/>
    </t:panelGroup>
    <t:panelGroup id="jobpnlgroup"  forceId="true" colspan="4"   rendered="#{detailBeanName.contractType=='0' || detailBeanName.minExcepted == true}">
        <t:outputText value="#{resourcesBundle.job_name}" style="display: inline-block; width: 135px;"
                      rendered="#{detailBeanName.contractType=='0' || detailBeanName.minExcepted == true}"/>
        <t:panelGroup colspan="3" rendered="#{detailBeanName.contractType=='0' || detailBeanName.minExcepted == true}">
            <t:inputText styleClass="textboxmedium2"   value="#{detailBeanName.jobName}" forceId="true" id="supToJobName"
                         disabled="true"/>
            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
            <a4j:commandButton value="#{globalResources.Available}" rendered="#{detailBeanName.contractType=='0' || detailBeanName.minExcepted == true }" disabled="#{!detailBeanName.civilExist}"
                                id="showSupToJob_btn"  tabindex="18"
                               styleClass="cssButtonSmaller" reRender="customDiv1" action="#{detailBeanName.showJobDiv}"
                               oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"/>
        </t:panelGroup>
         <t:panelGroup >
          <t:outputText value="#{resourcesBundle.error_job_condition}" styleClass="errMsg" id="outputJobConditionErrMsg"
                          forceId="true" rendered="#{detailBeanName.errorJobCondition}"/>
         
         </t:panelGroup>
    </t:panelGroup>
    
    <t:panelGroup colspan="4" style="background-color:#ffffff;display: block;"   rendered="#{detailBeanName.minExcepted == true}">
                <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
                    <htm:tr>
                        <htm:td width="9">
                            <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
                        </htm:td>
                        <htm:td>
                            <t:outputLabel value="#{resourcesBundle.contract_emp_equv_title}" styleClass="lable01"/>
                        </htm:td>
                    </htm:tr>
                     
                    <htm:tr>
                        <htm:td colspan="2" height="1">
                            <htm:img src="../../../app/media/images/seprator_group.jpg" width="100%" height="1"/>
                        </htm:td>
                    </htm:tr>
                </htm:table>
            </t:panelGroup>
            <!--Cader-->
            <h:outputText value="#{resourcesBundle.candidate_cader_label}" styleClass="divtext" rendered="#{detailBeanName.minExcepted == true}"/>
            <t:panelGroup rendered="#{detailBeanName.minExcepted == true}" >
              
                <t:panelGroup id="employeeCaderPanel" forceId="true"  >
                   <t:selectOneMenu value="#{detailBeanName.caderCodeEq}" disabled="#{empty detailBeanName.raiseCode}" 
                                     converter="javax.faces.Long" id="caderMenu" forceId="true"
                                     styleClass="DropdownboxMedium2">
                        <f:selectItem itemValue="#{detailBeanName.virtualLongValue}"
                                      itemLabel="#{resourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.caderListEq}" itemLabel="#{cader.name}"
                                       itemValue="#{cader.code.elmguideCode}" var="cader"/>
                        <a4j:support reRender="degreePanelEq,raisesCount_Id,jobs_group,JobGrpMenu"
                                     action="#{detailBeanName.resetGroupDegreeAndRaiseList}" event="onchange"/>
                    </t:selectOneMenu>
                </t:panelGroup>
            </t:panelGroup>
            <!--jobs_group-->
            <h:outputText value="#{resourcesBundle.candidate_jobs_group_label}" styleClass="divtext"
                         rendered="#{detailBeanName.minExcepted == true}" />
            <t:panelGroup rendered="#{detailBeanName.minExcepted == true}" >
                <t:panelGroup id="jobs_group" forceId="true">
                    <t:selectOneMenu value="#{detailBeanName.groupCodeEq}"  disabled="#{empty detailBeanName.raiseCode}" 
                                     converter="javax.faces.Long" id="JobGrpMenu" forceId="true"
                                     styleClass="DropdownboxMedium2">
                        <f:selectItem itemValue="#{detailBeanName.virtualLongValue}"
                                      itemLabel="#{resourcesBundle.select}"/>
                        <t:selectItems value="#{detailBeanName.groupListEq}" itemLabel="#{group.name}"
                                       itemValue="#{group.code.elmguideCode}" var="group"/>
                        <a4j:support reRender="degreePanelEq,raisesCount_Id,jobs_group"
                                     action="#{detailBeanName.resetDegreeAndRaiseList}" event="onchange"/>
                    </t:selectOneMenu>
                    
                </t:panelGroup>
            </t:panelGroup>
            <!--SalDegree-->
            <h:outputText value="#{resourcesBundle.candidate_sal_degree_label}" styleClass="divtext" rendered="#{detailBeanName.minExcepted == true}"/>
            <t:panelGroup id="degreePanelEq" forceId="true" rendered="#{detailBeanName.minExcepted == true}" >
             
                <t:selectOneMenu forceId="true"
                                 id="employees_degreeEq" styleClass="DropdownboxMedium2" disabled="#{empty detailBeanName.raiseCode}" 
                                 value="#{detailBeanName.degreeCodeEq}" converter="javax.faces.Long">
                    <f:selectItem itemValue="#{detailBeanName.virtualLongValue}" itemLabel="#{resourcesBundle.select}"/>
                    <t:selectItems value="#{detailBeanName.degreeList}" var="degree" itemLabel="#{degree.name}"
                                   itemValue="#{degree.code.elmguideCode}"/>
                    <a4j:support reRender="raisesCount_Id,degreePanelEq" event="onchange" action="#{detailBeanName.onDegreeChangedRaise}"/>
                </t:selectOneMenu>
             
            </t:panelGroup>
            <!--RaiseCount-->
            <t:outputLabel value="#{resourcesBundle.raisesCount}" styleClass="lable01" rendered="#{detailBeanName.minExcepted == true}"/>
            <t:panelGroup colspan="3" id="raisesCount_Id" forceId="true" rendered="#{detailBeanName.minExcepted == true}" >
           
                <t:selectOneMenu forceId="true" id="raisesCountEq" disabled="#{empty detailBeanName.raiseCode}" 
                                 styleClass="DropdownboxMedium2" value="#{detailBeanName.raiseCodeEq}">
                    <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                    <t:selectItems value="#{detailBeanName.raiseList}" itemLabel="#{raise.countGuide}"
                                   itemValue="#{raise.code.key}" var="raise"/>
                    <a4j:support reRender="rewardsPG" event="onchange"
                                 action="#{detailBeanName.fillTotalRewardAccepted}"/>
                </t:selectOneMenu>
               
            </t:panelGroup>
            
              <h:outputText value="#{resourcesBundle.total_reward_accepted}" styleClass="divtext" rendered="#{detailBeanName.minExcepted == true}"/>
            <t:panelGroup colspan="3" id="rewardsPG" forceId="true" rendered="#{detailBeanName.minExcepted == true}" >
                <t:inputText value="#{detailBeanName.totalRewardAccepted}" styleClass="textboxmedium" disabled="#{empty detailBeanName.raiseCode}" 
                             forceId="true" id="total_reward_acceptedId"/>
            </t:panelGroup>
</t:panelGrid>

<t:inputHidden forceId="true" id="nextYearFromNow" value="#{detailBeanName.nextYear}">
    <f:converter converterId="SqlDateConverter"/>
</t:inputHidden>
