package com.beshara.csc.hr.emp.presentation.backingbean.employee.shared;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesCMTClient;
import com.beshara.csc.hr.emp.business.dto.EmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.employment.shared.config.HireStageConst;
import com.beshara.csc.hr.emp.presentation.backingbean.employment.shared.config.ReviewConfigBean;
import com.beshara.csc.hr.sal.business.client.ISalElementGuidesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.ICatsDTO;
import com.beshara.csc.nl.job.business.dto.IJobSearchCriteriaDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
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


public class ReviewListBean1 extends LookUpBaseBean {
    private final Long DEWAN_CODE = new Long(13);
    public final int MINISTRY_VIEW_CONST = 0;
    public static final String METHOD_NAME_ADD_SELECTED_JOBS = "reviewMinListBean1.jobForCrs";
    private String bckBtnNavigationCase;
    private List saveStateList = new ArrayList();
    private ReviewConfigBean configBean = new ReviewConfigBean();
    private BussinessService1 biznasActions = new BussinessService1();
    private HireStageConst hireStageConst = new HireStageConst();
    private boolean invalidMinFileNo;
    private String supToJobCode;


    private final int ORG_CAT_CODE_ATT_BGT = 3; //الهيئات ذات الميزانيات الملحقة
    private final int ORG_CAT_CODE_SPR_BGT = 4; //المؤسسات ذات الميزانية المستقلة
    private final Long BGT_TYPE_FOR_ATT_SPR = 1L; //ميزانية الجهة

    private final Long BGT_TYPE_CODE_TAKMELY_AWAL = 1L;
    private final Long BGT_TYPE_CODE_MINISTRY_BGT = 2L;
    private final Long BGT_TYPE_CODE_TAKMELY_TANY = 3L;


    private String backActionMethodName;
    private String pageBeanName;
    // added for display grs condition lines with status (fail|success)
    private List<IResultDTO> linesResultList = new ArrayList<IResultDTO>();
    private JobFilter jobFilter = new JobFilter();
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
    private boolean jobMode;
    private Long suggestedCaderCode;
    private String suggestedCaderName;
    private Long acceptedCaderCode;
    private String acceptedCaderName;
    private boolean renderLovDiv = false;
    TreeDivBean treedivBean;
    private Long suggestedGroupCode;
    private Long acceptedGroupCode;
    private boolean eqTypeTemp = false;
    private Long suggestedDegreeCode;
    private String suggestedRaiseCode;
    private String suggestedRaiseName;
    private Long acceptedRaiseCode;
    private String acceptedRaiseName;
    private Long acceptedDegreeCode;
    private String acceptedDegreeName;
    private boolean renderJobDiv;
    private boolean searchJobMode = false;
    private String jobSearchType = "0";
    private boolean workCenterHasJobs;
    private int jobListSize = 0;
    private String minWorkCenter = "";
    private String workCenterName;
    private String jobNameForMin;
    private String suggestedJobValue;
    private String suggestedJobCode;
    private String acceptedJobValue;
    private String acceptedJobCode;
    private IBgtTypesDTO bgtTypesDTO;
    private String stageId;
    private String selectedMinistery;
    private String suggestedGroupName;
    private String acceptedGroupName;
    private String degreeName;
    private String suggestedDegreeName;
    private String jobKey;
    private String jobKey1;
    private IJobsDTO jobsDTO = JobDTOFactory.createJobsDTO();
    private String showDivMode;

    private boolean validCond = true;
    private boolean errorJobCondition = false;
    private boolean errorJobCondition1 = false;
    private boolean jobFromMin;
    private String approvedJobValue;
    public ReviewListBean1() {

        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setSelectedPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setClient(EmpClientFactory.getEmpCandidatesClient());
        ((EmpCandidatesDTO)getPageDTO()).setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());

        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setUsingPaging(true);
        if (getLovBaseBean().isUsingPaging()) {
            getLovBaseBean().generatePagingRequestDTO("reviewMinListBean1", "initMainDataBean");
        } else {
            getLovBaseBean().setMyTableData(new ArrayList());
        }
        initJobFilter();
        setCustomDiv1(getCustomDiv1() + " extraTooWideDiv");
    }

    public void initiateBeanOnce() {
        super.initiateBeanOnce();
        initTreeDivBean();
        fillBudgetTypeList();
        // fillCaderList();
        fillDegreeList();
        fillGroupList();
        fillRaiseList();
        // fillWorkMinistriesList();
    }


    private void initJobFilter() {
        setJobKey("");
        jobFilter.setOkButtonMethod(METHOD_NAME_ADD_SELECTED_JOBS);
        jobFilter.setSingleSelection(true);
        jobFilter.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());

    }

    public void showJobDiv() {
        jobFilter.setSelectedJobGroup("");
        jobFilter.setJobGroupCode("");
        jobFilter.setDisableJobGroup(false);
        List<String> excludedJobCodeList = new ArrayList<String>();
        if (this.getSuggestedJobValue() != null) {
            excludedJobCodeList.add(this.suggestedJobValue);
        }
        jobFilter.setExcludedJobList(excludedJobCodeList);
        try {
            jobFilter.cancelSearch();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        jobFilter.setResetMode(false);
        jobFilter.setLoadSecAccessibleJobsOnly(true);
        jobFilter.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        setWorkCenterMode(false);
        setJobMode(true);
        setRenderLovDiv(true);
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
                if (entityKey.getTypeCode().equals(BGT_TYPE_CODE_MINISTRY_BGT)) {
                    getConfigBean().setDisableBudgetType(true);
                } else {
                    getConfigBean().setDisableBudgetType(false);
                    for (IBasicDTO dto2 : getBudgetTypeList()) {
                        if (((IBgtTypesEntityKey)dto2.getCode()).getTypeCode().equals(BGT_TYPE_CODE_MINISTRY_BGT)) {
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

    public void salaryDataSectionController(boolean hasExperience, String _stageId) {

        Long stageId = Long.parseLong(_stageId);
        if ((stageId.equals(IEMPConstant.HIRE_STAGE_JOB_NAME_ACCEPTRD) && !hasExperience) ||
            (stageId.equals(IEMPConstant.HIRE_STAGE_FIN_DEGREE_REQUIRED) && hasExperience)) {
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
            stageId.equals(IEMPConstant.HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED);
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

    public void findJobName() {

        errorJobCondition = false;
        IJobsDTO filteredjobDTO = JobDTOFactory.createJobsDTO();
        filteredjobDTO.setJobKey(jobKey);

        try {
            //  jobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getByJobKey(filteredjobDTO);

            jobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getJobInMinistry(filteredjobDTO,  CSCSecurityInfoHelper.getLoggedInMinistryCode());

            ((EmpCandidatesDTO)getPageDTO()).setJobsDTO(jobsDTO);
            //                ((EmpCandidatesDTO)getPageDTO()).setJobKey(jobKey);
            setJobKey(jobKey);
            ((EmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setSuggestedJobCode(jobKey);
            setSuggestedJobValue(jobsDTO.getName());

            String jobCode = jobsDTO.getCode().getKey();
            Long realCivil =
                Long.valueOf(((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
            boolean checkJobCond = true;
            try {
                checkJobCond = JobClientFactory.getJobsClient().checkJobConditions(realCivil, jobCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!checkJobCond) {
                errorJobCondition = true;
                ((EmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setSuggestedJobCode(null);
                ((EmpCandidatesDTO)getPageDTO()).setJobsDTO(null);
            }


        } catch (DataBaseException e) {
            setSuggestedJobValue(null);
        } catch (SharedApplicationException e) {
            setSuggestedJobValue(null);
        }


    }

    public void fillJobKey() {
        Object o = getLovBaseBean().getSelectedDTOS().get(0);
        if (o != null && o instanceof IJobsDTO) {
            System.out.print("key" + ((IJobsDTO)getLovBaseBean().getSelectedDTOS().get(0)).getJobKey());
            setJobKey(((IJobsDTO)getLovBaseBean().getSelectedDTOS().get(0)).getJobKey());
            setSuggestedJobCode(getJobKey());
        }
        getLovBaseBean().closeLovDiv();
    }


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


    public void showViewByStage(Long stage) {
        System.out.println(" ============================== " + stage + " ==================== ");
        setStageId(stage.toString());
        configBean.resetAll();
        switch (Integer.parseInt(stage.toString())) {

        case 21:
            configBean.resetAll();
            configBean.selectionDepartmentView2();
            break;

        case IEMPConstants.ACCEPT_JOB_AND_NAME_IN_PROGRESS:

            configBean.jobAndDegreeInProgressView();
            break;

        case IEMPConstants.REJECT_ORDER_TO_WORK_MINISTRY:
            configBean.selectionCompleteView();
            configBean.renderButtonView();
            break;

            //case 15
            //            case IEMPConstants.HIRE_STAGE_JOB_NAME_AND_DEGREE_ACCEPTED:
            //                configBean.selectionCompleteView();
            //                configBean.renderButton();
            //                break;

        case 1:
            configBean.selectionDepartmentView2();
            break;

        case 2:
            configBean.selectionDepartmentView2();
            break;

        case 12:
            configBean.selectionCompleteView();
            configBean.renderButtonView();
            break;

        case 15:
            System.out.println("case 15");
            //configBean.selectionCompleteView();
            // configBean.renderButtonView();
            configBean.approvalView();
            // configBean.renderButtonView();
            break;

        case 9:
            configBean.jobAndDegreeInProgressView();
            break;

        case 10:
            configBean.jobAndDegreeInProgressView();
            break;

        case 11:
            configBean.jobAndDegreeInProgressView();
            break;


            //        case HireStageConst.HIRE_STAGE_JOB_NAME_ACCEPTRD:
            //            configBean.selectionDepartmentView1();
            //            break;
            //
            //        case HireStageConst.HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED:
            //            configBean.selectionDepartmentView1();
            //            break;
            //        case HireStageConst.HIRE_STAGE_REJECTED_BY_FATWA:
            //            configBean.selectionDepartmentView2();
            //            break;
            //
            //        case HireStageConst.HIRE_STAGE_REJECTED_BY_DEWAN:
            //            configBean.ministryView();
            //            ((IEmployeesDTO)getPageDTO()).setBgtTypesDTO(getBgtTypesDTO());
            //            break;
            //
            //        case MINISTRY_VIEW_CONST: // for ministry View
            //            configBean.ministryView();
            //            ((IEmployeesDTO)getPageDTO()).setBgtTypesDTO(getBgtTypesDTO());
            //            break;


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
            getLovBaseBean().generatePagingRequestDTO("reviewMinListBean1", "showListOfValuesDivWithPaging");
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
        getLovBaseBean().setReturnMethodName("reviewMinListBean1.wrkCenterValue");
        getLovBaseBean().setSearchMethod("reviewMinListBean1.searchWrkCenters");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("reviewMinListBean1.cancelSearchLovDiv");
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
        setJobMode(true);
        setRenderLovDiv(true);
        getLovBaseBean().setUsingPaging(true);
        if (getLovBaseBean().isUsingPaging()) {
            getLovBaseBean().generatePagingRequestDTO("reviewMinListBean1", "getJobListForMinWithPaging");

        }

        getLovBaseBean().getOkCommandButton().setReRender("WorkCenter_Panel,suggestedJobPanelGrp,selected_job_panel_grp");
        getLovBaseBean().setReturnMethodName("reviewMinListBean1.suggestedJobValue");
        getLovBaseBean().setSearchMethod("reviewMinListBean1.searchJob");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("reviewMinListBean1.cancelSearchLovDivForJob");
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
            getLovBaseBean().generatePagingRequestDTO("reviewMinListBean1", "getJobListForMinWithPaging");

        }

        getLovBaseBean().getOkCommandButton().setReRender("WorkCenter_Panel,suggestedJobPanelGrp,selected_job_panel_grp");
        getLovBaseBean().setReturnMethodName("reviewMinListBean1.jobForCrs");
        getLovBaseBean().setSearchMethod("reviewMinListBean1.searchJob");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("reviewMinListBean1.cancelSearchLovDivForCrs");
    }


    public void cancelSearchLovDivForJob() {
        showListOfValuesDivForJob();
    }


    public void cancelSearchLovDivForCrs() {
        showListOfValuesDivForJobCrs();
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

                    getLovBaseBean().generatePagingRequestDTO("reviewMinListBean1", "searchWrkCentersWithPaging",
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
                setSuggestedJobValue("");
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
                }
                ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(jobFilter.getSelectedDTOS().get(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void jobForCrs() {
        errorJobCondition = false;
        if (jobFilter != null && jobFilter.getSelectedDTOS() != null && !jobFilter.getSelectedDTOS().isEmpty()) {
            try {
                ((IEmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setSuggestedJobCode(jobFilter.getSelectedDTOS().get(0).getCode().getKey());
                if (!stageId.equals(String.valueOf(HireStageConst.HIRE_STAGE_JOB_NAME_REQUIRED))) {
                    setSuggestedJobValue(jobFilter.getSelectedDTOS().get(0).getName());
                    setJobKey(((IJobsDTO)jobFilter.getSelectedDTOS().get(0)).getJobKey());
                }
                ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(jobFilter.getSelectedDTOS().get(0));
                setJobKey(((IJobsDTO)jobFilter.getSelectedDTOS().get(0)).getJobKey());


                String jobCode = jobFilter.getSelectedDTOS().get(0).getCode().getKey();
                Long realCivil =
                    Long.valueOf(((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
                boolean checkJobCond = JobClientFactory.getJobsClient().checkJobConditions(realCivil, jobCode);
                if (!checkJobCond) {
                    errorJobCondition = true;
                    ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(null);
                }

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
            jobSearchCriteriaDTO.setMinCode(getLoggedInMinistry());
            jobList = JobClientFactory.getJobsClient().getCodeNameOfMinistryJobsAndPublicJobs(jobSearchCriteriaDTO);
            //jobList =JobClientFactory.getJobsClient().getCodeName();
            //                    JobClientFactory.getJobsClient().getCodeNameByWorkCenterCodeAndHierarchicalFlag(minWorkCenter);
            //        } catch (SharedApplicationException e) {
            //            jobList = new ArrayList();
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
        setSuggestedDegreeCode(null);
        setSuggestedRaiseName(null);
        setDegreeList(new ArrayList());
        setSuggestedRaiseCode(null);
        setRaiseList(new ArrayList());
        fillDegreeList();

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
        treedivBean.setCancelSearchMethod("reviewMinListBean.cancelTreeSearch");
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
                jobSearchCriteriaDTO.setMinCode(getLoggedInMinistry());
                getLovBaseBean().setUsingPaging(true);
                if (getLovBaseBean().isUsingPaging()) {
                    getLovBaseBean().generatePagingRequestDTO("reviewMinListBean1", "searchJobWithPaging",
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
        app.setShowbar(false);
        app.setShowdatatableContent(false);
        app.setShowContent1(true);
        app.setShowSearch(false);
        app.setShowbar(false);
        app.setShowpaging(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(true);
        app.setShowDelAlert(true);
        app.setShowDelConfirm(false);
        app.setShowLovDiv(true);
        app.setShowCustomDiv1(true);
        app.setShowCustomDiv2(true);
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
            getLovBaseBean().generatePagingRequestDTO("reviewMinListBean1", "showCaderListOfValuesDivWithPaging");
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
        getLovBaseBean().getOkCommandButton().setReRender("employeeCaderPanel,JobGrpMenu,jobs_groupId,suggestedDegreePanel,raisesCount_Id");
        getLovBaseBean().setReturnMethodName("reviewMinListBean1.caderValue");
        getLovBaseBean().setSearchMethod("reviewMinListBean1.searchCader");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("reviewMinListBean1.cancelSearchCaderLovDiv");
        getLovBaseBean().getMyDataTable().setFirst(0);
        fillCaderList();
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
            treedivBean.setMyTableData(SalClientFactory.getSalElementGuidesClient().getDegreesTreeByGroup(suggestedGroupCode));
            treedivBean.setClientValueBinding("reviewMinListBean.salElementClient");
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
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{reviewMinListBean.addSelectedRaise}",
                                                                                   null);
        treedivBean.getOkCommandButton().setAction(mb);
        treedivBean.setEnabledNotLeaf(false);
        ValueBinding vb =
            FacesContext.getCurrentInstance().getApplication().createValueBinding("#{treeDivBean.enabledNotLeaf && !treeDivBean.enabledRoot && (treeDivBean.selectionNo==1)}");
        treedivBean.getOkCommandButton().setValueBinding("rendered", vb);
        treedivBean.getOkCommandButton().setReRender("employees_degreeId,validScriptPanel");
    }

    public void addSelectedRaise() {
        try {
            if (treedivBean.getDtoDetails() != null && treedivBean.getDtoDetails().getCode() != null) {

                //                if (((ISalElementGuidesDTO)(treedivBean.getDtoDetails()).getParentObject()).getEqType().equals(getManagedConstantsBean().getEqTypeTempConstant())) {
                //                    eqTypeTemp = true;
                //                } else {
                //                    eqTypeTemp = false;
                //                }
                suggestedRaiseCode = treedivBean.getDtoDetails().getCode().getKey();
                suggestedRaiseName = treedivBean.getDtoDetails().getName();
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
            if (getSuggestedRaiseCode() != null) {
                selectedSalElementGuidesDTO =
                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getById(SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.valueOf(getSuggestedRaiseCode())));
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
                    setSuggestedRaiseName(raiseDTO.getName());
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
                    setSuggestedGroupCode(groupCode);
                    setSuggestedGroupName(groupDTO.getName());
                    //getGroupList();

                }
                //Cader
                if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                    caderCode = ((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode();
                    setSuggestedCaderCode(caderCode);
                    setSuggestedCaderName(groupDTO.getParentObject().getName());
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

                getLovBaseBean().generatePagingRequestDTO("reviewMinListBean1", "searchCaderWithPaging");
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

    public Long getLoggedInMinistry() {
        return CSCSecurityInfoHelper.getLoggedInMinistryCode() == null ? DEWAN_CODE :
               CSCSecurityInfoHelper.getLoggedInMinistryCode();
    }

    public PagingResponseDTO searchCaderWithPaging(PagingRequestDTO request) {

        String searchQury = request.getParams()[0].toString();
        String searchType = request.getParams()[1].toString();
        try {

            ISalElementGuidesClient salElementGuidesClient = SalClientFactory.getSalElementGuidesClient();
            if (searchType.equals("0")) {
                return new PagingResponseDTO(salElementGuidesClient.searchByCaderCodeAndMinCode(getLoggedInMinistry(),
                                                                                                Long.valueOf(searchQury)));
            } else if (searchType.equals("1"))
                return new PagingResponseDTO(salElementGuidesClient.searchByCaderNameAndMinCode(getLoggedInMinistry(),
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
                setSuggestedCaderCode(Long.parseLong(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString()));
                setSuggestedCaderName(getLovBaseBean().getSelectedDTOS().get(0).getName());
                resetSelectedJobData();
                fillGroupList();
                setRaiseList(null);
                setDegreeList(null);
                setSuggestedDegreeCode(null);
                setSuggestedDegreeName("");
                setSuggestedRaiseCode("");
                setSuggestedRaiseName("");
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

    public List initListForgetSal() {
        List list = new ArrayList();
        list.add(IEMPConstants.CONTRACT_TYPE);
        list.add(IEMPConstants.ESTANA_TYPE);
        list.add(2);
        list.add(Boolean.FALSE);
        return list;
    }

    public void setCaderList(List caderList) {
        this.caderList = caderList;
    }

    public void fillCaderList() {
        if (caderList == null || (caderList != null && caderList.size() == 0)) {
            try {
                caderList =
                        SalClientFactory.getSalElementGuidesClient().getCaderCodeName(getLoggedInMinistry(), initListForgetSal());

            } catch (DataBaseException e) {
                e.printStackTrace();
                caderList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                caderList = new ArrayList();
            }

        }
    }

    public List getCaderList() {
        return caderList;
    }

    public void setGroupList(List groupList) {
        this.groupList = groupList;
    }

    public void fillGroupList() {
        if (suggestedCaderCode != null && !suggestedCaderCode.equals("")) {
            try {
                groupList =
                        SalClientFactory.getSalElementGuidesClient().getGroupCodeNameByCader(suggestedCaderCode, initListForgetSal());
            } catch (DataBaseException f) {
                f.printStackTrace();
                groupList = new ArrayList();
            } catch (Exception f) {
                f.printStackTrace();
                groupList = new ArrayList();
            }
        }
    }

    public List getGroupList() {
        return groupList;
    }

    public void setWorkCenterMode(boolean workCenterMode) {
        this.workCenterMode = workCenterMode;
    }

    public boolean isWorkCenterMode() {
        return workCenterMode;
    }

    public void setSuggestedCaderCode(Long caderCode) {
        this.suggestedCaderCode = caderCode;
    }

    public Long getSuggestedCaderCode() {
        return suggestedCaderCode;
    }

    public void setSuggestedCaderName(String caderName) {
        this.suggestedCaderName = caderName;
    }

    public String getSuggestedCaderName() {
        return suggestedCaderName;
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

    public void setSuggestedRaiseCode(String suggestedRaiseCode) {
        this.suggestedRaiseCode = suggestedRaiseCode;
    }

    public String getSuggestedRaiseCode() {
        return suggestedRaiseCode;
    }

    public void setSuggestedRaiseName(String raiseName) {
        this.suggestedRaiseName = raiseName;
    }

    public String getSuggestedRaiseName() {
        return suggestedRaiseName;
    }

    public void setTreedivBean(TreeDivBean treedivBean) {
        this.treedivBean = treedivBean;
    }

    public TreeDivBean getTreedivBean() {
        return treedivBean;
    }

    public void setSuggestedGroupCode(Long groupCode) {
        this.suggestedGroupCode = groupCode;
    }

    public Long getSuggestedGroupCode() {
        return suggestedGroupCode;
    }

    public void setSuggestedDegreeCode(Long degreeCode) {
        this.suggestedDegreeCode = degreeCode;
    }

    public Long getSuggestedDegreeCode() {
        return suggestedDegreeCode;
    }

    public void setRaiseList(List raiseList) {
        this.raiseList = raiseList;
    }

    public void fillRaiseList() {
        if (suggestedDegreeCode != null && !suggestedDegreeCode.equals("")) {
            try {
                raiseList =
                        SalClientFactory.getSalElementGuidesClient().getRaiseCodeNameByDegree(suggestedDegreeCode, initListForgetSal());
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
    }

    public List getRaiseList() {
        return raiseList;
    }

    public void setDegreeList(List degreeList) {
        this.degreeList = degreeList;
    }

    public void fillDegreeList() {
        if (suggestedGroupCode != null && !suggestedGroupCode.equals("")) {
            try {
                degreeList =
                        (List)SalClientFactory.getSalElementGuidesClient().getDegreeCodeNameByGroupSorted(suggestedGroupCode,
                                                                                                    initListForgetSal());
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List getDegreeList() {
        return degreeList;
    }

    public void setBudgetTypeList(List budgetTypeList) {
        this.budgetTypeList = budgetTypeList;
    }

    public void fillBudgetTypeList() {
        if ((budgetTypeList == null || budgetTypeList.size() == 0))
            try {
                budgetTypeList = BgtClientFactory.getBgtTypesClient().getCodeName();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public List<IBasicDTO> getBudgetTypeList() {
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
                    OrgEntityKeyFactory.createMinistriesEntityKey(getLoggedInMinistry());
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
    }

    public List getWorkMinistriesList() {
        return workMinistriesList;
    }

    public void setWrkCenterList(List wrkCenterList) {
        this.wrkCenterList = wrkCenterList;
    }

    public List getWrkCenterList() {
        try {
            if ((wrkCenterList == null || wrkCenterList.size() == 0)) {
                IMinistriesEntityKey ministryEntityKey =
                    OrgEntityKeyFactory.createMinistriesEntityKey(getLoggedInMinistry());
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

    public void setBiznasActions(BussinessService1 biznasActions) {
        this.biznasActions = biznasActions;
    }

    public BussinessService1 getBiznasActions() {
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

    public void setSuggestedGroupName(String groupName) {
        this.suggestedGroupName = groupName;
    }

    public String getSuggestedGroupName() {
        return suggestedGroupName;
    }

    public void setInvalidMinFileNo(boolean invalidMinFileNo) {
        this.invalidMinFileNo = invalidMinFileNo;
    }

    public boolean isInvalidMinFileNo() {
        return invalidMinFileNo;
    }

    public void setAcceptedCaderCode(Long acceptedCaderCode) {
        this.acceptedCaderCode = acceptedCaderCode;
    }

    public Long getAcceptedCaderCode() {
        return acceptedCaderCode;
    }

    public void setAcceptedCaderName(String acceptedCaderName) {
        this.acceptedCaderName = acceptedCaderName;
    }

    public String getAcceptedCaderName() {
        return acceptedCaderName;
    }

    public void setAcceptedGroupCode(Long acceptedGroupCode) {
        this.acceptedGroupCode = acceptedGroupCode;
    }

    public Long getAcceptedGroupCode() {
        return acceptedGroupCode;
    }

    public void setAcceptedRaiseCode(Long acceptedRaiseCode) {
        this.acceptedRaiseCode = acceptedRaiseCode;
    }

    public Long getAcceptedRaiseCode() {
        return acceptedRaiseCode;
    }

    public void setAcceptedRaiseName(String acceptedRaiseName) {
        this.acceptedRaiseName = acceptedRaiseName;
    }

    public String getAcceptedRaiseName() {
        return acceptedRaiseName;
    }

    public void setAcceptedDegreeCode(Long acceptedDegreeCode) {
        this.acceptedDegreeCode = acceptedDegreeCode;
    }

    public Long getAcceptedDegreeCode() {
        return acceptedDegreeCode;
    }

    public void setSuggestedJobValue(String suggestedJobValue) {
        this.suggestedJobValue = suggestedJobValue;
    }

    public String getSuggestedJobValue() {
        return suggestedJobValue;
    }

    public void setAcceptedJobValue(String acceptedJobValue) {
        this.acceptedJobValue = acceptedJobValue;
    }

    public String getAcceptedJobValue() {
        return acceptedJobValue;
    }

    public void setAcceptedGroupName(String acceptedGroupName) {
        this.acceptedGroupName = acceptedGroupName;
    }

    public String getAcceptedGroupName() {
        return acceptedGroupName;
    }

    public void setSuggestedJobCode(String suggestedJobCode) {
        this.suggestedJobCode = suggestedJobCode;
    }

    public String getSuggestedJobCode() {
        return suggestedJobCode;
    }

    public void setAcceptedJobCode(String acceptedJobCode) {
        this.acceptedJobCode = acceptedJobCode;
    }

    public String getAcceptedJobCode() {
        return acceptedJobCode;
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

    public void setAcceptedDegreeName(String acceptedDegreeName) {
        this.acceptedDegreeName = acceptedDegreeName;
    }

    public String getAcceptedDegreeName() {
        return acceptedDegreeName;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getJobKey() {
        if (getSuggestedJobValue() == null) {
            return null;
        }
        return jobKey;
    }

    public void setJobsDTO(IJobsDTO jobsDTO) {
        this.jobsDTO = jobsDTO;
    }

    public IJobsDTO getJobsDTO() {
        return jobsDTO;
    }

    public void setJobFilter(JobFilter jobFilter) {
        this.jobFilter = jobFilter;
    }

    public JobFilter getJobFilter() {
        return jobFilter;
    }

    public void setSupToJobCode(String supToJobCode) {
        this.supToJobCode = supToJobCode;
    }

    public String getSupToJobCode() {
        return supToJobCode;
    }

    private Long getCivilId() {
        KwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (KwtCitizensResidentsDTO)((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO();
        KwtCitizensResidentsEntityKey kwtEntityKey = (KwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode();
        Long civilId = kwtEntityKey.getCivilId();

        return civilId;
    }

    public void resetRaiseList() {
        try {
            validCond =
                    EmpClientFactory.getEmployeesClient().isConditionFromGrsValid(getCivilId(), suggestedDegreeCode);
        } catch (DataBaseException e) {
            e.printStackTrace();
            validCond = false;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            validCond = false;
        }

        if (validCond) {
            fillRaiseList();
        } else {
            raiseList = new ArrayList();
            suggestedRaiseCode = "";
        }
    }

    public void setValidCond(boolean validCond) {
        this.validCond = validCond;
    }

    public boolean isValidCond() {
        return validCond;
    }

    public void setErrorJobCondition(boolean errorJobCondition) {
        this.errorJobCondition = errorJobCondition;
    }

    public boolean isErrorJobCondition() {
        return errorJobCondition;
    }

    public void setJobFromMin(boolean jobFromMin) {
        this.jobFromMin = jobFromMin;
    }

    public boolean isJobFromMin() {
        return jobFromMin;
    }
    public void showJobDivCertified() {
        String jobCode=getAcceptedJobCode();
        IJobsEntityKey ek=JobEntityKeyFactory.createJobsEntityKey(jobCode);
        IJobsDTO dto =null;
        try {
            dto = (IJobsDTO)JobClientFactory.getJobsClient().getById(ek);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        String groupCode=calcGroupCode(dto);
        jobFilter.resetValues(1);
        jobFilter.setSelectedJobGroup(groupCode);
        jobFilter.setJobGroupCode(groupCode);
        jobFilter.setDisableJobGroup(true);
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
       initJobFilter1();
        setWorkCenterMode(false);
        setRenderLovDiv(true);
        setJobMode(true);

    }

    public void setJobKey1(String jobKey1) {
        this.jobKey1 = jobKey1;
    }

    public String getJobKey1() {
        return jobKey1;
    }
    public void jobForMinValueApprove() {
        errorJobCondition1 = false;
        if (jobFilter != null && jobFilter.getSelectedDTOS() != null && !jobFilter.getSelectedDTOS().isEmpty()) {
            try {
                ((IEmpCandidatesDTO)getPageDTO()).setApprovedJobsDTO(jobFilter.getSelectedDTOS().get(0));
                // setJobKey(jobFilter.getSelectedDTOS().get(0).getCode().getKey());
                setJobKey1(((IJobsDTO)jobFilter.getSelectedDTOS().get(0)).getJobKey());

                String jobCode = jobFilter.getSelectedDTOS().get(0).getCode().getKey();
                Long realCivil =
                    Long.valueOf(((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
                boolean checkJobCond = JobClientFactory.getJobsClient().checkJobConditions(realCivil, jobCode);
                if (!checkJobCond) {
                    errorJobCondition1 = true;
                    ((IEmpCandidatesDTO)getPageDTO()).setApprovedJobsDTO(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void initJobFilter1() {
        setJobKey1("");
        jobFilter.setOkButtonMethod("reviewMinListBean1.jobForMinValueApprove");
        jobFilter.setSingleSelection(true);
        jobFilter.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());

    }

    public void setApprovedJobValue(String approvedJobValue) {
        this.approvedJobValue = approvedJobValue;
    }

    public String getApprovedJobValue() {
        return approvedJobValue;
    }

    public void setErrorJobCondition1(boolean errorJobCondition1) {
        this.errorJobCondition1 = errorJobCondition1;
    }

    public boolean isErrorJobCondition1() {
        return errorJobCondition1;
    }
    public void findJobName1() {

        errorJobCondition1 = false;
        IJobsDTO filteredjobDTO = JobDTOFactory.createJobsDTO();
        filteredjobDTO.setJobKey(jobKey1);

        try {
            //  jobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getByJobKey(filteredjobDTO);

            jobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getJobInMinistry(filteredjobDTO,  CSCSecurityInfoHelper.getLoggedInMinistryCode());

            ((EmpCandidatesDTO)getPageDTO()).setApprovedJobsDTO(jobsDTO);
            ((EmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setApprovedJob(jobKey);
            setApprovedJobValue(jobsDTO.getName());

            String jobCode = jobsDTO.getCode().getKey();
            Long realCivil =
                Long.valueOf(((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
            boolean checkJobCond = true;
            try {
                checkJobCond = JobClientFactory.getJobsClient().checkJobConditions(realCivil, jobCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!checkJobCond) {
                errorJobCondition1 = true;
                ((EmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setApprovedJob(null);
                ((EmpCandidatesDTO)getPageDTO()).setApprovedJobsDTO(null);
            }


        } catch (DataBaseException e) {
            setApprovedJobValue(null);
        } catch (SharedApplicationException e) {
            setApprovedJobValue(null);
        }


    }
    private String calcGroupCode(IJobsDTO dto) {
        String groupCode = "";
        if (dto != null && dto.getCatsDTO() != null) {
            ICatsDTO catsDTO = (ICatsDTO)dto.getCatsDTO().getParentObject();
            groupCode = catsDTO.getCode().getKey();
            while (catsDTO.getParentObject() != null) {
                groupCode = catsDTO.getParentCode().getKey();
                catsDTO = (ICatsDTO)catsDTO.getParentObject();
            }
        }
        return groupCode;
    }
}
