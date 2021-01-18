package com.beshara.csc.hr.emp.presentation.backingbean.empinternalexperience;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpInternalExperienceClient;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpInternalExperienceDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class ListBean extends LookUpBaseBean {
    protected static final String BEAN_NAME = "empInternalExperience";
    protected static final String NAVIGATION_KEY = "empinternalexperiencelist";
    
    private static final String RESOURCE_BUNDLE = "com.beshara.csc.hr.emp.presentation.resources.emp";
    private static final String RESOURCE_KEY_REV_SUCCEEDED = "emp_internal_exp_rev_succeeded";
    private static final String RESOURCE_KEY_REV_FAILED = "emp_internal_exp_rev_failed";
    
    private static final String SEARCH_BY_CODE = "0";
    private static final String SEARCH_BY_NAME = "1";
    
    private String civilId;
    private String empFullName;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private boolean empHired = true;
    private boolean payrollInfoExist = true;
    
    public ListBean() {
        setPageDTO(EmpDTOFactory.createEmpInternalExperienceDTO());
        super.setSelectedPageDTO(EmpDTOFactory.createEmpInternalExperienceDTO());
        setClient((IEmpInternalExperienceClient) EmpClientFactory.getEmpInternalExperienceClient());
        setIndexArray(new boolean[] { true, true, true, true, true, true, true,true, true, true, true, true, true, true,true, true, true, true, true, true, true});
        setSingleSelection(true);
        setDivMainContent("divMainContentScrollable");
        setEmpListOfValues(new EmployeeListOfValues());
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app =super.appMainLayoutBuilder();
        
        app.setShowSearch(false);
        app.setShowbar(true);
        app.setShowpaging(true);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowCommonData(true);
        app.setShowshortCut(true);
        app.setShowScirptGenerator(true);
        app.setShowEmpSrchDiv(true);
        return app;
    }
    
    public List getMyTableData() throws DataBaseException {
        List superResult = super.getMyTableData();
        //getSharedUtil().setNoOfTableRows(8);
        return superResult;
    }
    
    public void getAll() throws DataBaseException {
        filterByCivilId();
    }
    
    public void filterByCivilId() {
        setMyTableData(new ArrayList<IBasicDTO>());
        if (this.getCivilId() != null) {
            empHired = true;
            payrollInfoExist = true;
            if (GlobalValidation.isValidCivilId(Long.parseLong(civilId))) {
                validCivilId = true;
                civilExist = true;
                empFullName = null;
                try{
                    empFullName = ((IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenName(Long.parseLong(civilId))).getFullName();
                }catch(Throwable e){
                    e.printStackTrace();
                }
//                IEmployeesEntityKey empKey = null;
//                try {//TODO by Ashraf Gaber ... Emad said no status validation
//                    empKey = EmpEntityKeyFactory.createEmployeesEntityKey(new Long(civilId));
//                    empHired = EmpClientFactory.getEmployeesClient().isEmployeeHired(empKey);
//                } catch (Exception e) {
//                    empHired = false;
//                    civilExist = false;
//                    e.printStackTrace();
//                }
                if (empHired) {
                    Boolean employeeInMinistry = true;
                    Long minCode = getManagedConstantsBean().getMinCode();
                    try {
                        employeeInMinistry = EmpClientFactory.getEmployeesClient().isEmployeeInMinistry(Long.parseLong(civilId), minCode);
                    } catch (Exception e) {
                        employeeInMinistry = false;
                        e.printStackTrace();
                    }
                    if (employeeInMinistry) {
                        try {
                            List data = ((IEmpInternalExperienceClient)getClient()).getAllByCivilIdAndMinCode(Long.parseLong(civilId), minCode);
                            setMyTableData(data);
                        } catch (Exception e) {
//                            civilExist = false;
//                            payrollInfoExist = false;
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                validCivilId = false;
                civilExist = false;
            }
        }
    }

    public void reSetData(ActionEvent event){
        event = null; //unused
        civilId = null;
        empFullName = null;
        empHired = true;
        payrollInfoExist = true;
        validCivilId = true;
        civilExist = false;
        setMyTableData(new ArrayList());
        setPageMode(0);
        resetPageIndex();
        setSearchMode(false);
    }
 
    public void showSearchForEmployeeDiv() {
       EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues) getEmpListOfValues();
       
        employeeListOfValuesBean.setReturnMethodName( BEAN_NAME + ".returnMethodAction");
        employeeListOfValuesBean.setSearchMethod( BEAN_NAME + ".searchEmpLovDiv");
        
        employeeListOfValuesBean.getOkCommandButton().setReRender("mainDataEmpPanel,scriptGenerator,displayBtnPanel,dataT_data_panel,OperationBar,paging_panel,empLovDivContainer");
        employeeListOfValuesBean.resetData();       
    }
    
    public void returnMethodAction() {
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        List<IBasicDTO> selectedDTOsList = employeeListOfValuesBean.getSelectedDTOS();
        
        if (selectedDTOsList != null && selectedDTOsList.get(0) != null && selectedDTOsList.get(0).getCode() != null) {
             setCivilId((String)(selectedDTOsList.get(0).getCode().getKey()));
             filterByCivilId();  
         }
    }   
    
    public String searchEmpLovDiv(Object searchType, Object searchQuery) {
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        try {
            IEmpEmployeeSearchDTO searchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
            //searchDTO.setEmployment(true); //TODO by Ashraf Gaber ... Emad said no status validation
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
    
    public void updateRevisionFlag(){
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            IEmpInternalExperienceDTO empInternalExperienceDTO = (IEmpInternalExperienceDTO)getSelectedDTOS().get(0);
            empInternalExperienceDTO.setRevFlagBoolean(true);
            setSelectedPageDTO(empInternalExperienceDTO);
            super.getClient().update(empInternalExperienceDTO);
            cancelSearch();

            if (super.isUsingPaging()) {
                generatePagingRequestDTO((String)getSelectedPageDTO().getCode().getKey());

            } else {
                getPageIndex((String)getSelectedPageDTO().getCode().getKey());
            }

            if (super.getHighlightedDTOS() != null) {
                super.getHighlightedDTOS().clear();
                super.getHighlightedDTOS().add(this.getSelectedPageDTO());
            }

            sharedUtil.setSuccessMsgValue(sharedUtil.messageLocator(RESOURCE_BUNDLE,RESOURCE_KEY_REV_SUCCEEDED));
        } catch (Exception e) {
            sharedUtil.handleException(e, RESOURCE_BUNDLE, RESOURCE_KEY_REV_FAILED);
        }
        setSelectedRadio("");
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

    public void setEmpFullName(String empFullName) {
        this.empFullName = empFullName;
    }

    public String getEmpFullName() {
        return empFullName;
    }

    public boolean isSelectedDTOReviewed() {
        if(getSelectedDTOS() != null && getSelectedDTOS().size() != 0){
            IEmpInternalExperienceDTO selected = (IEmpInternalExperienceDTO)getSelectedDTOS().get(0);
            return selected.isRevFlagBoolean();
        }
        return false;
    }
}