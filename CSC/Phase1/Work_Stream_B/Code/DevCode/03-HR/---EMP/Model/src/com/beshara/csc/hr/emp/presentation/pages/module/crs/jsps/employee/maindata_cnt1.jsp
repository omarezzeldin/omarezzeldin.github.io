<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<t:saveState value="#{detailBeanName.caderCode}"/>
<t:saveState value="#{detailBeanName.caderName}"/>
<t:saveState value="#{detailBeanName.groupCode}"/>
<t:saveState value="#{detailBeanName.degreeCode}"/>
<t:saveState value="#{detailBeanName.raiseCode}"/>
<t:saveState value="#{detailBeanName.eqCaderName}"/>
<t:saveState value="#{detailBeanName.renderJobDiv}"/>
<t:saveState value="#{detailBeanName.renderLovDiv}"/>
<t:saveState value="#{detailBeanName.eqCaderCode}"/>
<t:saveState value="#{detailBeanName.eqGroupCode}"/>
<t:saveState value="#{detailBeanName.eqDegreeCode}"/>
<t:saveState value="#{detailBeanName.eqRaiseCode}"/>
<t:saveState value="#{detailBeanName.eqRaiseName}" />
<t:saveState value="#{detailBeanName.eqTypeTemp}"/>
<t:saveState value="#{detailBeanName.groupList}"/>
<t:saveState value="#{detailBeanName.degreeList}"/>
<t:saveState value="#{detailBeanName.raiseList}"/>
<t:saveState value="#{detailBeanName.hireTypesList}"/>
<t:saveState value="#{detailBeanName.bgtProgramName}"/>
<t:saveState value="#{detailBeanName.workCenterHasJobs}"/>
<t:panelGroup styleClass="#{pageBeanName.maintainMode==1? 'divMainContent1Employees' : 'divMainContent1Employees2'}">
<t:messages showDetail="true" />

<t:panelGrid  rowClasses="row02,row01" cellpadding="3" columnClasses="colCenter1,colCenter2" cellspacing="0" width="100%" align="cenetr"  columns="2" >

<h:outputText  value="#{resourcesBundle.hiretype}" />

      <t:panelGroup>
        <t:selectOneMenu onblur="document.getElementById('employees_civilIdAdd').focus();"
                         readonly="true"
                         forceId="true" id="employees_hiretypes"
                         styleClass="DropdownboxMedium2"
                         value="#{pageBeanName.pageDTO.hireTypeKey}">
          <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                        itemLabel="#{resourcesBundle.select}"/>
          <t:selectItems value="#{detailBeanName.hireTypesList}"
                         itemLabel="#{hireTypesList.name}"
                         itemValue="#{hireTypesList.code.key}"
                         var="hireTypesList"/>
          <%-- 
            <f:selectItem
            itemValue="#{managedConstantsBean.empHireTypeExceptedFromCenteralEmployment}"
            itemLabel="#{resourcesBundle.exceptedFromCenteralEmployment}"/>
            <f:selectItem
            itemValue="#{managedConstantsBean.empHireTypeNominationAgain}"
            itemLabel="#{resourcesBundle.nominationAgain}"/> <f:selectItem
            itemValue="#{managedConstantsBean.empHireTypeNominationByMinistry}"
            itemLabel="#{resourcesBundle.nominationByMinistry}"/> <f:selectItem
            itemValue="#{managedConstantsBean.empHireTypeFromCenteralEmployment}"
            itemLabel="#{resourcesBundle.EMP_HIRE_TYPE_FROM_CENTER_EMPLOYMENT}"/>
            <f:selectItem
            itemValue="#{managedConstantsBean.empHireTypeMovedToOtherMinisties}"
            itemLabel="#{resourcesBundle.EMP_HIRE_TYPE_MOVEED_TO_OTHER_MINISTRIES}"/>
          --%>
          <%-- 
            a4j:support event="onchange"
            actionListener="#{pageBeanName.resetDocumentList}" /
          --%>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"
                      rendered="#{(pageBeanName.maintainMode==0 && !empMainDataBean_crs.civilExist )}"/>
        <t:outputText value="" styleClass="errMsg" id="outputErrMsg"
                      forceId="true"/>
        <f:verbatim>
          <br/>
        </f:verbatim>
        <c:compareValidator componentToValidate="employees_hiretypes"
                            componentToCompare="virtualvalueId" operator="not"
                            errorMessage="#{globalResources.missingField}"
                            highlight="false" display="dynamic"/>
      </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid rowClasses="row02,row01" cellpadding="3"
                 columnClasses="colu1,colu2" cellspacing="0" width="100%"
                 forceId="true" id="employeeMainDataPanel" columns="4"
                 align="cenetr">
      <t:panelGroup colspan="4" style="background-color:#ffffff;">
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
          <htm:tr>
            <htm:td width="9">
              <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9"
                       height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
              <t:outputLabel value=" #{resourcesBundle.maindata}"
                             styleClass="lable01"/>
            </htm:td>
          </htm:tr>
          <htm:tr>
            <htm:td colspan="2" height="1">
              <htm:img src="../../../../app/media/images/seprator_group.jpg"
                       width="100%" height="1"/>
            </htm:td>
          </htm:tr>
        </htm:table>
      </t:panelGroup>
      <%-- 
        row1-
      --%>
      <h:outputText value="#{resourcesBundle.civilid}"/>
      <t:panelGroup>
        <t:inputText disabled="true" forceId="true" id="employees_civilIdEdit"
                     styleClass="textbox"
                     value="#{pageBeanName.pageDTO.citizensResidentsDTO.code.civilId}"
                     converter="javax.faces.Long"
                     rendered="#{pageBeanName.maintainMode==1 }"/>
        <t:inputText forceId="true" maxlength="12" id="employees_civilIdAdd"
                     styleClass="textbox" value="#{detailBeanName.civilId}"
                     converter="javax.faces.Long"
                     rendered="#{( pageBeanName.maintainMode==0)}"
                     disabled="true"/>
        <t:panelGroup forceId="true" id="civilIDmsgpanel">
          <t:outputLabel value="#{resourcesBundle.civilIdFound}" forceId="true"
                         rendered="#{empMainDataBean_crs.invalidCivilID==true &&( pageBeanName.maintainMode==0 )}"
                         id="civilErr" styleClass="errMsg"/>
          <t:outputLabel value="#{resourcesBundle.NonDBCivilID}" forceId="true"
                         rendered="#{empMainDataBean_crs.nonDBCivilID==true && ( pageBeanName.maintainMode==0 )}"
                         id="civilNonDbErr" styleClass="errMsg"/>
          <t:outputLabel value="#{resourcesBundle.EmployeeAlreadyEmploymentException}"
                         forceId="true"
                         rendered="#{empMainDataBean_crs.invalidCivilWithHireType==true && ( pageBeanName.maintainMode==0 )}"
                         id="civilErrwithHire" styleClass="errMsg"/>
          <t:outputLabel value="" forceId="true"
                         rendered="#{pageBeanName.maintainMode==0 }"
                         id="civilLengthError" styleClass="errMsg"/>
        </t:panelGroup>
      </t:panelGroup>
      <%-- 
        row2-
      --%>
      <h:outputText value="#{resourcesBundle.candidate_name}"/>
      <t:inputText forceId="true" id="employees_nam1" styleClass="textboxmedium"
                   value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}"
                   disabled="true"/>
      <%-- 
        added Row
      --%>
      <h:outputText value="#{resourcesBundle.gender}"/>
      <t:inputText forceId="true" id="employees_gender_Add" styleClass="textbox"
                   value="#{pageBeanName.pageDTO.citizensResidentsDTO.genderTypesDTO.name}"
                   disabled="true"/>
      <h:outputText value="#{resourcesBundle.status}"/>
      <t:inputText forceId="true" id="employees_status_Add" styleClass="textbox"
                   value="#{pageBeanName.pageDTO.citizensResidentsDTO.maritalStatusDTO.name}"
                   disabled="true"/>
      <%-- 
        added Row
      --%>
      <h:outputText value="#{resourcesBundle.nationalityType}"/>
      <t:inputText forceId="true" id="countriesDTO_status" styleClass="textbox"
                   value="#{pageBeanName.pageDTO.citizensResidentsDTO.countriesDTO.name}"
                   disabled="true"/>
      <h:outputText value="#{resourcesBundle.ministry_fileNo}"/>
      <t:panelGroup>
        <t:inputText forceId="true" maxlength="20"
                     id="employees_ministryFileNo1" styleClass="textbox"
                     value="#{pageBeanName.pageDTO.ministryFileNo}">
          <a4j:support event="onchange"
                       actionListener="#{pageBeanName.checkValidFilMinNo}"
                       reRender="validScriptPanel,minFilNomsgPanel"/>
        </t:inputText>
        <t:panelGroup forceId="true" id="minFilNomsgPanel">
          <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim>
            <br/>
          </f:verbatim>
          <t:outputLabel value="#{resourcesBundle.MINISTRY_FILE_NOFound}"
                         forceId="true"
                         rendered="#{pageBeanName.invalidMinFileNo==true }"
                         id="minFileErr" styleClass="errMsg"/>
          <c:requiredFieldValidator componentToValidate="employees_ministryFileNo1"
                                    errorMessage="#{globalResources.missingField}"
                                    highlight="false" display="dynamic"/>
        </t:panelGroup>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.hireDate}"/>
      <t:panelGroup>
        <t:inputCalendar onblur="calcNextRaiseJs();" onchange="calcNextRaiseJs();"
                         popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                         popupDateFormat="dd/MM/yyyy" forceId="true"
                         value="#{pageBeanName.pageDTO.hireDate}"
                         id="employees_hireDate1" size="20" maxlength="200"
                         styleClass="textbox"
                         currentDayCellClass="currentDayCell"
                         renderAsPopup="true" align="#{globalResources.align}"
                         popupLeft="#{shared_util.calendarpopupLeft}"
                         renderPopupButtonAsImage="true"
                         title="#{globalResources.inputCalendarHelpText}">
          <f:converter converterId="SqlDateConverter"/>
          <a4j:jsFunction name="calcNextRaiseJsFunction" action="#{detailBeanName.calculateNextDateOfRaise}" reRender="nextRaisePnlGrp"/>
        <%--a4j:support action="#{detailBeanName.calculateNextDateOfRaise}" reRender="nextRaisePnlGrp" event="onchange"/--%>
        </t:inputCalendar>
   
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
          <br/>
        </f:verbatim>
        <c:requiredFieldValidator componentToValidate="employees_hireDate1"
                                  errorMessage="#{globalResources.missingField}"
                                  highlight="false" display="dynamic"/>
        <c:dateFormatValidator componentToValidate="employees_hireDate1"
                               errorMessage="#{resourcesBundle.dateFormat}"
                               highlight="false" display="dynamic"/>
        <c:compareDateValidator componentToValidate="employees_hireDate1"
                                componentToCompare="employees_start_work_date"
                                operator="before"
                                errorMessage="#{resourcesBundle.hireDatebeforeStartDateErrMsg}"
                                display="dynamic"/>
                                
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.employees_start_work_date}"/>
      <t:panelGroup>
        <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                         popupDateFormat="dd/MM/yyyy" forceId="true"
                         value="#{pageBeanName.pageDTO.startWorkingDate}"
                         id="employees_start_work_date" size="20"
                         maxlength="200" styleClass="textbox"
                         currentDayCellClass="currentDayCell"
                         renderAsPopup="true" align="#{globalResources.align}"
                         popupLeft="#{shared_util.calendarpopupLeft}"
                         renderPopupButtonAsImage="true"
                         title="#{globalResources.inputCalendarHelpText}">
          <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
        <f:verbatim>
          <br/>
        </f:verbatim>
        <c:dateFormatValidator componentToValidate="employees_start_work_date"
                               errorMessage="#{resourcesBundle.dateFormat}"
                               highlight="false" display="dynamic"/>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.employees_work_center}"/>
      <t:panelGroup colspan="3">
        <t:inputText disabled="true" readonly="true" tabindex="888888888"
                     styleClass="textboxlarge" forceId="true"
                     id="workCenterCodeText"
                     value="#{detailBeanName.workCenterName}"/>
        <f:verbatim>
          &nbsp;&nbsp;
        </f:verbatim>
        <a4j:commandButton value="#{globalResources.Available}"
                           styleClass="cssButtonSmaller"
                           reRender="lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn"
                           action="#{detailBeanName.showListOfValuesDiv}"
                           oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"
                           disabled="#{!empMainDataBean_crs.civilExist && pageBeanName.maintainMode==0}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <c:requiredFieldValidator componentToValidate="workCenterCodeText"
                                  errorMessage="#{globalResources.missingField}"
                                  highlight="false" display="dynamic"/>
      </t:panelGroup>
      <%-- 
        row5-
      --%>
      <h:outputText value="#{resourcesBundle.job_name}"/>
      <t:panelGroup>
        <t:inputText disabled="true" tabindex="999999999"
                     styleClass="textboxlarge" forceId="true" id="jobName"
                     value="#{pageBeanName.pageDTO.jobDTO.name}"/>
        <f:verbatim>
          &nbsp;&nbsp;
        </f:verbatim>
        <a4j:commandButton styleClass="cssButtonSmaller"
                           value="#{globalResources.Available}"
                           action="#{detailBeanName.showJobDiv}"
                           reRender="jobDivPanelGroup"
                           oncomplete="changeVisibilityDiv(window.blocker,window.delAlert);settingFoucsAtDivAdd();"
                           disabled="#{(!empMainDataBean_crs.civilExist && pageBeanName.maintainMode==0)||(detailBeanName.workCenterName==null || detailBeanName.workCenterName=='')}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <c:requiredFieldValidator componentToValidate="jobName"
                                  errorMessage="#{globalResources.missingField}"
                                  highlight="false" display="dynamic"/>
      </t:panelGroup>
      
      
       <h:outputText  value="#{resourcesBundle.Job_Work_Center_Job_Description}" rendered="#{ !(detailBeanName.jobDescription == null || detailBeanName.jobDescription =='') }"/>  
       <t:inputText disabled="true" tabindex="4444444444444" styleClass="textboxlarge" forceId="true" id="jobDescriptionName" value="#{detailBeanName.jobDescription}" rendered="#{ !(detailBeanName.jobDescription == null || detailBeanName.jobDescription =='') }"/>
    
      <%-- 
        technical jobs combo box
      --%>
      <t:panelGroup colspan="2" rendered="#{(detailBeanName.jobDescription == null || detailBeanName.jobDescription =='') }"/>
     
     <%--
      <h:outputText value="#{resourcesBundle.employees_special_job}"
                    rendered="#{(pageBeanName.pageDTO.jobDTO !=null && pageBeanName.pageDTO.jobDTO.typeKey !=null ) && (detailBeanName.supervisionJobType==pageBeanName.pageDTO.jobDTO.typeKey)}"/>
      <t:panelGroup rendered="#{(pageBeanName.pageDTO.jobDTO !=null && pageBeanName.pageDTO.jobDTO.typeKey !=null ) && (detailBeanName.supervisionJobType==pageBeanName.pageDTO.jobDTO.typeKey)}">
        <t:selectOneMenu forceId="true" id="employees_technicalJobs"
                         styleClass="DropdownboxMedium2"
                         value="#{pageBeanName.pageDTO.techJobKey}">
          <f:selectItem itemValue="#{detailBeanName.virtualConstValue}"
                        itemLabel="#{resourcesBundle.select}"/>
          <t:selectItems value="#{detailBeanName.techicalJobsList}"
                         itemLabel="#{techicalJobsList.name}"
                         itemValue="#{techicalJobsList.code.key}"
                         var="techicalJobsList"/>
        </t:selectOneMenu>
      </t:panelGroup>
      --%>
      <t:panelGroup colspan="4" style="background-color:#ffffff;" >
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
          <htm:tr>
            <htm:td width="9">
              <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9"
                       height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
              <t:outputLabel value=" #{resourcesBundle.budget}"
                             styleClass="lable01"/>
            </htm:td>
          </htm:tr>
          <htm:tr>
            <htm:td colspan="2" height="1">
              <htm:img src="../../../../app/media/images/seprator_group.jpg"
                       width="100%" height="1"/>
            </htm:td>
          </htm:tr>
        </htm:table>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.budgetType}"/>
      <t:panelGroup forceId="true" id="budgetTypePanel">
        <t:selectOneMenu forceId="true" id="employees_budgetType"
                         styleClass="DropdownboxMedium2"
                         value="#{pageBeanName.pageDTO.bgtTypeKey}">
          <f:selectItem itemValue="#{detailBeanName.virtualConstValue}"
                        itemLabel="#{resourcesBundle.select}"/>
          <t:selectItems value="#{detailBeanName.budgetTypeList}"
                         itemLabel="#{budgetType.name}"
                         itemValue="#{budgetType.code.key}" var="budgetType"/>
        </t:selectOneMenu>
        <t:panelGroup>
          <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim>
            <br/>
          </f:verbatim>
          <c:compareValidator componentToValidate="employees_budgetType"
                              componentToCompare="virtualvalueId" operator="not"
                              errorMessage="#{globalResources.missingField}"
                              highlight="false" display="dynamic"/>
        </t:panelGroup>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.budget}"/>
      <t:inputText value="#{detailBeanName.bgtProgramName}"
                   styleClass="textboxlarge" disabled="true"/>
      <%-- 
        <t:panelGroup forceId="true" id="budgetPanel"> <t:selectOneMenu
        forceId="true" id="employees_budget"
        value="#{pageBeanName.pageDTO.bgtPrgmKey}"
        styleClass="DropdownboxMedium2" > <f:selectItem
        itemValue="#{detailBeanName.virtualConstValue}"
        itemLabel="#{resourcesBundle.select}"/> <t:selectItems
        value="#{detailBeanName.budgetList}" itemLabel="#{budget.name}"
        itemValue="#{budget.code.key}" var="budget"/> </t:selectOneMenu>
        <t:panelGroup> <h:outputText value="*" styleClass="mandatoryAsterisk" />
        <f:verbatim><br/></f:verbatim> <c2:compareValidator
        componentToValidate="employees_budget"
        componentToCompare="virtualvalueId" operator="not"
        errorMessage="#{globalResources.missingField}" highlight="false"
        display="dynamic" /> </t:panelGroup> </t:panelGroup>
      --%>
      <t:panelGroup colspan="4" style="background-color:#ffffff;" >
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
          <htm:tr>
            <htm:td width="9">
              <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9"
                       height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
              <t:outputLabel value=" #{resourcesBundle.job_main_data}"
                             styleClass="lable01"/>
            </htm:td>
          </htm:tr>
          <htm:tr>
            <htm:td colspan="2" height="1">
              <htm:img src="../../../../app/media/images/seprator_group.jpg"
                       width="100%" height="1"/>
            </htm:td>
          </htm:tr>
        </htm:table>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.cader}"/>
      <t:panelGroup forceId="true" id="caderPanel">
        <t:inputText disabled="true" tabindex="9999999999999"
                     styleClass="textboxmedium" forceId="true" id="caderText"
                     value="#{detailBeanName.caderName}"/>
        <t:panelGroup>
          <f:verbatim>
            &nbsp;
          </f:verbatim>
          <a4j:commandButton value="#{globalResources.Available}"
                             styleClass="cssButtonSmaller"
                             reRender="lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn"
                             action="#{detailBeanName.showCaderListOfValuesDiv}"
                             oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"
                             disabled="#{!empMainDataBean_crs.civilExist && pageBeanName.maintainMode==0}"/>
          <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim>
            <br/>
          </f:verbatim>
          <%-- 
            c2:requiredFieldValidator componentToValidate="employees_cader"
            errorMessage="#{globalResources.missingField}" highlight="false"
            display="dynamic" /
          --%>
          <c:requiredFieldValidator componentToValidate="caderText"
                                    errorMessage="#{globalResources.missingField}"
                                    highlight="false" display="dynamic"/>
        </t:panelGroup>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.group}"/>
      <t:panelGroup forceId="true" id="groupPanel">
        <t:selectOneMenu forceId="true" id="employees_group"
                         styleClass="DropdownboxMedium2"
                         value="#{detailBeanName.groupCode}">
          <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
          <t:selectItems value="#{detailBeanName.groupList}"
                         itemLabel="#{group.name}"
                         itemValue="#{group.code.elmguideCode}" var="group"/>
          <a4j:support reRender="employeeMainDataPanel"
                       action="#{detailBeanName.resetDegreeAndRaiseList}"
                       event="onchange"/>
        </t:selectOneMenu>
        <%-- 
          raisePanel removed from rerender
        --%>
        <t:panelGroup>
          <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim>
            <br/>
          </f:verbatim>
          <c:requiredFieldValidator componentToValidate="employees_group"
                                    errorMessage="#{globalResources.missingField}"
                                    highlight="false" display="dynamic"/>
        </t:panelGroup>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.degree}"/>
      <t:panelGroup forceId="true" id="degreePanel">
        <t:inputText forceId="true" id="employees_degree"
                     styleClass="textboxmedium"
                     value="#{detailBeanName.raiseName}" disabled="true"/>
        <f:verbatim>
          &nbsp;
        </f:verbatim>
        <a4j:commandButton value="..." styleClass="cssButtonSmaller"
                           action="#{detailBeanName.OpenTreeDiv}"
                           oncomplete="changeVisibilityDiv(window.blocker,window.divTree);settingFoucsAtTreeDiv();return false;"
                           reRender="searchText,cancelsearchpanel,noResult,treeDivPanel,radioTreeDivPanel11,okPanel"
                           disabled="#{!empMainDataBean_crs.civilExist && pageBeanName.maintainMode==0}"/>
        <t:panelGroup>
          <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim>
            <br/>
          </f:verbatim>
          <c:requiredFieldValidator componentToValidate="employees_degree"
                                    errorMessage="#{globalResources.missingField}"
                                    highlight="false" display="dynamic"/>
          <%-- 
            a4j:support action="#{detailBeanName.resetRaiseList}"
            event="onchange" /
          --%>
        </t:panelGroup>
      </t:panelGroup>
      <%-- 
        raisePanel removed from rerender
      --%>
      <!-- moved by Assmaa Omar hr-666 point 14-->
      <t:panelGroup>
        <h:outputText styleClass="lable01" value="#{resourcesBundle.bouns_date}" rendered="#{managedConstantsBean.kwaitNationality==pageBeanName.pageDTO.citizensResidentsDTO.nationality}"/>
      </t:panelGroup>
      <t:panelGroup forceId="true" id="nextRaisePnlGrp">
        <t:inputText disabled="true" styleClass="textbox" value="#{pageBeanName.pageDTO.dateOfNextRaise}" forceId="true" id="employees_dateOfNextRaise"  rendered="#{managedConstantsBean.kwaitNationality==pageBeanName.pageDTO.citizensResidentsDTO.nationality}" >
              <f:converter converterId="SqlDateConverter"/>
        </t:inputText>
      </t:panelGroup>
      
      <!--end -->
      <t:panelGroup rendered="#{detailBeanName.eqTypeTemp}" colspan="4"
                    style="background-color:#ffffff;">
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
          <htm:tr>
            <htm:td width="9">
              <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9"
                       height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
              <t:outputLabel value=" #{resourcesBundle.eq_job_data}"
                             styleClass="lable01"/>
            </htm:td>
          </htm:tr>
          <htm:tr>
            <htm:td colspan="2" height="1">
              <htm:img src="../../../../app/media/images/seprator_group.jpg"
                       width="100%" height="1"/>
            </htm:td>
          </htm:tr>
        </htm:table>
      </t:panelGroup>
      <h:outputText rendered="#{detailBeanName.eqTypeTemp}"
                    value="#{resourcesBundle.cader}"/>
      <t:panelGroup rendered="#{detailBeanName.eqTypeTemp}" forceId="true"
                    id="eqCaderPanel">
        <t:inputText disabled="true" tabindex="8888888888"
                     styleClass="textboxmedium" forceId="true" id="eqCaderText"
                     value="#{detailBeanName.eqCaderName}"/>
        <t:panelGroup>
          <f:verbatim>
            &nbsp;
          </f:verbatim>
          <a4j:commandButton value="#{globalResources.Available}"
                             styleClass="cssButtonSmaller"
                             reRender="lovDivPanelGroup,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn"
                             action="#{detailBeanName.showEqCaderListOfValuesDiv}"
                             oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
          <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim>
            <br/>
          </f:verbatim>
          <%-- 
            c2:requiredFieldValidator componentToValidate="employees_cader"
            errorMessage="#{globalResources.missingField}" highlight="false"
            display="dynamic" /
          --%>
          <c:requiredFieldValidator active="#{empMainDataBean_crs.eqTypeTemp}"
                                    componentToValidate="eqCaderText"
                                    errorMessage="#{globalResources.missingField}"
                                    highlight="false" display="dynamic"/>
        </t:panelGroup>
      </t:panelGroup>
      <h:outputText rendered="#{detailBeanName.eqTypeTemp}"
                    value="#{resourcesBundle.group}"/>
      <t:panelGroup rendered="#{detailBeanName.eqTypeTemp}" forceId="true"
                    id="eqGroupPanel">
        <t:selectOneMenu forceId="true" id="eq_employees_group"
                         styleClass="DropdownboxMedium2"
                         value="#{detailBeanName.eqGroupCode}">
          <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
          <t:selectItems value="#{detailBeanName.eqGroupList}"
                         itemLabel="#{group.name}"
                         itemValue="#{group.code.elmguideCode}" var="group"/>
          <a4j:support reRender="employeeMainDataPanel"
                       action="#{detailBeanName.resetEqDegreeAndRaiseList}"
                       event="onchange"/>
        </t:selectOneMenu>
        <%-- 
          raisePanel removed from rerender
        --%>
        <t:panelGroup>
          <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim>
            <br/>
          </f:verbatim>
          <c:requiredFieldValidator active="#{empMainDataBean_crs.eqTypeTemp}"
                                    componentToValidate="eq_employees_group"
                                    errorMessage="#{globalResources.missingField}"
                                    highlight="false" display="dynamic"/>
        </t:panelGroup>
      </t:panelGroup>
      <h:outputText rendered="#{detailBeanName.eqTypeTemp}"
                    value="#{resourcesBundle.degree}"/>
      <t:panelGroup rendered="#{detailBeanName.eqTypeTemp}" forceId="true"
                    id="eqDegreePanel">
        <t:inputText forceId="true" id="eq_employees_degree"
                     styleClass="textboxmedium"
                     value="#{detailBeanName.eqRaiseName}" disabled="true"/>
        <f:verbatim>
          &nbsp;
        </f:verbatim>
        <a4j:commandButton value="..." styleClass="cssButtonSmaller"
                           action="#{detailBeanName.OpenEqTreeDiv}"
                           oncomplete="changeVisibilityDiv(window.blocker,window.divTree);return false;"
                           reRender="searchText,cancelsearchpanel,noResult,treeDivPanel,radioTreeDivPanel11,okPanel"
                           disabled="#{!empMainDataBean_crs.civilExist && pageBeanName.maintainMode==0}"/>
        <t:panelGroup>
          <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <f:verbatim>
            <br/>
          </f:verbatim>
          <c:requiredFieldValidator active="#{empMainDataBean_crs.eqTypeTemp}"
                                    componentToValidate="eq_employees_degree"
                                    errorMessage="#{globalResources.missingField}"
                                    highlight="false" display="dynamic"/>
          <%-- 
            a4j:support action="#{detailBeanName.resetRaiseList}"
            event="onchange" /
          --%>
        </t:panelGroup>
      </t:panelGroup>
      <t:panelGroup rendered="#{detailBeanName.eqTypeTemp}" colspan="2"/>
      <!-- added by Assmaa Omar hr-666 point 13-->
      <t:panelGroup colspan="4" style="background-color:#ffffff;">
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
          <htm:tr>
            <htm:td width="9">
              <htm:img src="../../../../app/media/images/op_arrow.jpg" width="9"
                       height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
              <t:outputLabel value=" #{resourcesBundle.qualificationData}"
                             styleClass="lable01"/>
            </htm:td>
          </htm:tr>
          <htm:tr>
            <htm:td colspan="2" height="1">
              <htm:img src="../../../../app/media/images/seprator_group.jpg"
                       width="100%" height="1"/>
            </htm:td>
          </htm:tr>
        </htm:table>
      </t:panelGroup>
      <!-- end-->
      <t:panelGroup colspan="4"
                    rendered="#{pageBeanName.maintainMode==1 && !empMainDataBean_crs.disableQualificationEditting}">
        <t:panelGrid align="center">
          <t:outputLabel value="#{resourcesBundle.QulAddedSuccessfullyandWillBeShownLatetr}"
                         styleClass="errMsg"/>
        </t:panelGrid>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.educationLevel}"/>
      <t:panelGroup>
        <t:selectOneMenu value="#{detailBeanName.qulLevelCode}"
                         readonly="#{empMaintainBean_crs.maintainMode==1||(empMaintainBean_crs.maintainMode==0 && detailBeanName.disableQualificationEditting)}"
                         forceId="true" id="employees_educationLevel"
                         styleClass="DropdownboxMedium2">
          <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                        itemLabel="#{resourcesBundle.select}"/>
          <t:selectItems value="#{detailBeanName.educationLevelList}"
                         itemLabel="#{educationLevelList.name}"
                         itemValue="#{educationLevelList.code.key}"
                         var="educationLevelList"/>
          <a4j:support event="onchange" reRender="QulPanel"/>
        </t:selectOneMenu>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.qualification}"/>
      <t:panelGroup forceId="true" id="QulPanel">
        <t:inputText readonly="true" tabindex="888888899"
                     value="#{detailBeanName.personQulDTO.qualificationsDTO.name}"
                     styleClass="textboxmedium" forceId="true"
                     id="qualification"
                     disabled="#{empMaintainBean_crs.maintainMode==1||(empMaintainBean_crs.maintainMode==0 && detailBeanName.disableQualificationEditting)|| ((detailBeanName.qulLevelCode==managedConstantsBean.virtualStringValueConstant)||(detailBeanName.qulLevelCode==null))}"/>
        <f:verbatim>
          &nbsp;&nbsp;
        </f:verbatim>
        <!--|| (detailBeanName.qulLevelCode == null || detailBeanName.qulLevelCode == managedConstantsBean.virtualStringValueConstant) can be added in disable for button but in this case i need to make leve ajax-->
        <a4j:commandButton id="qualification_button"
                           disabled="#{empMaintainBean_crs.maintainMode==1||(empMaintainBean_crs.maintainMode==0 && detailBeanName.disableQualificationEditting)|| ((detailBeanName.qulLevelCode==managedConstantsBean.virtualStringValueConstant)||(detailBeanName.qulLevelCode==null))}"
                           value="#{globalResources.Available}"
                           styleClass="cssButtonSmaller"
                           reRender="lovDivPanelGroup,qualification,lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_searchRadioBtn"
                           action="#{detailBeanName.showListOfQulValuesDiv}"
                           oncomplete="changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"
                      rendered="#{(pageBeanName.maintainMode==0)}"/>
        <f:verbatim>
          <br/>
        </f:verbatim>
        <c:requiredFieldValidator active="#{empMaintainBean_crs.maintainMode==0 && !empMainDataBean_crs.disableQualificationEditting}"
                                  componentToValidate="qualification"
                                  errorMessage="#{globalResources.missingField}"
                                  highlight="false" display="dynamic"/>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.qualificationCountry}"/>
      <t:panelGroup forceId="true" id="qualificationCountryPanel">
        <t:selectOneMenu forceId="true" value="#{detailBeanName.qulCountryCode}"
                         readonly="#{empMaintainBean_crs.maintainMode==1||(empMaintainBean_crs.maintainMode==0 && detailBeanName.disableQualificationEditting)}"
                         id="employees_qualificationCountry"
                         styleClass="DropdownboxMedium2">
          <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                        itemLabel="#{resourcesBundle.select}"/>
          <t:selectItems value="#{detailBeanName.qulCountriesList}"
                         itemLabel="#{qulCountriesList.name}"
                         itemValue="#{qulCountriesList.code.key}"
                         var="qulCountriesList"/>
          <a4j:support event="onchange"
                       actionListener="#{detailBeanName.countryChanged}"
                       reRender="employees_qualificationCenter,qualificationCenterPanel"/>
        </t:selectOneMenu>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.qualificationMinistry}"/>
      <t:panelGroup forceId="true" id="qualificationCenterPanel">
        <t:selectOneMenu value="#{detailBeanName.qulCenterCode}"
                         readonly="#{empMaintainBean_crs.maintainMode==1||(empMaintainBean_crs.maintainMode==0 && detailBeanName.disableQualificationEditting)}"
                         forceId="true" id="employees_qualificationCenter"
                         styleClass="DropdownboxMedium2"
                         disabled="#{(detailBeanName.qulCountryCode==null)||(detailBeanName.qulCountryCode==managedConstantsBean.virtualStringValueConstant)}">
          <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                        itemLabel="#{resourcesBundle.select}"/>
          <t:selectItems value="#{detailBeanName.qulCentersList}"
                         itemLabel="#{qulCentersList.name}"
                         itemValue="#{qulCentersList.code.key}"
                         var="qulCentersList"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"
                      rendered="#{(pageBeanName.maintainMode==0)}"/>
        <f:verbatim>
          <br/>
        </f:verbatim>
        <c:compareValidator componentToValidate="employees_qualificationCenter"
                            operator="not"
                            active="#{empMaintainBean_crs.maintainMode==0 && !empMainDataBean_crs.disableQualificationEditting}"
                            componentToCompare="virtualvalueId"
                            errorMessage="#{globalResources.missingField}"
                            highlight="false" display="dynamic"/>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.qualificationDate}"/>
      <t:panelGroup>
        <t:inputCalendar value="#{detailBeanName.personQulDTO.qualificationDate}"
                         popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                         popupDateFormat="dd/MM/yyyy" forceId="true"
                         id="qualificationDateTwo" size="20" maxlength="200"
                         styleClass="textbox"
                         currentDayCellClass="currentDayCell"
                         renderAsPopup="true" align="#{globalResources.align}"
                         popupLeft="#{shared_util.calendarpopupLeft}"
                         renderPopupButtonAsImage="true"
                         title="#{globalResources.inputCalendarHelpText}"
                         disabled="#{empMaintainBean_crs.maintainMode==1||(empMaintainBean_crs.maintainMode==0 && detailBeanName.disableQualificationEditting)}">
          <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
        <h:outputText value="*" styleClass="mandatoryAsterisk"
                      rendered="#{(pageBeanName.maintainMode==0)}"/>
        <f:verbatim>
          <br/>
        </f:verbatim>
        <c:requiredFieldValidator componentToValidate="qualificationDateTwo"
                                  active="#{empMaintainBean_crs.maintainMode==0 && !empMainDataBean_crs.disableQualificationEditting}"
                                  errorMessage="#{globalResources.missingField}"
                                  highlight="false" display="dynamic"/>
        <c:dateFormatValidator componentToValidate="qualificationDateTwo"
                               errorMessage="#{resourcesBundle.dateFormat}"
                               highlight="false" display="dynamic"/>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.grade}"/>
      <t:panelGroup>
        <t:inputText maxlength="5" onkeypress="enabelFloatOnly(this);"
                     onkeyup="enabelFloatOnly(this);"
                     value="#{detailBeanName.personQulDTO.qualificationDegree}"
                     readonly="#{empMaintainBean_crs.maintainMode==1||(empMaintainBean_crs.maintainMode==0 && detailBeanName.disableQualificationEditting)}"
                     forceId="true" id="grade" styleClass="textbox"
                     disabled="#{empMaintainBean_crs.maintainMode==1||(empMaintainBean_crs.maintainMode==0 && detailBeanName.disableQualificationEditting)}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"
                      rendered="#{(pageBeanName.maintainMode==0)}"/>
        <f:verbatim>
          <br/>
        </f:verbatim>
        <%-- 
          c2:requiredFieldValidator componentToValidate="grade"
          active="#{!empMainDataBean_crs.disableQualificationEditting}"
          errorMessage="#{globalResources.missingField}" highlight="false"
          display="dynamic" /
        --%>
        <c:regularExpressionValidator pattern="/^(\\d{1,3}(\\.\\d{1,2})?$)/"
                                      componentToValidate="grade"
                                      active="#{empMaintainBean_crs.maintainMode==0 && !empMainDataBean_crs.disableQualificationEditting}"
                                      errorMessage="#{globalResources.messageErrorForAdding}"
                                      highlight="false" display="dynamic"/>
        <c:rangeValidator maxValue="100" minValue="0"
                          componentToValidate="grade"
                          active="#{empMaintainBean_crs.maintainMode==0 && !empMainDataBean_crs.disableQualificationEditting }"
                          errorMessage="#{resourcesBundle.valueRangeErr}"
                          highlight="false" display="dynamic"/>
      </t:panelGroup>
  <%--    <t:panelGroup colspan="4" style="background-color:#ffffff;">
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
          <htm:tr>
            <htm:td width="9">
              <htm:img src="../../../app/media/images/op_arrow.jpg" width="9"
                       height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
              <t:outputLabel value="#{resourcesBundle.bankData}"
                             styleClass="lable01"/>
            </htm:td>
          </htm:tr>
          <htm:tr>
            <htm:td colspan="2" height="1">
              <htm:img src="../../../app/media/images/seprator_group.jpg"
                       width="100%" height="1"/>
            </htm:td>
          </htm:tr>
        </htm:table>
      </t:panelGroup>
      <!-- end-->
      <h:outputText value="#{resourcesBundle.bank}"/>
      <t:panelGroup>
        <t:selectOneMenu forceId="true" id="employees_bank"
                         styleClass="DropdownboxMedium2"
                         value="#{detailBeanName.selectedBankCode}"
                         converter="javax.faces.Long">
          <a4j:support event="onchange" action="#{detailBeanName.filterByBank}"
                       reRender="bankBranchesPanel,validScriptPanel"/>
          <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
          <t:selectItems value="#{detailBeanName.bankList}"
                         itemLabel="#{bankList.name}"
                         itemValue="#{bankList.code.bankCode}" var="bankList"/>
        </t:selectOneMenu>
      </t:panelGroup>
      <h:outputText value="#{resourcesBundle.bransh}"/>
      <t:panelGroup forceId="true" id="bankBranchesPanel">
        <t:selectOneMenu forceId="true" id="employees_bransh"
                         styleClass="DropdownboxMedium2"
                         value="#{pageBeanName.pageDTO.bankBranchKey}"
                         disabled="#{detailBeanName.selectedBankCode==null}">
          <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
          <t:selectItems value="#{detailBeanName.bankBranchesList}"
                         itemLabel="#{bankBranchesList.name}"
                         itemValue="#{bankBranchesList.code.key}"
                         var="bankBranchesList"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"
                      rendered="#{empMainDataBean_crs.selectedBankCode!=null}"/>
        <htm:br/>
        <c:requiredFieldValidator componentToValidate="employees_bransh"
                                  active="#{empMainDataBean_crs.selectedBankCode!=null}"
                                  errorMessage="#{globalResources.missingField}"
                                  highlight="false" display="dynamic"/>
      </t:panelGroup>
   
      <h:outputText value="#{resourcesBundle.employees_bank_account}"/>
      <t:panelGroup>
        <t:inputText forceId="true" onblur="foucsAfterLastElemenyOnPage()"
                     id="employees_bank_account" styleClass="textboxmedium"
                     value="#{pageBeanName.pageDTO.accountNo}" maxlength="20"/>
      </t:panelGroup>
      <t:outputLabel value=""/>
      <t:outputLabel value=""/>--%>
    </t:panelGrid>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="qualificationDateTwo,50,-50:employees_hireDate1,50,150:employees_start_work_date,100,130"/>
<t:inputHidden forceId="true" id="hireDateHF">
    <f:converter converterId="SqlDateConverter"/>
</t:inputHidden>

  </t:panelGroup><f:verbatim>
    <script type="text/javascript"> 
        setFocusAtMyFirstElement();
        function setFocusAtMyFirstElement(){
            if(document.getElementById('check_civil_btn') != null){
                document.getElementById('employees_hiretypes').focus();
                document.getElementById('employees_hiretypes').focus();
            }else {
                document.getElementById('employees_ministryFileNo1').focus();
                document.getElementById('employees_ministryFileNo1').focus();
            }
        }

        function foucsAfterLastElemenyOnPage(){
            if(document.getElementById('check_civil_btn') != null){
                document.getElementById('employees_hiretypes').focus();
                document.getElementById('employees_hiretypes').focus();
            }else if(document.getElementById('reset_btn') != null){                
                document.getElementById('finish_btn').focus();
            }
        }
        
        function foucsAfterFileNo(){
            if(document.getElementById('reset_btn') != null){                
                document.getElementById('reset_btn').focus();                
            }else{
                document.getElementById('employees_start_work_date').focus();
            }
        }
        
        function disableQualification()
        {
            if(document.getElementById('employees_educationLevel').value==-100)
                document.getElementById('myForm:qualification_button').disabled=true;
            
        }
        function calcNextRaiseJs() {
            
            if(document.getElementById('employees_dateOfNextRaise') == null){
                return; // not kwt
            }
            var oldValue = document.getElementById('hireDateHF').value;
            var newValue = document.getElementById('employees_hireDate1').value;
            if(oldValue == newValue){
                return ; // no updates
            }
            calcNextRaiseJsFunction();
            document.getElementById('hireDateHF').value = newValue;
        }
        
    </script>
  </f:verbatim>
