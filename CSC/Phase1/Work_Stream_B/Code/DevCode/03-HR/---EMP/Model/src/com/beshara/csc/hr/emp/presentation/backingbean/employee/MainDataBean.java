package com.beshara.csc.hr.emp.presentation.backingbean.employee;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.client.IBgtProgramsClient;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobWorkCentersDTO;
import com.beshara.csc.nl.job.business.entity.IJobWorkCentersEntityKey;
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
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class MainDataBean extends ManyToManyDetailsMaintain {
    private static final String EMP_RESOURCES = "com.beshara.csc.hr.emp.presentation.resources.emp";
    public static final String BEAN_NAME = "empMainDataBean";
    public static final String METHOD_NAME_ADD_SELECTED_JOBS = "empMainDataBean.addSelectedJobs";
    public static final int JOB_DIV_MODE = 1;
    public static final int WORK_CENTER_DIV_MODE = 2;
    public static final String OTHER_TYPE = "67";
    private final Long DEWAN_CODE = new Long(13);
    private boolean invalidCivilID = false;
    private boolean NonDBCivilID = false;
    private boolean civilExist = false;
    private boolean invalidCivilWithHireType = false;
    MaintainBean MaintainBean_object = new MaintainBean();
    private String selectedHireTypeCode = "-100"; // change the defulat value from 3 to -100 due to CSC-22302
    private String contractType;
    private String oldContractType = "-100";
    // combo boxes lists declartions
    private List techicalJobsList = new ArrayList();
    private List jobList = new ArrayList();

    private List hireStatusList = new ArrayList();
    private List<IBasicDTO> hireTypesList = new ArrayList<IBasicDTO>();
    private List hireStagesList = new ArrayList();

    private List workMinistriesList = new ArrayList();
    private List bankList = new ArrayList();
    private List bankBranchesList = new ArrayList();

    /* flag to refer if job is supervision type or not and if job type is supervision so
     * technical jobs combo box will appear
     * */
    private Long supervisionJobType = ISystemConstant.JOB_SUPERVISION_TYPE;
    private String virtulValue = String.valueOf(ISystemConstant.VIRTUAL_VALUE.longValue());

    // dumy variable for ministry of current user
    private IMinistriesEntityKey ministryEntityKey = OrgEntityKeyFactory.createMinistriesEntityKey(CSCSecurityInfoHelper.getLoggedInMinistryCode());

    // refers to selected bank
    private Long selectedBankCode;

    //refers to entered civil id in add mode
    private Long civilId;

    // reference to container bean
    // MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");  performance issue

    // part of two jobs and work ministries  divs
    private int jobListSize = 0;
    private String selectedJobName;
    private String searchjobString;
    private HtmlDataTable jobDataTable = new HtmlDataTable();
    private boolean searchJobMode = false;
    private String jobCode;

    private int workMinistriesListSize = 0;
    private String selectedWorkMinistriesName;
    private String searchWorkMinistriesString;
    private HtmlDataTable workMinistriesDataTable = new HtmlDataTable();
    private boolean searchWorkMinistriesMode = false;


    private List caderList = new ArrayList();
    private List groupList = new ArrayList();
    private List eqGroupList = new ArrayList();
    private List budgetList = new ArrayList();
    private List<IBasicDTO> budgetTypeList = new ArrayList<IBasicDTO>();

    private Long caderCode;
    private Long groupCode;
    private String degreeCode;
    private String raiseCode;

    private Long eqCaderCode;
    private Long eqGroupCode;
    private Long eqRaiseCode;
    private Long eqDegreeCode;

    private Long virtualValue = ISystemConstant.VIRTUAL_VALUE;
    private List wrkCenterList = new ArrayList();

    private String jobSearchType = "0";
    private boolean workCenterMode = false;
    private String degreeName;
    private String raiseName;

    private List<SalElementGuidesDTO> degreesList;
    private List<IBasicDTO> raisesCount;

    private String eqRaiseName;

    private String workCenterName;
    private String workCenterKey;
    private String caderName;
    private String eqCaderName;
    private boolean renderJobDiv = false;
    private boolean renderLovDiv = false;
    private boolean eqTypeTemp = false;
    private String titleFlageOfDiv;
    private String bgtProgramName = "";
    private boolean workCenterHasJobs = false;
    private boolean renderQulType = true;
    private Long loginedMinistrycode = null;
    private String jobDescription = null;
    private int shangLangth = 1;
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO;
    private boolean inValidValue = false;
    private ResourceBundle bundle = null;
    private String pageId;
    private boolean dataDisabledIfEmpFromCRS;
    private Boolean enableResetData = false;
    private String fireComponentID = "1";
    //  Activiation btn flags
    private Boolean addBtnDisabled = true;
    private JobFilter jobFilter = new JobFilter();
    private int divMode;
    private String jobName;
    private Date nextYear;
    private Date previousYear;
    private boolean renderErrorHireDate = false;
    private boolean renderEmpFilNumRedundant = false;
    private boolean renderCandFilNumRedundant = false;
    private boolean invalidNextYear;
    private WorkCentersHelperBean wcIntegrationBean =
        new WorkCentersHelperBean("empMaintainBean", "integrationDiv1", false, false, true, true, null);
    private IWorkCentersDTO workCentersDTO;
    private boolean fromEmpList = false;
    private boolean renderedErrorMsgHirType = false;
    private boolean errorCanDegree = false;
    private boolean kuwaitCitizen;
    private boolean errorJobCondition = false;
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
    /**
     * Purpose:initialze job and work ministry center if user in edit operation and have job and work ministry
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora
     * 1.2 - Date:   3/8/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */

    public MainDataBean() {
        try {
            System.out.println("com.beshara.csc.hr.emp.presentation.backingbean.employee.MainDataBean : constructor : start.");
            getMaintainBeanInstance().setMainDataOnlyFlag(true); 
            System.out.println("  create Instance of MaintainBean =========> success");
            setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
            System.out.println("  create Instance of PageDTO =========> success"); 
            setBundle(ResourceBundle.getBundle("com.beshara.csc.hr.emp.presentation.resources.emp_ar"));
            System.out.println("  create Instance of ResourceBundle =========> success");
            setUsingPaging(true);
            System.out.println("  create Instance of ResourceBundle =========> success");
            setLovBaseBean(new LOVBaseBean());
            getLovBaseBean().setUsingPaging(true);
            if (getLovBaseBean().isUsingPaging()) {
                getLovBaseBean().generatePagingRequestDTO("empMainDataBean", "initMainDataBean");
            } else {
                getLovBaseBean().setMyTableData(new ArrayList());
            }
            setDivTreeAdd("regulationDivSearch2");
            pageId =
                    ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("pageId");
            if (pageId == null) {
                setContent1Div("module_tabs_cont");
            }

            setDelAlert("divSheardStyle1");

            initJobFilter();
            System.out.println(" initJobFilter =========> success");
            setCustomDiv1(getCustomDiv1() + " extraTooWideDiv");
            System.out.println("com.beshara.csc.hr.emp.presentation.backingbean.employee.MainDataBean : constructor : end.");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

  
    private void initJobFilter() {
        jobFilter.setOkButtonMethod(METHOD_NAME_ADD_SELECTED_JOBS);
        jobFilter.setSingleSelection(true);
        jobFilter.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());

    }

    public void showJobDiv() {
        setDivMode(JOB_DIV_MODE);
        List<String> excludedJobCodeList = new ArrayList<String>();
        if (((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).getJobsDTO() != null) {
            excludedJobCodeList.add(((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).getJobsDTO().getCode().getKey());
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
    }

    public void addSelectedJobs() throws DataBaseException, SharedApplicationException {
        if (jobFilter != null && jobFilter.getSelectedDTOS() != null && !jobFilter.getSelectedDTOS().isEmpty()) {
            errorJobCondition = false;
            String jobCode = jobFilter.getSelectedDTOS().get(0).getCode().getKey();
            boolean checkJobCond = JobClientFactory.getJobsClient().checkJobConditions(Long.valueOf(civilId), jobCode);
            if (!checkJobCond) {
                errorJobCondition = true;
            } else if (this.divMode == JOB_DIV_MODE) {
                ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).setJobsDTO(jobFilter.getSelectedDTOS().get(0));

            }

        }
    }

    public void setAddBtnDisabled(Boolean addBtnDisabled) {
        this.addBtnDisabled = addBtnDisabled;
    }

    public Boolean getAddBtnDisabled() {
        return addBtnDisabled;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setEnableResetData(Boolean enableResetData) {
        this.enableResetData = enableResetData;
    }

    public Boolean getEnableResetData() {
        return enableResetData;
    }

    public void setFireComponentID(String fireComponentID) {
        this.fireComponentID = fireComponentID;
    }

    public String getFireComponentID() {
        return fireComponentID;
    }

    /*******************************************************************************************/


    public static MainDataBean getInstance() {
        return (MainDataBean)JSFHelper.getValue(BEAN_NAME);
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {
        return new PagingResponseDTO(new ArrayList());
    }

    public PagingResponseDTO initMainDataBean(PagingRequestDTO request) {
        request = null;

        return new PagingResponseDTO(new ArrayList());
    }

    public void filterDegreesByGroup() {
        
        List list = initListForgetSal(Long.valueOf(getContractType()));
        if (!isFromEmpList()) {
        setDegreesList(new ArrayList<SalElementGuidesDTO>());
        setDegreeCode("");
        setRaisesCount(new ArrayList());
        setRaiseCode("");
        }
       
        
        resetEqData();
        try {
            degreesList =
                    (List)SalClientFactory.getSalElementGuidesClient().getDegreeCodeNameByGroupSorted(Long.valueOf(groupCode),
                                                                                                list);

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            degreesList = new ArrayList();
        } catch (Exception e) {
            e.printStackTrace();
            degreesList = new ArrayList();
        }
    }

    public void filterRaisesByDegree() {
        resetEqData();
        if (degreeCode != null && !degreeCode.equals("")) {
            errorCanDegree = false;
            boolean condition = false;
            try {
                condition =
                        EmpClientFactory.getEmployeesClient().isConditionFromGrsValid(civilId, Long.valueOf(degreeCode));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!condition) {
                errorCanDegree = true;
                raisesCount = new ArrayList();
                return;
            }

            List list = initListForgetSal(Long.valueOf(getContractType()));
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
        } else {
            errorCanDegree = false;
            raisesCount = new ArrayList();
        }
    }

    public void showRaiseCodeValue() {
        System.out.println("raiseCode=" + raiseCode);
    }
    // return degreesList to be used in manyToMany Mood

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        app.setManyToMany(true);
        app.setShowNavigation(true);
        app.setShowsteps(true);
        app.setShowCustomDiv1(true);
        app.setShowIntegrationDiv1(true);
        app.setShowbar(false);
        return app;
    }

    public void reSetData(ActionEvent ae) {
        ae = null;

        setCivilId(null);
        getMaintainBeanInstance().setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        invalidCivilID = false;
        invalidCivilWithHireType = false;
        civilExist = false;
        NonDBCivilID = false;
        setErrorMsg("");
        setWrkCenterList(new ArrayList());
        setCaderList(new ArrayList());
        setGroupList(new ArrayList());
        setEqGroupList(new ArrayList());
        setWorkMinistriesList(new ArrayList());
        setJobList(new ArrayList());
        setBudgetList(new ArrayList());
        setBudgetTypeList(new ArrayList());
        setCaderName("");
        setDegreeName("");
        jobDescription = null;
        bgtProgramName = null;
        eqTypeTemp = false;
        setDegreesList(new ArrayList());
        setDegreeCode("");
        setRaisesCount(new ArrayList());
        setRaiseCode("");


        if (getMaintainBeanInstance() != null && getMaintainBeanInstance().getPageDTO() != null) {
            ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).setHireTypeKey(getManagedConstantsBean().getEmpHireTypeNominationByMinistry());
        }

    }

    public void fillPersonQulInfoAndSalDTOS() {
        IEmpCndSalaryElementsDTO empCndSalaryElementsDTO = EmpDTOFactory.createEmpCndSalaryElementsDTO();
        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
        ISalElementGuidesDTO salElmntGuideDTO = SalDTOFactory.createSalElementGuidesDTO();
        if (raiseCode != null && !raiseCode.equals("")) {
            salElmntGuideDTO.setCode(SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.parseLong(raiseCode)));
            try {
                empCndSalaryElementsDTO =
                        EmpClientFactory.getEmpCndSalaryElementsClient().getByCandCode(maintainBean.getPageDTO().getCode());
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
            if (empCndSalaryElementsDTO.getCode() != null) {
                if (((IEmpCandidatesDTO)maintainBean.getPageDTO()).getHireDate() != null) {
                    empCndSalaryElementsDTO.setFromDate(((IEmpCandidatesDTO)maintainBean.getPageDTO()).getHireDate());
                }
                empCndSalaryElementsDTO.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
                empCndSalaryElementsDTO.setSalElementGuidesDTO(salElmntGuideDTO);
            } else {
                empCndSalaryElementsDTO.setElementRatio(IEMPConstants._SALARY_ELEMENT_RATIO);
                if (((IEmpCandidatesDTO)maintainBean.getPageDTO()).getHireDate() == null) {
                    Calendar cal = Calendar.getInstance();
                    Date date = new Date(cal.getTimeInMillis());
                    empCndSalaryElementsDTO.setFromDate(date);
                } else {
                    empCndSalaryElementsDTO.setFromDate(((IEmpCandidatesDTO)maintainBean.getPageDTO()).getHireDate());
                }
                empCndSalaryElementsDTO.setSalElementGuidesDTO(salElmntGuideDTO);
                empCndSalaryElementsDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            }
            
            if (getRaiseCodeEq() != null && !getRaiseCodeEq().equals(""))
                empCndSalaryElementsDTO.setElmguideCodeEquv(Long.valueOf(getRaiseCodeEq()));
            else
                empCndSalaryElementsDTO.setElmguideCodeEquv(null);
            if (totalRewardAccepted != null && !totalRewardAccepted.equals(""))
                empCndSalaryElementsDTO.setElementValue(Float.valueOf(totalRewardAccepted));
            else
                empCndSalaryElementsDTO.setElementValue(null);
            ((IEmpCandidatesDTO)maintainBean.getPageDTO()).getEmpCndSalaryElementsList().clear();
            ((IEmpCandidatesDTO)maintainBean.getPageDTO()).getEmpCndSalaryElementsList().add(empCndSalaryElementsDTO);
        
        }
        ((IEmpCandidatesDTO)maintainBean.getPageDTO()).setMinExcepted(minExcepted);

    }

    public String getBgtProgramName() {
        return bgtProgramName;
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

        wcIntegrationBean.getOkCommandButton().setReRender("employeeMainDataPanelEdit,integrationDiv1 , workCenterEditPanel,workCenterCodeText, bgtNamePanel,bgtInputId,employeeMainDataPanelEdit");
        wcIntegrationBean.setReturnMethodName("empMainDataBean" + ".linkWorkCenter");
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
                workCenterKey =
                        ((IWorkCentersEntityKey)wcIntegrationBean.getSelectedDTOSList().get(0).getCode()).getWrkCode();
                workCenterName = wcIntegrationBean.getSelectedDTOSList().get(0).getName();
                setWorkCentersDTO((IWorkCentersDTO)wcIntegrationBean.getSelectedDTOSList().get(0));
                ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).setWorkCentersDTO(workCentersDTO);
                fillBgtProgram(workCenterKey);
            }
        }
    }

    private void fillBgtProgram(String workCenterKey) {
        IOrgMinExtraDataClient extraDataClient = OrgClientFactory.getOrgMinExtraDataClient();
        if (workCenterKey != null && workCenterKey != "") {
            IBgtProgramsClient bgtProgramClient = BgtClientFactory.getBgtProgramsClient();
            List list = null;
            try {
                list = bgtProgramClient.getBgtProgramByWrkCenterCode(workCenterKey);

        
            if (list.size() > 0) {
                ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).setBgtProgramsDTO((IBasicDTO)list.get(0));
                setBgtProgramName(((IBgtProgramsDTO)list.get(0)).getName());
                String bgtValue = ((IBgtProgramsDTO)list.get(0)).getCode().getKey();
                if (contractType.toString().trim().equals(IEMPConstants.CONTRACT_TYPE.toString().trim())) {
                    minExcepted =
                            extraDataClient.checkExceptedMin(CSCSecurityInfoHelper.getLoggedInMinistryCode(), 43L,
                                                             bgtValue);
                } else if (contractType.toString().trim().equals(IEMPConstants.ESTANA_TYPE.toString().trim())) {
            
                        minExcepted =
                            extraDataClient.checkExceptedMin(CSCSecurityInfoHelper.getLoggedInMinistryCode(), 44L,
                                                             bgtValue);
               
                }}
                } catch (DataBaseException e) {
                    e.printStackTrace();
                    setBgtProgramName("");
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                    setBgtProgramName("");
                }  
        }    else {
                setBgtProgramName("");
            }
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

    public void setSupervisionJobType(Long jobType) {
        this.supervisionJobType = jobType;
    }

    public Long getSupervisionJobType() {
        return supervisionJobType;
    }

    public void setHireStatusList(List hireStatusList) {
        this.hireStatusList = hireStatusList;
    }

    public List getHireStatusList() {

        if ((hireStatusList == null || hireStatusList.size() == 0) &&
            (checkCivilValidity() || getMaintainBeanInstance().getMaintainMode() == 1)) {

            try {
                hireStatusList = EmpClientFactory.getHireStatusClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
                hireStatusList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                hireStatusList = new ArrayList();
            }
        }
        return hireStatusList;
    }


    public void setHireStagesList(List hireStagesList) {
        this.hireStagesList = hireStagesList;
    }

    public List getHireStagesList() {

        if ((hireStagesList == null || hireStagesList.size() == 0) &&
            (checkCivilValidity() || getMaintainBeanInstance().getMaintainMode() == 1)) {
            try {
                hireStagesList = EmpClientFactory.getHireStagesClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
                hireStagesList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                hireStagesList = new ArrayList();
            }
        }
        return hireStagesList;
    }

    public void setJobList(List jobList) {
        this.jobList = jobList;
    }

    public void hideJobDiv() {
        setRenderJobDiv(false);
    }

    public void hideLovDiv() {
        setRenderLovDiv(false);
    }

    public List getJobList() {


        return jobList;
    }

    public Long getLoggedInMinistry() {
        return CSCSecurityInfoHelper.getLoggedInMinistryCode() == null ? DEWAN_CODE :
               CSCSecurityInfoHelper.getLoggedInMinistryCode();
    }


    public void setWorkMinistriesList(List workMinistriesList) {
        this.workMinistriesList = workMinistriesList;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setSelectedJobName(String selectedJobName) {
        this.selectedJobName = selectedJobName;
    }

    public String getSelectedJobName() {
        return selectedJobName;
    }

    public void setVirtulValue(String virtulValue) {
        this.virtulValue = virtulValue;
    }

    public String getVirtulValue() {
        return virtulValue;
    }

    public void setCaderList(List caderList) {
        this.caderList = caderList;
    }

    public List getCaderList() {
        return caderList;
    }

    public void setGroupList(List groupList) {
        this.groupList = groupList;
    }

    public List getGroupList() {
        return groupList;
    }

    public void setBudgetList(List budgetList) {
        this.budgetList = budgetList;
    }

    public List getBudgetList() {
        try {
            if (ministryEntityKey.getMinCode() != null &&
                (checkCivilValidity() || getMaintainBeanInstance().getMaintainMode() == 1))
                budgetList =
                        OrgClientFactory.getMinistriesClient().getMinRelatedCurrentPrograms(ministryEntityKey.getMinCode());
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        return budgetList;
    }

    public void setBudgetTypeList(List<IBasicDTO> budgetTypeList) {
        this.budgetTypeList = budgetTypeList;
    }

    public List<IBasicDTO> getBudgetTypeList() {
        if ((budgetTypeList == null || budgetTypeList.size() == 0) &&
            (checkCivilValidity() || getMaintainBeanInstance().getMaintainMode() == 1))
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

    public void setCaderCode(Long caderCode) {
        this.caderCode = caderCode;
    }

    public Long getCaderCode() {
        return caderCode;
    }

    public void setVirtualValue(Long virtualValue) {
        this.virtualValue = virtualValue;
    }

    public Long getVirtualValue() {
        return virtualValue;
    }

    public void setGroupCode(Long groupCode) {
        this.groupCode = groupCode;
    }

    public Long getGroupCode() {
        return groupCode;
    }


    public void setInvalidCivilID(boolean invalidCivilID) {
        this.invalidCivilID = invalidCivilID;
    }

    public boolean isInvalidCivilID() {
        return invalidCivilID;
    }

    public void setNonDBCivilID(boolean nonDBCivilID) {
        this.NonDBCivilID = nonDBCivilID;
    }

    public boolean isNonDBCivilID() {
        return NonDBCivilID;
    }

    public void setWrkCenterList(List wrkCenterList) {
        this.wrkCenterList = wrkCenterList;
    }

    public List getWrkCenterList() {
        try {
            if ((wrkCenterList == null || wrkCenterList.size() == 0) &&
                (checkCivilValidity() || getMaintainBeanInstance().getMaintainMode() == 1))
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

    public void setCivilExist(boolean civilExist) {
        this.civilExist = civilExist;
    }

    public boolean isCivilExist() {
        return civilExist;
    }

    public void setInvalidCivilWithHireType(boolean invalidCivilWithHireType) {
        this.invalidCivilWithHireType = invalidCivilWithHireType;
    }

    public boolean isInvalidCivilWithHireType() {
        return invalidCivilWithHireType;
    }

    public void setJobSearchType(String jobSearchType) {
        this.jobSearchType = jobSearchType;
    }

    public String getJobSearchType() {
        return jobSearchType;
    }

    public void setWorkCenterMode(boolean workCenterMode) {
        this.workCenterMode = workCenterMode;
    }

    public boolean isWorkCenterMode() {
        return workCenterMode;
    }


    public IBasicClient getSalElementClient() {
        return SalClientFactory.getSalElementGuidesClient();
    }


    private boolean checkCivilValidity() {
        return (!invalidCivilID && !NonDBCivilID && !invalidCivilWithHireType && civilExist);
    }

    public void setWorkCenterName(String workCenterName) {
        this.workCenterName = workCenterName;
    }

    public String getWorkCenterName() {
        if (getMaintainBeanInstance().getPageDTO() != null &&
            ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).getWorkCentersDTO() != null){
            fillBgtProgram(((IWorkCentersEntityKey)((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).getWorkCentersDTO().getCode()).getWrkCode());
            return ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).getWorkCentersDTO().getName();
            }
        return workCenterName;
    }

    public void setCaderName(String caderName) {
        this.caderName = caderName;
    }

    public String getCaderName() {
        return caderName;
    }

    public void setRenderJobDiv(boolean renderJobDiv) {
        this.renderJobDiv = renderJobDiv;
    }

    public boolean isRenderJobDiv() {
        return renderJobDiv;
    }

    public void setRenderLovDiv(boolean renderLovDiv) {
        this.renderLovDiv = renderLovDiv;
    }

    public boolean isRenderLovDiv() {
        return renderLovDiv;
    }

    public void setEqCaderName(String eqCaderName) {
        this.eqCaderName = eqCaderName;
    }

    public String getEqCaderName() {
        return eqCaderName;
    }

    public void setEqCaderCode(Long eqCaderCode) {
        this.eqCaderCode = eqCaderCode;
    }

    public Long getEqCaderCode() {
        return eqCaderCode;
    }

    public void setEqGroupCode(Long eqGroupCode) {
        this.eqGroupCode = eqGroupCode;
    }

    public Long getEqGroupCode() {
        return eqGroupCode;
    }

    public void setEqRaiseCode(Long eqRaiseCode) {
        this.eqRaiseCode = eqRaiseCode;
    }

    public Long getEqRaiseCode() {
        return eqRaiseCode;
    }

    public void setEqRaiseName(String eqRaiseName) {
        this.eqRaiseName = eqRaiseName;
    }

    public String getEqRaiseName() {
        return eqRaiseName;
    }

    public void setEqTypeTemp(boolean eqTypeTemp) {
        this.eqTypeTemp = eqTypeTemp;
    }

    public boolean isEqTypeTemp() {
        return eqTypeTemp;
    }

    public void setEqDegreeCode(Long eqDegreeCode) {
        this.eqDegreeCode = eqDegreeCode;
    }

    public Long getEqDegreeCode() {
        return eqDegreeCode;
    }

    public void setEqGroupList(List eqGroupList) {
        this.eqGroupList = eqGroupList;
    }

    public List getEqGroupList() {
        if (eqCaderCode != null && !eqCaderCode.equals("") &&
            (checkCivilValidity() || getMaintainBeanInstance().getMaintainMode() == 1)) {
            try {
                eqGroupList = SalClientFactory.getSalElementGuidesClient().getLocalGroupCodeName(eqCaderCode);
            } catch (DataBaseException f) {
                f.printStackTrace();
                eqGroupList = new ArrayList();
            } catch (Exception f) {
                f.printStackTrace();
                eqGroupList = new ArrayList();
            }
        }
        return eqGroupList;
    }

    public void setTitleFlageOfDiv(String titleFlageOfDiv) {
        this.titleFlageOfDiv = titleFlageOfDiv;
    }

    public String getTitleFlageOfDiv() {
        return titleFlageOfDiv;
    }

    public void setBgtProgramName(String bgtProgramName) {
        this.bgtProgramName = bgtProgramName;
    }

    public void setHireTypesList(List<IBasicDTO> hireTypesList) {
        this.hireTypesList = hireTypesList;
    }

    public List<IBasicDTO> getHireTypesList() {
        return hireTypesList;
    }

    public void fillHireTypesList(IEmpCandidatesDTO empCandidatesDTO) {
        Long hireTypeCode =
            Long.valueOf(((IHireTypesDTO)empCandidatesDTO.getHireTypesDTO()).getFirstParent().getKey());
        // if (hireTypesList.size() == 0) { due to CSC-22302
        try {

            hireTypesList = (

                    List)EmpClientFactory.getHireTypesClient().getRelatedByCenterTwzef(hireTypeCode,
                                                                                       getLoginedMinistrycode());
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        // }
    }

    public void setHireTypeCodeOnChange() {
        ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).setJobsDTO(null);
        resetEqData();
        renderedErrorMsgHirType = false;
        if (!getSelectedHireTypeCode().equals("-100")) {
            Long hirtypeCode = Long.valueOf(getSelectedHireTypeCode());
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
                getMaintainBeanInstance().setSelectedHireTypeKey(selectedHireTypeCode);
                ((IEmpCandidatesDTO)getPageDTO()).setHireTypeKey(selectedHireTypeCode);
            } else {
                renderedErrorMsgHirType = true;
            }

        }
    }

    public void setWorkCenterHasJobs(boolean workCenterHasJobs) {
        this.workCenterHasJobs = workCenterHasJobs;
    }

    public boolean isWorkCenterHasJobs() {
        return workCenterHasJobs;
    }

    public void checkValidFilMinNo() {
        int count = 0;
        if (getMaintainBeanInstance().getPageDTO() != null &&
            ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).getMinistryFileNo() != null &&
            !((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).getMinistryFileNo().equals("")) {

            try {
                Long civilId =
                    Long.valueOf(((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
                count =
                        EmpClientFactory.getEmpCandidatesClient().isFilNumRedundant(CSCSecurityInfoHelper.getLoggedInMinistryCode(),
                                                                                    ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).getMinistryFileNo(),
                                                                                    civilId);
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

    public void calculateNextDateOfRaise() {
        Date empHireDate = ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).getHireDate();
        if (empHireDate != null) {
            try {
                ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).setDateOfNextRaise(EmpClientFactory.getEmployeesClient().calculateNextRaiseDate(empHireDate));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).setDateOfNextRaise(null);
        }
        System.out.println("Next Raise of Date>>>>>>>" +
                           ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).getDateOfNextRaise());
    }

    public void setLoginedMinistrycode(Long loginedMinistrycode) {
        this.loginedMinistrycode = loginedMinistrycode;
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

    public void setJobDescription(String techJobName) {
        this.jobDescription = techJobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void obtainJobDescription(IJobWorkCentersEntityKey jobWorkCentersEntityKey) {

        IJobWorkCentersDTO jobWorkCentersDTO = null;
        try {
            jobWorkCentersDTO =
                    (IJobWorkCentersDTO)JobClientFactory.getJobWorkCentersClient().getById(jobWorkCentersEntityKey);
            jobDescription = jobWorkCentersDTO.getJobDescription();
        } catch (Exception e) {
            e.printStackTrace();
            jobDescription = null;
        }


    }

    public void setInValidValue(boolean inValidValue) {
        this.inValidValue = inValidValue;
    }

    public boolean isInValidValue() {
        return inValidValue;
    }

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {
        return kwtCitizensResidentsDTO;
    }

    public void setShangLangth(int shangLangth) {
        this.shangLangth = shangLangth;
    }

    public int getShangLangth() {
        return shangLangth;
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
        setCaderCode(getVirtualLongValue());
        setGroupList(new ArrayList());
        setGroupCode(getVirtualLongValue());
        setDegreesList(new ArrayList<SalElementGuidesDTO>());
        setDegreeCode("");
        setRaisesCount(new ArrayList());
        setRaiseCode("");
    }


    public void fillCaderList() {
        if (!isFromEmpList()) { 
            resetSalValues();
        }

        setMinExcepted(false);
        resetEqData();

        if (getContractType() != null && !getContractType().equals(getVirtualConstValue())) {
            if ((getContractType().equals("6") || getContractType().equals("7")) && getOldContractType().equals("0")) {
                ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).setJobsDTO(null);
            }
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
        } else {
            resetSalValues();
        }
    }

    public void fillGroupList() {
        if (!isFromEmpList()) {
            setGroupList(new ArrayList());
            setGroupCode(getVirtualLongValue());
            setDegreesList(new ArrayList<SalElementGuidesDTO>());
            setDegreeCode("");
            setRaisesCount(new ArrayList());
            setRaiseCode("");
        }
               resetEqData();
             
        if (caderCode.equals(getVirtualLongValue())) {
            setGroupCode(getVirtualLongValue());
        } else {
            List list = initListForgetSal(Long.valueOf(getContractType()));
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

    public void checkaboutHireDate() {
        IEmpCandidatesDTO empCandidateDTO = (IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO();
        Date hireDate = empCandidateDTO.getHireDate();

        if (hireDate != null) {
            if (compareHireDateWithNextYear(hireDate) || compareHireDateWithPreviousYear(hireDate)) {
                setInvalidNextYear(true);
                setRenderErrorHireDate(false);
                ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).setDateOfNextRaise(null);
            } else if (checkAboutHireDateAndCivilId(empCandidateDTO)) {
                setRenderErrorHireDate(true);
                setInvalidNextYear(false);
                ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).setDateOfNextRaise(null);
            } else {
                setRenderErrorHireDate(false);
                setInvalidNextYear(false);
                calculateNextDateOfRaise();
            }
        } else {
            setRenderErrorHireDate(false);
            setInvalidNextYear(false);
            ((IEmpCandidatesDTO)getMaintainBeanInstance().getPageDTO()).setDateOfNextRaise(null);
        }
    }

    private boolean compareHireDateWithNextYear(Date hireDate) {
        // TODO compare hireDate with getNextYear
        if (hireDate.compareTo(getNextYear()) > 0) {
            return true;
        }

        else {
            return false;
        }
    }

    private boolean compareHireDateWithPreviousYear(Date hireDate) {
        Date preYear = getPreviousYear();
        if (hireDate.getYear() >= preYear.getYear())
            if (hireDate.getMonth() >= preYear.getMonth())
                if (hireDate.getDate() >= preYear.getDate())
                    return false;
        return true;
    }

    private boolean checkAboutHireDateAndCivilId(IEmpCandidatesDTO empCandidateDTO) {
        boolean found = false;
        try {
            found = InfClientFactory.getKwtWorkDataClient().checkAboutHireDate(empCandidateDTO);
        } catch (DataBaseException e) {
            e.printStackTrace();
            found = false;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            found = false;
        }
        return found;
    }

    public void setRenderErrorHireDate(boolean renderErrorHireDate) {
        this.renderErrorHireDate = renderErrorHireDate;
    }

    public boolean isRenderErrorHireDate() {
        return renderErrorHireDate;
    }

    public void setInvalidNextYear(boolean invalidNextYear) {
        this.invalidNextYear = invalidNextYear;
    }

    public boolean isInvalidNextYear() {
        return invalidNextYear;
    }

    public void setNextYear(Date nextYear) {
        this.nextYear = nextYear;
    }

    public Date getNextYear() {
        return storeNextYearFromNow();
    }

    public Date storeNextYearFromNow() {
        java.util.Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1); // to get previous year add -1
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        Date nextYear = new java.sql.Date(cal.getTime().getTime());

        return nextYear;
    }

    public void setPreviousYear(Date previousYear) {
        this.previousYear = previousYear;
    }

    public Date getPreviousYear() {
        return storePreviousYearFromNow();
    }

    public Date storePreviousYearFromNow() {
        java.util.Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date preYear = new java.sql.Date(cal.getTime().getTime());
        return preYear;
    }


    public void fillQualIntegration(Long civilId) {
        Integer WIZARD_BAR_PAGE = 1;

        QulIntegrationListBean qulIntegrationListBean =
            (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
        qulIntegrationListBean.setCivilId(civilId);
        qulIntegrationListBean.setPageType(WIZARD_BAR_PAGE);
        qulIntegrationListBean.setAddQualificationIntegrationpage("AddQualificationIntegrationInEdit");
        qulIntegrationListBean.setQulListPageinWizardBar("empQualificationsCandidateEdit");
        qulIntegrationListBean.setSaveInDB(false);
        qulIntegrationListBean.setBundleMsg(getMaintainBeanInstance().getBundleMsg());
        qulIntegrationListBean.getSaveStateList().add(getMaintainBeanInstance().getInstance());
        qulIntegrationListBean.getSaveStateList().add(getMaintainBeanInstance().getMaintainBean());
        qulIntegrationListBean.setDataTablestyleClass("dataT-With-3-row-filter");
//        qulIntegrationListBean.setCustomCurentQual(true);
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        integrationBean.getHmBaseBeanFrom().put("qulIntegrationBean", qulIntegrationListBean);
    }


    public static MaintainBean getMaintainBeanInstance() {
        return (MaintainBean)JSFHelper.getValue("empMaintainBean");
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setRaisesCount(List<IBasicDTO> raisesCount) {
        this.raisesCount = raisesCount;
    }

    public List<IBasicDTO> getRaisesCount() {
        return raisesCount;
    }

    public void setRaiseName(String raiseName) {
        this.raiseName = raiseName;
    }

    public String getRaiseName() {
        return raiseName;
    }


    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    public String getDegreeCode() {
        return degreeCode;
    }

    public void setDegreesList(List<SalElementGuidesDTO> degreesList) {
        this.degreesList = degreesList;
    }

    public List<SalElementGuidesDTO> getDegreesList() {
        return degreesList;
    }

    public void setRaiseCode(String raiseCode) {
        this.raiseCode = raiseCode;
    }

    public String getRaiseCode() {
        return raiseCode;
    }

    public void setDataDisabledIfEmpFromCRS(boolean dataDisabledIfEmpFromCRS) {
        this.dataDisabledIfEmpFromCRS = dataDisabledIfEmpFromCRS;
    }

    public boolean isDataDisabledIfEmpFromCRS() {
        return dataDisabledIfEmpFromCRS;
    }

    public void setSelectedHireTypeCode(String selectedHireTypeCode) {
        this.selectedHireTypeCode = selectedHireTypeCode;
        getMaintainBeanInstance().setSelectedHireTypeKey(selectedHireTypeCode);
    }

    public String getSelectedHireTypeCode() {
        return selectedHireTypeCode;
    }

    public void setRenderQulType(boolean renderQulType) {
        this.renderQulType = renderQulType;
    }

    public boolean isRenderQulType() {
        return renderQulType;
    }

    public void setJobFilter(JobFilter jobFilter) {
        this.jobFilter = jobFilter;
    }

    public JobFilter getJobFilter() {
        return jobFilter;
    }

    public void setDivMode(int divMode) {
        this.divMode = divMode;
    }

    public int getDivMode() {
        return divMode;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractType() {
        return contractType;
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

    public String getContracts() {
        return String.valueOf(IEMPConstants.CONTRACT_TYPE);
    }

    public String getOtherType() {
        return OTHER_TYPE;
    }

    public String getEstanaType() {
        return String.valueOf(IEMPConstants.ESTANA_TYPE);
    }

    public void setWcIntegrationBean(WorkCentersHelperBean wcIntegrationBean) {
        this.wcIntegrationBean = wcIntegrationBean;
    }

    public WorkCentersHelperBean getWcIntegrationBean() {
        return wcIntegrationBean;
    }

    public void setWorkCentersDTO(IWorkCentersDTO workCentersDTO) {
        this.workCentersDTO = workCentersDTO;
    }

    public IWorkCentersDTO getWorkCentersDTO() {
        return workCentersDTO;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setWorkCenterKey(String workCenterKey) {
        this.workCenterKey = workCenterKey;
    }

    public String getWorkCenterKey() {
        return workCenterKey;
    }

    public void setFromEmpList(boolean fromEmpList) {
        this.fromEmpList = fromEmpList;
    }

    public boolean isFromEmpList() {
        return fromEmpList;
    }

    public void setRenderedErrorMsgHirType(boolean renderedErrorMsgHirType) {
        this.renderedErrorMsgHirType = renderedErrorMsgHirType;
    }

    public boolean isRenderedErrorMsgHirType() {
        return renderedErrorMsgHirType;
    }

    public void setOldContractType(String oldContractType) {
        this.oldContractType = oldContractType;
    }

    public String getOldContractType() {
        return oldContractType;
    }

    public void setErrorCanDegree(boolean errorCanDegree) {
        this.errorCanDegree = errorCanDegree;
    }

    public boolean isErrorCanDegree() {
        return errorCanDegree;
    }

    public void setKuwaitCitizen(boolean kuwaitCitizen) {
        this.kuwaitCitizen = kuwaitCitizen;
    }

    public boolean isKuwaitCitizen() {
        return kuwaitCitizen;
    }

    public void setErrorJobCondition(boolean errorJobCondition) {
        this.errorJobCondition = errorJobCondition;
    }

    public boolean isErrorJobCondition() {
        return errorJobCondition;
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
