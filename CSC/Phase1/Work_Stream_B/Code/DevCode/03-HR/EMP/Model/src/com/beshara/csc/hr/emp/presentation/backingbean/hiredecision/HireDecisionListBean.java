package com.beshara.csc.hr.emp.presentation.backingbean.hiredecision;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.common.shared.SharedUtils;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.grs.integration.presentation.backingbean.checkconditionlines.ConditionLinesdetailsHelperBean;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.client.IEmpCndSalaryElementsClient;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStagesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.empexecute.EmpExecuteBean;
import com.beshara.csc.hr.emp.presentation.backingbean.empexecute.EmpExecuteListBean;
import com.beshara.csc.hr.emp.presentation.backingbean.shared.EmpJoinDecHelperBean;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalExtraDataTypesDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.CountriesEntityKey;
import com.beshara.csc.integration.presentation.backingbean.reg.DecisionJoinBean;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegPersonDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.ITemplatesDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.sharedUtil.IConstants;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.joindec.JoinDecHelperBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.details.DecisionEmployeesMaintain;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.details.DecisionMainDataMaintain;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.emp.EmpOperationDoneFileOperationFailException;
import com.beshara.csc.sharedutils.business.exception.fil.PersonHasAppointmentFileException;
import com.beshara.csc.sharedutils.business.exception.reg.CanNotAddEmpDecisionsException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.constants.IRegConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.rmi.RemoteException;

import java.sql.Date;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;


public class HireDecisionListBean extends LookUpBaseBean {

    private IEmpEmployeeSearchDTO empEmployeesSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
    private Long hireType = 0L;
    private String hireTypeName = "";
    private String itemSelection = String.valueOf(ISystemConstant.VIRTUAL_VALUE.longValue());
    private List<IBasicDTO> hireTypesList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> hireStagesList = new ArrayList<IBasicDTO>();

    public static final String SELECTED_DTO_BINDING = "hireDecisionListBean.selectedDTOS";
    public static final String BEAN_NAME = "hireDecisionListBean";
    private List filteredList = new ArrayList();
    private List<IBasicDTO> resList = new ArrayList();
    //Decision Relate
    // private SharedDecisionRelate decisionIntegration = new SharedDecisionRelate ();
    private DecisionJoinBean decisionIntegration = new DecisionJoinBean();
    private List<IResultDTO> decIntegratedResult = new ArrayList<IResultDTO>();

    private IBasicDTO decisionBasicDTO; // will be passed to work execute bean

    private boolean addNewDecision;

    private List<IBasicDTO> firstLevelHireTypesList;
    private String virtualStringValue = ISystemConstant.VIRTUAL_VALUE.toString();
    List allList = new ArrayList();
    private Long loginedMinistrycode = null;
    boolean validToLink = false;
    List<IBasicDTO> selectedDTOs = null;
    private JoinDecHelperBean jdHelper = EmpJoinDecHelperBean.getInstance(BEAN_NAME, SELECTED_DTO_BINDING);
    private static final String EMP_RESOURCES = "com.beshara.csc.hr.emp.presentation.resources.emp";
    private String errorMessage = "";
    private ConditionLinesdetailsHelperBean cdIntegrationBean = new ConditionLinesdetailsHelperBean();

    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    private boolean enableViewCondDtls = false;
    String go_Employment_Excecute_Suggest = "employmentExcecuteSuggest";
   private EmpExecuteListBean empExecuteListBean=EmpExecuteListBean.getInstance();

    /* start CSC-23106 */
    private String reportUrl = "rep=2121&_paramsP_CIVIL=&_paramsP_REQ_NO=&_paramsP_COPY_TO=&_paramsP_NOTES=&_paramsP_DCMAKER=";
    private List reportTemplatesList;
    Map reportsTemplate = new HashMap();
    public static final Long DEC_TEMPLATE_CODE_FIRST_COPY = 7L;
    private String templateName = "";
    /* end CSC-23106 */

    public HireDecisionListBean() {
        IEmployeesClient client = EmpClientFactory.getEmployeesClient();
        //   List<IBasicDTO> dto;
        //
        //        try {
        //            EmpSearchRecodDesc searchdto=new EmpSearchRecodDesc();
        //            searchdto.setRealCivilID(280040701142L);
        //            //searchdto.setRealCivilID(280040701142L);
        //            dto = client.getEmpByEmpSearch(searchdto);
        //           System.out.print(dto.size());
        //        } catch (DataBaseException e) {
        //        } catch (SharedApplicationException e) {
        //        }
        try {
            System.out.print(client.getStatusForHire());
        } catch (RemoteException e) {
        }
        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        super.setSelectedPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setClient(EmpClientFactory.getEmpCandidatesClient());
        //Decision Relate
        decisionIntegration.okMethodName = "hireDecisionListBean.saveSelectedDecision";
        decisionIntegration.backMethodName = "hireDecisionListBean.backFromDecision";
        setMasterDetailDiv("divREGIntegrate");

        IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getPageDTO();
        setSaveSortingState(true);
        setUsingPaging(true);
        setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));
        setSaveSortingState(false);


        //        setPageDTO(empCandidatesDTO);


        /*   IEmployeesDTO empDTO = (IEmployeesDTO)getPageDTO();
                if(empDTO.getHireTypeKey().equals("") || empDTO.getHireTypeKey()==null){
                    ((IEmployeesDTO)getPageDTO()).setHireTypeKey("-100");
                }  */

    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowSearch(true);

        app.setShowMasterDetail(true);
        app.setShowContent1(true);
        app.setShowCustomDiv1(true);
        app.setShowCustomDiv2(true);
        return app;
    }

    public static HireDecisionListBean getInstance() {
        return (HireDecisionListBean)JSFHelper.getValue(BEAN_NAME);
    }

    /**
     * Purpose: get all employees that are waiting for hiring decision
     * Creation/Modification History : creation
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  14-july-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void getAll() throws DataBaseException {

        if (isUsingPaging()) {

            generatePagingRequestDTO("hireDecisionListBean", "getAllWithPaging");

        } else {

            setMyTableData(getAllList());

            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();

        }

    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {
        return new PagingResponseDTO(getAllList());
    }

    public void cancelSearch() throws DataBaseException {

        setSearchMode(false);
        setEmpEmployeesSearchDTO(EmpDTOFactory.createEmpEmployeeSearchDTO());


        if (isUsingPaging()) {
            getPagingBean().cancelSearch();
        } else {
            getAll();
        }

        setCheckAllFlag(false);
        setSelectedRadio("");
        String hireTypeKey = ((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey();
        IEmpCandidatesDTO pageDTO = EmpDTOFactory.createEmpCandidatesDTO();
        pageDTO.setHireTypeKey(hireTypeKey);
        setPageDTO(pageDTO);
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        //        IEmpCandidatesDTO dto = (IEmpCandidatesDTO)getPageDTO();
        //        setHireType(((IHireTypesEntityKey)dto.getHireTypesDTO().getCode()).getHirtypeCode());

    }

    /**
     * Purpose: search about employees that are waiting for hiring decision depending on hire type
     * Creation/Modification History : creation
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  13-july-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */

    public void clearData() {
        if (isSearchMode() == false) {
            setEmpEmployeesSearchDTO(EmpDTOFactory.createEmpEmployeeSearchDTO());
        }
    }

    public static String formatSearchString(String inputString) {
        if (inputString.contains("%"))
            return inputString;
        return "%" + inputString.trim() + "%";
    }

    public void searchHireDecision() throws DataBaseException, NoResultException, SharedApplicationException {

        System.out.println("Calling search()...");

        List<Long> hireStage = new ArrayList();
        hireStage.add(2L);
        hireStage.add(5L);
        empEmployeesSearchDTO.setEmpHireStagesList(hireStage);

        if (getHireType() > 0L) {
            empEmployeesSearchDTO.setEmpHireTypes(Long.valueOf(getHireType()));
        }
        empEmployeesSearchDTO.setMinistryCode(getLoginedMinistrycode());
        if (getSelectedDTOS() != null)
            getSelectedDTOS().clear();
        if (getHighlightedDTOS() != null)
            getHighlightedDTOS().clear();
        this.setSearchMode(true);


        if (empEmployeesSearchDTO != null) {
            if (empEmployeesSearchDTO.getEmpName() != null && !empEmployeesSearchDTO.getEmpName().equals("")) {
                empEmployeesSearchDTO.setEmpName(formatSearchString(empEmployeesSearchDTO.getEmpName()));
            }

            setSearchMode(true);
            if (isUsingPaging()) {
                generatePagingRequestDTO("hireDecisionListBean", "searchHireDecisionWithPaging");
                getPagingRequestDTO().setSearchDTO(empEmployeesSearchDTO);

            } else {

                setMyTableData(getSearchResult(empEmployeesSearchDTO));

            }

            setSelectedRadio("");


        }

    }

    public PagingResponseDTO searchHireDecisionWithPaging(PagingRequestDTO request) {

        //String hireType = (String)request.getParams()[0];
        empEmployeesSearchDTO = (IEmpEmployeeSearchDTO)request.getSearchDTO();


        return new PagingResponseDTO(getSearchResult(empEmployeesSearchDTO));

    }

    private List getSearchResult(IBasicDTO empEmployeesSearchDTO) {


        // Long hireTypeKey= Long.valueOf(((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey());

        List resultList = null;

        try {

            // ((IEmpCandidatesDTO)empEmployeesSearchDTO).setHirtypeCode(hireTypeKey);
            resultList =
                    ((IEmpCandidatesClient)getClient()).getFilterEmpWaitingForHireDecWithDiffStage(empEmployeesSearchDTO);


        } catch (DataBaseException e) {
            resultList = new ArrayList();
        } catch (SharedApplicationException e) {
            resultList = new ArrayList();
        }
        return resultList;
    }

    public String empAddDecisoin() {


        try {

            EmpClientFactory.getEmployeesClient().addDecision(getIntegrationBean().getSelectedDTOFrom(),
                                                              getIntegrationBean().getSelectedDTOTo().get(0));
            //By Ashraf Gaber HR-668 5-1-09
            decisionBasicDTO = getSelectedDTOS().get(0);
            // added by nora to solve hr-799
            try {
                decisionBasicDTO = EmpClientFactory.getEmployeesClient().getById(getSelectedDTOS().get(0).getCode());
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
            addNewDecision = true;
            setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('CancelButtonEdit').focus();");
            //getSharedUtil().handleSuccMsg("SuccessAdds");

            //TODO check for setMyTableData(null);
            if (isUsingPaging()) {
                // getPagingBean().cancelSearch();
                generatePagingRequestDTO("hireDecisionListBean", "getAllWithPaging");
            } else {
                getAll();
            }
        } catch (CanNotAddEmpDecisionsException e) {
            getSharedUtil().handleException(e);
        } catch (PersonHasAppointmentFileException e) {
            getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                            "PersonHasAppointmentFileException");
        } catch (EmpOperationDoneFileOperationFailException e) {
            getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                            "EmpOperationDoneFileOperationFailException");
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e);
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e);
        }
        return "hiredicision";
    }

    public String addDecisoin() {
        getIntegrationBean().reInitializeBean();
        getIntegrationBean().setNavgationCaseFrom("hiredicision");
        getIntegrationBean().setBeanNameTo("decisionListBean");
        getIntegrationBean().setActionTo("goAdd");
        getIntegrationBean().getHmBaseBeanFrom().put("hireDecisionListBean",
                                                     evaluateValueBinding("hireDecisionListBean"));
        getIntegrationBean().getSelectedDTOFrom().addAll(getSelectedDTOS());
        getIntegrationBean().setModuleFrom("emp");
        getIntegrationBean().setBeanNameFrom("hireDecisionListBean");
        getIntegrationBean().setActionFrom("empAddDecisoin");
        getIntegrationBean().setInitializeMethod("hireDecisionListBean.initializeDecisionDTO");

        return getIntegrationBean().goTO();
    }

    public String gotoDecisoin() {
        enableViewCondDtls = true;
        reIntializeMsgs();
        getIntegrationBean().reInitializeBean();
        getIntegrationBean().setNavgationCaseFrom("hiredecisionListFromIntegration");
        getIntegrationBean().setBeanNameTo("decisionListBean");
        getIntegrationBean().setActionTo("goAdd");
        //setSearchMode(false);
        getIntegrationBean().getHmBaseBeanFrom().put("hireDecisionListBean",
                                                     evaluateValueBinding("hireDecisionListBean"));
        getIntegrationBean().getHmBaseBeanFrom().put("isEmployee", false);
        getIntegrationBean().getHmBaseBeanFrom().put("tableName", "HR_EMP_CANDIDATES");
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0 && getSelectedDTOS().get(0) != null &&
            ((IEmpCandidatesDTO)getSelectedDTOS().get(0)).getCitizensResidentsDTO() != null) {
            try {
                for (int i = 0; i < getSelectedDTOS().size(); i++) {
                    IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(i);


                    //////////////
                    SharedUtilBean sharedUtil = getSharedUtil();
                    //اذا كان احد المرشحين المختارين حالته "جاري تن�?يذ التعيين" تظهر شاشة وبها المرشحين

                    resList = ((IEmpCandidatesClient)getClient()).getOtherCandByCivils(getSelectedDTOS());
                    if (resList.size() > 0) {
                        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                        setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errCancelAnotherCand"));
                        return null;
                    }
                    // will be un hashed after edit the change
                    resList = ((IEmpCandidatesClient)getClient()).checkDecisionHire(getSelectedDTOS());
                    if (resList.size() > 0) {
                        enableViewCondDtls = false ;
                        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                        setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errJoinDecBefore"));

                        return null;
                    }

                    //تن�?يذ ربط القرار

                    resList = ((IEmpCandidatesClient)getClient()).checkConditions(getSelectedDTOS());
                    // will be un hashed after edit the change
                                        if (resList.size() > 0) {
                                            this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                                            setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errcandConditionFailed"));
                                            return null;
                                        }

                    ////////////////////////
                    /// to Do new Change By M.abdelsabour
                    IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = new KwtCitizensResidentsDTO();
                    kwtCitizensResidentsDTO.setCode(((IEmpCandidatesDTO)getSelectedDTOS().get(i)).getCitizensResidentsDTO().getCode());
                    kwtCitizensResidentsDTO.setTabrecSerial(empCandidatesDTO.getTabrecSerial());
                    getIntegrationBean().getSelectedDTOFrom().add(kwtCitizensResidentsDTO);


                }
                getIntegrationBean().setModuleFrom("emp");
                getIntegrationBean().setBeanNameFrom(BEAN_NAME);
                getIntegrationBean().setInitializeMethod(BEAN_NAME + ".initializeDecisionDTO");
                getIntegrationBean().setActionFrom("addDecision");
                //BUILD DECISION TITLE
                buildDecisionText();

                return "decisionmaindata";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * Purpose:to make combo boxes with specific default @ decision page
     * Creation/Modification History :
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  5-1-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void initializeDecisionDTO() {
        DecisionMaintainBean decisionMaintain = (DecisionMaintainBean)evaluateValueBinding("decisionMaintainBean");

        if (decisionMaintain.getPageDTO() != null) {

            DecisionMainDataMaintain decisionMainData =
                (DecisionMainDataMaintain)evaluateValueBinding("decisionMainDataMaintainBean");
            DecisionEmployeesMaintain decisionEmployeesMaintain =
                (DecisionEmployeesMaintain)evaluateValueBinding("decisionEmployeesMaintainBean");
            decisionEmployeesMaintain.setShowBarMainData(false);
            ((IDecisionsDTO)decisionMaintain.getPageDTO()).setSubjectsDTOKey(IRegConstants.REGULATION_SUBJECT_APPOINTMENT_DECISION.toString()); //TODO
            decisionMainData.setShowLovDivFlag(true);
            try {
                decisionMainData.setTypesDTOKey(getManagedConstantsBean().getDecisionAdministrationType());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ((IDecisionsDTO)decisionMaintain.getPageDTO()).setDecisionText("hamada");
        }
    }

    /**
     * Purpose:to navigate to the work initiation page
     * Creation/Modification History :
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  5-1-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public String goToWorkInitiation() {
        System.out.println("goToWorkInitiation");
        if (decisionBasicDTO != null) {

            IEmployeesDTO dto = (IEmployeesDTO)decisionBasicDTO;
            String civilId = dto.getCode().getKey();
            IPersonQualificationsDTO persontDTO = null;
            try {

                persontDTO =
                        (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getPersonQulificationInfo(Long.valueOf(civilId));
            } catch (Exception e) {
                e.printStackTrace();
            }

            EmpExecuteBean empExecuteBean = EmpExecuteBean.getInstance();

            empExecuteBean.setPageDTO(dto);
            if (persontDTO != null)
                empExecuteBean.setPersonQulDTO(persontDTO);
            return empExecuteBean.getPageNavigationKey();
        }
        return null;
    }
    public boolean validateEmp() {
        SharedUtilBean sharedUtil = getSharedUtil();
        enableViewCondDtls = false;
        try {
            resList = ((IEmpCandidatesClient)getClient()).getOtherCandByCivils(getSelectedDTOS());
            if (resList.size() > 0) {
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errCancelAnotherCand"));

                return true;
            }
            //اذا كان احد المرشحين المختارين حالته "جاري اصدار قرار التعيين" ونوع التعيين للمرشح الزامي ان يتم ربط المرشح بقرار

            resList = ((IEmpCandidatesClient)getClient()).checkApplyHire(getSelectedDTOS());
            if (resList.size() > 0) {
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errJoinDecFirst"));
                return true;
            }
            //تن�?يذ التعيين


            resList = ((IEmpCandidatesClient)getClient()).checkConditions(getSelectedDTOS());
            if (resList.size() > 0) {
                enableViewCondDtls = true;
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errcandConditionFailed"));
                return true;
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
      return false;
    }

    public String goEmpExecute(){
            if (validateEmp()) {
                return "";
            }
        
            HireDecisionAddBean hireDecisionAddBean=HireDecisionAddBean.getInstance();
            hireDecisionAddBean.setSelectedDTOS(getSelectedDTOS());
           return hireDecisionAddBean.goEmpExecute();
        }
    public void setHireType(Long hireType) {
        this.hireType = hireType;
    }

    public Long getHireType() {
        return hireType;
    }

    public void setHireTypesList(List<IBasicDTO> hireTypesList) {
        this.hireTypesList = hireTypesList;
    }

    public List<IBasicDTO> getHireTypesList() {
        if (hireTypesList.size() == 0) {
            SharedUtilBean sharedUtilBean = getSharedUtil();

            List<IBasicDTO> dtos = new ArrayList<IBasicDTO>();
            for (int i = 0; i < 2; i++) {
                dtos.add(i, new BasicDTO());
            }
            // dtos.get(0).setCode(EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(getManagedConstantsBean().getEmpHireTypeExceptedFromCenteralEmployment())));
            //dtos.get(0).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
            //                                                "exceptedFromCenteralEmployment"));
            dtos.get(0).setCode(EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(getManagedConstantsBean().getEmpHireTypeNominationAgain())));
            dtos.get(0).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                              "nominationAgain"));
            //dtos.get(2).setCode(EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(getManagedConstantsBean().getEmpHireTypeMovedToOtherMinisties())));
            //dtos.get(2).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
            //                                                "EMP_HIRE_TYPE_MOVEED_TO_OTHER_MINISTRIES"));
            dtos.get(1).setCode(EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(getManagedConstantsBean().getEmpHireTypeNominationByMinistry())));
            dtos.get(1).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                              "nominationByMinistry"));
            dtos.add(2, new BasicDTO());
            dtos.get(2).setCode(EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(getManagedConstantsBean().getEmpHireTypeFromCenteralEmployment())));
            dtos.get(2).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                              "EMP_HIRE_TYPE_FROM_CENTER_EMPLOYMENT"));
            hireTypesList = dtos;

        }
        return hireTypesList;
    }

    public void setItemSelection(String empty) {
        this.itemSelection = empty;
    }

    public String getItemSelection() {
        return itemSelection;
    }

    public void saveSelectedDecision() {

        if (decisionIntegration.decisionsDTO != null && decisionIntegration.decisionsDTO.getCode() != null &&
            getSelectedDTOS() != null && getSelectedDTOS().size() >= 1 && insureLocked()) {

            try {
                //By Ashraf Gaber HR-676 11-1-09
                //decIntegratedResult =
                List<ResultDTO> rsList =
                    EmpClientFactory.getEmployeesClient().addJoinToDecision(getSelectedDTOS(), decisionIntegration.decisionsDTO);
                decisionBasicDTO = getSelectedDTOS().get(0);
                if (!((ResultDTO)rsList.get(0)).getStatus().equals("NotAdded")) {


                    // added by nora to solve hr-799
                    try {
                        decisionBasicDTO =
                                EmpClientFactory.getEmployeesClient().getById(getSelectedDTOS().get(0).getCode());
                    } catch (SharedApplicationException e) {
                        e.printStackTrace();
                    } catch (DataBaseException e) {
                        e.printStackTrace();
                    }
                    addNewDecision = false;
                    setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('CancelButtonEdit').focus();");

                    if (isUsingPaging()) {
                        //                        generatePagingRequestDTO("hireDecisionListBean", "getAllWithPaging");
                        selectedDTOs = getSelectedDTOS();
                        filterEmpByHireType();
                        setSelectedRadio("");
                        setHighlightedDTOS(new ArrayList<IBasicDTO>(selectedDTOs));
                        selectedDTOs.clear();

                    } else {
                        getAll();
                    }
                } else {
                    getSharedUtil().setErrMsgValue("EntityExistsException_Join_Decision");
                }
            } catch (PersonHasAppointmentFileException e) {
                getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                "PersonHasAppointmentFileException");
            } catch (EmpOperationDoneFileOperationFailException e) {
                getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                "EmpOperationDoneFileOperationFailException");
            } catch (DataBaseException e) {
                e.printStackTrace();
                decisionIntegration.decisionsDTO = null;
                getSharedUtil().setErrMsgValue("FailureInUpdate");
            } catch (Exception e) {
                e.printStackTrace();
                decisionIntegration.decisionsDTO = null;
                getSharedUtil().setErrMsgValue("FailureInUpdate");
            } finally {
                unlock();
            }

            decisionIntegration.setSearchModeDesDiv(false);
            decisionIntegration.setRegulationSearchDTO(RegDTOFactory.createRegulationSearchDTO());
            setMyTableData(new ArrayList());

        }

    }

    /* start CSC-23106 */
    public void openReportAfterExecution(IDecisionsDTO decDTO) {
        try {
            reportTemplatesList =
                    RegClientFactory.getTemplatesClient().getByValidityCodeAndStatusCode(IConstants.DEC_VALIDITY_CODE,
                                                                                         decDTO.getStatusCode());
        } catch (Exception e) {
            reportTemplatesList = new ArrayList();
        }
        loadReportTemplates();
        int template_key = DEC_TEMPLATE_CODE_FIRST_COPY.intValue();
        reportUrl = (String)reportsTemplate.get(template_key);
        templateName = getReportTemplateName(DEC_TEMPLATE_CODE_FIRST_COPY.toString());
        String P_WATER_MARK = "P_WATER_MARK";
        String P_DECNO = "P_DECNO";
        // 19-10-2015
        String P_DECYEAR = "P_DECYEAR";
        String P_DECTYPE = "P_DECTYPE";
        String P_DATE = "P_DATE";
        String P_MAT = "P_MAT";
        String P_PERSONS = "P_PERSONS";
        IDecisionsEntityKey decision_EK = (IDecisionsEntityKey)decDTO.getCode();
        if (reportUrl.indexOf(P_DECYEAR) != -1) {

            reportUrl = reportUrl.replaceFirst("P_DECYEAR" + "=", P_DECYEAR + "=" + decision_EK.getDecyearCode());
        }

        if (reportUrl.indexOf(P_DECTYPE) != -1) {
            reportUrl = reportUrl.replaceFirst("P_DECTYPE" + "=", P_DECTYPE + "=" + decision_EK.getDectypeCode());
        }

        if (reportUrl.indexOf(P_WATER_MARK) != -1) {
            reportUrl = reportUrl.replaceFirst(P_WATER_MARK + "=", P_WATER_MARK + "=" + templateName);
        }
        if (reportUrl.indexOf(P_DECNO) != -1) {
            /* String decNum= ((IDecisionsDTO)getSelectedDTOS().get(0)).getDecisionNumber().toString();
              if(((IDecisionsDTO)getSelectedDTOS().get(0)).getRegStatusDTO().getCode().getKey().equals("1")){
                  decNum= ((IDecisionsDTO)getSelectedDTOS().get(0)).getRegNum();
              }*/
            reportUrl = reportUrl.replaceFirst(P_DECNO + "=", P_DECNO + "=" + decDTO.getRegNum());
        }

        if (reportUrl.indexOf(P_DATE) != -1) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String decDate = sdf.format(decDTO.getDecisionDate());
            reportUrl = reportUrl.replaceFirst("P_DATE" + "=", P_DATE + "=" + decDate);
        }
        if (reportUrl.indexOf(P_MAT) != -1) {
            reportUrl = reportUrl.replaceFirst("P_MAT" + "=", P_MAT + "=1");

        }
        if (reportUrl.indexOf(P_PERSONS) != -1) {
            reportUrl = reportUrl.replaceFirst("P_PERSONS" + "=", P_PERSONS + "=" + getSelectedDTOS().size());

        }
        System.out.println("openReportAfterExecution reportUrl = "+reportUrl);
        String stringcaller = "openReportWindow('reportUrl')";
        setJavaScriptCaller(stringcaller);

    }

    private void loadReportTemplates() {
        reportsTemplate.put(1,
                            "rep=831&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
        reportsTemplate.put(2,
                            "rep=832&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
        //reportsTemplate.put(3, "rep=833&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_REGYEAR=&_paramsP_REGTYPE=&_paramsP_REGNO=&_paramsP_WATER_MARK=");
        //reportsTemplate.put(4, "rep=834&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DECYEAR=&_paramsP_DATE=&_paramsP_REGYEAR=&_paramsP_REGTYPE=&_paramsP_REGNO=&_paramsP_WATER_MARK=");
        reportsTemplate.put(5,
                            "rep=835&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
        reportsTemplate.put(6,
                            "rep=836&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
        reportsTemplate.put(7,
                            "rep=837&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
        //reportsTemplate.put(8, "rep=838&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_REGYEAR=&_paramsP_REGTYPE=&_paramsP_REGNO=&_paramsP_WATER_MARK=");
        reportsTemplate.put(9,
                            "rep=839&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
        reportsTemplate.put(10,
                            "rep=826&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
    }
    /* end CSC-23106 */

    public void backFromDecision() {
        unlock();
    }

    public void setDecIntegratedResult(List<IResultDTO> decIntegratedResult) {
        this.decIntegratedResult = decIntegratedResult;
    }

    public List<IResultDTO> getDecIntegratedResult() {
        return decIntegratedResult;
    }

    public void setDecisionIntegration(DecisionJoinBean decisionIntegration) {
        this.decisionIntegration = decisionIntegration;
    }

    public DecisionJoinBean getDecisionIntegration() {
        return decisionIntegration;
    }

    public void setDecisionBasicDTO(IBasicDTO decisionBasicDTO) {
        this.decisionBasicDTO = decisionBasicDTO;
    }

    public IBasicDTO getDecisionBasicDTO() {
        return decisionBasicDTO;
    }

    public void setEmpEmployeesSearchDTO(IEmpEmployeeSearchDTO empEmployeesSearchDTO) {
        this.empEmployeesSearchDTO = empEmployeesSearchDTO;
    }

    public IEmpEmployeeSearchDTO getEmpEmployeesSearchDTO() {
        return empEmployeesSearchDTO;
    }

    public void setAddNewDecision(boolean addNewDecision) {
        this.addNewDecision = addNewDecision;
    }

    public boolean isAddNewDecision() {
        return addNewDecision;
    }

    public void openSearchDiv() {


        if (!isSearchMode()) {
            setEmpEmployeesSearchDTO(EmpDTOFactory.createEmpEmployeeSearchDTO());
        }

    }

    public void openDecJoinDiv() {
        SharedUtilBean sharedUtil = getSharedUtil();
        //  getDecisionIntegration().cancelSearchDecisionIntegrate(null);
        // getDecisionIntegration().getRegulationSearchDTO().getNumber();
        IEmpCandidatesDTO employeesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        try {


            //اذا كان احد المرشحين المختارين حالته "جاري تن�?يذ التعيين" تظهر شاشة وبها المرشحين

            resList = ((IEmpCandidatesClient)getClient()).getOtherCandByCivils(getSelectedDTOS());
            if (resList.size() > 0) {
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errCancelAnotherCand"));
                return;
            }

            resList = ((IEmpCandidatesClient)getClient()).checkDecisionHire(getSelectedDTOS());
            if (resList.size() > 0) {
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errJoinDecBefore"));

                return;
            }

            //تن�?يذ ربط القرار

            resList = ((IEmpCandidatesClient)getClient()).checkConditions(getSelectedDTOS());
            if (resList.size() > 0) {
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errcandConditionFailed"));
                return;
            }

            ///to do call integration

            //تن�?يذ اصدار القرار
            gotoDecisoin();
            if (((IEmpCandidatesClient)getClient()).applyDecisionForHire(getSelectedDTOS())) {
                ///To do check about GRS conditions
                String msg = sharedUtil.messageLocator(EMP_RESOURCES, "applydechireSuccess");

                sharedUtil.setSuccessMsgValue(msg);
                filterEmpByHireType();
            }


        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }


    }


    public void openJoinDivForExcute() {
        SharedUtilBean sharedUtil = getSharedUtil();
        //  getDecisionIntegration().cancelSearchDecisionIntegrate(null);
        // getDecisionIntegration().getRegulationSearchDTO().getNumber();
        IEmpCandidatesDTO employeesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        try {


            //اذا كان احد المرشحين المختارين حالته "جاري تن�?يذ التعيين" تظهر شاشة وبها المرشحين

            resList = ((IEmpCandidatesClient)getClient()).getOtherCandByCivils(getSelectedDTOS());
            if (resList.size() > 0) {
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errCancelAnotherCand"));
                return;
            }

            resList = ((IEmpCandidatesClient)getClient()).checkDecisionHire(getSelectedDTOS());
            if (resList.size() > 0) {
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errJoinDecBefore"));
                return;
            }

            resList = ((IEmpCandidatesClient)getClient()).checkConditions(getSelectedDTOS());
            if (resList.size() > 0) {
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errcandConditionFailed"));
                return;
            }
            ///to do call integration

            //تن�?يذ ربط القرار
            if (((IEmpCandidatesClient)getClient()).applyDecisionForHire(getSelectedDTOS())) {
                String msg = sharedUtil.messageLocator(EMP_RESOURCES, "applydecJoinhireSuccess");

                sharedUtil.setSuccessMsgValue(msg);
                filterEmpByHireType();
            }


        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }


    }

    public void excuteHire() {
        boolean showCusDivFlag=false;
        enableViewCondDtls = false;
        SharedUtilBean sharedUtil = getSharedUtil();
        //        getDecisionIntegration().cancelSearchDecisionIntegrate(null);
        //        getDecisionIntegration().getRegulationSearchDTO().getNumber();
        //        IEmpCandidatesDTO employeesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        try {

            String msg;

            if (((IEmpCandidatesClient)getClient()).applyHire(getSelectedDTOS())) {
                ///To do check about GRS conditions
                /// open decision integration div\


                //                getDecisionIntegration().getEmpCivilIdList().clear();
                //                getDecisionIntegration().getEmpCivilIdList().add(employeesDTO.getCivilId());
                //                if (lock("emp_hire_decision")) {
                //                    //TODO locking code
                //                    // propagate the locking status to the maintain bean
                //                    // because it will be responsible to unlock the edited item if it was locked
                //                    setLastLockingAction(getLastLockingAction());
                //                }
                //// sheet point 125  By M.abdelsabour ////
                //                msg = sharedUtil.messageLocator(EMP_RESOURCES, "success_execution");
                //                sharedUtil.setSuccessMsgValue(msg);
                if( getSelectedDTOS().size() ==1 ){
                    showCusDivFlag=true;
                    empExecuteListBean.setSelectedDTOS(getSelectedDTOS());
                }
                filterEmpByHireTypeForExecute();
               if(showCusDivFlag)
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv2);");
               else
                backFromDiv();
            } else {
                msg = sharedUtil.messageLocator(EMP_RESOURCES, "fail_execution");
                sharedUtil.setErrMsgValue(msg);

            }

        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }


    }
    
    public void backFromDiv(){
            if (getSelectedDTOS() != null) {
                getSelectedDTOS().clear();

            }
        
        }

    public void filterEmpByHireTypeForExecute() {
        //        getPagingBean().clearDTOS();

        generatePagingRequestDTO("0");
        List<IBasicDTO> empList = new ArrayList<IBasicDTO>();
        IEmpCandidatesDTO dto = (IEmpCandidatesDTO)getPageDTO();
        try {
            if (dto.getHireTypeKey() != null && !dto.getHireTypeKey().equals("-1")) {
                empList =
                        EmpClientFactory.getEmpCandidatesClient().filterByHireTypeWithMin(Long.valueOf(dto.getHireTypeKey()),
                                                                                          getLoginedMinistrycode());
                setHireType(((IHireTypesEntityKey)dto.getHireTypesDTO().getCode()).getHirtypeCode());


            } else {
                empList =
                        EmpClientFactory.getEmpCandidatesClient().filterByAllHireTypesWithMin(getLoginedMinistrycode());

                setHireType(0L);

            }


        } catch (DataBaseException f) {
            f.printStackTrace();
        }
        if (isSearchMode()) {
            try {
                cancelSearch();
                setSearchMode(false);
            } catch (DataBaseException f) {
            }
        }
        setAllList(empList);
        setUpdateMyPagedDataModel(true);
        setSelectedRadio("");

       
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
                IEmpCandidatesDTO dto = (IEmpCandidatesDTO)getPageDTO();

                initDT();
            } catch (DataBaseException e) {
                e.printStackTrace();
                firstLevelHireTypesList = new ArrayList<IBasicDTO>();
            }
        }
        return firstLevelHireTypesList;
    }

    /**
     * Hany Omar 16/2/2014
     * @param e
     */

    public void filterEmpByHireType(ActionEvent e) {
        filterEmpByHireType();
    }

    public void filterEmpByHireType() {
        //        getPagingBean().clearDTOS();

        generatePagingRequestDTO("0");
        List<IBasicDTO> empList = new ArrayList<IBasicDTO>();
        IEmpCandidatesDTO dto = (IEmpCandidatesDTO)getPageDTO();
        try {
            if (dto.getHireTypeKey() != null && !dto.getHireTypeKey().equals("-1")) {
                empList =
                        EmpClientFactory.getEmpCandidatesClient().filterByHireTypeWithMin(Long.valueOf(dto.getHireTypeKey()),
                                                                                          getLoginedMinistrycode());
                setHireType(((IHireTypesEntityKey)dto.getHireTypesDTO().getCode()).getHirtypeCode());


            } else {
                empList =
                        EmpClientFactory.getEmpCandidatesClient().filterByAllHireTypesWithMin(getLoginedMinistrycode());

                setHireType(0L);

            }


        } catch (DataBaseException f) {
            f.printStackTrace();
        }
        if (isSearchMode()) {
            try {
                cancelSearch();
                setSearchMode(false);
            } catch (DataBaseException f) {
            }
        }
        setAllList(empList);
        setUpdateMyPagedDataModel(true);
        setSelectedRadio("");

        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();

        }
    }

    public void setAllList(List allList) {
        this.allList = allList;
    }

    public List getAllList() {
        return allList;
    }


    public void hireExecution() {
        IEmpCandidatesDTO dto = (IEmpCandidatesDTO)getSelectedDTOS().get(0);

        IHireStagesEntityKey key =
            EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstants._EMP_STAGE_WAITING_EXECUTION);

        IHireStagesDTO hireStageDTO = EmpDTOFactory.createHireStagesDTO(); //newobject

        hireStageDTO.setCode(key);

        dto.setHireStagesDTO(hireStageDTO);

        try {
            EmpClientFactory.getEmpCandidatesClient().update(dto);
        } catch (Exception e) {
            getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp", "fail_execution");
        }
        getSharedUtil().handleSuccMsg("com.beshara.csc.hr.emp.presentation.resources.emp", "success_execution");
        filterEmpByHireType();
    }

    /*public void canselEmployee() {
        IEmployeesDTO dto = (IEmployeesDTO)getSelectedDTOS().get(0);

        IHireStagesEntityKey key = EmpEntityKeyFactory.createHireStagesEntityKey(-40l);

        IHireStagesDTO hireStageDTO = EmpDTOFactory.createHireStagesDTO();

        hireStageDTO.setCode(key);

        dto.setHireStageDTO(hireStageDTO);

        try {
           EmpClientFactory.getEmployeesClient().update(dto);

        } catch (Exception e) {
            getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp", "fail_cansel");
        }
        getSharedUtil().handleSuccMsg("com.beshara.csc.hr.emp.presentation.resources.emp", "success_cansel");
        filterEmpByHireType();
    }


*/


    /////////////////// To DO important    disabled="#{!$myBean$.validToLink }"   //////////////////

    public boolean isValidToLink() {
        if (!getSelectedDTOS().isEmpty() && getSelectedDTOS().size() != 0) {
            //            IEmpCandidatesDTO selectedDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
            //            if (selectedDTO.getHireStagesDTO() != null) {
            //                if (selectedDTO.getHireStagesDTO().getCode() != null) {
            //                    if (selectedDTO.getHireStagesDTO().getCode().getKey().equals("2")) {
            //                        this.validToLink = true;
            //                    } else {
            //                        this.validToLink = false;
            //                    }
            //                } else {
            //                    this.validToLink = false;
            //                }
            this.validToLink = true;
        } else {
            this.validToLink = false;
        }
        //}
        return validToLink;
    }

    public void setValidToLink(boolean valid) {
        this.validToLink = valid;
    }

    public void setJdHelper(JoinDecHelperBean jdHelper) {
        this.jdHelper = jdHelper;
    }

    public JoinDecHelperBean getJdHelper() {
        return jdHelper;
    }

    public void setFilteredList(List filteredList) {
        this.filteredList = filteredList;
    }

    public List getFilteredList() {
        return filteredList;
    }

    public void initDT() {
        try {
            if (super.getMyTableData() == null || super.getMyTableData().size() == 0) {
                IHireStagesEntityKey entityKey = EmpEntityKeyFactory.createHireStagesEntityKey(Long.parseLong("-1"));
                IHireStagesDTO hireStagesDTO = EmpDTOFactory.createHireStagesDTO();
                hireStagesDTO.setCode(entityKey);
                ((IEmpCandidatesDTO)getPageDTO()).setHireTypesDTO(hireStagesDTO);

                filterEmpByHireType();
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void setHireStagesList(List<IBasicDTO> hireStagesList) {
        this.hireStagesList = hireStagesList;
    }

    public List<IBasicDTO> getHireStagesList() {

        if (hireStagesList.size() == 0) {
            SharedUtilBean sharedUtilBean = getSharedUtil();

            List<IBasicDTO> dtos = new ArrayList<IBasicDTO>();
            for (int i = 0; i < 1; i++) {
                dtos.add(i, new BasicDTO());
            }

            dtos.get(0).setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT_DECISION));
            dtos.get(0).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                              "makeing_hire_decision"));
            dtos.add(1, new BasicDTO());
            dtos.get(1).setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT));
            dtos.get(1).setName(sharedUtilBean.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                              "done_hire_decision"));


            hireStagesList = dtos;

        }


        return hireStagesList;
    }


    public void setHireTypeName(String hireTypeName) {
        this.hireTypeName = hireTypeName;
    }

    public String getHireTypeName() {
        return hireTypeName;
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

    public void setResList(List<IBasicDTO> resList) {
        this.resList = resList;
    }

    public List<IBasicDTO> getResList() {
        return resList;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String addDecision() {
        String BUNDULE_NAME = "com.beshara.csc.nl.reg.integration.presentation.resources.integration";
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            BaseBean beanFrom =
                ((BaseBean)getIntegrationBean().getHmBaseBeanFrom().get(getIntegrationBean().getBeanNameFrom()));
            if (beanFrom.getSelectedDTOS() != null && beanFrom.getSelectedDTOS().size() > 0 &&
                beanFrom.getSelectedDTOS().get(0) != null && beanFrom.getSelectedDTOS().get(0).getCode() != null) {

                IBasicDTO decisionsDTO = getIntegrationBean().getSelectedDTOTo().get(0);
                for (int i = 0; i < ((IDecisionsDTO)decisionsDTO).getRegPersonDecisionDtoList().size(); i++) {
                    (((IRegPersonDecisionsDTO)(((IDecisionsDTO)decisionsDTO).getRegPersonDecisionDtoList().get(i)))).setTabrecSerialRef(((IEmpCandidatesDTO)beanFrom.getSelectedDTOS().get(i)).getTabrecSerial());
                    //                    ((IDecisionsDTO)decisionsDTO).setRegPersonDecisionDtoList(getIntegrationBean().getSelectedDTOFrom());
                }


                IDecisionsDTO addedDecisionDTO = (IDecisionsDTO)RegClientFactory.getDecisionsCMTClient().addDectionFromIntegration((IDecisionsDTO)decisionsDTO);
                beanFrom.getHighlightedDTOS().clear();
                if (beanFrom.getHighlightedDTOS() != null) {
                    for (int i = 0; i < beanFrom.getSelectedDTOS().size(); i++) {
                        beanFrom.getHighlightedDTOS().addAll(beanFrom.getSelectedDTOS());
                    }
                }
//                getSharedUtil().handleSuccMsg(BUNDULE_NAME, "execute_add_decision_success");
                /* start CSC-23106 */
                String message = getSharedUtil().messageLocator(BUNDULE_NAME , "execute_add_decision_success");
                //getSharedUtil().setSuccessMsgValue(message + " " + addedDTO.getRegNum());
                getSharedUtil().handleSuccMsg(message + " " + addedDecisionDTO.getRegNum());
                /* end CSC-23106 */

                //تن�?يذ ربط القرار
                if (((IEmpCandidatesClient)getClient()).applyDecisionForHire(getSelectedDTOS() ) ) {
                    String msg = sharedUtil.messageLocator(EMP_RESOURCES, "applydecJoinhireSuccess");

                    sharedUtil.setSuccessMsgValue(msg + " " + addedDecisionDTO.getRegNum() );
                    filterEmpByHireType();
                }
                /* start CSC-23106 */
                openReportAfterExecution(addedDecisionDTO);
                /* end CSC-23106 */
                //
            }
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, BUNDULE_NAME, "execute_add_decision_fail");
        } catch (DataBaseException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, BUNDULE_NAME, "execute_add_decision_fail");
        } catch (Exception e) {
            e.printStackTrace();
            // getSharedUtil().handleException(e, BUNDULE_NAME, "execute_add_decision_fail");
        }
        return getIntegrationBean().getNavgationCaseFrom();
    }


    public void gotoJoinDecisoin() {
        //
        enableViewCondDtls = false;
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0 && getSelectedDTOS().get(0) != null &&
            ((IEmpCandidatesDTO)getSelectedDTOS().get(0)).getCitizensResidentsDTO() != null) {
            try {
                for (int i = 0; i < getSelectedDTOS().size(); i++) {
                    IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(i);

                    //////////////
                    SharedUtilBean sharedUtil = getSharedUtil();

                    //اذا كان احد المرشحين المختارين حالته "جاري تن�?يذ التعيين" تظهر شاشة وبها المرشحين

                    resList = ((IEmpCandidatesClient)getClient()).getOtherCandByCivils(getSelectedDTOS());
                    if (resList.size() > 0) {
                        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                        setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errCancelAnotherCand"));
                        return;
                    }

                    resList = ((IEmpCandidatesClient)getClient()).checkDecisionHire(getSelectedDTOS());
                    if (resList.size() > 0) {
                        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                        setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errJoinDecBefore"));
                        return;
                    }

                    resList = ((IEmpCandidatesClient)getClient()).checkConditions(getSelectedDTOS());
                    if (resList.size() > 0) {
                        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                        setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errcandConditionFailed"));
                        return;
                    }
                    //تن�?يذ ربط القرار

                    resList = ((IEmpCandidatesClient)getClient()).checkConditions(getSelectedDTOS());
                    if (resList.size() > 0) {
                        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                        setErrorMessage(sharedUtil.messageLocator(EMP_RESOURCES, "errcandConditionFailed"));
                        return;
                    }


                }
                ((EmpJoinDecHelperBean)jdHelper).openJoinDivForExcute();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void viewCondLines(ActionEvent actionEvent) {
        List<Long> tabrecSerial = new ArrayList();
        IEmpCandidatesDTO empCandidatesDTO =
            (IEmpCandidatesDTO)actionEvent.getComponent().getAttributes().get("empCandDTO");
        Long civilId = Long.valueOf(empCandidatesDTO.getCitizensResidentsDTO().getCode().getKey());
        Long hiretype = ((IHireTypesEntityKey)empCandidatesDTO.getHireTypesDTO().getCode()).getHirtypeCode();
        try {
            tabrecSerial = EmpClientFactory.getHireTypesClient().getHireTyperecserialByHireType(hiretype);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        cdIntegrationBean.setTabRecSerial(tabrecSerial.get(0));
        cdIntegrationBean.setCivilId(civilId);
        cdIntegrationBean.setContainerBeanName("hireDecisionListBean");
        cdIntegrationBean.viewConditionlinesDetails();
    }


    public void setCdIntegrationBean(ConditionLinesdetailsHelperBean cdIntegrationBean) {
        this.cdIntegrationBean = cdIntegrationBean;
    }

    public ConditionLinesdetailsHelperBean getCdIntegrationBean() {
        return cdIntegrationBean;
    }

    public void setEnableViewCondDtls(boolean enableViewCondDtls) {
        this.enableViewCondDtls = enableViewCondDtls;
    }

    public boolean isEnableViewCondDtls() {
        return enableViewCondDtls;
    }

    public String goExecuteEmployement() {
      return  empExecuteListBean.goEmpExecute();
    }


    public void buildDecisionText() {
                SharedUtilBean sharedUtil = getSharedUtil();
                Long genCode = null;
        String startLine="<div style=\"font-family: Tahoma;font-size: 10pt;text-align:right;margin-right:10px;\">";
        String newLine = "</div>";
        com.beshara.csc.nl.reg.integration.presentation.backingbean.decision.DecisionMaintainBean decisionMaintain =
            (com.beshara.csc.nl.reg.integration.presentation.backingbean.decision.DecisionMaintainBean)evaluateValueBinding("decisionMaintainBean");
        IEmpCndSalaryElementsClient candSalClient = EmpClientFactory.getEmpCndSalaryElementsClient();
        List<IEmpCandidateExtraDataDTO> candSalList ;
                                                   
        StringBuilder decText = new StringBuilder("<div style=\"font-family: Tahoma;font-size: 10pt;text-align:right;margin-right:10px;\">");
        //decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "decsion_Header"));
        decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "decsion_Header1"));
        decText.append(newLine).append(startLine);
        decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "decsion_Header2"));
        decText.append(newLine).append(startLine);
        decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "decsion_Header3"));
        decText.append(newLine).append(startLine);
        decText.append(newLine).append(startLine);
        decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "decsion_Header4"));
        decText.append(newLine).append(startLine);
        decText.append(newLine).append(startLine);
        decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "first_unit"));
        decText.append(newLine).append(startLine);
        for (IBasicDTO basic : getSelectedDTOS()) {
            IEmpCandidatesDTO dto = (IEmpCandidatesDTO)basic;
            IKwtCitizensResidentsDTO citizenDTO =
                (IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)dto).getCitizensResidentsDTO();
            IPersonQualificationsDTO qualDTO = fillQual(citizenDTO.getCivilId());
            List salData = fillSalaryElementData(dto);
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
                        if(empCandidatesDTO != null && empCandidatesDTO.getCode() != null ){
                           genCode = ((IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO()).getGentypeCode();     
                        }
                        if(genCode != null && genCode == 1){
                            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "emp_women_name_in_dec")); // emp name
                            decText.append(citizenDTO.getFullName());
                        }else {
                            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "emp_man_name_in_dec")); // emp name
                            decText.append(citizenDTO.getFullName());
                        }
//            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "emp_name_in_dec")); // emp name
//            decText.append(citizenDTO.getFullName());
            
            decText.append(newLine).append(startLine);
            
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "nationality_in_dec")); // nationality
            try {
                decText.append(InfClientFactory.getCountriesClient().getById(new CountriesEntityKey(citizenDTO.getNationality())).getName());
                
                decText.append(newLine).append(startLine);

            } catch (Exception e) {
                e.printStackTrace();
                decText.append("");
            }
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "accoring_to_data"));
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "real_civil"));
            decText.append(" "+citizenDTO.getCode().getKey() );
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "min_file"));
            decText.append((dto.getMinistryFileNo() == null) ?  " " :  dto.getMinistryFileNo());
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "qual"));
            decText.append((qualDTO ==null || qualDTO.getQualificationsDTO().getName()== null) ? " " : qualDTO.getQualificationsDTO().getName());
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "qual_Date"));
            decText.append(qualDTO ==null || qualDTO.getQualificationDate() == null ? " " : qualDTO.getQualificationDate());
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "job"));
            decText.append( dto.getJobsDTO().getName() == null ? " " : dto.getJobsDTO().getName());
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "work_center"));
            decText.append( dto.getWorkCentersDTO().getName() == null ? " " : dto.getWorkCentersDTO().getName());
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "bgt_type_for_dec"));
            decText.append( dto.getBgtTypesDTO().getName() == null ? " " : dto.getBgtTypesDTO().getName());
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "sal_cader"));
            decText.append(salData.get(0) ==null ?  " "  : salData.get(0) );
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "sal_group"));
            decText.append( salData.get(1) == null ? " " :salData.get(1));
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "sal_degree"));
            decText.append(salData.get(2) == null ? " " : salData.get(2));
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "first_related_deg"));
            decText.append(salData.get(3) == null ? " " :salData.get(3) );
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "raise_count"));
            decText.append(salData.get(4)  == null ? " " : salData.get(4));
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "raise_next_Date"));
            decText.append(salData.get(5) == null ? " " : salData.get(5));
            decText.append(newLine).append(startLine);
            decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "replacement"));
            decText.append(newLine).append(startLine);
            try{
          candSalList=  candSalClient.getBounsForCandidae((IEmpCandidatesEntityKey)dto.getCode());
            }catch(Exception e)   {
                candSalList = new ArrayList<IEmpCandidateExtraDataDTO> ();
            }
            
            if(candSalList!=null && candSalList.size()>0) {
                for(IEmpCandidateExtraDataDTO candSal :candSalList){
                  decText.append( candSal.getValueDesc()  +"  " +  candSal.getName() );
                    decText.append("\n" );
                    
                }
                
            }else{
                decText.append( " \n " );
            }

        }

        decText.append(newLine).append(startLine);
        decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "secound_unit"));
        decText.append(" ");

        decText.append(newLine).append(startLine);
        decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "reg_date"));
        decText.append(  ((IDecisionsDTO)decisionMaintain.getPageDTO()).getDecisionDate() == null ? SharedUtils.getSqlDate().toString() : new java.sql.Date(((IDecisionsDTO)decisionMaintain.getPageDTO()).getDecisionDate().getTime()).toString());
        
      //  decText.append(getSharedUtil().messageLocator(BUNDLE_NAME, "sign_name"));
        if (((IDecisionsDTO)decisionMaintain.getPageDTO()).getSignBy() != null){
            decText.append("<div style=\"text-align: left;margin-left:80px; font-size: 12px;\">");
            decText.append(" " + ((IDecisionsDTO)decisionMaintain.getPageDTO()).getSignBy());
            decText.append("</p>");
        }

        String finalDecText = decText.toString();

        finalDecText = finalDecText.replaceAll("\n", "</div><div style=\"font-family: Tahoma;font-size: 10pt;text-align:right;margin-right:10px;\">");
        finalDecText = finalDecText.replaceAll("<p>", "<div style=\" font-family: Tahoma;font-size: 10pt;text-align:right;margin-right:10px;\">");
        finalDecText = finalDecText.replaceAll("</p>", "</div>");
        finalDecText = finalDecText.replaceAll("\\s\\s\\s\\s\\s","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        finalDecText = finalDecText.replaceAll("\\s\\s\\s","&nbsp;&nbsp;&nbsp;");
        finalDecText = finalDecText.replaceAll("text-align: left;", "margin-right: 280px;");
        ((IDecisionsDTO)decisionMaintain.getPageDTO()).setDecisionText(finalDecText);   
        ((IDecisionsDTO)decisionMaintain.getPageDTO()).setDecisionText(finalDecText);  
                IEmpCandidatesDTO empCandidatesDTO1 = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
                IKwtCitizensResidentsDTO citizenDTO1 =
                    (IKwtCitizensResidentsDTO)(empCandidatesDTO1).getCitizensResidentsDTO();
                    String decTitle2 = citizenDTO1.getFullName();
                if(genCode != null && genCode == 1){
                    String decTitle1 = sharedUtil.messageLocator(BUNDLE_NAME, "women_emp_decision");
                    ((IDecisionsDTO)decisionMaintain.getPageDTO()).setDecisionTitle(decTitle1+decTitle2);
                }else {
                    String decTitle1 = sharedUtil.messageLocator(BUNDLE_NAME, "man_emp_decision");
                    ((IDecisionsDTO)decisionMaintain.getPageDTO()).setDecisionTitle(decTitle1+decTitle2); // emp name
                }
                int currYear = getCurrentYear();
                ((IDecisionsDTO)decisionMaintain.getPageDTO()).setYearsDTOKey(String.valueOf(currYear));
            }
            public static int getCurrentYear() {
                
        Calendar currentDate = Calendar.getInstance();
        int yearCode = currentDate.get(Calendar.YEAR);
               return yearCode;
    }


    public IPersonQualificationsDTO fillQual(Long realCivilId) {
        IPersonQualificationsDTO personQulDTO = InfDTOFactory.createPersonQualificationsDTO();
        List personalQulList = new ArrayList();
        IPersonQualificationsDTO persontDTO = null;
        List personQulList = null;
        try {
            persontDTO =
                    (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getPersonQulificationInfo(realCivilId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        personalQulList.add(persontDTO);
        personQulList = personalQulList;

        if (personQulList != null && personQulList.size() > 0)
            personQulDTO = (IPersonQualificationsDTO)personQulList.get(0);
        return personQulDTO;

    }


    private List fillSalaryElementData(IEmpCandidatesDTO emp) {
        List salData = new ArrayList();
        ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
        ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
        ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
        List<ISalExtraDataTypesDTO> salExtraDataTypesDTOList = new ArrayList();
        IEmpCndSalaryElementsDTO salaryElementDTO = EmpDTOFactory.createEmpCndSalaryElementsDTO();
        String caderName = null;
        String groupName = null;
        String degreeName = null;
        Long countGuid = null;
        Date dateOfNextRaise = null;
        float raiseValue =0;

        try {
            salaryElementDTO = EmpClientFactory.getEmpCndSalaryElementsClient().getByCandCode(emp.getCode());
            countGuid = salaryElementDTO.getSalElementGuidesDTO().getCountGuide(); /// عدد العلاوات ///
            String raiseCode =
                ((ISalElementGuidesEntityKey)salaryElementDTO.getSalElementGuidesDTO().getCode()).getElmguideCode().toString();
            if (salaryElementDTO != null && salaryElementDTO.getCode() != null) {
                if (raiseCode != null) {
                    raiseDTO =
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.valueOf(raiseCode));
                if(raiseDTO.getValue()!=null)
                   raiseValue= raiseDTO.getValue();
                }
//                try {
//                    salExtraDataTypesDTOList =
//                            (List)SalClientFactory.getSalGuideExtraDataClient().getExtraDataTypesByElmguideCode(raiseDTO.getCode());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                if (salExtraDataTypesDTOList != null && salExtraDataTypesDTOList.size() > 0) {
//                    String contractType = salExtraDataTypesDTOList.get(0).getCode().getKey();
//                }
                //Degree
                Long degreeCode=null;
                if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                    degreeCode = ((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode();
                    degreeName = raiseDTO.getParentObject().getName();
                }
                if (degreeCode != null) {
                    degreeDTO =
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(degreeCode);
                }
                //Group
                Long groupCode = null;
                if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                    groupCode = ((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode();
                    groupName = degreeDTO.getParentObject().getName();
                }
                if (groupCode != null) {
                    groupDTO =
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(groupCode);
                }
                //Cader
                if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                    Long caderCode =
                        ((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode();
                    caderName = groupDTO.getParentObject().getName();
                }

            }
        } catch (DataBaseException e) {
            System.out.println(e.getMessage());
        } catch (SharedApplicationException e) {
            System.out.println(e.getMessage());
        }
        dateOfNextRaise = emp.getDateOfNextRaise();
        salData.add(caderName);
        salData.add(groupName);
        salData.add(degreeName);
        salData.add(raiseValue);
        salData.add(countGuid);
        salData.add(dateOfNextRaise);
        return salData;
    }


    public void setEmpExecuteListBean(EmpExecuteListBean empExecuteListBean) {
        this.empExecuteListBean = empExecuteListBean;
    }

    public EmpExecuteListBean getEmpExecuteListBean() {
        return empExecuteListBean;
    }
    /* start CSC-23106 */
    public void setReportTemplatesList(List reportTemplatesList) {
        this.reportTemplatesList = reportTemplatesList;
    }

    public List getReportTemplatesList() {
        return reportTemplatesList;
    }

    public void setReportsTemplate(Map reportsTemplate) {
        this.reportsTemplate = reportsTemplate;
    }

    public Map getReportsTemplate() {
        return reportsTemplate;
    }
    
    private String getReportTemplateName(String templateKey) {
        List<IBasicDTO> list = reportTemplatesList;
        String repTemplateName = "";
        for (IBasicDTO dto : list) {
            ITemplatesDTO templateDTO = (ITemplatesDTO)dto;
            if (templateKey.equals(templateDTO.getCode().getKey())) {
                repTemplateName = templateDTO.getName();
            }

        }
        return repTemplateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }
    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }
    public String getReportUrl() {
        return reportUrl;
    }
    /* end CSC-23106 */
}

