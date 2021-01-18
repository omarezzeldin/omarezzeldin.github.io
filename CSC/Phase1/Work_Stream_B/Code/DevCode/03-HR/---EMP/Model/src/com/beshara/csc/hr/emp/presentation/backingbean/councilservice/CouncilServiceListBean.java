package com.beshara.csc.hr.emp.presentation.backingbean.councilservice;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata.WorkDataListBean;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidateDocumentsClient;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.presentation.backingbean.councilservice.review.CivilServiceReviewBean;
import com.beshara.csc.hr.emp.presentation.backingbean.employmentcycle.EmployeeListOfValues;
import com.beshara.csc.hr.emp.presentation.backingbean.viewdocuments.ViewDocumentsBean;
import com.beshara.csc.inf.business.client.IPersonQualificationsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IPersonQualificationsEntityKey;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.event.ActionEvent;


public class CouncilServiceListBean extends LookUpBaseBean {
    private static final String BEAN_NAME = "councilServiceListBean";
    private static final String LIST_PAGE = "civilServiceList";

    WorkDataListBean workDataListBean = (WorkDataListBean)evaluateValueBinding("workDataListBean");
    @SuppressWarnings("compatibility:271085973111036864")
    private static final long serialVersionUID = 1L;

    private List<IBasicDTO> categoryList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> ministryList = new ArrayList<IBasicDTO>();

    private String selectedCategory ;
    private String selectedMinistry = null ;

    private boolean disabled = true;

    private static final String COLUMN_NAME_HR_EMP_EMPLOYEES_CIVIL_ID = "HR_EMP_EMPLOYEES.CIVIL_ID";
    private static final String COLUMN_NAME_HR_EMP_EMPLOYEES_FULL_NAME = "FULL_NAME";
    private String noKwtCitOnQual;
    private boolean validCivilId = false;
       private boolean civilExist = false;
       private String civilName;
       private Long civilId;
    IEmployeeSearchDTO searchDTO= EmpDTOFactory.createEmployeeSearchDTO();
    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    public CouncilServiceListBean() {
        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setSelectedPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setClient(EmpClientFactory.getEmpCandidatesClient());
        setEmpListOfValues(new EmployeeListOfValues());
        setSaveSortingState(true);
        loadCategories();
    }

    public void initIntegration() {
        workDataListBean.setNavigationCase(LIST_PAGE);
        workDataListBean.setBeanName(BEAN_NAME);
        workDataListBean.setBackAction("backFromExperience");
        saveStateObjects();
    }

    public String backFromExperience() {
        getSaveStateObjects();
        getTotalList();
        return workDataListBean.getNavigationCase();
    }

    private void saveStateObjects() {
        HashMap hm = workDataListBean.getHmObjects();
        hm.put("selectedCategory", selectedCategory);
        hm.put("selectedMinistry", selectedMinistry);
        hm.put("ministryList", ministryList);
        hm.put("categoryList", categoryList);
    }

    private void getSaveStateObjects() {
        HashMap hm = workDataListBean.getHmObjects();
        selectedCategory = (String)hm.get("selectedCategory");
        selectedMinistry = (String)hm.get("selectedMinistry");
        ministryList = (List<IBasicDTO>)hm.get("ministryList");
        categoryList = (List<IBasicDTO>)hm.get("categoryList");
    }


    public static CouncilServiceListBean getInstance() {
        return (CouncilServiceListBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowSearch(false);
        app.setShowbar(true);
        app.setShowpaging(true);
        app.setShowEmpSrchDiv(true);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowdatatableContent(true);
        app.setShowCommonData(false);
        app.setShowshortCut(false);
        return app;
    }

    public List loadCategories() {
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
        //        setSelectedMinistry(null);
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
        } else {
            ministryList = new ArrayList<IBasicDTO>();
        }
    }

    public void filterByMinistry(ActionEvent event) {
        getTotalList();
    }

    public List fillDataTable() {
        IEmpCandidatesClient empCandidatesClient = EmpClientFactory.getEmpCandidatesClient();
        List allCndRequests = null;
        try {
            if(getSelectedMinistry() != null && !getSelectedMinistry().equals("")){
            searchDTO.setMinistryCode(Long.valueOf(getSelectedMinistry()));
            }else{
                    searchDTO.setMinistryCode(null);
                }
            allCndRequests = empCandidatesClient.getAllRequests(searchDTO);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        setMyTableData(allCndRequests);
        return allCndRequests;
    }

    public IPersonQualificationsDTO getLastPersonQualification() {
        IEmpCandidatesDTO currentDTO = (IEmpCandidatesDTO)this.getMyDataTable().getRowData();
        try {
            IEntityKey kwtCitizenEntityKey =
                ((KwtCitizensResidentsEntityKey)currentDTO.getCitizensResidentsDTO().getCode());
            IPersonQualificationsClient personQualificationsClient = InfClientFactory.getPersonQualificationsClient();
            IPersonQualificationsDTO perQualDTO =
                (IPersonQualificationsDTO)personQualificationsClient.getCurrentCentralEmpPersonQul(Long.valueOf(kwtCitizenEntityKey.getKey()));

            countKwtCitizens(perQualDTO);

            return perQualDTO;
        } catch (SharedApplicationException e) {
            return InfDTOFactory.createPersonQualificationsDTO();
        } catch (DataBaseException e) {
            return InfDTOFactory.createPersonQualificationsDTO();
        }
    }

    public void countKwtCitizens(IPersonQualificationsDTO dto) {
        IEmpCandidatesClient empCandClient = EmpClientFactory.getEmpCandidatesClient();
        String qualificationKey = ((IPersonQualificationsEntityKey)dto.getCode()).getQualificationKey();
        try {
            noKwtCitOnQual = empCandClient.countCitizensRegisteredOnQual(Long.valueOf(qualificationKey)).toString();
        } catch (DataBaseException e) {
            ;
        } catch (SharedApplicationException e) {
            ;
        }
    }

    public String goToExperiences() {
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

    public void reloadDataForReviewListBean() {
        CivilServiceReviewBean civilServiceReviewBean =
            (CivilServiceReviewBean)evaluateValueBinding("civilServiceReviewBean");
        setSelectedMinistry((String)civilServiceReviewBean.getSaveStateList().get(0));
    }

    public String reviewRequest() {

        CivilServiceReviewBean civilServiceReviewBean =
            (CivilServiceReviewBean)evaluateValueBinding("civilServiceReviewBean");
        IEmpCandidatesDTO empDTO;
            try{
            empDTO = makeEmployeeDTO(getSelectedDTOS().get(0).getCode());
            } catch (Exception e) {
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "qul_must_found"));
                return null;
            }
            civilServiceReviewBean.setPageDTO(empDTO);
            civilServiceReviewBean.initiateData();
            civilServiceReviewBean.setBckBtnNavigationCase("civilServiceList");
            civilServiceReviewBean.getSaveStateList().add(getSelectedMinistry());
            civilServiceReviewBean.setBackActionMethodName(BEAN_NAME + ".reloadDataForReviewListBean");
            IWorkCentersDTO temp1 = (IWorkCentersDTO)empDTO.getWorkCentersDTO();
            if (temp1 != null && !temp1.equals(" ")) {
                civilServiceReviewBean.setWorkCenterName(empDTO.getWorkCentersDTO().getName());
            }

            civilServiceReviewBean.setPageBeanName(BEAN_NAME);
            civilServiceReviewBean.setSelectedMinistery(getSelectedMinistry());

            return "civilServiceReviewList";
      

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

    public List getTotalList() {
        return fillDataTable();
    }

    public void setCategoryList(List<IBasicDTO> categoryList) {
        this.categoryList = categoryList;
    }

    public List<IBasicDTO> getCategoryList() {
        return categoryList;
    }

    public void setMinistryList(List<IBasicDTO> ministryList) {
        this.ministryList = ministryList;
    }

    public List<IBasicDTO> getMinistryList() {
        return ministryList;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

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

    public String getNoKwtCitOnQual() {
        return noKwtCitOnQual;
    }

    public void setNoKwtCitOnQual(String noKwtCitOnQual) {
        this.noKwtCitOnQual = noKwtCitOnQual;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
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
            viewDocuments.getSaveStateList().add(getSelectedMinistry());
            viewDocuments.setBackActionMethodName(BEAN_NAME + ".reloadDataFromViewDocuments");
            viewDocuments.setBckBtnNavigationCase("civilServiceList");
        }
        return "viewDocumentsPage";
    }

    public void reloadDataFromViewDocuments() {
        ViewDocumentsBean viewDocuments = ViewDocumentsBean.getInstance();
        setSelectedMinistry((String)viewDocuments.getSaveStateList().get(0));
    }

    public void showSearchForEmployeeDiv() {
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        employeeListOfValuesBean.setUseCustomSearch(true);
        employeeListOfValuesBean.setEmpEmployeeSearchDTO(searchDTO);
        employeeListOfValuesBean.setSearchMethod("councilServiceListBean.fillDataTable");
        employeeListOfValuesBean.setEmpListOfValuesStyle("m2mEditDivClass");
        employeeListOfValuesBean.setReturnMethodName("councilServiceListBean.returnMethodAction");
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
                        searchDTO.setCivilId(civilId);
                        getTotalList();
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

    public void setSearchDTO(IEmployeeSearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public IEmployeeSearchDTO getSearchDTO() {
        return searchDTO;
    }
    public void reSetData(ActionEvent e){
            e=null;
            civilId=null;
            validCivilId=false;
            civilExist=false;
            civilName="";
            getSearchDTO().setCivilId(null);
            searchDTO.setName(null);
            searchDTO.setCivilId(null);
            getTotalList();
        }
}
