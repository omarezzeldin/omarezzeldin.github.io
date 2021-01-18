package com.beshara.csc.hr.emp.presentation.backingbean.employment.shared;

import com.beshara.base.entity.IEntityKey;
import com.beshara.common.presentation.bean.SharedUtilsBean;
import com.beshara.common.shared.SharedUtils;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.crs.business.client.CrsClientFactory;
import com.beshara.csc.hr.crs.business.dto.CrsDTOFactory;
import com.beshara.csc.hr.crs.business.dto.IAcceptCandidateDTO;
import com.beshara.csc.hr.crs.business.dto.ICandidatePersonsDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExtraDataTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.business.shared.exception.BeenAccreditationPleaseCompleteTheDataCandidate;
import com.beshara.csc.hr.emp.business.shared.exception.BeenAccreditationPleaseReviewTheDataCandidate;
import com.beshara.csc.hr.emp.business.shared.exception.HireDateRequiredException;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
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

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class BussinessService {


    public BussinessService() {
    }


    public String rejectJobNameAction() {
        System.out.println("rejectJobNameAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean");
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

    // اعتماد الوظيفة والدرجه للمرشح

    public String approveOrderAction() {
        System.out.println("approveOrderAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean");
        //Long selectedMinistry = Long.parseLong(reviewListBean.getSelectedMinistery());
        //((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStatusKey(reviewListBean.getManagedConstantsBean().getH);
        // todo if methodin islam task complete set stageCode 2 else 1
        
        //          if( empclient.checkEmployeeRequiredProceduresStatus(serial) && empclient.checkEmployeeRequiredDocumentsStatus(serial)){
        //                ((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStageKey(IEMPConstants.HIRE_STAGE_RELEASE_DECISION_INPROGRESS);
        //          }else{
        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(IEMPConstants.HIRE_STAGE_COMPLETING_INFO_INPROGRESS);
        //          }
        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        IEmpCandidatesDTO updatedEmp ;
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
//            ISalElementGuidesDTO salElmntGuideDTO = SalDTOFactory.createSalElementGuidesDTO();
//            salElmntGuideDTO.setCode(SalEntityKeyFactory.createSalElementGuidesEntityKey(reviewListBean.getAcceptedRaiseCode()));
//            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = SalDTOFactory.createSalEmpSalaryElementsDTO();
//            Long civlId = ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).getCivilId();
//            try {
//                salEmpSalaryElementsDTO.setCode(SalEntityKeyFactory.createSalEmpSalaryElementsEntityKey(civlId,
//                                                                                                        reviewListBean.getAcceptedRaiseCode(),
//                                                                                                        SharedUtils.getSqlDate(),
//                                                                                                        IEMPConstants._SALARY_ELEMENT_SERIAL));
//                salEmpSalaryElementsDTO.setFromDate(SharedUtils.getSqlDate());
//                salEmpSalaryElementsDTO.setElementRatio(IEMPConstants._SALARY_ELEMENT_RATIO);
//                salEmpSalaryElementsDTO.setEmpCivilId(civlId);
//                salEmpSalaryElementsDTO.setReqSerial(IEMPConstants._SALARY_ELEMENT_SERIAL);
//                salEmpSalaryElementsDTO.setStatus(1L);
//                salEmpSalaryElementsDTO.setSalElementGuidesDTO(salElmntGuideDTO);
//                SalClientFactory.getSalEmpSalaryElementsClient().add(salEmpSalaryElementsDTO);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }
        

        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
//            suppliedEmployee.setStartWorkingDate(suppliedEmployee.getHireDate());
            updatedEmp = (IEmpCandidatesDTO)EmpClientFactory.getEmpCandidatesClient().adoptionOfFinal(suppliedEmployee);

        }  catch (HireDateRequiredException e) {
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
        if(updatedEmp.getHireStageKey().equals(IEMPConstants.HIRE_STAGE_RELEASE_DECISION_INPROGRESS)){
           msg = baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "pleaseReviewTheDataCandidate");
            }
        if(updatedEmp.getHireStageKey().equals(IEMPConstants.HIRE_STAGE_COMPLETING_INFO_INPROGRESS)){
          msg =  baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "pleaseCompleteTheDataCandidate");
        
        }
        baseBean.getSharedUtil().setSuccessMsgValue(msg);
        return reviewListBean.getBckBtnNavigationCase();


    }

    // TODO صفحات جهة عمل مرشح

    public String moveRequestToDiwan() {

        System.out.println("moveRequestToDiwan");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean");
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
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean");
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


    // TODO صفحات إدارة الفتوى والرأى

    public String approveSalaryDegreeAction() {
        System.out.println("approveSalaryDegreeAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean");
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
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean");
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


    // TODO بيانات جارى استكمالها او تم رفضها لسبب معين

    public String transferForJobArrangmentAction() {

        System.out.println("transferForJobArrangmentAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean");
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


    // TODO تم استيفاء المسمى الوظيفه ومطلوب استيفاء شرط الدرجة او تم استيفاء شرط الدرجة

    public String transeferForFatwaDeptAction2() {

        System.out.println("transeferForFatwaDeptAction2");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean");
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
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean");
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

    public String hireStageCompletingJobNameAction() {

        System.out.println("hireStageCompletingJobNameAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean");
        List<IEmpCandidateExtraDataDTO> empCandidateExtraDataList = null;
        // ToDo add minCode
        // Long selectedMinistry = Long.parseLong(reviewListBean.getSelectedMinistery());
        //Long selectedMinistry = 13L;
        //((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStatusKey(reviewListBean.getManagedConstantsBean().getEmpHireStageDefault().toString());

        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(IEMPConstants.HIRE_STAGE_REVIEW_ORDER_IN_SELECTION_INPROGRESS);
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
        if (!raiseExist) {
            IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
            IEntityKey key = null;
            key = EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(12L);
            empExtraDataTypesDTO.setCode(key);
            empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
            empCandidateExtraDataDTO.setValue(reviewListBean.getSuggestedRaiseCode());
            empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            empCandidateExtraDataDTO.setEmpCandidatesDTO((IEmpCandidatesDTO)reviewListBean.getPageDTO());
            empCandidateExtraDataList.add(empCandidateExtraDataDTO);
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
                IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
                IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
                IEntityKey key = null;
                key = EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(4L);
                empExtraDataTypesDTO.setCode(key);
                empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
                empCandidateExtraDataDTO.setValue(suppliedEmployee.getJobsDTO().getCode().getKey());
                empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
                empCandidateExtraDataDTO.setEmpCandidatesDTO((IEmpCandidatesDTO)reviewListBean.getPageDTO());
                empCandidateExtraDataList.add(empCandidateExtraDataDTO);
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
        if (!noteExist && suppliedEmployee.getEmpExtraDataValueDTO().getMinistryNotes()!=null && !suppliedEmployee.getEmpExtraDataValueDTO().getMinistryNotes().isEmpty()) {
            IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();

            IEntityKey key = null;
            key = EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN);
            empExtraDataTypesDTO.setCode(key);
            empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
            empCandidateExtraDataDTO.setValue(suppliedEmployee.getEmpExtraDataValueDTO().getMinistryNotes());
            empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            empCandidateExtraDataDTO.setEmpCandidatesDTO((IEmpCandidatesDTO)reviewListBean.getPageDTO());
            empCandidateExtraDataList.add(empCandidateExtraDataDTO);
        }
        suppliedEmployee.setEmpCandidateExtraDataList((List)empCandidateExtraDataList);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setJobCode(null);
            suppliedEmployee.setJobsDTO(null);
            EmpClientFactory.getEmpCandidatesClient().updateEmp(suppliedEmployee);
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



    public String hireStageCompletingAddNewJobNameAction() {

        System.out.println("hireStageCompletingAddNewJobNameAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("reviewMinListBean");
        List<IEmpCandidateExtraDataDTO> empCandidateExtraDataList = null;
        // ToDo add minCode
        // Long selectedMinistry = Long.parseLong(reviewListBean.getSelectedMinistery());
        //Long selectedMinistry = 13L;
        //((IEmployeesDTO)reviewListBean.getPageDTO()).setHireStatusKey(reviewListBean.getManagedConstantsBean().getEmpHireStageDefault().toString());

//        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(IEMPConstants.HIRE_STAGE_REVIEW_ORDER_IN_SELECTION_INPROGRESS);
        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        empCandidateExtraDataList = (List)suppliedEmployee.getEmpCandidateExtraDataList();
        if (empCandidateExtraDataList == null) {
            empCandidateExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
        }
//        if (empCandidateExtraDataList != null && empCandidateExtraDataList.size() > 0) {
//            for (IEmpCandidateExtraDataDTO empCandidateExtraDataDTO : empCandidateExtraDataList) {
//                if (empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(13L))||
//                    empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(15L))||
//                empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(14L))||
//                empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(6L))||
//                empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(7L))||
//                empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(8L))) {
//                    empCandidateExtraDataDTO.setValue(null);
//        
//                }
//            }
//        }
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
        if (!raiseExist) {
            IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
            IEntityKey key = null;
            key = EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(12L);
            empExtraDataTypesDTO.setCode(key);
            empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
            empCandidateExtraDataDTO.setValue(reviewListBean.getSuggestedRaiseCode());
            empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            empCandidateExtraDataDTO.setEmpCandidatesDTO((IEmpCandidatesDTO)reviewListBean.getPageDTO());
            empCandidateExtraDataList.add(empCandidateExtraDataDTO);
        }

        if (reviewListBean.getSuggestedJobValue() != null) {
            boolean jobExist = false;
            if (empCandidateExtraDataList != null && empCandidateExtraDataList.size() > 0) {
                for (IEmpCandidateExtraDataDTO empCandidateExtraDataDTO : empCandidateExtraDataList) {
                    if (empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(4L))) {
                        empCandidateExtraDataDTO.setValue(reviewListBean.getSuggestedJobValue());
                        empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
                        jobExist = true;
                        break;
                    }
                }
            }
            if (!jobExist) {
                IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
                IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
                IEntityKey key = null;
                key = EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(4L);
                empExtraDataTypesDTO.setCode(key);
                empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
                empCandidateExtraDataDTO.setValue(suppliedEmployee.getJobsDTO().getCode().getKey());
                empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
                empCandidateExtraDataDTO.setEmpCandidatesDTO((IEmpCandidatesDTO)reviewListBean.getPageDTO());
                empCandidateExtraDataList.add(empCandidateExtraDataDTO);
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
        if (!noteExist && suppliedEmployee.getEmpExtraDataValueDTO().getMinistryNotes()!=null && !suppliedEmployee.getEmpExtraDataValueDTO().getMinistryNotes().isEmpty()) {
            IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();

            IEntityKey key = null;
            key = EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN);
            empExtraDataTypesDTO.setCode(key);
            empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
            empCandidateExtraDataDTO.setValue(suppliedEmployee.getEmpExtraDataValueDTO().getMinistryNotes());
            empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            empCandidateExtraDataDTO.setEmpCandidatesDTO((IEmpCandidatesDTO)reviewListBean.getPageDTO());
            empCandidateExtraDataList.add(empCandidateExtraDataDTO);
        }
       
        suppliedEmployee.setEmpCandidateExtraDataList((List)empCandidateExtraDataList);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setActiveFlag(0L);
            EmpClientFactory.getEmpCandidatesClient().addNewSeqForCandidate(suppliedEmployee);
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


}
