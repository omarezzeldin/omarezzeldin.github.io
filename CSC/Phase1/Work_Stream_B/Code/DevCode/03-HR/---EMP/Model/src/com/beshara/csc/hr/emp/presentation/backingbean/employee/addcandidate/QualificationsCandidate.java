package com.beshara.csc.hr.emp.presentation.backingbean.employee.addcandidate;


import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.EmployeeListBean;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.listqul.QulIntegrationListBean;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import javax.faces.context.FacesContext;

public class QualificationsCandidate extends ManyToManyDetailsMaintain {
    private QulIntegrationListBean qulListIntegrationBean = new QulIntegrationListBean();

    public QualificationsCandidate() {
        super();
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowContent1(true);
        app.setShowbar(false);
        app.setShowCustomDiv2(true);
        return app;
    }

    public void setQulListIntegrationBean(QulIntegrationListBean qulListIntegrationBean) {
        this.qulListIntegrationBean = qulListIntegrationBean;
    }

    public QulIntegrationListBean getQulListIntegrationBean() {
        return qulListIntegrationBean;
    }

    public String viewCandidateQuls() {
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        integrationBean.getHmBaseBeanFrom().clear();
        integrationBean.getHmBaseBeanFrom().put("empListBean", EmployeeListBean.getInstance());
        integrationBean.setNavgationCaseFrom("emplist");
        Long civilId =
            ((IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)getSelectedDTOS().get(0)).getCitizensResidentsDTO()).getCivilId();
        qulListIntegrationBean.setCivilId(civilId);
        integrationBean.getHmBaseBeanFrom().put("qulIntegrationBean", qulListIntegrationBean);
        integrationBean.getHmBaseBeanFrom().put("backPage", "filterPage");
        //        getQulIntegrationBean().setKwtCitizensResidentsDTO((IKwtCitizensResidentsDTO)qulIntegrationBean.getKwtCitizenInfo(civilId));
        //            getQulIntegrationBean().setPrepareMethodName(BEAN_NAME + ".prePareMethodName");
        //            getQulIntegrationBean().setReturnMethodName(BEAN_NAME + ".returnMethodName");

        return "viewIntegrationPeronalQuls";
    }
}
