package com.beshara.csc.hr.emp.presentation.backingbean.employee;


import com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata.WorkDataListBean;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class ExperiencesBean extends ManyToManyDetailsMaintain {
    public static final Long TRXTYPE_INTERNAL_DELGATION_80 = Long.valueOf(80);
    public static final Long TRXTYPE_EXTERNAL_DELGATION_90 = Long.valueOf(90);
    WorkDataListBean workDataListBean = new WorkDataListBean();
    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";

    public ExperiencesBean() {
        setContent1Div("module_tabs_cont1");
    }

    public String goAdd() {
        preAdd();
        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
        if (!maintainBean.isEnableEditExper()) {
            //maintainBean.setBundleMsg(getSharedUtil().messageLocator(BUNDLE_NAME,"unable_add_ExperiencesOrQual"));
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "unable_add_Experiences"));
            return null;

        }
        //        if (maintainBean.getBundleMsg() != null && !maintainBean.getBundleMsg().isEmpty()) {
        //            getSharedUtil().setErrMsgValue(maintainBean.getBundleMsg());
        //            return null;
        //        } else {
        return workDataListBean.goAdd();
        //        }

    }
 

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowWizardBar(true);
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowbar(true);
        return app;
    }

    public void setWorkDataListBean(WorkDataListBean workDataListBean) {
        this.workDataListBean = workDataListBean;
    }

    public WorkDataListBean getWorkDataListBean() {
        return workDataListBean;
    }

    public void initiateBeanOnce() {
        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
        Long civilId =
            Long.valueOf(((IEmpCandidatesDTO)maintainBean.getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
        List<Long> trxCodList = new ArrayList<Long>();
        trxCodList.add(TRXTYPE_INTERNAL_DELGATION_80);
        trxCodList.add(TRXTYPE_EXTERNAL_DELGATION_90);
//        getIntegrationBean().reInitializeBean();
        Integer WIZARD_BAR_PAGE = 1;

        workDataListBean.setPageType(WIZARD_BAR_PAGE);
        workDataListBean.setAddExperIntegrationpage("empaddexperiencespage");
        workDataListBean.setListPageinWizardBar("empexperiencespage");
        workDataListBean.setSaveInDB(false);
        workDataListBean.setEnableAdd(true);
        workDataListBean.setEnableBack(false);
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        integrationBean.getHmBaseBeanFrom().put("trxCodList", trxCodList);
        integrationBean.getHmBaseBeanFrom().put("includeTrx", false);
        integrationBean.getHmBaseBeanFrom().put("trxTypesCode", 10L);

        //  integrationBean.getHmBaseBeanFrom().put("empMaintainBean", MaintainBean.getInstance());
        integrationBean.getHmBaseBeanFrom().put("empMaintainBean", maintainBean);
        integrationBean.getHmBaseBeanFrom().put("empExperiencesBean", this);
        integrationBean.setNavgationCaseFrom("empexperiencespage");
        integrationBean.setBeanNameFrom("empExperiencesBean");
        workDataListBean.setCivilId(civilId);
        workDataListBean.setFromTabs(true);

        //        workDataListBean.setPanelGroupStyleClass("manytomany_treeDivPanel");
        workDataListBean.setPanelGroupStyleClass("tree-area-tabs-With-2-row-filter");

        integrationBean.getHmBaseBeanFrom().put("workDataListBean", workDataListBean);
    }
    
    public String retFromIntigration() {
        return "empexperiencespage";
    }
    
    public String back(){
            return "empexperiencespage";
        }
}
