package com.beshara.csc.hr.emp.presentation.backingbean.hiredecision;


import com.beshara.base.client.ServiceNotAvailableException;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata.WorkDataListBean;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.EmployeesClientImpl;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision.GovEmpMaintainBean;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyListBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.myfaces.custom.calendar.HtmlInputCalendar;


public class HireSearchListBean extends ManyToManyListBaseBean {
    
    private static final String COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_FULL_NAME =
        "INF_KWT_CITIZENS_RESIDENTS.FULL_NAME";
    private static final String COLUMN_NAME_HR_EMP_EMPLOYEES_CIVIL_ID = "HR_EMP_EMPLOYEES.CIVIL_ID";
    private static final String COLUMN_NAME_HR_EMP_EMPLOYEES_HIRE_DATE = "HR_EMP_EMPLOYEES.HIRE_DATE";
    private static final String COLUMN_NAME_HR_EMP_EMPLOYEES_MINISTRY_FILE_NO = "HR_EMP_EMPLOYEES.MINISTRY_FILE_NO";
    private static final String NAVIGATION_KEY = "hireSearchlist";
    private  String selectedEmp;
    private static final String BEAN_NAME = "hireSearchListBean";
    private static final String METHOD_NAME_RESTORE = "restorePage";
    IEmployeeSearchDTO searchDTO;
    private final Long DEWAN_CODE = new Long(13);

    private IEmpEmployeeSearchDTO empEmployeesSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
private String empHireTypesValue ="";
    List allList = new ArrayList();
    private List<IBasicDTO> firstLevelHireTypesList;
    List<IBasicDTO> selectedDTOs = null;
    List<IBasicDTO> result =new ArrayList<IBasicDTO>();
    List<String> sortingColumnList = new ArrayList<String>();
    
    private boolean showEnterSearch ;
    private WorkDataListBean workDataListBean = new WorkDataListBean();
    
   
    public HireSearchListBean() {
        

        setUsingPaging(true);
        setUsingBsnPaging(true);
        setPageDTO(EmpDTOFactory.createEmployeesDTO());
        
        super.setSelectedPageDTO(EmpDTOFactory.createEmployeesDTO());
        setClient((EmployeesClientImpl)EmpClientFactory.getEmployeesClient());
       
        setSaveSortingState(true);
      setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));
        empEmployeesSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        empEmployeesSearchDTO.setEmpHireStatus(IEMPConstants._EMP_ACTIVE_STATUS);
        empEmployeesSearchDTO.setActiveFlag(IEMPConstants._EMP_ACTIVE_STATUS);
        empEmployeesSearchDTO.setRecordDescCode(IEMPConstants._EMP_ACTIVE_STATUS); 
        
        setShowEnterSearch(true);
        
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowLookupEdit(false);
        app.setShowSearch(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowshortCut(false);
        app.setShowLookupAdd(false);
        app.setShowMasterDetail(false);
        app.setShowContent1(true);
        return app;
    }

    /**
     * Purpose: get all employees that are waiting for hiring decision
     * Creation/Modification History : creation
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  14-july-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void getAll() throws DataBaseException {
        setShowEnterSearch(false);
        
       if(!getEmpHireTypesValue().equals(""))
        empEmployeesSearchDTO.setEmpHireTypes(Long.valueOf(getEmpHireTypesValue()));
    
        if (isUsingPaging()) {
                generatePagingRequestDTO("hireSearchListBean", "getAllWithPaging", empEmployeesSearchDTO);
            }  
            
            
            //added by nora
            setSorting(false);

            resetPageIndex();
          //  setMyTableData(new ArrayList());
            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();
    }


    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {
        //return new PagingResponseDTO(getAllList());
        if(empEmployeesSearchDTO.getHireDateFrom()==null){
            generatePagingRequestDTO("0");
            return new PagingResponseDTO(new ArrayList());
        }
       IPagingResponseDTO bsnResponseDTO =  getSearchByEmpWithPaging(request);
          
        PagingResponseDTO pagingResponseDTO = null; 
        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());
            if (getCurrentPageIndex() == 1) {
                pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                getPagingRequestDTO().setParams(new Object[] { bsnResponseDTO.getCount() });
            } else {
                pagingResponseDTO.setTotalListSize(((Long)getPagingRequestDTO().getParams()[0]).intValue());
            }
            
        }
        return pagingResponseDTO;
        
        //return new PagingResponseDTO(new ArrayList());
    }

    public String viewEmpDetails() {
        try {
            IEmployeesDTO emp = (IEmployeesDTO)getSelectedDTOS().get(0);
            emp = (IEmployeesDTO)getClient().getById(emp.getCode());
            getIntegrationBean().reInitializeBean();
            getIntegrationBean().setBeanNameTo(GovEmpMaintainBean.BEAN_NAME);
            getIntegrationBean().setActionTo(GovEmpMaintainBean.METHOD_NAME_VIEW);
            getIntegrationBean().setNavgationCaseFrom(NAVIGATION_KEY);
            getIntegrationBean().getHmBaseBeanFrom().put(BEAN_NAME, evaluateValueBinding(BEAN_NAME));
            getIntegrationBean().setBeanNameFrom(BEAN_NAME);
            getIntegrationBean().setActionFrom(METHOD_NAME_RESTORE);
            getIntegrationBean().getHmObjects().put(GovEmpMaintainBean.MAP_KEY_CIVIL_ID, emp.getCode().getKey());
            GovEmpMaintainBean govEmpMaintainBean = (GovEmpMaintainBean)evaluateValueBinding("govEmpMaintainBean");
            govEmpMaintainBean.setTitlePageKey("Gov_emp_data_revision_title");
            return getIntegrationBean().goTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String restorePage() {
        return NAVIGATION_KEY;
    }

    public void setEmpEmployeesSearchDTO(IEmpEmployeeSearchDTO empEmployeesSearchDTO) {
        this.empEmployeesSearchDTO = empEmployeesSearchDTO;
    }

    public IEmpEmployeeSearchDTO getEmpEmployeesSearchDTO() {
        return empEmployeesSearchDTO;
    }
  
    public void reset() throws DataBaseException {
     
           empEmployeesSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();  
           
           empEmployeesSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
           empEmployeesSearchDTO.setEmpHireStatus(IEMPConstants._EMP_ACTIVE_STATUS);
           empEmployeesSearchDTO.setActiveFlag(IEMPConstants._EMP_ACTIVE_STATUS);
           empEmployeesSearchDTO.setRecordDescCode(IEMPConstants._EMP_ACTIVE_STATUS);
           empEmployeesSearchDTO.setEmpHireTypes(null);
           setEmpHireTypesValue("");
           getAll();
           setShowEnterSearch(true);
           setSelectedDTOS(null);
           setSelectedRadio("");
          
       }
    
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;
    private com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO = 
        new com.beshara.base.paging.impl.PagingRequestDTO();
    
    private com.beshara.base.paging.impl.PagingResponseDTO getSearchByEmpWithPaging(PagingRequestDTO pagingRequestDTO) {
        int pageIndex = getCurrentPageIndex();
       
        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));
        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));
        if (pageIndex == 1) {
            bsnPagingRequestDTO.setCountRequired(true);
        }
        if (isSorting()) {
            bsnPagingRequestDTO.setSortAscending(pagingRequestDTO.isSortAscending());
                
            //add ur sorting columns
            if (pagingRequestDTO.getBsnSortingColumnName()!=null){
                sortingColumnList=new ArrayList<String>();
            if (pagingRequestDTO.getBsnSortingColumnName().equals(COLUMN_NAME_HR_EMP_EMPLOYEES_CIVIL_ID)) {
                sortingColumnList.add("o.citizensResidentsEntity.civilId");
            } else  if(pagingRequestDTO.getBsnSortingColumnName().equals(COLUMN_NAME_HR_EMP_EMPLOYEES_MINISTRY_FILE_NO)) {
                sortingColumnList.add("o.ministryFileNo");
            }else if(pagingRequestDTO.getBsnSortingColumnName().equals(COLUMN_NAME_HR_EMP_EMPLOYEES_HIRE_DATE)) {
                sortingColumnList.add("o.hireDate");
            }else {
                sortingColumnList.add("o.citizensResidentsEntity.firstName");
                sortingColumnList.add("o.citizensResidentsEntity.secondName");
                sortingColumnList.add("o.citizensResidentsEntity.thirdName");
                sortingColumnList.add("o.citizensResidentsEntity.lastName");
            }
            }
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
             }
     //List stagesList = (List) pagingRequestDTO.getParams()[0];
      // IEmployeeSearchDTO searchDTO = EmpDTOFactory.createEmployeeSearchDTO();
        
     // empEmployeesSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
//      empEmployeesSearchDTO.setEmpHireStatus(IEMPConstants._EMP_ACTIVE_STATUS);
//      empEmployeesSearchDTO.setActiveFlag(IEMPConstants._EMP_ACTIVE_STATUS);
//      empEmployeesSearchDTO.setRecordDescCode(IEMPConstants._EMP_ACTIVE_STATUS);
        //empEmployeesSearchDTO.setEmpHireStages(stagesList);
       // setPagingRequestDTO(bsnPagingRequestDTO);
      
        // empEmployeesSearchDTO.setMinistryCode(getLoggedInMinistry()); 
        
    
        try {
              empEmployeesSearchDTO.setEmployment(true);
            //bsnPagingResponseDTO =(com.beshara.base.paging.impl.PagingResponseDTO)(EmpClientFactory.getEmployeesClient()).simpleSearchWithPaging(empEmployeesSearchDTO,bsnPagingRequestDTO);
            bsnPagingResponseDTO =(com.beshara.base.paging.impl.PagingResponseDTO)(EmpClientFactory.getEmployeesClient()).simpleSearchEmpWithPaging(empEmployeesSearchDTO,bsnPagingRequestDTO);
           result = bsnPagingResponseDTO.getBasicDTOList();
                
            bsnPagingResponseDTO.setBasicDTOList(result);
            
            } catch(NoResultException ne) {
            
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
         
        } catch (ServiceNotAvailableException e) {
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "ServiceNotAvailableException");
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (Throwable e) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        }
        return bsnPagingResponseDTO;
    }

    public Long getLoggedInMinistry(){
        return CSCSecurityInfoHelper.getLoggedInMinistryCode() == null ? DEWAN_CODE :
               CSCSecurityInfoHelper.getLoggedInMinistryCode();
    }
    
    public void filterEmpByHireDate() {


        //        generatePagingRequestDTO("0");
//        List<IBasicDTO> empList = new ArrayList<IBasicDTO>();
//        try {
//            empList =
//                    (List<IBasicDTO>)EmpClientFactory.getEmployeesClient().simpleSearchWithPaging(empEmployeesSearchDTO, (IPagingRequestDTO)getPagingRequestDTO());
//            //setMyTableData(empList);
//            setAllList(empList);
//        } catch (DataBaseException f) {
//            f.printStackTrace();
//            setAllList(new ArrayList());
//            //setMyTableData(new ArrayList());
//            
//        } catch (SharedApplicationException e) {
//            
//            setAllList(new ArrayList());
//            //setMyTableData(new ArrayList());
//        }
//        
//        setAllList(empList);
//        
//        setUpdateMyPagedDataModel(true);
//        setSelectedRadio("");

        
        try {
            getAll();
           
        } catch (DataBaseException e) {
           setAllList(new ArrayList());
        }
        
        
    }

    public void setFirstLevelHireTypesList(List<IBasicDTO> firstLevelHireTypesList) {
        this.firstLevelHireTypesList = firstLevelHireTypesList;
    }

    public List<IBasicDTO> getFirstLevelHireTypesList() {

        if (firstLevelHireTypesList == null) {
            try {
                firstLevelHireTypesList = EmpClientFactory.getHireTypesClient().getByTreeLevelHireTypes(IEMPConstants.EMP_HIRE_TYPE_TREE_LEVEL_ONE);
            } catch (DataBaseException e) {
                e.printStackTrace();
                firstLevelHireTypesList = new ArrayList<IBasicDTO>();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                firstLevelHireTypesList = new ArrayList<IBasicDTO>();
            }
        }
        return firstLevelHireTypesList;
    }

    public void setAllList(List allList) {
        this.allList = allList;
    }

    public String getColumnNameHR_EMP_EMPLOYEES_CIVIL_ID() {
        return COLUMN_NAME_HR_EMP_EMPLOYEES_CIVIL_ID;
    }

    public String getColumnNameHR_EMP_EMPLOYEES_MINISTRY_FILE_NO() {
        return COLUMN_NAME_HR_EMP_EMPLOYEES_MINISTRY_FILE_NO;
    }

    public String getColumnNameHR_EMP_EMPLOYEES_HIRE_DATE() {
        return COLUMN_NAME_HR_EMP_EMPLOYEES_HIRE_DATE;
    }

    public List getAllList() {
        return allList;
    }

    public String viewExperiences() {
   
        IEmployeesDTO emp = (IEmployeesDTO)getSelectedDTOS().get(0);
        getIntegrationBean().reInitializeBean();
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        workDataListBean.setCivilId(emp.getRealCivilId());
        workDataListBean.setCitizinFullName(((IKwtCitizensResidentsDTO)emp.getCitizensResidentsDTO()).getFullName());
        workDataListBean.setViewOnly(true);
        workDataListBean.setNavigationCase("hireSearchlist");
        workDataListBean.setBeanName(BEAN_NAME);
        workDataListBean.setBackAction("backFromViewExper");
        integrationBean.getHmBaseBeanFrom().put("workDataListBean", workDataListBean);
        integrationBean.getHmBaseBeanFrom().put(BEAN_NAME, this);

        return "workDataList";
    }
    public String backFromViewExper(){
        IntegrationBean integrationBean =IntegrationBean.getInstance();
        if (integrationBean != null && integrationBean.getHmBaseBeanFrom().size() > 0) {
            setEmpEmployeesSearchDTO(((HireSearchListBean)integrationBean.getHmBaseBeanFrom().get("hireSearchListBean")).getEmpEmployeesSearchDTO());
            setEmpHireTypesValue(((HireSearchListBean)integrationBean.getHmBaseBeanFrom().get("hireSearchListBean")).getEmpHireTypesValue());
            setShowEnterSearch(((HireSearchListBean)integrationBean.getHmBaseBeanFrom().get("hireSearchListBean")).isShowEnterSearch());
        }
        return "hireSearchlist";
    }
    public void setSelectedEmp(String selectedEmp) {
        this.selectedEmp = selectedEmp;
    }

    public String getSelectedEmp() {
        return selectedEmp;
    }

    public void setShowEnterSearch(boolean showEnterSearch) {
        this.showEnterSearch = showEnterSearch;
    }

    public boolean isShowEnterSearch() {
        return showEnterSearch;
    }


    public void setBsnPagingRequestDTO(com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO) {
        this.bsnPagingRequestDTO = bsnPagingRequestDTO;
    }

    public com.beshara.base.paging.impl.PagingRequestDTO getBsnPagingRequestDTO() {
        return bsnPagingRequestDTO;
    }

    public void setSortingColumnList(List<String> sortingColumnList) {
        this.sortingColumnList = sortingColumnList;
    }

    public List<String> getSortingColumnList() {
        return sortingColumnList;
    }

    public void setEmpHireTypesValue(String empHireTypesValue) {
        this.empHireTypesValue = empHireTypesValue;
    }

    public String getEmpHireTypesValue() {
        return empHireTypesValue;
    }

    public void setWorkDataListBean(WorkDataListBean workDataListBean) {
        this.workDataListBean = workDataListBean;
}

    public WorkDataListBean getWorkDataListBean() {
        return workDataListBean;
    }
}

