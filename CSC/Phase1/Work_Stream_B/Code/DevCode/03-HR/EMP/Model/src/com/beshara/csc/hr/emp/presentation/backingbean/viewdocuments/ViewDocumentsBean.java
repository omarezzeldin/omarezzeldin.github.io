package com.beshara.csc.hr.emp.presentation.backingbean.viewdocuments;


import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.adddocAttachments.AddDocAttachmentsIntegrationBean;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class ViewDocumentsBean extends LookUpBaseBean {
    private List saveStateList = new ArrayList();
    private String backActionMethodName;
    private String bckBtnNavigationCase;

    public ViewDocumentsBean() {
        //        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowContent1(true);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowSearch(false);
        app.setShowbar(true);
        return app;
    }

    public List getTotalList() {
        return ((IEmpCandidatesDTO)getPageDTO()).getEmpCandidateDocumentsList();
    }

    public static ViewDocumentsBean getInstance() {
        return (ViewDocumentsBean)JSFHelper.getValue("viewDocumentsBean");
    }

    public String back() {
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        return integrationBean.goFrom();
    }

    public String addIntegrationDocument() {
        getIntegrationBean().reInitializeBean();
        getIntegrationBean().setNavgationCaseFrom("viewDocumentsPage");
        getIntegrationBean().setBeanNameFrom("viewDocumentsBean");
        getIntegrationBean().setActionTo("goAdd");

        IEmpCandidateDocumentsDTO empCandidateDocumentsDTO = (IEmpCandidateDocumentsDTO)getMyDataTable().getRowData();
        ViewDocumentsBean viewDocumentsBean = (ViewDocumentsBean)evaluateValueBinding("viewDocumentsBean");
        getIntegrationBean().getHmBaseBeanFrom().put("viewDocumentsBean", ViewDocumentsBean.getInstance());
//        getIntegrationBean().getHmBaseBeanFrom().put("empListBean", EmployeeListBean.getInstance());
        Long civilId =
            Long.valueOf(((IEmpCandidatesDTO)viewDocumentsBean.getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
        Long docTypeCode = Long.valueOf(empCandidateDocumentsDTO.getDocumentTypesDTO().getCode().getKey());

        String fullName = ((IEmpCandidatesDTO)viewDocumentsBean.getPageDTO()).getCitizensResidentsDTO().getName();
        String docTypeName = empCandidateDocumentsDTO.getDocumentTypesDTO().getName();

        getAddDocAttachmentsIntegrationBean().setCivilId(civilId);
        getAddDocAttachmentsIntegrationBean().setEmpName(fullName);
        getAddDocAttachmentsIntegrationBean().setSelectedDocType(docTypeCode.toString());
        getAddDocAttachmentsIntegrationBean().setDoctypeName(docTypeName);
        getAddDocAttachmentsIntegrationBean().setDisableSelectedDocType(true);
        getAddDocAttachmentsIntegrationBean().setModuleName("emp");
        getAddDocAttachmentsIntegrationBean().addDocAttachment();
        getAddDocAttachmentsIntegrationBean().setForShowAttachmentOnly(true);

        return "docAttchmentIntgrationPage";

    }

    private AddDocAttachmentsIntegrationBean getAddDocAttachmentsIntegrationBean() {
        return (AddDocAttachmentsIntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{addDocAttachmentsIntegrationBean}").getValue(FacesContext.getCurrentInstance());

    }

    public String backAction() {
        if (!getBackActionMethodName().equals(""))
            executeMethodBinding(getBackActionMethodName(), null);
        return getBckBtnNavigationCase();
    }

    public void setSaveStateList(List saveStateList) {
        this.saveStateList = saveStateList;
    }

    public List getSaveStateList() {
        return saveStateList;
    }

    public void setBackActionMethodName(String backActionMethodName) {
        this.backActionMethodName = backActionMethodName;
    }

    public String getBackActionMethodName() {
        return backActionMethodName;
    }

    public void setBckBtnNavigationCase(String bckBtnNavigationCase) {
        this.bckBtnNavigationCase = bckBtnNavigationCase;
    }

    public String getBckBtnNavigationCase() {
        return bckBtnNavigationCase;
    }
}
