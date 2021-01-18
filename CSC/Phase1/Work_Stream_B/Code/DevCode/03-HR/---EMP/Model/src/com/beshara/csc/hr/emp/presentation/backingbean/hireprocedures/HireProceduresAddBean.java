package com.beshara.csc.hr.emp.presentation.backingbean.hireprocedures;


import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IHireProceduresClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
import com.beshara.csc.nl.org.business.client.IMinistriesClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IMinistrySearchCriteriaDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.MinistriesEntityKey;
import com.beshara.csc.nl.org.integration.presentation.backingbean.ministry.SearchMinistriesHelperBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


public class HireProceduresAddBean extends LookupMaintainBaseBean {

    private static final String BEAN_NAME = "hireProceduresAddBean";
    private String searchTypeStr = "0";
    private static final String SEARCH_TYPE_BY_CODE = "0";
    private static final String SEARCH_TYPE_BY_NAME = "1";
    private String errorMsgValue;
    //new added by m.abdelsabour
    private SearchMinistriesHelperBean ministryHelper =
        new SearchMinistriesHelperBean("hireProceduresAddBean", "integrationDiv1");


    //    private String selectedGender = "";
    //    private List genderList = new ArrayList();
    //    private String selectedNationalityType = "";
    //    private String nationality_Kuwaiti =
    //        ISystemConstant.NATIONALITY_KUWAITI.toString();
    //    private String nationality_NonKuwaiti =
    //        ISystemConstant.NATIONALITY_NON_KUWAITI.toString();
    //    private String nationality_NonSpecified =
    //        ISystemConstant.NATIONALITY_NON_SPECIFIED.toString();

    private String gov_Flag = ISystemConstant.GOVERNMENT_FLAG.toString();
    private String nonGov_Flag = ISystemConstant.NON_GOVERNMENT_FLAG.toString();
    private String selectedGovFlag = gov_Flag;
    private String selectedCategoryType = "";
    private List categoryList = new ArrayList();
    
    private HtmlDataScroller dataScroller = new HtmlDataScroller();
    private String ministrySearchStr; //for search in ministry div
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;

    private String ministryCode;

    //new added that value will be execluded after selection from integration DIV
   private IMinistriesDTO minDTO;
   
    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";


    public HireProceduresAddBean() {
        setPageDTO(EmpDTOFactory.createHireProceduresDTO());
        if (getPageDTO() != null && ((IHireProceduresDTO)getPageDTO()).getMinistriesDTO() == null)
            ((IHireProceduresDTO)getPageDTO()).setMinistriesDTO(OrgDTOFactory.createMinistriesDTO());
        setClient((IHireProceduresClient)EmpClientFactory.getHireProceduresClient());
        setLookupAddDiv("publishEditDiv");
        //        setDivSearch("divSearchCustomized");
        setDivSearchMany("divSearchCustomized");

        setUsingPaging(true);
        setUsingBsnPaging(true);
        getSharedUtil().setNoOfTableRows(3);
        initMinistryDiv();
    }

    public void scrollerAction(ActionEvent ae) {

        super.changePageIndex(ae);
        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.divSearch);settingFoucsAtDivSearch();");
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        //        app.setShowSearch(true);
        app.setShowIntegrationDiv1(true);
        return app;
    }


    /**
     * Purpose:get All categories by selected Gov_Flag
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  17/Jul/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:get All categories by selected Gov_Flag
     */
    public void chooseCategoryByGovFlag(ActionEvent ae) {

        super.repositionPage(0);
        setSelectedRadio("");

        setSearchMode(false);
        cancelSearch();

        if (selectedGovFlag == null || selectedGovFlag.equals("")) {
            setSelectedGovFlag(gov_Flag);
        }
        setSelectedCategoryType(null);
        setMinistrySearchStr(null);
        setMyTableData(null);

        try {
            getAll();
            categoryList = (OrgClientFactory.getCatsClient()).getCatsByGovFlag(Long.parseLong(selectedGovFlag));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            categoryList = new ArrayList();
        } catch (DataBaseException e) {
            e.printStackTrace();
            categoryList = new ArrayList();
        }

    }

    public void getMinById() {
        try {

            IMinistriesClient ministryClient = OrgClientFactory.getMinistriesClient();
            IMinistriesDTO ministryDTO =
                (IMinistriesDTO)ministryClient.getById(new MinistriesEntityKey(Long.parseLong(ministryCode)));

            ((IHireProceduresDTO)getPageDTO()).setMinistriesDTO(ministryDTO);
            
            if(ministryDTO!=null){
            List minDTOs = new ArrayList<IMinistriesDTO>();
            minDTOs.add( ministryDTO);
            
            ministryHelper.setExcludedMinistriesList(minDTOs); 
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (ItemNotFoundException e) {
            ((IHireProceduresDTO)getPageDTO()).setMinistriesDTO(null);
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param ce
     */
    public void filterDataTableByCategory(ValueChangeEvent ce) throws DataBaseException {

        String compId;
        compId = ((HtmlSelectOneMenu)ce.getSource()).getId();
        String changedValue = (String)ce.getNewValue();
        System.out.println(compId);
        setMinistrySearchStr(null);
        if (isSearchMode() == true) {
            setSearchMode(false);
            cancelSearch();
        }
        if (compId.equals("search_first_inputTxt")) {
            if (!(changedValue.equals(""))) {
                if (isUsingPaging()) {
                    generatePagingRequestDTO(BEAN_NAME, "filterDataTableByCategoryWithPaging");
                    Object[] params = new Object[] { changedValue };
                    getPagingRequestDTO().setParams(params);
                    resetPageIndex();
                } else {
                    setMyTableData(new ArrayList());
                }

            } else {
                getAll();
            }
        }

        getSelectedDTOS().clear();
    }


    public void filterDataByCategory(ActionEvent ae) {
        setMinistrySearchStr(null);
        setSearchMode(false);
        setMyTableData(null);
    }

    public void getAll() throws DataBaseException {

        if (isUsingPaging()) {
            generatePagingRequestDTO(BEAN_NAME, "getAllWithPaging");
            //resetPageIndex();
        } else {
            setMyTableData(new ArrayList());
        }
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO pagingRequest) {

        return new PagingResponseDTO(new ArrayList());

    }

    public PagingResponseDTO filterDataTableByCategoryWithPaging(PagingRequestDTO pagingRequest) {

        IPagingResponseDTO bsnResponseDTO = getDataByCategoryIdWithPaging(pagingRequest);

        PagingResponseDTO pagingResponseDTO = null;
        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());

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

    private com.beshara.base.paging.impl.PagingResponseDTO getDataByCategoryIdWithPaging(PagingRequestDTO pagingRequestDTO) {

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
            sortingColumnList.add(pagingRequestDTO.getBsnSortingColumnName());
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }

        String catId = (String)pagingRequestDTO.getParams()[0];

        IMinistrySearchCriteriaDTO searchDTO = OrgDTOFactory.createMinistrySearchCriteriaDTO();
        searchDTO.setCategoryCode(Long.parseLong(catId));
        searchDTO.setPagingRequestDTO(bsnPagingRequestDTO);

        try {
            bsnPagingResponseDTO =
                    (com.beshara.base.paging.impl.PagingResponseDTO)OrgClientFactory.getMinistriesClient().getAllByCategoryWithPagingInCenter(searchDTO);
        } catch (NoResultException ne) { //this means no search results found
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (SharedApplicationException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        } catch (DataBaseException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        }

        return bsnPagingResponseDTO;
    }


    public void setSelectedGovFlag(String selectedGovFlag) {
        this.selectedGovFlag = selectedGovFlag;
    }

    /**
     * Purpose:To add hire procedure common part between sane and saveANDNEW
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  17/Jul/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:add hire procedure
     */
    public void add() throws DataBaseException {
        SharedUtilBean shared_util = getSharedUtil();
        //        if (selectedGender != null && !selectedGender.equals("") &&
        //            selectedNationalityType != null &&
        //            !selectedNationalityType.equals("")) {
        //            IGenderTypesDTO genderTypeDTO =
        //                InfDTOFactory.createGenderTypesDTO();
        //            genderTypeDTO.setCode(InfEntityKeyFactory.createGenderTypesEntityKey(Long.parseLong(selectedGender)));
        //            ((IHireProceduresDTO)getPageDTO()).setGenderTypesDTO(genderTypeDTO);
        //            ((IHireProceduresDTO)getPageDTO()).setNationalityType(Long.parseLong(selectedNationalityType));

        try {
            ((IHireProceduresDTO)getPageDTO()).setStatus(1L);
            Calendar cal = Calendar.getInstance();
            ((IHireProceduresDTO)getPageDTO()).setFromDate(new java.sql.Date(cal.getTime().getTime()));
            setPageDTO(getClient().add(getPageDTO()));
            super.setSuccess(true);
        } catch (SharedApplicationException e) {
            e.printStackTrace();
           this.setShowErrorMsg(true);
            if(e instanceof InvalidDataEntryException){
                super.setSuccess(false);
                this.setErrorMsg(getSharedUtil().messageLocator(BUNDLE_NAME,"name_duplicated"));
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                              "name_duplicated"));
            }else{
            this.setErrorMsg("FailureInAdd");
                    shared_util.setErrMsgValue(e.getMessage());    
                }
            
            clearDTO();
        }
        //        }
    }

    /**
     * Purpose:To add hire procedure
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  17/Jul/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:add hire procedure
     */
    public String saveHireProcedure() {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            add();
            FacesContext fc = FacesContext.getCurrentInstance();
            Application app = fc.getApplication();
            HireProceduresListBean hireProceduresListBean =
                (HireProceduresListBean)app.createValueBinding("#{hireProceduresListBean}").getValue(fc);
            hireProceduresListBean.setMyTableData(hireProceduresListBean.getMyTableData());
            hireProceduresListBean.setMyDataTable(hireProceduresListBean.getMyDataTable());

            if (getPageDTO() != null && getPageDTO().getCode() != null) {

                hireProceduresListBean.getHighlightedDTOS().add(getPageDTO());
                hireProceduresListBean.getPageIndex((String)getPageDTO().getCode().getKey());
                shared_util.setSuccessMsgValue("SuccessAdds");
            }
            reInitializePageDTO();
        } catch (DataBaseException e) {
            e.printStackTrace();
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            shared_util.setErrMsgValue("FailureInAdd");

        } catch (Exception e) {
            e.printStackTrace();
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
        }
        return "list";

    }


    /**
     * Purpose:To add hire procedure and reset the add page "SAVE AND NEW"
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  17/Jul/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:add hire procedure
     */
    public void saveNewHireProcedure() {
        try {
            add();
            setMinistryCode(null);
            clearDTO();
            // Black Horse [e.saber]
            // clear error msg in order not to appear in the main page (list)..
            SharedUtilBean shared_util = getSharedUtil();
            if(shared_util.getErrMsgValue() != null && !shared_util.getErrMsgValue().equals("")){
                shared_util.setErrMsgValue(null);
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            clearDTO();
        } catch (Exception e) {
            e.printStackTrace();
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            clearDTO();
        }


    }
    public String goBack(){
            FacesContext fc = FacesContext.getCurrentInstance();
            Application app = fc.getApplication();
            HireProceduresListBean hireProceduresListBean =
                (HireProceduresListBean)app.createValueBinding("#{hireProceduresListBean}").getValue(fc);
        try {
            hireProceduresListBean.setMyTableData(hireProceduresListBean.getMyTableData());
        hireProceduresListBean.setMyDataTable(hireProceduresListBean.getMyDataTable());
             if (getPageDTO() != null && getPageDTO().getCode() != null) {
            
            hireProceduresListBean.getHighlightedDTOS().add(getPageDTO());
       
            hireProceduresListBean.getPageIndex((String)getPageDTO().getCode().getKey());
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        return "list";
        }

    private void clearDTO() {
        //        setSelectedGender("");
        //        setSelectedNationalityType("");
        setSelectedGovFlag(gov_Flag);
        setSelectedCategoryType(null);
        setMyTableData(new ArrayList());
        getPageDTO().setName("");
        ((IHireProceduresDTO)getPageDTO()).getMinistriesDTO().setName("");
    }

    /**
     * Purpose:To  Cancel SearchMode  and reset searchMode,ministrySearchStr
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  17/Jul/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: cancel Search mode
     */
    public void cancelSearch() {
        if (selectedCategoryType != null && !selectedCategoryType.equals("")) {
            setMinistrySearchStr(null);
            searchForMinistry();
            setSearchMode(false);
            setSelectedRadio("");
        } else {
            setMyTableData(new ArrayList());
        }
        setSearchType(0);
        setSearchMode(false);
        setSelectedRadio("");
        setSearchQuery("");

    }

    /**
     * Purpose: search for a ministry
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa  Omar
     * 1.2 - Date:  17/Jul/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: is used to search for a ministry by ministrySearchStr and categoryCode
     *
     */
    public void searchForMinistry() {

        if (isUsingPaging()) {

            if (!getSelectedCategoryType().equals(getVirtualConstValue()) && !getSelectedCategoryType().equals("")) {
                setSearchMode(true);
                setSelectedRadio("");
                super.repositionPage(0);
                setCurrentPageIndex(1);
                generatePagingRequestDTO(BEAN_NAME, "searchForMinistryWithPaging");
                Object[] params = new Object[] { getSelectedCategoryType() };
                getPagingRequestDTO().setParams(params);
            }


        }

    }

    public PagingResponseDTO searchForMinistryWithPaging(PagingRequestDTO pagingRequest) {

        IPagingResponseDTO bsnResponseDTO = searchBsnForMinistryWithPaging(pagingRequest);

        PagingResponseDTO pagingResponseDTO = null;
        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());

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


    private com.beshara.base.paging.impl.PagingResponseDTO searchBsnForMinistryWithPaging(PagingRequestDTO pagingRequestDTO) {

        int pageIndex = getCurrentPageIndex();

        com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO =
            new com.beshara.base.paging.impl.PagingRequestDTO();

        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));

        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));
        if (pageIndex == 1) {
            bsnPagingRequestDTO.setCountRequired(true);
        }
        String catId = (String)pagingRequestDTO.getParams()[0];

        IMinistrySearchCriteriaDTO searchDTO = OrgDTOFactory.createMinistrySearchCriteriaDTO();
        searchDTO.setCategoryCode(Long.parseLong(catId));

        if (ministrySearchStr != null) {
            if (searchTypeStr.toString().equals(SEARCH_TYPE_BY_CODE)) {
                searchDTO.setMinistryCode(Long.parseLong(ministrySearchStr));
            } else {
                searchDTO.setMinistryName(ministrySearchStr);
            }
        }

        searchDTO.setPagingRequestDTO(bsnPagingRequestDTO);
        if (!isSearchMode()) {
            setMinistrySearchStr(null);
        }
        try {
            bsnPagingResponseDTO =
                    (com.beshara.base.paging.impl.PagingResponseDTO)OrgClientFactory.getMinistriesClient().getAllByCategoryAndNameWithPaging(searchDTO);
        } catch (NoResultException ne) { //this means no search results found
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (SharedApplicationException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        } catch (DataBaseException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        }

        return bsnPagingResponseDTO;
    }


    /**
     * Purpose:To add the selected ministry  to the hire procedure
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  17/Jul/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:add the selected ministry  to the hire procedure
     */
    public void addMinistry() {
        setMinistrySearchStr(null);
        super.setSuccess(false);
        super.setShowErrorMsg(false);

        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            ((IHireProceduresDTO)getPageDTO()).setMinistriesDTO((IMinistriesDTO)getSelectedDTOS().get(0));
            setMinistryCode(getSelectedDTOS().get(0).getCode().getKey());
        }
    }


    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
    }

    public void setMinistrySearchStr(String ministryName) {
        this.ministrySearchStr = ministryName;
    }

    public String getMinistrySearchStr() {
        return ministrySearchStr;
    }

    /**
     * Purpose:cancel adding ministry
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  17/Jul/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:cancel adding ministry and close ministry div without selecting any ministry
     */
    public void cancelAdd() {
        setMinistrySearchStr(null);
        if (isSearchMode()) {
            cancelSearch();
            setSearchMode(false);
        }

    }

    //    public void setNationality_Kuwaiti(String nationality_Kuwaiti) {
    //        this.nationality_Kuwaiti = nationality_Kuwaiti;
    //    }
    //
    //    public String getNationality_Kuwaiti() {
    //        return nationality_Kuwaiti;
    //    }
    //
    //    public void setNationality_NonKuwaiti(String nationality_NonKuwaiti) {
    //        this.nationality_NonKuwaiti = nationality_NonKuwaiti;
    //    }
    //
    //    public String getNationality_NonKuwaiti() {
    //        return nationality_NonKuwaiti;
    //    }
    //
    //    public void setNationality_NonSpecified(String nationality_NonSpecified) {
    //        this.nationality_NonSpecified = nationality_NonSpecified;
    //    }
    //
    //    public String getNationality_NonSpecified() {
    //        return nationality_NonSpecified;
    //    }
    //
    //
    //    public void setSelectedGender(String selectedGender) {
    //        this.selectedGender = selectedGender;
    //    }

    //    public String getSelectedGender() {
    //        return selectedGender;
    //    }

    public void setSelectedCategoryType(String selectedCategoryType) {
        this.selectedCategoryType = selectedCategoryType;
    }

    public String getSelectedCategoryType() {
        return selectedCategoryType;
    }

    //    public void setGenderList(List genderList) {
    //        this.genderList = genderList;
    //    }

    //    public List getGenderList() {
    //        if (genderList == null || genderList.size() == 0) {
    //            try {
    //                genderList =
    //                        (InfClientFactory.getGenderTypesClient()).getCodeNameInCenter();
    //            } catch (DataBaseException e) {
    //                e.printStackTrace();
    //                genderList = new ArrayList();
    //            }
    //        }
    //        return genderList;
    //    }

    //    public void setSelectedNationalityType(String selectedNationalityType) {
    //        this.selectedNationalityType = selectedNationalityType;
    //    }
    //
    //    public String getSelectedNationalityType() {
    //        return selectedNationalityType;
    //    }


    public String getSelectedGovFlag() {
        return selectedGovFlag;
    }

    public void setGov_Flag(String gov_Flag) {
        this.gov_Flag = gov_Flag;
    }

    public String getGov_Flag() {
        return gov_Flag;
    }

    public void setNonGov_Flag(String nonGov_Flag) {
        this.nonGov_Flag = nonGov_Flag;
    }

    public String getNonGov_Flag() {
        return nonGov_Flag;
    }

    public void setCategoryList(List categoryList) {
        this.categoryList = categoryList;
    }

    public List getCategoryList() {

        if (categoryList == null || categoryList.size() == 0) {
            try {

                categoryList = (OrgClientFactory.getCatsClient()).getCatsByGovFlag(Long.parseLong(selectedGovFlag));
                setMinistrySearchStr(null);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                categoryList = new ArrayList();
            } catch (DataBaseException e) {
                e.printStackTrace();
                categoryList = new ArrayList();
            }
        }
        return categoryList;
    }

    public void openAddMinistryDiv() {
        selectedCategoryType = "";
        selectedGovFlag = gov_Flag;
        cancelSearch();
        try {
            getAll();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public String getSearchTypeByCode() {
        return SEARCH_TYPE_BY_CODE;
    }

    public String getSearchTypeByName() {
        return SEARCH_TYPE_BY_NAME;
    }

    public void setSearchTypeStr(String searchTypeStr) {
        this.searchTypeStr = searchTypeStr;
    }

    public String getSearchTypeStr() {
        return searchTypeStr;
    }

    public void setErrorMsgValue(String errorMsgValue) {
        this.errorMsgValue = errorMsgValue;
    }

    public String getErrorMsgValue() {
        return errorMsgValue;
    }

    public void setMinistryCode(String ministryCode) {
        this.ministryCode = ministryCode;
    }

    public String getMinistryCode() {
        return ministryCode;
    }


    //new added to apply integration of ministry div

    public void setMinistryHelper(SearchMinistriesHelperBean ministryHelper) {
        this.ministryHelper = ministryHelper;
    }

    public SearchMinistriesHelperBean getMinistryHelper() {
        return ministryHelper;
    }


    private void initMinistryDiv() {

        ministryHelper.setReturnMethodName("hireProceduresAddBean.saveLinkage");
        ministryHelper.setPreOpenMethodName("hireProceduresAddBean.preLinkMinistry");


    }

    public void preLinkMinistry() {
        if (minDTO != null) {
            List minDTOs = new ArrayList<IMinistriesDTO>();
            minDTOs.add(0, minDTO);
            ministryHelper.setExcludedMinistriesList(minDTOs);

        }
        initMinistryDiv();
    }


    // to save selected Ministry

    public void saveLinkage() {


        if (ministryHelper.getSelectedDTOSList().size() == 1) {


            minDTO = (IMinistriesDTO)ministryHelper.getSelectedDTOSList().get(0);

            ((IHireProceduresDTO)getPageDTO()).setMinistriesDTO(minDTO);
            this.setMinistryCode(minDTO.getCode().getKey());
        }
        
        if (ministryHelper.getSelectedDTOSList().size() > 1) {
            
         // To Do no muliple Selection validation error
         getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                       "cantNotChooseMoreThanOneforProc"));    
            
            
            
        }

    }


    public void setMinDTO(IMinistriesDTO minDTO) {
        this.minDTO = minDTO;
    }

    public IMinistriesDTO getMinDTO() {
        return minDTO;
    }


   
}
