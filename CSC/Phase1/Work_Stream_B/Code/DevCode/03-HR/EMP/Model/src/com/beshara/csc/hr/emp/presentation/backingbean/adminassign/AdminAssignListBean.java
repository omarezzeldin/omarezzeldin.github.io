package com.beshara.csc.hr.emp.presentation.backingbean.adminassign;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IAdminAssignmentsClient;
import com.beshara.csc.hr.emp.business.dto.AdminAssignmentsDTO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IAdminAssignmentsDTO;
import com.beshara.csc.hr.emp.business.dto.IAssignmentsSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntityKey;
import com.beshara.csc.hr.emp.presentation.backingbean.governmentalemployeedatarevision.GovEmpMaintainBean;
import com.beshara.csc.integration.presentation.backingbean.reg.DecisionJoinBean;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.details.DecisionEmployeesMaintain;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.details.DecisionMainDataMaintain;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.emp.NoDecisionFoundException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class AdminAssignListBean extends LookUpBaseBean {
    private static final String BEAN_NAME = "adminAssignListBean";
    private static final String NAVIGATION_KEY = "adminassignlist";
    private static final String METHOD_NAME_RESTORE = "restorePage";
    private IAssignmentsSearchDTO searchDTO = 
        EmpDTOFactory.createAssignmentsSearchDTO();
    private List<IBasicDTO> assignReasonsDTOList;
    private DecisionJoinBean decisionIntegration = new DecisionJoinBean();

    public AdminAssignListBean() {

        setPageDTO(EmpDTOFactory.createAdminAssignmentsDTO());
        setSelectedPageDTO(EmpDTOFactory.createAdminAssignmentsDTO());
        setClient(EmpClientFactory.getAdminAssignmentsClient());
        setSaveSortingState(true);
        setUsingPaging(true);
        decisionIntegration.okMethodName = 
                "adminAssignListBean.saveSelectedDecision";
        setMasterDetailDiv("divREGIntegrate");
    }

    public static AdminAssignListBean getInstance() {
        return (AdminAssignListBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowMasterDetail(true);
        return app;
    }

    public void executeAssignmentDecision() {

        try {
            EmpClientFactory.getAdminAssignmentsClient().executeAdminAssignments(getSelectedDTOS().get(0));
            getAll();
            getSharedUtil().handleSuccMsg("com.beshara.csc.hr.emp.presentation.resources.emp", 
                                          "execute_adminAssignment_decision_success");
        } catch (NoDecisionFoundException e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.csc.hr.emp.presentation.resources.emp", 
                                            "NoDecisionFoundException");
        } catch (Exception e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.csc.hr.emp.presentation.resources.emp", 
                                            "execute_adminAssignment_decision_fail");
        }
        // setSelectedRadio("");
        try {
            cancelSearch();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        //        if (getSelectedDTOS() != null)
        //            getSelectedDTOS().clear();
    }

    public void saveSelectedDecision() {
        System.out.println("saveSelectedDecision");
        if (decisionIntegration.decisionsDTO != null && 
            decisionIntegration.decisionsDTO.getCode() != null && 
            ((IAdminAssignmentsDTO)getSelectedDTOS().get(0)).getEmployeesDTO() != 
            null && 
            ((IAdminAssignmentsDTO)getSelectedDTOS().get(0)).getEmployeesDTO().getCode() != 
            null) {
            SharedUtilBean shared_util = getSharedUtil();
            try {
                IAdminAssignmentsDTO adminAssignmentsDTO = 
                    (IAdminAssignmentsDTO)EmpClientFactory.getAdminAssignmentsClient().getById(getSelectedDTOS().get(0).getCode());
                List<IBasicDTO> empDecisionList = new ArrayList<IBasicDTO>();
                IEmpDecisionsDTO empIDecisionsDTO = 
                    RegDTOFactory.createEmpDecisionsDTO();
                empIDecisionsDTO.setTableName(ISystemConstant.TABLE_HR_EMP_ADMIN_ASSIGNMENTS);
                empIDecisionsDTO.setTabrecSerialRef(EmpClientFactory.getAdminAssignmentsClient().getTableRecordSerial(adminAssignmentsDTO.getCode()));
                empIDecisionsDTO.setEmployeesDTO(((IAdminAssignmentsDTO)getSelectedDTOS().get(0)).getEmployeesDTO());
                empIDecisionsDTO.setDecisionsDTO(decisionIntegration.decisionsDTO);
                empDecisionList.add(empIDecisionsDTO);

                IResultDTO resultDTO;

                resultDTO = 
                        ((RegClientFactory.getEmpDecisionsClient().joinToDecision(empDecisionList)).get(0));

                if (resultDTO != null && 
                    resultDTO.getStatus().equals(ISystemConstant.ITEM_ADDED)) {
                    shared_util.handleSuccMsg("SucessInJoinDecision");
                    getAll();
                    setHighlightedDTOS(new ArrayList());
                    getHighlightedDTOS().add(adminAssignmentsDTO);
                } else if (resultDTO != null && 
                           resultDTO.getStatus().equals(ISystemConstant.ITEM_ALRAEDY_EXIST)) {
                    shared_util.setErrMsgValue(shared_util.messageLocator("com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                                                          "EntityExistsException_Join_Decision"));
                } else {
                    shared_util.setErrMsgValue(shared_util.messageLocator("com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                                                          "FailureInJoinDecision"));
                }
            }

            catch (DataBaseException e) {
                e.printStackTrace();
                decisionIntegration.decisionsDTO = null;
                shared_util.handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInJoinDecision");
            } catch (Exception e) {
                e.printStackTrace();
                decisionIntegration.decisionsDTO = null;
                shared_util.handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInJoinDecision");
            }
            //            setSelectedDTOS(new ArrayList<IBasicDTO>());
            //            setSelectedRadio("");
            setMyTableData(null);
            decisionIntegration.setSearchModeDesDiv(false);
            decisionIntegration.setRegulationSearchDTO(RegDTOFactory.createRegulationSearchDTO());
        }
    }

    public String viewEmpDetails() {
        try {
            IEmployeesDTO emp = 
                ((IAdminAssignmentsDTO)getSelectedDTOS().get(0)).getEmployeesDTO();
            getIntegrationBean().reInitializeBean();
            getIntegrationBean().setBeanNameTo(GovEmpMaintainBean.BEAN_NAME);
            getIntegrationBean().setActionTo(GovEmpMaintainBean.METHOD_NAME_VIEW);
            getIntegrationBean().setNavgationCaseFrom(NAVIGATION_KEY);
            getIntegrationBean().getHmBaseBeanFrom().put(BEAN_NAME, 
                                                         evaluateValueBinding(BEAN_NAME));
            getIntegrationBean().setBeanNameFrom(BEAN_NAME);
            getIntegrationBean().setActionFrom(METHOD_NAME_RESTORE);
            getIntegrationBean().getHmObjects().put(GovEmpMaintainBean.MAP_KEY_CIVIL_ID, 
                                                    emp.getCode().getKey());
            return getIntegrationBean().goTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String restorePage() {
        return NAVIGATION_KEY;
    }

    public void getAll() throws DataBaseException {
        if (getFilterDTO() == null) {
            if (isUsingPaging()) {
                generatePagingRequestDTO(BEAN_NAME, "getAllWithPaging");
            } else {
                setMyTableData(getAllResult());
                if (getSelectedDTOS() != null)
                    getSelectedDTOS().clear();
                if (getHighlightedDTOS() != null)
                    getHighlightedDTOS().clear();
            }
        }
        this.reinitializeSort();
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {
        return new PagingResponseDTO(getAllResult());
    }

    private List getAllResult() {
        SharedUtilBean shared_util = getSharedUtil();
        List allList = null;
        try {
            allList = ((IAdminAssignmentsClient)getClient()).getAllToApprove();
        } catch (SharedApplicationException ne) {
            allList = new ArrayList();
            ne.printStackTrace();
        } catch (DataBaseException db) {
            shared_util.handleException(db);
        } catch (Exception e) {
            shared_util.handleException(e);
        }
        return allList;
    }

    public void search() throws DataBaseException, NoResultException {
        System.out.println("Calling search ( ) ...");
        if (getSelectedDTOS() != null)
            getSelectedDTOS().clear();
        if (getHighlightedDTOS() != null)
            getHighlightedDTOS().clear();
        this.setSearchMode(true);
        searchDTO.setAssstatusCode(IEMPConstant.ASSIGNMENT_UNDER_INVESTIGATE);
        if (isUsingPaging()) {
            generatePagingRequestDTO(BEAN_NAME, "searchWithPaging", searchDTO);
            resetPageIndex();
        } else {
            setMyTableData(getSearchResult());
            this.repositionPage(0);
        }
        setSelectedRadio("");
    }

    public PagingResponseDTO searchWithPaging(PagingRequestDTO request) throws DataBaseException {
        searchDTO = (IAssignmentsSearchDTO)request.getSearchDTO();
        return new PagingResponseDTO(getSearchResult());
    }

    private List getSearchResult() throws DataBaseException {
        List searchResult = null;
        try {
            searchDTO.setAssstatusCode(IEMPConstant.ASSIGNMENT_UNDER_INVESTIGATE);
            searchResult = getClient().search(searchDTO);
        } catch (ItemNotFoundException e) { //this means no search results found 
            searchResult = new ArrayList();
        } catch (NoResultException ne) { //this means no search results found 
            searchResult = new ArrayList();
        } catch (Exception db) {
            ErrorDisplay errorDisplay = 
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("# { error_dissplay } ").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);
            throw new DataBaseException(db.getMessage());
        }
        return searchResult;
    }

    public static String getNavigationKey() {
        return NAVIGATION_KEY;
    }

    public String goToAdd() {

        return AdminAssignAddBean.getNavigationKey();
    }

    public String goToEdit() {
        AdminAssignEditBean adminAssignEditBean = 
            AdminAssignEditBean.getInstance();
        Long civilId = null;
        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            IAdminAssignmentsDTO adminAssignmentsDTO = 
                (IAdminAssignmentsDTO)getSelectedDTOS().get(0);
            IEmployeesDTO employeesDTO = null;
            try {
                civilId = 
                        Long.parseLong(adminAssignmentsDTO.getEmployeesDTO().getCode().getKeys()[0].toString());
                employeesDTO = 
                        (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getById(EmpEntityKeyFactory.createEmployeesEntityKey(civilId));

            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
            adminAssignmentsDTO.setEmployeesDTO(employeesDTO);
            adminAssignEditBean.setSelectedPageDTO(adminAssignmentsDTO);
            if (civilId != null)
                adminAssignEditBean.populateSalariesData(civilId);

            adminAssignEditBean.fillCaderCode();
            try {
                if (adminAssignmentsDTO.getJobsDTO() != null) {
                    adminAssignEditBean.setJobName(adminAssignmentsDTO.getJobsDTO().getName());
                    String workMinistrieKey = 
                        adminAssignEditBean.getCurrentWorkMinistryKey();
                    String[] keys = workMinistrieKey.split("[*]");
                    String workCenterCode = keys[1];
                    adminAssignEditBean.obtainJobDescription(JobEntityKeyFactory.createJobWorkCentersEntityKey(adminAssignmentsDTO.getJobsDTO().getCode().getKey(), 
                                                                                                               getManagedConstantsBean().getMinCode(), 
                                                                                                               workCenterCode));

                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return adminAssignEditBean.getNavigationKey();
    }

    public void preSearch() {
        if (!isSearchMode()) {
            setSearchDTO(EmpDTOFactory.createAssignmentsSearchDTO());
        }
    }

    public void setSearchDTO(IAssignmentsSearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public IAssignmentsSearchDTO getSearchDTO() {
        return searchDTO;
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

    /** 
     * Purpose:This Method which redirect the usecase to Decision Module to make new decision * Creation/Modification History : * 1.1 - Developer Name:Amr Abdo * 1.2 - Date: 05/03/2009 * 1.3 - Creation/Modification:Creation * 1.4- Description: */
    public String gotoDecisoin() {
        IEmployeesDTO employeesDTO = 
            ((IAdminAssignmentsDTO)getSelectedDTOS().get(0)).getEmployeesDTO();
        if (employeesDTO != null) {
            try {
                getIntegrationBean().reInitializeBean();
                getIntegrationBean().setNavgationCaseFrom(NAVIGATION_KEY);
                getIntegrationBean().setBeanNameTo("decisionListBean");
                getIntegrationBean().setActionTo("goAdd");
                //getIntegrationBean ( ) .getHmBaseBeanFrom ( ) .put ( BEAN_NAME , evaluateValueBinding ( BEAN_NAME ) ) ; 
                getIntegrationBean().getHmBaseBeanFrom().put("adminAssignListBean", 
                                                             evaluateValueBinding("adminAssignListBean"));
                getIntegrationBean().getSelectedDTOFrom().add(employeesDTO);
                getIntegrationBean().getHmObjects().put("getSelectedDTOS", 
                                                        getSelectedDTOS().get(0));
                getIntegrationBean().setModuleFrom("emp");
                getIntegrationBean().setBeanNameFrom(BEAN_NAME);
                getIntegrationBean().setActionFrom("addDecision");
                getIntegrationBean().setInitializeMethod(BEAN_NAME + 
                                                         ".initializeDecisionDTO");
                return getIntegrationBean().goTO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /** 
     * Purpose:to make combo boxes with specific default @ decision page 
     * Creation/Modification History : * 1.1 - Developer Name: Amr Abdo * 1.2 - Date: 05-03-2009 * 1.3 - Creation/Modification:Creation * 1.4- Description: */
    public void initializeDecisionDTO() {
        DecisionMaintainBean decisionMaintain = 
            (DecisionMaintainBean)evaluateValueBinding("decisionMaintainBean");
        if (decisionMaintain.getPageDTO() != null) {
            DecisionMainDataMaintain decisionMainData = 
                (DecisionMainDataMaintain)evaluateValueBinding("decisionMainDataMaintainBean");
            DecisionEmployeesMaintain decisionEmployeesMaintain = 
                (DecisionEmployeesMaintain)evaluateValueBinding("decisionEmployeesMaintainBean");
            decisionEmployeesMaintain.setShowBarMainData(false);
            decisionMainData.setShowLovDivFlag(true);
        }
    }

    /** 
     * Purpose:It is Action method of Finish Button in addDecision IUIsIeICIaIsIeI.I * This Method executes request by call "IAdminAssignmentsClient.addDecision" which take selected request * and maked decision Then return to the original IuIsIeIcIaIsIeI.I * Creation/Modification History : * 1.1 - Developer Name:Amr Abdo * 1.2 - Date: 05/03/2009 * 1.3 - Creation/Modification:Creation * 1.4- Description: */
    public String addDecision() {
        IAdminAssignmentsDTO adminAssignmentsDTO = null;
        try {
            adminAssignmentsDTO = 
                    (IAdminAssignmentsDTO)getIntegrationBean().getHmObjects().get("getSelectedDTOS");
            if (adminAssignmentsDTO != null) {
                ((IAdminAssignmentsClient)getClient()).addDecision(adminAssignmentsDTO, 
                                                                   (IDecisionsDTO)getIntegrationBean().getSelectedDTOTo().get(0));
                getAll();
                setHighlightedDTOS(new ArrayList());
                getHighlightedDTOS().add(adminAssignmentsDTO);
                getSharedUtil().handleSuccMsg("SucessInAddDecision");
            }
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInAddDecision");
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInAddDecision");
        } catch (Exception e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInAddDecision");
        }
        //setSelectedRadio("");
        try {
            cancelSearch();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        if (getSelectedDTOS() == null || getSelectedDTOS().size() == 0) {
            setSelectedDTOS(new ArrayList<IBasicDTO>());
        } else {
            getSelectedDTOS().clear();
        }
        getSelectedDTOS().add(adminAssignmentsDTO);


        return NAVIGATION_KEY;
    }

    public void setDecisionIntegration(DecisionJoinBean decisionIntegration) {
        this.decisionIntegration = decisionIntegration;
    }

    public DecisionJoinBean getDecisionIntegration() {
        return decisionIntegration;
    }

    public void openDecJoinDiv() {
        getDecisionIntegration().cancelSearchDecisionIntegrate(null);
        IEmployeesDTO employeesDTO = ((AdminAssignmentsDTO)getSelectedDTOS().get(0)).getEmployeesDTO();
        getDecisionIntegration().getEmpCivilIdList().clear();
        getDecisionIntegration().getEmpCivilIdList().add(((EmployeesEntityKey)employeesDTO.getCode()).getCivilId());
    }
}
