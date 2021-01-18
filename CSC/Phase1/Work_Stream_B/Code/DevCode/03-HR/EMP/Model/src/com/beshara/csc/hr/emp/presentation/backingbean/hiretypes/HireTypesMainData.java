package com.beshara.csc.hr.emp.presentation.backingbean.hiretypes;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.IConditionAppModulesClient;
import com.beshara.csc.gn.grs.business.client.IConditionsClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionRelationsDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.dto.ISearchConditionsDTO;
import com.beshara.csc.gn.grs.business.entity.ConditionsEntityKey;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.integration.business.joincond.ITargetForJoinConditionDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.TargetForJoinConditionDTO;
import com.beshara.csc.gn.grs.integration.presentation.backingbean.joincond.JoinConditionHelperBean;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.hiretypes.treelist.HireTypesTreeListBean;
import com.beshara.csc.hr.emp.presentation.backingbean.shared.HireTypeJoinCondHelperBean;
import com.beshara.csc.nl.job.business.dto.IJobCatLevelsDTO;
import com.beshara.csc.nl.org.business.client.IMinistriesClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IMinistrySearchCriteriaDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.MinistriesEntityKey;
import com.beshara.csc.nl.org.integration.presentation.backingbean.ministry.SearchMinistriesHelperBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


public class HireTypesMainData extends ManyToManyDetailsMaintain {

    private String publicCondition;
    private String searchTypeStr = "0";
    public static final String CONDITION_TARGET_BINDING = "hireTypesMainData.hireTypesTreeListBean.dtoDetails";
    private static final String SEARCH_TYPE_BY_CODE = "0";
    private static final String SEARCH_TYPE_BY_NAME = "1";
    private String errorMsgValue;
    private boolean codeNotFoundMsg;
    private boolean minCodeNotFound;
    //  private HireTypeJoinCondHelperBean jcHelper = HireTypeJoinCondHelperBean.getInstance();
    HireTypesTreeListBean hireTypesTreeListBean = (HireTypesTreeListBean)evaluateValueBinding("hireTypesTreeListBean");

    private SearchMinistriesHelperBean ministryHelper =
        new SearchMinistriesHelperBean("hireTypesMainData", null, true, null);

    private IMinistriesDTO minDTO;

    private IMinistriesDTO selectedMinDTO;
    private IConditionsDTO conditionsDTO;

    private long selectedTabregSer;

    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    private JoinConditionHelperBean jcHelper;

    public HireTypesMainData() {
        setLookupAddDiv("divCondition");
        setCustomDiv1("divSheardStyle1");
        setContent1Div("module_tabs_cont");
        setDivSearchMany("divSearchCustomized");
        setUsingPaging(true);
        setUsingBsnPaging(true);

        initMinistryDiv();
        initJCHelperBean();


    }

    public void condCode() throws DataBaseException, SharedApplicationException {

        ManyToManyMaintainBaseBean maintainBean =
            (ManyToManyMaintainBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
                                                                                                              "hireTypesMaintainBean" +
                                                                                                              "}").getValue(FacesContext.getCurrentInstance());
        IHireTypesDTO hireTypesDTO = (IHireTypesDTO)getClient().getById(this.getSelectedPageDTO().getCode());
        if (hireTypesDTO.getConditionsDTO() != null && !hireTypesDTO.getConditionsDTO().equals("")) {
            this.setConditionCode(hireTypesDTO.getConditionsDTO().getCode().getKey());
        }
        maintainBean.setPageDTO(hireTypesDTO);


        //        getSelectedPageDTO().setCode(jcHelper.getActiveCondRelation().getConditionsDTO().getCode());
        //        if(jcHelper.getActiveCondCode()!=null||jcHelper.getActiveCondCode()!=Long.parseLong(conditionCode))
        //            setConditionCode(jcHelper.getActiveCondRelation().getConditionsDTO().getCode().getKey());
        //
    }


    private void initJCHelperBean() {
        if (getJcHelper() == null) {
            setJcHelper(new JoinConditionHelperBean());
        }

    }

    /**
     * Purpose: this method handle show and hide divs
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description:
     * @return
     * @throws
     */
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        //app.setShowLookupAdd(true);

        app.setShowCustomDiv1(true);
        app.setManyToMany(true);
        app.setShowNavigation(true);
        app.setShowsteps(true);
        // app.setShowSearch(true);
        app.setShowIntegrationDiv1(true);

        app.setShowshortCut(false);
        return app;
    }

    public void scrollerAction(ActionEvent ae) {
        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1); ");
    }

    public void scrollerMinAction(ActionEvent ae) {
        super.changePageIndex(ae);
        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.divSearch); ");
    }

    /**
     * Purpose: set list Available
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description:
     * @return
     * @throws
     */
    public void getListAvailable() {
        //IConditionsClient iConditions = GrsClientFactory.getConditionsClient();
        IConditionAppModulesClient iConditions = GrsClientFactory.getConditionAppModulesClient();
        try {
            //  setAvailableDetails(iConditions.getCodeNameInCenter());
            setAvailableDetails(iConditions.getEmpConditions());
        } catch (DataBaseException e) {
            e.printStackTrace();
            setAvailableDetails(new ArrayList());
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setAvailableDetails(new ArrayList());
        }
    }

    public void setPublicCondition(String publicCondition) {
        this.publicCondition = publicCondition;
    }

    public void resetPublicConditionDiv() {
        try {
            cancelSearchAvailable();
            setSearchMode(false);
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }


    public void cancelAdd() {
        errorMsgValue = null;
    }

    /**
     * Purpose: get Slected DTOs from div
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description:
     * @return
     * @throws
     */
    public void addCondition() {
        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            IConditionsDTO conditionsDTO = GrsDTOFactory.createConditionsDTO();
            try {

                conditionsDTO =
                        (IConditionsDTO)GrsClientFactory.getConditionsClient().getByIdInCenter(((IConditionsDTO)getSelectedDTOS().get(0)).getCode());
                ((IHireTypesDTO)getMaintainBean().getPageDTO()).setConditionsDTO(conditionsDTO);
                setConditionCode(((IConditionsDTO)getSelectedDTOS().get(0)).getCode().getKey());
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                getSharedUtil().setErrMsgValue(e.getMessage());
            } catch (DataBaseException e) {
                e.printStackTrace();
                getSharedUtil().setErrMsgValue(e.getMessage());
            }
        }
    }

    public IntegrationBean getIntegrationBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (IntegrationBean)app.createValueBinding("#{integrationBean}").getValue(ctx);
    }

    public String getPublicCondition() {
        try{
        if (getIntegrationBean().getSelectedDTOTo() != null && getIntegrationBean().getSelectedDTOTo().size() != 0) {
            ((IHireTypesDTO)getMaintainBean().getPageDTO()).setConditionsDTO((IConditionsDTO)getIntegrationBean().getSelectedDTOTo().get(0));
            getIntegrationBean().reInitializeBean();
        }
        if (((IHireTypesDTO)getMaintainBean().getPageDTO()).getConditionsDTO() != null &&
            ((IHireTypesDTO)getMaintainBean().getPageDTO()).getConditionsDTO().getName() != null) {
            publicCondition = ((IHireTypesDTO)getMaintainBean().getPageDTO()).getConditionsDTO().getName();
        }
        return publicCondition;
        }
            catch (Exception e){
                e.printStackTrace();
            }
        return "";
    }

    public MaintainBean getMaintainBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (MaintainBean)app.createValueBinding("#{hireTypesMaintainBean}").getValue(ctx);

    }


    /**
     * Purpose: search using in div method
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description:
     * @return
     * @throws
     */
    public void removeCurrentFromAvailable() {

        System.out.println("removing current");
        if (isSearchMode() && (getSearchString() != null && !getSearchString().equals(""))) {
            setSelectedRadio("");
            try {
                ISearchConditionsDTO searchConditionsDTO = GrsDTOFactory.createSearchConditionsDTO();

                if (searchTypeStr.toString().equals(SEARCH_TYPE_BY_CODE)) {
                    searchConditionsDTO.setConditionCode(Long.parseLong(getSearchString()));
                } else {
                    searchConditionsDTO.setConditionName(formatSearchString(getSearchString()));
                }
                prepareSearchConditionDTO(searchConditionsDTO);

                List<IBasicDTO> list = GrsClientFactory.getConditionsClient().search(searchConditionsDTO);
                setAvailableDetails(list);

            } catch (SharedApplicationException e) {
                e.printStackTrace();
                setAvailableDetails(new ArrayList());
            } catch (DataBaseException e) {
                e.printStackTrace();
                setAvailableDetails(new ArrayList());
            } catch (Exception e) {
                e.printStackTrace();
                setAvailableDetails(new ArrayList());
            }
        }
    }

    private void prepareSearchConditionDTO(ISearchConditionsDTO searchConditionsDTO) {
        Long[] longArray = new Long[1];
        longArray[0] = new Long(-100);
        searchConditionsDTO.setExceptConditionsCode(longArray);
        searchConditionsDTO.setAppModuleCode(IEMPConstants.APP_MODULE_CODE_EMP);
    }

    public String addNewCondition() {
        getIntegrationBean().reInitializeBean();
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        getIntegrationBean().setRenderedBackButton(true);
        getIntegrationBean().setNavgationCaseFrom("hiretypesmaindata");
        getIntegrationBean().setNavgationCaseTo("condition_list");
        getIntegrationBean().getHmBaseBeanFrom().put("hireTypesMainData",
                                                     app.createValueBinding("#{hireTypesMainData}").getValue(ctx));
        getIntegrationBean().getHmBaseBeanFrom().put("hireTypesMaintainBean", getMaintainBean());

        return getIntegrationBean().goTO();
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

    /************************************* Start Add Ministry DIV  *****************************************************/
    public static final String BEAN_NAME = "hireTypesMainData";
    private String gov_Flag = ISystemConstant.GOVERNMENT_FLAG.toString();
    private String nonGov_Flag = ISystemConstant.NON_GOVERNMENT_FLAG.toString();
    private String selectedGovFlag = gov_Flag;
    private String selectedCategoryType = "";
    private List categoryList = new ArrayList();
    private HtmlDataScroller dataScroller = new HtmlDataScroller();
    private HtmlDataScroller dataScroller2 = new HtmlDataScroller();
    private String ministrySearchStr; //for search in ministry div
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;
    private boolean mustDecision;
    private String ministryCode;
    private String conditionCode;

    public void setMinistryCode(String ministryCode) {
        this.ministryCode = ministryCode;
    }

    public String getMinistryCode() {
        return ministryCode;
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

    public void fillMinById() {
        try {
            if (ministryCode != null && !ministryCode.equals("")) {
                IMinistriesClient ministryClient = OrgClientFactory.getMinistriesClient();
                IMinistriesDTO ministryDTO =
                    (IMinistriesDTO)ministryClient.getById(new MinistriesEntityKey(Long.parseLong(ministryCode)));
                setMinCodeNotFound(false);
                if (ministryDTO != null) {
                    setMinCodeNotFound(false);
                    List minDTOs = new ArrayList<IMinistriesDTO>();
                    minDTOs.add(ministryDTO);
                    ministryHelper.setExcludedMinistriesList(minDTOs);
                    ((IHireTypesDTO)getMaintainBean().getPageDTO()).setMinistriesDTO(ministryDTO);

                    System.out.println(((IHireTypesDTO)getMaintainBean().getPageDTO()).getMinistriesDTO().getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            setMinCodeNotFound(true);
            ((IHireTypesDTO)getMaintainBean().getPageDTO()).setMinistriesDTO(null);
        }
    }

    public void getConditionById() {
        try {

            IConditionsClient conditionsClient = GrsClientFactory.getConditionsClient();
            IConditionsDTO conditionsDTO =
                (IConditionsDTO)conditionsClient.getById(new ConditionsEntityKey(Long.parseLong(conditionCode)));
            setCodeNotFoundMsg(false);
            if (conditionsDTO == null || conditionsDTO.equals("")) {
                setCodeNotFoundMsg(true);
            }
            ((IHireTypesDTO)getMaintainBean().getPageDTO()).setConditionsDTO(conditionsDTO);
        } catch (DataBaseException e) {
            e.printStackTrace();
            setCodeNotFoundMsg(true);
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setCodeNotFoundMsg(true);
        }
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

    public void addMinistry() {
        setMinistrySearchStr(null);
        super.setSuccess(false);
        super.setShowErrorMsg(false);

        if (getSelectedDTOS() != null && getSelectedMinDTOS().size() != 0) {
            ((IHireTypesDTO)getMaintainBean().getPageDTO()).setMinistriesDTO((IMinistriesDTO)getSelectedMinDTOS().get(0));
            setMinistryCode(((IMinistriesDTO)getSelectedMinDTOS().get(0)).getCode().getKey());
        }


    }
    private List selectedMinDTOS = new ArrayList();

    public void selectedRadioButtonBase(ValueChangeEvent event) throws Exception {

        if (isUsingPaging()) {
            getPagingBean().setPreUpdatedDataModel(true);
        }

        if (event.getNewValue() != null) {

            setSelectedMinDTOS(new ArrayList<IBasicDTO>());
            IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();
            this.getSelectedMinDTOS().add(selected);

        }

    }

    /************************************* End Add Ministry DIV  *****************************************************/
    public void setMinistrySearchStr(String ministrySearchStr) {
        this.ministrySearchStr = ministrySearchStr;
    }

    public String getMinistrySearchStr() {
        return ministrySearchStr;
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

    public void setSelectedMinDTOS(List selectedMinDTOS) {
        this.selectedMinDTOS = selectedMinDTOS;
    }

    public List getSelectedMinDTOS() {
        return selectedMinDTOS;
    }

    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }

    public String getConditionCode() {
        return conditionCode;
    }

    public void setCodeNotFoundMsg(boolean codeNotFoundMsg) {
        this.codeNotFoundMsg = codeNotFoundMsg;
    }

    public boolean isCodeNotFoundMsg() {
        return codeNotFoundMsg;
    }

    public void setMinCodeNotFound(boolean minCodeNotFound) {
        this.minCodeNotFound = minCodeNotFound;
    }

    public boolean isMinCodeNotFound() {
        return minCodeNotFound;
    }

    public void setMustDecision(boolean mustDecision) {
        ((IHireTypesDTO)getMaintainBean().getPageDTO()).setDecisionMust(mustDecision == true ? 1L : 0L);
        this.mustDecision = mustDecision;
    }

    public boolean isMustDecision() {
        if (((IHireTypesDTO)getMaintainBean().getPageDTO()).getDecisionMust() == 1)
            return true;
        else
            return false;
    }

    //    public void setJcHelper(HireTypeJoinCondHelperBean jcHelper) {
    //        this.jcHelper = jcHelper;
    //    }
    //
    //    public HireTypeJoinCondHelperBean getJcHelper() {
    //        return jcHelper;
    //    }

    public void setHireTypesTreeListBean(HireTypesTreeListBean hireTypesTreeListBean) {
        this.hireTypesTreeListBean = hireTypesTreeListBean;
    }

    public HireTypesTreeListBean getHireTypesTreeListBean() {
        return hireTypesTreeListBean;
    }


    //new addeed

    private void initMinistryDiv() {

        ministryHelper.setReturnMethodName("hireTypesMainData.saveLinkage");
        ministryHelper.setPreOpenMethodName("hireTypesMainData.preLinkMinistry");


    }

    public void preLinkMinistry() {
        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("hireTypesMaintainBean");

        IHireTypesDTO selected = (IHireTypesDTO)maintainBean.getPageDTO();
        selectedMinDTO = selected.getMinistriesDTO();
        if (minDTO != null || selectedMinDTO != null) {
            List minDTOs = new ArrayList<IMinistriesDTO>();
            minDTOs.add(0, selectedMinDTO);
            if (minDTO != null)
                minDTOs.add(1, minDTO);
            ministryHelper.setExcludedMinistriesList(minDTOs);

        }
        initMinistryDiv();
    }

    public void preOpenJoinDiv() {
        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("hireTypesMaintainBean");

        IHireTypesDTO _dto = (IHireTypesDTO)maintainBean.getPageDTO();
        ITargetForJoinConditionDTO target = new TargetForJoinConditionDTO();
        selectedTabregSer = _dto.getTabrecSerial();
        IEntityKey ek = new EntityKey();
        if (_dto.getCode().getKey() != null) {
            ek.setKey(_dto.getCode().getKey());
        }
        if (_dto.getCode().getKeys() != null) {
            ek.setKeys(_dto.getCode().getKeys());
        }
        target.setCode(ek);
//        if (_dto.getName() != null) {
//            target.setName(_dto.getName());
//        }
        target.setFromDate(SharedUtils.getCurrentDate());
        target.setTabrecSerial(_dto.getTabrecSerial());
        target.setJCTableName("HR_EMP_HIRE_TYPES");
        jcHelper.setTargetForJoinConditionDTO(target);
        jcHelper.setAllowMultiCondition(false);
        jcHelper.setContainerBeanName("hireTypesMainData");
        
        jcHelper.setTransactionName((getSharedUtil().messageLocator(BUNDLE_NAME, "module_div_title")) +
                                    "-" + _dto.getName());
        //  List l= jcHelper.getMyTableData();
        //    setConditionsDTO((IConditionsDTO)l);

        jcHelper.openJoinDiv();

    }
    // to save selected Ministry

    public void saveLinkage() {


        if (ministryHelper.getSelectedDTOSList().size() == 1) {


            minDTO = (IMinistriesDTO)ministryHelper.getSelectedDTOSList().get(0);

            MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("hireTypesMaintainBean");

            ((IHireTypesDTO)maintainBean.getPageDTO()).setMinistriesDTO(minDTO);


            this.setMinistryCode(minDTO.getCode().getKey());

        }

        if (ministryHelper.getSelectedDTOSList().size() > 1) {

            // To Do no muliple Selection validation error
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "cantNotChooseMoreThanOne"));


        }

    }


    public void setMinistryHelper(SearchMinistriesHelperBean ministryHelper) {
        this.ministryHelper = ministryHelper;
    }

    public SearchMinistriesHelperBean getMinistryHelper() {
        return ministryHelper;
    }


    public void setMinDTO(IMinistriesDTO minDTO) {
        this.minDTO = minDTO;
    }

    public IMinistriesDTO getMinDTO() {
        return minDTO;
    }

    public void setSelectedMinDTO(IMinistriesDTO selectedMinDTO) {
        this.selectedMinDTO = selectedMinDTO;
    }

    public IMinistriesDTO getSelectedMinDTO() {
        return selectedMinDTO;
    }


    public void setJcHelper(JoinConditionHelperBean jcHelper) {
        this.jcHelper = jcHelper;
    }

    public JoinConditionHelperBean getJcHelper() {
        return jcHelper;
    }

    public void setSelectedTabregSer(long selectedTabregSer) {
        this.selectedTabregSer = selectedTabregSer;
    }

    public long getSelectedTabregSer() {
        return selectedTabregSer;
    }

    public void setConditionsDTO(IConditionsDTO conditionsDTO) {
        this.conditionsDTO = conditionsDTO;
    }

    public IConditionsDTO getConditionsDTO() {
        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("hireTypesMaintainBean");
        IHireTypesDTO _dto = (IHireTypesDTO)maintainBean.getPageDTO();

        if (_dto.getCode().getKey() != null ) {
            IHireTypesDTO hireTypesDTO = null;

            try {
                hireTypesDTO = (IHireTypesDTO)EmpClientFactory.getHireTypesClient().getById(_dto.getCode());
                if (hireTypesDTO.getConditionsDTO() != null) {
                    conditionsDTO = hireTypesDTO.getConditionsDTO();
                    setConditionCode(hireTypesDTO.getConditionsDTO().getCode().getKey());
                    String condName = hireTypesDTO.getConditionsDTO().getName();
                    setPublicCondition(condName);
                   
                } else {
                    conditionsDTO = GrsDTOFactory.createConditionsDTO();
                    conditionsDTO.setCode( GrsEntityKeyFactory.createConditionsEntityKey());
                }


            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
        return conditionsDTO;
    }


}

