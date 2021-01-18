package com.beshara.csc.hr.emp.presentation.backingbean.employee.shared;


import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.crs.business.client.CrsClientFactory;
import com.beshara.csc.hr.crs.business.dto.CrsDTOFactory;
import com.beshara.csc.hr.crs.business.dto.IAcceptCandidateDTO;
import com.beshara.csc.hr.crs.business.dto.ICandidatePersonsDTO;
import com.beshara.csc.hr.emp.business.client.EmpCandidatesClientImpl;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExtraDataTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.business.shared.exception.HireDateRequiredException;
import com.beshara.csc.hr.emp.presentation.backingbean.employee.EmployeeListBean;
import com.beshara.csc.hr.emp.presentation.backingbean.employment.shared.ReviewListBean;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NotMatchedOnHireTypeException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.emp.NotMatchedOnJobGRSConditionException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.BaseBean;

import java.sql.Date;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class BussinessService1 {

    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    private static final String PAGE_URI = "/module/jsps/employee/shared/printList.jsf";
    public BussinessService1() {
        super();
    }

    public String rejectJobNameAction() {
        System.out.println("rejectJobNameAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean1");
        Long selectedMinistry = Long.parseLong(reviewListBean.getSelectedMinistery());
        //((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStatusKey(reviewListBean.getManagedConstantsBean().getH);
        ((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT() +
                                                                     "");
        IEmployeesDTO suppliedEmployee = (IEmployeesDTO)reviewListBean.getPageDTO();
        if (suppliedEmployee.getSalEmpSalaryElementsDTOList() != null &&
            suppliedEmployee.getSalEmpSalaryElementsDTOList().size() > 0) {
            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO =
                suppliedEmployee.getSalEmpSalaryElementsDTOList().get(0);
            salEmpSalaryElementsDTO.setSalEqElementGuidesDTO(null);
        }
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(suppliedEmployee.getHireDate());
            EmpClientFactory.getEmployeesCMTClient(selectedMinistry).updateEmpCMT(suppliedEmployee);

        } catch (NotMatchedOnHireTypeException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (NotMatchedOnJobGRSConditionException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (DataBaseException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (SharedApplicationException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (Exception e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                     "failure_Msg_value");
            return reviewListBean.getBckBtnNavigationCase();
        } finally {
            if (!"".equals(reviewListBean.getBackActionMethodName()))
                reviewListBean.executeMethodBinding(reviewListBean.getBackActionMethodName(), null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();
    }

    // اعتماد الوظي�?ة والدرجه للمرشح

    public String approveOrderAction() {
        System.out.println("approveOrderAction");
        ReviewListBean1 reviewListBean = (ReviewListBean1)jsfHelper("reviewMinListBean1");

        //((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(IEMPConstants.HIRE_STAGE_COMPLETING_INFO_INPROGRESS);

        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        IEmpCandidatesDTO updatedEmp;
        if (reviewListBean.getAcceptedJobValue() != null && !reviewListBean.getAcceptedJobValue().equals("")) {
            try {
                IJobsEntityKey entitKey = JobEntityKeyFactory.createJobsEntityKey(reviewListBean.getAcceptedJobCode());
                IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                suppliedEmployee.setJobsDTO(jobDTO);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (reviewListBean.getBgtTypesDTO() != null && !reviewListBean.getBgtTypesDTO().equals("")) {
            suppliedEmployee.setBgtTypesDTO(reviewListBean.getBgtTypesDTO());
            suppliedEmployee.setBgtTypeKey(reviewListBean.getBgtTypesDTO().getCode().getKey());
        }
        if (reviewListBean.getPageDTO() != null && !reviewListBean.getPageDTO().equals("")) {
            suppliedEmployee.setAcceptedRaiseCode(reviewListBean.getAcceptedRaiseCode());
        }
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        if (reviewListBean.isJobFromMin()) {
            if (reviewListBean.getJobKey1() == null || reviewListBean.getJobKey1().trim().equals("")) {
                String msg =
                    baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                            "job_required_for_can_approve");
                baseBean.getSharedUtil().setErrMsgValue(msg);
                return "";
            }
        }
        try {

            IEmpCandidatesClient client = new EmpCandidatesClientImpl();


            updatedEmp = (IEmpCandidatesDTO)client.applyCRSSuggestion(suppliedEmployee);
            reviewListBean.setPageDTO(updatedEmp);
            //            updatedEmp =
            //                    (IEmpCandidatesDTO)EmpClientFactory.getEmpCandidatesClient().adoptionOfFinal(suppliedEmployee);

        } catch (HireDateRequiredException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                     "hireDateRequired");
            return "";
        } catch (DataBaseException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (SharedApplicationException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (Exception e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                     "failure_Msg_value");
            return reviewListBean.getBckBtnNavigationCase();
        } finally {
            if (!"".equals(reviewListBean.getBackActionMethodName()))
                reviewListBean.executeMethodBinding(reviewListBean.getBackActionMethodName(), null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg = "";
        if (updatedEmp.getHireStageKey().equals(IEMPConstants.HIRE_STAGE_RELEASE_DECISION_INPROGRESS)) {
            msg =
    baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "pleaseReviewTheDataCandidate");
        }
        if (updatedEmp.getHireStageKey().equals(IEMPConstants.HIRE_STAGE_COMPLETING_INFO_INPROGRESS)) {
            msg =
    baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "pleaseCompleteTheDataCandidate");

        }
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        if(updatedEmp!=null){
        //IEmpCandidatesDTO empcandDTO = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        IEmpCandidatesDTO empcandDTO = updatedEmp;
        EmployeeListBean empListBean = (EmployeeListBean)jsfHelper("empListBean");
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
        
        return reviewListBean.getBckBtnNavigationCase();


    }

    // TODO ص�?حات جهة عمل مرشح

    public String moveRequestToDiwan() {

        System.out.println("moveRequestToDiwan");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean1");
        ICandidatePersonsDTO candidatePersonsDTO = (ICandidatePersonsDTO)reviewListBean.getSaveStateList().get(1);
        IAcceptCandidateDTO acceptCandidateDTO = CrsDTOFactory.createAcceptCandidateDTO();
        reviewListBean.calculateNextDateOfRaise();
        Date startWorkingDate = ((IEmployeesDTO)reviewListBean.getPageDTO()).getHireDate();
        ((IEmployeesDTO)reviewListBean.getPageDTO()).setStartWorkingDate(startWorkingDate);
        acceptCandidateDTO.setEmployeesDTO(reviewListBean.getPageDTO());
        acceptCandidateDTO.setCandidatePersonsDTO(candidatePersonsDTO);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            CrsClientFactory.getCandidatePersonsCMTClient().acceptCandidatePersons(acceptCandidateDTO,
                                                                                   CSCSecurityInfoHelper.getLoggedInMinistryCode());
            EmpClientFactory.getEmployeesClient().refresh();
        } catch (NotMatchedOnHireTypeException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (NotMatchedOnJobGRSConditionException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (DataBaseException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (SharedApplicationException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (Exception e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                     "failure_Msg_value");
            return reviewListBean.getBckBtnNavigationCase();
        } finally {
            if (!"".equals(reviewListBean.getBackActionMethodName()))
                reviewListBean.executeMethodBinding(reviewListBean.getBackActionMethodName(), null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();

    }

    private Object jsfHelper(String experssionName) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return app.createValueBinding("#{" + experssionName + "}").getValue(ctx);

    }


    public String replyRequestForUpdateDataAction() {
        System.out.println("moveRequestToDiwan");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean1");
        Long selectedMinistry = Long.parseLong(reviewListBean.getSelectedMinistery());
        //((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStatusKey(reviewListBean.getManagedConstantsBean().getH);
        ((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_REJECTED_BY_DEWAN() +
                                                                     "");
        IEmployeesDTO suppliedEmployee = (IEmployeesDTO)reviewListBean.getPageDTO();
        if (suppliedEmployee.getSalEmpSalaryElementsDTOList() != null &&
            suppliedEmployee.getSalEmpSalaryElementsDTOList().size() > 0) {
            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO =
                (ISalEmpSalaryElementsDTO)suppliedEmployee.getSalEmpSalaryElementsDTOList().get(0);
            salEmpSalaryElementsDTO.setSalEqElementGuidesDTO(null);
        }
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(suppliedEmployee.getHireDate());
            EmpClientFactory.getEmployeesCMTClient(selectedMinistry).updateEmpCMT(suppliedEmployee);

        } catch (NotMatchedOnHireTypeException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (NotMatchedOnJobGRSConditionException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (DataBaseException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (SharedApplicationException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (Exception e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                     "failure_Msg_value");
            return reviewListBean.getBckBtnNavigationCase();
        } finally {
            if (!"".equals(reviewListBean.getBackActionMethodName()))
                reviewListBean.executeMethodBinding(reviewListBean.getBackActionMethodName(), null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();

    }


    // TODO ص�?حات إدارة ال�?توى والرأى

    public String approveSalaryDegreeAction() {
        System.out.println("approveSalaryDegreeAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean1");
        Long selectedMinistry = Long.parseLong(reviewListBean.getSelectedMinistery());
        //((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStatusKey(reviewListBean.getManagedConstantsBean().getH);
        ((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED() +
                                                                     "");
        IEmployeesDTO suppliedEmployee = (IEmployeesDTO)reviewListBean.getPageDTO();
        if (suppliedEmployee.getSalEmpSalaryElementsDTOList() != null &&
            suppliedEmployee.getSalEmpSalaryElementsDTOList().size() > 0) {
            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO =
                (ISalEmpSalaryElementsDTO)suppliedEmployee.getSalEmpSalaryElementsDTOList().get(0);
            salEmpSalaryElementsDTO.setSalEqElementGuidesDTO(null);
        }
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(suppliedEmployee.getHireDate());
            EmpClientFactory.getEmployeesCMTClient(selectedMinistry).updateEmpCMT(suppliedEmployee);

        } catch (NotMatchedOnHireTypeException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (NotMatchedOnJobGRSConditionException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (DataBaseException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (SharedApplicationException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (Exception e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                     "failure_Msg_value");
            return reviewListBean.getBckBtnNavigationCase();
        } finally {
            if (!"".equals(reviewListBean.getBackActionMethodName()))
                reviewListBean.executeMethodBinding(reviewListBean.getBackActionMethodName(), null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();

    }

    public String replyRequestForUpdateDataForFatwaDeptAction() {

        System.out.println("replyRequestForUpdateDataForFatwaDeptAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean1");
        Long selectedMinistry = Long.parseLong(reviewListBean.getSelectedMinistery());
        //((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStatusKey(reviewListBean.getManagedConstantsBean().getH);
        ((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_REJECTED_BY_FATWA() +
                                                                     "");
        IEmployeesDTO suppliedEmployee = (IEmployeesDTO)reviewListBean.getPageDTO();
        if (suppliedEmployee.getSalEmpSalaryElementsDTOList() != null &&
            suppliedEmployee.getSalEmpSalaryElementsDTOList().size() > 0) {
            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO =
                (ISalEmpSalaryElementsDTO)suppliedEmployee.getSalEmpSalaryElementsDTOList().get(0);
            salEmpSalaryElementsDTO.setSalEqElementGuidesDTO(null);
        }
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(suppliedEmployee.getHireDate());
            EmpClientFactory.getEmployeesCMTClient(selectedMinistry).updateEmpCMT(suppliedEmployee);

        } catch (NotMatchedOnHireTypeException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (NotMatchedOnJobGRSConditionException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (DataBaseException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (SharedApplicationException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (Exception e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                     "failure_Msg_value");
            return reviewListBean.getBckBtnNavigationCase();
        } finally {
            if (!"".equals(reviewListBean.getBackActionMethodName()))
                reviewListBean.executeMethodBinding(reviewListBean.getBackActionMethodName(), null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();
    }


    // TODO بيانات جارى استكمالها او تم ر�?ضها لسبب معين

    public String transferForJobArrangmentAction() {

        System.out.println("transferForJobArrangmentAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean1");
        Long selectedMinistry = Long.parseLong(reviewListBean.getSelectedMinistery());
        //((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStatusKey(reviewListBean.getManagedConstantsBean().getEmpHireStageDefault().toString());
        ((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_REQUIRED() +
                                                                     "");
        IEmployeesDTO suppliedEmployee = (IEmployeesDTO)reviewListBean.getPageDTO();
        if (suppliedEmployee.getSalEmpSalaryElementsDTOList() != null &&
            suppliedEmployee.getSalEmpSalaryElementsDTOList().size() > 0) {
            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO =
                (ISalEmpSalaryElementsDTO)suppliedEmployee.getSalEmpSalaryElementsDTOList().get(0);
            salEmpSalaryElementsDTO.setSalEqElementGuidesDTO(null);
        }
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(suppliedEmployee.getHireDate());
            EmpClientFactory.getEmployeesCMTClient(selectedMinistry).updateEmpCMT(suppliedEmployee);
        } catch (NotMatchedOnHireTypeException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (NotMatchedOnJobGRSConditionException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (DataBaseException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (SharedApplicationException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (Exception e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                     "failure_Msg_value");
            return reviewListBean.getBckBtnNavigationCase();
        } finally {
            if (!"".equals(reviewListBean.getBackActionMethodName()))
                reviewListBean.executeMethodBinding(reviewListBean.getBackActionMethodName(), null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();
    }

    public String transferForFatwaDeptAction() {
        return "";
    }

    public String transferForCandidateMinistry() {
        return "";
    }


    // TODO تم استي�?اء المسمى الوظي�?ه ومطلوب استي�?اء شرط الدرجة او تم استي�?اء شرط الدرجة

    public String transeferForFatwaDeptAction2() {

        System.out.println("transeferForFatwaDeptAction2");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean1");
        Long selectedMinistry = Long.parseLong(reviewListBean.getSelectedMinistery());

        //((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStatusKey(reviewListBean.getManagedConstantsBean().getEmpHireStageDefault().toString());

        ((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_FIN_DEGREE_REQUIRED().toString() +
                                                                     "");
        IEmployeesDTO suppliedEmployee = (IEmployeesDTO)reviewListBean.getPageDTO();
        if (suppliedEmployee.getSalEmpSalaryElementsDTOList() != null &&
            suppliedEmployee.getSalEmpSalaryElementsDTOList().size() > 0) {
            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO =
                (ISalEmpSalaryElementsDTO)suppliedEmployee.getSalEmpSalaryElementsDTOList().get(0);
            salEmpSalaryElementsDTO.setSalEqElementGuidesDTO(null);
        }
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(suppliedEmployee.getHireDate());
            EmpClientFactory.getEmployeesCMTClient(selectedMinistry).updateEmpCMT(suppliedEmployee);
        } catch (NotMatchedOnHireTypeException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (NotMatchedOnJobGRSConditionException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (DataBaseException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (SharedApplicationException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (Exception e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                     "failure_Msg_value");
            return reviewListBean.getBckBtnNavigationCase();
        } finally {
            if (!"".equals(reviewListBean.getBackActionMethodName()))
                reviewListBean.executeMethodBinding(reviewListBean.getBackActionMethodName(), null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();
    }

    public String approveRequestAction() {

        System.out.println("approveRequestAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean1");
        Long selectedMinistry = Long.parseLong(reviewListBean.getSelectedMinistery());

        //((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStatusKey(reviewListBean.getManagedConstantsBean().getEmpHireStageDefault().toString());
        ((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStageKey(IEMPConstant.EMP_STAGE_DEFAULT.toString() + "");
        IEmployeesDTO suppliedEmployee = (IEmployeesDTO)reviewListBean.getPageDTO();
        if (suppliedEmployee.getSalEmpSalaryElementsDTOList() != null &&
            suppliedEmployee.getSalEmpSalaryElementsDTOList().size() > 0) {
            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO =
                (ISalEmpSalaryElementsDTO)suppliedEmployee.getSalEmpSalaryElementsDTOList().get(0);
            salEmpSalaryElementsDTO.setSalEqElementGuidesDTO(null);
        }
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(suppliedEmployee.getHireDate());
            EmpClientFactory.getEmployeesCMTClient().updateEmpCMT(suppliedEmployee);
        } catch (NotMatchedOnHireTypeException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (NotMatchedOnJobGRSConditionException e) {
            baseBean.setShowErrorMsg(true);
            if (e.getExtraInformation() != null) {
                reviewListBean.setLinesResultList(e.getExtraInformation());
                baseBean.getSharedUtil().setSuccessMsgValue(null);
                baseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);document.getElementById('cancelLinesDivStatusDiv').focus();");
                return "";
            }
        } catch (DataBaseException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (SharedApplicationException e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e);
            return reviewListBean.getBckBtnNavigationCase();
        } catch (Exception e) {
            baseBean.setShowErrorMsg(true);
            baseBean.getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                                     "failure_Msg_value");
            return reviewListBean.getBckBtnNavigationCase();
        } finally {
            if (!"".equals(reviewListBean.getBackActionMethodName()))
                reviewListBean.executeMethodBinding(reviewListBean.getBackActionMethodName(), null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();
    }
    // تحويل الى ادارة الاختيار

    // first step in cycle

    public String hireStageCompletingJobNameAction() {

        System.out.println("hireStageCompletingJobNameAction");
        ReviewListBean1 reviewListBean = (ReviewListBean1)jsfHelper("reviewMinListBean1");
        List<IEmpCandidateExtraDataDTO> empCandidateExtraDataList = null;

        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        empCandidateExtraDataList = (List)suppliedEmployee.getEmpCandidateExtraDataList();
        if (empCandidateExtraDataList == null) {
            empCandidateExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
        }
        boolean raiseExist = false;
        if (empCandidateExtraDataList != null && empCandidateExtraDataList.size() > 0) {
            for (IEmpCandidateExtraDataDTO empCandidateExtraDataDTO : empCandidateExtraDataList) {
                if (empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(12L))) {
                    empCandidateExtraDataDTO.setValue(reviewListBean.getSuggestedRaiseCode());
                    empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
                    raiseExist = true;
                    break;
                }
            }
        }


        if (!raiseExist && reviewListBean.getSuggestedRaiseCode() != null) {
            if (empCandidateExtraDataList == null) {
                empCandidateExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
            }
            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
            IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
            empExtraDataTypesDTO.setCode(EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(12L));
            empCandidateExtraDataDTO.setValue(reviewListBean.getSuggestedRaiseCode());
            empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
            empCandidateExtraDataDTO.setEmpCandidatesDTO(suppliedEmployee);
            empCandidateExtraDataList.add(empCandidateExtraDataDTO);
            suppliedEmployee.setEmpCandidateExtraDataList((List)empCandidateExtraDataList);
        }
        if (suppliedEmployee.getJobsDTO() != null) {
            boolean jobExist = false;
            if (empCandidateExtraDataList != null && empCandidateExtraDataList.size() > 0) {
                for (IEmpCandidateExtraDataDTO empCandidateExtraDataDTO : empCandidateExtraDataList) {
                    if (empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(4L))) {
                        empCandidateExtraDataDTO.setValue(suppliedEmployee.getJobsDTO().getCode().getKey());
                        empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
                        jobExist = true;
                        break;
                    }
                }
            }
            if (!jobExist) {
                if (empCandidateExtraDataList == null) {
                    empCandidateExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
                }
                IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
                IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
                empExtraDataTypesDTO.setCode(EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(4L));
                empCandidateExtraDataDTO.setValue(suppliedEmployee.getJobsDTO().getCode().getKey());
                empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
                empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
                empCandidateExtraDataDTO.setEmpCandidatesDTO(suppliedEmployee);
                empCandidateExtraDataList.add(empCandidateExtraDataDTO);
                suppliedEmployee.setEmpCandidateExtraDataList((List)empCandidateExtraDataList);
            }
        }


        boolean noteExist = false;
        if (empCandidateExtraDataList != null && empCandidateExtraDataList.size() > 0) {
            for (IEmpCandidateExtraDataDTO empCandidateExtraDataDTO : empCandidateExtraDataList) {
                if (empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(5L))) {
                    empCandidateExtraDataDTO.setValue(suppliedEmployee.getEmpExtraDataValueDTO().getMinistryNotes());
                    empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
                    noteExist = true;
                    break;
                }
            }
        }
        if (!noteExist && !suppliedEmployee.getEmpExtraDataValueDTO().getMinistryNotes().equals("")) {
            if (empCandidateExtraDataList == null) {
                empCandidateExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
            }
            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
            IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
            empExtraDataTypesDTO.setCode(EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(5L));
            empCandidateExtraDataDTO.setValue(suppliedEmployee.getEmpExtraDataValueDTO().getMinistryNotes());
            empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
            empCandidateExtraDataDTO.setEmpCandidatesDTO(suppliedEmployee);
            empCandidateExtraDataList.add(empCandidateExtraDataDTO);
            
       
        }
        
        IEmpCandidateExtraDataDTO empCandidateExtraDataDTO1 = EmpDTOFactory.createEmpCandidateExtraDataDTO();
        IEmpExtraDataTypesDTO empExtraDataTypesDTO1 = EmpDTOFactory.createEmpExtraDataTypesDTO();
        empExtraDataTypesDTO1.setCode(EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(326L));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            java.util.Date date = new java.util.Date();  
        empCandidateExtraDataDTO1.setValue(formatter.format(date));
        empCandidateExtraDataDTO1.setStatusFlag(ISystemConstant.ADDED_ITEM);
        empCandidateExtraDataDTO1.setEmpExtraDataTypesDTO(empExtraDataTypesDTO1);
        empCandidateExtraDataDTO1.setEmpCandidatesDTO(suppliedEmployee);
        empCandidateExtraDataList.add(empCandidateExtraDataDTO1);
        suppliedEmployee.setEmpCandidateExtraDataList((List)empCandidateExtraDataList);

        IEmpCandidatesDTO dto = (IEmpCandidatesDTO)reviewListBean.getPageDTO();


        IEmpCandidatesClient client = new EmpCandidatesClientImpl();
        IEmpCandidatesDTO employeeCandidate=null;
        
        try {
          employeeCandidate = (IEmpCandidatesDTO)client.proceedCRSPersonData(dto, empCandidateExtraDataList);
         
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }

        
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

    //     IEmpCandidatesDTO empcandDTO = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        
        if(employeeCandidate!=null){
        IEmpCandidatesDTO empcandDTO   =  employeeCandidate ;
        EmployeeListBean empListBean = (EmployeeListBean)jsfHelper("empListBean");
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

        return reviewListBean.getBckBtnNavigationCase();

    }


    // step 2 hire sytage 12

    public void hireStageCompletingAddNewJobNameActionDiv(){
            ReviewListBean1 reviewListBean = (ReviewListBean1)jsfHelper("reviewMinListBean1");
            reviewListBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv2);");
        }
    public String hireStageCompletingAddNewJobNameAction() {

        return hireStageCompletingJobNameAction();
    }
    
    public void printAction(){
            try {
            PrintListBean printListBean = (PrintListBean)jsfHelper("printListBean");
            ReviewListBean1 reviewListBean = (ReviewListBean1)jsfHelper("reviewMinListBean1");
            IEmpCandidatesDTO selected = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
           printListBean.constructConditionDetailsPagePath(((IEmpCandidatesEntityKey)selected.getCode()).getCandidateCode().toString(), PAGE_URI);
           reviewListBean.setJavaScriptCaller("openEmpRequestDetailsWindow();");
            JSFHelper.getSession().setAttribute("printPageDTO", reviewListBean.getPageDTO());
            JSFHelper.getSession().setAttribute("workCenterName", reviewListBean.getWorkCenterName());
            JSFHelper.getSession().setAttribute("suggestedJobValue", reviewListBean.getSuggestedJobValue());
            JSFHelper.getSession().setAttribute("suggestedCaderName", reviewListBean.getSuggestedCaderName());
                String   suggestedGroupName ="";
                if(reviewListBean.getSuggestedGroupCode() !=null){
                ISalElementGuidesDTO groupDTO = (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedGroupCode());
                   suggestedGroupName =groupDTO.getName();
                }
            JSFHelper.getSession().setAttribute("suggestedGroupName", suggestedGroupName);
                String   suggestedDegreeName="";
                if(reviewListBean.getSuggestedDegreeCode() !=null){
                ISalElementGuidesDTO degreeDTO = (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedDegreeCode());
                   suggestedDegreeName=degreeDTO.getName();
                }
            JSFHelper.getSession().setAttribute("suggestedDegreeName",suggestedDegreeName);
                String   suggestedRaiseName= "";
                if(reviewListBean.getSuggestedRaiseCode() !=null && !reviewListBean.getSuggestedRaiseCode().equals("")){
                ISalElementGuidesDTO raiseDTO = (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.valueOf(reviewListBean.getSuggestedRaiseCode()));
                   suggestedRaiseName=raiseDTO.getCountGuide().toString();
                }
            JSFHelper.getSession().setAttribute("suggestedRaiseName", suggestedRaiseName);
            JSFHelper.getSession().setAttribute("acceptedJobValue", reviewListBean.getAcceptedJobValue());
            JSFHelper.getSession().setAttribute("bgtTypesDTO", reviewListBean.getBgtTypesDTO());
            JSFHelper.getSession().setAttribute("acceptedCaderName", reviewListBean.getAcceptedCaderName());
            JSFHelper.getSession().setAttribute("acceptedGroupName", reviewListBean.getAcceptedGroupName());
            JSFHelper.getSession().setAttribute("acceptedDegreeName", reviewListBean.getAcceptedDegreeName());
            JSFHelper.getSession().setAttribute("acceptedRaiseName", reviewListBean.getAcceptedRaiseName());
            
       
                
            
            
            
            
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
        }
    
    public String reviewPageList(){
        
        return "review";
        }
    
   


}
