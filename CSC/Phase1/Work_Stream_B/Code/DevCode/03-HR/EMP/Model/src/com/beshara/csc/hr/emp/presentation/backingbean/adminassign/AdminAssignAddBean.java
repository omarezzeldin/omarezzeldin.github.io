package com.beshara.csc.hr.emp.presentation.backingbean.adminassign;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IAdminAssignmentsDTO;
import com.beshara.csc.hr.emp.business.dto.IAssignReasonsDTO;
import com.beshara.csc.hr.emp.business.dto.IAssignTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.sal.business.client.ISalElementGuidesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobSearchCriteriaDTO;
import com.beshara.csc.nl.job.business.dto.IJobWorkCentersDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.business.entity.IJobWorkCentersEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersSearchCriteriaDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NotMatchedOnHireTypeException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.emp.InvalidHireStatusException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class AdminAssignAddBean extends LookupMaintainBaseBean {
    private static final String NAVIGATION_KEY = "adminassignadd";
    private static final String BEAN_NAME = "adminAssignAddBean";
    private Long civilId; // 279051900069 
    private boolean validCivilId = true;
    private boolean civilExist;
    private boolean empNotHired;
    private List<IBasicDTO> assignTypesDTOList;
    private Long assignTypesCode;
    private List<IBasicDTO> cadersList;
    private Long caderCode;
    private List<IBasicDTO> groupsList;
    private Long groupCode;
    private List<IJobsDTO> jobsDTOList;
    private String jobCode;
    private List<IBasicDTO> assignReasonsDTOList;
    private Long assignReasonCode;
    private List<IBasicDTO> workMinistriesList;
    private String workMinistrieKey;
    private boolean workCenterHasJobs = false;
    private String jobName = null;
    private String workCenterName = null;
    private String jobDescription = null;
    private String caderName;
    private String raiseName;
    private String groupName;
    
    public AdminAssignAddBean() {
        setPageDTO(EmpDTOFactory.createAdminAssignmentsDTO());
        setClient(EmpClientFactory.getAdminAssignmentsClient());
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        setEmpListOfValues(new EmployeeListOfValues());
    }

    public static String getNavigationKey() {
        return NAVIGATION_KEY;
    }

    public String goToListPage() {
        AdminAssignListBean adminAssignListBean = 
            AdminAssignListBean.getInstance();
        adminAssignListBean.setHighlightedDTOS(new ArrayList<IBasicDTO>());
        return adminAssignListBean.getNavigationKey();
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        app.setShowEmpSrchDiv(true);
        app.setShowLovDiv(true);
        return app;
    }
    
    public void populateSalariesData(Long civilId) {
        try {
            Long raiseCode = null;
            Long degreeCode = null;
            Long groupCode = null;
            Long caderCode = null;

            ISalElementGuidesDTO raiseDTO = 
                SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO degreeDTO = 
                SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO groupDTO = 
                SalDTOFactory.createSalElementGuidesDTO();
//            Long civilId = 
//                ((IKwtCitizensResidentsEntityKey)((IEmployeesDTO)getPageDTO()).getCitizensResidentsDTO().getCode()).getCivilId();


            ISalEmpSalaryElementsDTO salaryElementDTO = 
                (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(civilId, 
                                                                                                                     ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);

            //Raise
            if (salaryElementDTO != null && 
                salaryElementDTO.getCode() != null) {
                raiseCode = 
                        ((ISalElementGuidesEntityKey)salaryElementDTO.getSalElementGuidesDTO().getCode()).getElmguideCode();
                ((IAdminAssignmentsDTO)getPageDTO()).getEmployeesDTO().getSalEmpSalaryElementsDTOList();
                if (raiseCode != null) {
                    raiseDTO = 
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(raiseCode);
                    setRaiseName(raiseDTO.getName());
                }
                //Degree
                if (raiseDTO.getParentObject() != null && 
                    raiseDTO.getParentObject().getCode() != null) {
                    degreeCode = 
                            ((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode();
                }
                if (degreeCode != null) {
                    degreeDTO = 
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(degreeCode);
                }
                //Group
                if (degreeDTO.getParentObject() != null && 
                    degreeDTO.getParentObject().getCode() != null) {
                    groupCode = 
                            ((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode();
                }

                if (groupCode != null) {

                    groupDTO = 
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(groupCode);
                    setGroupCode(groupCode);
                    setGroupName(groupDTO.getName());
                    //getGroupList();

                }
                //Cader
                if (groupDTO.getParentObject() != null && 
                    groupDTO.getParentObject().getCode() != null) {
                    caderCode = 
                            ((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode();
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

    public void setAssignTypesDTOList(List<IBasicDTO> assignTypesDTOList) {
        this.assignTypesDTOList = assignTypesDTOList;
    }

    public List<IBasicDTO> getAssignTypesDTOList() {
        if (assignTypesDTOList == null) {
            try {
                assignTypesDTOList = 
                        EmpClientFactory.getAssignTypesClient().getCodeName();
            } catch (DataBaseException e) {
                ;
            }
        }
        return assignTypesDTOList;
    }

    public void setCadersList(List<IBasicDTO> cadersList) {
        this.cadersList = cadersList;
    }

    public List<IBasicDTO> getCadersList() {
        if (cadersList == null) {
            try {//by Ashraf Gaber GN Issue
                cadersList = SalClientFactory.getSalElementGuidesClient().getCaderCodeNameByElmMinistryCode(getManagedConstantsBean().getMinCode());
                        //SalClientFactory.getSalElementGuidesClient().getLocalCaderCodeName();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cadersList;
    }

    public void setGroupsList(List<IBasicDTO> groupsList) {
        this.groupsList = groupsList;
    }

    public void fillGroups(ActionEvent aE) {
        aE = null;
        groupCode = null;
        groupsList = null;
        if (caderCode == null) {
            return;
        }
        try {
            groupsList = 
                    SalClientFactory.getSalElementGuidesClient().getGroupCodeName(caderCode);
        } catch (DataBaseException e) {
        }
    }

    public List<IBasicDTO> getGroupsList() {
        return groupsList;
    }

    public void setCaderCode(Long caderCode) {
        this.caderCode = caderCode;
    }

    public Long getCaderCode() {
        return caderCode;
    }

    public void setAssignTypesCode(Long assignTypesCode) {
        this.assignTypesCode = assignTypesCode;
    }

    public Long getAssignTypesCode() {
        return assignTypesCode;
    }

    public void setGroupCode(Long groupCode) {
        this.groupCode = groupCode;
    }

    public Long getGroupCode() {
        return groupCode;
    }

    public void setJobsDTOList(List<IJobsDTO> jobsDTOList) {
        this.jobsDTOList = jobsDTOList;
    }

    public List<IJobsDTO> getJobsDTOList() {
        if (jobsDTOList == null) {
            jobsDTOList = new ArrayList<IJobsDTO>();
        }
        return jobsDTOList;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    private String getMainJobCode() {
        IBasicDTO jobsDTO = 
            ((IAdminAssignmentsDTO)getPageDTO()).getEmployeesDTO().getTechJobsDTO();
        ;
        if (jobsDTO == null) {
            jobsDTO = 
                    ((IAdminAssignmentsDTO)getPageDTO()).getEmployeesDTO().getJobDTO();
        }
        if (jobsDTO != null) {
            return jobsDTO.getCode().getKey().toString();
        }
        return null;
    }

    private String getMainWorkMinistryKey() {
        IBasicDTO workCentersDTO = 
            ((IAdminAssignmentsDTO)getPageDTO()).getEmployeesDTO().getWorkCenterDTO();
        if (workCentersDTO != null) {
            return workCentersDTO.getCode().getKey().toString();
        }
        return null;
    }

    private ISalElementGuidesDTO getMainGroupDTO() {
        ISalElementGuidesDTO groupDTO = null;
        List<ISalEmpSalaryElementsDTO> salEmpSalaryElementsDTOList = 
            ((IAdminAssignmentsDTO)getPageDTO()).getEmployeesDTO().getSalEmpSalaryElementsDTOList();
        if (salEmpSalaryElementsDTOList != null && 
            salEmpSalaryElementsDTOList.size() > 0) {
            IBasicDTO salElementGuidesDTOBeforeDegree = 
                salEmpSalaryElementsDTOList.get(0).getSalElementGuidesDTO().getParentObject();
            if (salElementGuidesDTOBeforeDegree != null) {
                groupDTO = 
                        (ISalElementGuidesDTO)((ISalElementGuidesDTO)salElementGuidesDTOBeforeDegree).getParentObject();
            }
        }
        return groupDTO;
    }

    private void fillCombosWithDefaultData() {
        IEmployeesDTO employeesDTO = ((IAdminAssignmentsDTO)getPageDTO()).getEmployeesDTO();
        if (employeesDTO.getJobDTO() != null) {
            setJobName(employeesDTO.getJobDTO().getName());
        }
        if ((jobCode == null || jobCode.length() == 0) && 
            jobsDTOList != null) {
            jobCode = getMainJobCode();
        }
        if ((workMinistrieKey == null || workMinistrieKey.length() == 0) && 
            workMinistriesList != null) {
            workMinistrieKey = getMainWorkMinistryKey();
        }
        if (workMinistrieKey != null && !workMinistrieKey.equals("")) {

            String[] keys = workMinistrieKey.split("[*]");
            String workCenterCode = keys[1];
            obtainJobDescription(JobEntityKeyFactory.createJobWorkCentersEntityKey(jobCode, getManagedConstantsBean().getMinCode(), workCenterCode));
        }
        if (caderCode != null && cadersList != null) {
            ISalElementGuidesDTO groupDTO = getMainGroupDTO();
            if (groupDTO != null) {
                ISalElementGuidesDTO caderDTO = 
                    (ISalElementGuidesDTO)groupDTO.getParentObject();
                if (caderDTO != null) {
                    caderCode = 
                            Long.parseLong(caderDTO.getCode().getKeys()[0].toString());
                    fillGroups(null);
                    groupCode = 
                            Long.parseLong(groupDTO.getCode().getKeys()[0].toString());
                }
            }
        }
    }

    public boolean isAdminAssigmentTypeMustChangeData() { //TODO 
        if (((IAdminAssignmentsDTO)getPageDTO()).getEmployeesDTO() == null) {
            ((IAdminAssignmentsDTO)getPageDTO()).setEmployeesDTO(EmpDTOFactory.createEmployeesDTO());
        }
        Long mainGroupCode = null;
        String mainWrkCenterKey = getMainWorkMinistryKey();
        String mainJobCode = getMainJobCode();
        ISalElementGuidesDTO groupDTO = getMainGroupDTO();
        if (groupDTO != null) {
            mainGroupCode = 
                    Long.parseLong(groupDTO.getCode().getKeys()[0].toString());
        }
        if ((mainGroupCode != null && groupCode != null && 
             mainGroupCode.equals(groupCode)) && 
            (mainWrkCenterKey != null && workMinistrieKey != null && 
             mainWrkCenterKey.equals(workMinistrieKey)) && 
            (mainJobCode != null && jobCode != null && 
             mainJobCode.equals(jobCode))) {
            return true;
        }
        return false;
    }

    public void setAssignReasonCode(Long assignReasonCode) {
        this.assignReasonCode = assignReasonCode;
    }

    public Long getAssignReasonCode() {
        return assignReasonCode;
    }

    public void setAssignReasonsDTOList(List<IBasicDTO> assignReasonsDTOList) {
        this.assignReasonsDTOList = assignReasonsDTOList;
    }

    public List<IBasicDTO> getAssignReasonsDTOList() {
        if (assignReasonsDTOList == null) {
            try {
                assignReasonsDTOList = 
                        EmpClientFactory.getAssignReasonsClient().getCodeName();
            } catch (DataBaseException e) {
                ;
            }
        }
        return assignReasonsDTOList;
    }

    public void reSetData() {
        ((IAdminAssignmentsDTO)getPageDTO()).setEmployeesDTO(EmpDTOFactory.createEmployeesDTO());
        civilId = null;
        validCivilId = true;
        civilExist = false;
        empNotHired = false;
        assignTypesCode = null;
        caderCode = null;
        groupCode = null;
        jobCode = null;
        assignReasonCode = null;
        workMinistrieKey = null;
        jobName = null;
        workCenterName = null;
        jobDescription = null;
        setCaderName("");
        setRaiseName("");
        setGroupName("");
    }

    public void checkAvailable() throws DataBaseException {
        empNotHired = false;
        civilExist = true;
        try {
            InfClientFactory.getKwtCitizensResidentsClient().checkCivilIdExist(civilId);
            try {
                EmpClientFactory.getEmployeesClient().isEmployeeHired(EmpEntityKeyFactory.createEmployeesEntityKey(civilId));
            } catch (InvalidHireStatusException e) {
                empNotHired = true;
                setPageDTO(EmpDTOFactory.createAdminAssignmentsDTO());
                setMyTableData(new ArrayList());
                e.printStackTrace();
                return;
            } catch (SharedApplicationException e) {
                empNotHired = true;
                setPageDTO(EmpDTOFactory.createAdminAssignmentsDTO());
                setMyTableData(new ArrayList());
                e.printStackTrace();
                return;
            }
            validCivilId = true;
            civilExist = true;
            try {
                IEmployeesDTO employeesDTO = 
                    (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getById(EmpEntityKeyFactory.createEmployeesEntityKey(civilId));
                ((IAdminAssignmentsDTO)getPageDTO()).setEmployeesDTO(employeesDTO);
                populateSalariesData(civilId);
                fillCombosWithDefaultData();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SharedApplicationException e) {
            validCivilId = false;
            civilExist = false;
            setPageDTO(EmpDTOFactory.createAdminAssignmentsDTO());
        }
    }

    public void showSearchForEmployeeDiv() {
        EmployeeListOfValues empListOfValuesBean = 
            ((EmployeeListOfValues)getEmpListOfValues());
        empListOfValuesBean.setReturnMethodName(BEAN_NAME + 
                                                ".returnMethodAction");
        empListOfValuesBean.resetData();
        empListOfValuesBean.getOkCommandButton().setReRender("lookupAddDiv , scriptPnl");
    }

    public void returnMethodAction() {
        if (getEmpListOfValues().getSelectedDTOS() != null && 
            getEmpListOfValues().getSelectedDTOS().get(0) != null && 
            getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
            validCivilId = true;
            civilExist = true;
            empNotHired = false;
            IEmployeesDTO employeesDTO = 
                (IEmployeesDTO)getEmpListOfValues().getSelectedDTOS().get(0);
            civilId = 
                    ((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId();
            try {
                employeesDTO = 
                        (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getById(EmpEntityKeyFactory.createEmployeesEntityKey(civilId));
            } catch (SharedApplicationException e) {
                ;
            } catch (DataBaseException e) {
                ;
            }
            ((IAdminAssignmentsDTO)getPageDTO()).setEmployeesDTO(employeesDTO);
            fillCombosWithDefaultData();
        }
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
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

    public void setWorkMinistriesList(List<IBasicDTO> workMinistriesList) {
        this.workMinistriesList = workMinistriesList;
    }

    public List<IBasicDTO> getWorkMinistriesList() {
        if (workMinistriesList == null) {
            try {
                workMinistriesList = //OrgClientFactory.getWorkCentersClient().getAllActiveAndPendingWorkcenters(getManagedConstantsBean().getMinCode());
                        OrgClientFactory.getWorkCentersClient().getAllByMinistry(OrgEntityKeyFactory.createMinistriesEntityKey(getManagedConstantsBean().getMinCode()));
            } catch (Exception e) {
                e.printStackTrace();
                workMinistriesList = new ArrayList<IBasicDTO>();
            }
        }
        return workMinistriesList;
    }

    public void showWorkMinistriesListOfValuesDiv() {
        //setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(getWorkMinistriesList());
        getLovBaseBean().setReturnMethodName(BEAN_NAME + 
                                             ".returnWorkMinistriesLovDiv");
        getLovBaseBean().setSearchMethod(BEAN_NAME + 
                                         ".searchWorkMinistriesLovDiv");
        getLovBaseBean().setCancelSearchMethod(BEAN_NAME + 
                                               ".cancelSearchWorkMinistriesLovDiv");
        getLovBaseBean().setRenderedDropDown("work_Ministries,jobPanelId");
        getLovBaseBean().setLovDivLabelSearch(getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", 
                                                                             "wrkCenter"));
        if (workMinistrieKey != null)
            getLovBaseBean().setSelectedRadio(workMinistrieKey);
        getLovBaseBean().cancelSearchLovDiv();
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setHighlightedDTOS(null);
        getLovBaseBean().setKeyIndex(1L);
        getLovBaseBean().resetPageIndex();
    }

    public String searchWorkMinistriesLovDiv(Object searchType, 
                                             Object searchQuery) {
        try {
            IWorkCentersSearchCriteriaDTO srchDTO = 
                OrgDTOFactory.createWorkCentersSearchCriteriaDTO();
            srchDTO.setMinCode(getManagedConstantsBean().getMinCode());
            if (searchQuery != null && !searchQuery.equals("")) {
                if (searchType.toString().equals("0")) {
                    srchDTO.setWorkCode(searchQuery.toString());
                } else if (searchType.toString().equals("1")) {
                    String wrkName = new String(searchQuery.toString());
                    srchDTO.setWorkName(formatSearchString(wrkName));
                }
                getLovBaseBean().setMyTableData(OrgClientFactory.getWorkCentersClient().search(srchDTO));
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }
        getLovBaseBean().resetPageIndex();
        return "";
    }

    public String cancelSearchWorkMinistriesLovDiv() {
        getLovBaseBean().setMyTableData(getWorkMinistriesList());
        setSelectedRadio("");
        return "";
    }

    public String returnWorkMinistriesLovDiv() {
        try {
            workMinistrieKey = 
                    getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString();
            workCenterName = 
                    getLovBaseBean().getSelectedDTOS().get(0).getName();
            jobName = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    public void fillJobDiv() {

        if (workMinistrieKey != null && !workMinistrieKey.equals("")) {

            String[] keys = workMinistrieKey.split("[*]");
            String workCenterCode = keys[1];

            try {
                setJobsDTOList((List)JobClientFactory.getJobsClient().getCodeNameByWorkCenterCodeAndHierarchicalFlag(workCenterCode));
                workCenterHasJobs = true;
            } catch (Exception e) {
                setJobsDTOList(new ArrayList<IJobsDTO>());
            }

//            if (getJobsDTOList().size() == 0) {
//                workCenterHasJobs = false;
//                try {
//                    setJobsDTOList(JobClientFactory.getJobsClient().getCodeName());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }

        } else {
            setJobsDTOList(new ArrayList<IJobsDTO>());
            workCenterHasJobs = false;
        }
    }

    public void showJobsListOfValuesDiv() {
        //setLovBaseBean(new LOVBaseBean());

        getLovBaseBean().setReturnMethodName(BEAN_NAME + ".returnJobsLovDiv");
        getLovBaseBean().setSearchMethod(BEAN_NAME + ".searchJobsLovDiv");
        getLovBaseBean().setCancelSearchMethod(BEAN_NAME + 
                                               ".cancelSearchJobsLovDiv");
        getLovBaseBean().setRenderedDropDown("employeeEnteredDataPanel,scriptPnl");
        getLovBaseBean().setLovDivLabelSearch(getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", 
                                                                             "job_name"));
        if (jobCode != null)
            getLovBaseBean().setSelectedRadio(jobCode);
        getLovBaseBean().cancelSearchLovDiv();
        getLovBaseBean().setMyTableData(getJobsDTOList());
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setHighlightedDTOS(null);
        getLovBaseBean().setKeyIndex(null);
        getLovBaseBean().resetPageIndex();
    }

    public String searchJobsLovDiv(Object searchType, Object searchQuery) {
        if (workCenterHasJobs) {
            try {
                IJobSearchCriteriaDTO jobSearchCriteriaDTO = 
                    JobDTOFactory.createJobSearchCriteriaDTO();
                jobSearchCriteriaDTO.setJobFreez(ISystemConstant.NON_FREEZE_FLAG);
                if (searchQuery != null && !searchQuery.equals("")) {
                    String[] keys = workMinistrieKey.split("[*]");
                    String workCenterCode = keys[1];
                    jobSearchCriteriaDTO.setWorkCenterCode(workCenterCode);
                
                    if (searchType.toString().equals("0")) {
                        jobSearchCriteriaDTO.setJobCode(searchQuery.toString());
                    } else if (searchType.toString().equals("1")) {
                        jobSearchCriteriaDTO.setJobName(searchQuery.toString());
                    }
                    getLovBaseBean().setMyTableData(JobClientFactory.getJobsClient().search(jobSearchCriteriaDTO));
                }
            } catch (Exception e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList(0));
            }
        } else {
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }
        getLovBaseBean().resetPageIndex();
        return "";
    }

    public String cancelSearchJobsLovDiv() {
        fillJobDiv();
        getLovBaseBean().setMyTableData(getJobsDTOList());
        setSelectedRadio("");
        return "";
    }

    public String returnJobsLovDiv() {
        try {
            jobCode = 
                    getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString();
            jobName = getLovBaseBean().getSelectedDTOS().get(0).getName();
            if (workMinistrieKey != null && !workMinistrieKey.equals("")) {

                String[] keys = workMinistrieKey.split("[*]");
                String workCenterCode = keys[1];
                obtainJobDescription(JobEntityKeyFactory.createJobWorkCentersEntityKey(jobCode, getManagedConstantsBean().getMinCode(), workCenterCode));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "";
    }

    public void showCadersListOfValuesDiv() {
        //setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(getCadersList());
        getLovBaseBean().setReturnMethodName(BEAN_NAME + 
                                             ".returnCadersLovDiv");
        getLovBaseBean().setSearchMethod(BEAN_NAME + ".searchCadersLovDiv");
        getLovBaseBean().setCancelSearchMethod(BEAN_NAME + 
                                               ".cancelSearchCadersLovDiv");
        getLovBaseBean().setRenderedDropDown("new_cader,grp_panelGrp");
        getLovBaseBean().setLovDivLabelSearch(getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", 
                                                                             "cader"));
        if (caderCode != null)
            getLovBaseBean().setSelectedRadio(caderCode.toString());
        getLovBaseBean().cancelSearchLovDiv();
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setHighlightedDTOS(null);
        getLovBaseBean().setKeyIndex(null);
        getLovBaseBean().resetPageIndex();
    }

    public String searchCadersLovDiv(Object searchType, Object searchQuery) {
        try {
            ISalElementGuidesClient salElementGuidesClient = 
                SalClientFactory.getSalElementGuidesClient();
            if (searchQuery != null && !searchQuery.equals("")) {
                if (searchType.toString().equals("0")) {//by Ashraf Gaber GN Issue
                    getLovBaseBean().setMyTableData(salElementGuidesClient.searchByCaderCodeAndMinCode(getManagedConstantsBean().getMinCode(), Long.valueOf(searchQuery.toString())));
                    //getLovBaseBean().setMyTableData(salElementGuidesClient.searchPayrollByCode(Long.valueOf(searchQuery.toString())));
                } else if (searchType.toString().equals("1")) {
                    getLovBaseBean().setMyTableData(salElementGuidesClient.searchByCaderNameAndMinCode(getManagedConstantsBean().getMinCode(), searchQuery.toString()));
                    //getLovBaseBean().setMyTableData(salElementGuidesClient.searchPayrollsByName(searchQuery.toString()));
                }
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }
        getLovBaseBean().resetPageIndex();
        return "";
    }

    public String cancelSearchCadersLovDiv() {
        getLovBaseBean().setMyTableData(getCadersList());
        setSelectedRadio("");
        return "";
    }

    public String returnCadersLovDiv() {
        caderCode = 
                Long.parseLong(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString());
        fillGroups(null);
        return "";
    }

    public void setWorkMinistrieKey(String workMinistrieKey) {
        this.workMinistrieKey = workMinistrieKey;
    }

    public String getWorkMinistrieKey() {
        return workMinistrieKey;
    }

    private void fillPageDTOWithData() {
        IAdminAssignmentsDTO dto = (IAdminAssignmentsDTO)getPageDTO();
        if (assignReasonsDTOList != null && assignReasonCode != null) {
            dto.setAssignReasonsDTO((IAssignReasonsDTO)getSelectedComboDTO(assignReasonsDTOList, 
                                                                           assignReasonCode.toString()));
        }
        if (assignTypesDTOList != null && assignTypesCode != null) {
            dto.setAssignTypesDTO((IAssignTypesDTO)getSelectedComboDTO(assignTypesDTOList, 
                                                                       assignTypesCode.toString()));
        }
        if (groupsList != null && groupCode != null) {
            dto.setSalElementGuidesDTO((ISalElementGuidesDTO)getSelectedComboDTO(groupsList, 
                                                                                 groupCode.toString()));
        }
        if (workMinistriesList != null && workMinistrieKey != null && 
            workMinistrieKey.length() != 0) {
            dto.setWorkCentersDTO((IWorkCentersDTO)getSelectedComboDTO(workMinistriesList, 
                                                                       workMinistrieKey));
        }
        if (jobsDTOList != null && jobCode != null && jobCode.length() != 0) {
            dto.setJobsDTO((IJobsDTO)getSelectedComboDTO((List)jobsDTOList, 
                                                         jobCode));
        } // // for test onlyyyyyyyyyyyyyyyyyyyyyyyyyyy 
        // IDecisionMakerTypesDTO decisionMakerTypesDTO = InfDTOFactory.createDecisionMakerTypesDTO ( ) ; 
        // decisionMakerTypesDTO.setCode ( InfEntityKeyFactory.createDecisionMakerTypesEntityKey ( 1L ) ) ; 
        // dto.setDecisionMakerTypesDTO ( decisionMakerTypesDTO ) ; 
    }

    private IBasicDTO getSelectedComboDTO(List<IBasicDTO> list, String key) {
        for (IBasicDTO item: list) {
            if (item.getCode().getKey().toString().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public String saveAdminAssign() throws DataBaseException {
        fillPageDTOWithData();
        AdminAssignListBean adminAssignListBean = 
            AdminAssignListBean.getInstance();
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            if (((IAdminAssignmentsDTO)getPageDTO()).getJobsDTO() == null && 
                ((IAdminAssignmentsDTO)getPageDTO()).getEmployeesDTO().getJobDTO() != 
                null) {
                ((IAdminAssignmentsDTO)getPageDTO()).setJobsDTO((IJobsDTO)((IAdminAssignmentsDTO)getPageDTO()).getEmployeesDTO().getJobDTO());
            }
            setPageDTO(getClient().add(getPageDTO()));
            adminAssignListBean.setPageDTO(getPageDTO());
            if (adminAssignListBean.isUsingPaging()) {
                adminAssignListBean.getPagingBean().clearDTOS();
                adminAssignListBean.generatePagingRequestDTO((String)getPageDTO().getCode().getKey());
            } else {
                adminAssignListBean.getAll();
                adminAssignListBean.getPageIndex((String)getPageDTO().getCode().getKey());
            }
            if (adminAssignListBean.getHighlightedDTOS() != null) {
                adminAssignListBean.getHighlightedDTOS().add(getPageDTO());
            }
            sharedUtil.setSuccessMsgValue("SuccessAdds");
            return adminAssignListBean.getNavigationKey();
        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInAdd");
        } catch (NotMatchedOnHireTypeException e) {
            this.setShowErrorMsg(true);
            getSharedUtil().handleException(e, 
                                            "com.beshara.csc.hr.emp.presentation.resources.emp", 
                                            "NotMatchedOnHireTypeException_adminassign");
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, 
                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                       "FailureInAdd");
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, 
                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                       "FailureInAdd");
        } //added by nora to reset radio if list has radio column 
        adminAssignListBean.setSelectedRadio("");
        return null;
    }

    public void setEmpNotHired(boolean empNotHired) {
        this.empNotHired = empNotHired;
    }

    public boolean isEmpNotHired() {
        return empNotHired;
    }

    public void changeData(ActionEvent aE) {
        aE = null;
    }

    public void changeWorkCenterData(ActionEvent aE) {
        aE = null;
        jobName = null;
        jobCode = null;
        jobDescription = null;
    }

    public Long getAssignTypeCodeWithPrivilege() {
        return IEMPConstant.ADMIN_ASSIGNMENT_WITH_PRIVILEGE_TYPE;
    }

    public Long getAssignTypeCodeAdminAssimentType() {
        return IEMPConstant.ADMIN_ASSIGNMENT_TYPE;
    }

    public void setWorkCenterHasJobs(boolean workCenterHasJobs) {
        this.workCenterHasJobs = workCenterHasJobs;
    }

    public boolean isWorkCenterHasJobs() {
        return workCenterHasJobs;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setWorkCenterName(String workCenterName) {
        this.workCenterName = workCenterName;
    }

    public String getWorkCenterName() {
        return workCenterName;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobDescription() {
        return jobDescription;
    }
    
    public void obtainJobDescription(IJobWorkCentersEntityKey jobWorkCentersEntityKey){
     IJobWorkCentersDTO jobWorkCentersDTO=null;
        try {
            jobWorkCentersDTO = (IJobWorkCentersDTO)JobClientFactory.getJobWorkCentersClient().getById(jobWorkCentersEntityKey);
            jobDescription = jobWorkCentersDTO.getJobDescription();
        } catch (Exception e) {
            e.printStackTrace();
            jobDescription=null;
        }
    }

    public void setCaderName(String caderName) {
        this.caderName = caderName;
    }

    public String getCaderName() {
        return caderName;
    }

    public void setRaiseName(String raiseName) {
        this.raiseName = raiseName;
    }

    public String getRaiseName() {
        return raiseName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }
}
