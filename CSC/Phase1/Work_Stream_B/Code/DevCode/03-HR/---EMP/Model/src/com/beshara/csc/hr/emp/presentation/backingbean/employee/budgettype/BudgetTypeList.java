package com.beshara.csc.hr.emp.presentation.backingbean.employee.budgettype;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.client.IBgtTypesClient;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IChangeBudgetTypeDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.nl.org.business.client.IMinistriesClient;
import com.beshara.csc.nl.org.business.client.IWorkCentersClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersSearchCriteriaDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;


public class BudgetTypeList extends LookUpBaseBean {
    private String defaultValue = getVirtualConstValue();
    private List bugetProgramList = null;
    private List frmBudgetType = null;
    private List wrkCenterList = null;
    private List toBudgetType = null;
    private boolean searchNotDone = true;

    private String budgetProgramValue = getVirtualConstValue();
    private String bgtTypesDTOValue = getVirtualConstValue();
    private String selectedWorkCentersValue = getVirtualConstValue();
    private String toBudgetTypeValue = getVirtualConstValue();
    
    private Boolean srchAgainBtn = Boolean.FALSE;


    public BudgetTypeList() {
        //      setClient(BgtClientFactory.getBgtTypesClient());
        setPageDTO(EmpDTOFactory.createChangeBudgetTypeDTO());
        setMyTableData(new ArrayList());
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList());
        

    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowbar(false);
        app.setShowSearch(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowContent1(true);
        app.setShowpaging(false);
        app.setShowLovDiv(true);

        return app;
    }

    public void wrkCenterValue() {
        if (getLovBaseBean().getSelectedDTOS().size() > 0) {
            if (getLovBaseBean().getSelectedDTOS().get(0) != null && 
                getLovBaseBean().getSelectedDTOS().get(0).getCode() != null) {
                try {
                    setSelectedWorkCentersValue(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString());
                    setBugetProgramList(null);
                    budgetProgramValue = 
                            ((IWorkCentersDTO)getLovBaseBean().getSelectedDTOS().get(0)).getBgtProgramsDTO().getCode().getKeys()[0].toString();
                } catch (Exception e) {
                    ;
                }
            }
        }else {
                Iterator it = getWrkCenterList().iterator();
                while(it.hasNext()) {
                    IWorkCentersDTO workCentersDTO = (IWorkCentersDTO)it.next();
                    IWorkCentersEntityKey workCentersEntityKey = (IWorkCentersEntityKey)workCentersDTO.getCode();
                    String temp = selectedWorkCentersValue;
                    temp = temp.substring(temp.indexOf("*")+1);
//                    if(workCentersEntityKey.getKeys()[1].toString().equals(temp))
                    if(workCentersEntityKey.getKeys()[1].toString().equals(temp) && workCentersDTO.getBgtProgramsDTO()!=null)
                        budgetProgramValue = workCentersDTO.getBgtProgramsDTO().getCode().getKeys()[0].toString();
                }
            
//            budgetProgramValue = selectedWorkCentersValue;    
        }

        
    }

    public void searchWrkCenters(Object searchType, Object searchQuery) {
        if (searchQuery != null && !searchQuery.toString().equals("") && 
            searchType != null && !searchType.equals("")) {
            getLovBaseBean().setSearchMode(true);
            try {
                IWorkCentersSearchCriteriaDTO srchDTO = 
                    OrgDTOFactory.createWorkCentersSearchCriteriaDTO();
                srchDTO.setMinCode(getManagedConstantsBean().getMinCode());
                if (searchQuery != null && !searchQuery.equals("")) {
                    if (searchType.toString().equals("0")) {
                        srchDTO.setWorkCode(searchQuery.toString());
                    } else if (searchType.toString().equals("1")) {
                        String wrkName = new String(searchQuery.toString());
                        srchDTO.setWorkName(formatSearchString(wrkName));
                    }
                    getLovBaseBean().setMyTableData(OrgClientFactory.getWorkCentersClient().search(srchDTO));
                }
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            } catch (DataBaseException e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            } catch (Exception e) {
                getLovBaseBean().setMyTableData(new ArrayList());
                e.printStackTrace();
            }
        }
        getLovBaseBean().getMyDataTable().setFirst(0);

    }

    public void cancelSearchLovDiv() {
        showListOfValuesDiv();
    }


    public void showListOfValuesDiv() {

        try {
            getLovBaseBean().setMyTableData(getWrkCenterList());
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        }
        getLovBaseBean().getOkCommandButton().setReRender("employeeMainDataPanel");
        getLovBaseBean().setReturnMethodName("budgetTypeList.wrkCenterValue");
        getLovBaseBean().setSearchMethod("budgetTypeList.searchWrkCenters");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("budgetTypeList.cancelSearchLovDiv");
    }

    public String executeAction() throws CloneNotSupportedException {

        IBgtTypesDTO suppliedBgtTypesDTO = BgtDTOFactory.createBgtTypesDTO();
        //       suppliedBgtTypesDTO.setCode(new IBgtTypesEntityKey(Long.parseLong(getBgtTypesDTOValue())));
        suppliedBgtTypesDTO.setCode(BgtEntityKeyFactory.createBgtTypesEntityKey(Long.parseLong(getToBudgetTypeValue())));

        ((IChangeBudgetTypeDTO)getPageDTO()).setBgtTypesDTO(suppliedBgtTypesDTO);

        if (getSelectedDTOS().size() > 0 && toBudgetTypeValue != null && 
            !toBudgetTypeValue.equals("") && 
            ((IChangeBudgetTypeDTO)getPageDTO()).getApplyDate() != null)
            ((IChangeBudgetTypeDTO)getPageDTO()).setEmployeesDTOList((List)getSelectedDTOS());
        else {
            //setSearchNotDone(true);
            //super.getSelectedDTOS().clear();
            return null;
        }
        //((IChangeBudgetTypeDTO)getPageDTO()).setEmployeesDTOList(null);

        try {
            List errorList = 
                EmpClientFactory.getEmployeesClient().changeBudgetType(getPageDTO());
            if ((errorList != null && errorList.size() > 0)) {
                SharedUtilBean shared_util = getSharedUtil();
                shared_util.setErrMsgValue(shared_util.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp","transeferBudgetListException"));
                clearDTO();
            } else {
                SharedUtilBean shared_util = getSharedUtil();
                shared_util.setSuccessMsgValue(shared_util.messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp","transeferBudgetListDone"));
                searchAction();
            }


        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        } catch (DataBaseException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        } catch (Exception e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        }
        setSearchNotDone(true);
//        super.getSelectedDTOS().clear();
        
        return "";

    }

    private void clearDTO() {
        setBudgetProgramValue(defaultValue);
        setBgtTypesDTOValue(defaultValue);
        setSelectedWorkCentersValue(defaultValue);
        setToBudgetTypeValue(defaultValue);
        setMyTableData(new ArrayList());
        getToBudgetType().clear();
        ((IChangeBudgetTypeDTO)getPageDTO()).setApplyDate(null);
    }

    public String searchAction() throws CloneNotSupportedException {
        IEmployeesClient employeesClient = 
            EmpClientFactory.getEmployeesClient();
        IEmployeeSearchDTO employeeSrchDTO = EmpDTOFactory.createEmployeeSearchDTO();
        if (!budgetProgramValue.equals(getVirtualConstValue()))
            employeeSrchDTO.setBgtProgramsCode(budgetProgramValue);

        if (!selectedWorkCentersValue.equals(getVirtualConstValue()))
            employeeSrchDTO.setWorkCenterCode(selectedWorkCentersValue);

        employeeSrchDTO.setChecked(Boolean.TRUE);
        setCheckAllFlag(true);
        employeeSrchDTO.setBgtTypesCode(Long.parseLong(bgtTypesDTOValue));
        try {
            List empList = new ArrayList();
            empList = employeesClient.search(employeeSrchDTO);
            setMyTableData(empList);
            setSearchNotDone(false);
            setSelectedDTOS((List)((ArrayList)empList).clone());
            setSrchAgainBtn(Boolean.TRUE);
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
            getSelectedDTOS().clear();
            setSrchAgainBtn(Boolean.FALSE);
        } catch (DataBaseException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
            getSelectedDTOS().clear();
            setSrchAgainBtn(Boolean.FALSE);
        }
        return "";
    }
    
    public String fromBudgetTypeAction() {
        setToBudgetType(null);
        setSrchAgainBtn(Boolean.FALSE);
        return "";
    }
    
    public void searchAgain() {
        setSrchAgainBtn(Boolean.FALSE);
        clearDTO();
        getSelectedDTOS().clear();
    }
    
    
    public void selectedCheckboxsAll(ActionEvent event) throws Exception {

        try {

            Set s = new HashSet();
            s.addAll(this.getSelectedDTOS());
            if(isCheckAllFlag()) {
                if(getSelectedDTOS() != null) 
                    setSelectedDTOS(getMyTableData());
                }
                else {
                    unCheck();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setBugetProgramList(List bugetProgramList) {
        this.bugetProgramList = bugetProgramList;
    }

    public List getBugetProgramList() {
        if (bugetProgramList == null) {
            IMinistriesClient ministriesClient = 
                OrgClientFactory.getMinistriesClient();

            try {
                bugetProgramList = 
                        ministriesClient.getMinRelatedCurrentPrograms(getManagedConstantsBean().getMinCode());
            } catch (DataBaseException e) {
                e.printStackTrace();
                bugetProgramList = new ArrayList();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                bugetProgramList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                bugetProgramList = new ArrayList();
            }


        }

        return bugetProgramList;
    }

    public void setBudgetProgramValue(String budgetProgramValue) {
        this.budgetProgramValue = budgetProgramValue;
    }

    public String getBudgetProgramValue() {
        return budgetProgramValue;
    }

    public void setFrmBudgetType(List frmBudgetType) {
        this.frmBudgetType = frmBudgetType;
    }

    public List getFrmBudgetType() {
        if (frmBudgetType == null) {
            IBgtTypesClient iBgtTypesClient = 
                BgtClientFactory.getBgtTypesClient();
            try {
                frmBudgetType = iBgtTypesClient.getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
                frmBudgetType = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                frmBudgetType = new ArrayList();
            }
        }
        return frmBudgetType;
    }

    public void setBgtTypesDTOValue(String bgtTypesDTOValue) {
        this.bgtTypesDTOValue = bgtTypesDTOValue;
    }

    public String getBgtTypesDTOValue() {
        return bgtTypesDTOValue;
    }

    public void setWrkCenterList(List wrkCenterList) {
        this.wrkCenterList = wrkCenterList;
    }

    public List getWrkCenterList() {
        if (wrkCenterList == null) {
            IWorkCentersClient workCentersClient = 
                OrgClientFactory.getWorkCentersClient();


            try {
                wrkCenterList = //GN by Ashraf Gaber
                        workCentersClient.getAllByMinistry(OrgEntityKeyFactory.createMinistriesEntityKey(getManagedConstantsBean().getMinCode()));
                         //workCentersClient.getAllActiveAndPendingWorkcenters(getManagedConstantsBean().getMinCode());
                         System.out.println(getManagedConstantsBean().getMinCode());
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                wrkCenterList = new ArrayList();
            } catch (DataBaseException e) {
                e.printStackTrace();
                wrkCenterList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                wrkCenterList = new ArrayList();
            }
        }
        return wrkCenterList;
    }

    public void setSelectedWorkCentersValue(String selectedWorkCentersValue) {
        this.selectedWorkCentersValue = selectedWorkCentersValue;
    }

    public String getSelectedWorkCentersValue() {
        return selectedWorkCentersValue;
    }

    public void setToBudgetType(List toBudgetType) {
        this.toBudgetType = toBudgetType;
    }

    public List getToBudgetType() {
        if (toBudgetType == null) {
            IBgtTypesClient bgtTypesClient = 
                BgtClientFactory.getBgtTypesClient();
            try {
                if (!bgtTypesDTOValue.equals(defaultValue)) {
                    toBudgetType = 
                            bgtTypesClient.getCodeName(Long.parseLong(bgtTypesDTOValue));
                } else
                    toBudgetType = new ArrayList();

            } catch (DataBaseException e) {
                e.printStackTrace();
                toBudgetType = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                toBudgetType = new ArrayList();
            }
        }
        return toBudgetType;
    }

    public void setToBudgetTypeValue(String toBudgetTypeValue) {
        this.toBudgetTypeValue = toBudgetTypeValue;
    }

    public String getToBudgetTypeValue() {
        return toBudgetTypeValue;
    }


    public void setSearchNotDone(boolean searchNotDone) {
        this.searchNotDone = searchNotDone;
    }

    public boolean isSearchNotDone() {
        return searchNotDone;
    }

    public void setSrchAgainBtn(Boolean srchAgainBtn) {
        this.srchAgainBtn = srchAgainBtn;
    }

    public Boolean getSrchAgainBtn() {
        return srchAgainBtn;
    }
}
