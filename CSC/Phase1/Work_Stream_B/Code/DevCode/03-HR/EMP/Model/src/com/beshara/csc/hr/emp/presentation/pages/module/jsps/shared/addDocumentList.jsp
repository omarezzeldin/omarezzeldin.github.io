<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form enctype="multipart/form-data" id="myForm" binding="#{addDocAttachmentsListBean.frm}">
        <f:loadBundle basename="com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"
                      var="infintegrationBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{addDocAttachmentsListBean}">
            <tiles:insert flush="false" definition="addDocAttachments.page">
                <t:saveState value="#{pageBeanName.civilId}"/>
                <t:saveState value="#{pageBeanName.empName}"/>
                <t:saveState value="#{pageBeanName.selectedDocType}"/>
                <t:saveState value="#{pageBeanName.docTypeList}"/>
                <t:saveState value="#{pageBeanName.docName}"/>
                <t:saveState value="#{pageBeanName.docPath}"/>
                <t:saveState value="#{pageBeanName.fromDate}"/>
                <t:saveState value="#{pageBeanName.untilDate}"/>
                <t:saveState value="#{pageBeanName.attchmentPath}"/>
                <t:saveState value="#{pageBeanName.attchmentName}"/>
                <t:saveState value="#{pageBeanName.doctypeCode}"/>
                <t:saveState value="#{pageBeanName.fakeImageString}"/>
                <t:saveState value="#{pageBeanName.relativeFileName}"/>
                <t:saveState value="#{pageBeanName.uploadedFile}"/>
                <t:saveState value="#{pageBeanName.uploadingError}"/>
                <t:saveState value="#{pageBeanName.invalidImageType}"/>
                <t:saveState value="#{pageBeanName.detailsSavedStates}"/>
                <t:saveState value="#{pageBeanName.clonedPageDTO}"/>
                <t:saveState value="#{pageBeanName.selectedImagePath}"/>
                <t:saveState value="#{pageBeanName.currentDisplayList}"/>
                <t:saveState value="#{pageBeanName.detailsSavedStates}"/>
                <t:saveState value="#{pageBeanName.personDocumntsDTO}"/>
                <t:saveState value="#{pageBeanName.personDocAttachemntsDTO}"/>
                <t:saveState value="#{pageBeanName.attachmentList}"/>
                <t:saveState value="#{pageBeanName.moduleName}"/>
                <t:saveState value="#{pageBeanName.disableSelectedDocType}"/>
                <t:saveState value="#{pageBeanName.filesTransaction}"/>
                <t:saveState value="#{pageBeanName.file}"/>
                <t:saveState value="#{pageBeanName.doctypeName}"/>
                <t:saveState value="#{pageBeanName.forShowAttachmentOnly}"/>
                <t:saveState value="#{jobsSelectionListBean.categoryList}"/>
                <t:saveState value="#{jobsSelectionListBean.ministryList}"/>
                <t:saveState value="#{jobsSelectionListBean.selectedMinistry}"/>
                <t:saveState value="#{jobsSelectionListBean.selectedDTOS}"/>
                <t:saveState value="#{jobsSelectionListBean.selectedPageDTO}"/>
                <t:saveState value="#{jobsSelectionListBean.selectedCategory}"/>
                <t:saveState value="#{fatwaSelectionListBean.approveBtnShow}"/>
                <t:saveState value="#{selectionListBean.approveBtnShow}"/>
                 <t:saveState value="#{pageBeanName.empDTO}"/>
                      <t:saveState value="#{jobsSelectionListBean.currentPageIndex}"/>
                            <t:saveState value="#{jobsSelectionListBean.oldPageIndex}"/>
                           <t:saveState value="#{jobsSelectionListBean.civilId}"/>
                  <t:saveState value="#{jobsSelectionListBean.civilName}"/>
                  <t:saveState value="#{jobsSelectionListBean.civilExist}"/>
                   <t:saveState value="#{jobsSelectionListBean.validCivilId}"/>
                        <t:saveState value="#{jobsSelectionListBean.searchDTO1}"/>
                <t:saveState value="#{jobsSelectionListBean.searchMode}"/>
                    <t:saveState value="#{pageBeanName.bckBtnNavigationCase}"/>
                <t:saveState value="#{pageBeanName.saveStateList}"/>
                <t:saveState value="#{pageBeanName.backActionMethodName}"/>
                
                        <t:saveState value="#{fatwaSelectionListBean.civilId}"/>
                  <t:saveState value="#{fatwaSelectionListBean.civilName}"/>
                  <t:saveState value="#{fatwaSelectionListBean.civilExist}"/>
                   <t:saveState value="#{fatwaSelectionListBean.validCivilId}"/>
                   <t:saveState value="#{fatwaSelectionListBean.selectedCategory}"/>
                <t:saveState value="#{fatwaSelectionListBean.categoryList}"/>
                <t:saveState value="#{fatwaSelectionListBean.ministryList}"/>
                <t:saveState value="#{fatwaSelectionListBean.selectedMinistry}"/>
                <t:saveState value="#{fatwaSelectionListBean.searchMode}"/>
                <t:saveState value="#{fatwaSelectionListBean.selectedDTOS}"/>
                <t:saveState value="#{fatwaSelectionListBean.selectedPageDTO}"/>
                   <t:saveState value="#{fatwaSelectionListBean.searchDTO1}"/>
                <t:saveState value="#{fatwaSelectionListBean.currentPageIndex}"/>
                <t:saveState value="#{fatwaSelectionListBean.oldPageIndex}"/>
                
                <t:saveState value="#{selectionListBean.civilId}"/>
                  <t:saveState value="#{selectionListBean.civilName}"/>
                  <t:saveState value="#{selectionListBean.civilExist}"/>
                   <t:saveState value="#{selectionListBean.validCivilId}"/>
                   <t:saveState value="#{selectionListBean.selectedCategory}"/>
                <t:saveState value="#{selectionListBean.categoryList}"/>
                <t:saveState value="#{selectionListBean.ministryList}"/>
                <t:saveState value="#{selectionListBean.selectedMinistry}"/>
                <t:saveState value="#{selectionListBean.searchMode}"/>
                <t:saveState value="#{selectionListBean.selectedDTOS}"/>
                <t:saveState value="#{selectionListBean.selectedPageDTO}"/>
                   <t:saveState value="#{selectionListBean.searchDTO}"/>
                <t:saveState value="#{selectionListBean.currentPageIndex}"/>
                <t:saveState value="#{selectionListBean.oldPageIndex}"/>
                
                   <t:saveState value="#{selectionListBean.hideHasExprince}"/>
                   <t:saveState value="#{jobsSelectionListBean.hideHasExprince}"/>
                    <t:saveState value="#{fatwaSelectionListBean.hideHasExprince}"/>
                    
            </tiles:insert>
        </t:aliasBean>
         <t:panelGroup forceId="true" id="scriptGeneratorPanelInfIntegration">
        <c:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
</f:view>
