package com.beshara.csc.hr.emp.presentation.backingbean.employmentcycle;


import com.beshara.base.client.ServiceNotAvailableException;
import com.beshara.base.deploy.SessionBeanProviderException;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.datascroller.ScrollerActionEvent;


public class EmployeeListOfValues extends LOVBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private String empListOfValuesStyle = "empListOfValuesStyle";
    private boolean searchAtAllEmployees;
    private Long ministryCode;
    private boolean cantLocateSession;

    /**
     * @author TechnicalTeam[H.Omar]
     * @since 13/10/2015
     * @Note  CSC-13831 :
     * bsnPagingResponseDTO it holds results returned from DB paged.
     * empEmployeeSearchDTO parameter passed to search method , it holds search criteria passed from Calling bean.
     */
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;
    private IEmployeeSearchDTO empEmployeeSearchDTO ;
    private boolean useCustomSearch = false;

    /**
     * @author TechnicalTeam[H.Omar]
     * @since 13/10/2015
     * @Note  CSC-13831 : it enforce presentation scroller , and bsnPaging.
     */
    public EmployeeListOfValues() {

        super.setMyTableData(new ArrayList());
        super.setSelectedDTOS(new ArrayList<IBasicDTO>());
        super.setSelectedRadio("");
        super.setSearchTyp("1");
        super.setSearchQuery("");
        super.setSearchMode(false);
        super.getOkCommandButton().setReRender("continerDiv,SaveButton");
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {
        return new PagingResponseDTO(new ArrayList());
    }

    public void openLovDiv(ActionEvent ae) {
        Boolean manyToMany = (Boolean)evaluateValueBinding("appMainLayout.manyToMany");
        String beanNameBindingKey = "pageBeanName";
        if (manyToMany != null && manyToMany) {
            beanNameBindingKey = "detailBeanName";
        }

        BaseBean currentBaseBean = (BaseBean)evaluateValueBinding(beanNameBindingKey);
        currentBaseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lovEmp);");


        if (isUsingPaging() && ae != null && ae instanceof ScrollerActionEvent) {
            super.changePageIndex(ae);
        }
    }

    /**by Ashraf Gaber to reset LOV attributes*/
    public void resetData() {
        super.resetData();
        setCantLocateSession(false);
    }

    public String searchLovDiv() {
        setSelectedRadio("");
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        Object[] params = new Object[2];
        params[0] = getSearchTyp();
        params[1] = getSearchQuery();
        Object searchType=params[0];
        Object searchQuery=params[1];
        setSearchMode(true);
        resetPageIndex();
        if (isUsingPaging()) {
            setUpdateMyPagedDataModel(true);
        }
        IEmpCandidatesClient empCandidatesClient = EmpClientFactory.getEmpCandidatesClient();
        if (useCustomSearch && !"".equals(getSearchMethod()) && getSearchMethod() != null) {
            resetPageIndex();
            if (searchQuery != null && !searchQuery.toString().equals("") && searchType != null &&
                !searchType.equals("")) {
                super.setSearchMode(true);
                if (searchType.toString().equals("0")){
                    empEmployeeSearchDTO.setCivilId(Long.valueOf(searchQuery.toString()));
                    empEmployeeSearchDTO.setName(null);
                }  else if (searchType.toString().equals("1")){
                   empEmployeeSearchDTO.setName(searchQuery.toString());
                   empEmployeeSearchDTO.setCivilId(null);
                 }
            
                try {

                    setMyTableData(empCandidatesClient.getAllRequests(empEmployeeSearchDTO));
                        setOldPageIndex(0);
                        setCurrentPageIndex(1);

                  
                } catch (Exception e) {
                    e.printStackTrace();
                    super.setMyTableData(new ArrayList());

                    cantLocateSession = true;
                    super.setSearchMode(false);
                } 
            } else {
                super.setErrorMsgValue(messageLocator("com.beshara.jsfbase.csc.msgresources.global", "missingField"));
                super.setSearchMode(false);
            }

            super.repositionPage(0);
            super.setSelectedDTOS(new ArrayList<IBasicDTO>(0));
            return "";
        } else {
            resetPageIndex();
            setUsingBsnPaging(true);
            setUsingPaging(true);
            searchForEmployee(getSearchTyp(), getSearchQuery());
            return "";
        }

    }

    public String cancelSearchLovDiv() {
        setSearchMode(false);
        setSelectedRadio("");
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        setSearchQuery("");
        setSearchTyp("1");
        empEmployeeSearchDTO.setName(null);
        empEmployeeSearchDTO.setCivilId(null);
        //empEmployeeSearchDTO = EmpDTOFactory.createEmployeeSearchDTO();
        setCantLocateSession(false);

        if (isUsingPaging()) {
            getPagingBean().updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 0));
        }

        if (!"".equals(getCancelSearchMethod()) && getCancelSearchMethod() != null) {
            return (String)executeMethodBinding(getCancelSearchMethod(), null);
        } else {
            super.setMyTableData(new ArrayList());
            super.setSelectedDTOS(new ArrayList<IBasicDTO>());
        }


        return "";
    }

    //    public String cancelSearchLovDiv() {
    //        try {
    //            super.setMyTableData(new ArrayList());
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            super.setMyTableData(new ArrayList());
    //        }
    //        return "";
    //    }

    /**
     * @author TechnicalTeam[H.Omar]
     * @since 13/10/2015
     * @Note  CSC-13831 : check if usginPaging so set required paging configuration parameters
     */
    public void searchForEmployee(Object searchType, Object searchQuery) {

        if (searchQuery != null && !searchQuery.toString().equals("") && searchType != null &&
            !searchType.equals("")) {
            super.setSearchMode(true);
            if (searchType.toString().equals("0")){
                empEmployeeSearchDTO.setCivilId(Long.valueOf(searchQuery.toString()));
                empEmployeeSearchDTO.setName(null);
            }  else if (searchType.toString().equals("1")){
               empEmployeeSearchDTO.setName(searchQuery.toString());
               empEmployeeSearchDTO.setCivilId(null);
             }
        
            try {


                    setUpdateMyPagedDataModel(true);

                    setPagingRequestDTO(new PagingRequestDTO("filterSearchByEmpWithPaging"));


                    setOldPageIndex(0);
                    setCurrentPageIndex(1);

              
            } catch (SessionBeanProviderException e) {
                e.printStackTrace();
                super.setMyTableData(new ArrayList());

                cantLocateSession = true;
                super.setSearchMode(false);
            } 
        } else {
            super.setErrorMsgValue(messageLocator("com.beshara.jsfbase.csc.msgresources.global", "missingField"));
            super.setSearchMode(false);
        }

        super.repositionPage(0);
        super.setSelectedDTOS(new ArrayList<IBasicDTO>(0));

    }

    /**
     * @author TechnicalTeam[H.Omar]
     * @since 13/10/2015
     * @Note  CSC-13831 : check if usginPaging so set required paging configuration parameters
     */
    public PagingResponseDTO filterSearchByEmpWithPaging(PagingRequestDTO pagingRequest) {

        IPagingResponseDTO bsnResponseDTO = getSearchByEmpWithPaging(pagingRequest);
        PagingResponseDTO pagingResponseDTO = null;

        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());
            if (getCurrentPageIndex() == 1) {
                pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                //                pagingRequest.setParams(new Object[] { bsnResponseDTO.getCount() });
                getPagingRequestDTO().setParams(new Object[] { bsnResponseDTO.getCount() });
            } else {
                pagingResponseDTO.setTotalListSize(((Long)getPagingRequestDTO().getParams()[0]).intValue());
            }
        }
        return pagingResponseDTO;
    }

    /**
     * @author TechnicalTeam[H.Omar]
     * @since 13/10/2015
     * @Note  CSC-13831 : configure bsnPagingRequestDTO with required parameters used for business paging ,
     *  and invoke simpleSearchBasicWithPaging from empClient
     */
    private com.beshara.base.paging.impl.PagingResponseDTO getSearchByEmpWithPaging(PagingRequestDTO pagingRequestDTO) {

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
            //add ur sorting columns
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }
        try {
            
            empEmployeeSearchDTO.setPagingRequestDTO(bsnPagingRequestDTO);
            bsnPagingResponseDTO =
                    (com.beshara.base.paging.impl.PagingResponseDTO)EmpClientFactory.getEmpCandidatesClient().getByHireStageWithPaging(empEmployeeSearchDTO);


        } catch (NoResultException ne) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (ServiceNotAvailableException e) {
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "ServiceNotAvailableException");
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (Throwable e) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        }

        return bsnPagingResponseDTO;
    }


    public String messageLocator(String basename, String key) {
        ResourceBundle bundle =
            ResourceBundle.getBundle(basename, FacesContext.getCurrentInstance().getViewRoot().getLocale());
        return bundle.getString(key);
    }


    public void setEmpListOfValuesStyle(String empListOfValuesStyle) {
        this.empListOfValuesStyle = empListOfValuesStyle;
    }

    public String getEmpListOfValuesStyle() {
        return empListOfValuesStyle;
    }


    public void setSearchAtAllEmployees(boolean searchAtAllEmployees) {
        this.searchAtAllEmployees = searchAtAllEmployees;
    }

    public boolean isSearchAtAllEmployees() {
        return searchAtAllEmployees;
    }

    public void setMinistryCode(Long ministryCode) {
        this.ministryCode = ministryCode;
    }

    public Long getMinistryCode() {
        return ministryCode;
    }

    public void setCantLocateSession(boolean cantLocateSession) {
        this.cantLocateSession = cantLocateSession;
    }

    public boolean isCantLocateSession() {
        return cantLocateSession;
    }

 

    public void setUseCustomSearch(boolean useCustomSearch) {
        this.useCustomSearch = useCustomSearch;
    }

    public boolean isUseCustomSearch() {
        return useCustomSearch;
    }

    public void setEmpEmployeeSearchDTO(IEmployeeSearchDTO empEmployeeSearchDTO) {
        this.empEmployeeSearchDTO = empEmployeeSearchDTO;
    }

    public IEmployeeSearchDTO getEmpEmployeeSearchDTO() {
        return empEmployeeSearchDTO;
    }
}
