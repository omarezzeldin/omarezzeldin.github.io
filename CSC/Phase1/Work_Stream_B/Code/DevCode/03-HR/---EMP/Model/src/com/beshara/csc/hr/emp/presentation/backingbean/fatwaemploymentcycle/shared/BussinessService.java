
package com.beshara.csc.hr.emp.presentation.backingbean.fatwaemploymentcycle.shared;


import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExtraDataTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExtraDataValueDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.HireStagesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.fatwaemploymentcycle.ReviewListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NotMatchedOnHireTypeException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.emp.NotMatchedOnJobGRSConditionException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.BaseBean;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class BussinessService {

    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    /**
     * This Method Add Notes to ExtraDataDTO
     * @param employeesDTO
     * @param dataTypeCode  dataType Code For notes
     * @return updated employeesDTO
     * @auther Kareem Sayed
     * Updated BY Ahmed Kamal Change From EMP_EMPLOYEE TO EMP_CANDIDATES 02/09/2014
     */
    private IEmpCandidatesDTO addNotesExtraDataDto(IEmpCandidatesDTO employeesDTO, Long dataTypeCode) {
        List<IEmpCandidateExtraDataDTO> empCandExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
        empCandExtraDataList = (List)employeesDTO.getEmpCandidateExtraDataList();
        IEmpExtraDataValueDTO empExtraDataValueDTO = employeesDTO.getEmpExtraDataValueDTO();
        String notes = "";
        if (dataTypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
            notes = empExtraDataValueDTO.getSelectionDeptNotes();
        } else if (dataTypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
            notes = empExtraDataValueDTO.getFatwaDeptNotes();
        } else if (dataTypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
            notes = empExtraDataValueDTO.getArrangmentDeptNotes();
        }
        if (notes != null && !notes.trim().isEmpty()) {
            boolean exist = false;
            if (empCandExtraDataList != null) {
                for (IEmpCandidateExtraDataDTO empCandidateExtraDataDTO : empCandExtraDataList) {
                    if (empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(dataTypeCode))) {
                        empCandidateExtraDataDTO.setValue(notes);
                        empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
                        if (employeesDTO.getHireStagesDTO().getCode().getKey().equals(IEMPConstants.HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT)) {
                            PropertyResourceBundle p =
                                (PropertyResourceBundle)ResourceBundle.getBundle("com.beshara.csc.hr.emp.presentation.resources.emp");
                            String desc = p.getString("order_refused_from_ArrangmentManger");
                            empCandidateExtraDataDTO.setValueDesc(desc);
                        }
                        exist = true;
                        break;
                    }
                }
            }
            if (!exist) {
                if (empCandExtraDataList == null) {
                    empCandExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
                }
                IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
                IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
                empCandidateExtraDataDTO.setValue(notes);
                if (employeesDTO.getHireStagesDTO().getCode().getKey().equals(IEMPConstants.HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT)) {
                    PropertyResourceBundle p =
                        (PropertyResourceBundle)ResourceBundle.getBundle("com.beshara.csc.hr.emp.presentation.resources.emp");
                    String desc = p.getString("order_refused_from_ArrangmentManger");
                    empCandidateExtraDataDTO.setValueDesc(desc);
                }
                empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
                empExtraDataTypesDTO.setCode(EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(dataTypeCode));
                empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
                empCandidateExtraDataDTO.setEmpCandidatesDTO(employeesDTO);
                empCandExtraDataList.add(empCandidateExtraDataDTO);
                employeesDTO.setEmpCandidateExtraDataList((List)empCandExtraDataList);
            }
        }


        return employeesDTO;
    }

    /**
     * Methd add ExtraDataDto
     * @param employeesDTO
     * @param dataType code
     * @return updated employeesDTO
     * @auther Kareem Sayed
     * Updated BY Ahmed Kamal Change From EMP_EMPLOYEE TO EMP_CANDIDATES 02/09/2014
     */
    private IEmpCandidatesDTO addExtraData(IEmpCandidatesDTO employeesDTO, Long dataType) {
        List<IEmpCandidateExtraDataDTO> empCandExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
        employeesDTO.setShowerrorMsg(false);
        empCandExtraDataList = (List)employeesDTO.getEmpCandidateExtraDataList();
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("fatwaReviewListBean");
        String extraDataValue = "";
        String extraDataValueDesc = "";
        if (dataType.equals(IEMPConstants.EXTA_DATA_ACCEPTED_FIN_DEGREE)) {
            extraDataValue = reviewListBean.getRaiseCode();
            if(extraDataValue.equals("-100")){
                employeesDTO.setShowerrorMsg(true);
                return employeesDTO;
            }
            if (reviewListBean.getRaiseDTO() != null) {
                extraDataValueDesc = String.valueOf(reviewListBean.getRaiseDTO().getValue());
            }
        } else if (dataType.equals(IEMPConstants.EX_DATA_BGT_TYPE)) {
            extraDataValue = reviewListBean.getApprovedBgtTypeCode();
        } else if (dataType.equals(IEMPConstants.EX_DATA_JOB_ACCEPTED)) {
            extraDataValue = employeesDTO.getJobsDTO().getCode().getKey();
        }
        boolean exist = false;
        if (empCandExtraDataList != null) {
            for (IEmpCandidateExtraDataDTO empCandidateExtraDataDTO : empCandExtraDataList) {
                if (empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(dataType))) {
                    empCandidateExtraDataDTO.setValue(extraDataValue);
                    empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
                    empCandidateExtraDataDTO.setValueDesc(extraDataValueDesc);
                    exist = true;
                    break;
                }
            }
        }
        if (!exist) {
            if (empCandExtraDataList == null) {
                empCandExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
            }
            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
            IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
            empExtraDataTypesDTO.setCode(EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(dataType));
            empCandidateExtraDataDTO.setValue(extraDataValue);
            if (!extraDataValueDesc.equals("")) {
                empCandidateExtraDataDTO.setValueDesc(extraDataValueDesc);
            }
            empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
            empCandidateExtraDataDTO.setEmpCandidatesDTO(employeesDTO);
            empCandExtraDataList.add(empCandidateExtraDataDTO);
            employeesDTO.setEmpCandidateExtraDataList((List)empCandExtraDataList);
        }
        return employeesDTO;
    }

    private IEmpCandidatesDTO addBounsesToPageDTO(IEmpCandidatesDTO employeesDTO,
                                                  List<IEmpCandidateExtraDataDTO> empCandExtraDataList) {
        employeesDTO.getEmpCandidateExtraDataList().addAll(empCandExtraDataList);
        return employeesDTO;
    }

    /**
     * Fatwa Manager
     * From Hire Stage 9 (جارى مراجعة الطلب من الديوان)
     * this action convert hire stage to (جارى استي�?اء المسمى الوظي�?ى) number 11
     * @return to Table List
     * @Updated BY Kareem Sayed
     * Updated BY Ahmed Kamal Change From EMP_EMPLOYEE TO EMP_CANDIDATES 02/09/2014
     */
    public String transferForJobArrangmentAction() {
        System.out.println("transferForJobArrangmentAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("fatwaReviewListBean");
        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_REQUIRED() +
                                                                         "");
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        IEmpCandidatesDTO suppliedEmployee;
        suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT);
     
        try {
            suppliedEmployee.setStartWorkingDate(null);
            suppliedEmployee.setJobsDTO(null);
            suppliedEmployee.setJobCode(null);
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
                reviewListBean.executeMethodBinding("fatwaSelectionListBean.reloadDataForApproveListBean", null);
        }
        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);

        String msg = null;
        msg =
baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "transfer_request_to_order_min_msg");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();
    }
    
    
    
    public String forwardToJobArrangmentAction() {
        System.out.println("forwardToJobArrangmentAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("fatwaReviewListBean");
       
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        IEmpCandidatesDTO suppliedEmployee;
        suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
       
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT);
        IEmpExtraDataValueDTO dTO = suppliedEmployee.getEmpExtraDataValueDTO();
              if( dTO.getFatwaDeptNotes() ==null || dTO.getFatwaDeptNotes().trim().equals("")){
                    String  msg =
                      baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "note_mandatory");
                      baseBean.getSharedUtil().setErrMsgValue(msg);
                  return "";
                  }
        try {
            suppliedEmployee.setStartWorkingDate(null);
            suppliedEmployee.setJobsDTO(null);
            suppliedEmployee.setJobCode(null);
            ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_REQUIRED() +
                                                                             "");
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
                reviewListBean.executeMethodBinding("fatwaSelectionListBean.reloadDataForApproveListBean", null);
        }
        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);

        String msg = null;
        msg =
    baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "transfer_request_to_Job_min_msg");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();
    }

    /**
     * Fatwa Manager
     * From Hire Stage 10 with no Experiences (جارى إستي�?اء الدرجة المالية)
     * this action convert hire stage to (تم اعنماد الوظي�?ة والدرجة) number 15
     * this method accept job fin degree or set new
     * if not foun set statusFlag to Added latest
     * else set statusFlag to added_Item
     * @return to Table List
     * @Updated BY Kareem Sayed
     * Updated BY Ahmed Kamal Change From EMP_EMPLOYEE TO EMP_CANDIDATES 02/09/2014
     */
    public String approveSalaryDegreeAction() {
        System.out.println("approveSalaryDegreeAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("fatwaReviewListBean");
        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED() +
                                                                         "");
        IEmpCandidatesDTO suppliedEmployee;
        suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT);
        suppliedEmployee.setShowerrorMsg(false);
        suppliedEmployee = addExtraData(suppliedEmployee, IEMPConstants.EXTA_DATA_ACCEPTED_FIN_DEGREE);
        if (reviewListBean.getEmpCandExtraDataList() != null && reviewListBean.getEmpCandExtraDataList().size() > 0) {
            suppliedEmployee = addBounsesToPageDTO(suppliedEmployee, reviewListBean.getEmpCandExtraDataList());
        }
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        if(suppliedEmployee.isShowerrorMsg()){
             String msg=   baseBean.getSharedUtil().messageLocator(BUNDLE_NAME, "Data_not_completed");
                baseBean.getSharedUtil().setErrMsgValue(msg);
                return "";
            }
        try {
            suppliedEmployee.setStartWorkingDate(null);
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
                reviewListBean.executeMethodBinding("fatwaSelectionListBean.reloadDataForApproveListBean", null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);

        String msg = null;
        msg =
baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "approve_sal_degree_msg");

        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();

    }
    
    public String saveSalaryDegreeAction() {
        System.out.println("saveSalaryDegreeAction");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("fatwaReviewListBean");
//        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED() +
//                                                                         "");
        IEmpCandidatesDTO suppliedEmployee;
        suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT);
        suppliedEmployee.setShowerrorMsg(false);
        suppliedEmployee = addExtraData(suppliedEmployee, IEMPConstants.EXTA_DATA_ACCEPTED_FIN_DEGREE);
        if (reviewListBean.getEmpCandExtraDataList() != null && reviewListBean.getEmpCandExtraDataList().size() > 0) {
            suppliedEmployee = addBounsesToPageDTO(suppliedEmployee, reviewListBean.getEmpCandExtraDataList());
        }
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        
        if(suppliedEmployee.isShowerrorMsg()){
             String msg=   baseBean.getSharedUtil().messageLocator(BUNDLE_NAME, "Data_not_completed");
                baseBean.getSharedUtil().setErrMsgValue(msg);
                return "";
            }
        try {
            suppliedEmployee.setStartWorkingDate(null);
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

        String msg = null;
        msg =
    baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "job_arrang_save_msg");

        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();

    }

    /**
     * Fatwa Manager
     * this action convert hire stage to (تم رد الطلب من اداره ال�?توى والرأى) number 16
     * @return to Table List
     * @created BY Kareem Sayed,Suzan Abd elaziz
     */
    public String replyRequestForUpdateDataActionForContract() {
        System.out.println("moveRequestToDiwan");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("fatwaReviewListBean");
        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        
       
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT);
        
        IEmpExtraDataValueDTO dTO = suppliedEmployee.getEmpExtraDataValueDTO();
              if( dTO.getFatwaDeptNotes() ==null || dTO.getFatwaDeptNotes().trim().equals("")){
                    String  msg =
                      baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "note_mandatory");
                      baseBean.getSharedUtil().setErrMsgValue(msg);
                  return "";
                  }
        
        
        
        try {
            suppliedEmployee.setStartWorkingDate(null);
            suppliedEmployee.setJobsDTO(null);
            suppliedEmployee.setJobCode(null);
            ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_REJECTED_BY_FATWA() +
                                                                             "");
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
                reviewListBean.executeMethodBinding("fatwaSelectionListBean.reloadDataForApproveListBean", null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);

        String msg = null;
        msg =
baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "transefer_request_ministry_msg");

        baseBean.getSharedUtil().setSuccessMsgValue(msg);
        return reviewListBean.getBckBtnNavigationCase();

    }

    private Object jsfHelper(String experssionName) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return app.createValueBinding("#{" + experssionName + "}").getValue(ctx);

    }

    public String transferForFatwaDeptAction() {
        return "";
    }

    public String transferForCandidateMinistry() {
        return "";
    }


    /* added by M.abdelsabour && M.sayed
     * Centeral Employement CRS
     * this function approve the employee finanicial degree

     * */

    public String approveFinDegreeFatwa() {
        System.out.println("approveFinDegreeFatwa");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("fatwaReviewListBean");
        //((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED() +"");
        IEmpCandidatesDTO suppliedEmployee;
        suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT);
        suppliedEmployee = addExtraData(suppliedEmployee, IEMPConstants.EXTA_DATA_ACCEPTED_FIN_DEGREE);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(null);
            suppliedEmployee.setJobsDTO(null);
            IHireStagesEntityKey key = new HireStagesEntityKey((13L));
            suppliedEmployee.getHireStagesDTO().setCode(key);
            EmpClientFactory.getEmpCandidatesClient().approveFinancialDegreeFatwa(reviewListBean.getPageDTO());
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
                reviewListBean.executeMethodBinding("fatwaSelectionListBean.reloadDataForApproveListBean", null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);

        String msg = null;
        msg =
baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "approve_inf_and_transfer_choice_min_msg");

        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();

    }
    
    
    public String saveFinDegreeFatwa() {
        System.out.println("approveFinDegreeFatwa");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("fatwaReviewListBean");
        //((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED() +"");
        IEmpCandidatesDTO suppliedEmployee;
        suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT);
        suppliedEmployee = addExtraData(suppliedEmployee, IEMPConstants.EXTA_DATA_ACCEPTED_FIN_DEGREE);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(null);
            suppliedEmployee.setJobsDTO(null);
            EmpClientFactory.getEmpCandidatesClient().approveFinancialDegreeFatwa(reviewListBean.getPageDTO());
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

        String msg = null;
        msg =
    baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "job_arrang_save_msg");

        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();

    }


    /* added by M.abdelsabour && M.sayed
     * Centeral Employement CRS
     * this function return the employee to choice departement
     * */

    public String convertEmpToChoiceDpt() {

        // fromFatwaToChoiceDept
        System.out.println("move To Choice Departement");
        ReviewListBean reviewListBean = (ReviewListBean)jsfHelper("fatwaReviewListBean");
        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT);
        suppliedEmployee = addExtraData(suppliedEmployee, IEMPConstants.EXTA_DATA_ACCEPTED_FIN_DEGREE);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        IEmpExtraDataValueDTO dTO = suppliedEmployee.getEmpExtraDataValueDTO();
        if( dTO.getFatwaDeptNotes() ==null || dTO.getFatwaDeptNotes().trim().equals("")){
              String  msg =
                baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "note_mandatory");
                baseBean.getSharedUtil().setErrMsgValue(msg);
            return "";
            }
        try {
            suppliedEmployee.setStartWorkingDate(null);
            suppliedEmployee.setJobsDTO(null);
            suppliedEmployee.setJobCode(null);
            ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_REJECTED_BY_FATWA() +
                                                                             "");
            EmpClientFactory.getEmpCandidatesClient().fromFatwaToChoiceDept(suppliedEmployee);
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
                reviewListBean.executeMethodBinding("fatwaSelectionListBean.reloadDataForApproveListBean", null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);

        String msg = null;
        msg =
baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "transfer_tochoice_min_msg");

        baseBean.getSharedUtil().setSuccessMsgValue(msg);
        return reviewListBean.getBckBtnNavigationCase();

    }

}
