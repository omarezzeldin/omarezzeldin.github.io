package com.beshara.csc.hr.emp.presentation.backingbean.employee.addcandidate;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateStatusDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStagesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IProcedureResultsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IProcedureResultsEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.EmployeeListBean;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.listqul.QulIntegrationListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;


public class AddCandidateMaintainBean extends ManyToManyMaintainBaseBean {

    @SuppressWarnings("compatibility:9127951593953358065")
    private static final long serialVersionUID = 8741521628105837425L;

    private boolean empHireTypeNominationAgainFromOtherMinistries = false;
    private boolean invalidMinFileNo = false;
    private boolean mainDataOnlyFlag = true;
    private List<IBasicDTO> hireTypesList = new ArrayList<IBasicDTO>();

    private static final Long MINISTRY_CODE = CSCSecurityInfoHelper.getLoggedInMinistryCode();
    public static final String BEAN_NAME = "addCandidateMaintainBean";
    public static final String PROC_N_CASE = "procedurescandidatePage";
    protected static final Integer WIZARD_BAR_PAGE = 1;
    private String BackHireType = "";

    public AddCandidateMaintainBean() {
        setCurrentStep("mainDataPage");
        setNextNavigationCase("qualificationsCandidatePage");
        setFinishNavigationCase("emplist");
        setClient(EmpClientFactory.getEmpCandidatesClient());
        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setContent1Div("module_tabs_cont");
        //        fillHireTypesList();
    }

    public static AddCandidateMaintainBean getInstance() {
        return (AddCandidateMaintainBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowsteps(true);
        app.setShowbar(false);
        app.setShowCustomDiv2(true);
        return app;
    }

    public String previous() {

        String nCase = getPreviousNavigationCase();

        ProceduresCandidate procBean = ProceduresCandidate.getInstance();
        if (nCase != null && nCase.equals(PROC_N_CASE)) {
            procBean.fillProcedureList();
        }

        this.updateStepDependancies(getCurrentStep());
        nCase = getNavigationCase(this.getAvailablePreviousStep(getCurrentStep()));

        String targetStep = getPreviousStep(getCurrentStep());
        if (targetStep != null)
            if (validateStep(getCurrentStep(), targetStep) && checkStepRelevants(targetStep)) {

                setNextNavigationCase(getNavigationCase(getCurrentStep()));
                setPreviousNavigationCase(getPreviousNavigationCase(getPreviousStep(getCurrentStep())));

                setVisited(getPreviousStep(getCurrentStep()));
                setValidated(getCurrentStep());
                setVisited(getCurrentStep());
                if (getFinishButtonOverride(getPreviousStep(getCurrentStep())) == 1) {
                    setRenderFinish(getFinishButtonStatus(getPreviousStep(getCurrentStep())));
                } else if (getFinishButtonOverride(getPreviousStep(getCurrentStep())) == 2) {
                    setRenderFinish(false);
                } else {
                    setRenderFinish(true);
                }
                setCurrentStep(getPreviousStep(getCurrentStep()));
                getWizardBar().setCurrentStep(getCurrentStep());
                return nCase;
            }
        return null;
    }


    public String next() {
        ProceduresCandidate procBean = ProceduresCandidate.getInstance();
        String temp = super.next();
        if (temp != null && temp.equals(PROC_N_CASE)) {
            procBean.fillProcedureList();
        }
        setRenderSave(true);

        MainDataCandidate mainDataCandidate = MainDataCandidate.getInstance();

        mainDataCandidate.fillPageDTO();
        return temp;
    }

    public void navigate(javax.faces.event.ActionEvent actionEvent) {

        String stepKey = actionEvent.getComponent().getId();
        this.updateStepDependancies(getCurrentStep());
        String nCase = null;
        if (validateStep(getCurrentStep(), stepKey) && checkStepRelevants(stepKey)) {
            setValidated(getCurrentStep());
            setVisited(getCurrentStep());
            setVisited(stepKey);
            setCurrentStep(stepKey);
            getWizardBar().setCurrentStep(stepKey);
            if (getFinishButtonOverride(getCurrentStep()) == 1) {
                setRenderFinish(getFinishButtonStatus(stepKey));
            } else if (getFinishButtonOverride(getCurrentStep()) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }
            nCase = getNavigationCase(stepKey);
            setNextNavigationCase(getNextNavigationCase(stepKey));
            setPreviousNavigationCase(getPreviousNavigationCase(stepKey));
        }
        if (nCase != null)
            handleNavigation(nCase);
    }

    public void handleNavigation(String nCase) {
        ProceduresCandidate procBean = ProceduresCandidate.getInstance();
        if (nCase != null && nCase.equals(PROC_N_CASE)) {
            procBean.fillProcedureList();
        }
        NavigationHandler nHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        nHandler.handleNavigation(FacesContext.getCurrentInstance(), null, nCase);

    }

    public String goBack() {
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());

        integrationBean.getHmBaseBeanFrom().clear();
        EmployeeListBean empListBean = (EmployeeListBean)evaluateValueBinding("empListBean");
        Long hirtypeCode = Long.valueOf(getBackHireType());
        IHireTypesEntityKey hireTypeEK = EmpEntityKeyFactory.createHireTypesEntityKey(hirtypeCode);

        IHireTypesDTO hireTypesDTO = EmpDTOFactory.createHireTypesDTO();
        hireTypesDTO.setCode(hireTypeEK);
        ((IEmpCandidatesDTO)empListBean.getPageDTO()).setHireTypesDTO(hireTypesDTO);
        return super.back();
    }

    public void fillHireTypesList() {
        if (hireTypesList == null || hireTypesList.size() == 0) {
            try {
                hireTypesList = (List)EmpClientFactory.getHireTypesClient().getEmpHireTypesAdd(MINISTRY_CODE);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
    }

    public void setEmpHireTypeNominationAgainFromOtherMinistries(boolean empHireTypeNominationAgainFromOtherMinistries) {
        this.empHireTypeNominationAgainFromOtherMinistries = empHireTypeNominationAgainFromOtherMinistries;
    }

    public boolean isEmpHireTypeNominationAgainFromOtherMinistries() {
        return empHireTypeNominationAgainFromOtherMinistries;
    }

    public void setInvalidMinFileNo(boolean invalidMinFileNo) {
        this.invalidMinFileNo = invalidMinFileNo;
    }

    public boolean isInvalidMinFileNo() {
        return invalidMinFileNo;
    }

    public void setMainDataOnlyFlag(boolean mainDataOnlyFlag) {
        this.mainDataOnlyFlag = mainDataOnlyFlag;
    }

    public boolean isMainDataOnlyFlag() {
        return mainDataOnlyFlag;
    }

    public void setHireTypesList(List<IBasicDTO> hireTypesList) {
        this.hireTypesList = hireTypesList;
    }

    public List<IBasicDTO> getHireTypesList() {
        return hireTypesList;
    }

    private void fillpageDto() {
        //DocumentsCandidate
        MainDataCandidate mainDataCandidate =
            (MainDataCandidate)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{mainDataCandidateBean}").getValue(FacesContext.getCurrentInstance());
        mainDataCandidate.fillPageDTO();
        DocumentsCandidate documentsCandidateBean =
            (DocumentsCandidate)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{documentsCandidateBean}").getValue(FacesContext.getCurrentInstance());
        if (documentsCandidateBean.getCurrentDetails() != null &&
            documentsCandidateBean.getCurrentDetails().size() > 0) {
            ((IEmpCandidatesDTO)this.getPageDTO()).setEmpCandidateDocumentsList(documentsCandidateBean.getCurrentDetails());
        }
        //QualCandidate
        QulIntegrationListBean qulIntegrationListBean =
            (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
        if (qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList() != null &&
                  qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList().size() > 0) {
                      ((IEmpCandidatesDTO)this.getPageDTO()).setPersonsInformationDTOList(new ArrayList());
                      for (IPersonsInformationDTO dto : qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList()) {
                          if( dto.getPersonQualificationsDTO() != null){
                              if(   dto.getPersonQualificationsDTO().isCurrentQualStatus()){
                              dto.getPersonQualificationsDTO().setCurrentQual(1L);
                              }else{
                                      dto.getPersonQualificationsDTO().setCurrentQual(0L);   
                                  }
                          }
                          ((IEmpCandidatesDTO)this.getPageDTO()).getPersonsInformationDTOList().add(dto);
                      } 
                  }
              if (qulIntegrationListBean.getKwtCitizenDTO().getDeletedPersonsInformationDTOList() != null &&
                  qulIntegrationListBean.getKwtCitizenDTO().getDeletedPersonsInformationDTOList().size() > 0) {
                      ((IEmpCandidatesDTO)this.getPageDTO()).setDeletedPersonsInformationDTOList((qulIntegrationListBean.getKwtCitizenDTO().getDeletedPersonsInformationDTOList()));
                  }
        //ProceduresCandidate
        ProceduresCandidate proceduresCandidateBean =
            (ProceduresCandidate)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{proceduresCandidateBean}").getValue(FacesContext.getCurrentInstance());
        if (proceduresCandidateBean.getCurrentDetails() != null &&
            proceduresCandidateBean.getCurrentDetails().size() > 0) {
            ((IEmpCandidatesDTO)this.getPageDTO()).setEmpCandidateProceduresList(proceduresCandidateBean.getCurrentDetails());
            IProcedureResultsDTO procedureResultsDTO = EmpDTOFactory.createProcedureResultsDTO();
            if (proceduresCandidateBean.getEmpProceduresDTO().getCrmStatusCode() != null) {
                if (proceduresCandidateBean.getEmpProceduresDTO().isCrmChecked()) {
                    IProcedureResultsEntityKey ek = EmpEntityKeyFactory.createProcedureResultsEntityKey(1L);
                    procedureResultsDTO.setCode(ek);
                    proceduresCandidateBean.getEmpProceduresDTO().setProcedureResultsDTO(procedureResultsDTO);
                } else {
                    IProcedureResultsEntityKey ek = EmpEntityKeyFactory.createProcedureResultsEntityKey(2L);
                    procedureResultsDTO.setCode(ek);
                    proceduresCandidateBean.getEmpProceduresDTO().setProcedureResultsDTO(procedureResultsDTO);
                }
                proceduresCandidateBean.getEmpProceduresDTO().setSendDate(proceduresCandidateBean.getEmpProceduresDTO().getCrmLetterDate());
                ((IEmpCandidatesDTO)this.getPageDTO()).getEmpCandidateProceduresList().add(proceduresCandidateBean.getEmpProceduresDTO());
            }
        }
        //EperiencesCandidate
        ExperiencesCandidate experiencesCandidate =
            (ExperiencesCandidate)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{experiencesCandidate}").getValue(FacesContext.getCurrentInstance());
        if (experiencesCandidate.getWorkDataListBean().getKwtWorkDataDTOList() != null) {
            ((IEmpCandidatesDTO)this.getPageDTO()).setKwtWorkDataTreeDTOList(new ArrayList());
            for (IKwtWorkDataTreeDTO dto : experiencesCandidate.getWorkDataListBean().getKwtWorkDataDTOList()) {
                if (dto.getStatusFlag() != null && dto.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                    ((IEmpCandidatesDTO)this.getPageDTO()).getKwtWorkDataTreeDTOList().add(dto);
                }
            }
        }
        //hireStagesDTO
        IEntityKey hirStageCode = EmpEntityKeyFactory.createHireStagesEntityKey(1L);
        IHireStagesDTO hireStagesDTO = EmpDTOFactory.createHireStagesDTO();
        hireStagesDTO.setCode(hirStageCode);
        ((IEmpCandidatesDTO)this.getPageDTO()).setHireStagesDTO(hireStagesDTO);
        //ActiveFlag
        ((IEmpCandidatesDTO)this.getPageDTO()).setActiveFlag(1L);
        //empCandidateStatus
        IEmpCandidateStatusDTO empCandidateStatus = EmpDTOFactory.createEmpCandidateStatusDTO();
        empCandidateStatus.setCode(EmpEntityKeyFactory.createEmpCandidateStatusEntityKey(2L));
        ((IEmpCandidatesDTO)this.getPageDTO()).setEmpCandidateStatusDTO(empCandidateStatus);

    }

    public void add() throws DataBaseException {
        try {
            fillpageDto();
            setPageDTO(EmpClientFactory.getEmpCandidatesClient().addCandidateInfo(getPageDTO()));
            if (isUsingPaging()) {
                getPagingBean().clearDTOS();
                generatePagingRequestDTO((String)getPageDTO().getCode().getKey());
            } else {
                getAll();
                getPageIndex((String)getPageDTO().getCode().getKey());
            }
            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().add(getPageDTO());
            }
            this.setSearchQuery("");
            this.setSearchType(0);
            this.setSearchMode(false);

            getSharedUtil().setSuccessMsgValue("SuccessAdds");
            IntegrationBean integrationBean =
                (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());

            integrationBean.getHmBaseBeanFrom().clear();
        } catch (SharedApplicationException e) {
            e.printStackTrace();

        }
        setSelectedRadio("");
    }

    private boolean checkQul() {
        QulIntegrationListBean qulIntegrationListBean =
            (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
        List<IPersonsInformationDTO> list = null;
        if (qulIntegrationListBean.getKwtCitizenDTO() != null) {
            list = qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList();
        }
        if (list == null || list.size() < 1) {
            return true;
        } else {
            boolean qualNotExist = true;
            for (IPersonsInformationDTO dto : list) {
                if (dto.getPersonQualificationsDTO() != null) {
                    qualNotExist = false;
                    break;
                }
            }
            return qualNotExist;
        }

    }

 

    public String finish() throws DataBaseException, SharedApplicationException {
        MainDataCandidate mainDataCandidate =
            (MainDataCandidate)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{mainDataCandidateBean}").getValue(FacesContext.getCurrentInstance());
      String wrkCenter=((IWorkCentersEntityKey)mainDataCandidate.getWorkCentersDTO().getCode()).getWrkCode();  
      if(EmpClientFactory.getEmpCandidatesClient().chkWrkCenterStatus(MINISTRY_CODE, wrkCenter)){
              String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "emp_not_hired_for_freezed_wrkCenter");
              getSharedUtil().setErrMsgValue(message);
              return null;
          }
        QulIntegrationListBean qulIntegrationListBean =
            (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
        if(!((IEmpCandidatesDTO)getPageDTO()).isWitoutQualFlag()){
        if(qulIntegrationListBean.getKwtCitizenDTO() == null){
                 qulIntegrationListBean.loadKwtCitizenDTO();
                 }
      
                 if (qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList() != null &&
                     qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList().size() > 0) {
                 Long currntQualCount=0L;
                         for (IPersonsInformationDTO dto : qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList()) {
                             
                             if(dto.getPersonQualificationsDTO() == null){
                                   continue;
                                 }
                             
                                 if( dto.getPersonQualificationsDTO().isCurrentQualStatus()){
                                     currntQualCount += 1;
                                    
                                 }
                         } 
                         if(currntQualCount == 0 ){
                             String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "select_one_qual");
                             getSharedUtil().setErrMsgValue(message);
                             return null;
                         }else if(currntQualCount > 1 ){
                                 String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "select_one_qual_only");
                                 getSharedUtil().setErrMsgValue(message);
                                 return null;
                             }
                 }else{
                         String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "enforce_message_addQulCand");
                         getSharedUtil().setErrMsgValue(message);
                         return null;
                     }
    }
        if (!((IEmpCandidatesDTO)getPageDTO()).isWitoutQualFlag() && checkQul()) {
            String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "enforce_message_addQulCand");
            getSharedUtil().setErrMsgValue(message);
            return null;
        } 
       else {

            Long realCivilId = Long.valueOf(mainDataCandidate.getCivilId());
            boolean isEmpIndebt = EmpClientFactory.getEmployeesClient().isEmpIndebt(realCivilId);
            if (isEmpIndebt) {
                setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv2);");
            } else {
                return doFinishSteps();
            }
        }
        return "";
    }

    public String doFinishSteps() throws DataBaseException {
        MainDataCandidate mainDataCandidate =
            (MainDataCandidate)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{mainDataCandidateBean}").getValue(FacesContext.getCurrentInstance());
        String finishNavigationCase = finishLocal();
        EmployeeListBean employeeListBean = (EmployeeListBean)evaluateValueBinding("empListBean");
        employeeListBean.getHighlightedDTOS().add(getPageDTO());
        if (employeeListBean != null) {
            employeeListBean.setSelectedHireType(mainDataCandidate.getHireTypeFirstParent());
            employeeListBean.searchByMainHireType();
        }
        if (isUsingPaging()) {
            if (employeeListBean != null) {
                if (getPageDTO().getCode() != null) {
                    employeeListBean.getPageIndex(getPageDTO().getCode().getKey());
                    employeeListBean.getHighlightedDTOS().add(getPageDTO());
                    highlighDataTable("empListBean");
                    employeeListBean.setRepositionTable(true);
                    employeeListBean.setSortedTable(false);
                    employeeListBean.generatePagingRequestDTO((String)getPageDTO().getCode().getKey());
                }
            }
        } else {
            if (this.getMaintainMode() == 1) {
                employeeListBean.getPageIndex((String)getPageDTO().getCode().getKey());
            } else {
                highlightListDTO("empListBean");
            }
        }
        return finishNavigationCase;
    }

    public String finishLocal() {
        try {
            if (getFinishButtonOverride(getCurrentStep()) == 1) {
                setRenderFinish(getFinishButtonStatus(getCurrentStep()));
            } else if (getFinishButtonOverride(getCurrentStep()) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }
            if (validateStep(getCurrentStep(), null)) {
                this.add();
                updateStepData(getCurrentStep());
                //TODO locking code
                unlock();
                return getFinishNavigationCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /* public String getCurrentStepJSValidation() {

        String clientSideJavaScript = null;
        WizardStep currentStep = (WizardStep)(getWizardBar().getWizardSteps()).get(getCurrentStep());

        if (currentStep != null)
            clientSideJavaScript = currentStep.getJsValidation();

        if (clientSideJavaScript == null)
            clientSideJavaScript = "return validationEmpTabs();";
        return clientSideJavaScript;
    }  */

    public String getMaintainBean() {
        return BEAN_NAME;
    }

    public void setBackHireType(String BackHireType) {
        this.BackHireType = BackHireType;
    }

    public String getBackHireType() {
        return BackHireType;
    }

}
