package com.beshara.csc.hr.emp.presentation.backingbean.addIlleagalAccomodation;


import com.beshara.base.client.ServiceNotAvailableException;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.EmployeesClientImpl;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IVacEmployeeSearchDTO;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.EmployeeListBean;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.addcandidate.QualificationsCandidate;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.vac.business.client.VacClientFactory;
import com.beshara.csc.hr.vac.business.dto.IVacGroupVacationDTO;
import com.beshara.csc.hr.vac.business.dto.IVacSickRequestsSearchDTO;
import com.beshara.csc.hr.vac.business.dto.VacDTOFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobSearchCriteriaDTO;
import com.beshara.csc.nl.job.business.dto.JobSearchCriteriaDTO;
import com.beshara.csc.nl.org.business.client.IWorkCentersClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersSearchCriteriaDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IClassificationsEntityKey;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.IWrkLevelsEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.org.integration.presentation.backingbean.workcenters.WorkCentersHelperBean;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.listqul.QulIntegrationListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import weblogic.ejb20.utils.OrderedSet;


public class IlleagalAccomodationQualifications extends ManyToManyDetailsMaintain {

    private QulIntegrationListBean qulListIntegrationBean = new QulIntegrationListBean();
    public IlleagalAccomodationQualifications() {
   
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowContent1(true);
        app.setShowbar(true);
        return app;
    }

    public void setQulListIntegrationBean(QulIntegrationListBean qulListIntegrationBean) {
        this.qulListIntegrationBean = qulListIntegrationBean;
    }

    public QulIntegrationListBean getQulListIntegrationBean() {
        return qulListIntegrationBean;
    }

//    public String viewCandidateQuls() {
//        IntegrationBean integrationBean =
//            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
//        integrationBean.getHmBaseBeanFrom().clear();
//        integrationBean.getHmBaseBeanFrom().put("empListBean", EmployeeListBean.getInstance());
//        integrationBean.setNavgationCaseFrom("emplist");
//        Long civilId =
//            ((IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)getSelectedDTOS().get(0)).getCitizensResidentsDTO()).getCivilId();
//        qulListIntegrationBean.setCivilId(civilId);
//        integrationBean.getHmBaseBeanFrom().put("qulIntegrationBean", qulListIntegrationBean);
//        integrationBean.getHmBaseBeanFrom().put("backPage", "filterPage");
//        //        getQulIntegrationBean().setKwtCitizensResidentsDTO((IKwtCitizensResidentsDTO)qulIntegrationBean.getKwtCitizenInfo(civilId));
//        //            getQulIntegrationBean().setPrepareMethodName(BEAN_NAME + ".prePareMethodName");
//        //            getQulIntegrationBean().setReturnMethodName(BEAN_NAME + ".returnMethodName");
//
//        return "viewIntegrationPeronalQuls";
//    }
}
    
        
        

 

