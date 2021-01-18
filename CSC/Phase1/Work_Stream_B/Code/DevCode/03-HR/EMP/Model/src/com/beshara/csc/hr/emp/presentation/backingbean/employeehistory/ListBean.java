package com.beshara.csc.hr.emp.presentation.backingbean.employeehistory;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpEmployeeHistoriesClient;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeHistoriesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.presentation.backingbean.governmentalemployeedatarevision.GovEmpMaintainBean;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;


public class ListBean extends LookUpBaseBean {
    private static final String BEAN_NAME = "employeeHistory";
    private static final String NAVIGATION_KEY = "employeehistorylist";
    private static final String METHOD_NAME_RESTORE = "restorePage";
    private String civilId;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private boolean empHired = true;
    private boolean payrollInfoExist = true;
    private static final Long MINISTRY_CODE = 
        CSCSecurityInfoHelper.getLoggedInMinistryCode();
    private ISalEmpSalaryElementsDTO salaryElementDTO;
    private boolean outerModule = false;
    private IEmpEmployeeHistoriesDTO searchDTO;
    private List<IEmpEmployeeHistoriesDTO> filteredByCivilIDList;

    private List ministriesSearchDTOList;
    private List workCentersSearchDTOList;
    private List jobsSearchDTOList;
    private String virtual_value = "";
    private String degree;
    private IEmployeesDTO employeesDTO;
    private boolean notDefinedGenderTypeFlag;
    private boolean showPaySlipPanel = false;
    private boolean enableEmpLovDiv;
    private static final String SEARCH_BY_CODE = "0";
    private static final String SEARCH_BY_NAME = "1";

    public ListBean() {
        this.setEmpListOfValues(new EmployeeListOfValues());
        setEmpListOfValues(new EmployeeListOfValues());
        setIndexArray(new boolean[] { true, true, false, true, true, true, 
                                      false, false, false });

        setDivMainContent("delDivScroll3");

        setPageDTO(EmpDTOFactory.createEmpEmployeeHistoriesDTO());
        setSearchDTO(EmpDTOFactory.createEmpEmployeeHistoriesDTO());
        setClient(EmpClientFactory.getEmpEmployeeHistoriesClient());
        if (((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("employeeCivilId") != 
            null) {
            civilId = 
                    ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("employeeCivilId");
            outerModule = true;
            filterByCivilId();
            setUsingPaging(true);
        } else
            outerModule = false;
        filterByCivilId();


    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        //app.setShowNavigation(true);

        app.setShowContent1(true);
        app.setShowdatatableContent(true);

        app.setShowSearch(true);
        app.setShowbar(true);
        app.setShowpaging(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);

        app.setShowCommonData(true);
        app.setShowshortCut(false);
        app.setShowEmpSrchDiv(true);
        return app;
    }

    public String viewEmpDetails() {
        try {
            Long civilID = Long.parseLong(civilId);
            getIntegrationBean().reInitializeBean();
            getIntegrationBean().setBeanNameTo(GovEmpMaintainBean.BEAN_NAME);
            getIntegrationBean().setActionTo(GovEmpMaintainBean.METHOD_NAME_VIEW);
            getIntegrationBean().setNavgationCaseFrom(NAVIGATION_KEY);
            getIntegrationBean().getHmBaseBeanFrom().put(BEAN_NAME, 
                                                         evaluateValueBinding(BEAN_NAME));
            getIntegrationBean().setBeanNameFrom(BEAN_NAME);
            getIntegrationBean().setActionFrom(METHOD_NAME_RESTORE);
            getIntegrationBean().getHmObjects().put(GovEmpMaintainBean.MAP_KEY_CIVIL_ID, 
                                                    civilID);
            return getIntegrationBean().goTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String restorePage() {
        return NAVIGATION_KEY;
    }

    public void getAll() throws DataBaseException {

        filterByCivilId();

    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
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

    public void setEmpHired(boolean empHired) {
        this.empHired = empHired;
    }

    public boolean isEmpHired() {
        return empHired;
    }

    public void setPayrollInfoExist(boolean payrollInfoExist) {
        this.payrollInfoExist = payrollInfoExist;
    }

    public boolean isPayrollInfoExist() {
        return payrollInfoExist;
    }

    public String simpleSearchsearch() {
        try {
            setSearchMode(true);
            getSearchDTO().setCivilId(Long.valueOf(getCivilId()));
            setMyTableData(((IEmpEmployeeHistoriesClient)getClient()).searchEmpEmployeeHistoriesDTO(getSearchDTO()));
        } catch (DataBaseException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
        }
        return null;
    }

    public void filterByCivilId() {
        showPaySlipPanel = false;
        if (this.getCivilId() != null) {
            empHired = true;
            payrollInfoExist = true;
            if (GlobalValidation.isValidCivilId(Long.parseLong(civilId))) {
                validCivilId = true;
                civilExist = true;
                IEmployeesEntityKey empKey = null;
                
                try {
                    empKey = EmpEntityKeyFactory.createEmployeesEntityKey(new Long(civilId));
                    empHired = 
                            EmpClientFactory.getEmployeesClient().isEmployeeHired(empKey);
                } catch (Exception e) {
                    empHired = false;
                    civilExist = false;
                    e.printStackTrace();
                }

                try {
                    degree = 
                            SalClientFactory.getSalElementGuidesClient().getFullDegreeName(Long.valueOf(this.getCivilId()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (empHired) {
                    Boolean employeeInMinistry = true;
                    try {
                        employeeInMinistry = 
                                EmpClientFactory.getEmployeesClient().isEmployeeInMinistry(empKey.getCivilId(), 
                                                                                           MINISTRY_CODE);
                    } catch (Exception e) {
                        employeeInMinistry = false;
                        e.printStackTrace();
                    }
                    
                    if (employeeInMinistry) {
                        try {
                            employeesDTO = 
                                    (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getEmployeeInfoByElmType(empKey.getCivilId());
                        } catch (Exception e) {
                            //civilExist = false;
                            payrollInfoExist = false;
                            e.printStackTrace();
                        }
                        
                        try {
                            setMyTableData(((IEmpEmployeeHistoriesClient)getClient()).getAllByCivilID(Long.valueOf(getCivilId()), 
                                                                                                      MINISTRY_CODE));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                validCivilId = false;
                civilExist = false;
                employeesDTO = EmpDTOFactory.createEmployeesDTO();
            }

            try {
                if (((IKwtCitizensResidentsDTO)getEmployeesDTO().getCitizensResidentsDTO()).getGenderTypesDTO().getCode().getKey().toString().equals("3")) {
                    setNotDefinedGenderTypeFlag(true);
                } else {
                    setNotDefinedGenderTypeFlag(false);
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }

        setSearchDTO(EmpDTOFactory.createEmpEmployeeHistoriesDTO());
    }


    public String filterWorkCenterByMinistry() {
        setWorkCentersSearchDTOList(null);
        getSearchDTO().setWrkCode(null);
        try {
            if (getSearchDTO().getMinCode() != null)
                setWorkCentersSearchDTOList(OrgClientFactory.getWorkCentersClient().getCodeNameByMinistry(getSearchDTO().getMinCode()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reSetData(ActionEvent event) {
        event = null; //unused
        showPaySlipPanel = false;
        employeesDTO = EmpDTOFactory.createEmployeesDTO();
        degree = null;
        civilId = null;
        empHired = true;
        payrollInfoExist = true;
        enableEmpLovDiv = false;
        validCivilId = true;
        civilExist = false;
        setMyTableData(new ArrayList());
        setPageMode(0);
        resetPageIndex();
        setNotDefinedGenderTypeFlag(false);
        setSearchMode(false);
    }

    public void showSearchForEmployeeDiv() {
        EmployeeListOfValues employeeListOfValuesBean = 
            (EmployeeListOfValues)getEmpListOfValues();

        employeeListOfValuesBean.setReturnMethodName("employeeHistory.returnMethodAction");
        employeeListOfValuesBean.setSearchMethod("employeeHistory.searchEmpLovDiv");

        employeeListOfValuesBean.getOkCommandButton().setReRender("mainDataEmpPanel,scriptGenerator,displayBtnPanel,dataT_data_panel,OperationBar,paging_panel,empLovDivContainer");
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
            setCivilId((String)(selectedDTOsList.get(0).getCode().getKey()));
            filterByCivilId();
        }
        enableEmpLovDiv = false;

    }

    public String searchEmpLovDiv(Object searchType, Object searchQuery) {
        //  evaluateValueBinding(detailBeanName).
        EmployeeListOfValues employeeListOfValuesBean = 
            (EmployeeListOfValues)getEmpListOfValues();
        try {
            IEmpEmployeeSearchDTO searchDTO = 
                EmpDTOFactory.createEmpEmployeeSearchDTO();
            searchDTO.setEmployment(true);
            if (searchType.toString().equals(SEARCH_BY_CODE)) {
                searchDTO.setCivilId(Long.valueOf((String)searchQuery));

            } else if (searchType.toString().equals(SEARCH_BY_NAME)) {
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


    public void cancelSearch() throws DataBaseException {

        System.out.println("Calling cancelSearch()...");

        this.setSearchQuery("");
        this.setSearchType(0);
        this.setSearchMode(false);

        this.filterByCivilId();
    }

    public void setSalaryElementDTO(ISalEmpSalaryElementsDTO salaryElementDTO) {
        this.salaryElementDTO = salaryElementDTO;
    }

    public ISalEmpSalaryElementsDTO getSalaryElementDTO() {
        return salaryElementDTO;
    }

    public void setOuterModule(boolean outerModule) {
        this.outerModule = outerModule;
    }

    public boolean isOuterModule() {
        return outerModule;
    }

    public void setSearchDTO(IEmpEmployeeHistoriesDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public IEmpEmployeeHistoriesDTO getSearchDTO() {
        return searchDTO;
    }

    public void setFilteredByCivilIDList(List<IEmpEmployeeHistoriesDTO> filteredByCivilIDList) {
        this.filteredByCivilIDList = filteredByCivilIDList;
    }

    public List<IEmpEmployeeHistoriesDTO> getFilteredByCivilIDList() {
        return filteredByCivilIDList;
    }

    public void setMinistriesSearchDTOList(List ministriesSearchDTOList) {
        this.ministriesSearchDTOList = ministriesSearchDTOList;
    }

    public List getMinistriesSearchDTOList() {
        try {
            setMinistriesSearchDTOList(OrgClientFactory.getMinistriesClient().getMinistriesByGovFlag(Long.valueOf(ISystemConstant.GOVERNMENT_FLAG)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ministriesSearchDTOList;
    }

    public void setWorkCentersSearchDTOList(List workCentersSearchDTOList) {
        this.workCentersSearchDTOList = workCentersSearchDTOList;
    }

    public List getWorkCentersSearchDTOList() {
        return workCentersSearchDTOList;
    }

    public void setJobsSearchDTOList(List jobsSearchDTOList) {
        this.jobsSearchDTOList = jobsSearchDTOList;
    }

    public List getJobsSearchDTOList() {


        try {
            jobsSearchDTOList = JobClientFactory.getJobsClient().getCodeName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobsSearchDTOList;
    }

    public void setVirtual_value(String virtual_value) {
        this.virtual_value = virtual_value;
    }

    public String getVirtual_value() {
        return virtual_value;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

    public void setNotDefinedGenderTypeFlag(boolean notDefinedGenderTypeFlag) {
        this.notDefinedGenderTypeFlag = notDefinedGenderTypeFlag;
    }

    public boolean isNotDefinedGenderTypeFlag() {
        return notDefinedGenderTypeFlag;
    }

    public void setShowPaySlipPanel(boolean showPaySlipPanel) {
        this.showPaySlipPanel = showPaySlipPanel;
    }

    public boolean isShowPaySlipPanel() {
        return showPaySlipPanel;
    }

    public void setEnableEmpLovDiv(boolean enableEmpLovDiv) {
        this.enableEmpLovDiv = enableEmpLovDiv;
    }

    public boolean isEnableEmpLovDiv() {
        return enableEmpLovDiv;
    }
}


