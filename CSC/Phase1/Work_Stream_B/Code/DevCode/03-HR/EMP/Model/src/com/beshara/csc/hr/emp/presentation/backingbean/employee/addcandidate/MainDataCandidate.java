package com.beshara.csc.hr.emp.presentation.backingbean.employee.addcandidate;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.client.IBgtProgramsClient;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.sal.business.client.ISalElementGuidesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.ICountriesDTO;
import com.beshara.csc.inf.business.dto.IGenderCountryDTO;
import com.beshara.csc.inf.business.dto.IGenderTypesDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IMaritalStatusDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobSearchCriteriaDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.integration.presentation.backingbean.search.JobFilter;
import com.beshara.csc.nl.org.business.client.IOrgMinExtraDataClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.org.integration.presentation.backingbean.workcenters.WorkCentersHelperBean;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.listqul.QulIntegrationListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class MainDataCandidate extends ManyToManyDetailsMaintain {
    private final Long DEWAN_CODE = new Long(13);
    private static final String EMP_RESOURCES = "com.beshara.csc.hr.emp.presentation.resources.emp";
    public static final String OTHER_TYPE = "67";

    private String civilId;
    private boolean empHired = false;
    private boolean empCandidate = false;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private boolean disabledContractType = false;

    private boolean empNationality = false;

    
    private String candidateName;
    private String genderTypeName;
    private String maritalStatus;
    private String nationality;
    private String fileNo;
    private String virtual_value = "";
    private String bgtProgName;
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO;
    private String selectedHireType;
  
   
    private String workCenterName;
    private String workCenterKey;
    private String titleFlageOfDiv;
    private boolean workCenterMode = false;
    private boolean renderLovDiv = false;
    private List wrkCenterList = new ArrayList();
    private boolean invalidCivilID = false;
    private boolean NonDBCivilID = false;
    private boolean invalidCivilWithHireType = false;
    private boolean searchJobMode = false;
    private List jobList = new ArrayList();
    private TreeDivBean treeDivBean = (TreeDivBean)evaluateValueBinding("treeDivBean");
    TreeDivBean treedivBean;
    private IBasicClient centerClient;
    private List<IBasicDTO> budgetTypeList = new ArrayList<IBasicDTO>();
    private boolean jobMode;
    private String caderName;
    private String contracts;
    private String otherType;
    private String estanaType;
    private IWorkCentersDTO workCentersDTO;

    WorkCentersHelperBean wcIntegrationBean =
        new WorkCentersHelperBean("mainDataCandidateBean", "integrationDiv1", false, false, true, true, null);

    private List<SalElementGuidesDTO> degreesList;
    private List<IBasicDTO> raisesCount;

    private List caderList = new ArrayList();
    private List groupList = new ArrayList();
    private List eqGroupList = new ArrayList();
    private List budgetList = new ArrayList();

    private String caderCode;
    private String groupCode;
    private String degreeCode;
    private String raiseCode;
    private String contractType;
    private String wrkCenterName;
    private String jobName;
    private String jobCode;
    private String bgtTypeKey = getVirtualConstValue();
    AddCandidateMaintainBean addCandidateMaintainBean =
        (AddCandidateMaintainBean)evaluateValueBinding("addCandidateMaintainBean");

    public static final String BEAN_NAME = "mainDataCandidateBean";
    public static final String METHOD_NAME_ADD_SELECTED_JOBS = "mainDataCandidateBean.addSelectedJobs";
    private JobFilter jobFilter = new JobFilter();
    private IJobsDTO jobDTO;
    private boolean renderEmpFilNumRedundant = false;
    private boolean renderCandFilNumRedundant = false;
    

   
    private boolean renderedErrorMsgHirType = false;
    private String hireTypeFirstParent;
    // private boolean enableEditQulAndExper = true;
    private boolean errorCanDegree = false;
    
    private boolean errorJobCondition;
    private boolean dataNotcomplete;
    private String dataNotcompleteErrorMSG;
    private boolean invalidNextYear;
    private Date dateOfNextRaise;
    private boolean renderErrorHireDate = false;
    private Date nextYear;
    private Date previousYear;
    private boolean kuwaitCitizen;
    private  Date hireDate;
    
    private boolean minExcepted;
    
    private List caderListEq = new ArrayList();
    private List groupListEq = new ArrayList();
    private List raiseList = new ArrayList();
    private List degreeList = new ArrayList();
    private Long caderCodeEq;
    private boolean validCond = true;
    private Long degreeCodeEq;
    private String raiseCodeEq;
    private Long groupCodeEq;
    private ISalElementGuidesDTO raiseDTO;
    private String totalRewardAccepted = "";
    public MainDataCandidate() {
        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setClient(EmpClientFactory.getEmpCandidatesClient());
        setContent1Div("module_tabs_cont");
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setUsingPaging(true);
        if (getLovBaseBean().isUsingPaging()) {
            getLovBaseBean().generatePagingRequestDTO("mainDataCandidateBean", "initMainDataBean");
        } else {
            getLovBaseBean().setMyTableData(new ArrayList());
        }
        setCustomDiv1("extraTooWideDiv");
        initJobFilter();
    }

    private void initJobFilter() {
        jobFilter.setOkButtonMethod(METHOD_NAME_ADD_SELECTED_JOBS);
        jobFilter.setSingleSelection(true);
        jobFilter.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());

    }

    public void showJobDiv() {
        List<String> excludedJobCodeList = new ArrayList<String>();
        if (this.getJobCode() != null) {
            excludedJobCodeList.add(this.getJobCode());
        }
        if (excludedJobCodeList.size() > 0) {
            jobFilter.setExcludedJobList(excludedJobCodeList);
        }
        try {
            jobFilter.cancelSearch();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        jobFilter.setResetMode(false);
        jobFilter.setLoadSecAccessibleJobsOnly(true);
        jobFilter.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
    }

    public void addSelectedJobs() throws DataBaseException, SharedApplicationException {
        errorJobCondition = false;
        if (jobFilter != null && jobFilter.getSelectedDTOS() != null && !jobFilter.getSelectedDTOS().isEmpty()) {
            // to do check condition
            String jobCode = jobFilter.getSelectedDTOS().get(0).getCode().getKey();
            boolean checkJobCond = JobClientFactory.getJobsClient().checkJobConditions(Long.valueOf(civilId), jobCode);
            this.setJobCode(jobFilter.getSelectedDTOS().get(0).getCode().getKey());
            setJobDTO((IJobsDTO)jobFilter.getSelectedDTOS().get(0));
            this.setJobName(jobFilter.getSelectedDTOS().get(0).getName());

            if (!checkJobCond) {
                errorJobCondition = true;
                this.setJobCode(null);
                setJobDTO(null);
                this.setJobName(jobFilter.getSelectedDTOS().get(0).getName());
            }

        }
    }

    public PagingResponseDTO initMainDataBean(PagingRequestDTO request) {
        request = null;
        return new PagingResponseDTO(new ArrayList());
    }

    public void openWorkCentersIntegDiv() {
        initIntegration();
        IMinistriesDTO dto = null;
        try {
            dto =
(IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(OrgEntityKeyFactory.createMinistriesEntityKey(CSCSecurityInfoHelper.getLoggedInMinistryCode()));
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        wcIntegrationBean.setMinName(dto.getName());
        wcIntegrationBean.setCatName(dto.getCatsDTO().getName());
        wcIntegrationBean.setSelectedMinCode(dto.getCode().getKey().toString());
        wcIntegrationBean.setSelectedCatCode(dto.getCatsDTO().getCode().getKey());
        wcIntegrationBean.setSingleSelectionFlag(true);
        //        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.integrationDiv1);");
    }

    public void initIntegration() {

        wcIntegrationBean.getOkCommandButton().setReRender("employeeMainDataPanel,integrationDiv1 , workCenterPanel,workCenterName, bgtNamePanel,bgtInputId,jobDataPanel");
        wcIntegrationBean.setReturnMethodName("mainDataCandidateBean" + ".linkWorkCenter");
        //            wcIntegrationBean.setPreOpenMethodName("mainDataCandidateBean" + ".openSearchWorkCenter");
        wcIntegrationBean.setSingleSelectionFlag(true);
        if (wcIntegrationBean.getSelectedDTOSList() != null && wcIntegrationBean.getSelectedDTOSList().size() > 0) {
            wcIntegrationBean.getSelectedDTOSList().clear();
        }
    }


    public void linkWorkCenter() {

        if (wcIntegrationBean.getSelectedDTOSList().size() > 1) {
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(EMP_RESOURCES, "canNotUseMoreThanOne"));

        }

        else {
            if (wcIntegrationBean.getSelectedDTOSList() != null &&
                wcIntegrationBean.getSelectedDTOSList().size() != 0) {
                workCenterName = wcIntegrationBean.getSelectedDTOSList().get(0).getName();
                workCenterKey =
                        ((IWorkCentersEntityKey)wcIntegrationBean.getSelectedDTOSList().get(0).getCode()).getWrkCode();
                setWorkCentersDTO((IWorkCentersDTO)wcIntegrationBean.getSelectedDTOSList().get(0));
            }
        }
        fillBgtProgName();
    }

    //    public void openSearchWorkCenter() {
    //        try {
    //            wcIntegrationBean.cancelSearch();
    //        } catch (DataBaseException e) {
    //            e.printStackTrace();
    //        }
    //        wcIntegrationBean.setSingleSelectionFlag(true);
    //    }


    public void showCaderListOfValuesDiv() {
        titleFlageOfDiv = "cader";
        getLovBaseBean().setMyTableData(new ArrayList());
        getLovBaseBean().getPagingBean().updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 0));
        getLovBaseBean().setCurrentPageIndex(1);
        getLovBaseBean().setOldPageIndex(0);
        setWorkCenterMode(false);
        setJobMode(false);
        setRenderLovDiv(true);
        if (getLovBaseBean().isUsingPaging()) {
            getLovBaseBean().generatePagingRequestDTO("mainDataCandidateBean", "showCaderListOfValuesDivWithPaging");
        } else {
            try {
                setCaderList(new ArrayList());
                getLovBaseBean().setMyTableData(getCaderList());
                if (getLovBaseBean().getMyDataTable() != null)
                    getLovBaseBean().getMyDataTable().setFirst(0);

            } catch (Exception e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            }
        }
        getLovBaseBean().getOkCommandButton().setReRender("employeeCaderPanel,JobGrpMenu");
        getLovBaseBean().setReturnMethodName("mainDataCandidateBean.caderValue");
        getLovBaseBean().setSearchMethod("mainDataCandidateBean.searchCader");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("mainDataCandidateBean.cancelSearchCaderLovDiv");
        getLovBaseBean().getMyDataTable().setFirst(0);
    }

    public void cancelSearchCaderLovDiv() {
        showCaderListOfValuesDiv();
    }

    public void caderValue() {
        if (getLovBaseBean().getSelectedDTOS() != null && getLovBaseBean().getSelectedDTOS().get(0) != null &&
            getLovBaseBean().getSelectedDTOS().get(0).getCode() != null) {
            try {
                setCaderCode(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString());
                setCaderName(getLovBaseBean().getSelectedDTOS().get(0).getName());
                resetSelectedJobData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void resetSelectedJobData() {

    }

    public void searchCader(Object searchType, Object searchQuery) {
        if (searchQuery != null && !searchQuery.toString().equals("") && searchType != null &&
            !searchType.equals("")) {
            getLovBaseBean().setSearchMode(true);

            System.out.println("searchQuery" + searchQuery);
            if (getLovBaseBean().isUsingPaging()) {

                String[] searchData = new String[2];
                searchData[0] = searchQuery.toString();
                searchData[1] = searchType.toString();

                getLovBaseBean().generatePagingRequestDTO("mainDataCandidateBean", "searchCaderWithPaging");
                getLovBaseBean().getPagingRequestDTO().setParams(searchData);
                getLovBaseBean().resetPageIndex();

            } else {
                try {
                    ISalElementGuidesClient salElementGuidesClient = SalClientFactory.getSalElementGuidesClient();
                    if (searchType.toString().equals("0")) {
                        getLovBaseBean().setMyTableData(salElementGuidesClient.searchPayrollByCode(Long.valueOf(searchQuery.toString())));
                    } else if (searchType.toString().equals("1"))
                        getLovBaseBean().setMyTableData(salElementGuidesClient.searchPayrollsByName(searchQuery.toString()));
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                    getLovBaseBean().setMyTableData(new ArrayList());
                } catch (DataBaseException e) {
                    e.printStackTrace();
                    getLovBaseBean().setMyTableData(new ArrayList());
                } catch (Exception e) {
                    getLovBaseBean().setMyTableData(new ArrayList());
                    e.printStackTrace();
                }
                getLovBaseBean().getMyDataTable().setFirst(0);
            }
        }

    }

    public PagingResponseDTO showCaderListOfValuesDivWithPaging(PagingRequestDTO request) {
        request = null;
        return new PagingResponseDTO(getCaderList());
    }

    public PagingResponseDTO searchCaderWithPaging(PagingRequestDTO request) {

        String searchQury = request.getParams()[0].toString();
        String searchType = request.getParams()[1].toString();
        try {

            ISalElementGuidesClient salElementGuidesClient = SalClientFactory.getSalElementGuidesClient();
            if (searchType.equals("0")) {
                return new PagingResponseDTO(salElementGuidesClient.searchByCaderCodeAndMinCode(getMinistryCode(),
                                                                                                Long.valueOf(searchQury)));
            } else if (searchType.equals("1"))
                return new PagingResponseDTO(salElementGuidesClient.searchByCaderNameAndMinCode(getMinistryCode(),
                                                                                                searchQury));
            return new PagingResponseDTO(null);
        } catch (Exception e) {
            e.printStackTrace();
            return new PagingResponseDTO(new ArrayList());
        }
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        app.setManyToMany(true);
        app.setShowNavigation(true);
        app.setShowsteps(true);
        app.setShowCustomDiv1(true);
        app.setShowCustomDiv2(true);
        app.setShowIntegrationDiv1(true);
        app.setShowbar(false);
        return app;
    }

    public Long getLoggedInMinistry() {
        return CSCSecurityInfoHelper.getLoggedInMinistryCode() == null ? DEWAN_CODE :
               CSCSecurityInfoHelper.getLoggedInMinistryCode();
    }


    private boolean checkAboutCitizen(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        if (kwtCitizensResidentsDTO.getFirstName() == null || kwtCitizensResidentsDTO.getFirstName().equals("")) {
            setDataNotcomplete(true);
            setDataNotcompleteErrorMSG(getSharedUtil().messageLocator(EMP_RESOURCES, "first_name_not_found"));
            return false;
        }
        if (kwtCitizensResidentsDTO.getGenderTypesDTO() == null) {
            setDataNotcomplete(true);
            setDataNotcompleteErrorMSG(getSharedUtil().messageLocator(EMP_RESOURCES, "gender_not_found"));
            return false;
        }
        if (kwtCitizensResidentsDTO.getNationality() == null) {
            setDataNotcomplete(true);
            setDataNotcompleteErrorMSG(getSharedUtil().messageLocator(EMP_RESOURCES, "nationality_not_found"));
            return false;
        }
        if (kwtCitizensResidentsDTO.getNationality() == 0 &&
            (kwtCitizensResidentsDTO.getNonStatus() == null || kwtCitizensResidentsDTO.getNonStatus() == 1 ||
             kwtCitizensResidentsDTO.getNonStatus() == 3)) {
            setEmpNationality(true);
            return false;
        }
        return true;
    }

    public void filterByCivilId() {
        IBasicDTO empDTO = null;
        boolean fillEmp = false;

        if (this.getCivilId() != null) {
            try {
                kwtCitizensResidentsDTO = InfClientFactory.getKwtCitizensResidentsClient().getPersonInfo(Long.valueOf(civilId));

            } catch (Exception e) {
                validCivilId = false;
                civilExist = false;
                empCandidate = false;
                empHired = false;
                empNationality = false;
                System.out.println("civilExist ? >>>>>>>>>>>>>>>>>>>>" + civilExist);
                System.out.println("validCivilId ? >>>>>>>>>>>>>>>>>>>>" + validCivilId);
                return;
            }
            if (kwtCitizensResidentsDTO != null) {
                boolean empEndServiceflag = false;
                validCivilId = true;
                civilExist = true;
                empHired = false;
                empNationality = false;
                empCandidate = false;
                dataNotcomplete = false;
                if (!checkAboutCitizen(kwtCitizensResidentsDTO)) {
                    civilExist = false;
                    return;
                }
                if (civilExist) {
                    try {
                        empDTO = EmpClientFactory.getEmployeesClient().getEmpForMov(new Long(civilId), -1L);
                    } catch (SharedApplicationException e) {
                        if (e instanceof ItemNotFoundException) {
                            empHired = false;
                            civilExist = false;
                            System.out.println("empHired ? >>>>>>>>>>>>>>>>>>>>" + empHired);
                        } else {
                            System.out.println("main data candidate exception raised :" + e.getMessage());
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println("main data candidate exception raised :" + e.getMessage());
                        return;
                    }
                    if (empDTO != null) {
                        if (((IEmployeesDTO)empDTO).getHireStatusDTO().getCode().getKey().equals(String.valueOf(IEMPConstant.EMP_STATUS_END_SERVICE))) {
                            setSelectedHireType(String.valueOf(IEMPConstants.EMP_HIRE_TYPE_RE_EMPLOYEE));

                            setHireTypeCodeOnChange();
                            empEndServiceflag = true;
                            empHired = false;

                            civilExist = true;
                        } else {
                            empHired = true;

                            civilExist = false;
                            System.out.println("empHired ? >>>>>>>>>>>>>>>>>>>>" + empHired);
                            return;
                        }


                        if (((IEmployeesDTO)empDTO).getHireStatusDTO().getCode().getKey().equals(String.valueOf(IEMPConstant.EMP_STATUS_END_SERVICE))) {
                            setSelectedHireType(String.valueOf(IEMPConstants.EMP_HIRE_TYPE_RE_EMPLOYEE));
                            if (((IEmployeesDTO)empDTO).getMinCode().equals(getLoggedInMinistry())) {
                                fillEmp = true;
                            }
                        }
                    }
                }

                //                if (!empHired && !empEndServiceflag) {
                if (!empHired) {

                    List<IEmpCandidatesDTO> empCndList = new ArrayList<IEmpCandidatesDTO>();


                    try {
                        empCndList =
                                EmpClientFactory.getEmpCandidatesClient().checkIfCitizenIsCandidate(new Long(civilId));
                    } catch (DataBaseException e) {
                        empCandidate = false;
                        civilExist = false;
                        System.out.println("empCandidate ? >>>>>>>>>>>>>>>>>>>>" + empCandidate);

                    } catch (SharedApplicationException e) {
                        empCandidate = false;
                        civilExist = false;
                        System.out.println("empCandidate ? >>>>>>>>>>>>>>>>>>>>" + empCandidate);

                    }


                    if (empCndList != null && empCndList.size() > 0) {
                        empCandidate = true;
                        civilExist = false;
                        System.out.println("empCandidate ? >>>>>>>>>>>>>>>>>>>>" + empCandidate);
                        return;
                    } else {
                        if (empDTO != null && fillEmp)
                            fillEmpData((IEmployeesDTO)empDTO);

                    }


                }

                fillQualIntegration();
               
                addCandidateMaintainBean.fillHireTypesList();
                setCandidateName(kwtCitizensResidentsDTO.getFullName());
                IGenderTypesDTO genderTypesDTO = InfDTOFactory.createGenderTypesDTO();
                try {
                    genderTypesDTO =
                            (IGenderTypesDTO)InfClientFactory.getGenderTypesClient().getById(kwtCitizensResidentsDTO.getGenderTypesDTO().getCode());
                    setGenderTypeName(genderTypesDTO.getGentypeName());
                    IMaritalStatusDTO marDTO =
                        (IMaritalStatusDTO)InfClientFactory.getMaritalStatusClient().getById(kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode());
                    setMaritalStatus(marDTO.getMarstatusName());
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                }
                if (kwtCitizensResidentsDTO.getNationality() != null) {
                    ICountriesDTO countriesDTO = InfDTOFactory.createCountriesDTO();
                    IGenderCountryDTO genderCountryDTO = InfDTOFactory.createGenderCountryDTO();
                    Long cntryCode = null;
                    Long genderCode = Long.valueOf(genderTypesDTO.getCode().getKey());
                    ICountriesEntityKey countriesEntityKey =
                        InfEntityKeyFactory.createCountriesEntityKey(kwtCitizensResidentsDTO.getNationality());
                    try {
                        countriesDTO =
                                (ICountriesDTO)InfClientFactory.getCountriesClient().getById(countriesEntityKey);
                        cntryCode = ((ICountriesEntityKey)countriesDTO.getCode()).getCntryCode();
                        genderCountryDTO =
                                (IGenderCountryDTO)InfClientFactory.getGenderCountryClient().getById(InfEntityKeyFactory.createGenderCountryEntityKey(genderCode,
                                                                                                                                                      cntryCode));
                        setNationality(genderCountryDTO.getName());
                    } catch (DataBaseException e) {
                        e.printStackTrace();
                    } catch (SharedApplicationException e) {
                        e.printStackTrace();
                    }

                   
                }
                ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setCitizensResidentsDTO(kwtCitizensResidentsDTO);
                if (!empEndServiceflag) {
                    setSelectedHireType(String.valueOf(IEMPConstants._EMP_CANDIDATE_FOR_MINISTRY));
                    setHireTypeCodeOnChange();
                    }else{
                                 procedureDataInitate();  
                             }
                
                if (kwtCitizensResidentsDTO.getNationality() == 0 && kwtCitizensResidentsDTO.getNonStatus() == 2) {
                    setSelectedHireType(getManagedConstantsBean().getVirtualStringValueConstant());
                    empNationality = false;
                    setContractType("7");
                    setDisabledContractType(true);
                    fillCaderList();
                }
                validCivilId = true;
                civilExist = true;
            } else {
                validCivilId = false;
                civilExist = false;
                empCandidate = false;
                empHired = false;
                empNationality = false;
            }

        } else {
            validCivilId = false;
            civilExist = false;
            empCandidate = false;
            empHired = false;
            empNationality = false;
        }


    }
    public void procedureDataInitate()  {
        
            ProceduresCandidate procBean=ProceduresCandidate.getInstance();
            procBean.fillListAvailable();
        try {
            procBean.selectedAvailableAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        procBean.add();
            procBean.empProceduresDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
        }
    public void fillEmpData(IEmployeesDTO empDTO) {

        setFileNo(empDTO.getMinistryFileNo());
        setWorkCentersDTO((IWorkCentersDTO)empDTO.getWorkCenterDTO());
        setWorkCenterName(((IWorkCentersDTO)empDTO.getWorkCenterDTO()).getName());
        setWorkCenterKey(((IWorkCentersEntityKey)getWorkCentersDTO().getCode()).getWrkCode());
        fillBgtProgName();
        setBgtTypeKey(empDTO.getBgtTypeKey());
        //        setBgtProgName(empDTO.getBgtProgramsDTO().getName());
        //        ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setBgtProgramsDTO(empDTO.getBgtProgramsDTO());
    }

    public void setHireTypeCodeOnChange() {
        renderedErrorMsgHirType = false;
        if (!getSelectedHireType().equals("-100")) {
            Long hirtypeCode = Long.valueOf(getSelectedHireType());
            IHireTypesEntityKey hireTypeEK = EmpEntityKeyFactory.createHireTypesEntityKey(hirtypeCode);

            IHireTypesDTO hireTypesDTO = EmpDTOFactory.createHireTypesDTO();
            try {
                hireTypesDTO = (IHireTypesDTO)EmpClientFactory.getHireTypesClient().getById(hireTypeEK);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            if (hireTypesDTO != null && hireTypesDTO.getConditionsDTO() != null) {
                renderedErrorMsgHirType = false;
                setHireTypeFirstParent(hireTypesDTO.getFirstParent().getKey());
                ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setHireTypesDTO(hireTypesDTO);
                procedureDataInitate();
            } else {
                renderedErrorMsgHirType = true;
            }
        }

    }

    public void fillPageDTO() {

        if (getRaiseCode() != null && !getRaiseCode().equals("")) {
            List<IBasicDTO> empCndSalaryElementsList = new ArrayList();
            IEmpCndSalaryElementsDTO dto = EmpDTOFactory.createEmpCndSalaryElementsDTO();
            ISalElementGuidesDTO salElementGuidesDTO = SalDTOFactory.createSalElementGuidesDTO();
            salElementGuidesDTO.setCode(SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.valueOf(getRaiseCode())));
            dto.setSalElementGuidesDTO(salElementGuidesDTO);
            if (getRaiseCodeEq() != null && !getRaiseCodeEq().equals(""))
                dto.setElmguideCodeEquv(Long.valueOf(getRaiseCodeEq()));
            else
                dto.setElmguideCodeEquv(null);
            if (totalRewardAccepted != null && !totalRewardAccepted.equals(""))
                dto.setElementValue(Float.valueOf(totalRewardAccepted));
            else
                dto.setElementValue(null);
            Calendar cal = Calendar.getInstance();
                Date date = new Date(cal.getTimeInMillis());
                dto.setFromDate(date);
       

            empCndSalaryElementsList.add(dto);
            ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setEmpCndSalaryElementsList(empCndSalaryElementsList);
        }
        if (getBgtTypeKey() != null && !bgtTypeKey.equals(getVirtualConstValue())) {
            IBgtTypesEntityKey bgtTypesEK = BgtEntityKeyFactory.createBgtTypesEntityKey(Long.valueOf(bgtTypeKey));
            IBgtTypesDTO bgtTypesDTO = BgtDTOFactory.createBgtTypesDTO();
            bgtTypesDTO.setCode(bgtTypesEK);
            ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setBgtTypesDTO(bgtTypesDTO);
        }

        ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setCitizensResidentsDTO(kwtCitizensResidentsDTO);
        ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setMinistryFileNo(fileNo);
       

        if (getJobDTO() != null) {
            ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setJobsDTO(getJobDTO());
        }
        if (getWorkCentersDTO() != null) {
            ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setWorkCentersDTO(getWorkCentersDTO());
        } else {
            ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setMinCode(getLoggedInMinistry());
        }
        ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setMinExcepted(minExcepted);

    }


    public void reSetData(ActionEvent event) {
        addCandidateMaintainBean.setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        civilId = null;
        empHired = false;
        empNationality = false;
        empCandidate = false;
        validCivilId = true;
        civilExist = false;
        candidateName = "";
        genderTypeName = "";
        maritalStatus = "";
        nationality = "";
        selectedHireType = "";
        fileNo = "";
        contractType = getVirtualConstValue();
        setDisabledContractType(false);
        workCentersDTO = null;
        workCenterName = null;
        workCenterKey = null;
        bgtProgName = null;
        bgtTypeKey = getVirtualConstValue();
        setJobCode(null);
        setJobDTO(null);
        setJobName(null);
        resetSalValues();
        DocumentsCandidate documentsCandidateBean =
            (DocumentsCandidate)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{documentsCandidateBean}").getValue(FacesContext.getCurrentInstance());
        documentsCandidateBean.getCurrentDetails().clear();
        ProceduresCandidate proceduresCandidateBean =
            (ProceduresCandidate)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{proceduresCandidateBean}").getValue(FacesContext.getCurrentInstance());
        proceduresCandidateBean.getCurrentDetails().clear();
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        if (integrationBean.getHmBaseBeanFrom().size() > 0 &&
            ((QulIntegrationListBean)integrationBean.getHmBaseBeanFrom().get("qulIntegrationBean")) != null) {
            ((QulIntegrationListBean)integrationBean.getHmBaseBeanFrom().get("qulIntegrationBean")).setCivilId(null);
        }
        QulIntegrationListBean qulIntegrationListBean =
            (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
        if (qulIntegrationListBean != null) {
            qulIntegrationListBean.setCivilId(null);
            qulIntegrationListBean.setKwtCitizenDTO(null);
        }
    }

    public PagingResponseDTO getJobListForMinWithPaging(PagingRequestDTO request) {
        request = null;
        try {
            IJobSearchCriteriaDTO jobSearchCriteriaDTO = JobDTOFactory.createJobSearchCriteriaDTO();
            jobSearchCriteriaDTO.setJobFreez(ISystemConstant.NON_FREEZE_FLAG);
            jobSearchCriteriaDTO.setMinCode(getLoggedInMinistry());
            jobList = JobClientFactory.getJobsClient().getCodeNameOfMinistryJobsAndPublicJobs(jobSearchCriteriaDTO);
        } catch (Exception e) {
            e.printStackTrace();
            jobList = new ArrayList();
        }
        return new PagingResponseDTO(jobList);
    }


    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setEmpHired(boolean empHired) {
        this.empHired = empHired;
    }

    public boolean isEmpHired() {
        return empHired;
    }

    public void setValidCivilId(boolean validCivilId) {
        this.validCivilId = validCivilId;
    }

    public boolean isValidCivilId() {
        return validCivilId;
    }

    public void setCivilExist(boolean civilExist) {
        this.civilExist = civilExist;
    }

    public boolean isCivilExist() {
        return civilExist;
    }

    public void setVirtual_value(String virtual_value) {
        this.virtual_value = virtual_value;
    }

    public String getVirtual_value() {
        return virtual_value;
    }

    public void setEmpCandidate(boolean empCandidate) {
        this.empCandidate = empCandidate;
    }

    public boolean isEmpCandidate() {
        return empCandidate;
    }

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {
        return kwtCitizensResidentsDTO;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateName() {

        return candidateName;
    }

    public void setGenderTypeName(String genderTypeName) {
        this.genderTypeName = genderTypeName;
    }

    public String getGenderTypeName() {
        return genderTypeName;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setSelectedHireType(String selectedHireType) {
        this.selectedHireType = selectedHireType;
    }

    public String getSelectedHireType() {
        return selectedHireType;
    }

   

    public void setTitleFlageOfDiv(String titleFlageOfDiv) {
        this.titleFlageOfDiv = titleFlageOfDiv;
    }

    public String getTitleFlageOfDiv() {
        return titleFlageOfDiv;
    }


    public void setWorkCenterMode(boolean workCenterMode) {
        this.workCenterMode = workCenterMode;
    }

    public boolean isWorkCenterMode() {
        return workCenterMode;
    }

    public void setRenderLovDiv(boolean renderLovDiv) {
        this.renderLovDiv = renderLovDiv;
    }

    public boolean isRenderLovDiv() {
        return renderLovDiv;
    }

    public void setWrkCenterList(List wrkCenterList) {
        this.wrkCenterList = wrkCenterList;
    }

   

 
   

   


 
    public List getWrkCenterList() {

        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        IMinistriesEntityKey ministryEntityKey = OrgEntityKeyFactory.createMinistriesEntityKey(minCode);

        try {
            if ((wrkCenterList == null || wrkCenterList.size() == 0) &&
                (checkCivilValidity() || addCandidateMaintainBean.getMaintainMode() == 1))
                wrkCenterList = OrgClientFactory.getWorkCentersClient().getAllByMinistry(ministryEntityKey);

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            wrkCenterList = new ArrayList();
        } catch (DataBaseException e) {
            e.printStackTrace();
            wrkCenterList = new ArrayList();
        } catch (Exception e) {
            e.printStackTrace();
            wrkCenterList = new ArrayList();
        }
        return wrkCenterList;
    }

    public void filterRaisesCountByDegree() {

        if (degreeCode != null) {
            try {
                raisesCount =
                        SalClientFactory.getSalElementGuidesClient().getRaiseCodeNameByDegree(Long.valueOf(degreeCode),
                                                                                              null);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
    }

    public List getDegreesByGroup() {

        try {
            degreesList =
                    (List)SalClientFactory.getSalElementGuidesClient().getDegreesTreeByGroupFirstLevel(Long.valueOf(groupCode));

        } catch (SharedApplicationException e) {
            treedivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        } catch (Exception e) {
            treedivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        }
        return degreesList;
    }


    public void cancelSearchLovDiv() {
        //  showListOfValuesDiv();
    }

    private boolean checkCivilValidity() {
        return (!invalidCivilID && !NonDBCivilID && !invalidCivilWithHireType && civilExist);
    }

    public void setInvalidCivilID(boolean invalidCivilID) {
        this.invalidCivilID = invalidCivilID;
    }

    public boolean isInvalidCivilID() {
        return invalidCivilID;
    }

    public void setNonDBCivilID(boolean NonDBCivilID) {
        this.NonDBCivilID = NonDBCivilID;
    }

    public boolean isNonDBCivilID() {
        return NonDBCivilID;
    }

    public void setInvalidCivilWithHireType(boolean invalidCivilWithHireType) {
        this.invalidCivilWithHireType = invalidCivilWithHireType;
    }

    public boolean isInvalidCivilWithHireType() {
        return invalidCivilWithHireType;
    }

    public void setSearchJobMode(boolean searchJobMode) {
        this.searchJobMode = searchJobMode;
    }

    public boolean isSearchJobMode() {
        return searchJobMode;
    }

    public void setJobList(List jobList) {
        this.jobList = jobList;
    }

    public List getJobList() {
        return jobList;
    }

    public void setWorkCenterName(String workCenterName) {
        this.workCenterName = workCenterName;
    }

    public String getWorkCenterName() {
        return workCenterName;
    }

    public void setCenterClient(IBasicClient centerClient) {
        this.centerClient = centerClient;
    }

    public IBasicClient getCenterClient() {
        return centerClient;
    }

    public void setBudgetTypeList(List<IBasicDTO> budgetTypeList) {
        this.budgetTypeList = budgetTypeList;
    }

    public List<IBasicDTO> getBudgetTypeList() {
        if ((budgetTypeList == null || budgetTypeList.size() == 0) &&
            (checkCivilValidity() || addCandidateMaintainBean.getMaintainMode() == 0))
            try {
                budgetTypeList = BgtClientFactory.getBgtTypesClient().getCodeName();
                for (IBasicDTO dto : budgetTypeList) {
                    Long value =Long.valueOf(dto.getCode().getKey());
                    if (value.equals(5L)) {
                        budgetTypeList.remove(dto);
                        break;
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        return budgetTypeList;
    }

    public void setCaderName(String caderName) {
        this.caderName = caderName;
    }

    public String getCaderName() {
        return caderName;
    }

    public void setCaderCode(String caderCode) {
        this.caderCode = caderCode;
    }

    public String getCaderCode() {
        return caderCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    public String getDegreeCode() {
        return degreeCode;
    }

    public void setRaiseCode(String raiseCode) {
        this.raiseCode = raiseCode;
    }

    public String getRaiseCode() {
        return raiseCode;
    }

    public void setCaderList(List caderList) {
        this.caderList = caderList;
    }

    public List getCaderList() {
        return caderList;
    }

    private Long getMinistryCode() {
        if (getPageDTO() != null) {
            IEmpCandidatesDTO employeesDTO = ((IEmpCandidatesDTO)getPageDTO());
            //            return ((IWorkCentersEntityKey)employeesDTO.getWorkCentersDTO().getCode()).getMinCode();
            return employeesDTO.getMinCode();
        }
        return null;
    }

    public void setGroupList(List groupList) {
        this.groupList = groupList;
    }

    public List getGroupList() {
        return groupList;
    }

    public void setEqGroupList(List eqGroupList) {
        this.eqGroupList = eqGroupList;
    }

    public List getEqGroupList() {
        return eqGroupList;
    }

    public void setBudgetList(List budgetList) {
        this.budgetList = budgetList;
    }

    public List getBudgetList() {
        return budgetList;
    }

    public void setRaisesCount(List<IBasicDTO> raisesCount) {
        this.raisesCount = raisesCount;
    }

    public List<IBasicDTO> getRaisesCount() {
        return raisesCount;
    }

    public void setDegreesList(List<SalElementGuidesDTO> degreesList) {
        this.degreesList = degreesList;
    }

    public List<SalElementGuidesDTO> getDegreesList() {
        return degreesList;
    }

    public void setWcIntegrationBean(WorkCentersHelperBean wcIntegrationBean) {
        this.wcIntegrationBean = wcIntegrationBean;
    }

    public WorkCentersHelperBean getWcIntegrationBean() {
        return wcIntegrationBean;
    }

    public void setWorkCenterKey(String workCenterKey) {
        this.workCenterKey = workCenterKey;
    }

    public String getWorkCenterKey() {
        return workCenterKey;
    }

    public void setWrkCenterName(String wrkCenterName) {
        this.wrkCenterName = wrkCenterName;
    }

    public String getWrkCenterName() {
        return wrkCenterName;
    }

    public void setBgtProgName(String bgtProgName) {
        this.bgtProgName = bgtProgName;
    }

    public String getBgtProgName() {
        return this.bgtProgName;
    }

    public String fillBgtProgName() {
        String bgtprName = "";
        IOrgMinExtraDataClient extraDataClient = OrgClientFactory.getOrgMinExtraDataClient();
        if (workCenterKey != null && workCenterKey != "") {
            IBgtProgramsClient bgtProgramClient = BgtClientFactory.getBgtProgramsClient();
            List list = null;
            try {
                list = bgtProgramClient.getBgtProgramByWrkCenterCode(workCenterKey);
                if (list.size() > 0) {
                    ((IEmpCandidatesDTO)addCandidateMaintainBean.getPageDTO()).setBgtProgramsDTO((IBasicDTO)list.get(0));
                    bgtprName = ((IBgtProgramsDTO)list.get(0)).getName();
                    
                    String bgtValue=  ((IBgtProgramsDTO)list.get(0)).getCode().getKey();
                    if (contractType.toString().trim().equals(IEMPConstants.CONTRACT_TYPE.toString().trim())) {
                        minExcepted =
                                extraDataClient.checkExceptedMin(CSCSecurityInfoHelper.getLoggedInMinistryCode(), 43L,
                                                                 bgtValue);
                    } else if (contractType.toString().trim().equals(IEMPConstants.ESTANA_TYPE.toString().trim())) {
                        minExcepted =
                                extraDataClient.checkExceptedMin(CSCSecurityInfoHelper.getLoggedInMinistryCode(), 44L,
                                                                 bgtValue);
                    }
                    
                }
            } catch (DataBaseException e) {
                e.printStackTrace();
                bgtprName = "";
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                bgtprName = "";
            }
        }
        this.bgtProgName = bgtprName;

        return bgtprName;
    }

    public String fillBgtValue(){
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

    public List initListForgetSal(Long contractType) {
        List list = new ArrayList();
        IOrgMinExtraDataClient extraDataClient = OrgClientFactory.getOrgMinExtraDataClient();
        String bgtValue = fillBgtValue();
        if (contractType.equals(IEMPConstants.CONTRACT_TYPE)) {
            list.add(IEMPConstants.CONTRACT_TYPE);
            list.add(null);
            list.add(1);
            try {
                 minExcepted=extraDataClient.checkExceptedMin( CSCSecurityInfoHelper.getLoggedInMinistryCode() ,43L);
                
                if(!bgtValue.equals("")){
                        minExcepted=extraDataClient.checkExceptedMin( CSCSecurityInfoHelper.getLoggedInMinistryCode() ,43L,bgtValue);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(Boolean.TRUE);
        } else if (contractType.equals(IEMPConstants.ESTANA_TYPE)) {
            list.add(IEMPConstants.ESTANA_TYPE);
            list.add(null);
            list.add(1);
            try {
                 minExcepted=extraDataClient.checkExceptedMin( CSCSecurityInfoHelper.getLoggedInMinistryCode() ,44L);
                if(!bgtValue.equals("")){
                        minExcepted=extraDataClient.checkExceptedMin( CSCSecurityInfoHelper.getLoggedInMinistryCode() ,44L,bgtValue);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(Boolean.TRUE);
        } else {
            list.add(IEMPConstants.CONTRACT_TYPE);
            list.add(IEMPConstants.ESTANA_TYPE);
            list.add(2);
            list.add(Boolean.FALSE);
        }

        return list;
    }

    public void resetSalValues() {
        setCaderList(new ArrayList());
        setCaderCode(getVirtualConstValue());
        setGroupList(new ArrayList());
        setGroupCode(getVirtualConstValue());
        setDegreesList(new ArrayList<SalElementGuidesDTO>());
        setDegreeCode("");
        setRaisesCount(new ArrayList());
        setRaiseCode("");
    }

    public void fillCaderList() {
        resetSalValues();
        setMinExcepted(false);
        this.setJobCode(null);
        setJobDTO(null);
        this.setJobName(null);
        resetEqData();
        if (getContractType() != null && !getContractType().equals(getVirtualConstValue())) {
            List list = initListForgetSal(Long.valueOf(getContractType()));
            if (caderList == null || (caderList != null && caderList.size() == 0)) {
                try {
                    caderList =
                            SalClientFactory.getSalElementGuidesClient().getCaderCodeName(getLoggedInMinistry(), list);
                } catch (DataBaseException e) {
                    e.printStackTrace();
                    caderList = new ArrayList();
                } catch (Exception e) {
                    e.printStackTrace();
                    caderList = new ArrayList();
                }

            }
        }
    }

    public void fillGroupList() {
        List list = initListForgetSal(Long.valueOf(getContractType()));
        setGroupList(new ArrayList());
        setGroupCode(getVirtualConstValue());
        setDegreesList(new ArrayList<SalElementGuidesDTO>());
        setDegreeCode("");
        setRaisesCount(new ArrayList());
        resetEqData();
        setRaiseCode("");
        if (groupList == null || (groupList != null && groupList.size() == 0)) {
            try {
                groupList =
                        SalClientFactory.getSalElementGuidesClient().getGroupCodeNameByCader(Long.valueOf(caderCode),
                                                                                             list);
            } catch (DataBaseException f) {
                f.printStackTrace();
                groupList = new ArrayList();
            } catch (Exception f) {
                f.printStackTrace();
                groupList = new ArrayList();
            }
        }
    }

   

    public void filterDegreesByGroup() {
        List list = initListForgetSal(Long.valueOf(getContractType()));
        setDegreesList(new ArrayList<SalElementGuidesDTO>());
        setDegreeCode("");
        setRaisesCount(new ArrayList());
        setRaiseCode("");
        resetEqData();
        if (degreesList == null || (degreesList != null && degreesList.size() == 0)) {
            try {
                degreesList =
                        (List)SalClientFactory.getSalElementGuidesClient().getDegreeCodeNameByGroupSorted(Long.valueOf(groupCode),
                                                                                                    list);

            } catch (SharedApplicationException e) {
                treedivBean.setMyTableData(new ArrayList());
                e.printStackTrace();
                degreesList = new ArrayList();
            } catch (Exception e) {
                treedivBean.setMyTableData(new ArrayList());
                e.printStackTrace();
                degreesList = new ArrayList();
            }
        }
    }

    public void filterRaisesByDegree() {
        resetEqData();
        if (degreeCode != null && !degreeCode.equals("")) {

            errorCanDegree = false;
            boolean condition = false;
            try {
                condition =
                        EmpClientFactory.getEmployeesClient().isConditionFromGrsValid(Long.valueOf(civilId), Long.valueOf(degreeCode));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!condition) {
                errorCanDegree = true;
                raisesCount = new ArrayList();
                return;
            }

            List list = initListForgetSal(Long.valueOf(getContractType()));
            setRaisesCount(new ArrayList());
            setRaiseCode("");
            if (raisesCount == null || (raisesCount != null && raisesCount.size() == 0)) {
                try {
                    raisesCount =
                            SalClientFactory.getSalElementGuidesClient().getRaiseCodeNameByDegree(Long.valueOf(degreeCode),
                                                                                                  list);
                } catch (DataBaseException e) {
                    e.printStackTrace();
                    raisesCount = new ArrayList();
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                    raisesCount = new ArrayList();
                }
            }
        } else {
            errorCanDegree = false;
            raisesCount = new ArrayList();
        }
    }


    public void checkAboutFilNum() {
        int count = 0;
        if (fileNo != null && !fileNo.trim().isEmpty()) {
            try {
                Long civilId = Long.valueOf(this.getCivilId());
                count =
                        EmpClientFactory.getEmpCandidatesClient().isFilNumRedundant(CSCSecurityInfoHelper.getLoggedInMinistryCode(),
                                                                                    fileNo, civilId);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            if (count == 1) {
                renderEmpFilNumRedundant = true;
                renderCandFilNumRedundant = false;
            } else if (count == 2) {
                renderEmpFilNumRedundant = false;
                renderCandFilNumRedundant = true;
            } else {
                renderEmpFilNumRedundant = false;
                renderCandFilNumRedundant = false;
            }
        } else {
            renderEmpFilNumRedundant = false;
            renderCandFilNumRedundant = false;
        }

    }

    public void setJobMode(boolean jobMode) {
        this.jobMode = jobMode;
    }

    public boolean isJobMode() {
        return jobMode;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractType() {
        return contractType;
    }

    public String getContracts() {
        return String.valueOf(IEMPConstants.CONTRACT_TYPE);
    }

    public String getOtherType() {
        return OTHER_TYPE;
    }

    public String getEstanaType() {
        return String.valueOf(IEMPConstants.ESTANA_TYPE);
    }

    public void setJobFilter(JobFilter jobFilter) {
        this.jobFilter = jobFilter;
    }

    public JobFilter getJobFilter() {
        return jobFilter;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobDTO(IJobsDTO jobDTO) {
        this.jobDTO = jobDTO;
    }

    public IJobsDTO getJobDTO() {
        return jobDTO;
    }

    public void setWorkCentersDTO(IWorkCentersDTO workCentersDTO) {
        this.workCentersDTO = workCentersDTO;
    }

    public IWorkCentersDTO getWorkCentersDTO() {
        return workCentersDTO;
    }

    public void setBgtTypeKey(String bgtTypeKey) {
        this.bgtTypeKey = bgtTypeKey;
    }

    public String getBgtTypeKey() {
        return bgtTypeKey;
    }

    

 
    public void setRenderEmpFilNumRedundant(boolean renderEmpFilNumRedundant) {
        this.renderEmpFilNumRedundant = renderEmpFilNumRedundant;
    }

    public boolean isRenderEmpFilNumRedundant() {
        return renderEmpFilNumRedundant;
    }

    public void setRenderCandFilNumRedundant(boolean renderCandFilNumRedundant) {
        this.renderCandFilNumRedundant = renderCandFilNumRedundant;
    }

    public boolean isRenderCandFilNumRedundant() {
        return renderCandFilNumRedundant;
    }

   

   

    public void fillQualIntegration() {
        getIntegrationBean().reInitializeBean();
        Integer WIZARD_BAR_PAGE = 1;
        QulIntegrationListBean qulIntegrationListBean =
            (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
        qulIntegrationListBean.setCivilId(Long.valueOf(kwtCitizensResidentsDTO.getCode().getKey()));
        qulIntegrationListBean.setPageType(WIZARD_BAR_PAGE);
        qulIntegrationListBean.setAddQualificationIntegrationpage("AddQualificationIntegration");
        qulIntegrationListBean.setQulListPageinWizardBar("qualificationsCandidatePage");
        qulIntegrationListBean.setSaveInDB(false);
        qulIntegrationListBean.setCustomCurentQual(true);
        if (kwtCitizensResidentsDTO.getStatusFlag() != null &&
            kwtCitizensResidentsDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
            qulIntegrationListBean.setKwtCitizenDTO(kwtCitizensResidentsDTO);
        } else {
            qulIntegrationListBean.loadKwtCitizenDTO();
        }
        qulIntegrationListBean.setShowCustomDiv2(true);
        qulIntegrationListBean.setDataTablestyleClass("dataT-With-3-row-filter");
        qulIntegrationListBean.setNeedAddQualification(true);
        qulIntegrationListBean.setBundleMsg(null);
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        integrationBean.getHmBaseBeanFrom().put("qulIntegrationBean", qulIntegrationListBean);
    }

    public void setEmpNationality(boolean empNationality) {
        this.empNationality = empNationality;
    }

    public boolean isEmpNationality() {
        return empNationality;
    }

    public void setDisabledContractType(boolean disabledContractType) {
        this.disabledContractType = disabledContractType;
    }

    public boolean isDisabledContractType() {
        return disabledContractType;
    }

    public void setHireTypeFirstParent(String hireTypeFirstParent) {
        this.hireTypeFirstParent = hireTypeFirstParent;
    }

    public String getHireTypeFirstParent() {
        return hireTypeFirstParent;
    }

    public void setRenderedErrorMsgHirType(boolean renderedErrorMsgHirType) {
        this.renderedErrorMsgHirType = renderedErrorMsgHirType;
    }

    public boolean isRenderedErrorMsgHirType() {
        return renderedErrorMsgHirType;
    }

  
    public void setErrorCanDegree(boolean errorCanDegree) {
        this.errorCanDegree = errorCanDegree;
    }

    public boolean isErrorCanDegree() {
        return errorCanDegree;
    }

    public static MainDataCandidate getInstance() {
        return (MainDataCandidate)JSFHelper.getValue(BEAN_NAME);
    }

  

    public void setErrorJobCondition(boolean errorJobCondition) {
        this.errorJobCondition = errorJobCondition;
    }

    public boolean isErrorJobCondition() {
        return errorJobCondition;
    }

    public void setDataNotcomplete(boolean dataNotcomplete) {
        this.dataNotcomplete = dataNotcomplete;
    }

    public boolean isDataNotcomplete() {
        return dataNotcomplete;
    }

    public void setDataNotcompleteErrorMSG(String dataNotcompleteErrorMSG) {
        this.dataNotcompleteErrorMSG = dataNotcompleteErrorMSG;
    }

    public String getDataNotcompleteErrorMSG() {
        return dataNotcompleteErrorMSG;
    }

    public void setInvalidNextYear(boolean invalidNextYear) {
        this.invalidNextYear = invalidNextYear;
    }

    public boolean isInvalidNextYear() {
        return invalidNextYear;
    }

    public void setDateOfNextRaise(Date dateOfNextRaise) {
        this.dateOfNextRaise = dateOfNextRaise;
    }

    public Date getDateOfNextRaise() {
        return dateOfNextRaise;
    }

    public void setRenderErrorHireDate(boolean renderErrorHireDate) {
        this.renderErrorHireDate = renderErrorHireDate;
    }

    public boolean isRenderErrorHireDate() {
        return renderErrorHireDate;
    }

    public void setNextYear(Date nextYear) {
        this.nextYear = nextYear;
    }

    public Date getNextYear() {
        return nextYear;
    }

    public void setPreviousYear(Date previousYear) {
        this.previousYear = previousYear;
    }

    public Date getPreviousYear() {
        return previousYear;
    }

    public void setKuwaitCitizen(boolean kuwaitCitizen) {
        this.kuwaitCitizen = kuwaitCitizen;
    }

    public boolean isKuwaitCitizen() {
        return kuwaitCitizen;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setMinExcepted(boolean minExcepted) {
        this.minExcepted = minExcepted;
    }

    public boolean isMinExcepted() {
        return minExcepted;
    }
    public List initListForgetSal() {
        List list = new ArrayList();
        list.add(IEMPConstants.CONTRACT_TYPE);
        list.add(IEMPConstants.ESTANA_TYPE);
        list.add(2);
        list.add(Boolean.FALSE);
        return list;
    }
    public List getCaderListEq() {
        if (caderListEq == null || (caderListEq != null && caderListEq.size() == 0)) {
            try {
                caderListEq =
                        SalClientFactory.getSalElementGuidesClient().getCaderCodeName(getLoggedInMinistry(), initListForgetSal());
            } catch (DataBaseException e) {
                e.printStackTrace();
                caderListEq = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                caderListEq = new ArrayList();
            }

        }
        return caderListEq;
    }

    public void setGroupListEq(List groupListEq) {
        this.groupListEq = groupListEq;
    }

    public List getGroupListEq() {
        if (groupListEq == null || (groupListEq != null && groupListEq.size() == 0)) {
            if (caderCodeEq != null && !caderCodeEq.equals("")) {
                try {
                    groupListEq =
                            SalClientFactory.getSalElementGuidesClient().getGroupCodeNameByCader(caderCodeEq, initListForgetSal());
                } catch (DataBaseException f) {
                    f.printStackTrace();
                    groupListEq = new ArrayList();
                } catch (Exception f) {
                    f.printStackTrace();
                    groupListEq = new ArrayList();
                }
            }
        }
        return groupListEq;
    }

    public void setCaderListEq(List caderListEq) {
        this.caderListEq = caderListEq;
    }

    public void setCaderCodeEq(Long caderCodeEq) {
        this.caderCodeEq = caderCodeEq;
    }

    public Long getCaderCodeEq() {
        return caderCodeEq;
    }
    
    public void onDegreeChange() {
        try {
            validCond = EmpClientFactory.getEmployeesClient().isConditionFromGrsValid(Long.valueOf(getCivilId()), degreeCodeEq);
        } catch (DataBaseException e) {
            e.printStackTrace();
            validCond = true;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            validCond = true;
        }

        if (validCond) {
            fillRaiseList();
        } else {
            raiseList = new ArrayList();
            raiseCodeEq = "";
        }
    }

    public void onDegreeChangedRaise(){
            raiseList = new ArrayList();
            raiseCodeEq = "";
            fillRaiseList();
        }

    public void fillRaiseList() {
        if (degreeCodeEq != null && !degreeCodeEq.equals(getVirtualLongValue())) {
            try {
                raiseList =
                        SalClientFactory.getSalElementGuidesClient().getRaiseCodeNameByDegree(degreeCodeEq, initListForgetSal());
            } catch (DataBaseException e) {
                e.printStackTrace();
                raiseList = new ArrayList();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                raiseList = new ArrayList();
            }
        }
    }

    public void setRaiseList(List raiseList) {
        this.raiseList = raiseList;
    }

    public List getRaiseList() {
        return raiseList;
    }

    public void fillTotalRewardAccepted() {
        if (raiseCodeEq != null && !getRaiseCodeEq().equals("")) {
            try {

                raiseDTO =
                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.parseLong(raiseCodeEq));
                setTotalRewardAccepted(String.valueOf(raiseDTO.getValue()));
            } catch (DataBaseException e) {
                e.printStackTrace();
                setTotalRewardAccepted("");
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                setTotalRewardAccepted("");
            }
        }

    }

    public void setValidCond(boolean validCond) {
        this.validCond = validCond;
    }

    public boolean isValidCond() {
        return validCond;
    }

    public void setDegreeCodeEq(Long degreeCodeEq) {
        this.degreeCodeEq = degreeCodeEq;
    }

    public Long getDegreeCodeEq() {
        return degreeCodeEq;
    }

    public void setRaiseCodeEq(String raiseCodeEq) {
        this.raiseCodeEq = raiseCodeEq;
    }

    public String getRaiseCodeEq() {
        return raiseCodeEq;
    }

    public void setRaiseDTO(ISalElementGuidesDTO raiseDTO) {
        this.raiseDTO = raiseDTO;
    }

    public ISalElementGuidesDTO getRaiseDTO() {
        return raiseDTO;
    }
    public void setDegreeList(List degreeList) {
        this.degreeList = degreeList;
    }

    public List getDegreeList() {
        if (degreeList == null || (degreeList != null && degreeList.size() == 0)) {
            if (groupCodeEq != null && !groupCodeEq.equals(getVirtualLongValue())) {
                try {
                    degreeList =
                            (List)SalClientFactory.getSalElementGuidesClient().getDegreeCodeNameByGroupSorted(groupCodeEq, initListForgetSal());
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return degreeList;
    }

    public void setGroupCodeEq(Long groupCodeEq) {
        this.groupCodeEq = groupCodeEq;
    }

    public Long getGroupCodeEq() {
        return groupCodeEq;
    }
    
    public void resetDegreeAndRaiseList() {
        setDegreeCodeEq(null);
        setDegreeList(new ArrayList());
        setRaiseCodeEq(null);
        setRaiseList(new ArrayList());

    }
    
    public void resetGroupDegreeAndRaiseList() {
        setDegreeCodeEq(null);
        setDegreeList(new ArrayList());
        setRaiseCodeEq(null);
        setRaiseList(new ArrayList());
        setGroupCodeEq(null);
        setGroupListEq(new ArrayList());

    }

    public void setTotalRewardAccepted(String totalRewardAccepted) {
        this.totalRewardAccepted = totalRewardAccepted;
    }

    public String getTotalRewardAccepted() {
        return totalRewardAccepted;
    }
    
    public void resetEqData() {
        setDegreeCodeEq(null);
        setDegreeList(new ArrayList());
        setRaiseCodeEq(null);
        setRaiseList(new ArrayList());
        setGroupCodeEq(null);
        setGroupListEq(new ArrayList());
        setTotalRewardAccepted("");
        setCaderCodeEq(null);
        setCaderListEq(new ArrayList());
    }
}
