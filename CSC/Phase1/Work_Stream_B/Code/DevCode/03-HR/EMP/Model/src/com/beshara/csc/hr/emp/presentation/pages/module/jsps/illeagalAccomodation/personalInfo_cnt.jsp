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
    <t:panelGroup>
        <t:inputText onkeypress="FireButtonClick('filterByCivilButton');" onkeyup="enabelIntegerOnly(this);"
                     disabled="#{detailBeanName.civilExist || pageBeanName.pageMode == 1}" maxlength="12" forceId="true"
                     tabindex="1" id="CivilIdAdd" value="#{detailBeanName.civilId}" style="width: 104px;"
                     styleClass="textboxmedium"
                     onkeydown="onKeyDownFirstElement(event,'filterByCivilButton','BackButtonManyToMany')"/>
        <t:outputLabel value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.pageMode == 0}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:panelGroup forceId="true" id="btnPnlGrp">
            <t:commandButton forceId="true" id="filterByCivilButton" value="#{globalResources.Available}"
                             styleClass="cssButtonSmall" onclick="return validateInputForFormNotEmpty();" tabindex="2"
                             action="#{detailBeanName.filterByCivilId}"
                             rendered="#{!detailBeanName.civilExist && pageBeanName.pageMode == 0}"/>
            <%-- <a4j:commandButton type="button" value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"
                 id="searchBtn" rendered="#{!detailBeanName.civilExist && pageBeanName.pageMode == 0}"
                 oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"
                 action="#{detailBeanName.showSearchForEmployeeDiv}" reRender="customDiv1"/>--%>
            <a4j:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}"
                               rendered="#{detailBeanName.civilExist && pageBeanName.pageMode == 0}"
                               styleClass="cssButtonSmall" action="#{detailBeanName.reSetData}" oncomplete="clearMsg();"
                               reRender="employeeMainDataPanel,phonesPanel,addressPanel"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:outputText forceId="true" id="inputText_err_missing" value="#{globalResources.missingField}"
                          styleClass="error" style="display:none"/>
            <t:outputText forceId="true" id="inputText_notComplete" value="#{globalResources.civil_no_not_less_than_12}"
                          styleClass="error" style="display:none"/>
            <t:outputText forceId="true" id="errInvalidCivilID" value="#{resourcesBundle.civilId_not_found}"
                          rendered="#{!detailBeanName.validCivilId}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="leagalCivilAccomodationErrMsg"
                          value="#{resourcesBundle.illeagal_civil_accomodation}"
                          rendered="#{!detailBeanName.leagalCivilAccomodation}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="newCivilErrMsg" value="#{resourcesBundle.new_civil_err}"
                          rendered="#{!detailBeanName.newCivil}" styleClass="errMsg"/>
            <t:outputText forceId="true" id="data_notComplete_error" value="#{detailBeanName.dataNotcompleteErrorMSG}"
                          rendered="#{detailBeanName.dataNotcomplete}" styleClass="errMsg"/>
            <c2:requiredFieldValidator componentToValidate="CivilIdAdd" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" active="true" highlight="false"/>
            <c2:regularExpressionValidator componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/" active="true"
                                           errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false"
                                           display="dynamic"/>
        </t:panelGroup>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.candidate_name}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="employee_name_panel">
        <t:inputText forceId="true" id="employees_nam1" styleClass="textboxmedium"
                     value="#{detailBeanName.candidateName}" tabindex="4" disabled="true"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.gender}" styleClass="lable01"/>
    <t:panelGroup>
        <%-- <t:inputText value="#{detailBeanName.genderType}" disabled="true" forceId="true" id="employee_gender_type"
             tabindex="8" styleClass="textboxmedium"/>--%>
        <t:selectOneMenu value="#{detailBeanName.genderType}" disabled="true" forceId="true" id="employee_gender_type"
                         tabindex="8" styleClass="DropdownboxMedium2">
            <f:selectItem itemValue="null" itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{detailBeanName.genderTypeList}" itemLabel="#{genderTypeList.name}"
                           itemValue="#{genderTypeList.code.key}" var="genderTypeList"/>
            <a4j:support reRender="employeeMainDataPanel" event="onchange">
                <t:updateActionListener property="#{detailBeanName.genderInputHidden}"
                                        value="#{detailBeanName.genderType}"/>
            </a4j:support>
        </t:selectOneMenu>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.status}" styleClass="lable01"/>
    <t:panelGroup>
        <%-- <t:inputText value="#{detailBeanName.maritalStatus}" disabled="true" forceId="true"
             id="employee_maritalStatus" tabindex="8" styleClass="textboxmedium"/>--%>
        <t:selectOneMenu value="#{detailBeanName.maritalStatus}" disabled="true" forceId="true"
                         id="employee_maritalStatus" tabindex="8" styleClass="DropdownboxMedium2">
            <f:selectItem itemValue="null" itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems value="#{detailBeanName.maritalStatusList}" itemLabel="#{maritalStatusList.name}"
                           itemValue="#{maritalStatusList.code.key}" var="maritalStatusList"/>
            <a4j:support reRender="employeeMainDataPanel" event="onchange">
                <t:updateActionListener property="#{detailBeanName.maritalStatusInputHidden}"
                                        value="#{detailBeanName.maritalStatus}"/>
            </a4j:support>
        </t:selectOneMenu>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.emp_birth_date}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         disabled="true" forceId="true" id="employee_birth_date" size="20"
                         value="#{detailBeanName.birthDate}"
                         onblur="return validateInputCalenderFormate('employee_birth_date','null','null');"
                         maxlength="200" styleClass="textboxmedium" tabindex="10" currentDayCellClass="currentDayCell"
                         renderAsPopup="true" align="#{globalResources.align}" onkeypress="checkaboutHireDate(event);"
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                         title="#{globalResources.inputCalendarHelpText}">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.emp_internal_exp_catName}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu id="categoryList" tabindex="3" forceId="true" styleClass="DropdownboxMedium2"
                         value="#{detailBeanName.selectedCategory}"
                         disabled="#{pageBeanName.pageMode == 0 && !detailBeanName.civilExist }">
            <f:selectItem itemValue="null" itemLabel="#{resourcesBundle.select}"/>
              <t:selectItems value="#{detailBeanName.catList}" itemLabel="#{catList.name}"
                           itemValue="#{catList.code.key}" var="catList"/>
            <a4j:support reRender="employeeMainDataPanel" event="onchange">
                <t:updateActionListener property="#{detailBeanName.categoryInputHidden}"
                                        value="#{detailBeanName.selectedCategory}"/>
            </a4j:support>
        </t:selectOneMenu>
        <%-- <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>--%>
        <t:inputHidden id="categoryInputHidden" value="#{detailBeanName.categoryInputHidden}" style="width: 1px;"
                       styleClass="textboxmedium"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <%-- <c2:requiredFieldValidator componentToValidate="categoryInputHidden" display="dynamic"
             errorMessage="#{globalResources.missingField}" active="true" highlight="false"/>--%>
        <%-- <t:outputText forceId="true" id="categoryList_err_missing" value="#{globalResources.missingField}"
             styleClass="error" style="display:none"/>--%>
    </t:panelGroup>
</t:panelGrid>
<t:panelGroup colspan="4" style="background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td width="9">
                <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
                <t:outputLabel value=" #{resourcesBundle.phonesInformation}" styleClass="lable01"/>
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
             forceId="true" id="phonesPanel" columns="4" align="cenetr" border="0">
    <h:outputText value="#{resourcesBundle.mobile}" styleClass="lable01"/>
    <t:inputText forceId="true" maxlength="20" id="mobile" tabindex="9" styleClass="textboxmedium"
                 value="#{detailBeanName.mobileNum}" onkeyup="enabelFloatOnly(this);"></t:inputText>
    <h:outputText value="#{resourcesBundle.phone}" styleClass="lable01"/>
    <t:inputText forceId="true" maxlength="200" id="phone" tabindex="9" styleClass="textboxmedium"
                 value="#{detailBeanName.phoneNum}" onkeyup="enabelFloatOnly(this);"></t:inputText>
</t:panelGrid>
<t:panelGroup colspan="4" style="background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td width="9">
                <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
                <t:outputLabel value=" #{resourcesBundle.addressInformation}" styleClass="lable01"/>
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
             forceId="true" id="addressPanel" columns="4" align="cenetr" border="0">
    <h:outputText value="#{resourcesBundle.cuntry_erea}" styleClass="lable01"/>
    <t:panelGroup>
        <%-- Missing List box--%>
        <t:inputText value="#{detailBeanName.kwMapName}" readonly="true" styleClass="textbox_175" forceId="true"
                     id="selectedNodeField"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton value="#{globalResources.Available}" action="#{detailBeanName.openTreeKwmap}" tabindex="8"
                           reRender="treeDivList,searchText1,cancelsearchpanel,noResult,clientErrorMessage,okPanel,radioTreeDivPanel,treeList,treeDivPanel"
                           styleClass="cssButtonSmall"
                           oncomplete="changeVisibilityDiv(window.blocker,window.divTree);settingFoucsAtTreeDiv();document.getElementById('theSelectedNodeId').value ='';"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.the_street}" styleClass="lable01"/>
    <t:selectOneMenu value="#{detailBeanName.streetName}" forceId="true" id="add_StreetID" tabindex="8"
                     styleClass="DropdownboxMedium2">
        <f:selectItem itemValue="null" itemLabel="#{resourcesBundle.select}"/>
        <t:selectItems value="#{detailBeanName.streetsList}" itemLabel="#{street.name}" itemValue="#{street.code.key}"
                       var="street"/>
    </t:selectOneMenu>
    <h:outputText value="#{resourcesBundle.the_buldings}" styleClass="lable01"/>
    <t:inputText forceId="true" id="buildingNo" styleClass="textboxmedium" value="#{detailBeanName.buildingNo}"
                 maxlength="20"/>
    <h:outputText value="#{resourcesBundle.floor_num}" styleClass="lable01"/>
    <t:inputText forceId="true" id="floor" styleClass="textboxmedium" value="#{detailBeanName.floorNum}" maxlength="3"
                 onkeyup="enabelIntegerOnly(this);"/>
    <h:outputText value="#{resourcesBundle.unitNo}" styleClass="lable01"/>
    <t:inputText forceId="true" id="unitNo" styleClass="textboxmedium" value="#{detailBeanName.unitNo}" maxlength="3"
                 onkeyup="enabelIntegerOnly(this);"/>
</t:panelGrid>
<t:panelGroup colspan="4" style="background-color:#ffffff;" rendered="#{pageBeanName.pageMode == 1}">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td width="9">
                <htm:img src="../../../app/media/images/op_arrow.jpg" width="9" height="9"/>
            </htm:td>
            <htm:td styleClass="group_title">
                <t:outputLabel value=" #{resourcesBundle.orderType}" styleClass="lable01"/>
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
             forceId="true" id="orderTypePnl" columns="4" align="cenetr" border="0"
             rendered="#{pageBeanName.pageMode == 1}">
    <h:outputText value="#{resourcesBundle.the_status}" styleClass="lable01"/>
    <h:inputText disabled="true" id="under_studdingID" styleClass="textbox_175"
                 rendered="#{detailBeanName.orderType==1}" value="#{resourcesBundle.under_studding}"/>
    <h:inputText disabled="true" id="approveID" styleClass="textbox_175" rendered="#{detailBeanName.orderType==2}"
                 value="#{resourcesBundle.approve}"/>
    <h:inputText disabled="true" id="refuseID" styleClass="textbox_175" rendered="#{detailBeanName.orderType==3}"
                 value="#{resourcesBundle.refuse}"/>
</t:panelGrid>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="employee_birth_date,23,97"/>
<script type="text/javascript">
  function clearMsg() {
      document.getElementById('errInvalidCivilID').style.display = "none";
      missingMsg.style.display = "none";
      complMsg.style.display = "none";
  }

  function validateInputForFormNotEmpty() {

      var input = document.getElementById('CivilIdAdd');
      var complMsg = document.getElementById('inputText_notComplete');
      var missingMsg = document.getElementById('inputText_err_missing');
      if (input == null || input.value == "") {
          missingMsg.style.display = "inline";
          complMsg.style.display = "none";

          return false;
      }
      else {
          missingMsg.style.display = "none";
          complMsg.style.display = "none";

      }

      if (input.value.length != 12) {
          complMsg.style.display = "inline";
          missingMsg.style.display = "none";

          return false;
      }
      else {
          complMsg.style.display = "none";

      }

  }
</script>
