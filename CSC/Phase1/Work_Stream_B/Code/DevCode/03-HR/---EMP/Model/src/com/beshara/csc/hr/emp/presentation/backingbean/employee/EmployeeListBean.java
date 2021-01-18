package com.beshara.csc.hr.emp.presentation.backingbean.employee;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata.WorkDataListBean;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.client.IBgtProgramsClient;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidateDocumentsClient;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.HireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateParent;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateStatusDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCriminalStatusDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStagesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmpExtraDataTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.addcandidate.AddCandidateMaintainBean;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.addcandidate.MainDataCandidate;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.contractemployees.ContractEmployeesBean;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.estanaemployees.EstanaListBean;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.shared.ReviewListBean1;
import com.beshara.csc.hr.emp.presentation.backingbean.employment.shared.config.HireStageConst;
import com.beshara.csc.hr.emp.presentation.backingbean.viewdocuments.ViewDocumentsBean;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalExtraDataTypesDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IGenderCountryEntityKey;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.IOrgMinExtraDataClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.listqul.QulIntegrationListBean;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.qualificationAdd.QulAddIntegrationBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyListBaseBean;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;


public class EmployeeListBean extends ManyToManyListBaseBean {
    // abdelmoity
    private static final String BEAN_NAME = "empListBean";
    private boolean disableCandidate;
    private Long loginedMinistrycode = null;
    // override selected RadioButton
    MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
    IEmpEmployeeSearchDTO empEmployeesSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
    private String searchEmpName;
    private String searchWorkCenterName;

    //list that represents hire types in search div
    private List<IBasicDTO> hireTypesList = new ArrayList<IBasicDTO>();

    WorkDataListBean workDataListBean = new WorkDataListBean();
    private List<IBasicDTO> firstLevelHireTypesList;
    //    private QulIntegrationListBean qulIntegrationBean = new QulIntegrationListBean();
    private QulIntegrationListBean qulIntegrationBean = new QulIntegrationListBean();
    //Constant value that indicate empty value
    private Long virtualValue = ISystemConstant.VIRTUAL_VALUE;
    private String virtualStringValue = ISystemConstant.VIRTUAL_VALUE.toString();
    private List filteredList = new ArrayList();
    private Long civilId;
    private boolean contract;
    private boolean estana;

    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";

    private Long hireStageCode;

    private static final String SELECTION_BEAN_NAME = "empListBean";

    private String go = "";
    private String selectedHireType = "";
    private String selectedBackHireType;

    private boolean employeeInNeed;
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;
    
    private String reportUrl;
    private boolean enableRep =false;
    
    public EmployeeListBean() {
        maintainBean.setMainDataOnlyFlag(false);
        setAddBeanName("addCandidateMaintainBean");
        setEditBeanName("empMaintainBean");
        setEditNavigationCase("empmaindata");
        setAddNavigationCase("mainDataPage");

        setClient(EmpClientFactory.getEmpCandidatesClient());
        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setSelectedPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setSaveSortingState(true);
        setUsingPaging(true);
        setUsingBsnPaging(true);

        /* if (empCandidatesDTO.getHireTypeKey() == null) {
            empCandidatesDTO.setHireTypeKey("0");
        }

        setPageDTO(empCandidatesDTO);
        initForPage(); */

    }


    public void initForPage() {

        try {
            searchByMainHireType();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyListPage();
        app.setShowScirptGenerator(true);
        app.setShowContent1(true);
        app.setShowCustomDiv1(true);
        return app;
    }

    public void selectedRadioButton(ValueChangeEvent event) throws Exception {

        if (isUsingPaging()) {
            getPagingBean().setPreUpdatedDataModel(true);
        }

        if (event.getNewValue() != null) {

            setSelectedDTOS(new ArrayList<IBasicDTO>());
            IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();
            this.getSelectedDTOS().add(selected);

        }

    }

    // using hashset to solve the problem of the duplicate selected items in the datatable added by abolehassan hamed applied in the crs module

    /*
      * override selectedRadioButton in JsfBase
      * abdelmoity 27/02/2014
      */

    public void selectedRadioButton(ActionEvent event) throws Exception {
        int indexOfSelectedDataInDataTable = getMyDataTable().getRowIndex();
        this.getSelectedDTOS().clear();
        IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();
        this.getSelectedDTOS().add(selected);
        // updated
        IEmpCandidatesDTO empDto = (IEmpCandidatesDTO)selected;
        String hireType = empDto.getHireTypesDTO().getCode().getKey();

        IHireTypesDTO parentHireType = ((IHireTypesDTO)empDto.getHireTypesDTO()).getParentHireType();
        String parent = "";
        try {
            if (parentHireType != null) {
                parent = parentHireType.getCode().getKey();
            }

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            parent = "10";
        }
        if (hireType.equals(IEMPConstants._EMP_CENTERAL_HIRE_TYPE.toString()) ||
            parent.equals(IEMPConstants._EMP_CENTERAL_HIRE_TYPE.toString())) {
            disableCandidate = false;
        } else {
            disableCandidate = true;
        }
    }

    /**
     * Purpose:cancel candidate for centralized
     * Creation/Modification History :
     * 1.1 - Developer Name:abdelmoity
     * 1.2 - Date:  26/02/2014
     * 1.3 - Developer Name:Kareem Sayed
     * 1.4 - Date:  28/08/2014
     */
    
     public void cancelCandidateConfirmDiv() {
        setSelectedPageDTO(getSelectedDTOS().get(0));
         setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.customDiv1);");  
     }
    public void  cancelBack(){
        getSelectedDTOS().clear();
        }
    public void cancelCandidate() {
        IEmpCandidatesDTO empDto = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        // set dto
        empDto.setActiveFlag(ISystemConstant.NON_ACTIVE_FLAG);
        //empDto.getHireStagesDTO().setCode(new HireStagesEntityKey(IEMPConstants.EMP_HSTAGE_CANCEL_Candidate));
        IEmpCandidateStatusDTO empCandidateStatusDTO = EmpDTOFactory.createEmpCandidateStatusDTO();
        empCandidateStatusDTO.setCode(EmpEntityKeyFactory.createEmpCandidateStatusEntityKey(IEMPConstants.EMP_CAND_STATUS_REFUSE_CANDITATE));
        empDto.setEmpCandidateStatusDTO(empCandidateStatusDTO);


        try {
            EmpClientFactory.getEmpCandidatesClient().updateEmpCancelCandidate(empDto);
            setSearchMode(false);
            searchByMainHireType(); // refresh
           
            getSharedUtil().handleSuccMsg("com.beshara.csc.hr.emp.presentation.resources.emp",
                                          "success_cancel_candidate");
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleSuccMsg("com.beshara.csc.hr.emp.presentation.resources.emp",
                                          "fail_cancel_candidate");
        } catch (DataBaseException e) {
            e.printStackTrace();
            getSharedUtil().handleSuccMsg("com.beshara.csc.hr.emp.presentation.resources.emp",
                                          "fail_cancel_candidate");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // update
    //    protected IBasicDTO executeEdit() throws DataBaseException, SharedApplicationException {
    //        client.update(pageDTO);
    //        return pageDTO;
    //    }


    public static String formatSearchString(String inputString) {
        if (inputString.contains("%"))
            return inputString;
        return "%" + inputString.trim() + "%";
    }

    /**
     * Purpose:Search Method
     * Creation/Modification History :
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  29-July-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: fill empEmployeesSearchDTO with the selected values from search div
     */
    public void simpleSearch() {

        setSearchMode(true);
        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }
        setSelectedRadio("");
        try {
            if (searchEmpName != null && !searchEmpName.equals("")) {
                empEmployeesSearchDTO.setEmpName(formatSearchString(searchEmpName));
            }
        } catch (Exception e) {
            e.printStackTrace();
            empEmployeesSearchDTO.setEmpName(null);
        }


        empEmployeesSearchDTO.setLoginMinistry(getLoginedMinistrycode());


        IEmpCandidatesDTO empCanDTO = ((IEmpCandidatesDTO)getPageDTO());

        if (empCanDTO.getHireTypeKey() != null) {

            empEmployeesSearchDTO.setHireTypeFromDropList(Long.valueOf(empCanDTO.getHireTypeKey()));

        }

        if (isUsingPaging()) {

            generatePagingRequestDTO("empListBean", "simpleSearchWithPaging", empEmployeesSearchDTO);
            //added by nora
            setSorting(false);

            resetPageIndex();

        } else {

            setMyTableData(getSearchResult(empEmployeesSearchDTO));

            if (getSelectedDTOS() != null) {
                getSelectedDTOS().clear();
            }

            this.repositionPage(0);

        }


    }

    public PagingResponseDTO simpleSearchWithPaging(PagingRequestDTO request) {

        IEmpEmployeeSearchDTO empEmployeesSearchDTO = (IEmpEmployeeSearchDTO)request.getSearchDTO();

        return new PagingResponseDTO(getSearchResult(empEmployeesSearchDTO));

    }

    private List getSearchResult(IEmpEmployeeSearchDTO empEmployeesSearchDTO) {

        List resultList = null;

        try {
            //   empEmployeesSearchDTO.setActiveFlag(com.beshara.csc.hr.emp.business.shared.IEMPConstants.EMP_ACTIVE_FLAG_NON);

            // resultList = EmpClientFactory.getEmployeesClient().searchEmployeeToCompleteData(empEmployeesSearchDTO);

            // to do M.abdelsabour

            resultList =
                    ((IEmpCandidatesClient)getClient()).searchForSpecificEmployeeByLoggedMin(empEmployeesSearchDTO);

        } catch (Exception e) {
            resultList = new ArrayList();
            setMyTableData(resultList);
            setFilteredList(resultList);

            e.printStackTrace();
        }

        return resultList;

    }

    public void cancelSearch() {
        try {
            if (getSelectedDTOS() != null) {
                getSelectedDTOS().clear();
            }
            setSelectedRadio("");
            setEmpEmployeesSearchDTO(EmpDTOFactory.createEmpEmployeeSearchDTO());
            setSearchWorkCenterName("");
            setSearchEmpName("");
            super.cancelSearch();
            searchByMainHireType();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEmpEmployeesSearchDTO(IEmpEmployeeSearchDTO empEmployeesSearchDTO) {
        this.empEmployeesSearchDTO = empEmployeesSearchDTO;
    }

    public IEmpEmployeeSearchDTO getEmpEmployeesSearchDTO() {
        return empEmployeesSearchDTO;
    }

    public void setHireTypesList(List<IBasicDTO> hireTypesList) {
        this.hireTypesList = hireTypesList;
    }

    public List<IBasicDTO> getHireTypesList() {
        if (hireTypesList.size() == 0) {
            SharedUtilBean sharedUtilBean = getSharedUtil();

            List<IBasicDTO> dtos = new ArrayList<IBasicDTO>();
            for (int i = 0; i < 4; i++) {
                dtos.add(i, new BasicDTO());
            }
            dtos.get(0).setCode(EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(getManagedConstantsBean().getEmpHireTypeExceptedFromCenteralEmployment())));
            dtos.get(0).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                              "exceptedFromCenteralEmployment"));
            dtos.get(1).setCode(EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(getManagedConstantsBean().getEmpHireTypeNominationAgain())));
            dtos.get(1).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                              "nominationAgain"));
            dtos.get(2).setCode(EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(getManagedConstantsBean().getEmpHireTypeMovedToOtherMinisties())));
            dtos.get(2).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                              "EMP_HIRE_TYPE_MOVEED_TO_OTHER_MINISTRIES"));
            dtos.get(3).setCode(EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(getManagedConstantsBean().getEmpHireTypeNominationByMinistry())));
            dtos.get(3).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                              "nominationByMinistry"));
            dtos.add(4, new BasicDTO());
            dtos.get(4).setCode(EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(getManagedConstantsBean().getEmpHireTypeFromCenteralEmployment())));
            dtos.get(4).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                              "EMP_HIRE_TYPE_FROM_CENTER_EMPLOYMENT"));
            hireTypesList = dtos;

        }
        return hireTypesList;
    }

    public void setVirtualValue(Long virtualValue) {
        this.virtualValue = virtualValue;
    }

    public Long getVirtualValue() {
        return virtualValue;
    }

    /**
     * Purpose:calling before add/edit operation to initialize needed objects in any tab
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora
     * 1.2 - Date:   31/7/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     * modified by I.Omar
     * modified by Kareem Sayed
     * 1.5 - Date:   8/9/2014
     */
    public void initializeObjectBeforeMaintain() {
        IEmpCandidateParent empCandidateParent = EmpDTOFactory.createEmpCandidateParent();
        ProceduresBean procBean = ProceduresBean.getInstance();
        MainDataBean maindataBean = (MainDataBean)evaluateValueBinding("empMainDataBean");
        MaintainBean maintainBean =
            (MaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
                                                                                                this.getEditBeanName() +
                                                                                                "}").getValue(FacesContext.getCurrentInstance());
        QulIntegrationListBean qulIntegrationListBean =
            (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
        try {
            qulIntegrationListBean.setNeedAddQualification(true);
            qulIntegrationListBean.setKwtCitizenDTO(null);
            IEntityKey newcode =
                EmpEntityKeyFactory.createEmpCandidatesEntityKey(((IEmpCandidatesEntityKey)getPageDTO().getCode()).getCandidateCode(),
                                                                 1L);
            IEmpCandidatesDTO firstEmpCandidatesDTO =
                (IEmpCandidatesDTO)EmpClientFactory.getEmpCandidatesClient().getById(newcode);


            empCandidateParent.setFirstEmpCandidatesDTO(firstEmpCandidatesDTO);
            empCandidateParent.setSelectedEmpCandidatesDTO((IEmpCandidatesDTO)getPageDTO());
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getPageDTO();


            Long firstParentHireTypeCode =
                Long.valueOf(((IHireTypesDTO)empCandidatesDTO.getHireTypesDTO()).getFirstParent().getKey());
            if (IEMPConstants._EMP_CENTERAL_HIRE_TYPE.equals(firstParentHireTypeCode)) {
                maindataBean.setDataDisabledIfEmpFromCRS(true);
                maintainBean.setEnableEditQulAndExper(false);
                if (!empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals(IEMPConstants.HIRE_STAGE_COMPLETING_INFO_INPROGRESS) &&
                    !empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals(IEMPConstants.HIRE_STAGE_RELEASE_DECISION_INPROGRESS) &&
                    !empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals(IEMPConstants.HIRE_STAGE_DEMAND_RESPONSE_FROM_DEPARTMENT_OF_CHOICE)) {
                    maintainBean.setEnableEditExper(false);
                }
                maintainBean.setBundleMsg(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                         "not_available_for_central_cand"));
                maindataBean.setSelectedHireTypeCode(((IHireTypesEntityKey)empCandidatesDTO.getHireTypesDTO().getCode()).getHirtypeCode().toString());
                qulIntegrationListBean.setCustomCurentQual(false);
            } else {
                qulIntegrationListBean.setCustomCurentQual(true);
                civilId = Long.valueOf(empCandidatesDTO.getCitizensResidentsDTO().getCode().getKey());
                System.out.println(civilId + "civilId");
                IEmpCandidatesClient client = (IEmpCandidatesClient)getClient();
           Long candidateCode=   ((IEmpCandidatesEntityKey)  empCandidatesDTO.getCode()).getCandidateCode();
                contract = client.checkEmployeeByExtraDataType(civilId, IEMPConstants.CONTRACT_TYPE,candidateCode);
                System.out.println(contract + "contract");
                estana = client.checkEmployeeByExtraDataType(civilId, IEMPConstants.ESTANA_TYPE,candidateCode);
                System.out.println(estana + "estana");
                if (contract) {
                    if (!empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals(IEMPConstants.HIRE_STAGE_COMPLETING_INFO_INPROGRESS) &&
                        !empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals(IEMPConstants.HIRE_STAGE_REJECTED_BY_FATWA) &&
                        !empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals(IEMPConstants.HIRE_STAGE_RELEASE_DECISION_INPROGRESS)) {
                        maintainBean.setEnableEditQulAndExper(false);
                        maintainBean.setEnableEditExper(false);
                        maintainBean.setBundleMsg(getSharedUtil().messageLocator(BUNDLE_NAME, "unable_add_Qual"));
                        IOrgMinExtraDataClient extraDataClient = OrgClientFactory.getOrgMinExtraDataClient();
                        try {
                            maindataBean.setMinExcepted(extraDataClient.checkExceptedMin(empCandidatesDTO.getMinCode(),
                                                                                         43L));
                            String bgtValue =
                                fillBgtValue(((IWorkCentersEntityKey)empCandidatesDTO.getWorkCentersDTO().getCode()).getWrkCode());
                            if (!bgtValue.equals("")) {
                                maindataBean.setMinExcepted(extraDataClient.checkExceptedMin(empCandidatesDTO.getMinCode(),
                                                                                             43L, bgtValue));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (estana) {
                    if (!empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals(IEMPConstants.HIRE_STAGE_COMPLETING_INFO_INPROGRESS) &&
                        !empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals(IEMPConstants.HIRE_STAGE_RELEASE_DECISION_INPROGRESS) &&
                        !empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals(IEMPConstants.REJECTED_BY_CIVIL_SERVICE)) {
                        maintainBean.setEnableEditQulAndExper(false);
                        maintainBean.setEnableEditExper(false);
                        maintainBean.setBundleMsg(getSharedUtil().messageLocator(BUNDLE_NAME, "unable_add_Qual"));
                        IOrgMinExtraDataClient extraDataClient = OrgClientFactory.getOrgMinExtraDataClient();
                                   try {
                                  maindataBean.setMinExcepted(extraDataClient.checkExceptedMin(empCandidatesDTO.getMinCode(),44L));
                                       String bgtValue =
                                           fillBgtValue(((IWorkCentersEntityKey)empCandidatesDTO.getWorkCentersDTO().getCode()).getWrkCode());
                                       if (!bgtValue.equals("")) {
                                           maindataBean.setMinExcepted(extraDataClient.checkExceptedMin(empCandidatesDTO.getMinCode(),
                                                                                                        44L, bgtValue));
                                       }
                                   } catch (Exception e) {
                                       e.printStackTrace();
                                   }
                    }
                }
                maindataBean.setDataDisabledIfEmpFromCRS(false);
                maindataBean.setSelectedHireTypeCode(((IHireTypesEntityKey)empCandidatesDTO.getHireTypesDTO().getCode()).getHirtypeCode().toString());
            }
            maindataBean.fillHireTypesList(empCandidatesDTO);
            IKwtCitizensResidentsDTO kwtCitizen = (IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO();
            civilId = ((IKwtCitizensResidentsEntityKey)kwtCitizen.getCode()).getCivilId();

            maindataBean.fillQualIntegration(civilId);
            maindataBean.setCivilId(civilId);
            maintainBean.setMainDataOnlyFlag(false);
            boolean nonCitizenNationality = false;
            if (kwtCitizen.getNationality() == 0 && kwtCitizen.getNonStatus() !=null && kwtCitizen.getNonStatus() == 2) {
                nonCitizenNationality = true;
            }
            if (getPageDTO() != null) {
                ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
                ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
                ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
                
                ISalElementGuidesDTO raiseDTOEq = SalDTOFactory.createSalElementGuidesDTO();
                ISalElementGuidesDTO degreeDTOEq = SalDTOFactory.createSalElementGuidesDTO();
                ISalElementGuidesDTO groupDTOEq = SalDTOFactory.createSalElementGuidesDTO();
                List<ISalExtraDataTypesDTO> salExtraDataTypesDTOList = new ArrayList();
                IEmpCndSalaryElementsDTO salaryElementDTO = EmpDTOFactory.createEmpCndSalaryElementsDTO();

                System.out.println(ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
                try {
                    salaryElementDTO =
                            EmpClientFactory.getEmpCndSalaryElementsClient().getByCandCode(empCandidatesDTO.getCode());
                } catch (Exception e) {
                    maindataBean.setContractType(getVirtualConstValue());
                }
                if (salaryElementDTO != null && salaryElementDTO.getCode() != null) {
                    maindataBean.setRaiseCode(((ISalElementGuidesEntityKey)salaryElementDTO.getSalElementGuidesDTO().getCode()).getElmguideCode().toString());
                    
                    
                    String code = maindataBean.getRaiseCode();
                    if (maindataBean.getRaiseCode() != null) {
                        raiseDTO =
                                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.parseLong(maindataBean.getRaiseCode()));

                        maindataBean.setRaiseName(raiseDTO.getName());
                        try {
                            salExtraDataTypesDTOList =
                                    (List)SalClientFactory.getSalGuideExtraDataClient().getExtraDataTypesByElmguideCode(raiseDTO.getCode());
                        } catch (DataBaseException e) {
                            maindataBean.setContractType("0");
                            if (!IEMPConstants._EMP_CENTERAL_HIRE_TYPE.equals(firstParentHireTypeCode)) {
                                maintainBean.setEnableEditQulAndExper(true);
                                //                                qulIntegrationListBean.setNeedAddQualification(true);
                            }
                        } catch (SharedApplicationException e) {
                            maindataBean.setContractType("0");
                            if (!IEMPConstants._EMP_CENTERAL_HIRE_TYPE.equals(firstParentHireTypeCode)) {
                                maintainBean.setEnableEditQulAndExper(true);
                                maintainBean.setEnableEditExper(true);
                                //                                qulIntegrationListBean.setNeedAddQualification(true);
                            }
                        }
                        if (salExtraDataTypesDTOList != null && salExtraDataTypesDTOList.size() > 0) {
                            maindataBean.setContractType(salExtraDataTypesDTOList.get(0).getCode().getKey());
                            maintainBean.setEnableEditQulAndExper(false);
                            //                            qulIntegrationListBean.setNeedAddQualification(false);
                            maintainBean.setBundleMsg(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                                     "unable_add_ExperiencesOrQual"));
                        }


                    }

                    //Degree
                    if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                        maindataBean.setDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode().toString());
                    }
                    if (maindataBean.getDegreeCode() != null) {
                        degreeDTO =
                                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.parseLong(maindataBean.getDegreeCode()));
                    }
                    //Group
                    if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                        maindataBean.setGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
                    }
                    if (maindataBean.getGroupCode() != null) {
                        groupDTO =
                                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(maindataBean.getGroupCode());
                    }


                    //Cader
                    if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                        maindataBean.setCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                        maindataBean.setCaderName(groupDTO.getParentObject().getName());
                    }
                    if (raiseDTO != null && degreeDTO != null && groupDTO != null) {
                        maindataBean.setFromEmpList(true);
                        maindataBean.fillCaderList();
                        maindataBean.fillGroupList();
                        maindataBean.filterDegreesByGroup();
                        maindataBean.filterRaisesByDegree();
                        maindataBean.setFromEmpList(false);
                    }
                    
                    
                    if(salaryElementDTO.getElementValue() !=null ){
                            maindataBean.setTotalRewardAccepted(salaryElementDTO.getElementValue().toString());
                        }
                    
                    if(salaryElementDTO.getElmguideCodeEquv()!=null && !salaryElementDTO.getElmguideCodeEquv().equals("")){
                           maindataBean.setRaiseCodeEq(salaryElementDTO.getElmguideCodeEquv().toString());
                        raiseDTOEq =
                                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.parseLong(maindataBean.getRaiseCodeEq()));
                        
                        if (raiseDTOEq.getParentObject() != null && raiseDTOEq.getParentObject().getCode() != null) {
                            maindataBean.setDegreeCodeEq(((ISalElementGuidesEntityKey)raiseDTOEq.getParentObject().getCode()).getElmguideCode());
                        }
                        if (maindataBean.getDegreeCodeEq() != null) {
                            degreeDTOEq =
                                    (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(maindataBean.getDegreeCodeEq());
                        }
                        //Group
                        if (degreeDTOEq.getParentObject() != null && degreeDTOEq.getParentObject().getCode() != null) {
                            maindataBean.setGroupCodeEq(((ISalElementGuidesEntityKey)degreeDTOEq.getParentObject().getCode()).getElmguideCode());
                        }
                        if (maindataBean.getGroupCodeEq() != null) {
                            groupDTOEq =
                                    (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(maindataBean.getGroupCodeEq());
                        }


                        //Cader
                        if (groupDTOEq.getParentObject() != null && groupDTOEq.getParentObject().getCode() != null) {
                            maindataBean.setCaderCodeEq(((ISalElementGuidesEntityKey)groupDTOEq.getParentObject().getCode()).getElmguideCode());
                        }
                        if (raiseDTOEq != null && degreeDTOEq != null && groupDTOEq != null) {
                            maindataBean.setFromEmpList(true);
                            maindataBean.getCaderListEq();
                            maindataBean.getGroupListEq();
                            maindataBean.getDegreeList();
                            maindataBean.fillRaiseList();
                        }
                    }

                } else if (nonCitizenNationality) {
                    maindataBean.setContractType("7");
                    maintainBean.setEnableContractType(true);
                    maindataBean.fillCaderList();
                } else {
                    maindataBean.setContractType(getVirtualConstValue());
                }


            }
            Long hirStage = Long.valueOf(empCandidatesDTO.getHireStagesDTO().getCode().getKey());
            if (!IEMPConstants._EMP_CENTERAL_HIRE_TYPE.equals(firstParentHireTypeCode)) {
                if (hirStage.equals(1L) || hirStage.equals(2L)) {
                    if (!nonCitizenNationality)
                        maintainBean.setEnableEditQulAndExper(true);
                    qulIntegrationListBean.setNeedAddQualification(true);
                }
            }
            if (IEMPConstants._EMP_CENTERAL_HIRE_TYPE.equals(firstParentHireTypeCode)) {
                maindataBean.setContractType(getVirtualConstValue());
            }
            if (empCandidatesDTO.getCitizensResidentsDTO() == null) {
                empCandidatesDTO.setCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
            }

            if (empCandidatesDTO != null && empCandidatesDTO.getJobsDTO() != null &&
                (maindataBean.getSelectedJobName() == null || maindataBean.getSelectedJobName().equals("")))
                maindataBean.setSelectedJobName(empCandidatesDTO.getJobsDTO().getName());

            if (empCandidateParent.getFirstEmpCandidatesDTO().getEmpCandidateDocumentsList() == null) {
                empCandidatesDTO.setEmpCandidateDocumentsList(new ArrayList<IBasicDTO>());
            } else {
                empCandidatesDTO.setEmpCandidateDocumentsList(empCandidateParent.getFirstEmpCandidatesDTO().getEmpCandidateDocumentsList());
            }
            if (empCandidateParent.getFirstEmpCandidatesDTO().getEmpCandidateProceduresList() == null ||
                empCandidateParent.getFirstEmpCandidatesDTO().getEmpCandidateProceduresList().size() == 0L) {
                empCandidatesDTO.setEmpCandidateProceduresList(new ArrayList<IBasicDTO>());

                procBean.fillListAvailable();
                procBean.selectedAvailableAll();
                procBean.add();
                procBean.empProceduresDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            } else if (empCandidateParent.getFirstEmpCandidatesDTO().getEmpCandidateProceduresList() == null ||
                       empCandidateParent.getFirstEmpCandidatesDTO().getEmpCandidateProceduresList().size() == 1L) {

                procBean.fillListAvailable();
                procBean.selectedAvailableAll();
                procBean.add();
                procBean.getEmpProceduresDTO().setStatusFlag(ISystemConstant.ADDED_ITEM);
                procBean.update();
                procBean.setCurrentDetails(empCandidateParent.getFirstEmpCandidatesDTO().getEmpCandidateProceduresList());
            } else {
                empCandidatesDTO.setEmpCandidateProceduresList(empCandidateParent.getFirstEmpCandidatesDTO().getEmpCandidateProceduresList());

                for (IBasicDTO basicDto : empCandidatesDTO.getEmpCandidateProceduresList()) {
                    IEmpCandidateProceduresDTO dto = (IEmpCandidateProceduresDTO)basicDto;
                    if (dto.getHireProceduresDTO().getCode().getKey().equals("2")) {
                        if (dto.getProcedureResultsDTO().getCode().getKey().equals("1"))
                            dto.setCrmChecked(true);
                        List statusList =
                            EmpClientFactory.getEmpCriminalStatusClient().searchByCode(dto.getCrmStatusCode());
                        procBean.setCriminalResult(((IEmpCriminalStatusDTO)statusList.get(0)).getName());
                        procBean.setEmpProceduresDTO(dto);
                        procBean.setHasCriminalCase(true);
                        if (dto.getCrmStatusCode().equals("1")) {
                            procBean.setShowBlock(false);
                        } else if (dto.getCrmStatusCode().equals("2")) {
                            procBean.setShowBlock(false);
                        } else {
                            procBean.setShowBlock(true);
                        }
                        procBean.getCurrentDetails().remove(dto);
                        break;
                    }
                }
            }
            if (empCandidatesDTO != null && empCandidatesDTO.getWorkCentersDTO() != null) {
                maindataBean.setWorkCenterKey(((IWorkCentersEntityKey)empCandidatesDTO.getWorkCentersDTO().getCode()).getWrkCode());
                maindataBean.setBgtProgramName(fillBgtProgName(((IWorkCentersEntityKey)empCandidatesDTO.getWorkCentersDTO().getCode()).getWrkCode()));
            }

            maindataBean.setOldContractType(maindataBean.getContractType());
            maindataBean.setPageDTO(empCandidatesDTO);
            Long cntryCode = ((IGenderCountryEntityKey)kwtCitizen.getCountriesDTO().getCode()).getCntryCode();
            if (cntryCode.equals(101L))
                maindataBean.setKuwaitCitizen(true);
            else
                maindataBean.setKuwaitCitizen(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String fillBgtValue(String workCenterKey){
               String bgtValue= "";
               if (workCenterKey != null && workCenterKey != "") {
                   IBgtProgramsClient bgtProgramClient = BgtClientFactory.getBgtProgramsClient();
                   List list = null;
                   try {
                       list = bgtProgramClient.getBgtProgramByWrkCenterCode(workCenterKey);
                       if (list.size() > 0) {
                           
                            bgtValue=  ((IBgtProgramsDTO)list.get(0)).getCode().getKey();
                           
                           
                       }
                   } catch (DataBaseException e) {
                       e.printStackTrace();
                       bgtValue = "";
                   } catch (SharedApplicationException e) {
                       e.printStackTrace();
                       bgtValue = "";
                   }
               }
               return bgtValue;
           }

    public String fillBgtProgName(String workCenterKey) {
        String bgtprName = "";
        if (workCenterKey != null && workCenterKey != "") {
            IBgtProgramsClient bgtProgramClient = BgtClientFactory.getBgtProgramsClient();
            List list = null;
            try {
                list = bgtProgramClient.getBgtProgramByWrkCenterCode(workCenterKey);
                if (list.size() > 0) {
                    bgtprName = ((IBgtProgramsDTO)list.get(0)).getName();
                }
            } catch (DataBaseException e) {
                e.printStackTrace();
                bgtprName = "";
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                bgtprName = "";
            }
        }
        return bgtprName;
    }

    public void setSearchEmpName(String searchEmpName) {
        this.searchEmpName = searchEmpName;
    }

    public String getSearchEmpName() {
        return searchEmpName;
    }

    public void setSearchWorkCenterName(String searchWorkCenterName) {
        this.searchWorkCenterName = searchWorkCenterName;
    }

    public String getSearchWorkCenterName() {
        return searchWorkCenterName;
    }

    public String goPreEdit() throws DataBaseException, SharedApplicationException {

        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {

            ManyToManyMaintainBaseBean maintainBean =
                (ManyToManyMaintainBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
                                                                                                                  this.getEditBeanName() +
                                                                                                                  "}").getValue(FacesContext.getCurrentInstance());

            setPageDTO(getClient().getById(this.getSelectedDTOS().get(0).getCode()));
            maintainBean.setPageDTO(this.getPageDTO());
            this.initializeObjectBeforeMaintain();
            return getEditNavigationCase();

        }
        return null;
    }

    public String goEdit() throws DataBaseException, SharedApplicationException {

        String returnString = this.goPreEdit();

        //TODO locking code
        if (!lock()) {
            return null;
        }
        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
        maintainBean.setBackEditHireType(getSelectedBackHireType());
        maintainBean.setSavedHireType(((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey());
        maintainBean.setSavedDocumentList(((IEmpCandidatesDTO)getPageDTO()).getEmpCandidateDocumentsList());
        maintainBean.setPageDTO(super.getPageDTO());
        if (workDataListBean.getKwtWorkDataDTOList() != null)
            workDataListBean.getKwtWorkDataDTOList().clear();
        //TODO locking code
        // propagate the locking status to the maintain bean
        // because it will be responsible to unlock the edited item if it was locked
        maintainBean.setLastLockingAction(getLastLockingAction());
        return returnString;
    }

    public void getAll() throws DataBaseException {

        if (isUsingPaging()) {

            generatePagingRequestDTO("empListBean", "getAllWithPaging");

        } else {

            setMyTableData(getFilteredList());

            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();

        }

        this.reinitializeSort();

    }

    public PagingResponseDTO getAllWithPagingForEmp(PagingRequestDTO pagingRequestDTO) {
        
        return baseSearchWithPaging(pagingRequestDTO);  
       
    
        
    }
    
    public PagingResponseDTO baseSearchWithPaging(PagingRequestDTO pagingRequest) {
                IPagingResponseDTO bsnResponseDTO = getDataWithPaging(pagingRequest);
                PagingResponseDTO pagingResponseDTO = null;

                if (bsnResponseDTO.getBasicDTOList() == null) { 
                    pagingResponseDTO = new PagingResponseDTO(new ArrayList());
                } else {
                    pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getBasicDTOList()); 
//                    if (getCurrentPageIndex() == 1) {
                        pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                        getPagingRequestDTO().setParams(new Object[] { bsnResponseDTO.getCount() });
//                    } else if (getPagingBean() != null && getPagingBean().getTotalListSize() != 0) {
//                        pagingResponseDTO.setTotalListSize((getPagingBean().getTotalListSize()));
//
//                    }
                }


                return pagingResponseDTO;
            }
       
    private com.beshara.base.paging.impl.PagingResponseDTO getDataWithPaging(PagingRequestDTO pagingRequestDTO) {
        System.out.println(pagingRequestDTO == null);
//        if(empEmployeesSearchDTO.getEmpHireTypes() ==null){
//            return  new com.beshara.base.paging.impl.PagingResponseDTO();
//        }
        int pageIndex = getCurrentPageIndex();
        com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO = 
            new com.beshara.base.paging.impl.PagingRequestDTO();

        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));

        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));

//        if (pageIndex == 1) {
            bsnPagingRequestDTO.setCountRequired(true);
//        }
        if (isSorting() && getBsnSortingColumnName() != null) {
            bsnPagingRequestDTO.setSortAscending(isSortAscending());
            List<String> sortingColumnList = new ArrayList<String>();
            sortingColumnList.add(getBsnSortingColumnName());

            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }
        empEmployeesSearchDTO.setPagingRequestDTO(bsnPagingRequestDTO);

        try {

            bsnPagingResponseDTO =
                    (com.beshara.base.paging.impl.PagingResponseDTO)EmpClientFactory.getEmpCandidatesClient().searchEmployeeToCompleteData(empEmployeesSearchDTO);


        } catch (NoResultException ne) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (SharedApplicationException e) {
            //getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            //  getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (Throwable e) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } 

        return bsnPagingResponseDTO;
    }
    
    
    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {

            return new PagingResponseDTO(getFilteredList());

        }

    public void openSearchDiv() {

        if (!isSearchMode()) {
            if (getSelectedDTOS() != null) {
                getSelectedDTOS().clear();
            }
            setSelectedRadio("");
            setEmpEmployeesSearchDTO(EmpDTOFactory.createEmpEmployeeSearchDTO());

            setSearchWorkCenterName("");
            setSearchEmpName("");
        }

    }

    public void setVirtualStringValue(String virtualStringValue) {
        this.virtualStringValue = virtualStringValue;
    }

    public String getVirtualStringValue() {
        return virtualStringValue;
    }

    public void setFirstLevelHireTypesList(List<IBasicDTO> firstLevelHireTypesList) {
        this.firstLevelHireTypesList = firstLevelHireTypesList;
    }

    public List<IBasicDTO> getFirstLevelHireTypesList() {

        if (firstLevelHireTypesList == null) { 
            try {
                firstLevelHireTypesList = EmpClientFactory.getHireTypesClient().getFirstLevelHireTypes();
                    initDT();
            } catch (DataBaseException e) {
                e.printStackTrace();
                firstLevelHireTypesList = new ArrayList<IBasicDTO>();
            }
        }
        return firstLevelHireTypesList;
    }

    public static EmployeeListBean getInstance() {
        return (EmployeeListBean)JSFHelper.getValue(BEAN_NAME);
    }

    public String viewCandidateQuls() {
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());

        integrationBean.getHmBaseBeanFrom().put("empListBean", EmployeeListBean.getInstance());
        integrationBean.setNavgationCaseFrom("emplist");
        Long civilId =
            ((IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)getSelectedDTOS().get(0)).getCitizensResidentsDTO()).getCivilId();
        if (qulIntegrationBean == null)

            qulIntegrationBean.setCivilId(civilId);

        integrationBean.getHmBaseBeanFrom().put("qulIntegrationBean", qulIntegrationBean);
        //        getQulIntegrationBean().setKwtCitizensResidentsDTO((IKwtCitizensResidentsDTO)qulIntegrationBean.getKwtCitizenInfo(civilId));
        //            getQulIntegrationBean().setPrepareMethodName(BEAN_NAME + ".prePareMethodName");
        //            getQulIntegrationBean().setReturnMethodName(BEAN_NAME + ".returnMethodName");

        return "viewIntegrationPeronalQuls";
    }

    public void searchByMainHireType() {
        if (isSearchMode()) {
            return;
        }

      
        IEmpCandidatesDTO empDTO = (IEmpCandidatesDTO)getPageDTO();
        Long hireTypeKey;
        List list = new ArrayList();
        if (isSearchMode()) {
            try {
                cancelSearch();
                setMyTableData(new ArrayList());
                if (getListSize() > 0)
                    setSearchMode(true);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        if (empDTO != null) {
            setSelectedBackHireType(empDTO.getHireTypeKey());
            hireTypeKey = 0L;
            if(empDTO.getHireTypeKey()!=null)
            hireTypeKey = Long.valueOf(empDTO.getHireTypeKey());
            
            if (!getSelectedHireType().equals("")) {
                hireTypeKey = Long.valueOf(getSelectedHireType());
                ((IEmpCandidatesDTO)getPageDTO()).setHireTypeKey(String.valueOf(hireTypeKey));
            }
          
                if (hireTypeKey !=null && hireTypeKey == IEMPConstants._EMP_CENTERAL_HIRE_TYPE ||
                    hireTypeKey == IEMPConstants._EMP_CANDIDATE_FOR_MINISTRY ||
                    hireTypeKey == IEMPConstants._EMP_REEMPLOYEMENTS ||
                    hireTypeKey == IEMPConstants._EMP_NOT_ACTIVE_STATUS) {
                    empEmployeesSearchDTO.setEmpHireTypes(hireTypeKey);
                } else {
                    HireTypesDTO hireTypesDTO = (HireTypesDTO)empDTO.getHireTypesDTO();
                    empEmployeesSearchDTO.setEmpHireTypes(Long.valueOf(hireTypesDTO.getParentCode().getKey()));
                    ((IEmpCandidatesDTO)getPageDTO()).setHireTypeKey(hireTypesDTO.getParentCode().getKey());
                    setSelectedBackHireType(hireTypesDTO.getParentCode().getKey());
                }

                // TODO set MinCode form Login Ministry

                empEmployeesSearchDTO.setMinistryCode(getLoginedMinistrycode());
            setUpdateMyPagedDataModel(true);
           generatePagingRequestDTO("empListBean", "getAllWithPagingForEmp");
             
   
         
        }
    }

    public Long getLoginedMinistrycode() {
        if (loginedMinistrycode == null) {
            try {
                loginedMinistrycode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (loginedMinistrycode == null) {
                loginedMinistrycode = 13L;
            }
        }
        return loginedMinistrycode;
    }
    /////////// Kamal Start

    public String goAddQualificationIntegration() {

        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());

        integrationBean.getHmBaseBeanFrom().put("empListBean", EmployeeListBean.getInstance());
        integrationBean.setNavgationCaseFrom("emplist");
        integrationBean.setBeanNameFrom("empListBean");
        integrationBean.setActionFrom("retFromIntigration");
        civilId =
                ((IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)getSelectedDTOS().get(0)).getCitizensResidentsDTO()).getCivilId();

        //            getQulAddIntegrationBean().setPrepareMethodName(BEAN_NAME + ".prePareMethodName");
        //            getQulAddIntegrationBean().setReturnMethodName(BEAN_NAME + ".returnMethodName");

        getQulAddIntegrationBean().setCivilId(civilId);
        //  getQulAddIntegrationBean().setDisableQulDateRegistration(false);
        getQulAddIntegrationBean().getKwtCitizenInfo(civilId);
        getQulAddIntegrationBean().setKwtCitizensResidentsDTO((IKwtCitizensResidentsDTO)getQulAddIntegrationBean().getPageDTO());
        return "AddQualificationIntegration";

    }

    public String retFromIntigration() {
        setSuccess(true);
        getHighlightedDTOS().clear();
        getHighlightedDTOS().add(getSelectedDTOS().get(0));
        return "emplist";
    }


    private QulAddIntegrationBean getQulAddIntegrationBean() {
        return (QulAddIntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qualificationAddHelperBean}").getValue(FacesContext.getCurrentInstance());

    }

    //////////kamal End

    public void setFilteredList(List allList) {
        this.filteredList = allList;
    }

    public List getFilteredList() {
        return filteredList;
    }


    public void setDisableCandidate(boolean disableCandidate) {
        this.disableCandidate = disableCandidate;
    }

    public boolean getDisableCandidate() {
        return disableCandidate;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setQulIntegrationBean(QulIntegrationListBean qulIntegrationBean) {
        this.qulIntegrationBean = qulIntegrationBean;
    }

    public QulIntegrationListBean getQulIntegrationBean() {
        return qulIntegrationBean;
    }

    //    private IEmpCandidatesDTO makeEmployeeDTO(IEntityKey key) throws DataBaseException, SharedApplicationException {
    //        IEmpCandidatesDTO empDTO;
    //        IPersonQualificationsDTO personQualificationsDTO = InfDTOFactory.createPersonQualificationsDTO();
    //        empDTO = EmpClientFactory.getEmpCandidatesClient().getByCndCodeAndSequnc(key);
    //        try {
    //            personQualificationsDTO =
    //                    (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getCentralEmpPersonQul(Long.valueOf(empDTO.getCitizensResidentsDTO().getCode().getKey()));
    //        } catch (NoResultException e) {
    //            e.printStackTrace();
    //        }
    //        ((IKwtCitizensResidentsDTO)empDTO.getCitizensResidentsDTO()).setPersonQualificationsDTOList(new ArrayList<IPersonQualificationsDTO>());
    //        if (personQualificationsDTO.getCode() != null) {
    //            ((IKwtCitizensResidentsDTO)empDTO.getCitizensResidentsDTO()).getPersonQualificationsDTOList().add(personQualificationsDTO);
    //        }
    //
    //        return empDTO;
    //    }

    public String gosuggestAndAdd() {

        //TO DO

        IEmpCandidatesDTO dto = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        boolean docStatus = dto.getDocumentsStatus();

        Long civilId = Long.valueOf(dto.getCitizensResidentsDTO().getCode().getKey());

        IEmpCandidatesClient client = (IEmpCandidatesClient)getClient();
        // obtain civilID and check for contract
        IHireTypesEntityKey hTypeEK = (IHireTypesEntityKey)dto.getHireTypesDTO().getCode();
        Long hireTypeCode = hTypeEK.getHirtypeCode();
        Long candidateCode=   ((IEmpCandidatesEntityKey)  dto.getCode()).getCandidateCode();   
        try {
            contract = client.checkEmployeeByExtraDataType(civilId, IEMPConstants.CONTRACT_TYPE,candidateCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!hireTypeCode.equals(2L) && contract) {
            IOrgMinExtraDataClient extraDataClient = OrgClientFactory.getOrgMinExtraDataClient();
            boolean returnStatus = false;
            try {
                 returnStatus=extraDataClient.checkExceptedMin(dto.getMinCode(),43L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(returnStatus){
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                                  "min_exepted_from_dewan_approve"));
                return null;
                }
            String bgtValue =
                fillBgtValue(((IWorkCentersEntityKey)dto.getWorkCentersDTO().getCode()).getWrkCode());
            boolean returnStatus1 = false;
            try {
                if (!bgtValue.equals("")) {
                 returnStatus1=extraDataClient.checkExceptedMin(dto.getMinCode(),43L,bgtValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(returnStatus1){
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                                  "bgt_expected_from_dwan_approve"));
                return null;
                }
            if (!docStatus) {

                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                              "can_Not_Complete_process_bec_doc"));
                return null;
            }

            else {

                try {
                    ContractEmployeesBean contEmployeesBean =
                        (ContractEmployeesBean)evaluateValueBinding("contractEmployeesBean");
                    //Ayman contEmployeesBean.setBckBtnNavigationCase("backToEmpSelection");
                    contEmployeesBean.setBckBtnNavigationCase("backToEmp");
                    contEmployeesBean.getSaveStateList().add(getHireStageCode());
                    contEmployeesBean.getSaveStateList().add(getSelectedDTOS().get(0));
                    contEmployeesBean.getSaveStateList().add(((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey());
                    contEmployeesBean.setBackActionMethodName(SELECTION_BEAN_NAME +
                                                              ".reloadDataForReviewListBeanContract");

                    dto = makeEmployeeDTO(dto.getCode());
                    contEmployeesBean.setPageDTO(dto);
                    contEmployeesBean.InitiateData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "conract";
            }

        }
        try {
            estana = client.checkEmployeeByExtraDataType(civilId, IEMPConstants.ESTANA_TYPE,candidateCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // if isEstana true goTo estana
     
        
        if (!hireTypeCode.equals(2L) && estana) {
            IOrgMinExtraDataClient extraDataClient = OrgClientFactory.getOrgMinExtraDataClient();
            boolean returnStatus = false;
            try {
                 returnStatus=extraDataClient.checkExceptedMin(dto.getMinCode(),44L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(returnStatus){
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                                  "min_exepted_from_dewan_approve"));
                return null;
                }
            String bgtValue =
                fillBgtValue(((IWorkCentersEntityKey)dto.getWorkCentersDTO().getCode()).getWrkCode());
            boolean returnStatus1 = false;
            try {
                if (!bgtValue.equals("")) {
                 returnStatus1=extraDataClient.checkExceptedMin(dto.getMinCode(),44L,bgtValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(returnStatus1){
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                                  "bgt_expected_from_dwan_approve"));
                return null;
                }
            if (!docStatus) {

                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                              "can_Not_Complete_process_bec_doc"));
                return null;
            }

            else {

                try {
                    EstanaListBean estanaListBean = (EstanaListBean)evaluateValueBinding("estanaListBean");
                    estanaListBean.setBckBtnNavigationCase("backToEmp");
                    estanaListBean.getSaveStateList().add(getHireStageCode());
                    estanaListBean.getSaveStateList().add(getSelectedDTOS().get(0));
                    IEmpCandidatesDTO cand = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
                    IHireTypesDTO hTDTO = (IHireTypesDTO)cand.getHireTypesDTO();
                    //      String hireTypeKey  = hTDTO.getParentCode().getKey();

                    estanaListBean.getSaveStateList().add(((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey());
                    estanaListBean.setBackActionMethodName(SELECTION_BEAN_NAME + ".reloadDataForReviewListBeanInNeed");

                    dto = makeEmployeeDTO(dto.getCode());
                    estanaListBean.setPageDTO(dto);
                    estanaListBean.InitiateData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "empInNeed";
            }
        }


        else {
            // from Twzef markzy
            IHireTypesDTO hTDTO = (IHireTypesDTO)dto.getHireTypesDTO();


            // getHireStage Code from Selected DTO to go required page

            IHireStagesDTO hireStageDTO = (IHireStagesDTO)dto.getHireStagesDTO();
            IHireStagesEntityKey hStageEntitykey = (IHireStagesEntityKey)hireStageDTO.getCode();
            hireStageCode = hStageEntitykey.getHirstageCode();


            if (hireTypeCode.equals(2L)) {
                if (!docStatus) {

                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                                  "can_Not_Complete_process_bec_doc"));
                    return null;
                }

                else {
                    return redirectToPageByStageCode(hireStageCode);

                }


            }


            if (hTDTO.getParentHireType() != null) {

                if (!docStatus) {

                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                                  "can_Not_Complete_process_bec_doc"));
                    return null;
                }

                else {
                    IHireTypesDTO parentHTDTO = hTDTO.getParentHireType();
                    IHireTypesEntityKey parentHtek = (IHireTypesEntityKey)parentHTDTO.getCode();
                    Long hireTypeCodeFromParent = parentHtek.getHirtypeCode();

                    if (hireTypeCodeFromParent.equals(2L)) {


                        return redirectToPageByStageCode(hireStageCode);
                    }
                }
            }


            System.out.println("============ not central , contract or estana  show error MSG============ ");
            // validation message for not central and contract or needed


            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "messageForCenteralContract"));


            return null;
        }
    }


    //Long.valueOf(IEMPConstants.HIRE_STAGE_COMPLETING_INFO_INPROGRESS);

    public String viewReviewListAction() {
        ReviewListBean1 reviewListBean = (ReviewListBean1)evaluateValueBinding("reviewMinListBean1");
        IEmpCandidatesDTO empDTO;
        try {
            empDTO = makeEmployeeDTO(getSelectedDTOS().get(0).getCode());

            reviewListBean.setPageDTO(empDTO);
            if (empDTO.getEmpCandidateExtraDataList() != null && empDTO.getEmpCandidateExtraDataList().size() > 0) {
                ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
                ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
                ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
                ISalElementGuidesDTO caderDTO = SalDTOFactory.createSalElementGuidesDTO();
                try {
                    Long civilId = Long.valueOf(empDTO.getCitizensResidentsDTO().getCode().getKey());
                    //                    Long civilId =((IEmployeesEntityKey)empDTO.getCode()).getCivilId();
                    for (IBasicDTO list1 : empDTO.getEmpCandidateExtraDataList()) {
                        IEmpCandidateExtraDataDTO list = (IEmpCandidateExtraDataDTO)list1;
                        // where suggestedCader equal 12L get suggestedcader , suggestedGroup and suggestedRaise depend on suggestedRaise value in extraData table
                        if (((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_CADER_SUGGESTED)) {
                            //raise
                            reviewListBean.setSuggestedRaiseCode(list.getValue());
                            if (reviewListBean.getSuggestedRaiseCode() != null) {
                                raiseDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.valueOf(reviewListBean.getSuggestedRaiseCode()));
                                //reviewListBean.getRaiseList().add(raiseDTO);
                                reviewListBean.setSuggestedRaiseName(String.valueOf(raiseDTO.getCountGuide()));
                            }
                            //degree
                            if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                                reviewListBean.setSuggestedDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getSuggestedDegreeCode() != null) {
                                degreeDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedDegreeCode());
                                reviewListBean.setSuggestedDegreeName(degreeDTO.getName());
                            }
                            //group
                            if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                                reviewListBean.setSuggestedGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getSuggestedGroupCode() != null) {
                                groupDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedGroupCode());
                                //reviewListBean.getGroupList().add(groupDTO);
                                reviewListBean.setSuggestedGroupName(groupDTO.getName());
                            }
                            //Cader
                            if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                                reviewListBean.setSuggestedCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                                if (reviewListBean.getSuggestedCaderCode() != null) {
                                    caderDTO =
                                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedCaderCode());
                                    //reviewListBean.getCaderList().add(caderDTO);
                                    reviewListBean.setSuggestedCaderName(groupDTO.getParentObject().getName());
                                }
                            }
                            // where acceptedCader equal 14L get acceptedcader , acceptedGroup and acceptedRaise depend on acceptedRaise value in extraData table
                        } else if (((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_CADER_ACCEPTED)) {
                            //raise
                            reviewListBean.setAcceptedRaiseCode(Long.valueOf(list.getValue()));
                            if (reviewListBean.getAcceptedRaiseCode() != null) {
                                raiseDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getAcceptedRaiseCode());
                                //reviewListBean.getRaiseList().add(raiseDTO);
                                reviewListBean.setAcceptedRaiseName(String.valueOf(raiseDTO.getCountGuide()));
                            }
                            //degree
                            if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                                reviewListBean.setAcceptedDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getAcceptedDegreeCode() != null) {
                                degreeDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getAcceptedDegreeCode());
                                reviewListBean.setAcceptedDegreeName(degreeDTO.getName());
                            }
                            //group
                            if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                                reviewListBean.setAcceptedGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getAcceptedGroupCode() != null) {
                                groupDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getAcceptedGroupCode());
                                // reviewListBean.getGroupList().add(groupDTO);
                                reviewListBean.setAcceptedGroupName(groupDTO.getName());
                            }
                            //Cader
                            if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                                reviewListBean.setAcceptedCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                                if (reviewListBean.getAcceptedCaderCode() != null) {
                                    caderDTO =
                                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getAcceptedCaderCode());
                                    //reviewListBean.getCaderList().add(caderDTO);
                                    reviewListBean.setAcceptedCaderName(groupDTO.getParentObject().getName());
                                }
                            }
                            // where suggestedJob equal 4L get suggestedJobCode , suggestedJobName  depend on suggestedJobCode value in extraData table
                        } else if (((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_JOB_SUGGESTED)) {
                            //jibDTO
                            reviewListBean.setSuggestedJobCode(list.getValue());
                            IJobsEntityKey entitKey = JobEntityKeyFactory.createJobsEntityKey(list.getValue());
                            IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                            //reviewListBean.getJobList().add(jobDTO);
                            reviewListBean.setSuggestedJobValue(jobDTO.getName());
                            reviewListBean.setJobKey(jobDTO.getJobKey());
                            // where acceptedJob equal 4L get acceptedJobCode , acceptedJobName  depend on acceptedJobCode value in extraData table
                        } else if (((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_JOB_ACCEPTED)) {
                            //jobDTO
                            reviewListBean.setAcceptedJobCode(list.getValue());
                            IJobsEntityKey entitKey = JobEntityKeyFactory.createJobsEntityKey(list.getValue());
                            IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                            //reviewListBean.getJobList().add(jobDTO);
                            reviewListBean.setJobKey(jobDTO.getJobKey());
                            reviewListBean.setAcceptedJobValue(jobDTO.getName());
                            // where acceptedBGT equal 15L get acceptedBgtTypesDTO depend on BgtTypesCode value in extraData table
                        } else if (((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.JOB_FROM_MIN)) {
                            if(list.getValue().equals("1")){
                            reviewListBean.setJobFromMin(true);
                            }else{
                                    reviewListBean.setJobFromMin(false);      
                                }
                        } else if (((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_BGT_TYPE)) {
                            //BgtTypesDTO
                            IBgtTypesEntityKey entitKey =
                                BgtEntityKeyFactory.createBgtTypesEntityKey(Long.valueOf(list.getValue()));
                            IBgtTypesDTO bgtTypesDTO =
                                (IBgtTypesDTO)BgtClientFactory.getBgtTypesClient().getById(entitKey);
                            //reviewListBean.getJobList().add(jobDTO);
                            reviewListBean.setBgtTypesDTO(bgtTypesDTO);

                        } else if (((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_MIN_NOTES)) {

                            ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).getEmpExtraDataValueDTO().setMinistryNotes(list.getValue());

                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + getHireStageCode());

            // the main point used to show or hide parts in page
            reviewListBean.showViewByStage(getHireStageCode());
            //reviewListBean.setBckBtnNavigationCase("selectionMinListPage");
            reviewListBean.setBckBtnNavigationCase("backToEmp");
            reviewListBean.getSaveStateList().add(getHireStageCode());
            reviewListBean.getSaveStateList().add(getSelectedDTOS().get(0));
            reviewListBean.getSaveStateList().add(((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey());
            reviewListBean.setBackActionMethodName(SELECTION_BEAN_NAME + ".reloadDataForReviewListBean");
            //            String temp =empDTO.getEmpExtraDataValueDTO().getSuggestedJobCode();
            //            IJobsEntityKey entitKey = null;
            //            IJobsDTO jobDTO = null;
            //            if(temp != null && !temp.equals(" ")){
            //                entitKey = JobEntityKeyFactory.createJobsEntityKey(temp);
            //                jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
            //                reviewListBean.setJobNameForMin(jobDTO.getName());
            //            }

            IWorkCentersDTO temp1 = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
            if (temp1 != null && !temp1.equals(" ")) {
                reviewListBean.setWorkCenterName(empDTO.getWorkCentersDTO().getName());
            }
            reviewListBean.setPageBeanName(SELECTION_BEAN_NAME);
            // reviewListBean.budgetTypeController(empDTO.getBgtTypesDTO(),getSelectedStageId());
            reviewListBean.salaryDataSectionController(empDTO.isHasExperience(), getHireStageCode().toString());
            //reviewListBean.setSelectedMinistery(getSelectedMinistry());
            // reviewListBean.populateSalariesData();

            if (getHireStageCode().equals(HireStageConst.HIRE_STAGE_JOB_NAME_REQUIRED)) {
                IWorkCentersDTO wrkCenter = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
                IWorkCentersEntityKey key = (IWorkCentersEntityKey)wrkCenter.getCode();
                reviewListBean.setMinWorkCenter(key.getWrkCode());
            }
            return "review";
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e);
        } catch (DataBaseException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e);
        }
        return "";

    }


    private IEmpCandidatesDTO makeEmployeeDTO(IEntityKey key) throws DataBaseException, SharedApplicationException {
        IEmpCandidatesDTO empDTO;
        empDTO = (IEmpCandidatesDTO)EmpClientFactory.getEmpCandidatesClient().getByCndCodeAndSequnc(key);

        try {
            IPersonQualificationsDTO personQualificationsDTO =
                (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getCurrentCentralEmpPersonQul(Long.valueOf(empDTO.getCitizensResidentsDTO().getCode().getKey()));
            ((IKwtCitizensResidentsDTO)empDTO.getCitizensResidentsDTO()).setPersonQualificationsDTOList(new ArrayList<IPersonQualificationsDTO>());
            ((IKwtCitizensResidentsDTO)empDTO.getCitizensResidentsDTO()).getPersonQualificationsDTOList().add(personQualificationsDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return empDTO;
    }


    public void setHireStageCode(Long hireStageCode) {
        this.hireStageCode = hireStageCode;
    }

    public Long getHireStageCode() {
        return hireStageCode;
    }

    public void reloadDataForReviewListBean() {
        ReviewListBean1 reviewListBean = (ReviewListBean1)evaluateValueBinding("reviewMinListBean1");
        //setSelectedStageId();
        setHireStageCode((Long)reviewListBean.getSaveStateList().get(0));
        IEmpCandidatesDTO backDTO = (IEmpCandidatesDTO)reviewListBean.getSaveStateList().get(1);
        if (reviewListBean.getSaveStateList().size() > 2) {
            if (reviewListBean.getSaveStateList().get(2).toString().equals("")) {
                backDTO.setHireTypeKey("0");
            } else {
                backDTO.setHireTypeKey(reviewListBean.getSaveStateList().get(2).toString());
            }
        }
        setPageDTO(backDTO);
        initForPage();

        setSelectedPageDTO(backDTO);
        //
        //  setPageId((String)reviewListBean.getSaveStateList().get(1));
        //  setSelectedMinistry((String)reviewListBean.getSaveStateList().get(2));
    }


    public String redirectToPageByStageCode(Long stageCode) {
        go = viewReviewListAction();
        return go;
    }


    public String goAdd() {
        //        String returnString = super.goAdd();

        //TODO locking code
        if (!lock()) {
            return null;
        }
        AddCandidateMaintainBean maintainBean =
            (AddCandidateMaintainBean)evaluateValueBinding("addCandidateMaintainBean");
        maintainBean.setBackHireType(((IEmpCandidatesDTO)this.getPageDTO()).getHireTypesDTO().getCode().getKey());
        maintainBean.setPageDTO(this.getPageDTO());
        maintainBean.getInstance();
        MainDataCandidate mainDataCandidate =
            (MainDataCandidate)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{mainDataCandidateBean}").getValue(FacesContext.getCurrentInstance());
        mainDataCandidate.reSetData(null);
        if (workDataListBean.getKwtWorkDataDTOList() != null)
            workDataListBean.getKwtWorkDataDTOList().clear();
        //TODO locking code
        // propagate the locking status to the maintain bean
        // because it will be responsible to unlock the edited item if it was locked
        maintainBean.setLastLockingAction(getLastLockingAction());
        return getAddNavigationCase();
    }


    public void reloadDataForReviewListBeanContract() {
        ContractEmployeesBean reviewListBean = (ContractEmployeesBean)evaluateValueBinding("contractEmployeesBean");
        //setSelectedStageId();
        setHireStageCode((Long)reviewListBean.getSaveStateList().get(0));
        IEmpCandidatesDTO backDTO = (IEmpCandidatesDTO)reviewListBean.getSaveStateList().get(1);
        if (reviewListBean.getSaveStateList().size() > 2) {
            if (reviewListBean.getSaveStateList().get(2).toString() == "") {
                backDTO.setHireTypeKey("0");
            } else {
                backDTO.setHireTypeKey(reviewListBean.getSaveStateList().get(2).toString());
            }
        }
        setPageDTO(backDTO);
        initForPage();
        setSelectedPageDTO(backDTO);
        //
        //  setPageId((String)reviewListBean.getSaveStateList().get(1));
        //  setSelectedMinistry((String)reviewListBean.getSaveStateList().get(2));
    }

    public void reloadDataForReviewListBeanInNeed() {
        EstanaListBean reviewListBean = (EstanaListBean)evaluateValueBinding("estanaListBean");
        //setSelectedStageId();
        setHireStageCode((Long)reviewListBean.getSaveStateList().get(0));
        IEmpCandidatesDTO backDTO = (IEmpCandidatesDTO)reviewListBean.getSaveStateList().get(1);
        if (reviewListBean.getSaveStateList().size() > 2) {
            if (reviewListBean.getSaveStateList().get(2).toString() == "") {
                backDTO.setHireTypeKey("0");
            } else {
                backDTO.setHireTypeKey(reviewListBean.getSaveStateList().get(2).toString());
            }
        }
        setPageDTO(backDTO);
        initForPage();
        setSelectedPageDTO(backDTO);
        //
        //  setPageId((String)reviewListBean.getSaveStateList().get(1));
        //  setSelectedMinistry((String)reviewListBean.getSaveStateList().get(2));
    }

    public void initDT() {
       
        if(((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey() == null || ((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey().equals("0")){
            if(((IEmpCandidatesDTO)getPageDTO()).getHireTypesDTO() ==null ){
                IHireTypesEntityKey entityKey = EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong("0"));
                IHireTypesDTO hireTypesDTO = EmpDTOFactory.createHireTypesDTO();
                hireTypesDTO.setCode(entityKey);
                ((IEmpCandidatesDTO)getPageDTO()).setHireTypesDTO(hireTypesDTO); 
            }
        }
                searchByMainHireType();
        
    
   
    }

    public String viewDocuments() {
        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
            ViewDocumentsBean viewDocuments = ViewDocumentsBean.getInstance();
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
            List<IEmpCandidateDocumentsDTO>  empCandidateDocumentsList=null;
            try {
            IEmpCandidateDocumentsClient  client=EmpClientFactory.getEmpCandidateDocumentsClient();
              empCandidateDocumentsList=  client.getByCandCodeNew(empCandidatesDTO.getCode());
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            empCandidatesDTO.setEmpCandidateDocumentsList((List)empCandidateDocumentsList);
            viewDocuments.setPageDTO(empCandidatesDTO);
            viewDocuments.setBckBtnNavigationCase("backToEmp");
            viewDocuments.getSaveStateList().add(getHireStageCode());
            viewDocuments.getSaveStateList().add(getSelectedDTOS().get(0));
            viewDocuments.getSaveStateList().add(((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey());
            viewDocuments.setBackActionMethodName(BEAN_NAME + ".reloadDataFromViewDocuments");
        }
        return "viewDocumentsPage";
    }

    public void reloadDataFromViewDocuments() {
        ViewDocumentsBean viewDocuments = ViewDocumentsBean.getInstance();
        setHireStageCode((Long)viewDocuments.getSaveStateList().get(0));
        IEmpCandidatesDTO backDTO = (IEmpCandidatesDTO)viewDocuments.getSaveStateList().get(1);
        if (viewDocuments.getSaveStateList().size() > 2) {
            if (viewDocuments.getSaveStateList().get(2).toString() == "") {
                backDTO.setHireTypeKey("0");
            } else {
                backDTO.setHireTypeKey(viewDocuments.getSaveStateList().get(2).toString());
            }
        }
        setPageDTO(backDTO);
        initForPage();
        setSelectedPageDTO(backDTO);
    }

    public void clearData() {
        if (isSearchMode() == false) {
            setEmpEmployeesSearchDTO(EmpDTOFactory.createEmpEmployeeSearchDTO());
            setSearchEmpName("");
        }

    }

    public String goAddQual() {
        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
        if (!maintainBean.isEnableEditQulAndExper()) {
            //maintainBean.setBundleMsg(getSharedUtil().messageLocator(BUNDLE_NAME,"unable_add_ExperiencesOrQual"));
            if (getSharedUtil().getErrMsgValue().equals(null) || getSharedUtil().getErrMsgValue().equals(""))
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                              "unable_add_ExperiencesOrQual"));
            return null;

        }
        qulIntegrationBean = new QulIntegrationListBean();
        String add = qulIntegrationBean.goAdd();
        return add;
    }

    public void setSelectedHireType(String selectedHireType) {
        this.selectedHireType = selectedHireType;
    }

    public String getSelectedHireType() {
        return selectedHireType;
    }

    public void setSelectedBackHireType(String selectedBackHireType) {
        this.selectedBackHireType = selectedBackHireType;
    }

    public String getSelectedBackHireType() {
        return selectedBackHireType;
    }

    public void setEstana(boolean estana) {
        this.estana = estana;
    }

    public boolean isEstana() {
        return estana;
    }


    //    public void beforeGoSuggestAndAdd() {
    //
    //        IEmpCandidatesDTO dto = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
    //        boolean docStatus = dto.getDocumentsStatus();
    //        //  boolean procedureStatus=dto.getProceduresStatus();
    //
    //
    //        if (docStatus) {
    //            return gosuggestAndAdd();
    //        }
    //
    //        else {
    //
    //            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
    //                                                                          "can_Not_Complete_process_bec_doc"));
    //
    //            return null;
    //        }
    //
    //
    //    }

    public void openReport() {
        IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        reportUrl="rep=9717&_paramsP_CIVIL_ID="+ empCandidatesDTO.getCitizensResidentsDTO().getCode().getKey();
    
        String stringcaller = "openReportWindow('reportUrl')";
        setJavaScriptCaller(stringcaller);

    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setEnableRep(boolean enableRep) {
        this.enableRep = enableRep;
    }

    public boolean isEnableRep() {
        if(getSelectedDTOS()!=null && getSelectedDTOS().size()!=0){
        IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        if(empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals("15")||
        empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals("5") ||
        empCandidatesDTO.getHireStagesDTO().getCode().getKey().equals("2")){
            return true;
        }else{
            return false;
        }
        }else{
            return false;
        }
    }
}

