package com.beshara.csc.hr.emp.presentation.backingbean.employee.addcandidate;


import com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata.WorkDataListBean;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class ExperiencesCandidate extends ManyToManyDetailsMaintain {
    public static final Long TRXTYPE_INTERNAL_DELGATION_80 = Long.valueOf(80);
    public static final Long TRXTYPE_EXTERNAL_DELGATION_90 = Long.valueOf(90);
    private WorkDataListBean workDataListBean = new WorkDataListBean();

    public ExperiencesCandidate() {
        setContent1Div("module_tabs_cont1");
    }

    public ExperiencesCandidate(WorkDataListBean _workDataListBean) {
        setContent1Div("module_tabs_cont1");
        this.workDataListBean = _workDataListBean;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowWizardBar(true);
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowbar(true);
        app.setShowCustomDiv2(true);
        return app;
    }

    public String goAdd() {
        return workDataListBean.goAdd();
    }

    public void setWorkDataListBean(WorkDataListBean workDataListBean) {
        this.workDataListBean = workDataListBean;
    }

    public WorkDataListBean getWorkDataListBean() {
        return workDataListBean;
    }

    public void initiateBeanOnce() {
        AddCandidateMaintainBean addCandidateMaintainBean = AddCandidateMaintainBean.getInstance();
        Long civilId =
            Long.valueOf(((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
        List<Long> trxCodList = new ArrayList<Long>();
        trxCodList.add(TRXTYPE_INTERNAL_DELGATION_80);
        trxCodList.add(TRXTYPE_EXTERNAL_DELGATION_90);
//        getIntegrationBean().reInitializeBean();
        Integer WIZARD_BAR_PAGE = 1;
        workDataListBean.setPageType(WIZARD_BAR_PAGE);
        workDataListBean.setAddExperIntegrationpage("addExperiencePage");
        workDataListBean.setListPageinWizardBar("experiencePage");
        workDataListBean.setSaveInDB(false);
        workDataListBean.setEnableAdd(true);
        workDataListBean.setEnableBack(false);
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        integrationBean.getHmBaseBeanFrom().put("trxCodList", trxCodList);
        integrationBean.getHmBaseBeanFrom().put("includeTrx", false);
        integrationBean.getHmBaseBeanFrom().put("trxTypesCode", 10L);
        integrationBean.getHmBaseBeanFrom().put("addCandidateMaintainBean", AddCandidateMaintainBean.getInstance());
        integrationBean.getHmBaseBeanFrom().put("experiencesCandidate", this);
        integrationBean.setNavgationCaseFrom("experiencePage");
        integrationBean.setBeanNameFrom("experiencesCandidate");
        workDataListBean.setCivilId(civilId);
        if (((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).getCitizensResidentsDTO().getStatusFlag() != null &&
            ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).getCitizensResidentsDTO().getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
            workDataListBean.setKwtCitizenDTO((IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).getCitizensResidentsDTO());
        }
        workDataListBean.setFromTabs(true);
//        workDataListBean.setPanelGroupStyleClass("manytomany_treeDivPanel");
        workDataListBean.setPanelGroupStyleClass("tree-area-tabs-With-2-row-filter");

        integrationBean.getHmBaseBeanFrom().put("workDataListBean", workDataListBean);
    }
    
    
    public String retFromIntigration() {
        return "experiencePage";
    }
    
    public String back(){
            return "experiencePage";
        }
}
