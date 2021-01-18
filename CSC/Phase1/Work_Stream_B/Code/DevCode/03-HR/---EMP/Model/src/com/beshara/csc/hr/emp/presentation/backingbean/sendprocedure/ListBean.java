package com.beshara.csc.hr.emp.presentation.backingbean.sendprocedure;


import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class ListBean extends LookUpBaseBean {

    //empEmployeesSearchDTO is filled in simple Search Div to pass it to search function
    private IEmpEmployeeSearchDTO empEmployeesSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
    //Lists.
    private List hire_type_list = new ArrayList();
    private List hire_stage_list = new ArrayList();
    private List gender_type_list = new ArrayList();
    //selected values from comboboxes in search div
    private String selectedGenderType = ISystemConstant.GENDER_UNDEFINED.toString();
    //Variables used ISystemContants
    private Long nationality_kuwait = ISystemConstant.NATIONALITY_KUWAITI;
    private Long nationality_non_kuwait = ISystemConstant.NATIONALITY_NON_KUWAITI;
    private Long nationality_non_specified = ISystemConstant.NATIONALITY_NON_SPECIFIED;
    private Long virtual_value = ISystemConstant.VIRTUAL_VALUE;
    //Constructor

    public ListBean() {

        setPageDTO(EmpDTOFactory.createEmployeesDTO());
        super.setSelectedPageDTO(EmpDTOFactory.createEmployeesDTO());
        setClient((IEmployeesClient)EmpClientFactory.getEmployeesClient());
        setSaveSortingState(true);
        setUsingPaging(true);

    }
    // All Methods

    /**
     * Purpose:Override appMainLayoutBuilder to setShowLookupAdd,setShowLookupEdit , setShowLookupAdd,setShowDelConfirm true
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  17-7-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowLookupEdit(false);
        app.setShowLookupAdd(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        return app;
    }

    /**
     * Purpose:go_send_procedure()
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  17-7-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:getSelectedDTOs from list and set them in selectedEmployees of SendProcedure bean
     */
    public String go_send_procedure() {
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0) {
            FacesContext fc = FacesContext.getCurrentInstance();
            Application app = fc.getApplication();
            SendProcedure sendProcedure = (SendProcedure)app.createValueBinding("#{sendProcedure}").getValue(fc);
            sendProcedure.setSelectedEmployees(getSelectedDTOS());
            return "send_procedure";
        }
        return null;
    }

    /**
     * Purpose:Search Method
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  17-7-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: fill empEmployeesSearchDTO with the selected values from div
     */
    public void simpleSearchsearch() {

        setSearchMode(true);
        empEmployeesSearchDTO.setGenderType(Long.valueOf(selectedGenderType));

        if (isUsingPaging()) {

            generatePagingRequestDTO("sendProcedureListBean", "simpleSearchWithPaging", empEmployeesSearchDTO);

            resetPageIndex();

        } else {

            setMyTableData(getSearchResult(empEmployeesSearchDTO));

            this.repositionPage(0);

        }
    }

    public PagingResponseDTO simpleSearchWithPaging(PagingRequestDTO request) {

        IEmpEmployeeSearchDTO empEmployeesSearchDTO = (IEmpEmployeeSearchDTO)request.getSearchDTO();

        return new PagingResponseDTO(getSearchResult(empEmployeesSearchDTO));

    }

    private List getSearchResult(IEmpEmployeeSearchDTO empEmployeesSearchDTO) {

        List searchResult = null;

        try {

            searchResult = EmpClientFactory.getEmployeesClient().simpleSearch(empEmployeesSearchDTO);

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            searchResult = new ArrayList();
        } catch (DataBaseException e) {
            e.printStackTrace();
            searchResult = new ArrayList();
        }

        return searchResult;

    }

    /**
     * Purpose:cancel search method
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  17-7-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void cancelSearch() throws DataBaseException {
        selectedGenderType = ISystemConstant.GENDER_UNDEFINED.toString();
        setEmpEmployeesSearchDTO(EmpDTOFactory.createEmpEmployeeSearchDTO());
        super.cancelSearch();
    }

    //All Gets and sets.

    public void setEmpEmployeesSearchDTO(IEmpEmployeeSearchDTO empEmployeesSearchDTO) {
        this.empEmployeesSearchDTO = empEmployeesSearchDTO;
    }

    public IEmpEmployeeSearchDTO getEmpEmployeesSearchDTO() {
        return empEmployeesSearchDTO;
    }

    public void setHire_type_list(List hire_type_list) {
        this.hire_type_list = hire_type_list;
    }

    public List getHire_type_list() {
        try {
            if (hire_type_list.isEmpty()) {
                hire_type_list = EmpClientFactory.getHireTypesClient().getCodeName();
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            hire_type_list = new ArrayList();
        }
        return hire_type_list;
    }

    public void setHire_stage_list(List hire_stage_list) {
        this.hire_stage_list = hire_stage_list;
    }

    public List getHire_stage_list() {
        try {
            if (hire_stage_list.isEmpty()) {
                hire_stage_list = EmpClientFactory.getHireStagesClient().getCodeName();
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            hire_stage_list = new ArrayList();
        }
        return hire_stage_list;
    }

    public void setGender_type_list(List gender_type_list) {
        this.gender_type_list = gender_type_list;
    }

    public List getGender_type_list() {
        try {
            if (gender_type_list.isEmpty()) {
                gender_type_list = InfClientFactory.getGenderTypesClient().getCodeName();
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            gender_type_list = new ArrayList();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            gender_type_list = new ArrayList();
        }
        return gender_type_list;
    }

    public void setSelectedGenderType(String selectedGenderType) {
        this.selectedGenderType = selectedGenderType;
    }

    public String getSelectedGenderType() {
        return selectedGenderType;
    }


    public void setNationality_kuwait(Long nationality_kuwait) {
        this.nationality_kuwait = nationality_kuwait;
    }

    public Long getNationality_kuwait() {
        return nationality_kuwait;
    }

    public void setNationality_non_kuwait(Long nationality_non_kuwait) {
        this.nationality_non_kuwait = nationality_non_kuwait;
    }

    public Long getNationality_non_kuwait() {
        return nationality_non_kuwait;
    }

    public void setNationality_non_specified(Long nationality_non_specified) {
        this.nationality_non_specified = nationality_non_specified;
    }

    public Long getNationality_non_specified() {
        return nationality_non_specified;
    }

    public void setVirtual_value(Long virtual_value) {
        this.virtual_value = virtual_value;
    }

    public Long getVirtual_value() {
        return virtual_value;
    }
}

