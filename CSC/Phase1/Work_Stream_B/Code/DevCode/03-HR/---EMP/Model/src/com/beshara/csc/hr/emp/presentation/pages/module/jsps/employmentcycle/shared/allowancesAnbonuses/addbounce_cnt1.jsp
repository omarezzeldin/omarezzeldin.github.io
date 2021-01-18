<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGroup forceId="true" id="addBenRewardPage">
    <t:panelGrid forceId="true" id="cnt1Panel" columns="4" width="100%" rowClasses="row01,row02" cellspacing="0"
                 cellpadding="0">
        <t:outputText value="#{resourcesBundle.candidate_civilId_label}" styleclass="divtext"/>
        <t:inputText disabled="true" value="#{pageBeanName.civilId}" forceId="true" id="CivilIdAdd"
                     styleClass="textboxmedium"/>
        <t:outputText value="#{resourcesBundle.candidate_name_label}" styleclass="divtext"/>
        <t:inputText disabled="true" value="#{pageBeanName.fullName}" styleClass="textboxmedium" style="width:220px;"/>
        <t:outputText value="#{resourcesBundle.total_bounses}" styleclass="divtext"/>
        <t:inputText disabled="true" value="#{pageBeanName.totalBounces}" styleClass="textboxmedium" forceId="true"
                     id="totalBouncesId"/>
        <t:outputText value="#{resourcesBundle.basic_Salary}" styleclass="divtext"/>
        <t:inputText disabled="true" value="#{pageBeanName.basicSalary}" styleClass="textboxmedium" forceId="true"
                     id="basicSalaryId"/>
    </t:panelGrid>
    <t:panelGroup forceId="true" id="add_cnt_grp" rendered="#{!pageBeanName.viewBounses}">
        <t:panelGrid border="0" columnClasses="standardTable_Column" cellpadding="1" forceId="true" id="addPanel"
                     cellspacing="0" align="center" width="100%" columns="6" rowClasses="row01,row02">
            <t:panelGroup colspan="2">
                <t:panelGrid align="center" forceId="true" id="radioPanel" columns="1"
                             dir="#{shared_util.pageDirection}">
                    <t:selectOneRadio styleClass="radio1" onblur="document.getElementById('code').focus();"
                                      value="#{pageBeanName.selectedBenReward}"
                                      valueChangeListener="#{pageBeanName.selectedBenRewardChange}" forceId="true"
                                      id="radioBtn" disabled="#{pageBeanName.benefitRewardCodeExists}">
                        <f:selectItem id="benCode" itemLabel="#{resourcesBundle.Benefit}" itemValue="0"/>
                        <f:selectItem itemLabel="#{resourcesBundle.Reward}" itemValue="1"/>
                    </t:selectOneRadio>
                </t:panelGrid>
            </t:panelGroup>
            <t:panelGroup colspan="4">
                <t:panelGrid columns="2">
                    <t:outputText forceId="true" id="codeText" styleClass="divtext"
                                  value="#{resourcesBundle.BenefitRewardPayTypeCode}"/>
                    <t:panelGroup>
                        <t:inputText forceId="true" id="code" onkeypress="FireButtonClick('showAvailable')"
                                     value="#{pageBeanName.id}" styleClass="textbox" maxlength="6"
                                     readonly="#{pageBeanName.benefitRewardCodeExists}"
                                     onkeyup="disableCharacters(this);"/>
                        <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                        <t:panelGroup rendered="#{!pageBeanName.benefitRewardCodeExists}">
                            <t:commandButton forceId="true" id="showAvailable" styleClass="cssButtonSmall" type="button"
                                             value="#{globalResources.Available}"
                                             action="#{pageBeanName.fillSalElementGuide}"
                                             onclick="clearErrorMsg();validateCode();"/>
                            <a4j:jsFunction name="searchLines" action="#{pageBeanName.fillSalElementGuide}"
                                            reRender="addBenRewardPage, scriptpanel, controlBtns,elmGuideMaxValue"/>
                            <f:verbatim>&nbsp;</f:verbatim>
                        </t:panelGroup>
                        <t:panelGroup rendered="#{pageBeanName.benefitRewardCodeExists}">
                            <a4j:commandButton value="#{globalResources.reSetButton}" styleClass="cssButtonSmall"
                                               action="#{pageBeanName.reSetPage}"
                                               reRender="scriptpanel,addBenRewardPage,controlBtns"
                                               onblur="document.getElementById('fromDate').focus();"/>
                            <f:verbatim>&nbsp;</f:verbatim>
                        </t:panelGroup>
                        <a4j:commandButton type="button" id="Search" styleClass="cssButtonSmall"
                                           value="#{globalResources.SearchButton}"
                                           disabled="#{pageBeanName.benefitRewardCodeExists}"
                                           action="#{pageBeanName.openTreeDiv}"
                                           oncomplete="changeVisibilityDiv(window.blocker,window.pagedDivTree,'backButtonPagedTreeDiv');focusOnSearchText();"
                                           reRender="pagedTreeDiv"/>
                        <f:verbatim>
                            <br/>
                        </f:verbatim>
                        <c:requiredFieldValidator componentToValidate="code"
                                                  errorMessage="#{globalResources.missingField}"
                                                  display="dynamic" group="requiredFields"/>
                        <t:outputText forceId="true" id="benefitCodeWrong" value="#{resourcesBundle.must_choose_benfit}"
                                      rendered="#{pageBeanName.benefitRewardCodeWrong && pageBeanName.selectedBenReward ==0 }"
                                      styleClass="errMsg"/>
                        <t:outputText forceId="true" id="rewardCodeWrong" value="#{resourcesBundle.must_choose_reward}"
                                      rendered="#{pageBeanName.benefitRewardCodeWrong && pageBeanName.selectedBenReward == 1 }"
                                      styleClass="errMsg"/>
                    </t:panelGroup>
                </t:panelGrid>
            </t:panelGroup>
            <%-- **************************************************************--%>
            <t:outputText value="#{resourcesBundle.BenefitRewardType}" styleClass="divtext"/>
            <t:panelGroup colspan="5">
                <t:inputText styleClass="textboxXlarge" forceId="true" disabled="true" id="BenefitRewardType"
                             value="#{pageBeanName.pageDTO.salElementGuidesDTO.parentObject.parentObject.name}"/>
            </t:panelGroup>
            <%-- **************************************************************--%>
            <t:outputText value="#{resourcesBundle.Benefit_Reward}" styleClass="divtext"/>
            <t:panelGroup colspan="5">
                <t:inputText styleClass="textboxXlarge" disabled="true" forceId="true" id="Benefit_Reward"
                             value="#{pageBeanName.pageDTO.salElementGuidesDTO.parentObject.name}"/>
            </t:panelGroup>
            <%-- **************************************************************--%>
            <t:outputText value="#{resourcesBundle.category}" styleClass="divtext"/>
            <t:panelGroup colspan="5">
                <t:inputText styleClass="textboxXlarge" disabled="true" forceId="true" id="category"
                             value="#{pageBeanName.pageDTO.salElementGuidesDTO.name}"/>
            </t:panelGroup>
            <%-- **************************************************************--%>
            <t:outputText value="#{resourcesBundle.Value}" styleClass="divtext"/>
            <t:panelGrid columns="2">
                <t:inputText title="#{resourcesBundle.value_title}" styleClass="textbox"
                             disabled="#{pageBeanName.pageDTO.salElementGuidesDTO == null}"
                             value="#{pageBeanName.pageDTO.salElementGuidesDTO.value}" maxlength="9" id="Value"
                             forceId="true" onchange="trimBorders(Value);" onkeypress="enabelFloatOnly(this);"
                             onkeyup="enabelFloatOnly(this)"/>
                <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                <t:panelGroup>
                    <c:requiredFieldValidator componentToValidate="Value" errorMessage="#{globalResources.missingField}"
                                              display="dynamic"/>
                    <c:compareValidator componentToValidate="Value" componentToCompare="zeroValueHidden"
                                        display="dynamic" errorMessage="#{resourcesBundle.valueMustBeGreaterThanZero}"
                                        operator="gre"/>
                    <c:compareValidator componentToValidate="Value" componentToCompare="elmGuideMaxValue"
                                        display="dynamic" errorMessage="#{resourcesBundle.maxValueErr}"
                                        operator="lesEq"/>
                    <c:compareValidator componentToValidate="Value" componentToCompare="elmGuideMinValue"
                                        display="dynamic" errorMessage="#{resourcesBundle.minValueErr}"
                                        operator="greEq"/>
                    <c:regularExpressionValidator componentToValidate="Value" pattern="/^(\\d{1,5}(\\.\\d{1,3})?$)/"
                                                  display="dynamic"
                                                  errorMessage="#{globalResources.messageErrorForAdding}"/>
                </t:panelGroup>
            </t:panelGrid>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="elmGuideMaxValue" value="#{pageBeanName.elmGuideMaxValue}"/>
        <t:inputHidden forceId="true" id="elmGuideMinValue" value="#{pageBeanName.elmGuideMinValue}"/>
        <t:inputHidden id="zeroValueHidden" forceId="true" value="0"/>
    </t:panelGroup>
</t:panelGroup>
<t:panelGrid columns="1" border="0" align="center" forceId="true" id="controlBtns" rendered="#{!pageBeanName.viewBounses}">
    <t:commandButton onclick="return validateAllPage();"
                     action="#{pageBeanName.addbounces}" styleClass="cssButtonSmall" id="SaveButton"
                     value="#{globalResources.add}"/>
</t:panelGrid>


<script type="text/javascript">
  focusOnRadio();

  function focusOnRadio() {
      if (document.getElementById('code') != null) {
          document.getElementById('code').focus();
          document.getElementById('code').focus();
      }
  }

  function clearErrorMsg() {
      if (document.getElementById("rewardCodeWrong") != null) {
          document.getElementById("rewardCodeWrong").innerHTML = "";
      }
      if (document.getElementById("benefitCodeWrong") != null) {
          document.getElementById("benefitCodeWrong").innerHTML = "";
      }
  }

  function validateCode() {
      var returnFromValidation = validatemyForm('requiredFields');
      if (returnFromValidation) {
          searchLines();
      }
      else {
          return false;
      }
      return true;
  }
  function validateAllPage(){
    var result = validatemyForm('requiredFields');
    if(result) {
        return validatemyForm();
    }else{
        return false;
    }
  }
</script>
