
package com.beshara.csc.hr.emp.presentation.backingbean.employee;


import com.beshara.base.dto.IResultDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateParent;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IProcedureResultsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IProcedureResultsEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.addcandidate.ExperiencesCandidate;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.addcandidate.MainDataCandidate;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.listqul.QulIntegrationListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NotMatchedOnHireTypeException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.sal.exception.InValidSalEmpSalaryElementException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class MaintainBean extends ManyToManyMaintainBaseBean {

    private String virtulValue = String.valueOf(ISystemConstant.VIRTUAL_VALUE.longValue());

    private boolean invalidMinFileNo = false;
    private String savedHireType;
    private List savedDocumentList = new ArrayList();

    private String selectedHireTypeKey;

    //Hr-666 strat
    private boolean mainDataOnlyFlag = true;
    private int maintainMode = 1;
    private String tiles_defValue = "empmaindata.page";
    public static final String PROC_N_CASE = "empprocedures";
    private boolean empHireTypeNominationAgainFromOtherMinistries = false;
    private boolean enableEditQulAndExper = true;
    private boolean enableContractType;
    private boolean enableEditExper = true;
    private String bundleMsg;
    // added for display grs condition lines with status (fail|success)
    private List<IResultDTO> linesResultList = new ArrayList<IResultDTO>();
    private QulIntegrationListBean qulIntegrationListBean =
        (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
    private String backEditHireType = "";
    private static final String select_Hire_Type_Defualt = "-100";

    public MaintainBean() {
        System.out.println("com.beshara.csc.hr.emp.presentation.backingbean.employee.MaintainBean : constructor : start.");
        setCurrentStep("maindata");
        setNextNavigationCase("empQualificationsCandidateEdit");
        setFinishNavigationCase("emplist");
        setDelAlert("divSearch");
        setMainDataOnlyFlag(false);

        setClient(EmpClientFactory.getEmpCandidatesClient());
        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setSelectedPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        System.out.println("com.beshara.csc.hr.emp.presentation.backingbean.employee.MaintainBean : constructor : end.");
    }
    //override by Assmaa Omar hr-666

    public static MaintainBean getInstance() {
        return (MaintainBean)JSFHelper.getValue("empMaintainBean");
    }

    private boolean checkExperienceconflictByHireDate(Date hireDate) {
        boolean confict = false;

        ExperiencesCandidate experiencesCandidate =
            (ExperiencesCandidate)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{experiencesCandidate}").getValue(FacesContext.getCurrentInstance());
        ArrayList<IKwtWorkDataTreeDTO> list = experiencesCandidate.getWorkDataListBean().getKwtWorkDataDTOList();
        if (hireDate != null && list != null && list.size() > 0) {
            for (IKwtWorkDataTreeDTO kwtDto : list) {
                if ((hireDate.after(kwtDto.getFromDate()) || hireDate.equals(kwtDto.getFromDate())) &&
                    (hireDate.before(kwtDto.getUntilDate()) || hireDate.equals(kwtDto.getUntilDate()))) {
                    confict = true;
                    break;
                }
            }
        }
        return confict;
    }

    public String finish() {
        String navigationCase = "";
        // CSC-22302
        MainDataBean empMainDataBean = (MainDataBean)evaluateValueBinding("empMainDataBean");
        if (selectedHireTypeKey != null && selectedHireTypeKey.equals(select_Hire_Type_Defualt)) {
            String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "please_select_Hire_Type");
            getSharedUtil().setErrMsgValue(message);
            return null;

        }else if (selectedHireTypeKey != null && !selectedHireTypeKey.equals(select_Hire_Type_Defualt)) {
            ((IEmpCandidatesDTO)getPageDTO()).setHireTypeKey(selectedHireTypeKey);
        }

        try {
            if (checkExperienceconflictByHireDate(((IEmpCandidatesDTO)getPageDTO()).getHireDate())) {
                String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "renderErrorHireDate");
                getSharedUtil().setErrMsgValue(message);
                return null;
            }
            String wrkCenter=empMainDataBean.getWorkCenterKey();  
            if(EmpClientFactory.getEmpCandidatesClient().chkWrkCenterStatus(CSCSecurityInfoHelper.getLoggedInMinistryCode(), wrkCenter)){
                    String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "emp_not_hired_for_freezed_wrkCenter");
                    getSharedUtil().setErrMsgValue(message);
                    return null;
                }
        
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
                    String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "qul_mandatory");
                    getSharedUtil().setErrMsgValue(message);
                    return null;
                }
            }
            if (getMaintainMode() == 0) {
                getSharedUtil().setSuccessMsgValue(null);
                if ((!isEmpHireTypeNominationAgainFromOtherMinistries()) &&
                    ((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey() != null &&
                    ((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey().equals(getManagedConstantsBean().getEmpHireTypeNominationAgain())) {

                    if (!insureLocked()) {
                        return navigationCase;
                    }
                    edit();

                } else {
                    add();
                }

                if (getSharedUtil().getSuccessMsgValue() != null && !getSharedUtil().getSuccessMsgValue().equals("")) {
                    getSharedUtil().setSuccessMsgValue(null);
                    setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);document.getElementById('backButtonAddDiv').focus();");
                    return null;
                }
             
                empMainDataBean.reSetData(null);
            } else {
                navigationCase = super.finish();
            }

            unlock();

            return navigationCase;
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String previous() {

        String nCase = getPreviousNavigationCase();

        ProceduresBean procBean = ProceduresBean.getInstance();
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
        ProceduresBean procBean = ProceduresBean.getInstance();
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
        ProceduresBean procBean = ProceduresBean.getInstance();
        if (nCase != null && nCase.equals(PROC_N_CASE)) {
            procBean.fillProcedureList();
        }
        NavigationHandler nHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        nHandler.handleNavigation(FacesContext.getCurrentInstance(), null, nCase);

    }

    private void fillPageDTO() {
        DocumentsBean empDocumentsBean = (DocumentsBean)evaluateValueBinding("empDocumentsBean");
        ProceduresBean proceduresBean = (ProceduresBean)evaluateValueBinding("empProceduresBean");
        MainDataBean empMainDataBean = (MainDataBean)evaluateValueBinding("empMainDataBean");
        empMainDataBean.fillPersonQulInfoAndSalDTOS();
        if (((IEmpCandidatesDTO)this.getPageDTO()).getBgtTypeKey() != null &&
            !((IEmpCandidatesDTO)this.getPageDTO()).getBgtTypeKey().equals(getVirtualConstValue())) {
            IBgtTypesEntityKey bgtTypesEK =
                BgtEntityKeyFactory.createBgtTypesEntityKey(Long.valueOf(((IEmpCandidatesDTO)this.getPageDTO()).getBgtTypeKey()));
            IBgtTypesDTO bgtTypesDTO = BgtDTOFactory.createBgtTypesDTO();
            bgtTypesDTO.setCode(bgtTypesEK);
            ((IEmpCandidatesDTO)this.getPageDTO()).setBgtTypesDTO(bgtTypesDTO);
        } else {
            ((IEmpCandidatesDTO)this.getPageDTO()).setBgtTypesDTO(null);
        }
        if (empDocumentsBean.getCurrentDetails() != null && empDocumentsBean.getCurrentDetails().size() > 0) {
            ((IEmpCandidatesDTO)this.getPageDTO()).setEmpCandidateDocumentsList(empDocumentsBean.getCurrentDetails());
        }
        //ProceduresCandidate
        if (proceduresBean.getCurrentDetails() != null && proceduresBean.getCurrentDetails().size() > 0) {
            ((IEmpCandidatesDTO)this.getPageDTO()).setEmpCandidateProceduresList(proceduresBean.getCurrentDetails());
            IProcedureResultsDTO procedureResultsDTO = EmpDTOFactory.createProcedureResultsDTO();
            if (proceduresBean.getEmpProceduresDTO().getCrmStatusCode() != null) {
                if (proceduresBean.getEmpProceduresDTO().isCrmChecked()) {
                    IProcedureResultsEntityKey ek = EmpEntityKeyFactory.createProcedureResultsEntityKey(1L);
                    procedureResultsDTO.setCode(ek);
                    proceduresBean.getEmpProceduresDTO().setProcedureResultsDTO(procedureResultsDTO);
                } else {
                    IProcedureResultsEntityKey ek = EmpEntityKeyFactory.createProcedureResultsEntityKey(2L);
                    procedureResultsDTO.setCode(ek);
                    proceduresBean.getEmpProceduresDTO().setProcedureResultsDTO(procedureResultsDTO);
                }
                proceduresBean.getEmpProceduresDTO().setSendDate(proceduresBean.getEmpProceduresDTO().getCrmLetterDate());
                ((IEmpCandidatesDTO)this.getPageDTO()).getEmpCandidateProceduresList().add(proceduresBean.getEmpProceduresDTO());
            }
        }
        if (((IEmpCandidatesDTO)getPageDTO()).getJobsDTOOtherJob() != null &&
            ((IEmpCandidatesDTO)getPageDTO()).getJobsDTOOtherJob().getCode() != null &&
            (((IEmpCandidatesDTO)getPageDTO()).getJobsDTOOtherJob()).getCode().getKey().equals(getVirtulValue())) {
            ((IEmpCandidatesDTO)getPageDTO()).setJobsDTOOtherJob(null);
        }
        //QualCandidate
        if(!((IEmpCandidatesDTO)getPageDTO()).isWitoutQualFlag()){
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
        }
        //EperiencesCandidate
        ExperiencesBean experiencesCandidate =
            (ExperiencesBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{empExperiencesBean}").getValue(FacesContext.getCurrentInstance());
        if (experiencesCandidate.getWorkDataListBean().getKwtWorkDataDTOList() != null) {
            ((IEmpCandidatesDTO)this.getPageDTO()).setKwtWorkDataTreeDTOList(new ArrayList());
            for (IKwtWorkDataTreeDTO dto : experiencesCandidate.getWorkDataListBean().getKwtWorkDataDTOList()) {
                if (dto.getStatusFlag() != null && dto.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                    ((IEmpCandidatesDTO)this.getPageDTO()).getKwtWorkDataTreeDTOList().add(dto);
                }
            }
        }
    }

    private IEmpCandidateParent fillSaveDto() {
        MainDataBean maindataBean = (MainDataBean)evaluateValueBinding("empMainDataBean");
        IEmpCandidateParent empCandidateParent = EmpDTOFactory.createEmpCandidateParent();
        fillPageDTO();
        try {
            IEntityKey newcode =
                EmpEntityKeyFactory.createEmpCandidatesEntityKey(((IEmpCandidatesEntityKey)getPageDTO().getCode()).getCandidateCode(),
                                                                 1L);
            IEmpCandidatesDTO firstEmpCandidatesDTO =
                (IEmpCandidatesDTO)EmpClientFactory.getEmpCandidatesClient().getById(newcode);
            //            if(!maindataBean.getContractType().equals("0")){
            //                ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(null);
            //            }
            empCandidateParent.setFirstEmpCandidatesDTO(firstEmpCandidatesDTO);
            empCandidateParent.getFirstEmpCandidatesDTO().setEmpCandidateDocumentsList(((IEmpCandidatesDTO)getPageDTO()).getEmpCandidateDocumentsList());
            empCandidateParent.getFirstEmpCandidatesDTO().setEmpCandidateProceduresList(((IEmpCandidatesDTO)getPageDTO()).getEmpCandidateProceduresList());
            empCandidateParent.getFirstEmpCandidatesDTO().setEmpCndSalaryElementsList(((IEmpCandidatesDTO)getPageDTO()).getEmpCndSalaryElementsList());
            empCandidateParent.setSelectedEmpCandidatesDTO(((IEmpCandidatesDTO)getPageDTO()));

        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        return empCandidateParent;
    }

    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        EmployeeListBean employeeListBean = (EmployeeListBean)evaluateValueBinding("empListBean");
        try {

            setPageDTO(EmpClientFactory.getEmpCandidatesClient().updateEmpCandCMT(fillSaveDto()));

            cancelSearch();
            employeeListBean.setSelectedHireType(((IHireTypesDTO)((IEmpCandidatesDTO)getPageDTO()).getHireTypesDTO()).getFirstParent().getKey());
            employeeListBean.setPageDTO(getPageDTO());

            employeeListBean.searchByMainHireType();
            if (employeeListBean.isUsingPaging()) {
                employeeListBean.getPagingBean().clearDTOS();
                employeeListBean.generatePagingRequestDTO(getPageDTO().getCode().getKey());
            } else {

                employeeListBean.getPageIndex(getPageDTO().getCode().getKey());
            }
            if (employeeListBean.getHighlightedDTOS() != null) {
                employeeListBean.getHighlightedDTOS().add(getPageDTO());
            }
            //resetTableHeaderStatus();

            if (((IEmpCandidatesDTO)getPageDTO()).getHireStagesDTO().getCode().getKey().equals(getManagedConstantsBean().getEmpStageWaitingEmploymentDecision().toString())) {
                getSharedUtil().handleSuccMsg("com.beshara.csc.hr.emp.presentation.resources.emp",
                                              "EmpStageWaitingEmploymentDecision");
            } else if (((IEmpCandidatesDTO)getPageDTO()).getHireStagesDTO().getCode().getKey().equals(getManagedConstantsBean().getEmpStageCompleteDoc().toString())) {
                getSharedUtil().handleSuccMsg("com.beshara.csc.hr.emp.presentation.resources.emp",
                                              "EmpStageCompleteDoc");
            } else if (((IEmpCandidatesDTO)getPageDTO()).getHireStagesDTO().getCode().getKey().equals(getManagedConstantsBean().getEmpStageCompleteProc().toString())) {
                getSharedUtil().handleSuccMsg("com.beshara.csc.hr.emp.presentation.resources.emp",
                                              "EmpStageCompleteProc");
            } else {
                getSharedUtil().handleSuccMsg("SuccesUpdated");
            }
            IntegrationBean integrationBean =
                (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());

            integrationBean.getHmBaseBeanFrom().clear();
            this.getSelectedDTOS().clear();
            //added by nora
            highlighDataTable("empListBean");
            //resetTableHeaderStatus();
        } catch (NotMatchedOnHireTypeException e) {
            getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                            "NotMatchedOnHireTypeException_EDIT");
        } catch (InValidSalEmpSalaryElementException e) {
            getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                            "InValidSalEmpSalaryElementException");

        } catch (DataBaseException e) {
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "FailureInUpdate");
        } catch (ItemNotFoundException item) {
            getSharedUtil().handleException(item, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "FailureInUpdate");
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "FailureInUpdate");
        } catch (Exception e) {
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "FailureInUpdate");
        }
        setSelectedRadio("");
        ;
    }

    public void resetDocumentList(ActionEvent ae) {
        try {
            if (savedHireType != null && savedHireType.equals(((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey()) &&
                savedDocumentList != null && savedDocumentList.size() != 0) {
                ((IEmpCandidatesDTO)getPageDTO()).setEmpCandidateDocumentsList(savedDocumentList);
            } else {
                ((IEmpCandidatesDTO)getPageDTO()).setEmpCandidateDocumentsList(new ArrayList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String back() {
        EmployeeListBean empListBean = (EmployeeListBean)evaluateValueBinding("empListBean");
        Long hirtypeCode = null;
        try {
            hirtypeCode = Long.valueOf(getBackEditHireType());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return super.back();
        }
        if (hirtypeCode != null) {
            IHireTypesEntityKey hireTypeEK = EmpEntityKeyFactory.createHireTypesEntityKey(hirtypeCode);
            IHireTypesDTO hireTypesDTO = EmpDTOFactory.createHireTypesDTO();
            hireTypesDTO.setCode(hireTypeEK);
            ((IEmpCandidatesDTO)empListBean.getPageDTO()).setHireTypesDTO(hireTypesDTO);
        }
        return super.back();
    }

    public void setVirtulValue(String virtulValue) {
        this.virtulValue = virtulValue;
    }

    public String getVirtulValue() {
        return virtulValue;
    }


    public void setInvalidMinFileNo(boolean invalidMinFileNo) {
        this.invalidMinFileNo = invalidMinFileNo;
    }

    public boolean isInvalidMinFileNo() {
        return invalidMinFileNo;
    }

    public void setSavedHireType(String savedHireType) {
        this.savedHireType = savedHireType;
    }

    public String getSavedHireType() {
        return savedHireType;
    }

    public void setSavedDocumentList(List savedDocumentList) {
        this.savedDocumentList = savedDocumentList;
    }

    public List getSavedDocumentList() {
        return savedDocumentList;
    }

    public void setMainDataOnlyFlag(boolean mainDataOnlyFlag) {
        this.mainDataOnlyFlag = mainDataOnlyFlag;
    }

    public boolean isMainDataOnlyFlag() {
        return mainDataOnlyFlag;
    }

    public void setMaintainMode(int maintainMode) {
        this.maintainMode = maintainMode;
    }

    public int getMaintainMode() {
        return maintainMode;
    }

    public void setTiles_defValue(String tiles_defValue) {
        this.tiles_defValue = tiles_defValue;
    }

    public String getTiles_defValue() {
        return tiles_defValue;
    }

    public void setEmpHireTypeNominationAgainFromOtherMinistries(boolean empHireTypeNominationAgainFromOtherMinistries) {
        this.empHireTypeNominationAgainFromOtherMinistries = empHireTypeNominationAgainFromOtherMinistries;
    }

    public boolean isEmpHireTypeNominationAgainFromOtherMinistries() {
        return empHireTypeNominationAgainFromOtherMinistries;
    }

    public void setLinesResultList(List<IResultDTO> linesResultList) {
        this.linesResultList = linesResultList;
    }

    public List<IResultDTO> getLinesResultList() {
        return linesResultList;
    }

    public void setSelectedHireTypeKey(String selectedHireTypeKey) {
        this.selectedHireTypeKey = selectedHireTypeKey;
    }

    public String getSelectedHireTypeKey() {
        return selectedHireTypeKey;
    }

    public void setQulIntegrationListBean(QulIntegrationListBean qulIntegrationListBean) {
        this.qulIntegrationListBean = qulIntegrationListBean;
    }

    public QulIntegrationListBean getQulIntegrationListBean() {
        return qulIntegrationListBean;
    }

    public String getMaintainBean() {
        return "empMaintainBean";
    }

    public void setEnableEditQulAndExper(boolean enableEditQulAndExper) {
        this.enableEditQulAndExper = enableEditQulAndExper;
    }

    public boolean isEnableEditQulAndExper() {
        return enableEditQulAndExper;
    }

    public void setBackEditHireType(String backEditHireType) {
        this.backEditHireType = backEditHireType;
    }

    public String getBackEditHireType() {
        return backEditHireType;
    }

    public void setBundleMsg(String bundleMsg) {
        this.bundleMsg = bundleMsg;
    }

    public String getBundleMsg() {
        return bundleMsg;
    }

    public void setEnableEditExper(boolean enableEditExper) {
        this.enableEditExper = enableEditExper;
    }

    public boolean isEnableEditExper() {
        return enableEditExper;
    }

    public void setEnableContractType(boolean enableContractType) {
        this.enableContractType = enableContractType;
    }

    public boolean isEnableContractType() {
        return enableContractType;
    }
}
