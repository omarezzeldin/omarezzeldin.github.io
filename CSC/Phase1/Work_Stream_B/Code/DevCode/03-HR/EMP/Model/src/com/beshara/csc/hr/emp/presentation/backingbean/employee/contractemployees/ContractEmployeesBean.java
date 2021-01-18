package com.beshara.csc.hr.emp.presentation.backingbean.employee.contractemployees;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.dto.EmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.EmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.EmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExtraDataTypesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.EmployeeListBean;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.shared.PrintListBean;
import com.beshara.csc.hr.emp.presentation.backingbean.fatwaemploymentcycle.shared.AddBounceBean;
import com.beshara.csc.hr.sal.business.client.ISalElementGuidesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalElementGuidesEntityKey;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.ICatsDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.business.dto.JobsDTO;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.job.business.entity.JobsEntityKey;
import com.beshara.csc.nl.job.integration.presentation.backingbean.search.JobFilter;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class ContractEmployeesBean extends LookUpBaseBean {

    public static final String METHOD_NAME_ADD_SELECTED_JOBS = "contractEmployeesBean.jobs";
    private static final String PAGE_URI = "/module/jsps/employee/contractemployees/printList.jsf";
    private String workCenterName;
    private String cader;
    private String group;
    private String degree;
    private String raise;
    private String eqcader;
    private String eqgroup;
    private String eqdegree;
    private String eqraise;
    private String backActionMethodName;
    private String bckBtnNavigationCase;
    private String deptNotes;
    private String selectionNotes;
    private String fatwaNotes;
    private IJobsDTO finalJobDTO;
    ISalElementGuidesDTO salEqElemGuidDTO;

    private String jobKey;
    private IJobsDTO jobsDTO = JobDTOFactory.createJobsDTO();
    private String suggestedJobValue;
    private JobFilter jobFilter = new JobFilter();
    private boolean jobMode;
    private boolean renderLovDiv = false;
    private boolean renderJobDiv;
    private boolean searchJobMode = false;
    private String jobSearchType = "0";
    private String hireStageCode;
    private String hireStageName;
    private String emp_final_job;
    private List saveStateList = new ArrayList();
    private boolean errorJobCondition = false;
    private boolean errorJobCondition1 = false;
    private boolean jobFromMin;
    private String approvedJobValue;
    private String jobKey1;

    public ContractEmployeesBean() {

        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setClient(EmpClientFactory.getEmpCandidatesClient());

        ((EmpCandidatesDTO)getPageDTO()).setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());

        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setUsingPaging(true);
        if (getLovBaseBean().isUsingPaging()) {
            getLovBaseBean().generatePagingRequestDTO("contractEmployeesBean", "initMainDataBean");
        } else {
            getLovBaseBean().setMyTableData(new ArrayList());
        }

        initJobFilter();
        setCustomDiv1(getCustomDiv1() + " extraTooWideDiv");
    }

    public PagingResponseDTO initMainDataBean(PagingRequestDTO request) {
        request = null;

        return new PagingResponseDTO(new ArrayList());
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowbar(false);
        app.setShowdatatableContent(true);
        app.setShowContent1(false);
        app.setShowSearch(false);
        app.setShowbar(false);
        app.setShowpaging(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(true);
        app.setShowDelAlert(true);
        app.setShowDelConfirm(false);
        app.setShowTreediv(false);
        app.setShowLovDiv(true);
        app.setShowCustomDiv1(true);

        return app;
    }

    public void findJobName() {
        IJobsDTO filteredjobDTO = JobDTOFactory.createJobsDTO();
        filteredjobDTO.setJobKey(jobKey);

        try {
            errorJobCondition = false;
            // jobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getByJobKey(filteredjobDTO);
            jobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getJobInMinistry(filteredjobDTO, CSCSecurityInfoHelper.getLoggedInMinistryCode());
            ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(jobsDTO);
            //                ((EmpCandidatesDTO)getPageDTO()).setJobKey(jobKey);
            String jobKey1 = jobsDTO.getCode().getKey();
            setJobKey(jobKey);
            ((IEmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setSuggestedJobCode(jobKey1);
            setSuggestedJobValue(jobsDTO.getName());
            // to do
            String jobCode = jobKey1;
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
                ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(null);
                ((IEmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setSuggestedJobCode(null);
            }


        } catch (DataBaseException e) {
            setSuggestedJobValue(null);
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }

    private void initJobFilter() {
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
        //setWorkCenterMode(false);
        setJobMode(true);
        setRenderLovDiv(true);
    }

    public void InitiateData() {

        try {
            ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO caderDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesClient salClient = SalClientFactory.getSalElementGuidesClient();
            finalJobDTO = null;
            salEqElemGuidDTO = null;

            hireStageCode = ((IEmpCandidatesDTO)getPageDTO()).getHireStagesDTO().getCode().getKey();
            hireStageName = ((IEmpCandidatesDTO)getPageDTO()).getHireStagesDTO().getName();

            IWorkCentersDTO cndWorkCenter = ((IWorkCentersDTO)((IEmpCandidatesDTO)getPageDTO()).getWorkCentersDTO());
            if (cndWorkCenter != null) {
                workCenterName = cndWorkCenter.getName();
            }

            List candSalList = ((IEmpCandidatesDTO)getPageDTO()).getEmpCndSalaryElementsList();
            if (candSalList != null) {
                IEmpCndSalaryElementsDTO cndSalaryElementDTo = ((IEmpCndSalaryElementsDTO)(candSalList.get(0)));
                ISalElementGuidesDTO salElemGuid = (ISalElementGuidesDTO)cndSalaryElementDTo.getSalElementGuidesDTO();
                ISalElementGuidesDTO salEqElemGuid =
                    (ISalElementGuidesDTO)cndSalaryElementDTo.getSalEqElementGuidesDTO();

                if (cndSalaryElementDTo != null) {
                    raiseDTO =
                            (ISalElementGuidesDTO)salClient.getElmGuideByCode(Long.parseLong(salElemGuid.getCode().getKey()));
                    degreeDTO =
                            (ISalElementGuidesDTO)salClient.getElmGuideByCode(Long.parseLong(raiseDTO.getParentCode().getKey()));
                    groupDTO =
                            (ISalElementGuidesDTO)salClient.getElmGuideByCode(Long.parseLong(degreeDTO.getParentCode().getKey()));
                    caderDTO =
                            (ISalElementGuidesDTO)salClient.getElmGuideByCode(Long.parseLong(groupDTO.getParentCode().getKey()));

                    Long countGuide = raiseDTO.getCountGuide();
                    if (countGuide == null)
                        raise = "";
                    else
                        raise = countGuide.toString();

                    degree = degreeDTO.getName();
                    group = groupDTO.getName();
                    cader = caderDTO.getName();
                }
            }


            List<IBasicDTO> lstExtraData = ((IEmpCandidatesDTO)getPageDTO()).getEmpCandidateExtraDataList();
            if (lstExtraData == null)
                return;

            acceptedTotalReward = 0F;
            for (IBasicDTO extraDataDTO : lstExtraData) {
                IEmpExtraDataTypesDTO extraDataTypeDTO =
                    ((EmpCandidateExtraDataDTO)extraDataDTO).getEmpExtraDataTypesDTO();
                int extraDataTypeCode = Integer.parseInt(extraDataTypeDTO.getCode().getKey());

                switch (extraDataTypeCode) {
                case 5:
                    deptNotes = ((EmpCandidateExtraDataDTO)extraDataDTO).getValue();
                    break;
                case 7:
                    selectionNotes = ((EmpCandidateExtraDataDTO)extraDataDTO).getValue();
                    break;
                case 8:
                    fatwaNotes = ((EmpCandidateExtraDataDTO)extraDataDTO).getValue();
                    break;
                case 4:
                    String jobCode = ((EmpCandidateExtraDataDTO)extraDataDTO).getValue();
                    if (!jobCode.trim().equals("") && jobCode != null) {
                        IJobsDTO deptJobDTO = JobDTOFactory.createJobsDTO();
                        IJobsEntityKey key = new JobsEntityKey(jobCode);

                        deptJobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(key);
                        setJobKey(((JobsDTO)deptJobDTO).getJobKey());
                        setSuggestedJobValue(((JobsDTO)deptJobDTO).getName());
                    }
                    break;
                case 345:
                    if (((EmpCandidateExtraDataDTO)extraDataDTO).getValue().equals("1")) {
                        setJobFromMin(true);
                    } else {
                        setJobFromMin(false);
                    }
                break;
                case 13:
                    String finalJobCode = ((EmpCandidateExtraDataDTO)extraDataDTO).getValue();
                    if (!finalJobCode.trim().equals("") && finalJobCode != null) {
                        finalJobDTO = JobDTOFactory.createJobsDTO();
                        IJobsEntityKey key = new JobsEntityKey(finalJobCode);

                        finalJobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(key);
                        emp_final_job = ((JobsDTO)finalJobDTO).getName();
                    }
                    break;
                case 14:
                    String eqElemGuide = ((EmpCandidateExtraDataDTO)extraDataDTO).getValue();
                    salEqElemGuidDTO = SalDTOFactory.createSalElementGuidesDTO();
                    ISalElementGuidesEntityKey egKey = new SalElementGuidesEntityKey(Long.parseLong(eqElemGuide));

                    salEqElemGuidDTO =
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getByCode(egKey);

                    if (salEqElemGuidDTO != null) {
                        raiseDTO =
                                (ISalElementGuidesDTO)salClient.getElmGuideByCode(Long.parseLong(salEqElemGuidDTO.getCode().getKey()));
                        degreeDTO =
                                (ISalElementGuidesDTO)salClient.getElmGuideByCode(Long.parseLong(raiseDTO.getParentCode().getKey()));
                        groupDTO =
                                (ISalElementGuidesDTO)salClient.getElmGuideByCode(Long.parseLong(degreeDTO.getParentCode().getKey()));
                        caderDTO =
                                (ISalElementGuidesDTO)salClient.getElmGuideByCode(Long.parseLong(groupDTO.getParentCode().getKey()));

                        Long eqCountGuide = raiseDTO.getCountGuide();
                        if (eqCountGuide == null)
                            eqraise = "";
                        else
                            eqraise = eqCountGuide.toString();

                        eqdegree = degreeDTO.getName();
                        eqgroup = groupDTO.getName();
                        eqcader = caderDTO.getName();
                        // added
                        acceptedTotalReward += Float.valueOf(((EmpCandidateExtraDataDTO)extraDataDTO).getValueDesc());
                    }
                    break;
                case 17:
                    String suggestedTotalReward = ((EmpCandidateExtraDataDTO)extraDataDTO).getValue();
                    ((EmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setSuggestedTotalReward(suggestedTotalReward);
                    break;
                case 21:
                    String valueDesc = ((EmpCandidateExtraDataDTO)extraDataDTO).getValueDesc();
                    acceptedTotalReward += Float.valueOf(valueDesc);
                    String value = ((EmpCandidateExtraDataDTO)extraDataDTO).getValue();
                    fillBounsesList(value,valueDesc);
                    
                    break;
                }
            }
            if (acceptedTotalReward.equals(0F)) {
                ((EmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setAcceptedTotalReward("");
            } else {
                ((EmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setAcceptedTotalReward(acceptedTotalReward.toString());
            }

            //((IWorkCentersDTO)((IEmpCandidatesDTO)getPageDTO()).getEmpCandidateExtraDataList()
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }
    private Float acceptedTotalReward;
    private List<ISalEmpSalaryElementsDTO> bounsesList=new ArrayList();

    private void fillBounsesList(String value,String valueDec) {
        ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = SalDTOFactory.createSalEmpSalaryElementsDTO();
        ISalElementGuidesDTO bounsesDTO = SalDTOFactory.createSalElementGuidesDTO();
        ISalElementGuidesEntityKey key = new SalElementGuidesEntityKey(Long.valueOf(value));
        try {
            bounsesDTO = (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getByCode(key);
        } catch (ItemNotFoundException e) {
        } catch (DataBaseException e) {
        }
        bounsesDTO.setValue(Float.valueOf(valueDec));
        salEmpSalaryElementsDTO.setSalElementGuidesDTO(bounsesDTO);
        bounsesList.add(salEmpSalaryElementsDTO);
    }

    public void jobs() {
        errorJobCondition = false;
        if (jobFilter != null && jobFilter.getSelectedDTOS() != null && !jobFilter.getSelectedDTOS().isEmpty()) {
            try {
                ((IEmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setSuggestedJobCode(jobFilter.getSelectedDTOS().get(0).getCode().getKey());
                setSuggestedJobValue(jobFilter.getSelectedDTOS().get(0).getName());

                setJobKey(((IJobsDTO)jobFilter.getSelectedDTOS().get(0)).getJobKey());
                ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(jobFilter.getSelectedDTOS().get(0));
                setJobKey(((IJobsDTO)jobFilter.getSelectedDTOS().get(0)).getJobKey());

                String jobCode = jobFilter.getSelectedDTOS().get(0).getCode().getKey();
                Long realCivil =
                    Long.valueOf(((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
                boolean checkJobCond =
                    JobClientFactory.getJobsClient().checkJobConditions(Long.valueOf(realCivil), jobCode);
                if (!checkJobCond) {
                    errorJobCondition = true;
                    ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(null);
                    ((IEmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().setSuggestedJobCode(null);
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
    }

    public String goExecute() {
        SharedUtilBean shared_util = getSharedUtil();
        IEmpCandidatesDTO bExecuteResult = null;
        String resourceMsg = "";
        String msg = "";
        try {
            IEmpCandidatesClient client = (IEmpCandidatesClient)getClient();
            bExecuteResult =
                    (IEmpCandidatesDTO)client.proceedContractPersonData(getPageDTO(), (IBasicDTO)((IEmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bExecuteResult != null) {
                resourceMsg = "contract_emp_todewan_success";
                msg = shared_util.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", resourceMsg);
                shared_util.setSuccessMsgValue(msg);
            } else {
                resourceMsg = "contract_emp_todewan_error";
                msg = shared_util.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", resourceMsg);
                shared_util.setErrMsgValue(msg);
                return "conract";
            }
        }

        // TODO

        if (bExecuteResult != null) {
            EmployeeListBean empListBean = (EmployeeListBean)evaluateValueBinding("empListBean");
            IEmpCandidatesDTO empcandDTO = bExecuteResult;
            empListBean.setPageDTO(empcandDTO);
            empListBean.searchByMainHireType();
            if (empListBean.isUsingPaging()) {
                empListBean.getPagingBean().clearDTOS();
                empListBean.generatePagingRequestDTO(empcandDTO.getCode().getKey());
            } else {

                try {
                    empListBean.getPageIndex(empcandDTO.getCode().getKey());
                } catch (DataBaseException e) {
                }
            }
            empListBean.getSelectedDTOS().clear();
            empListBean.getHighlightedDTOS().clear();
            empListBean.getHighlightedDTOS().add(empcandDTO);

        }


        return getBckBtnNavigationCase();
    }

    public String goSignExecute() {
        SharedUtilBean shared_util = getSharedUtil();
        IEmpCandidatesDTO bExecuteResult = null;
        String resourceMsg = "";
        String msg = "";
        ContractEmployeesBean contractEmployeesBean =(ContractEmployeesBean)jsfHelper("contractEmployeesBean");
        if (isJobFromMin()) {
            if (getJobKey1() == null || getJobKey1().trim().equals("")) {
                 msg =
                    getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                            "job_required_for_can_approve");
                contractEmployeesBean.getSharedUtil().setErrMsgValue(msg);
                return "";
            }
        }
        try {
            ((IEmpCandidatesDTO)getPageDTO()).setJobsDTO(finalJobDTO);
            ((EmpCndSalaryElementsDTO)(((IEmpCandidatesDTO)getPageDTO()).getEmpCndSalaryElementsList().get(0))).setSalEqElementGuidesDTO(salEqElemGuidDTO);
            Float elementValue =
                Float.valueOf(((EmpCandidatesDTO)getPageDTO()).getEmpExtraDataValueDTO().getAcceptedTotalReward());
            ((EmpCndSalaryElementsDTO)(((IEmpCandidatesDTO)getPageDTO()).getEmpCndSalaryElementsList().get(0))).setElementValue(elementValue);
            IEmpCandidatesClient client = (IEmpCandidatesClient)getClient();
            bExecuteResult = (IEmpCandidatesDTO)client.applyJobSuggestion(getPageDTO());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bExecuteResult != null) {
                resourceMsg = "contract_emp_sign_success";
                msg = shared_util.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", resourceMsg);
                shared_util.setSuccessMsgValue(msg);
            } else {
                resourceMsg = "contract_emp_sign_error";
                msg = shared_util.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", resourceMsg);
                shared_util.setErrMsgValue(msg);
                return "conract";
            }
        }


        //To DO

        if (bExecuteResult != null) {
            EmployeeListBean empListBean = (EmployeeListBean)evaluateValueBinding("empListBean");
            IEmpCandidatesDTO empcandDTO = bExecuteResult;
            empListBean.setPageDTO(empcandDTO);
            empListBean.searchByMainHireType();
            if (empListBean.isUsingPaging()) {
                empListBean.getPagingBean().clearDTOS();
                empListBean.generatePagingRequestDTO(empcandDTO.getCode().getKey());
            } else {

                try {
                    empListBean.getPageIndex(empcandDTO.getCode().getKey());
                } catch (DataBaseException e) {
                }
            }
            empListBean.getSelectedDTOS().clear();
            empListBean.getHighlightedDTOS().clear();
            empListBean.getHighlightedDTOS().add(empcandDTO);

        }

        return getBckBtnNavigationCase();
    }

    public String Back() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        if (!"".equals(backActionMethodName))
            executeMethodBinding(backActionMethodName, null);
        return getBckBtnNavigationCase();
    }

    public void setWorkCenterName(String workCenterName) {
        this.workCenterName = workCenterName;
    }

    public String getWorkCenterName() {
        return workCenterName;
    }

    public void setCader(String cader) {
        this.cader = cader;
    }

    public String getCader() {
        return cader;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setRaise(String raise) {
        this.raise = raise;
    }

    public String getRaise() {
        return raise;
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

    public void setSuggestedJobValue(String suggestedJobValue) {
        this.suggestedJobValue = suggestedJobValue;
    }

    public String getSuggestedJobValue() {
        return suggestedJobValue;
    }

    public void setJobFilter(JobFilter jobFilter) {
        this.jobFilter = jobFilter;
    }

    public JobFilter getJobFilter() {
        return jobFilter;
    }

    public void setJobMode(boolean jobMode) {
        this.jobMode = jobMode;
    }

    public boolean isJobMode() {
        return jobMode;
    }

    public void setRenderLovDiv(boolean renderLovDiv) {
        this.renderLovDiv = renderLovDiv;
    }

    public boolean isRenderLovDiv() {
        return renderLovDiv;
    }

    public void setRenderJobDiv(boolean renderJobDiv) {
        this.renderJobDiv = renderJobDiv;
    }

    public boolean isRenderJobDiv() {
        return renderJobDiv;
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

    public void setHireStageCode(String hireStageCode) {
        this.hireStageCode = hireStageCode;
    }

    public String getHireStageCode() {
        return hireStageCode;
    }

    public void setHireStageName(String hireStageName) {
        this.hireStageName = hireStageName;
    }

    public String getHireStageName() {
        return hireStageName;
    }

    public void setEmp_final_job(String emp_final_job) {
        this.emp_final_job = emp_final_job;
    }

    public String getEmp_final_job() {
        return emp_final_job;
    }

    public void setEqcader(String eqcader) {
        this.eqcader = eqcader;
    }

    public String getEqcader() {
        return eqcader;
    }

    public void setEqgroup(String eqgroup) {
        this.eqgroup = eqgroup;
    }

    public String getEqgroup() {
        return eqgroup;
    }

    public void setEqdegree(String eqdegree) {
        this.eqdegree = eqdegree;
    }

    public String getEqdegree() {
        return eqdegree;
    }

    public void setEqraise(String eqraise) {
        this.eqraise = eqraise;
    }

    public String getEqraise() {
        return eqraise;
    }

    public void setBackActionMethodName(String backActionMethodName) {
        this.backActionMethodName = backActionMethodName;
    }

    public String getBackActionMethodName() {
        return backActionMethodName;
    }

    public void setBckBtnNavigationCase(String bckBtnNavigationCase) {
        this.bckBtnNavigationCase = bckBtnNavigationCase;
    }

    public String getBckBtnNavigationCase() {
        return bckBtnNavigationCase;
    }

    public void setDeptNotes(String deptNotes) {
        this.deptNotes = deptNotes;
    }

    public String getDeptNotes() {
        return deptNotes;
    }

    public void setSelectionNotes(String selectionNotes) {
        this.selectionNotes = selectionNotes;
    }

    public String getSelectionNotes() {
        return selectionNotes;
    }

    public void setFatwaNotes(String fatwaNotes) {
        this.fatwaNotes = fatwaNotes;
    }

    public String getFatwaNotes() {
        return fatwaNotes;
    }

    public void setFinalJobDTO(IJobsDTO finalJobDTO) {
        this.finalJobDTO = finalJobDTO;
    }

    public IJobsDTO getFinalJobDTO() {
        return finalJobDTO;
    }

    public void setSalEqElemGuidDTO(ISalElementGuidesDTO salEqElemGuidDTO) {
        this.salEqElemGuidDTO = salEqElemGuidDTO;
    }

    public ISalElementGuidesDTO getSalEqElemGuidDTO() {
        return salEqElemGuidDTO;
    }

    public void setSaveStateList(List saveStateList) {
        this.saveStateList = saveStateList;
    }

    public List getSaveStateList() {
        return saveStateList;
    }

    public void setErrorJobCondition(boolean errorJobCondition) {
        this.errorJobCondition = errorJobCondition;
    }

    public boolean isErrorJobCondition() {
        return errorJobCondition;
    }

    public String viewBounses() {
        AddBounceBean addBounceBean = AddBounceBean.getInstance();
        addBounceBean.setBackActionMethodName("contractEmployeesBean.reloadDataForReviewListBean");
        addBounceBean.setBckBtnNavigationCase("conract");
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO();
        addBounceBean.setCivilId(kwtCitizensResidentsDTO.getCode().getKey());
        addBounceBean.setFullName(kwtCitizensResidentsDTO.getFullName());
        addBounceBean.setBasicSalary(Float.valueOf(salEqElemGuidDTO.getValue()));
        addBounceBean.setTotalBounces(getAcceptedTotalReward());
        addBounceBean.setViewBounses(true);
        addBounceBean.setMyTableData(getBounsesList());
//        addBounceBean.setEmpCandidatesDTO((IEmpCandidatesDTO)getPageDTO());
        addBounceBean.getSaveStateAddBounceList().add(getPageDTO());
        return "viewBounsesContract";
    }


    public void reloadDataForReviewListBean() {
        AddBounceBean addBounceBean = AddBounceBean.getInstance();
        setPageDTO((IBasicDTO)addBounceBean.getSaveStateAddBounceList().get(0));
    }

    public void setBounsesList(List<ISalEmpSalaryElementsDTO> bounsesList) {
        this.bounsesList = bounsesList;
    }

    public List<ISalEmpSalaryElementsDTO> getBounsesList() {
        return bounsesList;
    }

    public void setAcceptedTotalReward(Float acceptedTotalReward) {
        this.acceptedTotalReward = acceptedTotalReward;
    }

    public Float getAcceptedTotalReward() {
        return acceptedTotalReward;
    }
    private Object jsfHelper(String experssionName) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return app.createValueBinding("#{" + experssionName + "}").getValue(ctx);

    }
    public void printAction(){
            PrintListBean printListBean = (PrintListBean)jsfHelper("printListBean");
            ContractEmployeesBean reviewListBean = (ContractEmployeesBean)jsfHelper("contractEmployeesBean");
            IEmpCandidatesDTO selected = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
           printListBean.constructConditionDetailsPagePath(((IEmpCandidatesEntityKey)selected.getCode()).getCandidateCode().toString(), PAGE_URI);
           reviewListBean.setJavaScriptCaller("openEmpRequestDetailsWindow();");
            JSFHelper.getSession().setAttribute("printPageDTO", reviewListBean.getPageDTO());
            JSFHelper.getSession().setAttribute("workCenterName", reviewListBean.getWorkCenterName());
            JSFHelper.getSession().setAttribute("suggestedJobValue", reviewListBean.getSuggestedJobValue());
           JSFHelper.getSession().setAttribute("suggestedCaderName", reviewListBean.getCader());
            JSFHelper.getSession().setAttribute("suggestedGroupName", reviewListBean.getGroup());
            JSFHelper.getSession().setAttribute("suggestedDegreeName", reviewListBean.getDegree());
            JSFHelper.getSession().setAttribute("suggestedRaiseName", reviewListBean.getRaise());
            JSFHelper.getSession().setAttribute("acceptedJobValue", reviewListBean.getEmp_final_job());
            JSFHelper.getSession().setAttribute("acceptedCaderName", reviewListBean.getEqcader());
            JSFHelper.getSession().setAttribute("acceptedGroupName", reviewListBean.getEqgroup());
            JSFHelper.getSession().setAttribute("acceptedDegreeName", reviewListBean.getEqdegree());
            JSFHelper.getSession().setAttribute("acceptedRaiseName", reviewListBean.getEqraise());
            JSFHelper.getSession().setAttribute("acceptedRaiseName", reviewListBean.getEqraise());
        }
    
    public void setJobFromMin(boolean jobFromMin) {
        this.jobFromMin = jobFromMin;
    }

    public boolean isJobFromMin() {
        return jobFromMin;
    }
    public void showJobDivCertified() {
        String jobCode=finalJobDTO.getCode().getKey();
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
        try {
            jobFilter.cancelSearch();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        jobFilter.setResetMode(false);
        jobFilter.setLoadSecAccessibleJobsOnly(true);
        
       
       initJobFilter1();
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
        jobFilter.setOkButtonMethod("contractEmployeesBean.jobForMinValueApprove");
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
