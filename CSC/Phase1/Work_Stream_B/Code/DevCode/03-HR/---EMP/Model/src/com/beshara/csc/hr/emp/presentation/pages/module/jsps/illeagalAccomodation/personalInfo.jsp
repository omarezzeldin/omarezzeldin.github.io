<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <t:messages showDetail="true" showSummary="true"/>
    <h:form id="myForm" binding="#{illeagalAccomodationPersonalInfo.frm}">
        <f:loadBundle basename="com.beshara.csc.hr.vac.presentation.resources.vac_ar" var="resourcesBundle"/>
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{illeagalAccomodationMaintainBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{illeagalAccomodationPersonalInfo}">
                <tiles:insert flush="false" definition="illeagalAccomodationPersonalInfo.page">
                    <t:saveState value="#{pageBeanName.pageDTO}" id="mainostep"/>
                    <t:saveState value="#{pageBeanName.nextNavigationCase}" id="mainnstep"/>
                    <t:saveState value="#{pageBeanName.previousNavigationCase}" id="mainpstep"/>
                    <t:saveState value="#{pageBeanName.finishNavigationCase}" id="mainfstep"/>
                    <t:saveState value="#{pageBeanName.currentNavigationCase}" id="maincstep"/>
                    <t:saveState value="#{pageBeanName.currentStep}" id="mainstep"/>
                    <t:saveState value="#{pageBeanName.maintainMode}" id="mainmode"/>
                    <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
                    <t:saveState value="#{pageBeanName.showPrevious}"/>
                    <t:saveState  value="#{pageBeanName.maintainMode}" />
                    <t:saveState  value="#{pageBeanName.pageMode}" />
                    <t:saveState value="#{ilLegalCitizensListBean.requestStatust}"/>
                    <t:saveState value="#{ilLegalCitizensListBean.myDataTable}"/>
                   <t:saveState value="#{ilLegalCitizensListBean.filterDTO}"/>
                    <t:saveState value="#{ilLegalCitizensListBean.myTableData}"/>
                    <%-- start paging--%>
                    
                     <t:saveState value="#{illeagalAccomodationMaintainBean.pageDTO}"/>
                    <t:saveState value="#{detailBeanName.streetsList}"/>
                    <t:saveState value="#{detailBeanName.kwMapName}" id="kwMapName"/>
                    <t:saveState value="#{detailBeanName.civilId}" />
                    <t:saveState value="#{detailBeanName.searchInEmpMode}" />
                    <t:saveState value="#{detailBeanName.civilExist}" />
                    <t:saveState value="#{detailBeanName.validCivilId}" />
                    <t:saveState value="#{detailBeanName.candidateName}" />
                    <t:saveState value="#{detailBeanName.genderType}" />
                    <t:saveState value="#{detailBeanName.orderType}" />
                    <t:saveState value="#{detailBeanName.maritalStatus}" />
                    <t:saveState value="#{detailBeanName.birthDate}" />
                    <t:saveState value="#{detailBeanName.selectedCategory}" />
                    <t:saveState value="#{detailBeanName.phoneNum}" />
                    <t:saveState value="#{detailBeanName.mobileNum}" />
                    <t:saveState value="#{detailBeanName.kwMapCode}" />
                    <t:saveState value="#{detailBeanName.streetName}" />
                    <t:saveState value="#{detailBeanName.buildingNo}" />
                    <t:saveState value="#{detailBeanName.floorNum}" />
                    <t:saveState value="#{detailBeanName.unitNo}" />
                    <t:saveState value="#{detailBeanName.genderInputHidden}" />
                    <t:saveState value="#{detailBeanName.maritalStatusInputHidden}" />
                    <t:saveState value="#{detailBeanName.categoryInputHidden}" />
                    <t:saveState value="#{detailBeanName.leagalCivilAccomodation}" />
                    <t:saveState value="#{detailBeanName.newCivil}" />
                    
  <t:saveState value="#{detailBeanName.catList}" />
                    <t:saveState value="#{detailBeanName.pageMode}" />
                    <t:saveState value="#{detailBeanName.pageDTO}" />
                    <t:saveState value="#{detailBeanName.kwtCitizensResidentsDTO}" />
                    <t:saveState value="#{qulListIntegrationBean.kwtCitizenDTO}"/>
                    <t:saveState value="#{qulListIntegrationBean.civilId}"/>
                    <t:saveState value="#{qulListIntegrationBean.citizinFullName}"/>
                    <t:saveState value="#{qulListIntegrationBean.personsInformationDTOList}"/>
                    <t:saveState value="#{qulListIntegrationBean.selectedDTOS}"/>
                    <t:saveState value="#{qulListIntegrationBean.listSize}"/>
                    <t:saveState value="#{qulListIntegrationBean.pageType}"/>
                    <t:saveState value="#{qulListIntegrationBean.qulListPageinWizardBar}"/>
                     <t:saveState value="#{qulListIntegrationBean.saveStateList}"/>
                     <t:saveState value="#{qulListIntegrationBean.customCurentQual}"/>
                    <t:saveState value="#{qulListIntegrationBean.saveInDB}"/>
                    <t:saveState value="#{qulListIntegrationBean.notSavedPersonsInformationDTOList}"/>
                    <t:saveState value="#{qulListIntegrationBean.dataTablestyleClass}"/>
                    <t:saveState value="#{qulListIntegrationBean.needAddQualification}"/>
                    <t:saveState value="#{qulListIntegrationBean.addQualificationIntegrationpage}"/>
                    <t:saveState value="#{qulListIntegrationBean.customCurentQual}"/>
                </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
    </h:form>
    <t:panelGroup forceId="true" id="scriptpanel1">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
</f:view>
