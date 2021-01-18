package com.beshara.csc.hr.emp.presentation.backingbean.externalexperience;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExternalExperienceDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExternalExperienceSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBeanLocal;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;

import java.util.ArrayList;
import java.util.List;


public class ListBean extends LookUpBaseBeanLocal {

    private Long civilId;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private boolean validEmpStatus = true;
    private IPersonQualificationsDTO personQulDTO = 
        InfDTOFactory.createPersonQualificationsDTO();
    private static final String SEARCH_BY_CODE = "0";
    private static final String SEARCH_BY_NAME = "1";
    private boolean enableEmpLovDiv;
    private String empName;
    private static final String BEAN_NAME = "externalExperienceListBean";
    private IEmployeesDTO emp = EmpDTOFactory.createEmployeesDTO();
    private IEmpExternalExperienceSearchDTO searchDTO = 
        EmpDTOFactory.createEmpEmpExternalExperienceSearchDTO();

    public ListBean() {
        setClient(EmpClientFactory.getEmpExternalExperienceClient());
        setPageDTO(EmpDTOFactory.createEmpExternalExperienceDTO());
        setSelectedPageDTO(EmpDTOFactory.createEmpExternalExperienceDTO());
        this.setEmpListOfValues(new EmployeeListOfValues());
        setEmpListOfValues(new EmployeeListOfValues());
        setSaveSortingState(true);
        setDivMainContent("divContentExternalTable");
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowEmpSrchDiv(true);
        return app;
    }

    public void init() {

        if ((civilId != null)) {
            IEmployeesDTO empDTO = null;
            {
                validEmpStatus = true;
                getHighlightedDTOS().clear();
                if (GlobalValidation.isValidCivilId(civilId)) {
                    validCivilId = true;
                    civilExist = true;
                    empDTO = getEmployeesDTO(Long.valueOf(civilId));
                } else {
                    validCivilId = false;
                    civilExist = false;
                }
            }
            if (empDTO != null) {
                setEmp(empDTO);
                try {
                    getAll();
                    repositionPage(0);
                    setEmpName(((IKwtCitizensResidentsDTO)empDTO.getCitizensResidentsDTO()).getFullName());
                } catch (Throwable e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private IEmployeesDTO getEmployeesDTO(Long empCivilId) {
        IEmployeesDTO empDTO = null;
        try {
            {
                IEmpEmployeeSearchDTO searchDTO = 
                    EmpDTOFactory.createEmpEmployeeSearchDTO();
                //searchDTO.setEmployment(true);
                searchDTO.setCivilId(Long.valueOf(empCivilId));
                searchDTO.setEmpName(null);
                empDTO = 
                        (IEmployeesDTO)EmpClientFactory.getEmployeesClient().simpleSearch(searchDTO).get(0);
            }
        } catch (Throwable e) {
            civilExist = false;
            validEmpStatus = false;
            e.printStackTrace();
        }
        return empDTO;
    }

    public void reSetData() {
        setEmp(EmpDTOFactory.createEmployeesDTO());
        setCivilId(null);
        validCivilId = true;
        civilExist = false;
        validEmpStatus = true;
        personQulDTO = InfDTOFactory.createPersonQualificationsDTO();
        try {
            getAll();
        } catch (Exception e) {
           e.printStackTrace();
        }
        setPageMode(0);
    }

    public void showSearchForEmployeeDiv() {
        EmployeeListOfValues employeeListOfValuesBean = 
            (EmployeeListOfValues)getEmpListOfValues();
        employeeListOfValuesBean.setEmpListOfValuesStyle("newEmpListOfValuesStyle");
        employeeListOfValuesBean.setReturnMethodName(BEAN_NAME + 
                                                     ".returnMethodAction");
        employeeListOfValuesBean.setSearchMethod(BEAN_NAME + 
                                                 ".searchEmpLovDiv");

        employeeListOfValuesBean.getOkCommandButton().setReRender("stepsPanel,mainDataEmpPanel,scriptPanelId,displayBtnPanel,OperationBar,dataT_data_panel,paging_panel,empLovDivContainer,emp_query_panel_id");
        employeeListOfValuesBean.resetData();
        enableEmpLovDiv = true;
    }

    public void returnMethodAction() {

        EmployeeListOfValues employeeListOfValuesBean = 
            (EmployeeListOfValues)getEmpListOfValues();
        List<IBasicDTO> selectedDTOsList = 
            employeeListOfValuesBean.getSelectedDTOS();

        if (selectedDTOsList != null && selectedDTOsList.get(0) != null && 
            selectedDTOsList.get(0).getCode() != null) {
            setCivilId(Long.valueOf((selectedDTOsList.get(0).getCode().getKey())));
            init();
        }
        enableEmpLovDiv = false;

    }

    public String searchEmpLovDiv(Object searchType, Object searchQuery) {
        EmployeeListOfValues employeeListOfValuesBean = 
            (EmployeeListOfValues)getEmpListOfValues();
        try {
            IEmpEmployeeSearchDTO searchDTO = 
                EmpDTOFactory.createEmpEmployeeSearchDTO();
            //searchDTO.setEmployment(true);
            if (searchType.toString().equals(SEARCH_BY_CODE)) {
                searchDTO.setCivilId(Long.valueOf((String)searchQuery));
                searchDTO.setEmpName(null);
            } else if (searchType.toString().equals(SEARCH_BY_NAME)) {
                searchDTO.setCivilId(null);
                searchDTO.setEmpName(searchQuery.toString());
            }
            IEmployeesClient client = EmpClientFactory.getEmployeesClient();
            List<IBasicDTO> searchResult = client.simpleSearch(searchDTO);

            employeeListOfValuesBean.setMyTableData(searchResult);

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            employeeListOfValuesBean.setMyTableData(new ArrayList(0));
        } catch (DataBaseException e) {
            e.printStackTrace();
            employeeListOfValuesBean.setMyTableData(new ArrayList(0));
        }
        return "";
    }

    

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
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

    public void setValidEmpStatus(boolean validEmpStatus) {
        this.validEmpStatus = validEmpStatus;
    }

    public boolean isValidEmpStatus() {
        return validEmpStatus;
    }

    public void setPersonQulDTO(IPersonQualificationsDTO personQulDTO) {
        this.personQulDTO = personQulDTO;
    }

    public IPersonQualificationsDTO getPersonQulDTO() {
        return personQulDTO;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmp(IEmployeesDTO emp) {
        this.emp = emp;
    }

    public IEmployeesDTO getEmp() {
        return emp;
    }

    public void setEnableEmpLovDiv(boolean enableEmpLovDiv) {
        this.enableEmpLovDiv = enableEmpLovDiv;
    }

    public boolean isEnableEmpLovDiv() {
        return enableEmpLovDiv;
    }

    public List getTotalList() {

        List totalList = null;
        if (civilId != null && validCivilId && civilExist) {
            try {
                totalList = 
                        EmpClientFactory.getEmpExternalExperienceClient().getAllByCivilId(civilId);
            } catch (SharedApplicationException ne) {
                totalList = new ArrayList();
                ne.printStackTrace();
            } catch (DataBaseException db) {
                getSharedUtil().handleException(db);
                totalList = new ArrayList();
            } catch (Exception e) {
                getSharedUtil().handleException(e);
                totalList = new ArrayList();
            }
        } else {
            totalList = new ArrayList();
        }
        return totalList;

    }

    public void add() throws DataBaseException {
        ((IEmpExternalExperienceDTO)getPageDTO()).setEmployeesDTO(getEmp());
        super.add();
    }

    public void reInitializePageDTO() {
        setPageDTO(EmpDTOFactory.createEmpExternalExperienceDTO());

    }

    public void setSearchDTO(IEmpExternalExperienceSearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public IEmpExternalExperienceSearchDTO getSearchDTO() {
        return searchDTO;
    }

    public List getBaseSearchResult() throws DataBaseException {
        List resultList = new ArrayList();
        if (civilId != null && validCivilId && civilExist) {
            searchDTO.setCivilId(civilId);
            try {
                resultList = 
                        EmpClientFactory.getEmpExternalExperienceClient().search(searchDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            resultList = new ArrayList();
        }
        return resultList;
    }

    public void resetSearchDTO() {
        if (!isSearchMode()) {
            searchDTO = 
                    EmpDTOFactory.createEmpEmpExternalExperienceSearchDTO();
        }
        setPageMode(3);
    }
    public void cancelSearch() throws DataBaseException {
        System.out.println("Calling cancelSearch()...");
        searchDTO = EmpDTOFactory.createEmpEmpExternalExperienceSearchDTO();
        this.setSearchMode(false);
        if (isUsingPaging()) {
            getPagingBean().cancelSearch();
        } else {
            this.getAll();
            repositionPage(0);
        }
        setPageMode(0);
    }

    public void preAdd() {
        super.preAdd();
        reInitializePageDTO();
    }
}
