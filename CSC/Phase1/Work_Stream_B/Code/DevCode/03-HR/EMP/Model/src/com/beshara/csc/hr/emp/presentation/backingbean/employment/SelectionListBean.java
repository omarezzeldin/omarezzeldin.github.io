package com.beshara.csc.hr.emp.presentation.backingbean.employment;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmpExtraDataTypesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.employment.experiencelist.ExperienceListBean;
import com.beshara.csc.hr.emp.presentation.backingbean.employment.shared.ReviewListBean;
import com.beshara.csc.hr.emp.presentation.backingbean.employment.shared.config.HireStageConst;
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

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;


public class SelectionListBean extends LookUpBaseBean {

    private static final String SELECTION_BEAN_NAME = "selectionMinListBean";

    private static final String COLUMN_NAME_HR_EMP_EMPLOYEES_CIVIL_ID = 
        "HR_EMP_EMPLOYEES.CIVIL_ID";
    private static final String COLUMN_NAME_HR_EMP_EMPLOYEES_FULL_NAME = 
        "FULL_NAME";

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

    private final Long DEWAN_CODE = new Long(13);

    private static final String HIRE_STAGE_COMPLETING_JOB_NAME = "9";
    private static final String HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT = "14";
    private static final String HIRE_STAGE_REJECTED_BY_FATWA = "16";

    private static final String HIRE_STAGE_JOB_NAME_ACCEPTRD = "13";
    private static final String HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED = 
        "15";

    private static final String HIRE_STAGE_FIN_DEGREE_REQUIRED = "10";
    private static final String HIRE_STAGE_JOB_NAME_REQUIRED = "11";

    private static final String PAGE_ID_FROM_CRS = "1";
    private static final String PAGE_ID_JOB_NAME_AND_FIN_DEGREE_ACCEPTED = "2";
    private static final String PAGE_ID_JOB_NAME_REQUIRED = "3";
    private static final String PAGE_ID_FIN_DEGREE_REQUIRED = "4";
    
    private static final String ACCEPT_JOB_AND_NAME_IN_PROGRESS = "9111013";
    private static final String REJECT_ORDER_TO_WORK_MINISTRY = "121416";
    //  change Request update HIRE_SUGG
    private static final String HIRE_SUGG_JOB_AND_DEGREE = "21";
    
    private String hireSuggJobandDegree =  HIRE_SUGG_JOB_AND_DEGREE;//IEMPConstants.HIRE_STAGE_SUGGEST_JOB_DEGREE_AND_NAME_INPROGRESS;
    private String hireStageJobNameAndFinDegree = IEMPConstants.HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED;
    private String acceptJobAndNameInPro = ACCEPT_JOB_AND_NAME_IN_PROGRESS; 
    private String rejectOrderToWorkMinistry =  REJECT_ORDER_TO_WORK_MINISTRY;
    
    private String selectedStageId;
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;

//    private List minstriesList;
//    private String selectedMinistry = "" + DEWAN_CODE;

    private String pageId;
    private boolean hideHasExprince;

     /// add filiter by ministry and categrory
     private List<IBasicDTO> categoryList = new ArrayList<IBasicDTO>();
     private List<IBasicDTO> ministryList = new ArrayList<IBasicDTO>();
     
     
    private String oldMethodName;
//    protected static final String BEAN_NAME = "selectionListBean";
    private String selectedCategory;
    private String selectedMinistry;

     public SelectionListBean() {

        setUsingPaging(true);
        setUsingBsnPaging(true);

        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setSaveSortingState(true);
        super.setSelectedPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setClient((IEmpCandidatesClient)EmpClientFactory.getEmpCandidatesClient());

        setDivMainContent("divContent1Fixed");

        pageId = 
                String.valueOf(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("pageId"));
        if (pageId != null) {

            if (pageId.equals(PAGE_ID_FROM_CRS)) {
               // setSelectedStageId(IEMPConstants.HIRE_STAGE_SUGGEST_JOB_DEGREE_AND_NAME_INPROGRESS);
                setSelectedStageId(HIRE_SUGG_JOB_AND_DEGREE);
            } 
        }
    }


    public void reloadDataForReviewListBean() {
        ReviewListBean reviewListBean = 
            (ReviewListBean)evaluateValueBinding("reviewMinListBean");
        setSelectedStageId((String)reviewListBean.getSaveStateList().get(0));
        setPageId((String)reviewListBean.getSaveStateList().get(1));
        setSelectedMinistry((String)reviewListBean.getSaveStateList().get(2));
    }   


    public String viewReviewListAction() {
        ReviewListBean reviewListBean = 
            (ReviewListBean)evaluateValueBinding("reviewMinListBean");
        IEmpCandidatesDTO empDTO;
        try {
            empDTO= makeEmployeeDTO(getSelectedDTOS().get(0).getCode());
            reviewListBean.setPageDTO(empDTO);
            if(empDTO.getEmpCandidateExtraDataList()!=null && empDTO.getEmpCandidateExtraDataList().size()> 0){
                ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
                ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
                ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
                ISalElementGuidesDTO caderDTO = SalDTOFactory.createSalElementGuidesDTO();
                try{
                   Long civilId = Long.valueOf(empDTO.getCitizensResidentsDTO().getCode().getKey());
//                    Long civilId =((IEmployeesEntityKey)empDTO.getCode()).getCivilId();
                    for(IBasicDTO list1 : empDTO.getEmpCandidateExtraDataList()){
                        IEmpCandidateExtraDataDTO list = (IEmpCandidateExtraDataDTO)list1;
                        // where suggestedCader equal 12L get suggestedcader , suggestedGroup and suggestedRaise depend on suggestedRaise value in extraData table  
                        if(((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_CADER_SUGGESTED)){
                            //raise
                            reviewListBean.setSuggestedRaiseCode(list.getValue());  
                            if (reviewListBean.getSuggestedRaiseCode() != null) {
                                raiseDTO =  (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.valueOf(reviewListBean.getSuggestedRaiseCode()));
                                 //reviewListBean.getRaiseList().add(raiseDTO);
                                 reviewListBean.setSuggestedRaiseName(String.valueOf(raiseDTO.getCountGuide()));
                             }
                            //degree
                            if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                                 reviewListBean.setSuggestedDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode());
                             }
                            if (reviewListBean.getSuggestedDegreeCode() != null) {
                                 degreeDTO = (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedDegreeCode());
                                  reviewListBean.setSuggestedDegreeName(degreeDTO.getName());
                              }
                            //group
                            if (degreeDTO.getParentObject() != null &&  degreeDTO.getParentObject().getCode() != null) {
                                 reviewListBean.setSuggestedGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
                             }
                              if (reviewListBean.getSuggestedGroupCode() != null) {
                                  groupDTO =  (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedGroupCode());
                                  //reviewListBean.getGroupList().add(groupDTO);
                                  reviewListBean.setSuggestedGroupName(groupDTO.getName());
                              }
                            //Cader
                             if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                                  reviewListBean.setSuggestedCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                                   if(reviewListBean.getSuggestedCaderCode()!=null){
                                        caderDTO =  (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getSuggestedCaderCode()); 
                                   //reviewListBean.getCaderList().add(caderDTO);
                                   reviewListBean.setSuggestedCaderName(groupDTO.getParentObject().getName());
                                    } 
                                  }
                            // where acceptedCader equal 14L get acceptedcader , acceptedGroup and acceptedRaise depend on acceptedRaise value in extraData table  
                        }else if(((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_CADER_ACCEPTED)){
                            //raise
                            reviewListBean.setAcceptedRaiseCode(Long.valueOf(list.getValue()));  
                            if (reviewListBean.getAcceptedRaiseCode() != null) {
                                raiseDTO =  (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getAcceptedRaiseCode());
                                 //reviewListBean.getRaiseList().add(raiseDTO);
                                  reviewListBean.setAcceptedRaiseName(String.valueOf(raiseDTO.getCountGuide()));
                             }
                            //degree
                            if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                                 reviewListBean.setAcceptedDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode());
                             }
                            if (reviewListBean.getAcceptedDegreeCode() != null) {
                                 degreeDTO = (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getAcceptedDegreeCode());
                                 reviewListBean.setAcceptedDegreeName(degreeDTO.getName());
                              }
                            //group
                            if (degreeDTO.getParentObject() != null &&  degreeDTO.getParentObject().getCode() != null) {
                                 reviewListBean.setAcceptedGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
                             }
                              if (reviewListBean.getAcceptedGroupCode() != null) {
                                  groupDTO =  (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getAcceptedGroupCode());
                                 // reviewListBean.getGroupList().add(groupDTO);
                                  reviewListBean.setAcceptedGroupName(groupDTO.getName());
                              }
                            //Cader
                             if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                                  reviewListBean.setAcceptedCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                                   if(reviewListBean.getAcceptedCaderCode()!=null){
                                        caderDTO =  (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(reviewListBean.getAcceptedCaderCode()); 
                                   //reviewListBean.getCaderList().add(caderDTO);
                                   reviewListBean.setAcceptedCaderName(groupDTO.getParentObject().getName());
                                    } 
                                  }
                            // where suggestedJob equal 4L get suggestedJobCode , suggestedJobName  depend on suggestedJobCode value in extraData table       
                        }else if(((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_JOB_SUGGESTED)){
                            //jibDTO
                                reviewListBean.setSuggestedJobCode(list.getValue());
                                IJobsEntityKey entitKey = JobEntityKeyFactory.createJobsEntityKey(list.getValue());
                                IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                               //reviewListBean.getJobList().add(jobDTO);
                               reviewListBean.setSuggestedJobValue(jobDTO.getName());
                               reviewListBean.setJobKey(jobDTO.getJobKey());
                            // where acceptedJob equal 4L get acceptedJobCode , acceptedJobName  depend on acceptedJobCode value in extraData table       
                        }else if(((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_JOB_ACCEPTED)){
                            //jobDTO
                                reviewListBean.setAcceptedJobCode(list.getValue());
                                IJobsEntityKey entitKey = JobEntityKeyFactory.createJobsEntityKey(list.getValue());
                                IJobsDTO jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
                               //reviewListBean.getJobList().add(jobDTO);
                               reviewListBean.setJobKey(jobDTO.getJobKey());
                               reviewListBean.setAcceptedJobValue(jobDTO.getName());
                            // where acceptedBGT equal 15L get acceptedBgtTypesDTO depend on BgtTypesCode value in extraData table        
                        }else if(((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_BGT_TYPE)){
                            //BgtTypesDTO
                                IBgtTypesEntityKey entitKey = BgtEntityKeyFactory.createBgtTypesEntityKey(Long.valueOf(list.getValue()));
                                IBgtTypesDTO bgtTypesDTO = (IBgtTypesDTO)BgtClientFactory.getBgtTypesClient().getById(entitKey);
                               //reviewListBean.getJobList().add(jobDTO);
                               reviewListBean.setBgtTypesDTO(bgtTypesDTO);
                               
                        }else if(((IEmpExtraDataTypesEntityKey)list.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode().equals(IEMPConstants.EX_DATA_MIN_NOTES)){
                                                        
                                          ((IEmpCandidatesDTO)reviewListBean.getPageDTO()).getEmpExtraDataValueDTO().setMinistryNotes(list.getValue());                                                               
                            
                        }
                           
                            
                        } 
                    }
               catch(Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + 
                               getSelectedStageId());
            reviewListBean.showViewByStage(Long.parseLong(getSelectedStageId()));
            //reviewListBean.setBckBtnNavigationCase("selectionMinListPage");
            reviewListBean.setBckBtnNavigationCase("backToMinSelection");
            reviewListBean.getSaveStateList().add(getSelectedStageId());
            reviewListBean.getSaveStateList().add(getPageId());
            reviewListBean.getSaveStateList().add(getSelectedMinistry());
            reviewListBean.setBackActionMethodName(SELECTION_BEAN_NAME+".reloadDataForReviewListBean");
//            String temp =empDTO.getEmpExtraDataValueDTO().getSuggestedJobCode();
//            IJobsEntityKey entitKey = null;
//            IJobsDTO jobDTO = null; 
//            if(temp != null && !temp.equals(" ")){
//                entitKey = JobEntityKeyFactory.createJobsEntityKey(temp);
//                jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(entitKey);
//                reviewListBean.setJobNameForMin(jobDTO.getName());
//            }
           IWorkCentersDTO temp1 = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
            if(temp1 != null && !temp1.equals(" ")){
            reviewListBean.setWorkCenterName(empDTO.getWorkCentersDTO().getName());
            }
            reviewListBean.setPageBeanName(SELECTION_BEAN_NAME);
           // reviewListBean.budgetTypeController(empDTO.getBgtTypesDTO(),getSelectedStageId());
            reviewListBean.salaryDataSectionController(empDTO.isHasExperience(),getSelectedStageId());
            reviewListBean.setSelectedMinistery(getSelectedMinistry());
          // reviewListBean.populateSalariesData();
            
            if(getSelectedStageId().equals(String.valueOf(HireStageConst.HIRE_STAGE_JOB_NAME_REQUIRED))){
                IWorkCentersDTO  wrkCenter = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
                IWorkCentersEntityKey key = (IWorkCentersEntityKey)wrkCenter.getCode();
               reviewListBean.setMinWorkCenter(key.getWrkCode());
            }
            return "reviewMinRequestPage";
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e);
        } catch (DataBaseException e) {
             e.printStackTrace();
             getSharedUtil().handleException(e);
        }
        return "";

    }
    
    // to get cader group 
    public void getCaderGroupDegree(IEmployeesDTO empDTO){
        
        
    }

    private IEmpCandidatesDTO makeEmployeeDTO(IEntityKey key) throws DataBaseException, 
                                          SharedApplicationException {
        IEmpCandidatesDTO empDTO;
        empDTO =
                (IEmpCandidatesDTO)EmpClientFactory.getEmpCandidatesClient().getByCndCodeAndSequnc(key);

        
        IPersonQualificationsDTO personQualificationsDTO =
            (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getCurrentCentralEmpPersonQul(Long.valueOf(empDTO.getCitizensResidentsDTO().getCode().getKey()));
        ((IKwtCitizensResidentsDTO)empDTO.getCitizensResidentsDTO()).setPersonQualificationsDTOList(new ArrayList<IPersonQualificationsDTO>());
        ((IKwtCitizensResidentsDTO)empDTO.getCitizensResidentsDTO()).getPersonQualificationsDTOList().add(personQualificationsDTO);
        
         return empDTO;
    }


    public AppMainLayout appMainLayoutBuilder() {

        AppMainLayout app = new AppMainLayout();

        app.setShowdatatableContent(true);
        app.setShowContent1(true);
        app.setShowpaging(true);
        app.setShowbar(true);

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

        IPagingResponseDTO bsnResponseDTO = 
            getDataByStageIdWithPaging(pagingRequest);

        PagingResponseDTO pagingResponseDTO = null;

        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = 
                    new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());
            if (getCurrentPageIndex() == 1) {
                pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                getPagingRequestDTO().setParams(new Object[] { pagingRequest.getParams()[0], 
                                                               bsnResponseDTO.getCount() });
            } else {
                pagingResponseDTO.setTotalListSize(((Long)pagingRequest.getParams()[1]).intValue());
            }
        }
        return pagingResponseDTO;
    }
    
     
    public void fillDataTableByStageId() {

        setUpdateMyPagedDataModel(true);
        getSelectedMinistry();
        setSorting(false);
        setFullColumnName(null);
        
        resetPageIndex();

        if (getSelectedStageId() != null && !getSelectedStageId().equals("")) {
            
            if(getSelectedDTOS().size() > 0)
                getSelectedDTOS().clear();
                
            generatePagingRequestDTO(SELECTION_BEAN_NAME, 
                                     "filterDataTableByStageWithPaging");
            List parameters = new ArrayList();
            
            if(getSelectedStageId().equals(ACCEPT_JOB_AND_NAME_IN_PROGRESS)){
                    parameters.add(HIRE_STAGE_COMPLETING_JOB_NAME);
                    parameters.add(HIRE_STAGE_FIN_DEGREE_REQUIRED);
                    parameters.add(HIRE_STAGE_JOB_NAME_REQUIRED);
                    parameters.add(HIRE_STAGE_JOB_NAME_ACCEPTRD);    
                
                    Object[] params = new Object[] { parameters };
                    getPagingRequestDTO().setParams(params);
                }  
            ////
            if(getSelectedStageId().equals(REJECT_ORDER_TO_WORK_MINISTRY)){
                    parameters.add(IEMPConstants.HIRE_STAGE_DEMAND_RESPONSE_FROM_DEPARTMENT_OF_CHOICE);
//                    parameters.add(IEMPConstants.HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT);
//                   parameters.add(IEMPConstants.HIRE_STAGE_REJECTED_BY_FATWA);               
                    Object[] params = new Object[] { parameters };
                    getPagingRequestDTO().setParams(params);
                }  
            /////
            if(getSelectedStageId().equals(HIRE_SUGG_JOB_AND_DEGREE)){
                parameters = new ArrayList();
                parameters.add(IEMPConstants.HIRE_STAGE_RELEASE_DECISION_INPROGRESS);
                parameters.add(IEMPConstants.HIRE_STAGE_COMPLETING_INFO_INPROGRESS);
            Object[] params = new Object[] { parameters };
                getPagingRequestDTO().setParams(params);
            }
            if(getSelectedStageId().equals(IEMPConstants.HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED)){
                parameters = new ArrayList();
                parameters.add(hireStageJobNameAndFinDegree);   
            Object[] params = new Object[] { parameters };
                getPagingRequestDTO().setParams(params);
            }
            
        }
    }

    public IPersonQualificationsDTO getLastPersonQualification() {
        IEmpCandidatesDTO currentDTO = 
            (IEmpCandidatesDTO)this.getMyDataTable().getRowData();
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
        return HIRE_STAGE_COMPLETING_JOB_NAME;
    }

    public String getStageIdRejectedByFatwaDept() {
        return HIRE_STAGE_REJECTED_BY_FATWA;
    }

    public String getStageIdRejectedByJobsArrangementDept() {
        return HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT;
    }

    public String getStageIdJobNameAccepted() {
        return HIRE_STAGE_JOB_NAME_ACCEPTRD;
    }

    public String getStageIdJobNameAndFinDegreeAccepted() {
        return HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED;
    }

    public String getStageIdJobNameRequired() {
        return HIRE_STAGE_JOB_NAME_REQUIRED;
    }

    public String getStageIdFinDegreeRequired() {
        return HIRE_STAGE_FIN_DEGREE_REQUIRED;
    }

    public String getPageIdFromCRS() {
        return PAGE_ID_FROM_CRS;
    }

    public String getPageIdJobNameAndFinDegreeAccepted() {
        return PAGE_ID_JOB_NAME_AND_FIN_DEGREE_ACCEPTED;
    }

    public String getPageIdJobNameRequired() {
        return PAGE_ID_JOB_NAME_REQUIRED;
    }

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

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageId() {
        return pageId;
    }

    private com.beshara.base.paging.impl.PagingResponseDTO getDataByStageIdWithPaging(PagingRequestDTO pagingRequestDTO) {

        int pageIndex = getCurrentPageIndex();

        com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO = 
            new com.beshara.base.paging.impl.PagingRequestDTO();

        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));

        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));

        if (pageIndex == 1) {
            bsnPagingRequestDTO.setCountRequired(true);
        }

        if (isSorting()) {
            bsnPagingRequestDTO.setSortAscending(pagingRequestDTO.isSortAscending());
            List<String> sortingColumnList = new ArrayList<String>();
            if (pagingRequestDTO.getBsnSortingColumnName().equals(COLUMN_NAME_HR_EMP_EMPLOYEES_CIVIL_ID)) {
                sortingColumnList.add(pagingRequestDTO.getBsnSortingColumnName());
            } else {
                sortingColumnList.add(COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_FIRST_NAME);
                sortingColumnList.add(COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_SECOND_NAME);
                sortingColumnList.add(COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_THIRD_NAME);
                sortingColumnList.add(COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_LAST_NAME);
                sortingColumnList.add(COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_FAMILY_NAME);
            }
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }

//        String stageId = (String)pagingRequestDTO.getParams()[0];
        List stagesList = (List) pagingRequestDTO.getParams()[0];
        IEmployeeSearchDTO searchDTO = EmpDTOFactory.createEmployeeSearchDTO();
//        searchDTO.setEmpHireStages(Long.parseLong(stageId));
//        searchDTO.setEmpHireTypes(IEMPConstant.EMP_HIRE_TYPE_CSC);
        searchDTO.setEmpHireTypes(IEMPConstants.EMP_CANDIDATE_HIRE_TYPE);
        searchDTO.setEmpHireStatus(IEMPConstants._EMP_CENTERAL_HIRE_TYPE);
        searchDTO.setActiveFlag(IEMPConstants.EMP_ACTIVE_FLAG);
        searchDTO.setEmpHireStages(stagesList);
        searchDTO.setPagingRequestDTO(bsnPagingRequestDTO);
        searchDTO.setMinistryCode(getLoggedInMinistry());
        /*if(getSelectedMinistry() != null && !getSelectedMinistry().equals("") ){
        searchDTO.setMinistryCode(Long.parseLong(getSelectedMinistry()));
        }*/
        try {
            bsnPagingResponseDTO = 
                    (com.beshara.base.paging.impl.PagingResponseDTO)EmpClientFactory.getEmpCandidatesClient().getByHireStageWithPaging(searchDTO);
        }catch (NoResultException ne) {
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e);
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e);
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        }

        return bsnPagingResponseDTO;
    }
    
    public Long getLoggedInMinistry(){
        return CSCSecurityInfoHelper.getLoggedInMinistryCode() == null ? DEWAN_CODE :CSCSecurityInfoHelper.getLoggedInMinistryCode();
    }

    //masoud

    public void selectedRadioButton(ActionEvent event) throws Exception {
        super.selectedRadioButton(event);
        System.out.println("test");
        IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        boolean exprince = empCandidatesDTO.isHasExperience();
        setHideHasExprince(exprince);
    }

    public String goexperienceList() {
        ExperienceListBean experienceListBean = 
            (ExperienceListBean)evaluateValueBinding("experienceMinListBean");
        IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
        Long civilId = Long.valueOf(empCandidatesDTO.getCitizensResidentsDTO().getCode().getKey());
        experienceListBean.setCivileId(civilId);
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = 
            (IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO();
        experienceListBean.setEmployeeName(kwtCitizensResidentsDTO.getFullName());
        experienceListBean.setBackActionMethodName("selectionMinListBean.reloadData");
        experienceListBean.setBackBtnNavigationCase("backToMinSelection");
        experienceListBean.getSaveStateList().add(getSelectedStageId());
        experienceListBean.getSaveStateList().add(getPageId());

        return "experienceMinList";
    }

    public void reloadData() {
        ExperienceListBean experienceListBean = 
            (ExperienceListBean)evaluateValueBinding("experienceMinListBean");
        setSelectedStageId((String)experienceListBean.getSaveStateList().get(0));
        setPageId((String)experienceListBean.getSaveStateList().get(1));
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
                categoryList = 
                        OrgClientFactory.getCatsClient().getCatsByGovFlag(ISystemConstant.GOVERNMENT_FLAG);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }

        return categoryList;
    }

     public void filterByCategory(ActionEvent event) {
//        selectedMinistry = null;
//         generatePagingRequestDTO(SELECTION_BEAN_NAME, 
//                                  "filterDataTableByStageWithPaging");

//        generatePagingRequestDTO(BEAN_NAME, "filterDataWithPaging");
//        oldMethodName = "filterDataWithPaging";

            if (selectedCategory != null && !(selectedCategory.equals(""))) {
            try {
                ministryList = 
                        OrgClientFactory.getMinistriesClient().getAllByCategory(Long.valueOf(selectedCategory));
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
     }
     
    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }
    
    
    public void filterByMinistry(ActionEvent event) {
        if (selectedMinistry != null && !(selectedCategory.equals(""))) 
          // && selectedMinistry.equals(getManagedConstantsBean().getEducationMinistryCode().toString())) {

        fillDataTableByStageId();
//            eduMinstPrivilege = true;
//            if (peroidTypes != null && peroidTypes.size() == 1) {
//                IBasicDTO type2 = null;
//                try {
//                    type2 = 
//                            CrsClientFactory.getRegPeriodTypesClient().getById(CrsEntityKeyFactory.createRegPeriodTypesEntityKey(Constants.REG_PERIOD_TYPE_WAZART_EL_TARBIA));
//                } catch (SharedApplicationException e) {
//                    e.printStackTrace();
//                } catch (DataBaseException e) {
//                    e.printStackTrace();
//                }
//                getPeroidTypes().add(type2);
//            }
//        } else {
//            eduMinstPrivilege = false;
//            peroidTypes = null;
//            selectedPeroidType = 
//                    Constants.REG_PERIOD_TYPE_TAWZEEF_MARKAZY.toString();
//        }
//
//        filterByPeriod(event);
    }
    
    public void setMinistryList(List<IBasicDTO> ministryList) {
        this.ministryList = ministryList;
    }

    public List<IBasicDTO> getMinistryList() {
        return ministryList;
    }

    public void setHireSuggJobandDegree(String hireSuggJobandDegree) {
        this.hireSuggJobandDegree = hireSuggJobandDegree;
    }

    public String getHireSuggJobandDegree() {
        return hireSuggJobandDegree;
    }

    public void setHireStageJobNameAndFinDegree(String hireStageJobNameAndFinDegree) {
        this.hireStageJobNameAndFinDegree = hireStageJobNameAndFinDegree;
    }

    public String getHireStageJobNameAndFinDegree() {
        return hireStageJobNameAndFinDegree;
    }

    public void setAcceptJobAndNameInPro(String acceptJobAndNameInPro) {
        this.acceptJobAndNameInPro = acceptJobAndNameInPro;
    }

    public String getAcceptJobAndNameInPro() {
        return acceptJobAndNameInPro;
    }

    public void setRejectOrderToWorkMinistry(String rejectOrderToWorkMinistry) {
        this.rejectOrderToWorkMinistry = rejectOrderToWorkMinistry;
    }

    public String getRejectOrderToWorkMinistry() {
        return rejectOrderToWorkMinistry;
    }
}
