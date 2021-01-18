package com.beshara.csc.hr.emp.presentation.backingbean.hireprocedures;

import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IHireProceduresClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.ISearchHireProceduresDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IMinistrySearchCriteriaDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;


public class HireProceduresSearchBean extends LookupMaintainBaseBean {


    private String searchTypeStr = "0";
    private static final String SEARCH_TYPE_BY_CODE = "0";
    private static final String SEARCH_TYPE_BY_NAME = "1";
    private String errorMsgValue;


    private String selectedGender = "";

    private String selectedNationalityType = "";

    private List genderTypeList = new ArrayList();


    private String emptyItemSelection = 
        String.valueOf(ISystemConstant.VIRTUAL_VALUE.longValue());

    private String gov_Flag = ISystemConstant.GOVERNMENT_FLAG.toString();
    private String nonGov_Flag = 
        ISystemConstant.NON_GOVERNMENT_FLAG.toString();
    private String selectedGovFlag = gov_Flag;

    private String selectedGenderType = "";
    private String selectedCategoryType = "";
    private Long selectedMinistry;
    private List categoryList = new ArrayList();
    private boolean simpleSearchFlag = true;
    private String stringSearchType = "false";

    private String ministrySearchStr; //for search in ministry div
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;

    private String kuwaiti = 
        String.valueOf(ISystemConstant.NATIONALITY_KUWAITI.longValue());
    private String nonKuwaiti = 
        String.valueOf(ISystemConstant.NATIONALITY_NON_KUWAITI.longValue());
    private String nonSpecified = 
        String.valueOf(ISystemConstant.NATIONALITY_NON_SPECIFIED.longValue());

    ISearchHireProceduresDTO searchHireProceduresDTO = 
        EmpDTOFactory.createSearchHireProceduresDTO();

    public HireProceduresSearchBean() {
        setPageDTO(EmpDTOFactory.createHireProceduresDTO());
        if (getPageDTO() != null && 
            ((IHireProceduresDTO)getPageDTO()).getMinistriesDTO() == null)
            ((IHireProceduresDTO)getPageDTO()).setMinistriesDTO(OrgDTOFactory.createMinistriesDTO());
        setClient((IHireProceduresClient)EmpClientFactory.getHireProceduresClient());
//        setDivSearch("divSearchCustomized");
        setDivSearchMany("divSearchCustomized");
        setLookupAddDiv("publishEditDiv");
        setUsingPaging(true);
        setUsingBsnPaging(true);

    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();

        app.setShowContent1(true);
        app.setShowSearch(true);

        return app;
    }

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
            categoryList = 
                    (OrgClientFactory.getCatsClient()).getCatsByGovFlag(Long.parseLong(selectedGovFlag));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            categoryList = new ArrayList();
        } catch (DataBaseException e) {
            e.printStackTrace();
            categoryList = new ArrayList();
        }

    }

    public void addMinistry() {
        setMinistrySearchStr(null);
        super.setSuccess(false);
        super.setShowErrorMsg(false);


        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            ((IHireProceduresDTO)getPageDTO()).setMinistriesDTO((IMinistriesDTO)getSelectedDTOS().get(0));
            selectedMinistry = 
                    Long.parseLong((getSelectedDTOS().get(0)).getCode().getKey());
        }
    }

    public void cancelAdd() {
        setMinistrySearchStr(null);
        if (isSearchMode()) {
            cancelSearch();
            setSearchMode(false);
        }

    }

    public String searchHireProcedures() throws DataBaseException, 
                                                NoResultException, 
                                                SharedApplicationException {
        System.out.println("Calling search()...");


        HireProceduresListBean hireProceduresListBean = 
            (HireProceduresListBean)evaluateValueBinding("hireProceduresListBean");
        hireProceduresListBean.setSearchMode(true);
        hireProceduresListBean. setRendrAdvancedSearch(true);
        try {
            if (searchHireProceduresDTO != 
                null) { // it means that user in advanced search mode 

                if (selectedMinistry != null) {
                    if ((!selectedMinistry.equals("")) && 
                        (!selectedMinistry.equals(emptyItemSelection))) { //set ministry code

                        searchHireProceduresDTO.setMinCode(selectedMinistry);
                    }
                }

                if (selectedGenderType != null && 
                    !(selectedGenderType.equals("") || 
                      (selectedGenderType.equals(emptyItemSelection)))) {

                    searchHireProceduresDTO.setGenderType(Long.parseLong(selectedGenderType));
                }

                if (selectedNationalityType != null) {
                    if (!selectedNationalityType.equals("") && 
                        (!selectedNationalityType.equals(emptyItemSelection))) { // set nationality type 
                        searchHireProceduresDTO.setNationalityType(Long.parseLong(selectedNationalityType));
                    }
                }

                hireProceduresListBean.setMyTableData((getClient()).search(searchHireProceduresDTO));
             
            }

        } catch (ItemNotFoundException e) { //this means no search results found
            //setSimpleSearchFlag(true);
            hireProceduresListBean.setMyTableData(new ArrayList());
            //this.setStringSearchType("false");
            //setSearchQuery("");
            e.printStackTrace();

        }

        catch (NoResultException ne) { //this means no search results found
            hireProceduresListBean.setMyTableData(new ArrayList());
            hireProceduresListBean.setStringSearchType("false");
            //setSearchQuery("");
            ne.printStackTrace();
        }

        catch (Exception db) {
            hireProceduresListBean.setStringSearchType("false");
            //setSearchQuery("");
            ErrorDisplay errorDisplay = 
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);

            db.printStackTrace();
        }

        if (getSelectedDTOS() != null)
            hireProceduresListBean.getSelectedDTOS().clear();
        if (getHighlightedDTOS() != null)
            getHighlightedDTOS().clear();
        hireProceduresListBean.repositionPage(0);
        //this.setStringSearchType("false");
        //setSearchQuery("");
        //setSimpleSearchFlag(true);
        //setStringGovFlag("");
        //setSelectedCategory("");
        //setMinistryList(new ArrayList(0));
        //setSelectedGenderType("");
        hireProceduresListBean.setSearchHireProceduresDTO(EmpDTOFactory.createSearchHireProceduresDTO());

        return "list";
    }

    public void clearDTO() {
        setSelectedGender("");
        setSelectedGenderType("");
        setSelectedNationalityType("");
        setSelectedGovFlag(gov_Flag);
        getPageDTO().setName("");
        ((IHireProceduresDTO)getPageDTO()).getMinistriesDTO().setName("");
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
                    generatePagingRequestDTO("hireProceduresSearchBean", 
                                             "filterDataTableByCategoryWithPaging");
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

    public void scrollerAction(ActionEvent ae) {

        super.changePageIndex(ae);
        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.divSearch);settingFoucsAtDivSearch();");
    }

    public void searchForMinistry() {

        if (isUsingPaging()) {

            if (!getSelectedCategoryType().equals(getVirtualConstValue()) && 
                !getSelectedCategoryType().equals("")) {
                setSearchMode(true);
                setSelectedRadio("");
                super.repositionPage(0);
                setCurrentPageIndex(1);
                generatePagingRequestDTO("hireProceduresSearchBean", 
                                         "searchForMinistryWithPaging");
                Object[] params = new Object[] { getSelectedCategoryType() };
                getPagingRequestDTO().setParams(params);
            }
        }
    }

    public PagingResponseDTO searchForMinistryWithPaging(PagingRequestDTO pagingRequest) {

        IPagingResponseDTO bsnResponseDTO = 
            searchBsnForMinistryWithPaging(pagingRequest);

        PagingResponseDTO pagingResponseDTO = null;
        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = 
                    new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());

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

        IMinistrySearchCriteriaDTO searchDTO = 
            OrgDTOFactory.createMinistrySearchCriteriaDTO();
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
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
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

    public void filterDataByCategory(ActionEvent ae) {
        setMinistrySearchStr(null);
        setSearchMode(false);
        setMyTableData(null);
    }

    public void getAll() throws DataBaseException {

        if (isUsingPaging()) {
            generatePagingRequestDTO("hireProceduresSearchBean", 
                                     "getAllWithPaging");
            //resetPageIndex();
        } else {
            setMyTableData(new ArrayList());
        }
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO pagingRequest) {

        return new PagingResponseDTO(new ArrayList());

    }

    public PagingResponseDTO filterDataTableByCategoryWithPaging(PagingRequestDTO pagingRequest) {

        IPagingResponseDTO bsnResponseDTO = 
            getDataByCategoryIdWithPaging(pagingRequest);

        PagingResponseDTO pagingResponseDTO = null;
        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = 
                    new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());

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

        IMinistrySearchCriteriaDTO searchDTO = 
            OrgDTOFactory.createMinistrySearchCriteriaDTO();
        searchDTO.setCategoryCode(Long.parseLong(catId));
        searchDTO.setPagingRequestDTO(bsnPagingRequestDTO);

        try {
            bsnPagingResponseDTO = 
                    (com.beshara.base.paging.impl.PagingResponseDTO)OrgClientFactory.getMinistriesClient().getAllByCategoryWithPagingInCenter(searchDTO);
        } catch (NoResultException ne) { //this means no search results found
            bsnPagingResponseDTO = 
                    new com.beshara.base.paging.impl.PagingResponseDTO();
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

    public void setSelectedCategoryType(String selectedCategoryType) {
        this.selectedCategoryType = selectedCategoryType;
    }

    public String getSelectedCategoryType() {
        return selectedCategoryType;
    }

    public void setSelectedGovFlag(String selectedGovFlag) {
        this.selectedGovFlag = selectedGovFlag;
    }

    public String getSelectedGovFlag() {
        return selectedGovFlag;
    }

    public void setGov_Flag(String gov_Flag) {
        this.gov_Flag = gov_Flag;
    }

    public String getGov_Flag() {
        return gov_Flag;
    }

    public void setCategoryList(List categoryList) {
        this.categoryList = categoryList;
    }

    public List getCategoryList() {

        if (categoryList == null || categoryList.size() == 0) {
            try {

                categoryList = 
                        (OrgClientFactory.getCatsClient()).getCatsByGovFlag(Long.parseLong(selectedGovFlag));
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

    public void setSelectedNationalityType(String selectedNationalityType) {
        this.selectedNationalityType = selectedNationalityType;
    }

    public String getSelectedNationalityType() {
        return selectedNationalityType;
    }

    public void setSelectedGender(String selectedGender) {
        this.selectedGender = selectedGender;
    }

    public String getSelectedGender() {
        return selectedGender;
    }

    public void setNonGov_Flag(String nonGov_Flag) {
        this.nonGov_Flag = nonGov_Flag;
    }

    public String getNonGov_Flag() {
        return nonGov_Flag;
    }

    public void setStringSearchType(String stringSearchType) {
        this.stringSearchType = stringSearchType;
    }

    public String getStringSearchType() {
        return stringSearchType;
    }

    public void setSimpleSearchFlag(boolean simpleSearchFlag) {
        this.simpleSearchFlag = simpleSearchFlag;
    }

    public boolean isSimpleSearchFlag() {
        return simpleSearchFlag;
    }

    public void setSearchHireProceduresDTO(ISearchHireProceduresDTO searchHireProceduresDTO) {
        this.searchHireProceduresDTO = searchHireProceduresDTO;
    }

    public ISearchHireProceduresDTO getSearchHireProceduresDTO() {
        return searchHireProceduresDTO;
    }

    public void setSelectedMinistry(Long selectedMinistry) {
        this.selectedMinistry = selectedMinistry;
    }

    public Long getSelectedMinistry() {
        return selectedMinistry;
    }

    public void setSelectedGenderType(String selectedGenderType) {
        this.selectedGenderType = selectedGenderType;
    }

    public String getSelectedGenderType() {
        return selectedGenderType;
    }

    public void setEmptyItemSelection(String emptyItemSelection) {
        this.emptyItemSelection = emptyItemSelection;
    }

    public String getEmptyItemSelection() {
        return emptyItemSelection;
    }


    public void setKuwaiti(String kuwaiti) {
        this.kuwaiti = kuwaiti;
    }

    public String getKuwaiti() {
        return kuwaiti;
    }

    public void setNonKuwaiti(String nonKuwaiti) {
        this.nonKuwaiti = nonKuwaiti;
    }

    public String getNonKuwaiti() {
        return nonKuwaiti;
    }

    public void setNonSpecified(String nonSpecified) {
        this.nonSpecified = nonSpecified;
    }

    public String getNonSpecified() {
        return nonSpecified;
    }

    public void setGenderTypeList(List genderTypeList) {
        this.genderTypeList = genderTypeList;
    }

    public List getGenderTypeList() {

        if (genderTypeList == null || genderTypeList.size() == 0) {
            try {
                genderTypeList = 
                        (InfClientFactory.getGenderTypesClient()).getCodeNameInCenter();
            } catch (DataBaseException e) {
                genderTypeList = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                genderTypeList = new ArrayList();
                e.printStackTrace();
            }
        }
        return genderTypeList;
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

    public String getSearchTypeByCode() {
        return SEARCH_TYPE_BY_CODE;
    }

    public String getSearchTypeByName() {
        return SEARCH_TYPE_BY_NAME;
    }

    public void setMinistrySearchStr(String ministryName) {
        this.ministrySearchStr = ministryName;
    }

    public String getMinistrySearchStr() {
        return ministrySearchStr;
    }
}
