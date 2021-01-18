package com.beshara.csc.hr.emp.presentation.backingbean.fatwaemploymentcycle;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata.WorkDataListBean;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidateDocumentsClient;
import com.beshara.csc.hr.emp.business.client.IEmployeesCMTClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.fatwaemploymentcycle.shared.AddBounceBean;
import com.beshara.csc.hr.emp.presentation.backingbean.fatwaemploymentcycle.shared.BussinessService;
import com.beshara.csc.hr.emp.presentation.backingbean.fatwaemploymentcycle.shared.config.HireStageConst;
import com.beshara.csc.hr.emp.presentation.backingbean.fatwaemploymentcycle.shared.config.ReviewConfigBean;
import com.beshara.csc.hr.emp.presentation.backingbean.viewdocuments.ViewDocumentsBean;
import com.beshara.csc.hr.sal.business.client.ISalElementGuidesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobSearchCriteriaDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.integration.presentation.backingbean.search.JobFilter;
import com.beshara.csc.nl.org.business.client.IWorkCentersClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersSearchCriteriaDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;


public class ReviewListBean extends LookUpBaseBean {
    public final int MINISTRY_VIEW_CONST = 0;
    //    public static final String METHOD_NAME_ADD_SELECTED_JOBS = "reviewListBean.jobForCrs";
    public static final String METHOD_NAME_ADD_SELECTED_CERTIFIED_JOBS = "fatwaReviewListBean.jobForMinValue";

    private String bckBtnNavigationCase;
    private List saveStateList = new ArrayList();
    private ReviewConfigBean configBean = new ReviewConfigBean();
    private BussinessService biznasActions = new BussinessService();
    private HireStageConst hireStageConst = new HireStageConst();
    private boolean invalidMinFileNo;
    private boolean hasExperience = false;
    private boolean jobMode;

    private String backActionMethodName;
    private String pageBeanName;
    // added for display grs condition lines with status (fail|success)
    private List<IResultDTO> linesResultList = new ArrayList<IResultDTO>();

    /////////////
    private String hireSystemMode = "";
    ////////////////////////
    private String titleFlageOfDiv;
    private List caderList = new ArrayList();
    private List groupList = new ArrayList();
    private List raiseList = new ArrayList();
    private List degreeList = new ArrayList();
    private List wrkCenterList = new ArrayList();
    private List workMinistriesList = new ArrayList();
    private List jobList = new ArrayList();
    private List budgetTypeList = new ArrayList();
    private boolean workCenterMode;
    private Long caderCode;
    private String caderName;
    private Long suggestedCaderCode;
    private String suggestedCaderName;
    private boolean renderLovDiv = false;
    TreeDivBean treedivBean;
    private Long groupCode;
    private Long suggestedGroupCode;
    private boolean eqTypeTemp = false;
    private Long degreeCode;
    private String degreeName;
    private String suggestedDegreeName;
    private Long suggestedDegreeCode;
    private String raiseCode;
    private String raiseName;
    private Long suggestedRaiseCode;
    private String suggestedRaiseName;
    private boolean renderJobDiv;
    private boolean searchJobMode = false;
    private String jobSearchType = "0";
    private boolean workCenterHasJobs;
    private int jobListSize = 0;
    private String minWorkCenter = "";
    private String workCenterName;
    private String jobNameForMin;
    private IBgtTypesDTO bgtTypesDTO;
    private String approvedBgtTypeCode = getVirtualConstValue();
    private String approvedBgtTypeName = "";
    private IJobsDTO jobDTO;
    private String stageId;
    private String selectedMinistery;
    private String groupName;
    private String suggestedGroupName;
    private String jobKey;
    private IJobsDTO jobsDTO = JobDTOFactory.createJobsDTO();
    private JobFilter jobFilter = new JobFilter();
    private String acceptedJobName;
    //case centeral Employement
    private List caderCRSList = new ArrayList();
    private List groupCRSList = new ArrayList();
    private List raiseCRSList = new ArrayList();
    private List degreeCRSList = new ArrayList();
    private String totalRewardSuggested = "";
    private String totalRewardAccepted = "";
    private boolean enableAddRewardButton;
    private ISalElementGuidesDTO raiseDTO;

    private boolean validCond = true;
    private List<IEmpCandidateExtraDataDTO> empCandExtraDataList;
    private String caderNamePrevious;
    private String groupNamePrevious;
    private String degreeNamePrevious;
    private String raiseNamePrevious;
     private String  totalRewardAcceptedPrevious;
    private boolean jobFromMin; 
    WorkDataListBean workDataListBean = (WorkDataListBean)evaluateValueBinding("workDataListBean");
    private static final String BEAN_NAME = "fatwaReviewListBean";
    private static final String LIST_PAGE = "fatwaReviewRequestPage";
    private static final String LIST_PAGE_APPROVE = "fatwaReviewRequestApprovePage";
    private static final String LIST_PAGE_CRS = "fatwaCenteralEmployment";
    private static final String LIST_PAGE_APPROVE_CRS = "fatwaCenteralEmploymentApprove";
    public ReviewListBean() {
        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setSelectedPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setClient(EmpClientFactory.getEmpCandidatesClient());
        ((IEmpCandidatesDTO)getPageDTO()).setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());

        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setUsingPaging(true);
        if (getLovBaseBean().isUsingPaging()) {
            getLovBaseBean().generatePagingRequestDTO("fatwaReviewListBean", "initMainDataBean");
        } else {
            getLovBaseBean().setMyTableData(new ArrayList());
        }
        initTreeDivBean();
        setCustomDiv1(getCustomDiv1() + " extraTooWideDiv");
        initJobFilter();

    }

    private void initJobFilter() {
        jobFilter.setOkButtonMethod(METHOD_NAME_ADD_SELECTED_CERTIFIED_JOBS);
        jobFilter.setSingleSelection(true);
    }

    public void showJobDivCertified() {
        List<String> excludedJobCodeList = new ArrayList<String>();
        if (this.getJobNameForMin() != null) {
            excludedJobCodeList.add(this.jobNameForMin);
        }
        jobFilter.setExcludedJobList(excludedJobCodeList);
        try {
            jobFilter.cancelSearch();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        jobFilter.setResetMode(false);
        jobFilter.setLoadSecAccessibleJobsOnly(true);
        setWorkCenterMode(false);
        setRenderLovDiv(true);
        setJobMode(true);

    }

    public void checkValidFilMinNo(ActionEvent ae) {
        setInvalidMinFileNo(false);
        if (getPageDTO() != null && ((IEmpCandidatesDTO)getPageDTO()).getMinistryFileNo() != null &&
            !((IEmpCandidatesDTO)getPageDTO()).getMinistryFileNo().equals("")) {
            try {
                boolean exist =
                    EmpClientFactory.getEmployeesCMTClient().isMinistryFileNoExist(((IEmpCandidatesDTO)getPageDTO()).getMinistryFileNo());
                if (exist) {
                    setInvalidMinFileNo(true);
                }
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void budgetTypeController(IBasicDTO budgetTypeDTO, String _stageId) {
        IBgtTypesDTO dto = (IBgtTypesDTO)budgetTypeDTO;
        Long stageId = Long.parseLong(_stageId);
        if (checkForStageId(stageId)) {
            if (dto.getCode() != null) {
                IBgtTypesEntityKey entityKey = (IBgtTypesEntityKey)budgetTypeDTO.getCode();
                if (entityKey.getTypeCode().equals(IEMPConstants.BGT_TYPE_CODE_MINISTRY_BGT)) {
                    getConfigBean().setDisableBudgetType(true);
                } else {
                    getConfigBean().setDisableBudgetType(false);
                    for (IBasicDTO dto2 : getBudgetTypeList()) {
                        if (((IBgtTypesEntityKey)dto2.getCode()).getTypeCode().equals(IEMPConstants.BGT_TYPE_CODE_MINISTRY_BGT)) {
                            getBudgetTypeList().remove(dto2);
                            break;
                        }

                    }
                }

            }
        } else { // any satge rather than the above disable input text
            getConfigBean().setDisableBudgetType(true);
        }
    }

    /**
     * overloaded method
     * @param EmpDto
     * @param _stageId
     */
    public void budgetTypeController(IEmpCandidatesDTO EmpDto, String _stageId) {
        Long stageId = Long.parseLong(_stageId);

        if (((IWorkCentersDTO)EmpDto.getWorkCentersDTO()).getMinistriesDTO() != null){
            String key =
                (((IWorkCentersDTO)EmpDto.getWorkCentersDTO()).getMinistriesDTO().getCatsDTO().getCode()).getKey();
            if (stageId.equals(IEMPConstant.HIRE_STAGE_JOB_NAME_REQUIRED)) {
                if (key.equals(String.valueOf(IEMPConstants.ORG_CAT_CODE_ATT_BGT)) ||
                    key.equals(String.valueOf(IEMPConstants.ORG_CAT_CODE_SPR_BGT))) {
                    getConfigBean().setDisableBudgetType(true);
                    getBudgetTypeList().remove(1);
                    getBudgetTypeList().remove(2);
                } else if (key.equals(String.valueOf(IEMPConstants.ORG_CAT_CODE_ADM_BGT)) ||
                           key.equals(String.valueOf(IEMPConstants.ORG_CAT_CODE_MIN_BGT))) {
                    getConfigBean().setDisableBudgetType(true);
                    for (IBasicDTO dto2 : getBudgetTypeList()) {
                        if (((IBgtTypesEntityKey)dto2.getCode()).getTypeCode().equals(IEMPConstants.BGT_TYPE_CODE_MINISTRY_BGT)) {
                            getBudgetTypeList().remove(dto2);
                            break;
                        }
                    }
                }
            } else { // any satge rather than the above disable input text
                getConfigBean().setDisableBudgetType(true);
            }
        }

    }

    public void salaryDataSectionController(boolean hasExperience, String _stageId) {

        Long stageId = Long.parseLong(_stageId);
        if (stageId.equals(IEMPConstant.HIRE_STAGE_FIN_DEGREE_REQUIRED)) {
            getConfigBean().setDisableCadre(false);
            getConfigBean().setDisableSalDegree(false);
            getConfigBean().setDisableJobGroup(false);
        } else {
            getConfigBean().setDisableCadre(true);
            getConfigBean().setDisableSalDegree(true);
            getConfigBean().setDisableJobGroup(true);
        }
    }


    private boolean checkForStageId(Long stageId) {
        return stageId.equals(IEMPConstant.HIRE_STAGE_COMPLETING_JOB_NAME) ||
            stageId.equals(IEMPConstant.HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT) ||
            stageId.equals(IEMPConstant.HIRE_STAGE_REJECTED_BY_FATWA) ||
            stageId.equals(IEMPConstant.HIRE_STAGE_JOB_NAME_ACCEPTRD) ||
            stageId.equals(IEMPConstant.HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED) ||
            stageId.equals(IEMPConstant.HIRE_STAGE_JOB_NAME_REQUIRED);
    }

    public void calculateNextDateOfRaise() {
        Date empHireDate = ((IEmpCandidatesDTO)getPageDTO()).getHireDate();
        if (empHireDate != null) {
            try {
                ((IEmpCandidatesDTO)getPageDTO()).setDateOfNextRaise(EmpClientFactory.getEmployeesClient().calculateNextRaiseDate(empHireDate));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ((IEmpCandidatesDTO)getPageDTO()).setDateOfNextRaise(null);
        }
        System.out.println("Next Raise of Date>>>>>>>" + ((IEmpCandidatesDTO)getPageDTO()).getDateOfNextRaise());
    }

    public void findJobName() {

        IJobsDTO filteredjobDTO = JobDTOFactory.createJobsDTO();
        filteredjobDTO.setJobKey(jobKey);

        try {
            jobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getByJobKey(filteredjobDTO);

            ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(jobsDTO);


        } catch (DataBaseException e) {
            ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(JobDTOFactory.createJobsDTO());
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(JobDTOFactory.createJobsDTO());
        }


    }

    public void fillJobKey() {
        Object o = getLovBaseBean().getSelectedDTOS().get(0);
        if (o != null && o instanceof IJobsDTO) {
            System.out.print("key" + ((IJobsDTO)getLovBaseBean().getSelectedDTOS().get(0)).getJobKey());
            setJobKey(((IJobsDTO)getLovBaseBean().getSelectedDTOS().get(0)).getJobKey());
        }
        getLovBaseBean().closeLovDiv();
    }

    //    public void checkForBgtType(IOrgCatsEntityKey catsEntityKey) {
    //        if (catsEntityKey.equals(ORG_CAT_CODE_ATT_BGT) ||
    //            catsEntityKey.equals(ORG_CAT_CODE_SPR_BGT)) {
    //            IBgtTypesDTO bgtTypesDTO = BgtDTOFactory.createBgtTypesDTO();
    //            try {
    //                bgtTypesDTO = (IBgtTypesDTO)BgtClientFactory.getBgtTypesClient().getByIdInCenter(BgtEntityKeyFactory.createBgtTypesEntityKey(BGT_TYPE_FOR_ATT_SPR));
    //            } catch (SharedApplicationException e) {
    //                e.printStackTrace();
    //            } catch (DataBaseException e) {
    //                 e.printStackTrace();
    //            }
    //            ((IEmployeesDTO)getPageDTO()).setBgtTypesDTO(bgtTypesDTO);
    //            ((IEmployeesDTO)getPageDTO()).setBgtTypeKey(bgtTypesDTO.getCode().getKey());
    //        }
    //        else {
    //            List bgtTypes = null;
    //            try {
    //                bgtTypes = BgtClientFactory.getBgtTypesClient().getCodeName();
    //            } catch (DataBaseException e) {
    //                e.printStackTrace();
    //            }
    //            bgtTypes.remove(1L);
    //            System.out.println("BgtTypes"+bgtTypes.size());
    //        }
    //    }

    public boolean checkIsCandidatePersonValid() {
        boolean valid = true;
        IEmployeesCMTClient employeesCMTClient = EmpClientFactory.getEmployeesCMTClient();
        KwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (KwtCitizensResidentsDTO)((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO();
        KwtCitizensResidentsEntityKey kwtEntityKey = (KwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode();
        Long civilId = kwtEntityKey.getCivilId();
        if (civilId != null && !civilId.equals("")) {
            try {

                if (((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey() != null) {
                    if (employeesCMTClient.isEmployeeExist(civilId))
                        valid = false;
                }
            } catch (DataBaseException e) {
                e.printStackTrace();
                return valid;
            } catch (Exception e) {
                e.printStackTrace();
                return valid;
            }

        }
        return valid;
    }


    public void cancelSearchLovDiv() {
        showListOfValuesDiv();
    }

    /**
     *This method prepare page To do business and set Stage
     * @param stage
     */
    public void showViewByStage(Long stage) {
        System.out.println(" ============================== " + stage + " ==================== ");
        setStageId(stage.toString());
        configBean.resetAll();
        switch (Integer.parseInt(stage.toString())) {

        case HireStageConst.HIRE_STAGE_COMPLETING_JOB_NAME:
            //fatwa-Contract ::gary_morag3t_Talb_from_Dewan
            configBean.resetAll();
            configBean.selectionFatwaReview1();
            break;
        case HireStageConst.HIRE_STAGE_FIN_DEGREE_EQUVLANT:
            //fatwa-Contract ::matlob_t7ded-draga_malya_mo3adla
            configBean.fatwaSalDegreeDetectInContract();
            break;
        case HireStageConst.HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT:
            //fatwa-CRS :: Order Refused
            configBean.orderRefusedFormDewan();
            break;

        case MINISTRY_VIEW_CONST: // for ministry View
            configBean.ministryView();
            ((IEmpCandidatesDTO)getPageDTO()).setBgtTypesDTO(getBgtTypesDTO());
            break;
            // added by M.abdelsabour
            //case 10:توظي�? مركزى جارى إستيقاء الدرجة المالية
        case HireStageConst.HIRE_STAGE_PROGRESS_FIN_DEGREE:
            configBean.orderReturnToChoiceDept();
            break;

        default:
            configBean.resetAll();

        }


    }

    public void showListOfValuesDiv() {
        titleFlageOfDiv = "employees_work_centerInDiv";
        getLovBaseBean().setMyTableData(new ArrayList());
        getLovBaseBean().getPagingBean().updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 0));
        getLovBaseBean().setCurrentPageIndex(1);
        getLovBaseBean().setOldPageIndex(0);
        getLovBaseBean().getMyDataTable().setFirst(0);
        setWorkCenterMode(true);
        setRenderLovDiv(true);
        setJobMode(false);
        if (getLovBaseBean().isUsingPaging()) {
            getLovBaseBean().generatePagingRequestDTO("fatwaReviewListBean", "showListOfValuesDivWithPaging");
        } else {
            try {
                getLovBaseBean().setMyTableData(getWrkCenterList());
                if (getLovBaseBean().getMyDataTable() != null)
                    getLovBaseBean().getMyDataTable().setFirst(0);

            } catch (Exception e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            }
        }

        getLovBaseBean().getOkCommandButton().setReRender("WorkCenter_Panel,suggestedJobPanelGrp");
        getLovBaseBean().setReturnMethodName("fatwaReviewListBean.wrkCenterValue");
        getLovBaseBean().setSearchMethod("fatwaReviewListBean.searchWrkCenters");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("fatwaReviewListBean.cancelSearchLovDiv");
    }

    //    public void budgetTypeInformation() {
    //
    //            CSCSecurityInfoHelper.getLoggedInMinistry();
    //
    //
    //
    //    }

    public void showListOfValuesDivForJob() {
        titleFlageOfDiv = "job_name_lovdiv";
        getLovBaseBean().setMyTableData(new ArrayList());
        getLovBaseBean().getPagingBean().updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 0));
        getLovBaseBean().setCurrentPageIndex(1);
        getLovBaseBean().setOldPageIndex(0);
        getLovBaseBean().getMyDataTable().setFirst(0);
        setWorkCenterMode(false);
        setRenderLovDiv(true);
        setJobMode(true);
        getLovBaseBean().setUsingPaging(true);
        if (getLovBaseBean().isUsingPaging()) {
            getLovBaseBean().generatePagingRequestDTO("fatwaReviewListBean", "getJobListForMinWithPaging");

        }

        getLovBaseBean().getOkCommandButton().setReRender("WorkCenter_Panel,suggestedJobPanelGrp,selected_job_panel_grp");
        getLovBaseBean().setReturnMethodName("fatwaReviewListBean.jobForMinValue");
        getLovBaseBean().setSearchMethod("fatwaReviewListBean.searchJob");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("fatwaReviewListBean.cancelSearchLovDivForJob");
    }


    public void showListOfValuesDivForJobCrs() {
        titleFlageOfDiv = "job_name_lovdiv";
        getLovBaseBean().setMyTableData(new ArrayList());
        getLovBaseBean().getPagingBean().updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 0));
        getLovBaseBean().setCurrentPageIndex(1);
        getLovBaseBean().setOldPageIndex(0);
        getLovBaseBean().getMyDataTable().setFirst(0);
        setWorkCenterMode(false);
        setJobMode(true);
        setRenderLovDiv(true);
        getLovBaseBean().setUsingPaging(true);
        if (getLovBaseBean().isUsingPaging()) {
            getLovBaseBean().generatePagingRequestDTO("fatwaReviewListBean", "getJobListForMinWithPaging");

        }

        getLovBaseBean().getOkCommandButton().setReRender("WorkCenter_Panel,suggestedJobPanelGrp,selected_job_panel_grp");
        getLovBaseBean().setReturnMethodName("fatwaReviewListBean.jobForCrs");
        getLovBaseBean().setSearchMethod("fatwaReviewListBean.searchJob");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("fatwaReviewListBean.cancelSearchLovDivForJob");
    }


    public void cancelSearchLovDivForJob() {
        showListOfValuesDivForJob();
    }

    public void searchWrkCenters(Object searchType, Object searchQuery) {

        if (searchQuery != null && !searchQuery.toString().equals("") && searchType != null &&
            !searchType.equals("")) {

            getLovBaseBean().setSearchMode(true);

            IWorkCentersSearchCriteriaDTO srchDTO = OrgDTOFactory.createWorkCentersSearchCriteriaDTO();

            srchDTO.setMinCode(getManagedConstantsBean().getMinCode());

            if (searchQuery != null && !searchQuery.equals("")) {

                if (searchType.toString().equals("0")) {
                    srchDTO.setWorkCode(searchQuery.toString());
                } else if (searchType.toString().equals("1")) {
                    srchDTO.setWorkName(searchQuery.toString());
                }

                if (getLovBaseBean().isUsingPaging()) {

                    getLovBaseBean().generatePagingRequestDTO("fatwaReviewListBean", "searchWrkCentersWithPaging",
                                                              srchDTO);
                    getLovBaseBean().resetPageIndex();

                } else {

                    try {

                        IWorkCentersClient workCentersClient = OrgClientFactory.getWorkCentersClient();

                        getLovBaseBean().setMyTableData(workCentersClient.search(srchDTO));

                        getLovBaseBean().getMyDataTable().setFirst(0);

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

                }

            }

        }

    }

    public PagingResponseDTO searchWrkCentersWithPaging(PagingRequestDTO request) {

        IWorkCentersSearchCriteriaDTO srchDTO = (IWorkCentersSearchCriteriaDTO)request.getSearchDTO();

        try {

            IWorkCentersClient workCentersClient = OrgClientFactory.getWorkCentersClient();

            return new PagingResponseDTO(workCentersClient.search(srchDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return new PagingResponseDTO(new ArrayList());
        }

    }


    public PagingResponseDTO showListOfValuesDivWithPaging(PagingRequestDTO request) {
        request = null;
        return new PagingResponseDTO(getWrkCenterList());
    }

    public void wrkCenterValue() {
        if (getLovBaseBean().getSelectedDTOS() != null && getLovBaseBean().getSelectedDTOS().get(0) != null &&
            getLovBaseBean().getSelectedDTOS().get(0).getCode() != null) {
            try {
                ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(null);
                ((IEmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setSuggestedJobCode("");

                setWorkCenterName(getLovBaseBean().getSelectedDTOS().get(0).getName());
                setMinWorkCenter(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKeys()[1].toString());
                ((IEmpCandidatesDTO)getPageDTO()).setWorkCentersDTO(getLovBaseBean().getSelectedDTOS().get(0));
                ((IEmpCandidatesDTO)getPageDTO()).setBgtPrgmKey(((IWorkCentersDTO)getLovBaseBean().getSelectedDTOS().get(0)).getBgtProgramsDTO().getCode().getKeys()[0].toString());
                setJobNameForMin("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void jobForMinValue() {
        if (jobFilter != null && jobFilter.getSelectedDTOS() != null && !jobFilter.getSelectedDTOS().isEmpty()) {
            try {
                if (!stageId.equals(String.valueOf(HireStageConst.HIRE_STAGE_JOB_NAME_REQUIRED))) {
                    setJobNameForMin(jobFilter.getSelectedDTOS().get(0).getName());
                    setJobKey(jobFilter.getSelectedDTOS().get(0).getCode().getKey());
                }
                ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(jobFilter.getSelectedDTOS().get(0));
                setJobKey(jobFilter.getSelectedDTOS().get(0).getCode().getKey());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void jobForCrs() {
        if (getLovBaseBean().getSelectedDTOS() != null && getLovBaseBean().getSelectedDTOS().get(0) != null &&
            getLovBaseBean().getSelectedDTOS().get(0).getCode() != null) {
            try {
                ((IEmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setSuggestedJobCode(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey());
                if (!stageId.equals(String.valueOf(HireStageConst.HIRE_STAGE_JOB_NAME_REQUIRED))) {
                    setJobNameForMin(getLovBaseBean().getSelectedDTOS().get(0).getName());
                }
                ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(getLovBaseBean().getSelectedDTOS().get(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void cancelSearchJob() {

        setSearchJobMode(false);
        setSearchQuery("");
        setJobSearchType("0");
        setSelectedRadio("");
        jobList = null;
    }

    public PagingResponseDTO getJobListForMinWithPaging(PagingRequestDTO request) {
        request = null;
        try {
            IJobSearchCriteriaDTO jobSearchCriteriaDTO = JobDTOFactory.createJobSearchCriteriaDTO();
            jobSearchCriteriaDTO.setJobFreez(ISystemConstant.NON_FREEZE_FLAG);
            jobSearchCriteriaDTO.setMinCode(getMinistryCode());
            jobList = JobClientFactory.getJobsClient().getCodeNameOfMinistryJobsAndPublicJobs(jobSearchCriteriaDTO);
        } catch (Exception e) {
            e.printStackTrace();
            jobList = new ArrayList();
        }
        return new PagingResponseDTO(jobList);

    }

    public PagingResponseDTO getJobListWithPaging(PagingRequestDTO request) {
        request = null;
        return new PagingResponseDTO(fillJobList());
    }

    public List fillJobList() {
        setUsingPaging(true);
        if (getPageDTO() != null && ((IEmpCandidatesDTO)getPageDTO()).getWorkCentersDTO() != null &&
            ((IEmpCandidatesDTO)getPageDTO()).getWorkCentersDTO().getCode() != null) {
            try {
                jobList =
                        JobClientFactory.getJobsClient().getCodeNameByWorkCenterCodeAndHierarchicalFlag(((IWorkCentersEntityKey)((IEmpCandidatesDTO)getPageDTO()).getWorkCentersDTO().getCode()).getWrkCode());

                workCenterHasJobs = true;
            } catch (Exception e) {
                e.printStackTrace();
                jobList = new ArrayList();
            }

        } else {
            jobList = new ArrayList();
            workCenterHasJobs = false;
        }

        return jobList;
    }

    public void setRenderJobDiv(boolean renderJobDiv) {
        this.renderJobDiv = renderJobDiv;
    }

    public boolean isRenderJobDiv() {
        return renderJobDiv;
    }

    public void resetDegreeAndRaiseList() {
        setEqTypeTemp(false);
        setDegreeCode(null);
        setRaiseName(null);
        setDegreeList(new ArrayList());
        setRaiseCode(null);
        setRaiseList(new ArrayList());

    }

    public void initTreeDivBean() {
        treedivBean = (TreeDivBean)evaluateValueBinding("treeDivBean");
        treedivBean.setMyTableData(new ArrayList());
        treedivBean.setBundleName("com.beshara.csc.hr.emp.presentation.resources.emp_");
        treedivBean.setRootName("raise");
        treedivBean.setClient(SalClientFactory.getSalElementGuidesClient());
        treedivBean.setPageDTO(SalDTOFactory.createSalElementGuidesDTO());
        treedivBean.setDto(SalDTOFactory.createSalElementGuidesDTO());
        treedivBean.setDtoDetails(SalDTOFactory.createSalElementGuidesDTO());
        treedivBean.getOkCommandButton().setReRender("msgShow,employees_degree");
        treedivBean.setCancelSearchMethod("fatwaReviewListBean.cancelTreeSearch");
        ValueBinding vb =
            FacesContext.getCurrentInstance().getApplication().createValueBinding("#{treeDivBean.enabledNotLeaf && !treeDivBean.enabledRoot && (treeDivBean.selectionNo==1)}");
        treedivBean.getOkCommandButton().setValueBinding("rendered", vb);

        treedivBean.setEnableSearchByCode(true);


    }

    public void searchJob(Object searchType, Object searchQuery) {
        try {

            getLovBaseBean().setSearchMode(true);
            getLovBaseBean().getPagingBean().updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 0));
            getLovBaseBean().setCurrentPageIndex(1);
            getLovBaseBean().setOldPageIndex(0);

            IJobSearchCriteriaDTO jobSearchCriteriaDTO = JobDTOFactory.createJobSearchCriteriaDTO();

            if ((searchQuery != null || !searchQuery.equals(""))) {
                if (workCenterMode) {
                    String wrkCenterCode = null;
                    if (getPageDTO() != null && ((IEmpCandidatesDTO)getPageDTO()).getWorkCentersDTO() != null &&
                        ((IEmpCandidatesDTO)getPageDTO()).getWorkCentersDTO().getCode() != null)
                        wrkCenterCode =
                                ((IWorkCentersEntityKey)((IEmpCandidatesDTO)getPageDTO()).getWorkCentersDTO().getCode()).getWrkCode();

                    jobSearchCriteriaDTO.setWorkCenterCode(wrkCenterCode);
                }
                if (searchType.toString().equals("0")) {
                    jobSearchCriteriaDTO.setJobKey(searchQuery.toString());
                } else if (searchType.toString().equals("1")) {
                    jobSearchCriteriaDTO.setJobName(searchQuery.toString());
                }
                jobSearchCriteriaDTO.setJobFreez(ISystemConstant.NON_FREEZE_FLAG);
                jobSearchCriteriaDTO.setMinCode(getMinistryCode());
                getLovBaseBean().setUsingPaging(true);
                if (getLovBaseBean().isUsingPaging()) {
                    getLovBaseBean().generatePagingRequestDTO("reviewMinListBean", "searchJobWithPaging",
                                                              jobSearchCriteriaDTO);
                    getLovBaseBean().resetPageIndex();

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public PagingResponseDTO searchJobWithPaging(PagingRequestDTO request) {
        setSelectedRadio("");
        IJobSearchCriteriaDTO srchDTO = (IJobSearchCriteriaDTO)request.getSearchDTO();
        try {

            //return new PagingResponseDTO(JobClientFactory.getJobsClient().searchJobs(srchDTO));
            return new PagingResponseDTO(JobClientFactory.getJobsClient().getCodeNameOfMinistryJobsAndPublicJobs(srchDTO));

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            return new PagingResponseDTO(new ArrayList());
        } catch (DataBaseException e) {
            e.printStackTrace();
            return new PagingResponseDTO(new ArrayList());
        }
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
      
        app.setShowdatatableContent(false);
        app.setShowContent1(true);
        app.setShowSearch(false);
        app.setShowbar(true);
        app.setShowpaging(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(true);
        app.setShowDelAlert(true);
        app.setShowDelConfirm(false);
        app.setShowLovDiv(true);
        app.setShowCustomDiv1(true);
        app.setShowTreediv(true);
        return app;
    }

    public PagingResponseDTO initMainDataBean(PagingRequestDTO request) {
        request = null;

        return new PagingResponseDTO(new ArrayList());
    }


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
            getLovBaseBean().generatePagingRequestDTO("fatwaReviewListBean", "showCaderListOfValuesDivWithPaging");
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
        getLovBaseBean().setReturnMethodName("fatwaReviewListBean.caderValue");
        getLovBaseBean().setSearchMethod("fatwaReviewListBean.searchCader");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("fatwaReviewListBean.cancelSearchCaderLovDiv");
        getLovBaseBean().getMyDataTable().setFirst(0);
    }

    public void cancelTreeSearch() {

        treedivBean.setSearchQuery("");
        treedivBean.getHtmlTree().collapseAll();
        treedivBean.setSearchMode(false);
        treedivBean.setSelectionNo(0);
        treedivBean.setTreeNodeBase(null);
        treedivBean.setShowTreeContent(true);

    }

    public String backAction() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        if (!"".equals(backActionMethodName))
            executeMethodBinding(backActionMethodName, null);
        return getBckBtnNavigationCase();
    }

    public void OpenTreeDiv() {
        cancelTreeSearch();
        treedivBean.setMyTableData(new ArrayList());

        try {
            treedivBean.setMyTableData(SalClientFactory.getSalElementGuidesClient().getDegreesTreeByGroup(groupCode));
            treedivBean.setClientValueBinding("fatwaReviewListBean.salElementClient");

        } catch (SharedApplicationException e) {
            treedivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        } catch (Exception e) {
            treedivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        }
        treedivBean.setTreeNodeBase(null);
        treedivBean.setShowTreeContent(true);

        MethodBinding mb =
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{fatwaReviewListBean.addSelectedRaise}",
                                                                                   null);
        treedivBean.getOkCommandButton().setAction(mb);
        treedivBean.setEnabledNotLeaf(false);
        ValueBinding vb =
            FacesContext.getCurrentInstance().getApplication().createValueBinding("#{treeDivBean.enabledNotLeaf && !treeDivBean.enabledRoot && (treeDivBean.selectionNo==1)}");
        treedivBean.getOkCommandButton().setValueBinding("rendered", vb);
        treedivBean.getOkCommandButton().setReRender("employees_degree,validScriptPanel");

    }

    public void addSelectedRaise() {
        try {
            if (treedivBean.getDtoDetails() != null && treedivBean.getDtoDetails().getCode() != null) {
                raiseCode = treedivBean.getDtoDetails().getCode().getKey();
                raiseName = treedivBean.getDtoDetails().getName();
                addRaiseToEmployee();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addRaiseToEmployee() {
        ISalElementGuidesDTO selectedSalElementGuidesDTO = SalDTOFactory.createSalElementGuidesDTO();
        ISalElementGuidesDTO selectedEqSalElementGuidesDTO = SalDTOFactory.createSalElementGuidesDTO();
        try {
            if (getRaiseCode() != null) {
                selectedSalElementGuidesDTO =
                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getById(SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.parseLong(getRaiseCode())));
                ((IEmpCandidatesDTO)getPageDTO()).setEmpCndSalaryElementsList(new ArrayList());
                ISalEmpSalaryElementsDTO salaryElementDTO = SalDTOFactory.createSalEmpSalaryElementsDTO();
                salaryElementDTO.setSalElementGuidesDTO(selectedSalElementGuidesDTO);
                salaryElementDTO.setSalEqElementGuidesDTO(selectedEqSalElementGuidesDTO);
                ((IEmpCandidatesDTO)getPageDTO()).getEmpCndSalaryElementsList().add(salaryElementDTO);
            }
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateSalariesData() {
        try {
            Long raiseCode = null;
            Long degreeCode = null;
            Long groupCode = null;
            Long caderCode = null;

            ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
            Long civilId =
                ((IKwtCitizensResidentsEntityKey)((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO().getCode()).getCivilId();


            ISalEmpSalaryElementsDTO salaryElementDTO =
                (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(civilId,
                                                                                                                     ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);

            //Raise
            if (salaryElementDTO != null && salaryElementDTO.getCode() != null) {
                raiseCode =
                        ((ISalElementGuidesEntityKey)salaryElementDTO.getSalElementGuidesDTO().getCode()).getElmguideCode();
                ((IEmpCandidatesDTO)getPageDTO()).getEmpCndSalaryElementsList();
                if (raiseCode != null) {
                    raiseDTO =
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(raiseCode);
                    setRaiseName(raiseDTO.getName());
                }
                //Degree
                if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                    degreeCode = ((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode();
                }
                if (degreeCode != null) {
                    degreeDTO =
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(degreeCode);
                }
                //Group
                if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                    groupCode = ((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode();
                }

                if (groupCode != null) {

                    groupDTO =
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(groupCode);
                    setGroupCode(groupCode);
                    setGroupName(groupDTO.getName());
                    //getGroupList();

                }
                //Cader
                if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                    caderCode = ((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode();
                    setCaderCode(caderCode);
                    setCaderName(groupDTO.getParentObject().getName());
                }


            }
        } catch (SharedApplicationException e) {
            // TODO
            e.printStackTrace();
        } catch (DataBaseException e) {
            // TODO
            e.printStackTrace();
        }
    }


    public void cancelSearchCaderLovDiv() {
        showCaderListOfValuesDiv();
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

                getLovBaseBean().generatePagingRequestDTO("fatwaReviewListBean", "searchCaderWithPaging");
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

    private Long getMinistryCode() {
        if (getPageDTO() != null) {
            IEmpCandidatesDTO employeesDTO = ((IEmpCandidatesDTO)getPageDTO());
            //            return ((IWorkCentersEntityKey)employeesDTO.getWorkCentersDTO().getCode()).getMinCode();
            return employeesDTO.getMinCode();
        }
        return null;
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

    public void caderValue() {
        if (getLovBaseBean().getSelectedDTOS() != null && getLovBaseBean().getSelectedDTOS().get(0) != null &&
            getLovBaseBean().getSelectedDTOS().get(0).getCode() != null) {
            try {
                setCaderCode(Long.parseLong(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString()));
                setCaderName(getLovBaseBean().getSelectedDTOS().get(0).getName());
                resetSelectedJobData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void resetSelectedJobData() {

    }

    public PagingResponseDTO showCaderListOfValuesDivWithPaging(PagingRequestDTO request) {
        request = null;
        return new PagingResponseDTO(getCaderList());
    }


    public void setBckBtnNavigationCase(String bckBtnNavigationCase) {
        this.bckBtnNavigationCase = bckBtnNavigationCase;
    }

    public String getBckBtnNavigationCase() {
        return bckBtnNavigationCase;
    }

    public void setSaveStateList(List saveStateList) {
        this.saveStateList = saveStateList;
    }

    public List getSaveStateList() {
        return saveStateList;
    }


    public void setConfigBean(ReviewConfigBean configBean) {
        this.configBean = configBean;
    }

    public ReviewConfigBean getConfigBean() {
        return configBean;
    }

    public void setCaderList(List caderList) {
        this.caderList = caderList;
    }

    public List initListForgetSal() {
        List list = new ArrayList();
        list.add(IEMPConstants.CONTRACT_TYPE);
        list.add(IEMPConstants.ESTANA_TYPE);
        list.add(2);
        list.add(Boolean.FALSE);
        return list;
    }

    public List getCaderList() {
        if (caderList == null || (caderList != null && caderList.size() == 0)) {
            try {
                caderList =
                        SalClientFactory.getSalElementGuidesClient().getCaderCodeName(getMinistryCode(), initListForgetSal());
            } catch (DataBaseException e) {
                e.printStackTrace();
                caderList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                caderList = new ArrayList();
            }

        }
        return caderList;
    }

    public void setGroupList(List groupList) {
        this.groupList = groupList;
    }

    public List getGroupList() {
        if (groupList == null || (groupList != null && groupList.size() == 0)) {
            if (caderCode != null && !caderCode.equals("")) {
                try {
                    groupList =
                            SalClientFactory.getSalElementGuidesClient().getGroupCodeNameByCader(caderCode, initListForgetSal());
                    //    groupList = SalClientFactory.getSalElementGuidesClient().getLocalGroupCodeName(caderCode);
                } catch (DataBaseException f) {
                    f.printStackTrace();
                    groupList = new ArrayList();
                    treedivBean.setMyTableData(new ArrayList());
                } catch (Exception f) {
                    f.printStackTrace();
                    groupList = new ArrayList();
                    treedivBean.setMyTableData(new ArrayList());
                }
            }
        }
        return groupList;
    }

    public void setWorkCenterMode(boolean workCenterMode) {
        this.workCenterMode = workCenterMode;
    }

    public boolean isWorkCenterMode() {
        return workCenterMode;
    }

    public void setCaderCode(Long caderCode) {
        this.caderCode = caderCode;
    }

    public Long getCaderCode() {
        return caderCode;
    }

    public void setCaderName(String caderName) {
        this.caderName = caderName;
    }

    public String getCaderName() {
        return caderName;
    }

    public void setRenderLovDiv(boolean renderLovDiv) {
        this.renderLovDiv = renderLovDiv;
    }

    public boolean isRenderLovDiv() {
        return renderLovDiv;
    }

    public void setTitleFlageOfDiv(String titleFlageOfDiv) {
        this.titleFlageOfDiv = titleFlageOfDiv;
    }

    public String getTitleFlageOfDiv() {
        return titleFlageOfDiv;
    }

    public IBasicClient getSalElementClient() {
        return SalClientFactory.getSalElementGuidesClient();
    }

    public void setEqTypeTemp(boolean eqTypeTemp) {
        this.eqTypeTemp = eqTypeTemp;
    }

    public boolean isEqTypeTemp() {
        return eqTypeTemp;
    }

    public void setRaiseCode(String raiseCode) {
        this.raiseCode = raiseCode;
    }

    public String getRaiseCode() {
        return raiseCode;
    }

    public void setRaiseName(String raiseName) {
        this.raiseName = raiseName;
    }

    public String getRaiseName() {
        return raiseName;
    }

    public void setTreedivBean(TreeDivBean treedivBean) {
        this.treedivBean = treedivBean;
    }

    public TreeDivBean getTreedivBean() {
        return treedivBean;
    }

    public void setGroupCode(Long groupCode) {
        this.groupCode = groupCode;
    }

    public Long getGroupCode() {
        return groupCode;
    }

    public void setDegreeCode(Long degreeCode) {
        this.degreeCode = degreeCode;
    }

    public Long getDegreeCode() {
        return degreeCode;
    }

    private Long getCivilId() {
        KwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (KwtCitizensResidentsDTO)((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO();
        KwtCitizensResidentsEntityKey kwtEntityKey = (KwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode();
        Long civilId = kwtEntityKey.getCivilId();

        return civilId;
    }

    public void onDegreeChange() {
        try {
            validCond = EmpClientFactory.getEmployeesClient().isConditionFromGrsValid(getCivilId(), degreeCode);
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
            raiseCode = "";
        }
    }

    public void onDegreeChangedRaise(){
            raiseList = new ArrayList();
            raiseCode = "";
            fillRaiseList();
        }

    public void fillRaiseList() {
        if (degreeCode != null && !degreeCode.equals(getVirtualLongValue())) {
            try {
                raiseList =
                        SalClientFactory.getSalElementGuidesClient().getRaiseCodeNameByDegree(degreeCode, initListForgetSal());
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
        if (raiseCode != null) {
            try {


                raiseDTO =
                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.parseLong(raiseCode));

                if (raiseDTO.getValue() == null) {
                    setTotalRewardAccepted("");
                    setEnableAddRewardButton(false);
                } else {
                    AddBounceBean addBounceBean = AddBounceBean.getInstance();
                    if (addBounceBean.getTotalBouncseNotBasicSal() != null &&
                        addBounceBean.getTotalBouncseNotBasicSal() > 0F) {
                        setTotalRewardAccepted(String.valueOf(addBounceBean.getTotalBouncseNotBasicSal() +
                                                              raiseDTO.getValue()));
                    } else {
                        setTotalRewardAccepted(String.valueOf(raiseDTO.getValue()));
                    }
                    setEnableAddRewardButton(true);
                }
            } catch (DataBaseException e) {
                e.printStackTrace();
                setTotalRewardAccepted("");
                setEnableAddRewardButton(false);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                setTotalRewardAccepted("");
                setEnableAddRewardButton(false);
            }
        }

    }

    public String addBounses() {
        AddBounceBean addBounceBean = AddBounceBean.getInstance();
        addBounceBean.setBackActionMethodName("fatwaReviewListBean.reloadDataForReviewListBean");
        addBounceBean.setBckBtnNavigationCase("fatwaReviewRequestPage");
        addBounceBean.setAfterSaveMethodName("fatwaReviewListBean.fillPageDTOByBounse");
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO();
        addBounceBean.setCivilId(kwtCitizensResidentsDTO.getCode().getKey());
        addBounceBean.setFullName(kwtCitizensResidentsDTO.getFullName());
        addBounceBean.setBasicSalary(Float.valueOf(getRaiseDTO().getValue()));
        addBounceBean.setTotalBounces(Float.valueOf(getTotalRewardAccepted()));
        addBounceBean.setEmpCandExtraDataList(getEmpCandExtraDataList());
        addBounceBean.setEmpCandidatesDTO((IEmpCandidatesDTO)getPageDTO());
        addBounceBean.getSaveStateAddBounceList().add(getPageDTO());
        return "addbouncePage";
    }
    
    
    public String addBounsesApprove() {
        AddBounceBean addBounceBean = AddBounceBean.getInstance();
        addBounceBean.setBackActionMethodName("fatwaReviewListBean.reloadDataForReviewListBean");
        addBounceBean.setBckBtnNavigationCase("fatwaReviewRequestPage");
        addBounceBean.setAfterSaveMethodName("fatwaReviewListBean.fillPageDTOByBounse");
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO();
        addBounceBean.setCivilId(kwtCitizensResidentsDTO.getCode().getKey());
        addBounceBean.setFullName(kwtCitizensResidentsDTO.getFullName());
        addBounceBean.setBasicSalary(Float.valueOf(getRaiseDTO().getValue()));
        addBounceBean.setTotalBounces(Float.valueOf(getTotalRewardAccepted()));
        addBounceBean.setEmpCandExtraDataList(getEmpCandExtraDataList());
        addBounceBean.setEmpCandidatesDTO((IEmpCandidatesDTO)getPageDTO());
        addBounceBean.getSaveStateAddBounceList().add(getPageDTO());
        return "addbounceApprovePage";
    }

    public void fillPageDTOByBounse() {
        AddBounceBean addBounceBean = AddBounceBean.getInstance();
        setEmpCandExtraDataList(addBounceBean.getEmpCandExtraDataList());
        //        setPageDTO(addBounceBean.getEmpCandidatesDTO());
    }

    public void reloadDataForReviewListBean() {
        AddBounceBean addBounceBean = AddBounceBean.getInstance();
        setPageDTO((IBasicDTO)addBounceBean.getSaveStateAddBounceList().get(0));
        setTotalRewardAccepted(String.valueOf(addBounceBean.getTotalBounces()));
    }

    public void setDegreeList(List degreeList) {
        this.degreeList = degreeList;
    }

    public List getDegreeList() {
        if (degreeList == null || (degreeList != null && degreeList.size() == 0)) {
            if (groupCode != null && !groupCode.equals(getVirtualLongValue())) {
                try {
                    degreeList =
                            (List)SalClientFactory.getSalElementGuidesClient().getDegreeCodeNameByGroupSorted(groupCode, initListForgetSal());
                    // degreeList =(List)SalClientFactory.getSalElementGuidesClient().getDegreesTreeByGroupFirstLevel(groupCode);
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return degreeList;
    }

    public void setBudgetTypeList(List budgetTypeList) {
        this.budgetTypeList = budgetTypeList;
    }

    public List<IBasicDTO> getBudgetTypeList() {
        if ((budgetTypeList == null || budgetTypeList.size() == 0))
            try {
                budgetTypeList = BgtClientFactory.getBgtTypesClient().getCodeName();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return budgetTypeList;
    }

    public void setJobList(List jobList) {
        this.jobList = jobList;
    }

    public List getJobList() {
        return jobList;
    }

    public void setSearchJobMode(boolean searchJobMode) {
        this.searchJobMode = searchJobMode;
    }

    public boolean isSearchJobMode() {
        return searchJobMode;
    }

    public void setJobSearchType(String jobSearchType) {
        this.jobSearchType = jobSearchType;
    }

    public String getJobSearchType() {
        return jobSearchType;
    }

    public void setWorkCenterHasJobs(boolean workCenterHasJobs) {
        this.workCenterHasJobs = workCenterHasJobs;
    }

    public boolean isWorkCenterHasJobs() {
        return workCenterHasJobs;
    }

    public void setJobListSize(int jobListSize) {
        this.jobListSize = jobListSize;
    }

    public int getJobListSize() {
        if (isUsingPaging()) {

            PagingBean pagingBean = getPagingBean();

            try {
                if (getPagingBean().getMyPagedDataModel() == null) {

                    getPagingBean().getMyPagedDataModel();

                }
            } catch (NoResultException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }

            return pagingBean.getTotalListSize();

        } else {
            if (jobList != null)
                jobListSize = jobList.size();
        }
        return jobListSize;
    }

    public void setMinWorkCenter(String minWorkCenter) {
        this.minWorkCenter = minWorkCenter;
    }

    public String getMinWorkCenter() {
        return minWorkCenter;
    }

    public void setWorkMinistriesList(List workMinistriesList) {
        this.workMinistriesList = workMinistriesList;
    }

    public void fillWorkMinistriesList() {

        if ((workMinistriesList == null || workMinistriesList.size() == 0)) {

            try {
                IMinistriesEntityKey ministryEntityKey =
                    OrgEntityKeyFactory.createMinistriesEntityKey(getMinistryCode());
                workMinistriesList = OrgClientFactory.getWorkCentersClient().getAllByMinistry(ministryEntityKey);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                workMinistriesList = new ArrayList();
            } catch (DataBaseException e) {
                e.printStackTrace();
                workMinistriesList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                workMinistriesList = new ArrayList();
            }
        }
        //  return workMinistriesList;
    }

    public void setWrkCenterList(List wrkCenterList) {
        this.wrkCenterList = wrkCenterList;
    }

    public List getWrkCenterList() {
        try {
            if ((wrkCenterList == null || wrkCenterList.size() == 0)) {
                IMinistriesEntityKey ministryEntityKey =
                    OrgEntityKeyFactory.createMinistriesEntityKey(getMinistryCode());
                wrkCenterList = OrgClientFactory.getWorkCentersClient().getAllByMinistry(ministryEntityKey);
            }
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

    public void setBiznasActions(BussinessService biznasActions) {
        this.biznasActions = biznasActions;
    }

    public BussinessService getBiznasActions() {
        return biznasActions;
    }

    public void setWorkCenterName(String workCenterName) {
        this.workCenterName = workCenterName;
    }

    public String getWorkCenterName() {
        return workCenterName;
    }

    public void setJobNameForMin(String jobNameForMin) {
        this.jobNameForMin = jobNameForMin;
    }

    public String getJobNameForMin() {
        return jobNameForMin;
    }

    public void setBackActionMethodName(String backActionMethodName) {
        this.backActionMethodName = backActionMethodName;
    }

    public String getBackActionMethodName() {
        return backActionMethodName;
    }

    public void setPageBeanName(String pageBeanName) {
        this.pageBeanName = pageBeanName;
    }

    public String getPageBeanName() {
        return pageBeanName;
    }

    public void setLinesResultList(List<IResultDTO> linesResultList) {
        this.linesResultList = linesResultList;
    }

    public List<IResultDTO> getLinesResultList() {
        return linesResultList;
    }


    public void setBgtTypesDTO(IBgtTypesDTO bgtTypesDTO) {
        this.bgtTypesDTO = bgtTypesDTO;
    }

    public IBgtTypesDTO getBgtTypesDTO() {
        return bgtTypesDTO;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getStageId() {
        return stageId;
    }


    public void setHireStageConst(HireStageConst hireStageConst) {
        this.hireStageConst = hireStageConst;
    }

    public HireStageConst getHireStageConst() {
        return hireStageConst;
    }

    public void setSelectedMinistery(String selectedMinistery) {
        this.selectedMinistery = selectedMinistery;
    }

    public String getSelectedMinistery() {
        return selectedMinistery;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setInvalidMinFileNo(boolean invalidMinFileNo) {
        this.invalidMinFileNo = invalidMinFileNo;
    }

    public boolean isInvalidMinFileNo() {
        return invalidMinFileNo;
    }

    public void setSuggestedCaderCode(Long suggestedCaderCode) {
        this.suggestedCaderCode = suggestedCaderCode;
    }

    public Long getSuggestedCaderCode() {
        return suggestedCaderCode;
    }

    public void setSuggestedCaderName(String suggestedCaderName) {
        this.suggestedCaderName = suggestedCaderName;
    }

    public String getSuggestedCaderName() {
        return suggestedCaderName;
    }

    public void setSuggestedGroupCode(Long suggestedGroupCode) {
        this.suggestedGroupCode = suggestedGroupCode;
    }

    public Long getSuggestedGroupCode() {
        return suggestedGroupCode;
    }

    public void setSuggestedRaiseCode(Long suggestedRaiseCode) {
        this.suggestedRaiseCode = suggestedRaiseCode;
    }

    public Long getSuggestedRaiseCode() {
        return suggestedRaiseCode;
    }

    public void setSuggestedRaiseName(String suggestedRaiseName) {
        this.suggestedRaiseName = suggestedRaiseName;
    }

    public String getSuggestedRaiseName() {
        return suggestedRaiseName;
    }

    public void setSuggestedDegreeCode(Long suggestedDegreeCode) {
        this.suggestedDegreeCode = suggestedDegreeCode;
    }

    public Long getSuggestedDegreeCode() {
        return suggestedDegreeCode;
    }

    public void setSuggestedGroupName(String suggestedGroupName) {
        this.suggestedGroupName = suggestedGroupName;
    }

    public String getSuggestedGroupName() {
        return suggestedGroupName;
    }

    public void setHasExperience(boolean hasExperience) {
        this.hasExperience = hasExperience;
    }

    public boolean isHasExperience() {
        return hasExperience;
    }

    public void setJobMode(boolean jobMode) {
        this.jobMode = jobMode;
    }

    public boolean isJobMode() {
        return jobMode;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setSuggestedDegreeName(String suggestedDegreeName) {
        this.suggestedDegreeName = suggestedDegreeName;
    }

    public String getSuggestedDegreeName() {
        return suggestedDegreeName;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getJobKey() {
        return jobKey;
    }

    public void setJobsDTO(IJobsDTO jobsDTO) {
        this.jobsDTO = jobsDTO;
    }

    public IJobsDTO getJobsDTO() {
        return jobsDTO;
    }

    public void setApprovedBgtTypeCode(String approvedBgtTypeCode) {
        this.approvedBgtTypeCode = approvedBgtTypeCode;
    }

    public String getApprovedBgtTypeCode() {
        return approvedBgtTypeCode;
    }

    public void setApprovedBgtTypeName(String approvedBgtTypeName) {
        this.approvedBgtTypeName = approvedBgtTypeName;
    }

    public String getApprovedBgtTypeName() {
        return approvedBgtTypeName;
    }

    public void setJobFilter(JobFilter jobFilter) {
        this.jobFilter = jobFilter;
    }

    public JobFilter getJobFilter() {
        return jobFilter;
    }

    public void setAcceptedJobName(String acceptedJobName) {
        this.acceptedJobName = acceptedJobName;
    }

    public String getAcceptedJobName() {
        return acceptedJobName;
    }

    public void showCRSCaderListOfValuesDiv() {
        titleFlageOfDiv = "cader";
        getLovBaseBean().setMyTableData(new ArrayList());
        getLovBaseBean().getPagingBean().updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 0));
        getLovBaseBean().setCurrentPageIndex(1);
        getLovBaseBean().setOldPageIndex(0);
        setWorkCenterMode(false);
        setJobMode(false);
        setRenderLovDiv(true);
        if (getLovBaseBean().isUsingPaging()) {
            getLovBaseBean().generatePagingRequestDTO("fatwaReviewListBean", "showCRSCaderListOfValuesDivWithPaging");
        } else {
            try {
                setCaderCRSList(new ArrayList());
                getLovBaseBean().setMyTableData(getCaderList());
                if (getLovBaseBean().getMyDataTable() != null)
                    getLovBaseBean().getMyDataTable().setFirst(0);

            } catch (Exception e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            }
        }
        getLovBaseBean().getOkCommandButton().setReRender("employeeCaderPanel,JobGrpMenu");
        getLovBaseBean().setReturnMethodName("fatwaReviewListBean.caderValue");
        getLovBaseBean().setSearchMethod("fatwaReviewListBean.searchCader");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("fatwaReviewListBean.cancelSearchCRSCaderLovDiv");
        getLovBaseBean().getMyDataTable().setFirst(0);
    }
    //case centeral Employement

    public PagingResponseDTO showCRSCaderListOfValuesDivWithPaging(PagingRequestDTO request) {
        request = null;
        return new PagingResponseDTO(getCaderCRSList());
    }

    public void cancelSearchCRSCaderLovDiv() {
        showCRSCaderListOfValuesDiv();
    }

    public void setCaderCRSList(List caderCRSList) {
        this.caderCRSList = caderCRSList;
    }

    public void resetCRSDegreeAndRaiseList() {
        setEqTypeTemp(false);
        setDegreeCode(null);
        setRaiseName(null);
        setDegreeCRSList(new ArrayList());
        setRaiseCode(null);
        setRaiseCRSList(new ArrayList());

    }

    public List getCaderCRSList() {
        if (caderCRSList == null || (caderCRSList != null && caderCRSList.size() == 0)) {
            try {
                caderCRSList =
                        SalClientFactory.getSalElementGuidesClient().getCaderCodeName(getMinistryCode(), initListForgetSal());
                //caderCRSList =                        SalClientFactory.getSalElementGuidesClient().getCaderCodeNameByElmMinistryCode(getMinistryCode());
            } catch (DataBaseException e) {
                e.printStackTrace();
                caderCRSList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                caderCRSList = new ArrayList();
            }

        }
        return caderCRSList;
    }

    public void setGroupCRSList(List groupCRSList) {
        this.groupCRSList = groupCRSList;
    }

    public List getGroupCRSList() {
        if (caderCode != null && !caderCode.equals("")) {
            try {
                //   groupCRSList = SalClientFactory.getSalElementGuidesClient().getLocalGroupCodeName(caderCode);
                groupCRSList =
                        SalClientFactory.getSalElementGuidesClient().getGroupCodeNameByCader(caderCode, initListForgetSal());
            } catch (DataBaseException f) {
                f.printStackTrace();
                groupCRSList = new ArrayList();
                treedivBean.setMyTableData(new ArrayList());
            } catch (Exception f) {
                f.printStackTrace();
                groupCRSList = new ArrayList();
                treedivBean.setMyTableData(new ArrayList());
            }
        }
        return groupCRSList;
    }

    public void setRaiseCRSList(List raiseCRSList) {
        this.raiseCRSList = raiseCRSList;
    }

    public void fillRaiseCRSList() {
        if (degreeCode != null && !degreeCode.equals("")) {
            try {
                raiseCRSList =
                        SalClientFactory.getSalElementGuidesClient().getRaiseCodeNameByDegree(degreeCode, initListForgetSal());
            } catch (DataBaseException e) {
                e.printStackTrace();
                raiseList = new ArrayList();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                raiseList = new ArrayList();
            }
        }
    }

    public List getRaiseCRSList() {
        return raiseCRSList;
    }

    public void setDegreeCRSList(List degreeCRSList) {
        this.degreeCRSList = degreeCRSList;
    }

    public List getDegreeCRSList() {
        if (groupCode != null && !groupCode.equals("")) {
            try {
                degreeCRSList =
                        (List)SalClientFactory.getSalElementGuidesClient().getDegreeCodeNameByGroupSorted(groupCode, initListForgetSal());
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return degreeCRSList;
    }
    
    
    public void onDegreeCRSChange() {
        try {
            validCond = EmpClientFactory.getEmployeesClient().isConditionFromGrsValid(getCivilId(), degreeCode);
        } catch (DataBaseException e) {
            e.printStackTrace();
            validCond = true;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            validCond = true;
        }

        if (validCond) {
            fillRaiseCRSList();
        } else {
            raiseCRSList = new ArrayList();
            setRaiseCode("");
        }
    }

    public void setHireSystemMode(String hireSystemMode) {
        this.hireSystemMode = hireSystemMode;
    }

    public String getHireSystemMode() {
        return hireSystemMode;
    }

    public List getWorkMinistriesList() {
        return workMinistriesList;
    }

    public void setTotalRewardSuggested(String totalRewardSuggested) {
        this.totalRewardSuggested = totalRewardSuggested;
    }

    public String getTotalRewardSuggested() {
        return totalRewardSuggested;
    }

    public void setTotalRewardAccepted(String totalRewardAccepted) {
        this.totalRewardAccepted = totalRewardAccepted;
    }

    public String getTotalRewardAccepted() {
        return totalRewardAccepted;
    }

    public void setEnableAddRewardButton(boolean enableAddRewardButton) {
        this.enableAddRewardButton = enableAddRewardButton;
    }

    public boolean isEnableAddRewardButton() {
        return enableAddRewardButton;
    }

    public void setRaiseDTO(ISalElementGuidesDTO raiseDTO) {
        this.raiseDTO = raiseDTO;
    }

    public ISalElementGuidesDTO getRaiseDTO() {
        return raiseDTO;
    }

    public void setEmpCandExtraDataList(List<IEmpCandidateExtraDataDTO> empCandExtraDataList) {
        this.empCandExtraDataList = empCandExtraDataList;
    }

    public List<IEmpCandidateExtraDataDTO> getEmpCandExtraDataList() {
        return empCandExtraDataList;
    }

    public void setValidCond(boolean validCond) {
        this.validCond = validCond;
    }

    public boolean isValidCond() {
        return validCond;
    }
    
    public String viewDocumentsApproveCen() {
        if (this.getPageDTO() != null ) {
            ViewDocumentsBean viewDocuments = ViewDocumentsBean.getInstance();
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getPageDTO();
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
            viewDocuments.setBackActionMethodName("");
            viewDocuments.setBckBtnNavigationCase("fatwaCenteralEmploymentApprove");
        }
        return "viewDocumentsPage";
    }
    
    public String viewDocumentsCen() {
        if (this.getPageDTO() != null ) {
            ViewDocumentsBean viewDocuments = ViewDocumentsBean.getInstance();
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getPageDTO();
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
            viewDocuments.setBackActionMethodName("");
            viewDocuments.setBckBtnNavigationCase("fatwaCenteralEmployment");
        }
        return "viewDocumentsPage";
    }
    
    
    public String viewDocumentsApprove() {
        if (this.getPageDTO() != null ) {
            ViewDocumentsBean viewDocuments = ViewDocumentsBean.getInstance();
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getPageDTO();
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
            viewDocuments.setBackActionMethodName("");
            viewDocuments.setBckBtnNavigationCase("fatwaCenteralEmploymentApprove");
        }
        return "viewDocumentsPage";
    }
    
    public String viewDocuments() {
        if (this.getPageDTO() != null ) {
            ViewDocumentsBean viewDocuments = ViewDocumentsBean.getInstance();
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getPageDTO();
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
            viewDocuments.setBackActionMethodName("");
            viewDocuments.setBckBtnNavigationCase("fatwaCenteralEmployment");
        }
        return "viewDocumentsPage";
    }

    public void setCaderNamePrevious(String caderNamePrevious) {
        this.caderNamePrevious = caderNamePrevious;
    }

    public String getCaderNamePrevious() {
        return caderNamePrevious;
    }

    public void setGroupNamePrevious(String groupNamePrevious) {
        this.groupNamePrevious = groupNamePrevious;
    }

    public String getGroupNamePrevious() {
        return groupNamePrevious;
    }

    public void setDegreeNamePrevious(String degreeNamePrevious) {
        this.degreeNamePrevious = degreeNamePrevious;
    }

    public String getDegreeNamePrevious() {
        return degreeNamePrevious;
    }

    public void setRaiseNamePrevious(String raiseNamePrevious) {
        this.raiseNamePrevious = raiseNamePrevious;
    }

    public String getRaiseNamePrevious() {
        return raiseNamePrevious;
    }

    public void setTotalRewardAcceptedPrevious(String totalRewardAcceptedPrevious) {
        this.totalRewardAcceptedPrevious = totalRewardAcceptedPrevious;
    }

    public String getTotalRewardAcceptedPrevious() {
        return totalRewardAcceptedPrevious;
    }
    public void initIntegration() {
        workDataListBean.setNavigationCase(LIST_PAGE);
        workDataListBean.setBeanName(BEAN_NAME);
        workDataListBean.setBackAction("backFromExperience");
    }
    public String backFromExperience() {
        return workDataListBean.getNavigationCase();
    }
    public String goToExperiences() {
        initIntegration();
        IEmpCandidatesDTO currentDTO = (IEmpCandidatesDTO)getPageDTO();
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (IKwtCitizensResidentsDTO)currentDTO.getCitizensResidentsDTO();

        String key = kwtCitizensResidentsDTO.getCode().getKey();
        String fullName = kwtCitizensResidentsDTO.getFullName();


        workDataListBean.setCivilId(Long.valueOf(key));
        workDataListBean.setCitizinFullName(fullName);
        workDataListBean.setShowBtn(false);
        workDataListBean.setCivilExist(true);
        workDataListBean.setPanelGroupStyleClass(null);

        try {
            workDataListBean.getAll();
        } catch (DataBaseException e) {
            ;
        }
        return "workDataListPage";
    }
    public void initIntegrationApprove() {
        workDataListBean.setNavigationCase(LIST_PAGE_APPROVE);
        workDataListBean.setBeanName(BEAN_NAME);
        workDataListBean.setBackAction("backFromExperience");
    }
    
    public String goToApproveExperiences() {
        initIntegrationApprove();
        IEmpCandidatesDTO currentDTO = (IEmpCandidatesDTO)getPageDTO();
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (IKwtCitizensResidentsDTO)currentDTO.getCitizensResidentsDTO();

        String key = kwtCitizensResidentsDTO.getCode().getKey();
        String fullName = kwtCitizensResidentsDTO.getFullName();


        workDataListBean.setCivilId(Long.valueOf(key));
        workDataListBean.setCitizinFullName(fullName);
        workDataListBean.setShowBtn(false);
        workDataListBean.setCivilExist(true);
        workDataListBean.setPanelGroupStyleClass(null);

        try {
            workDataListBean.getAll();
        } catch (DataBaseException e) {
            ;
        }
        return "workDataListPage";
    }
    
    public void initIntegrationCrs() {
        workDataListBean.setNavigationCase(LIST_PAGE_CRS);
        workDataListBean.setBeanName(BEAN_NAME);
        workDataListBean.setBackAction("backFromExperience");
    }
  
    public String goToExperiencesCrs() {
        initIntegrationCrs();
        IEmpCandidatesDTO currentDTO = (IEmpCandidatesDTO)getPageDTO();
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (IKwtCitizensResidentsDTO)currentDTO.getCitizensResidentsDTO();

        String key = kwtCitizensResidentsDTO.getCode().getKey();
        String fullName = kwtCitizensResidentsDTO.getFullName();


        workDataListBean.setCivilId(Long.valueOf(key));
        workDataListBean.setCitizinFullName(fullName);
        workDataListBean.setShowBtn(false);
        workDataListBean.setCivilExist(true);
        workDataListBean.setPanelGroupStyleClass(null);

        try {
            workDataListBean.getAll();
        } catch (DataBaseException e) {
            ;
        }
        return "workDataListPage";
    }
    public void initIntegrationApproveCrs() {
        workDataListBean.setNavigationCase(LIST_PAGE_APPROVE_CRS);
        workDataListBean.setBeanName(BEAN_NAME);
        workDataListBean.setBackAction("backFromExperience");
    }
    
    public String goToApproveExperiencesCrs() {
        initIntegrationApproveCrs();
        IEmpCandidatesDTO currentDTO = (IEmpCandidatesDTO)getPageDTO();
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (IKwtCitizensResidentsDTO)currentDTO.getCitizensResidentsDTO();

        String key = kwtCitizensResidentsDTO.getCode().getKey();
        String fullName = kwtCitizensResidentsDTO.getFullName();


        workDataListBean.setCivilId(Long.valueOf(key));
        workDataListBean.setCitizinFullName(fullName);
        workDataListBean.setShowBtn(false);
        workDataListBean.setCivilExist(true);
        workDataListBean.setPanelGroupStyleClass(null);

        try {
            workDataListBean.getAll();
        } catch (DataBaseException e) {
            ;
        }
        return "workDataListPage";
    }

    public void setJobFromMin(boolean jobFromMin) {
        this.jobFromMin = jobFromMin;
    }

    public boolean isJobFromMin() {
        return jobFromMin;
    }
}
