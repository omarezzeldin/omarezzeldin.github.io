package com.beshara.csc.hr.emp.presentation.backingbean.jobsArr.centeralEmployement;


import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExtraDataTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExtraDataValueDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NotMatchedOnHireTypeException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.emp.NotMatchedOnJobGRSConditionException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.BaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class BussinessService {


    public BussinessService() {
    }

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
                        exist = true;
                        break;
                    }
                }
            }
            if (!exist) {
                if(empCandExtraDataList==null){
                    empCandExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
                }
                IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
                IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
                empCandidateExtraDataDTO.setValue(notes);
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


    private boolean checkExperDto(IEmpCandidatesDTO employeesDTO, Long dataTypeCode) {
        List<IEmpCandidateExtraDataDTO> empCandExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
        empCandExtraDataList = (List)employeesDTO.getEmpCandidateExtraDataList();
        boolean exist=false;
            if (empCandExtraDataList != null) {
            for (IEmpCandidateExtraDataDTO empCandidateExtraDataDTO : empCandExtraDataList) {
                if (empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(dataTypeCode))) {
                    exist=true;
                    break;
                }
                   
            }
            }
            return exist;
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
        empCandExtraDataList = (List)employeesDTO.getEmpCandidateExtraDataList();
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)jsfHelper("ceneteralEmpArrang");
        String extraDataValue = "";
        if (dataType.equals(IEMPConstants.EXTA_DATA_ACCEPTED_FIN_DEGREE)) {
            extraDataValue = reviewListBean.getRaiseCode();
        } else if (dataType.equals(IEMPConstants.EX_DATA_BGT_TYPE)) {
            extraDataValue = reviewListBean.getApprovedBgtTypeCode();
        } else if (dataType.equals(IEMPConstants.EX_DATA_JOB_ACCEPTED)) {
//            if(reviewListBean.isJobFromMin()){
//                extraDataValue="Del";
//            }else{
            extraDataValue = employeesDTO.getJobsDTO().getCode().getKey();
//            }
        }
        boolean exist = false;
        if (empCandExtraDataList != null) {
            for (IEmpCandidateExtraDataDTO empCandidateExtraDataDTO : empCandExtraDataList) {
                if (empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(dataType))) {
                    empCandidateExtraDataDTO.setValue(extraDataValue);
                    empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
                    exist = true;
                    break;
                }
            }
        }
        if (!exist) {
            if(empCandExtraDataList==null){
                empCandExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
            }
            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
            IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
            empExtraDataTypesDTO.setCode(EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(dataType));
            empCandidateExtraDataDTO.setValue(extraDataValue);
            empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
            empCandidateExtraDataDTO.setEmpCandidatesDTO(employeesDTO);
            empCandExtraDataList.add(empCandidateExtraDataDTO);
            employeesDTO.setEmpCandidateExtraDataList((List)empCandExtraDataList);
        }
        return employeesDTO;
    }
    
    private IEmpCandidatesDTO addExtraDataJobFromMin(IEmpCandidatesDTO employeesDTO, Long dataType) {
        List<IEmpCandidateExtraDataDTO> empCandExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
        empCandExtraDataList = (List)employeesDTO.getEmpCandidateExtraDataList();
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)jsfHelper("ceneteralEmpArrang");
        String extraDataValue = "";
        if (reviewListBean.isJobFromMin()) {
            extraDataValue = "1";
        } else {
            extraDataValue = "0";
        }
        boolean exist = false;
        if (empCandExtraDataList != null) {
            for (IEmpCandidateExtraDataDTO empCandidateExtraDataDTO : empCandExtraDataList) {
                if (empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(String.valueOf(dataType))) {
                    empCandidateExtraDataDTO.setValue(extraDataValue);
                    empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
                    exist = true;
                    break;
                }
            }
        }
        if (!exist) {
            if(empCandExtraDataList==null){
                empCandExtraDataList = new ArrayList<IEmpCandidateExtraDataDTO>();
            }
            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
            IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
            empExtraDataTypesDTO.setCode(EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(dataType));
            empCandidateExtraDataDTO.setValue(extraDataValue);
            empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
            empCandidateExtraDataDTO.setEmpCandidatesDTO(employeesDTO);
            empCandExtraDataList.add(empCandidateExtraDataDTO);
            employeesDTO.setEmpCandidateExtraDataList((List)empCandExtraDataList);
        }
        return employeesDTO;
    }
    /*******************************إدارة الاختيار *****************************************************/

    /**
     * From Hire Stage 9 (جارى مراجعة الطلب من إدارة الاختيار)
     * this action convert hire stage to (جارى استي�?اء المسمى الوظي�?ى) number 11
     * @return to Table List
     * @Updated BY Kareem Sayed
     * Updated BY Ahmed Kamal Change From EMP_EMPLOYEE TO EMP_CANDIDATES 02/09/2014
     */
    public String transferForJobArrangmentAction() {
        System.out.println("transferForJobArrangmentAction");
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)jsfHelper("ceneteralEmpArrang");
        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_REQUIRED() +
                                                                     "");
        IEmpCandidatesDTO suppliedEmployee;
        suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
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
                reviewListBean.executeMethodBinding("jobsSelectionListBean.reloadDataForApproveListCRSBean", null);
        }
        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);
        return reviewListBean.getBckBtnNavigationCase();
    }

    /**
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
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)jsfHelper("ceneteralEmpArrang");
        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED() +
                                                                     "");
        IEmpCandidatesDTO suppliedEmployee;
        suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT);
        suppliedEmployee = addExtraData(suppliedEmployee, IEMPConstants.EXTA_DATA_ACCEPTED_FIN_DEGREE);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
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
                reviewListBean.executeMethodBinding("jobsSelectionListBean.reloadDataForApproveListCRSBean", null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "contract_emp_sign_success");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();

    }

    /**
     * From Hire Stage (13) (تم استي�?اء المسمى الوظي�?ى والدرجة)
     * this action convert hire stage to (تم اعنماد الوظي�?ة والدرجة) number 15
     * @return to Table List
     * @Updated BY Kareem Sayed
     * Updated BY Ahmed Kamal Change From EMP_EMPLOYEE TO EMP_CANDIDATES 02/09/2014
     */
    public String approveRequestAction() {
        System.out.println("approveRequestAction");
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)jsfHelper("ceneteralEmpArrang");
        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED() +
                                                                     "");
        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
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
                reviewListBean.executeMethodBinding("jobsSelectionListBean.reloadDataForApproveListCRSBean", null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        
        String mode, stageId, msg = null;
        stageId = (String)reviewListBean.getSaveStateList().get(0);
        mode = reviewListBean.getHireSystemMode();
        
        if(mode.equals(IEMPConstants.CENTRAL_EMPLOYEMENT.toString())) {
            if(stageId.equals(IEMPConstants.HIRE_STAGE_JOB_NAME_AND_DEGREE_ACCEPTRD)){// 13
                msg = baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "approve_sal_degree_msg");
            }
            else if(stageId.equals(IEMPConstants.HIRE_STAGE_FIN_DEGREE_REQUIRED_INPROGRESS)){// 10
                msg = baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "approve_inf_and_transfer_choice_min_msg");
            }
        }
        else if(mode.equals(IEMPConstants.CONTRACTS.toString())){
                if(stageId.equals(IEMPConstants.HIRE_STAGE_FIN_DEGREE_REQUIRED_INPROGRESS)){// 10
                    msg = baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "approve_sal_degree_msg");
                }
                else if(stageId.equals(IEMPConstants.HIRE_STAGE_REVIEW_ORDER_IN_SELECTION_INPROGRESS)){// 9
                    msg = baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "transfer_request_to_order_min_msg");
                }
        }
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();
    }

    /**
     * this action convert hire stage to (رد الطلب لجهة العمل) number 12
     * @return to Table List
     * @Updated BY Kareem Sayed
     * Updated BY Ahmed Kamal Change From EMP_EMPLOYEE TO EMP_CANDIDATES 02/09/2014
     */
    public String replyRequestForUpdateDataAction() {
        System.out.println("moveRequestToDiwan");
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)jsfHelper("ceneteralEmpArrang");
        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_REJECTED_BY_DEWAN() +
                                                                     "");
        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
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
                reviewListBean.executeMethodBinding("jobsSelectionListBean.reloadDataForApproveListCRSBean", null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();

    }

    /***************************************** إدارة ال�?توى والرأى**************************************************/

    /**
     *From Hire Stage عرض المرشحين ذوات الخبرة 10
     * this action convert hire stage to (تم استي�?اء الوظي�?ة والدرجة) number 13
     * @return to Table List
     * @Updated BY Kareem Sayed
     * @Updated BY Ahmed Kamal Change From EMP_EMPLOYEE TO EMP_CANDIDATES 02/09/2014
     */
    public String transeferForFatwaDeptAction2() {
        System.out.println("transeferForFatwaDeptAction2");
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)jsfHelper("ceneteralEmpArrang");
        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_JOB_NAME_ACCEPTRD().toString() +
                                                                     "");
        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT);
        suppliedEmployee = addExtraData(suppliedEmployee, IEMPConstants.EXTA_DATA_ACCEPTED_FIN_DEGREE);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(null);
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
                reviewListBean.executeMethodBinding("jobsSelectionListBean.reloadDataForApproveListCRSBean", null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();
    }

    /**
     *From Hire Stage عرض المرشحين ذوات الخبرة 10
     * this action convert hire stage to (رد الطلب من إدارة ال�?توى) number 16
     * @return to Table List
     * @Updated BY Kareem Sayed
     * Updated BY Ahmed Kamal Change From EMP_EMPLOYEE TO EMP_CANDIDATES 02/09/2014
     */
    public String replyRequestForUpdateDataForFatwaDeptAction() {
        System.out.println("replyRequestForUpdateDataForFatwaDeptAction");
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)jsfHelper("ceneteralEmpArrang");
        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_REJECTED_BY_FATWA() +
                                                                     "");
        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(null);
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
                reviewListBean.executeMethodBinding("jobsSelectionListBean.reloadDataForApproveListCRSBean", null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        String msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "successfull_Msg_value");
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();
    }

    /***************************************** إدارة الترتيب**************************************************/

    /**
     * From Hire Stage (11) جارى استي�?اء المسمى الوظي�?ى
     * this action convert hire stage to (جارى استي�?اء الدرجة المالية) number 10
     * arrange job name and BGTType
     * @return to Table List
     * @Updated BY Kareem Sayed
     * Updated BY Ahmed Kamal Change From EMP_EMPLOYEE TO EMP_CANDIDATES 02/09/2014
     */
    public String approveJobNameAction() {
        System.out.println("approveJobNameAction");
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)jsfHelper("ceneteralEmpArrang");
        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_FIN_DEGREE_REQUIRED() +
                                                                     "");
        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT);
        suppliedEmployee = addExtraData(suppliedEmployee, IEMPConstants.EX_DATA_BGT_TYPE);
        suppliedEmployee = addExtraData(suppliedEmployee, IEMPConstants.EX_DATA_JOB_ACCEPTED);
        suppliedEmployee = addExtraDataJobFromMin(suppliedEmployee, IEMPConstants.JOB_FROM_MIN);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        IEmpExtraDataValueDTO dTO = suppliedEmployee.getEmpExtraDataValueDTO();
        if( reviewListBean.isJobFromMin() && (dTO.getArrangmentDeptNotes() ==null || dTO.getArrangmentDeptNotes().trim().equals(""))){
                    String  msg =
                      baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "note_mandatory");
                      baseBean.getSharedUtil().setErrMsgValue(msg);
                  return "";
                  }
        try {
            suppliedEmployee.setStartWorkingDate(null);
            suppliedEmployee.setJobCode(null);
            suppliedEmployee.setJobsDTO(null);
            EmpClientFactory.getEmpCandidatesClient().approveJobOrderDept(suppliedEmployee);
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
                reviewListBean.executeMethodBinding("jobsSelectionListBean.reloadDataForApproveListCRSBean", null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        
        String msg = null;
        Boolean hasExp = reviewListBean.isHasExperience();
        
        if (checkExperDto(suppliedEmployee, 336L) || hasExp) {
            msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "approve_inf_and_transfer_to_fatwa_min_msg");
        } else {
            msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "approve_inf_and_transfer_choice_min_msg");
        }

        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();


    }


    public String saveJobNameAction() {
        System.out.println("saveJobNameAction");
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)jsfHelper("ceneteralEmpArrang");
//        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_FIN_DEGREE_REQUIRED() +
//                                                                     "");
        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
        suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT);
        suppliedEmployee = addExtraData(suppliedEmployee, IEMPConstants.EX_DATA_BGT_TYPE);
        suppliedEmployee = addExtraData(suppliedEmployee, IEMPConstants.EX_DATA_JOB_ACCEPTED);
        suppliedEmployee = addExtraDataJobFromMin(suppliedEmployee, IEMPConstants.JOB_FROM_MIN);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        IEmpExtraDataValueDTO dTO = suppliedEmployee.getEmpExtraDataValueDTO();
           if( reviewListBean.isJobFromMin() && (dTO.getArrangmentDeptNotes() ==null || dTO.getArrangmentDeptNotes().trim().equals(""))){
                    String  msg =
                      baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "note_mandatory");
                      baseBean.getSharedUtil().setErrMsgValue(msg);
                  return "";
                  }
        try {
            suppliedEmployee.setStartWorkingDate(null);
            suppliedEmployee.setJobCode(null);
            suppliedEmployee.setJobsDTO(null);
            EmpClientFactory.getEmpCandidatesClient().saveJobOrderDept(suppliedEmployee);
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
        Boolean hasExp = reviewListBean.isHasExperience();
  
            msg =
            baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "job_arrang_save_msg");
  
        baseBean.getSharedUtil().setSuccessMsgValue(msg);

        return reviewListBean.getBckBtnNavigationCase();


    }
    /**
     * From Hire Stage (11) جارى استي�?اء المسمى الوظي�?ى
     * this action convert hire stage to (رد الطلب من إدارة التوصي�?) number 14
     * arrange job name and BGTType
     * @return to Table List
     * @Updated BY Kareem Sayed
     * Updated BY Ahmed Kamal Change From EMP_EMPLOYEE TO EMP_CANDIDATES 02/09/2014
     */
    public String rejectJobNameAction() {
        System.out.println("rejectJobNameAction");
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)jsfHelper("ceneteralEmpArrang");
        ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setHireStageKey(reviewListBean.getManagedConstantsBean().getHIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT() +
                                                                     "");
        IEmpCandidatesDTO suppliedEmployee = (IEmpCandidatesDTO)reviewListBean.getPageDTO();
       suppliedEmployee = addNotesExtraDataDto(suppliedEmployee, IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT);
        BaseBean baseBean = (BaseBean)jsfHelper(reviewListBean.getPageBeanName());
        try {
            suppliedEmployee.setStartWorkingDate(null);
            EmpClientFactory.getEmpCandidatesClient().fromOrderToChoiceDept(suppliedEmployee);

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
                reviewListBean.executeMethodBinding("jobsSelectionListBean.reloadDataForApproveListCRSBean", null);
        }

        baseBean.setSearchQuery("");
        baseBean.setSearchType(0);
        baseBean.setSearchMode(false);
        
        String msg = null;
        msg = baseBean.getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "transfer_tochoice_min_msg");
        
        
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

}
