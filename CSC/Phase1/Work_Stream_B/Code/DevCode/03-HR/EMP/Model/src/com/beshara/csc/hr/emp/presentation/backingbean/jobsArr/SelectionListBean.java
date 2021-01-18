package com.beshara.csc.hr.emp.presentation.backingbean.jobsArr;


import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata.WorkDataListBean;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidateDocumentsClient;
import com.beshara.csc.hr.emp.business.client.IEmpCndSalaryElementsClient;
import com.beshara.csc.hr.emp.business.dto.EmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.entity.EmpExtraDataTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.employmentcycle.experiencelist.ExperienceListBean;
import com.beshara.csc.hr.emp.presentation.backingbean.fatwaemploymentcycle.shared.config.HireStageConst;
import com.beshara.csc.hr.emp.presentation.backingbean.jobsArr.centeralEmployement.ReviewListBeanCRS;
import com.beshara.csc.hr.emp.presentation.backingbean.shared.AddDocAttachmentsListBean;
import com.beshara.csc.hr.emp.presentation.backingbean.viewdocuments.ViewDocumentsBean;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class SelectionListBean extends LookUpBaseBean {

    private static final String SELECTION_BEAN_NAME = "jobsSelectionListBean";

    private static final String COLUMN_NAME_HR_EMP_EMPLOYEES_CIVIL_ID = "INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID";
    private static final String COLUMN_NAME_HR_EMP_EMPLOYEES_FULL_NAME = "FULL_NAME";

    private static final String COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_FIRST_NAME =
        "INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME";
    private static final String COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_SECOND_NAME =
        "INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME";
    private static final String COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_THIRD_NAME =
        "INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME";
    private static final String COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_LAST_NAME =
        "INF_KWT_CITIZENS_RESIDENTS.LAST_NAME";
    private static final String COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_FAMILY_NAME =
        "INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME";

    private static final String COLUMN_MINSTRY_NAME = "NL_ORG_MINISTRIES.MIN_NAME";

    private final Long DEWAN_CODE = new Long(13);


    private static final String HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT = "14";
    private static final String HIRE_STAGE_REJECTED_BY_FATWA = "16";
    private static final String HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED = "15";
    private static final String HIRE_STAGE_JOB_NAME_REQUIRED = "11";

    private static final String PAGE_ID_FIN_DEGREE_REQUIRED = "4";
    private static final String HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT_CAND_REJECTED_BY_FATWA = "1416";
    private String selectedStageId;
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;

    private boolean hideHasExprince;

    private List<IBasicDTO> categoryList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> ministryList = new ArrayList<IBasicDTO>();


    private String oldMethodName;
    private String selectedCategory;
    private String selectedMinistry;
    IEmployeeSearchDTO searchDTO1;


    private String calcPageMode;
    private String centeralEmployment = IEMPConstants.CENTRAL_EMPLOYEMENT.toString();
    private String contracts = IEMPConstants.CONTRACTS.toString();

    private boolean contractActive = false;
    WorkDataListBean workDataListBean = (WorkDataListBean)evaluateValueBinding("workDataListBean");
    private static final String LIST_PAGE = "jobsSelectionListPage";
    private static final String FIRST = "first";
    private static final String NEXT = "next";
    private static final String FASTFORWARD = "fastf";

    private static final String PREVIOUS = "previous";
    private static final String LAST = "last";
    private static final String FASTREWIND = "fastr";
    private boolean validCivilId = false;
    private boolean civilExist = false;
    private String civilName;
    private Long civilId;
    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    public SelectionListBean() {
        setUsingPaging(true);  
        setUsingBsnPaging(true);
        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setSaveSortingState(true);
        super.setSelectedPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setClient(EmpClientFactory.getEmpCandidatesClient());
        setEmpListOfValues(new EmployeeListOfValues());
        setDivMainContent("divContent1Fixed");
        intializeSearchDTO();
    }

    private void intializeSearchDTO() {
        if (searchDTO1 == null)
            searchDTO1 = EmpDTOFactory.createEmployeeSearchDTO();
        calcPageMode = IEMPConstants.CENTRAL_EMPLOYEMENT.toString();
        searchDTO1.setEmpHireTypes(IEMPConstants.EMP_CANDIDATE_HIRE_TYPE);
        searchDTO1.setEmpHireStatus(IEMPConstants._EMP_CENTERAL_HIRE_TYPE);
        searchDTO1.setActiveFlag(IEMPConstants.EMP_ACTIVE_FLAG);
        if (getCalcPageMode().equals(IEMPConstants.CENTRAL_EMPLOYEMENT.toString())) {
            searchDTO1.setEmpHireTypes(IEMPConstants.CENTRAL_EMPLOYEMENT);
            setSelectedStageId(getStageIdJobNameRequired());
        } else {
            searchDTO1.setEmpHireTypes(IEMPConstants.CONTRACTS);
        }
        searchDTO1.setExperienceCheck(IEMPConstants.HAS_EXPERIENCE);
    }
    
//    public void initiateBeanOnce() {
//        searchDTO = EmpDTOFactory.createEmployeeSearchDTO();
//        calcPageMode = IEMPConstants.CENTRAL_EMPLOYEMENT.toString();
//        searchDTO.setEmpHireTypes(IEMPConstants.EMP_CANDIDATE_HIRE_TYPE);
//        searchDTO.setEmpHireStatus(IEMPConstants._EMP_CENTERAL_HIRE_TYPE);
//        searchDTO.setActiveFlag(IEMPConstants.EMP_ACTIVE_FLAG);
//        if (getCalcPageMode().equals(IEMPConstants.CENTRAL_EMPLOYEMENT.toString())) {
//            searchDTO.setEmpHireTypes(IEMPConstants.CENTRAL_EMPLOYEMENT);
//            setSelectedStageId(getStageIdJobNameRequired());
//        } else {
//            searchDTO.setEmpHireTypes(IEMPConstants.CONTRACTS);
//        }
//        searchDTO.setExperienceCheck(IEMPConstants.HAS_EXPERIENCE);
//
//    }


    public void reloadDataForReviewListBean() {
        ReviewListBean reviewListBean = (ReviewListBean)evaluateValueBinding("jobsReviewListBean");
        setSelectedStageId((String)reviewListBean.getSaveStateList().get(0));
        setSelectedMinistry((String)reviewListBean.getSaveStateList().get(1));
        setCalcPageMode((String)reviewListBean.getSaveStateList().get(2));
        setSelectedDTOS((List)reviewListBean.getSaveStateList().get(3));
        setSelectedRadio((String)reviewListBean.getSaveStateList().get(4));
        setCivilId((Long)reviewListBean.getSaveStateList().get(5));
        if(getCivilId()!=null && civilExist)
        searchDTO1.setCivilId(civilId);
      
    }
    
    public void reloadDataForApproveListBean() {
        ReviewListBean reviewListBean = (ReviewListBean)evaluateValueBinding("jobsReviewListBean");
        setSelectedStageId((String)reviewListBean.getSaveStateList().get(0));
        setSelectedMinistry((String)reviewListBean.getSaveStateList().get(1));
        setCalcPageMode((String)reviewListBean.getSaveStateList().get(2));
  
        
        getSelectedDTOS().clear();
        reSetData();
        setCurrentPageIndex(1);
      
    }

    private void displaySalaryDegreeForContract(ReviewListBean reviewListBean, IEmpCandidatesDTO empCndDTO) {
        ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
        ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
        ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
        IEmpCndSalaryElementsDTO empCndSalaryElementsDTO = EmpDTOFactory.createEmpCndSalaryElementsDTO();
        IEmpCndSalaryElementsClient empCndSalaryElementsClient =
            ClientFactoryUtil.getInstance(IEmpCndSalaryElementsClient.class);

        try {
            empCndSalaryElementsDTO = empCndSalaryElementsClient.getByCandCode(empCndDTO.getCode());

            //raise
            reviewListBean.setSuggestedRaiseCode(Long.valueOf(empCndSalaryElementsDTO.getSalElementGuidesDTO().getCode().getKey()));
            if (reviewListBean.getSuggestedRaiseCode() != null) {
                raiseDTO =
                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedRaiseCode());
                reviewListBean.setSuggestedRaiseName(String.valueOf(raiseDTO.getCountGuide()));
            }
            //degree
            if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                reviewListBean.setSuggestedDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode());
            }
            if (reviewListBean.getSuggestedDegreeCode() != null) {
                degreeDTO =
                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedDegreeCode());
                reviewListBean.setSuggestedDegreeName(degreeDTO.getName());
            }
            //group
            if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                reviewListBean.setSuggestedGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
            }
            if (reviewListBean.getSuggestedGroupCode() != null) {
                groupDTO =
                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedGroupCode());
                reviewListBean.setSuggestedGroupName(groupDTO.getName());
            }
            //Cader
            if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                reviewListBean.setSuggestedCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                reviewListBean.setSuggestedCaderName(groupDTO.getParentObject().getName());
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }

    }

    private void showSuggestSalaryDegree(ReviewListBean reviewListBean, IEmpCandidatesDTO empDTO, Long extraDataType) {
        if (empDTO.getEmpCandidateExtraDataList() != null) {
            ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
            try {
                for (IBasicDTO list1 : empDTO.getEmpCandidateExtraDataList()) {
                    EmpCandidateExtraDataDTO list = (EmpCandidateExtraDataDTO)list1;
                    if (extraDataType != null) {
                        if (((EmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(extraDataType)) {
                            //raise
                            reviewListBean.setSuggestedRaiseCode(Long.valueOf(list.getValue()));
                            if (reviewListBean.getSuggestedRaiseCode() != null) {
                                raiseDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedRaiseCode());
                                reviewListBean.setSuggestedRaiseName(String.valueOf(raiseDTO.getCountGuide()));
                            }
                            //degree
                            if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                                reviewListBean.setSuggestedDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getSuggestedDegreeCode() != null) {
                                degreeDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedDegreeCode());
                                reviewListBean.setSuggestedDegreeName(degreeDTO.getName());
                            }
                            //group
                            if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                                reviewListBean.setSuggestedGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getSuggestedGroupCode() != null) {
                                groupDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedGroupCode());
                                reviewListBean.setSuggestedGroupName(groupDTO.getName());
                            }
                            //Cader
                            if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                                reviewListBean.setSuggestedCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                                reviewListBean.setSuggestedCaderName(groupDTO.getParentObject().getName());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showAcceptSalaryDegree(ReviewListBean reviewListBean, IEmpCandidatesDTO empDTO, Long extraDataType) {
        if (empDTO.getEmpCandidateExtraDataList() != null) {
            ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
            try {
                for (IBasicDTO list1 : empDTO.getEmpCandidateExtraDataList()) {
                    EmpCandidateExtraDataDTO list = (EmpCandidateExtraDataDTO)list1;
                    if (extraDataType != null) {
                        if (((EmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(extraDataType)) {
                            //raise
                            reviewListBean.setRaiseCode(list.getValue());
                            if (reviewListBean.getRaiseCode() != null) {
                                raiseDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.parseLong(reviewListBean.getRaiseCode()));
                                reviewListBean.setRaiseName(String.valueOf(raiseDTO.getCountGuide()));
                            }
                            //degree
                            if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                                reviewListBean.setDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getDegreeCode() != null) {
                                degreeDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getDegreeCode());
                                reviewListBean.setDegreeName(degreeDTO.getName());
                            }
                            //group
                            if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                                reviewListBean.setGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getGroupCode() != null) {
                                groupDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getGroupCode());
                                reviewListBean.setGroupName(groupDTO.getName());
                            }
                            //Cader
                            if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                                reviewListBean.setCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                                reviewListBean.setCaderName(groupDTO.getParentObject().getName());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showJobAndBudgetType(ReviewListBean reviewListBean, IEmpCandidatesDTO empDTO) {
        if (empDTO.getEmpCandidateExtraDataList() != null) {
            try {
                boolean existBudgetType = false;
                boolean existJob = false;
                boolean existJobNameForMin = false;
                reviewListBean.setShowFatwaNotes(false);
//                String previousNote = "";
//                String currentNote = "";
//                if (empDTO.getPreviousEmpExtraDataValueDTO() != null &&
//                    empDTO.getPreviousEmpExtraDataValueDTO().getArrangmentDeptNotes() != null) {
//                    previousNote = empDTO.getPreviousEmpExtraDataValueDTO().getArrangmentDeptNotes();
//                }
//                if (empDTO.getEmpExtraDataValueDTO() != null &&
//                    empDTO.getEmpExtraDataValueDTO().getArrangmentDeptNotes() != null) {
//                    currentNote = empDTO.getEmpExtraDataValueDTO().getArrangmentDeptNotes();
//                }
//                if (currentNote.trim().equals("") && !previousNote.trim().equals("")) {
//                    empDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(previousNote);
//                }
//
//                if (empDTO.getPreviousEmpCandidateExtraDataList() != null) {
//
//                    for (IBasicDTO basicDTO : empDTO.getPreviousEmpCandidateExtraDataList()) {
//                        EmpCandidateExtraDataDTO employeeExtraDataDTO = (EmpCandidateExtraDataDTO)basicDTO;
//
//                        if (((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(13L)) {
//                            IJobsEntityKey entitKey =
//                                JobEntityKeyFactory.createJobsEntityKey(employeeExtraDataDTO.getValue());
//                            IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
//                            reviewListBean.setJobKey(jobDTO.getJobKey());
//                            reviewListBean.setApprovedJobName(jobDTO.getName());
//                            reviewListBean.setAcceptedJobName(jobDTO.getName());
//                            empDTO.setJobsDTO(jobDTO);
//                            existJob = true;
//                            //suggested job
//                        } else if (((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(4L)) {
//                            IJobsEntityKey entitKey =
//                                JobEntityKeyFactory.createJobsEntityKey(employeeExtraDataDTO.getValue());
//                            IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
//                            reviewListBean.setJobNameForMin(jobDTO.getName());
//                            existJobNameForMin = true;
//                        }else if(((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(8L)) {
//                           reviewListBean.setFatwaNotes( employeeExtraDataDTO.getValue());
//                           reviewListBean.setShowFatwaNotes(true);
//                            }
//
//                    }
//                }
                                     if(((IEmpCandidatesEntityKey)(empDTO.getCode())).getCandidateCodeSeq() > 1L){
                if (empDTO.getPreviousEmpCandidateExtraDataList() != null) {
                              
                                   for (IBasicDTO basicDTO : empDTO.getPreviousEmpCandidateExtraDataList()) {
                                       EmpCandidateExtraDataDTO employeeExtraDataDTO = (EmpCandidateExtraDataDTO)basicDTO;
                                       if (((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(13L)) {
                                           IJobsEntityKey entitKey =
                                               JobEntityKeyFactory.createJobsEntityKey(employeeExtraDataDTO.getValue());
                                           IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                                           reviewListBean.setJobKeyPrevious(jobDTO.getJobKey());
                                           reviewListBean.setJobNamePrevious(jobDTO.getName());
                                       }
                                   }
                                  
                           }
                
                     }
                for (IBasicDTO basicDTO : empDTO.getEmpCandidateExtraDataList()) {
                    EmpCandidateExtraDataDTO employeeExtraDataDTO = (EmpCandidateExtraDataDTO)basicDTO;

                    if (((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(13L)) {
                        IJobsEntityKey entitKey =
                            JobEntityKeyFactory.createJobsEntityKey(employeeExtraDataDTO.getValue());
                        IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                        reviewListBean.setAcceptedJobName(jobDTO.getName());
                        empDTO.setJobsDTO(jobDTO);
                        existJob = true;
                        //suggested job
                    } else if (((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(4L)) {
                        IJobsEntityKey entitKey =
                            JobEntityKeyFactory.createJobsEntityKey(employeeExtraDataDTO.getValue());
                        IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                        reviewListBean.setJobNameForMin(jobDTO.getName());
                        existJobNameForMin = true;
                    }else if(((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(8L)) {
                       reviewListBean.setFatwaNotes( employeeExtraDataDTO.getValue());
                       reviewListBean.setShowFatwaNotes(true);
                        }
                    else if(((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(345L)) {
                        if(  employeeExtraDataDTO.getValue().equals("1")){
                                reviewListBean.setJobFromMin( true);
                        }else{
                                reviewListBean.setJobFromMin( false);
                            }
                            reviewListBean.jobFromMinAction();
                        }
                }
                //                if (!existBudgetType) {
                //                    reviewListBean.setApprovedBgtTypeCode(getVirtualConstValue());
                //                    reviewListBean.setApprovedBgtTypeName("");
                //                    reviewListBean.budgetTypeController(empDTO, getSelectedStageId());
                //                }
                if (!existJob) {
                    empDTO.getJobsDTO().setName(null);
                }
                if (!existJobNameForMin) {
                    reviewListBean.setJobNameForMin("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Fatwa_Manger
     * filldata in page according to hireStage.
     * @param empDTO
     * @param reviewListBean
     * @param stage
     */
    public void fillDataByStage(IEmpCandidatesDTO empDTO, ReviewListBean reviewListBean, String stage) {
        System.out.println(" ============================== " + stage + " ==================== ");

        switch (Integer.parseInt(stage)) {

        case HireStageConst.HIRE_STAGE_COMPLETING_JOB_NAME:
            displaySalaryDegreeForContract(reviewListBean, empDTO);
            showJobAndBudgetType(reviewListBean, empDTO); // show budgetType
            break;
        case HireStageConst.HIRE_STAGE_FIN_DEGREE_REQUIRED:
            displaySalaryDegreeForContract(reviewListBean, empDTO);
            showJobAndBudgetType(reviewListBean, empDTO); // show budgetType
            showAcceptSalaryDegree(reviewListBean, empDTO, 14L);
            break;
        case HireStageConst.HIRE_STAGE_JOB_NAME_REQUIRED:
            displaySalaryDegreeForContract(reviewListBean, empDTO);
            showJobAndBudgetType(reviewListBean, empDTO); // show budgetType
            //showAcceptSalaryDegree(reviewListBean, empDTO, 14L);
            break;

        default:
            System.out.println("default");

        }


    }


    // To Do M.abdelsabour

    public String viewApproveListAction() {
        if (getCalcPageMode().equals(IEMPConstants.CENTRAL_EMPLOYEMENT.toString())) {
            return viewApproveListActionForCenteralEmployement();
        } else {
            ReviewListBean reviewListBean = (ReviewListBean)evaluateValueBinding("jobsReviewListBean");
            IEmpCandidatesDTO empDTO;
            try {
                try{
                 empDTO = makeEmployeeDTO(getSelectedDTOS().get(0).getCode());  
                } catch (Exception e) {
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "qul_must_found"));
                    return null;
                }
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SatgeID=" + getSelectedStageId());
                fillDataByStage(empDTO, reviewListBean, getSelectedStageId());

                if (getCalcPageMode().equals(IEMPConstants.CONTRACTS.toString()) &&
                    getSelectedStageId().equals(Long.parseLong(IEMPConstants.HIRE_STAGE_FIN_DEGREE_REQUIRED_INPROGRESS))) {
                    // this step because hireStage=10 found in CRS & Contract
                    reviewListBean.showViewByStage((Long)(long)HireStageConst.HIRE_STAGE_FIN_DEGREE_EQUVLANT);
                } else {
                    reviewListBean.showViewByStage(Long.parseLong(getSelectedStageId()));
                }
                reviewListBean.setHasExperience(empDTO.isHasExperience());
                reviewListBean.setHireSystemMode(getCalcPageMode());
                reviewListBean.setBckBtnNavigationCase("jobsSelectionListPage");
                reviewListBean.getSaveStateList().add(getSelectedStageId());
                reviewListBean.getSaveStateList().add(getSelectedMinistry());
                reviewListBean.getSaveStateList().add(getCalcPageMode());
                reviewListBean.getSaveStateList().add(getSelectedDTOS());
                reviewListBean.getSaveStateList().add(getSelectedRadio());
                reviewListBean.getSaveStateList().add(getCivilId());
                reviewListBean.setBackActionMethodName(SELECTION_BEAN_NAME + ".reloadDataForReviewListBean");
                IJobsEntityKey entitKey = null;
                IJobsDTO jobDTO = null;
                if (empDTO.getEmpCandidateExtraDataList() != null) {
                    for (IBasicDTO basicDTO : empDTO.getEmpCandidateExtraDataList()) {
                        IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)basicDTO;
                        if (((EmpExtraDataTypesEntityKey)extraDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode() ==
                            13L) {
                            entitKey = JobEntityKeyFactory.createJobsEntityKey(extraDTO.getValue());
                            jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                            ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setJobsDTO(jobDTO);
                            break;
                        }
                    }
                }
                IWorkCentersDTO temp1 = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
                if (temp1 != null && !temp1.equals(" ")) {
                    reviewListBean.setWorkCenterName(empDTO.getWorkCentersDTO().getName());
                }
                
                String temp3 = empDTO.getEmpExtraDataValueDTO().getApprovedJob();
                IJobsEntityKey entitKey1 = null;
                IJobsDTO jobDTO1 = null;
                if (temp3 != null && !temp3.equals(" ")) {
                    entitKey1 = JobEntityKeyFactory.createJobsEntityKey(temp3);
                    jobDTO1 = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey1);
                    reviewListBean.setApprovedJobName(jobDTO1.getName());
                    reviewListBean.setJobKey(jobDTO1.getJobKey());
                }
                reviewListBean.setPageDTO(empDTO);
                reviewListBean.setPageBeanName(SELECTION_BEAN_NAME);
                reviewListBean.salaryDataSectionController(empDTO.isHasExperience(), getSelectedStageId());
                reviewListBean.setSelectedMinistery(getSelectedMinistry());

                if (getSelectedStageId().equals(String.valueOf(HireStageConst.HIRE_STAGE_JOB_NAME_REQUIRED))) {
                    IWorkCentersDTO wrkCenter = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
                    if (wrkCenter != null && !wrkCenter.equals(" ")) {
                        IWorkCentersEntityKey key = (IWorkCentersEntityKey)wrkCenter.getCode();
                        reviewListBean.setMinWorkCenter(key.getWrkCode());
                    }
                }
                return "approveListPage";
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                getSharedUtil().handleException(e);
            } catch (DataBaseException e) {
                e.printStackTrace();
                getSharedUtil().handleException(e);
            }
            return "";
        }

    }


    public String viewReviewListAction() {
        if (getCalcPageMode().equals(IEMPConstants.CENTRAL_EMPLOYEMENT.toString())) {
            return viewReviewListActionForCenteralEmployement();
        } else {
            ReviewListBean reviewListBean = (ReviewListBean)evaluateValueBinding("jobsReviewListBean");
            IEmpCandidatesDTO empDTO;
            try {
                try{
                 empDTO = makeEmployeeDTO(getSelectedDTOS().get(0).getCode());  
                } catch (Exception e) {
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "qul_must_found"));
                    return null;
                }
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SatgeID=" + getSelectedStageId());
                fillDataByStage(empDTO, reviewListBean, getSelectedStageId());

                if (getCalcPageMode().equals(IEMPConstants.CONTRACTS.toString()) &&
                    getSelectedStageId().equals(Long.parseLong(IEMPConstants.HIRE_STAGE_FIN_DEGREE_REQUIRED_INPROGRESS))) {
                    // this step because hireStage=10 found in CRS & Contract
                    reviewListBean.showViewByStage((Long)(long)HireStageConst.HIRE_STAGE_FIN_DEGREE_EQUVLANT);
                } else {
                    reviewListBean.showViewByStage(Long.parseLong(getSelectedStageId()));
                }
                reviewListBean.setHasExperience(empDTO.isHasExperience());
                reviewListBean.setHireSystemMode(getCalcPageMode());
                reviewListBean.setBckBtnNavigationCase("jobsSelectionListPage");
                reviewListBean.getSaveStateList().add(getSelectedStageId());
                reviewListBean.getSaveStateList().add(getSelectedMinistry());
                reviewListBean.getSaveStateList().add(getCalcPageMode());
                reviewListBean.getSaveStateList().add(getSelectedDTOS());
                reviewListBean.getSaveStateList().add(getSelectedRadio());
                reviewListBean.getSaveStateList().add(getCivilId());
                reviewListBean.setBackActionMethodName(SELECTION_BEAN_NAME + ".reloadDataForReviewListBean");
                IJobsEntityKey entitKey = null;
                IJobsDTO jobDTO = null;
                if (empDTO.getEmpCandidateExtraDataList() != null) {
                    for (IBasicDTO basicDTO : empDTO.getEmpCandidateExtraDataList()) {
                        IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)basicDTO;
                        if (((EmpExtraDataTypesEntityKey)extraDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode() ==
                            13L) {
                            entitKey = JobEntityKeyFactory.createJobsEntityKey(extraDTO.getValue());
                            jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                            ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).setJobsDTO(jobDTO);
                            break;
                        }
                    }
                }
                IWorkCentersDTO temp1 = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
                if (temp1 != null && !temp1.equals(" ")) {
                    reviewListBean.setWorkCenterName(empDTO.getWorkCentersDTO().getName());
                }
                String temp3 = empDTO.getEmpExtraDataValueDTO().getApprovedJob();
                IJobsEntityKey entitKey1 = null;
                IJobsDTO jobDTO1 = null;
                if (temp3 != null && !temp3.equals(" ")) {
                    entitKey1 = JobEntityKeyFactory.createJobsEntityKey(temp3);
                    jobDTO1 = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey1);
                    reviewListBean.setApprovedJobName(jobDTO1.getName());
                    reviewListBean.setJobKey(jobDTO1.getJobKey());
                }
                reviewListBean.setPageDTO(empDTO);


                reviewListBean.setPageBeanName(SELECTION_BEAN_NAME);
                reviewListBean.salaryDataSectionController(empDTO.isHasExperience(), getSelectedStageId());
                reviewListBean.setSelectedMinistery(getSelectedMinistry());

                if (getSelectedStageId().equals(String.valueOf(HireStageConst.HIRE_STAGE_JOB_NAME_REQUIRED))) {
                    IWorkCentersDTO wrkCenter = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
                    if (wrkCenter != null && !wrkCenter.equals(" ")) {
                        IWorkCentersEntityKey key = (IWorkCentersEntityKey)wrkCenter.getCode();
                        reviewListBean.setMinWorkCenter(key.getWrkCode());
                    }
                }
                return "jobsReviewRequestPage";
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                getSharedUtil().handleException(e);
            } catch (DataBaseException e) {
                e.printStackTrace();
                getSharedUtil().handleException(e);
            }
            return "";
        }

    }
    
    private IEmpCandidatesDTO makeEmployeeDTO(IEntityKey key) throws DataBaseException, SharedApplicationException {
        IEmpCandidatesDTO empDTO;
        IPersonQualificationsDTO personQualificationsDTO = InfDTOFactory.createPersonQualificationsDTO();
        empDTO = EmpClientFactory.getEmpCandidatesClient().getByCndCodeAndSequnc(key);
        if(!empDTO.isWitoutQualFlag()){
        try {
            personQualificationsDTO =
                    (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getCurrentCentralEmpPersonQul(Long.valueOf(empDTO.getCitizensResidentsDTO().getCode().getKey()));
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        ((IKwtCitizensResidentsDTO)empDTO.getCitizensResidentsDTO()).setPersonQualificationsDTOList(new ArrayList<IPersonQualificationsDTO>());
        if (personQualificationsDTO.getCode() != null) {
            ((IKwtCitizensResidentsDTO)empDTO.getCitizensResidentsDTO()).getPersonQualificationsDTOList().add(personQualificationsDTO);
        }
        }
        return empDTO;
    }


    public AppMainLayout appMainLayoutBuilder() {

        AppMainLayout app = new AppMainLayout();

        app.setShowdatatableContent(true);
        app.setShowContent1(true);
        app.setShowpaging(true);
        app.setShowbar(true);
        app.setShowEmpSrchDiv(true);
        return app;
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO pagingRequest) {

        if (getSelectedStageId() != null && !getSelectedStageId().equals("")) { 

            fillDataTableByStageId();

            return filterDataTableByStageWithPaging(getPagingRequestDTO()); 
        }

        return new PagingResponseDTO();
    }

    public PagingResponseDTO filterDataTableByStageWithPaging(PagingRequestDTO pagingRequest) {

        IPagingResponseDTO bsnResponseDTO = getDataByStageIdWithPaging(pagingRequest);

        PagingResponseDTO pagingResponseDTO = null;

        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());
          
                pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                getPagingRequestDTO().setParams(new Object[] { pagingRequest.getParams()[0],
                                                               bsnResponseDTO.getCount() });
       
        }
        resetPageIndexCustom();
         return pagingResponseDTO; 
    }

    public void fillDataTableByStageId() {

//        setUpdateMyPagedDataModel(true);
        getSelectedMinistry();
        setSorting(false);
        setFullColumnName(null);


        if (getSelectedStageId() != null) {
            if (!getSelectedStageId().equals("")) {
                // if (getSelectedStageId().equals(IEMPConstants.HIRE_STAGE_FIN_DEGREE_REQUIRED_INPROGRESS)) {
                searchDTO1.setExperienceCheck(IEMPConstants.HAS_EXPERIENCE);
                searchDTO1.setMangeId(IEMPConstants.JOBS_ARRANGMENT_MANAGER);
                if (getCalcPageMode().equals(IEMPConstants.CONTRACTS.toString())) {
                    searchDTO1.setEmpHireTypes(null);
                } else {
                    searchDTO1.setEmpHireTypes(Long.valueOf(getCalcPageMode()));
                }

            
                //}
//                if (getSelectedDTOS().size() > 0)
//                    getSelectedDTOS().clear();

                generatePagingRequestDTO(SELECTION_BEAN_NAME, "filterDataTableByStageWithPaging");
                List parameters = new ArrayList();
                if (getSelectedStageId().equals(HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT_CAND_REJECTED_BY_FATWA)) {
                    parameters.add(HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT);
                    parameters.add(HIRE_STAGE_REJECTED_BY_FATWA);

                    Object[] params = new Object[] { parameters };
                    getPagingRequestDTO().setParams(params);
                } else {
                    parameters.add(getSelectedStageId());
                    Object[] params = new Object[] { parameters };
                    getPagingRequestDTO().setParams(params);
                }
            }
        }
    }

    public IPersonQualificationsDTO getLastPersonQualification() {
        IEmpCandidatesDTO currentDTO = (IEmpCandidatesDTO)this.getMyDataTable().getRowData();
        try {
            return (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getCurrentCentralEmpPersonQul(((KwtCitizensResidentsEntityKey)currentDTO.getCitizensResidentsDTO().getCode()).getCivilId());
        } catch (SharedApplicationException e) {
            return InfDTOFactory.createPersonQualificationsDTO();
        } catch (DataBaseException e) {
            return InfDTOFactory.createPersonQualificationsDTO();
        }
    }

    public void setSelectedStageId(String selectedStageId) {
        this.selectedStageId = selectedStageId;
    }

    public String getSelectedStageId() {
        return selectedStageId;
    }

    public String getStageIdCompletingJobName() {
        return IEMPConstants.HIRE_STAGE_REVIEW_ORDER_IN_SELECTION_INPROGRESS;
    }

    public String getStageIdRejectedByJobsArrangementDeptAndRejectedByFatwaDept() {
        return HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT_CAND_REJECTED_BY_FATWA;
    }


    public String getStageIdRejectedByFatwaDept() {
        return IEMPConstants.HIRE_STAGE_JOB_NAME_AND_DEGREE_ACCEPTRD;
    }

    public String getStageIdRejectedByJobsArrangementDept() {
        return IEMPConstants.HIRE_STAGE_FIN_DEGREE_REQUIRED_INPROGRESS;
    }

    public String getStageIdJobNameAccepted() {
        return IEMPConstants.HIRE_STAGE_JOB_NAME_AND_DEGREE_ACCEPTRD;
    }

    public String getStageIdJobNameAndFinDegreeAccepted() {
        return HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED;
    }

    public String getStageIdJobNameRequired() {
        return IEMPConstants.HIRE_STAGE_JOB_NAME_REQUIRED_INPROGRESS;
    }

    public String getStageIdFinDegreeRequired() {
        return IEMPConstants.HIRE_STAGE_FIN_DEGREE_REQUIRED_INPROGRESS;
    }

    //    public String getPageIdFromCRS() {
    //        return PAGE_ID_FROM_CRS;
    //    }
    //
    //    public String getPageIdJobNameAndFinDegreeAccepted() {
    //        return PAGE_ID_JOB_NAME_AND_FIN_DEGREE_ACCEPTED;
    //    }
    //
    //    public String getPageIdJobNameRequired() {
    //        return PAGE_ID_JOB_NAME_REQUIRED;
    //    }

    public String getPageIdFinDegreeRequired() {
        return PAGE_ID_FIN_DEGREE_REQUIRED;
    }

    //    public void setMinstriesList(List minstriesList) {
    //        this.minstriesList = minstriesList;
    //    }
    //
    //    public List getMinstriesList() {
    //
    //        if (minstriesList == null) {
    //            try {
    //                minstriesList =
    //                        OrgClientFactory.getMinistriesClient().getMinistriesByGovFlag(ISystemConstant.GOVERNMENT_FLAG);
    //            } catch (DataBaseException e) {
    //                minstriesList = new ArrayList();
    //            } catch (SharedApplicationException e) {
    //                minstriesList = new ArrayList();
    //            }
    //        }
    //
    //        return minstriesList;
    //    }

    public void setSelectedMinistry(String selectedMinistry) {
        this.selectedMinistry = selectedMinistry;
    }

    public String getSelectedMinistry() {
        return selectedMinistry;
    }

    public String getColumnNameHR_EMP_EMPLOYEES_CIVIL_ID() {
        return COLUMN_NAME_HR_EMP_EMPLOYEES_CIVIL_ID;
    }

    public String getColumnNameHR_EMP_EMPLOYEES_FULL_NAME() {
        return COLUMN_NAME_HR_EMP_EMPLOYEES_FULL_NAME;
    }

    private com.beshara.base.paging.impl.PagingResponseDTO getDataByStageIdWithPaging(PagingRequestDTO pagingRequestDTO) {

        int pageIndex = getCurrentPageIndex();

        com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO =
            new com.beshara.base.paging.impl.PagingRequestDTO();

        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));

        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));

       // if (pageIndex == 1) {
            bsnPagingRequestDTO.setCountRequired(true);
        //}

        if (isSorting()) {
            bsnPagingRequestDTO.setSortAscending(pagingRequestDTO.isSortAscending());
            List<String> sortingColumnList = new ArrayList<String>();
            if (pagingRequestDTO.getBsnSortingColumnName().equals(COLUMN_NAME_HR_EMP_EMPLOYEES_FULL_NAME)) {

                sortingColumnList.add(COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_FIRST_NAME);
                sortingColumnList.add(COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_SECOND_NAME);
                sortingColumnList.add(COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_THIRD_NAME);
                sortingColumnList.add(COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_LAST_NAME);
                sortingColumnList.add(COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_FAMILY_NAME);
            } else {
                sortingColumnList.add(pagingRequestDTO.getBsnSortingColumnName());
            }
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }

        List stagesList = (List)pagingRequestDTO.getParams()[0];
        setSelectedMinistry(selectedMinistry);
        searchDTO1.setEmpHireStages(stagesList);
        searchDTO1.setPagingRequestDTO(bsnPagingRequestDTO);
        if (getSelectedMinistry() != null && !getSelectedMinistry().equals("")) {
            searchDTO1.setMinistryCode(Long.parseLong(getSelectedMinistry()));
        }
        
        if(getCivilId()!=null && civilExist)
        searchDTO1.setCivilId(civilId);
        
        try {
            bsnPagingResponseDTO =
                    (com.beshara.base.paging.impl.PagingResponseDTO)EmpClientFactory.getEmpCandidatesClient().getByHireStageWithPaging(searchDTO1,
                                                                                                                                       searchDTO1.getMangeId());


        } catch (NoResultException ne) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        }

        return bsnPagingResponseDTO;
    }

    //masoud

    public void selectedRadioButton(ActionEvent event) throws Exception {
        super.selectedRadioButton(event);
        System.out.println("test");
        IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        boolean exprince = empCandidatesDTO.isHasExperience();
        setHideHasExprince(exprince);
    }

    public void initIntegration() {
        workDataListBean.setNavigationCase(LIST_PAGE);
        workDataListBean.setBeanName(SELECTION_BEAN_NAME);
        workDataListBean.setBackAction("backFromExperience");
        saveStateObjects();
    }

    public String backFromExperience() {
        getSaveStateObjects();
        fillDataTableByStageId();
        return workDataListBean.getNavigationCase();
    }

    private void saveStateObjects() {
        HashMap hm = workDataListBean.getHmObjects();
        hm.put("calcPageMode", calcPageMode);
        hm.put("selectedStageId", selectedStageId);
        hm.put("selectedCategory", selectedCategory);
        hm.put("selectedMinistry", selectedMinistry);
        hm.put("ministryList", ministryList);
        hm.put("categoryList", categoryList);
        hm.put("selectedDTOS", getSelectedDTOS());
        hm.put("selectedRadio", getSelectedRadio());
        hm.put("currentPageIndex", getCurrentPageIndex());
        hm.put("civilId", getCivilId());
        hm.put("civilExist", civilExist);
        hm.put("validCivilId", validCivilId);
        hm.put("civilName", getCivilName());
        hm.put("hideHasExprince", hideHasExprince);
        
    }

    private void getSaveStateObjects() {
        HashMap hm = workDataListBean.getHmObjects();
        calcPageMode = (String)hm.get("calcPageMode");
        selectedStageId = (String)hm.get("selectedStageId");
        selectedCategory = (String)hm.get("selectedCategory");
        selectedMinistry = (String)hm.get("selectedMinistry");
        ministryList = (List<IBasicDTO>)hm.get("ministryList");
        categoryList = (List<IBasicDTO>)hm.get("categoryList");
        setSelectedDTOS((List)hm.get("selectedDTOS"));
        setSelectedRadio((String)hm.get("selectedRadio"));
        setCurrentPageIndex((Integer)hm.get("currentPageIndex"));
        setCivilId((Long)hm.get("civilId"));
        setCivilExist((Boolean)hm.get("civilExist"));
        setValidCivilId((Boolean)hm.get("validCivilId"));
        setCivilName((String)hm.get("civilName"));
        setHideHasExprince((Boolean)hm.get("hideHasExprince"));
        if (getCivilId() != null && civilExist)
            searchDTO1.setCivilId(civilId);
    }

    public String goexperienceList() {
        initIntegration();
        IEmpCandidatesDTO currentDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (IKwtCitizensResidentsDTO)currentDTO.getCitizensResidentsDTO();

        String key = kwtCitizensResidentsDTO.getCode().getKey();
        String fullName = kwtCitizensResidentsDTO.getFullName();


        workDataListBean.setCivilId(Long.valueOf(key));
        workDataListBean.setCitizinFullName(fullName);
        workDataListBean.setShowBtn(false);
        workDataListBean.setCivilExist(true);
        workDataListBean.setPanelGroupStyleClass(null);

        try {
            workDataListBean.getAll();
        } catch (DataBaseException e) {
            ;
        }
        return "workDataList";
    }

    public void reloadData() {
        ExperienceListBean experienceListBean = (ExperienceListBean)evaluateValueBinding("experienceListBean");
        setSelectedStageId((String)experienceListBean.getSaveStateList().get(0));
    }

    //masoud

    public void setHideHasExprince(boolean hideHasExprince) {
        this.hideHasExprince = hideHasExprince;
    }

    public boolean isHideHasExprince() {
        return hideHasExprince;
    }

    public void setCategoryList(List<IBasicDTO> categoryList) {
        this.categoryList = categoryList;
    }

    //  add filiter by category and ministry

    public List<IBasicDTO> getCategoryList() {
        if (categoryList == null || categoryList.size() == 0) {
            try {
                categoryList = OrgClientFactory.getCatsClient().getCatsByGovFlag(ISystemConstant.GOVERNMENT_FLAG);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }

        return categoryList;
    }

    public void filterByCategory(ActionEvent event) {

        // selectedMinistry = null;
        setSelectedMinistry(null);
        //         generatePagingRequestDTO(SELECTION_BEAN_NAME,
        //                                  "filterDataTableByStageWithPaging");

        //        generatePagingRequestDTO(BEAN_NAME, "filterDataWithPaging");
        //        oldMethodName = "filterDataWithPaging";

        if (selectedCategory != null && !(selectedCategory.equals(""))) {
            try {
                ministryList = OrgClientFactory.getMinistriesClient().getAllByCategory(Long.valueOf(selectedCategory));
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                ministryList = new ArrayList<IBasicDTO>();
            } catch (DataBaseException e) {
                e.printStackTrace();
                ministryList = new ArrayList<IBasicDTO>();
            }
            //            selectedPeroidType = Constants.REG_PERIOD_TYPE_TAWZEEF_MARKAZY.toString();
        } else {
            ministryList = new ArrayList<IBasicDTO>();
            searchDTO1.setMinistryCode(null);
            fillDataTableByStageId();
            //            selectedPeroidType = "";
        }

        //        if (getSelectedDTOS() != null) {
        //            getSelectedDTOS().clear();
        //            setSelectedRadio("");
        //        }

        //        setSearchMode(false);

        //        eduMinstPrivilege =
        //                (selectedMinistry != null && selectedMinistry.equals(getManagedConstantsBean().getEducationMinistryCode().toString()));
        //        peroidTypes = null;

        // fillDataTableByStageId();
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }


    public void filterByMinistry(ActionEvent event) {
        fillDataTableByStageId();
    }
   
    public void setMinistryList(List<IBasicDTO> ministryList) {
        this.ministryList = ministryList;
    }

    public List<IBasicDTO> getMinistryList() {
        return ministryList;
    }


    public void setCalcPageMode(String calcPageMode) {
        this.calcPageMode = calcPageMode;
    }

    public String getCalcPageMode() {
        return calcPageMode;
    }

    public void setCenteralEmployment(String centeralEmployment) {
        this.centeralEmployment = centeralEmployment;
    }

    public String getCenteralEmployment() {
        return centeralEmployment;
    }

    public void setContracts(String contracts) {
        this.contracts = contracts;
    }

    public String getContracts() {
        return contracts;
    }

    public void updatePageMode(ActionEvent ae) {
        ae = null;
        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        if (getCalcPageMode().equals(IEMPConstants.CENTRAL_EMPLOYEMENT.toString())) {
            searchDTO1.setEmpHireTypes(IEMPConstants.CENTRAL_EMPLOYEMENT);
            setContractActive(false);
        } else {
            setContractActive(true);
            searchDTO1.setEmpHireTypes(null);
            reSetData();

        }
        setSelectedStageId(getStageIdJobNameRequired());
        fillDataTableByStageId();
    }

    public String getStageIdReviewInDewan() {
        return IEMPConstants.HIRE_STAGE_REVIEW_ORDER_IN_DEWAN_INPROGRESS;
    }

    public String getStageIdRejectedByJobsArrangement() {
        return IEMPConstants.HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT;
    }


    public String getMinistryWokCenterName() {

        return COLUMN_MINSTRY_NAME;
    }


    public void setContractActive(boolean contractActive) {
        this.contractActive = contractActive;
    }

    public boolean isContractActive() {
        return contractActive;
    }


    //added by M.abdelsabour

    public String viewReviewListActionForCenteralEmployement() {
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)evaluateValueBinding("ceneteralEmpArrang"); 
        IEmpCandidatesDTO empDTO;
        try {
                try {
             empDTO = makeEmployeeDTO(getSelectedDTOS().get(0).getCode());  
            } catch (Exception e) {
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "qul_must_found"));
                return null;
            }
            showSuggestSalaryDegree(reviewListBean, empDTO, 12L); // suggest Salary degree
            showAcceptSalaryDegree(reviewListBean, empDTO, 14L); // accepted Salary Degree
            showJobAndBudgetType(reviewListBean, empDTO); // show budgetType
          
            //            Long civilId = ((IEmployeesEntityKey)empDTO.getCode()).getCivilId();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + getSelectedStageId());
            //            showSuggestSalaryDegree(reviewListBean, empDTO, 12L); // suggest Salary degree
            //            showAcceptSalaryDegree(reviewListBean, empDTO, 14L); // accepted Salary Degree
            //            showJobAndBudgetType(reviewListBean, empDTO); // show budgetType
            reviewListBean.setHasExperience(empDTO.isHasExperience());
            reviewListBean.setHireSystemMode(getCalcPageMode());
            reviewListBean.showViewByStage(Long.parseLong(getSelectedStageId()));
            reviewListBean.setBckBtnNavigationCase("jobsSelectionListPage");
            reviewListBean.getSaveStateList().add(getSelectedStageId());
            // reviewListBean.getSaveStateList().add(getPageId());
            reviewListBean.getSaveStateList().add(getSelectedMinistry());
            reviewListBean.getSaveStateList().add(getSelectedDTOS());
            reviewListBean.getSaveStateList().add(getSelectedRadio());
            reviewListBean.getSaveStateList().add(getCivilId());
            reviewListBean.setBackActionMethodName(SELECTION_BEAN_NAME + ".reloadDataForReviewListCRSBean");
            String temp = empDTO.getEmpExtraDataValueDTO().getSuggestedJobCode();
            IJobsEntityKey entitKey = null;
            IJobsDTO jobDTO = null;
            if (temp != null && !temp.equals(" ")) {
                entitKey = JobEntityKeyFactory.createJobsEntityKey(temp);
                jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                reviewListBean.setJobNameForMin(jobDTO.getName());
            }
            String temp3 = empDTO.getEmpExtraDataValueDTO().getApprovedJob();
            IJobsEntityKey entitKey1 = null;
            IJobsDTO jobDTO1 = null;
            if (temp3 != null && !temp3.equals(" ")) {
                entitKey1 = JobEntityKeyFactory.createJobsEntityKey(temp3);
                jobDTO1 = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey1);
                empDTO.setJobsDTO(jobDTO1);
                reviewListBean.setJobKey(jobDTO1.getJobKey());
            }
            
            IWorkCentersDTO temp1 = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
            if (temp1 != null && !temp1.equals(" ")) {
                reviewListBean.setWorkCenterName(empDTO.getWorkCentersDTO().getName());
            }
            reviewListBean.setPageDTO(empDTO);
            reviewListBean.setPageBeanName(SELECTION_BEAN_NAME);

            reviewListBean.salaryDataSectionController(empDTO.isHasExperience(), getSelectedStageId());
            reviewListBean.setSelectedMinistery(getSelectedMinistry());
            //reviewListBean.populateSalariesData();

            if (getSelectedStageId().equals(String.valueOf(HireStageConst.HIRE_STAGE_JOB_NAME_REQUIRED))) {
                IWorkCentersDTO wrkCenter = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
                if (wrkCenter != null && !wrkCenter.equals(" ")) {
                    IWorkCentersEntityKey key = (IWorkCentersEntityKey)wrkCenter.getCode();
                    reviewListBean.setMinWorkCenter(key.getWrkCode());
                }
            }
            return "arrangCenteralEmployment";
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e);
        } catch (DataBaseException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e);
        }
        return "";

    }
    
    
    public String viewApproveListActionForCenteralEmployement() {
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)evaluateValueBinding("ceneteralEmpArrang");
        IEmpCandidatesDTO empDTO;
        try {
            try{
             empDTO = makeEmployeeDTO(getSelectedDTOS().get(0).getCode());  
            } catch (Exception e) {
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "qul_must_found"));
                return null;
            }
            showSuggestSalaryDegree(reviewListBean, empDTO, 12L); // suggest Salary degree
            showAcceptSalaryDegree(reviewListBean, empDTO, 14L); // accepted Salary Degree
            showJobAndBudgetType(reviewListBean, empDTO); // show budgetType
          
            //            Long civilId = ((IEmployeesEntityKey)empDTO.getCode()).getCivilId();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + getSelectedStageId());
            //            showSuggestSalaryDegree(reviewListBean, empDTO, 12L); // suggest Salary degree
            //            showAcceptSalaryDegree(reviewListBean, empDTO, 14L); // accepted Salary Degree
            //            showJobAndBudgetType(reviewListBean, empDTO); // show budgetType
            reviewListBean.setHasExperience(empDTO.isHasExperience());
            reviewListBean.setHireSystemMode(getCalcPageMode());
            reviewListBean.showViewByStage(Long.parseLong(getSelectedStageId()));
            reviewListBean.setBckBtnNavigationCase("jobsSelectionListPage");
            reviewListBean.getSaveStateList().add(getSelectedStageId());
            // reviewListBean.getSaveStateList().add(getPageId());
            reviewListBean.getSaveStateList().add(getSelectedMinistry());
            reviewListBean.getSaveStateList().add(getSelectedDTOS());
            reviewListBean.getSaveStateList().add(getSelectedRadio());
            reviewListBean.getSaveStateList().add(getCivilId());
            reviewListBean.setBackActionMethodName(SELECTION_BEAN_NAME + ".reloadDataForReviewListCRSBean");
            String temp = empDTO.getEmpExtraDataValueDTO().getSuggestedJobCode();
            IJobsEntityKey entitKey = null;
            IJobsDTO jobDTO = null;
            if (temp != null && !temp.equals(" ")) {
                entitKey = JobEntityKeyFactory.createJobsEntityKey(temp);
                jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                reviewListBean.setJobNameForMin(jobDTO.getName());
            }
            String temp3 = empDTO.getEmpExtraDataValueDTO().getApprovedJob();
            IJobsEntityKey entitKey1 = null;
            IJobsDTO jobDTO1 = null;
            if (temp3 != null && !temp3.equals(" ")) {
                entitKey1 = JobEntityKeyFactory.createJobsEntityKey(temp3);
                jobDTO1 = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey1);
                empDTO.setJobsDTO(jobDTO1);
                reviewListBean.setJobKey(jobDTO1.getJobKey());
            }
            
            IWorkCentersDTO temp1 = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
            if (temp1 != null && !temp1.equals(" ")) {
                reviewListBean.setWorkCenterName(empDTO.getWorkCentersDTO().getName());
            }
            reviewListBean.setPageDTO(empDTO); 
            reviewListBean.setPageBeanName(SELECTION_BEAN_NAME);

            reviewListBean.salaryDataSectionController(empDTO.isHasExperience(), getSelectedStageId());
            reviewListBean.setSelectedMinistery(getSelectedMinistry());
            //reviewListBean.populateSalariesData();

            if (getSelectedStageId().equals(String.valueOf(HireStageConst.HIRE_STAGE_JOB_NAME_REQUIRED))) {
                IWorkCentersDTO wrkCenter = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
                if (wrkCenter != null && !wrkCenter.equals(" ")) {
                    IWorkCentersEntityKey key = (IWorkCentersEntityKey)wrkCenter.getCode();
                    reviewListBean.setMinWorkCenter(key.getWrkCode());
                }
            }
            return "approveListCentralPage";
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e);
        } catch (DataBaseException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e);
        }
        return "";

    }
  

    private void showSuggestSalaryDegree(ReviewListBeanCRS reviewListBean, IEmpCandidatesDTO empDTO,
                                         Long extraDataType) {
        if (empDTO.getEmpCandidateExtraDataList() != null) {
            ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
            try {
                for (IBasicDTO list1 : empDTO.getEmpCandidateExtraDataList()) {
                    EmpCandidateExtraDataDTO list = (EmpCandidateExtraDataDTO)list1;
                    if (extraDataType != null) {
                        if (((EmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(extraDataType)) {
                            //raise
                            reviewListBean.setSuggestedRaiseCode(Long.valueOf(list.getValue()));
                            if (reviewListBean.getSuggestedRaiseCode() != null) {
                                raiseDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedRaiseCode());
                                reviewListBean.setSuggestedRaiseName(String.valueOf(raiseDTO.getCountGuide()));
                            }
                            //degree
                            if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                                reviewListBean.setSuggestedDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getSuggestedDegreeCode() != null) {
                                degreeDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedDegreeCode());
                                reviewListBean.setSuggestedDegreeName(degreeDTO.getName());
                            }
                            //group
                            if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                                reviewListBean.setSuggestedGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getSuggestedGroupCode() != null) {
                                groupDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedGroupCode());
                                reviewListBean.setSuggestedGroupName(groupDTO.getName());
                            }
                            //Cader
                            if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                                reviewListBean.setSuggestedCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                                reviewListBean.setSuggestedCaderName(groupDTO.getParentObject().getName());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void showAcceptSalaryDegree(ReviewListBeanCRS reviewListBean, IEmpCandidatesDTO empDTO,
                                        Long extraDataType) {
        if (empDTO.getEmpCandidateExtraDataList() != null) {
            ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
            try {
                for (IBasicDTO list1 : empDTO.getEmpCandidateExtraDataList()) {
                    EmpCandidateExtraDataDTO list = (EmpCandidateExtraDataDTO)list1;
                    if (extraDataType != null) {
                        if (((EmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(extraDataType)) {
                            //raise
                            reviewListBean.setRaiseCode(list.getValue());
                            if (reviewListBean.getRaiseCode() != null) {
                                raiseDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.parseLong(reviewListBean.getRaiseCode()));
                                reviewListBean.setRaiseName(String.valueOf(raiseDTO.getCountGuide()));
                            }
                            //degree
                            if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                                reviewListBean.setDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getDegreeCode() != null) {
                                degreeDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getDegreeCode());
                                reviewListBean.setDegreeName(degreeDTO.getName());
                            }
                            //group
                            if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                                reviewListBean.setGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
                            }
                            if (reviewListBean.getGroupCode() != null) {
                                groupDTO =
                                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getGroupCode());
                                reviewListBean.setGroupName(groupDTO.getName());
                            }
                            //Cader
                            if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                                reviewListBean.setCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                                reviewListBean.setCaderName(groupDTO.getParentObject().getName());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void showJobAndBudgetType(ReviewListBeanCRS reviewListBean, IEmpCandidatesDTO empDTO) {
        if (empDTO.getEmpCandidateExtraDataList() != null) {
            try {
                boolean existBudgetType = false;
                boolean existJob = false;
                reviewListBean.setShowFatwaNotes(false);
//                String previousNote = ""; 
//                String currentNote="";
//                if( empDTO.getPreviousEmpExtraDataValueDTO() != null && empDTO.getPreviousEmpExtraDataValueDTO().getArrangmentDeptNotes() != null){
//                     previousNote=empDTO.getPreviousEmpExtraDataValueDTO().getArrangmentDeptNotes();
//                    }
//                if( empDTO.getEmpExtraDataValueDTO() != null && empDTO.getEmpExtraDataValueDTO().getArrangmentDeptNotes() != null){
//                     currentNote=empDTO.getEmpExtraDataValueDTO().getArrangmentDeptNotes();
//                    }
//                if(currentNote.trim().equals("") && !previousNote.trim().equals("")){
//                        empDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(previousNote);
//                    }
               
//                               if (empDTO.getPreviousEmpCandidateExtraDataList() != null) {
//                              
//                                   for (IBasicDTO basicDTO : empDTO.getPreviousEmpCandidateExtraDataList()) {
//                                       EmpCandidateExtraDataDTO employeeExtraDataDTO = (EmpCandidateExtraDataDTO)basicDTO;
//                                       if (((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(15L)) {
//                                           IBgtTypesEntityKey entitKey =
//                                               BgtEntityKeyFactory.createBgtTypesEntityKey(Long.valueOf(employeeExtraDataDTO.getValue()));
//                                           IBgtTypesDTO bgtTypesDTO =
//                                               (IBgtTypesDTO)BgtClientFactory.getBgtTypesClient().getById(entitKey);
//                                           //empDTO.setBgtTypesDTO(bgtTypesDTO);
//                                           reviewListBean.setApprovedBgtTypeCode(employeeExtraDataDTO.getValue());
//                                           reviewListBean.setApprovedBgtTypeName(bgtTypesDTO.getName());
//                                           reviewListBean.budgetTypeController(empDTO, getSelectedStageId());
//                                           existBudgetType = true;
//                                       } else if (((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(13L)) {
//                                           IJobsEntityKey entitKey =
//                                               JobEntityKeyFactory.createJobsEntityKey(employeeExtraDataDTO.getValue());
//                                           IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
//                                           empDTO.setJobsDTO(jobDTO);
//                                           reviewListBean.setJobKey(jobDTO.getJobKey());
//                                           existJob = true;
//                                       }else if(((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(8L)) {
//                                          reviewListBean.setFatwaNotes( employeeExtraDataDTO.getValue());
//                                          reviewListBean.setShowFatwaNotes(true);
//                                           }
//                                   }
//                                  
//                           } 
                     if(((IEmpCandidatesEntityKey)(empDTO.getCode())).getCandidateCodeSeq() > 1L){
                if (empDTO.getPreviousEmpCandidateExtraDataList() != null) {
                              
                                   for (IBasicDTO basicDTO : empDTO.getPreviousEmpCandidateExtraDataList()) {
                                       EmpCandidateExtraDataDTO employeeExtraDataDTO = (EmpCandidateExtraDataDTO)basicDTO;
                                       if (((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(15L)) {
                                           IBgtTypesEntityKey entitKey =
                                               BgtEntityKeyFactory.createBgtTypesEntityKey(Long.valueOf(employeeExtraDataDTO.getValue()));
                                           IBgtTypesDTO bgtTypesDTO =
                                               (IBgtTypesDTO)BgtClientFactory.getBgtTypesClient().getById(entitKey);
                                           reviewListBean.setApprovedBgtTypeNamePrevious(bgtTypesDTO.getName());
                                       } else if (((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(13L)) {
                                           IJobsEntityKey entitKey =
                                               JobEntityKeyFactory.createJobsEntityKey(employeeExtraDataDTO.getValue());
                                           IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                                           reviewListBean.setJobKeyPrevious(jobDTO.getJobKey());
                                           reviewListBean.setJobNamePrevious(jobDTO.getName());
                                       }
                                   }
                                  
                           }
                
                     }
                for (IBasicDTO basicDTO : empDTO.getEmpCandidateExtraDataList()) {
                    EmpCandidateExtraDataDTO employeeExtraDataDTO = (EmpCandidateExtraDataDTO)basicDTO;
                    if (((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(15L)) {
                        IBgtTypesEntityKey entitKey =
                            BgtEntityKeyFactory.createBgtTypesEntityKey(Long.valueOf(employeeExtraDataDTO.getValue()));
                        IBgtTypesDTO bgtTypesDTO =
                            (IBgtTypesDTO)BgtClientFactory.getBgtTypesClient().getById(entitKey);
                        //empDTO.setBgtTypesDTO(bgtTypesDTO);
                        reviewListBean.setApprovedBgtTypeCode(employeeExtraDataDTO.getValue());
                        reviewListBean.setApprovedBgtTypeName(bgtTypesDTO.getName());
                        reviewListBean.budgetTypeController(empDTO, getSelectedStageId());
                        existBudgetType = true;
                    } else if (((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(13L)) {
                        IJobsEntityKey entitKey =
                            JobEntityKeyFactory.createJobsEntityKey(employeeExtraDataDTO.getValue());
                        IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                        empDTO.setJobsDTO(jobDTO);
                        reviewListBean.setJobKey(jobDTO.getJobKey());
                        existJob = true;
                    }else if(((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(8L)) {
                       reviewListBean.setFatwaNotes( employeeExtraDataDTO.getValue());
                       reviewListBean.setShowFatwaNotes(true);
                    }else if(((EmpExtraDataTypesEntityKey)employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(345L)) {
                        if(  employeeExtraDataDTO.getValue().equals("1")){
                                reviewListBean.setJobFromMin( true);
                        }else{
                                reviewListBean.setJobFromMin( false);
                            }
                            reviewListBean.jobFromMinAction();
                        }
                }
                if (!existBudgetType) {
                    reviewListBean.setApprovedBgtTypeCode(getVirtualConstValue());
                    reviewListBean.setApprovedBgtTypeName("");
                    reviewListBean.budgetTypeController(empDTO, getSelectedStageId());
                }
                if (!existJob) {
                    empDTO.getJobsDTO().setName(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    // added By M.abdelsabour 23-12-2014

    public void reloadDataForReviewListCRSBean() {
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)evaluateValueBinding("ceneteralEmpArrang");
        setSelectedStageId((String)reviewListBean.getSaveStateList().get(0));
        setSelectedMinistry((String)reviewListBean.getSaveStateList().get(1));
        setSelectedDTOS((List)reviewListBean.getSaveStateList().get(2));
        setSelectedRadio((String)reviewListBean.getSaveStateList().get(3));
        setCivilId((Long)reviewListBean.getSaveStateList().get(4));
            if(getCivilId()!=null && civilExist)
        searchDTO1.setCivilId(civilId);
    }
    
    public void reloadDataForApproveListCRSBean() {
        ReviewListBeanCRS reviewListBean = (ReviewListBeanCRS)evaluateValueBinding("ceneteralEmpArrang");
        setSelectedStageId((String)reviewListBean.getSaveStateList().get(0));
        setSelectedMinistry((String)reviewListBean.getSaveStateList().get(1));
       
      getSelectedDTOS().clear();
      reSetData();
        setCurrentPageIndex(1);
        // setCalcPageMode((String)reviewListBean.getSaveStateList().get(2));
    }

    public String viewDocuments() {
        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
            ViewDocumentsBean viewDocuments = ViewDocumentsBean.getInstance();
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
            List<IEmpCandidateDocumentsDTO>  empCandidateDocumentsList=null;
            try {
            IEmpCandidateDocumentsClient  client=EmpClientFactory.getEmpCandidateDocumentsClient();
              empCandidateDocumentsList=  client.getByCandCodeNew(empCandidatesDTO.getCode());
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            empCandidatesDTO.setEmpCandidateDocumentsList((List)empCandidateDocumentsList);
            viewDocuments.setPageDTO(empCandidatesDTO);
            viewDocuments.getSaveStateList().add(getSelectedStageId());
            viewDocuments.getSaveStateList().add(getSelectedMinistry());
            viewDocuments.getSaveStateList().add(getCalcPageMode());
            viewDocuments.getSaveStateList().add(getSelectedDTOS());
            viewDocuments.getSaveStateList().add(getSelectedRadio());
            viewDocuments.getSaveStateList().add(getCurrentPageIndex());
            viewDocuments.getSaveStateList().add(getCivilId());
            viewDocuments.setBackActionMethodName(SELECTION_BEAN_NAME + ".reloadDataFromViewDocuments");
            viewDocuments.setBckBtnNavigationCase("jobsSelectionListPage");
        }
        return "viewDocumentsPage";
    }

    public void reloadDataFromViewDocuments() {
        ViewDocumentsBean viewDocuments = ViewDocumentsBean.getInstance();
        setSelectedStageId((String)viewDocuments.getSaveStateList().get(0));
        setSelectedMinistry((String)viewDocuments.getSaveStateList().get(1));
        setCalcPageMode((String)viewDocuments.getSaveStateList().get(2));
        setSelectedDTOS((List)viewDocuments.getSaveStateList().get(3)); 
        setSelectedRadio((String)viewDocuments.getSaveStateList().get(4)); 
        setCurrentPageIndex((Integer)viewDocuments.getSaveStateList().get(5)); 
        setCivilId((Long)viewDocuments.getSaveStateList().get(6));
        if(getCivilId()!=null && civilExist)
        searchDTO1.setCivilId(civilId);
        
    }

    public void setSearchDTO1(IEmployeeSearchDTO searchDTO1) {
        this.searchDTO1 = searchDTO1;
    }

    public IEmployeeSearchDTO getSearchDTO1() {
        return searchDTO1;
    }
    
    
    public void resetPageIndexCustom() {

      

            setCurrentPageIndex(getCurrentPageIndex());
            setOldPageIndex(getOldPageIndex());
            getMyDataTable().setFirst((getCurrentPageIndex()-1)*14);

       

    }
    
    
    public void showSearchForEmployeeDiv() {
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        employeeListOfValuesBean.setEmpEmployeeSearchDTO(searchDTO1);
        fillDataTableByStageIdData();
        employeeListOfValuesBean.setEmpListOfValuesStyle("m2mEditDivClass");
        employeeListOfValuesBean.setReturnMethodName("jobsSelectionListBean.returnMethodAction");
        employeeListOfValuesBean.getOkCommandButton().setReRender("mainDataEmpPanel,searchBtnPnlgrd,OperationBar,scriptpanel");
        employeeListOfValuesBean.resetData();
        employeeListOfValuesBean.setResetDivAfterClose(true);
    }
    
    public void returnMethodAction() {
        if (getEmpListOfValues().getSelectedDTOS() != null && getEmpListOfValues().getSelectedDTOS().get(0) != null &&
            getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
            validCivilId = true;
            civilExist = true;
            try {
           IEmpCandidatesDTO empCandidatesDTO=(IEmpCandidatesDTO)getEmpListOfValues().getSelectedDTOS().get(0);
            setCivilId(((IKwtCitizensResidentsDTO) empCandidatesDTO.getCitizensResidentsDTO()).getCivilId());
                checkAvailable();
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
    }
    
    public void checkAvailable() throws DataBaseException {
        validCivilId = false;
        civilExist = false;
        if (getCivilId() != null) {
            civilExist = GlobalValidation.isValidCivilId(civilId);
            if (civilExist) {
                    try {
                        searchDTO1.setCivilId(civilId);
                   fillDataTableByStageId();
                        
                        if(getMyTableData() != null){
                       setCivilName(((IKwtCitizensResidentsDTO)(((IEmpCandidatesDTO)getMyTableData().get(0)).getCitizensResidentsDTO())).getFullName());
                        validCivilId = true;
                        }
                        return; // everything ok
                    } catch (Exception e) {
                        setCivilName(null);
                        validCivilId = false;
                        return;
                    }
                }

               
            }
       


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

    public void setCivilName(String civilName) {
        this.civilName = civilName;
    }

    public String getCivilName() {
        return civilName;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }
    public void reSetData(ActionEvent e){
            e=null;
            civilId=null;
            validCivilId=false;
            civilExist=false;
            civilName="";
            fillDataTableByStageId();
            getSearchDTO1().setCivilId(null);
            searchDTO1.setName(null);
            searchDTO1.setCivilId(null);
        }
    
    
    public void reSetData(){
           
            civilId=null;
            validCivilId=false;
            civilExist=false;
            civilName="";
            getSearchDTO1().setCivilId(null);
            searchDTO1.setName(null);
            searchDTO1.setCivilId(null);
        }
    
    public void fillDataTableByStageIdData() {

        getSelectedMinistry();
        setSorting(false);
        setFullColumnName(null);


        if (getSelectedStageId() != null) {
            if (!getSelectedStageId().equals("")) {
                // if (getSelectedStageId().equals(IEMPConstants.HIRE_STAGE_FIN_DEGREE_REQUIRED_INPROGRESS)) {
                searchDTO1.setExperienceCheck(IEMPConstants.HAS_EXPERIENCE);
                searchDTO1.setMangeId(IEMPConstants.JOBS_ARRANGMENT_MANAGER);
                if (getCalcPageMode().equals(IEMPConstants.CONTRACTS.toString())) {
                    searchDTO1.setEmpHireTypes(null);
                } else {
                    searchDTO1.setEmpHireTypes(Long.valueOf(getCalcPageMode()));
                }

                List parameters = new ArrayList();
                if (getSelectedStageId().equals(HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT_CAND_REJECTED_BY_FATWA)) {
                    parameters.add(HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT);
                    parameters.add(HIRE_STAGE_REJECTED_BY_FATWA);

                    Object[] params = new Object[] { parameters };
                    getPagingRequestDTO().setParams(params);
                } else {
                    parameters.add(getSelectedStageId());
                    Object[] params = new Object[] { parameters };
                    getPagingRequestDTO().setParams(params);
                }
            }
        }
        
        List stagesList = (List)getPagingRequestDTO().getParams()[0];
        setSelectedMinistry(selectedMinistry);
        searchDTO1.setEmpHireStages(stagesList);
        if (getSelectedMinistry() != null && !getSelectedMinistry().equals("")) {
            searchDTO1.setMinistryCode(Long.parseLong(getSelectedMinistry()));
        }
    }
    
    public String addIntegrationDocument() {

        getIntegrationBean().reInitializeBean();
        getIntegrationBean().setNavgationCaseFrom("jobsSelectionListPage");
        getIntegrationBean().setBeanNameFrom("jobsSelectionListBean");
        getIntegrationBean().setActionTo("goAdd");
        
        IEmpCandidatesDTO empDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        Long civilId =Long.valueOf(empDTO.getCitizensResidentsDTO().getCode().getKey());

        Long docTypeCode = IEMPConstants.Attachment_jobArrangement;

        String fullName = empDTO.getCitizensResidentsDTO().getName();
        String docTypeName = getSharedUtil().messageLocator(BUNDLE_NAME, "jobArrangement_attachment");

        getAddDocAttachmentsIntegrationBean().setEmpDTO(empDTO);
        getAddDocAttachmentsIntegrationBean().setCivilId(civilId);
        getAddDocAttachmentsIntegrationBean().setEmpName(fullName);
        getAddDocAttachmentsIntegrationBean().setSelectedDocType(docTypeCode.toString());
        getAddDocAttachmentsIntegrationBean().setDoctypeName(docTypeName);
        getAddDocAttachmentsIntegrationBean().setDisableSelectedDocType(true);
        getAddDocAttachmentsIntegrationBean().setModuleName("emp");
        getAddDocAttachmentsIntegrationBean().addDocAttachment();
        getAddDocAttachmentsIntegrationBean().setForShowAttachmentOnly(false);
        getAddDocAttachmentsIntegrationBean().getSaveStateList().add(getSelectedStageId());
        getAddDocAttachmentsIntegrationBean().getSaveStateList().add(getSelectedMinistry());
        getAddDocAttachmentsIntegrationBean().getSaveStateList().add(getCalcPageMode());
        getAddDocAttachmentsIntegrationBean().getSaveStateList().add(getSelectedDTOS());
        getAddDocAttachmentsIntegrationBean().getSaveStateList().add(getSelectedRadio());
        getAddDocAttachmentsIntegrationBean().getSaveStateList().add(getCurrentPageIndex());
        getAddDocAttachmentsIntegrationBean().getSaveStateList().add(getCivilId());
        getAddDocAttachmentsIntegrationBean().setBackActionMethodName(SELECTION_BEAN_NAME + ".reloadDataFromAddDocuments");
        getAddDocAttachmentsIntegrationBean().setBckBtnNavigationCase("jobsSelectionListPage");
        return "adddocAttachmentsCustom";

    }
    
    private AddDocAttachmentsListBean getAddDocAttachmentsIntegrationBean() {
        return (AddDocAttachmentsListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{addDocAttachmentsListBean}").getValue(FacesContext.getCurrentInstance());

    }
    
    public void reloadDataFromAddDocuments() {
        AddDocAttachmentsListBean addDocAttachmentsListBean = AddDocAttachmentsListBean.getInstance();
        setSelectedStageId((String)addDocAttachmentsListBean.getSaveStateList().get(0));
        setSelectedMinistry((String)addDocAttachmentsListBean.getSaveStateList().get(1));
        setCalcPageMode((String)addDocAttachmentsListBean.getSaveStateList().get(2));
        setSelectedDTOS((List)addDocAttachmentsListBean.getSaveStateList().get(3)); 
        setSelectedRadio((String)addDocAttachmentsListBean.getSaveStateList().get(4)); 
        setCurrentPageIndex((Integer)addDocAttachmentsListBean.getSaveStateList().get(5)); 
        setCivilId((Long)addDocAttachmentsListBean.getSaveStateList().get(6));
        if(getCivilId()!=null && civilExist)
        searchDTO1.setCivilId(civilId);
    }

    
}
