<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <t:messages showDetail="true" showSummary="true"/>
    <h:form id="myForm" binding="#{qulListIntegrationBean.frm}">
        <f:loadBundle basename="com.beshara.csc.hr.vac.presentation.resources.vac_ar" var="resourcesBundle"/>
        <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
        <f:loadBundle basename="com.beshara.csc.nl.qul.integration.presentation.resources.qulintegration" var="qulintegration"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{illeagalAccomodationMaintainBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{qulListIntegrationBean}">
                <tiles:insert flush="false" definition="illeagalAccomodationQualification.page">
                    <t:saveState value="#{pageBeanName.pageDTO}" id="mainostep"/>
                    <t:saveState value="#{pageBeanName.nextNavigationCase}" id="mainnstep"/>
                    <t:saveState value="#{pageBeanName.previousNavigationCase}" id="mainpstep"/>
                    <t:saveState value="#{pageBeanName.finishNavigationCase}" id="mainfstep"/>
                    <t:saveState value="#{pageBeanName.currentNavigationCase}" id="maincstep"/>
                    <t:saveState value="#{pageBeanName.currentStep}" id="mainstep"/>
                    <t:saveState value="#{pageBeanName.maintainMode}" id="mainmode"/>
                    <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
                    <t:saveState value="#{pageBeanName.showPrevious}"/>
                    <t:saveState value="#{pageBeanName.pageMode}"/>
                    <t:saveState value="#{ilLegalCitizensListBean.requestStatust}"/>
                     <t:saveState value="#{ilLegalCitizensListBean.myDataTable}"/>
                    <t:saveState value="#{ilLegalCitizensListBean.filterDTO}"/>
                    <t:saveState value="#{ilLegalCitizensListBean.myTableData}"/>
                    
                    <%-- start paging--%>
                    <t:saveState value="#{detailBeanName.civilId}"/>
                    <t:saveState value="#{detailBeanName.kwtCitizenDTO}"/>
                    <t:saveState value="#{detailBeanName.citizinFullName}"/>
                    <t:saveState value="#{detailBeanName.personsInformationDTOList}"/>
                    <t:saveState value="#{detailBeanName.selectedDTOS}"/>
                    <t:saveState value="#{detailBeanName.listSize}"/>
                    
                    <t:saveState value="#{detailBeanName.pageType}"/>
                    <t:saveState value="#{detailBeanName.addQualificationIntegrationpage}"/>
                    <t:saveState value="#{detailBeanName.qulListPageinWizardBar}"/>
                    <t:saveState value="#{detailBeanName.saveStateList}"/>
                    <t:saveState value="#{detailBeanName.saveInDB}"/>
                    <t:saveState value="#{detailBeanName.notSavedPersonsInformationDTOList}"/>
                    <t:saveState value="#{detailBeanName.dataTablestyleClass}"/>
                    <t:saveState value="#{detailBeanName.needAddQualification}"/>
                    <t:saveState value="#{detailBeanName.customCurentQual}"/>
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.streetsList}"/>
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.kwMapName}" id="kwMapName"/>
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.civilId}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.orderType}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.searchInEmpMode}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.civilExist}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.validCivilId}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.candidateName}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.genderType}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.maritalStatus}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.birthDate}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.selectedCategory}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.phoneNum}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.mobileNum}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.kwMapCode}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.streetName}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.buildingNo}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.floorNum}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.unitNo}" />
                    <t:saveState value="#{illeagalAccomodationPersonalInfo.kwtCitizensResidentsDTO}" />
                     <t:saveState value="#{illeagalAccomodationPersonalInfo.catList}" />
                </tiles:insert>
            
             </t:aliasBean>
        </t:aliasBean>
    </h:form>
    <t:panelGroup forceId="true" id="scriptpanel1">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
</f:view>
