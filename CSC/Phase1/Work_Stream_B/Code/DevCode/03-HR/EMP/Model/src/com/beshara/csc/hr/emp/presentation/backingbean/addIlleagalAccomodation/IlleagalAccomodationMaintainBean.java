package com.beshara.csc.hr.emp.presentation.backingbean.addIlleagalAccomodation;


import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class IlleagalAccomodationMaintainBean extends ManyToManyMaintainBaseBean {
    protected static final Integer WIZARD_BAR_PAGE = 1;    
    
//    private int nonApprovedCount;
//    private int totalEmpCount;
//    private boolean approveSuccess= false;
    
    public static final String BEAN_NAME = "illeagalAccomodationMaintainBean";
        public static IlleagalAccomodationMaintainBean getInstance() {
        return (IlleagalAccomodationMaintainBean)JSFHelper.getValue(BEAN_NAME);
    }
        
    public static String getMaintainBean() {
        return BEAN_NAME;
    }
    
    public IlleagalAccomodationMaintainBean() {
//        setClient(VacClientFactory.getVacEmployeeVacationsClient());
        setCurrentStep("illeagalAccomodationPersonalInfo");//Key
        setNextNavigationCase("illeagalAccomodationQualCase");
        //setFinishNavigationCase("illeagalAccomodationPersonalInfoCase");
        setRenderSave(true);
        setPageDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
        initializePageDTO();
       
    }
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowsteps(true);
        app.setShowbar(false);
        app.setShowNavigation(true);
        return app;
    }
    public void initializePageDTO() {
//        setPageDTO(VacDTOFactory.createVacGroupVacationDTO());
//        IVacGroupVacationDTO vacEmployeeVacationsDTO = (IVacGroupVacationDTO)getPageDTO();
//        vacEmployeeVacationsDTO.setVacEmployeesSearchDTO(new VacEmployeeSearchDTO());
//        vacEmployeeVacationsDTO.getVacEmployeesSearchDTO().setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        System.out.println("initializePageDTO");
    }
    
    public String next() {
        String temp = super.next();
        setRenderSave(true);
//        MainDataCandidate mainDataCandidate =
//            (MainDataCandidate)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{mainDataCandidateBean}").getValue(FacesContext.getCurrentInstance());
//        mainDataCandidate.fillPageDTO();
        return temp;
    }


    public void navigate(javax.faces.event.ActionEvent actionEvent) {

        String stepKey = actionEvent.getComponent().getId();
        this.updateStepDependancies(getCurrentStep());
        String nCase = null;
        if (validateStep(getCurrentStep(), stepKey) && 
            checkStepRelevants(stepKey)) {
            //added for finish button status
            setValidated(getCurrentStep());
            setVisited(getCurrentStep());
            setVisited(stepKey);
            //set the finish button status
            setCurrentStep(stepKey);
            getWizardBar().setCurrentStep(stepKey);
            if (getFinishButtonOverride(getCurrentStep()) == 1) {
                setRenderFinish(getFinishButtonStatus(stepKey));
            } else if (getFinishButtonOverride(getCurrentStep()) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }

            //set the save button status


            //
            nCase = getNavigationCase(stepKey);
            setNextNavigationCase(getNextNavigationCase(stepKey));
            setPreviousNavigationCase(getPreviousNavigationCase(stepKey));
            System.out.println(stepKey);
        }
        if (nCase != null)
            handleNavigation(nCase);


    }
    
    public String doSave() {
        IlleagalAccomodationPersonalInfo illeagalAccomodationPersonalInfo = getPersonalInfoBean();
//        illeagalAccomodationPersonalInfo.setPageMode(this.getMaintainMode());
//        setPageMode(1);//For test; Edit
        illeagalAccomodationPersonalInfo.saveOrUpdatePageDTO();
        if(getSharedUtil().getErrMsgValue()!=null){
             return null;
        }
        return back();
    }
    
    private IlleagalAccomodationPersonalInfo getPersonalInfoBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
                Application app = ctx.getApplication();
                return (IlleagalAccomodationPersonalInfo)app.createValueBinding("#{illeagalAccomodationPersonalInfo}").getValue(ctx);
    }
    
    
    public void reInitializePageDTO() {
        setCurrentStep("illeagalAccomodationPersonalInfoCase");
        setNextNavigationCase("illeagalAccomodationPersonalInfoCase");
//        setFinishNavigationCase("illeagalAccomodationPersonalInfoCase");
        initializePageDTO();            
//        getMainDataBean().setJsVacationPeriod(null);
//        getMainDataBean().setFactorIdValue(null);
        
    }

    @Override
    public String back() {
//        QulIntegrationListBean qulIntegrationListBean =
//                 (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
//         qulIntegrationListBean.setPersonsInformationDTOList(new ArrayList());
        return "ilLegalCitizensCase";
    }
}

