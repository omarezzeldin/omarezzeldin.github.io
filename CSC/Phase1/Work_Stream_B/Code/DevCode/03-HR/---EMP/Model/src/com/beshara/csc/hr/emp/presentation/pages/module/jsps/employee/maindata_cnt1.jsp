<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<htm:style>.divContent1Dynamic { height: 340px !important; margin: 8px 3px !important; position:relative; }
           .col1_WC{width:126px;} .col1_WC2 { padding-left: 3px; padding-right: 2px; white-space: nowrap; width:129px; }
           .colu1 { width: 129px; }</htm:style>
<t:saveState value="#{detailBeanName.caderCode}"/>
<t:saveState value="#{detailBeanName.caderName}"/>
<t:saveState value="#{detailBeanName.groupCode}"/>
<t:saveState value="#{detailBeanName.raiseCode}"/>
<t:saveState value="#{detailBeanName.degreeCode}"/>
<t:saveState value="#{detailBeanName.eqCaderName}"/>
<t:saveState value="#{detailBeanName.renderJobDiv}"/>
<t:saveState value="#{detailBeanName.renderLovDiv}"/>
<t:saveState value="#{detailBeanName.eqCaderCode}"/>
<t:saveState value="#{detailBeanName.eqGroupCode}"/>
<t:saveState value="#{detailBeanName.eqDegreeCode}"/>
<t:saveState value="#{detailBeanName.eqRaiseCode}"/>
<t:saveState value="#{detailBeanName.eqRaiseName}"/>
<t:saveState value="#{detailBeanName.eqTypeTemp}"/>
<t:saveState value="#{detailBeanName.groupList}"/>
<t:saveState value="#{detailBeanName.hireTypesList}"/>
<t:saveState value="#{detailBeanName.bgtProgramName}"/>
<t:saveState value="#{detailBeanName.workCenterHasJobs}"/>
<t:panelGroup styleClass="divMainContent1Employees">
    <t:messages showDetail="true"/>
    <%-- <t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="col1_WC" cellspacing="0" width="100%"
         align="cenetr" columns="2" border="0"> </t:panelGrid>--%>
    <t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="col1_WC" cellspacing="0" width="100%"
                 forceId="true" id="employeeMainDataPanelEdit" columns="4" align="cenetr" border="0">
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
        <%-- row1---%>
       
        <h:outputText value="#{resourcesBundle.civilid}" styleClass="lable01"/>
        <t:inputText disabled="true" forceId="true" id="employees_civilIdEdit" styleClass="textboxmedium"
                     value="#{pageBeanName.pageDTO.citizensResidentsDTO.code.civilId}" converter="javax.faces.Long"/>
        <%-- row2---%>
        <h:outputText value="#{resourcesBundle.candidate_name}" styleClass="lable01"/>
        <t:inputText forceId="true" id="employees_nam1" styleClass="textboxmedium"
                     value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}" disabled="true"/>
        <%-- added Row--%>
        <h:outputText value="#{resourcesBundle.gender}" styleClass="lable01"/>
        <t:inputText forceId="true" id="employees_gender_Add" styleClass="textboxmedium"
                     value="#{pageBeanName.pageDTO.citizensResidentsDTO.genderTypesDTO.name}" disabled="true"/>
        <h:outputText value="#{resourcesBundle.status}" styleClass="lable01"/>
        <t:inputText forceId="true" id="employees_status_Add" styleClass="textboxmedium"
                     value="#{pageBeanName.pageDTO.citizensResidentsDTO.maritalStatusDTO.name}" disabled="true"/>
        <%-- added Row--%>
        <h:outputText value="#{resourcesBundle.nationalityType}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputText forceId="true" id="countriesDTO_status" styleClass="textboxmedium"
                         value="#{pageBeanName.pageDTO.citizensResidentsDTO.countriesDTO.name}" disabled="true"/>
        </t:panelGroup>
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
        <h:outputText value="#{resourcesBundle.hiretype}" styleClass="lable01"/>
        <t:panelGroup>
            <t:selectOneMenu onchange="changeHireType();" forceId="true" id="employees_hiretypes" tabindex="1"
                             onkeydown="onKeyDownFirstElement(event,'employees_ministryFileNo1','BackButtonManyToMany')"
                             styleClass="DropdownboxMedium2" value="#{detailBeanName.selectedHireTypeCode}">
                <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                              itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{detailBeanName.hireTypesList}" itemLabel="#{hireTypesList.concatenatedName}"
                               itemValue="#{hireTypesList.code.key}" var="hireTypesList"/>
                <a4j:jsFunction action="#{detailBeanName.setHireTypeCodeOnChange}" name="changeHireType"
                                reRender="hireTypePnlGrp1"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:panelGroup forceId="true" id="hireTypePnlGrp1">
                <t:outputText value="#{resourcesBundle.hirtype_notRelatedByCond}" styleClass="errMsg" id="outputErrMsg1"
                              forceId="true" rendered="#{detailBeanName.renderedErrorMsgHirType}"/>
            </t:panelGroup>
            <c2:compareValidator componentToValidate="employees_hiretypes" componentToCompare="virtualvalueId"
                                 operator="not" errorMessage="#{globalResources.missingField}" highlight="false"
                                 display="dynamic" group="hireTypeGroup"/>
        </t:panelGroup>
        <%--<h:outputText value="#{resourcesBundle.ministry_fileNo}" styleClass="lable01"/>--%>
        <%--<t:panelGroup>
            <t:inputText forceId="true" maxlength="20" id="employees_ministryFileNo1" tabindex="2"
                         styleClass="textboxmedium" value="#{pageBeanName.pageDTO.ministryFileNo}"
                         onkeypress="goChangeFilNumber(event);" 
                         onchange="changeFilNumberOnBlur();"></t:inputText>
            <a4j:jsFunction name="checkAboutFilNum" action="#{detailBeanName.checkValidFilMinNo}"
                            reRender="validScriptPanel,minFilNomsgPanel"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:panelGroup forceId="true" id="minFilNomsgPanel">
                <t:outputText forceId="true" id="renderEmpFilNumRedundant" value="#{resourcesBundle.empFilNumRedundant}"
                              rendered="#{detailBeanName.renderEmpFilNumRedundant}" styleClass="errMsg"/>
                <t:outputText forceId="true" id="renderCandFilNumRedundant"
                              value="#{resourcesBundle.candFilNumRedundant}"
                              rendered="#{detailBeanName.renderCandFilNumRedundant}" styleClass="errMsg"/>
            </t:panelGroup>
        </t:panelGroup>--%>
           <t:panelGroup  colspan="2" />
      
        <h:outputText value="#{resourcesBundle.employees_work_center}" styleClass="lable01"/>
        <t:panelGroup colspan="1" id="workCenterEditPanel" forceId="true">
            <t:inputText disabled="true" readonly="true" styleClass="textboxmedium" forceId="true"
                         id="workCenterCodeText" value="#{detailBeanName.workCenterName}"/>
            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
            <a4j:commandButton value="#{globalResources.Available}" tabindex="4"
                               action="#{detailBeanName.openWorkCentersIntegDiv}" styleClass="cssButtonSmaller"
                               oncomplete="showWorkCenterIntegrationDiv();"
                               reRender="workCenterCodeText,workCenterEditPanel,workcentersIntgSearchTbl,integrationDiv1"/>
             <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>
                <br/>
            </f:verbatim>
             <c2:requiredFieldValidator componentToValidate="workCenterCodeText" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"
                                />
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.budget}" styleClass="lable01"/>
        <t:panelGroup id="bgtNamePanel" forceId="true">
            <t:inputText forceId="true" Id="bgtInputId" value="#{detailBeanName.bgtProgramName}"
                         styleClass="textboxmedium" disabled="true"/>
        </t:panelGroup>
        <%-- row5-----%>
        <h:outputText value="#{resourcesBundle.Job_Work_Center_Job_Description}"
                      rendered="#{ !(detailBeanName.jobDescription == null || detailBeanName.jobDescription =='') }"
                      styleClass="lable01"/>
        <t:panelGroup colspan="3"
                      rendered="#{ !(detailBeanName.jobDescription == null || detailBeanName.jobDescription =='') }">
            <t:inputText disabled="true" styleClass="textboxmedium" forceId="true" id="jobDescriptionName"
                         value="#{detailBeanName.jobDescription}"
                         rendered="#{ !(detailBeanName.jobDescription == null || detailBeanName.jobDescription =='') }"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.budgetType}" styleClass="lable01"/>
        <t:panelGroup forceId="true" id="budgetTypePanel" >
            <t:selectOneMenu forceId="true" id="employees_budgetType" tabindex="5" styleClass="textboxmedium"
                             style="width:200px;" value="#{pageBeanName.pageDTO.bgtTypeKey}"
                             disabled="#{empMainDataBean.dataDisabledIfEmpFromCRS}">
                <f:selectItem itemValue="#{detailBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{detailBeanName.budgetTypeList}" itemLabel="#{budgetType.name}"
                               itemValue="#{budgetType.code.key}" var="budgetType"/>
            </t:selectOneMenu>
        </t:panelGroup>
         
        <t:panelGroup   colspan="2"  >
       <%--<t:selectBooleanCheckbox value="#{pageBeanName.pageDTO.witoutQualFlag}"
                            forceId="true" id="witoutQualFlag"  rendered="#{pageBeanName.pageDTO.hireTypesDTO.firstParent.key != '2'}" />--%>
                                 
        <%--<h:outputText value="#{resourcesBundle.cand_without_qual}" styleClass="lable01"  rendered="#{pageBeanName.pageDTO.hireTypesDTO.firstParent.key != '2'}"/>--%>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.contract_type}" styleClass="lable01"
                      rendered="#{!empMainDataBean.dataDisabledIfEmpFromCRS}"/>
        <t:panelGroup forceId="true" id="contractTypePanel" colspan="3"
                      rendered="#{!empMainDataBean.dataDisabledIfEmpFromCRS}">
            <t:selectOneMenu forceId="true" id="employees_contractType" styleClass="textboxmedium" style="width:200px;"
                             value="#{detailBeanName.contractType}" onchange="changeCader();" tabindex="6"
                             disabled="#{!pageBeanName.enableEditQulAndExper || pageBeanName.enableContractType}">
                <f:selectItem itemValue="#{detailBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.select}"/>
                <f:selectItem itemValue="6" itemLabel="#{resourcesBundle.contracts}"/>
                <f:selectItem itemValue="7" itemLabel="#{resourcesBundle.Estana}"/>
                <f:selectItem itemValue="0" itemLabel="#{resourcesBundle.other}"/>
            </t:selectOneMenu>
            <a4j:jsFunction action="#{empMainDataBean.fillCaderList}"
                            reRender="employeeMainDataPanelEdit"
                            name="changeCader"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.cader}" styleClass="lable01"/>
        <t:panelGroup forceId="true" id="caderPanel">
            <t:selectOneMenu forceId="true" id="cader_dropdown" styleClass="textboxmedium" style="width:200px;"
                             value="#{detailBeanName.caderCode}" onchange="fillGroupList();" tabindex="7"
                             converter="javax.faces.Long" disabled="#{empMainDataBean.dataDisabledIfEmpFromCRS}">
                <f:selectItem itemValue="#{detailBeanName.virtualLongValue}" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{detailBeanName.caderList}" itemLabel="#{cader.name}"
                               itemValue="#{cader.code.elmguideCode}" var="cader"/>
            </t:selectOneMenu>
            <a4j:jsFunction action="#{detailBeanName.fillGroupList}" reRender="groupPanel" name="fillGroupList"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.group}" styleClass="lable01"/>
        <t:panelGroup forceId="true" id="groupPanel">
            <t:selectOneMenu forceId="true" id="employees_group" tabindex="8" styleClass="DropdownboxMedium2"
                             value="#{detailBeanName.groupCode}" converter="javax.faces.Long"
                             disabled="#{empMainDataBean.dataDisabledIfEmpFromCRS}">
                <f:selectItem itemValue="#{detailBeanName.virtualLongValue}" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{detailBeanName.groupList}" itemLabel="#{group.name}"
                               itemValue="#{group.code.elmguideCode}" var="group"/>
                <a4j:support reRender="employees_degree,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn"
                             action="#{detailBeanName.filterDegreesByGroup}" event="onchange"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.degree}" styleClass="lable01"/>
        <t:panelGroup id="degreePanel" forceId="true">
            <t:selectOneMenu forceId="true" id="employees_degree" tabindex="9" styleClass="DropdownboxMedium2"
                             value="#{detailBeanName.degreeCode}"
                             disabled="#{empMainDataBean.dataDisabledIfEmpFromCRS}">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{detailBeanName.degreesList}" var="degree" itemLabel="#{degree.name}"
                               itemValue="#{degree.code.key}"/>
                <a4j:support reRender="raisesCountId,error_Code_ForDegree,erroor_Can_Degree,degreePanel"
                             action="#{detailBeanName.filterRaisesByDegree}" event="onchange"/>
            </t:selectOneMenu>
              <f:verbatim><br/></f:verbatim>
              <t:panelGroup id="error_Code_ForDegree">
            <h:outputText id="erroor_Can_Degree" value="#{resourcesBundle.error_degreeForEmp}" styleClass="errMsg"
                          rendered="#{detailBeanName.errorCanDegree}"/>
        </t:panelGroup>
        </t:panelGroup>
      
        <t:panelGroup>
            <t:outputLabel value="#{resourcesBundle.raisesCount}" styleClass="lable01"/>
        </t:panelGroup>
        <t:panelGroup colspan="3" id="raisesCountPanel" forceId="true">
            <t:selectOneMenu forceId="true" id="raisesCountId" tabindex="10" styleClass="DropdownboxMedium2" onchange="resetEqData();"
                             value="#{detailBeanName.raiseCode}" disabled="#{empMainDataBean.dataDisabledIfEmpFromCRS}">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{detailBeanName.raisesCount}" itemLabel="#{raise.countGuide}"
                               itemValue="#{raise.code.key}" var="raise"/>
                   <a4j:jsFunction action="#{detailBeanName.resetEqData}" reRender="employeeMainDataPanelEdit"
                        name="resetEqData"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <t:panelGroup id="jobpnlgroup" forceId="true" colspan="4" rendered="#{detailBeanName.contractType=='0' || detailBeanName.minExcepted == true}">
            <h:outputText value="#{resourcesBundle.job_name}" style="display: inline-block; width: 135px;"/>
            <t:panelGroup colspan="3">
                <t:inputText disabled="true" styleClass="textboxmedium" forceId="true" id="jobName"
                             value="#{pageBeanName.pageDTO.jobsDTO.name}"/>
                <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                <a4j:commandButton id="jobInEdit" styleClass="cssButtonSmaller" value="#{globalResources.Available}"
                                   action="#{detailBeanName.showJobDiv}" reRender="customDiv1"
                                
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
    
        <script type="text/javascript">
          setFocusAtMyFirstElement();

          function setFocusAtMyFirstElement() {
              if (document.getElementById('check_civil_btn') != null) {
                  document.getElementById('employees_hiretypes').focus();
                  document.getElementById('employees_hiretypes').focus();
              }
              else {
                  document.getElementById('employees_ministryFileNo1').focus();
                  document.getElementById('employees_ministryFileNo1').focus();
              }
          }

          function foucsAfterLastElemenyOnPage() {
              if (document.getElementById('check_civil_btn') != null) {
                  document.getElementById('employees_hiretypes').focus();
                  document.getElementById('employees_hiretypes').focus();
              }
              else if (document.getElementById('reset_btn') != null) {
                  document.getElementById('finish_btn').focus();
              }
          }

          function foucsAfterFileNo() {
              if (document.getElementById('reset_btn') != null) {
                  document.getElementById('reset_btn').focus();
              }
          }

          function calcNextRaiseJs() {
              if (document.getElementById('employees_dateOfNextRaise') == null) {
                  return;// not kwt
              }
              var oldValue = document.getElementById('hireDateHF').value;
              var newValue = document.getElementById('employees_hireDate1').value;
              if (oldValue == newValue) {
                  return;// no updates
              }
              calcNextRaiseJsFunction();
              document.getElementById('hireDateHF').value = newValue;
          }

          function validateSelection() {
              var inputTextValue;
              if (document.getElementById('missionTypeNameValidation') != null) {
                  document.getElementById('missionTypeNameValidation').value = '';
              }
              if (document.getElementById('missionTypeNameID') != null)
                  inputTextValue = document.getElementById('missionTypeNameID').value;
              if (checkEmpty2(inputTextValue, 'myForm:missionTypeNameValidation')) {
                  alert('false');
                  return false;
              }

          }

          function goChangeCountryVal(e) {
              if (e.keyCode == 13) {
                  e.cancelBubble = true;
                  e.returnValue = false;
                  e.preventDefault();
                  changeCountryVal();
              }
          }

          function ChangeCountryValOnBlur() {
              changeCountryVal();
          }
        </script>
    </t:panelGrid>
</t:panelGroup>
