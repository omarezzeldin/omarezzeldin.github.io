package com.beshara.csc.hr.emp.presentation.backingbean.adminassign;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.IAdminAssignmentsDTO;
import com.beshara.csc.hr.emp.business.dto.IAssignReasonsDTO;
import com.beshara.csc.hr.emp.business.dto.IAssignTypesDTO;
import com.beshara.csc.hr.sal.business.client.ISalElementGuidesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
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
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NotMatchedOnHireTypeException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class AdminAssignEditBean extends LookupMaintainBaseBean {
    private static final String BEAN_NAME = "adminAssignEditBean";
    private static final String NAVIGATION_KEY = "adminassignedit";
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

    public AdminAssignEditBean() {
        setClient(EmpClientFactory.getAdminAssignmentsClient());
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
    }

    public static String getNavigationKey() {
        return NAVIGATION_KEY;
    }

    public static AdminAssignEditBean getInstance() {
        return (AdminAssignEditBean)JSFHelper.getValue(BEAN_NAME);
    }

    public String goToListPage() {
        AdminAssignListBean adminAssignListBean = 
            AdminAssignListBean.getInstance();
        adminAssignListBean.setHighlightedDTOS(new ArrayList<IBasicDTO>());
        return adminAssignListBean.getNavigationKey();
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
                ((IAdminAssignmentsDTO)getSelectedPageDTO()).getEmployeesDTO().getSalEmpSalaryElementsDTOList();
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

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        app.setShowLovDiv(true);
        return app;
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
            try { //by Ashraf Gaber GN Issue
                cadersList = 
                        SalClientFactory.getSalElementGuidesClient().getCaderCodeNameByElmMinistryCode(getManagedConstantsBean().getMinCode());
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
            e.printStackTrace();
        }
    }

    public List<IBasicDTO> getGroupsList() {
        if(groupsList == null && caderCode != null) {
            fillGroups(null);
        }
        
        return groupsList;
    }

    public void setCaderCode(Long caderCode) {
        this.caderCode = caderCode;
    }

    public Long getCaderCode() {
        return caderCode;
    }

    public void fillCaderCode() {
        if (caderCode == null && getCadersList() != null) {
            caderCode = getCurrentCaderCode();
            if (caderCode != null) {
                fillGroups(null);
            }
        }
    }

    private Long getCurrentCaderCode() {
        ISalElementGuidesDTO caderDTO = 
            (ISalElementGuidesDTO)((IAdminAssignmentsDTO)getSelectedPageDTO()).getSalElementGuidesDTO().getParentObject();
        if (caderDTO != null) {
            return Long.parseLong(caderDTO.getCode().getKeys()[0].toString());
        }
        return null;
    }

    public void setAssignTypesCode(Long assignTypesCode) {
        this.assignTypesCode = assignTypesCode;
    }

    public Long getAssignTypesCode() {
        if (assignTypesCode == null && assignTypesDTOList != null) {
            assignTypesCode = getCurrentAssignTypesCode();
        }
        return assignTypesCode;
    }

    private Long getCurrentAssignTypesCode() {
        IAssignTypesDTO assignTypesDTO = 
            ((IAdminAssignmentsDTO)getSelectedPageDTO()).getAssignTypesDTO();
        if (assignTypesDTO != null) {
            return Long.parseLong(assignTypesDTO.getCode().getKeys()[0].toString());
        }
        return null;
    }

    public void setGroupCode(Long groupCode) {
        this.groupCode = groupCode;
    }

    public Long getGroupCode() {
        if (groupCode == null && groupsList != null) {
            groupCode = getCurrentGroupCode();
        }
        return groupCode;
    }

    private Long getCurrentGroupCode() {
        ISalElementGuidesDTO groupDTO = 
            ((IAdminAssignmentsDTO)getSelectedPageDTO()).getSalElementGuidesDTO();
        if (groupDTO != null) {
            return Long.parseLong(groupDTO.getCode().getKeys()[0].toString());
        }
        return null;
    }

    public void setJobsDTOList(List<IJobsDTO> jobsDTOList) {
        this.jobsDTOList = jobsDTOList;
    }

    public List<IJobsDTO> getJobsDTOList() {
        if (jobsDTOList == null) {
            jobsDTOList = new ArrayList<IJobsDTO>();
            //            try {
            //                jobsDTOList = JobClientFactory.getJobsClient().getCodeName();
            //            } catch (DataBaseException e) {
            //                ;
            //            }
        }
        return jobsDTOList;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        if (jobCode == null && assignTypesDTOList != null) {
            jobCode = getCurrentJobCode();
        }
        return jobCode;
    }

    private String getCurrentJobCode() {
        IJobsDTO jobsDTO = 
            ((IAdminAssignmentsDTO)getSelectedPageDTO()).getJobsDTO();
        if (jobsDTO != null) {
            return jobsDTO.getCode().getKey().toString();
        }
        return null;
    }

    public void setAssignReasonCode(Long assignReasonCode) {
        this.assignReasonCode = assignReasonCode;
    }

    public Long getAssignReasonCode() {
        if (assignReasonCode == null && assignReasonsDTOList != null) {
            assignReasonCode = getCurrentAssignReasonCode();
        }
        return assignReasonCode;
    }

    private Long getCurrentAssignReasonCode() {
        IAssignReasonsDTO assignReasonsDTO = 
            ((IAdminAssignmentsDTO)getSelectedPageDTO()).getAssignReasonsDTO();
        if (assignReasonsDTO != null) {
            return Long.parseLong(assignReasonsDTO.getCode().getKeys()[0].toString());
        }
        return null;
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

    public void setWorkMinistriesList(List<IBasicDTO> workMinistriesList) {
        this.workMinistriesList = workMinistriesList;
    }

    public List<IBasicDTO> getWorkMinistriesList() {
        if (workMinistriesList == null) {
            try { //OrgClientFactory.getWorkCentersClient().getAllActiveAndPendingWorkcenters(getManagedConstantsBean().getMinCode());
                    workMinistriesList = 
                        OrgClientFactory.getWorkCentersClient().getAllByMinistry(OrgEntityKeyFactory.createMinistriesEntityKey(getManagedConstantsBean().getMinCode()));
            } catch (Exception e) {
                e.printStackTrace();
                workMinistriesList = new ArrayList<IBasicDTO>();
            }
        }
        return workMinistriesList;
    }

    public void showWorkMinistriesListOfValuesDiv() {
        //        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(getWorkMinistriesList());
        getLovBaseBean().setReturnMethodName(BEAN_NAME + 
                                             ".returnWorkMinistriesLovDiv");
        getLovBaseBean().setSearchMethod(BEAN_NAME + 
                                         ".searchWorkMinistriesLovDiv");
        getLovBaseBean().setCancelSearchMethod(BEAN_NAME + 
                                               ".cancelSearchWorkMinistriesLovDiv");
        getLovBaseBean().setRenderedDropDown("work_Ministries,jobPanelId");
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

    public void changeWorkCenterData(ActionEvent aE) {
        aE = null;
        jobName = null;
        jobCode = null;
        jobDescription = null;
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

            //             if (getJobsDTOList().size() == 0) {
            //                 workCenterHasJobs = false;
            //                 try {
            //                     setJobsDTOList(JobClientFactory.getJobsClient().getCodeName());
            //                 } catch (Exception e) {
            //                     e.printStackTrace();
            //                 }
            //             }

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
                obtainJobDescription(JobEntityKeyFactory.createJobWorkCentersEntityKey(jobCode, 
                                                                                       getManagedConstantsBean().getMinCode(), 
                                                                                       workCenterCode));
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
        if (jobCode != null)
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
                if (searchType.toString().equals("0")) { //by Ashraf Gaber GN Issue
                    getLovBaseBean().setMyTableData(salElementGuidesClient.searchByCaderCodeAndMinCode(getManagedConstantsBean().getMinCode(), 
                                                                                                       Long.valueOf(searchQuery.toString())));
                    //getLovBaseBean().setMyTableData(salElementGuidesClient.searchPayrollByCode(Long.valueOf(searchQuery.toString())));
                } else if (searchType.toString().equals("1")) {
                    getLovBaseBean().setMyTableData(salElementGuidesClient.searchByCaderNameAndMinCode(getManagedConstantsBean().getMinCode(), 
                                                                                                       searchQuery.toString()));
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
        if (workMinistrieKey == null && workMinistriesList != null) {
            workMinistrieKey = getCurrentWorkMinistryKey();
        }
        return workMinistrieKey;
    }

    protected String getCurrentWorkMinistryKey() {
        IWorkCentersDTO workCentersDTO = 
            ((IAdminAssignmentsDTO)getSelectedPageDTO()).getWorkCentersDTO();
        if (workCentersDTO != null) {
            return workCentersDTO.getCode().getKey().toString();
        }
        return null;
    }

    private void fillPageDTOWithData() {
        IAdminAssignmentsDTO dto = (IAdminAssignmentsDTO)getSelectedPageDTO();
        if (assignReasonsDTOList != null && assignReasonCode != null && 
            !assignReasonCode.equals(getCurrentAssignReasonCode())) {
            dto.setAssignReasonsDTO((IAssignReasonsDTO)getSelectedComboDTO(assignReasonsDTOList, 
                                                                           assignReasonCode.toString()));
        }
        if (assignTypesDTOList != null && assignTypesCode != null && 
            !assignTypesCode.equals(getCurrentAssignTypesCode())) {
            dto.setAssignTypesDTO((IAssignTypesDTO)getSelectedComboDTO(assignTypesDTOList, 
                                                                       assignTypesCode.toString()));
        }
        if (groupsList != null && groupCode != null && 
            !groupCode.equals(getCurrentGroupCode())) {
            dto.setSalElementGuidesDTO((ISalElementGuidesDTO)getSelectedComboDTO(groupsList, 
                                                                                 groupCode.toString()));
        }
        if (workMinistriesList != null && workMinistrieKey != null && 
            workMinistrieKey.length() != 0 && 
            !workMinistrieKey.equals(getCurrentWorkMinistryKey())) {
            dto.setWorkCentersDTO((IWorkCentersDTO)getSelectedComboDTO(workMinistriesList, 
                                                                       workMinistrieKey));
        }
        if (jobsDTOList != null && jobCode != null && jobCode.length() != 0 && 
            !jobCode.equals(getCurrentJobCode())) {
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

    public String editAdminAssign() throws DataBaseException, 
                                           ItemNotFoundException, 
                                           SharedApplicationException {
        fillPageDTOWithData();
        AdminAssignListBean adminAssignListBean = 
            AdminAssignListBean.getInstance();
        SharedUtilBean shared_util = getSharedUtil();
        try {
            super.getClient().update(super.getSelectedPageDTO());
            adminAssignListBean.cancelSearch();
            if (adminAssignListBean.isUsingPaging()) {
                adminAssignListBean.generatePagingRequestDTO((String)getSelectedPageDTO().getCode().getKey());
            } else {
                adminAssignListBean.getPageIndex((String)getSelectedPageDTO().getCode().getKey());
            }
            if (adminAssignListBean.getHighlightedDTOS() != null) {
                adminAssignListBean.getHighlightedDTOS().add(this.getSelectedPageDTO());
            }
            adminAssignListBean.setShowEdit(false);
            shared_util.setSuccessMsgValue("SuccesUpdated");
            adminAssignListBean.setSelectedDTOS(new ArrayList<IBasicDTO>());
            return adminAssignListBean.getNavigationKey();
        } catch (DataBaseException e) {
            shared_util.setErrMsgValue("FailureInUpdate");
        } catch (ItemNotFoundException item) {
            shared_util.setErrMsgValue("itemALreadyDeleted");
        } catch (NotMatchedOnHireTypeException e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.csc.hr.emp.presentation.resources.emp", 
                                            "NotMatchedOnHireTypeException_adminassign");
        } catch (Exception item) {
            shared_util.setErrMsgValue("FailureInUpdate");
        }
        return null;
    }

    public void changeData(ActionEvent aE) {
        aE = null;
    }

    public Long getAssignTypeCodeWithPrivilege() {
        return IEMPConstant.ADMIN_ASSIGNMENT_WITH_PRIVILEGE_TYPE;
    }

    public Long getAssignTypeCodeAdminAssimentType() {
        return IEMPConstant.ADMIN_ASSIGNMENT_TYPE;
    }

    private String getMainJobCode() {
        IBasicDTO jobsDTO = 
            ((IAdminAssignmentsDTO)getSelectedPageDTO()).getEmployeesDTO().getTechJobsDTO();
        ;
        if (jobsDTO == null) {
            jobsDTO = 
                    ((IAdminAssignmentsDTO)getSelectedPageDTO()).getEmployeesDTO().getJobDTO();
        }
        if (jobsDTO != null) {
            return jobsDTO.getCode().getKey().toString();
        }
        return null;
    }

    private String getMainWorkMinistryKey() {
        IBasicDTO workCentersDTO = 
            ((IAdminAssignmentsDTO)getSelectedPageDTO()).getEmployeesDTO().getWorkCenterDTO();
        if (workCentersDTO != null) {
            return workCentersDTO.getCode().getKey().toString();
        }
        return null;
    }

    private ISalElementGuidesDTO getMainGroupDTO() {
        ISalElementGuidesDTO groupDTO = null;
        List<ISalEmpSalaryElementsDTO> salEmpSalaryElementsDTOList = 
            ((IAdminAssignmentsDTO)getSelectedPageDTO()).getEmployeesDTO().getSalEmpSalaryElementsDTOList();
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

    public boolean isAdminAssigmentTypeMustChangeData() {
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
