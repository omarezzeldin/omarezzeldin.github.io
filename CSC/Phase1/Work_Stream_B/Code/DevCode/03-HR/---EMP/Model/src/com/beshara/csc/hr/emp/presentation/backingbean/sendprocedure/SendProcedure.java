package com.beshara.csc.hr.emp.presentation.backingbean.sendprocedure;

//import com.beshara.csc.hr.emp.business.dto.IEmployeeProceduresDTO;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.ISearchHireProceduresDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class SendProcedure extends LookupMaintainBaseBean {
    //employeeProceduresDTO is filled in send procendure page
//    private IEmployeeProceduresDTO employeeProceduresDTO = 
//        EmpDTOFactory.createEmployeeProceduresDTO();
    //Selected Procedure Name from Procedures Div
    private String selected_procedure_name = "";
    //searchHireProceduresDTO is filled in search part of procedures div
    private ISearchHireProceduresDTO searchHireProceduresDTO = 
        EmpDTOFactory.createSearchHireProceduresDTO();
    //List of procedures is viewed in dataTable in procedures Div.
    private List procedures_list;
    //Its Size that is used to render datascroller.
    private int procedures_list_size;
    //Selected Employess from list Page
    private List selectedEmployees = new ArrayList();
    //Constructor

    public SendProcedure() {
    }

    //All Methods

    /**
     * Purpose:Override appMainLayoutBuilder to setShowLookupAdd and  setShowDelConfirm true
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  17-7-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description:
     */
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        app.setShowLookupAdd(true);
        app.setShowDelConfirm(true);
        return app;
    }

    /**
     * Purpose:sendingprocedure Method
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  17-7-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 1)getById using selected hireprocedure code from procedures div
     *                    2)loop in selected employees from list page to construct List of EmployeeProceduresDTO to send it to sendProcedure method
     */
    public void sendingprocedure() {
        List<IBasicDTO> employeeProceduresList = new ArrayList<IBasicDTO>();
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0) {
            IHireProceduresDTO selectedHireProcedure = EmpDTOFactory.createHireProceduresDTO();
            try {
                selectedHireProcedure = 
                        (IHireProceduresDTO)EmpClientFactory.getHireProceduresClient().getById(getSelectedDTOS().get(0).getCode());

                for (int i = 0; 
                     getSelectedEmployees() != null && i < getSelectedEmployees().size(); 
                     i++) {
//                    IEmployeeProceduresDTO newOne = EmpDTOFactory.createEmployeeProceduresDTO();
//
//                    newOne.setSendDate(employeeProceduresDTO.getSendDate());
//                    newOne.setProcDesc(employeeProceduresDTO.getProcDesc());
//
//                    newOne.setEmployeesDTO((IBasicDTO)getSelectedEmployees().get(i));
//                    newOne.setHireProcedureDTO(selectedHireProcedure);
//                    employeeProceduresList.add(newOne);
                }
                if (employeeProceduresList.size() > 0) {
                    setDeleteStatusDTOS(EmpClientFactory.getEmployeesClient().sendProcedure(employeeProceduresList));
                    setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");
                }

            } catch (SharedApplicationException e) {
                // e.printStackTrace();
                //setErrorMsg(e.getCause().getMessage());
                getSharedUtil().handleException(e, 
                                                "com.beshara.csc.hr.emp.presentation.resources.emp", 
                                                "emp_with_procedure_used_before");
            } catch (DataBaseException e) {
                getSharedUtil().handleException(e, 
                                                "com.beshara.csc.hr.emp.presentation.resources.emp", 
                                                "emp_with_procedure_used_before");
                //This is the handling of exception before creating handle exception method
                // e.printStackTrace();
                //  setErrorMsg(e.getCause().getMessage());
            } catch (Exception e) {
                //e.printStackTrace();
                //getSharedUtil().setErrMsgValue("FailureInAdd");
                getSharedUtil().handleException(e, 
                                                "com.beshara.csc.hr.emp.presentation.resources.emp", 
                                                "emp_with_procedure_used_before");
            }

        }
    }

    /**
     * Purpose:Action Listener of DataScroller in procedures div.
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  17-7-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: because dataScroller submits and div disappear so setJavaScriptCaller to open the div again
     */
    public void scrollerActionProcedure(ActionEvent ae) {
        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);");
    }

    /**
     * Purpose:Action Method of Search Button in procedures div.
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  17-7-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void search() {
        getMyDataTable().setFirst(0);
        setSearchMode(true);
        setSelectedRadio("");
        try {
            setProcedures_list(EmpClientFactory.getHireProceduresClient().search(searchHireProceduresDTO));
        } catch (SharedApplicationException e) {
            setProcedures_list(new ArrayList());
            e.printStackTrace();
        } catch (DataBaseException e) {
            setProcedures_list(new ArrayList());
            e.printStackTrace();
        }
    }

    /**
     * Purpose:Action Method of Cancel Search Button in procedures div.
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  17-7-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void cancelSearch() {
        getMyDataTable().setFirst(0);
        setSearchHireProceduresDTO(EmpDTOFactory.createSearchHireProceduresDTO());
        setSearchMode(false);
        setProcedures_list(null);
        setSelectedRadio("");
    }

    /**
     * Purpose:Action Method of Add Button in procedures div.
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  17-7-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: fill selected_procedure_name with the selected DTO from DIV
     */
    public String add_procedure() {
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0) {
            selected_procedure_name = getSelectedDTOS().get(0).getName();
        } else
            selected_procedure_name = "";
        // setSearchHireProceduresDTO(new SearchHireProceduresDTO());
        setSelectedRadio("");
        //  setSearchMode(false);
        return null;
    }

    /**
     * Purpose:Action Method of Back Button in procedures div.
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  17-7-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: reintialize SearchHireProceduresDTO 
     *                    reset the value of selected radio
     *                    set searchMode false
     */
    public void exit_procedure() {
        setSearchHireProceduresDTO(EmpDTOFactory.createSearchHireProceduresDTO());
        setSelectedRadio("");
        setSearchMode(false);
    }
    //All Gets and Sets

//    public void setEmployeeProceduresDTO(IEmployeeProceduresDTO employeeProceduresDTO) {
//        this.employeeProceduresDTO = employeeProceduresDTO;
//    }
//
//    public IEmployeeProceduresDTO getEmployeeProceduresDTO() {
//        return employeeProceduresDTO;
//    }


    public void setProcedures_list_size(int procedures_list_size) {
        this.procedures_list_size = procedures_list_size;
    }

    public int getProcedures_list_size() {
        if (procedures_list != null) {
            procedures_list_size = procedures_list.size();
        }
        return procedures_list_size;
    }

    public void setSearchHireProceduresDTO(ISearchHireProceduresDTO searchHireProceduresDTO) {
        this.searchHireProceduresDTO = searchHireProceduresDTO;
    }

    public ISearchHireProceduresDTO getSearchHireProceduresDTO() {
        return searchHireProceduresDTO;
    }

    public void setProcedures_list(List procedures_list) {
        this.procedures_list = procedures_list;
    }

    public List getProcedures_list() {
        try {
            if (procedures_list == null) {
                procedures_list = 
                        EmpClientFactory.getHireProceduresClient().getCodeName();
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            procedures_list = new ArrayList();
        }
        return procedures_list;
    }

    public void setSelectedEmployees(List selectedEmployees) {
        this.selectedEmployees = selectedEmployees;
    }

    public List getSelectedEmployees() {
        return selectedEmployees;
    }

    public void setSelected_procedure_name(String selected_procedure_name) {
        this.selected_procedure_name = selected_procedure_name;
    }

    public String getSelected_procedure_name() {
        return selected_procedure_name;
    }

}

